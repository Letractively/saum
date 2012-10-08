package br.com.meganet.hbm.vo;

import java.io.Serializable;

public class InfBoleto implements Serializable {

	private static final long serialVersionUID = 8894676328033980991L;
	// Fields

	private Long infboletoId;
	private Integer infboletoBanco;
	private String infboletoLocalPagamentoLn1;
	private String infboletoNumeroConvenio;
	private String infboletoInstrucao1;
	private String infboletoInstrucao2;
	private String infboletoInstrucao3;
	private String infboletoAgencia;
	private String infboletoConta;
	private String infboletoNome;
	private String infboletoUsr;
	private String infboletoSen;
	private String infboletoCnpj;
	private String infboletoCarteira;

	private String infboletoCodigoFornecidoAgenciaDV;
	private String infboletoCodigoFornecidoAgencia;
	private String infboletoCodigoOperacao;
	private String infboletoCodigoCliente;

	private Integer infboletoNumeroboletoPosIni;
	private Integer infboletoNumeroboletoPosFim;
	private Integer infboletoValorpagoaobancoPosIni;
	private Integer infboletoValorpagoaobancoPosFim;
	private Integer infboletoValorcreditadoPosIni;
	private Integer infboletoValorcreditadoPosFim;
	private Integer infboletoDatapagamentoPosIni;
	private Integer infboletoDatapagamentoPosFim;
	private String infboletoDatapagamentoMascara;
	
	// Property accessors
	public Long getInfboletoId() {
		return this.infboletoId;
	}

	public void setInfboletoId(Long infboletoId) {
		this.infboletoId = infboletoId;
	}

	public String getInfboletoLocalPagamentoLn1() {
		return this.infboletoLocalPagamentoLn1;
	}

	public void setInfboletoLocalPagamentoLn1(String infboletoLocalPagamentoLn1) {
		this.infboletoLocalPagamentoLn1 = infboletoLocalPagamentoLn1;
	}

	public String getInfboletoInstrucao1() {
		return this.infboletoInstrucao1;
	}

	public void setInfboletoInstrucao1(String infboletoInstrucao1) {
		this.infboletoInstrucao1 = infboletoInstrucao1;
	}

	public String getInfboletoInstrucao2() {
		return this.infboletoInstrucao2;
	}

	public void setInfboletoInstrucao2(String infboletoInstrucao2) {
		this.infboletoInstrucao2 = infboletoInstrucao2;
	}

	public String getInfboletoAgencia() {
		return this.infboletoAgencia;
	}

	public void setInfboletoAgencia(String infboletoAgencia) {
		this.infboletoAgencia = infboletoAgencia;
	}

	public String getInfboletoConta() {
		return this.infboletoConta;
	}

	public void setInfboletoConta(String infboletoConta) {
		this.infboletoConta = infboletoConta;
	}

	public String getInfboletoNome() {
		return this.infboletoNome;
	}

	public void setInfboletoNome(String infboletoNome) {
		this.infboletoNome = infboletoNome;
	}

	public String getInfboletoCnpj() {
		return this.infboletoCnpj;
	}

	public void setInfboletoCnpj(String infboletoCnpj) {
		this.infboletoCnpj = infboletoCnpj;
	}

	public String getInfboletoInstrucao3() {
		return infboletoInstrucao3;
	}

	public void setInfboletoInstrucao3(String infboletoInstrucao3) {
		this.infboletoInstrucao3 = infboletoInstrucao3;
	}

	public Integer getInfboletoBanco() {
		return infboletoBanco;
	}

	public void setInfboletoBanco(Integer infboletoBanco) {
		this.infboletoBanco = infboletoBanco;
	}

	public String getInfboletoUsr() {
		return infboletoUsr;
	}

	public void setInfboletoUsr(String infboletoUsr) {
		this.infboletoUsr = infboletoUsr;
	}

	public String getInfboletoSen() {
		return infboletoSen;
	}

	public void setInfboletoSen(String infboletoSen) {
		this.infboletoSen = infboletoSen;
	}

	public Integer getInfboletoValorpagoaobancoPosIni() {
		return infboletoValorpagoaobancoPosIni;
	}

	public void setInfboletoValorpagoaobancoPosIni(Integer infboletoValorpagoaobancoPosIni) {
		this.infboletoValorpagoaobancoPosIni = infboletoValorpagoaobancoPosIni;
	}

	public Integer getInfboletoValorpagoaobancoPosFim() {
		return infboletoValorpagoaobancoPosFim;
	}

	public void setInfboletoValorpagoaobancoPosFim(Integer infboletoValorpagoaobancoPosFim) {
		this.infboletoValorpagoaobancoPosFim = infboletoValorpagoaobancoPosFim;
	}

	public Integer getInfboletoValorcreditadoPosIni() {
		return infboletoValorcreditadoPosIni;
	}

	public void setInfboletoValorcreditadoPosIni(Integer infboletoValorcreditadoPosIni) {
		this.infboletoValorcreditadoPosIni = infboletoValorcreditadoPosIni;
	}

	public Integer getInfboletoValorcreditadoPosFim() {
		return infboletoValorcreditadoPosFim;
	}

	public void setInfboletoValorcreditadoPosFim(Integer infboletoValorcreditadoPosFim) {
		this.infboletoValorcreditadoPosFim = infboletoValorcreditadoPosFim;
	}

	public Integer getInfboletoDatapagamentoPosIni() {
		return infboletoDatapagamentoPosIni;
	}

	public void setInfboletoDatapagamentoPosIni(Integer infboletoDatapagamentoPosIni) {
		this.infboletoDatapagamentoPosIni = infboletoDatapagamentoPosIni;
	}

	public Integer getInfboletoDatapagamentoPosFim() {
		return infboletoDatapagamentoPosFim;
	}

	public void setInfboletoDatapagamentoPosFim(Integer infboletoDatapagamentoPosFim) {
		this.infboletoDatapagamentoPosFim = infboletoDatapagamentoPosFim;
	}

	public String getInfboletoDatapagamentoMascara() {
		return infboletoDatapagamentoMascara;
	}

	public void setInfboletoDatapagamentoMascara(String infboletoDatapagamentoMascara) {
		this.infboletoDatapagamentoMascara = infboletoDatapagamentoMascara;
	}

	public Integer getInfboletoNumeroboletoPosIni() {
		return infboletoNumeroboletoPosIni;
	}

	public void setInfboletoNumeroboletoPosIni(Integer infboletoNumeroboletoPosIni) {
		this.infboletoNumeroboletoPosIni = infboletoNumeroboletoPosIni;
	}

	public Integer getInfboletoNumeroboletoPosFim() {
		return infboletoNumeroboletoPosFim;
	}

	public void setInfboletoNumeroboletoPosFim(Integer infboletoNumeroboletoPosFim) {
		this.infboletoNumeroboletoPosFim = infboletoNumeroboletoPosFim;
	}

	public String getInfboletoNumeroConvenio() {
		return infboletoNumeroConvenio;
	}

	public void setInfboletoNumeroConvenio(String infboletoNumeroConvenio) {
		this.infboletoNumeroConvenio = infboletoNumeroConvenio;
	}

	public String getInfboletoCarteira() {
		return infboletoCarteira;
	}

	public void setInfboletoCarteira(String infboletoCarteira) {
		this.infboletoCarteira = infboletoCarteira;
	}

	public String getInfboletoCodigoFornecidoAgenciaDV() {
		return infboletoCodigoFornecidoAgenciaDV;
	}

	public void setInfboletoCodigoFornecidoAgenciaDV(
			String infboletoCodigoFornecidoAgenciaDV) {
		this.infboletoCodigoFornecidoAgenciaDV = infboletoCodigoFornecidoAgenciaDV;
	}

	public String getInfboletoCodigoFornecidoAgencia() {
		return infboletoCodigoFornecidoAgencia;
	}

	public void setInfboletoCodigoFornecidoAgencia(
			String infboletoCodigoFornecidoAgencia) {
		this.infboletoCodigoFornecidoAgencia = infboletoCodigoFornecidoAgencia;
	}

	public String getInfboletoCodigoOperacao() {
		return infboletoCodigoOperacao;
	}

	public void setInfboletoCodigoOperacao(String infboletoCodigoOperacao) {
		this.infboletoCodigoOperacao = infboletoCodigoOperacao;
	}

	public String getInfboletoCodigoCliente() {
		return infboletoCodigoCliente;
	}

	public void setInfboletoCodigoCliente(String infboletoCodigoCliente) {
		this.infboletoCodigoCliente = infboletoCodigoCliente;
	}
}