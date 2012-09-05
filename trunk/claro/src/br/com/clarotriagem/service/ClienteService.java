package br.com.clarotriagem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.entitades.Cliente;
import br.com.clarotriagem.service.factory.ServiceFactory;
import br.com.clarotriagem.utils.Logger;

@Service("clienteService")
@Transactional
public class ClienteService extends ServiceFactory<ClienteService>{
	
	private final static Logger log = new Logger(ClienteService.class);
	
	public ClienteService(){
		super();
	}

	public boolean adicionaCliente(Cliente cliente){
		try {
			cliente.setAtivo(true);
			getClienteDAO().save(cliente);
			if(cliente != null && cliente.getId() != null){
				return true;
			}
			return false;
		} catch (Exception e) {
			log.erro(e);
			return false;
		}
	}
	
	public boolean alteraCliente(Cliente cliente){
		try {
			getClienteDAO().update(cliente);
			if(cliente != null && cliente.getId() != null){
				return true;
			}
			return false;
		} catch (Exception e) {
			log.erro(e);
			return false;
		}
	}

	public List<String> buscaClientes(String query) {
		try {
			return getClienteDAO().getClientes(query);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public Cliente clienteSelecionado(String nomeCliente) {
		try {
			return getClienteDAO().getCliente(nomeCliente);
		} catch (Exception e) {
			log.erro(e);
			return new Cliente();
		}
	}

	public List<String> getWarehouse(String nomeCliente) {
		try {
			return getClienteDAO().getWarehouse(nomeCliente);
		} catch (Exception e) {
			log.erro(e);
			return new ArrayList<String>();
		}
	}
	
	public SortedMap<String, Long> buscaClientesPai() {
		try {
			return getClienteDAO().buscaClientesPai();
		} catch (Exception e) {
			log.erro(e);
			return new TreeMap<String, Long>();
		}
	}

	public List<Cliente> buscaTodosClientes(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder) {
		try {
			return getClienteDAO().buscaTodosClientes(startingAt, maxPerPage, sortField, sortOrder);
		} catch (Exception e) {
			log.erro(e);
			return new ArrayList<Cliente>();
		}
	}

	public int getQtdTotalCLientes() {
		try {
			return getClienteDAO().getQtdTotalCLientes();
		} catch (Exception e) {
			log.erro(e);
			return 0;
		}
	}
	
	public List<Cliente> buscaTodosWarehouse(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder) {
		try {
			return getClienteDAO().buscaTodosWarehouse(startingAt, maxPerPage, sortField, sortOrder);
		} catch (Exception e) {
			log.erro(e);
			return new ArrayList<Cliente>();
		}
	}
	
	public int getQtdTotalWarehouse() {
		try {
			return getClienteDAO().getQtdTotalWarehouse();
		} catch (Exception e) {
			log.erro(e);
			return 0;
		}
	}

	public void atualizaCliente(Cliente cliente) {
		try {
			getClienteDAO().update(cliente);
		} catch (Exception e) {
			log.erro(e);
		}
	}

	public SortedMap<String, Long> carregaWarehousePeloCliente(Long id) {
		try {
			return getClienteDAO().carregaWarehousePeloCliente(id);
		} catch (Exception e) {
			log.erro(e);
			return new TreeMap<String, Long>();
		}
	}
	
}
