package br.com.clarotriagem.controller;

import java.util.HashMap;
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
public class ClienteAdicionaBean extends BaseBean {

	private static final long serialVersionUID = -2847589072962140236L;

	@Autowired
	private ClienteService clienteService;
	
	private Map<String, String> estados;
	private Cliente cliente;
	
	public String gravaCliente(){
		boolean ret = clienteService.adicionaCliente(cliente);
		if(ret){
			addInfoMessage(getBunde("cliente_inserido_sucesso"));
			cliente = new Cliente();
		}else{
			addErroMessage(null, getTituloApp(), getBunde("erro_inesperado"));
		}
		return null;
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

	public Cliente getCliente() {
		if(cliente == null){
			cliente = new Cliente();
		}
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setEstados(Map<String, String> estados) {
		this.estados = estados;
	}
	
	
}
