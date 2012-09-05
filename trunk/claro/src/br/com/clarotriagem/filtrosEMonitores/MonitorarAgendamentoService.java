package br.com.clarotriagem.filtrosEMonitores;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.filtrosEMonitores.interfaces.MonitorarAgendamentoServiceI;
import br.com.clarotriagem.service.CalendarioService;
import br.com.clarotriagem.utils.Logger;

@Component("monitorarAgendamentoService")
public class MonitorarAgendamentoService implements MonitorarAgendamentoServiceI {
	
	private static Logger log = new Logger(MonitorarAgendamentoService.class);
	
	@Autowired
	@ManagedProperty("#{calendarioService}")
	private CalendarioService calendarioService;

	@Async	
	@Override
	public void job() {
		log.info("Monitorando os agendamentos futuros com no maximo 2 dias.");
		try {
			calendarioService.monitoraCalendario2Dias();
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
