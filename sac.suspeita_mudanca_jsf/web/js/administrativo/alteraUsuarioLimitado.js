//alterado
function carregaUsuarioParaAlteraccao(){
	AdministracaoJS.carregaCliente(carregaclientesAltera);
}

function carregaclientesAltera(usuario){
	$$("usuario-alt").value = usuario.usuarioNome;
	$$("id").value = usuario.usuarioId;
	$$("cpf-alt").value = usuario.usuarioCpf;
	$$("telefone-alt").value = usuario.usuarioTelefone1;
	$$("telefone2-alt").value = usuario.usuarioTelefone2;
	$$("endereco").value = usuario.usuarioEndereco;
	$$("complemento").value = usuario.usuarioComplementoEndereco;
	$$("bairro").value = usuario.usuarioBairro;
	$$("cidade").value = usuario.usuarioCidade;
	$$("estado").value = usuario.usuarioEstado;
	$$("cep-alt").value = usuario.usuarioCep;
	$$("email-alt").value = usuario.usuarioEmail;

	if(usuario.usuarioImprimeBoleto){
		$$("recebe-boleto").checked = true;
	}else{
		$$("recebe-boleto").checked = false;
	}
	
	$$("email-alt").value = usuario.usuarioEmail;

	$$("spanContrato").innerHTML = "<a href=\"#\" onclick=\"mostraContrato()\">"+ usuario.contrato.contratoNome +"</a>";
	
	var oComboPg = $$("combo-alt-data-pagamento");
	oComboPg.disabled = true;
	for(var i = 0; i < oComboPg.options.length; i++){
		if(oComboPg.options[i].value == usuario.usuarioDtPagamento){
			oComboPg.options[i].selected = true;
		}
	}
	modalMensagem.escondeModal();
}

function mostraContrato(){
	var idCliente = $$("id").value;
	var popup = window.open('visualizaContrato.do?id=' + idCliente, 'contrato','width=700,height=660,top=10,left=150,toolbar=no,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes');
	
	if(popup == null){
		alert("Bloqueador de pop ativado.\nDesative para visualizar o boleto.");
	}
}

function alteraUsuario(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var envia = true;
	var id = $$("id").value;
	var telefone = $$("telefone-alt").value;
	var telefone2 = $$("telefone2-alt").value;
	var email = $$("email-alt").value;

	var imprimeBoleto = false;
	if($$("recebe-boleto").checked){
		imprimeBoleto = true;
	}
	
	if(telefone == "" && telefone2 == ""){
		envia = false;
		alert("Preencha no mínimo um telefone");
	}
	
	if(email == ""){
		envia = false;
		$$("email-alt").style.background = "#FF0000";
	}else{
		$$("email-alt").style.background = "#CCE3FD";
	}
	
	if(envia){
		var oUsuario = {
			usuarioId: id,
			usuarioTelefone1: telefone,
			usuarioTelefone2: telefone2,
			usuarioEmail: email,
			usuarioImprimeBoleto: imprimeBoleto
		};
		AdministracaoJS.alteraUsuarioCliente(oUsuario, fnAlteracaoUsuarioCallBack);
	}else{
		modalMensagem.escondeModal();
	}
}

function fnAlteracaoUsuarioCallBack(retorno){
	if(retorno.resposta == null || retorno.resposta == ""){
		fnAlteraUsuario();
	}else{
		alert("Ação: " + retorno.acao + "\nRetornou o erro: " + retorno.resposta);
	}
	modalMensagem.escondeModal();
}
function carregaComboDatasPagamento(){
	AdministracaoJS.carregaComboDatasPagamento(callBackCarregaComboDatasPagamento);
}
function callBackCarregaComboDatasPagamento(retorno){
	var oCombo = $$("combo-alt-data-pagamento");
	DWRUtil.removeAllOptions("combo-alt-data-pagamento");
	for (var i = 0; i < retorno.length ; i++){
		var opt = new Option(retorno[i].valor, retorno[i].id);
		oCombo.options[i] = opt;
	}
}
function verificaTamanho(obj){
	if(obj.value.length < 13){
		obj.value = "";
	}
}