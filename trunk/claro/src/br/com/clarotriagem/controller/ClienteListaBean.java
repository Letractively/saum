package br.com.clarotriagem.controller;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.entitades.Cliente;
import br.com.clarotriagem.service.ClienteService;
import br.com.clarotriagem.service.lazy.ClientesListLazy;

@ManagedBean
@Scope("view")
@Component
public class ClienteListaBean extends BaseBean {

	private static final long serialVersionUID = 3795049971650536451L;

	@Autowired
	private ClienteService clienteService;
	
	private LazyDataModel<Cliente> clientes;
	private Cliente cliente;

	public LazyDataModel<Cliente> getClientes() {
		if(clientes == null){
			clientes = new ClientesListLazy(clienteService);
		}
		return clientes;
	}
	
	public String ativaDesativaCliente(int opcao){
		if(opcao ==0){
			cliente.setAtivo(false);
		}else{
			cliente.setAtivo(true);
		}
		clienteService.atualizaCliente(cliente);
		return null;
	}

	public void setClientes(LazyDataModel<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
