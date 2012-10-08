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

package org.jboleto.exemplos;

import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;
import org.jboleto.control.Generator;
import org.jboleto.control.PDFGenerator;

/**
 * @author Fabio Souza
 */
class ExemploItau {
        
	public static void main(String args[]) {
		JBoletoBean jBoletoBean = new JBoletoBean();
		
		
		jBoletoBean.setDataDocumento("31/01/2009");
        jBoletoBean.setDataProcessamento("31/01/2009");      
            
        jBoletoBean.setCedente("Meganet");  
        jBoletoBean.setCarteira("109");

        jBoletoBean.setNomeSacado("Teste");
        jBoletoBean.setEnderecoSacado("Rua Araticum 951");        
        jBoletoBean.setBairroSacado("Anil");
        jBoletoBean.setCidadeSacado("Rio de Janeiro");
        jBoletoBean.setUfSacado("RJ");
        jBoletoBean.setCepSacado("73370-074");
        jBoletoBean.setCpfSacado("000095449566115");
        
		jBoletoBean.setLocalPagamento("ATE O VENCIMENTO, PREFERENCIALMENTE NO Itaú");
		jBoletoBean.setLocalPagamento2("APOS O VENCIMENTO, SOMENTE NO BRADESCO");
        jBoletoBean.setDataVencimento("10/10/2008");
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        jBoletoBean.setAgencia("0654");
        jBoletoBean.setContaCorrente("54785");
        jBoletoBean.setDvContaCorrente("0");
        
        jBoletoBean.setNossoNumero("6578603",8);
        jBoletoBean.setNoDocumento("421");
        jBoletoBean.setValorBoleto("97.10");                
           
        Generator generator = new PDFGenerator(jBoletoBean, JBoleto.ITAU);
        
        JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.ITAU);
        
        jBoleto.addBoleto();
        jBoleto.closeBoleto("itau.pdf");	        
            
	}
}
