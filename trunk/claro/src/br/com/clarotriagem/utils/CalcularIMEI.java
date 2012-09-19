package br.com.clarotriagem.utils;

public class CalcularIMEI {

	public static boolean startValidate(String imei) {
		int resultSum = resultImei(imei);
		int check = 0;
		final int CARACTER_ZERO_ASCII = 48;
		if (resultSum % 10 == 0) {
			if (((int) (imei.charAt(imei.length() - 1) - CARACTER_ZERO_ASCII)) == 0) {
				return true;
			} else
				return false;
		} else

			check = resultSum % 10;
		if (((int) (imei.charAt(imei.length() - 1) - CARACTER_ZERO_ASCII)) == (10 - check)) {
			return true;
		} else
			return false;
	}

	private static int resultImei(String imei) {
		int totalResult = 0;
		int aux = 0;
		final int CARACTER_ZERO_ASCII = 48;
		for (int i = imei.length() - 2; i >= 0; i--) {
			if (i % 2 != 0) {
				aux = (int) (imei.charAt(i) - CARACTER_ZERO_ASCII);
				aux = aux * 2;
				String aux2 = String.valueOf(aux);
				if (aux > 9) {
					aux = ((int) (aux2.charAt(0) - CARACTER_ZERO_ASCII)) + ((int) (aux2.charAt(1) - CARACTER_ZERO_ASCII));
				}
				totalResult += aux;
			} else {
				aux = (int) (imei.charAt(i) - CARACTER_ZERO_ASCII);
				totalResult += aux;
			}
		}
		return totalResult;
	}
	
}
