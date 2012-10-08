function buscaDadosMenus(coluna){
	$$("hidden-coluna-escolhida-para-modificacao").value = coluna;
	AdministracaoJS.buscaMenu(coluna, buscaDadosMenusCallBack);
}
function buscaDadosMenusCallBack(dados){
	var oTable = $$('tabela-altera-menu');
	fnClearTable(oTable);
	var oTbody = oTable.tBodies[0];
	
	for(var i = 0; i < dados.length; i++){
		var oRow = oTbody.insertRow(oTbody.rows.length);
		oRow.onclick = fnRetornoMenu(dados[i]["menuId"], dados[i]["menuColuna"],dados[i]["menuText"], dados[i]["menuFuncaoJs"], dados[i]["menuChecked"], dados[i]["menuAtivado"], dados[i]["menuMostraIphone"]);
		oRow.className = "linha-tabela-alteracao-menu";
		var coluna = oRow.insertCell(oRow.cells.length);
		var texto = oRow.insertCell(oRow.cells.length);
		var funcaoJS = oRow.insertCell(oRow.cells.length);
		
		coluna.innerHTML = traduzMenu(dados[i]["menuColuna"]);
		texto.innerHTML = dados[i]["menuText"];
		funcaoJS.innerHTML = dados[i]["menuFuncaoJs"];
		
	}
	alternaTabela(oTable);

}
function fnRetornoMenu(menuId, coluna, texto, funcao, checked, ativado, iphone){
	return function(){ mostraContentAlteraMenu(menuId, coluna, texto, funcao, checked, ativado, iphone); };
}
function mostraContentAlteraMenu(menuId, coluna, texto, funcao, checked, ativado, iphone){

	var oComboBox = $$("select-altera-menu");
	for (var i = 0; i < oComboBox.length ; i++){
		if(oComboBox.options[i].value == coluna){
				oComboBox.options[i].selected = true;
		}
	}
	$$("hidden-id-menu").value = menuId;
	$$("cmp-texto").value = texto;
	$$("cmp-funcao").value = funcao;
	if(checked){
		$$("cmp-checked").checked = true;
	}else{
		$$("cmp-checked").checked = false;
	}
	if(ativado == 1){
		$$("cmp-ativado").checked = true;
	}else{
		$$("cmp-ativado").checked = false;
	}
	if(iphone){
		$$("cmp-iphone").checked = true;
	}else{
		$$("cmp-iphone").checked = false;
	}
	$$("content-altera-menu").style.display = "block";
}

function alteraMenu(){
	var menuId = $$("hidden-id-menu").value;
	var comboBox = $$("select-altera-menu");
	var coluna = comboBox.options[comboBox.selectedIndex].value;
	var texto = $$("cmp-texto").value;
	var funcao = $$("cmp-funcao").value;
	var menuChecked = $$("cmp-checked");
	var menuIphone = $$("cmp-iphone");
	var menuAtivado = $$("cmp-ativado");
	$$("content-altera-menu").style.display = "none";
	var oMenu = {
			menuId:menuId,
			menuText:texto,
			menuFuncaoJs:funcao,
			menuColuna:coluna,
			menuChecked:menuChecked.checked ? true : false,
			menuMostraIphone:menuIphone.checked ? true : false,
			menuAtivado:menuAtivado.checked ? 1 : 0
	};
	
	AdministracaoJS.alteraMenu(oMenu, alteraMenuCallBack);
}
function alteraMenuCallBack(){
	alert("menu alterado com sucesso!");
	buscaDadosMenus($$("hidden-coluna-escolhida-para-modificacao").value);
}

function traduzMenu(idColuna){
	var coluna = "";
	
	switch(idColuna){
		case 0:
			coluna = "A Meganet";
			break;
		case 1:
			coluna = "Pagamentos";
			break;
		case 2:
			coluna = "Meu Cadastro";
			break;
		case 3:
			coluna = "Dúvidas e Sugestões";
			break;
		case 4:
			coluna = "Controle de Clientes";
			break;
		case 5:
			coluna = "Controle de equipamentos";
			break;
		case 6:
			coluna = "Administrativo";
			break;
		case 7:
			coluna = "Relatórios";
			break;
		case 8:
			coluna = "Demandas";
			break;
		case 9:
			coluna = "Painel de controle";
			break;
		default:
			coluna = "";
			break;
	}
	return coluna;
}