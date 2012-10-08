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

import java.util.Vector;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;
import org.jboleto.control.Generator;
import org.jboleto.control.PDFGenerator;

/**
 * @author Fabio Souza
 */
class ExemploBradesco {
        
	public static void main(String args[]) {
        
		JBoletoBean jBoletoBean = new JBoletoBean();
		
		jBoletoBean.setDataDocumento("17/09/2010");
        jBoletoBean.setDataProcessamento("18/05/2010");      
            
        jBoletoBean.setCedente("AINODE Solucoes");  
        jBoletoBean.setCarteira("09");

        jBoletoBean.setNomeSacado("GtTurbo");
        jBoletoBean.setEnderecoSacado("Rua Araticum 951");        
        jBoletoBean.setBairroSacado("Anil");
        jBoletoBean.setCidadeSacado("Rio de Janeiro");
        jBoletoBean.setUfSacado("RJ");
        jBoletoBean.setCepSacado("22753-501");
        jBoletoBean.setCpfSacado("87524988753");
        
        jBoletoBean.setLocalPagamento("ATE O VENCIMENTO, PREFERENCIALMENTE NO BRADESCO");
        jBoletoBean.setLocalPagamento2("APOS O VENCIMENTO, SOMENTE NO BRADESCO");        
        
        Vector<String> descricoes = new Vector<String>();
        descricoes.add("Hospedagem I - teste descricao1 - R$ 39,90");
        descricoes.add("Manutencao - teste ricao2 - R$ 32,90");
        descricoes.add("Sistema - teste ssssde descricao3 - R$ 45,90");
        descricoes.add("Extra - teste de descricao4 - R$ 78,90");
        jBoletoBean.setDescricoes(descricoes);
        
        jBoletoBean.setDataVencimento("17/09/2010");
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        jBoletoBean.setAgencia("1228");
        jBoletoBean.setDvAgencia("9");
        
        jBoletoBean.setContaCorrente("0069900");
        jBoletoBean.setDvContaCorrente("4");
        
        jBoletoBean.setNossoNumero("00000088598",11);        
        jBoletoBean.setValorBoleto("700.00");
        
        Generator generator = new PDFGenerator(jBoletoBean, JBoleto.BRADESCO);
        
        JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.BRADESCO);
        
        jBoleto.addBoleto();
        
        jBoleto.closeBoleto("bradesco.pdf");	                    
	}
}
