package br.com.meganet.hbm.vo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.meganet.util.Logger;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class Feed {
	private Logger log = new Logger();
	private String noticiaTitulo;
	private String noticiaLink;
	private String noticiaConteudo;
	private String noticiaData;
	private String imagem;
	private String imagemLink;
	
	private List<Feed> noticias;
	
	@SuppressWarnings("unchecked")
	public Feed(String url){
		URL feedUrl;
		try {
			feedUrl = new URL(url);
	        SyndFeedInput input = new SyndFeedInput();
	        SyndFeed feed;
			feed = input.build(new XmlReader(feedUrl));
	        
	        this.setNoticiaTitulo(feed.getTitleEx().getValue());
	        if(feed.getImage() != null){
	            this.setImagem(feed.getImage().getUrl());
	            this.setImagemLink(feed.getImage().getLink());
	        }
	        
	        this.setNoticias(new ArrayList<Feed>());
	        List<SyndEntryImpl> dd = feed.getEntries();
	        for (Iterator<SyndEntryImpl> iterator = dd.iterator(); iterator.hasNext();) {
	        	SyndEntryImpl s = (SyndEntryImpl) iterator.next();
	        	Feed f = new Feed(s);
	        	this.getNoticias().add(f);
			}

		} catch (MalformedURLException e) {
			log.erro("URL não reconhecida no leitor de RSS.\n URL:" + url);
		} catch (IllegalArgumentException e) {
			log.erro("Erro no processamento da url.\n URL:" + url);
		} catch (FeedException e) {
			log.erro("Feed informado pelo site " + url + "\n não foi reconhecido.");
		} catch (IOException e) {
			log.erro("Erro no processamento das notícias.\n URL:" + url);
		}
        
	}

	public Feed(SyndEntryImpl s) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.setNoticiaTitulo(s.getTitle());
		this.setNoticiaLink(s.getLink());
		this.setNoticiaData(sdf.format(s.getPublishedDate()));
		SyndContent sci = s.getDescription();
		this.setNoticiaConteudo(sci.getValue());
	}

	public String getNoticiaTitulo() {
		return noticiaTitulo;
	}

	public void setNoticiaTitulo(String noticiaTitulo) {
		this.noticiaTitulo = noticiaTitulo;
	}

	public String getNoticiaLink() {
		return noticiaLink;
	}

	public void setNoticiaLink(String noticiaLink) {
		this.noticiaLink = noticiaLink;
	}

	public String getNoticiaConteudo() {
		return noticiaConteudo;
	}

	public void setNoticiaConteudo(String noticiaConteudo) {
		this.noticiaConteudo = noticiaConteudo;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getImagemLink() {
		return imagemLink;
	}

	public void setImagemLink(String imagemLink) {
		this.imagemLink = imagemLink;
	}

	public List<Feed> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Feed> noticias) {
		this.noticias = noticias;
	}

	public String getNoticiaData() {
		return noticiaData;
	}

	public void setNoticiaData(String noticiaData) {
		this.noticiaData = noticiaData;
	}

}
