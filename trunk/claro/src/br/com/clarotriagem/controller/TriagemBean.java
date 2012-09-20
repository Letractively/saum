package br.com.clarotriagem.controller;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.controller.factory.TriagemMontaFormulario;
import br.com.clarotriagem.entitades.AparelhoModelo;

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

	public String loteTriagemSelecionado() {
		AparelhoModelo am = triagemLote.getAparelhoModelo();
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
