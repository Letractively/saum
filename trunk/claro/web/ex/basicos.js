var NUM_DIGITOS_CPF  = 11;
var NUM_DIGITOS_CNPJ = 14;
var NUM_DGT_CNPJ_BASE = 8;
var DataInicial;

String.prototype.lpad = function(pSize, pCharPad) {
    var str = this;
    var dif = pSize - str.length;
    var ch = String(pCharPad).charAt(0);
    for (; dif>0; dif--) str = ch + str;
    return (str);
} //String.lpad

String.prototype.trim = function() {
    return this.replace(/^\s*/, "").replace(/\s*$/, "");
} //String.trim

function Trim(str){return str.replace(/^\s+|\s+$/g,"");}

function isValidMail() {
    parte1 = form1.mailCliente.value.indexOf("@");
    parte2 = form1.mailCliente.value.indexOf(".");
    parte3 = form1.mailCliente.value.length;
    if (!(parte1 >= 1 && parte2 >= 1 && parte3 >= 9)) {
        alert ("Este campo não é obrigatório mas deve conter um endereço de e-mail correto se for preenchê-lo!");
        form1.mailCliente.focus();
        return false;
    }
}

function formataPorc(campo)
{
	if (!campo.value)
	{
		campo.value='0';
	}
    if (campo.value > 100)
    {
    	window.alert('Porcentagem Inválida!!');
    	campo.focus();
    	campo.value='0';
    }
}

function formatCurrency(num) {
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
    num = "0";

    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
    cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
    num = num.substring(0,num.length-(4*i+3))+''+
    num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + '' + num + ',' + cents);
}

function unformatNumber(pNum) {
    return String(pNum).replace(/\D/g, "").replace(/^0+/, "");
} //unformatNumber

function formatCpfCnpj(pCpfCnpj, pUseSepar, pIsCnpj) {
    if (pIsCnpj==null) pIsCnpj = false;
    if (pUseSepar==null) pUseSepar = true;
    var maxDigitos = pIsCnpj? NUM_DIGITOS_CNPJ: NUM_DIGITOS_CPF;
    var numero = unformatNumber(pCpfCnpj);

    numero = numero.lpad(maxDigitos, '0');
    if (!pUseSepar) return numero;

    if (pIsCnpj) {
        reCnpj = /(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})$/;
        numero = numero.replace(reCnpj, "$1.$2.$3/$4-$5");
    } else {
        reCpf  = /(\d{3})(\d{3})(\d{3})(\d{2})$/;
        numero = numero.replace(reCpf, "$1.$2.$3-$4");
    }
    return numero;
} //formatCpfCnpj

function dvCpfCnpj(pEfetivo, pIsCnpj) {
    if (pIsCnpj==null) pIsCnpj = false;
    var i, j, k, soma, dv;
    var cicloPeso = pIsCnpj? NUM_DGT_CNPJ_BASE: NUM_DIGITOS_CPF;
    var maxDigitos = pIsCnpj? NUM_DIGITOS_CNPJ: NUM_DIGITOS_CPF;
    var calculado = formatCpfCnpj(pEfetivo, false, pIsCnpj);
    calculado = calculado.substring(2, maxDigitos);
    var result = "";

    for(j = 1; j <= 2; j++) {
        k = 2;
        soma = 0;
        for (i = calculado.length-1; i >= 0; i--) {
            soma += (calculado.charAt(i) - '0') * k;
            k = (k-1) % cicloPeso + 2;
        }
        dv = 11 - soma % 11;
        if (dv > 9) dv = 0;
        calculado += dv;
        result += dv
    }
    return result;
} //dvCpfCnpj

function isCpf(pCpf) {
    var numero = formatCpfCnpj(pCpf, false, false);
    var base = numero.substring(0, numero.length - 2);
    var digitos = dvCpfCnpj(base, false);
    var algUnico, i;

    // Valida dígitos verificadores
    if (numero != base + digitos) return false;

    /* Não serão considerados válidos os seguintes CPF:
    * 000.000.000-00, 111.111.111-11, 222.222.222-22, 333.333.333-33, 444.444.444-44,
    * 555.555.555-55, 666.666.666-66, 777.777.777-77, 888.888.888-88, 999.999.999-99.
    */
    algUnico = true;
    for (i=1; i<NUM_DIGITOS_CPF; i++)
    {
        algUnico = algUnico && (numero.charAt(i-1) == numero.charAt(i));
    }
    return (!algUnico);
} //isCpf

function isCnpj(pCnpj) {
    var numero = formatCpfCnpj(pCnpj, false, true);
    var base = numero.substring(0, NUM_DGT_CNPJ_BASE);
    var ordem = numero.substring(NUM_DGT_CNPJ_BASE, 12);
    var digitos = dvCpfCnpj(base + ordem, true);
    var algUnico;

    if (numero != base + ordem + digitos) return false;

    algUnico = numero.charAt(0) != '0';
    for (i=1; i<NUM_DGT_CNPJ_BASE; i++) algUnico = algUnico && (numero.charAt(i-1) == numero.charAt(i));
    if (algUnico) return false;

    if (ordem == "0000") return false;
    return (base == "00000000" || parseInt(ordem, 10) <= 300 || base.substring(0, 3) != "000");
} //isCnpj

function isCpfCnpj(campo) {	
    var numero = campo.value.replace(/\D/g, "");
    var retorno = false;
    if (numero.length == 11){
        if (isCpf(numero)) {
            campo.value = numero.substr(0,3) + '.' + numero.substr(3,3) + '.' + numero.substr(6,3) + '-' + numero.substr(9,2);
            retorno = true;
        }
	} else if(numero.length == 14){
		if (isCnpj(numero)) {
			campo.value = numero.substr(0,2) + '.' + numero.substr(2,3) + '.' + numero.substr(5,3) + '/' + numero.substr(8,4)+ '-' + numero.substr(12,numero.length-12);
			retorno = true;
		}
	}
    return retorno;
}
/*########################################################################################################################################
  #  USO:  onBlur='if ( (this.value) && (!isCpfCnpj(this)) ) { window.alert("C.P.F./C.N.P.J. Invalido!"); this.focus();}' maxlength='18' #
  ########################################################################################################################################*/

function isCpfCnpjWithLabel(campo) {
    var Label = document.getElementById("LabelCPF_CNPJ");
    var numero = campo.value.replace(/\D/g, "");
    var retorno = false;

    if (numero.length >= 11) {
        if (isCnpj(numero)) {
            campo.value = numero.substr(0,2) + '.' + numero.substr(2,3) + '.' + numero.substr(5,3) + '/' + numero.substr(8,4)+ '-' + numero.substr(12,numero.length-12);
            retorno = true;
            Label.innerHTML = "C.N.P.J.:";
        }

        if (isCpf(numero)) {
            campo.value = numero.substr(0,3) + '.' + numero.substr(3,3) + '.' + numero.substr(6,3) + '-' + numero.substr(9,2);
            retorno = true;
            Label.innerHTML = "C.P.F.:";
        }
    } else {
        Label.innerHTML = "C.P.F / C.N.P.J.:";
    }
    return retorno;
}
/*###############################################################################################################################################
#  USO:  onBlur='if ( (this.value) && (!isCpfCnpjWithLabel(this)) ) { window.alert("C.P.F./C.N.P.J. Invalido!"); this.focus();}' maxlength='18' #
#  Criar uma tag "span" com id igual a "LabelCPF_CNPJ" (<span id="LabelCPF_CNPJ">C.P.F / C.N.P.J.:</span>) no lugar do Label orginal            #
###############################################################################################################################################*/

function verificaCheckBox(formulario, nomeCampo, qtd) {
    var marcados = 0;
    var campo = formulario.elements[nomeCampo];
    if (campo.length == undefined) {
        if (campo.checked) marcados+= 1;
    } else {
        for (var i = 0; i < campo.length; i++)
        if (campo[i].checked) marcados+= 1;
    }
    return (marcados == qtd)?0:((marcados > qtd)?1:-1);
}


function pegaNoh(object, id) {
    do {
        if (object.id == id) break;
    } while (object = object.parentNode);
    return object;
}

function msgAlerta(matriz) {
    switch (matriz.length) {
        case 1:
            return "O Campo "+matriz[0]+" precisa ser preenchido!";
            break;
        case 2:
            return "Os Campos "+matriz[1]+" e "+matriz[0]+" precisam ser preenchidos!";
            break;
        default:
            var campos = matriz[(matriz.length - 1)];
            for (i = (matriz.length - 2); i > 0; i--) {
                campos+= ", "+matriz[i];
            }
            campos+= " e "+matriz[0];
            return "Os Campos "+campos+" precisam ser preenchidos!";
    }
}

function verificaForm(form) {    
	var pass       = true;
    var campos     = new Array();
    var cnt    = 0;
    for (i = (form.length - 1); i >= 0; i--){
		var tempobj=form.elements[i];
		if (tempobj.required == "yes"){
        	if (((tempobj.type == "text" || tempobj.type == "textarea" || tempobj.type == "password" || tempobj.type == "file")&& ( (tempobj.value == '') || (tempobj.value.replace(/^\s+|\s+$/g,"").length == 0 ) ) ) )
            {
                try{
                    pass = false;
                    campos[cnt] = tempobj.label;
                    cnt++;
                    tempobj.style.border = "1px solid #FF0000";
                    tempobj.focus();
                }catch(erro){
                    if (erro.description)
                        pass = true;
                        tempobj.style.border = "1px solid #CCCCCC";
                }
            } else {
                if ( (tempobj.type != "radio") && (tempobj.type != "checkbox") )
                    tempobj.style.border = "1px solid #CCCCCC";
            }

            if (tempobj.type.toString().charAt(0) == "s" && tempobj.selectedIndex == 0) {
                try{
                	pass = false;
                    tempobj.style.color = "#FF0000";
                    tempobj.focus();
                }catch(erro){
                    if (erro.description)
                        pass = true;
                        tempobj.style.color = "#111111";
                }
            } else if (tempobj.type.toString().charAt(0) == "s") {
                tempobj.style.color = "#111111";
            }

            if (tempobj.type == "radio") {
                var Checkeds = 0;
                var rdButtons = form.elements[tempobj.name];
                for (var i = 0; i < rdButtons.length; i++) if (rdButtons[i].checked) Checkeds = 1;
                if (Checkeds == 0) {
                    try{
                        pass = false;
                        campos[cnt] = tempobj.label;
                        cnt++;
                        tempobj.focus();
                    }catch(erro){
                        if (erro.description)
                            pass = true;
                    }
                }
            }

            if (tempobj.type == "checkbox") {
                if (verificaCheckBox(form, tempobj.name, tempobj.qtdchk) < 0) {
                    try{
                        pass = false;
                        campos[cnt] = tempobj.label+"("+tempobj.qtdchk+")";
                        cnt++;
                        tempobj.focus();
                    }catch(erro){
                        if (erro.description)
                            pass = true;
                    }
                }
            }
        }
    }

    if (pass == false) {
        if (form.msg.value.length > 1) {
            alert(form.msg.value);
        } else {
            alert (msgAlerta(campos));
        }
    }
    return pass;
}
/*####################################################### FIM DO QUE FOI ALTERADO POR SANTHYAGO ###############################################*/

function isHexa(str){
    str = str.toUpperCase();
    hex = true;
    for (x = 0; x <= str.length; x++)
    if ( (isNaN(str.substr(x,1))) && (str.substr(x,1) != "A") && (str.substr(x,1) != "B") && (str.substr(x,1) != "C") && (str.substr(x,1) != "D") && (str.substr(x,1) != "E") && (str.substr(x,1) != "F") )
    hex = false;
    return hex;
}

function notAlpha(str){
    aph = false;
    for (x = 0; x < str.length; x++)
    if (!isNaN(str.substr(x,1)))
    aph = true;
    return aph;
}

function notNum(str){
    aph = false;
    for (x = 0; x < str.length; x++)
         if (isNaN(str.substr(x, 1)))
             aph = true;

    return aph;
}
function haveNum(str){
    aph = false;
    for (x = 0; x < str.length; x++)
         if (!isNaN(str.substr(x, 1)))
             aph = true;

    return aph;
}
function isIMEI(imei){
    var posImpar = 0, posPar = 0, x, sumDigits;
    if ( (imei.length != 15) && (imei) ) return false;

    for(x = 0; x < 14; x++)
    {
        if (!(x%2))
        posImpar += parseInt(imei.charAt(x));
        else
        {
            if ( (imei.charAt(x)*2) > 9)
            {
                sumDigits = (imei.charAt(x))*2 + "0";
                posPar += parseInt(sumDigits.charAt(0)) + parseInt(sumDigits.charAt(1));
            }
            else
            posPar += imei.charAt(x)*2;
        }
    }
    if ( ( (10 - (posPar + posImpar)%10 == imei.charAt(14) ) || ((10 - (posPar + posImpar)%10 == 10) && (imei.charAt(14) == 0) ) ) || (!imei) )
    return true;
    else
    return false;
}

function validaImei(campo){
     if (!isIMEI(campo.value)) {
         window.alert('IMEI INVALIDO');
		 campo.focus();
	}
}

function isSerial(serial, tipo){
	var hoje = new Date();
	var mes = parseInt(hoje.getMonth()) + 1;
	var ano = parseInt(hoje.getYear());
	var dia = parseInt(hoje.getDate());
	var m = 0;
	var a = 0;
	var d = 0;
	var reNum = /^[a-zA-Z0-9]{5,25}$/;
	if(!reNum.test(serial)){
		return false;
	}
	
	var reAno = /^[jknrtwxyalmpqszbcJKNRTWXYALMPQSZBC]{1}$/;
	var reMes = /^[1-9abcABC]{1}$/;
	
    if (tipo == 'SSN'){
		a = serial.substring(3,4);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(4,5);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
		
        if(serial.length!=11){
			return false;
		}
        else if(serial.substring(0,1)!= "R" && serial.substring(0,1)!= "r" && serial.substring(0,1)!= "I" && serial.substring(0,1)!= "i" && serial.substring(0,1)!= "1"){
            return false;
		}
        else if(!isNaN(serial.substring(1,2))){
            return false;
		}
        else if(!reAno.test(serial.substring(3,4))){
            return false;
		}
        else if(!reMes.test(serial.substring(4,5))){
            return false;
		}
		else {
			if(parseInt(a) > parseInt(ano)){
				return false;
			}
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes))){
				return false;
			}
			else{
				return true;
			}
		} 
    }
	else if (tipo == 'SSN_CAM'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
	
        if(serial.length!=15)
            return false;
        else if(!isNaN(serial.substring(14,15)))
            return false;
        else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'SSN_CTV'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
	
        if(serial.length!=15)
            return false;
        else if(!isNaN(serial.substring(14,15)))
            return false;
		else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
	}
	else if (tipo == 'SSN_SWA'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
	
        if(serial.length!=14)
            return false;
		else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
	}
	else if (tipo == 'SSN_DSC'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
			
        if(serial.length!=15)
            return false;
        else if(!isNaN(serial.substring(14,15)))
            return false;
        else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'SSN_DVD'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
			
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
		
        if(serial.length!=15)
            return false;
        else if(!isNaN(serial.substring(14,15)))
            return false;
        else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
			return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'SSN_MON'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
	
        if(serial.length!=15)
            return false;
        else if(!isNaN(serial.substring(14,15)))
            return false;
        else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'SSN_PRT'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}

		if(serial.length < 15 || serial.length > 25)
			return false;
        else if(!isNaN(serial.substring((serial.length - 1),serial.length)))
            return false;
        else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'SSN_REF'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
	
        if(serial.length!=15)
            return false;
        else if(!isNaN(serial.substring(14,15)))
            return false;
        else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'SSN_V/C'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
	
        if(serial.length!=15)
            return false;
        else if(!isNaN(serial.substring(14,15)))
            return false;
        else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'SSN_W/M'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
	
        if(serial.length!=15)
            return false;
        else if(!isNaN(serial.substring(14,15)))
            return false;
        else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'SSN_YEPP'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
	
        if(serial.length!=15)
            return false;
        else if(!isNaN(serial.substring(14,15)))
            return false;
        else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'MSN'){
		serial = serial.toUpperCase();
		var apc = new Array();

		a = serial.substring(4,5);
		switch (a){
			case 'A': a = 2000; break;
			case 'B': a = 2001; break;
			case 'C': a = 2002; break;
			case 'D': a = 2003; break;
			case 'E': a = 2004; break;
			case 'F': a = 2005; break;
			case 'G': a = 2006; break;
			case 'H': a = 2007; break;
			case 'J': a = 2008; break;
			case 'K': a = 1999; break;
			case 'L': a = 2009; break;
			case 'M': a = 2019; break;
			case 'N': a = 2010; break;
			case 'P': a = 2020; break;
			case 'Q': a = 2011; break;
			case 'R': a = 2021; break;
			case 'S': a = 2012; break;
			case 'T': a = 2022; break;
			case 'V': a = 2013; break;
			case 'W': a = 2023; break;
			case 'X': a = 2014; break;
			case 'Z': a = 2024; break;
		}
		m = serial.substring(5,6);
		switch (m){
			case 'A': m = 1;  d =  0; break;
			case 'B': m = 1;  d = 15; break;
			case 'C': m = 2;  d =  0; break;
			case 'D': m = 2;  d = 15; break;
			case 'E': m = 3;  d =  0; break;
			case 'F': m = 3;  d = 15; break;
			case 'G': m = 4;  d =  0; break;
			case 'H': m = 4;  d = 15; break;
			case 'J': m = 5;  d =  0; break;
			case 'K': m = 5;  d = 15; break;
			case 'L': m = 6;  d =  0; break;
			case 'M': m = 6;  d = 15; break;
			case 'N': m = 7;  d =  0; break;
			case 'P': m = 7;  d = 15; break;
			case 'Q': m = 8;  d =  0; break;
			case 'R': m = 8;  d = 15; break;
			case 'S': m = 9;  d =  0; break;
			case 'T': m = 9;  d = 15; break;
			case 'U': m = 10; d =  0; break;
			case 'V': m = 10; d = 15; break;
			case 'W': m = 11; d =  0; break;
			case 'X': m = 11; d = 15; break;
			case 'Y': m = 12; d =  0; break;
			case 'Z': m = 12; d = 15; break;
		}
		
		var anoMSN = /^[ABCDEFGHJKLMNPQRSTVWXZ]{1}$/;
		var mesMSN = /^[ABCDEFGHJKLMNPQRSTUVWXYZ]{1}$/;
		var base28 = /^[23456789BCDFGHJKLMNPQRSTVWXZ]{1}$/;
		var sAPC = verAPC(serial.substring(0,3));
		
		if(serial.length != 10)
			return false;
        else if(!anoMSN.test(serial.substring(4,5)))
            return false;
        else if(!mesMSN.test(serial.substring(5,6)))
            return false;
		else if(!sAPC)
			return false;
		else if(serial.substring(3,4) != 'N')
			return false;
        else if(!base28.test(serial.substring(6,7)))
            return false;
        else if(!base28.test(serial.substring(7,8)))
            return false;
        else if(!base28.test(serial.substring(8,9)))
            return false;
        else if(!base28.test(serial.substring(9,10)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) == parseInt(mes)) && (parseInt(d) > parseInt(dia)))
				return false;
			else
				return true;
		} 
	}
	else if (tipo == 'MSN_NEXTEL'){
		serial = serial.toUpperCase();

		a = serial.substring(4,5);
		switch (a){
			case 'A': a = 2000; break;
			case 'B': a = 2001; break;
			case 'C': a = 2002; break;
			case 'D': a = 2003; break;
			case 'E': a = 2004; break;
			case 'F': a = 2005; break;
			case 'G': a = 2006; break;
			case 'H': a = 2007; break;
			case 'I': a = 2008; break;
			case 'J': a = 2008; break;
			case 'K': a = 2009; break;
			case 'L': a = 2010; break;
			case 'M': a = 2011; break;
			case 'N': a = 2012; break;
		}
		m = serial.substring(5,6);
		switch (m){
			case 'A': m = 1;  d =  0; break;
			case 'B': m = 1;  d = 15; break;
			case 'C': m = 2;  d =  0; break;
			case 'D': m = 2;  d = 15; break;
			case 'E': m = 3;  d =  0; break;
			case 'F': m = 3;  d = 15; break;
			case 'G': m = 4;  d =  0; break;
			case 'H': m = 4;  d = 15; break;
			case 'J': m = 5;  d =  0; break;
			case 'K': m = 5;  d = 15; break;
			case 'L': m = 6;  d =  0; break;
			case 'M': m = 6;  d = 15; break;
			case 'N': m = 7;  d =  0; break;
			case 'P': m = 7;  d = 15; break;
			case 'Q': m = 8;  d =  0; break;
			case 'R': m = 8;  d = 15; break;
			case 'S': m = 9;  d =  0; break;
			case 'T': m = 9;  d = 15; break;
			case 'U': m = 10; d =  0; break;
			case 'V': m = 10; d = 15; break;
			case 'W': m = 11; d =  0; break;
			case 'X': m = 11; d = 15; break;
			case 'Y': m = 12; d =  0; break;
			case 'Z': m = 12; d = 15; break;
		}
		
		var anoMSN = /^[ABCDEFGHIJKLMN]{1}$/;
		var mesMSN = /^[ABCDEFGHJKLMNPQRSTUVWXYZ]{1}$/;
		
		if(serial.length != 10)
			return false;
		else if(serial.substring(0,3) != "364" && serial.substring(0,3)!= "846")
			return false;
		else if(serial.substring(3,4) != 'N')
			return false;
		else if(!anoMSN.test(serial.substring(4,5)))
            return false;
        else if(!mesMSN.test(serial.substring(5,6)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) == parseInt(mes)) && (parseInt(d) > parseInt(dia)))
				return false;
			else
				return true;
		} 
	}
	else if (tipo == 'ESN'){
		a = serial.substring(8,9);
		switch (a){
			case 'a': case 'A': a = 2000; break;
			case 'b': case 'B': a = 2001; break;
			case 'c': case 'C': a = 2002; break;
			case 'd': case 'D': a = 2003; break;
			case 'e': case 'E': a = 2004; break;
			case 'f': case 'F': a = 2005; break;
			case 'g': case 'G': a = 2006; break;
			case 'h': case 'H': a = 2007; break;
			case 'j': case 'J': a = 2008; break;
			case 'k': case 'K': a = 1999; break;
			case 'l': case 'L': a = 2009; break;
			case 'm': case 'M': a = 2019; break;
			case 'n': case 'N': a = 2010; break;
			case 'p': case 'P': a = 2020; break;
			case 'q': case 'Q': a = 2011; break;
			case 'r': case 'R': a = 2021; break;
			case 's': case 'S': a = 2012; break;
			case 't': case 'T': a = 2022; break;
			case 'v': case 'V': a = 2013; break;
			case 'w': case 'W': a = 2023; break;
			case 'x': case 'X': a = 2014; break;
			case 'z': case 'Z': a = 2024; break;
		}
		m = serial.substring(9,10);
		switch (m){
			case 'a': case 'A': m = 1; d = 0; break;
			case 'b': case 'B': m = 1; d = 15; break;
			case 'c': case 'C': m = 2; d = 0; break;
			case 'd': case 'D': m = 2; d = 15; break;
			case 'e': case 'E': m = 3; d = 0; break;
			case 'f': case 'F': m = 3; d = 15; break;
			case 'g': case 'G': m = 4; d = 0; break;
			case 'h': case 'H': m = 4; d = 15; break;
			case 'j': case 'J': m = 5; d = 0; break;
			case 'k': case 'K': m = 5; d = 15; break;
			case 'l': case 'L': m = 6; d = 0; break;
			case 'm': case 'M': m = 6; d = 15; break;
			case 'n': case 'N': m = 7; d = 0; break;
			case 'p': case 'P': m = 7; d = 15; break;
			case 'q': case 'Q': m = 8; d = 0; break;
			case 'r': case 'R': m = 8; d = 15; break;
			case 's': case 'S': m = 9; d = 0; break;
			case 't': case 'T': m = 9; d = 15; break;
			case 'u': case 'U': m = 10; d = 0; break;
			case 'v': case 'V': m = 10; d = 15; break;
			case 'w': case 'W': m = 11; d = 0; break;
			case 'x': case 'X': m = 11; d = 15; break;
			case 'y': case 'Y': m = 12; d = 0; break;
			case 'z': case 'Z': m = 12; d = 15; break;
		}

		var anoESN = /^[abcdefghjklmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ]{1}$/;
		var mesESN = /^[abcdefghjklmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ]{1}$/;
		
		if(serial.length != 11)
			return false;
        else if(!anoESN.test(serial.substring(8,9)))
            return false;
        else if(!mesESN.test(serial.substring(9,10)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) == parseInt(mes)) && (parseInt(d) > parseInt(dia)))
				return false;
			else
				return true;
		} 
	}
	else if (tipo == 'SSN_MDM'){
		a = serial.substring(7,8);
		switch (a){
			case '6': a = 2006; break;
			case '7': a = 2007; break;
			case '8': a = 2008; break;
			case '9': a = 2009; break;
			case '0': a = 2010; break;
			case '1': a = 2011; break;
			case '2': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case '1': m = 1; break;
			case '2': m = 2; break;
			case '3': m = 3; break;
			case '4': m = 4; break;
			case '5': m = 5; break;
			case '6': m = 6; break;
			case '7': m = 7; break;
			case '8': m = 8; break;
			case '9': m = 9; break;
			case 'A': case 'a': m = 10; break;
			case 'B': case 'b': m = 11; break;
			case 'C': case 'c': m = 12; break;
		}
		var anoSSN_MDM = /^[6789012]{1}$/;
		var mesSSN_MDM = /^[123456789abcABC]{1}$/;
		
		if(serial.length != 16)
			return false;
        else if(!anoSSN_MDM.test(serial.substring(7,8)))
            return false;
        else if(!mesSSN_MDM.test(serial.substring(8,9)))
            return false;
	}
	else if (tipo == 'SSN_NBK'){
		a = serial.substring(7,8);
		switch (a){
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}

		var anoSSN_NBK = /^[szbSZBC]{1}$/;
		var mesSSN_NBK = /^[123456789abcABC]{1}$/;
        
		if(serial.length!=15)
            return false;
        else if(!anoSSN_NBK.test(serial.substring(7,8)))
            return false;
        else if(!mesSSN_NBK.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'SSN_NPC'){
		a = serial.substring(7,8);
		switch (a){
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}

		var anoSSN_NBK = /^[szbSZBC]{1}$/;
		var mesSSN_NBK = /^[123456789abcABC]{1}$/;
        
		if(serial.length!=15)
            return false;
        else if(!anoSSN_NBK.test(serial.substring(7,8)))
            return false;
        else if(!mesSSN_NBK.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
    if (tipo == 'SSN_LG'){
		a = serial.substring(0,1);
		switch (a){
			case '5': a = 2005; break;
			case '6': a = 2006; break;
			case '7': a = 2007; break;
			case '8': a = 2008; break;
			case '9': a = 2009; break;
			case '0': a = 2010; break;
			case '1': a = 2011; break;
			case '2': a = 2012; break;
			case '3': a = 2013; break;
			case '4': a = 2014; break;
		}
		m = parseInt(parseFloat(serial.substring(1,3)));

		if(isNaN(a))
			return false;
		else if((isNaN(m)) || (m < 1) || (m > 12))
			return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
    if (tipo == 'DATECODE'){
		var dias = 0;
		var x = 1;
		var semanas;
		while(x < parseInt(mes)){
			if((x==1) || (x==3) || (x==5) || (x==7) || (x==8) || (x==10) || (x==12))
				dias = dias + 31;
			else if((x==4) || (x==6) || (x==9) || (x==11))
				dias = dias + 30;
			else if(x==2)
				if (eBissexto(ano))
					dias = dias + 29;
				else
					dias = dias + 28;
			x++
		}
		dias = dias + dia + 6;
		semanas = dias/7;
		
		a = parseInt(serial.substring(0,2));
		w = parseInt(serial.substring(3,5));
		
		if(serial.length!=5)
			return false;
		else if(serial.substring(2,3).toUpperCase() != 'W')
			return false;
		else if((isNaN(w)) || (w < 1) || (w > 52))
			return false;
		else {
			if(parseInt(a) > parseInt(ano.toString().substring(2,4)))
				return false;
			else if((parseInt(a) == parseInt(ano.toString().substring(2,4))) && (parseInt(w) > parseInt(semanas)))
				return false;
			else
				return true;
		} 
    }
	return true;
}

function verAPC(sn){
	for( i = 0; i < apc.length; i++ )
		if(apc[i] == sn)
			return true;
	
	return false;
}

function soNumLetras(teclapres) {
	if(teclapres.keyCode > 64 && teclapres.keyCode < 91) //Letras minusculas
		teclapres.returnValue = true;
	else if(teclapres.keyCode > 96 && teclapres.keyCode < 123) //Letras mausculas
		teclapres.returnValue = true;
	else if(teclapres.keyCode > 47 && teclapres.keyCode < 58) //Numeros
		teclapres.returnValue = true;
	else if(teclapres.keyCode == 32) //Espaço
		teclapres.returnValue = true;
	else if(teclapres.keyCode == 44) //,
		teclapres.returnValue = true;
	else if(teclapres.keyCode == 46) //.
		teclapres.returnValue = true;
	else if(teclapres.keyCode == 58) //:
		teclapres.returnValue = true;
	else{
		teclapres.returnValue = false;
	}
}

function FormataHora(oCampo, teclapres) {
    var
    vr = oCampo.value;
    tam = vr.length;

    if ((teclapres.keyCode < 48) || (teclapres.keyCode > 57))
    teclapres.returnValue = false;
    else if (tam == 2) 
    oCampo.value = vr + ':';
}

function HoraErro(oCampo) {    
    var hora = oCampo.value.substring(0, 2);
    var min = oCampo.value.substring(3, 5);
    if(oCampo.value.length > 0)
    {
		//oCampo.value = '00:00:000';
    	if ((hora > 24) || (min > 60)){
    		alert('Horário Inválido!');
    		oCampo.value = '';
    	    oCampo.focus();
		}
    	else if ((hora == 24) && (min > 0)){
    		alert('Horário Inválido!');
    		oCampo.value = '';
    		oCampo.focus();
		}
    }
}

function FormataData(oCampo, teclapres) {
    var
    vr = oCampo.value;
    tam = vr.length;

    if ((teclapres.keyCode < 48) || (teclapres.keyCode > 57))
    teclapres.returnValue = false;
    else if ( (tam == 2) || (tam == 5) )
    oCampo.value = vr + '/';
}

function eBissexto(ano) {
	if ((ano % 400) == 0)
		return true;
	else if ((ano % 100) == 0)
		return false;
	else if ((ano % 4) == 0)
		return true;
	else
		return false;
}

function dataErro(campo) {
	var
    diaCompra = 0;
    mesCompra = 0;
    anoCompra = 0;
    dataInvalida = false;
    data1 = '';
    d = new Date();
    if (campo.value=='')
       return;

    diaCompra = campo.value.substr(0,2);
    mesCompra = campo.value.substr(3,2);
    anoCompra = campo.value.substr(6,4);

    if (campo.value) {
        if ( (mesCompra == 1) || (mesCompra == 3) || (mesCompra == 5) || (mesCompra == 7) || (mesCompra == 8) || (mesCompra == 10) || (mesCompra == 12) )
        if ( (diaCompra < 1) || (diaCompra > 31) ) dataInvalida = true;

        if ( (mesCompra == 4) || (mesCompra == 6) || (mesCompra == 9) || (mesCompra == 11) )
        if ( (diaCompra < 1) || (diaCompra > 30) ) dataInvalida = true;

        if (mesCompra == 2)
        	if (eBissexto(anoCompra)){
        		if ( (diaCompra < 1) || (diaCompra > 29) ) dataInvalida = true;
			}else if ( (diaCompra < 1) || (diaCompra > 28) ) dataInvalida = true;

        if ( (mesCompra < 1) || (mesCompra > 12) ) dataInvalida = true;
        if (anoCompra < 2000) dataInvalida = true;
    }

    data2 = campo.value;
    data1 = ( ((d.getDate() < 10)?'0'+d.getDate().toString():d.getDate()) ) + "/"+(( (d.getMonth()+1) < 10)?'0'+(d.getMonth()+1).toString():(d.getMonth()+1))+ "/"+d.getYear();

    if (dataInvalida) {
        window.alert("A Data Informada Não Existe, informe outra Data, utilizando o formato dd/mm/aaaa.");
        campo.value = '';
        campo.focus();
    }
}

function dataErroF(campo) {
	var
    diaCompra = 0;
    mesCompra = 0;
    anoCompra = 0;
    dataInvalida = false;
    data1 = '';
    d = new Date();
    if (campo.value=='')
       return;

    diaCompra = campo.value.substr(0,2);
    mesCompra = campo.value.substr(3,2);
    anoCompra = campo.value.substr(6,4);

    if (campo.value) {
        if ( (mesCompra == 1) || (mesCompra == 3) || (mesCompra == 5) || (mesCompra == 7) || (mesCompra == 8) || (mesCompra == 10) || (mesCompra == 12) )
        if ( (diaCompra < 1) || (diaCompra > 31) ) dataInvalida = true;

        if ( (mesCompra == 4) || (mesCompra == 6) || (mesCompra == 9) || (mesCompra == 11) )
        if ( (diaCompra < 1) || (diaCompra > 30) ) dataInvalida = true;

        if (mesCompra == 2)
			if (eBissexto(anoCompra)){
				if ( (diaCompra < 1) || (diaCompra > 29) ) dataInvalida = true;
			}else if ( (diaCompra < 1) || (diaCompra > 28) ) dataInvalida = true;

        if ( (mesCompra < 1) || (mesCompra > 12) ) dataInvalida = true;
        if (anoCompra < 2000) dataInvalida = true;
    }

    data2 = campo.value;
    data1 = ( ((d.getDate() < 10)?'0'+d.getDate().toString():d.getDate()) ) + "/"+(( (d.getMonth()+1) < 10)?'0'+(d.getMonth()+1).toString():(d.getMonth()+1))+ "/"+d.getYear();

    if(!arguments[1])
	    if ( parseInt( data2.split( "/" )[2].toString() + data2.split( "/" )[1].toString() + data2.split( "/" )[0].toString() ) > parseInt( data1.split( "/" )[2].toString() + data1.split( "/" )[1].toString() + data1.split( "/" )[0].toString() ) )
	    {
	        window.alert("A Data Informada está no futuro.");
	        campo.value = '';
	        campo.focus();
	    }

    if (dataInvalida) {
        window.alert("A Data Informada Não Existe, informe outra Data, utilizando o formato dd/mm/aaaa.");
        campo.value = '';
        campo.focus();
    }
}

function dataErroP(campo) {
	var
    diaCompra = 0;
    mesCompra = 0;
    anoCompra = 0;
    dataInvalida = false;
    data1 = '';
    d = new Date();
    if (campo.value=='')
       return;

    diaCompra = campo.value.substr(0,2);
    mesCompra = campo.value.substr(3,2);
    anoCompra = campo.value.substr(6,4);

    if (campo.value) {
        if ( (mesCompra == 1) || (mesCompra == 3) || (mesCompra == 5) || (mesCompra == 7) || (mesCompra == 8) || (mesCompra == 10) || (mesCompra == 12) )
        if ( (diaCompra < 1) || (diaCompra > 31) ) dataInvalida = true;

        if ( (mesCompra == 4) || (mesCompra == 6) || (mesCompra == 9) || (mesCompra == 11) )
        if ( (diaCompra < 1) || (diaCompra > 30) ) dataInvalida = true;

        if (mesCompra == 2)
			if (eBissexto(anoCompra)){
				if ( (diaCompra < 1) || (diaCompra > 29) ) dataInvalida = true;
			}else if ( (diaCompra < 1) || (diaCompra > 28) ) dataInvalida = true;

        if ( (mesCompra < 1) || (mesCompra > 12) ) dataInvalida = true;
        if (anoCompra < 2000) dataInvalida = true;
    }

    data2 = campo.value;
    data1 = ( ((d.getDate() < 10)?'0'+d.getDate().toString():d.getDate()) ) + "/"+(( (d.getMonth()+1) < 10)?'0'+(d.getMonth()+1).toString():(d.getMonth()+1))+ "/"+d.getYear();

    if(!arguments[1])
	    if ( parseInt( data2.split( "/" )[2].toString() + data2.split( "/" )[1].toString() + data2.split( "/" )[0].toString() ) < parseInt( data1.split( "/" )[2].toString() + data1.split( "/" )[1].toString() + data1.split( "/" )[0].toString() ) )
	    {
	        window.alert("A Data Informada está no passado.");
	        campo.value = '';
	        campo.focus();
	    }

    if (dataInvalida) {
        window.alert("A Data Informada Não Existe, informe outra Data, utilizando o formato dd/mm/aaaa.");
        campo.value = '';
        campo.focus();
    }
}


window.onerror = null;
var bName = navigator.appName;
var bVer = parseInt(navigator.appVersion);
var NS4 = (bName == "Netscape" && bVer >= 4);
var IE4 = (bName == "Microsoft Internet Explorer" && bVer >= 4);
var NS3 = (bName == "Netscape" && bVer < 4);
var IE3 = (bName == "Microsoft Internet Explorer" && bVer < 4);
var blink_speed=100;
var i=0;
if (NS4 || IE4)
{
    if (navigator.appName == "Netscape")
    {
        layerStyleRef="layer.";
        layerRef="document.layers";
        styleSwitch="";
    }
    else
    {
        layerStyleRef="layer.style.";
        layerRef="document.all";
        styleSwitch=".style";
    }
}

function Blink(layerName)
{
    if (NS4 || IE4)
    if(i%2==0)
    eval(layerRef+'["'+layerName+'"]'+styleSwitch+'.visibility="visible"');
    else
    eval(layerRef+'["'+layerName+'"]'+styleSwitch+'.visibility="hidden"');
    if(i<1)
    i++;
    else
    i--;
    setTimeout("Blink('"+layerName+"')",blink_speed);
}

function getWindowWidth() {
    var myWidth = 0;
    if( typeof( window.innerWidth ) == 'number' ) {
        //Non-IE
        myWidth = window.innerWidth;
    } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
        //IE 6+ in 'standards compliant mode'
        myWidth = document.documentElement.clientWidth;
    } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
        //IE 4 compatible
        myWidth = document.body.clientWidth;
    }
    return myWidth;
}

function getWindowHeight() {
    var myHeight = 0;
    if( typeof( window.innerHeight ) == 'number' ) {
        //Non-IE
        myHeight = window.innerHeight;
    } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
        //IE 6+ in 'standards compliant mode'
        myHeight = document.documentElement.clientHeight;
    } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
        //IE 4 compatible
        myHeight = document.body.clientHeight;
    }
    return myHeight;
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function mascaraFone(x) {
    if (x.length == 0)
    x = "(" + x;
    if (x.length == 3)
    x = x + ") ";
    if (x.length == 9)
    x = x + "-";
    return x;
}

function FormataCNPJ(Campo, teclapres) {

    var tecla = teclapres.keyCode;

    var vr = new String(Campo.value);
    vr = vr.replace(".", "");
    vr = vr.replace(".", "");
    vr = vr.replace("/", "");
    vr = vr.replace("-", "");

    tam = vr.length + 1 ;


    if (tecla != 9 && tecla != 8){
        if (tam > 2 && tam < 6)
        Campo.value = vr.substr(0, 2) + '.' + vr.substr(2,

        tam);
        if (tam >= 6 && tam < 9)
        Campo.value = vr.substr(0,2) + '.' + vr.substr(2,3) +

        '.' + vr.substr(5,tam-5);
        if (tam >= 9 && tam < 13)
        Campo.value = vr.substr(0,2) + '.' + vr.substr(2,3) +

        '.' + vr.substr(5,3) + '/' + vr.substr(8,tam-8);
        if (tam >= 13 && tam < 15)
        Campo.value = vr.substr(0,2) + '.' + vr.substr(2,3) +

        '.' + vr.substr(5,3) + '/' + vr.substr(8,4)+ '-' + vr.substr(12,tam-12);
    }
}

function ValidaCPF(CPF)
{
    var strCPF  = new String(CPF);
    var digitos = new Array(9);
    var calcdig = new Array(9);
    var dv1     = 0;
    var dv2     = 0;
    var nDV1, nDV2;
    var i, j, tot;

    for (i = 0; i < 9; i++)
    digitos[i] = Number(strCPF.charAt(i));

    for (i = 0, j = 10, tot = 0; i < 9; i++, j--)
    {
        calcdig[i] = digitos[i] * j;
        tot += calcdig[i];
    };

    dv1 = tot % 11;

    if (dv1 < 2)
    dv1 = 0;
    else
    dv1 = 11 - dv1;

    for (i = 0, j = 11, tot = 0; i < 9; i++, j--)
    {
        calcdig[i] = digitos[i] * j;
        tot += calcdig[i];
    };

    dv2 = (tot + (dv1 * 2)) % 11;

    if (dv2 < 2)
    dv2 = 0;
    else
    dv2 = 11 - dv2;

    nDV1 = new Number( dv1 );
    nDV2 = new Number( dv2 );

    return ((nDV1.toString(10) == strCPF.charAt(9)) && (nDV2.toString(10) == strCPF.charAt(10)));
}

function Toggle(secid)
{
    var sectionId = document.getElementById(secid);
    if (sectionId == null) return;
    if (sectionId.style.display == '')
    {
        sectionId.style.display = 'none';
        try
        {
            var ImgSrc = document.getElementById("i" + secid);
            ImgSrc.src = "../i/mais.gif";
        }catch(error){}
    }
    else
    {
        sectionId.style.display = '';
        try
        {
            var ImgSrc = document.getElementById("i" + secid);
            ImgSrc.src = "../i/menos.gif";
        }catch(error){}
    }
}

function Hide(obj)
{
    var oDiv = document.getElementById(obj);
    if(oDiv != null)
    {
        oDiv.style.display = "none";
    }
}

function abre_site(param) {
    var nomearq=param;
    var windowvar = window.open(nomearq,'abcd',"scrollbars=yes,location=no,directories=no,status=no,menubar=no,resizable=no,toolbar=no,width=800,height=600");
}

function janela(pag, w, h, janela, status, resize) {
    var windowvar = window.open(pag, janela, "scrollbars=yes,location=no,directories=no,status="+status+",menubar=no,resizable="+resize+",toolbar=no,width="+w+ ",height="+h);
}


function posicionaFocus(objeto,id_form)
{	
    x = pegaNoh(objeto,id_form);    
    var dentro;
    dentro = 0;
    for (i = (x.length - 1); i >= 0; i--)
    {
       var tempobj=x.elements[i];        
       if ((tempobj.type == "text" || tempobj.type == "textarea" || tempobj.type == "password")
    		&&((!tempobj.value)||(tempobj.value=='0,00')||(tempobj.value=='0')) &&(!tempobj.disabled))
    	{    		
    		if (tempobj.required == "yes"){
    			tempobj.focus();
    		}
    	}
    	else if (tempobj.type.toString().charAt(0) == "s" && tempobj.selectedIndex == 0)     	
    			if (tempobj.required == "yes")
    				tempobj.focus();     		
    				        
        //if ((tempobj.type == "button")&&(!tempobj.disabled))        
        //	tempobj.focus();        
    }
}

function Ajax(url,destino){
    //alert(url);
    try{
        xmlhttp = new XMLHttpRequest();
        if (xmlhttp.overrideMimeType){
            xmlhttp.overrideMimeType('text/xml');
        }
    }
    catch(ee){
        try{
            xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch(e){
            try{
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch(E){
                xmlhttp = false;
            }
        }
    }
    destino.innerHTML = "<span class='link1' ><font color='red'><b>"+((arguments[2])?arguments[2]:"<p align='center'><img src='../i/ajax_loader.gif'' align='absMiddle' border='0'></p>")+"</b></font></span>";
    xmlhttp.open("GET", url ,((arguments[3])?arguments[3]:true));
    xmlhttp.onreadystatechange=function(){
        if (xmlhttp.readyState==4){
            
            //alert(xmlhttp.responseText);
            destino.innerHTML = xmlhttp.responseText;
            extraiScript(xmlhttp.responseText);
        }
    }
    xmlhttp.send(null);
}

function Ajax1(url,destino){
    alert(url);
    try{
        xmlhttp = new XMLHttpRequest();
        if (xmlhttp.overrideMimeType){
            xmlhttp.overrideMimeType('text/xml');
        }
    }
    catch(ee){
        try{
            xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch(e){
            try{
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch(E){
                xmlhttp = false;
            }
        }
    }
    destino.innerHTML = "<span class='link1' ><font color='red'><b>"+((arguments[2])?arguments[2]:"<p align='center'><img src='../i/ajax_loader.gif'' align='absMiddle' border='0'></p>")+"</b></font></span>";
    xmlhttp.open("GET", url ,((arguments[3])?arguments[3]:true));
    xmlhttp.onreadystatechange=function(){
        if (xmlhttp.readyState==4){
            alert(xmlhttp.responseText);
            destino.innerHTML = xmlhttp.responseText;
            extraiScript(xmlhttp.responseText);
        }
    }
    xmlhttp.send(null);
}

function extraiScript(texto){
    var ini = 0;
    while (ini!=-1){
        ini = texto.indexOf('<script', ini);
        if (ini >=0){
            ini = texto.indexOf('>', ini) + 1;
            var fim = texto.indexOf('</script', ini);
            codigo = texto.substring(ini,fim);
            novo = document.createElement("script")
            novo.text = codigo;
            document.body.appendChild(novo);
        }
    }
}
//Seleciona o option de um select(obj_select) que tenha o value(valor)
//Adicionado por Rodolfo 04/09/2006
function selecione (obj_select, valor) {
	if (valor != '') {        
		for (i = 0; i < obj_select.options.length; i++){			              
            if (obj_select.options[i].value.toUpperCase() == valor.toUpperCase()) {                                              
                obj_select.selectedIndex = i; break; 
            }
        }
	}
}
//adcionado por Carlos 02/02/2007
//Valida NTC
function ValidaNTC(numNTC){
 igual=1;
 for (i= 0; i<(numNTC.value.length-4);i++)
 {
  if (numNTC.value.charAt(i) == numNTC.value.charAt(i-1) )
  {
   igual++;
  }
 }
 if (igual == 5){
    alert('Numero de Telefone Inválido');
    numNTC.value='';
    numNTC.focus();
 }

}

function FormataFone(oCampo,teclapres)
{
	var
	vr = oCampo.value;
	tam = vr.length;
	
	if ((teclapres.keyCode < 48) || (teclapres.keyCode > 57))
	    teclapres.returnValue = false;
	else
	{
	    if ((tam == 0) || (tam == 1))
	        if (teclapres.keyCode != 48)
	            oCampo.value = '(' + vr;
	        else
	            teclapres.returnValue = false;
	    if (tam == 3)
	        oCampo.value = vr + ')';
	
	    if (tam == 7)
	        oCampo.value = vr + '-';
	
	    if (tam == 12)
	        oCampo.value = vr.substr(0,7) + vr.substr(8,1) + '-' + vr.substr(9,4);
	}
}

function FormataData_mes(oCampo, teclapres) {
    var
    vr = oCampo.value;
    tam = vr.length;

    if ((teclapres.keyCode < 48) || (teclapres.keyCode > 57))
    teclapres.returnValue = false;
    else if ( (tam == 2) )
    oCampo.value = vr + '/';
}

function DataErro_mes(campo) {    
	if (campo.value=='') 
		return;
	
	var mesCompra = 0; anoCompra = 0; dataInvalida = false;    
    d = new Date();
    
    mesCompra = campo.value.substr(0,2);
    anoCompra = campo.value.substr(3,4);

    if (  isNaN(mesCompra) || isNaN(anoCompra) )
    	dataInvalida = true;    
    
    if (campo.value) {
        if ( (mesCompra < 1) || (mesCompra > 12) )
        	dataInvalida = true;

        if ( (anoCompra < 1995) || (anoCompra > d.getYear()) )
        	dataInvalida = true;
    }    
    if (dataInvalida) {
        window.alert("A Data Informada Não Existe, informe outra Data, utilizando o formato mm/aaaa.");
        campo.value = '';
        campo.focus();
        return;
    }    
    if(!arguments[1]){	    
	    if ( (anoCompra >= d.getYear()) && (mesCompra > d.getMonth()) )
	    {
	        window.alert("A Data Informada está no futuro.");
	        campo.value = '';
	        campo.focus();
	        return;
	    }
    }

}
function soNum(teclapres) {
    if ((teclapres.keyCode < 48) || (teclapres.keyCode > 57))
		teclapres.returnValue = false;
}


//Formata número tipo moeda usando o evento onKeyDown
function formataMoeda(objTextBox, SeparadorMilesimo, SeparadorDecimal, e){
    var sep = 0;
    var key = '';
    var i = j = 0;
    var len = len2 = 0;
    var strCheck = '0123456789';
    var aux = aux2 = '';
    var whichCode = (window.Event) ? e.which : e.keyCode;    
    // 13=enter, 8=backspace as demais retornam 0(zero)
    // whichCode==0 faz com que seja possivel usar todas as teclas como delete, setas, etc    
    if ((whichCode == 13) || (whichCode == 0) || (whichCode == 8))
    	return true;
    key = String.fromCharCode(whichCode); // Valor para o código da Chave
 
 
    if (strCheck.indexOf(key) == -1) 
    	return false; // Chave inválida
    len = objTextBox.value.length;
    for(i = 0; i < len; i++)
        if ((objTextBox.value.charAt(i) != '0') && (objTextBox.value.charAt(i) != SeparadorDecimal)) 
        	break;
    aux = '';
    for(; i < len; i++)
        if (strCheck.indexOf(objTextBox.value.charAt(i))!=-1) 
        	aux += objTextBox.value.charAt(i);
    aux += key;
    len = aux.length;
    if (len == 0) 
    	objTextBox.value = '';
    if (len == 1) 
    	objTextBox.value = '0'+ SeparadorDecimal + '0' + aux;
    if (len == 2) 
    	objTextBox.value = '0'+ SeparadorDecimal + aux;
    if (len > 2) {
        aux2 = '';
        for (j = 0, i = len - 3; i >= 0; i--) {
            if (j == 3) {
                aux2 += SeparadorMilesimo;
                j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
        }
        objTextBox.value = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--)
        	objTextBox.value += aux2.charAt(i);
        objTextBox.value += SeparadorDecimal + aux.substr(len - 2, len);
    }
    return false;
}

///////////////////////////////////////////////////////////////////////////////
// MASCARA VERIFICADORA DE DIFERENÇA ENTRE DATAS;
// ALEX SANDRO MARTINS DE ARAUJO
function DifDatas(dtInicial, dtFinal){
    var dtini = document.getElementById(dtInicial);
    var dtfim = document.getElementById(dtFinal);
	
    datInicio = new Date(dtini.substring(6,10), dtini.substring(3,5), dtini.substring(0,2));
    datInicio.setMonth(datInicio.getMonth() - 1); 
    
    datFim = new Date(dtfim.substring(6,10), dtfim.substring(3,5), dtfim.substring(0,2));
    datFim.setMonth(datFim.getMonth() - 1); 
	    if(datInicio <= datFim){
    	    alert('Cadastro Completo!');
        	return true;
	    } else {
    	    alert('ATENÇÃO: Data Inicial é maior que Data Final');
	        //document.all.campos.final.focus();
	        //document.all.campos.final.select();
	        return false;
    	}    
}
////////////////////////////////////////////////////////////////////////////

//document.write('<iframe style="display:none;" src="analytics.php" ></iframe>');
	