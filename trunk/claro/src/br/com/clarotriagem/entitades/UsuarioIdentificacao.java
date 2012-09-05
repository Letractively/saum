package br.com.clarotriagem.entitades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name="usuario_identificacao")
public class UsuarioIdentificacao implements Serializable {

	private static final long serialVersionUID = 7024035374479257229L;

	@Id
	@SequenceGenerator(name="ID_GENERATOR", sequenceName="USUARIO_IDENTIFICACAO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false)
	private Boolean ativo;

	@Column(name="cadastro_confirmado", nullable=false)
	private Boolean cadastroConfirmado;

	@Column(length=80)
	private String bairro;

	@Column(length=80, name="id_confirmacao_cadastro")
	private String idConfirmacaoCadastro;
	
	@Column(name="qtd_login_incorreto", nullable=false)
	private Integer qtdLoginIncorreto;

	@Column(length=10)
	private String cep;

	@Column(length=80)
	private String cidade;

	@Column(nullable=false, length=120)
	private String email;

	@Column(length=150)
	private String endereco;

	@Column(length=2)
	private String estado;

	@Column(length=60)
	private String matricula;

	@Column(nullable=false, length=25)
	private String nome;

	@Column(nullable=false, length=60)
	private String senha;

	@Column(length=120)
	private String sobrenome;

	@Column(name="telefone_1", length=15)
	private String telefone1;

	@Column(name="telefone_2", length=15)
	private String telefone2;
	
	//bi-directional many-to-one association to Empresa
	@ManyToOne(fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="empresa_id_fk")
	private Empresa empresa;

	//bi-directional many-to-one association to Perfil
	@ManyToOne(fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="perfil_id_fk", nullable=false)
	private Perfil perfil;
	
	@Transient
	private List<MenuPerfil> menus;
	
    public UsuarioIdentificacao() {
    }

	public UsuarioIdentificacao(Long id) {
		this.id = id;
	}

	public UsuarioIdentificacao(String email) {
		this.email = email;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSobrenome() {
		return this.sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Perfil getPerfil() {
		if(perfil == null){
			perfil = new Perfil();
		}
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<MenuPerfil> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuPerfil> menus) {
		this.menus = menus;
	}

	public Boolean getCadastroConfirmado() {
		return cadastroConfirmado;
	}

	public void setCadastroConfirmado(Boolean cadastroConfirmado) {
		this.cadastroConfirmado = cadastroConfirmado;
	}

	public String getIdConfirmacaoCadastro() {
		return idConfirmacaoCadastro;
	}

	public void setIdConfirmacaoCadastro(String idConfirmacaoCadastro) {
		this.idConfirmacaoCadastro = idConfirmacaoCadastro;
	}

	public Integer getQtdLoginIncorreto() {
		return qtdLoginIncorreto;
	}

	public void setQtdLoginIncorreto(Integer qtdLoginIncorreto) {
		this.qtdLoginIncorreto = qtdLoginIncorreto;
	}

}