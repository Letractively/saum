package br.com.meganet.util;

import br.com.meganet.hbm.vo.Feed;

public class RssUtil {
	private static RssUtil instance = null;
	private static CountDown cd = null;
	Feed feed2 = null;
	private static Logger logger = new Logger(RssUtil.class);

	/**
	 * Construtor default
	 */
	private RssUtil() {
		try {
			feed2 = new Feed(ConfigUtil.getInstance().getProperty("rss1","http://g1.globo.com/dynamo/brasil/rss2.xml"));
			if(cd == null || !cd.estaRodando()){
				cd = new CountDown();
				cd.setHor(new Integer(ConfigUtil.getInstance().getProperty("hora_intervalo_RSS_HHMMSS", "00:00:00").split(":")[0]));
				cd.setMin(new Integer(ConfigUtil.getInstance().getProperty("hora_intervalo_RSS_HHMMSS", "00:00:00").split(":")[1]));
				cd.setSec(new Integer(ConfigUtil.getInstance().getProperty("hora_intervalo_RSS_HHMMSS", "00:00:00").split(":")[2]));
				cd.start();
			}
		} catch (Exception exc) {
			feed2 = null;
			logger.erro("Erro no processamento das notícias.");
		}
	}


	public Feed getFeed2() {
		return feed2;
	}
	
	public static RssUtil getInstance() {
		if(cd == null){
			cd = new CountDown();
			cd.setHor(new Integer(ConfigUtil.getInstance().getProperty("hora_intervalo_RSS_HHMMSS", "00:00:00").split(":")[0]));
			cd.setMin(new Integer(ConfigUtil.getInstance().getProperty("hora_intervalo_RSS_HHMMSS", "00:00:00").split(":")[1]));
			cd.setSec(new Integer(ConfigUtil.getInstance().getProperty("hora_intervalo_RSS_HHMMSS", "00:00:00").split(":")[2]));
			cd.start();
		}
		if(instance == null || !cd.estaRodando()){
			instance = new RssUtil();
		}
		return instance;
	}
}