package br.com.meganet.util;

import java.util.List;

import br.com.meganet.hbm.vo.Avisos;


public class AvisosAdmUtil {
	private static AvisosAdmUtil instance = null;
	private static CountDown cd = null;
	private static Logger logger = new Logger(AvisosAdmUtil.class);
	
	private List<Avisos> avisos = null;
	private boolean estaRodando = false;
	/**
	 * Construtor default
	 */
	private AvisosAdmUtil() {
		try {
			if(cd == null || !cd.estaRodando()){
				cd = new CountDown();
				cd.setHor(new Integer(ConfigUtil.getInstance().getProperty("hora_intervalo_RSS_HHMMSS", "00:00:00").split(":")[0]));
				cd.setMin(new Integer(ConfigUtil.getInstance().getProperty("hora_intervalo_RSS_HHMMSS", "00:00:00").split(":")[1]));
				cd.setSec(new Integer(ConfigUtil.getInstance().getProperty("hora_intervalo_RSS_HHMMSS", "00:00:00").split(":")[2]));
				cd.start();
			}
		} catch (Exception exc) {
			logger.erro("Erro no processamento das notícias.");
		}
	}


	public static AvisosAdmUtil getInstance() {
		if(cd == null){
			cd = new CountDown();
			cd.setHor(new Integer(ConfigUtil.getInstance().getProperty("hora_intervalo_RSS_HHMMSS", "00:00:00").split(":")[0]));
			cd.setMin(new Integer(ConfigUtil.getInstance().getProperty("hora_intervalo_RSS_HHMMSS", "00:00:00").split(":")[1]));
			cd.setSec(new Integer(ConfigUtil.getInstance().getProperty("hora_intervalo_RSS_HHMMSS", "00:00:00").split(":")[2]));
			cd.start();
		}
		if(instance == null || !cd.estaRodando()){
			instance = new AvisosAdmUtil();
		}
		return instance;
	}


	public List<Avisos> getAvisos() {
		return avisos;
	}


	public void setAvisos(List<Avisos> avisos) {
		this.avisos = avisos;
	}


	public boolean getEstaRodando() {
		return estaRodando;
	}


	public void setEstaRodando(boolean estaRodando) {
		this.estaRodando = estaRodando;
	}


}