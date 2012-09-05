package br.com.clarotriagem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.entitades.Cliente;
import br.com.clarotriagem.service.ClienteService;
import br.com.clarotriagem.utils.singleton.mapas.ConfigUtil;

@ManagedBean
@Scope("view")
@Component
public class ClienteAlteraBean extends BaseBean {

	private static final long serialVersionUID = -423433077471148762L;

	@Autowired
	private ClienteService clienteService;
	
	private Map<String, String> estados;
	private String termoBusca;
	private Cliente cliente;
	
	public String altearCliente(){
		if(cliente.getId() == null || cliente.getId() <= 0){
			addErroMessage(null, getTituloApp(), getBunde("erro_altera_cliente"));
		}else{
			boolean ret = clienteService.alteraCliente(cliente);
			if(ret){
				addInfoMessage(getBunde("cliente_alterado_sucesso"));
				cliente = new Cliente();
				termoBusca = "";
			}else{
				addErroMessage(null, getTituloApp(), getBunde("erro_inesperado"));
			}
		}
		return null;
	}
	
	public List<String> buscaNomes(String query){
		List<String> ret = clienteService.buscaClientes(query);
		return ret;
	}

	public Cliente getCliente() {
		if(cliente == null){
			cliente = new Cliente();
		}
		return cliente;
	}

	public Map<String, String> getEstados() {
		String est[] = ConfigUtil.getInstance().getProperty("estados", "DF-Distrito Federal").split(";");
		estados = new HashMap<String, String>();
		for (int i = 0; i < est.length; i++) {
			String t[] = est[i].split("-");
			estados.put(t[1], t[0]);
		}
		return estados;
	}

	public void clienteSelecionado(){
		cliente = clienteService.clienteSelecionado(termoBusca);
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTermoBusca() {
		return termoBusca;
	}

	public void setTermoBusca(String termoBusca) {
		this.termoBusca = termoBusca;
	}

	public void setEstados(Map<String, String> estados) {
		this.estados = estados;
	}
	
	
}
