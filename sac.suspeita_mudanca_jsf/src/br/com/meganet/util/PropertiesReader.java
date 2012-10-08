
package br.com.meganet.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * Manipulacao dos logging.properties
 * 
 * @version 1.0
 *
 */
public final class PropertiesReader {
	

	private static HashMap<String, Properties> resources;
	private static PropertiesReader instancia;
	private static String path;

	/**
	 * Construtor no args
	 * Inicializa a Hash.
	 */
	private PropertiesReader() {
		resources = new HashMap<String, Properties>();
	}

	/**
	 * Metodo usa o padrao Singleton
	 * @return A instancia unica da classe
	 */
	public static PropertiesReader getInstance() {
		if (instancia == null) {
			instancia = new PropertiesReader();
			path = "properties";
		}
		return instancia;
	}

	/**
	 * Metodo usa o padrao Singleton
	 * @return A instancia unica da classe
	 */
	public static PropertiesReader getInstance(String caminho) {
		if (instancia == null) {
			instancia = new PropertiesReader();
			path = caminho;
		}
		
		return instancia;
	}

	/**
	 * Metodo que recupera o valor de um parametro
	 * de um arquivo .properties
	 * Recebe o nome do arquivo e o nome da propriedade.
	 * Por motivo de organizacao, atualmente obriga-se que
	 * seja criada uma pasta "properties" na raiz de "src"
	 * onde deverao ser jogados os .properties.
	 * @param arquivo Nome do Arquivo
	 * @param parametro Nome do parametro
	 * @return Valor do parametro no arquivo
	 */
	public String getProperty(String arquivo, String parametro) {
		String retorno = null;
		
		try {
			Properties prop = getResouceBundle(arquivo);
			retorno = (String) prop.getProperty(parametro);
			if (retorno == null){
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/**
	 * Equivalente ao Metodo getProperty(String arquivo, String parametro)
	 * Recebe apenas o nome do parametro, e considera o aquivo .properties
	 * default como sendo arqParam.properties. 
	 * Usa o getProperty(String arquivo, String parametro).
	 * @param parametro Nome do parametro a ser recuperado.
	 * @return Valor do parametro.
	 */
	public String getProperty(String parametro) {
		String retorno = null;
		try {
			retorno = getProperty("arqParam", parametro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/**
	 * Metodo que recupera o PropertyResourceBundle
	 * que sera usado pelos metodos de recuperar o valor do parametro.
	 * @param arquivo Nome do Arquivo
	 * @return PropertyResourceBundle
	 * @throws Exception Erro Inesperado
	 */
	private Properties getResouceBundle(String arquivo) throws Exception {
		Properties prop = null;
		Object obj = resources.get(arquivo);
		if (obj != null) {
			prop = (Properties) obj;
		} else {
			File file = new File(
					path + File.separator + arquivo + ".properties");
			InputStream is = new FileInputStream(file);
			prop = new Properties();
			prop.load(is);
			is.close();
			resources.put(arquivo, prop);
		}
		return prop; 
	}
	
	/**
	 * Retorna o arquivo de properties. Este Metodo foi criado para 
	 * a visualizacao dos valores dos atributos no properties a partir
	 * do jsp de gerenciador de property.
	 * @param arquivo Nome do Arquivo a ser retornado
	 * @return Properties.
	 * @throws Exception Excecao generica.
	 */
	public Properties getArquivoProperty(String arquivo) throws Exception {
		Properties prop = getResouceBundle(arquivo);
		
		return prop;
	}
	
	/**
	 * Retorna o arquivo de properties. Este Metodo foi criado para 
	 * a visualizacao dos valores dos atributos no properties a partir
	 * do jsp de gerenciador de property.
	 * @return Properties.
	 * @throws Exception Excecao generica.
	 */
	public Properties getArquivoProperty() throws Exception {
		Properties prop = getResouceBundle("arqParam");
	
		return prop;
	}	

	/**
	 * Metodo que recarrega o arquivo especificado
	 * @param arquivo Nome do Arquivo
	 */
	public static void reloadArquivo(String arquivo) {
		resources.remove(arquivo);
	}
	
}