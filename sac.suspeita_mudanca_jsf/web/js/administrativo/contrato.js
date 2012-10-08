function fnBuscaContratosCallBack(contratos){
	var oComboBox = $$("select-altera-contrato");
	for (var i = 0; i < contratos.length ; i++){
		oComboBox.options[i + 1] = new Option(contratos[i].contratoNome, contratos[i].contratoId);
	}
	modalMensagem.escondeModal();
}

function insereAlteraContrato(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();

	var id = $$("id").value;
	var nome = $$("nome").value;
	var contrato = tinyMCE.activeEditor.getContent();
	if(contrato == "" || nome == ""){
		alert("Preencha os dados do contrato.");
		modalMensagem.escondeModal();
	}else{
		var contrato = {
				contratoId:id,
				contratoContrato:contrato,
				contratoNome:nome
		}
		ContratoJS.salvaContrato(contrato, insereAlteraContratoCallBack);
	}
}

function insereAlteraContratoCallBack(ret){
	modalMensagem.escondeModal();
	if(ret){
		fnContrato();
	}
}

function carregaContratoParaAlteracao(){
	var oComboBox = $$("select-altera-contrato");
	var valorCombo = oComboBox.options[oComboBox.selectedIndex].value;
	
	if(valorCombo > 0){
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
		ContratoJS.buscaContrato(valorCombo, carregaContratoParaAlteracaoCallBack);
	}
}

function carregaContratoParaAlteracaoCallBack(contrato){
	$$("id").value = contrato.contratoId;
	$$("nome").value = contrato.contratoNome;
	tinyMCE.activeEditor.setContent(contrato.contratoContrato);
	modalMensagem.escondeModal();
}
