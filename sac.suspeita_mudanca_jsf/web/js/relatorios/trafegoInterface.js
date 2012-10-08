function iniciaCalendarioIni() {
	Calendar.setup({
		inputField : "dt-inicial",
		button     : "bt-data-ini",
		ifFormat   : "%d/%m/%Y"
	});
}
function buscaRelatorio(){
	var dataInicial = $$("dt-inicial").value;
	var oComboTorre = $$("combo-alt-torre");
	var comboTorre = oComboTorre.options[oComboTorre.selectedIndex].value;
	var oDiario = $$("diario");
	var diario = 0;
	if(oDiario.checked){
		diario = 1;
	}
	AdministracaoJS.buscaRelatorioTrafegoInterface(dataInicial, comboTorre, diario, buscaRelatorioCallBack);
}

function buscaRelatorioCallBack(xml){
	var chart = new FusionCharts("swf/MSArea.swf", "ChartId", "600", "300", "0", "0");
	//chart.setDataURL(xml);
	chart.setDataXML(xml);		   
	chart.render("chartdiv");
	
}