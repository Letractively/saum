//alterado
function iniciaCalendarioIni() {
	Calendar.setup({
		inputField : "dt-inicial",
		button     : "bt-data-ini",
		ifFormat   : "%d/%m/%Y"
	});
}

function iniciaCalendarioFin() {
	Calendar.setup({
		inputField : "dt-final",
		button     : "bt-data-fin",
		ifFormat   : "%d/%m/%Y"
	});
}

function buscaDemandas(){
	
	var dataInicial = $$("dt-inicial").value;
	var dataFinal = $$("dt-final").value;
	var cliente = $$("cliente").value;
	
	var tpCliente;
	if($$("tp").checked){
		tpCliente = 1;
	}else{
		tpCliente = 2;
	}
	
	var aberto;
	if($$("aberto").checked){
		aberto = 1;
	}else{
		aberto = 2;
	}
	
	AdministracaoJS.buscaRelatorioDemandas(dataInicial, dataFinal, cliente, tpCliente, aberto, buscaDemandasCallBack);
	
}

function buscaDemandasCallBack(ret){
	fnRelatorioDemandas();
}

function iniciaSessao(){
	var tpCliente = $$("sessao-tpcliente").value;
	var aberto = $$("sessao-aberto").value;
	if(aberto == 0 || aberto == 2){
		$$("aberto").checked = false;
	}else{
		$$("aberto").checked = true;
	}
	if(tpCliente == 2){
		$$("tp").checked = false;
		$$("tp1").checked = true;
	}else{
		$$("tp").checked = true;
		$$("tp1").checked = false;
	}
}
function detalha(id){
	fnLocalizarDemandaComId(id);
}