
package br.com.meganet.util;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.Provider;
import java.security.Security;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class Criptografia {

	public Criptografia() {
	}

	public static String encrypt(String s, String s1) {
		byte abyte1[];
		Key key = getKey(s1);
		try {
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(1, key);
			byte abyte0[] = s.getBytes();
			abyte1 = cipher.doFinal(abyte0);
			return getString(abyte1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String generateKey() {
		byte abyte0[];
		try {
			KeyGenerator keygenerator = KeyGenerator.getInstance("DESede");
			SecretKey secretkey = keygenerator.generateKey();
			abyte0 = secretkey.getEncoded();
			String tmp = new String(abyte0);
			byte ddd[] = tmp.getBytes(Charset.forName("UTF-8"));
			
			return new String(ddd);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decrypt(String cryptedText, String keyText) {
		byte abyte1[];
		try {
			Key key = getKey(keyText);
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			byte abyte0[] = isEncrypted(cryptedText) ? getBytesDecript(cryptedText)
					: getBytesDecript(getString(hex2ByteArray(cryptedText)));
			cipher.init(2, key);
			abyte1 = cipher.doFinal(abyte0);
			return new String(abyte1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Key getKey(String s) {
		SecretKey secretkey;
		try {
			byte abyte0[] = s.getBytes();
			DESedeKeySpec desedekeyspec = new DESedeKeySpec(abyte0);
			SecretKeyFactory secretkeyfactory = SecretKeyFactory.getInstance("DESede");
			secretkey = secretkeyfactory.generateSecret(desedekeyspec);
			return secretkey;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isEncrypted(String s) {
		if (s.indexOf('-') == -1)
			return false;
		for (StringTokenizer stringtokenizer = new StringTokenizer(s, "-",
				false); stringtokenizer.hasMoreTokens();) {
			String s1 = stringtokenizer.nextToken();
			if (s1.length() > 3)
				return false;
			int i = 0;
			while (i < s1.length()) {
				if (!Character.isDigit(s1.charAt(i)))
					return false;
				i++;
			}
		}

		return true;
	}

	protected String getString2(byte[] b) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			sb.append(b[i]);

		return sb.toString();

	}

	private static String getString(byte abyte0[]) {
		StringBuffer stringbuffer = new StringBuffer();
		for (int i = 0; i < abyte0.length; i++) {
			byte byte0 = abyte0[i];
			stringbuffer.append(0xff & byte0);
			if (i + 1 < abyte0.length)
				stringbuffer.append("-");
		}
		return stringbuffer.toString();
	}

	private static byte[] getBytesDecript(String s) {
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		int i;
		for (StringTokenizer stringtokenizer = new StringTokenizer(s, "-",
				false); stringtokenizer.hasMoreTokens(); bytearrayoutputstream
				.write((byte) i))
			i = Integer.parseInt(stringtokenizer.nextToken());
		return bytearrayoutputstream.toByteArray();
	}

	protected static byte[] getBytes(String s) {
		return s.getBytes();
	}

	public static void main(String args[]) {
		final String key = "sP81h6rB1wr2qoieqwrl5zW3";

		//String senha = "PVC_USER01";
		String senha = "/WEB-INF";
		System.out.println("Chave............: " + key);
		System.out.println("Senha............: " + senha);
		String senhaCriptografada = Criptografia.encrypt(senha, key);
		System.out.println("Encripted Text............: " + senhaCriptografada);
		System.out.println("Decripted text (direto)...: " + Criptografia.decrypt(senhaCriptografada, key));

	}

	public static void showProviders() {
		try {
			Provider aprovider[] = Security.getProviders();
			for (int i = 0; i < aprovider.length; i++) {
				System.out.println("Provider: " + aprovider[i].getName() + ", "
						+ aprovider[i].getInfo());
				String s;
				String s1;
				for (Iterator<?> iterator = aprovider[i].keySet().iterator(); iterator
						.hasNext(); System.out.println("\t" + s + " = " + s1)) {
					s = (String) iterator.next();
					s1 = (String) aprovider[i].get(s);
				}

			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static byte[] hex2ByteArray(String key) {
		byte[] bts = new java.math.BigInteger(key, 16).toByteArray();
		byte[] r = new byte[bts.length - 1];
		System.arraycopy(bts, 1, r, 0, r.length);
		return r;
	}

	public static String byteArray2Hex(byte[] bytes) {

		char[] HEX = "0123456789abcdef".toCharArray();

		char[] ret = new char[bytes.length * 2];
		for (int i = 0, j = 0; i < bytes.length; ++i) {
			ret[j++] = HEX[(bytes[i] >> 4) & 0x0F];
			ret[j++] = HEX[bytes[i] & 0x0F];
		}
		return String.valueOf(ret);
	}
}
