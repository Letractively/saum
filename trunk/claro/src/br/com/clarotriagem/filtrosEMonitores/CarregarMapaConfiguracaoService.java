package br.com.clarotriagem.filtrosEMonitores;

import java.util.Map;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.filtrosEMonitores.interfaces.CarregarMapaConfiguracaoServiceI;
import br.com.clarotriagem.service.DominiosService;
import br.com.clarotriagem.utils.Logger;
import br.com.clarotriagem.utils.singleton.mapas.ConfigUtil;

@Component("carregarMapaConfiguracaoService")
public class CarregarMapaConfiguracaoService implements CarregarMapaConfiguracaoServiceI {
	
	private static Logger log = new Logger(CarregarMapaConfiguracaoService.class);
	
	@Autowired
	@ManagedProperty("#{dominiosService}")
	private DominiosService dominiosService;

	@Async
	@Override
	public void job() {
		log.info("Carregando os dominios (mapas de configuração)");
		try {
			Map<String, String> doms = dominiosService.carregaDominios();
			if(doms != null && doms.size() > 0){
				ConfigUtil.getInstance().setMapas(doms);
			}
		} catch (Exception e) {
			log.erro(e);
			Thread.currentThread().interrupt();
		}
	}

	public DominiosService getDominiosService() {
		return dominiosService;
	}

	public void setDominiosService(DominiosService dominiosService) {
		this.dominiosService = dominiosService;
	}

}
