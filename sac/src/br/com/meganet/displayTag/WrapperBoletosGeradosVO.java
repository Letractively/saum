package br.com.meganet.displayTag;

import java.util.Calendar;
import java.util.Date;

import org.displaytag.decorator.TableDecorator;

import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.util.DateUtils;

public class WrapperBoletosGeradosVO extends TableDecorator{
	
	private String estaPago;
	private String entregueAdm;

	public String getEstaPago() {
		try{
			BoletosGerados boleto = (BoletosGerados) getCurrentRowObject();
			Calendar dataAtual = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
			if(boleto.getBoletosgeradosPago()){
				estaPago = "Pago";
			}else{
				if(DateUtils.aPrimeiraEhMaiorQueSegunda(dataAtual.getTime(), boleto.getBoletosgeradosDataVencimento())){
					estaPago = "<div style=\"cursor: pointer; color: #FF0000\" " +
							"onclick=\"geraBoleto('"+ boleto.getBoletosgeradosId() +"');\" \">Vencido</div>";
				}else{
					estaPago = "<div style=\"cursor: pointer; color: #FF0000\" " +
					"onclick=\"geraBoleto('"+ boleto.getBoletosgeradosId() +"');\">Gerar</div>";
				}
			}
			return estaPago;
			
		}catch (Exception e) {
			return "";
		}
	}
	public void setEstaPago(String estaPago) {
		this.estaPago = estaPago;
	}
	public String getEntregueAdm() {
		BoletosGerados boleto = (BoletosGerados) getCurrentRowObject();
		if(boleto.getBoletosgeradosDinheiroEntregueAdministradorConta()){
			entregueAdm = "Sim";
		}else{
			entregueAdm = "<div id=\"idTb"+boleto.getBoletosgeradosId()+"\" style=\"cursor: pointer; color: #FF0000\" " +
					"onclick=\"setaEntregue('"+ boleto.getBoletosgeradosId() +"');\" \">Não</div>";
		}
		return entregueAdm;
			
	}
	public void setEntregueAdm(String entregueAdm) {
		this.entregueAdm = entregueAdm;
	}
	
}
