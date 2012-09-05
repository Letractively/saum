package br.com.clarotriagem.entitades;

import java.io.Serializable;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="menu_perfil")
public class MenuPerfil implements Serializable {

	private static final long serialVersionUID = 5413129664488621083L;

	@Id
	@SequenceGenerator(name="ID_GENERATOR", sequenceName="MENU_PERFIL_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	//bi-directional many-to-one association to Menu
	@ManyToOne(fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="menu_id", nullable=false)
	private Menu menu;

	//bi-directional many-to-one association to Perfil
	@ManyToOne(fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="perfil_id", nullable=false)
	private Perfil perfil;

    public MenuPerfil() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
}