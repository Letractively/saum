package br.com.meganet.threads;

import java.util.Map;
import java.util.TimerTask;

import br.com.meganet.bo.DominiosBO;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Logger;

/**
 * @author efren
 * 
 */
public class CarregaMapas extends TimerTask {

	private DominiosBO dominiosBO;
	
	private static Logger logger = new Logger(CarregaMapas.class);

	@Override
	public void run() {
		try{
			Map<String, String> mapas = dominiosBO.buscaTodosMapasDeConfiguracao();
			ConfigUtil.getInstance().setPropertie(mapas);
		}catch (Exception e) {
			logger.erro("Erro ao recuperar mapas de parametros", e);
		}
	}

	public void setDominiosBO(DominiosBO dominiosBO) {
		this.dominiosBO = dominiosBO;
	}

}
