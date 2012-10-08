package testes;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * )
 * SocketServer.java - Simulador de Socket Server da aplicacao (usado para dev/hom
 * @version 1.0
 *
 */
public class SocketServer {

	public static void main(String[] args) {
		while(true) {
		ServerSocket ss = null;
		Socket cliente = null;
		OutputStream os = null;
//		InputStream in = null;
		try {
			// cria servidor de sockets na porta escolhida
			ss = new ServerSocket(527);
			// aguarda solicitacao

			System.out.println("Server: Aguardando na porta " + ss.getLocalPort());
			cliente = ss.accept();
			BufferedReader leitor = new BufferedReader( new InputStreamReader( cliente.getInputStream() ) );
			os = cliente.getOutputStream();
			String msg = "";
			int cont = 1;
			System.out.println("Conectado a "+ cont + cliente.getInetAddress().getHostName());
			// obtem informacao solicitada como sequencia de bytes	
			//args[0] representa a tag q finaliza o xml recebido.
			
			String finalizador = "</mensagem>";
			if(args.length > 0) {
				finalizador = args[0];
			}
			
			while (!(msg = leitor.readLine()).equals(finalizador)) {
				// inicializacao da conexao
				// retornou de accept(), solicitacao recebida
				System.out.println("Linha Recebida   "+ msg);				
			}
		
			String data =	"<?xml version=\"1.0\" encoding=\"UTF-8\"?> "+
							"<mensagem> "+
								"<cabecalho>"+
									"<empresa>BRT</empresa>"+
									"<sistema>COP</sistema>"+
									"<processo>RELMANPEDIDOCLI</processo>"+
									"<data>12/13/2007 11:55:07</data>"+
									"<identificador_requisicao >1197554107061</identificador_requisicao >"+
								"</cabecalho>"+
								"<conteudo><![CDATA["+
								"<root>" +
										"<codigo_mensagem>00</codigo_mensagem>" +
										"<descricao_mensagem>OK</descricao_mensagem>" +
										"<id>123</id>" +
										"<status_pedido>Em Andamento</status_pedido>" +								
										"</root>"+
										"]]></conteudo>"+ 
							"</mensagem>" ;
				byte[] buffer = data.getBytes();
				// escreve data para cliente				
				System.out.println("Server: Enviando \"" + new String(buffer) + "\"");
				os.write(buffer);
				os.flush();

		} catch (Exception e) {
			System.err.println(e);
		} finally {
			// fechando servicos...
			try {
				os.close();
				cliente.close();
				ss.close();
			} catch (Exception e) {
			}
		}
		}
	}
}

