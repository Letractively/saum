package br.com.meganet.web.upload.controler;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import br.com.meganet.facade.AdministracaoFacade;
import br.com.meganet.facade.BoletoFacade;
import br.com.meganet.hbm.vo.LogMeganet;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.ValidaMenu;

public class GenericFileUploadController implements Controller {
	
	private final int idMenu = 31;
	ValidaMenu validaMenu = new ValidaMenu();
	private BoletoFacade boletoFacade;
	private AdministracaoFacade administracaoFacade;
	
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
			        Assert.notNull(files, "files is null");
			        Assert.state(files.size() > 0, "0 files exist");
			
			        /*
			         * process files
			         */
			        for (MultipartFile file : files.values()) {
			        	boolean grava = boletoFacade.gravaLogArquivoRetorno(file.getOriginalFilename());
			        	if(!grava){
			        		return new ModelAndView("boleto_arquivoRetorno");
			        	}
			        }
			        for (MultipartFile file : files.values()) {
		        		file.transferTo(new File(new File(ConfigUtil.getInstance().getProperty("diretorio_retorno","c:\\retorno")), file.getOriginalFilename()));
			        }
			        
		        	List<LogMeganet> ul = administracaoFacade.buscaUltimosLog();
					request.getSession().setAttribute("ultimosLog", ul);
			        return new ModelAndView("boleto_arquivoRetorno");
			
		        }catch (ClassCastException e) {
		        	return new ModelAndView("boleto_arquivoRetorno");
		        }catch (Exception e) {
		        	request.getSession().setAttribute("arquivoNegado", "Erro ao processar arquivo");
		        	return new ModelAndView("boleto_arquivoRetorno");
				}
			}else{
				return new ModelAndView("index");
			}
		}else{
			return new ModelAndView("index");
		}
    }

	public void setBoletoFacade(BoletoFacade boletoFacade) {
		this.boletoFacade = boletoFacade;
	}

	public void setAdministracaoFacade(AdministracaoFacade administracaoFacade) {
		this.administracaoFacade = administracaoFacade;
	}

}