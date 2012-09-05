package br.com.clarotriagem.entitades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="perfil")
public class Perfil implements Serializable {
	
	private static final long serialVersionUID = -6047162475698861947L;

	@Id
	@SequenceGenerator(name="ID_GENERATOR", sequenceName="PERFIL_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_GENERATOR")
	@Column(name="perfil_id", unique=true, nullable=false)
	private Long perfilId;

	@Column(nullable=false)
	private Boolean ativado;

	@Column(length=250)
	private String descricao;

	@Column(length=3)
	private String abreviacao;
	
	@Column(nullable=false, length=100)
	private String nome;

	@Column(name="escala_funcionarios_agenda", nullable=false)
	private Boolean escalaFuncionariosAgenda;
	
	@Column(name="visualiza_todos_agendamentos", nullable=false)
	private Boolean visualizaTodosAgendamentos;
	
	//bi-directional many-to-one association to MenuPerfil
	@OneToMany(mappedBy="perfil", fetch=FetchType.LAZY)
	private List<MenuPerfil> menuPerfils;

	//bi-directional many-to-one association to UsuarioIdentificacao
	@OneToMany(mappedBy="perfil", fetch=FetchType.LAZY)
	private List<UsuarioIdentificacao> usuarioIdentificacaos;

    public Perfil() {
    }

	public Perfil(Long id) {
		this.perfilId = id;
	}

	public Long getPerfilId() {
		return this.perfilId;
	}

	public void setPerfilId(Long perfilId) {
		this.perfilId = perfilId;
	}

	public Boolean getAtivado() {
		return this.ativado;
	}

	public void setAtivado(Boolean ativado) {
		this.ativado = ativado;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<MenuPerfil> getMenuPerfils() {
		return this.menuPerfils;
	}

	public void setMenuPerfils(List<MenuPerfil> menuPerfils) {
		this.menuPerfils = menuPerfils;
	}
	
	public List<UsuarioIdentificacao> getUsuarioIdentificacaos() {
		return this.usuarioIdentificacaos;
	}

	public void setUsuarioIdentificacaos(List<UsuarioIdentificacao> usuarioIdentificacaos) {
		this.usuarioIdentificacaos = usuarioIdentificacaos;
	}

	public String getAbreviacao() {
		return abreviacao;
	}

	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}

	public Boolean getEscalaFuncionariosAgenda() {
		return escalaFuncionariosAgenda;
	}

	public void setEscalaFuncionariosAgenda(Boolean escalaFuncionariosAgenda) {
		this.escalaFuncionariosAgenda = escalaFuncionariosAgenda;
	}

	public Boolean getVisualizaTodosAgendamentos() {
		return visualizaTodosAgendamentos;
	}

	public void setVisualizaTodosAgendamentos(Boolean visualizaTodosAgendamentos) {
		this.visualizaTodosAgendamentos = visualizaTodosAgendamentos;
	}
	
}