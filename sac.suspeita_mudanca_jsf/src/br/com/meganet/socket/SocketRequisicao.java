package br.com.meganet.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeSet;

import br.com.meganet.hbm.DAO.TorreDAO;
import br.com.meganet.hbm.vo.Torre;
import br.com.meganet.telnet.Main;
import br.com.meganet.telnet.exception.TelnetConnectionException;
import br.com.meganet.util.Logger;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.DHGexParameters;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;


public class SocketRequisicao {
	
	private static Torre torre;
	private static TorreDAO torreDAO;
	private static String resposta = "";
	
	private static Logger log = new Logger(SocketRequisicao.class);
	
	private static Connection con = null;
	private static Session ses = null;
	private static boolean estaConetado = false;
	
	private static void conecta() throws IOException {
		log.info("Conectando ao servidor " + torre.getTorreNome() + " ip:" + torre.getTorreIpConexao());
		try{
			if (con == null) {
				con = new Connection(torre.getTorreIpConexao(), torre.getTorrePorta());
				con.setDHGexParameters(new DHGexParameters(1024));
				con.connect();
				estaConetado = con.authenticateWithPassword(torre.getTorreUsuario(), torre.getTorreSenha());
			}
		}catch (IOException e) {
			log.erro("Erro na primeira tentativa de conectar com a torre : "+ torre.getTorreNome() + ", IP " + torre.getTorreIpConexao() + ", porta " + torre.getTorrePorta());
			log.info("Conectando ao servidor " + torre.getTorreNome() + " ip:" + torre.getTorreIpConexao2());
			try{
				con = new Connection(torre.getTorreIpConexao2(), torre.getTorrePorta());
				con.setDHGexParameters(new DHGexParameters(1024));
				con.connect();
				estaConetado = con.authenticateWithPassword(torre.getTorreUsuario(), torre.getTorreSenha());
				if(estaConetado){
					String ip = torre.getTorreIpConexao();
					String ip2 = torre.getTorreIpConexao2();
					torre.setTorreIpConexao(ip2);
					torre.setTorreIpConexao2(ip);
					torreDAO.attachDirty(torre);
				}
			}catch (IOException e3) {
				log.erro("Erro na segunda tentativa de conectar com a torre : "+ torre.getTorreNome() + ", IP " + torre.getTorreIpConexao() + ", porta " + torre.getTorrePorta());
				throw new IOException("Erro na conexão SSH.");
			}catch (Exception e2) {
				log.erro("Exceção \""+ e2.getCause().getMessage() +"\" ao conectar com a torre : " + torre.getTorreNome(), e2);
			}
		}
		
	}
	
	public static boolean testaConexao(String ip, int porta) {
		log.info("Realizando teste de conexão com o IP:" + ip + ", na porta :" + porta);
		try{
			Connection con2 = new Connection(ip, porta);
			con2.setDHGexParameters(new DHGexParameters(1024));
			con2.connect();
			try {
				con2.close();
			} catch (Exception e) {}
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public static void closeSocket() {
		if ( con != null ) {
			try {
				con.close();
			} catch (Exception e) {}
		}
		con = null;
	}	
	
	protected static String read() throws IOException {
		StringBuffer buffer = new StringBuffer();
		
		InputStream stdout = new StreamGobbler(ses.getStdout());
		BufferedReader br = new BufferedReader(new InputStreamReader(stdout));

		String line = br.readLine();
        while( line != null ){
        	buffer.append( line + "\n" );
            line = br.readLine();
        }
		return buffer.toString();
	}
	
	public static synchronized String enviarRequisicao(Mensagem requisicao) throws Exception {
		String resp = "";
		TreeSet<Comando> cmds = requisicao.getComando();
		for (Iterator<Comando> iterator = cmds.iterator(); iterator.hasNext();) {

			Comando comando = (Comando) iterator.next();
			Torre t = torreDAO.findById(comando.getTorre().getTorreId());
			boolean utilizaIntermediario = t.getTorreUtilizarIntermediador();
			if(utilizaIntermediario){
				resp += conexaoViaIntermediario(comando, t.getTorreIpIntermediador(), t.getTorrePortaIntermediador());
			}else{
				resp += conexaoDireta(comando);
			}
		}
		return resp;
	}
	
	private static String conexaoViaIntermediario(Comando comando, String ipInterm, Integer portaInterm) throws Exception{
		RequesterViaIntermediador req = new RequesterViaIntermediador(comando, ipInterm, portaInterm);
		String ret = req.enviaComando();
		return ret;
	}

	private static String conexaoDireta(Comando comando) throws Exception{
		try {
			torre = comando.getTorre();
			resposta = "";
			conecta();
			enviaComando(comando);
			return resposta;
		}finally{
			closeSocket();
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	private static void enviaComando(Comando comando) throws IOException {
		log.info("Enviando comando: " + comando.getComando());
		if (estaConetado) {
			ses = con.openSession();
			if(comando.getComando().indexOf("queue simple move") > -1){
				try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			ses.execCommand(comando.getComando());
			String temp = read();
			log.info("Resposta do servidor - " + temp);
			if(!("".equalsIgnoreCase(temp) || temp == null)){
				resposta = resposta + temp + "\n";
			}
		}else{
			log.erro("Comando não enviado. - " + comando.getComando() + "\n" + "Erro na tentativa de conectar com a torre : "+ torre.getTorreNome());
		}
	}

	public static synchronized String enviarRequisicaoTelnet(Mensagem requisicao) throws Exception {
		
		Main helper = Main.getMain();
		try {
			StringBuffer temp = new StringBuffer();
			
			try {
				helper.connectTelnet(torre.getTorreIpConexao(), "23");
				estaConetado = true;
			} catch (TelnetConnectionException e) {
				try {
					helper.connectTelnet(torre.getTorreIpConexao2(), "23");
					estaConetado = true;
				} catch (TelnetConnectionException e1) {
					e1.printStackTrace();
					estaConetado = false;
				}
			}
			helper.sendMessage(torre.getTorreUsuario());
			helper.sendMessage(torre.getTorreSenha());
			if (estaConetado) {
				for (Iterator<Comando> iterator = requisicao.getComando().iterator(); iterator.hasNext();) {
					Comando comando = (Comando) iterator.next();
					temp.append(helper.sendMessage2(comando.getComando(), 5500));
					Thread.sleep(2000);
				}
			}
			System.out.println("///////////////////////////////////////////////\n " + temp.toString());
			return temp.toString();
		} catch (Exception e) {
			throw new Exception();
		}finally{
			helper.fecharConexao();
		}
	}

	public static TorreDAO getTorreDAO() {
		return torreDAO;
	}

	public void setTorreDAO(TorreDAO torreDAO) {
		SocketRequisicao.torreDAO = torreDAO;
	}

}