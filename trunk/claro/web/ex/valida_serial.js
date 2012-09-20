function isSerial(serial, tipo){
	var hoje = new Date();
	var mes = parseInt(hoje.getMonth()) + 1;
	var ano = parseInt(hoje.getYear());
	var dia = parseInt(hoje.getDate());
	var m = 0;
	var a = 0;
	var d = 0;
	var reNum = /^[a-zA-Z0-9]{5,25}$/;
	if(!reNum.test(serial)){
		return false;
	}
	
	var reAno = /^[jknrtwxyalmpqszbcJKNRTWXYALMPQSZBC]{1}$/;
	var reMes = /^[1-9abcABC]{1}$/;
	
    if (tipo == 'SSN'){}
	else if (tipo == 'SSN_CAM'){}
	else if (tipo == 'SSN_CTV'){}
	else if (tipo == 'SSN_SWA'){}
	else if (tipo == 'SSN_DSC'){}
	else if (tipo == 'SSN_DVD'){}
	else if (tipo == 'SSN_MON'){}
	else if (tipo == 'SSN_PRT'){}
	else if (tipo == 'SSN_REF'){}
	else if (tipo == 'SSN_V/C'){}
	else if (tipo == 'SSN_W/M'){}
	else if (tipo == 'SSN_YEPP'){}
	else if (tipo == 'MSN'){}
	else if (tipo == 'MSN_NEXTEL'){}
	else if (tipo == 'ESN'){}
	else if (tipo == 'SSN_MDM'){}
	else if (tipo == 'SSN_NBK'){}
	else if (tipo == 'SSN_NPC'){}
    if (tipo == 'SSN_LG'){}
    if (tipo == 'DATECODE'){}
	return true;
}
