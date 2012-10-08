package br.com.meganet.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.TreeSet;

import br.com.meganet.hbm.vo.Torre;
import br.com.meganet.util.Logger;

public class RequesterSocket {
	private Socket requestSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Comando cmd;
	private String mensagem;
	private int porta;
	private String url;

	private static Logger log = new Logger(RequesterSocket.class);
	
	public RequesterSocket(Comando cmd, String ipInterm, Integer portaInterm){
		this.porta = portaInterm.intValue();
		this.url = ipInterm;
		this.cmd = cmd;
	}
	
	public String enviaComando() throws Exception{
		try {
			requestSocket = new Socket(url,porta);
			log.info("Conectando ao servidor no endereco "+url+" na porta " + porta);
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			
			mensagem = (String) in.readObject();
			log.info("provider -> " + mensagem);
			sendMessage(preparaMensagem());
			String retorno = (String) in.readObject();
			log.info("provider -> Resposta da transação" + retorno);
			return retorno;
		} catch (ClassNotFoundException classNot) {
			throw new Exception("Formato da resposta desconhecido");
		} catch (UnknownHostException unknownHost) {
			throw new Exception("Tentativa de conexão uma uma URL desconhecida");
		} catch (IOException ioException) {
			throw new Exception("Erro na comunicação com o intermediador, verifique o log do intermediador para mais informção.");
		} finally {
			try {
				in.close();
				out.close();
				requestSocket.close();
			} catch (IOException ioException) {
				log.erro("Erro ao fechar conexao com o servidor socket intermediario.", ioException);
			}
		}
	}

	private String preparaMensagem() {
		return cmd.getComando();
	}

	private void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Torre t = new Torre();
		t.setTorreId(new Long(1));
		t.setTorreInterfaceCliente(false);
		t.setTorreIpConexao("187.5.67.237");
		t.setTorreIpConexao2("187.5.67.237");
		t.setTorreNome("Meganet");
		t.setTorreNomeInterface("Meganet");
		t.setTorrePorta(3129);
		t.setTorreSenha("@3fr3n3lj");
		t.setTorreUsuario("megafull");
		
		Comando c = new Comando();
		c.setComando("/interface pppoe-client print detail without-paging");
		
		TreeSet<Comando> cs = new TreeSet<Comando>();
		cs.add(c);
		
	}
}
