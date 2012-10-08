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

function buscaTrafego(){
	
	var dataInicial = $$("dt-inicial").value;
	var dataFinal = $$("dt-final").value;
	var cliente = $$("cliente").value;
	
	var tpCliente;
	if($$("tp").checked){
		tpCliente = 1;
	}else{
		tpCliente = 2;
	}
	
	AdministracaoJS.buscaRelatorioTrafego(dataInicial, dataFinal, cliente, tpCliente, buscaTrafegoCallBack);
	
}

function preencheCampoBuscaRelatorioCliente(nome){
	$$("cliente").value = nome;
	$$("tp1").checked = true;
	$$("tp").checked = false;
	buscaTrafego();
}

function buscaTrafegoCallBack(ret){
	fnrelatorioTrafegoUsuario();
}

function iniciaSessao(){
	var tpCliente = $$("sessao-tpcliente").value;
	if(tpCliente == 2){
		$$("tp").checked = false;
		$$("tp1").checked = true;
	}else{
		$$("tp").checked = true;
		$$("tp1").checked = false;
	}
}