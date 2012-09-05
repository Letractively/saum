package br.com.clarotriagem.service.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.clarotriagem.entitades.Mensagem;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.MensagemService;

public class MensagemListLazy extends LazyDataModel<Mensagem> {

	private static final long serialVersionUID = -1887041924801083040L;

	private List<Mensagem> mensagems;
	private UsuarioIdentificacao usuarioIdentificacao;

	private MensagemService mensagemService;

	public MensagemListLazy() {
		super();
	}

	public MensagemListLazy(UsuarioIdentificacao usuarioIdentificacao, MensagemService mensagemService) {
		this.usuarioIdentificacao = usuarioIdentificacao;
		this.mensagemService = mensagemService;
	}

	@Override
	public List<Mensagem> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		this.mensagems = mensagemService.buscaMensagemTodas(usuarioIdentificacao, startingAt, maxPerPage, sortField, sortOrder);

		setRowCount(mensagemService.getQtdTotalMensagem(usuarioIdentificacao));

		setPageSize(maxPerPage);

		return mensagems;
	}

	@Override
	public Object getRowKey(Mensagem mensagem) {
		return mensagem.getId();
	}

	@Override
	public Mensagem getRowData(String msgId) {
		try{
			Long id = Long.valueOf(msgId);
		
			for (Mensagem msg : mensagems) {
				if (id.equals(msg.getId())) {
					return msg;
				}
			}
		
			return null;
		}catch (Exception e) {
			return null;
		}
	}

	public List<Mensagem> getMensagems() {
		return mensagems;
	}

	public void setMensagems(List<Mensagem> mensagems) {
		this.mensagems = mensagems;
	}

	public UsuarioIdentificacao getUsuarioIdentificacao() {
		return usuarioIdentificacao;
	}

	public void setUsuarioIdentificacao(UsuarioIdentificacao usuarioIdentificacao) {
		this.usuarioIdentificacao = usuarioIdentificacao;
	}

	public MensagemService getMensagemService() {
		return mensagemService;
	}

	public void setMensagemService(MensagemService mensagemService) {
		this.mensagemService = mensagemService;
	}
}
