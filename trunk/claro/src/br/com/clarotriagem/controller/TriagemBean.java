package br.com.clarotriagem.controller;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.controller.factory.TriagemValidador;

@ManagedBean
@Scope("session")
@Component
public class TriagemBean extends TriagemValidador {

	
	private static final long serialVersionUID = -3822225104563517958L;

	private int quantidadeAparelhoTriado;
	private boolean painelEncolhido;
	
	public TriagemBean(){
		painelEncolhido = false;
	}

	public String loteTriagemSelecionado(){
		return "triar_aparelho";
	}
	
	public void encolheuPainel(){
		painelEncolhido = !painelEncolhido;
	}
	
	public int getQuantidadeAparelhoTriado() {
		quantidadeAparelhoTriado = triagemService.getQuantidadeAparelhoTriado(triagemLote.getId());
		return quantidadeAparelhoTriado;
	}

	public boolean isPainelEncolhido() {
		return painelEncolhido;
	}

}
