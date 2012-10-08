//alterado
function fnCarregaComboServidorAlteraCallback(servs){
	var oComboBox = $$('combo-alt-servidores');
	for (var i = 0; i < servs.length ; i++){
		oComboBox.options[i + 1] = new Option(servs[i].servidorNome, servs[i].servidorId);
	}
}
function buscaRelatorio(){
	var oComboServ = $$("combo-alt-servidores");
	var comboServ = oComboServ.options[oComboServ.selectedIndex].value;
	AdministracaoJS.buscaGrupos(comboServ, buscaRelatorioCallBack);
}
function buscaRelatorio2(){
	var oComboServ = $$("combo-alt-servidores");
	var comboServ = oComboServ.options[oComboServ.selectedIndex].value;
	AdministracaoJS.buscaGrupos(comboServ, buscaRelatorioCallBack2);
}

function buscaRelatorioCallBack2(ret){
	montaRelatorio(ret, true);
}
function buscaRelatorioCallBack(ret){
	montaRelatorio(ret, false);
}
function montaRelatorio(ret, iphone){
	var html = "<table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\" style=\"font-size:12px;\">";
	var cont = 1;
	for(var i = 0; i < ret.length; i++){
		if(cont == 1){
			html += "<tr>";
		}
		if(iphone == true){
			html += "<td width=\"50%\"  style=\"border:#DDDDDD 1px solid\">";
		}else{
			html += "<td width=\"33%\"  style=\"border:#DDDDDD 1px solid\">";
		}
		
		if(ret[i].temUsuario){
			html += "<div>";
			html += ret[i].enderecoipEndereco + " - " + ret[i].nomeUsr + "</div>";
		}else{
			html += "<div>";
			html += ret[i].enderecoipEndereco + "</div>";
		}
		
		html += "</td>";
		if(iphone == true){
			if(cont == 2){
				cont = 1;
				html += "</tr>";
			}else{
				cont++;
			}
		}else{
			if(cont == 3){
				cont = 1;
				html += "</tr>";
			}else{
				cont++;
			}
		}
		if(i == (ret.length -1)){
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
	$$("div-result").innerHTML = html;
}