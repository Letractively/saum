package br.com.clarotriagem.utils.validadoresSerial;

import java.util.regex.Pattern;


public class ValidaMSN extends ValidadorGeral {

	String[] apc;

	public ValidaMSN(String serial, String[] apc) {
		super(serial);
		this.apc = apc;
	}

	public boolean valida() throws Exception {
		serial = serial.toUpperCase();

		switch (new Character(serial.charAt(4))){
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
		
		switch (new Character(serial.charAt(5))){
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
		
		String base28 = "^[23456789BCDFGHJKLMNPQRSTVWXZ]{1}$";
		String anoMSN = "^[ABCDEFGHJKLMNPQRSTVWXZ]{1}$";
		String mesMSN = "^[ABCDEFGHJKLMNPQRSTUVWXYZ]{1}$";

		Pattern padrao28 = Pattern.compile(base28);  
		Pattern padraoANO = Pattern.compile(anoMSN);  
		Pattern padraoMES = Pattern.compile(mesMSN);  

		if (serial.length() != 10)
			return false;
		else if (!serial.substring(3, 4).equalsIgnoreCase("N"))
			return false;
		else if (!verAPC(serial.substring(0, 3)))
			return false;
		else if (!padraoANO.matcher(serial.substring(4, 5)).matches())
			return false;
		else if (!padraoMES.matcher(serial.substring(5, 6)).matches())
			return false;
		else if (!padrao28.matcher(serial.substring(6, 7)).matches())
			return false;
		else if (!padrao28.matcher(serial.substring(7, 8)).matches())
			return false;
		else if (!padrao28.matcher(serial.substring(8, 9)).matches())
			return false;
		else if (!padrao28.matcher(serial.substring(9, 10)).matches())
			return false;
		else {
			if (a > ano)
				return false;
			else if (a == ano && m > mes)
				return false;
			else if (a == ano && m == mes && d > dia)
				return false;
			else
				return true;
		}
	}
	
	private boolean verAPC(String sn){
		if(apc != null){
			for (int j = 0; j < apc.length; j++) {
				if(apc[j] == sn)
				return true;
			}		
		}
		return true;
	}
	
	public static void main(String[] args){
		ValidaMSN vm = new ValidaMSN("J28NNS28D8", null);
		try {
			System.out.println(vm.valida());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
