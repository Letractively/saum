package br.com.clarotriagem.entitades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="aparelho_marca")
public class AparelhoMarca implements Serializable {

	private static final long serialVersionUID = 8953905011737158525L;

	@Id
	@SequenceGenerator(name="APARELHO_MARCA_ID_GENERATOR", sequenceName="aparelho_marca_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APARELHO_MARCA_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false)
	private Boolean ativo;

	@Column(length=40)
	private String nome;

	//bi-directional many-to-one association to AparelhoModelo
	@OneToMany(mappedBy="aparelhoMarca", fetch=FetchType.LAZY)
	private List<AparelhoModelo> aparelhoModelos;

	public AparelhoMarca(Long id) {
		this.id = id;
	}
    public AparelhoMarca() {
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

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<AparelhoModelo> getAparelhoModelos() {
		return this.aparelhoModelos;
	}

	public void setAparelhoModelos(List<AparelhoModelo> aparelhoModelos) {
		this.aparelhoModelos = aparelhoModelos;
	}
	
}