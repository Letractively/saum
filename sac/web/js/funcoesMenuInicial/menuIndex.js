function fnIndex() {
	window.location = "index.do";
}
function fnAdiaBoleto() {
	window.location = "adiaBoleto.do";
}
function fnConfiguracaoAvancada() {
	window.location = "configuracaoAvancada.do";
}
function fnLocalizarDemanda() {
	fnLocalizarDemandaComId("");
}
function fnLocalizarDemandaComId(id) {
	window.location = "localizarDemanda.do?id=" + id;
}
function fnCusto() {
	window.location = "custo.do";
}
function fnTrafegoInterface() {
	window.location = "trefegoInterface.do";
}
function fnProperties() {
	window.location = "properties.do";
}
function fnResponderContato() {
	window.location = "responderContato.do";
}
function fnRelatorioContato() {
	window.location = "relatorioContato.do";
}
function fnComunicado() {
	window.location = "comunicado.do";
}
function fnEnderecoIP() {
	window.location = "enderecoIP.do";
}
function fnComandos() {
	window.location = "comando.do";
}
function fnComandoMonitoramento() {
	window.location = "comandoMonitoramento.do";
}
function fnUtilizacaoGrupos() {
	window.location = "utilizacaoGrupos.do";
}
function fnRelatorioBoletoPagoEmMao() {
	window.location = "relatorioBoletoPagoEmMao.do";
}
function fnRelacaoUsuarios() {
	window.location = "relacaoUsuarios.do";
}
function fnVisita() {
	window.location = "visita.do";
}
function fnConfiguraTorre() {
	window.location = "torre.do";
}
function fnConfiguracaoGrupoTorre() {
	window.location = "grupotorre.do";
}
function fnContato() {
	window.location = "contato.do";
}
function fnArquivoRetorno() {
	window.location = "arquivoRetorno.do";
}
function fnProdutosWireless() {
	window.location = "produtosWireless.do";
}
function fnInsersaoUsuario() {
	window.location = "insereUsuario.do";
}
function fnAlteraUsuario() {
	window.location = "alteraUsuario.do";
}
function fnAlteraInsereMenu() {
	window.location = "menu.do";
}
function fnBoleto() {
	window.location = "boleto.do";
}
function fnConfiguracaoBoleto() {
	window.location = "configuraBoleto.do";
}
function fnConfiguracaoPlano() {
	window.location = "configuraPlano.do";
}
function fnCadastroAP() {
	window.location = "cadastroAP.do";
}
function fnExtratoMensalidade() {
	window.location = "extrato.do";
}
function fnLocalizarBoleto() {
	window.location = "localizacarBoleto.do";
}
function fnContrato() {
	window.location = "manutencaoContrato.do";
}
function fnAtendimentoCliente() {
	window.location = "atendimentoUsuario.do";
}
function fnSolicitarDemanda() {
	window.location = "gerarDemanda.do";
}
function fnAtenderDemanda() {
	window.location = "finalizarDemanda.do";
}
function fnRelatorioRendimentos() {
	window.location = "rendimentos.do";
}
function fnrelatorioTrafegoUsuario() {
	window.location = "trafegoUsuario.do";
}
function fnRelatorioDemandas() {
	window.location = "relatorioDemandas.do";
}
function fnRelatorioLucro() {
	window.location = "relatorioLucro.do";
}
function fnRelatorioLog() {
	window.location = "relatorioLog.do";
}
function fnRelatorioSituacaoCliente() {
	window.location = "relatorioSituacao.do";
}
function fnLocalizacaoClienteGoogle() {
	window.location = "localizacaoClienteGoogle.do";
}
function fnLocalizacaoCliente() {
	var larguraTela = screen.width - 50;
	var alturaTela = screen.height - 80;
	var top = 10;
	var left = (screen.width / 2) - (1100 / 2);
	var popup = window
			.open(
					'localizacaoCliente.do',
					'Localiza',
					'width=1100,height='
							+ alturaTela
							+ ',top='
							+ top
							+ ',left='
							+ left
							+ ',toolbar=no,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes');
	if (popup == null) {
		alert("Bloqueador de pop ativado.\nDesative para visualizar a página.");
	}
}
function fnEsquemaMegnaet() {
	var larguraTela = screen.width - 50;
	var alturaTela = screen.height - 50;
	var top = (screen.width / 2) - (larguraTela / 2);
	var left = (screen.height / 2) - (alturaTela / 2);
	var popup = window
			.open(
					'esquemaMegnaet.htm',
					'Localiza',
					'width='
							+ larguraTela
							+ ',height='
							+ alturaTela
							+ ',top='
							+ top
							+ ',left='
							+ left
							+ ',toolbar=no,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes');
	if (popup == null) {
		alert("Bloqueador de pop ativado.\nDesative para visualizar o esquema.");
	}
}