package br.com.clarotriagem.entitades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="aparelho_modelo")
public class AparelhoModelo implements Serializable {

	private static final long serialVersionUID = -3312402101252872312L;
 
	@Id
	@SequenceGenerator(name="APARELHO_MODELO_ID_GENERATOR", sequenceName="ADSA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APARELHO_MODELO_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false)
	private Boolean ativo;

	@Column(nullable=false, length=50)
	private String nome;

	//bi-directional many-to-one association to AparelhoMarca
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="marca", nullable=false)
	private AparelhoMarca aparelhoMarca;

	//bi-directional many-to-one association to TriagemLote
	@OneToMany(mappedBy="aparelhoModelo")
	private List<TriagemLote> triagemLotes;

    public AparelhoModelo() {
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

	public AparelhoMarca getAparelhoMarca() {
		return this.aparelhoMarca;
	}

	public void setAparelhoMarca(AparelhoMarca aparelhoMarca) {
		this.aparelhoMarca = aparelhoMarca;
	}
	
	public List<TriagemLote> getTriagemLotes() {
		return this.triagemLotes;
	}

	public void setTriagemLotes(List<TriagemLote> triagemLotes) {
		this.triagemLotes = triagemLotes;
	}
	
}