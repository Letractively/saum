//alterado
function calculate() {
	var ipaddress = ($$("ipinput").value);
	var oComboIP = $$("cidr_netmask");
	var smmask = (oComboIP.options[oComboIP.selectedIndex].value);
	var validchars = "0123456789.";
	var test = false;
	for (i = 0; i < ipaddress.length; i++) {
		var c = 0;
		test = false;
		while ((c < 11) && (test == false)) {
			if (ipaddress.charAt(i) == validchars.charAt(c)) {
				test = true;
			}
			;
			c = c + 1;
		}
		if (test == false) {
			$$("reportbox").value = "Endereço inválido. Endereço IP pode conter apenas números e ponto.";
			return;
		}
		;
	}
	for (i = 0; i < smmask.length; i++) {
		var c = 0;
		test = false;
		while ((c < 11) && (test == false)) {
			if (smmask.charAt(i) == validchars.charAt(c)) {
				test = true;
			}
			;
			c = c + 1;
		}
		if (test == false) {
			$$("reportbox").value = "Endereço inválido. Endereço IP pode conter apenas números e ponto.";
			return;
		}
		;
	}
	var dotpos = new Array(3);
	dotpos[0] = 0;
	dotpos[1] = 0;
	dotpos[2] = 0;
	var c = -1;
	for (i = 0; i < ipaddress.length; i++) {
		if (ipaddress.charAt(i) == ".") {
			c = (c + 1);
			dotpos[c] = i + 1;
		}
	}
	var val1 = parseInt(ipaddress.substring(0, dotpos[0]), 10);
	var val2 = parseInt(ipaddress.substring(dotpos[0], dotpos[1]), 10);
	var val3 = parseInt(ipaddress.substring(dotpos[1], dotpos[2]), 10);
	var val4 = parseInt(ipaddress.substring(dotpos[2], ipaddress.length), 10);
	dotpos[0] = 0;
	dotpos[1] = 0;
	dotpos[2] = 0;
	var c = -1;
	for (i = 0; i < smmask.length; i++) {
		if (smmask.charAt(i) == ".") {
			c = (c + 1);
			dotpos[c] = i + 1;
		}
	}
	var val5 = parseInt(smmask.substring(0, dotpos[0]), 10);
	var val6 = parseInt(smmask.substring(dotpos[0], dotpos[1]), 10);
	var val7 = parseInt(smmask.substring(dotpos[1], dotpos[2]), 10);
	var val8 = parseInt(smmask.substring(dotpos[2], smmask.length), 10);
	$$("starthost").value = "0.0.0.0";
	$$("endhost").value = "0.0.0.0";
	$$("numofhosts").value = "0";
	$$("subnetaddress").value = "0.0.0.0";
	$$("broadcastaddress").value = "0.0.0.0";
	$$("networkclass").value = "-";
	$$("subsizebits").value = "0";
	$$("hostsizebits").value = "0";
	var val9 = val1;
	var val10 = 0;
	var val11 = 0;
	var val12 = 0;
	var val13 = val1;
	var val14 = 0;
	var val15 = 0;
	var val16 = 0;
	var val17 = "-";
	var x = 0;
	var ipdec = 0;
	var smdec = 0;
	var startdec = 0;
	var enddec = 0;
	var message1 = "Valor precisa estar entre 0 e 255";
	var message2 = "Máscara de sub-rede inválida.";
	var message3 = "O endereço IP não pode ser usado como endereço de host utilizando essa máscara de sub-rede.";
	var message4 = "A mêscara de sub-rede é inválida.";
	var message5 = "O endereço IP é inválido";
	var message6 = " ";
	var message7 = "O tipo de endereço 127.x.x.x é inválido como endereço de um host.";
	var message9 = "Nota: "
			+ val1 + "." + val2 + "." + val3 + "." + val4
			+ " é um endereço IP classe B e normalmente é usado com sub-rede do tipo 255.255.x.x";
	var message10 = "nota: "
			+ val1 + "." + val2 + "." + val3 + "." + val4 
			+ " é um endereço IP classe C e normalmente é usado com sub-rede do tipo 255.255.255.x";
	var message11 = "Pronto.";
	var message12 = "Endereço IP do tipo 0.x.x.x são inválidos como endereço de host.";
	var message13 = "Este é um endereço IP classe D. Inválido como endereço de host.";
	var message14 = "Este é um endereço IP classe E. Inválido como endereço de host.";
	var output = message11;
	if (val1 == 127) {
		$$("reportbox").value = message7;
		return
	};
	if (val1 == 0) {
		$$("reportbox").value = message12;
		return
	};
	if ((val1 > 255) || (val2 > 255) || (val3 > 255) || (val4 > 255)
			|| (val5 > 255) || (val6 > 255) || (val7 > 255) || (val8 > 255)) {
		$$("reportbox").value = message1;
		return
	};
	if (val5 != 255) {
		$$("reportbox").value = message2;
		return
	};
	if ((val6 != 00) && (val6 != 128) && (val6 != 192) && (val6 != 224)
			&& (val6 != 240) && (val6 != 248) && (val6 != 252) && (val6 != 254)
			&& (val6 != 255)) {
		$$("reportbox").value = message2;
		return
	};
	if ((val7 != 00) && (val7 != 128) && (val7 != 192) && (val7 != 224)
			&& (val7 != 240) && (val7 != 248) && (val7 != 252) && (val7 != 254)
			&& (val7 != 255)) {
		$$("reportbox").value = message2;
		return
	};
	if ((val8 != 00) && (val8 != 128) && (val8 != 192) && (val8 != 224)
			&& (val8 != 240) && (val8 != 248) && (val8 != 252) && (val8 != 254)
			&& (val8 != 255)) {
		$$("reportbox").value = message2;
		return
	};
	if (val6 != 255) {
		if ((val7 != 0) || (val8 != 0)) {
			$$("reportbox").value = message4;
			return
		}
	};
	if (val7 != 255) {
		if (val8 != 0) {
			$$("reportbox").value = message4;
			return
		}
	};
	if (val8 > 252) {
		$$("reportbox").value = message4;
		return
	};
	smdec = (val6 * 65536) + (val7 * 256) + val8;
	ipdec = (val2 * 65536) + (val3 * 256) + val4;
	for (t = 2; t < 25; t = t + 1) {
		if (smdec == (16777216 - Math.pow(2, t))) {
			(n = 32 - t);
		}
	}
	if (val1 < 127) {
		val17 = "A";
	}
	if ((val1 > 127) && (val1 < 192)) {
		val17 = "B";
	}
	if ((val1 > 191) && (val1 < 224)) {
		val17 = "C";
	}
	if ((val1 > 223) && (val1 < 240)) {
		val17 = "D";
	}
	if ((val1 > 239) && (val1 < 256)) {
		val17 = "E";
	}
	if ((val17 == "B") && (n < 16)) {
		output = message9;
	};
	if ((val17 == "C") && (n < 24)) {
		output = message10;
	};
	if (val17 == "D") {
		$$("reportbox").value = message13;
		return
	};
	if (val17 == "E") {
		$$("reportbox").value = message14;
		return
	};
	if (n > 23) {
		x = Math.pow(2, (32 - n));
		for (i = -1; i < (256 - x); i = (i + x)) {
			if ((val4 >= (i + 1)) && (val4 <= (i + x))) {
				val12 = (i + 2);
				val16 = (i + (x - 1));
			}
		}
		val10 = val2;
		val11 = val3;
		val14 = val2;
		val15 = val3;
		if ((String(String(val2) + val3 + (val4)) == String(String(val10)
				+ val11 + (val12 - 1)))
				|| (String(String(val2) + val3 + (val4)) == String(String(val14)
						+ val15 + (val16 + 1)))) {
			output = "Atenção:IP " + val1 + "." + val2 + "." + val3
					+ "." + val4
					+ " não é usual como host utilizando a mascara de sub-rede " + val5
					+ "." + val6 + "." + val7 + "." + val8;
		};
	}
	if ((n >= 16) && (n < 24)) {
		x = Math.pow(2, (24 - n));
		for (i = 0; i <= (256 - x); i = (i + x)) {
			if ((val3 >= (i)) && (val3 <= (i + x))) {
				val11 = (i);
				val15 = (i + (x - 1));
			}
		}
		val10 = val2;
		val14 = val2;
		val12 = 1;
		val16 = 254;
		if ((String(String(val2) + val3 + (val4)) == String(String(val10)
				+ val11 + (val12 - 1)))
				|| (String(String(val2) + val3 + (val4)) == String(String(val14)
						+ val15 + (val16 + 1)))) {
			output = "Atenção:IP " + val1 + "." + val2 + "." + val3	+ "." + val4
					+ " não é usual como host utilizando a mascara de sub-rede " + val5
					+ "." + val6 + "." + val7 + "." + val8;
		};
	}
	if ((n >= 8) && (n < 16)) {
		x = Math.pow(2, (16 - n));
		for (i = 0; i <= (256 - x); i = (i + x)) {
			if ((val2 >= (i)) && (val2 <= (i + x))) {
				val10 = (i);
				val14 = (i + (x - 1));
			}
		}
		val11 = 0;
		val15 = 255;
		val12 = 1;
		val16 = 254;
		if ((String(String(val2) + val3 + (val4)) == String(String(val10)
				+ val11 + (val12 - 1)))
				|| (String(String(val2) + val3 + (val4)) == String(String(val14)
						+ val15 + (val16 + 1)))) {
			output = "Atenção:IP " + val1 + "." + val2 + "." + val3
					+ "." + val4
					+ " não é usual como host utilizando a mascara de sub-rede " + val5
					+ "." + val6 + "." + val7 + "." + val8;
		};
	}
	$$("starthost").value = val9 + "." + val10 + "." + val11 + "." + val12;
	$$("endhost").value = val13 + "." + val14 + "." + val15 + "." + val16;
	$$("numofhosts").value = (Math.pow(2, (32 - n)) - 2);
	$$("subnetaddress").value = val9 + "." + val10 + "." + val11 + "." + ((val12) - 1);
	$$("broadcastaddress").value = val13 + "." + val14 + "." + val15 + "." + ((val16) + 1);
	$$("subsizebits").value = n;
	$$("hostsizebits").value = 32 - n;
	$$("networkclass").value = val17;
	$$("reportbox").value = output;
	
	montaTabelaIp(val12, val16, (Math.pow(2, (32 - n)) - 2), val9 + "." + val10 + "." + val11 + ".");
}
function montaTabelaIp(primeiroIP, ultimoHost, numHosts, ips){
	var html = "<table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\" style=\"font-size:12px;\">";
	html += "<tr bgcolor=\"#cccccc\"><td colspan=\"5\" align=\"center\"><strong>Escolha os IP's que estarão dispníveis</strong></td></tr>";
	var cont = 1;
	var impar = true;
	for(var i = primeiroIP; i <= ultimoHost; i++){
		if(cont == 1){
			if(impar == true){
				html += "<tr bgcolor=\"#DFE8F6\">";
				impar = false;
			}else{
				html += "<tr>";
				impar = true;
			}
		}
		
		html += "<td width=\"20%\">";
		html += "<label><input name=\"cb-ips\" type=\"checkbox\" id=";
		html += "\"cb-ip" + ips + "\" value=\"" + ips + i + "\" checked=\"checked\" ";
		html += "/><strong>&nbsp;" + ips + i + "</strong></label>";

		html += "</td>";
		if(cont == 5){
			cont = 1;
			html += "</tr>";
		}else{
			cont++;
		}
		if(i == (ultimoHost)){
			switch (cont){
				case 1:
					html += "</table><br/>";
					break;
				case 2:
					html += "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></table><br/>";
					break;
				case 3:
					html += "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></table><br/>";
					break;
				case 4:
					html += "<td>&nbsp;</td><td>&nbsp;</td></table><br/>";
					break;
				case 5:
					html += "<td>&nbsp;</td></table><br/>";
					break;
			}
		}
	}
	$$("div-ips").innerHTML = html;
}

function buscaIP(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
	var oComboServ = $$("combo-alt-servidor");
	var comboServ = oComboServ.options[oComboServ.selectedIndex].value;
	if(comboServ != ""){
		UtilsJS.buscaIP(comboServ, buscaIPCallback);
	}else{
		modalMensagem.escondeModal();
	}
}
function buscaIPCallback(ip){
	if(ip != null){
		$$("ipinput").value = ip.enderecoipEndereco;
		var mask = $$("cidr_netmask");
		for(var i = 0; i < mask.options.length; i++){
			if(mask.options[i].value == ip.enderecoipMascaraSubrede){
				mask.options[i].selected = true;
			}
		}
		calculate();
		modalMensagem.escondeModal();
	}else{
		modalMensagem.escondeModal();
	}
}
function enviar(){
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();

	var ipInicio = $$("starthost").value;
	var ipFim = $$("endhost").value;
	var qtdIP = $$("numofhosts").value;
	var oComboServ = $$("combo-alt-servidor");
	var servidor = oComboServ.options[oComboServ.selectedIndex].value;
	var servidorNome = oComboServ.options[oComboServ.selectedIndex].text;
	var oMask = $$("cidr_netmask");
	var mask = oMask.options[oMask.selectedIndex].value;

	var envia = true;
	if(ipInicio == "0.0.0.0" || ipFim == "0.0.0.0" || qtdIP == "0" || servidor == ""){
		alert("IP's inválidos.");
		envia = false;
	}
	if(envia){
		if(confirm("Tem certeza que deseja alterar os IP para os clientes da torre " + servidorNome + "?\n\nTodos os IP's dessa torre serão zerados\ne os clientes estarão sem IP.")){
			
			var oCbIp = document.getElementsByName("cb-ips");
			var listaIP = new Array();
			var temIP = false;
			for(var i = 0; i < oCbIp.length; i++){
					if(oCbIp[i].checked){
						var t = {
							servidor:{servidorId:servidor},
							enderecoipEndereco:oCbIp[i].value,
							enderecoipAtivado:oCbIp[i].checked == true ? "true" : "false",
							enderecoipMascaraSubrede:mask
						};
						listaIP.push(t);
						temIP = true;
					}
			}

			
			UtilsJS.gravaIP(listaIP, servidor, enviarCallBack);
		}else{
			modalMensagem.escondeModal();
		}
	}else{
		modalMensagem.escondeModal();
	}
}
function enviarCallBack(ret){
	if(ret == 0){
		fnEnderecoIP();
	}else{
		alert("Erro ao atribuir endereços de IP à torre.");
	}
	modalMensagem.escondeModal();
}