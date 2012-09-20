package br.com.clarotriagem.utils.validadoresSerial;



public class ValidaSSN_WM extends ValidadorGeral {

	public ValidaSSN_WM(String serial) {
		super(serial);
	}

	public boolean valida() throws Exception {
		switch (new Character(serial.charAt(7))){
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
		switch (new Character(serial.charAt(8))){
			case 'a': case 'A': m = 10; break;
			case 'b': case 'B': m = 11; break;
			case 'c': case 'C': m = 12; break;
		}
	
        if(serial.length() != 15)
            return false;
        else if(Character.isDigit(serial.charAt(14)))
            return false;
        else if(!reAno.matcher(serial.substring(7,8)).matches())
            return false;
        else if(!reMes.matcher(serial.substring(8,9)).matches())
            return false;
		else {
			if((a) > (ano))
				return false;
			else if(((a) == (ano)) && ((m) > (mes)))
				return false;
			else
				return true;
		} 
    }
	
	public static void main(String[] args){
		ValidaSSN_WM vm = new ValidaSSN_WM("846NNS28D8");
		try {
			System.out.println(vm.valida());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
