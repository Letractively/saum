//alterado
addEventListener("load", function(){setTimeout(updateLayout, 1500);}, false);
var currentWidth = 0;
function updateLayout(){
	if (window.innerWidth != currentWidth){
		currentWidth = window.innerWidth;
 
        var orient = currentWidth < 350 ? "profile" : "landscape";
        document.body.setAttribute("orient", orient);
        setTimeout(function(){
            window.scrollTo(0, 1);
        }, 100);
    }
}
setInterval(updateLayout, 1500);

var campoDeBuscaCliente;
function mostraEscondeFieldSet(tipo, titulo) {
	var oConteudo = $$("content-" + tipo);
	var oLegend = $$("legend-" + tipo);
	if(oConteudo.style.display == "none"){
		oConteudo.style.display = "block";
		oLegend.innerHTML = "<img id=\"img-login\" src=\"img/img_menos.gif\" alt=\"\" class=\"show-hide\" onclick=\"mostraEscondeFieldSet('"+ tipo +"', '"+ titulo +"')\" /> " + titulo;
	}else{
		oConteudo.style.display = "none";
		oLegend.innerHTML = "<img id=\"img-login\" src=\"img/img_mais.gif\" alt=\"\" class=\"show-hide\" onclick=\"mostraEscondeFieldSet('"+ tipo +"', '"+ titulo +"')\" /> " + titulo;
	}
}

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
		alert("Tabela: \"" + oTable + "\" not found!");
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
	  obj.setSelectionRange(campo.value.length, campo.value.length);
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

function vertical() {
   var navItems = document.getElementById("nav").getElementsByTagName("li");
   for (var i=0; i< navItems.length; i++) {
      if(navItems[i].className == "submenu") {
         navItems[i].onmouseover=function() {this.getElementsByTagName('ul')[0].style.display="block";this.style.backgroundColor = "#f9f9f9";};
         navItems[i].onmouseout=function() {this.getElementsByTagName('ul')[0].style.display="none";this.style.backgroundColor = "#FFFFFF";};
      }
   }

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
function fnCarregaComboServidorAlteraUsrCallback(serv){
	var oComboBox = $$('combo-alt-servidor');
	for (var i = 0; i < serv.length ; i++){
		oComboBox.options[i + 1] = new Option(serv[i].servidorNome, serv[i].servidorId);
	}
}
function fnCarregaComboServidoresCallback(lista){
	var oComboBox = $$('combo-servidor');
	for (var i = 0; i < lista.length ; i++){
		oComboBox.options[i + 1] = new Option(lista[i].servidorNome, lista[i].servidorId);
	}
}
function fnCarregaComboTorreCallback(lista){
	var oComboBox = $$('combo-torre');
   
	for (var i = 0; i < lista.length ; i++){
		oComboBox.options[i + 1] = new Option(lista[i].torreNome, lista[i].torreId);
	}
}
function fnCarregaComboTorreCartaoWirelessCallback(lista){
	var oComboBox = $$('combo-torre');
   
	for (var i = 0; i < lista.length ; i++){
		oComboBox.options[i + 1] = new Option(lista[i].nome, lista[i].id);
	}
}
function fncarregaComboEnderecoIPCallback(lista){
	var oComboBox = $$('combo-endereco-ip');
	DWRUtil.removeAllOptions('combo-endereco-ip');
	oComboBox.options[0] = new Option("Endereço IP", "");
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

function fncarregaFormularioMenuCallback(lista){
	var html= "";
	html += "<label><input name=\"total\" type=\"checkbox\" id=";
	html += "\"total" + i + "\" value=\"total\" onclick=\"selectionaTodosMenus(this);\"";
	html += "/><strong>&nbsp;Selecionar todos</strong></label>";

	html = "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-size:11px;\">";
	for(var i = 0; i < lista.length; i++){
		html += "<tr>";
		html += "<td  nowrap=\"nowrap\" style=\"padding-bottom:3px\"><div>";
		html += lista[i].menuText + "</div></td><td><span class=\"toggle\"><input name=\"cb-menu\" type=\"checkbox\" id=";
		html += "\"cb-menu" + i + "\" value=\"" + lista[i].menuId + "\"";
		(lista[i].menuChecked) ? html += "checked=\"checked\" " : html += " ";
		html += "/></span>";
		html += "</td>";
		html += "</tr>";
	}
	html += "</table>";
	$$("menus").innerHTML = html;
}
function fncarregaFormularioEqpCallback(lista){
	var html = "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-size:11px;\">";
	for(var i = 0; i < lista.length; i++){
		html += "<tr>";
		html += "<td  nowrap=\"nowrap\" style=\"padding-bottom:3px\"><div>";
		html += lista[i].tipoEquipamentoDescricao + "</div></td><td><span class=\"toggle\"><input name=\"cb-eqp\" type=\"checkbox\" id=";
		html += "\"cb-eqp" + i + "\" value=\"" + lista[i].tipoEquipamentoId + "\"";
		html += "/></span>";
		html += "</td>";
		html += "</tr>";
	}
	html += "</table>";
	$$("eqp").innerHTML = html;
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
	var trIni = "<tr><td colspan=\"2\" height=\"9\"><img src=\"dss\" height=\"1\" width=\"1\"><hr/></td></tr>";
	if(clientes.length > 0 && clientes != null){
		var conteudo = "<table width=\"100%\" border=\"0\" cellspacing=\"1\" style=\"font-size: 10px\" cellpadding=\"0\">" + trIni;
		for ( var int = 0; int < clientes.length; int++) {
	        conteudo = conteudo + "<tr style=\"cursor:pointer\" onClick=\"preencheCampoBusca('"+clientes[int].usuarioNome+"');\">";
	        conteudo = conteudo + "<td width=\"70%\">"+ clientes[int].usuarioNome +"</td>";
	        conteudo = conteudo + "<td width=\"30%\">"+ clientes[int].usuarioCpf +"</td>";
	        conteudo = conteudo + trIni;
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
	html += "<th width=\"14%\">Num.</th><th width=\"24%\">Dt venc.</th><th width=\"24%\">Dt pag.</th><th width=\"16%\">Valor</th><th>&nbsp;</th></tr></thead><tbody>";
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
		
		
		html += "<br><br>";
		if(!ret[i].boletosgeradosPago){
			html += "<img src=\""+ imgCancelar +"\" border=\"0\" style=\"cursor: pointer;\" title=\"Cancelar boleto\" onclick=\"cancelarBoleto('"+ ret[i].boletosgeradosId +"');\"/>&nbsp;";
		}else{
			html += "<img src=\""+ imgCancelar +"\" style=\"visibility:hidden\"/>&nbsp;&nbsp;";
		}
		
		if(!ret[i].boletosgeradosPago && ret[i].boletosgeradosUrlBoletoF2b != null && ret[i].boletosgeradosIdF2B != null){
			html += "<img src=\""+ imgReenviar +"\" border=\"0\" title=\"Reenviar boleto\" onclick=\"reenviarBoleto('"+ ret[i].boletosgeradosId +"', '"+ ret[i].valorReenvio +"');\"/>&nbsp;&nbsp;";
		}else{
			html += "<img src=\""+ imgReenviar +"\" style=\"visibility:hidden\"/>";
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
