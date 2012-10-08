package br.com.meganet.facade;

import java.util.List;

import br.com.meganet.bo.AvisosBO;
import br.com.meganet.hbm.vo.Avisos;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.Logger;

public class AvisosFacade {
	
	private Logger log;
    private AvisosBO avisosBO;

	public void setAvisosBO(AvisosBO avisosBO) {
		this.avisosBO = avisosBO;
	}
	public void setLog(Logger log) {
		this.log = log;
	}

	public List<Avisos> carregaAvisos(Usuario usuario, Boolean fb) {
		try{
			if(usuario != null){
				if(!usuario.getUsuarioAdministrativo()){
					return avisosBO.carregaAvisosCliente(usuario);
				}else{
					return avisosBO.carregaAvisosAdministrativo(usuario, fb);
				}
			}else{
				return avisosBO.carregaAvisos();
			}
		}catch (Exception e) {
			log.erro(e);
			return null;
		}
	}
	public List<Avisos> carregaAvisosWAP(Usuario usuario) {
		try{
			if(usuario != null){
				if(!usuario.getUsuarioAdministrativo()){
					return avisosBO.carregaAvisos();
				}else{
					return avisosBO.carregaAvisosAdministrativoWAP(usuario);
				}
			}else{
				return avisosBO.carregaAvisos();
			}
		}catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

}
