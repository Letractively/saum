package br.com.meganet.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;

import br.com.meganet.exception.GAPBDException;




public class UrlConnection {

	private final static Logger logger = Logger.getLogger(UrlConnection.class);

	public String nomeClasse() {
		return this.getClass().getName();
	}

	public static String getXML(String sURL) throws GAPBDException{

		String result = "";

   try{

			// Vou alterar a URL, mudando de https para http.
			
			
		    TrustManager[] trustAllCerts = new TrustManager[]{
		        new X509TrustManager() {
		            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		                return null;
		            }
		            public void checkClientTrusted(
		                java.security.cert.X509Certificate[] certs, String authType) {
		            }
		            public void checkServerTrusted(
		                java.security.cert.X509Certificate[] certs, String authType) {
		            }
		        }
		    };
		    
		    try {
		        SSLContext sc = SSLContext.getInstance("SSL");
		        sc.init(null, trustAllCerts, new java.security.SecureRandom());
		        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		    } catch (Exception e) {
		    	System.out.print(e);
		    }
		 
			int iPos = sURL.indexOf("https");

			if (iPos > 0) {
				sURL = sURL.substring(0, iPos)
						+ sURL.substring(iPos + 5, sURL.length());
			}		


			logger.debug("getXML() - URL: " + sURL);

			URL url = new URL(sURL);

			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			// urlConnection.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded");
           
            
			
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream(), Charset.forName("ISO-8859-1")));
			

			String inputLine;
			StringBuffer sb = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
			}

			in.close();

			urlConnection.disconnect();

			result = sb.toString();

		} catch (Exception e) {
			logger.warn("getXML()", e);
			throw new GAPBDException("Erro ao buscar xml EBPP : " + e.getMessage());
		}

		int iPos = result.indexOf("https");
		if (iPos > 0) {
			result = result.substring(0, iPos) + "http"
					+ result.substring(iPos + 5, result.length());
		}

		logger.debug("getXML() - head of result: "
				+ result.substring(0, Math.min(60, result.length())));

		return result;
	}

   
	/**
	 * 
	 * @param sURL
	 * @param out 
	 * @return
	 * @throws GPABDException
	 */
	public static InputStream getPDF(String sURL, OutputStream out)
			throws GAPBDException {
		InputStream retorno = null;
		try {
			URL url = new URL(sURL);

			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");

			retorno = urlConnection.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(retorno);
			int i = 0;
			while ((i = bis.read()) > -1) {
				out.write(i);
			}
			out.flush();
			out.close();
			bis.close();

			urlConnection.disconnect();
		} catch (Exception e) {
			logger.warn("getXML()", e);
			throw new GAPBDException("Erro ao buscar xml EBPP : "
					+ e.getMessage());
		}
		return retorno;

	}
	
}
