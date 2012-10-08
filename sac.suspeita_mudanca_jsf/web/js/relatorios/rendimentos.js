function buscaRendimento(){
	
	var oComboEstado = $$("combo-estado");
	var comboEstado = oComboEstado.options[oComboEstado.selectedIndex].value;
	
	var oComboPesquisa = $$("combo-campo");
	var comboPesquisa = oComboPesquisa.options[oComboPesquisa.selectedIndex].value;
	
	var dataInicial = $$("dt-inicial").value;
	var dataFinal = $$("dt-final").value;
	
	AdministracaoJS.buscaRelatorioRendimento(comboEstado, comboPesquisa, dataInicial, dataFinal, buscaRelatorioRendimentoCallBack);
	
}

function buscaRelatorioRendimentoCallBack(ret){
	fnRelatorioRendimentos();
}

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

function iniciaSessao(){
	var comboEstado = $$("s-comboEstado").value;
	var comboPesquisa = $$("s-comboPesquisa").value;
	var oComboEstado = $$("combo-estado");
	var oComboPesquisa = $$("combo-campo");
	
	for(var i = 0; i < oComboEstado.options.length; i++){
		if(oComboEstado.options[i].value == comboEstado){
			oComboEstado.options[i].selected = true;
		}
	}
	
	for(var i = 0; i < oComboPesquisa.options.length; i++){
		if(oComboPesquisa.options[i].value == comboPesquisa){
			oComboPesquisa.options[i].selected = true;
		}
	}
	
}