package br.com.clarotriagem.service.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.clarotriagem.entitades.AparelhoModelo;
import br.com.clarotriagem.service.AparelhosService;

public class AparelhoModelosListLazy extends LazyDataModel<AparelhoModelo> {

	private static final long serialVersionUID = -1887041924801083040L;

	private List<AparelhoModelo> modelos;
	private AparelhosService aparelhosService;
	private Long idMarca;

	public AparelhoModelosListLazy() {
		super();
	}

	public AparelhoModelosListLazy(AparelhosService aparelhosService, Long idMarca) {
		this.aparelhosService = aparelhosService;
		this.idMarca = idMarca;
	}

	@Override
	public List<AparelhoModelo> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, String> filters) {
//		this.modelos = aparelhosService.buscaModelosTodos(startingAt, maxPerPage, sortField, sortOrder, filters, idMarca);

		setRowCount(aparelhosService.getQtdTotalModelos(idMarca));
		setPageSize(maxPerPage);

		return modelos;
	}

	@Override
	public Object getRowKey(AparelhoModelo marca) {
		return marca.getId();
	}

	@Override
	public AparelhoModelo getRowData(String msgId) {
		try{
			Long id = Long.valueOf(msgId);
		
			for (AparelhoModelo m : modelos) {
				if (id.equals(m.getId())) {
					return m;
				}
			}
			return null;
		}catch (Exception e) {
			return null;
		}
	}


	public AparelhosService getAparelhosService() {
		return aparelhosService;
	}

	public void setAparelhosService(AparelhosService aparelhosService) {
		this.aparelhosService = aparelhosService;
	}

	public List<AparelhoModelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<AparelhoModelo> modelos) {
		this.modelos = modelos;
	}

}
