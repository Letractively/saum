package br.com.clarotriagem.service.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.clarotriagem.entitades.AparelhoMarca;
import br.com.clarotriagem.service.AparelhosService;

public class AparelhoMarcasListLazy extends LazyDataModel<AparelhoMarca> {

	private static final long serialVersionUID = -1887041924801083040L;

	private List<AparelhoMarca> marcas;
	private AparelhosService aparelhosService;

	public AparelhoMarcasListLazy() {
		super();
	}

	public AparelhoMarcasListLazy(AparelhosService aparelhosService) {
		this.aparelhosService = aparelhosService;
	}

	@Override
	public List<AparelhoMarca> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		this.marcas = aparelhosService.buscaMarcasTodas(startingAt, maxPerPage, sortField, sortOrder, filters);

		setRowCount(aparelhosService.getQtdTotalMarcas());

		setPageSize(maxPerPage);

		return marcas;
	}

	@Override
	public Object getRowKey(AparelhoMarca marca) {
		return marca.getId();
	}

	public void setRowIndex(final int rowIndex) {
		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		} else {
			super.setRowIndex(rowIndex % getPageSize());
		}
	}

	@Override
	public AparelhoMarca getRowData(String msgId) {
		try{
			Long id = Long.valueOf(msgId);
		
			for (AparelhoMarca m : marcas) {
				if (id.equals(m.getId())) {
					return m;
				}
			}
			return null;
		}catch (Exception e) {
			return null;
		}
	}

	public List<AparelhoMarca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<AparelhoMarca> marcas) {
		this.marcas = marcas;
	}

	public AparelhosService getAparelhosService() {
		return aparelhosService;
	}

	public void setAparelhosService(AparelhosService aparelhosService) {
		this.aparelhosService = aparelhosService;
	}

}
