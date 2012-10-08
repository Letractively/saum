function buscaMensagem(idContato){
	$$("idContato").value = idContato;
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	AdministracaoJS.buscaMensagemCliente(idContato, buscaMensagemCallBack);
}
function buscaMensagemCallBack(ret){
	$$("fieldset-reponde-contato").style.display = "block";
	$$("descricao-mensagem").value = ret;
	modalMensagem.escondeModal();
}
function gravaResposta(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var desc = $$("descricao-fechamento").value;
	var id = $$("idContato").value;
	AdministracaoJS.gravaResposta(id, desc, gravaRespostaCallBack);
}
function gravaRespostaCallBack(ret){
	if(ret == 0){
		fnResponderContato();
	}else if(ret == 1){
		alert("Resposta não enviada devido a um erro desconhecido.");
	}
	modalMensagem.escondeModal();
}