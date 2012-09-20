package br.com.clarotriagem.controller;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.controller.factory.TriagemValidador;
import br.com.clarotriagem.entitades.AparelhoModelo;

@ManagedBean
@Scope("session")
@Component
public class TriagemBean extends TriagemValidador {
	
	private static final long serialVersionUID = -3822225104563517958L;

	public TriagemBean(){
		painelEncolhido = false;
	}
	public String loteTriagemSelecionado(){
		AparelhoModelo am = triagemLote.getAparelhoModelo();
		temTiposSerial1 = am.getId1() != null;
		temTiposSerial2 = am.getId2() != null;
		temTiposSerial3 = am.getId3() != null;
		temTiposSerial4 = am.getId4() != null;
		return "triar_aparelho";
	}
	public int getQuantidadeAparelhoTriado() {
		quantidadeAparelhoTriado = triagemService.getQuantidadeAparelhoTriado(triagemLote.getId());
		return quantidadeAparelhoTriado;
	}
	
}
