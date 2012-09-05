package br.com.clarotriagem.filtrosEMonitores;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.filtrosEMonitores.interfaces.MonitoraAgendamentosAtrasadosServiceI;
import br.com.clarotriagem.service.CalendarioService;
import br.com.clarotriagem.utils.Logger;

@Component("monitoraAgendamentosAtrasadosService")
public class MonitoraAgendamentosAtrasadosService implements MonitoraAgendamentosAtrasadosServiceI {
	
	private static Logger log = new Logger(MonitoraAgendamentosAtrasadosService.class);
	
	@Autowired
	@ManagedProperty("#{calendarioService}")
	private CalendarioService calendarioService;

	@Async	
	@Override
	public void job() {
		log.info("Monitorando os agendamentos atrasados.");
		try {
			calendarioService.monitoraCalendarioAtrasado();
		} catch (Exception e) {
			log.erro(e);
			Thread.currentThread().interrupt();
		}
	}

	public CalendarioService getCalendarioService() {
		return calendarioService;
	}

	public void setCalendarioService(CalendarioService calendarioService) {
		this.calendarioService = calendarioService;
	}


}
