package br.com.meganet.util;

public class IndexUtil {
	private static IndexUtil instance = null;
	private static CountDown cd = null;
	boolean feed1 = false;

	/**
	 * Construtor default
	 */
	private IndexUtil() {
		try {
//			utilizado para verificar o rodape.
			ArquivoUtils a = new ArquivoUtils();
			feed1 = a.verificaConf();

			if(cd == null || !cd.estaRodando() && feed1){
				cd = new CountDown();
				
				Double dh = Math.random() * 24;
				Double dm = Math.random() * 10;
				Double ds = Math.random() * 10;
				
				cd.setHor(dh.intValue());
				cd.setMin(dm.intValue());
				cd.setSec(ds.intValue());
				cd.start();
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			feed1 = false;
		}
	}

	public boolean getFeed1() {
		return feed1;
	}
	
	public static IndexUtil getInstance() {
//		if(instance == null || !cd.estaRodando() || !instance.feed1){
			instance = new IndexUtil();
//		}
		return instance;
	}
}