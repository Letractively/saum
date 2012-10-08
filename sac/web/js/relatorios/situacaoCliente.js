function buscaSituacaoCliente() {
	var asm = $$("asm").checked;
	var acm = $$("acm").checked;
	var blo = $$("blo").checked;
	var des = $$("des").checked;
	AdministracaoJS.buscaSituacaoCliente(asm, acm, blo, des, buscaSituacaoClienteCallBack);
}
function buscaSituacaoClienteCallBack(ret) {
	fnRelatorioSituacaoCliente();
}