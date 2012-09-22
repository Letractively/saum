package br.com.clarotriagem.controller.validaTriagem;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.clarotriagem.entitades.Triagem;
import br.com.clarotriagem.entitades.TriagemLote;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.TriagemService;
import br.com.clarotriagem.utils.enums.SintomaConstatadoAparelho;
import br.com.clarotriagem.utils.enums.TiposResultadosTriagem;

public class ValidadaTriagem {
	
	private long tempoDeUsoAparelhoPadrao = 1000 * 60 * 60 * 24 * 360;//tempo de uso padrao de 1 ano

	public TiposResultadosTriagem validaDados(Triagem triagem, TriagemLote triagemLote, UsuarioIdentificacao usuarioLogado, TriagemService triagemService) {
		TiposResultadosTriagem ret = TiposResultadosTriagem.DOA;
		
		
		//verfica se existe algum identificador que já foi inserido
		List<Triagem> triagemAntiga = triagemService.buscaTriagemAntiga(triagem, triagemLote);
		if(triagemAntiga != null){
			if(triagemAntiga.size() > 0){
				for(Triagem t : triagemAntiga){
					if(t.getIdentificador1Utilizado()){
						if(t.getIdentificador1().equalsIgnoreCase(triagem.getIdentificador1())){
							ret = TiposResultadosTriagem.INVALIDO;
							ret.setMsgRetorno("O " + triagem.getIdentificador1Nome() + " já foi triado nesse lote.");
							return ret;
						}
						if(t.getIdentificador2().equalsIgnoreCase(triagem.getIdentificador2())){
							ret = TiposResultadosTriagem.INVALIDO;
							ret.setMsgRetorno("O " + triagem.getIdentificador2Nome() + " já foi triado nesse lote.");
							return ret;
						}
						if(t.getIdentificador3().equalsIgnoreCase(triagem.getIdentificador3())){
							ret = TiposResultadosTriagem.INVALIDO;
							ret.setMsgRetorno("O " + triagem.getIdentificador3Nome() + " já foi triado nesse lote.");
							return ret;
						}
						if(t.getIdentificador4().equalsIgnoreCase(triagem.getIdentificador4())){
							ret = TiposResultadosTriagem.INVALIDO;
							ret.setMsgRetorno("O " + triagem.getIdentificador4Nome() + " já foi triado nesse lote.");
							return ret;
						}
						if(t.getIdentificador5().equalsIgnoreCase(triagem.getIdentificador5())){
							ret = TiposResultadosTriagem.INVALIDO;
							ret.setMsgRetorno("O " + triagem.getIdentificador5Nome() + " já foi triado nesse lote.");
							return ret;
						}
					}
				}
			}
		}
		
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
		seteDiasAtras.setTimeInMillis(System.currentTimeMillis());
		seteDiasAtras.set(Calendar.DAY_OF_MONTH, seteDiasAtras.get(Calendar.DAY_OF_MONTH) - 7);
		seteDiasAtras.set(Calendar.HOUR_OF_DAY, 0);
		seteDiasAtras.set(Calendar.MINUTE, 0);
		seteDiasAtras.set(Calendar.SECOND, 0);
		seteDiasAtras.set(Calendar.MILLISECOND, 0);
		
		Calendar dataNFVenda = new GregorianCalendar();
		dataNFVenda.setTime(triagem.getNfVendaData());
		int difDataVenda = dataNFVenda.getTime().compareTo(seteDiasAtras.getTime());
		
		int difDataTroca = 0;
		if(triagem.getNfTrocaData() != null){
			Calendar dataNFTroca = new GregorianCalendar();
			dataNFTroca.setTime(triagem.getNfTrocaData());
			difDataTroca = dataNFTroca.getTime().compareTo(seteDiasAtras.getTime());
		}
		
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
