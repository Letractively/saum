//alterado
function insereUsuario(alteraMAC){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var envia = true;
	
	var mac = $$("mac").value;
	var usuario = $$("usuario").value;
	
	var oComboBloq = $$("combo-cliente-bloqueado");
	var comboBloq = oComboBloq.options[oComboBloq.selectedIndex].value;

	var oComboContrato = $$("combo-contrato");
	var comboContrato = oComboContrato.options[oComboContrato.selectedIndex].value;
	
	var oComboPlano = $$("combo-plano");
	var comboPlano = oComboPlano.options[oComboPlano.selectedIndex].value;
	
	var oComboEndIP = $$("combo-endereco-ip");
	var comboEndIP = oComboEndIP.options[oComboEndIP.selectedIndex].value;
	
	var oComboServ = $$("combo-servidor");
	var comboServ = oComboServ.options[oComboServ.selectedIndex].value;
	
	if(comboPlano == ""){
		envia = false;
		$$("combo-plano").style.background = "#FF0000";
	}else{
		$$("combo-plano").style.background = "#CCE3FD";
	}
	if(usuario == ""){
		envia = false;
		$$("usuario").style.background = "#FF0000";
	}else{
		$$("usuario").style.background = "#CCE3FD";
	}
	if(mac == ""){
		envia = false;
		$$("mac").style.background = "#FF0000";
	}else{
		$$("mac").style.background = "#CCE3FD";
	}
	if(comboEndIP == ""){
		envia = false;
		$$("combo-endereco-ip").style.background = "#FF0000";
	}else{
		$$("combo-endereco-ip").style.background = "#CCE3FD";
	}
	if(comboServ == ""){
		envia = false;
		$$("combo-servidor").style.background = "#FF0000";
	}else{
		$$("combo-servidor").style.background = "#CCE3FD";
	}
	if(envia){
		
		var oPlano = {planospacotesId:comboPlano};
		var oServ = {servidorId:comboServ};
		var oEnderecoIp = {enderecoipId:comboEndIP};
		var oContrato = {contratoId:comboContrato};
		
		var oUsuario = {
			planosPacotes: oPlano,
			servidor: oServ,
			enderecoIp: oEnderecoIp,
			contrato: oContrato,
			usuarioNome: usuario,
			usuarioMac: mac.toUpperCase(),
			usuarioBloqueado: comboBloq,
			usuarioAdministrativo: false
		};
		AdministracaoJS.insereUsuario(oUsuario, fnInsereUsuarioCallBack);
	}else{
		modalMensagem.escondeModal();
	}
}


function fnInsereUsuarioCallBack(retorno){
	if(retorno.resposta == null || retorno.resposta == "" || retorno == null){
		$$("form-insere-cliente").reset();
		fnCadastroAP();
	}else{
		alert("Ação: " + retorno.acao + "\nRetornou o erro: " + retorno.resposta);
	}
	modalMensagem.escondeModal();
}

function selectTorreEnderecoIP(){
	var oComboServ = $$("combo-servidor");
	var comboServ = oComboServ.options[oComboServ.selectedIndex].value;
	UtilsJS.carregaComboEnderecoIP(comboServ, fncarregaComboEnderecoIPCallback);
}
function verificarMAC(){
	var mac = $$("mac").value;
	UtilsJS.verificarMAC(mac, verificarMACCallBack);
}
function verificarMACCallBack(ret){
	if(ret != null){
		$$("div-observacao").innerHTML = "MAC já cadastrado para " + ret;
	}
}