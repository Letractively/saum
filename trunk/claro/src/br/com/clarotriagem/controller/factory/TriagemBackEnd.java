package br.com.clarotriagem.controller.factory;

import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.clarotriagem.controller.BaseBean;
import br.com.clarotriagem.entitades.Triagem;
import br.com.clarotriagem.entitades.TriagemLote;
import br.com.clarotriagem.service.CalendarioService;
import br.com.clarotriagem.service.TriagemService;
import br.com.clarotriagem.utils.enums.AparenciaAparelho;
import br.com.clarotriagem.utils.enums.Operadoras;
import br.com.clarotriagem.utils.enums.SintomaConstatadoAparelho;
import br.com.clarotriagem.utils.enums.SintomaInformadoAparelho;
import br.com.clarotriagem.utils.enums.TiposResultadosTriagem;

/**
 * 
 * @author efren.junior
 * 
 *         Essa classe serve apenas para Validar os dados e declarar variáveis
 *         do formulario na página, separando, assim, a logina de triagem,
 *         validador e controlador
 * 
 */

public class TriagemBackEnd extends BaseBean {

	private static final long serialVersionUID = 8982154360605463893L;

	@Autowired
	protected CalendarioService calendarioService;

	@Autowired
	protected TriagemService triagemService;

	protected TriagemLote triagemLote;
	protected Triagem triagem;

	protected int quantidadeAparelhoTriado;
	protected boolean painelEncolhido;
	protected boolean temTiposSerial1;
	protected boolean temTiposSerial2;
	protected boolean temTiposSerial3;
	protected boolean temTiposSerial4;
	protected String tempoUsoAparelho;
	protected TiposResultadosTriagem resultadoTriagem;
	
	
	//combos
	protected SortedMap<String, Integer> operadorasCombo;
	protected SortedMap<String, Integer> tipoAparenciaAparelho;
	protected SortedMap<String, Integer> sintomaInformado;
	protected SortedMap<String, Integer> sintomaConstatado;
	
	private Operadoras operadoras;
	private AparenciaAparelho aparenciaAparelho;
	private SintomaInformadoAparelho sintomaInformadoAparelho;
	private SintomaConstatadoAparelho sintomaConstatadoAparelho;

	public TriagemLote getTriagemLote() {
		return triagemLote;
	}

	public void setTriagemLote(TriagemLote triagemLote) {
		this.triagemLote = triagemLote;
	}

	public Triagem getTriagem() {
		if (triagem == null) {
			triagem = new Triagem();
		}
		return triagem;
	}
	
	public void setTriagem(Triagem triagem) {
		this.triagem = triagem;
	}

	public boolean isPainelEncolhido() {
		return painelEncolhido;
	}

	public boolean isTemTiposSerial1() {
		return temTiposSerial1;
	}

	public void setTemTiposSerial1(boolean temTiposSerial1) {
		this.temTiposSerial1 = temTiposSerial1;
	}

	public boolean isTemTiposSerial2() {
		return temTiposSerial2;
	}

	public void setTemTiposSerial2(boolean temTiposSerial2) {
		this.temTiposSerial2 = temTiposSerial2;
	}

	public boolean isTemTiposSerial3() {
		return temTiposSerial3;
	}

	public void setTemTiposSerial3(boolean temTiposSerial3) {
		this.temTiposSerial3 = temTiposSerial3;
	}

	public boolean isTemTiposSerial4() {
		return temTiposSerial4;
	}

	public void setTemTiposSerial4(boolean temTiposSerial4) {
		this.temTiposSerial4 = temTiposSerial4;
	}

	public void setPainelEncolhido(boolean painelEncolhido) {
		this.painelEncolhido = painelEncolhido;
	}

	public int getQuantidadeAparelhoTriado() {
		return quantidadeAparelhoTriado;
	}

	public void setQuantidadeAparelhoTriado(int quantidadeAparelhoTriado) {
		this.quantidadeAparelhoTriado = quantidadeAparelhoTriado;
	}

	public String getTempoUsoAparelho() {
		return tempoUsoAparelho;
	}

	public void setTempoUsoAparelho(String tempoUsoAparelho) {
		this.tempoUsoAparelho = tempoUsoAparelho;
	}

	public TiposResultadosTriagem getResultadoTriagem() {
		return resultadoTriagem;
	}

	public void setResultadoTriagem(TiposResultadosTriagem resultadoTriagem) {
		this.resultadoTriagem = resultadoTriagem;
	}

	public Operadoras getOperadoras() {
		return operadoras;
	}

	public void setOperadoras(Operadoras operadoras) {
		this.operadoras = operadoras;
	}

	public AparenciaAparelho getAparenciaAparelho() {
		return aparenciaAparelho;
	}

	public void setAparenciaAparelho(AparenciaAparelho aparenciaAparelho) {
		this.aparenciaAparelho = aparenciaAparelho;
	}

	public SintomaInformadoAparelho getSintomaInformadoAparelho() {
		return sintomaInformadoAparelho;
	}

	public void setSintomaInformadoAparelho(
			SintomaInformadoAparelho sintomaInformadoAparelho) {
		this.sintomaInformadoAparelho = sintomaInformadoAparelho;
	}

	public SintomaConstatadoAparelho getSintomaConstatadoAparelho() {
		return sintomaConstatadoAparelho;
	}

	public void setSintomaConstatadoAparelho(
			SintomaConstatadoAparelho sintomaConstatadoAparelho) {
		this.sintomaConstatadoAparelho = sintomaConstatadoAparelho;
	}

}
