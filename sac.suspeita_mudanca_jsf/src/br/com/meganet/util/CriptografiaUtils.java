package br.com.meganet.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class CriptografiaUtils {
	
	public static final String codificarECS(String str) throws Exception {
		if (str == null || str.trim().length() == 0) {
			throw new Exception("O parametro informado nao pode ser nulo ou vazio.");
		}

		String result = "";
		int dia = 25;
		int iasc;
		String tmpChar;
		byte[] chars = str.getBytes();

		for (int i = 0; i < chars.length; i++) {
			iasc = chars[i] + dia;

			//
			if (iasc > 255) {
				iasc -= 255;
			}

			//    
			tmpChar = Integer.toHexString(iasc);

			if (tmpChar.length() < 2) {
				tmpChar = "0" + tmpChar;
			}

			result = tmpChar + result;
		}

		return result.toUpperCase();
	}

	public static final String decodificarECS(String str) throws Exception {
		if (str == null || str.trim().length() == 0) {
			throw new Exception("O parametro informado nao pode ser nulo ou vazio.");
		}
        
        String result = "";
		int dia = 25;

		for (int i = 0; i < str.length(); i += 2) {
            String tmp = str.substring(i, i + 2);
            //System.out.println((char)(Integer.parseInt(tmp, 16) - dia));
            
            int iasc = Integer.parseInt(tmp, 16) - dia;
            if (iasc < 0){
                iasc += 255;
            }
            
            result = (char)(iasc) + result;
		}

		return result;
	}
	
	public static final String getSenha(String str)throws Exception {
		
			String strDecodificada = decodificarECS(str);
			return strDecodificada.substring(0,4);
			
	}
	
	public static final String getCodigo(String str)throws Exception {
		
			String strDecodificada = decodificarECS(str);
			return strDecodificada.substring(4,15);
			
	}
	
	public static final String geraCadeia(int qtd){
		try {
			String result = "";
			for(int i = 0; i <= qtd; i++){
				int contSeg = 0;
				while(contSeg < 1500){
				contSeg++;
				Double in = Math.random() * 200;
				int rand = in.intValue();
					if((rand >= 97 && rand <= 122) || (rand >= 65 && rand <= 90) || (rand >= 48 && rand <= 57)
							|| (rand == 45) && (rand == 61) || (rand == 63) || (rand == 46) || (rand == 38) 
							|| (rand == 43) || (rand >= 192 && rand <= 195)){
						result += (char) rand;
						break;
					}
				}
			}
			
			return result;
			
		} catch (Exception e) {
			System.out.println(e);
			return "";
		}
	}

	public static String hashMD5(final String value) {
		try {
			BigInteger sen = new BigInteger(1, MessageDigest.getInstance("MD5").digest(value.getBytes()));
			return sen.toString();
		} catch (NoSuchAlgorithmException exception) {
			throw new RuntimeException(exception);
		}
	}
}
