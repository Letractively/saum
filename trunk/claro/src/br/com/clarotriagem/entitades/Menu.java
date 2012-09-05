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
@Table(name="menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = 7143056253319647605L;

	@Id
	@SequenceGenerator(name="ID_GENERATOR", sequenceName="MENU_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_GENERATOR")
	@Column(name="menu_id", unique=true, nullable=false)
	private Long menuId;

	@Column(nullable=false)
	private Boolean ativo;

	@Column(nullable=false)
	private Boolean checked;

	@Column(nullable=false)
	private Integer coluna;

	@Column(nullable=false)
	private Integer ordem;
	
	@Column(nullable=false, length=100)
	private String nome;

	@Column(nullable=false)
	private Boolean publico;

	@Column(nullable=false, length=250)
	private String url;

	//bi-directional many-to-one association to MenuPerfil
	@OneToMany(mappedBy="menu", fetch=FetchType.LAZY)
	private List<MenuPerfil> menuPerfils;

    public Menu() {
    }

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getChecked() {
		return this.checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Integer getColuna() {
		return this.coluna;
	}

	public void setColuna(Integer coluna) {
		this.coluna = coluna;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getPublico() {
		return this.publico;
	}

	public void setPublico(Boolean publico) {
		this.publico = publico;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuPerfil> getMenuPerfils() {
		return this.menuPerfils;
	}

	public void setMenuPerfils(List<MenuPerfil> menuPerfils) {
		this.menuPerfils = menuPerfils;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
	
}