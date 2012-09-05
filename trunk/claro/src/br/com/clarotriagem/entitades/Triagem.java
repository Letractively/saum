package br.com.clarotriagem.entitades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="triagem")
public class Triagem implements Serializable {

	private static final long serialVersionUID = -2959136537562624573L;

	@Id
	@SequenceGenerator(name="TRIAGEM_ID_GENERATOR", sequenceName="triagem_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRIAGEM_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="aparelho_bloqueado", nullable=false)
	private Boolean aparelhoBloqueado;

	private Integer aparencia;

	@Column(name="autorizacao_ccc", nullable=false)
	private Boolean autorizacaoCcc;

	private Boolean doa;

	@Column(name="identificador_1", length=100)
	private String identificador1;

	@Column(name="identificador_2", length=100)
	private String identificador2;

	@Column(name="identificador_3", length=100)
	private String identificador3;

	@Column(name="identificador_4", length=100)
	private String identificador4;

    @Temporal( TemporalType.DATE)
	@Column(name="nf_troca_data")
	private Date nfTrocaData;

	@Column(name="nf_troca_numero")
	private Long nfTrocaNumero;

    @Temporal( TemporalType.DATE)
	@Column(name="nf_venda_data")
	private Date nfVendaData;

	@Column(name="nf_venda_numero")
	private Long nfVendaNumero;

	@Column(length=2147483647)
	private String observacoes;

	private Integer operadora;

	@Column(name="os_cod_peca_1", length=25)
	private String osCodPeca1;

	@Column(name="os_cod_peca_2", length=25)
	private String osCodPeca2;

	@Column(name="os_cod_peca_3", length=25)
	private String osCodPeca3;

    @Temporal( TemporalType.DATE)
	@Column(name="os_data_abertura_1")
	private Date osDataAbertura1;

    @Temporal( TemporalType.DATE)
	@Column(name="os_data_abertura_2")
	private Date osDataAbertura2;

    @Temporal( TemporalType.DATE)
	@Column(name="os_data_abertura_3")
	private Date osDataAbertura3;

    @Temporal( TemporalType.DATE)
	@Column(name="os_data_entrega_1")
	private Date osDataEntrega1;

    @Temporal( TemporalType.DATE)
	@Column(name="os_data_entrega_2")
	private Date osDataEntrega2;

    @Temporal( TemporalType.DATE)
	@Column(name="os_data_entrega_3")
	private Date osDataEntrega3;

	@Column(name="os_numero_1", length=25)
	private String osNumero1;

	@Column(name="os_numero_2", length=25)
	private String osNumero2;

	@Column(name="os_numero_3", length=25)
	private String osNumero3;

	@Column(name="possui_check_list")
	private Boolean possuiCheckList;

	@Column(name="sintoma_constatado")
	private Integer sintomaConstatado;

	@Column(name="sintoma_informado")
	private Integer sintomaInformado;

	@Column(name="tempo_uso_hora")
	private Integer tempoUsoHora;

	@Column(name="tempo_uso_minu")
	private Integer tempoUsoMinu;

	@Column(name="tempo_uso_segu")
	private Integer tempoUsoSegundos;

	//bi-directional many-to-one association to TriagemLote
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="triagem_lote", nullable=false)
	private TriagemLote triagemLote;

	//bi-directional many-to-one association to UsuarioIdentificacao
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="triador", nullable=false)
	private UsuarioIdentificacao usuarioIdentificacao;

	@OneToMany(mappedBy="triagem", fetch=FetchType.EAGER)
	private List<TriagemAcessorioFalta> triagemAcessorioFaltas;

    public Triagem() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAparelhoBloqueado() {
		return this.aparelhoBloqueado;
	}

	public void setAparelhoBloqueado(Boolean aparelhoBloqueado) {
		this.aparelhoBloqueado = aparelhoBloqueado;
	}

	public Integer getAparencia() {
		return this.aparencia;
	}

	public void setAparencia(Integer aparencia) {
		this.aparencia = aparencia;
	}

	public Boolean getAutorizacaoCcc() {
		return this.autorizacaoCcc;
	}

	public void setAutorizacaoCcc(Boolean autorizacaoCcc) {
		this.autorizacaoCcc = autorizacaoCcc;
	}

	public Boolean getDoa() {
		return this.doa;
	}

	public void setDoa(Boolean doa) {
		this.doa = doa;
	}

	public String getIdentificador1() {
		return this.identificador1;
	}

	public void setIdentificador1(String identificador1) {
		this.identificador1 = identificador1;
	}

	public String getIdentificador2() {
		return this.identificador2;
	}

	public void setIdentificador2(String identificador2) {
		this.identificador2 = identificador2;
	}

	public String getIdentificador3() {
		return this.identificador3;
	}

	public void setIdentificador3(String identificador3) {
		this.identificador3 = identificador3;
	}

	public String getIdentificador4() {
		return this.identificador4;
	}

	public void setIdentificador4(String identificador4) {
		this.identificador4 = identificador4;
	}

	public Date getNfTrocaData() {
		return this.nfTrocaData;
	}

	public void setNfTrocaData(Date nfTrocaData) {
		this.nfTrocaData = nfTrocaData;
	}

	public Long getNfTrocaNumero() {
		return this.nfTrocaNumero;
	}

	public void setNfTrocaNumero(Long nfTrocaNumero) {
		this.nfTrocaNumero = nfTrocaNumero;
	}

	public Date getNfVendaData() {
		return this.nfVendaData;
	}

	public void setNfVendaData(Date nfVendaData) {
		this.nfVendaData = nfVendaData;
	}

	public Long getNfVendaNumero() {
		return this.nfVendaNumero;
	}

	public void setNfVendaNumero(Long nfVendaNumero) {
		this.nfVendaNumero = nfVendaNumero;
	}

	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getOperadora() {
		return this.operadora;
	}

	public void setOperadora(Integer operadora) {
		this.operadora = operadora;
	}

	public String getOsCodPeca1() {
		return this.osCodPeca1;
	}

	public void setOsCodPeca1(String osCodPeca1) {
		this.osCodPeca1 = osCodPeca1;
	}

	public String getOsCodPeca2() {
		return this.osCodPeca2;
	}

	public void setOsCodPeca2(String osCodPeca2) {
		this.osCodPeca2 = osCodPeca2;
	}

	public String getOsCodPeca3() {
		return this.osCodPeca3;
	}

	public void setOsCodPeca3(String osCodPeca3) {
		this.osCodPeca3 = osCodPeca3;
	}

	public Date getOsDataAbertura1() {
		return this.osDataAbertura1;
	}

	public void setOsDataAbertura1(Date osDataAbertura1) {
		this.osDataAbertura1 = osDataAbertura1;
	}

	public Date getOsDataAbertura2() {
		return this.osDataAbertura2;
	}

	public void setOsDataAbertura2(Date osDataAbertura2) {
		this.osDataAbertura2 = osDataAbertura2;
	}

	public Date getOsDataAbertura3() {
		return this.osDataAbertura3;
	}

	public void setOsDataAbertura3(Date osDataAbertura3) {
		this.osDataAbertura3 = osDataAbertura3;
	}

	public Date getOsDataEntrega1() {
		return this.osDataEntrega1;
	}

	public void setOsDataEntrega1(Date osDataEntrega1) {
		this.osDataEntrega1 = osDataEntrega1;
	}

	public Date getOsDataEntrega2() {
		return this.osDataEntrega2;
	}

	public void setOsDataEntrega2(Date osDataEntrega2) {
		this.osDataEntrega2 = osDataEntrega2;
	}

	public Date getOsDataEntrega3() {
		return this.osDataEntrega3;
	}

	public void setOsDataEntrega3(Date osDataEntrega3) {
		this.osDataEntrega3 = osDataEntrega3;
	}

	public String getOsNumero1() {
		return this.osNumero1;
	}

	public void setOsNumero1(String osNumero1) {
		this.osNumero1 = osNumero1;
	}

	public String getOsNumero2() {
		return this.osNumero2;
	}

	public void setOsNumero2(String osNumero2) {
		this.osNumero2 = osNumero2;
	}

	public String getOsNumero3() {
		return this.osNumero3;
	}

	public void setOsNumero3(String osNumero3) {
		this.osNumero3 = osNumero3;
	}

	public Boolean getPossuiCheckList() {
		return this.possuiCheckList;
	}

	public void setPossuiCheckList(Boolean possuiCheckList) {
		this.possuiCheckList = possuiCheckList;
	}

	public Integer getSintomaConstatado() {
		return this.sintomaConstatado;
	}

	public void setSintomaConstatado(Integer sintomaConstatado) {
		this.sintomaConstatado = sintomaConstatado;
	}

	public Integer getSintomaInformado() {
		return this.sintomaInformado;
	}

	public void setSintomaInformado(Integer sintomaInformado) {
		this.sintomaInformado = sintomaInformado;
	}

	public Integer getTempoUsoHora() {
		return this.tempoUsoHora;
	}

	public void setTempoUsoHora(Integer tempoUsoHora) {
		this.tempoUsoHora = tempoUsoHora;
	}

	public Integer getTempoUsoMinu() {
		return this.tempoUsoMinu;
	}

	public void setTempoUsoMinu(Integer tempoUsoMinu) {
		this.tempoUsoMinu = tempoUsoMinu;
	}

	public UsuarioIdentificacao getUsuarioIdentificacao() {
		return this.usuarioIdentificacao;
	}

	public void setUsuarioIdentificacao(UsuarioIdentificacao usuarioIdentificacao) {
		this.usuarioIdentificacao = usuarioIdentificacao;
	}
	
	public List<TriagemAcessorioFalta> getTriagemAcessorioFaltas() {
		return this.triagemAcessorioFaltas;
	}

	public void setTriagemAcessorioFaltas(List<TriagemAcessorioFalta> triagemAcessorioFaltas) {
		this.triagemAcessorioFaltas = triagemAcessorioFaltas;
	}

	public TriagemLote getTriagemLote() {
		return triagemLote;
	}

	public void setTriagemLote(TriagemLote triagemLote) {
		this.triagemLote = triagemLote;
	}

	public Integer getTempoUsoSegundos() {
		return tempoUsoSegundos;
	}

	public void setTempoUsoSegundos(Integer tempoUsoSegundos) {
		this.tempoUsoSegundos = tempoUsoSegundos;
	}
	
}