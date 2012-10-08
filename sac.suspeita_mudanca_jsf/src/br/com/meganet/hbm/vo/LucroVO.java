package br.com.meganet.hbm.vo;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class LucroVO implements Serializable{

	private static final long serialVersionUID = 6119106171967190731L;

	private Double valorTotalBoletos = new Double(0);
	private Double valorTotalBoletosPagos = new Double(0);
	private Double valorTotalBoletosPagosNoBanco = new Double(0);
	private Double valorTotalBoletosPagosEmMao = new Double(0);
	private Double valorGastoNoPeriodo = new Double(0);
	private Double valorLucro = new Double(0);
	
	private Double valorPagoBanco = new Double(0);
	private List<Gasto> gastos = new ArrayList<Gasto>(0);
	
	private int qtdBoletos = 0;
	private int qtdBoletoVencidoNaoPago = 0;
	private int qtdBoletosPagos = 0;

	private int qtdClientesPagaramEmDia = 0;
	private int qtdClientesPagaramAtrasado = 0;
	private int qtdClientesPagaramAdiantado = 0;


	public String getValorTotalBoletos() {
		return NumberFormat.getCurrencyInstance().format(valorTotalBoletos);
	}

	public void setValorTotalBoletos(Double valorTotalBoletos) {
		this.valorTotalBoletos = valorTotalBoletos;
	}

	public String getValorTotalBoletosPagos() {
		return NumberFormat.getCurrencyInstance().format(valorTotalBoletosPagos);
	}

	public void setValorTotalBoletosPagos(Double valorTotalBoletosPagos) {
		this.valorTotalBoletosPagos = valorTotalBoletosPagos;
	}

	public String getValorGastoNoPeriodo() {
		return NumberFormat.getCurrencyInstance().format(valorGastoNoPeriodo);
	}

	public void setValorGastoNoPeriodo(Double valorGastoNoPeriodo) {
		this.valorGastoNoPeriodo = valorGastoNoPeriodo;
	}

	public String getValorPagoBanco() {
		return NumberFormat.getCurrencyInstance().format(valorPagoBanco);
	}

	public void setValorPagoBanco(Double valorPagoBanco) {
		this.valorPagoBanco = valorPagoBanco;
	}

	public List<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}

	public int getQtdBoletos() {
		return qtdBoletos;
	}

	public void setQtdBoletos(int qtdBoletos) {
		this.qtdBoletos = qtdBoletos;
	}

	public int getQtdBoletoVencidoNaoPago() {
		return qtdBoletoVencidoNaoPago;
	}

	public void setQtdBoletoVencidoNaoPago(int qtdBoletoVencidoNaoPago) {
		this.qtdBoletoVencidoNaoPago = qtdBoletoVencidoNaoPago;
	}

	public int getQtdBoletosPagos() {
		return qtdBoletosPagos;
	}

	public void setQtdBoletosPagos(int qtdBoletosPagos) {
		this.qtdBoletosPagos = qtdBoletosPagos;
	}

	public int getQtdClientesPagaramEmDia() {
		return qtdClientesPagaramEmDia;
	}

	public void setQtdClientesPagaramEmDia(int qtdClientesPagaramEmDia) {
		this.qtdClientesPagaramEmDia = qtdClientesPagaramEmDia;
	}

	public int getQtdClientesPagaramAtrasado() {
		return qtdClientesPagaramAtrasado;
	}

	public void setQtdClientesPagaramAtrasado(int qtdClientesPagaramAtrasado) {
		this.qtdClientesPagaramAtrasado = qtdClientesPagaramAtrasado;
	}

	public String getValorTotalBoletosPagosNoBanco() {
		return NumberFormat.getCurrencyInstance().format(valorTotalBoletosPagosNoBanco);
	}

	public void setValorTotalBoletosPagosNoBanco(Double valorTotalBoletosPagosNoBanco) {
		this.valorTotalBoletosPagosNoBanco = valorTotalBoletosPagosNoBanco;
	}

	public String getValorTotalBoletosPagosEmMao() {
		return NumberFormat.getCurrencyInstance().format(valorTotalBoletosPagosEmMao);
	}

	public void setValorTotalBoletosPagosEmMao(Double valorTotalBoletosPagosEmMao) {
		this.valorTotalBoletosPagosEmMao = valorTotalBoletosPagosEmMao;
	}

	public int getQtdClientesPagaramAdiantado() {
		return qtdClientesPagaramAdiantado;
	}

	public void setQtdClientesPagaramAdiantado(int qtdClientesPagaramAdiantado) {
		this.qtdClientesPagaramAdiantado = qtdClientesPagaramAdiantado;
	}

	public String getValorLucro() {
		return NumberFormat.getCurrencyInstance().format(valorLucro);
	}

	public void setValorLucro(Double valorLucro) {
		this.valorLucro = valorLucro;
	}

}
