package br.com.meganet.hbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 8643299401714855864L;
	// Fields

	private Long usuarioId;
	private String usuarioNome;
	private String usuarioSenha;
	private String usuarioTelefone1;
	private String usuarioTelefone2;
	private String usuarioEmail;
	private String usuarioEqpComodato;
	private String usuarioMenu;
	private String usuarioCep;
	private String usuarioCpf;
	private String usuarioComplementoEndereco;
	private String motivoDesbloqueio;
	private Long usuarioAtivo;
	private String usuarioMac;
	private Long usuarioDtPagamento;
	private Timestamp usuarioDtAtivacao;
	private Timestamp usuarioDataLimiteDesbloqueio;
	private Timestamp usuarioUltimoEnvioEmail;
	private Short usuarioBloqueado;
	private Boolean usuarioAdministrativo;
	private Boolean usuarioImprimeBoleto;
	private Boolean usuarioAlteraProprioPerfil;

	private String usuarioEndereco;
	private String usuarioBairro;
	private String usuarioCidade;
	private String usuarioEstado;
	private String usuarioIdTrocaSenha;
	private Boolean usuarioEnvioEmailCadastro;
	private Boolean usuarioPodePagarMao;
	
	private String pesquisaF2BInicial;
	private String pesquisaF2BFinal;
	
	private String dataTemp;
	private String dataTemp2;

	private PlanosPacotes planosPacotes;
	private Servidor servidor;
	private EnderecoIp enderecoIp;
	private Contrato contrato;

	private boolean usuarioEnviaEmailMonetario;
	private boolean permanecer;
	private boolean vaiInserir = false;
	
	private boolean pagamentoAtrasado;
	
	private String log;
	private Double lat;
	private Double lng;

	private Set<Demandas> demandases = new HashSet<Demandas>(0);
	private Set<BoletosGerados> boletosGeradoses = new HashSet<BoletosGerados>(0);
	private BoletosGerados boleto = new BoletosGerados();
	
	private Usuario usuarioRealizouAlteracao;

	public Usuario(){
		
	}
	public Usuario(String nome){
		this.usuarioNome = nome;
	}
	public Usuario(Long id){
		this.usuarioId = id;
	}
	
	// Property accessors
	public String getDataTemp2() {
		return dataTemp2;
	}
	
	public void setDataTemp2(String dataTemp2) {
		this.dataTemp2 = dataTemp2;
	}
	
	public Timestamp getUsuarioDataLimiteDesbloqueio() {
		return usuarioDataLimiteDesbloqueio;
	}
	
	public void setUsuarioDataLimiteDesbloqueio(Timestamp usuarioDataLimiteDesbloqueio) {
		this.usuarioDataLimiteDesbloqueio = usuarioDataLimiteDesbloqueio;
	}
	
	public String getUsuarioEndereco() {
		return usuarioEndereco;
	}

	public void setUsuarioEndereco(String usuarioEndereco) {
		this.usuarioEndereco = usuarioEndereco;
	}
	
	public String getUsuarioBairro() {
		return usuarioBairro;
	}

	public void setUsuarioBairro(String usuarioBairro) {
		this.usuarioBairro = usuarioBairro;
	}

	public String getUsuarioCidade() {
		return usuarioCidade;
	}

	public void setUsuarioCidade(String usuarioCidade) {
		this.usuarioCidade = usuarioCidade;
	}

	public String getUsuarioEstado() {
		return usuarioEstado;
	}

	public void setUsuarioEstado(String usuarioEstado) {
		this.usuarioEstado = usuarioEstado;
	}

	public Boolean getUsuarioAdministrativo() {
		return usuarioAdministrativo;
	}

	public void setUsuarioAdministrativo(Boolean usuarioAdministrativo) {
		this.usuarioAdministrativo = usuarioAdministrativo;
	}

	public boolean getPermanecer() {
		return permanecer;
	}

	public void setPermanecer(boolean permanecer) {
		this.permanecer = permanecer;
	}

	public Long getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getDataTemp() {
		return dataTemp;
	}

	public void setDataTemp(String dataTemp) {
		this.dataTemp = dataTemp;
	}

	public PlanosPacotes getPlanosPacotes() {
		return planosPacotes;
	}

	public void setPlanosPacotes(PlanosPacotes planosPacotes) {
		this.planosPacotes = planosPacotes;
	}

	public EnderecoIp getEnderecoIp() {
		return enderecoIp;
	}

	public void setEnderecoIp(EnderecoIp enderecoIp) {
		this.enderecoIp = enderecoIp;
	}

	public String getUsuarioNome() {
		return this.usuarioNome;
	}

	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}

	public String getUsuarioSenha() {
		return this.usuarioSenha;
	}

	public void setUsuarioSenha(String usuarioSenha) {
		this.usuarioSenha = usuarioSenha;
	}

	public String getUsuarioTelefone1() {
		return this.usuarioTelefone1;
	}

	public String getTelefoneAtivo(){
		try{
			if(this.usuarioTelefone1 != null && this.usuarioTelefone1.length() > 10 && this.usuarioTelefone1.contains("(")){
				return this.usuarioTelefone1;
			}else if(this.usuarioTelefone2 != null && this.usuarioTelefone2.length() > 10 && this.usuarioTelefone2.contains("(")){
				return this.usuarioTelefone2;
			}else{
				return "";
			}
		}catch (Exception e) {
			return "";
		}
	}
	
	public void setUsuarioTelefone1(String usuarioTelefone1) {
		this.usuarioTelefone1 = usuarioTelefone1;
	}

	public String getUsuarioTelefone2() {
		return this.usuarioTelefone2;
	}

	public void setUsuarioTelefone2(String usuarioTelefone2) {
		this.usuarioTelefone2 = usuarioTelefone2;
	}

	public String getUsuarioEmail() {
		return this.usuarioEmail;
	}

	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}

	public String getUsuarioMenu() {
		return this.usuarioMenu;
	}

	public void setUsuarioMenu(String usuarioMenu) {
		this.usuarioMenu = usuarioMenu;
	}

	public String getUsuarioCep() {
		return this.usuarioCep;
	}

	public void setUsuarioCep(String usuarioCep) {
		this.usuarioCep = usuarioCep;
	}

	public String getUsuarioCpf() {
		return this.usuarioCpf;
	}

	public void setUsuarioCpf(String usuarioCpf) {
		this.usuarioCpf = usuarioCpf;
	}

	public Long getUsuarioAtivo() {
		return this.usuarioAtivo;
	}

	public void setUsuarioAtivo(Long usuarioAtivo) {
		this.usuarioAtivo = usuarioAtivo;
	}

	public String getUsuarioMac() {
		return this.usuarioMac;
	}

	public void setUsuarioMac(String usuarioMac) {
		this.usuarioMac = usuarioMac;
	}

	public Long getUsuarioDtPagamento() {
		return this.usuarioDtPagamento;
	}

	public void setUsuarioDtPagamento(Long usuarioDtPagamento) {
		this.usuarioDtPagamento = usuarioDtPagamento;
	}

	public Timestamp getUsuarioDtAtivacao() {
		return this.usuarioDtAtivacao;
	}

	public void setUsuarioDtAtivacao(Timestamp usuarioDtAtivacao) {
		this.usuarioDtAtivacao = usuarioDtAtivacao;
	}

	public Short getUsuarioBloqueado() {
		return this.usuarioBloqueado;
	}

	public void setUsuarioBloqueado(Short usuarioBloqueado) {
		this.usuarioBloqueado = usuarioBloqueado;
	}

	public Set<Demandas> getDemandases() {
		return this.demandases;
	}

	public void setDemandases(Set<Demandas> demandases) {
		this.demandases = demandases;
	}

	public Set<BoletosGerados> getBoletosGeradoses() {
		return this.boletosGeradoses;
	}

	public void setBoletosGeradoses(Set<BoletosGerados> boletosGeradoses) {
		this.boletosGeradoses = boletosGeradoses;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public boolean getVaiInserir() {
		return vaiInserir;
	}

	public void setVaiInserir(boolean vaiInserir) {
		this.vaiInserir = vaiInserir;
	}

	public boolean isPagamentoAtrasado() {
		return pagamentoAtrasado;
	}

	public void setPagamentoAtrasado(boolean pagamentoAtrasado) {
		this.pagamentoAtrasado = pagamentoAtrasado;
	}

	public Boolean getUsuarioImprimeBoleto() {
		return usuarioImprimeBoleto;
	}

	public void setUsuarioImprimeBoleto(Boolean usuarioImprimeBoleto) {
		this.usuarioImprimeBoleto = usuarioImprimeBoleto;
	}

	public Boolean getUsuarioEnvioEmailCadastro() {
		return usuarioEnvioEmailCadastro;
	}

	public void setUsuarioEnvioEmailCadastro(Boolean usuarioEnvioEmailCadastro) {
		this.usuarioEnvioEmailCadastro = usuarioEnvioEmailCadastro;
	}

	public String getUsuarioIdTrocaSenha() {
		return usuarioIdTrocaSenha;
	}

	public void setUsuarioIdTrocaSenha(String usuarioIdTrocaSenha) {
		this.usuarioIdTrocaSenha = usuarioIdTrocaSenha;
	}

	public String getMotivoDesbloqueio() {
		return motivoDesbloqueio;
	}

	public void setMotivoDesbloqueio(String motivoDesbloqueio) {
		this.motivoDesbloqueio = motivoDesbloqueio;
	}

	public BoletosGerados getBoleto() {
		return boleto;
	}

	public void setBoleto(BoletosGerados boleto) {
		this.boleto = boleto;
	}

	public String getPesquisaF2BInicial() {
		return pesquisaF2BInicial;
	}

	public void setPesquisaF2BInicial(String pesquisaF2BInicial) {
		this.pesquisaF2BInicial = pesquisaF2BInicial;
	}

	public String getPesquisaF2BFinal() {
		return pesquisaF2BFinal;
	}

	public void setPesquisaF2BFinal(String pesquisaF2BFinal) {
		this.pesquisaF2BFinal = pesquisaF2BFinal;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Boolean getUsuarioPodePagarMao() {
		return usuarioPodePagarMao;
	}

	public void setUsuarioPodePagarMao(Boolean usuarioPodePagarMao) {
		this.usuarioPodePagarMao = usuarioPodePagarMao;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}
	public boolean getUsuarioEnviaEmailMonetario() {
		return usuarioEnviaEmailMonetario;
	}
	public void setUsuarioEnviaEmailMonetario(boolean usuarioEnviaEmailMonetario) {
		this.usuarioEnviaEmailMonetario = usuarioEnviaEmailMonetario;
	}
	public String getUsuarioComplementoEndereco() {
		return usuarioComplementoEndereco;
	}
	public void setUsuarioComplementoEndereco(String usuarioComplementoEndereco) {
		this.usuarioComplementoEndereco = usuarioComplementoEndereco;
	}
	public String getUsuarioEqpComodato() {
		return usuarioEqpComodato;
	}
	public void setUsuarioEqpComodato(String usuarioEqpComodato) {
		this.usuarioEqpComodato = usuarioEqpComodato;
	}
	public Timestamp getUsuarioUltimoEnvioEmail() {
		return usuarioUltimoEnvioEmail;
	}
	public void setUsuarioUltimoEnvioEmail(Timestamp usuarioUltimoEnvioEmail) {
		this.usuarioUltimoEnvioEmail = usuarioUltimoEnvioEmail;
	}
	public Servidor getServidor() {
		return servidor;
	}
	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}
	public Boolean getUsuarioAlteraProprioPerfil() {
		return usuarioAlteraProprioPerfil;
	}
	public void setUsuarioAlteraProprioPerfil(Boolean usuarioAlteraProprioPerfil) {
		this.usuarioAlteraProprioPerfil = usuarioAlteraProprioPerfil;
	}
	public Usuario getUsuarioRealizouAlteracao() {
		return usuarioRealizouAlteracao;
	}
	public void setUsuarioRealizouAlteracao(Usuario usuarioRealizouAlteracao) {
		this.usuarioRealizouAlteracao = usuarioRealizouAlteracao;
	}

}