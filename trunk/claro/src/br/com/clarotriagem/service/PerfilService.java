package br.com.clarotriagem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.entitades.Perfil;
import br.com.clarotriagem.service.factory.ServiceFactory;
import br.com.clarotriagem.utils.Logger;

@Service("perfilService")
@Transactional
public class PerfilService extends ServiceFactory<PerfilService> {
	
	private final static Logger log = new Logger(PerfilService.class);
	
	public PerfilService(){
		super();
	}
	
	public List<Perfil> findAll(){
		try {
			return getPerfilDAO().findAll();
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public List<Perfil> buscaPerfisAtivados() {
		try {
			return getPerfilDAO().buscaPerfisAtivados();
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}
	
}
