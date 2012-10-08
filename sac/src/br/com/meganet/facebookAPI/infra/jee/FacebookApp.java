package br.com.meganet.facebookAPI.infra.jee;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.meganet.facebookAPI.facebook.Facebook;

public class FacebookApp implements ServletContextListener {

	private Facebook facebook = new Facebook();

	// @Override
	public void contextInitialized(ServletContextEvent event) {
		event.getServletContext().setAttribute("facebook", facebook);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

}
