//alterado
var idArq;
var arrArquivosInvalidos = new Array();
function existeLogParaEsseArquivo(nome, id){
	idArq = id;
	$$("content-msg-erro").style.display = "none";
	$$("content-msg-erro").innerHTML = "";
	AdministracaoJS.existeLogParaEsseArquivo(nome, existeLogParaEsseArquivoCallBack);
}
function existeLogParaEsseArquivoCallBack(ret){
	if(ret == 1){
		$$(idArq).className = "msg-erro";
		$$(idArq).innerHTML += "<span>&nbsp;&nbsp;-&nbsp;&nbsp;<b>Arquivo duplicado não será enviado</b></span>";
		uploader.removeFile({id:idArq});
	}else{
		$$(idArq).className = "msg";
	}
}