package br.com.clarotriagem.service.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.UsuarioService;

public class UsuariosListLazy extends LazyDataModel<UsuarioIdentificacao> {

	private static final long serialVersionUID = -1887041924801083040L;

	private List<UsuarioIdentificacao> listaUsuarios;
	private UsuarioService usuarioService;


	public UsuariosListLazy(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@Override
	public List<UsuarioIdentificacao> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		this.listaUsuarios = usuarioService.buscaTodosUsuarios(startingAt, maxPerPage, sortField, sortOrder);

		if (getRowCount() <= 0) {
			setRowCount(usuarioService.getQtdTotalUsuarios());
		}

		setPageSize(maxPerPage);
		return listaUsuarios;
	}

	@Override
	public Object getRowKey(UsuarioIdentificacao usuarioIdentificacao) {
		return usuarioIdentificacao.getId();
	}

	public void setRowIndex(final int rowIndex) {
		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		} else {
			super.setRowIndex(rowIndex % getPageSize());
		}
	}

	@Override
	public UsuarioIdentificacao getRowData(String msgId) {
		Long id = Long.valueOf(msgId);

		for (UsuarioIdentificacao usr : listaUsuarios) {
			if (id.equals(usr.getId())) {
				return usr;
			}
		}
		return null;
	}

	public List<UsuarioIdentificacao> getMensagems() {
		return listaUsuarios;
	}

	public void setMensagems(List<UsuarioIdentificacao> mensagems) {
		this.listaUsuarios = mensagems;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

}
