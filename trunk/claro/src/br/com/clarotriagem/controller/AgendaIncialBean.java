package br.com.clarotriagem.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.controller.factory.AgendaFactory;
import br.com.clarotriagem.entitades.Calendario;
import br.com.clarotriagem.service.CalendarioService;
import br.com.clarotriagem.service.ClienteService;
import br.com.clarotriagem.service.UsuarioService;

@ManagedBean
@Scope("view")
@Component
public class AgendaIncialBean extends AgendaFactory {

	private static final long serialVersionUID = 7068676584689089558L;
	
	@Autowired
	private CalendarioService calendarioService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ClienteService clienteService;
	
	public void adicionaAlteraEvento(ActionEvent actionEvent) {
		super.adicionaAlteraEventoGERAL(actionEvent, calendarioService);
	}

	public ScheduleModel getTodosEventosDaAgenda() {
		if(todosEventosDaAgenda == null){
			preencheAgenda();
		}
		return todosEventosDaAgenda;
	}
	
	public void quandoAlterarDataDoEvento(ScheduleEntryMoveEvent event) {
		Calendario cal = mapaEventos.get(event.getScheduleEvent().getId());
		cal.setDataInicial(new Timestamp(event.getScheduleEvent().getStartDate().getTime()));
		cal.setDataFinal(new Timestamp(event.getScheduleEvent().getEndDate().getTime()));
		calendarioService.atualizaCalendario(cal);
		mapaEventos.put(event.getScheduleEvent().getId(), cal);
		addInfoMessage(getBunde("compromisso_alterado"));
		resetForm();
	}
	
	public void quandoAlterarIntervaloDeTempoDoEvento(ScheduleEntryResizeEvent event) {
		Calendario cal = mapaEventos.get(event.getScheduleEvent().getId());
		cal.setDataInicial(new Timestamp(event.getScheduleEvent().getStartDate().getTime()));
		cal.setDataFinal(new Timestamp(event.getScheduleEvent().getEndDate().getTime()));
		calendarioService.atualizaCalendario(cal);
		mapaEventos.put(event.getScheduleEvent().getId(), cal);
		addInfoMessage(getBunde("compromisso_alterado"));
		resetForm();
	}
	public void quandoSelecionaEventoJaGravado(ScheduleEntrySelectEvent selectEvent) {
		resetForm();
		event = selectEvent.getScheduleEvent();
		calendario = mapaEventos.get(event.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		cliente = calendario.getWarehouse().getCliente();
		warehouse = calendario.getWarehouse();
		warehouseCombo = clienteService.carregaWarehousePeloCliente(cliente.getId());
		usuarioResponsavel = calendario.getUsuarioResponsavel();
		horaEventoInicial = sdf.format(calendario.getDataInicial());
		horaEventoFinal = sdf.format(calendario.getDataFinal());

	}
	public void quandoSelecionarDataEmBranco(DateSelectEvent selectEvent) {
		resetForm();
		event = new DefaultScheduleEvent("", selectEvent.getDate(), selectEvent.getDate());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		horaEventoInicial = sdf.format(selectEvent.getDate());
		horaEventoFinal = "";
	}

	public SortedMap<String, Long> getClientesCombo() {
		if(clientesCombo == null){
			clientesCombo = clienteService.buscaClientesPai();
		}
		return clientesCombo;
	}

	public void carregaWarehouse(){
		warehouseCombo = clienteService.carregaWarehousePeloCliente(cliente.getId());
	}

	public SortedMap<String, Long> getUsuariosSistema() {
		if(getUsuarioLogado().getPerfil().getEscalaFuncionariosAgenda()){
			usuariosSistema = usuarioService.buscaUsuariosAutorizados();
		}else{
			usuariosSistema = new TreeMap<String, Long>();
			usuariosSistema.put(getUsuarioLogado().getNome() + " - " + getUsuarioLogado().getEmail(), getUsuarioLogado().getId());
		}
		return usuariosSistema;
	}
	
	private void preencheAgenda(){
		List<Calendario> agenda = calendarioService.getAgendaSemana(getUsuarioLogado());
		todosEventosDaAgenda = new DefaultScheduleModel();
		if(agenda != null && agenda.size() > 0){
			for (Iterator<Calendario> iterator = agenda.iterator(); iterator.hasNext();) {
				Calendario cal = (Calendario) iterator.next();
				DefaultScheduleEvent dse = preencheEvento(cal);
				dse.setStyleClass("color:#000000;");
				todosEventosDaAgenda.addEvent(dse);
				mapaEventos.put(dse.getId(), cal);
			}
		}
	}
	
}
