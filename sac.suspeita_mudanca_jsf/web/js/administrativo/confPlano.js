//alterado
function carregaPlanoAlteracao(){
	var oPLano = $$("select-plano");
	var plano = oPLano.options[oPLano.selectedIndex].value;
	if(plano != -1){
		UtilsJS.carregaPlanoParaAlteracao(plano, carregaPlanoAlteracaoCallBack);
	}else{
		carregaPlanoAlteracaoCallBack(null);
	}
}

function carregaPlanoAlteracaoCallBack(plantao){
	$$("id-plano").value = plantao == null ? "" : plantao.planospacotesId;
	$$("nome-plano").value = plantao == null ? "" : plantao.planospacotesNome;
	$$("valor-plano").value = plantao == null ? "" : plantao.planospacotesValor;
	$$("velocidade-plano").value = plantao == null ? "" : plantao.planospacotesVelocidade;
	$$("descricao-plano").value = plantao == null ? "" : plantao.planospacotesDescricao;
	$$("juroPorMes").value = plantao == null ? "" : plantao.planospacotesJuroMes;
	$$("multa").value = plantao == null ? "" : plantao.planospacotesMulta;
	$$("desconto").value = plantao == null ? "" : plantao.planospacotesDesconto;
	$$("diasDesconto").value = plantao == null ? "" : plantao.planospacotesLimiteDesconto;
	
	if(plantao != null){
		if(plantao.planospacotesAtivado){
			$$("plano-ativado").checked = true;
		}else{
			$$("plano-ativado").checked = false;
		}
	
		if(plantao.planospacotesBloqueiaPgAtrasado){
			$$("bloqueia-atraso").checked = true;
		}else{
			$$("bloqueia-atraso").checked = false;
		}
	}else{
		$$("plano-ativado").checked = false;
		$$("bloqueia-atraso").checked = false;
	}
}

function gravaPlano(){
	var envia = true;
	
	if($$("juroPorMes").value == ""){
		envia = false;
		$$("juroPorMes").style.background = "#FF0000";
	}else{
		$$("juroPorMes").style.background = "#CCE3FD";
	}
	if($$("multa").value == ""){
		envia = false;
		$$("multa").style.background = "#FF0000";
	}else{
		$$("multa").style.background = "#CCE3FD";
	}
	if($$("desconto").value == ""){
		envia = false;
		$$("desconto").style.background = "#FF0000";
	}else{
		$$("desconto").style.background = "#CCE3FD";
	}
	if($$("diasDesconto").value == ""){
		envia = false;
		$$("diasDesconto").style.background = "#FF0000";
	}else{
		$$("diasDesconto").style.background = "#CCE3FD";
	}
	if($$("velocidade-plano").value == ""){
		envia = false;
		$$("velocidade-plano").style.background = "#FF0000";
	}else{
		$$("velocidade-plano").style.background = "#CCE3FD";
	}
	
	if($$("descricao-plano").value == ""){
		envia = false;
		$$("descricao-plano").style.background = "#FF0000";
	}else{
		$$("descricao-plano").style.background = "#CCE3FD";
	}
	
	if($$("valor-plano").value == ""){
		envia = false;
		$$("valor-plano").style.background = "#FF0000";
	}else{
		$$("valor-plano").style.background = "#CCE3FD";
	}
	
	if($$("nome-plano").value == ""){
		envia = false;
		$$("nome-plano").style.background = "#FF0000";
	}else{
		$$("nome-plano").style.background = "#CCE3FD";
	}
	
	if(envia){
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
		var oPlano = {
				planospacotesId: $$("id-plano").value,
				planospacotesNome: $$("nome-plano").value,
				planospacotesValor: $$("valor-plano").value,
				planospacotesDescricao: $$("descricao-plano").value,
				planospacotesVelocidade: $$("velocidade-plano").value,
				planospacotesAtivado: $$("plano-ativado").checked == true ? true : false, 
				planospacotesBloqueiaPgAtrasado: $$("bloqueia-atraso").checked == true ? true : false,
				planospacotesJuroMes: $$("juroPorMes").value,
				planospacotesMulta: $$("multa").value,
				planospacotesDesconto: $$("desconto").value,
				planospacotesLimiteDesconto: $$("diasDesconto").value
		};
		UtilsJS.gravaPlanoPacote(oPlano, alteraInfBoletoCallBack);
	}
}
function alteraInfBoletoCallBack(ret){
	if(ret){
		fnConfiguracaoPlano();
	}else{
		alert("Erro na alteração");
	}
	modalMensagem.escondeModal();
}