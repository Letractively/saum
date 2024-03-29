package br.com.meganet.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage; 
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication; 

public class SendMailGMAIL {
	
	private String mailSMTPServer;
	private String mailSMTPServerPort;
	
	/*
	 * quando instanciar um Objeto ja sera atribuido o servidor SMTP do GMAIL 
	 * e a porta usada por ele
	 */
	public SendMailGMAIL() { //Para o GMAIL 
		mailSMTPServer = "smtp.gmail.com";
		mailSMTPServerPort = "465";
	}
	/*
	 * caso queira mudar o servidor e a porta, so enviar para o contrutor
	 * os valor como string
	 */
	public SendMailGMAIL(String mailSMTPServer, String mailSMTPServerPort) { //Para outro Servidor
		this.mailSMTPServer = mailSMTPServer;
		this.mailSMTPServerPort = mailSMTPServerPort;
	}
	
	public void s(){
		HtmlTableGenerator htg = new HtmlTableGenerator();
		htg.setTituloTabela("Cadastro do provedor de internet.<br>Anote sua senha.");
		htg.getDados().put("Nome", "contato");
		htg.getDados().put("Senha", "Teste");
		htg.setRodapeTabela("Meganet a sua internet");
		try{
		sendMail("Meganet", "efrenjunior@gmail.com", "teste e teste", htg.montaTabela());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		SendMailGMAIL sm= new SendMailGMAIL();
		sm.s();
	}
	
	public void sendMail(String from, String to, String subject, String message) throws Exception {
		
		if(ConfigUtil.getInstance().getBooleanProperty("envia_email",false)){
		
			Properties props = new Properties();
			
//			quem estiver utilizando um SERVIDOR PROXY descomente essa parte e atribua as propriedades do SERVIDOR PROXY utilizado
//		    props.setProperty("proxySet","true");
//		    props.setProperty("socksProxyHost","192.168.155.1"); // IP do Servidor Proxy
//		    props.setProperty("socksProxyPort","1080");  // Porta do servidor Proxy
	
			props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP
			props.put("mail.smtp.starttls.enable","true"); 
			props.put("mail.smtp.host", mailSMTPServer); //server SMTP do GMAIL
			props.put("mail.smtp.auth", "true"); //ativa autenticacao
			props.put("mail.smtp.user", ConfigUtil.getInstance().getProperty("email_administrativo_usr","contato@saum.com.br")); //usuario ou seja, a conta que esta enviando o email
			props.put("mail.debug", "true");
			props.put("mail.smtp.port", mailSMTPServerPort); //porta
			props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			
			//Cria um autenticador que sera usado a seguir
			SimpleAuth auth = null;
			auth = new SimpleAuth (
					ConfigUtil.getInstance().getProperty("email_administrativo_usr","contato@saum.com.br"),
					ConfigUtil.getInstance().getProperty("email_administrativo_sen","3fr3n3lj"));
			
			//Session - objeto que ira realizar a conexao com o servidor
			/*Como ha necessidade de autenticacao e criada uma autenticacao que
			 * responsavel por solicitar e retornar o usuario e senha para 
			 * autenticacao */
			Session session = Session.getDefaultInstance(props, auth);
			session.setDebug(true); //Habilita o LOG das ações executadas durante o envio do email
	
			//Objeto que contem a mensagem
			Message msg = new MimeMessage(session);
	
			//Setando o destinatario
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			//Setando a origem do email
			msg.setFrom(new InternetAddress(from));
			//Setando o assunto
			msg.setSubject(subject);
			//Setando o conteudo/corpo do email
			msg.setContent(message,"text/html");
			
			//Objeto encarregado de enviar os dados para o email
			Transport tr;
	
			tr = session.getTransport("smtp"); //define smtp para transporte
			/*
			 *  1 - define o servidor smtp
			 *  2 - seu nome de usuario do gmail
			 *  3 - sua senha do gmail
			 */
			tr.connect(
					mailSMTPServer, 
					ConfigUtil.getInstance().getProperty("email_administrativo_usr","contato@saum.com.br"), 
					ConfigUtil.getInstance().getProperty("email_administrativo_sen","3fr3n3lj"));
			msg.saveChanges(); // don't forget this
			//envio da mensagem
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
		}
	}
	
}

//clase que retorna uma autenticacao para ser enviada e verificada pelo servidor smtp
class SimpleAuth extends Authenticator {
	public String username = null;
	public String password = null;


	public SimpleAuth(String user, String pwd) {
		username = user;
		password = pwd;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication (username,password);
	}
} 
