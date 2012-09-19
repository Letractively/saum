package br.com.clarotriagem.utils.validadoresSerial;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public abstract class ValidadorGeral {

	Calendar cal;
	protected int mes;
	protected int ano;
	protected int anoDoisDigitos;
	protected int dia;
	protected int m;
	protected int a;
	protected int d;

	protected Pattern reNum;
	protected Pattern reAno;
	protected Pattern reMes;

	protected String serial;

	public ValidadorGeral(String serial) {

		if (serial != null) {
			this.serial = serial.toUpperCase();
		}

		cal = new GregorianCalendar();
		cal.setTimeInMillis(System.currentTimeMillis());

		mes = cal.get(Calendar.MONTH) + 1;
		ano = cal.get(Calendar.YEAR);
		anoDoisDigitos = new Integer(Integer.toString(ano).substring(2, 4));
		dia = cal.get(Calendar.DAY_OF_MONTH);

		m = 0;
		a = 0;
		d = 0;

		reNum = Pattern.compile("^[a-zA-Z0-9]{5,25}$");
		reAno = Pattern.compile("^[jknrtwxyalmpqszbcJKNRTWXYALMPQSZBC]{1}$");
		reMes = Pattern.compile("^[1-9abcABC]{1}$");
	}

	protected boolean ehBissexto() {
		if ((ano % 400) == 0) {
			return true;
		} else if ((ano % 100) == 0) {
			return false;
		} else if ((ano % 4) == 0) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean validaPadraoSerial() {
		if (!reNum.matcher(serial).matches()) {
			return false;
		} else {
			return true;
		}
	}

	public abstract boolean valida() throws Exception;

}
