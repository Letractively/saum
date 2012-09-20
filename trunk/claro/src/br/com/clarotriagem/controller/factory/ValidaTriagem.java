package br.com.clarotriagem.controller.factory;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.clarotriagem.controller.BaseBean;
import br.com.clarotriagem.utils.ValidaIdentificador;
import br.com.clarotriagem.utils.enums.TiposSerial;

@FacesValidator(value="validaTriagem")
public class ValidaTriagem extends BaseBean implements Validator{

	private static final long serialVersionUID = 8410129420214929854L;

	@Override
	public void validate(FacesContext ctx, UIComponent ui, Object value) throws ValidatorException {
	    Integer codTipoSerial = (Integer) ui.getAttributes().get("tipoSerial");
	    
	    String serial = (String) value;
	    
	    TiposSerial ts = TiposSerial.getRotuloPorCod(codTipoSerial);
	    ValidaIdentificador vi = new ValidaIdentificador();
	    Boolean valido = vi.validaSerial(ts, serial);
	    
	    if (!valido) {
	        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "X "));
	    }
	}
}
