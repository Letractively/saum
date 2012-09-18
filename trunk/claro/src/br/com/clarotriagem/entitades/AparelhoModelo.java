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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="aparelho_modelo")
public class AparelhoModelo implements Serializable {

	private static final long serialVersionUID = -2338554267930191728L;

	@Id
	@SequenceGenerator(name="APARELHO_MODELO_ID_GENERATOR", sequenceName="aparelho_modelo_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APARELHO_MODELO_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false)
	private Boolean ativo;

	@Column(nullable=false)
	private Integer familia;

	private Integer identificador1;

	private Integer identificador2;

	private Integer identificador3;

	private Integer identificador4;

	private Integer identificador5;

	@Column(nullable=false, length=50)
	private String nome;

	//bi-directional many-to-one association to AparelhoMarca
	@ManyToOne(fetch=FetchType.EAGER)
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

	public Integer getFamilia() {
		return this.familia;
	}

	public void setFamilia(Integer familia) {
		this.familia = familia;
	}

	public Integer getIdentificador1() {
		return this.identificador1;
	}

	public void setIdentificador1(Integer identificador1) {
		this.identificador1 = identificador1;
	}

	public Integer getIdentificador2() {
		return this.identificador2;
	}

	public void setIdentificador2(Integer identificador2) {
		this.identificador2 = identificador2;
	}

	public Integer getIdentificador3() {
		return this.identificador3;
	}

	public void setIdentificador3(Integer identificador3) {
		this.identificador3 = identificador3;
	}

	public Integer getIdentificador4() {
		return this.identificador4;
	}

	public void setIdentificador4(Integer identificador4) {
		this.identificador4 = identificador4;
	}

	public Integer getIdentificador5() {
		return this.identificador5;
	}

	public void setIdentificador5(Integer identificador5) {
		this.identificador5 = identificador5;
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