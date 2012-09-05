package br.com.clarotriagem.utils.singleton.mapas;

import java.util.Map;
import java.util.Properties;

import br.com.clarotriagem.utils.Logger;


public class ConfigUtil {
	private static ConfigUtil instance = null;
	Properties properties = null;
	boolean mapaCarregado = false;
	private static Logger logger = new Logger(ConfigUtil.class);

	/**
	 * Construtor default
	 */
	private ConfigUtil() {
		properties = new Properties();
	}

	/**
	 * Busca uma propriedade do tipo String
	 * @param name - nome da propriedade
	 * @return valor da propriedade ou null caso nao a encontre
	 */
	public String getProperty(String name, String defaultValue) {
		String ret = properties.getProperty(name, defaultValue);
		if(ret == null || "null".equalsIgnoreCase(ret) || "".equalsIgnoreCase(ret)){
			ret = defaultValue;
		}
		return ret;
	}
	
	public int getIntProperty(String name, String defaultValue) {
		int value = 0;
		try {
			String strValue = properties.getProperty(name, defaultValue);
			if (strValue != null) {
				value = Integer.parseInt(strValue);
			}else{
				if("null".equalsIgnoreCase(strValue) || "".equalsIgnoreCase(strValue)){
					value = new Integer(defaultValue);
				}
			}
		} catch (Exception exc) {
			System.out.println("Error ao buscar propriedade " + name +": " + exc.getMessage());
		}
		return value;
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
			}else{
				if("null".equalsIgnoreCase(strValue) || "".equalsIgnoreCase(strValue)){
					value = defaultValue;
				}
			}
		} catch (java.lang.NumberFormatException exc) {
			logger.erro(
				"Error ao buscar propriedade " + name +": " + exc.getMessage());
		} catch (Exception exc) {
			logger.erro(
				"Error ao buscar propriedade " + name +": " + exc.getMessage());
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
			logger.erro("Error ao buscar propriedade " + name +": " + exc.getMessage());
		}
		return value;
	}

	
	/**
	 * @return a instancia estatica de ConfigUtil
	 */
	public static ConfigUtil getInstance() {
		if(instance == null){
			instance = new ConfigUtil();
		}
		return instance;
	}

	public void setMapas(Map<String, String> mapas) {
		properties.putAll(mapas);
		mapaCarregado = true;
	}

}