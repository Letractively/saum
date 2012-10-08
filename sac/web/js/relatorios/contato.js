function iniciaCalendarioIni() {
	Calendar.setup({
		inputField : "dt-inicial",
		button     : "bt-data-ini",
		ifFormat   : "%d/%m/%Y"
	});
}
function iniciaCalendarioFin() {
	Calendar.setup({
		inputField : "dt-final",
		button     : "bt-data-fin",
		ifFormat   : "%d/%m/%Y"
	});
}
function buscaContatos(){
	var dataInicial = $$("dt-inicial").value;
	var dataFinal = $$("dt-final").value;
	
	var aberto;
	if($$("aberto").checked){
		aberto = 1;
	}else{
		aberto = 2;
	}
	AdministracaoJS.buscaRelatorioContatos(dataInicial, dataFinal, aberto, buscaDemandasCallBack);
}
function buscaDemandasCallBack(ret){
	fnRelatorioContato();
}
function iniciaSessao(){
	var aberto = $$("sessao-aberto").value;
	if(aberto == 0 || aberto == 2){
		$$("aberto").checked = false;
	}else{
		$$("aberto").checked = true;
	}
}
function buscaMensagem(idContato){
	var parametros = {
	altura:400,
	largura:500,
	endereco:"detalharContato.do?tp=" + idContato};
	
	var modal = new Modal(parametros);
	modal.criaMosca();
	modal.criaDivConteudo();
}