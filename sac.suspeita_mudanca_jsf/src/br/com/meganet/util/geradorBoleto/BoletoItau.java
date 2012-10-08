package br.com.meganet.util.geradorBoleto;

import java.util.Iterator;
import java.util.List;

import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;
import org.jboleto.control.HtmlGenerator;

public class BoletoItau {
	
	public String processaBoletoItau(JBoletoBean jBoletoBean) {
		try{
				HtmlGenerator generator = new HtmlGenerator();
				JBoleto jBoleto = new JBoleto(generator, jBoletoBean, 341);
				jBoletoBean.setEnderecoCodBar((new StringBuilder("boleto?cb=")).append(jBoletoBean.getCodigoBarras()).toString());
				jBoleto.addBoleto();
				String html = "<table width='640' cellspacing=5 cellpadding=0 border=0><tr>" +
						"<form name='forma'><td align=\"center\">" +
						"<input type=button value=' Imprimir Boleto' onClick='x86()' name='button'>" +
						"</td></form></tr></table>";
				return generator.toString() + html;
		}catch (Exception e) {
			return "<div style=\"text-align:center; font-color:#FF0000\">Não foi possível realizar a solicitação.</div>";
		}
	}
	
	public StringBuilder processaBoletoItau(List<JBoletoBean> jBoletoBean) {
		try{
			StringBuilder retorno = new StringBuilder();
			if(jBoletoBean != null){
				for (Iterator<JBoletoBean> iterator = jBoletoBean.iterator(); iterator.hasNext();) {
					JBoletoBean boletoBean = (JBoletoBean) iterator.next();
					if(boletoBean.getUrlF2B() != null){
						HtmlGenerator generator = new HtmlGenerator();
						JBoleto jBoleto = new JBoleto(generator, boletoBean, JBoleto.ITAU);
						boletoBean.setEnderecoCodBar((new StringBuilder("boleto?cb=")).append(boletoBean.getCodigoBarras()).toString());
						jBoleto.addBoleto();
						retorno.append(generator.toString());
					}else{
						retorno.append("" +
								"<hr style=\"margin-top: 5px; margin-bottom:5px\"/>" +
									"<div style=\"text-align:center; font-color:#FF0000\">" +
										"Boleto com vencimento em: <b>"+ boletoBean.getDataVencimento() +"</b><br/>" +
										"Valor: <b>" + boletoBean.getValorBoleto() +"</b><br/>" +
										"do cliente: <b>" + boletoBean.getNomeSacado() +"</b><br/>" +
										"ainda não foi enviado à F2B." +
									"</div>" +
								"<hr style=\"margin-top: 5px; margin-bottom:5px\"/><br/>");
					}
				}
			}
			return retorno;
		}catch (Exception e) {
			return new StringBuilder("<div style=\"text-align:center; font-color:#FF0000\">Não foi possível realizar a solicitação.</div>");
		}
	}
	

	
	public String processoStringF2B(JBoletoBean jBoletoBean) {
		try{
			if(jBoletoBean.getUrlF2B() != null){
				StringBuilder retorno = new StringBuilder();
				retorno.append("<script language=\"javascript\">");
				retorno.append("window.location = \""+ jBoletoBean.getUrlF2B() +"\";");
				retorno.append("</script>");
				retorno.trimToSize();
				return retorno.toString();
			}else{
				return "<div style=\"text-align:center; font-color:#FF0000\">Boleto ainda não foi processado.<br/>O processamento ocorre .</div>";
			}
		}catch (Exception e) {
			return "<div style=\"text-align:center; font-color:#FF0000\">Não foi possível realizar a solicitação.</div>";
		}
	}
	
	public StringBuilder processoStringF2B(List<JBoletoBean> jBoletoBean) {
		try{
			StringBuilder retorno = new StringBuilder();
			if(jBoletoBean != null && jBoletoBean.size() > 1){
				for (Iterator<JBoletoBean> iterator = jBoletoBean.iterator(); iterator.hasNext();) {
					JBoletoBean boletoBean = (JBoletoBean) iterator.next();
					if(boletoBean.getUrlF2B() != null){
						retorno.append("<div style=\"text-align:center;\"><span style=\"font-size:18px; color:#FF0000\">ATENÇÃO</span><br />");
						retorno.append("Boleto nº. <strong>"+ boletoBean.getNossoNumero() +"</strong>.<br/>Emitido para <b>"+ boletoBean.getNomeSacado() +"</b> não pode ser gerado pelo sistema.");
						retorno.append("<br/> utilize o link abaixo para gerar um novo boleto </p>");
						retorno.append("<a href=\""+boletoBean.getUrlF2B()+"\"> Imprimir 2º via do boleto</a></p>");
						retorno.append("</div><hr style=\"margin-top: 5px; margin-bottom:5px\"/>");
					}else{
						retorno.append("<hr style=\"margin-top: 5px; margin-bottom:5px\"/><div style=\"text-align:center; font-color:#FF0000\">Boleto com vencimento em: "+ boletoBean.getDataVencimento() +"<br/>" +
								"Valor: " + boletoBean.getValorTitulo() +"<br/>" +
								"Número: " + boletoBean.getDvNossoNumero() + "<br/>" +
								"ainda não foi enviado à F2B.</div><hr style=\"margin-top: 5px; margin-bottom:5px\"/><br/>");
					}
				}
			}
			return retorno;
		}catch (Exception e) {
			return new StringBuilder("<div style=\"text-align:center; font-color:#FF0000\">Não foi possível realizar a solicitação.</div>");
		}
	}
	
}