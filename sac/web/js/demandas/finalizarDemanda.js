//alterado
function finalizaDemanda(idCliente){
	$$("idDemanda").value = idCliente;
	carregaComboTipoErro();
	$$("fieldset-solicita-demanda").style.display = "block";
}

function gravaDemanda(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var desc = $$("descricao-demanda").value;
	var id = $$("idDemanda").value;
	var oCombo = $$("combo-tp-erro");
	var combo = oCombo.options[oCombo.selectedIndex].value;
	
	var data = $$("data").value;
	var dia = data.substring(0, 2);
	var mes = data.substring(3, 5);
	var ano = data.substring(6, 10);
	
	var oHora = $$("hora");
	var hora = oHora.options[oHora.selectedIndex].value;
	
	var oMinuto = $$("minuto");
	var minuto = oMinuto.options[oMinuto.selectedIndex].value;

	AdministracaoJS.finalizarDemanda(id, desc, combo, dia, mes, ano, hora, minuto, gravaDemandaCallBack);
}

function gravaDemandaCallBack(ret){
	if(ret == 0){
		fnAtenderDemanda();
	}else if(ret == 1){
		alert("Demanda não finalizada devido a um erro desconhecido.");
	}else if(ret == 2){
		alert("Demanda não finalizada\nUsuário sem boleto ou valor da visita não fixado.");
	}
	modalMensagem.escondeModal();
}
function carregaComboTipoErro(){
	AdministracaoJS.carregaComboTipoErro(callBackCarregaComboTipoErro);
}

function callBackCarregaComboTipoErro(retorno){
	var oCombo = $$("combo-tp-erro");
	DWRUtil.removeAllOptions("combo-tp-erro");
	for (var i = 0; i < retorno.length ; i++){
		var opt = new Option(retorno[i].valor, retorno[i].id);
		oCombo.options[i] = opt;
	}
}
function iniciaCalendario() {
	Calendar.setup({
		inputField : "data",
		button     : "bt-data",
		ifFormat   : "%d/%m/%Y"
	});
}
