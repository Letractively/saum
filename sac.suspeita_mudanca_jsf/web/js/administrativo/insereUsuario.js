//alterado
function excluirUsuario(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var usuarioId = $$("id").value == "" ? null : $$("id").value;
	
	if(usuarioId == null){
		alert("Selecione um MAC para a exclusão");
		modalMensagem.escondeModal();
	}else{
		if(confirm("Tem certeza que deseja excluir esse cidadão...?")){
			AdministracaoJS.excluirUsuario(usuarioId, fnInsereUsuarioCallBack);
		}else{
			modalMensagem.escondeModal();
		}
	}
}
function insereUsuario(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var envia = true;
	
	var usuario = $$("usuario").value;
	var cpf = $$("cpf").value;
	var telefone = $$("telefone").value;
	var telefone2 = $$("telefone2").value;
	var endereco = $$("endereco").value;
	var bairro = $$("bairro").value;
	var cidade = $$("cidade").value;
	var cep = $$("cep").value;
	var email = $$("email").value;
	var dtAtivacao = $$('dt-ativacao').value;
	var complemento = $$('complemento').value;
	var lat = $$("latitude").value;
	var lng = $$("longitude").value;

	var comboMAC = "";
	var oComboMAC = $$("combo-mac");
	if(oComboMAC.options.length > 0){
		comboMAC = oComboMAC.options[oComboMAC.selectedIndex].value;
	}
	var oComboestado = $$("select-estado");
	var estado = oComboestado.options[oComboestado.selectedIndex].value;
	
	var oCombopg = $$("combo-data-pagamento");
	var combopg = oCombopg.options[oCombopg.selectedIndex].value;
	
	var oComboBloq = $$("combo-cliente-bloqueado");
	var comboBloq = oComboBloq.options[oComboBloq.selectedIndex].value;

	var oComboContrato = $$("combo-contrato");
	var comboContrato = oComboContrato.options[oComboContrato.selectedIndex].value;
	
	var oComboPlano = $$("combo-alt-plano");
	var comboPlano = oComboPlano.options[oComboPlano.selectedIndex].value;
	
	var oComboEndIP = $$("combo-alt-endereco-ip");
	var comboEndIP = oComboEndIP.options[oComboEndIP.selectedIndex].value;
	
	var oComboServ = $$("combo-alt-servidor");
	var comboServ = oComboServ.options[oComboServ.selectedIndex].value;
	
	var oMenu = document.getElementsByName("cb-menu");
	var listaMenu = "";
	var primeiroMenu = true;
	for(var i = 0; i < oMenu.length; i++){
			if(oMenu[i].checked){
				if(primeiroMenu){
					listaMenu = oMenu[i].value;
					primeiroMenu = false;
				}else{
					listaMenu = listaMenu + "," + oMenu[i].value;
				}
			}
	}
	
	var oEqp = document.getElementsByName("cb-eqp");
	var listaEqp = "";
	var primeiroEqp = true;
	for(var i = 0; i < oEqp.length; i++){
		if(oEqp[i].checked){
			if(primeiroEqp){
				listaEqp = oEqp[i].value;
				primeiroEqp = false;
			}else{
				listaEqp = listaEqp + "," + oEqp[i].value;
			}
		}
	}
	
	if($$("usuario-administrativo").checked == false){
		if(comboPlano == ""){
			envia = false;
			$$("combo-alt-plano").style.background = "#FF0000";
		}else{
			$$("combo-alt-plano").style.background = "#CCE3FD";
		}
		if(comboEndIP == ""){
			envia = false;
			$$("combo-alt-endereco-ip").style.background = "#FF0000";
		}else{
			$$("combo-alt-endereco-ip").style.background = "#CCE3FD";
		}
		if(comboServ == ""){
			envia = false;
			$$("combo-alt-servidor").style.background = "#FF0000";
		}else{
			$$("combo-alt-servidor").style.background = "#CCE3FD";
		}
		if(dtAtivacao == ""){
			envia = false;
			$$("dt-ativacao").style.background = "#FF0000";
		}else{
			$$("dt-ativacao").style.background = "#CCE3FD";
		}
	}
	if(lat == "" || lng == ""){
		envia = false;
		alert("Selecione o local do cliente no mapa.");
	}
	
	if(usuario == ""){
		envia = false;
		$$("usuario").style.background = "#FF0000";
	}else{
		$$("usuario").style.background = "#CCE3FD";
	}
	
	if(endereco == ""){
		envia = false;
		$$("endereco").style.background = "#FF0000";
	}else{
		$$("endereco").style.background = "#CCE3FD";
	}
	
	if(complemento == ""){
		envia = false;
		$$("complemento").style.background = "#FF0000";
	}else{
		$$("complemento").style.background = "#CCE3FD";
	}
	
	if(bairro == ""){
		envia = false;
		$$("bairro").style.background = "#FF0000";
	}else{
		$$("bairro").style.background = "#CCE3FD";
	}
	
	if(cidade == ""){
		envia = false;
		$$("cidade").style.background = "#FF0000";
	}else{
		$$("cidade").style.background = "#CCE3FD";
	}
	
	if(cpf == ""){
		envia = false;
		$$("cpf").style.background = "#FF0000";
	}else{
		$$("cpf").style.background = "#CCE3FD";
	}
	
	if(telefone == "" && telefone2 == ""){
		envia = false;
		alert("Preencha no mínimo um telefone");
	}
	
	if(cep == ""){
		envia = false;
		$$("cep").style.background = "#FF0000";
	}else{
		$$("cep").style.background = "#CCE3FD";
	}
	
	if(email == ""){
		envia = false;
		$$("email").style.background = "#FF0000";
	}else{
		$$("email").style.background = "#CCE3FD";
	}
	
	if(envia){
		var oPlano = {planospacotesId:comboPlano};
		var oServ = {servidorId:comboServ};
		var oEnderecoIp = {enderecoipId:comboEndIP};
		var oContrato = {contratoId:comboContrato};
		
		var oUsuario = {
			usuarioId: $$("id").value == "" ? null : $$("id").value,
			planosPacotes: oPlano,
			servidor: oServ,
			contrato: oContrato,
			enderecoIp: oEnderecoIp,
			contrato: oContrato,
			usuarioNome: usuario,
			usuarioTelefone1: telefone,
			usuarioTelefone2: telefone2,
			usuarioEmail: email,
			usuarioCpf: cpf,
			usuarioCep: cep,
			usuarioMac: comboMAC.toUpperCase(),
			usuarioMenu: listaMenu,
			usuarioEqpComodato: listaEqp,
			dataTemp: dtAtivacao,
			lat:lat,
			lng:lng,
			usuarioBloqueado: comboBloq,
			usuarioComplementoEndereco: complemento,
			usuarioDtPagamento:combopg,
			usuarioEndereco:endereco,
			usuarioBairro:bairro,
			usuarioCidade:cidade,
			usuarioEstado:estado,
			usuarioAlteraProprioPerfil: $$("altera_proprio_perfil").checked == true ? true : false,
			usuarioAdministrativo: $$("usuario-administrativo").checked == true ? true : false};
 		
		AdministracaoJS.insereDadosUsuario(oUsuario, fnInsereUsuarioCallBack);
	}else{
		modalMensagem.escondeModal();
	}
}

function fnInsereUsuarioCallBack(retorno){
	if(retorno.resposta == null || retorno.resposta == ""){
		$$("form-cliente").reset();
		fnInsersaoUsuario();
	}else{
		alert("Ação: " + retorno.acao + "\nRetornou o erro: " + retorno.resposta);
		modalMensagem.escondeModal();
	}
}
function iniciaCalendario() {
	Calendar.setup({
		inputField : "dt-ativacao",
		button     : "bt-data",
		ifFormat   : "%d/%m/%Y"
	});
}

function fncarregaComboMACCallback(macs){
	if(macs.length > 0){
		var oComboMac = $$("combo-mac");
		oComboMac.options[0] = new Option("", "");
		DWRUtil.addOptions("combo-mac", macs,"usuarioMac", "usuarioMac");
		oComboMac.options[0].selected = true;
		UtilsJS.carregaComboPlano(fnCarregaComboPlanoAlteraUsrCallback);
		UtilsJS.carregaComboServidoresAtivos(fnCarregaComboServidorAlteraUsrCallback);
		ContratoJS.buscaContratos(fnCarregaComboContratosCallBack);
		iniciaCalendario();
		$$("combo-data-pagamento").disabled = "disabled";
		$$("combo-contrato").disabled = "disabled";
		$$("combo-alt-plano").disabled = "disabled";
		$$("combo-alt-endereco-ip").disabled = "disabled";
		$$("combo-alt-servidor").disabled = "disabled";
		$$("combo-cliente-bloqueado").disabled = "disabled";
		$$("bt-data").style.visibility = "hidden";
		$$("dt-ativacao").value = "";
		$$("usuario-administrativo").checked = true;
		$$("usuario-administrativo").disabled = "disabled";
	}else{
		$$("combo-mac").disabled = "disabled";
		$$("combo-data-pagamento").disabled = "disabled";
		$$("combo-contrato").disabled = "disabled";
		$$("combo-alt-plano").disabled = "disabled";
		$$("combo-alt-endereco-ip").disabled = "disabled";
		$$("combo-alt-servidor").disabled = "disabled";
		$$("combo-cliente-bloqueado").disabled = "disabled";
		$$("bt-data").style.visibility = "hidden";
		$$("dt-ativacao").value = "";
		$$("usuario-administrativo").checked = true;
		$$("usuario-administrativo").disabled = "disabled";
	}
}

function selecionaMac(){
	var oComboBox = $$("combo-mac");
	var valorCombo = oComboBox.options[oComboBox.selectedIndex].value;
	if(valorCombo != ""){
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
		AdministracaoJS.buscaUsuarioMAC(valorCombo, carregaUsuariosParaAlteracaoCallBack);
		$$("usuario-administrativo").checked = false;

		var comboContrato = $$("combo-contrato");
		comboContrato.disabled = false;
		
		var comboClienteBloqueado = $$("combo-cliente-bloqueado");
		comboClienteBloqueado.disabled = false;
		
		var comboPlano = $$("combo-alt-plano");
		comboPlano.disabled = false;
		
		var oComboServ = $$("combo-alt-servidor");
		oComboServ.disabled = true;
		
		var oComboDataPg = $$("combo-data-pagamento");
		oComboDataPg.disabled = false;
		
		$$("bt-data").style.visibility = "visible";

	}else{
		
		$$("usuario").value = "";
		$$("usuario-administrativo").checked = true;
		$$("usuario-administrativo").disabled = true;

		var comboContrato = $$("combo-contrato");
		comboContrato.options[0].selected = true;
		comboContrato.disabled = true;
		
		var comboClienteBloqueado = $$("combo-cliente-bloqueado");
		comboClienteBloqueado.options[0].selected = true;
		comboClienteBloqueado.disabled = true;
		
		var comboPlano = $$("combo-alt-plano");
		comboPlano.options[0].selected = true;
		comboPlano.disabled = true;
		
		var oComboServ = $$("combo-alt-servidor");
		oComboServ.options[0].selected = true;
		oComboServ.disabled = true;
		
		var oComboDataPg = $$("combo-data-pagamento");
		oComboDataPg.options[0].selected = true;
		oComboDataPg.disabled = true;
		
		$$("bt-data").style.visibility = "hidden";
	}
}

function carregaUsuariosParaAlteracaoCallBack(usuario){
	$$("id").value = usuario.usuarioId;
	$$("usuario").value = usuario.usuarioNome;
	$$("usuario-administrativo").checked = false;
	$$("usuario-administrativo").disabled = true;
	
	var oComboContrato = $$("combo-contrato");
	for(var i = 0; i < oComboContrato.options.length; i++){
		if(oComboContrato.options[i].value == usuario.contrato.contratoId){
			oComboContrato.options[i].selected = true;
		}
	}
	var oComboBloq = $$("combo-cliente-bloqueado");
	for(var i = 0; i < oComboBloq.options.length; i++){
		if(oComboBloq.options[i].value == usuario.usuarioBloqueado){
			oComboBloq.options[i].selected = true;
		}
	}
	
	var oComboPlano = $$("combo-alt-plano");
	for(var i = 0; i < oComboPlano.options.length; i++){
		if(oComboPlano.options[i].value == usuario.planosPacotes.planospacotesId){
			oComboPlano.options[i].selected = true;
		}
	}
	
	var oComboEndIP = $$("combo-alt-endereco-ip");
	oComboEndIP.options[0] = new Option(usuario.enderecoIp.enderecoipEndereco, usuario.enderecoIp.enderecoipId);
	oComboEndIP.options[0].selected = true;

	
	var oComboServ = $$("combo-alt-servidor");
	for(var i = 0; i < oComboServ.options.length; i++){
		if(oComboServ.options[i].value == usuario.servidor.servidorId){
			oComboServ.options[i].selected = true;
		}
	}
	modalMensagem.escondeModal();
}

function selectTorreEnderecoIP(){
	var oComboServ = $$("combo-alt-servidor");
	var comboServ = oComboServ.options[oComboServ.selectedIndex].value;
	UtilsJS.carregaComboEnderecoIP(comboServ, fncarregaComboEnderecoIPAlteraUsrCallback);
}
function carregaComboDatasPagamento(){
	AdministracaoJS.carregaComboDatasPagamento(callBackCarregaComboDatasPagamento);
}
function callBackCarregaComboDatasPagamento(retorno){
	var oCombo = $$("combo-data-pagamento");
	DWRUtil.removeAllOptions("combo-data-pagamento");
	for (var i = 0; i < retorno.length ; i++){
		var opt = new Option(retorno[i].valor, retorno[i].id);
		oCombo.options[i] = opt;
	}
}
function expandeRecolhe(){
	var obj = $$("menus");
	var msg = $$("msg-menu-cont");
	if(obj.style.display == "none"){
		obj.style.display = "block";
		msg.innerHTML = "- Menus";
	}else{
		obj.style.display = "none";
		msg.innerHTML = "+ Menus";
	}
}
var latlng;
var map;
var clickedLocation;
var marker;

function initialize(latIni, latFim, msg, z) {
	latlng = new google.maps.LatLng(latIni, latFim);
	var myOptions = {
		zoom : z,
		navigationControl: true,
		mapTypeControl: false,
		scaleControl: true,
		center : latlng,
		mapTypeId : google.maps.MapTypeId.SATELLITE
	};
	map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	
	google.maps.event.addListener(map, 'click', function(event) {
		placeMarker(event.latLng);
	});
}
function placeMarker(location) {
	clickedLocation = location;
	if(marker != null){
		marker.setMap(null);
	}
	marker = new google.maps.Marker({
		position : clickedLocation,
		map : map
	});
	$$("latitude").value = location.lat();
	$$("longitude").value = location.lng();
}
function buscaCEP(){
	var cep = $$("cep").value;
	if(cep.length < 8){
		alert("CEP inválido.");
	}else{
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
		AdministracaoJS.buscaCEP(cep, buscaCEPCallBack);
	}
}
function buscaCEPCallBack(ret){
	$$("endereco").value = ret.usuarioEndereco;
	$$("bairro").value = ret.usuarioBairro;
	$$("cidade").value = ret.usuarioCidade;
	var oComboEstado = $$("select-estado");
	for(var i = 0; i < oComboEstado.options.length; i++){
		if(oComboEstado.options[i].value == ret.usuarioEstado){
			oComboEstado.options[i].selected = true;
		}
	}
	modalMensagem.escondeModal();
}
function verificaTamanho(obj){
	if(obj.value.length < 13){
		obj.value = "";
	}
}