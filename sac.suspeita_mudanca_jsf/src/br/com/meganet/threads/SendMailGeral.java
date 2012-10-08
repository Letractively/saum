package br.com.meganet.threads;

import java.util.Iterator;
import java.util.List;

import br.com.meganet.bo.EnviaEmailBO;
import br.com.meganet.hbm.vo.Avisos;
import br.com.meganet.hbm.vo.Usuario;

public class SendMailGeral extends Thread{
	
	private Avisos aviso;
	private List<Usuario> emails; 
	
	public SendMailGeral(Avisos aviso, List<Usuario> usr) {
		this.aviso = aviso;
		this.emails = usr;
	}

	public void run(){
		try {
			for (Iterator<Usuario> iterator = emails.iterator(); iterator.hasNext();) {
				Usuario usr = (Usuario) iterator.next();
				EnviaEmailBO.enviaEmailAviso(aviso, usr);
			}
			this.interrupt();
		} catch (Exception e) {
			System.out.println("");
			this.interrupt();
		}
	}
	
}
