package br.com.clarotriagem.entitades;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="triagem_acessorio_falta")
public class TriagemAcessorioFalta implements Serializable {

	private static final long serialVersionUID = -6352528758135557371L;

	@Id
	@SequenceGenerator(name="TRIAGEM_ACESSORIO_FALTA_ID_GENERATOR", sequenceName="triagem_acessorio_falta_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRIAGEM_ACESSORIO_FALTA_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=100)
	private String acessorio;

	//bi-directional many-to-one association to Triagem
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="triagem", nullable=false)
	private Triagem triagem;

    public TriagemAcessorioFalta() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAcessorio() {
		return this.acessorio;
	}

	public void setAcessorio(String acessorio) {
		this.acessorio = acessorio;
	}

	public Triagem getTriagem() {
		return triagem;
	}

	public void setTriagem(Triagem triagem) {
		this.triagem = triagem;
	}

}