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

public class F2BAlteraCancelaCobranca { 
    public static void main(String args[]) {
        try {
            // Create request endpoint
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
            URL endpoint = new URL("https://www.f2b.com.br/WSBillingAction");
            // Create request message
            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage request = mf.createMessage();
            // Get message elements
            SOAPPart part = request.getSOAPPart();
            SOAPEnvelope envelope = part.getEnvelope();
            // Remove header
            SOAPHeader header = envelope.getHeader();
            ((Node)header).detachNode();
            // Add content to body
            SOAPBody body = envelope.getBody();
            Name F2bAcaoCobrancaName = envelope.createName("F2bAcaoCobranca", "m", "http://www.f2b.com.br/soap/wsbillingaction.xsd");
            SOAPBodyElement F2bAcaoCobranca = body.addBodyElement(F2bAcaoCobrancaName);
            // Add mensagem
            SOAPElement mensagem = F2bAcaoCobranca.addChildElement("mensagem");
            mensagem.addAttribute(envelope.createName("data"), (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(new java.util.Date()));
            mensagem.addAttribute(envelope.createName("numero"), "1");

            // Add cliente
            SOAPElement cliente = F2bAcaoCobranca.addChildElement("cliente");
            cliente.addAttribute(envelope.createName("conta"), ConfigUtil.getInstance().getProperty("boleto_f2b_usuario", "0000000000000000"));
            cliente.addAttribute(envelope.createName("senha"), ConfigUtil.getInstance().getProperty("boleto_f2b_senha", "000000"));

            // Add cobranca
            SOAPElement cobranca = F2bAcaoCobranca.addChildElement("acao_cobranca");

		// Enviar p/ o nosso servidor 
		// ********************** Numero da cobranca ************************************
              cobranca.addAttribute(envelope.createName("numero"), "0003443316");
		// ***********************************************************************************

// e/ou ---------------

		// ********************** Acao Cancelar Cobranca (use 1 para cancelar ou deixe vazio para não realizar essa acao) *******************************
            cobranca.addAttribute(envelope.createName("cancelar_cobranca"), "1");
// ou ----------------
	
            // ********************** Acao Registrar Pagamento*****************************
            cobranca.addAttribute(envelope.createName("registrar_pagamento"), "");
// e -----------------
            
            cobranca.addAttribute(envelope.createName("registrar_pagamento_valor"), "");
// e -----------------
            
            cobranca.addAttribute(envelope.createName("dt_registrar_pagamento"), "");
	    // ***********************************************************************************
// ou ----------------

            // ********************** Acao Cancelar Multa (use 1 para cancelar ou deixe vazio para não realizar essa acao) *******************************
            cobranca.addAttribute(envelope.createName("cancelar_multa"), "");
// e/ou ---------------
	
            // ********************** Acao Permitir Pagamento **************************
            cobranca.addAttribute(envelope.createName("permitir_pagamento"), "");
// e -----------------

            cobranca.addAttribute(envelope.createName("dt_permitir_pagamento"), "");
            // ***********************************************************************************

// e/ou ---------------
	
            // ********************** Acao Reenviar Email **************************
            cobranca.addAttribute(envelope.createName("reenviar_email"), "");
// e -----------------

            cobranca.addAttribute(envelope.createName("email_tosend"), "");
            // ***********************************************************************************

			System.out.println("request: " + request);
			request.writeTo(System.out);
			System.out.println("");
            
            // Send request
            SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
            SOAPConnection sc = scf.createConnection();
            SOAPMessage response = sc.call(request, endpoint);
			
            System.out.println("response: " + response);
			response.writeTo(System.out);
			System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
