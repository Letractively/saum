//alterado
var campoDeBuscaCliente;
function $$(nomeObjeto){
	return document.getElementById(nomeObjeto);
}
function escondeFieldSet(tipo) {
	var oConteudo = $$("fieldset-" + tipo);
	oConteudo.style.display = "none";
}
function fnClearTable(oTable) {
	oTable.removeChild(oTable.tBodies[0]);
	oTable.appendChild(document.createElement('tbody'));
}
function alternaTabela(oTable) {
	var oTable = (typeof oTable == "string") ? ($$(oTable)) ? $$(oTable) : oTable : oTable;
	if(typeof oTable == "object" && oTable.nodeName.toLowerCase() == "table") {
		for(var i=0; i<oTable.tBodies.length; i++) {
			var oRows = oTable.tBodies[i].rows;
			
			for(var j=0; j<oRows.length; j++) {
				oRows[j].style.background = (j%2) ? "#EAEAEA" : "#ffffff";
			}
		}
	}
	else 
		alert("Table \"" + oTable + "\" not found!");
}
function removeAllChilds(obj){
	while(obj.hasChildNodes() == true){
	        obj.removeChild(obj.firstChild);
	}
}
function formataTelefone( campo, e ) {
	car = (navigator.appName == "Netscape" ) ? e.which : e.keyCode;
	var caractereEscape = (car != 37) && (car != 8) && (car != 46) && (car != 39);
	if(car == 8 || car == 46){
		return true;
	}
	if(campo.value.length > 13){
		return false;
	}
	var tecladoNumerico = car > 95 && car < 106;
	if ( (( car < 48 || car > 57 ) && ( car > 31 )) && (caractereEscape) && (!tecladoNumerico)) return false;
	if ( campo.value.length==1 && caractereEscape){
	  if (campo.value != '(')
	    campo.value='(' + campo.value;
	  }
	  if ( campo.value.length == 3 && caractereEscape){
	    campo.value+=')';
	  }
	  if ( campo.value.length==8 && caractereEscape){
	    campo.value+='-';
	  }
	  return true;
}
function limpaCampo(campo){
	$$("input-temp").value = campo.value; 
	campo.value = "";
}
function preencheCampo(campo, valor){
	campo.value = $$("input-temp").value;
}
function formataCEP( campo, e ) {
	car = (navigator.appName == "Netscape" ) ? e.which : e.keyCode;
	var caractereEscape = (car != 37) && (car != 8) && (car != 46) && (car != 39);
	if(car == 8 || car == 46){
		return true;
	}
	if(campo.value.length > 13){
		return false;
	}
	var tecladoNumerico = car > 95 && car < 106;
	if ( (( car < 48 || car > 57 ) && ( car > 31 )) && (caractereEscape) && (!tecladoNumerico)) return false;
		
	if ( campo.value.length==5 && caractereEscape){
	  if (campo.value != '-')
	    campo.value = campo.value + "-";
	  }
	  return true;
}
function formataMAC( campo, e ) {
	car = (navigator.appName == "Netscape" ) ? e.which : e.keyCode;
	var caractereEscape = (car != 37) && (car != 8) && (car != 46) && (car != 39);
	if(car == 8 || car == 46){
		return true;
	}
	if(campo.value.length > 17){
		return false;
	}
	var letras = (car >= 65) && (car <= 70);
	var tecladoNumerico = car > 95 && car < 106;
	if ( (( car < 48 || car > 57 ) && ( car > 31 )) && (caractereEscape) && (!tecladoNumerico) && (!letras)) return false;
	
	if ( campo.value.length ==2 || campo.value.length == 5 || campo.value.length == 8 || campo.value.length == 11 || campo.value.length == 14){
	  if (campo.value != ':')
	    campo.value = campo.value + ":";
	  }
	  return true;
}
function formatarCpfCnpj(s, e) {
	if(isShiftOrTab(e)) return;
	s = s.toString().replace(/[^\d]/g, '');
	if(s.length <= 11) {
		return formatarCPF(s);
	}
	else {
		return formatarCNPJ(s);
	}
}
function formatarNumero(s) {
	s.value = s.value.replace(/[^\d]/g, '');
}
function formatarCPF(s) {
	s = s.toString().replace(/[^\d]/g, '');
	if(s.length > 3) s = s.substring(0,3) + '.' + s.substring(3);
	if(s.length > 7) s = s.substring(0,7) + '.' + s.substring(7);
	if(s.length > 11) s = s.substring(0,11) + '-' + s.substring(11);
	return s;
}
function formatarCNPJ(s) {
	s = s.toString().replace(/[^\d]/g, '');
	if(s.length > 2) s = s.substring(0,2) + '.' + s.substring(2);
	if(s.length > 6) s = s.substring(0,6) + '.' + s.substring(6);
	if(s.length > 10) s = s.substring(0,10) + '/' + s.substring(10);
	if(s.length > 15) s = s.substring(0,15) + '-' + s.substring(15, 17);
	return s;
}
function isShiftOrTab(e) {
	if(!e) e = window.event;
	if(!e) return false;
	
	if(getKeyCode(e) == 16 || getKeyCode(e) == 9)
		return true;

	return false;
}
function getKeyCode(e) {
	if(!e) e = window.event;
	if(e.keyCode)
		return e.keyCode;
	else if(e.which)
		return e.which;
}
function formataCpfNome( campo, e ) {
	if($$("tp").checked){
		if(isShiftOrTab(e)) return;
		campo.value = formatarCpfCnpj(campo.value, e);
	}else if($$("tp1").checked){
		procuraCLiente("cliente");
		return true;
	}else if($$("tp2").checked){
		car = (navigator.appName == "Netscape" ) ? e.which : e.keyCode;
		var caractereEscape = (car != 37) && (car != 8) && (car != 46) && (car != 39);
		if(car == 8 || car == 46){
			return true;
		}
		if(campo.value.length > 5){
			return false;
		}
		var tecladoNumerico = car > 95 && car < 106;
		if ( (( car < 48 || car > 57 ) && ( car > 31 )) && (caractereEscape) && (!tecladoNumerico)) return false;
	}
}
function formataCPF( campo, e ) {
	if(isShiftOrTab(e)) return;
	campo.value = formatarCpfCnpj(campo.value, e);
}
function getAlturaPagina(){
	var browser = navigator.appName;
	var b_version = navigator.appVersion;
	var version = parseFloat(b_version);
	
	var alturaIE = document.body.offsetHeight;
	var alturaFX = document.body.scrollHeight;
	var alturaSemScroll = window.screen.height - 170;
	
	if(browser == "Netscape"){
		var alturaSemScroll = window.screen.height - 180;
	}else{
		var alturaSemScroll = window.screen.height - 150;
	}
	if(alturaIE < alturaFX){
		if (alturaFX < alturaSemScroll){
			return alturaSemScroll;
		}else{
			return alturaFX;
		}
	} else {
		if (alturaIE < alturaSemScroll){
			return alturaSemScroll;
		}else{
			return alturaIE;
		}
	}
}
function fnCarregaComboUsuarioCallBack(lista){
	$$("fieldset-cria-boleto").style.display = "block";
	var oComboBox = $$('select-cria-boleto');
	for (i = 0; i < lista.length ; i++){
		oComboBox.options[i + 1] = new Option(lista[i].nome, lista[i].usuarioId);
	}
}
function fnCarregaComboPlanoCallback(lista){
	var oComboBox = $$('combo-plano');
	for (var i = 0; i < lista.length ; i++){
		oComboBox.options[i + 1] = new Option(lista[i].planospacotesNome, lista[i].planospacotesId);
	}
}
function fnCarregaComboContratosCallBack(contratos){
	var oComboBox = $$('combo-contrato');
	for (var i = 0; i < contratos.length ; i++){
		oComboBox.options[i + 1] = new Option(contratos[i].contratoNome, contratos[i].contratoId);
	}
}
function fnCarregaComboTorreCallback(lista){
	var oComboBox = $$('combo-torre');
	for (var i = 0; i < lista.length ; i++){
		oComboBox.options[i + 1] = new Option(lista[i].torreNome, lista[i].torreId);
	}
}
function fnCarregaComboServidoresCallback(lista){
	var oComboBox = $$('combo-servidor');
	for (var i = 0; i < lista.length ; i++){
		oComboBox.options[i + 1] = new Option(lista[i].servidorNome, lista[i].servidorId);
	}
}
function fnCarregaComboTorreCartaoWirelessCallback(lista){
	var oComboBox = $$('combo-torre');
	for (var i = 0; i < lista.length ; i++){
		oComboBox.options[i + 1] = new Option(lista[i].nome, lista[i].id);
	}
	modalMensagem.escondeModal();
}
function fncarregaComboEnderecoIPCallback(lista){
	var oComboBox = $$('combo-endereco-ip');
	DWRUtil.removeAllOptions('combo-endereco-ip');
	for (var i = 0; i < lista.length ; i++){
		oComboBox.options[i + 1] = new Option(lista[i].enderecoipEndereco, lista[i].enderecoipId);
	}
}
function fnCarregaComboPlanoAlteraUsrCallback(lista){
	var oComboBox = $$('combo-alt-plano');
	for (var i = 0; i < lista.length ; i++){
		oComboBox.options[i + 1] = new Option(lista[i].planospacotesNome, lista[i].planospacotesId);
	}
}
function fnCarregaComboPlanoAlteraPlanoCallback(lista){
	var oComboBox = $$('select-plano');
	for (var i = 0; i < lista.length ; i++){
		oComboBox.options[i + 1] = new Option(lista[i].planospacotesNome, lista[i].planospacotesId);
	}
}
function fnCarregaComboTorreAlteraUsrCallback(torres){
	var oComboBox = $$('combo-alt-torre');
	for (var i = 0; i < torres.length ; i++){
		oComboBox.options[i + 1] = new Option(torres[i].torreNome, torres[i].torreId);
	}
}
function fnCarregaComboServidorAlteraUsrCallback(serv){
	var oComboBox = $$('combo-alt-servidor');
	for (var i = 0; i < serv.length ; i++){
		oComboBox.options[i + 1] = new Option(serv[i].servidorNome, serv[i].servidorId);
	}
}

function fncarregaComboEnderecoIPAlteraUsrCallback(lista){
	var oComboBox = $$('combo-alt-endereco-ip');
	DWRUtil.removeAllOptions('combo-alt-endereco-ip');

	for (var i = 0; i < lista.length ; i++){
		oComboBox.options[i + 1] = new Option(lista[i].enderecoipEndereco, lista[i].enderecoipId);
	}
}

function tbOver(obj){
	obj.style.background = "#B3ffff";
}

function tbOut(obj){
	obj.style.background = "#B3D9FF";
}

function scaneiaItensMenu(lista, valorCB){
	var arrLista = lista.split(",");
	for(var i = 0; i < arrLista.length; i++){
		if(arrLista[i] == valorCB){
			return true;
		}
	}
	return false;
}

function fncarregaFormularioMenuCallback(lista){
	
	var col1 = new Array();
	var col2 = new Array();
	var col3 = new Array();
	var col4 = new Array();
	var col5 = new Array();
	var col6 = new Array();
	var col7 = new Array();
	var col8 = new Array();
	var col9 = new Array();
	
	for(var i = 0; i < lista.length; i++){
		switch (lista[i].menuColuna){
		case 1:
			col1.push(lista[i]);
			break;
		case 2:
			col2.push(lista[i]);
			break;
		case 3:
			col3.push(lista[i]);
			break;
		case 4:
			col4.push(lista[i]);
			break;
		case 5:
			col5.push(lista[i]);
			break;
		case 6:
			col6.push(lista[i]);
			break;
		case 7:
			col7.push(lista[i]);
			break;
		case 8:
			col8.push(lista[i]);
			break;
		case 9:
			col9.push(lista[i]);
			break;
		}
	}
	
	var html = "";
	html += "<label><input name=\"total\" type=\"checkbox\" id=";
	html += "\"total" + i + "\" value=\"total\" onclick=\"selectionaTodosMenus(this);\"";
	html += "/><strong>&nbsp;Selecionar todos</strong></label>";
	
	if(col1.length > 0){
		html += montaTabelaMenu(col1);
	}
	if(col2.length > 0){
		html += montaTabelaMenu(col2);
	}
	if(col3.length > 0){
		html += montaTabelaMenu(col3);
	}
	if(col4.length > 0){
		html += montaTabelaMenu(col4);
	}
	if(col5.length > 0){
		html += montaTabelaMenu(col5);
	}
	if(col6.length > 0){
		html += montaTabelaMenu(col6);
	}
	if(col7.length > 0){
		html += montaTabelaMenu(col7);
	}
	if(col8.length > 0){
		html += montaTabelaMenu(col8);
	}
	if(col9.length > 0){
		html += montaTabelaMenu(col9);
	}
	
	$$("menus").innerHTML = html;
	modalMensagem.escondeModal();
	
	
}
function getNomeColuna(id){
	switch (id){
	case 1:
		return "Pagamentos";
		break;
	case 2:
		return "Cadastro";
		break;
	case 3:
		return "Dúvidas";
		break;
	case 4:
		return "Clientes";
		break;
	case 5:
		return "Boletos";
		break;
	case 6:
		return "Administrativo";
		break;
	case 7:
		return "Relatório";
		break;
	case 8:
		return "Demandas";
		break;
	case 9:
		return "Painel de controle";
		break;
	}
}
function selectionaTodosMenus(o){
	var oMenu = document.getElementsByName("cb-menu");
	for(var i = 0; i < oMenu.length; i++){
		if(o.checked == true){
			oMenu[i].checked = true;
		}else{
			oMenu[i].checked = false;
		}
	}
}
function montaTabelaMenu(lista){	
	var html = "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-size:12px;\">";
	var cont = 1;
	var cabecalho = true;
	for(var i = 0; i < lista.length; i++){
		if(cabecalho){
			html += "<tr bgcolor=\"#cccccc\"><td colspan=\"3\">"+ getNomeColuna(lista[i].menuColuna) +"</td></tr>";
			cabecalho = false;
		}
		if(cont == 1){
			html += "<tr>";
		}
		html += "<td width=\"33%\">";
		html += "<label><input name=\"cb-menu\" type=\"checkbox\" id=";
		html += "\"cb-menu" + i + "\" value=\"" + lista[i].menuId + "\"";
		(lista[i].menuChecked) ? html += "checked=\"checked\" " : html += " ";
		if(lista[i].menuChecked){
			html += "/><strong>&nbsp;" + lista[i].menuText + "</strong></label>";
		}else{
			html += "/>&nbsp;" + lista[i].menuText + "</label>";
		}			
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
	return html;
}
function fncarregaFormularioEqpCallback(lista){	
	var html = "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-size:12px;\">";
	var cont = 1;
	html += "<tr id=\"titulo-eqp\" bgcolor=\"#cccccc\"><td colspan=\"2\">&nbsp;Equipamentos para comodato</td></tr>";
	for(var i = 0; i < lista.length; i++){
		if(cont == 1){
			html += "<tr>";
		}
		html += "<td width=\"50%\">";
		html += "<label><input name=\"cb-eqp\" type=\"checkbox\" id=";
		html += "\"cb-eqp" + i + "\" value=\"" + lista[i].tipoEquipamentoId + "\"";
		html += "/>&nbsp;" + lista[i].tipoEquipamentoDescricao + "</label>";
		html += "</td>";
		if(cont == 2){
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
				html += "<td>&nbsp;</td></table><br/>";
				break;
			}
		}
	}
	$$("eqp").innerHTML = html;
}

function adiquirirBoletosEmAberto(){
	var cpf = $$("cpf-boleto").value;
	if(cpf.length > 9){
		BoletoJS.adiquirirBoletosEmAberto(cpf, adiquirirBoletosEmAbertoCallBack);
	}
}

function adiquirirBoletosEmAbertoCallBack(boletos){
	var divConteiner = $$("conteudoBoletos");
	if(boletos != null && boletos.length > 0){
		var conteudoHTML = "<table width=\"100%\">" +
				"<tr bgcolor=\"#DFE8F6\" align=\"center\">" +
				"<td width=\"33%\">Data Venc.</td><td width=\"33%\">Data pag.</td><td>Gerar</td></tr>";
		
		for ( var int = 0; int < boletos.length; int++) {
			if(!boletos[int].boletosgeradosPago){
				var link = " onClick=\"geraBoleto(\'"+ boletos[int].boletosgeradosId +"\');\" ";
				
				conteudoHTML += "<tr align=\"center\"><td>"+ boletos[int].dataTempVencimento +"</td>";
				if(boletos[int].vencido){
					conteudoHTML += "<td>Vencido</td>";
				}else{
					conteudoHTML += "<td>Aberto</td>";
				}
				conteudoHTML += "<td><button style=\"width: 30px; height=18px;font-size: 10px\""+ link +">OK</button></td>";
			conteudoHTML += "</tr>";
			}
		}
		conteudoHTML += "</table>";
		divConteiner.innerHTML = conteudoHTML;
	}else{
		var conteudoHTML = "<table width=\"100%\">" +
		"<tr bgcolor=\"#DFE8F6\" align=\"center\">" +
		"<td width=\"33%\">Não existe boleto a ser pago.</td></tr></table>";

		divConteiner.innerHTML = conteudoHTML;
	}
}

function geraBoleto(id){
	var popup = window.open("geraBoleto.do?id=" + id, 'boleto','width=830,height=660,top=10,left=150,toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no');
	if(popup == null){
		alert("Bloqueador de pop ativado.\nDesative para visualizar o boleto.");
	}

}
function limpaNome(){
	$$("cliente").value = "";
	$$("cliente").focus();
}
function procuraCLiente(campo){
	campoDeBuscaCliente = $$(campo);
	if(campoDeBuscaCliente.value.length > 4){
		AdministracaoJS.buscarUsuarios(campoDeBuscaCliente.value, procuraCLienteCallBack);
	}else{
		$$("nomes-clientes-resultado").innerHTML = "";
	}
}
function procuraCLienteCallBack(clientes){
	var conteudoResultado = $$("nomes-clientes-resultado");
	
	if(clientes.length > 0 && clientes != null){
		var conteudo = "<table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\">";
		for ( var int = 0; int < clientes.length; int++) {
	        conteudo = conteudo + "<tr style=\"cursor:pointer\" onClick=\"preencheCampoBusca('"+clientes[int].usuarioNome+"');\">";
	        conteudo = conteudo + "<td width=\"27%\">"+ clientes[int].usuarioCpf +"</td>";
	        conteudo = conteudo + "<td width=\"73%\">"+ clientes[int].usuarioNome +"</td>";
	        conteudo = conteudo + "</tr>";
		}
		conteudo = conteudo + "</table>";
	}else{
		conteudo = "<center>Cliente Não encontrado</center>";
	}
	$$("fieldset-resultado-busca").style.display = "block";
	conteudoResultado.innerHTML = conteudo;
}
function preencheCampoBusca(nome){
	campoDeBuscaCliente.value = nome;
}
function printCor(resultSinal){
	if(resultSinal <= 62){
		return "#00FF00";
	}else if(resultSinal >= 63 && resultSinal < 70){
		return "#FFFF00";
	}else if(resultSinal >= 71){
		return "#FF0000";
	}
}
function printCor2(resultSinal){
	if(resultSinal >= 90){
		return "#00FF00";
	}else if(resultSinal >= 60 && resultSinal < 90){
		return "#FFFF00";
	}else if(resultSinal <= 59){
		return "#FF0000";
	}
}

function carregaComboAno(idCombo){
	AdministracaoJS.carregaComboAno({
		callback:function(retorno) { 
			callBackCarregaComboAno(idCombo, retorno);
		; }
	});
}
function callBackCarregaComboAno(idCombo, retorno){
	var oCombo = $$(idCombo);
	DWRUtil.removeAllOptions(idCombo);
	for (var i = 0; i < retorno.length ; i++){
		var opt = new Option(retorno[i].valor, retorno[i].id);
		oCombo.options[i] = opt;
		if(i == 2){
			opt.selected = true;
		}
	}
}
function carregaComboMes(idCombo){
	AdministracaoJS.carregaComboMes({
		callback:function(retorno) { 
			callBackCarregaComboMes(idCombo, retorno);
		; }
	});
}
function callBackCarregaComboMes(idCombo, retorno){
	var oCombo = $$(idCombo);
	DWRUtil.removeAllOptions(idCombo);
	var selecionadoPadrao = 0;
	for (var i = 0; i < retorno.length ; i++){
		if(i == 0){
			selecionadoPadrao = retorno[i].id;
		}else{
			var opt = new Option(retorno[i].valor, retorno[i].id);
			oCombo.options[i - 1] = opt;
			if(i == selecionadoPadrao){
				opt.selected = true;
			}
		}
	}
}
function formataPercentual(campo, tammax) {
	var vr = campo.value;
	vr = vr.replace( /[^\d]/g, "" ); // retira não digitos
	vr = vr.replace( /^0*/, "" ); // retira zeros iniciais
	tam = vr.length;
	if (tam > tammax) {
		vr = vr.substr(0, tammax);
		retorno = vr;
	}
	if(vr != ""){
		if(tam > 2){
			retorno = vr.substr( 0, vr.length - 2 ) + '.' + vr.substr( vr.length - 2, vr.length );
		}else if(tam == 1){
			retorno = "0.0" + vr;
		}else if(tam == 2){
			retorno = vr.substr( 0, 1 ) + "." + vr.substr( 1, vr.length);
		}
	}else{
		retorno = "0";
	}
	campo.value = retorno;
}
function formataDecimal(campo, tammax) {
	var vr = campo.value;
	vr = vr.replace( /[^\d]/g, "" ); // retira não digitos
	vr = vr.replace( /^0*/, "" ); // retira zeros iniciais
	tam = vr.length;
	if (tam > tammax) {
		vr = vr.substr(0, tammax);
		retorno = vr;
	}
	if(vr != ""){
		if(tam > 2){
			retorno = vr.substr( 0, vr.length - 2 ) + '.' + vr.substr( vr.length - 2, vr.length );
		}else if(tam == 1){
			retorno = "0.0" + vr;
		}else if(tam == 2){
			retorno = "0." + vr;
		}
	}else{
		retorno = "0";
	}
	campo.value = retorno;
}
function carregaComboEstado(idCombo){
	AdministracaoJS.carregaComboEstado({
		callback:function(retorno) { 
		callBackcarregaComboEstado(idCombo, retorno);
		; }
	});
}
function callBackcarregaComboEstado(idCombo, retorno){
	var oCombo = $$(idCombo);
	DWRUtil.removeAllOptions(idCombo);
	var selecionadoPadrao = 0;
	for (var i = 0; i < retorno.length ; i++){
		var opt = new Option(retorno[i].valor, retorno[i].id);
		oCombo.options[i] = opt;
		if(i == selecionadoPadrao){
			opt.selected = true;
		}
	}
}
function getValorSemNull(obj){
	if(obj != null && obj != "null"){
		return obj;
	}else{
		return "";
	}
}
function localizarBoletosCallBack(ret){
	if(ret != null){
	var classeTR = false;
	var html = "<table class=\"report\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\"><thead><tr>";
	html += "<th width=\"14%\">Num.</th><th width=\"25%\">Dt venc.</th><th width=\"25%\">Dt pag.</th><th width=\"20%\">Valor</th><th>&nbsp;</th></tr></thead><tbody>";
	for(var i = 0; i < ret.length; i++){
		if(classeTR){
			html += "<tr class=\"odd\">";
			classeTR = false;
		}else{
			html += "<tr class=\"even\">";
			classeTR = true;
		}
		html += "<td><a href=\"localizacarBoleto.do?id="+ ret[i].boletosgeradosId +"\">"+ ret[i].boletosgeradosId +"</a></td>";
		html += "<td>"+ ret[i].dataTempVencimento +"</td>";
		html += "<td>"+ ret[i].dataTempPagamento +"</td>";
		html += "<td>"+ ret[i].boletosgeradosValor +"</td>";
		
		var imgPagasac = "img/indicadores/pagasac.gif";
		var imgPaga = "img/indicadores/paga.gif";
		var imgCompensar = "img/indicadores/acompensar.gif";
		
		var imgRemeter = "img/indicadores/remeter.gif";
		var imgRemetida = "img/indicadores/remetida.gif";
		var imgEmail = "img/indicadores/email.gif";
		var imgCorreio = "img/indicadores/correio.gif";
		var imgService = "img/indicadores/webservice.gif";
		var imgUrl = "img/indicadores/url.jpg";
		var imgReenviar = "img/indicadores/reenviar.gif";
		var imgCancelar = "img/indicadores/cancelada.gif";

		html += "<td align=\"center\">";
		
		if(ret[i].boletosgeradosPago){
			if(ret[i].boletosgeradosPagouEmMao){
				html += "<img src=\""+ imgPagasac +"\" border=\"0\" title=\"Pagamento manual\"/>&nbsp;&nbsp;";
			}else{
				html += "<img src=\""+ imgPaga +"\" border=\"0\" title=\"Pago\"/>&nbsp;&nbsp;";
			}
		}else{
			html += "<img src=\""+ imgCompensar +"\" border=\"0\" title=\"A pagar\"/>&nbsp;&nbsp;";
		}
		if(ret[i].boletosgeradosTipoEnvioF2B == "a"){
			if(ret[i].boletosgeradosPago){
				html += "<img src=\""+ imgRemeter +"\" style=\"visibility:hidden\">&nbsp;&nbsp;";
			}else{
				html += "<img src=\""+ imgRemeter +"\" border=\"0\" title=\"A enviar\"/>&nbsp;&nbsp;";
			}
		}else if(ret[i].boletosgeradosTipoEnvioF2B == "b"){
			html += "<img src=\""+ imgRemetida +"\" border=\"0\" title=\"Enviado e-mail e carta\"/>&nbsp;&nbsp;";
		}else if(ret[i].boletosgeradosTipoEnvioF2B == "e"){
			html += "<img src=\""+ imgEmail +"\" border=\"0\" title=\"Enviado e-mail\"/>&nbsp;&nbsp;";
		}else if(ret[i].boletosgeradosTipoEnvioF2B == "p"){
			html += "<img src=\""+ imgCorreio +"\" border=\"0\" title=\"Enviado correio\"/>&nbsp;&nbsp;";
		}else{
			html += "<img src=\""+ imgService +"\" border=\"0\" title=\"Registrado\"/>&nbsp;&nbsp;";
		}
		
		if(!ret[i].boletosgeradosPago){
			html += "<img src=\""+ imgCancelar +"\" border=\"0\" style=\"cursor: pointer;\" title=\"Cancelar boleto\" onclick=\"cancelarBoleto('"+ ret[i].boletosgeradosId +"');\"/>&nbsp;";
		}else{
			html += "<img src=\""+ imgCancelar +"\" style=\"visibility:hidden\"/>&nbsp;";
		}
		
		if(ret[i].boletosgeradosUrlBoletoF2b != null && !ret[i].boletosgeradosPago){
			html += "<a href=\""+ ret[i].boletosgeradosUrlBoletoF2b +"\" target=\"_blank\"><img src=\""+ imgUrl +"\" border=\"0\" title=\"Url\"/></a>&nbsp;&nbsp;";
		}else{
			html += "<a href=\""+ ret[i].boletosgeradosUrlBoletoF2b +"\" target=\"_blank\" style=\"visibility:hidden\"><img src=\""+ imgUrl +"\" border=\"0\" title=\"Url\"/></a>&nbsp;&nbsp;";
		}
		
		if(!ret[i].boletosgeradosPago && ret[i].boletosgeradosUrlBoletoF2b != null && ret[i].boletosgeradosIdF2B != null){
			html += "<img src=\""+ imgReenviar +"\" border=\"0\" style=\"cursor: pointer;\" title=\"Reenviar boleto\" onclick=\"reenviarBoleto('"+ ret[i].boletosgeradosId +"', '"+ ret[i].valorReenvio +"');\"/>&nbsp;&nbsp;";
		}else{
			html += "<img src=\""+ imgReenviar +"\" style=\"visibility:hidden\"/>&nbsp;&nbsp;";
		}

		html += "</td>";
		html += "</tr>";
	}
	html += "</tbody></table>";
	$$("div-boletos").innerHTML = html;
	$$("fieldset-boletos").style.display = "block";
	}else{
		alert("Nenhum boleto encontrado.");
	}
	modalMensagem.escondeModal();
}