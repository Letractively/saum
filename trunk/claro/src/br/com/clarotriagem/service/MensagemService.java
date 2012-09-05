package br.com.clarotriagem.service;

import java.sql.Timestamp;
import java.util.List;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.entitades.Mensagem;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.factory.ServiceFactory;
import br.com.clarotriagem.utils.Logger;

@Service("mensagemService")
@Transactional
public class MensagemService extends ServiceFactory<MensagemService> {
	
	private final static Logger log = new Logger(MensagemService.class);
	
	public MensagemService(){
		super();
	}

	public List<Mensagem> buscaMensagemNaoLida(UsuarioIdentificacao ui) {
		try {
			return getMensagemDAO().buscaMensagemNaoLida(ui);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public List<Mensagem> buscaMensagemTodas(UsuarioIdentificacao ui, int startingAt, int maxPerPage, String sortField, SortOrder sortOrder) {
		try {
			return getMensagemDAO().buscaMensagemTodas(ui, startingAt, maxPerPage, sortField, sortOrder);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}
	
	public List<Mensagem> buscaMensagemTodasEnviadas(UsuarioIdentificacao ui, int startingAt, int maxPerPage, String sortField, SortOrder sortOrder) {
		try {
			return getMensagemDAO().buscaMensagemEnviadasTodas(ui, startingAt, maxPerPage, sortField, sortOrder);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public void delete(Mensagem mensagem) {
		try {
			mensagem.setExcluido(true);
			getMensagemDAO().update(mensagem);
		} catch (Exception e) {
			log.erro(e);
		}
	}

	public void marcaMensagemComoLida(Mensagem mensagem) {
		try {
			mensagem.setLida(true);
			mensagem.setDataLeitura(new Timestamp(System.currentTimeMillis()));
			getMensagemDAO().update(mensagem);
		} catch (Exception e) {
			log.erro(e);
		}
	}

	public void marcaMensagemComoNaoLida(Mensagem mensagem) {
		try {
			mensagem.setLida(false);
			getMensagemDAO().update(mensagem);
		} catch (Exception e) {
			log.erro(e);
		}
	}

	public Integer getQtdMensagemNaoLida(UsuarioIdentificacao usuarioIdentificacao) {
		try {
			return getMensagemDAO().getQtdMensagensNaoLidas(usuarioIdentificacao);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public Integer getQtdTotalMensagem(UsuarioIdentificacao usuarioIdentificacao) {
		try {
			return getMensagemDAO().getQtdTotalMensagens(usuarioIdentificacao);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}
	
	public Integer getQtdTotalMensagemEnviadas(UsuarioIdentificacao usuarioIdentificacao) {
		try {
			return getMensagemDAO().getQtdTotalMensagemEnviadas(usuarioIdentificacao);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public boolean enviarMensagem(Mensagem mensagem) {
		try{
			getMensagemDAO().save(mensagem);
			if(mensagem.getId() != null){
				return true;
			}else{
				return false;
			}
		}catch (Exception e) {
			log.erro(e);
			return false;
		}
	}

}
