package br.com.meganet.hbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class BoletosGerados implements Serializable{

	private static final long serialVersionUID = -8656365638819661779L;
	private Long boletosgeradosId;
	private Long boletosgeradosIdF2B;
	private Usuario usuario;
	private String boletosgeradosValor;
	private String boletosgeradosValorPago;
	private String boletosgeradosValorCreditoDebito;
	private String boletosgeradosResticio;
	private String boletosgeradosMotivoResticio;
	private String boletosgeradosMotivoCreditoDebito;
	private String boletosgeradosUrlBoletoF2b;
	private String boletosgeradosTipoEnvioF2B;
	private Timestamp boletosgeradosDataPagamento;
	private Timestamp boletosgeradosProcessamento;
	private Timestamp boletosgeradosDataVencimento;
	private Timestamp boletosgeradosDataVencimentoProrrogado;
	private Boolean boletosgeradosPago = false;
	private Boolean boletosgeradosDinheiroEntregueAdministradorConta = false;
	private Boolean boletosgeradosPagouEmMao = false;
	private Boolean boletosgeradosBoletoExtra;

	private Double boletosgeradosMulta;
	private Double boletosgeradosJuros;
	private Double boletosgeradosDesconto;
	private Long boletosgeradosLimiteDesconto;

	private String dataTempVencimento;
	private String valorReenvio;
	private String estaPago;
	private String entregueAdm;
	private String dataTempPagamento;
	private Boolean vencido = false;
	private String valorDeveriaSerPago;
	private String valorPagoAoBanco;
	private String valorRecebidoPeloBoletoNaConta;
	private String taxa_pagamento;

	public String getValorDeveriaSerPago() {
		return valorDeveriaSerPago;
	}

	public void setValorDeveriaSerPago(String valorDeveriaSerPago) {
		this.valorDeveriaSerPago = valorDeveriaSerPago;
	}

	public String getEstaPago() {
		return estaPago;
	}

	public void setEstaPago(String estaPago) {
		this.estaPago = estaPago;
	}

	public Boolean getVencido() {
		return vencido;
	}

	public void setVencido(Boolean vencido) {
		this.vencido = vencido;
	}

	public String getDataTempVencimento() {
		return dataTempVencimento;
	}

	public void setDataTempVencimento(String dataTempVencimento) {
		this.dataTempVencimento = dataTempVencimento;
	}

	public String getDataTempPagamento() {
		return dataTempPagamento;
	}

	public void setDataTempPagamento(String dataTempPagamento) {
		this.dataTempPagamento = dataTempPagamento;
	}

	public Boolean getBoletosgeradosPago() {
		return boletosgeradosPago;
	}

	public void setBoletosgeradosPago(Boolean boletosgeradosPago) {
		this.boletosgeradosPago = boletosgeradosPago;
	}

	public Long getBoletosgeradosId() {
		return this.boletosgeradosId;
	}

	public void setBoletosgeradosId(Long boletosgeradosId) {
		this.boletosgeradosId = boletosgeradosId;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getBoletosgeradosValor() {
		return this.boletosgeradosValor;
	}

	public void setBoletosgeradosValor(String boletosgeradosValor) {
		this.boletosgeradosValor = boletosgeradosValor;
	}

	public String getBoletosgeradosValorPago() {
		return this.boletosgeradosValorPago;
	}

	public void setBoletosgeradosValorPago(String boletosgeradosValorPago) {
		this.boletosgeradosValorPago = boletosgeradosValorPago;
	}

	public Timestamp getBoletosgeradosDataPagamento() {
		return this.boletosgeradosDataPagamento;
	}

	public void setBoletosgeradosDataPagamento(Timestamp boletosgeradosDataPagamento) {
		this.boletosgeradosDataPagamento = boletosgeradosDataPagamento;
	}

	public Timestamp getBoletosgeradosProcessamento() {
		return this.boletosgeradosProcessamento;
	}

	public void setBoletosgeradosProcessamento(Timestamp boletosgeradosProcessamento) {
		this.boletosgeradosProcessamento = boletosgeradosProcessamento;
	}

	public Timestamp getBoletosgeradosDataVencimento() {
		return this.boletosgeradosDataVencimento;
	}

	public void setBoletosgeradosDataVencimento(Timestamp boletosgeradosDataVencimento) {
		this.boletosgeradosDataVencimento = boletosgeradosDataVencimento;
	}

	public Timestamp getBoletosgeradosDataVencimentoProrrogado() {
		return boletosgeradosDataVencimentoProrrogado;
	}

	public void setBoletosgeradosDataVencimentoProrrogado(Timestamp boletosgeradosDataVencimentoProrrogado) {
		this.boletosgeradosDataVencimentoProrrogado = boletosgeradosDataVencimentoProrrogado;
	}
	
	public String getBoletosgeradosResticio() {
		return boletosgeradosResticio;
	}

	public void setBoletosgeradosResticio(String boletosgeradosResticio) {
		this.boletosgeradosResticio = boletosgeradosResticio;
	}

	public String getBoletosgeradosMotivoResticio() {
		return boletosgeradosMotivoResticio;
	}

	public void setBoletosgeradosMotivoResticio(String boletosgeradosMotivoResticio) {
		this.boletosgeradosMotivoResticio = boletosgeradosMotivoResticio;
	}

	public String getBoletosgeradosMotivoCreditoDebito() {
		return boletosgeradosMotivoCreditoDebito;
	}

	public void setBoletosgeradosMotivoCreditoDebito(String boletosgeradosMotivoCreditoDebito) {
		this.boletosgeradosMotivoCreditoDebito = boletosgeradosMotivoCreditoDebito;
	}

	public String getBoletosgeradosValorCreditoDebito() {
		return boletosgeradosValorCreditoDebito;
	}

	public void setBoletosgeradosValorCreditoDebito(String boletosgeradosValorCreditoDebito) {
		this.boletosgeradosValorCreditoDebito = boletosgeradosValorCreditoDebito;
	}

	public Boolean getBoletosgeradosPagouEmMao() {
		return boletosgeradosPagouEmMao;
	}

	public void setBoletosgeradosPagouEmMao(Boolean boletosgeradosPagouEmMao) {
		this.boletosgeradosPagouEmMao = boletosgeradosPagouEmMao;
	}

	public Boolean getBoletosgeradosDinheiroEntregueAdministradorConta() {
		return boletosgeradosDinheiroEntregueAdministradorConta;
	}

	public void setBoletosgeradosDinheiroEntregueAdministradorConta(Boolean boletosgeradosDinheiroEntregueAdministradorConta) {
		this.boletosgeradosDinheiroEntregueAdministradorConta = boletosgeradosDinheiroEntregueAdministradorConta;
	}

	public String getEntregueAdm() {
		return entregueAdm;
	}

	public void setEntregueAdm(String entregueAdm) {
		this.entregueAdm = entregueAdm;
	}

	public String getValorPagoAoBanco() {
		return valorPagoAoBanco;
	}

	public void setValorPagoAoBanco(String valorPagoAoBanco) {
		this.valorPagoAoBanco = valorPagoAoBanco;
	}

	public String getValorRecebidoPeloBoletoNaConta() {
		return valorRecebidoPeloBoletoNaConta;
	}

	public void setValorRecebidoPeloBoletoNaConta(String valorRecebidoPeloBoletoNaConta) {
		this.valorRecebidoPeloBoletoNaConta = valorRecebidoPeloBoletoNaConta;
	}

	public Long getBoletosgeradosIdF2B() {
		return boletosgeradosIdF2B;
	}

	public void setBoletosgeradosIdF2B(Long boletosgeradosIdF2B) {
		this.boletosgeradosIdF2B = boletosgeradosIdF2B;
	}

	public String getBoletosgeradosUrlBoletoF2b() {
		return boletosgeradosUrlBoletoF2b;
	}

	public void setBoletosgeradosUrlBoletoF2b(String boletosgeradosUrlBoletoF2b) {
		this.boletosgeradosUrlBoletoF2b = boletosgeradosUrlBoletoF2b;
	}

	public String getTaxa_pagamento() {
		return taxa_pagamento;
	}

	public void setTaxa_pagamento(String taxaPagamento) {
		taxa_pagamento = taxaPagamento;
	}

	public String getBoletosgeradosTipoEnvioF2B() {
		return boletosgeradosTipoEnvioF2B;
	}

	public void setBoletosgeradosTipoEnvioF2B(String boletosgeradosTipoEnvioF2B) {
		this.boletosgeradosTipoEnvioF2B = boletosgeradosTipoEnvioF2B;
	}

	public String getValorReenvio() {
		return valorReenvio;
	}

	public void setValorReenvio(String valorReenvio) {
		this.valorReenvio = valorReenvio;
	}

	public Boolean getBoletosgeradosBoletoExtra() {
		return boletosgeradosBoletoExtra;
	}

	public void setBoletosgeradosBoletoExtra(Boolean boletosgeradosBoletoExtra) {
		this.boletosgeradosBoletoExtra = boletosgeradosBoletoExtra;
	}

	public Double getBoletosgeradosMulta() {
		return boletosgeradosMulta;
	}

	public void setBoletosgeradosMulta(Double boletosgeradosMulta) {
		this.boletosgeradosMulta = boletosgeradosMulta;
	}

	public Double getBoletosgeradosJuros() {
		return boletosgeradosJuros;
	}

	public void setBoletosgeradosJuros(Double boletosgeradosJuros) {
		this.boletosgeradosJuros = boletosgeradosJuros;
	}

	public Double getBoletosgeradosDesconto() {
		return boletosgeradosDesconto;
	}

	public void setBoletosgeradosDesconto(Double boletosgeradosDesconto) {
		this.boletosgeradosDesconto = boletosgeradosDesconto;
	}

	public Long getBoletosgeradosLimiteDesconto() {
		return boletosgeradosLimiteDesconto;
	}

	public void setBoletosgeradosLimiteDesconto(Long boletosgeradosLimiteDesconto) {
		this.boletosgeradosLimiteDesconto = boletosgeradosLimiteDesconto;
	}

}