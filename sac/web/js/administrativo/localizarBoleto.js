//alterado
function soNumero( campo, e ) {
	if(isShiftOrTab(e)) return;
	car = (navigator.appName == "Netscape" ) ? e.which : e.keyCode;
	var caractereEscape = (car != 37) && (car != 8) && (car != 46) && (car != 39);
	if(car == 8 || car == 46){
		return true;
	}
	var tecladoNumerico = car > 95 && car < 106;
	if ( (( car < 48 || car > 57 ) && ( car > 31 )) && (caractereEscape) && (!tecladoNumerico)) return false;
	return true;
}
function carregaUsuario2(){
	$$("localiza-bol").value = "";
	return carregaUsuario();
}
function carregaUsuario(){
	var cliente = $$("cliente").value;
	if(cliente != "" && cliente.length > 0){
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
		AdministracaoJS.buscaUsuarioPeloBoleto(cliente, carregaUsuarioCallBack);
	}
	return false;
}

function carregaUsuarioCallBack(boleto){
	
	if(boleto != null){
		carregaclientes(boleto);
	}else{
		$$("usuario-alt").value = "";
		$$("id").value = "";
		$$("cpf-alt").value = "";
		$$("telefone-alt").value = "";
		$$("telefone2-alt").value = "";
		$$("endereco").value = "";
		$$("complemento").value = "";
		$$("bairro").value = "";
		$$("cidade").value = "";
		$$("estado").value = "";
		$$("cep-alt").value = "";
		$$("email-alt").value = "";
		$$("mac-alt").value = "";
		$$("dt-ativacao").value = "";
		$$("combo-cliente-bloqueado").value = "";
		$$("combo-alt-data-pagamento").value = "";
		$$("combo-alt-plano").value = "";
		$$("combo-alt-torre").value = "";
		$$("combo-alt-endereco-ip").value = "";
		$$("bol-valor").value = "";
		$$("bol-valor-pago").value = "";
		$$("bol-dt-pagamento").value = "";
		$$("bol-dt-vencimento").value = "";
		$$("bol-pago-mao").value = "";

		alert("Não encontrado!");
	}
	modalMensagem.escondeModal();
}

function carregaclientes(boleto){
	var usuario = boleto.usuario;
	$$("usuario-alt").value = usuario.usuarioNome;
	$$("id").value = usuario.usuarioId;
	$$("id-bol").value = boleto.boletosgeradosId;
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
	$$("mac-alt").value = usuario.usuarioMac;
	$$("dt-ativacao").value = usuario.dataTemp;
	if(boleto.boletosgeradosIdF2B != null && boleto.boletosgeradosIdF2B != ""){
		$$("id-boleto").value = boleto.boletosgeradosId;
		$$("id-f2b").value = boleto.boletosgeradosIdF2B;
	}else{
		$$("id-boleto").value = boleto.boletosgeradosId;
		$$("id-f2b").value = boleto.boletosgeradosIdF2B;
	}
	
	switch(usuario.usuarioBloqueado){
		case 0: $$("combo-cliente-bloqueado").value = "Desb. e sem mensagem";
		break;
		case 1: $$("combo-cliente-bloqueado").value = "Desb. e com mensagem";
		break;
		case 2: $$("combo-cliente-bloqueado").value = "Bloqueado";
		break;
		case 3: $$("combo-cliente-bloqueado").value = "Desativado";
		break;
	}
	$$("combo-alt-data-pagamento").value = usuario.usuarioDtPagamento + " de cada mês";
	$$("combo-alt-plano").value = usuario.planosPacotes.planospacotesNome;
	$$("combo-alt-torre").value = usuario.servidor.servidorNome;

	if(usuario.enderecoIp != null){
		$$("combo-alt-endereco-ip").value = usuario.enderecoIp.enderecoipEndereco;
	}else{
		$$("combo-alt-endereco-ip").value = "";
	}
	$$("bol-valor").value = boleto.boletosgeradosValor;
	$$("bol-valor-pago").value = boleto.boletosgeradosValorPago;
	$$("bol-dt-pagamento").value = boleto.dataTempPagamento;
	$$("bol-dt-vencimento").value = boleto.dataTempVencimento;
	if(boleto.boletosgeradosPagouEmMao){
		$$("bol-pago-mao").value = "Sim";
		$$("bol-valor-pago-mao").value = boleto.boletosgeradosValorPago;
	}else{
		$$("bol-pago-mao").value = "Não";
		$$("bol-valor-pago-mao").value = "";
	}
	if(boleto.boletosgeradosPago){
		$$("chk-hab-pag").disabled = true;
	}else{
		$$("chk-hab-pag").disabled = false;
	}
}
function habilitaPagamento(obj){
	if(obj.checked == true){
		$$('bol-valor-pago-mao').readOnly=false;
		$$('bt-inf-pag').disabled = false;
	}else{
		$$('bol-valor-pago-mao').readOnly=true;
		$$('bt-inf-pag').disabled = true;
	}
}
function informaPagamentoEmMao(){
	var envia = true;
	var idBol = $$("id-bol").value;
	var valor = $$("bol-valor-pago-mao").value;
	
	if(valor == ""){
		envia = false;
		$$("bol-valor-pago-mao").style.background = "#FF0000";
	}else{
		$$("bol-valor-pago-mao").style.background = "#CCE3FD";
	}
	
	if(envia){
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
		AdministracaoJS.informaPagamentoEmMaoPeloIdBoleto(idBol, valor, informaPagamentoEmMaoCallBack);
	}
}
function informaPagamentoEmMaoCallBack(ret){
	if(ret == 2){
		var loc = $$("localiza-bol").value;
		if(loc == ""){
			fnLocalizarBoleto();
		}else{
			fnAtendimentoCliente();
		}
	}else if(ret == 1){
		alert("Boleto ou usuário não encontrado.");
		modalMensagem.escondeModal();
	}else if(ret == 5){
		alert("Usuário desativado.\nBoleto foi dado baixa,\nmas o usuário não foi desbloqueado.");
		modalMensagem.escondeModal();
	}else if(ret == 3){
		alert("Existem boletos anteriores a esse em aberto.\nEles devem ser pagos antes.");
		modalMensagem.escondeModal();
	}else if(ret == 4){
		alert("Pagamento em mão não permitido para esse usuario.");
		modalMensagem.escondeModal();
	}
}
