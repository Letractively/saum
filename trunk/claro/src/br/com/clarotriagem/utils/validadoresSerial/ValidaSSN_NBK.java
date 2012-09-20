package br.com.clarotriagem.utils.validadoresSerial;

import java.util.regex.Pattern;



public class ValidaSSN_NBK extends ValidadorGeral {

	public ValidaSSN_NBK(String serial) {
		super(serial);
	}

	public boolean valida() throws Exception {
		switch (new Character(serial.charAt(7))){
			case 's': case 'S': a = 2009; break;
			case 'z': case 'Z': a = 2010; break;
			case 'b': case 'B': a = 2011; break;
			case 'c': case 'C': a = 2012; break;
		}
		switch (new Character(serial.charAt(8))){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}

		Pattern anoSSN_NBK = Pattern.compile("^[szbSZBC]{1}$");  
		Pattern mesSSN_NBK = Pattern.compile("^[123456789abcABC]{1}$");  
		
		if(serial.length() != 15)
            return false;
        else if(!anoSSN_NBK.matcher(serial.substring(7,8)).matches())
            return false;
        else if(!mesSSN_NBK.matcher(serial.substring(8,9)).matches())
            return false;
		else {
			if(a > ano)
				return false;
			else if(a == ano && m > mes)
				return false;
			else
				return true;
		} 
    }
	
	public static void main(String[] args){
		ValidaSSN_NBK vm = new ValidaSSN_NBK("846NNS28D8");
		try {
			System.out.println(vm.valida());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
