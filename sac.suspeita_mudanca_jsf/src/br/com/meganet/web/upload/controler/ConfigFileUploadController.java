package br.com.meganet.web.upload.controler;

import java.io.File;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ValidaMenu;

public class ConfigFileUploadController implements Controller {
	
	private final int idMenu = 47;
	ValidaMenu validaMenu = new ValidaMenu();

    @SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if (usuario != null){
			if(validaMenu.validaMenu(idMenu, usuario.getUsuarioMenu().split(","))){

		        try{
		        	final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		        	
			        /*
			         * extract files
			         */
			        final Map<String, MultipartFile> files = multiRequest.getFileMap();
			        Assert.notNull(files, "Entrada de arquivos nula.");
			        Assert.state(files.size() > 0, "Puts.! Nenhum arquivo foi upado.");
			        
			        /*
			         * process files
			         */
					ServletContext context = request.getSession().getServletContext();

			        for (MultipartFile file : files.values()) {
		        		file.transferTo(new File(new File(context.getRealPath("WEB-INF/classes/properties")), "config.properties"));
			        }
			        
			        return new ModelAndView("configuracao_properties");
		        }catch (ClassCastException e) {
		        	return new ModelAndView("configuracao_properties");
		        }catch (Exception e) {
		        	return new ModelAndView("configuracao_properties");
				}
			}else{
				return new ModelAndView("index");
			}
		}else{
			return new ModelAndView("index");
		}
    }
}