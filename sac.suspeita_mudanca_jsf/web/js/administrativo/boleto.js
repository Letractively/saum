function iniciaCalendario() {
	Calendar.setup({
		inputField : "dt-inicial",
		button     : "bt-inicial",
		ifFormat   : "%d/%m/%Y"
	});
	Calendar.setup({
		inputField : "dt-final",
		button     : "bt-final",
		ifFormat   : "%d/%m/%Y"
	});
	Calendar.setup({
		inputField : "dt-cria-bol",
		button     : "bt-cria-bol",
		ifFormat   : "%d/%m/%Y"
	});
}


function fnBuscaUsuariosCallBack(usuarios){
	var oComboBox = $$("select-cria-boleto");
	for (var i = 0; i < usuarios.length ; i++){
		oComboBox.options[i] = new Option(usuarios[i].usuarioNome, usuarios[i].usuarioId);
	}
	modalMensagem.escondeModal();
}

function abreCriaBoletoUsuario(){
	var envia = true;
	var cliente = $$("cliente").value;
	var radioTp = document.getElementsByName("tp");
	var tpPesquisa = "";
	for(var i = 0; i < radioTp.length; i++){
		if(radioTp[i].checked == true){
			tpPesquisa = radioTp[i].value;
		}
	}
	
	if(cliente == ""){
		envia = false;
		$$("cliente").style.background = "#FF0000";
	}else{
		$$("cliente").style.background = "#CCE3FD";
	}
	
	if(envia){
		var oMes = $$("select-mes-boleto");
		var mes = oMes.options[oMes.selectedIndex].value;
		var oAno = $$("select-ano-boleto");
		var ano = oAno.options[oAno.selectedIndex].value;
		var popup = window.open("geraBoletoUsuarioMes.do?tp="+ tpPesquisa +"&cliente=" + cliente +"&mes=" + mes +"&ano=" + ano, "boleto","width=700,height=660,top=10,left=150,toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no");
		if(popup == null){
			alert("Bloqueador de pop ativado.\nDesative para visualizar o boleto.");
		}
	}
}

function abreCriaBoletoData(){
	var ini = $$("dt-inicial").value;
	var fim = $$("dt-final").value;
	var envia = true;
	if($$("dt-inicial").value == ""){
		envia = false;
		$$("dt-inicial").style.background = "#FF0000";
	}else{
		$$("dt-inicial").style.background = "#CCE3FD";
	}

	if($$("dt-final").value == ""){
		envia = false;
		$$("dt-final").style.background = "#FF0000";
	}else{
		$$("dt-final").style.background = "#CCE3FD";
	}
	
	
	
	if(envia){
		var popup = window.open('geraBoletoDataPagamento.do?ini=' + ini +'&fim=' + fim, 'boleto','width=700,height=660,top=10,left=150,toolbar=no,location=no,status=no,menubar=yes,scrollbars=yes,resizable=no');
		
		if(popup == null){
			alert("Bloqueador de pop ativado.\nDesative para visualizar o boleto.");
		}
	}
	
}

function fncarregaInformacoesBoletoCallBack(infBoleto){
	$$("id").value = infBoleto.infboletoId;
	$$("nome").value = infBoleto.infboletoNome;
	$$("cnpj").value = infBoleto.infboletoCnpj;
	$$("agencia").value = infBoleto.infboletoAgencia;
	$$("conta").value = infBoleto.infboletoConta;
	$$("carteira").value = infBoleto.infboletoCarteira;
	$$("juroPorMes").value = infBoleto.infboletoJuroMes;
	$$("multa").value = infBoleto.infboletoMulta;
	$$("desconto").value = infBoleto.infboletoDesconto;
	$$("diasDesconto").value = infBoleto.infboletoDataLimiteDesconto;
	$$("instrucao1").value = infBoleto.infboletoInstrucao1;
	$$("instrucao2").value = infBoleto.infboletoInstrucao2;
	$$("localPagamento").value = infBoleto.infboletoLocalPagamentoLn1;
}

function formataCreditoCpfNome( campo, e ) {
	if($$("credito-cpf").checked){
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
		
		if ( (campo.value.length == 3 || campo.value.length == 7 ) && caractereEscape){
			if (campo.value != '.' && caractereEscape){
				campo.value = campo.value + ".";
			}
		}
		
		if ( campo.value.length == 11 && caractereEscape){
			if (campo.value != "-"){
				campo.value = campo.value + "-";
			}
		}
	}else{
		procuraCLiente("credito-cliente");
		return true;
	}
	
}

function formataPagamentoMaoCpfNome( campo, e ) {
	if($$("tp-pg").checked){
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
		
		if ( (campo.value.length == 3 || campo.value.length == 7 ) && caractereEscape){
			if (campo.value != '.' && caractereEscape){
				campo.value = campo.value + ".";
			}
		}
		if ( campo.value.length == 11 && caractereEscape){
			if (campo.value != "-"){
				campo.value = campo.value + "-";
			}
		}
	}else{
		procuraCLiente("cliente-pg");
		return true;
	}
	
}

function formataCriaBoletoCpfNome( campo, e ) {
	if($$("tp-cria").checked){
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
		
		if ( (campo.value.length == 3 || campo.value.length == 7 ) && caractereEscape){
			if (campo.value != '.' && caractereEscape){
				campo.value = campo.value + ".";
			}
		}
		if ( campo.value.length == 11 && caractereEscape){
			if (campo.value != "-"){
				campo.value = campo.value + "-";
			}
		}
	}else{
		procuraCLiente("cliente-cria");
		return true;
	}
	
}

function limpaNomeCredito(){
	$$("credito-cliente").value = "";
	$$("credito-cliente").focus();
}

function limpaNomePagamento(){
	$$("cliente-pg").value = "";
	$$("cliente-pg").focus();
}

function limpaNomeCriaBoleto(){
	$$("cliente-cria").value = "";
	$$("cliente-cria").focus();
}
function buscaUsuarioCredito(){
	
	var cliente = $$("credito-cliente").value;
	var radioTp = document.getElementsByName("tp-credito");
	var tpPesquisa = "";
	for(var i = 0; i < radioTp.length; i++){
		if(radioTp[i].checked == true){
			tpPesquisa = radioTp[i].value;
		}
	}
	var envia = true;
	if($$("credito-cliente").value == ""){
		envia = false;
		$$("credito-cliente").style.background = "#FF0000";
	}else{
		$$("credito-cliente").style.background = "#CCE3FD";
	}
	if(envia){
		AdministracaoJS.buscaUsuarioCredito(cliente, tpPesquisa, true, buscaUsuarioCreditoCallBack);
	}
}
function buscaUsuarioCreditoCallBack(boletos){
	if(boletos != null){
		for(var i = 0; i < boletos.length && i < 3; i++){
			if(i == 0){
				$$("cpf").innerHTML = boletos[i].usuario.usuarioCpf;
				$$("nome").innerHTML = boletos[i].usuario.usuarioNome;
				$$("endereco").innerHTML = boletos[i].usuario.usuarioEndereco + " - " + boletos[i].usuario.usuarioComplementoEndereco;
			}
			var valor = boletos[i].boletosgeradosValorCreditoDebito;
			var sinal = valor.substring(0,1);
			if(sinal == "-"){
				valor = valor.substring(1,(valor.length));
				$$("debito-" + (i+1)).checked = true;
			}else{
				$$("debito-" + (i+1)).checked = false;
			}
			
			$$("valor-" + (i+1)).innerHTML = boletos[i].boletosgeradosValor;
			$$("dt-" + (i+1)).innerHTML = boletos[i].dataTempVencimento;
			
			$$("rest-" + (i+1)).value = valor;
			$$("rest-" + (i+1)).disabled = false;
			
			$$("id-boleto-" + (i+1)).value = boletos[i].boletosgeradosId;
			$$("motivo-" + (i+1)).value = boletos[i].boletosgeradosMotivoCreditoDebito;
			$$("motivo-" + (i+1)).disabled = false;
			$$("debito-" + (i+1)).disabled = false;
			$$("conteiner-credito").style.display = "block";
		}
	}else{
		alert("Não encontrado.");
		$$("conteiner-credito").style.display = "none";

	}
}
function gravaResticio(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();

	var bol = new Array();
	var bol1;
	var bol2;
	var bol3;
	var usuario = {usuarioCpf:$$("cpf").innerHTML};
	if($$("dt-1").innerHTML != "" && $$("dt-1").innerHTML != "&nbsp;" && $$("dt-1").innerHTML != null){
		bol1 = {
			boletosgeradosValorCreditoDebito:$$("debito-1").checked ? "-" + $$("rest-1").value : $$("rest-1").value,
			dataTempVencimento:$$("dt-1").innerHTML,
			boletosgeradosMotivoCreditoDebito:$$("motivo-1").value,
			boletosgeradosId:$$("id-boleto-1").value
		};
		bol.push(bol1);
	}
	if($$("dt-2").innerHTML != "" && $$("dt-2").innerHTML != "&nbsp;" && $$("dt-2").innerHTML != null){
		bol2 = {
			boletosgeradosValorCreditoDebito:$$("debito-2").checked ? "-" + $$("rest-2").value : $$("rest-2").value,
			dataTempVencimento:$$("dt-2").innerHTML,
			boletosgeradosMotivoCreditoDebito:$$("motivo-2").value,
			boletosgeradosId:$$("id-boleto-2").value
		};
		bol.push(bol2);
	}
	if($$("dt-3").innerHTML != "" && $$("dt-3").innerHTML != "&nbsp;" && $$("dt-3").innerHTML != null){
		bol3 = {
			boletosgeradosValorCreditoDebito:$$("debito-3").checked ? "-" + $$("rest-3").value : $$("rest-3").value,
			dataTempVencimento:$$("dt-3").innerHTML,
			boletosgeradosMotivoCreditoDebito:$$("motivo-3").value,
			boletosgeradosId:$$("id-boleto-3").value
		};
		bol.push(bol3);
	}
	AdministracaoJS.salvaCredito(bol, salvaResticiosCallBack);
}
function salvaResticiosCallBack(ret){
	if(ret == 1){
		fnBoleto();
	}else if(ret == 2){
		alert("Verifique os reqistros.\nO valor de um boleto não pode ser menor que R$ 10,00.\nSenão é prejúizo.");
		modalMensagem.escondeModal();
	}else if(ret == 3){
		alert("Ocorreu um erro ao gravar os dados.\nFaça uma nova consulta para verificar o que foi alterado.");
		modalMensagem.escondeModal();
	}
}
function criaBoletoExtraUsuario(){
	var radioTp = document.getElementsByName("tp-cria");
	var tpPesquisa = "";
	for(var i = 0; i < radioTp.length; i++){
		if(radioTp[i].checked == true){
			tpPesquisa = radioTp[i].value;
		}
	}
	var nome = $$("cliente-cria").value;
	var valor = $$("valor-bol").value;
	var ini = $$("dt-cria-bol").value;
	var multa = $$("multa").value;
	var desc = $$("desconto").value;
	var juro = $$("juros").value;
	var dias = $$("diasDesconto").value;

	var envia = true;
	if($$("multa").value == ""){
		envia = false;
		$$("multa").style.background = "#FF0000";
	}else{
		$$("multa").style.background = "#CCE3FD";
	}
	if($$("desconto").value == ""){
		envia = false;
		$$("desconto").style.background = "#FF0000";
	}else{
		$$("desconto").style.background = "#CCE3FD";
	}
	if($$("juros").value == ""){
		envia = false;
		$$("juros").style.background = "#FF0000";
	}else{
		$$("juros").style.background = "#CCE3FD";
	}
	if($$("diasDesconto").value == ""){
		envia = false;
		$$("diasDesconto").style.background = "#FF0000";
	}else{
		$$("diasDesconto").style.background = "#CCE3FD";
	}
	if($$("cliente-cria").value == ""){
		envia = false;
		$$("cliente-cria").style.background = "#FF0000";
	}else{
		$$("cliente-cria").style.background = "#CCE3FD";
	}
	if($$("dt-cria-bol").value == ""){
		envia = false;
		$$("dt-cria-bol").style.background = "#FF0000";
	}else{
		$$("dt-cria-bol").style.background = "#CCE3FD";
	}
	if($$("valor-bol").value == ""){
		envia = false;
		$$("valor-bol").style.background = "#FF0000";
	}else{
		$$("valor-bol").style.background = "#CCE3FD";
	}

	if(envia){
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
		AdministracaoJS.criaBoletoExtraUsuario(nome, valor, ini, tpPesquisa, multa, desc, juro, dias, criaBoletoExtraUsuarioCallBack);
//		criaBoletoExtraUsuarioCallBack({ehErro:false, resposta:"afsdfafdf fdasd\nfasdf asdfs", acao:"criar boleto"});
	}
}
function criaBoletoExtraUsuarioCallBack(ret){
	if(ret.ehErro == false){
		alert(ret.acao + "\n" +ret.resposta);
		fnBoleto();
	}else{
		alert(ret.resposta);
		modalMensagem.escondeModal();
	}
}