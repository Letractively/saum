//alterado
function carregaUsuarios(){
	var cliente = $$("cliente").value;
	if(cliente != "" && cliente.length > 0){
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
		if($$("tp").checked){
			AdministracaoJS.buscaUsuarioPeloCPF(cliente, carregaUsuariosParaAlteraccaoCallBack);
		}else if($$("tp1").checked){
			AdministracaoJS.buscaUsuarioPeloNome(cliente, carregaUsuariosParaAlteraccaoCallBack);
		}else if($$("tp2").checked){
			AdministracaoJS.buscaUsuarioPeloId(cliente, carregaUsuariosParaAlteraccaoCallBack);
		}
	}
	return false;
}
function carregaUsuariosParaAlteraccaoCallBack(usuario){
	if(usuario != null){
		if(!usuario.usuarioAdministrativo){
			carregaclientes(usuario);
		}else{
			alert("Atendimento para usuário administrativo\nnão permitido.");
		}
	}else{
		alert("Não encontrado!");
	}
	modalMensagem.escondeModal();
}
function carregaclientes(usuario){
	$$("fieldset-perfomance-ap-media").style.display = "none";
	$$("fieldset-desbloqueio").style.display = "none";
	$$("fieldset-perfomance-ap").style.display = "none";
	$$("fieldset-trafego-img-ap").style.display = "none";
	$$("fieldset-torre").style.display = "none";
	$$("fieldset-plano").style.display = "none";
	$$("fieldset-solicita-demanda").style.display = "none";
	$$("fieldset-eqp").style.display = "none";
	$$("fieldset-boletos").style.display = "none";
	$$("fieldset-demandas").style.display = "none";
	
	if(usuario != null){
		$$("id").value = usuario.usuarioId;
		$$("hEqp").value = usuario.usuarioEqpComodato;
		if(usuario.enderecoIp != null){
			$$("end-ip").innerHTML = "&nbsp;" + getValorSemNull(usuario.enderecoIp.enderecoipEndereco);
		}
		$$("nome").innerHTML = "&nbsp;" + getValorSemNull(usuario.usuarioNome);
		$$("mac").innerHTML = "&nbsp;" + getValorSemNull(usuario.usuarioMac);
		$$("cpf").innerHTML = "&nbsp;" + getValorSemNull(usuario.usuarioCpf);
		$$("torre").innerHTML = "&nbsp;" + getValorSemNull(usuario.servidor.servidorNome);
		$$("telefone").innerHTML = "&nbsp;" + getValorSemNull(usuario.usuarioTelefone1);
		$$("celular").innerHTML = "&nbsp;" + getValorSemNull(usuario.usuarioTelefone2);
		$$("endereco").innerHTML = "&nbsp;" + getValorSemNull(usuario.usuarioEndereco);
		$$("complemento").innerHTML = "&nbsp;" + getValorSemNull(usuario.usuarioComplementoEndereco);
		$$("bairro").innerHTML = "&nbsp;" + getValorSemNull(usuario.usuarioBairro);
		$$("id-cliente-html").innerHTML = "&nbsp;" + getValorSemNull(usuario.usuarioId);
		$$("cidade").innerHTML = "&nbsp;" + getValorSemNull(usuario.usuarioCidade);
		$$("estado").innerHTML = "&nbsp;" + getValorSemNull(usuario.usuarioEstado);
		$$("email").innerHTML = "<a href=\"mailto:"+ usuario.usuarioEmail +"\" style=\"float:left; margim-left:2px\">" + getValorSemNull(usuario.usuarioEmail) + "</a>";
		$$("dt-ativacao").innerHTML = "&nbsp;" + getValorSemNull(usuario.dataTemp);
		$$("dt-pagamento").innerHTML = "&nbsp;" + getValorSemNull(usuario.usuarioDtPagamento) + " de cada mês";
		$$("pg-atrasado").innerHTML = (usuario.pagamentoAtrasado == true ? "<div style=\"color:#FF0000\">&nbsp;Sim</div>" : "&nbsp;Não");
		placeMarker(new google.maps.LatLng(usuario.lat, usuario.lng));
		
		var ativo = usuario.usuarioBloqueado;
		if(ativo == 0){
			$$("ativo-bloqueado").innerHTML = "Sem restrição";
			$$("bt-demanda").disabled = false;
			$$("bt-perf-a").disabled = false;
			$$("bt-desbloqueio").disabled = true;
		}else if(ativo == 1){
			$$("ativo-bloqueado").innerHTML = "Mensagem de advertência";
			$$("bt-demanda").disabled = false;
			$$("bt-perf-a").disabled = false;
			$$("bt-desbloqueio").disabled = false;
		}else if(ativo == 2){
			$$("ativo-bloqueado").innerHTML = "Bloqueado";
			$$("bt-demanda").disabled = false;
			$$("bt-desbloqueio").disabled = false;
			$$("bt-perf-a").disabled = false;
		}else if(ativo == 3){
			$$("ativo-bloqueado").innerHTML = "Desativado - Não gera débito";
			alert("Não pode gerar demanda.");
			$$("bt-desbloqueio").disabled = false;
			$$("bt-demanda").disabled = true;
			$$("bt-perf-a").disabled = true;
		}
		$$("bt-perf").disabled = false;
		$$("bt-perf-a").disabled = false;
		if($$("bt-torre") != null){
			$$("bt-torre").disabled = false;
		}
		$$("bt-plano").disabled = false;
		$$("bt-eqp").disabled = false;
		$$("bt-contrato").disabled = false;
		$$("bt-demanda").disabled = false;
		if($$("bt-img") != null){
			$$("bt-img").disabled = false;
		}
		$$("bt-bol").disabled = false;
		$$("bt-dem").disabled = false;
	}else{
		$$("bt-perf").disabled = true;
		$$("bt-eqp").disabled = true;
		$$("bt-perf-a").disabled = true;
		$$("bt-desbloqueio").disabled = true;
		if($$("bt-torre") != null){
			$$("bt-torre").disabled = true;
		}
		$$("bt-plano").disabled = true;
		$$("bt-contrato").disabled = true;
		$$("bt-demanda").disabled = true;
		if($$("bt-img") != null){
			$$("bt-img").disabled = true;
		}
		$$("bt-bol").disabled = true;
		$$("bt-dem").disabled = true;
	}
}
function verificarComodato(){
	var oEqp = document.getElementsByName("cb-eqp");
	for(var i = 0; i < oEqp.length; i++){
		oEqp[i].checked = false;
	}
	for(var i = 0; i < oEqp.length; i++){
		var cbEqp = oEqp[i].value;
		oEqp[i].disabled = true;
		if(scaneiaItensMenu($$("hEqp").value, cbEqp)){
			oEqp[i].checked = true;
		}
	}

	$$("fieldset-eqp").style.display = "block";
	$$("titulo-eqp").style.display = "none";
}
function verificaTorre(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var idCliente = $$("id").value;
	AdministracaoJS.verificaTorre(idCliente, verificaTorreCallBAck);
}
function verificaTorreCallBAck(status){
	if(status != null){
		$$("vel-med-up-torre").innerHTML = "&nbsp;&nbsp;" + getValorSemNull(status.trfficRXAtual);
		$$("vel-med-down-torre").innerHTML = "&nbsp;&nbsp;" + getValorSemNull(status.trfficTXAtual);
		$$("torre").innerHTML = "&nbsp;&nbsp;" + getValorSemNull(status.nomeTorre);
		$$("qtd").innerHTML = "&nbsp;&nbsp;" + getValorSemNull(status.qtdClienteRegistrado);
		$$("torre-atual").innerHTML = "&nbsp;&nbsp;" + getValorSemNull(status.nomeTorre);
		var ccq = status.txCCQ;
		$$("ccq-torre").innerHTML = "<div style=\"font-size:10px; width: "+ ccq +"%; background: "+ printCor2(ccq) +"; height: 12px\">&nbsp;&nbsp;"+ ccq + "%</div>";
		
		var sinal = status.noiseFloor;
		var resultSinal = (sinal * -1);
		if(resultSinal > 100){
			resultSinal = 100;
		}
		
		$$("noise-torre").innerHTML = "<div style=\"font-size:10px; width: "+ (resultSinal) +"%; background: "+ printCor2(resultSinal) +"; height: 12px\">&nbsp;&nbsp;"+ sinal + "</div>";
	}
	$$("fieldset-torre").style.display = "block";
	modalMensagem.escondeModal();
}
function verificaMediaPerfomanceAP(){
	var idCliente = $$("id").value;
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	AdministracaoJS.verificaMediaPerfomanceAP(idCliente, verificaMediaPerfomanceAPCallBack);
}
function verificaMediaPerfomanceAPCallBack(status){
	if(status != null){
		$$("vel-med-up").innerHTML = "&nbsp;&nbsp;" + getValorSemNull(status.statusclienteTxrate);
		$$("vel-med-down").innerHTML = "&nbsp;&nbsp;" + getValorSemNull(status.statusclienteRxrate);
		$$("data").innerHTML = "&nbsp;&nbsp;" + getValorSemNull(status.data);
		$$("throug").innerHTML = "&nbsp;&nbsp;" + getValorSemNull(status.statusclienteThroughput);
		var sinal = getValorSemNull(status.statusclienteSignalstrength);
		var resultSinal = (sinal * -1);
		$$("sinal").innerHTML = "<div style=\"font-size:10px; width: "+ (100 - resultSinal) +"%; background: "+ printCor(resultSinal) +"; height: 12px\">&nbsp;&nbsp;"+ sinal + "</div>";
		var ccq = status.statusclienteTxccq;
		$$("ccq").innerHTML = "<div style=\"font-size:10px; width: "+ ccq +"%; background: "+ printCor2(ccq) +"; height: 12px\">&nbsp;&nbsp;"+ ccq + "%</div>";
	}
	$$("fieldset-perfomance-ap-media").style.display = "block";
	modalMensagem.escondeModal();
}
function verificarImagemCallBack(url){
	iframe = document.createElement("iframe");
	iframe.id = "iframe-img";
	iframe.style.height = "150px";
	iframe.style.width = "100%";
	iframe.src = url;
	iframe.frameBorder = 0;
	$$("div-trafego-img").innerHTML = "";
	$$("div-trafego-img").appendChild(iframe);
	$$("fieldset-trafego-img-ap").style.display = "block";
	modalMensagem.escondeModal();
}
function verificaAtualPerfomanceAP(){
	var idCliente = $$("id").value;
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	AdministracaoJS.verificaAtualPerfomanceAP(idCliente, verificaAtualPerfomanceAPCallBack);
}
function verificaAtualPerfomanceAPCallBack(status){
	if(status != null){
		$$("vel-med-up-at").innerHTML = "&nbsp;&nbsp;" + getValorSemNull(status.statusclienteTxrate);
		$$("vel-med-down-at").innerHTML = "&nbsp;&nbsp;" + getValorSemNull(status.statusclienteRxrate);
		$$("data-at").innerHTML = "&nbsp;&nbsp;" + getValorSemNull(status.data);
		$$("throug-at").innerHTML = "&nbsp;&nbsp;" + getValorSemNull(status.statusclienteThroughput);
		
		var sinal = status.statusclienteSignalstrength;
		var resultSinal = (sinal * -1);
		$$("sinal-at").innerHTML = "<div style=\"font-size:10px; width: "+ (100 - resultSinal) +"%; background: "+ printCor(resultSinal) +"; height: 12px\">&nbsp;&nbsp;"+ sinal + "</div>";
		
		var ccq = status.statusclienteTxccq;
		$$("ccq-at").innerHTML = "<div style=\"font-size:10px; width: "+ ccq +"%; background: "+ printCor2(ccq) +"; height: 12px\">&nbsp;&nbsp;"+ ccq + "%</div>";
	}
	$$("fieldset-perfomance-ap").style.display = "block";
	modalMensagem.escondeModal();
}
function verificarPlanoCliente(){
	var idCliente = $$("id").value;
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	AdministracaoJS.verificarPlanoCliente(idCliente, verificarPlanoClienteCallBack);
}
function verificarPlanoClienteCallBack(status){
	if(status != null){
		$$("nome-plano").innerHTML = "&nbsp;&nbsp;" + getValorSemNull(status.planospacotesNome);
		$$("velocidade-plano").innerHTML = "&nbsp;&nbsp;" + getValorSemNull(status.planospacotesVelocidade);
		$$("pode-bloqueio").innerHTML = "&nbsp;&nbsp;" + (status.planospacotes4ueiaPgAtrasado ? "Sim" : "Não");
		$$("valor-mens").innerHTML = "&nbsp;&nbsp; R$$ " + getValorSemNull(status.valor);
	}
	$$("fieldset-plano").style.display = "block";
	modalMensagem.escondeModal();
}
function visualizarContrato(){
	var idCliente = $$("id").value;
	var popup = window.open('visualizaContrato.do?id=' + idCliente, 'contrato','width=700,height=660,top=10,left=150,toolbar=no,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes');
	
	if(popup == null){
		alert("Bloqueio de pop-up ativado.\nDesative para visualizar o boleto.");
	}
}
function solicitarDemanda(){
	AdministracaoJS.buscaUsuariosAdministrativos(buscaUsuariosAdministrativosCallBack);
}
function gravaDemanda(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var idCliente = $$("id").value;
	var descricao = $$("descricao-demanda").value;
	
	var oResponsavel = $$("responsavel");
	var responsavel = oResponsavel.options[oResponsavel.selectedIndex].value;
	
	AdministracaoJS.gravaDemandaPadrao(idCliente, descricao, responsavel, gravaDemandaCallBack);
}
function gravaDemandaCallBack(ret){
	if(ret == 0){
		alert("Não gravado.");
	}else{
		fnAtendimentoCliente();
	}
	modalMensagem.escondeModal();
}
function localizarBoletos(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var idCliente = $$("id").value;
	AdministracaoJS.localizarBoletos(idCliente, localizarBoletosCallBack);
}
function reenviarBoleto(id, valorReen){
	if(confirm("O cliente deve ser avisado que haverá um custo de reenvio de R$ " + valorReen + "\n Reenviar o boleto mesmo assim?")){
		AdministracaoJS.reenviarBoleto(id, reenviarBoletoCallBack);
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
	}
}

function reenviarBoletoCallBack(ret){
	if(ret){
		localizarBoletos();
		alert("Boleto reenviado com sucesso!!!");
	}else{
		alert("Aconteceu algum problema ao reenviar o boleto.\nTente novamente mais tarde.");
	}
	modalMensagem.escondeModal();
}

function cancelarBoleto(id){
	if(confirm("Cancelar o boleto?")){
		AdministracaoJS.cancelarBoleto(id, cancelarBoletoCallBack);
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
	}
}
function cancelarBoletoCallBack(ret){
	if(ret){
		localizarBoletos();
		alert("Boleto cancelado com sucesso!!!");
	}else{
		alert("Aconteceu algum problema ao cancelar o boleto.\nTente novamente mais tarde.");
	}
	modalMensagem.escondeModal();
}


function localizarDemandas(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var idCliente = $$("id").value;
	AdministracaoJS.localizarDemandas(idCliente, localizarDemandasCallBack);
}
function localizarDemandasCallBack(ret){
	if(ret != null){
		var classeTR = false;
		var html = "<table class=\"report\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><thead><tr>";
		html += "<th width=\"25%\">Dt. abt</th><th width=\"25%\">Dt. enc.</th><th width=\"25%\">Dt. prev.</th><th width=\"25%\">&nbsp;</th></tr></thead><tbody>";
		for(var i = 0; i < ret.length; i++){
			if(classeTR){
				html += "<tr style=\"border:0px\" bgcolor=\"#DFE8F6\">";
			}else{
				html += "<tr style=\"border:0px\" bgcolor=\"#FFFFFF\">";
			}
			html += "<td style=\"border:0px\">"+ ret[i].dataAbertura +"</td>";
			html += "<td style=\"border:0px\">"+ ret[i].dataEncerramento +"</td>";
			html += "<td style=\"border:0px\" colspan=\"2\">"+ ret[i].dataPrevistaAtendimento +"</td>";
			html += "</tr>";
			
			if(classeTR){
				html += "<tr style=\"border:0px\" bgcolor=\"#DFE8F6\">";
			}else{
				html += "<tr style=\"border:0px\" bgcolor=\"#FFFFFF\">";
			}
			html += "<td style=\"border:0px\" colspan=\"2\">Descrição abertura</td>";
			html += "<td style=\"border:0px\" colspan=\"2\">Descrição encerramento</td>";
			html += "</tr>";
			
			if(classeTR){
				html += "<tr style=\"border:0px\" bgcolor=\"#DFE8F6\">";
			}else{
				html += "<tr style=\"border:0px\" bgcolor=\"#FFFFFF\">";
			}
			html += "<td style=\"border:0px\" colspan=\"2\">"+ ret[i].demandasDescricao +"</td>";
			if(ret[i].demandasDescricaoEncerramento == null || ret[i].demandasDescricaoEncerramento == "null"){
				html += "<td style=\"border:0px\" colspan=\"2\">&nbsp;</td>";
			}else{
				html += "<td style=\"border:0px\" colspan=\"2\">"+  ret[i].demandasDescricaoEncerramento +"</td>";
			}
			html += "</tr>";
			if(classeTR){
				html += "<tr style=\"border:0px\"><td colspan=\"4\" height=\"6\"/></tr>";
				classeTR = false;
			}else{
				html += "<tr style=\"border:0px\"><td colspan=\"4\" height=\"6\"/></tr>";
				classeTR = true;
			}
		}
		html += "</tbody></table>";
		
		$$("div-demandas").innerHTML = html;
		$$("fieldset-demandas").style.display = "block";
	}else{
		alert("Nenhuma demanda encontrada.");
	}
	modalMensagem.escondeModal();
}
function buscaUsuariosAdministrativosCallBack(lista){
	var oComboBox = $$('responsavel');
	for (var i = 0; i < lista.length ; i++){
		oComboBox.options[i] = new Option(lista[i].usuarioNome, lista[i].usuarioId);
	}
	$$("fieldset-solicita-demanda").style.display = "block";
}
function verificaDesbloqueio(){
	$$("fieldset-desbloqueio").style.display = "block";
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
	carregaUsuarios();
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
}
function desbloqueiaUsr(){
	var id = $$("id").value;
	var usrDataLimiteDesbloqueio = $$("dt-limite-bloqueio").value;
	var usrMotivoDesbloqueio = $$("motivo").value;
	var envia = true;
	
	if(usrDataLimiteDesbloqueio == ""){
		envia = false;
		$$("dt-limite-bloqueio").style.background = "#FF0000";
	}else{
		$$("dt-limite-bloqueio").style.background = "#CCE3FD";
	}
	if(usrMotivoDesbloqueio == ""){
		envia = false;
		$$("motivo").style.background = "#FF0000";
	}else{
		$$("motivo").style.background = "#CCE3FD";
	}
	if(envia == true){
		AdministracaoJS.desbloqueiaUsr(id, usrDataLimiteDesbloqueio, usrMotivoDesbloqueio, desbloqueiaUsrCallBAck);
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
	}
}
function desbloqueiaUsrCallBAck(ret){
	if(ret.resposta == null || ret.resposta == ""){
		$$("bt-desbloqueio").disabled = true;
		$$("fieldset-desbloqueio").style.display = "none";
		$$("ativo-bloqueado").innerHTML = "Sem restrição";
	}else{
		alert("Ação: " + ret.acao + "\nRetornou o erro: " + ret.resposta);
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
