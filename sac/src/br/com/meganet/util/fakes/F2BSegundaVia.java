package br.com.meganet.util.fakes;

import java.net.URL;
import java.security.Security;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import br.com.meganet.util.ConfigUtil;

public class F2BSegundaVia {

    public static void main(String args[]) {
        try {
            // Create request endpoint
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
            URL endpoint = new URL("http://www.f2b.com.br/WSBillingSegundaVia");
            // Create request message
            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage request = mf.createMessage();
            // Get message elements
            SOAPPart part = request.getSOAPPart();
            SOAPEnvelope envelope = part.getEnvelope();
            // Remove header
            SOAPHeader header = envelope.getHeader();
            ((Node) header).detachNode();
             // Add content to body
            SOAPBody body = envelope.getBody();
            Name F2bSegundaViaName = envelope.createName("F2bSegundaVia", "m", "http://www.f2b.com.br/soap/wsbillingsegundavia.xsd");
            SOAPBodyElement F2bSegundaVia = body.addBodyElement(F2bSegundaViaName);
             // Add cliente
            SOAPElement cliente = F2bSegundaVia.addChildElement("cliente");
            cliente.addAttribute(envelope.createName("conta"), ConfigUtil.getInstance().getProperty("boleto_f2b_usuario", "0000000000000000"));
            cliente.addAttribute(envelope.createName("senha"), ConfigUtil.getInstance().getProperty("boleto_f2b_senha", "000000"));
            // Add sacado
            SOAPElement sacado = F2bSegundaVia.addChildElement("sacado");
            sacado.addAttribute(envelope.createName("txt_email"), "	efrenlixo@gmail.com");
            sacado.addAttribute(envelope.createName("num_cpf"), "76983927168");
            //somente_registradas:1=exibe cobrancas registradas;2=exibe cobrancas registradas e pagas
			sacado.addAttribute(envelope.createName("somente_registradas"), "1");

            System.out.println("request: " + request);
            request.writeTo(System.out);
            System.out.println("");

            // Send request
            SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
            SOAPConnection sc = scf.createConnection();
            SOAPMessage response = sc.call(request, endpoint);

            //get response
            System.out.println("response: " + response);
            response.writeTo(System.out);
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
