package br.com.meganet.util.geradorBoleto;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;
import org.jboleto.control.HtmlGenerator;

import br.com.meganet.hbm.DAO.InfBoletoDAO;
import br.com.meganet.hbm.vo.InfBoleto;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.EnumBancos;

public class GeraHTMLBoletoFacade {
	private InfBoletoDAO infBoletoDAO;
	public void setInfBoletoDAO(InfBoletoDAO infBoletoDAO) {
		this.infBoletoDAO = infBoletoDAO;
	}
	
	public String processaBoleto(List<JBoletoBean> jBoletoBean) {
		try {
			InfBoleto infBol = infBoletoDAO.findById(Constantes.ID_INF_BOLETO);
			String retorno = "";
			int loop = 0;
			if (jBoletoBean != null) {
				for (Iterator<JBoletoBean> iterator = jBoletoBean.iterator(); iterator.hasNext();) {
					JBoletoBean boletoBean = (JBoletoBean) iterator.next();
					if(loop > 0){
						retorno += "<br style=\"page-break-before: always;\">";
					}
					if ((EnumBancos.F2B.getCodigo().equalsIgnoreCase(infBol.getInfboletoBanco().toString()) && boletoBean.getUrlF2B() == null) ) {
						retorno += ""
							+ "<hr style=\"margin-top: 5px; margin-bottom:5px\"/>"
							+ "<div style=\"text-align:center; font-color:#FF0000\">"
							+ "Boleto com vencimento em: <b>"
							+ boletoBean.getDataVencimento()
							+ "</b><br/>Valor: <b>"
							+ boletoBean.getValorBoleto()
							+ "</b><br/>do cliente: <b>"
							+ boletoBean.getNomeSacado()
							+ "</b><br/>Boleto ainda não registrado na F2B."
							+ "</div><hr style=\"margin-top: 5px; margin-bottom:5px\"/><br/>";
					}else{
						HtmlGenerator generator = new HtmlGenerator();
						JBoleto jBoleto = new JBoleto(generator, boletoBean, boletoBean.getInfBoleto().getInfboletoBanco());
						boletoBean.setEnderecoCodBar((new StringBuilder("boleto?cb=")).append(boletoBean.getCodigoBarras()).toString());
						jBoleto.addBoleto();
						retorno += generator.toString();
					}
					loop++;
				}
			}
			return retorno;
		} catch (Exception e) {
			return "<div style=\"text-align:center; font-color:#FF0000\">Não foi possível realizar a solicitação.</div>";
		}
	}

	public String processaBoleto(JBoletoBean jBoletoBean) {
		try {

			if(EnumBancos.F2B.getCodigo().equalsIgnoreCase(jBoletoBean.getInfBoleto().getInfboletoBanco().toString())){
				StringBuilder retorno = new StringBuilder();
				retorno.append("<script language=\"javascript\">");
				retorno.append("window.location = \""+ jBoletoBean.getUrlF2B() +"\";");
				retorno.append("</script>");
				retorno.trimToSize();
				return retorno.toString();
			}else{
				HtmlGenerator generator = new HtmlGenerator();
				JBoleto jBoleto = new JBoleto(generator, jBoletoBean, jBoletoBean.getInfBoleto().getInfboletoBanco().intValue());
				jBoletoBean.setEnderecoCodBar((new StringBuilder("boleto?cb=")).append(jBoletoBean.getCodigoBarras()).toString());
				jBoleto.addBoleto();
				String html = "<table width='640' cellspacing=5 cellpadding=0 border=0><tr>"
						+ "<form name='forma'><td align=\"center\">"
						+ "<input type=button value=' Imprimir Boleto' onClick='x86()' name='button'>"
						+ "</td></form></tr></table>";
				return generator.toString() + html;
			}
		} catch (NumberFormatException e) {
			return "<div style=\"text-align:center; font-color:#FF0000\">Não foi possível realizar a solicitação.<br/ >Verifique as configurações do boleto.</div>";
		} catch (Exception e) {
			return "<div style=\"text-align:center; font-color:#FF0000\">Não foi possível realizar a solicitação.</div>";
		}
	}

	public static void gerarCodBarras(OutputStream out, String s) {
		HtmlGenerator generator = new HtmlGenerator();
		generator.geraCodBar(out, s);
	}

}
