package br.com.meganet.bo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.meganet.exception.GAPBDException;
import br.com.meganet.hbm.DAO.DominiosDAO;
import br.com.meganet.hbm.vo.Dominios;
import br.com.meganet.threads.Monitor;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Util;


public class DominiosBO {
	
	private DominiosDAO dominiosDAO;

	public void setDominiosDAO(DominiosDAO dominiosDAO) {
		this.dominiosDAO = dominiosDAO;
	}

	public Map<String, String> buscaTodosMapasDeConfiguracao() throws GAPBDException {
		List<Dominios> dom = carregaDominiosDeConfiguracao();
		
//		verificaDesenvolvimento(dom);
		
		Map<String, String> ret = new TreeMap<String, String>();
		for (Iterator<Dominios> iterator = dom.iterator(); iterator.hasNext();) {
			Dominios dominios = (Dominios) iterator.next();
			if(dominios.getDominiosTipo() == 3){
				ret.put(dominios.getDominiosChave(), dominios.getDominiosValor());
			}
		}
		return ret;
	}

	public void verificaDesenvolvimento(List<Dominios> dom) {
		try{
			for (Iterator<Dominios> iterator = dom.iterator(); iterator.hasNext();) {
				Dominios dominios = (Dominios) iterator.next();
				if(dominios.getDominiosChave().equalsIgnoreCase("hora_inicio_comandos_HHMM")){
					Calendar c = new GregorianCalendar();
					c.setTimeInMillis(System.currentTimeMillis());
					c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
					dominios.setDominiosValor(c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE));
				}
				
				if(dominios.getDominiosChave().equalsIgnoreCase("envia_comando_usuario")){
					dominios.setDominiosValor("false");
				}
				
				if(dominios.getDominiosChave().equalsIgnoreCase("envia_email")){
					dominios.setDominiosValor("false");
				}
				
				if(dominios.getDominiosChave().equalsIgnoreCase("envia_comando_arquivo")){
					dominios.setDominiosValor("false");
				}
				if(dominios.getDominiosChave().equalsIgnoreCase("envia_comando_velocidade_media")){
					dominios.setDominiosValor("false");
				}
				
				if(dominios.getDominiosChave().equalsIgnoreCase("envia_comando_velocidade_interface")){
					dominios.setDominiosValor("false");
				}
				if(dominios.getDominiosChave().equalsIgnoreCase("envia_comando_verificar_torre_atual")){
					dominios.setDominiosValor("false");
				}
				
				if(dominios.getDominiosChave().equalsIgnoreCase("envia_bol_f2b")){
					dominios.setDominiosValor("false");
				}
				
				if(dominios.getDominiosChave().equalsIgnoreCase("verifica_situacao_boleto")){
					dominios.setDominiosValor("false");
				}
				
			}
		}catch (Exception e) {
		}
	}

	public List<Dominios> carregaDominiosDeConfiguracao() throws GAPBDException {
		List<Dominios> dom = dominiosDAO.findByCriteria(
				Order.asc(DominiosDAO.CHAVE), 
				Expression.eq(DominiosDAO.TIPO, new Long(3)));
		List<Dominios> ret = new ArrayList<Dominios>();
		for (Iterator<Dominios> iterator = dom.iterator(); iterator.hasNext();) {
			Dominios dominios = (Dominios) iterator.next();
			ret.add(Util.copiaPropriedades(dominios));
		}
		return ret;
	}

	public String salvaConfAvancadas(List<Dominios> dominios, boolean primeiroAcesso) throws GAPBDException{
		for (Iterator<Dominios> iterator = dominios.iterator(); iterator.hasNext();) {
			Dominios dom = (Dominios) iterator.next();
			List<Dominios> domsBD = dominiosDAO.findByProperty(DominiosDAO.CHAVE, dom.getDominiosChave());
			if(domsBD != null && domsBD.size() > 0){
				Dominios domBD = domsBD.get(0);
				domBD.setDominiosValor(dom.getDominiosValor());
				dominiosDAO.attachDirty(domBD);
			}
		}
		if(primeiroAcesso){
			Map<String, String> ret = buscaTodosMapasDeConfiguracao();
			ConfigUtil.getInstance().setPropertie(ret);
			return null;
		}
		String m = "";
		String s = "";
		
		if(Monitor.m < 10){
			m = "0" + Monitor.m;
		}else{
			m = "" + Monitor.m;
		}
		if(Monitor.s < 10){
			s = "0" + Monitor.s;
		}else{
			s = "" + Monitor.s;
		}
		
		return "Alterações efetuadas com sucesso.\nPor razões de estabilidade, espere " + m + " minutos e " + s + " segundos\npara que as alterações entrem em vigor.";
	}

}
