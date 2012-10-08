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

function buscaRelatorio(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var dataInicial = $$("dt-inicial").value;
	var dataFinal = $$("dt-final").value;
	BoletoJS.buscaBoletosPagoEmMao(dataInicial, dataFinal, buscaRelatorioCallBack);
}

function buscaRelatorioCallBack(ret){
	fnRelatorioBoletoPagoEmMao();	
}

function setaEntregue(id){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var dataInicial = $$("dt-inicial").value;
	var dataFinal = $$("dt-final").value;
	BoletoJS.setValorBoletoEntregue(dataInicial, dataFinal, id, setaEntregueCallBack);
}
function setaEntregueCallBack(id){
	var divInf = $$("idTb" + id);
	divInf.innerHTML = "Entregue";
	modalMensagem.escondeModal();
}