package br.com.meganet.util.fakes;


public class F2BSituacaoCobranca {
//    
//	public static void main(String[] args){
//		F2BSituacaoCobranca.dd();
//	}
//	
//    public static String dd() {
//        try {
//            // Create request endpoint
//            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//            System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
//            URL endpoint = new URL("https://www.f2b.com.br/WSBillingStatus");
//            // Create request message
//            MessageFactory mf = MessageFactory.newInstance();
//            SOAPMessage request = mf.createMessage();
//            // Get message elements
//            SOAPPart part = request.getSOAPPart();
//            SOAPEnvelope envelope = part.getEnvelope();
//            // Remove header
//            SOAPHeader header = envelope.getHeader();
//            ((Node)header).detachNode();
//            // Add content to body
//            SOAPBody body = envelope.getBody();
//            Name F2bSituacaoCobrancaName = envelope.createName("F2bSituacaoCobranca", "m", "http://www.f2b.com.br/soap/wsbillingstatus.xsd");
//            SOAPBodyElement F2bSituacaoCobranca = body.addBodyElement(F2bSituacaoCobrancaName);
//            // Add mensagem
//            SOAPElement mensagem = F2bSituacaoCobranca.addChildElement("mensagem");
//            mensagem.addAttribute(envelope.createName("data"), (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
//            mensagem.addAttribute(envelope.createName("numero"), (new SimpleDateFormat("HHmmss")).format(new Date()));
//
//            // Add cliente
//            SOAPElement cliente = F2bSituacaoCobranca.addChildElement("cliente");
//            cliente.addAttribute(envelope.createName("conta"), ConfigUtil.getInstance().getProperty("boleto_f2b_usuario", "0000000000000000"));
//            cliente.addAttribute(envelope.createName("senha"), ConfigUtil.getInstance().getProperty("boleto_f2b_senha", "000000"));
//
//            // Add cobranca
//            SOAPElement cobranca = F2bSituacaoCobranca.addChildElement("cobranca");
//
//		// Enviar p/ o nosso servidor 
//		// ********************** Intervalos de cobrancas ************************************
////              cobranca.addAttribute(envelope.createName("numero"), "85423");
//		// e/ou
////              cobranca.addAttribute(envelope.createName("numero_final"), "85425");
//		// ***********************************************************************************
//
//		// ou ---------------
//
//		// ********************** Intervalo (Data de registro) *******************************
////            cobranca.addAttribute(envelope.createName("registro"), "2004-10-07");
//		// e/ou
////            cobranca.addAttribute(envelope.createName("registro_final"), "2004-10-27");
//		// ***********************************************************************************
//
//		// ou ---------------
//            cobranca.addAttribute(envelope.createName("situacao"), "2");
//		// ********************** Intervalo (Data de vencimento) *****************************
//            cobranca.addAttribute(envelope.createName("vencimento"), "2010-06-03");
//		// e/ou
//            cobranca.addAttribute(envelope.createName("vencimento_final"), "2010-10-01");
//		// ***********************************************************************************
//
//		// ou ---------------
//	
//		// ********************** Intervalo (Data de processamento) **************************
////            cobranca.addAttribute(envelope.createName("processamento"), "2004-10-30");
//		// e/ou
////            cobranca.addAttribute(envelope.createName("processamento_final"), "2004-11-30");
//		// ***********************************************************************************
//
//		// ou ---------------
//	
//		// ********************** Intervalo (Data de credito) ********************************
////            cobranca.addAttribute(envelope.createName("credito"), "2004-10-30");
//		// e/ou
////            cobranca.addAttribute(envelope.createName("credito_final"), "2004-11-30");
//		// ***********************************************************************************
//
//		// e/ou ---------------
////            cobranca.addAttribute(envelope.createName("cod_sacado"), "XYZ1234");
//		// e/ou ---------------
////            cobranca.addAttribute(envelope.createName("cod_grupo"), "GrupoTeste");
//		// e/ou ---------------
////            cobranca.addAttribute(envelope.createName("tipo_pagamento"), "B");
//        //"B" - Boleto; "C" - Cartao de Credito, "D" - Cartao de Debito, "F" - Entre contas F2b,
//        //"M" - Registrado pela F2b, "S" - Registrado pelo sacador, "T" - Transferencia on-line.
//		// e/ou ---------------
////            cobranca.addAttribute(envelope.createName("numero_pagamento"), "123456");
//
//			System.out.println("request: " + request);
//			request.writeTo(System.out);
//			System.out.println("");
//            
//            // Send request
//            SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
//            SOAPConnection sc = scf.createConnection();
//            SOAPMessage response = sc.call(request, endpoint);
//			
//            System.out.println("response: " + response);
//			ByteOutputStream b = new ByteOutputStream();
//			response.writeTo(b);
//			
//			System.out.println(b.toString());
//			return b.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    
}
