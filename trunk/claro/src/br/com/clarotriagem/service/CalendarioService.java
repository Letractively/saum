package br.com.clarotriagem.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.controller.BaseBean;
import br.com.clarotriagem.entitades.Calendario;
import br.com.clarotriagem.entitades.Mensagem;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.factory.ServiceFactory;
import br.com.clarotriagem.utils.Logger;
import br.com.clarotriagem.utils.mail.EnviaEmailBO;

@Service("calendarioService")
@Transactional
public class CalendarioService extends ServiceFactory<CalendarioService>{

	private final static Logger log = new Logger(CalendarioService.class);
	
	public List<Calendario> getAgendaSemana(UsuarioIdentificacao usuarioIdentificacao) {
		try {
			Calendar dtInicial = new GregorianCalendar();
			Calendar dtFinal = new GregorianCalendar();
			
			dtInicial.setTimeInMillis(System.currentTimeMillis());
			dtFinal.setTimeInMillis(System.currentTimeMillis());
			
			dtInicial.set(Calendar.HOUR_OF_DAY, 0);
			dtInicial.set(Calendar.MINUTE, 0);
			dtInicial.set(Calendar.SECOND, 0);
			dtInicial.set(Calendar.MILLISECOND, 0);

			dtFinal.set(Calendar.HOUR_OF_DAY, 23);
			dtFinal.set(Calendar.MINUTE, 59);
			dtFinal.set(Calendar.SECOND, 59);
			dtFinal.set(Calendar.MILLISECOND, 0);

			dtInicial.set(Calendar.DAY_OF_MONTH, dtInicial.get(Calendar.DAY_OF_MONTH) - 21);
			dtFinal.set(Calendar.DAY_OF_MONTH, dtFinal.get(Calendar.DAY_OF_MONTH) + 21);
			
			List<Calendario> ret = getCalendarioDAO().getAgendaPorIntervaloDeDias(dtInicial.getTime(), dtFinal.getTime(), usuarioIdentificacao);
			return ret;
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public List<Calendario> getAgendaUsuario(UsuarioIdentificacao usuarioIdentificacao) {
		try {
			Calendar dtInicial = new GregorianCalendar();
			Calendar dtFinal = new GregorianCalendar();
			
			dtInicial.setTimeInMillis(System.currentTimeMillis());
			dtFinal.setTimeInMillis(System.currentTimeMillis());
			
			dtInicial.set(Calendar.HOUR_OF_DAY, 0);
			dtInicial.set(Calendar.MINUTE, 0);
			dtInicial.set(Calendar.SECOND, 0);
			dtInicial.set(Calendar.MILLISECOND, 0);

			dtFinal.set(Calendar.HOUR_OF_DAY, 23);
			dtFinal.set(Calendar.MINUTE, 59);
			dtFinal.set(Calendar.SECOND, 59);
			dtFinal.set(Calendar.MILLISECOND, 0);

			dtInicial.set(Calendar.YEAR, dtInicial.get(Calendar.YEAR) - 2);
			dtFinal.set(Calendar.YEAR, dtFinal.get(Calendar.YEAR) + 3);

			List<Calendario> ret = getCalendarioDAO().getAgendaPorIntervaloDeDias(dtInicial.getTime(), dtFinal.getTime(), usuarioIdentificacao);
			return ret;
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}
	
	public Calendario adicionaCalendario(Calendario cal) {
		try {
			Calendario ret = getCalendarioDAO().save(cal);
			EnviaEmailBO.enviaEmailNovoAgendamento(cal);
			
			Mensagem msg = new Mensagem();
			msg.setData(new Timestamp(System.currentTimeMillis()));
			msg.setLida(false);
			msg.setExcluido(false);
			msg.setRemetente(cal.getUsuarioAbriu());
			msg.setDestinatario(cal.getUsuarioResponsavel());
			msg.setTitulo(BaseBean.getBundeExterno("agendamento_msg"));
			msg.setMensagem(EnviaEmailBO.msgEnviaEmailNovoAgendamento(cal));
			getMensagemDAO().save(msg);
			return ret;
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public void atualizaCalendario(Calendario cal) {
		try {
			getCalendarioDAO().atualizaCalendario(cal);
			EnviaEmailBO.enviaEmailAlteracaoAgendamento(cal);
			
			Mensagem msg = new Mensagem();
			msg.setData(new Timestamp(System.currentTimeMillis()));
			msg.setLida(false);
			msg.setExcluido(false);
			msg.setRemetente(cal.getUsuarioAbriu());
			msg.setDestinatario(cal.getUsuarioResponsavel());
			msg.setTitulo(BaseBean.getBundeExterno("agendamento_msg"));
			msg.setMensagem(EnviaEmailBO.msgEnviaEmailAlteracaoAgendamento(cal));
			getMensagemDAO().save(msg);
		} catch (Exception e) {
			log.erro(e);
		}
	}

	public Calendario getCalendarioPeloID(Long id) {
		try {
			return getCalendarioDAO().getCalendarioPeloID(id);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public List<Calendario> buscaCalendarios(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, boolean buscaTodos, UsuarioIdentificacao usrLogado) {
		try {
			return getCalendarioDAO().buscaCalendarios(startingAt, maxPerPage, sortField, sortOrder, buscaTodos, usrLogado);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public int getQtdTotalCalendario(boolean buscaTodos, UsuarioIdentificacao usrLogado) {
		try {
			return getCalendarioDAO().getQtdTotalCalendario(buscaTodos, usrLogado);
		} catch (Exception e) {
			log.erro(e);
			return 0;
		}
	}

	public void monitoraCalendario2Dias() {
		try {
			Calendar dtInicial = new GregorianCalendar();
			Calendar dtFinal = new GregorianCalendar();
			
			dtInicial.setTimeInMillis(System.currentTimeMillis());
			dtFinal.setTimeInMillis(System.currentTimeMillis());
			
			dtInicial.set(Calendar.HOUR_OF_DAY, 0);
			dtInicial.set(Calendar.MINUTE, 0);
			dtInicial.set(Calendar.SECOND, 0);
			dtInicial.set(Calendar.MILLISECOND, 0);

			dtFinal.set(Calendar.HOUR_OF_DAY, 23);
			dtFinal.set(Calendar.MINUTE, 59);
			dtFinal.set(Calendar.SECOND, 59);
			dtFinal.set(Calendar.MILLISECOND, 0);

			dtFinal.set(Calendar.DAY_OF_MONTH, dtFinal.get(Calendar.DAY_OF_MONTH) + 2);
			
			Map<Long, List<Calendario>> conjEmail = new HashMap<Long, List<Calendario>>();
			List<Calendario> cals = getCalendarioDAO().getAgendaPorIntervaloDeDiasSemEnvioDeEmailAntecipado(dtInicial.getTime(), dtFinal.getTime());
			
			for (Calendario cal : cals) {
				cal.setEnvioEmailAntecipado(true);
				getCalendarioDAO().atualizaCalendario(cal);
				List<Calendario> listaAtualDoCliente = conjEmail.get(cal.getUsuarioResponsavel().getId());
				if(listaAtualDoCliente == null){ //ainda nao existe lista para o usuario atual da lista
					
					List<Calendario> listaASerInserida = new ArrayList<Calendario>();
					listaASerInserida.add(cal);
					
					conjEmail.put(cal.getUsuarioResponsavel().getId(), listaASerInserida);
				}else{//ja foi feita uma lista para o usuario atual, mas o calendario nao esta presente nela
					
					listaAtualDoCliente.add(cal);
					conjEmail.put(cal.getUsuarioResponsavel().getId(), listaAtualDoCliente);
				}
			}
			
			for(Map.Entry<Long, List<Calendario>> item : conjEmail.entrySet()){
				if(item.getValue() != null && item.getValue().size() > 0){
					EnviaEmailBO.enviaEmaiAvisoAgendamentosAVencer(item.getValue());
					log.info("Enviando email antecipado: " + item.getValue().toArray().toString());
				}
			}
			
		} catch (Exception e) {
			log.erro(e);
		}
	}

	public void monitoraCalendarioAtrasado() {
		try {
			Calendar dtInicial = new GregorianCalendar();
			Calendar dtFinal = new GregorianCalendar();
			
			dtInicial.setTimeInMillis(System.currentTimeMillis());
			dtFinal.setTimeInMillis(System.currentTimeMillis());
			
			dtInicial.set(Calendar.HOUR_OF_DAY, 0);
			dtInicial.set(Calendar.MINUTE, 0);
			dtInicial.set(Calendar.SECOND, 0);
			dtInicial.set(Calendar.MILLISECOND, 0);
			
			dtFinal.set(Calendar.HOUR_OF_DAY, 23);
			dtFinal.set(Calendar.MINUTE, 59);
			dtFinal.set(Calendar.SECOND, 59);
			dtFinal.set(Calendar.MILLISECOND, 0);
			
			dtInicial.set(Calendar.DAY_OF_MONTH, dtFinal.get(Calendar.DAY_OF_MONTH) - 3);
			dtFinal.set(Calendar.DAY_OF_MONTH, dtFinal.get(Calendar.DAY_OF_MONTH) - 1);
			
			Map<Long, List<Calendario>> conjEmail = new HashMap<Long, List<Calendario>>();
			List<Calendario> cals = getCalendarioDAO().getAgendaPorIntervaloDeDiasSemEnvioDeEmailAntecipado(dtInicial.getTime(), dtFinal.getTime());
			
			for (Calendario cal : cals) {
				cal.setEnvioEmailAtrasado(true);
				getCalendarioDAO().atualizaCalendario(cal);
				List<Calendario> listaAtualDoCliente = conjEmail.get(cal.getUsuarioResponsavel().getId());
				if(listaAtualDoCliente == null){//ainda nao existe lista para o usuario atual da lista
					
					List<Calendario> listaASerInserida = new ArrayList<Calendario>();
					listaASerInserida.add(cal);
					
					conjEmail.put(cal.getUsuarioResponsavel().getId(), listaASerInserida);
				}else{//ja foi feita uma lista para o usuario atual, mas o calendario nao esta presente nela
					
					listaAtualDoCliente.add(cal);
					conjEmail.put(cal.getUsuarioResponsavel().getId(), listaAtualDoCliente);
				}
			}
			
			for(Map.Entry<Long, List<Calendario>> item : conjEmail.entrySet()){
				if(item.getValue() != null && item.getValue().size() > 0){
					EnviaEmailBO.enviaEmaiAvisoAgendamentosAtrasados(item.getValue());
					log.info("Enviando email atrasado: " + item.getValue().toArray().toString());
				}
			}
			
		} catch (Exception e) {
			log.erro(e);
		}
	}

	public List<Calendario> buscaCalendariosAbertos(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, boolean buscaTodos, UsuarioIdentificacao usrLogado) {
		try {
			if(buscaTodos){
				return getCalendarioDAO().buscaCalendariosAbertos(startingAt, maxPerPage, sortField, sortOrder);
			}else{
				return getCalendarioDAO().buscaCalendariosPorUsuarioAbertos(startingAt, maxPerPage, sortField, sortOrder, usrLogado);
			}
		} catch (Exception e) {
			log.erro(e);
			return new ArrayList<Calendario>();
		}
	}
	
}
