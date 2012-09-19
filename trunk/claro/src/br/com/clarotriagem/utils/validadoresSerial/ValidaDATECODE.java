package br.com.clarotriagem.utils.validadoresSerial;

import java.util.Calendar;

public class ValidaDATECODE extends ValidadorGeral {

	public ValidaDATECODE(String serial) {
		super(serial);
	}

	public boolean valida() throws Exception {
		int semanas = cal.getActualMaximum(Calendar.WEEK_OF_YEAR);

		a = new Integer(serial.substring(0, 2));
		int w = new Integer(serial.substring(3, 5));

		if (serial.length() != 5) {
			return false;
		} else if (serial.substring(2, 3).equalsIgnoreCase("W")) {
			return false;
		} else if (w < 1 || w > 52) {
			return false;
		} else {
			if (a > anoDoisDigitos) {
				return false;
			} else if (a == anoDoisDigitos && w > semanas) {
				return false;
			} else {
				return true;
			}
		}
	}
}
