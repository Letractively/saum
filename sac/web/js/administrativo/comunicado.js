function iniciaCalendario(){
	Calendar.setup({
		inputField : "dt-ativacao",
		button     : "bt-data",
		ifFormat   : "%d/%m/%Y"
	});
}
function fnCarregaAvisosCallBack(avisos){
	var oComboBox = $$("select-altera-comunicado");
	for (var i = 0; i < avisos.length ; i++){
		oComboBox.options[i] = new Option(avisos[i].avisosTitulo, avisos[i].avisosId);
	}
	modalMensagem.escondeModal();
}
function carregaComunicado(){
	var oComboBox = $$("select-altera-comunicado");
	var valorCombo = oComboBox.options[oComboBox.selectedIndex].value;
	if(valorCombo > 0){
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
		AdministracaoJS.carregaAviso(valorCombo, carregaComunicadoCallBack);
	}
}
function carregaComunicadoCallBack(aviso){
	$$("dt-ativacao").value = aviso.tmp;
	tinyMCE.activeEditor.setContent(aviso.avisosAviso);
	modalMensagem.escondeModal();
}
function novoComunicado(){
	$$("titulo").value = "";
	$$("dt-ativacao").value = "";
	tinyMCE.activeEditor.setContent("");
}
function gravaComunicado(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	
	var opcEscolhida = $$("tp-envio").options[$$("tp-envio").selectedIndex].value;
	
	var arrOpcoes = new Array();
	var oCombo = $$("combo-opcoes");
	for (var i=0; i<oCombo.options.length; i++) {
	    if (oCombo.options[i].selected) {
	    	arrOpcoes.push(oCombo.options[i].value);
	    }
	}
	var data = $$("dt-ativacao").value;
	var titulo = $$("titulo").value;
	var msg = tinyMCE.activeEditor.getContent();
	if(titulo == "" || data == "" || msg == ""){
		alert("Preencha todos os dados.");
		modalMensagem.escondeModal();
	}else{
		var aviso = {
				avisosAviso:msg,
				tmp:data,
				avisosTitulo:titulo
		};
		AdministracaoJS.salvaAviso(aviso, arrOpcoes, opcEscolhida, salvaAvisoCallBack);
	}
}
function salvaAvisoCallBack(ret){
	modalMensagem.escondeModal();
	if(ret){
		fnComunicado();
	}
}
function selecionaTipo(obj){
	var v = obj.options[obj.selectedIndex].value;
	if(v == 1){
		$$("conteudo-escolha-tipo").style.display = "none";
		DWRUtil.removeAllOptions("combo-opcoes");
		$$("combo-opcoes").multiple = false;
		$$("combo-opcoes").size = 1;
	}else if(v == 2){
		UtilsJS.carregaComboTodasTorre(fnRetornoTorre);
		$$("combo-opcoes").multiple = false;
		$$("combo-opcoes").size = 1;
	}else if(v == 3){
		AdministracaoJS.buscaComboClientes(fnRetornoClientes);
		$$("combo-opcoes").multiple = true;
		$$("combo-opcoes").size = 5;
	}else if(v == 4){
		AdministracaoJS.carregaComboDatasPagamento(fnRetornoDatasPG);
		$$("combo-opcoes").multiple = false;
		$$("combo-opcoes").size = 1;
	}else if(v == 5){
		carregaComboEstado("combo-opcoes");
		$$("combo-opcoes").multiple = false;
		$$("combo-opcoes").size = 1;
		$$("conteudo-escolha-tipo").style.display = "block";
	}
}
function fnRetornoClientes(ret){
	DWRUtil.removeAllOptions("combo-opcoes");
	var oComboBox = $$("combo-opcoes");
	for (var i = 0; i < ret.length ; i++){
		oComboBox.options[i] = new Option(ret[i].usuarioNome, ret[i].usuarioId);
	}
	$$("conteudo-escolha-tipo").style.display = "block";
}
function fnRetornoTorre(ret){
	DWRUtil.removeAllOptions("combo-opcoes");
	var oComboBox = $$("combo-opcoes");
	for (var i = 0; i < ret.length ; i++){
		oComboBox.options[i] = new Option(ret[i].torreNome, ret[i].torreId);
	}
	$$("conteudo-escolha-tipo").style.display = "block";
}
function fnRetornoDatasPG(retorno){
	var oCombo = $$("combo-opcoes");
	DWRUtil.removeAllOptions("combo-opcoes");
	for (var i = 0; i < retorno.length ; i++){
		var opt = new Option(retorno[i].valor, retorno[i].id);
		oCombo.options[i] = opt;
	}
	$$("conteudo-escolha-tipo").style.display = "block";
}