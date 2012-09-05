package br.com.clarotriagem.utils.mail;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailMimeMessage extends MimeMessage {
    
    protected String messageID = "";
    
    public EmailMimeMessage(javax.mail.Session session) {
        super(session);
    }
    
    void setMessageID(String p_value) {
        messageID = p_value;
    }
    
    protected void updateHeaders() throws MessagingException {
        super.updateHeaders();
        if (messageID != null && messageID.length() > 0) {
            setHeader("Message-ID", messageID);
        }
    }
}
