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
	else if (tipo == 'SSN_SWA'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
	
        if(serial.length!=14)
            return false;
		else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
	}
	else if (tipo == 'SSN_DSC'){}
	else if (tipo == 'SSN_DVD'){}
	else if (tipo == 'SSN_MON'){}
	else if (tipo == 'SSN_PRT'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}

		if(serial.length < 15 || serial.length > 25)
			return false;
        else if(!isNaN(serial.substring((serial.length - 1),serial.length)))
            return false;
        else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'SSN_REF'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
	
        if(serial.length!=15)
            return false;
        else if(!isNaN(serial.substring(14,15)))
            return false;
        else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'SSN_V/C'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
	
        if(serial.length!=15)
            return false;
        else if(!isNaN(serial.substring(14,15)))
            return false;
        else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'SSN_W/M'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
	
        if(serial.length!=15)
            return false;
        else if(!isNaN(serial.substring(14,15)))
            return false;
        else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'SSN_YEPP'){
		a = serial.substring(7,8);
		switch (a){
			case 'j': case 'J': a = 1998; break;
			case 'k': case 'K': a = 1999; break;
			case 'n': case 'N': a = 2000; break;
			case 'r': case 'R': a = 2001; break;
			case 't': case 'T': a = 2002; break;
			case 'w': case 'W': a = 2003; break;
			case 'x': case 'X': a = 2004; break;
			case 'y': case 'Y': a = 2005; break;
			case 'a': case 'A': a = 2006; break;
			case 'l': case 'L': a = 2006; break;
			case 'm': case 'M': a = 2007; break;
			case 'p': case 'P': a = 2007; break;
			case 'q': case 'Q': a = 2008; break;
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
	
        if(serial.length!=15)
            return false;
        else if(!isNaN(serial.substring(14,15)))
            return false;
        else if(!reAno.test(serial.substring(7,8)))
            return false;
        else if(!reMes.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'MSN'){}
	else if (tipo == 'MSN_NEXTEL'){}
	else if (tipo == 'ESN'){}
	else if (tipo == 'SSN_MDM'){
		a = serial.substring(7,8);
		switch (a){
			case '6': a = 2006; break;
			case '7': a = 2007; break;
			case '8': a = 2008; break;
			case '9': a = 2009; break;
			case '0': a = 2010; break;
			case '1': a = 2011; break;
			case '2': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case '1': m = 1; break;
			case '2': m = 2; break;
			case '3': m = 3; break;
			case '4': m = 4; break;
			case '5': m = 5; break;
			case '6': m = 6; break;
			case '7': m = 7; break;
			case '8': m = 8; break;
			case '9': m = 9; break;
			case 'A': case 'a': m = 10; break;
			case 'B': case 'b': m = 11; break;
			case 'C': case 'c': m = 12; break;
		}
		var anoSSN_MDM = /^[6789012]{1}$/;
		var mesSSN_MDM = /^[123456789abcABC]{1}$/;
		
		if(serial.length != 16)
			return false;
        else if(!anoSSN_MDM.test(serial.substring(7,8)))
            return false;
        else if(!mesSSN_MDM.test(serial.substring(8,9)))
            return false;
	}
	else if (tipo == 'SSN_NBK'){
		a = serial.substring(7,8);
		switch (a){
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}

		var anoSSN_NBK = /^[szbSZBC]{1}$/;
		var mesSSN_NBK = /^[123456789abcABC]{1}$/;
        
		if(serial.length!=15)
            return false;
        else if(!anoSSN_NBK.test(serial.substring(7,8)))
            return false;
        else if(!mesSSN_NBK.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
	else if (tipo == 'SSN_NPC'){
		a = serial.substring(7,8);
		switch (a){
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		m = serial.substring(8,9);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}

		var anoSSN_NBK = /^[szbSZBC]{1}$/;
		var mesSSN_NBK = /^[123456789abcABC]{1}$/;
        
		if(serial.length!=15)
            return false;
        else if(!anoSSN_NBK.test(serial.substring(7,8)))
            return false;
        else if(!mesSSN_NBK.test(serial.substring(8,9)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else
				return true;
		} 
    }
    if (tipo == 'SSN_LG'){}
    if (tipo == 'DATECODE'){}
	return true;
}
