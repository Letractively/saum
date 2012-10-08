function iniciaCalendarioIni() {
	Calendar.setup({
		inputField : "dt-inicial",
		button     : "bt-data-ini",
		ifFormat   : "%d/%m/%Y"
	});
}


function iniciaCalendarioFin() {
	Calendar.setup({
		inputField : "dt-final",
		button     : "bt-data-fin",
		ifFormat   : "%d/%m/%Y"
	});
}


function carregaComboTipoLog(){
	AdministracaoJS.carregaComboTipoLog(callBackCarregaComboTipoLog);
}

function callBackCarregaComboTipoLog(retorno){
	var oCombo = $$("combo-tipo");
	var vlrSessao = $$("sessao-combo").value;
	DWRUtil.removeAllOptions("combo-tipo");
	for (var i = 0; i < retorno.length ; i++){
		var opt = new Option(retorno[i].valor, retorno[i].id);
		oCombo.options[i] = opt;
		if(vlrSessao != "" && retorno[i].id == vlrSessao){
			opt.selected = true;
		}
	}
}

function buscaLog(){
	var oCombo = $$("combo-tipo");
	var combo = oCombo.options[oCombo.selectedIndex].value;
	
	var dataInicial = $$("dt-inicial").value;
	var dataFinal = $$("dt-final").value;
	AdministracaoJS.buscaLog(combo, dataInicial, dataFinal, buscaLogCallBack);
}

function buscaLogCallBack(ret){
	if(ret == 0){
		fnRelatorioLog();
	}else{
		alert("Sem resultado.");
	}
}