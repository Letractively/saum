package br.com.clarotriagem.filtrosEMonitores.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.clarotriagem.filtrosEMonitores.interfaces.CarregarMenuServiceI;

@Service
public class CarregaMenuScheduler {
	
	@Autowired
	@Qualifier("carregarMenuService")
	private CarregarMenuServiceI carregarMenuServiceI;

	/**
	 * You can opt for cron expression or fixedRate or fixedDelay
	 * <p>
	 * See Spring Framework 3 Reference: Chapter 25.5 Annotation Support for
	 * Scheduling and Asynchronous Execution
	 */
	//@Scheduled(fixedDelay=5000)
	//@Scheduled(cron = "0 * 0/1 * * ?")
	@Scheduled(fixedRate=(1000 * 60 * 60 * 24 * 365)) //um ano
	public void doSchedule() {
		getCarregarMenuServiceI().job();
	}

	public CarregarMenuServiceI getCarregarMenuServiceI() {
		return carregarMenuServiceI;
	}

	public void setCarregarMenuServiceI(CarregarMenuServiceI carregarMenuServiceI) {
		this.carregarMenuServiceI = carregarMenuServiceI;
	}



}
