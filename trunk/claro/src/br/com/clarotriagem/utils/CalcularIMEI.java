package br.com.clarotriagem.utils;

public class CalcularIMEI {

	public static boolean startValidate(String imei) {
		int resultSum = resultImei(imei);
		int check = 0;
		final int CARACTER_ZERO_ASCII = 48;
		if (resultSum % 10 == 0) {
			System.out.println("CheckSum = 0");
			if (((int) (imei.charAt(imei.length() - 1) - CARACTER_ZERO_ASCII)) == 0) {
				return true;
			} else
				return false;
		} else

			check = resultSum % 10;
		System.out.println("CheckSum  = " + (10 - check));
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
			System.out.println(imei.charAt(i));
			if (i % 2 != 0) {
				System.out.println("Odd ");
				System.out.println("Number to be verified = " + imei.charAt(i));
				aux = (int) (imei.charAt(i) - CARACTER_ZERO_ASCII);
				aux = aux * 2;
				System.out.println("Number with the value doubled = " + aux);
				String aux2 = String.valueOf(aux);
				if (aux > 9) {
					System.out.println("Sum grather then 9");
					aux = ((int) (aux2.charAt(0) - CARACTER_ZERO_ASCII)) + ((int) (aux2.charAt(1) - CARACTER_ZERO_ASCII));
					System.out.println("Result of the sum of digits: " + aux);
				}
				totalResult += aux;
				System.out.println("Partial Sum = " + totalResult);
			} else {
				System.out.println("Pair ...");
				aux = (int) (imei.charAt(i) - CARACTER_ZERO_ASCII);
				totalResult += aux;
				System.out.println("Result of the partial sum = " + totalResult);
			}
		}
		System.out.println("Final result of sum = " + totalResult);

		return totalResult;
	}
	
}
