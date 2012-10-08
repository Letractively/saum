package br.com.meganet.servicos.vo;

import java.util.ArrayList;
import java.util.List;

public class Retorno{
	private Sacado sacado;
	private Cliente cliente;
	private Total total;
	private List<Cobranca> cobranca = new ArrayList<Cobranca>();
	private String log;
	private String xmlns;
	
	public Sacado getSacado() {
		return sacado;
	}
	public void setSacado(Sacado sacado) {
		this.sacado = sacado;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getXmlns() {
		return xmlns;
	}
	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}
	public List<Cobranca> getCobranca() {
		return cobranca;
	}
	public void setCobranca(List<Cobranca> cobranca) {
		this.cobranca = cobranca;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Total getTotal() {
		return total;
	}
	public void setTotal(Total total) {
		this.total = total;
	}
}
