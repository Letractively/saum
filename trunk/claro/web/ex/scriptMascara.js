function digitosNumericos(campo, n){
	var reNum = /^[0-9]{15}$/;
	if(campo.value){
		if(!reNum.test(campo.value)){
			window.alert('Este campo deve conter '+n+' dígitos numéricos.');
			campo.focus();
		}
	}
}

function formatHora(oCampo, teclapres, format) {
    var
    vr = oCampo.value;
    tam = vr.length;
    if ((teclapres.keyCode < 48) || (teclapres.keyCode > 57))
    	teclapres.returnValue = false;
	else {
		if (format == 1) { // hh:mm
			if (tam == 2)
				oCampo.value = vr + ':';
		}		
		if (format == 2) { // hh:mm:ss
			if ((tam == 2) || (tam == 5))
				oCampo.value = vr + ':';
		}		
		if (format == 3) { // hhh:mm
			if (tam == 3)
				oCampo.value = vr + ':';
		}		
		if (format == 4) { // hhh:mm:ss
			if ((tam == 3) || (tam == 6))
				oCampo.value = vr + ':';
		}
	}
}

function ehImei(sn){
    if (!isIMEI(sn.value)) {
         window.alert('IMEI INVALIDO');
		 sn.focus();
	} 
	else {
		var x = 0;
		for( i = 0; i < imei.length; i++ ) {
			if(imei[i] == sn.value){
				x = 1;
			}
		}
		if(x == 1){
			window.alert('IMEI lançado já existe neste lote.');
			sn.focus();
		}
	}
}

function horaErr(oCampo,format) {  

	   if (oCampo.value.length < 1){
	  	    oCampo.value = '000:00:00';	
	   }
	   
       if	(oCampo.value.length > 0) {
		if (format == 1) { // hh:mm
			if (oCampo.value.length != 5)
				var erro = 1;
			var hora = oCampo.value.substring(0, 2);
			var minuto = oCampo.value.substring(3, 5);
			var segundo = 00;
		}
		if (format == 2) { // hh:mm:ss
			if (oCampo.value.length != 8)
				var erro = 1;
			var hora = oCampo.value.substring(0, 2);
			var minuto = oCampo.value.substring(3, 5);
			var segundo = oCampo.value.substring(6, 8);
		}
		if (format == 3) { // hhh:mm
			if (oCampo.value.length != 6)
				var erro = 1;
			var hora = oCampo.value.substring(0, 3);
			var minuto = oCampo.value.substring(4, 6);
			var segundo = 00;
		}
		if (format == 4) { // hhh:mm:ss
			if (oCampo.value.length != 9)
				var erro = 1;
			var hora = oCampo.value.substring(0, 3);
			var minuto = oCampo.value.substring(4, 6);
			var segundo = oCampo.value.substring(7, 9);
		}
		if ((minuto > 59) || (segundo > 59) || (erro == 1)){
    		alert('Horário Inválido!');
    		oCampo.value = '';
    		oCampo.focus();
		}
	}
}

function formatData(oCampo, teclapres) {
    var
    vr = oCampo.value;
    tam = vr.length;

    if ((teclapres.keyCode < 48) || (teclapres.keyCode > 57))
    teclapres.returnValue = false;
    else if ( (tam == 2) || (tam == 5) )
    oCampo.value = vr + '/';
}

function dataErr(campo,format) {
	var
    dia = 0;
    mes = 0;
    ano = 0;
    dataInvalida = false;
    data1 = '';
    d = new Date();

    if (campo.value=='')
       return;

    if (campo.value.length == 10) {
		dia = campo.value.substr(0,2);
		mes = campo.value.substr(3,2);
		ano = campo.value.substr(6,4);

        if ((mes == 1) || (mes == 3) || (mes == 5) || (mes == 7) || (mes == 8) || (mes == 10) || (mes == 12))
        	if ((dia < 1) || (dia > 31)) 
				dataInvalida = true;

		if ((mes == 4) || (mes == 6) || (mes == 9) || (mes == 11))
			if ( (dia < 1) || (dia > 30) ) 
				dataInvalida = true;

        if (mes == 2)
			if (eBissexto(ano)){
				if ((dia < 1) || (dia > 29)) 
					dataInvalida = true;
			} 
			else {
				if ((dia < 1) || (dia > 28)) 
					dataInvalida = true;
			}

        if ((mes < 1) || (mes > 12)) 
			dataInvalida = true;

		if (ano < 2000) 
			dataInvalida = true;

		if(!dataInvalida){
			data2 = campo.value;
			data1 = (((d.getDate() < 10)?'0'+d.getDate().toString():d.getDate()) ) + "/"+(( (d.getMonth()+1) < 10)?'0'+(d.getMonth()+1).toString():(d.getMonth()+1))+ "/"+d.getYear();
		
			if (format == 2){ // Apenas datas passadas
				if ( parseInt( data2.split( "/" )[2].toString() + data2.split( "/" )[1].toString() + data2.split( "/" )[0].toString() ) > parseInt( data1.split( "/" )[2].toString() + data1.split( "/" )[1].toString() + data1.split( "/" )[0].toString())){
					window.alert("A Data Informada está no futuro.");
					campo.value = '';
					campo.focus();
				}
			}
		
			if (format == 3){ // Apenas datas futuras
				if ( parseInt( data2.split( "/" )[2].toString() + data2.split( "/" )[1].toString() + data2.split( "/" )[0].toString() ) < parseInt( data1.split( "/" )[2].toString() + data1.split( "/" )[1].toString() + data1.split( "/" )[0].toString())){
					window.alert("A Data Informada está no passado.");
					campo.value = '';
					campo.focus();
				}
			}
		}
    }
	else{
		dataInvalida = true;
	}
	
	if (dataInvalida) {
        window.alert("A Data Informada Não Existe, informe outra Data, utilizando o formato dd/mm/aaaa.");
        campo.value = '';
        campo.focus();
    }
}

function soNum(teclapres) {
    if ((teclapres.keyCode < 48) || (teclapres.keyCode > 57))
		teclapres.returnValue = false;
}

function DateCodeSE(campo){
	var serial = campo.value;
	var erro = 0;
	if(serial.length > 0){
		var dias = 0;
		var x = 1;
		var semanas;
		var hoje = new Date();
		var mes = parseInt(hoje.getMonth(), 10) + 1;
		var ano = parseInt(hoje.getYear(), 10);
		var dia = parseInt(hoje.getDate(), 10);
		
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
			x++;
		} 
		dias = dias + dia + 6;
		semanas = dias/7;
		
		a = parseInt(serial.substring(0,2), 10);
		w = parseInt(serial.substring(3,5), 10);
		
		if(serial.length!=5)
			erro = 1;
		else if(serial.substring(2,3).toUpperCase() != 'W')
			erro = 1;
		else if((isNaN(w)) || (w < 1) || (w > 52))
			erro = 1;
		else {
			if(parseInt(a) > parseInt(ano.toString().substring(2,4)))
				erro = 1;
			else if((parseInt(a) == parseInt(ano.toString().substring(2,4))) && (parseInt(w) > parseInt(semanas)))
				erro = 1;
		} 
		
		if(erro != 0){
			window.alert('DATA CODE Inválido!');
			campo.value = '';
			campo.focus();
		}
	}
}