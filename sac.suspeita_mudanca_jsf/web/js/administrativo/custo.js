function iniciaCalendarioIni() {
	Calendar.setup({
		inputField : "dt-inicial",
		button     : "bt-data-ini",
		ifFormat   : "%d/%m/%Y"
	});
}
function gravaCusto(){
	var motivo = $$("motivo-custo").value;
	var valor = $$("valor-custo").value;
	var data = $$("dt-inicial").value;
	var oComboTP = $$("combo-tipo");
	var comboTP = oComboTP.options[oComboTP.selectedIndex].value;

	var envia = true;
	
	if($$("motivo-custo").value == "" && comboTP == "Outros"){
		envia = false;
		$$("motivo-custo").style.background = "#FF0000";
	}else{
		$$("motivo-custo").style.background = "#CCE3FD";
	}
	if($$("valor-custo").value == ""){
		envia = false;
		$$("valor-custo").style.background = "#FF0000";
	}else{
		$$("valor-custo").style.background = "#CCE3FD";
	}
	if($$("dt-inicial").value == ""){
		envia = false;
		$$("dt-inicial").style.background = "#FF0000";
	}else{
		$$("dt-inicial").style.background = "#CCE3FD";
	}
	if(envia){
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
		AdministracaoJS.gravaCusto(comboTP, motivo, valor, data, gravaCustoCallBack);
	}
}
function gravaCustoCallBack(ret){
	if(ret == 2){
		fnCusto();
	}else{
		alert("Problema ao inserir custo.\nValor maior que R$$ 999,00");
		modalMensagem.escondeModal();
	}
}
function carregaComboTipoCustoCallBack(retorno){
	var oCombo = $$("combo-tipo");
	DWRUtil.removeAllOptions("combo-tipo");
	var opt = new Option("Tipo", "Tipo");
	oCombo.options[0] = opt;
	for (var i = 1; i < retorno.length + 1; i++){
		var opt = new Option(retorno[i -1].valor, retorno[i -1].id);
		oCombo.options[i] = opt;
	}

}
function selecionaTipo(){
	var oComboTP = $$("combo-tipo");
	var comboTP = oComboTP.options[oComboTP.selectedIndex].value;
	if(comboTP == "Outros"){
		$$("motivo-custo").disabled = false;
	}else{
		$$("motivo-custo").disabled = true;
	}

}