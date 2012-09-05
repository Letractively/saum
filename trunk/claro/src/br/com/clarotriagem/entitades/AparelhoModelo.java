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


@Entity
@Table(name="aparelho_modelo")
public class AparelhoModelo implements Serializable {

	private static final long serialVersionUID = 8151301231841420187L;

	@Id
	@SequenceGenerator(name="APARELHO_MODELO_ID_GENERATOR", sequenceName="aparelho_modelo_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APARELHO_MODELO_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=50)
	private String nome;

	@Column(nullable=false)
	private Boolean ativo;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="marca", nullable=false)
	private AparelhoMarca aparelhoMarca;

    public AparelhoModelo() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public AparelhoMarca getAparelhoMarca() {
		return this.aparelhoMarca;
	}

	public void setAparelhoMarca(AparelhoMarca aparelhoMarca) {
		this.aparelhoMarca = aparelhoMarca;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}