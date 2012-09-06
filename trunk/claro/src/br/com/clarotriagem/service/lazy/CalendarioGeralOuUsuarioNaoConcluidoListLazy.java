package br.com.clarotriagem.service.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.clarotriagem.entitades.Calendario;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.CalendarioService;

public class CalendarioGeralOuUsuarioNaoConcluidoListLazy extends
		LazyDataModel<Calendario> {

	private static final long serialVersionUID = -1887041924801083040L;

	private List<Calendario> listaCalendario;
	private CalendarioService calendarioService;
	private boolean buscaTodos;
	private UsuarioIdentificacao usrLogado;

	public CalendarioGeralOuUsuarioNaoConcluidoListLazy(
			CalendarioService calendarioService, boolean buscaTodos,
			UsuarioIdentificacao usrLogado) {
		this.calendarioService = calendarioService;
		this.buscaTodos = buscaTodos;
		this.usrLogado = usrLogado;
	}

	@Override
	public List<Calendario> load(int startingAt, int maxPerPage,
			String sortField, SortOrder sortOrder, Map<String, String> filters) {
		this.listaCalendario = calendarioService.buscaCalendariosAbertos(
				startingAt, maxPerPage, sortField, sortOrder, buscaTodos,
				usrLogado);

		if (getRowCount() <= 0) {
			setRowCount(calendarioService.getQtdTotalCalendario(buscaTodos,
					usrLogado));
		}

		setPageSize(maxPerPage);
		return listaCalendario;
	}

	public void setRowIndex(final int rowIndex) {
		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		} else {
			super.setRowIndex(rowIndex % getPageSize());
		}
	}

	@Override
	public Object getRowKey(Calendario arg) {
		return arg.getId();
	}

	@Override
	public Calendario getRowData(String msgId) {
		Long id = Long.valueOf(msgId);

		for (Calendario cal : listaCalendario) {
			if (id.equals(cal.getId())) {
				return cal;
			}
		}
		return null;
	}

}
