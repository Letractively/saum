package br.com.meganet.facebookAPI.infra.jee;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;

import br.com.meganet.facebookAPI.facebook.controller.AuthController;

public class FrontControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Map<String, Class<? extends AbstractController>> controllers = new HashMap<String, Class<? extends AbstractController>>();
	//private Class<? extends BaseController> defaultController;
	
	public FrontControllerServlet() {
		controllers.put("/auth", AuthController.class);
		controllers.put("/auth/", AuthController.class);
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response, Class<? extends AbstractController> controller) throws Exception {
		Class<?>[] types = {HttpServletRequest.class, HttpServletResponse.class};
		Object[] args = {request, response};		
		
		AbstractController bean = controller.getConstructor(types).newInstance(args);
		
		// Popula o bean com os parametro da requisicao
		Enumeration<?> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String name = (String)e.nextElement();
			String value = request.getParameter(name);
			if (PropertyUtils.isWriteable(bean, name)) {
				PropertyUtils.setSimpleProperty(bean, name, value);
			}
		}
		
		request.setAttribute("bean", bean);
		bean.execute();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("###################################################################################");
		String requestUri = request.getRequestURI();
		request.getSession().setAttribute("facebook", true);
		System.out.println("DEBUG " + this.getClass() + " - URI " + requestUri);
		
		UserLogged userLogged = (UserLogged) request.getSession().getAttribute("userLogged");
		if(userLogged == null){
			request.getSession(true);
			userLogged = new UserLogged();
			request.getSession().setAttribute("userLogged", userLogged);
			System.out.println("USERLOGGED NULO " + request.getSession());
		}
		
		String name = requestUri.substring(requestUri.indexOf("/", 0));
		
		try {
			if (controllers.containsKey(name)) {
				Class<? extends AbstractController> controller = controllers.get(name);
				execute(request, response, controller);
			} else {
				System.out.println("DEBUG REDIRECT INTERNO = " + requestUri.substring(requestUri.indexOf("/", 1)));
				String urlTMP = requestUri.substring(requestUri.indexOf("/", 0));
				String url = "";
				if(urlTMP.indexOf("auth") > -1){
					url = "/fb" + requestUri.substring(requestUri.indexOf("/", 1));
				}
				request.getRequestDispatcher(url).forward(request, response);
			}
		} catch (Exception e) {
			response.sendError(404);
			e.printStackTrace();
		}

		System.out.println("###################################################################################");
	}

}
