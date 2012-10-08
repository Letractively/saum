package br.com.meganet.util;

import java.text.DecimalFormat;

public class UtilNumero {
	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static boolean validaCPF(String cpf) {
		if ((cpf == null) || (cpf.length() != 11))
			return false;

		Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
	}

	public static boolean validaCNPJ(String cnpj) {
		if ((cnpj == null) || (cnpj.length() != 14))
			return false;

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}
	public static final String formataNumeroCasaDecimal(String numero){
		Double d = new Double(numero);
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(d).replace(",", ".");
	}

	public static final String aplicaMascaraMoeda(String numero){
		Double d = new Double(numero);
		DecimalFormat df = new DecimalFormat("R$ ###,###,###,###,##0.00");
		return df.format(d);
	}
	
	public static final String formataNumeroCasaDecimal(Double numero){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(numero).replace(",", ".");
	}
	
	public static final String formataNumeroCasaDecimal(Long numero){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(numero).replace(",", ".");
	}
	
	public static final Double formataNumero(String numero){
		Double d = new Double(numero);
		DecimalFormat df = new DecimalFormat("0.00");
		return new Double(df.format(d).replace(",", "."));
	}
	
	public static final Double formataNumero(Double numero){
		DecimalFormat df = new DecimalFormat("0.00");
		return new Double(df.format(numero).replace(",", "."));
	}
	
	public static final Double formataNumero(Long numero){
		DecimalFormat df = new DecimalFormat("0.00");
		return new Double(df.format(numero).replace(",", "."));
	}

	public static String formataNumeroCasaDecimalSemprePositivo(Double numero) {
		if(numero < 0){
			return "0.00";
		}else{
			DecimalFormat df = new DecimalFormat("0.00");
			return df.format(numero).replace(",", ".");
		}
	}
	
}
