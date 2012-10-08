package br.com.meganet.util;



public class IndexSessionUtil {
	private static IndexSessionUtil instance = null;
	boolean feed1 = false;

	/**
	 * Construtor default
	 * @param paramHttpServletRequest 
	 */
	public boolean getFeed1() {
		return feed1;
	}
	public void setFeed1(boolean feed) {
		feed1 = feed;
	}
	
	public static IndexSessionUtil getInstance() {
		if(instance == null){
			instance = new IndexSessionUtil();
		}
		return instance;
	}
}