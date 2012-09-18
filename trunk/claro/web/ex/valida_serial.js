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
	
    if (tipo == 'SSN'){
		a = serial.substring(3,4);
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
		m = serial.substring(4,5);
		switch (m){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
		
        if(serial.length!=11){
			return false;
		}
        else if(serial.substring(0,1)!= "R" && serial.substring(0,1)!= "r" && serial.substring(0,1)!= "I" && serial.substring(0,1)!= "i" && serial.substring(0,1)!= "1"){
            return false;
		}
        else if(!isNaN(serial.substring(1,2))){
            return false;
		}
        else if(!reAno.test(serial.substring(3,4))){
            return false;
		}
        else if(!reMes.test(serial.substring(4,5))){
            return false;
		}
		else {
			if(parseInt(a) > parseInt(ano)){
				return false;
			}
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes))){
				return false;
			}
			else{
				return true;
			}
		} 
    }
	else if (tipo == 'SSN_CAM'){
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
	else if (tipo == 'SSN_CTV'){
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
	else if (tipo == 'SSN_DSC'){
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
	else if (tipo == 'SSN_DVD'){
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
	else if (tipo == 'SSN_MON'){
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
	else if (tipo == 'MSN'){
		serial = serial.toUpperCase();
		var apc = new Array();

		a = serial.substring(4,5);
		switch (a){
			case 'A': a = 2000; break;
			case 'B': a = 2001; break;
			case 'C': a = 2002; break;
			case 'D': a = 2003; break;
			case 'E': a = 2004; break;
			case 'F': a = 2005; break;
			case 'G': a = 2006; break;
			case 'H': a = 2007; break;
			case 'J': a = 2008; break;
			case 'K': a = 1999; break;
			case 'L': a = 2009; break;
			case 'M': a = 2019; break;
			case 'N': a = 2010; break;
			case 'P': a = 2020; break;
			case 'Q': a = 2011; break;
			case 'R': a = 2021; break;
			case 'S': a = 2012; break;
			case 'T': a = 2022; break;
			case 'V': a = 2013; break;
			case 'W': a = 2023; break;
			case 'X': a = 2014; break;
			case 'Z': a = 2024; break;
		}
		m = serial.substring(5,6);
		switch (m){
			case 'A': m = 1;  d =  0; break;
			case 'B': m = 1;  d = 15; break;
			case 'C': m = 2;  d =  0; break;
			case 'D': m = 2;  d = 15; break;
			case 'E': m = 3;  d =  0; break;
			case 'F': m = 3;  d = 15; break;
			case 'G': m = 4;  d =  0; break;
			case 'H': m = 4;  d = 15; break;
			case 'J': m = 5;  d =  0; break;
			case 'K': m = 5;  d = 15; break;
			case 'L': m = 6;  d =  0; break;
			case 'M': m = 6;  d = 15; break;
			case 'N': m = 7;  d =  0; break;
			case 'P': m = 7;  d = 15; break;
			case 'Q': m = 8;  d =  0; break;
			case 'R': m = 8;  d = 15; break;
			case 'S': m = 9;  d =  0; break;
			case 'T': m = 9;  d = 15; break;
			case 'U': m = 10; d =  0; break;
			case 'V': m = 10; d = 15; break;
			case 'W': m = 11; d =  0; break;
			case 'X': m = 11; d = 15; break;
			case 'Y': m = 12; d =  0; break;
			case 'Z': m = 12; d = 15; break;
		}
		
		var anoMSN = /^[ABCDEFGHJKLMNPQRSTVWXZ]{1}$/;
		var mesMSN = /^[ABCDEFGHJKLMNPQRSTUVWXYZ]{1}$/;
		var base28 = /^[23456789BCDFGHJKLMNPQRSTVWXZ]{1}$/;
		var sAPC = verAPC(serial.substring(0,3));
		
		if(serial.length != 10)
			return false;
        else if(!anoMSN.test(serial.substring(4,5)))
            return false;
        else if(!mesMSN.test(serial.substring(5,6)))
            return false;
		else if(!sAPC)
			return false;
		else if(serial.substring(3,4) != 'N')
			return false;
        else if(!base28.test(serial.substring(6,7)))
            return false;
        else if(!base28.test(serial.substring(7,8)))
            return false;
        else if(!base28.test(serial.substring(8,9)))
            return false;
        else if(!base28.test(serial.substring(9,10)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) == parseInt(mes)) && (parseInt(d) > parseInt(dia)))
				return false;
			else
				return true;
		} 
	}
	else if (tipo == 'MSN_NEXTEL'){
		serial = serial.toUpperCase();

		a = serial.substring(4,5);
		switch (a){
			case 'A': a = 2000; break;
			case 'B': a = 2001; break;
			case 'C': a = 2002; break;
			case 'D': a = 2003; break;
			case 'E': a = 2004; break;
			case 'F': a = 2005; break;
			case 'G': a = 2006; break;
			case 'H': a = 2007; break;
			case 'I': a = 2008; break;
			case 'J': a = 2008; break;
			case 'K': a = 2009; break;
			case 'L': a = 2010; break;
			case 'M': a = 2011; break;
			case 'N': a = 2012; break;
		}
		m = serial.substring(5,6);
		switch (m){
			case 'A': m = 1;  d =  0; break;
			case 'B': m = 1;  d = 15; break;
			case 'C': m = 2;  d =  0; break;
			case 'D': m = 2;  d = 15; break;
			case 'E': m = 3;  d =  0; break;
			case 'F': m = 3;  d = 15; break;
			case 'G': m = 4;  d =  0; break;
			case 'H': m = 4;  d = 15; break;
			case 'J': m = 5;  d =  0; break;
			case 'K': m = 5;  d = 15; break;
			case 'L': m = 6;  d =  0; break;
			case 'M': m = 6;  d = 15; break;
			case 'N': m = 7;  d =  0; break;
			case 'P': m = 7;  d = 15; break;
			case 'Q': m = 8;  d =  0; break;
			case 'R': m = 8;  d = 15; break;
			case 'S': m = 9;  d =  0; break;
			case 'T': m = 9;  d = 15; break;
			case 'U': m = 10; d =  0; break;
			case 'V': m = 10; d = 15; break;
			case 'W': m = 11; d =  0; break;
			case 'X': m = 11; d = 15; break;
			case 'Y': m = 12; d =  0; break;
			case 'Z': m = 12; d = 15; break;
		}
		
		var anoMSN = /^[ABCDEFGHIJKLMN]{1}$/;
		var mesMSN = /^[ABCDEFGHJKLMNPQRSTUVWXYZ]{1}$/;
		
		if(serial.length != 10)
			return false;
		else if(serial.substring(0,3) != "364" && serial.substring(0,3)!= "846")
			return false;
		else if(serial.substring(3,4) != 'N')
			return false;
		else if(!anoMSN.test(serial.substring(4,5)))
            return false;
        else if(!mesMSN.test(serial.substring(5,6)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) == parseInt(mes)) && (parseInt(d) > parseInt(dia)))
				return false;
			else
				return true;
		} 
	}
	else if (tipo == 'ESN'){
		a = serial.substring(8,9);
		switch (a){
			case 'a': case 'A': a = 2000; break;
			case 'b': case 'B': a = 2001; break;
			case 'c': case 'C': a = 2002; break;
			case 'd': case 'D': a = 2003; break;
			case 'e': case 'E': a = 2004; break;
			case 'f': case 'F': a = 2005; break;
			case 'g': case 'G': a = 2006; break;
			case 'h': case 'H': a = 2007; break;
			case 'j': case 'J': a = 2008; break;
			case 'k': case 'K': a = 1999; break;
			case 'l': case 'L': a = 2009; break;
			case 'm': case 'M': a = 2019; break;
			case 'n': case 'N': a = 2010; break;
			case 'p': case 'P': a = 2020; break;
			case 'q': case 'Q': a = 2011; break;
			case 'r': case 'R': a = 2021; break;
			case 's': case 'S': a = 2012; break;
			case 't': case 'T': a = 2022; break;
			case 'v': case 'V': a = 2013; break;
			case 'w': case 'W': a = 2023; break;
			case 'x': case 'X': a = 2014; break;
			case 'z': case 'Z': a = 2024; break;
		}
		m = serial.substring(9,10);
		switch (m){
			case 'a': case 'A': m = 1; d = 0; break;
			case 'b': case 'B': m = 1; d = 15; break;
			case 'c': case 'C': m = 2; d = 0; break;
			case 'd': case 'D': m = 2; d = 15; break;
			case 'e': case 'E': m = 3; d = 0; break;
			case 'f': case 'F': m = 3; d = 15; break;
			case 'g': case 'G': m = 4; d = 0; break;
			case 'h': case 'H': m = 4; d = 15; break;
			case 'j': case 'J': m = 5; d = 0; break;
			case 'k': case 'K': m = 5; d = 15; break;
			case 'l': case 'L': m = 6; d = 0; break;
			case 'm': case 'M': m = 6; d = 15; break;
			case 'n': case 'N': m = 7; d = 0; break;
			case 'p': case 'P': m = 7; d = 15; break;
			case 'q': case 'Q': m = 8; d = 0; break;
			case 'r': case 'R': m = 8; d = 15; break;
			case 's': case 'S': m = 9; d = 0; break;
			case 't': case 'T': m = 9; d = 15; break;
			case 'u': case 'U': m = 10; d = 0; break;
			case 'v': case 'V': m = 10; d = 15; break;
			case 'w': case 'W': m = 11; d = 0; break;
			case 'x': case 'X': m = 11; d = 15; break;
			case 'y': case 'Y': m = 12; d = 0; break;
			case 'z': case 'Z': m = 12; d = 15; break;
		}

		var anoESN = /^[abcdefghjklmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ]{1}$/;
		var mesESN = /^[abcdefghjklmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ]{1}$/;
		
		if(serial.length != 11)
			return false;
        else if(!anoESN.test(serial.substring(8,9)))
            return false;
        else if(!mesESN.test(serial.substring(9,10)))
            return false;
		else {
			if(parseInt(a) > parseInt(ano))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) > parseInt(mes)))
				return false;
			else if((parseInt(a) == parseInt(ano)) && (parseInt(m) == parseInt(mes)) && (parseInt(d) > parseInt(dia)))
				return false;
			else
				return true;
		} 
	}
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
    if (tipo == 'SSN_LG'){
		a = serial.substring(0,1);
		switch (a){
			case '5': a = 2005; break;
			case '6': a = 2006; break;
			case '7': a = 2007; break;
			case '8': a = 2008; break;
			case '9': a = 2009; break;
			case '0': a = 2010; break;
			case '1': a = 2011; break;
			case '2': a = 2012; break;
			case '3': a = 2013; break;
			case '4': a = 2014; break;
		}
		m = parseInt(parseFloat(serial.substring(1,3)));

		if(isNaN(a))
			return false;
		else if((isNaN(m)) || (m < 1) || (m > 12))
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
    if (tipo == 'DATECODE'){
		var dias = 0;
		var x = 1;
		var semanas;
		while(x < parseInt(mes)){
			if((x==1) || (x==3) || (x==5) || (x==7) || (x==8) || (x==10) || (x==12))
				dias = dias + 31;
			else if((x==4) || (x==6) || (x==9) || (x==11))
				dias = dias + 30;
			else if(x==2)
				if (eBissexto(ano))
					dias = dias + 29;
				else
					dias = dias + 28;
			x++;
		}
		dias = dias + dia + 6;
		semanas = dias/7;
		
		a = parseInt(serial.substring(0,2));
		w = parseInt(serial.substring(3,5));
		
		if(serial.length!=5)
			return false;
		else if(serial.substring(2,3).toUpperCase() != 'W')
			return false;
		else if((isNaN(w)) || (w < 1) || (w > 52))
			return false;
		else {
			if(parseInt(a) > parseInt(ano.toString().substring(2,4)))
				return false;
			else if((parseInt(a) == parseInt(ano.toString().substring(2,4))) && (parseInt(w) > parseInt(semanas)))
				return false;
			else
				return true;
		} 
    }
	return true;
}
