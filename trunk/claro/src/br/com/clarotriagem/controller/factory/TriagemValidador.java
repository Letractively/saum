package br.com.clarotriagem.controller.factory;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.clarotriagem.controller.BaseBean;
import br.com.clarotriagem.entitades.TriagemLote;
import br.com.clarotriagem.service.CalendarioService;
import br.com.clarotriagem.service.TriagemService;
import br.com.clarotriagem.utils.enums.TiposSerial;

public class TriagemValidador extends BaseBean{

	private static final long serialVersionUID = 8982154360605463893L;

	@Autowired
	protected CalendarioService calendarioService;
	
	@Autowired
	protected TriagemService triagemService;
	
	protected TriagemLote triagemLote;
	protected int quantidadeAparelhoTriado;
	protected boolean painelEncolhido;
	protected boolean temTiposSerial1;
	protected boolean temTiposSerial2;
	protected boolean temTiposSerial3;
	protected boolean temTiposSerial4;

	
	public TriagemLote getTriagemLote() {
		return triagemLote;
	}
	public void setTriagemLote(TriagemLote triagemLote) {
		this.triagemLote = triagemLote;
	}
	public void encolheuPainel(){
		painelEncolhido = !painelEncolhido;
	}
	public boolean isPainelEncolhido() {
		return painelEncolhido;
	}

}
