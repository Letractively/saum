package br.com.clarotriagem.utils.validadoresSerial;

import java.util.regex.Pattern;



public class ValidaSSN_MDM extends ValidadorGeral {

	public ValidaSSN_MDM(String serial) {
		super(serial);
	}

	public boolean valida() throws Exception {
		switch (new Character(serial.charAt(7))){
			case '6': a = 2006; break;
			case '7': a = 2007; break;
			case '8': a = 2008; break;
			case '9': a = 2009; break;
			case '0': a = 2010; break;
			case '1': a = 2011; break;
			case '2': a = 2012; break;
		}
		switch (new Character(serial.charAt(8))){
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

		Pattern anoSSN_MDM = Pattern.compile("^[6789012]{1}$");  
		Pattern mesSSN_MDM = Pattern.compile("^[123456789abcABC]{1}$");  

		if(serial.length() != 16)
			return false;
        else if(!anoSSN_MDM.matcher(serial.substring(7,8)).matches())
            return false;
        else if(!mesSSN_MDM.matcher(serial.substring(8,9)).matches())
            return false;
        else
        	return true;
	}
	
	public static void main(String[] args){
		ValidaSSN_MDM vm = new ValidaSSN_MDM("846NNS28D8");
		try {
			System.out.println(vm.valida());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
