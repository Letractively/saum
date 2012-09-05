package br.com.clarotriagem.entitades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = -6391564959749412886L;

	@Id
	@SequenceGenerator(name="ID_GENERATOR", sequenceName="EMPRESA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=80)
	private String bairro;

	@Column(length=10)
	private String cep;

	@Column(length=80)
	private String cidade;

	@Column(nullable=false, length=18)
	private String cnpj;

    @Temporal( TemporalType.DATE)
	@Column(name="data_cadastro")
	private Date dataCadastro;

	@Column(length=120)
	private String email;

	@Column(length=150)
	private String endereco;

	@Column(length=2)
	private String estado;

	@Column(length=150)
	private String homepage;

	@Column(name="nome_fantasia", length=150)
	private String nomeFantasia;

	@Column(name="razao_social", length=150)
	private String razaoSocial;

	@Column(name="telefone_1", length=15)
	private String telefone1;

	@Column(name="telefone_2", length=15)
	private String telefone2;

	//bi-directional many-to-one association to UsuarioIdentificacao
	//@OneToMany(mappedBy="empresa")
	@Transient
	private List<UsuarioIdentificacao> usuarioIdentificacaos;

    public Empresa() {
    }

	public Empresa(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getHomepage() {
		return this.homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getNomeFantasia() {
		return this.nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getTelefone1() {
		return this.telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return this.telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public List<UsuarioIdentificacao> getUsuarioIdentificacaos() {
		return this.usuarioIdentificacaos;
	}

	public void setUsuarioIdentificacaos(List<UsuarioIdentificacao> usuarioIdentificacaos) {
		this.usuarioIdentificacaos = usuarioIdentificacaos;
	}
	
}