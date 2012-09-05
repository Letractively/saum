package br.com.clarotriagem.entitades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name="triagem_lote")
public class TriagemLote implements Serializable {

	private static final long serialVersionUID = 1564006288666708611L;

	@Id
	@SequenceGenerator(name="TRIAGEM_LOTE_ID_GENERATOR", sequenceName="triagem_lote_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRIAGEM_LOTE_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="data_criacao_lote", nullable=false)
	private Timestamp dataCriacaoLote;

	@Column(nullable=false)
	private Integer quantidade;

	//bi-directional many-to-one association to Triagem
	@OneToMany(mappedBy="triagemLote")
	private List<Triagem> triagems;

	//bi-directional many-to-one association to AparelhoModelo
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="aparelho_modelo", nullable=false)
	private AparelhoModelo aparelhoModelo;

	//bi-directional many-to-one association to Calendario
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="calendario", nullable=false)
	private Calendario calendario;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="warehouse", nullable=false)
	private Cliente cliente;

    public TriagemLote() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDataCriacaoLote() {
		return this.dataCriacaoLote;
	}

	public void setDataCriacaoLote(Timestamp dataCriacaoLote) {
		this.dataCriacaoLote = dataCriacaoLote;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public List<Triagem> getTriagems() {
		return this.triagems;
	}

	public void setTriagems(List<Triagem> triagems) {
		this.triagems = triagems;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public AparelhoModelo getAparelhoModelo() {
		return aparelhoModelo;
	}

	public void setAparelhoModelo(AparelhoModelo aparelhoModelo) {
		this.aparelhoModelo = aparelhoModelo;
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}
	
}