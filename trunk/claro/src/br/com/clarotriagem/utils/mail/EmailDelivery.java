package br.com.clarotriagem.utils.mail;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import br.com.clarotriagem.utils.singleton.mapas.ConfigUtil;

public class EmailDelivery {

	public final static String HIGH_PRIORITY = "1";
	public final static String NORMAL_PRIORITY = "3";
	public final static String LOW_PRIORITY = "5";

	EmailMimeMessage msg = null;
	Session session = null;
	Properties props = null;
	Multipart mp = null;

	String smtp_host = new String("");
	String smtp_username = null;
	String smtp_password = null;

	private String strMsg;

	boolean usingAuthentication = false;
	SimpleAuth auth = new SimpleAuth(
			ConfigUtil.getInstance().getProperty("email_administrativo_usr","teste@teste.com.br"), 
			ConfigUtil.getInstance().getProperty("email_administrativo_sen","123456"));

	public EmailDelivery() {
		props = System.getProperties();
		session = Session.getDefaultInstance(props, auth);
		session.setDebug(false);
		msg = new EmailMimeMessage(session);
		mp = new MimeMultipart();
		try {
			this.setSMTPHost(
					ConfigUtil.getInstance().getProperty("email_endereco_servidor","mail.teste.com.br"), 
					ConfigUtil.getInstance().getProperty("email_administrativo_usr","teste@teste.com.br"), 
					ConfigUtil.getInstance().getProperty("email_administrativo_sen","123456"));
		} catch (MessagingException e) {}
	}

	public void setSMTPHost(String p_host) throws MessagingException {
		this.setSMTPHost(p_host, null, null);
	}

	public void setSMTPHost(String p_host, String p_username, String p_password) throws MessagingException {
		System.out.println("EmailDelivery in setSMTPHost Method. Host: " + p_host + " Username: " + p_username);
		smtp_host = p_host;
		smtp_username = p_username;
		smtp_password = p_password;

		props.put("mail.smtp.host", smtp_host);

		if ((smtp_username != null && smtp_username.length() > 0) || (smtp_password != null && smtp_password.length() > 0)) {
			props.put("mail.smtp.auth", "true");
			usingAuthentication = true;
		} else {
			usingAuthentication = false;
		}
		System.out.println("EmailDelivery in setSMTPHost Method. UsingAuthenticaton: " + usingAuthentication);
	}

	public void setSMTPPort(int p_port) throws Exception {
		props.put("mail.smtp.port", String.valueOf(
				ConfigUtil.getInstance().getIntProperty("email_porta_smtp","25")));
	}

	public void setTo(String p_to) throws MessagingException, UnsupportedEncodingException {
		this.setTo(p_to, null);
	}

	public void setTo(String p_to, String p_to_name) throws MessagingException, UnsupportedEncodingException {
		this.processRecipientList(Message.RecipientType.TO, p_to, p_to_name);
	}

	public void setCC(String p_cc) throws MessagingException, UnsupportedEncodingException {
		this.setCC(p_cc, null);
	}

	public void setCC(String p_cc, String p_cc_name) throws MessagingException, UnsupportedEncodingException {
		this.processRecipientList(Message.RecipientType.CC, p_cc, p_cc_name);
	}

	public void setBCC(String p_bcc) throws MessagingException, UnsupportedEncodingException {
		this.setBCC(p_bcc, null);
	}

	public void setBCC(String p_bcc, String p_bcc_name) throws MessagingException, UnsupportedEncodingException {
		this.processRecipientList(Message.RecipientType.BCC, p_bcc, p_bcc_name);
	}

	private void processRecipientList(Message.RecipientType recipientType, String p_to, String p_to_name) throws MessagingException, UnsupportedEncodingException {
		StringTokenizer st = new StringTokenizer(p_to, ",");
		int tokenCount = st.countTokens();
		InternetAddress[] recipientList = new InternetAddress[tokenCount];

		for (int i = 0; st.hasMoreTokens(); i++) {
			String msgTo = st.nextToken();
			if (msgTo != null && msgTo.trim().length() > 0) {
				if (tokenCount == 1 && p_to_name != null && p_to_name.length() > 0) {
					recipientList[i] = new InternetAddress(msgTo, p_to_name);
				} else {
					recipientList[i] = new InternetAddress(msgTo);
				}
			}
		}
		msg.setRecipients(recipientType, recipientList);
	}

	public void setFrom(String p_from) throws MessagingException, UnsupportedEncodingException {
		this.setFrom(p_from, null);
	}

	public void setFrom(String p_from, String p_from_name) throws MessagingException, UnsupportedEncodingException {
		String msg_from = p_from;
		String msg_from_name = p_from_name;

		if (p_from_name != null && p_from_name.length() > 0) {
			msg.setFrom(new InternetAddress(msg_from, msg_from_name));
		} else {
			msg.setFrom(new InternetAddress(msg_from));
		}
	}

	public void setReplyTo(String p_reply_to) throws MessagingException, UnsupportedEncodingException {
		this.setReplyTo(p_reply_to, null);
	}

	public void setReplyTo(String p_reply_to, String p_reply_to_name) throws MessagingException, UnsupportedEncodingException {
		String msg_reply_to = p_reply_to;
		String msg_reply_to_name = p_reply_to_name;

		if (msg_reply_to_name != null && msg_reply_to_name.length() > 0) {
			InternetAddress[] address = { new InternetAddress(msg_reply_to, msg_reply_to_name) };
			msg.setReplyTo(address);
		} else {
			InternetAddress[] address = { new InternetAddress(msg_reply_to) };
			msg.setReplyTo(address);
		}
	}

	public void setSubject(String p_subject) throws MessagingException {
		msg.setSubject(p_subject);
	}

	public void setBody(String p_body) throws MessagingException {
		MimeBodyPart mbp_msgtext = new MimeBodyPart();
		strMsg = p_body;
		mbp_msgtext.setText(p_body);
		mp.addBodyPart(mbp_msgtext);
	}

	public void setHeader(String p_name, String p_value) throws MessagingException {
		msg.setHeader(p_name, p_value);
	}

	public void setPriority(String priorityValue) throws Exception {
		if (priorityValue != null && priorityValue.trim().length() > 0) {
			msg.setHeader("X-Priority", priorityValue);
		}
	}

	public void setMessageID(String p_value) throws MessagingException {
		msg.setMessageID(p_value);
	}

	public void forwardMessage(String p_msgFrom, String p_msgTo, String p_msgSubject, String p_msgBody, MimeMessage msgOrig) throws MessagingException, UnsupportedEncodingException {
		forwardMessage(p_msgFrom, null, p_msgTo, null, p_msgSubject, p_msgBody, msgOrig);
	}

	public void forwardMessage(String p_msgFrom, String p_msgFromText, String p_msgTo, String p_msgToText, String p_msgSubject, String p_msgBody, MimeMessage msgOrig) throws MessagingException, UnsupportedEncodingException {
		this.setFrom(p_msgFrom, p_msgFromText);
		this.setTo(p_msgTo, p_msgToText);
		this.setSubject(p_msgSubject);
		this.setBody(p_msgBody);

		MimeBodyPart part = new MimeBodyPart();
		part.setDisposition(Part.ATTACHMENT);
		part.setFileName(msgOrig.getSubject());
		part.setContent(msgOrig, "message/rfc822");

		mp.addBodyPart(part);
	}

	public void addFileAttachment(String p_fileName) throws MessagingException {
		MimeBodyPart mbp_file = new MimeBodyPart();

		FileDataSource fds = new FileDataSource(p_fileName);
		mbp_file.setDataHandler(new DataHandler(fds));
		mbp_file.setFileName(fds.getName());

		mp.addBodyPart(mbp_file);
	}

	public void addFileAttachmentFromStream(String p_fileName, InputStream istrm, int stream_len) throws MessagingException, IOException {

		String logMsg = "";
		byte b[] = new byte[stream_len];

		BufferedInputStream bistrm = new BufferedInputStream(istrm);
		int bytes_read = 0;
		bytes_read = bistrm.read(b, 0, stream_len);

		logMsg = "Bytes Read From Stream: " + Integer.toString(bytes_read);
		System.out.println(logMsg);

		System.out.println(logMsg);

		InternetHeaders hdr = new InternetHeaders();
		hdr.addHeader("Content-Type", "application/octet-stream; name=\"" + p_fileName + "\"");
		hdr.addHeader("Content-Transfer-Encoding", "base64");
		hdr.addHeader("Content-Disposition", "inline; filename=\"" + p_fileName + "\"");

	}

	public void sendMsg() throws MessagingException {
		if (ConfigUtil.getInstance().getBooleanProperty("envia_email",false)) {

			msg.setSentDate(new Date());
			msg.setContent(strMsg, "text/html");

			msg.saveChanges();

			if (usingAuthentication) {
				Transport transport = session.getTransport("smtp");
				transport.connect(smtp_host, smtp_username, smtp_password);
				transport.sendMessage(msg, msg.getAllRecipients());

				transport.close();
			} else {
				Transport.send(msg);
			}
		}
	}

	public static void main(String args[]) {
		try {
			System.out.println("Preparing to send email.");

			EmailDelivery ed = new EmailDelivery();
			ed.setTo("efrenjunior@gmail.com, efrenjunior@gmail.com");
			ed.setFrom("contato@meganetdf.com.br", "Contato");
			ed.setReplyTo("efrenlixo@gmail.com", "efren junior");
			ed.setSubject("Contato site assinesuatv.net");
			ed.setBody("<em><strong>TEste de e-mail</strong></em>");
			ed.sendMsg();

			System.out.println("Test message sent.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
