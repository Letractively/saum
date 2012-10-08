package br.com.meganet.hbm.vo;


public class Torre implements java.io.Serializable {

	private static final long serialVersionUID = 8135185644858793171L;
	// Fields

	private Long torreId;
	private String torreNome;
	private String torreDescricao;
	private String torreModelo;
	private String torreProcessador;
	private String torreMemoriaRam;
	private String torreEspacoDisco;
	private String torreLocal;
	private String torreUsuario;
	private String torreSenha;
	private String torreIpConexao;
	private String torreIpConexao2;
	private Integer torrePorta;
	private Boolean torreInterfaceCliente;
	private String torreNomeInterface;
	private Boolean torreDesativado;
	private String torreIpIntermediador;
	private Integer torrePortaIntermediador;
	private Boolean torreUtilizarIntermediador;

	// Constructors

	/** default constructor */
	public Torre() {
	}

	/** minimal constructor */
	public Torre(Long torreId) {
		this.torreId = torreId;
	}

	/** full constructor */
	public Torre(Long torreId, String torreNome, String torreDescricao,
			String torreModelo, String torreProcessador,
			String torreMemoriaRam, String torreEspacoDisco, String torreLocal,
			String torreUsuario, String torreSenha, String torreIpConexao,
			String torreIpConexao2, Integer torrePorta,
			Boolean torreInterfaceCliente, String torreNomeInterface,
			Boolean torreDesativado, String torreIpIntermediador,
			Integer torrePortaIntermediador, Boolean torreUtilizarIntermediador) {
		this.torreId = torreId;
		this.torreNome = torreNome;
		this.torreDescricao = torreDescricao;
		this.torreModelo = torreModelo;
		this.torreProcessador = torreProcessador;
		this.torreMemoriaRam = torreMemoriaRam;
		this.torreEspacoDisco = torreEspacoDisco;
		this.torreLocal = torreLocal;
		this.torreUsuario = torreUsuario;
		this.torreSenha = torreSenha;
		this.torreIpConexao = torreIpConexao;
		this.torreIpConexao2 = torreIpConexao2;
		this.torrePorta = torrePorta;
		this.torreInterfaceCliente = torreInterfaceCliente;
		this.torreNomeInterface = torreNomeInterface;
		this.torreDesativado = torreDesativado;
		this.torreIpIntermediador = torreIpIntermediador;
		this.torrePortaIntermediador = torrePortaIntermediador;
		this.torreUtilizarIntermediador = torreUtilizarIntermediador;
	}

	// Property accessors

	public Long getTorreId() {
		return this.torreId;
	}

	public void setTorreId(Long torreId) {
		this.torreId = torreId;
	}

	public String getTorreNome() {
		return this.torreNome;
	}

	public void setTorreNome(String torreNome) {
		this.torreNome = torreNome;
	}

	public String getTorreDescricao() {
		return this.torreDescricao;
	}

	public void setTorreDescricao(String torreDescricao) {
		this.torreDescricao = torreDescricao;
	}

	public String getTorreModelo() {
		return this.torreModelo;
	}

	public void setTorreModelo(String torreModelo) {
		this.torreModelo = torreModelo;
	}

	public String getTorreProcessador() {
		return this.torreProcessador;
	}

	public void setTorreProcessador(String torreProcessador) {
		this.torreProcessador = torreProcessador;
	}

	public String getTorreMemoriaRam() {
		return this.torreMemoriaRam;
	}

	public void setTorreMemoriaRam(String torreMemoriaRam) {
		this.torreMemoriaRam = torreMemoriaRam;
	}

	public String getTorreEspacoDisco() {
		return this.torreEspacoDisco;
	}

	public void setTorreEspacoDisco(String torreEspacoDisco) {
		this.torreEspacoDisco = torreEspacoDisco;
	}

	public String getTorreLocal() {
		return this.torreLocal;
	}

	public void setTorreLocal(String torreLocal) {
		this.torreLocal = torreLocal;
	}

	public String getTorreUsuario() {
		return this.torreUsuario;
	}

	public void setTorreUsuario(String torreUsuario) {
		this.torreUsuario = torreUsuario;
	}

	public String getTorreSenha() {
		return this.torreSenha;
	}

	public void setTorreSenha(String torreSenha) {
		this.torreSenha = torreSenha;
	}

	public String getTorreIpConexao() {
		return this.torreIpConexao;
	}

	public void setTorreIpConexao(String torreIpConexao) {
		this.torreIpConexao = torreIpConexao;
	}

	public String getTorreIpConexao2() {
		return this.torreIpConexao2;
	}

	public void setTorreIpConexao2(String torreIpConexao2) {
		this.torreIpConexao2 = torreIpConexao2;
	}

	public Integer getTorrePorta() {
		return this.torrePorta;
	}

	public void setTorrePorta(Integer torrePorta) {
		this.torrePorta = torrePorta;
	}

	public Boolean getTorreInterfaceCliente() {
		return this.torreInterfaceCliente;
	}

	public void setTorreInterfaceCliente(Boolean torreInterfaceCliente) {
		this.torreInterfaceCliente = torreInterfaceCliente;
	}

	public String getTorreNomeInterface() {
		return this.torreNomeInterface;
	}

	public void setTorreNomeInterface(String torreNomeInterface) {
		this.torreNomeInterface = torreNomeInterface;
	}

	public Boolean getTorreDesativado() {
		return this.torreDesativado;
	}

	public void setTorreDesativado(Boolean torreDesativado) {
		this.torreDesativado = torreDesativado;
	}

	public String getTorreIpIntermediador() {
		return this.torreIpIntermediador;
	}

	public void setTorreIpIntermediador(String torreIpIntermediador) {
		this.torreIpIntermediador = torreIpIntermediador;
	}

	public Integer getTorrePortaIntermediador() {
		return this.torrePortaIntermediador;
	}

	public void setTorrePortaIntermediador(Integer torrePortaIntermediador) {
		this.torrePortaIntermediador = torrePortaIntermediador;
	}

	public Boolean getTorreUtilizarIntermediador() {
		return this.torreUtilizarIntermediador;
	}

	public void setTorreUtilizarIntermediador(Boolean torreUtilizarIntermediador) {
		this.torreUtilizarIntermediador = torreUtilizarIntermediador;
	}

}