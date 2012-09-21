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
