package br.com.meganet.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.meganet.bo.EnviaEmailBO;
import br.com.meganet.hbm.DAO.LogMeganetDAO;
import br.com.meganet.hbm.vo.LogMeganet;

public class Logger {
	
	private Log logger = LogFactory.getLog(this.getClass());
	private LogMeganetDAO logMeganetDAO;
	
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
		EnviaEmailBO.enviaMensagemErro(o, null, emails);
		logger.info(o);
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
		EnviaEmailBO.enviaMensagemErro(logger.getClass().getCanonicalName(), t.getStackTrace(), emails);
		logger.info(t);
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
		
		LogMeganet lm = new LogMeganet();
		if(t != null){
			lm.setLogAcao(t.getLocalizedMessage());
		}else{
			lm.setLogAcao("localização da mensagem nula. NullPointerException");
		}
		lm.setLogDescricao(o.toString());
		lm.setLogTipo(Constantes.LOG_TIPO_RETORNO[3]);
		lm.setLogData(new Timestamp(System.currentTimeMillis()));
		try{
			logMeganetDAO.attachDirty(lm);
		}catch (Exception e) {
		}
		
		if(t != null){
			EnviaEmailBO.enviaMensagemErro(o.toString(), t.getStackTrace(), emails);
		}else{
			EnviaEmailBO.enviaMensagemErro(o.toString(), null, emails);
		}
		logger.info(o, t);
	}
	
	public void erroSemEmail(Object o){
		logger.info(o);
	}
	
	public void erroSemEmail(Object o, Throwable t){
		logger.info(o, t);
	}
	public void setLogMeganetDAO(LogMeganetDAO logMeganetDAO) {
		this.logMeganetDAO = logMeganetDAO;
	}
	
}
