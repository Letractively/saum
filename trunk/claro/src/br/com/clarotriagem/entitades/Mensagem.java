package br.com.clarotriagem.entitades;

import java.io.Serializable;
import java.sql.Timestamp;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name="mensagem")
public class Mensagem implements Serializable {
	
	private static final long serialVersionUID = 8377112595259761562L;

	@Id
	@SequenceGenerator(name="ID_GENERATOR", sequenceName="MENSAGEM_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false)
	private Timestamp data;

	@Column(name="data_leitura")
	private Timestamp dataLeitura;
	
	@Column(nullable=false)
	private Boolean lida;

	@Column(nullable=false)
	private Boolean excluido;
	
	@Column(nullable=false, length=2147483647)
	private String mensagem;
	
	@Transient
	private String mensagemResumo;

	@Column(length=25)
	private String titulo;

	//bi-directional many-to-one association to UsuarioIdentificacao
	@ManyToOne(fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="remetente", nullable=false)
	private UsuarioIdentificacao remetente;

	//bi-directional many-to-one association to UsuarioIdentificacao
	@ManyToOne(fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="destinatario", nullable=false)
	private UsuarioIdentificacao destinatario;

    public Mensagem() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getData() {
		return this.data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public Boolean getLida() {
		return this.lida;
	}

	public void setLida(Boolean lida) {
		this.lida = lida;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public UsuarioIdentificacao getRemetente() {
		return remetente;
	}

	public void setRemetente(UsuarioIdentificacao remetente) {
		this.remetente = remetente;
	}

	public UsuarioIdentificacao getDestinatario() {
		if(destinatario == null){
			destinatario = new UsuarioIdentificacao();
		}
		return destinatario;
	}

	public void setDestinatario(UsuarioIdentificacao destinatario) {
		this.destinatario = destinatario;
	}

	public Boolean getExcluido() {
		return excluido;
	}

	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}

	public String getMensagemResumo() {
		if(mensagem.length() > 50){
			if(mensagem.contains("<") && mensagem.contains("</")){
				mensagemResumo = "******************";
			}else{
				mensagemResumo = mensagem.substring(0, 50) + "...";
			}
		}else{
			mensagemResumo = mensagem;
		}
		
		return mensagemResumo;
	}

	public void setMensagemResumo(String mensagemResumo) {
		this.mensagemResumo = mensagemResumo;
	}

	public Timestamp getDataLeitura() {
		return dataLeitura;
	}

	public void setDataLeitura(Timestamp dataLeitura) {
		this.dataLeitura = dataLeitura;
	}

	
}