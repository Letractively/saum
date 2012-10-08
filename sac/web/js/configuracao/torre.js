//alterado
function buscaDadosTorre(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var oComboTorre = $$("combo-alt-torre");
	var comboTorre = oComboTorre.options[oComboTorre.selectedIndex].value;
	UtilsJS.buscaDadosTorre(comboTorre, buscaDadosTorreCallback);
}
function buscaDadosTorreCallback(ret){
	if(ret != null){
		$$("id").value = ret.torreId != null ? ret.torreId : "";
		$$("nome").value = ret.torreNome != null ? ret.torreNome : "";
		$$("modelo").value = ret.torreModelo != null ? ret.torreModelo : "";
		$$("processador").value = ret.torreProcessador != null ? ret.torreProcessador : "";
		$$("memoria").value = ret.torreMemoriaRam != null ? ret.torreMemoriaRam : "";
		$$("espaco-disco").value = ret.torreEspacoDisco != null ? ret.torreEspacoDisco : "";
		$$("local").value = ret.torreLocal != null ? ret.torreLocal : "";
		$$("usuario").value = ret.torreUsuario != null ? ret.torreUsuario : "";
		$$("senha").value = ret.torreSenha != null ? ret.torreSenha : "";
		$$("nome-interface").value = ret.torreNomeInterface != null ? ret.torreNomeInterface : "";
		$$("ip1").value = ret.torreIpConexao != null ? ret.torreIpConexao : "";
		$$("ip2").value = ret.torreIpConexao2 != null ? ret.torreIpConexao2 : "";
		$$("porta").value = ret.torrePorta != null ? ret.torrePorta : "";
		$$("bt-alterar").disabled = false;
		$$("bt-cancelar").disabled = false;
		$$("ip-intermediador").value = ret.torreIpIntermediador != null ? ret.torreIpIntermediador : "";
		$$("porta-intermediador").value = ret.torrePortaIntermediador != null ? ret.torrePortaIntermediador : "";
		
		var situacao = $$("situacao");
		for(var i = 0; i < situacao.options.length; i++){
			if(situacao.options[i].value == ret.torreDesativado + ""){
				situacao.options[i].selected = true;
			}
		}
		var intermed = $$("usa-intermediador");
		for(var i = 0; i < intermed.options.length; i++){
			if(intermed.options[i].value == ret.torreUtilizarIntermediador + ""){
				intermed.options[i].selected = true;
			}
		}
	}
	modalMensagem.escondeModal();
}
function novo(){
	$$("combo-alt-torre").options[0].selected = true;
	$$("combo-alt-torre").disabled = true;
	$$("bt-enviar").disabled = false;
	$$("bt-cancelar").disabled = false;
	$$("bt-alterar").disabled = true;
	$$("nome").disabled = false;
	limpaTodoscampos();
	alterar();
}
function limpaTodoscampos(){
	$$("id").value = "";
	$$("nome").value = "";
	$$("modelo").value = "";
	$$("processador").value = "";
	$$("memoria").value = "";
	$$("espaco-disco").value = "";
	$$("local").value = "";
	$$("usuario").value = "";
	$$("senha").value = "";
	$$("nome-interface").value = "";
	$$("ip1").value = "";
	$$("ip2").value = "";
	$$("porta").value = "";
	$$("situacao").options[0].selected = true;
	$$("combo-alt-torre").options[0].selected = true;
}
function cancela(){
	$$("combo-alt-torre").options[0].selected = true;
	$$("combo-alt-torre").disabled = false;
	limpaTodoscampos();
	desabilitaCampos();
	$$("usa-intermediador").selectedIndex = 0;
	$$("usa-intermediador").disabled = true;
	desabilitaELimpaCamposDoIntermediador();
}

function desabilitaCampos(){
	$$("bt-cancelar").disabled = true;
	$$("bt-enviar").disabled = true;	
	$$("bt-alterar").disabled = true;	
	$$("nome").disabled = true;
	$$("modelo").disabled = true;
	$$("processador").disabled = true;
	$$("memoria").disabled = true;
	$$("espaco-disco").disabled = true;
	$$("local").disabled = true;
	$$("usuario").disabled = true;
	$$("senha").disabled = true;
	$$("nome-interface").disabled = true;
	$$("ip1").disabled = true;
	$$("ip2").disabled = true;
	$$("porta").disabled = true;
	$$("situacao").disabled = true;
}
function alterar(){
	$$("modelo").disabled = false;
	$$("processador").disabled = false;
	$$("memoria").disabled = false;
	$$("espaco-disco").disabled = false;
	$$("local").disabled = false;
	$$("usuario").disabled = false;
	$$("senha").disabled = false;
	$$("nome-interface").disabled = false;
	$$("ip1").disabled = false;
	$$("ip2").disabled = false;
	$$("porta").disabled = false;
	$$("situacao").disabled = false;
	$$("bt-enviar").disabled = false;
	$$("usa-intermediador").disabled = false;
	desabilitaELimpaCamposDoIntermediador();
}
function desabilitaELimpaCamposDoIntermediador(){
	var oUsa = $$("usa-intermediador");
	var usa = oUsa.options[oUsa.selectedIndex].value;
	if(usa == "true"){
		$$("ip-intermediador").disabled = false;
		$$("porta-intermediador").disabled = false;
	}else{
		$$("ip-intermediador").disabled = true;
		$$("porta-intermediador").disabled = true;
		$$("ip-intermediador").value = "";
		$$("porta-intermediador").value = "";
	}
}
function gravaAlteracao(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var id = $$("id").value == "" ? null : $$("id").value;
	var nome = $$("nome").value;
	var modelo = $$("modelo").value;
	var processador = $$("processador").value;
	var memoria = $$("memoria").value;
	var espaco = $$("espaco-disco").value;
	var local = $$("local").value;
	var usuario = $$("usuario").value;
	var senha = $$("senha").value;
	var nomeInterface = $$("nome-interface").value;
	var ip1 = $$("ip1").value;
	var ip2 = $$("ip2").value;
	var porta = $$("porta").value;
	var ipInt = $$("ip-intermediador").value;
	var portaInt = $$("porta-intermediador").value;

	
	var oSituacao = $$("situacao");
	var situacao = oSituacao.options[oSituacao.selectedIndex].value;

	var oUsa = $$("usa-intermediador");
	var usa = oUsa.options[oUsa.selectedIndex].value;
	
	var envia = true;
	if(nome == ""){
		envia = false;
		$$("nome").style.background = "#FF0000";
	}else{
		$$("nome").style.background = "#CCE3FD";
	}
	if(usa == "true"){
		if(ipInt == ""){
			envia = false;
			$$("ip-intermediador").style.background = "#FF0000";
		}else{
			$$("ip-intermediador").style.background = "#CCE3FD";
		}
		if(portaInt == ""){
			envia = false;
			$$("porta-intermediador").style.background = "#FF0000";
		}else{
			$$("porta-intermediador").style.background = "#CCE3FD";
		}
	}
	if(usuario == ""){
		envia = false;
		$$("usuario").style.background = "#FF0000";
	}else{
		$$("usuario").style.background = "#CCE3FD";
	}
	if(senha == ""){
		envia = false;
		$$("senha").style.background = "#FF0000";
	}else{
		$$("senha").style.background = "#CCE3FD";
	}
	if(nomeInterface == ""){
		envia = false;
		$$("nome-interface").style.background = "#FF0000";
	}else{
		$$("nome-interface").style.background = "#CCE3FD";
	}
	if(porta == ""){
		envia = false;
		$$("porta").style.background = "#FF0000";
	}else{
		$$("porta").style.background = "#CCE3FD";
	}
	if(ip1 == ""){
		envia = false;
		$$("ip1").style.background = "#FF0000";
	}else{
		$$("ip1").style.background = "#CCE3FD";
	}
	if(ip2 == ""){
		envia = false;
		$$("ip2").style.background = "#FF0000";
	}else{
		$$("ip2").style.background = "#CCE3FD";
	}
	
	if(envia){
		var t = {
			torreId:id,
			torreNome:nome,
			torreModelo:modelo,
			torreProcessador:processador,
			torreMemoriaRam:memoria,
			torreEspacoDisco:espaco,
			torreLocal:local,
			torreUsuario:usuario,
			torreSenha:senha,
			torreNomeInterface:nomeInterface,
			torreIpConexao:ip1,
			torreIpConexao2:ip2,
			torrePorta:porta,
			torreDesativado:situacao,
			torreUtilizarIntermediador:usa,
			torreIpIntermediador:ipInt,
			torrePortaIntermediador:portaInt
		};
		AdministracaoJS.salvaTorre(t, gravaAlteracaoCallBack);
	}else{
		modalMensagem.escondeModal();
	}
}

function gravaAlteracaoCallBack(ret){
	if(ret == 0){
		fnConfiguraTorre();
	}else{
		modalMensagem.escondeModal();
		alert("Erro...\nProblema ao manipular a base de dados");
	}
}

function numero(campo, e){
	car = (navigator.appName == "Netscape" ) ? e.which : e.keyCode;
	var caractereEscape = (car != 37) && (car != 8) && (car != 46) && (car != 39);
	if(car == 8 || car == 46){
		return true;
	}
	var tecladoNumerico = car > 95 && car < 106;
	if ( (( car < 48 || car > 57 ) && ( car > 31 )) && (caractereEscape) && (!tecladoNumerico)) return false;
	return true;
}
function testarConexao(ip){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();

	if($$(ip).value != ""){
		AdministracaoJS.testarConexao($$(ip).value, $$("porta").value, testarConexaoCallBack);
	}else{
		modalMensagem.escondeModal();
		alert("Preencha o IP da torre");
	}
}
function testarConexaoCallBack(ret){
	modalMensagem.escondeModal();
	if(ret == true){
		alert("Teste de conexão realizado com sucesso.");
	}else{
		alert("Ops! Não foi possível conectar...");
	}
}