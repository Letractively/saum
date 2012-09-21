package br.com.clarotriagem.controller;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.controller.factory.TriagemMontaFormulario;
import br.com.clarotriagem.controller.validaTriagem.ValidadaTriagem;
import br.com.clarotriagem.entitades.AparelhoModelo;
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
		
		triagemService.salvaTriagem(triagem);
		
		return "triar_aparelho";
	}
	public String verificarDadosParaRevisao(){
		try {
			String[] tempo = tempoUsoAparelho.split(":");
			triagem.setTempoUsoHora(Integer.parseInt(tempo[0]));
			triagem.setTempoUsoMinu(Integer.parseInt(tempo[1]));
			triagem.setTempoUsoSegundos(Integer.parseInt(tempo[2]));
			return validaDadosInseridos();
		} catch (Exception e) {
			addErroMessage(null, "", e.getCause().getMessage());
			return "triar_aparelho";
		}
	}

	private String validaDadosInseridos() {
		try {
			ValidadaTriagem vt = new ValidadaTriagem();
			setResultadoTriagem(vt.validaDados(triagem, triagemLote, getUsuarioLogado()));
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
