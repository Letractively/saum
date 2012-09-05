package br.com.clarotriagem.filtrosEMonitores.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.clarotriagem.filtrosEMonitores.interfaces.MonitorarAgendamentoServiceI;

@Service
public class MonitoraAgendamentoScheduler {
	
	@Autowired
	@Qualifier("monitorarAgendamentoService")
	private MonitorarAgendamentoServiceI monitorarAgendamentoServiceI;

//	@Scheduled(fixedRate=(1000 * 60))
	@Scheduled(cron = "0 59 11 * * *")//roda todo dia a meia noite
	public void doSchedule() {
		getMonitorarAgendamentoServiceI().job();
	}

	public MonitorarAgendamentoServiceI getMonitorarAgendamentoServiceI() {
		return monitorarAgendamentoServiceI;
	}

	public void setMonitorarAgendamentoServiceI(
			MonitorarAgendamentoServiceI monitorarAgendamentoServiceI) {
		this.monitorarAgendamentoServiceI = monitorarAgendamentoServiceI;
	}



}
