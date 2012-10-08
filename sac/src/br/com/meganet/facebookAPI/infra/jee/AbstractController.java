package br.com.meganet.facebookAPI.infra.jee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.meganet.facebookAPI.facebook.Facebook;

public abstract class AbstractController {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	protected Facebook facebook;
	protected UserLogged userLogged;

	public AbstractController(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.facebook = (Facebook) session.getServletContext().getAttribute("facebook");
		this.userLogged = (UserLogged) session.getAttribute("userLogged");
	}

	protected void redirectTo(String url) throws IOException {
		response.setContentType("text/html");
		response.getWriter().println("<html><head><script>top.location.href='" + url + "'</script></head></html>");
		response.flushBuffer();
	}

	protected void sendRedirect(String url) throws IOException {
		response.sendRedirect(url);
	}

	protected void forward(String path) throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	public abstract void execute() throws Exception;

	public Facebook getFacebook() {
		return facebook;
	}
	
}
