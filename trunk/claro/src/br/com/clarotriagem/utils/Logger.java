package br.com.clarotriagem.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.clarotriagem.utils.mail.EnviaEmailBO;
import br.com.clarotriagem.utils.singleton.mapas.ConfigUtil;

public class Logger {
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@SuppressWarnings("rawtypes")
	public Logger(Class classe){
		logger = LogFactory.getLog(classe);
	}
	
	public Logger(){}
	
	public void info(Object o){
		logger.info(o);
	}

	public void info(Object o, Throwable t){
		logger.info(o, t);
	}
	
	public void erro(String o){
		List<String> emails = new ArrayList<String>();
		String[] arr = ConfigUtil.getInstance().getProperty("email_administrativo_erro", "teste@gmail.com").split(",");
		for (int i = 0; i < arr.length; i++) {
			String m = arr[i];
			if(m != null & !"".equalsIgnoreCase(m.trim())){
				emails.add(m);
			}
		}
//		EnviaEmailBO.enviaMensagemErro(o, null, emails);
		logger.error(o);
	}
	
	public void erro(Throwable t){
		List<String> emails = new ArrayList<String>();
		String[] arr = ConfigUtil.getInstance().getProperty("email_administrativo_erro", "teste@gmail.com").split(",");
		for (int i = 0; i < arr.length; i++) {
			String m = arr[i];
			if(m != null & !"".equalsIgnoreCase(m.trim())){
				emails.add(m);
			}
		}
//		EnviaEmailBO.enviaMensagemErro(logger.getClass().getCanonicalName(), t.getStackTrace(), emails);
		logger.error(t);
	}
	
	public void erro(Object o, Throwable t){
		List<String> emails = new ArrayList<String>();
		String[] arr = ConfigUtil.getInstance().getProperty("email_administrativo_erro", "teste@gmail.com").split(",");
		for (int i = 0; i < arr.length; i++) {
			String m = arr[i];
			if(m != null & !"".equalsIgnoreCase(m.trim())){
				emails.add(m);
			}
		}
		
		if(t != null){
			EnviaEmailBO.enviaMensagemErro(o.toString(), t.getStackTrace(), emails);
		}else{
			EnviaEmailBO.enviaMensagemErro(o.toString(), null, emails);
		}
		logger.error(o, t);
	}
	
	public void erroSemEmail(Object o){
		logger.error(o);
	}
	
	public void erroSemEmail(Object o, Throwable t){
		logger.error(o, t);
	}
}
