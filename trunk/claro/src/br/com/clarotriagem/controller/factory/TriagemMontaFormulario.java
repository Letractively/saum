package br.com.clarotriagem.controller.factory;

import java.util.SortedMap;

import br.com.clarotriagem.utils.enums.AparenciaAparelho;
import br.com.clarotriagem.utils.enums.Operadoras;
import br.com.clarotriagem.utils.enums.SintomaConstatadoAparelho;
import br.com.clarotriagem.utils.enums.SintomaInformadoAparelho;

/**
 * 
 * @author efren.junior
 * 
 *         Essa classe serve apenas para MONTAR o formulario na página,
 *         separando, assim, a logina de triagem, validador e controlador
 * 
 */

public class TriagemMontaFormulario extends TriagemBackEnd {

	private static final long serialVersionUID = -1047913497792507436L;

	public SortedMap<String, Integer> getOperadorasCombo() {
		if(operadorasCombo == null){
			operadorasCombo = Operadoras.getMapaRotulos();
		}
		return operadorasCombo;
	}
	
	protected void zeraForm() {
		triagem.setIdentificador1("");
		triagem.setIdentificador2("");
		triagem.setIdentificador3("");
		triagem.setIdentificador4("");
		triagem.setIdentificador5("");
		
		triagem.setAparelhoBloqueado(false);
		triagem.setAparencia(null);
		triagem.setAutorizacaoCcc(false);
		triagem.setDoa(null);
		triagem.setId(null);
		triagem.setNfTrocaData(null);
		triagem.setNfTrocaNumero(null);
		triagem.setNfVendaData(null);
		triagem.setNfVendaNumero(null);
		triagem.setObservacoes(null);
		triagem.setOperadora(null);
		triagem.setOsCodPeca1(null);
		triagem.setOsCodPeca2(null);
		triagem.setOsCodPeca3(null);
		triagem.setOsDataAbertura1(null);
		triagem.setOsDataAbertura2(null);
		triagem.setOsDataAbertura3(null);
		triagem.setOsNumero1(null);
		triagem.setOsNumero2(null);
		triagem.setOsNumero3(null);
		triagem.setPossuiCheckList(false);
		triagem.setSintomaConstatado(null);
		triagem.setSintomaInformado(null);
		triagem.setTempoUsoHora(null);
		triagem.setTempoUsoMinu(null);
		triagem.setTempoUsoSegundos(null);
	}
	
	public SortedMap<String, Integer> getTipoAparenciaAparelho() {
		if(tipoAparenciaAparelho == null){
			tipoAparenciaAparelho = AparenciaAparelho.getMapaRotulos();
		}
		return tipoAparenciaAparelho;
	}
	public SortedMap<String, Integer> getSintomaInformado() {
		if(sintomaInformado == null){
			sintomaInformado = SintomaInformadoAparelho.getMapaRotulos();
		}
		return sintomaInformado;
	}

	public SortedMap<String, Integer> getSintomaConstatado() {
		if(sintomaConstatado == null){
			sintomaConstatado = SintomaConstatadoAparelho.getMapaRotulos();
		}
		return sintomaConstatado;
	}

	
}
