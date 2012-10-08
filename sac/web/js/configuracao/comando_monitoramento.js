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
	
	UtilsJS.buscaTorresVinculadasAoServidor(comServ, buscaTorresVinculadasAoServidorCallback);
}
function buscaTorresVinculadasAoServidorCallback(torres){
	limpaTodoscampos();
	if(torres != null){
		DWRUtil.removeAllOptions("combo-torre-1");
		var oComboTorre = $$("combo-torre-1");
		for (var ii = 0; ii < torres.length ; ii++){
			oComboTorre.options[ii] = new Option(torres[ii].torreNome, torres[ii].torreId);
		}
		
		var oComboBox = $$('combo-alt-servidores');
		var comboServ = oComboBox.options[oComboBox.selectedIndex].value;
		
		var oComboTipo = $$("combo-tipo");
		var comboTipo = oComboTipo.options[oComboTipo.selectedIndex].value;
		UtilsJS.buscaComandoMonitoramentoTorre(comboServ, comboTipo, carregaComandoMonitoramentoTorreCallback);
	}else{
		modalMensagem.escondeModal();
	}
}


function carregaComandoMonitoramentoTorreCallback(ret){
	if(ret != null){
		var oComboTorre = $$("combo-torre-1");
		for(var i = 0; i < oComboTorre.options.length; i++){
			if(oComboTorre.options[i].value == ret.torre.torreId){
				oComboTorre.options[i].selected = true;
			}
		}
		
		$$("cmd1").value = ret.poolcomandosComando;
		$$("comando-exemplo").innerHTML = ret.comandoExemplo;
		$$("tb-comando").style.visibility = "visible";
	}else{
		$$("tb-comando").style.visibility = "hidden";
	}
	modalMensagem.escondeModal();
}
function limpaTodoscampos(){
	$$("cmd1").value = "";
}

function gravaAlteracao(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	
	var oComboTorre = $$("combo-torre-1");
	var comboTorre = oComboTorre.options[oComboTorre.selectedIndex].value;

	var oComboServ = $$("combo-alt-servidores");
	var comboServ = oComboServ.options[oComboServ.selectedIndex].value;
	
	var oComboTipo = $$("combo-tipo");
	var comboTipo = oComboTipo.options[oComboTipo.selectedIndex].value;
	
	var t = {torreId: comboTorre};
	var s = {servidorId: comboServ};
	
	var cmd = {
		poolcomandosComando: $$("cmd1").value,
		torre: t,
		servidor:s,
		poolcomandosIdentificador: comboTipo
		};
	UtilsJS.gravaAlteracaoComandoMonitoramento(cmd, gravaAlteracaoCallBack);
}

function gravaAlteracaoCallBack(ret){
	if(ret == 0){
		fnComandoMonitoramento();
	}else{
		modalMensagem.escondeModal();
		alert("Erro...\nProblema ao manipular a base de dados");
	}
}