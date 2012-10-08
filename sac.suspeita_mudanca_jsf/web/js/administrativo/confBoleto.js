//alterado
function buscaDadosInfBoletoCallBack(obj){
	$$("instrucao1").value = obj == null ? "" : obj.infboletoInstrucao1;
	$$("instrucao2").value = obj == null ? "" : obj.infboletoInstrucao2;
	$$("numeroConvenio").value = obj == null ? "" : obj.infboletoNumeroConvenio;
	$$("instrucao3").value = obj == null ? "" : obj.infboletoInstrucao3;
	$$("usr").value = obj.infboletoUsr;
	$$("sen").value = obj.infboletoSen;
	$$("localPagamento").value = obj == null ? "" : obj.infboletoLocalPagamentoLn1;
	$$("codigo_cliente").value = obj == null ? "" : obj.infboletoCodigoCliente;
	$$("agencia").value = obj == null ? "" : obj.infboletoAgencia;
	$$("conta").value = obj == null ? "" : obj.infboletoConta;
	$$("nome-titular").value = obj == null ? "" : obj.infboletoNome;
	$$("cnpj").value = obj == null ? "" : obj.infboletoCnpj;
	$$("carteira").value = obj == null ? "" : obj.infboletoCarteira;

	$$("codigo_fornecido_agencia_dv").value = obj == null ? "" : obj.infboletoCodigoFornecidoAgenciaDV;
	$$("codigo_fornecido_agencia").value = obj == null ? "" : obj.infboletoCodigoFornecidoAgencia;
	$$("codigo_0peracao").value = obj == null ? "" : obj.infboletoCodigoOperacao;

	$$("num_boleto_ini").value = obj == null ? "" : obj.infboletoNumeroboletoPosIni;
	$$("num_boleto_fim").value = obj == null ? "" : obj.infboletoNumeroboletoPosFim;
	$$("vlr_pago_banco_ini").value = obj == null ? "" : obj.infboletoValorpagoaobancoPosIni;
	$$("vlr_pago_banco_fim").value = obj == null ? "" : obj.infboletoValorpagoaobancoPosFim;
	$$("valor_cred_conta_ini").value = obj == null ? "" : obj.infboletoValorcreditadoPosIni;
	$$("valor_cred_conta_fim").value = obj == null ? "" : obj.infboletoValorcreditadoPosFim;
	$$("dt_pagamento_ini").value = obj == null ? "" : obj.infboletoDatapagamentoPosIni;
	$$("dt_pagamento_fim").value = obj == null ? "" : obj.infboletoDatapagamentoPosFim;
	$$("dt_pg_mascara").value = obj == null ? "" : obj.infboletoDatapagamentoMascara;
	
	var oComboContrato = $$("select-banco");
	for(var i = 0; i < oComboContrato.options.length; i++){
		if(oComboContrato.options[i].value == obj.infboletoBanco){
			oComboContrato.options[i].selected = true;
		}
	}
	
	var bc = oComboContrato.options[oComboContrato.selectedIndex].value;
	if(bc == "0"){
		$$("usr").disabled = false;
		$$("sen").disabled = false;
	}else{
		$$("usr").disabled = true;
		$$("sen").disabled = true;
	}

}

function gravaPlano(){
	var envia = true;
	
	if($$("instrucao1").value == ""){
		envia = false;
		$$("instrucao1").style.background = "#FF0000";
	}else{
		$$("instrucao1").style.background = "#CCE3FD";
	}
	if($$("instrucao2").value == ""){
		envia = false;
		$$("instrucao2").style.background = "#FF0000";
	}else{
		$$("instrucao2").style.background = "#CCE3FD";
	}
	if($$("localPagamento").value == ""){
		envia = false;
		$$("localPagamento").style.background = "#FF0000";
	}else{
		$$("localPagamento").style.background = "#CCE3FD";
	}
	if($$("agencia").value == ""){
		envia = false;
		$$("agencia").style.background = "#FF0000";
	}else{
		$$("agencia").style.background = "#CCE3FD";
	}
	if($$("conta").value == ""){
		envia = false;
		$$("conta").style.background = "#FF0000";
	}else{
		$$("conta").style.background = "#CCE3FD";
	}
	if($$("nome-titular").value == ""){
		envia = false;
		$$("nome-titular").style.background = "#FF0000";
	}else{
		$$("nome-titular").style.background = "#CCE3FD";
	}
	if($$("cnpj").value == ""){
		envia = false;
		$$("cnpj").style.background = "#FF0000";
	}else{
		$$("cnpj").style.background = "#CCE3FD";
	}
	
	if($$("carteira").value == ""){
		envia = false;
		$$("carteira").style.background = "#FF0000";
	}else{
		$$("carteira").style.background = "#CCE3FD";
	}
	
	var oComboBC = $$("select-banco");
	var comboBC = oComboBC.options[oComboBC.selectedIndex].value;
	
	if($$("usr").value == "" && comboBC == "0"){
		envia = false;
		$$("usr").style.background = "#FF0000";
	}else{
		$$("usr").style.background = "#CCE3FD";
	}
	if($$("sen").value == "" && comboBC == "0"){
		envia = false;
		$$("sen").style.background = "#FF0000";
	}else{
		$$("sen").style.background = "#CCE3FD";
	}
	
	if($$("num_boleto_ini").value == "" && comboBC != "0"){
		envia = false;
		$$("num_boleto_ini").style.background = "#FF0000";
	}else{
		$$("num_boleto_ini").style.background = "#CCE3FD";
	}
	if($$("num_boleto_fim").value == "" && comboBC != "0"){
		envia = false;
		$$("num_boleto_fim").style.background = "#FF0000";
	}else{
		$$("num_boleto_fim").style.background = "#CCE3FD";
	}
	if($$("vlr_pago_banco_ini").value == "" && comboBC != "0"){
		envia = false;
		$$("vlr_pago_banco_ini").style.background = "#FF0000";
	}else{
		$$("vlr_pago_banco_ini").style.background = "#CCE3FD";
	}
	if($$("vlr_pago_banco_fim").value == "" && comboBC != "0"){
		envia = false;
		$$("vlr_pago_banco_fim").style.background = "#FF0000";
	}else{
		$$("vlr_pago_banco_fim").style.background = "#CCE3FD";
	}
	if($$("valor_cred_conta_ini").value == "" && comboBC != "0"){
		envia = false;
		$$("valor_cred_conta_ini").style.background = "#FF0000";
	}else{
		$$("valor_cred_conta_ini").style.background = "#CCE3FD";
	}
	if($$("valor_cred_conta_fim").value == "" && comboBC != "0"){
		envia = false;
		$$("valor_cred_conta_fim").style.background = "#FF0000";
	}else{
		$$("valor_cred_conta_fim").style.background = "#CCE3FD";
	}
	if($$("dt_pagamento_ini").value == "" && comboBC != "0"){
		envia = false;
		$$("dt_pagamento_ini").style.background = "#FF0000";
	}else{
		$$("dt_pagamento_ini").style.background = "#CCE3FD";
	}
	if($$("dt_pagamento_fim").value == "" && comboBC != "0"){
		envia = false;
		$$("dt_pagamento_fim").style.background = "#FF0000";
	}else{
		$$("dt_pagamento_fim").style.background = "#CCE3FD";
	}
	if($$("dt_pg_mascara").value == "" && comboBC != "0"){
		envia = false;
		$$("dt_pg_mascara").style.background = "#FF0000";
	}else{
		$$("dt_pg_mascara").style.background = "#CCE3FD";
	}

	
	if(envia){
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
		var oInfBoleto = {
				infboletoInstrucao1: $$("instrucao1").value,
				infboletoNumeroConvenio: $$("numeroConvenio").value,
				infboletoInstrucao2: $$("instrucao2").value,
				infboletoInstrucao3: $$("instrucao3").value,
				infboletoLocalPagamentoLn1: $$("localPagamento").value,
				infboletoAgencia: $$("agencia").value,
				infboletoCodigoCliente: $$("codigo_cliente").value,
				infboletoConta: $$("conta").value,
				infboletoNome: $$("nome-titular").value,
				infboletoCnpj: $$("cnpj").value,
				infboletoUsr: $$("usr").value,
				infboletoSen: $$("sen").value,
				infboletoCarteira: $$("carteira").value,
				infboletoCodigoFornecidoAgenciaDV: $$("codigo_fornecido_agencia_dv").value,
				infboletoCodigoFornecidoAgencia: $$("codigo_fornecido_agencia").value,
				infboletoCodigoOperacao: $$("codigo_0peracao").value,
				infboletoBanco: comboBC,
				infboletoNumeroboletoPosIni:$$("num_boleto_ini").value,
				infboletoNumeroboletoPosFim:$$("num_boleto_fim").value,
				infboletoValorpagoaobancoPosIni:$$("vlr_pago_banco_ini").value,
				infboletoValorpagoaobancoPosFim:$$("vlr_pago_banco_fim").value,
				infboletoValorcreditadoPosIni:$$("valor_cred_conta_ini").value,
				infboletoValorcreditadoPosFim:$$("valor_cred_conta_fim").value,
				infboletoDatapagamentoPosIni:$$("dt_pagamento_ini").value,
				infboletoDatapagamentoPosFim:$$("dt_pagamento_fim").value,
				infboletoDatapagamentoMascara:$$("dt_pg_mascara").value
		};
		UtilsJS.gravaInfBoleto(oInfBoleto, alteraInfBoletoCallBack);
	}
}

function alteraInfBoletoCallBack(ret){
	if(ret){
		fnConfiguracaoBoleto();
	}else{
		alert("Erro na alteração");
	}
	modalMensagem.escondeModal();
}
function verificaBancoEscolhido(obj){
	var oBC = $$("select-banco");
	var bc = oBC.options[oBC.selectedIndex].value;
	if(bc == "0"){
		$$("usr").disabled = false;
		$$("sen").disabled = false;
	}else{
		$$("usr").disabled = true;
		$$("sen").disabled = true;
		$$("usr").value = "";
		$$("sen").value = "";
		
	}
}
function soNumero( campo, e ) {
	if(isShiftOrTab(e)) return;
	car = (navigator.appName == "Netscape" ) ? e.which : e.keyCode;
	var caractereEscape = (car != 37) && (car != 8) && (car != 46) && (car != 39);
	if(car == 8 || car == 46){
		return true;
	}
	var tecladoNumerico = car > 95 && car < 106;
	if ( (( car < 48 || car > 57 ) && ( car > 31 )) && (caractereEscape) && (!tecladoNumerico)) return false;
	return true;
}
