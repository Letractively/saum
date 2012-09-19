package br.com.clarotriagem.utils.validadoresSerial;

import java.util.regex.Pattern;

public class ValidaESN extends ValidadorGeral {

	public ValidaESN(String serial) {
		super(serial);
	}

	public boolean valida() throws Exception {
		char anoComparado = serial.charAt(8);
		switch (anoComparado){
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
		char mesComparado = serial.charAt(9);
		switch (mesComparado){
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

		String anoESN = "^[abcdefghjklmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ]{1}$";
		String mesESN = "^[abcdefghjklmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ]{1}$";
		
		Pattern pAnoESN = Pattern.compile(anoESN);  
		Pattern pMesESN = Pattern.compile(mesESN);  


		if(serial.length() != 11)
			return false;
        else if(!pAnoESN.matcher(serial.substring(8,9)).matches())
            return false;
        else if(!pMesESN.matcher(serial.substring(9,10)).matches())
            return false;
		else {
			if(a > ano){
				return false;
			}else if(a == ano && m > mes){
				return false;
			}else if(a == ano && m == mes && d > dia){
				return false;
			}else{
				return true;
			}
		} 
	}
}
