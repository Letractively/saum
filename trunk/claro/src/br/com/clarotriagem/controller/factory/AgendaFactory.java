package br.com.clarotriagem.controller.factory;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.clarotriagem.controller.BaseBean;
import br.com.clarotriagem.entitades.Calendario;
import br.com.clarotriagem.entitades.Cliente;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.CalendarioService;
import br.com.clarotriagem.utils.Erros;
import br.com.clarotriagem.utils.enums.TipoAtendimento;

public class AgendaFactory extends BaseBean{

	protected static final long serialVersionUID = 7779359671184071374L;

	protected ScheduleModel todosEventosDaAgenda;
	protected ScheduleEvent event = new DefaultScheduleEvent();

	protected Calendario calendario;
	protected Cliente cliente;
	protected Cliente warehouse;
	protected UsuarioIdentificacao usuarioLogado;
	protected UsuarioIdentificacao usuarioResponsavel;
	protected String horaEventoInicial;
	protected String horaEventoFinal;
	
	protected SortedMap<String, Long> clientesCombo;
	protected SortedMap<String, Long> usuariosSistema;
	protected SortedMap<String, Long> warehouseCombo;
	protected SortedMap<String, Integer> tipoAtendimento;

	protected Map<String, Calendario> mapaEventos = new HashMap<String, Calendario>();
	
	public void adicionaAlteraEventoGERAL(ActionEvent actionEvent, CalendarioService calendarioService) {
		try{
			if(validaForm()){
				Calendar calIni = new GregorianCalendar();
				Calendar calFim = new GregorianCalendar();
				
				calIni.setTimeInMillis(event.getStartDate().getTime());
				calFim.setTimeInMillis(event.getEndDate().getTime());
				
				calIni.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaEventoInicial.split(":")[0]));
				calIni.set(Calendar.MINUTE, Integer.parseInt(horaEventoInicial.split(":")[1]));
				
				calFim.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaEventoFinal.split(":")[0]));
				calFim.set(Calendar.MINUTE, Integer.parseInt(horaEventoFinal.split(":")[1]));
				if(event.getId() == null){
					
					calendario.setDataInicial(new Timestamp(calIni.getTimeInMillis()));
					calendario.setDataFinal(new Timestamp(calFim.getTimeInMillis()));
					calendario.setConcluido(false);
					
					calendario.setUsuarioAbriu(getUsuarioLogado());
					calendario.setUsuarioResponsavel(usuarioResponsavel);
					calendario.setWarehouse(warehouse);
					
					calendarioService.adicionaCalendario(calendario);
					Calendario cal = calendarioService.getCalendarioPeloID(calendario.getId());
					DefaultScheduleEvent dse = preencheEvento(cal);
					todosEventosDaAgenda.addEvent(dse);
					mapaEventos.put(dse.getId(), cal);
					addInfoMessage(getBunde("compromisso_inserido_sucesso"));
				}else{
					calendario.setDataInicial(new Timestamp(calIni.getTimeInMillis()));
					calendario.setDataFinal(new Timestamp(calFim.getTimeInMillis()));
					
					calendario.setUsuarioAbriu(getUsuarioLogado());
					calendario.setUsuarioResponsavel(usuarioResponsavel);
					calendario.setWarehouse(warehouse);
					
					calendarioService.atualizaCalendario(calendario);
					DefaultScheduleEvent dse = preencheEvento(calendario);
					
					todosEventosDaAgenda.deleteEvent(event);
					mapaEventos.remove(event.getId());
					
					todosEventosDaAgenda.addEvent(dse);
					mapaEventos.put(dse.getId(), calendario);
					addInfoMessage(getBunde("compromisso_alterado"));
				}
			}
			resetForm();
		}catch (Exception e) {
			addErroMessage(null, getTituloApp(Erros.ERRO_CALENDARIO_GERAL), getBunde("compromisso_inserido_erro"));
		}
	}

	
	protected boolean validaForm() throws Exception{
		boolean ret = true;
		if(event.getStartDate() == null){
			addErroMessage(null, getBunde("erro_msg"), getBunde("erro_data_inicial"));
			ret = false;
		}
		if(event.getEndDate() == null){
			addErroMessage(null, getBunde("erro_msg"), getBunde("erro_data_final"));
			ret = false;
		}
		if(horaEventoInicial == null || horaEventoInicial.length() != 5){
			addErroMessage(null, getBunde("erro_msg"), getBunde("erro_hora_inicial"));
			ret = false;
		}else{
			int hora = Integer.parseInt(horaEventoInicial.split(":")[0]);
			int min = Integer.parseInt(horaEventoInicial.split(":")[1]);
			if(hora >= 22 || hora < 5){
				addErroMessage(null, getBunde("erro_msg"), getBunde("hora_inicial") + "\n" + getBunde("range_hora_agendamento"));
				ret = false;
			}
			if(min >= 60 || min < 0){
				addErroMessage(null, getBunde("erro_msg"), getBunde("hora_inicial") + "\n" + getBunde("range_minu_agendamento"));
				ret = false;
			}
		}
		if(horaEventoFinal == null || horaEventoFinal.length() != 5){
			addErroMessage(null, getBunde("erro_msg"), getBunde("erro_hora_final"));
			ret = false;
		}else{
			int hora = Integer.parseInt(horaEventoFinal.split(":")[0]);
			int min = Integer.parseInt(horaEventoFinal.split(":")[1]);
			if(hora >= 22 || hora < 5){
				addErroMessage(null, getBunde("erro_msg"), getBunde("hora_final") + "\n" + getBunde("range_hora_agendamento"));
				ret = false;
			}
			if(min >= 60 || min < 0){
				addErroMessage(null, getBunde("erro_msg"), getBunde("hora_final") + "\n" + getBunde("range_minu_agendamento"));
				ret = false;
			}
		}
		
		
		if(horaEventoFinal != null && horaEventoFinal.length() == 5 && horaEventoInicial != null && horaEventoInicial.length() == 5){
			Calendar calIni = new GregorianCalendar();
			Calendar calFim = new GregorianCalendar();
			
			calIni.setTimeInMillis(event.getStartDate().getTime());
			calFim.setTimeInMillis(event.getEndDate().getTime());
			
			calIni.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaEventoInicial.split(":")[0]));
			calIni.set(Calendar.MINUTE, Integer.parseInt(horaEventoInicial.split(":")[1]));
			
			calFim.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaEventoFinal.split(":")[0]));
			calFim.set(Calendar.MINUTE, Integer.parseInt(horaEventoFinal.split(":")[1]));
			
			if(calIni.getTime().after(calFim.getTime())){
				addErroMessage(null, getBunde("erro_msg"), getBunde("data_final_data_inicial"));
				ret = false;
			}

		}
		
		
		if(cliente == null || cliente.getId() == null){
			addErroMessage(null, getBunde("erro_msg"), getBunde("erro_cliente"));
			ret = false;
		}
		if(getWarehouse() == null || getWarehouse().getId() == null){
			addErroMessage(null, getBunde("erro_msg"), getBunde("erro_warehouse"));
			ret = false;
		}
		if(calendario.getQuantidade() == null){
			addErroMessage(null, getBunde("erro_msg"), getBunde("erro_qtd_cel_triagem"));
			ret = false;
		}
		if(calendario.getContato() == null || calendario.getContato().length() == 0){
			addErroMessage(null, getBunde("erro_msg"), getBunde("erro_contato"));
			ret = false;
		}
		if((calendario.getTipoAtendimento() == 1 || calendario.getCancelar()) && (calendario.getDescricao() == null || calendario.getDescricao().length() == 0)){
			addErroMessage(null, getBunde("erro_msg"), getBunde("erro_descricao"));
			ret = false;
		}
		return ret;
	}

	protected DefaultScheduleEvent preencheEvento(Calendario cal){
		DefaultScheduleEvent dse = new DefaultScheduleEvent();
		dse.setTitle(cal.getWarehouse().getNomeFantasia() + " - " +cal.getWarehouse().getCliente().getNomeFantasia() + "\n" + cal.getContato());
		dse.setData(cal.getDescricao());
		dse.setEditable(getUsuarioLogado().getPerfil().getEscalaFuncionariosAgenda());
		dse.setEndDate(cal.getDataFinal());
		dse.setStartDate(cal.getDataInicial());
		return dse;
	}
	
	protected void resetForm(){
		event = new DefaultScheduleEvent();
		calendario = new Calendario();
		horaEventoInicial = "";
		horaEventoFinal = "";
		cliente = new Cliente();
		warehouse = new Cliente();
		usuarioResponsavel = new UsuarioIdentificacao();
		warehouseCombo = new TreeMap<String, Long>();
	}

	public Cliente getWarehouse() {
		if(warehouse == null){
			warehouse = new Cliente();
		}
		return warehouse;
	}

	public UsuarioIdentificacao getUsuarioLogado() {
		if(usuarioLogado == null){
			usuarioLogado = super.getUsuarioLogado();
		}
		return usuarioLogado;
	}

	public SortedMap<String, Integer> getTipoAtendimento() {
		if(tipoAtendimento == null){
			tipoAtendimento = TipoAtendimento.getMapaRotulos();
		}
		return tipoAtendimento;
	}

	public void setTodosEventosDaAgenda(ScheduleModel todosEventosDaAgenda) {
		this.todosEventosDaAgenda = todosEventosDaAgenda;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public Calendario getCalendario() {
		if(calendario == null){
			calendario = new Calendario();
		}
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	public Cliente getCliente() {
		if(cliente == null){
			cliente = new Cliente();
		}
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setWarehouse(Cliente warehouse) {
		this.warehouse = warehouse;
	}

	public void setUsuarioLogado(UsuarioIdentificacao usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public UsuarioIdentificacao getUsuarioResponsavel() {
		if(usuarioResponsavel == null){
			usuarioResponsavel = new UsuarioIdentificacao();
		}
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(UsuarioIdentificacao usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public String getHoraEventoInicial() {
		return horaEventoInicial;
	}

	public void setHoraEventoInicial(String horaEventoInicial) {
		this.horaEventoInicial = horaEventoInicial;
	}

	public String getHoraEventoFinal() {
		return horaEventoFinal;
	}

	public void setHoraEventoFinal(String horaEventoFinal) {
		this.horaEventoFinal = horaEventoFinal;
	}

	public void setClientesCombo(SortedMap<String, Long> clientesCombo) {
		this.clientesCombo = clientesCombo;
	}

	public void setUsuariosSistema(SortedMap<String, Long> usuariosSistema) {
		this.usuariosSistema = usuariosSistema;
	}

	public SortedMap<String, Long> getWarehouseCombo() {
		return warehouseCombo;
	}

	public void setWarehouseCombo(SortedMap<String, Long> warehouseCombo) {
		this.warehouseCombo = warehouseCombo;
	}

	public Map<String, Calendario> getMapaEventos() {
		return mapaEventos;
	}

	public void setMapaEventos(Map<String, Calendario> mapaEventos) {
		this.mapaEventos = mapaEventos;
	}

	public void setTipoAtendimento(SortedMap<String, Integer> tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

}
