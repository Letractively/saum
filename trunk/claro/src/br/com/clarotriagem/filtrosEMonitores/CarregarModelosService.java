package br.com.clarotriagem.filtrosEMonitores;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.entitades.AparelhoModelo;
import br.com.clarotriagem.filtrosEMonitores.interfaces.CarregarModelosServiceI;
import br.com.clarotriagem.service.AparelhosService;
import br.com.clarotriagem.utils.Logger;
import br.com.clarotriagem.utils.singleton.mapas.AparelhoModeloCache;

@Component("carregarModelosService")
public class CarregarModelosService implements CarregarModelosServiceI {
	
	private static Logger log = new Logger(CarregarModelosService.class);
	
	@Autowired
	@ManagedProperty("#{aparelhosService}")
	private AparelhosService aparelhosService;

	@Async	
	@Override
	public void job() {
		log.info("Carregando modelos de celulares");
		try {
			List<AparelhoModelo> ams = aparelhosService.buscaTodosModelos();
			AparelhoModeloCache.getInstance().addMap(ams);
		} catch (Exception e) {
			log.erro(e);
			Thread.currentThread().interrupt();
		}
	}

	public AparelhosService getAparelhosService() {
		return aparelhosService;
	}

	public void setAparelhosService(AparelhosService aparelhosService) {
		this.aparelhosService = aparelhosService;
	}


}
