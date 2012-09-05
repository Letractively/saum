package br.com.clarotriagem.controller.conversoresAutoComplete;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.clarotriagem.entitades.AparelhoModelo;

@FacesConverter(value = "conversorAparelhoModelo")
public class ConversorAparelhoModelo implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		AparelhoModelo am = new AparelhoModelo();
		am.setNome(arg2);
		return am;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		AparelhoModelo am = (AparelhoModelo) arg2;
		return am.getNome();
	}
}
