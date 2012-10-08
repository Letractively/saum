package br.com.meganet.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Classe Taglib que mascara os dados em EL de CNPJ e CPF.
 * 
 * Essa classe existe no intuito de podermos 
 * formatar CPF's e CNPJ's quando o tipo primitivo 
 * destes atributos em seus beans forem numerais.
 * 
 * Motivacao: As operacoes de format da taglib BeanUtils 
 * e a JSTL Format não surtiram efeito sobre atributos numericos dos TO`s.
 * 
 */
public class MascaraTag extends TagSupport {
	private static final long serialVersionUID = -5584185567421825796L;
	private String cnpjEL;
	private String cpfEL;
	private String telefoneEL;

	/**
	 * Configura um cpf no atributo cpf desta classe.
	 * @param cpf
	 */
	public void setCpf(String cpf) {
		this.cpfEL = cpf;
	}
	
	/**
	 * Configura um cnpj no atributo cnpj desta classe.
	 * @param cnpj
	 */
	public void setCnpj(String cnpj) {
		this.cnpjEL = cnpj;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			if(this.cnpjEL!=null && !this.cnpjEL.equals("")){
				pageContext.getOut().println(formataCNPJ(this.cnpjEL));
			}
			if(this.cpfEL!=null && !this.cpfEL.equals("")){
				pageContext.getOut().println(formataCPF(this.cpfEL));
			}
			if(this.telefoneEL!=null && !this.telefoneEL.equals("")){
				pageContext.getOut().println(formataTelefone(this.telefoneEL));
			}
			
		} catch (IOException ioex) {
			throw new JspException( ioex );
		}
		 return Tag.SKIP_BODY;
	}
	
	/**
	 * Formata um cpf com a mascara 999.999.999-99
	 * @param cpfEL
	 * @return cpfEL String
	 */
	public static String formataCPF(String cpfEL) {
		if(cpfEL != null && !cpfEL.equals("")){
			cpfEL = "&nbsp;"+ cpfEL.substring(0,3) + "." +
			cpfEL.substring(3,6) + "." +
			cpfEL.substring(6,9) + "-" +
			cpfEL.substring(9,11);
		}
		return cpfEL;
	}

	/**
	 * Formata um cnpj com a mascara 99.999.999/9999-99
	 * @param cnpjEL
	 * @return cnpjEL String
	 */
	public static String formataCNPJ(String cnpjEL) {
		if(cnpjEL != null && !cnpjEL.equals("")){
			cnpjEL = cnpjEL.substring(0,2) + "." +
				cnpjEL.substring(2,5) + "." +
					cnpjEL.substring(5,8) + "/" +
						cnpjEL.substring(8,12) + "-" +
							cnpjEL.substring(12,14);
		}
		return cnpjEL;
	}
	
	/**
	 * Formata um telefone com a mascara 9999-9999.
	 * @param telefoneEL
	 * @return telefoneEL Stirng
	 */
	public static String formataTelefone(String telefoneEL) {
		/*if(telefoneEL != null && !telefoneEL.equals("")){
			telefoneEL = telefoneEL.substring(0,4) + "-" +
			telefoneEL.substring(4,8);
		}*/
	
		if(telefoneEL != null && !telefoneEL.equals("") && !(telefoneEL.length() < 8)){
			telefoneEL = telefoneEL.substring(0,4) + "-" +
			telefoneEL.substring(4,8);
		}
	
		return telefoneEL;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}
	
	
	/**
	 * Configura um numero de telefone 
	 * no atributo telefoneEL desta classe.
	 * @param telefoneEL
	 */
	public void setTelefone(String telefoneEL) {
		this.telefoneEL = telefoneEL;
	}
	
}
