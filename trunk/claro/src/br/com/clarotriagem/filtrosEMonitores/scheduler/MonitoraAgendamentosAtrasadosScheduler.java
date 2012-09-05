package br.com.clarotriagem.filtrosEMonitores.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.clarotriagem.filtrosEMonitores.interfaces.MonitoraAgendamentosAtrasadosServiceI;

@Service
public class MonitoraAgendamentosAtrasadosScheduler {
	
	@Autowired
	@Qualifier("monitoraAgendamentosAtrasadosService")
	private MonitoraAgendamentosAtrasadosServiceI monitoraAgendamentosAtrasadosServiceI;

//	@Scheduled(fixedRate=(1000 * 60))
	@Scheduled(cron = "0 0 6 * * *") //roda todo dia 6:00h
	public void doSchedule() {
		getMonitoraAgendamentosAtrasadosServiceI().job();
	}

	public MonitoraAgendamentosAtrasadosServiceI getMonitoraAgendamentosAtrasadosServiceI() {
		return monitoraAgendamentosAtrasadosServiceI;
	}

	public void setMonitoraAgendamentosAtrasadosServiceI(MonitoraAgendamentosAtrasadosServiceI monitoraAgendamentosAtrasadosServiceI) {
		this.monitoraAgendamentosAtrasadosServiceI = monitoraAgendamentosAtrasadosServiceI;
	}



}
