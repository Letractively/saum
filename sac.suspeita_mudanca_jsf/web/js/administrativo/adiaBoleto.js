//alterado
function buscaUsuarioCredito(){
	
	var cliente = $$("credito-cliente").value;
	var radioTp = document.getElementsByName("tp-credito");
	var tpPesquisa = "";
	for(var i = 0; i < radioTp.length; i++){
		if(radioTp[i].checked == true){
			tpPesquisa = radioTp[i].value;
		}
	}
	var envia = true;
	if($$("credito-cliente").value == ""){
		envia = false;
		$$("credito-cliente").style.background = "#FF0000";
	}else{
		$$("credito-cliente").style.background = "#CCE3FD";
	}
	if(envia){
		AdministracaoJS.buscaUsuarioCredito(cliente, tpPesquisa, false, buscaUsuarioCreditoCallBack);
	}
}
function buscaUsuarioCreditoCallBack(boletos){
	if(boletos != null){
		for(var i = 0; i < boletos.length && i < 3; i++){
			if(i == 0){
				$$("cpf").innerHTML = boletos[i].usuario.usuarioCpf;
				$$("nome").innerHTML = boletos[i].usuario.usuarioNome;
				$$("endereco").innerHTML = boletos[i].usuario.usuarioEndereco + " - " + boletos[i].usuario.usuarioComplementoEndereco;
			}
			
			$$("valor-" + (i+1)).innerHTML = boletos[i].boletosgeradosValor;
			$$("dt-" + (i+1)).innerHTML = boletos[i].dataTempVencimento;
			$$("dt-adiar-usr-" + (i+1)).value = boletos[i].dataTempVencimento;
			
			$$("id-boleto-" + (i+1)).value = boletos[i].boletosgeradosId;
			$$("conteiner-credito").style.display = "block";
		}
	}else{
		alert("Não encontrado.");
		$$("conteiner-credito").style.display = "none";

	}
}

function formataCreditoCpfNome( campo, e ) {
	if($$("credito-cpf").checked){
		car = (navigator.appName == "Netscape" ) ? e.which : e.keyCode;
		var caractereEscape = (car != 37) && (car != 8) && (car != 46) && (car != 39);
		if(car == 8 || car == 46){
			return true;
		}
		if(campo.value.length > 13){
			return false;
		}

		var tecladoNumerico = car > 95 && car < 106;
		if ( (( car < 48 || car > 57 ) && ( car > 31 )) && (caractereEscape) && (!tecladoNumerico)) return false;
		
		if ( (campo.value.length == 3 || campo.value.length == 7 ) && caractereEscape){
			if (campo.value != '.' && caractereEscape){
				campo.value = campo.value + ".";
			}
		}
		
		if ( campo.value.length == 11 && caractereEscape){
			if (campo.value != "-"){
				campo.value = campo.value + "-";
			}
		}
	}else{
		procuraCLiente("credito-cliente");
		return true;
	}
	
}
function adiaBoletoData(){
	var ini = $$("dt-inicial").value;
	var fim = $$("dt-final").value;
	var dt = $$("dt-adiar-venc").value;

	if(ini == "" || fim == "" || dt == ""){
		alert("Tem data faltando...");
	}else{
		AdministracaoJS.adiaBoletoPorDataCliente(ini, fim, dt, adiaBoletoNaoCadastradoClienteCallBack);
	}
}

function iniciaCalendario() {
	Calendar.setup({
		inputField : "dt-adiar-venc",
		button     : "bt-dt-adiar-venc",
		ifFormat   : "%d/%m/%Y"
	});
	Calendar.setup({
		inputField : "dt-adiar-usr-1",
		button     : "bt-dt-adiar-usr-1",
		ifFormat   : "%d/%m/%Y"
	});
	Calendar.setup({
		inputField : "dt-adiar-usr-2",
		button     : "bt-dt-adiar-usr-2",
		ifFormat   : "%d/%m/%Y"
	});
	Calendar.setup({
		inputField : "dt-adiar-usr-3",
		button     : "bt-dt-adiar-usr-3",
		ifFormat   : "%d/%m/%Y"
	});
	Calendar.setup({
		inputField : "dt-inicial",
		button     : "bt-inicial",
		ifFormat   : "%d/%m/%Y"
	});
	Calendar.setup({
		inputField : "dt-final",
		button     : "bt-final",
		ifFormat   : "%d/%m/%Y"
	});
}
function seleciona(opcao){
	switch (opcao) {
	case 1:
		document.getElementById("dv-dt-1").style.display = "block";
		document.getElementById("dv-dt-2").style.display = "none";
		document.getElementById("dv-dt-3").style.display = "none";
		return;
	case 2:
		document.getElementById("dv-dt-1").style.display = "none";
		document.getElementById("dv-dt-2").style.display = "block";
		document.getElementById("dv-dt-3").style.display = "none";
		return;
	case 3:
		document.getElementById("dv-dt-1").style.display = "none";
		document.getElementById("dv-dt-2").style.display = "none";
		document.getElementById("dv-dt-3").style.display = "block";
		return;
	}
}
function adiaBoletoUsuario(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();

	var envia = true;
	var cliente = $$("credito-cliente").value;
	var radioTp = document.getElementsByName("tp-credito");
	var radioDT = document.getElementsByName("rd-alt");
	var tpPesquisa = "";
	var data = "";
	var oData = null;
	var idBol = 0;
	for(var i = 0; i < radioTp.length; i++){
		if(radioTp[i].checked == true){
			tpPesquisa = radioTp[i].value;
		}
	}
	for(var i = 1; i <= radioDT.length; i++){
		if(radioDT[i - 1].checked == true){
			oData = document.getElementById("dt-adiar-usr-" + i);
			idBol = document.getElementById("id-boleto-" + i).value;
		}
	}
	if(cliente == ""){
		envia = false;
		$$("credito-cliente").style.background = "#FF0000";
	}else{
		$$("credito-cliente").style.background = "#CCE3FD";
	}
	if(oData.value == ""){
		envia = false;
		oData.style.background = "#FF0000";
	}else{
		oData.style.background = "#CCE3FD";
		data = oData.value;
	}
	if(envia){
		AdministracaoJS.adiaBoletoCliente(tpPesquisa, cliente, data, idBol, adiaBoletoNaoCadastradoClienteCallBack);
	}
}

function adiaBoletoNaoCadastradoClienteCallBack(ret){
	alert(ret.acao + "\n" + ret.resposta);
	if(ret.ehErro == true){
		modalMensagem.escondeModal();
	}else{
		fnAdiaBoleto();
	}
}
