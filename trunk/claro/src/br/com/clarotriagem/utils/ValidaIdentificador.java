package br.com.clarotriagem.utils;

import br.com.clarotriagem.utils.enums.TiposSerial;
import br.com.clarotriagem.utils.validadoresSerial.ValidaDATECODE;
import br.com.clarotriagem.utils.validadoresSerial.ValidaESN;
import br.com.clarotriagem.utils.validadoresSerial.ValidaIMEI;
import br.com.clarotriagem.utils.validadoresSerial.ValidaMSN;
import br.com.clarotriagem.utils.validadoresSerial.ValidaMSN_NEXTEL;
import br.com.clarotriagem.utils.validadoresSerial.ValidaSSN;
import br.com.clarotriagem.utils.validadoresSerial.ValidaSSN_CAM;
import br.com.clarotriagem.utils.validadoresSerial.ValidaSSN_CTV;
import br.com.clarotriagem.utils.validadoresSerial.ValidaSSN_DSC;
import br.com.clarotriagem.utils.validadoresSerial.ValidaSSN_DVD;
import br.com.clarotriagem.utils.validadoresSerial.ValidaSSN_LG;
import br.com.clarotriagem.utils.validadoresSerial.ValidaSSN_MDM;
import br.com.clarotriagem.utils.validadoresSerial.ValidaSSN_MON;
import br.com.clarotriagem.utils.validadoresSerial.ValidadorGeral;

public class ValidaIdentificador {

	private final static Logger log = new Logger(ValidaIdentificador.class);
	
	public boolean validaSerial(TiposSerial ts, String serial) {
		try {
			ValidadorGeral v = new ValidadorGeral(serial) {
				@Override
				public boolean valida() throws Exception {
					return false;
				}
			};
			
			if (TiposSerial.DATECODE.getCod().intValue() == ts.getCod().intValue()) {
				v = new ValidaDATECODE(serial);
				return v.valida();
			
			} else if (TiposSerial.ESN.getCod().intValue() == ts.getCod().intValue()) {
				v = new ValidaESN(serial);
				return v.valida();
			
			} else if (TiposSerial.IMEI.getCod().intValue() == ts.getCod().intValue()) {
				v = new ValidaIMEI(serial);
				return v.valida();
			
			} else if (TiposSerial.MSN.getCod().intValue() == ts.getCod().intValue()) {
				v = new ValidaMSN(serial, null);
				return v.valida();
				
			} else if (TiposSerial.MSN_NEXTEL.getCod().intValue() == ts.getCod().intValue()) {
				v = new ValidaMSN_NEXTEL(serial);
				return v.valida();
				
			} else if (TiposSerial.SERIAL.getCod().intValue() == ts.getCod().intValue()) {
				return !v.valida();
				
			} else if (TiposSerial.SSN.getCod().intValue() == ts.getCod().intValue()) {
				v = new ValidaSSN(serial);
				v.valida();
				
			} else if (TiposSerial.SSN_CAM.getCod().intValue() == ts.getCod().intValue()) {
				v = new ValidaSSN_CAM(serial);
				return v.valida();
				
			} else if (TiposSerial.SSN_CTV.getCod().intValue() == ts.getCod().intValue()) {
				v = new ValidaSSN_CTV(serial);
				return v.valida();
				
			} else if (TiposSerial.SSN_DSC.getCod().intValue() == ts.getCod().intValue()) {
				v = new ValidaSSN_DSC(serial);
				return v.valida();
				
			} else if (TiposSerial.SSN_DVD.getCod().intValue() == ts.getCod().intValue()) {
				v = new ValidaSSN_DVD(serial);
				return v.valida();
				
			} else if (TiposSerial.SSN_LG.getCod().intValue() == ts.getCod().intValue()) {
				v = new ValidaSSN_LG(serial);
				return v.valida();
				
			} else if (TiposSerial.SSN_MDM.getCod().intValue() == ts.getCod().intValue()) {
				v = new ValidaSSN_MDM(serial);
				return v.valida();
				
			} else if (TiposSerial.SSN_MON.getCod().intValue() == ts.getCod().intValue()) {
				v = new ValidaSSN_MON(serial);
				return v.valida();
				
			} else if (TiposSerial.SSN_NBK.getCod().intValue() == ts.getCod().intValue()) {

				return v.valida();
				
			} else if (TiposSerial.SSN_NPC.getCod().intValue() == ts.getCod().intValue()) {

				return v.valida();
				
			} else if (TiposSerial.SSN_PRT.getCod().intValue() == ts.getCod().intValue()) {

				return v.valida();
				
			} else if (TiposSerial.SSN_REF.getCod().intValue() == ts.getCod().intValue()) {

				return v.valida();
				
			} else if (TiposSerial.SSN_SWA.getCod().intValue() == ts.getCod().intValue()) {

				return v.valida();
				
			} else if (TiposSerial.SSN_VC.getCod().intValue() == ts.getCod().intValue()) {

				return v.valida();
				
			} else if (TiposSerial.SSN_WM.getCod().intValue() == ts.getCod().intValue()) {

				return v.valida();
				
			} else if (TiposSerial.SSN_YEPP.getCod().intValue() == ts.getCod().intValue()) {

				return v.valida();
				
			} else {
				
				return v.valida();
			}
			return false;
		} catch (Exception e) {
			log.erroSemEmail(e);
			return false;
		}
	}
}
