package br.com.meganet.hbm.vo;

public class Servidor implements java.io.Serializable {

	private static final long serialVersionUID = -2688078964299826122L;
	// Fields

	private Long servidorId;
	private String servidorNome;
	private String servidorDescricao;
	private String servidorLocal;
	private String servidorTorres;
	private Boolean servidorDesativado;

	// Constructors

	/** default constructor */
	public Servidor() {
	}

	/** minimal constructor */
	public Servidor(Long servidorId) {
		this.servidorId = servidorId;
	}

	/** full constructor */
	public Servidor(Long servidorId, String servidorNome,
			String servidorDescricao, String servidorLocal, String servidorTorres) {
		this.servidorId = servidorId;
		this.servidorNome = servidorNome;
		this.servidorDescricao = servidorDescricao;
		this.servidorLocal = servidorLocal;
		this.servidorTorres = servidorTorres;
	}

	// Property accessors

	public Long getServidorId() {
		return this.servidorId;
	}

	public void setServidorId(Long servidorId) {
		this.servidorId = servidorId;
	}

	public String getServidorNome() {
		return this.servidorNome;
	}

	public void setServidorNome(String servidorNome) {
		this.servidorNome = servidorNome;
	}

	public String getServidorDescricao() {
		return this.servidorDescricao;
	}

	public void setServidorDescricao(String servidorDescricao) {
		this.servidorDescricao = servidorDescricao;
	}

	public String getServidorLocal() {
		return this.servidorLocal;
	}

	public void setServidorLocal(String servidorLocal) {
		this.servidorLocal = servidorLocal;
	}
	
	public String getServidorTorres() {
		return servidorTorres;
	}

	public void setServidorTorres(String servidorTorres) {
		this.servidorTorres = servidorTorres;
	}

	public Boolean getServidorDesativado() {
		return servidorDesativado;
	}

	public void setServidorDesativado(Boolean servidorDesativado) {
		this.servidorDesativado = servidorDesativado;
	}

}