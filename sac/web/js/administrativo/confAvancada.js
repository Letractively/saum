//alterado
function fnCarregaDominiosCallback(lsDom){
	var html = "<table width=\"100%\" border=\"0\">";
	var impar = true;
	for(var i = 0; i < lsDom.length; i++){
		if(lsDom[i].dominiosTratamentoEspecial == false){
			if(impar == true){
				html += "<tr valign=\"middle\" bgcolor=\"#DFE8F6\">";
			}else{
				html += "<tr valign=\"middle\">";
			}
			
			html += "<td width=\"21%\">"+ lsDom[i].dominiosChave +"</td>";
			html += "<td width=\"180\"><input value=\""+ getValorSemNull(lsDom[i].dominiosValor) +"\" id=\""+ lsDom[i].dominiosChave;
			html += "\" name=\"cmd\" type=\"text\" maxlength=\"100\" style=\"width:180px\"/></td>";
			html += "<td>"+ lsDom[i].dominiosDescricao +"</td>";
			html += "</tr>";
		}
	}
	html += "<tr valign=\"middle\" bgcolor=\"#DFE8F6\">";
	html += "<td>&nbsp;</td>";
	html += "<td colspan=\"2\"><button onClick=\"salvaConfAvancadas()\"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button></td>";
	html += "</tr>";

	html += "</table>";
	$$("formulario").innerHTML = html;
	
}

function salvaConfAvancadas(){
	
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	
	var oCmd = document.getElementsByName("cmd");
	var listaDom = new Array();
	for(var i = 0; i < oCmd.length; i++){
		dom = {
				dominiosChave:oCmd[i].id,
				dominiosValor:oCmd[i].value
		};
		listaDom.push(dom);
	}

	UtilsJS.salvaConfAvancadas(listaDom, salvaConfAvancadasCallBack);
}

function salvaConfAvancadasCallBack(ret){
	if(ret != null){
		alert(ret);
		fnConfiguracaoAvancada();
	}else{
		fnIndex();
	}
}