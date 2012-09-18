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

import br.com.clarotriagem.utils.enums.Familias;

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

	@Column(length=40, name="familias")
	private String familias;

	@Column(nullable=false)
	private Boolean concluido;
	
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
	
	@Transient
	private String descFamilias;
	

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

	public String getFamilias() {
		return familias;
	}

	public void setFamilias(String familias) {
		this.familias = familias;
	}

	public String getDescFamilias() {
		descFamilias = "";
		List<Familias> listaFamilias = Familias.getListaFamilia();
		if(getFamilias() != null && !"".equalsIgnoreCase(getFamilias())){
			String familiasBD = getFamilias();
			if(familiasBD != null && !"".equalsIgnoreCase(familiasBD)){
				String[] f = familiasBD.split(",");
				for (int i = 0; i < f.length; i++) {
					for(Familias fLista : listaFamilias){
						if(!"".equalsIgnoreCase(f[i]) && fLista.getCod().intValue() == new Integer(f[i]).intValue()){
							descFamilias += fLista.getSigla() + ", ";
						}
					}				
				}
			}
		}
		
		return descFamilias.substring(0, descFamilias.length() -1);
	}

	public void setDescFamilias(String descFamilias) {
		this.descFamilias = descFamilias;
	}

}