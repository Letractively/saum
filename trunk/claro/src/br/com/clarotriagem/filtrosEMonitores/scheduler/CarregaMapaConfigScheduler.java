package br.com.clarotriagem.filtrosEMonitores.scheduler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.clarotriagem.filtrosEMonitores.interfaces.CarregarMapaConfiguracaoServiceI;

@Service
public class CarregaMapaConfigScheduler {
	
	@Autowired
	@Qualifier("carregarMapaConfiguracaoService")
	private CarregarMapaConfiguracaoServiceI carregarMapaConfiguracaoServiceI;

	@Scheduled(fixedRate=(1000 * 60 * 60 * 24)) //um dia
	public void doSchedule() {
		getCarregarMapaConfiguracaoServiceI().job();
	}

	public CarregarMapaConfiguracaoServiceI getCarregarMapaConfiguracaoServiceI() {
		return carregarMapaConfiguracaoServiceI;
	}

	public void setCarregarMapaConfiguracaoServiceI(CarregarMapaConfiguracaoServiceI carregarMapaConfiguracaoServiceI) {
		this.carregarMapaConfiguracaoServiceI = carregarMapaConfiguracaoServiceI;
	}


}
