package br.com.meganet.geradorCPF;


import java.util.Random;


/**
 * Brazilian CPF: The purpose of this class is to facilitate the writing of
 * automated testing by testers well intentioned, the author is not responsible
 * for any misuse of these algorithms
 */
public class CPFGenerator {
	static Long generate(Random random) {
		int[] digits = NumberGenerator.generateArray(random, 11, 9);
		// First digit
		int total = 0;
		for (int i = 1, j = 10; i <= 9; i++, j--)
			total += digits[i - 1] * j;
		int rest = total % 11;
		if (rest < 2)
			digits[9] = 0;
		else
			digits[9] = 11 - rest;
		// Second digit
		total = 0;
		for (int i = 1, j = 11; i <= 10; i++, j--)
			total += digits[i - 1] * j;
		rest = total % 11;
		if (rest < 2)
			digits[10] = 0;
		else
			digits[10] = 11 - rest;
		return Util.arrayToLong(digits);
	}

	/**
	 * @return 11 digits ############
	 */
	public static Long generateRandom() {
		return generate(new Random());
	}

	/**
	 * @param seed
	 *            an indice
	 * @return 11 digits ############
	 */
	public static Long generate(long seed) {
		return generate(new Random(seed));
	}

	/**
	 * Put zeros in the beginning if the cpf has less than 11 digits.
	 *
	 * @return "###.###.###-##"
	 */
	public static String format(Long cpf) {
		String str = cpf.toString();
		if (str.length() < 11)
			for (int i = str.length(); i < 11; i++)
				str = "0" + str;
		return str.substring(0, 3) + "." + str.substring(3, 6) + "." + str.substring(6, 9) + "-"
				+ str.substring(9);
	}

	/**
	 * @return "###.###.###-##"
	 */
	public static String generateRandomFormatted() {
		return format(generateRandom());
	}
}