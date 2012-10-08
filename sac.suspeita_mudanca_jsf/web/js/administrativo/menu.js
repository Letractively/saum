function abreAlteraMenu(){
	var indexSelect = $$("select-menu").selectedIndex;
	var coluna = $$("select-menu").options[indexSelect].value;
	var parametros = {
	altura:400,
	largura:450,
	endereco:"menu-add.jsp?coluna=" +coluna};
	
	var modal = new Modal(parametros);
	modal.criaMosca();
	modal.criaDivConteudo();
}