package br.com.clarotriagem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.entitades.AparelhoMarca;
import br.com.clarotriagem.entitades.AparelhoModelo;
import br.com.clarotriagem.service.factory.ServiceFactory;
import br.com.clarotriagem.utils.Logger;
import br.com.clarotriagem.utils.singleton.mapas.AparelhoModeloCache;

@Service("aparelhosService")
@Transactional
public class AparelhosService extends ServiceFactory<AparelhosService>{
	
	private final static Logger log = new Logger(AparelhosService.class);
	
	public AparelhosService(){
		super();
	}

	public List<AparelhoModelo> buscaModelosAparelhos(String nome) {
		try {
			if(nome != null){
				List<AparelhoModelo> ret = new ArrayList<AparelhoModelo>();
				for(Map.Entry<String, AparelhoModelo>  objMapa : AparelhoModeloCache.getInstance().getModelosPorNOME().entrySet()){
					String key = objMapa.getKey();
					if(key.contains(nome.toLowerCase())){
						ret.add(objMapa.getValue());
					}
				}
				return ret;
			}else{
				return new ArrayList<AparelhoModelo>();
			}
		} catch (Exception e) {
			log.erro(e);
			return new ArrayList<AparelhoModelo>();
		}
	}

	public AparelhoModelo findByName(String nome) {
		try {
			return AparelhoModeloCache.getInstance().getModelosPorNOME(nome);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public List<AparelhoModelo> buscaTodosModelos() {
		try {
			List<AparelhoModelo> ams = getAparelhoModeloDAO().findModelosAtivos();
			return ams;
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public List<AparelhoMarca> buscaTodasMarcasAtivas() {
		try {
			List<AparelhoMarca> ams = getAparelhoMarcaDAO().findMarcasAtivas();
			return ams;
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public List<AparelhoMarca> buscaTodasMarcas() {
		try {
			List<AparelhoMarca> ams = getAparelhoMarcaDAO().findMarcas();
			return ams;
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public void atualizaMarca(AparelhoMarca marca) {
		try {
			getAparelhoMarcaDAO().update(marca);
			List<AparelhoModelo> ams = buscaTodosModelos();
			AparelhoModeloCache.getInstance().addMap(ams);
		} catch (Exception e) {
			log.erro(e);
		}
	}

	public List<AparelhoMarca> buscaMarcasTodas(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		try {
			return getAparelhoMarcaDAO().getMarcas(startingAt, maxPerPage, sortField, sortOrder, filters);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public int getQtdTotalMarcas() {
		try {
			return getAparelhoMarcaDAO().getQtdTotalMarcas();
		} catch (Exception e) {
			log.erro(e);
			return 0;
		}
	}

	public List<AparelhoModelo> buscaModelosTodos(Long idMarca) {
		try {
			return getAparelhoModeloDAO().getModelos(idMarca);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public int getQtdTotalModelos(Long idModelo) {
		try {
			return getAparelhoModeloDAO().getQtdTotalModelos(idModelo);
		} catch (Exception e) {
			log.erro(e);
			return 0;
		}
	}
	
	public SortedMap<String, Long> getMarcasParaCombo(){
		try {
			return getAparelhoMarcaDAO().getMarcasParaCombo();
		} catch (Exception e) {
			log.erro(e);
			return new TreeMap<String, Long>();
		}
	}

	public void atualizaModelo(AparelhoModelo modelo) {
		try {
			getAparelhoModeloDAO().update(modelo);
			List<AparelhoModelo> ams = buscaTodosModelos();
			AparelhoModeloCache.getInstance().addMap(ams);
		} catch (Exception e) {
			log.erro(e);
		}
	}

	public void insereModelo(AparelhoModelo mod) {
		try {
			getAparelhoModeloDAO().save(mod);
			List<AparelhoModelo> ams = buscaTodosModelos();
			AparelhoModeloCache.getInstance().addMap(ams);
		} catch (Exception e) {
			log.erro(e);
		}
	}

}
