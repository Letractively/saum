function formataTelefone(campo) {
	var v = campo.value;
    v=v.replace(/\D/g,"");                 
    v=v.replace(/^(\d\d)(\d)/g,"($1) $2");
    if(v.length < 14){
    	v=v.replace(/(\d{4})(\d)/,"$1-$2");
    }else{
    	v=v.replace(/(\d{5})(\d)/,"$1-$2");
    }
    
    campo.value = v;
    return true;
}
function formatarNumero(s) {
	s.value = s.value.replace(/[^\d]/g, '');
}
function getTamanhobrowser(){
	return 400;
}
/*Função que padroniza CPF*/
function formataCpf(campo){
	var v = campo.value;
    v=v.replace(/\D/g,"");                  
    v=v.replace(/(\d{3})(\d)/,"$1.$2");
    v=v.replace(/(\d{3})(\d)/,"$1.$2");
    
    v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2");
    campo.value = v;
}

/*Função que padroniza CEP*/
function formataCep(campo){
	var v = campo.value;
    v=v.replace(/\D/g,"");                 
    v=v.replace(/^(\d{5})(\d)/,"$1-$2");
    campo.value = v;
}

/*Função que padroniza CNPJ*/
function formataCnpj(campo){
	var v = campo.value;
    v=v.replace(/\D/g,"");
    v=v.replace(/^(\d{2})(\d)/,"$1.$2");
    v=v.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3");
    v=v.replace(/\.(\d{3})(\d)/,".$1/$2");
    v=v.replace(/(\d{4})(\d)/,"$1-$2");
    campo.value = v;
}
function formataData(campo){
	var v = campo.value;
    v=v.replace(/\D/g,"");
    v=v.replace(/(\d{2})(\d)/,"$1/$2");
    v=v.replace(/(\d{2})(\d)/,"$1/$2");
    campo.value = v;
}
function formataHora(campo){
	var v = campo.value;
    v=v.replace(/\D/g,""); 
    v=v.replace(/(\d{2})(\d)/,"$1:$2");  
    campo.value = v;
}
function formataPercentual(campo, tammax) {
	var vr = campo.value;
	vr = vr.replace(/[^\d]/g, ""); // retira não digitos
	vr = vr.replace(/^0*/, ""); // retira zeros iniciais
	tam = vr.length;
	if (tam > tammax) {
		vr = vr.substr(0, tammax);
		retorno = vr;
	}
	if (vr != "") {
		if (tam > 2) {
			retorno = vr.substr(0, vr.length - 2) + '.' + vr.substr(vr.length - 2, vr.length);
		} else if (tam == 1) {
			retorno = "0.0" + vr;
		} else if (tam == 2) {
			retorno = vr.substr(0, 1) + "." + vr.substr(1, vr.length);
		}
	} else {
		retorno = "0";
	}
	campo.value = retorno;
}
/*Função que padroniza valor monétario*/
function Valor(campo){
	var v = campo.value;
    v=v.replace(/\D/g,""); //Remove tudo o que não é dígito
    v=v.replace(/^([0-9]{3}\.?){3}-[0-9]{2}$/,"$1.$2");
    v=v.replace(/(\d)(\d{2})$/,"$1.$2"); //Coloca ponto antes dos 2 últimos digitos
    campo.value = retorno;
}
