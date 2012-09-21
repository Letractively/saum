package br.com.clarotriagem.controller.validaTriagem;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.com.clarotriagem.entitades.Triagem;
import br.com.clarotriagem.entitades.TriagemLote;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.utils.enums.SintomaConstatadoAparelho;
import br.com.clarotriagem.utils.enums.TiposResultadosTriagem;

public class ValidadaTriagem {
	
	private long tempoDeUsoAparelhoPadrao = 1000 * 60 * 60 * 24 * 360;//tempo de uso padrao de 1 ano

	public TiposResultadosTriagem validaDados(Triagem triagem, TriagemLote triagemLote, UsuarioIdentificacao usuarioLogado) {
		TiposResultadosTriagem ret = TiposResultadosTriagem.DOA;
		
		//aparelho bloqueado nao DOA
		if(triagem.getAparelhoBloqueado()){
			ret = TiposResultadosTriagem.NAO_DOA;
			ret.setMsgRetorno("Aparelho bloqueado");
			return ret;
		}
		
		
		//AO SELECIONAR O SINTOMA CONSTATADO COMO NAO TEM DEFEITO ELE TEM QUE SER NAO DOA.
		if(triagem.getSintomaConstatado().intValue() == SintomaConstatadoAparelho.NDE.getCod().intValue()){
			ret = TiposResultadosTriagem.NAO_DOA;
			ret.setMsgRetorno("Aparelho sem defeito");
			return ret;
		}

		Calendar seteDiasAtras = new GregorianCalendar();
		Calendar dataNFVenda = new GregorianCalendar();
		Calendar dataNFTroca = new GregorianCalendar();
		
		dataNFVenda.setTime(triagem.getNfVendaData());
		dataNFTroca.setTime(triagem.getNfTrocaData());
		
		seteDiasAtras.setTimeInMillis(System.currentTimeMillis());
		seteDiasAtras.set(Calendar.DAY_OF_MONTH, seteDiasAtras.get(Calendar.DAY_OF_MONTH) - 7);
		seteDiasAtras.set(Calendar.HOUR_OF_DAY, 0);
		seteDiasAtras.set(Calendar.MINUTE, 0);
		seteDiasAtras.set(Calendar.SECOND, 0);
		seteDiasAtras.set(Calendar.MILLISECOND, 0);
		
		int difDataVenda = dataNFVenda.getTime().compareTo(seteDiasAtras.getTime());
		int difDataTroca = dataNFTroca.getTime().compareTo(seteDiasAtras.getTime());
		
		//AUTORIZAÇÃO DE CCC SE FOR SIM, DESCONSIDERA O TEMPO DE USO
		if(!triagem.getAutorizacaoCcc() || difDataVenda < 0 || difDataTroca < 0){
			int tempoHora = triagem.getTempoUsoHora();
			int tempoMinu = triagem.getTempoUsoMinu();
			int tempoSegu = triagem.getTempoUsoSegundos();
			
			long tempoDeUsoinformadoUsuario = ((tempoSegu * 1000) + (tempoMinu * 1000 * 60) + (tempoHora * 1000 * 60 *60));
			
			//tempo de uso maior que o tempo padrao, se for maior o tempo de uso NAO DOA
			if(tempoDeUsoinformadoUsuario > tempoDeUsoAparelhoPadrao){
				ret = TiposResultadosTriagem.NAO_DOA;
				ret.setMsgRetorno("Tempo de uso do aparelho");
				return ret;
			}
		}
		
		return ret;
	}
	
}
