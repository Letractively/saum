//alterado
function carregaUsuariosParaAlteraccao(){
	var cliente = $$("cliente").value;
	if(cliente != "" && cliente.length > 0){
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
		$$("bt-enviar").disabled = false;
		$$("bt-excluir").disabled = false;
		if($$("tp").checked){
			AdministracaoJS.buscaUsuarioPeloCPF(cliente, carregaUsuariosParaAlteraccaoCallBack);
		}else{
			AdministracaoJS.buscaUsuarioPeloNome(cliente, carregaUsuariosParaAlteraccaoCallBack);
		}
	}
}

function carregaUsuariosParaAlteraccaoCallBack(usuario){
	if(usuario != null){
		if(!usuario.usuarioAdministrativo){
			UtilsJS.carregaComboEnderecoIP(usuario.servidor.servidorId, fncarregaComboEnderecoIPAlteraCallback);
			carregaclientes(usuario);
		}else{
			carregaUsuarioAdministrativo(usuario);
		}
	}else{
		alert("Não encontrado!");
	}
	modalMensagem.escondeModal();
}

function fncarregaComboEnderecoIPAlteraCallback(lista){
	if(lista != null){
		var oComboBox = $$('combo-alt-endereco-ip');
		for (var i = 0; i < lista.length ; i++){
			oComboBox.options[i + 1] = new Option(lista[i].enderecoipEndereco, lista[i].enderecoipId);
		}
	}
}
	
function carregaclientes(usuario){
	$$("usuario-alt").value = usuario.usuarioNome;
	$$("id").value = usuario.usuarioId;
	$$("cpf-alt").value = usuario.usuarioCpf;
	$$("telefone-alt").value = usuario.usuarioTelefone1;
	$$("telefone2-alt").value = usuario.usuarioTelefone2;
	$$("endereco").value = usuario.usuarioEndereco;
	$$("bairro").value = usuario.usuarioBairro;
	$$("cidade").value = usuario.usuarioCidade;
	$$("cep-alt").value = usuario.usuarioCep;
	$$("email-alt").value = usuario.usuarioEmail;
	$$("mac-alt").value = usuario.usuarioMac;
	$$("dt-ativacao").value = usuario.dataTemp;
	$$("complemento").value = usuario.usuarioComplementoEndereco;
	
	$$("usuario-administrativo").checked = usuario.usuarioAdministrativo;
	$$("usuario-administrativo").disabled = true;
	
	$$("usuario-paga-mao").checked = usuario.usuarioPodePagarMao;
	$$("usuario-email-monetario").checked = usuario.usuarioEnviaEmailMonetario;
	$$("altera_proprio_perfil").checked = usuario.usuarioAlteraProprioPerfil;
	if(usuario.usuarioAdministrativo == true){
		$$("usuario-paga-mao").disabled = true;
		$$("usuario-email-monetario").disabled = true;
	}else{
		$$("usuario-paga-mao").disabled = false;
	}
	
	$$("mac-alt").disabled = false;
	$$("dt-ativacao").disabled = false;
	
	var comboContrato = $$("combo-contrato");
	comboContrato.disabled = false;
	
	var comboClienteBloqueado = $$("combo-cliente-bloqueado");
	comboClienteBloqueado.disabled = false;
	
	$$("combo-alt-data-pagamento").disabled = false;
	
	var comboPlano = $$("combo-alt-plano");
	comboPlano.disabled = false;
	
	var oComboServ = $$("combo-alt-servidor");
	oComboServ.disabled = false;
	
	var oComboContrato = $$("combo-contrato");
	for(var i = 0; i < oComboContrato.options.length; i++){
		if(oComboContrato.options[i].value == usuario.contrato.contratoId){
			oComboContrato.options[i].selected = true;
		}
	}
	var oComboEstado = $$("select-estado");
	for(var i = 0; i < oComboEstado.options.length; i++){
		if(oComboEstado.options[i].value == usuario.usuarioEstado){
			oComboEstado.options[i].selected = true;
		}
	}
	var oComboBloq = $$("combo-cliente-bloqueado");
	for(var i = 0; i < oComboBloq.options.length; i++){
		if(oComboBloq.options[i].value == usuario.usuarioBloqueado){
			oComboBloq.options[i].selected = true;
		}
	}
	var oComboPg = $$("combo-alt-data-pagamento");
	for(var i = 0; i < oComboPg.options.length; i++){
		if(oComboPg.options[i].value == usuario.usuarioDtPagamento){
			oComboPg.options[i].selected = true;
		}
	}
	
	var oComboPlano = $$("combo-alt-plano");
	for(var i = 0; i < oComboPlano.options.length; i++){
		if(oComboPlano.options[i].value == usuario.planosPacotes.planospacotesId){
			oComboPlano.options[i].selected = true;
		}
	}
	if(usuario.enderecoIp != null){
		var oComboEndIP = $$("combo-alt-endereco-ip");
		oComboEndIP.options[0] = new Option(usuario.enderecoIp.enderecoipEndereco, usuario.enderecoIp.enderecoipId);
		oComboEndIP.options[0].selected = true;
		oComboEndIP.disabled = false;
	}else{
		var oComboEndIP = $$("combo-alt-endereco-ip");
		oComboEndIP.disabled = false;
	}
	
	var oComboServ = $$("combo-alt-servidor");
	for(var i = 0; i < oComboServ.options.length; i++){
		if(oComboServ.options[i].value == usuario.servidor.servidorId){
			oComboServ.options[i].selected = true;
		}
	}

	var oMenu = document.getElementsByName("cb-menu");
	for(var i = 0; i < oMenu.length; i++){
		oMenu[i].checked = false;
	}
	if(usuario.usuarioMenu != null){
		for(var i = 0; i < oMenu.length; i++){
			var cbMenu = oMenu[i].value;
			if(scaneiaItensMenu(usuario.usuarioMenu, cbMenu)){
				oMenu[i].checked = true;
			}
		}
	}
	
	var oEqp = document.getElementsByName("cb-eqp");
	for(var i = 0; i < oEqp.length; i++){
		oEqp[i].checked = false;
	}
	if(usuario.usuarioEqpComodato != null){
		for(var i = 0; i < oEqp.length; i++){
			var cbEqp = oEqp[i].value;
			if(scaneiaItensMenu(usuario.usuarioEqpComodato, cbEqp)){
				oEqp[i].checked = true;
			}
		}
	}
	placeMarker(new google.maps.LatLng(usuario.lat, usuario.lng));
}

function carregaUsuarioAdministrativo(usuario){
	$$("id").value = usuario.usuarioId;
	$$("usuario-alt").value = usuario.usuarioNome;
	$$("cpf-alt").value = usuario.usuarioCpf;
	$$("telefone-alt").value = usuario.usuarioTelefone1;
	$$("telefone2-alt").value = usuario.usuarioTelefone2;
	$$("endereco").value = usuario.usuarioEndereco;
	$$("complemento").value = usuario.usuarioComplementoEndereco;
	$$("bairro").value = usuario.usuarioBairro;
	$$("cidade").value = usuario.usuarioCidade;
	$$("cep-alt").value = usuario.usuarioCep;
	$$("email-alt").value = usuario.usuarioEmail;
	
	$$("usuario-administrativo").checked = usuario.usuarioAdministrativo;
	$$("usuario-administrativo").disabled = true;
	
	$$("usuario-paga-mao").checked = usuario.usuarioPodePagarMao;
	$$("usuario-email-monetario").checked = usuario.usuarioEnviaEmailMonetario;
	$$("altera_proprio_perfil").checked = usuario.usuarioAlteraProprioPerfil;
	if(usuario.usuarioAdministrativo == true){
		$$("usuario-paga-mao").disabled = true;
	}else{
		$$("usuario-paga-mao").disabled = false;
	}
	var oComboEstado = $$("select-estado");
	for(var i = 0; i < oComboEstado.options.length; i++){
		if(oComboEstado.options[i].value == usuario.usuarioEstado){
			oComboEstado.options[i].selected = true;
		}
	}
	$$("mac-alt").value = "";
	$$("mac-alt").disabled = true;

	$$("dt-ativacao").value = "";
	$$("dt-ativacao").disabled = true;
	
	var comboContrato = $$("combo-contrato");
	comboContrato.disabled = true;
	
	var comboClienteBloqueado = $$("combo-cliente-bloqueado");
	comboClienteBloqueado.disabled = true;
	
	$$("combo-alt-data-pagamento").disabled = true;
	
	var comboPlano = $$("combo-alt-plano");
	comboPlano.disabled = true;
	comboPlano.options[0].selected = true;
	
	var comboContrato = $$("combo-contrato");
	comboContrato.disabled = true;
	comboContrato.options[0].selected = true;
	
	var oComboEndIP = $$("combo-alt-endereco-ip");
	oComboEndIP.disabled = true;
	oComboEndIP.options[0] = new Option("", "");
	oComboEndIP.options[0].selected = true;

	var oComboServ = $$("combo-alt-servidor");
	oComboServ.disabled = true;
	oComboServ.options[0].selected = true;

	var oMenu = document.getElementsByName("cb-menu");
	for(var i = 0; i < oMenu.length; i++){
		var cbMenu = oMenu[i].value;
		if(scaneiaItensMenu(usuario.usuarioMenu, cbMenu)){
			oMenu[i].checked = true;
		}else{
			oMenu[i].checked = false;
		}
	}
	placeMarker(new google.maps.LatLng(usuario.lat, usuario.lng));
}

function selectTorreEnderecoIP(){
	var oComboServ = $$("combo-alt-servidor");
	var comboServ = oComboServ.options[oComboServ.selectedIndex].value;
	UtilsJS.carregaComboEnderecoIP(comboServ, selectServEnderecoIPCallback);
}

function selectServEnderecoIPCallback(lista){
	var oComboBox = $$('combo-alt-endereco-ip');
	
	var oComboEndIP = $$("combo-alt-endereco-ip");
	var optionCLiente = oComboEndIP.options[0];
	DWRUtil.removeAllOptions('combo-alt-endereco-ip');
	oComboBox.options[0] = optionCLiente;
	for (var i = 0; i < lista.length ; i++){
		oComboBox.options[i + 1] = new Option(lista[i].enderecoipEndereco, lista[i].enderecoipId);
	}
}

function excluirUsuario(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var usuarioId = $$("id").value == "" ? null : $$("id").value;
	
	if(usuarioId == null){
		alert("Selecione um MAC para a exclusão");
		modalMensagem.escondeModal();
	}else{
		if(confirm("Tem certeza que deseja excluir esse cidadão...?")){
			AdministracaoJS.excluirUsuario(usuarioId, fnAlteracaoUsuarioCallBack);
		}else{
			modalMensagem.escondeModal();
		}
	}
}
function alteraUsuario(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var envia = true;
	var usuario = $$("usuario-alt").value;
	var id = $$("id").value;
	var cpf = $$("cpf-alt").value;
	var telefone = $$("telefone-alt").value;
	var telefone2 = $$("telefone2-alt").value;
	var endereco = $$("endereco").value;
	var complemento = $$("complemento").value;
	var bairro = $$("bairro").value;
	var cidade = $$("cidade").value;
	var cep = $$("cep-alt").value;
	var email = $$("email-alt").value;
	var mac = $$("mac-alt").value;
	var dtAtivacao = $$('dt-ativacao').value;
	var lat = $$("latitude").value;
	var lng = $$("longitude").value;

	
	var usrDataLimiteDesbloqueio = null;
	var usrMotivoDesbloqueio = null;
	
	if($$("div-data-bloqueio").style.display == "block" && $$("dt-limite-bloqueio").value != ""){
		usrDataLimiteDesbloqueio = $$("dt-limite-bloqueio").value;
		usrMotivoDesbloqueio = $$("motivo").value;
	}
	var oCombopg = $$("combo-alt-data-pagamento");
	var combopg = oCombopg.options[oCombopg.selectedIndex].value;
	
	var oComboBloq = $$("combo-cliente-bloqueado");
	var comboBloq = oComboBloq.options[oComboBloq.selectedIndex].value;

	var oComboEstado = $$("select-estado");
	var estado = oComboEstado.options[oComboEstado.selectedIndex].value;
	
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
	
	var oEqp = document.getElementsByName("cb-eqp");
	var listaEqp = "";
	var temEqp = 0;
	var primeiroEqp = true;
	for(var i = 0; i < oEqp.length; i++){
		if(oEqp[i].checked){
			if(primeiroEqp){
				listaEqp = oEqp[i].value;
				primeiroEqp = false;
			}else{
				listaEqp = listaEqp + "," + oEqp[i].value;
			}
			temEqp = 1;
		}
	}
	
	if($$("usuario-administrativo").checked == false){
		if(comboPlano == ""){
			envia = false;
			$$("combo-alt-plano").style.background = "#FF0000";
		}else{
			$$("combo-alt-plano").style.background = "#CCE3FD";
		}
		if($$("dt-limite-bloqueio").value == "" && $$("div-data-bloqueio").style.display == "block"){
			envia = false;
			$$("dt-limite-bloqueio").style.background = "#FF0000";
		}else{
			$$("dt-limite-bloqueio").style.background = "#CCE3FD";
		}
		if($$("motivo").value == "" && $$("div-data-bloqueio").style.display == "block"){
			envia = false;
			$$("motivo").style.background = "#FF0000";
		}else{
			$$("motivo").style.background = "#CCE3FD";
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
		if(lat == "" || lng == ""){
			envia = false;
			alert("Selecione o local do cliente no mapa.");
		}
		if(dtAtivacao == ""){
			envia = false;
			$$("dt-ativacao").style.background = "#FF0000";
		}else{
			$$("dt-ativacao").style.background = "#CCE3FD";
		}
	}
	
	if(usuario == ""){
		envia = false;
		$$("usuario-alt").style.background = "#FF0000";
	}else{
		$$("usuario-alt").style.background = "#CCE3FD";
	}
	
	if(cpf == ""){
		envia = false;
		$$("cpf-alt").style.background = "#FF0000";
	}else{
		$$("cpf-alt").style.background = "#CCE3FD";
	}
	
	if(mac == "" && $$("usuario-administrativo").checked == false){
		envia = false;
		$$("mac-alt").style.background = "#FF0000";
	}else{
		$$("mac-alt").style.background = "#CCE3FD";
	}
	
	if(telefone == "" && telefone2 == ""){
		envia = false;
		alert("Preencha no mínimo um telefone");
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
	
	if(cep == ""){
		envia = false;
		$$("cep-alt").style.background = "#FF0000";
	}else{
		$$("cep-alt").style.background = "#CCE3FD";
	}
	
	if(email == ""){
		envia = false;
		$$("email-alt").style.background = "#FF0000";
	}else{
		$$("email-alt").style.background = "#CCE3FD";
	}
	
	if(temMenu == 0){
		envia = false;
		alert("Selecione os menus para o usuário");
	}
	if(envia){
		var oPlano = {planospacotesId:comboPlano};
		var oServ = {servidorId:comboServ};
		var oEnderecoIp = {enderecoipId:comboEndIP};
		var oContrato = {contratoId:comboContrato};
		
		var oUsuario = {
			usuarioId: id,
			planosPacotes: oPlano,
			servidor: oServ,
			enderecoIp: oEnderecoIp,
			contrato: oContrato,
			usuarioNome: usuario,
			usuarioTelefone1: telefone,
			usuarioTelefone2: telefone2,
			usuarioEndereco:endereco,
			usuarioBairro:bairro,
			usuarioCidade:cidade,
			usuarioComplementoEndereco:complemento,
			usuarioEstado:estado,
			usuarioEmail: email,
			usuarioCpf: cpf,
			usuarioCep: cep,
			usuarioMac: mac.toUpperCase(),
			usuarioMenu: listaMenu,
			usuarioEqpComodato: listaEqp,
			dataTemp: dtAtivacao,
			usuarioBloqueado: comboBloq,
			usuarioDtPagamento:combopg,
			dataTemp2:usrDataLimiteDesbloqueio,
			motivoDesbloqueio:usrMotivoDesbloqueio,
			lat:lat,
			lng:lng,
			usuarioPodePagarMao: $$("usuario-paga-mao").checked == true ? true : false,
			usuarioEnviaEmailMonetario: $$("usuario-email-monetario").checked == true ? true : false,
			usuarioAlteraProprioPerfil: $$("altera_proprio_perfil").checked == true ? true : false,
			usuarioAdministrativo: $$("usuario-administrativo").checked == true ? true : false};

		AdministracaoJS.alteraUsuario(oUsuario, fnAlteracaoUsuarioCallBack);
			
	}else{
		modalMensagem.escondeModal();
	}
	
}

function fnAlteracaoUsuarioCallBack(retorno){
	if(retorno.resposta == null || retorno.resposta == ""){
		$$("form-altera-cliente").reset();
		fnAlteraUsuario();
	}else{
		alert("Ação: " + retorno.acao + "\nRetornou o erro: " + retorno.resposta);
	}
	modalMensagem.escondeModal();
}
function iniciaCalendario() {
	Calendar.setup({
		inputField : "dt-limite-bloqueio",
		button     : "bt-dt-limite-bloqueio",
		ifFormat   : "%d/%m/%Y"
	});
}

function verificaDataLimiteClientePagamentoAtrasado(){
	var id = $$("id").value;
	
	var oComboBloq = $$("combo-cliente-bloqueado");
	var comboBloq = oComboBloq.options[oComboBloq.selectedIndex].value;
	if(comboBloq == 0){
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
		AdministracaoJS.verificaDataLimiteClientePagamentoAtrasado(id, verificaDataLimiteClientePagamentoAtrasadoCallBack);
	}else{
		$$("div-data-bloqueio").style.display = "none";
		$$("dt-limite-bloqueio").value = "";
	}
}

function verificaDataLimiteClientePagamentoAtrasadoCallBack(ret){
	modalMensagem.escondeModal();
	if(ret){
		$$("div-data-bloqueio").style.display = "block";
	}
}
function carregaComboDatasPagamento(){
	AdministracaoJS.carregaComboDatasPagamento(callBackCarregaComboDatasPagamento);
}
function callBackCarregaComboDatasPagamento(retorno){
	var oCombo = $$("combo-alt-data-pagamento");
	DWRUtil.removeAllOptions("combo-alt-data-pagamento");
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
	if(google != null){
		marker = new google.maps.Marker({
			position : clickedLocation,
			map : map
		});
	}
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