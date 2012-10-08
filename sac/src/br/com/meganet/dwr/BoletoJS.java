package br.com.meganet.dwr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import br.com.meganet.auditoria.BarraProgresso;
import br.com.meganet.facade.BoletoFacade;
import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.hbm.vo.LucroVO;

public class BoletoJS {

	private BoletoFacade boletoFacade;

	public void setBoletoFacade(BoletoFacade boletoFacade) {
		this.boletoFacade = boletoFacade;
	}

	BarraProgresso bp = new BarraProgresso();

	public Collection<BoletosGerados> adiquirirBoletosEmAberto(String cpf) {
		Collection<BoletosGerados> boletos = boletoFacade.adiquirirBoletosEmAberto(cpf);
		Collection<BoletosGerados> ret = new ArrayList<BoletosGerados>();
		for (Iterator<BoletosGerados> iterator = boletos.iterator(); iterator.hasNext();) {
			BoletosGerados bg = (BoletosGerados) iterator.next();
			BoletosGerados b = new BoletosGerados();
			b.setBoletosgeradosId(bg.getBoletosgeradosId());
			b.setBoletosgeradosPago(bg.getBoletosgeradosPago());
			b.setDataTempVencimento(bg.getDataTempVencimento());
			b.setVencido(bg.getVencido());
			ret.add(b);
		}
		return ret;
	}
	
	public LucroVO buscaPrevisaoLucro(String dtInicial){
		return boletoFacade.buscaPrevisaoLucro(dtInicial);
	}
	public int buscaBoletosPagoEmMao(String dtInicial, String dtFinal){
		List<BoletosGerados> bol = boletoFacade.buscaBoletosPagoEmMao(dtInicial, dtFinal);
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		req.getSession().setAttribute("dataInicial", dtInicial);
		req.getSession().setAttribute("dataFinal", dtFinal);
		req.getSession().setAttribute("boletoPagoEmMao", bol);
		return 0;
	}
	
	public Long setValorBoletoEntregue(String dtInicial, String dtFinal, Long id){
		Long ret = boletoFacade.setValorBoletoEntregue(dtInicial, dtFinal, id);
		return ret;
	}
}
