//alterado
function buscaDadosServidores(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var oComboTorre = $$("combo-alt-serv");
	var comboTorre = oComboTorre.options[oComboTorre.selectedIndex].value;
	var oT = document.getElementsByName("cb-t");
	for(var i = 0; i < oT.length; i++){
		oT[i].checked = false;
	}
	UtilsJS.buscaDadosServidor(comboTorre, buscaDadosServidoresCallback);
}
function buscaDadosServidoresCallback(ret){
	if(ret != null){
		$$("id").value = ret.servidorId != null ? ret.servidorId : "";
		$$("nome").value = ret.servidorNome != null ? ret.servidorNome : "";
		$$("descricao").value = ret.servidorDescricao != null ? ret.servidorDescricao : "";
		$$("local").value = ret.servidorLocal != null ? ret.servidorLocal : "";
		$$("bt-alterar").disabled = false;
		$$("bt-cancelar").disabled = false;
		
		var situacao = $$("situacao");
		for(var i = 0; i < situacao.options.length; i++){
			if(situacao.options[i].value == ret.servidorDesativado + ""){
				situacao.options[i].selected = true;
			}
		}
		
		var oMenu = document.getElementsByName("cb-t");
		if(ret.servidorTorres != null){
			for(var i = 0; i < oMenu.length; i++){
				var cbMenu = oMenu[i].value;
				if(scaneiaItensMenu(ret.servidorTorres, cbMenu)){
					oMenu[i].checked = true;
				}
			}
		}
	}
	modalMensagem.escondeModal();
}
function novo(){
	$$("combo-alt-serv").options[0].selected = true;
	$$("combo-alt-serv").disabled = true;
	$$("bt-enviar").disabled = false;
	$$("bt-cancelar").disabled = false;
	$$("bt-alterar").disabled = true;
	$$("nome").disabled = false;
	limpaTodoscampos();
	alterar();
}
function limpaTodoscampos(){
	$$("id").value = "";
	$$("nome").value = "";
	$$("local").value = "";
	$$("descricao").value = "";
	$$("situacao").options[0].selected = true;
	$$("combo-alt-serv").options[0].selected = true;
}
function cancela(){
	$$("combo-alt-serv").options[0].selected = true;
	$$("combo-alt-serv").disabled = false;
	limpaTodoscampos();
	desabilitaCampos();
}

function desabilitaCampos(){
	$$("bt-cancelar").disabled = true;
	$$("bt-enviar").disabled = true;	
	$$("bt-alterar").disabled = true;	
	$$("nome").disabled = true;
	$$("local").disabled = true;
	$$("descricao").disabled = true;
	$$("situacao").disabled = true;
}
function alterar(){
	$$("nome").disabled = false;
	$$("local").disabled = false;
	$$("descricao").disabled = false;
	$$("situacao").disabled = false;
	$$("bt-enviar").disabled = false;
}
function gravaAlteracao(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var id = $$("id").value == "" ? null : $$("id").value;
	var nome = $$("nome").value;
	var local = $$("local").value;
	var descricao = $$("descricao").value;
	
	var envia = true;
	if(nome == ""){
		envia = false;
		$$("nome").style.background = "#FF0000";
	}else{
		$$("nome").style.background = "#CCE3FD";
	}
	if(local == ""){
		envia = false;
		$$("local").style.background = "#FF0000";
	}else{
		$$("local").style.background = "#CCE3FD";
	}
	if(descricao == ""){
		envia = false;
		$$("descricao").style.background = "#FF0000";
	}else{
		$$("descricao").style.background = "#CCE3FD";
	}
	
	var oMenu = document.getElementsByName("cb-t");
	var listaMenu = "";
	var temMenu = 0;
	var primeiroMenu = true;
	for(var i = 0; i < oMenu.length; i++){
			if(oMenu[i].checked){
				if(primeiroMenu){
					listaMenu = oMenu[i].value;
					primeiroMenu = false;
				}else{
					listaMenu = listaMenu + "," + oMenu[i].value;
				}
				temMenu = 1;
			}
	}
	if(temMenu == 0){
		envia = false;
		alert("Selecione no mínimo uma torre.");
	}
	
	var oSituacao = $$("situacao");
	var situacao = oSituacao.options[oSituacao.selectedIndex].value;
	
	if(envia){
		var t = {
			servidorId:id,
			servidorNome:nome,
			servidorDescricao:descricao,
			servidorLocal:local,
			servidorTorres:listaMenu,
			servidorDesativado:situacao
		};
		AdministracaoJS.salvaServidor(t, gravaAlteracaoCallBack);
	}else{
		modalMensagem.escondeModal();
	}
}

function gravaAlteracaoCallBack(ret){
	if(ret == 0){
		fnConfiguracaoGrupoTorre();
	}else{
		modalMensagem.escondeModal();
		alert("Erro...\nProblema ao manipular a base de dados");
	}
}
function fnCarregaComboServidorAlteraCallback(servs){
	var oComboBox = $$('combo-alt-serv');
	for (var i = 0; i < servs.length ; i++){
		oComboBox.options[i + 1] = new Option(servs[i].servidorNome, servs[i].servidorId);
	}
}
function fncarregaFormularioTorresCallback(lista){	
	var html = "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-size:12px;\">";
	var cont = 1;
	for(var i = 0; i < lista.length; i++){
		if(cont == 1){
			html += "<tr>";
		}
		html += "<td width=\"33%\">";
		html += "<label><input name=\"cb-t\" type=\"checkbox\" id=";
		html += "\"cb-t" + lista[i].torreId + "\" value=\"" + lista[i].torreId + "\"";
		html += "/>&nbsp;" + lista[i].torreNome + "</label>";
		html += "</td>";
		if(cont == 3){
			cont = 1;
			html += "</tr>";
		}else{
			cont++;
		}
		if(i == (lista.length -1)){
			switch (cont){
				case 1:
					html += "</table><br/>";
					break;
				case 2:
					html += "<td>&nbsp;</td><td>&nbsp;</td></table><br/>";
					break;
				case 3:
					html += "<td>&nbsp;</td></table><br/>";
					break;
			}
		}
	}
	$$("torres").innerHTML = html;
	$$("torres").style.display = "block";
}
