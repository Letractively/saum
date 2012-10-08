//alterado
function fnCarregaComboServidorAlteraCallback(servs){
	var oComboBox = $$('combo-alt-servidores');
	for (var i = 0; i < servs.length ; i++){
		oComboBox.options[i + 1] = new Option(servs[i].servidorNome, servs[i].servidorId);
	}
}
function buscaTorresVinculadasAoServidor(){
	limpaTodoscampos();
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var o = $$('combo-alt-servidores');
	var comServ = o.options[o.selectedIndex].value;

	UtilsJS.buscaTorresVinculadasAoServidor(comServ, buscaComandosDaTorre);
}
function buscaComandosDaTorre(torres){
	limpaTodoscampos();
	for(var i = 1; i < 7; i++){
		DWRUtil.removeAllOptions("combo-torre-" + i);
		var oComboTorre = $$("combo-torre-" + i);
		for (var ii = 0; ii < torres.length ; ii++){
			oComboTorre.options[ii] = new Option(torres[ii].torreNome, torres[ii].torreId);
		}
	}
	
	var oComboBox = $$('combo-alt-servidores');
	var comboServ = oComboBox.options[oComboBox.selectedIndex].value;
	
	var oComboTipo = $$("combo-tipo");
	var comboTipo = oComboTipo.options[oComboTipo.selectedIndex].value;
	UtilsJS.buscaComandosDaTorre(comboServ, comboTipo, buscaComandosDaTorreCallback);
}
function buscaComandosDaTorreCallback(ret){
	if(ret != null){
		for(var i = 0; i < ret.length; i++){
			switch (ret[i].poolcomandosOrdem) {
			case 1:
				$$("cmd1").value = ret[i].poolcomandosComando;
				setaTorreDonaDoComando(ret[i].torre, ret[i].poolcomandosOrdem);
				break;
			case 2:
				$$("cmd2").value = ret[i].poolcomandosComando;
				setaTorreDonaDoComando(ret[i].torre, ret[i].poolcomandosOrdem);
				break;
			case 3:
				$$("cmd3").value = ret[i].poolcomandosComando;
				setaTorreDonaDoComando(ret[i].torre, ret[i].poolcomandosOrdem);
				break;
			case 4:
				$$("cmd4").value = ret[i].poolcomandosComando;
				setaTorreDonaDoComando(ret[i].torre, ret[i].poolcomandosOrdem);
				break;
			case 5:
				$$("cmd5").value = ret[i].poolcomandosComando;
				setaTorreDonaDoComando(ret[i].torre, ret[i].poolcomandosOrdem);
				break;
			case 6:
				$$("cmd6").value = ret[i].poolcomandosComando;
				setaTorreDonaDoComando(ret[i].torre, ret[i].poolcomandosOrdem);
				break;
			}
		}
	}
	modalMensagem.escondeModal();
}
function setaTorreDonaDoComando(torre, ordem){
	var oComboTorre = $$("combo-torre-" + ordem);
	for(var i = 0; i < oComboTorre.options.length; i++){
		if(oComboTorre.options[i].value == torre.torreId){
			oComboTorre.options[i].selected = true;
		}
	}
}
function limpaTodoscampos(){
	for(var i = 1; i < 7; i++){
		$$("cmd" + i).value = "";
	}
}
function gravaAlteracao(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	
	var oComboTipo = $$("combo-tipo");
	var comboTipo = oComboTipo.options[oComboTipo.selectedIndex].value;
	
	var oComboServ = $$('combo-alt-servidores');
	var comboServ = oComboServ.options[oComboServ.selectedIndex].value;
	
	var serv = {servidorId: comboServ};
	
	var bol = new Array();
	for(var i = 1; i < 7; i++){
		
		var oComboTorre = $$("combo-torre-" + i);
		var comboTorre = oComboTorre.options[oComboTorre.selectedIndex].value;
		var tor = {torreId: comboTorre};
		
		var cmd = {
				poolcomandosComando: $$("cmd" + i).value,
				poolcomandosOrdem: i,
				torre: tor,
				servidor: serv,
				poolcomandosTipo: comboTipo
			};
		bol.push(cmd);
	}
	UtilsJS.gravaAlteracaoComando(bol, gravaAlteracaoCallBack);
}

function gravaAlteracaoCallBack(ret){
	if(ret == 0){
		fnComandos();
	}else{
		modalMensagem.escondeModal();
		alert("Erro...\nProblema ao manipular a base de dados");
	}
}
function gerarSenha(){
	UtilsJS.geraChave(gerarSenhaCallBack);
}
function gerarSenhaCallBack(ret){
	$$("chave").innerHTML = ret;
}