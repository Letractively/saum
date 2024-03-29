/*
 * Esta biblioteca e um software livre, que pode ser redistribuido e/ou
 * modificado sob os termos da Licenca Publica Geral Reduzida GNU,
 * conforme publicada pela Free Software Foundation, versao 2.1 da licenca.
 *
 * Esta biblioteca e distribuida na experanca de ser util aos seus usuarios,
 * porem NAO TEM NENHUMA GARANTIA, EXPLICITAS OU IMPLICITAS, COMERCIAIS OU
 * DE ATENDIMENTO A UMA DETERMINADA FINALIDADE.
 * Veja a Licenca Publica Geral Reduzida GNU para maiores detalhes.
 * A licenca se encontra no arquivo lgpl-br.txt
 */

package org.jboleto.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jbarcodebean.Interleaved25;
import jbarcodebean.JBarcodeBean;

import org.jboleto.Banco;
import org.jboleto.JBoletoBean;

public class HtmlGenerator implements Generator {
	
    private StringBuilder sb = new StringBuilder();
    
    @SuppressWarnings("unused")
	private JBoletoBean jBoletoBean;
    @SuppressWarnings("unused")
	private Banco banco;
    
	public HtmlGenerator() {
	}
	
	public void addBoleto(JBoletoBean boleto, Banco banco) {
        
		try {
            this.jBoletoBean = boleto;
            this.banco = banco;
            
			URL url = getClass().getResource("/img/jboleto.html");
			InputStreamReader inReader=  new InputStreamReader(url.openStream());

			BufferedReader read = new BufferedReader(inReader);
			String linha = null;

			while ((linha = read.readLine()) != null) {
				String valor = "";
				Pattern p = Pattern.compile("&[a-zA-Z]+");
				Matcher m = p.matcher(linha);
                
				if (m.find()) {
					valor = selecionaDado(m.group(), boleto, banco);
					linha = linha.replace(m.group(), valor);
				}
				sb.append(linha + "\n");
			}			
		} 
        catch (FileNotFoundException e) {
            
			e.printStackTrace();
		} 
        catch (IOException e) {
            
			e.printStackTrace();
		}		
	}
	
	/**
	 * Pegar valor referente ao campo do boleto 
	 * 
	 * @param dados
	 * @param boleto
	 * @param banco
	 * @return String
	 */
	private String selecionaDado(String dados, JBoletoBean boleto, Banco banco){
        
		String retorno = "";
        
		if ("&nome".equals(dados)) {
			retorno = boleto.getNomeSacado();
		}

		if ("&titulo_boleto_html".equals(dados)) {
			retorno = boleto.getTituloBoletoHtml();
		}
        
		if ("&obsCred".equals(dados)) {
			retorno = boleto.getObservacaoBoleto();
		}
		
		if ("&imagemBanco".equals(dados)) {
			retorno = "img/" + banco.getNumero()+".gif";
		}
        
		if ("&codBanco".equals(dados)) {
			retorno = banco.getNumero();
		}
        
		if ("&dtVencimento".equals(dados) ){
			retorno = boleto.getDataVencimento();
		}
        
		if ("&valor".equals(dados)) {
			retorno = boleto.getValorBoleto();
		}
        
		if ("&localPgto".equals(dados)) {
			 StringBuffer sBuffer = new StringBuffer();
			 sBuffer.append(boleto.getLocalPagamento());
			 sBuffer.append("<br>"+boleto.getLocalPagamento2());
			 retorno = sBuffer.toString();
		}
        
		if ("&codBarra".equals(dados)) {
            
			retorno = banco.getLinhaDigitavel();
		}	
        
		if ("&agenciaConta".equals(dados)) {
            
			retorno = banco.getAgenciaCodCedenteFormatted();
		}	
        
		if ("&nossoNumero".equals(dados)) {
			retorno = boleto.getNossoNumero();
		}	
        
		if ("&cedente".equals(dados)) {
			 return boleto.getCedente();
		}	
        
		if ("&dtDoc".equals(dados)) {
			retorno = boleto.getDataDocumento();
		}	
        
		if ("&numeroDoc".equals(dados)) {
			retorno = boleto.getNoDocumento();
		}	
        
		if ("&especie".equals(dados)) {
			retorno = boleto.getEspecieDocumento();
		}	
        
		if ("&aceite".equals(dados)) {
			retorno = boleto.getAceite();
		}	
        
		if ("&dtProcesso".equals(dados)) {
            return boleto.getDataProcessamento();
		}	
        
		if ("&carteira".equals(dados)) {
			retorno = boleto.getCarteira();
		}	
        
		if ("&moeda".equals(dados)) {
			retorno = boleto.getMoeda();
			retorno = retorno.toString().replace("$", "\\$");//-- Necessario senao da erro no replaceALL
		}	
		
		if ("&instrucoes".equals(dados)){
			 StringBuffer sBufferInst = new StringBuffer();
			 sBufferInst.append(boleto.getInstrucao1());
			 sBufferInst.append("<br>"+boleto.getInstrucao2());
			 sBufferInst.append("<br>"+boleto.getInstrucao3());
			 sBufferInst.append("<br>"+boleto.getInstrucao4());
			 retorno = sBufferInst.toString().replace("$", "\\$");//-- Necessario senão da erro no replaceALL
		}
        
		if ("&carteiraNossoNumero".equals(dados)) {
			retorno = boleto.getCarteira()+"/"+boleto.getNossoNumero();
		}
		
		if ("&sacado".equals(dados)) {
			StringBuffer sBufferSacado = new StringBuffer();
			sBufferSacado.append(boleto.getNomeSacado()+" "+ boleto.getCpfSacado());
			sBufferSacado.append("<br>"+boleto.getEnderecoSacado());
			sBufferSacado.append("<br>"+boleto.getCepSacado()+" "+boleto.getBairroSacado());
			sBufferSacado.append(" - "+boleto.getCidadeSacado()+" "+boleto.getUfSacado());
			retorno = sBufferSacado.toString();
		}
		
		if ("&cpfsacado".equals(dados)) {
			retorno = boleto.getCpfSacado();
		}
		
		
		if ("&imgCodBar".equals(dados)) {
            retorno = boleto.getEnderecoCodBar();   		
        }
		
		if (retorno==null || "".equals(retorno)) {
			retorno = dados;
		}
		
		return retorno;
	}
	
	/**
	 * Gera o arquivo do codigo de barra
	 * uso o JBarCodeBean.jar
	 */
	public void geraCodBar(OutputStream fos, String codBarras) {

	     try {
             
	    	 JBarcodeBean bb = new JBarcodeBean();
	    	 bb.setCodeType(new Interleaved25()); 

	    	 bb.setShowText(false);
	    	 bb.setCode(codBarras);

	    	 bb.gifEncode(fos);
	    	 fos.close();
             
		} 
         catch (Exception e) {
			e.printStackTrace();
		}
	}    
    
    /**
     * Neste caso não foi preciso fazer esta rotina, pois como a saida
     * e em html o codigo foi jogado para o metodo toString()
     * 
     * @param caminho Aqui seria o caminho para onde o arquivo deveria ser salvo
     */
    public void closeBoleto(String caminho) {
        System.out.println("Neste caso não foi preciso fazer esta rotina");;
    }

    @Override
    public String toString() {
        
        return sb.toString();
    }
	
    
}
