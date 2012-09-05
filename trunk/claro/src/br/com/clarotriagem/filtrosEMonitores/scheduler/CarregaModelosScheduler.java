package br.com.clarotriagem.filtrosEMonitores.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.clarotriagem.filtrosEMonitores.interfaces.CarregarModelosServiceI;

@Service
public class CarregaModelosScheduler {
	
	@Autowired
	@Qualifier("carregarModelosService")
	private CarregarModelosServiceI carregarModelosServiceI;

	@Scheduled(fixedRate=(1000 * 60 * 60 * 24 * 365)) //um ano
	public void doSchedule() {
		getCarregarModelosServiceI().job();
	}

	public CarregarModelosServiceI getCarregarModelosServiceI() {
		return carregarModelosServiceI;
	}

	public void setCarregarModelosServiceI(CarregarModelosServiceI carregarModelosServiceI) {
		this.carregarModelosServiceI = carregarModelosServiceI;
	}


}
