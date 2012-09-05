package br.com.clarotriagem.entitades;

import java.io.Serializable;
import java.sql.Timestamp;
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
import javax.persistence.Transient;

@Entity
@Table(name="calendario")
public class Calendario implements Serializable {

	private static final long serialVersionUID = 7032330158339457775L;

	@Id
	@SequenceGenerator(name="ID_GENERATOR", sequenceName="CALENDARIO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false)
	private Boolean cancelar;

	@Column(length=80)
	private String contato;

	@Column(name="data_final", nullable=false)
	private Timestamp dataFinal;

	@Column(name="envio_email_atrasado", nullable=false)
	private Boolean envioEmailAtrasado;
	
	@Column(name="envio_email_antecipado", nullable=false)
	private Boolean envioEmailAntecipado;
	
	@Column(name="data_inicial", nullable=false)
	private Timestamp dataInicial;

	@Column(length=2147483647)
	private String descricao;

	@Column(name="familia_av", nullable=false)
	private Boolean familiaAv;

	@Column(nullable=false)
	private Boolean concluido;
	
	@Column(name="familia_cam", nullable=false)
	private Boolean familiaCam;

	@Column(name="familia_el", nullable=false)
	private Boolean familiaEl;

	@Column(name="familia_ha", nullable=false)
	private Boolean familiaHa;

	@Column(name="familia_info", nullable=false)
	private Boolean familiaInfo;

	@Column(name="familia_it", nullable=false)
	private Boolean familiaIt;

	@Column(name="familia_lb", nullable=false)
	private Boolean familiaLb;

	@Column(name="familia_mp3", nullable=false)
	private Boolean familiaMp3;

	@Column(name="familia_pc", nullable=false)
	private Boolean familiaPc;

	@Column(name="familia_sc_cr", nullable=false)
	private Boolean familiaScCr;

	@Column(name="familia_tc", nullable=false)
	private Boolean familiaTc;

	@Column(name="familia_tf", nullable=false)
	private Boolean familiaTf;

	@Column(name="familia_tm", nullable=false)
	private Boolean familiaTm;

	private Integer quantidade;

	@Column(name="tipo_acao")
	private Integer tipoAcao;

	@Column(name="tipo_atendimento")
	private Integer tipoAtendimento;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="warehouse", nullable=false)
	private Cliente warehouse;

	//bi-directional many-to-one association to UsuarioIdentificacao
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="usuario_responsavel", nullable=false)
	private UsuarioIdentificacao usuarioResponsavel;

	//bi-directional many-to-one association to UsuarioIdentificacao
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="usuario_abriu", nullable=false)
	private UsuarioIdentificacao usuarioAbriu;
	
	//bi-directional many-to-one association to TriagemLote
	@OneToMany(mappedBy="calendario", fetch=FetchType.LAZY)
	private List<TriagemLote> triagemLotes;

	@Transient
	private int qtdLotes;
	

    public Calendario() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getCancelar() {
		return this.cancelar;
	}

	public void setCancelar(Boolean cancelar) {
		this.cancelar = cancelar;
	}

	public String getContato() {
		return this.contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Timestamp getDataFinal() {
		return this.dataFinal;
	}

	public void setDataFinal(Timestamp dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Timestamp getDataInicial() {
		return this.dataInicial;
	}

	public void setDataInicial(Timestamp dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getFamiliaAv() {
		return this.familiaAv;
	}

	public void setFamiliaAv(Boolean familiaAv) {
		this.familiaAv = familiaAv;
	}

	public Boolean getFamiliaCam() {
		return this.familiaCam;
	}

	public void setFamiliaCam(Boolean familiaCam) {
		this.familiaCam = familiaCam;
	}

	public Boolean getFamiliaEl() {
		return this.familiaEl;
	}

	public void setFamiliaEl(Boolean familiaEl) {
		this.familiaEl = familiaEl;
	}

	public Boolean getFamiliaHa() {
		return this.familiaHa;
	}

	public void setFamiliaHa(Boolean familiaHa) {
		this.familiaHa = familiaHa;
	}

	public Boolean getFamiliaInfo() {
		return this.familiaInfo;
	}

	public void setFamiliaInfo(Boolean familiaInfo) {
		this.familiaInfo = familiaInfo;
	}

	public Boolean getFamiliaIt() {
		return this.familiaIt;
	}

	public void setFamiliaIt(Boolean familiaIt) {
		this.familiaIt = familiaIt;
	}

	public Boolean getFamiliaLb() {
		return this.familiaLb;
	}

	public void setFamiliaLb(Boolean familiaLb) {
		this.familiaLb = familiaLb;
	}

	public Boolean getFamiliaMp3() {
		return this.familiaMp3;
	}

	public void setFamiliaMp3(Boolean familiaMp3) {
		this.familiaMp3 = familiaMp3;
	}

	public Boolean getFamiliaPc() {
		return this.familiaPc;
	}

	public void setFamiliaPc(Boolean familiaPc) {
		this.familiaPc = familiaPc;
	}

	public Boolean getFamiliaScCr() {
		return this.familiaScCr;
	}

	public void setFamiliaScCr(Boolean familiaScCr) {
		this.familiaScCr = familiaScCr;
	}

	public Boolean getFamiliaTc() {
		return this.familiaTc;
	}

	public void setFamiliaTc(Boolean familiaTc) {
		this.familiaTc = familiaTc;
	}

	public Boolean getFamiliaTf() {
		return this.familiaTf;
	}

	public void setFamiliaTf(Boolean familiaTf) {
		this.familiaTf = familiaTf;
	}

	public Boolean getFamiliaTm() {
		return this.familiaTm;
	}

	public void setFamiliaTm(Boolean familiaTm) {
		this.familiaTm = familiaTm;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getTipoAcao() {
		return this.tipoAcao;
	}

	public void setTipoAcao(Integer tipoAcao) {
		this.tipoAcao = tipoAcao;
	}

	public Integer getTipoAtendimento() {
		return this.tipoAtendimento;
	}

	public void setTipoAtendimento(Integer tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public Cliente getWarehouse() {
		if(warehouse == null){
			warehouse = new Cliente();
		}
		return warehouse;
	}

	public void setWarehouse(Cliente warehouse) {
		this.warehouse = warehouse;
	}

	public UsuarioIdentificacao getUsuarioResponsavel() {
		if(usuarioResponsavel == null){
			usuarioResponsavel = new UsuarioIdentificacao();
		}
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(UsuarioIdentificacao usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public UsuarioIdentificacao getUsuarioAbriu() {
		return usuarioAbriu;
	}

	public void setUsuarioAbriu(UsuarioIdentificacao usuarioAbriu) {
		this.usuarioAbriu = usuarioAbriu;
	}

	public Boolean getConcluido() {
		return concluido;
	}

	public void setConcluido(Boolean concluido) {
		this.concluido = concluido;
	}

	public int getQtdLotes() {
		if(triagemLotes != null){
			qtdLotes = triagemLotes.size();
		}
		return qtdLotes;
	}

	public void setQtdLotes(int qtdLotes) {
		this.qtdLotes = qtdLotes;
	}

	public List<TriagemLote> getTriagemLotes() {
		return triagemLotes;
	}

	public void setTriagemLotes(List<TriagemLote> triagemLotes) {
		this.triagemLotes = triagemLotes;
	}

	public Boolean getEnvioEmailAtrasado() {
		return envioEmailAtrasado;
	}

	public void setEnvioEmailAtrasado(Boolean envioEmailAtrasado) {
		this.envioEmailAtrasado = envioEmailAtrasado;
	}

	public Boolean getEnvioEmailAntecipado() {
		return envioEmailAntecipado;
	}

	public void setEnvioEmailAntecipado(Boolean envioEmailAntecipado) {
		this.envioEmailAntecipado = envioEmailAntecipado;
	}

}