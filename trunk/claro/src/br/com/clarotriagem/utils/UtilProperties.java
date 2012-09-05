//<BEGIN TMS 35803> SMART 21027 - Fabrica Accenture- 01/10/2008
package br.com.clarotriagem.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * Abre o arquivo meganet.properties para retorno dos valores
 * Este arquivo recupera uma instancia, por isso não fazer uma classe
 * generica.
 * Caso retorne uma String:<br>
 * 
 * @author efren.junior
 * <br><b>Modo de Usar</b><br>
 * String retorno = AdiquirirPerfilTelevendas.getInstance().getProperty("propriedade");<br>
 * (Veja outros Metodos para mais possibiliades)
 * 
 */

public class UtilProperties {
	private static UtilProperties instance = null;
	Properties properties = null;

	
	/**
	 * Construtor com o caminho do arquivo e nome.
	 * @param caminhoArquivo - caminho
	 */
	private UtilProperties() {
		properties = new Properties();
		try {
			InputStream inputStream =
				this.getClass().getResourceAsStream("/claro.properties");
			if (inputStream != null) {
				properties.load(inputStream);
				inputStream.close();
			} else {
				System.out.println("Arquivo de propriedades não encontrado");
			}
		} catch (IOException exc) {
			System.out.println("Erro ao ler arquivo de propriedades: " + exc.getMessage());
		}
	}

	
	/**
	 * Busca uma propriedade do tipo String
	 * @param name - nome da propriedade
	 * @return valor da propriedade ou null caso nao a encontre
	 */
	public String getProperty(String name) {
		return properties.getProperty(name);
	}

	
	/**
	 * Busca uma propriedade do tipo String
	 * @param name - nome da propriedade
	 * @param defaultValue - valor default da propriedade
	 * @return o valor da propriedade ou o valor default caso nao a encontre
	 */
	public String getProperty(String name, String defaultValue) {
		return properties.getProperty(name, defaultValue);
	}
	
	
	/**
	 * Busca uma propriedade do tipo inteira
	 * @param name - nome da propriedade
	 * @param defaultValue - valor default da propriedade
	 * @return o valor da propriedade ou o valor default caso nao a encontre
	 */
	public long getLongProperty(String name, long defaultValue) {
		long value = defaultValue;
		try {
			String strValue = properties.getProperty(name);
			if (strValue != null) {
				value = Long.valueOf(strValue).intValue();
			}
		} catch (java.lang.NumberFormatException exc) {
			System.out.println("Error ao buscar propriedade " + name +": " + exc.getMessage());
		} catch (Exception exc) {
			System.out.println("Error ao buscar propriedade " + name +": " + exc.getMessage());
		}
		return value;
	}
	
	
	/**
	 * Busca uma propriedade do tipo boolean, ou seja 
	 * com valor true ou false (ignorando maiusculo/minusculo)
	 * @param name - nome da propriedade
	 * @param defaultValue - valor default
	 * @return o valor da propriedade ou o valor default caso nao a encontre
	 */
	public boolean getBooleanProperty(String name, boolean defaultValue) {
		boolean value = defaultValue;
		try {
			String strValue = properties.getProperty(name);
			if (strValue != null) {
				value = Boolean.valueOf(strValue).booleanValue();
			}
		} catch (Exception exc) {
			System.out.println("Error ao buscar propriedade " + name +": " + exc.getMessage());
		}
		return value;
	}
	
	
	/**
	 * Busca uma propriedade do tipo boolean, ou seja 
	 * com valor true ou false (ignorando maiusculo/minusculo)
	 * @param name - nome da propriedade
	 * @param defaultValue - valor default
	 * @return o valor da propriedade ou o valor default caso nao a encontre
	 */
	public int getIntProperty(String name) {
		int value = 0;
		try {
			String strValue = properties.getProperty(name);
			if (strValue != null) {
				value = Integer.parseInt(strValue);
			}
		} catch (Exception exc) {
			System.out.println("Error ao buscar propriedade " + name +": " + exc.getMessage());
		}
		return value;
	}
	
	
	
	
	/**
	 * @return a instancia estatica de UtilProperties
	 */
	public static UtilProperties getInstance() {
		if (instance == null) {
			instance = new UtilProperties();
		}
		return instance;
	}
}