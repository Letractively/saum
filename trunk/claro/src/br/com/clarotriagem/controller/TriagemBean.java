package br.com.clarotriagem.controller;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.controller.factory.TriagemMontaFormulario;
import br.com.clarotriagem.controller.validaTriagem.ValidadaTriagem;
import br.com.clarotriagem.entitades.AparelhoModelo;
import br.com.clarotriagem.entitades.Triagem;
import br.com.clarotriagem.utils.enums.AparenciaAparelho;
import br.com.clarotriagem.utils.enums.Operadoras;
import br.com.clarotriagem.utils.enums.SintomaConstatadoAparelho;
import br.com.clarotriagem.utils.enums.SintomaInformadoAparelho;
import br.com.clarotriagem.utils.enums.TiposResultadosTriagem;

/**
 * 
 * @author efren.junior
 * 
 *         Essa classe serve apenas para CONTROLAR o formulario na página,
 *         separando, assim, a logina de triagem, validador e controlador
 * 
 */

@ManagedBean
@Scope("session")
@Component
public class TriagemBean extends TriagemMontaFormulario {

	private static final long serialVersionUID = -3822225104563517958L;

	public TriagemBean() {
		setPainelEncolhido(false);
	}
	
	public String salvaTriagem(){
		triagem.setDoa(getResultadoTriagem().getCod());
		triagem.setUsuarioIdentificacao(getUsuarioLogado());
		triagem.setTriagemLote(getTriagemLote());
		
		AparelhoModelo am = triagemLote.getAparelhoModelo();
		triagem.setIdentificador1Utilizado(am.getId1() != null);
		triagem.setIdentificador2Utilizado(am.getId2() != null);
		triagem.setIdentificador3Utilizado(am.getId3() != null);
		triagem.setIdentificador4Utilizado(am.getId4() != null);
		triagem.setIdentificador5Utilizado(am.getId5() != null);
		
		triagem.setIdentificador1Nome(am.getId1() == null ? null : am.getId1().getDescricao());
		triagem.setIdentificador2Nome(am.getId2() == null ? null : am.getId2().getDescricao());
		triagem.setIdentificador3Nome(am.getId3() == null ? null : am.getId3().getDescricao());
		triagem.setIdentificador4Nome(am.getId4() == null ? null : am.getId4().getDescricao());
		triagem.setIdentificador5Nome(am.getId5() == null ? null : am.getId5().getDescricao());
		
		triagemService.salvaTriagem(triagem);
		
		if(getQuantidadeAparelhoTriado() <= triagemLote.getQuantidade().intValue()){
			triagemService.setTriagemLoteConcluido(triagemLote);
			return "triagem_concluido";
		}else{
			return "triar_aparelho";
		}
	}

	public String verificarDadosParaRevisao(){
		try {
			if(getTempoUsoAparelho() == null || "".equalsIgnoreCase(getTempoUsoAparelho().trim())){
				tempoUsoAparelho = "000:00:00";
			}
			
			if(triagem.getNfTrocaNumero().intValue() == 0){
				triagem.setNfTrocaNumero(null);
			}
			if(triagem.getNfVendaNumero().intValue() == 0){
				triagem.setNfVendaNumero(null);
			}
			
			String[] tempo = tempoUsoAparelho.split(":");
			triagem.setTempoUsoHora(Integer.parseInt(tempo[0]));
			triagem.setTempoUsoMinu(Integer.parseInt(tempo[1]));
			triagem.setTempoUsoSegundos(Integer.parseInt(tempo[2]));
			
			setSintomaConstatadoAparelho(SintomaConstatadoAparelho.getRotuloPorCod(triagem.getSintomaConstatado()));
			setSintomaInformadoAparelho(SintomaInformadoAparelho.getRotuloPorCod(triagem.getSintomaInformado()));
			setOperadoras(Operadoras.getRotuloPorCod(triagem.getOperadora()));
			setAparenciaAparelho(AparenciaAparelho.getRotuloPorCod(triagem.getAparencia()));
			
			return validaDadosInseridos();
		} catch (Exception e) {
			addErroMessage(null, "", e.getCause().getMessage());
			return "triar_aparelho";
		}
	}
	public String iniciarNovoLote(){
		setTriagem(new Triagem());
		setQuantidadeAparelhoTriado(0);
		setPainelEncolhido(false);
		setTemTiposSerial1(false);
		setTemTiposSerial2(false);
		setTemTiposSerial3(false);
		setTemTiposSerial4(false);
		setTempoUsoAparelho("");

		return "triagem_selecao";
	}
	public String voltaParaFormularioCorrgirDados(){
		triagem.setTempoUsoHora(null);
		triagem.setTempoUsoMinu(null);
		triagem.setTempoUsoSegundos(null);
		setResultadoTriagem(null);
		return "triar_aparelho";
	}

	private String validaDadosInseridos() {
		try {
			ValidadaTriagem vt = new ValidadaTriagem();
			setResultadoTriagem(vt.validaDados(triagem, triagemLote, getUsuarioLogado(), triagemService));
			if(getResultadoTriagem().getCod().intValue() == TiposResultadosTriagem.NAO_DOA.getCod().intValue()){
				return "triagem_revisao";
			}else if(getResultadoTriagem().getCod().intValue() == TiposResultadosTriagem.DOA.getCod().intValue()){
				addInfoMessage(null, "DOA", "Aparelho pré-aprovado");
				return "triagem_revisao";
				
			}else if(getResultadoTriagem().getCod().intValue() == TiposResultadosTriagem.INVALIDO.getCod().intValue()){
				return "triagem_revisao";
			}else{
				return "triagem_revisao";
			}
		} catch (Exception e) {
			addErroMessage(null, "", e.getCause().getMessage());
			return null;
		}
	}

	public String loteTriagemSelecionado() {
		setTriagem(new Triagem());
		AparelhoModelo am = triagemLote.getAparelhoModelo();
		getTriagem().setIdentificador1("353940040038861");
		getTriagem().setIdentificador2("rsez837310b");
		getTriagem().setAparelhoBloqueado(true);
		getTriagem().setAutorizacaoCcc(true);
		getTriagem().setAparencia(2);
		getTriagem().setSintomaInformado(6);
		getTriagem().setOperadora(3);
		getTriagem().setSintomaConstatado(6);
		getTriagem().setObservacoes("asdfasd fasd fasd fasd f asdf asd fa sdf asd fa sdf asd f asdf asd f asd f sd fa sdf asd fas df asd fas df asd fa sdf asd fa sdf asd fasd f asd fas dfa sd fa sdfs df asd fa sdf asd f a fs df sd fasd fa sdf asd fa sdf asd fa sdf asd fa sdf as");
		
		
		setTemTiposSerial1(am.getId1() != null);
		setTemTiposSerial2(am.getId2() != null);
		setTemTiposSerial3(am.getId3() != null);
		setTemTiposSerial4(am.getId4() != null);
		return "triar_aparelho";
	}

	public int getQuantidadeAparelhoTriado() {
		setQuantidadeAparelhoTriado(triagemService.getQuantidadeAparelhoTriado(triagemLote.getId()));
		return quantidadeAparelhoTriado;
	}

	public void encolheuPainel() {
		setPainelEncolhido(!isPainelEncolhido());
	}


}
