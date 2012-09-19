package br.com.clarotriagem.utils.validadoresSerial;



public class ValidaSSN_LG extends ValidadorGeral {

	public ValidaSSN_LG(String serial) {
		super(serial);
	}

	public boolean valida() throws Exception {
		switch (new Character(serial.charAt(0))){
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
		
		m = new Integer(serial.substring(1,3));

		if(!Character.isDigit(serial.charAt(0)))
			return false;
		else if(m < 1 || m > 12)
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
		ValidaSSN_LG vm = new ValidaSSN_LG("846NNS28D8");
		try {
			System.out.println(vm.valida());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
