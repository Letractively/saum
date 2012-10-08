package br.com.meganet.util;

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import br.com.meganet.facade.AdministracaoFacade;

public class ConfigUtil {
	private static ConfigUtil instance = null;
	Properties properties = null;
	boolean mapaCarregado = false;
	private static Logger logger = new Logger(AdministracaoFacade.class);

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
	public String getProperty(String name) {
		return properties.getProperty(name);
	}
	
	public void setPropertie(Map<String, String> mapas) {
		properties.putAll(mapas);
		mapaCarregado = true;
	}
	
	public boolean getMapaCarregado(){
		return mapaCarregado;
	}

	/**
	 * Busca uma propriedade do tipo String
	 * @param name - nome da propriedade
	 * @param defaultValue - valor default da propriedade
	 * @return o valor da propriedade ou o valor default caso nao a encontre
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
	

	public static boolean ehAPorcariaDoIE(PageContext pageContext) {
		try {
			HttpServletRequest hReq = ((HttpServletRequest) pageContext.getRequest());
			String agent = hReq.getHeader("user-agent");
			if (agent.contains("MSIE")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean ehIE6(PageContext pageContext) {
		try {
			HttpServletRequest hReq = ((HttpServletRequest) pageContext.getRequest());
			String agent = hReq.getHeader("user-agent");
			Double versao = new Double("0");
			if (agent.contains("MSIE")) {
				int pos = agent.indexOf("MSIE ") + 5;
				String tmp = agent.substring(pos, (agent.indexOf(";", pos)));
				versao = new Double(tmp);
			} else if (agent.contains("Firefox")) {
				return false;
			} else if (agent.contains("Chrome")) {
				return false;
			}
			int ver = versao.intValue();
			switch (ver) {
			case 6:
				return true;
			case 7:
				return false;
			case 8:
				return false;
			default:
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean ehIE8(PageContext pageContext) {
		try {
			HttpServletRequest hReq = ((HttpServletRequest) pageContext.getRequest());
			String agent = hReq.getHeader("user-agent");
			Double versao = new Double("0");
			if (agent.contains("MSIE")) {
				int pos = agent.indexOf("MSIE ") + 5;
				String tmp = agent.substring(pos, (agent.indexOf(";", pos)));
				versao = new Double(tmp);
			} else if (agent.contains("Firefox")) {
				return false;
			} else if (agent.contains("Chrome")) {
				return false;
			}
			int ver = versao.intValue();
			switch (ver) {
			case 6:
				return false;
			case 7:
				return false;
			case 8:
				return true;
			default:
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean ehIE7(PageContext pageContext) {
		try {
			HttpServletRequest hReq = ((HttpServletRequest) pageContext.getRequest());
			String agent = hReq.getHeader("user-agent");
			Double versao = new Double("0");
			if (agent.contains("MSIE")) {
				int pos = agent.indexOf("MSIE ") + 5;
				String tmp = agent.substring(pos, (agent.indexOf(";", pos)));
				versao = new Double(tmp);
			} else if (agent.contains("Firefox")) {
				return false;
			} else if (agent.contains("Chrome")) {
				return false;
			}
			int ver = versao.intValue();
			switch (ver) {
			case 6:
				return false;
			case 7:
				return true;
			case 8:
				return false;
			default:
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public static String getTamanhoBrowser(PageContext pageContext) {
		HttpServletRequest hReq = ((HttpServletRequest) pageContext.getRequest());
		try {
			String agent = hReq.getHeader("user-agent");
			Double versao = new Double("0");
			// Internet explorer
			if (agent.contains("MSIE")) {
				int pos = agent.indexOf("MSIE ") + 5;
				String tmp = agent.substring(pos, (agent.indexOf(";", pos)));
				versao = new Double(tmp);
			} else if (agent.contains("Firefox")) {
				return "250";
			} else if (agent.contains("Chrome")) {
				return "250";
			}
			int ver = versao.intValue();
			switch (ver) {
			case 6:
				return "250";
			case 7:
				return "255";
			case 8:
				return "255";
			default:
				return "250";
			}
		} catch (Exception e) {
			return "250";
		}
	}

}