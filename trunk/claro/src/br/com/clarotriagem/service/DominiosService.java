package br.com.clarotriagem.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.entitades.Dominio;
import br.com.clarotriagem.service.factory.ServiceFactory;
import br.com.clarotriagem.utils.Logger;

@Service("dominiosService")
@Transactional
public class DominiosService extends ServiceFactory<DominiosService>{
	
	private final static Logger log = new Logger(DominiosService.class);
	
	public Map<String, String> carregaDominios(){
		try {
			Map<String, String> ret = new HashMap<String, String>();
			List<Dominio> d = getDominiosDAO().findAll();
			for (Iterator<Dominio> iterator = d.iterator(); iterator.hasNext();) {
				Dominio dom = (Dominio) iterator.next();
				ret.put(dom.getDominiosChave(), dom.getDominiosValor());
			}
			
			return ret;
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}
	
}
