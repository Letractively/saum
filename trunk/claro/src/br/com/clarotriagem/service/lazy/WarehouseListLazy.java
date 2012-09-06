package br.com.clarotriagem.service.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.clarotriagem.entitades.Cliente;
import br.com.clarotriagem.service.ClienteService;

public class WarehouseListLazy extends LazyDataModel<Cliente> {

	private static final long serialVersionUID = -1887041924801083040L;

	private List<Cliente> listaClientes;
	private ClienteService clienteService;


	public WarehouseListLazy(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Override
	public List<Cliente> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		this.listaClientes = clienteService.buscaTodosWarehouse(startingAt, maxPerPage, sortField, sortOrder);

		if (getRowCount() <= 0) {
			setRowCount(clienteService.getQtdTotalWarehouse());
		}

		setPageSize(maxPerPage);
		return listaClientes;
	}

	public void setRowIndex(final int rowIndex) {
		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		} else {
			super.setRowIndex(rowIndex % getPageSize());
		}
	}

	@Override
	public Object getRowKey(Cliente cliente) {
		return cliente.getId();
	}

	@Override
	public Cliente getRowData(String msgId) {
		Long id = Long.valueOf(msgId);

		for (Cliente usr : listaClientes) {
			if (id.equals(usr.getId())) {
				return usr;
			}
		}
		return null;
	}

}
