package br.com.clarotriagem.entitades;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


@Entity
@Table(name="aparelho_marca")
public class AparelhoMarca implements Serializable {

	private static final long serialVersionUID = 3658977535605710138L;

	@Id
	@SequenceGenerator(name="APARELHO_MARCA_ID_GENERATOR", sequenceName="aparelho_marca_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APARELHO_MARCA_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false)
	private Boolean ativo;

	@Column(length=20)
	private String imei;

	@Column(length=40)
	private String nome;

	@Column(name="nome_identificador1", length=60)
	private String nomeIdentificador1;

	@Column(name="nome_identificador2", length=60)
	private String nomeIdentificador2;

	@Column(name="nome_identificador3", length=60)
	private String nomeIdentificador3;

	@Column(name="usa_identificador1", nullable=false)
	private Boolean usaIdentificador1;

	@Column(name="usa_identificador2", nullable=false)
	private Boolean usaIdentificador2;

	@Column(name="usa_identificador3", nullable=false)
	private Boolean usaIdentificador3;

	@Column(name="usa_os1", nullable=false)
	private Boolean usaOs1;

	@Column(name="usa_os2", nullable=false)
	private Boolean usaOs2;

	@Column(name="usa_os3", nullable=false)
	private Boolean usaOs3;

	//bi-directional many-to-one association to AparelhoModelo
	@OneToMany(mappedBy="aparelhoMarca")
	private List<AparelhoModelo> aparelhoModelos;

    public AparelhoMarca() {
    }
    
	public AparelhoMarca(Long id) {
		this.id = id;
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

	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeIdentificador1() {
		return this.nomeIdentificador1;
	}

	public void setNomeIdentificador1(String nomeIdentificador1) {
		this.nomeIdentificador1 = nomeIdentificador1;
	}

	public String getNomeIdentificador2() {
		return this.nomeIdentificador2;
	}

	public void setNomeIdentificador2(String nomeIdentificador2) {
		this.nomeIdentificador2 = nomeIdentificador2;
	}

	public String getNomeIdentificador3() {
		return this.nomeIdentificador3;
	}

	public void setNomeIdentificador3(String nomeIdentificador3) {
		this.nomeIdentificador3 = nomeIdentificador3;
	}

	public Boolean getUsaIdentificador1() {
		return this.usaIdentificador1;
	}

	public void setUsaIdentificador1(Boolean usaIdentificador1) {
		this.usaIdentificador1 = usaIdentificador1;
	}

	public Boolean getUsaIdentificador2() {
		return this.usaIdentificador2;
	}

	public void setUsaIdentificador2(Boolean usaIdentificador2) {
		this.usaIdentificador2 = usaIdentificador2;
	}

	public Boolean getUsaIdentificador3() {
		return this.usaIdentificador3;
	}

	public void setUsaIdentificador3(Boolean usaIdentificador3) {
		this.usaIdentificador3 = usaIdentificador3;
	}

	public Boolean getUsaOs1() {
		return this.usaOs1;
	}

	public void setUsaOs1(Boolean usaOs1) {
		this.usaOs1 = usaOs1;
	}

	public Boolean getUsaOs2() {
		return this.usaOs2;
	}

	public void setUsaOs2(Boolean usaOs2) {
		this.usaOs2 = usaOs2;
	}

	public Boolean getUsaOs3() {
		return this.usaOs3;
	}

	public void setUsaOs3(Boolean usaOs3) {
		this.usaOs3 = usaOs3;
	}

	public List<AparelhoModelo> getAparelhoModelos() {
		return this.aparelhoModelos;
	}

	public void setAparelhoModelos(List<AparelhoModelo> aparelhoModelos) {
		this.aparelhoModelos = aparelhoModelos;
	}
	
}