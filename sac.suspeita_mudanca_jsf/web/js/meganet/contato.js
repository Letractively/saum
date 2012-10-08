function enviaMensagem(){
	var envia = true;
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var usuario = $$("usuario").value;
	var telefone = $$("telefone").value;
	var cr = $$("cr").value;
	var email = $$("email").value;
	var msg = $$("mensagem-texto").value;
	var tipo = $$("tipo").value;
	
	if(usuario == ""){
		envia = false;
		$$("usuario").style.background = "#FF0000";
	}else{
		$$("usuario").style.background = "#CCE3FD";
	}
	
	if(telefone == ""){
		envia = false;
		$$("telefone").style.background = "#FF0000";
	}else{
		$$("telefone").style.background = "#CCE3FD";
	}
	
	if(cr == ""){
		envia = false;
		$$("cr").style.background = "#FF0000";
	}else{
		$$("cr").style.background = "#CCE3FD";
	}
	
	if(email == ""){
		envia = false;
		$$("email").style.background = "#FF0000";
	}else{
		$$("email").style.background = "#CCE3FD";
	}
	
	if(msg == ""){
		envia = false;
		$$("mensagem-texto").style.background = "#FF0000";
	}else{
		$$("mensagem-texto").style.background = "#CCE3FD";
	}
	
	if(envia){
		var oContato = {
			contatoNome: usuario,
			contatoEndCr: cr,
			contatoTelefone: telefone,
			contatoMensagem: msg,
			contatoTipo: tipo,
			contatoEmail: email
		};
		ContatoJS.insereContato(oContato, fnInsereContatoCallBack);		
	}
}

function fnInsereContatoCallBack(retorno){
	$$("usuario").value = "";
	$$("telefone").value = "";
	$$("cr").value = "";
	$$("email").value = "";
	$$("mensagem-texto").value = "";
	modalMensagem.escondeModal();
	if(retorno == 1){
		alert("Mensagem Enviada.\nLogo entraremos em contato!");
	}
}