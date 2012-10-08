package br.com.meganet.util.fakes;

import java.net.URL;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.sun.net.ssl.internal.ssl.Provider;

public class F2BRegistroCobranca {
    
    public static void main(String args[]) {
        try {
            // Create request endpoint
            Security.addProvider(new Provider());
            System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
            URL endpoint = new URL("https://www.f2b.com.br/WSBilling");
            
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
            Name f2bCobrancaName = envelope.createName("F2bCobranca", "m", "http://www.f2b.com.br/soap/wsbilling.xsd");
            SOAPBodyElement f2bCobranca = body.addBodyElement(f2bCobrancaName);
            // Add mensagem
            SOAPElement mensagem = f2bCobranca.addChildElement("mensagem");
            mensagem.addAttribute(envelope.createName("data"), (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
            mensagem.addAttribute(envelope.createName("numero"), "22222");
            mensagem.addAttribute(envelope.createName("tipo_ws"), "WebService");
            // Add sacador
            SOAPElement sacador = f2bCobranca.addChildElement("sacador");
            sacador.addAttribute(envelope.createName("conta"), ConfigUtil.getInstance().getProperty("boleto_f2b_usuario", "0000000000000000"));
            sacador.addAttribute(envelope.createName("senha"), ConfigUtil.getInstance().getProperty("boleto_f2b_senha", "000000"));
            sacador.addTextNode("Meg@net");
            // Add cobranca
            SOAPElement cobranca = f2bCobranca.addChildElement("cobranca");
            cobranca.addAttribute(envelope.createName("valor"), "12.00");
            cobranca.addAttribute(envelope.createName("tipo_cobranca"), "BT"); 
		// Tipo de cobranca:
		// B - Boleto; C - Cartao de credito; D - Cartao de debito; T - Transferencia On-line
		// Caso queira permitir cobranca por mais de um tipo, enviar as letras juntas. Ex.: "BCD" (Aceitar Boleto, Credito e Debito)
            cobranca.addAttribute(envelope.createName("num_document"), "22222"); 
            cobranca.addAttribute(envelope.createName("cod_banco"), ""); 

            cobranca.addChildElement("demonstrativo").addTextNode("Cobrança F2b");
            cobranca.addChildElement("demonstrativo").addTextNode("Pague em qualquer banco");
            SOAPElement desconto = cobranca.addChildElement("desconto");
            desconto.addAttribute(envelope.createName("valor"), "2.00");
            desconto.addAttribute(envelope.createName("tipo_desconto"), "0");
            desconto.addAttribute(envelope.createName("antecedencia"), "5");
            SOAPElement multa = cobranca.addChildElement("multa");
            multa.addAttribute(envelope.createName("valor"), "1.00");
            multa.addAttribute(envelope.createName("tipo_multa"), "0");
            multa.addAttribute(envelope.createName("valor_dia"), "0.10");
            multa.addAttribute(envelope.createName("tipo_multa_dia"), "0");
            multa.addAttribute(envelope.createName("atraso"), "20");
            // Add agendamento
            SOAPElement agendamento = f2bCobranca.addChildElement("agendamento");
            agendamento.addAttribute(envelope.createName("vencimento"), "2010-07-29");
		//  Descomentar os atributos abaixo caso queria realizar cobrancas com Agendamento //////
            //agendamento.addAttribute(envelope.createName("ultimo_dia"), "n");
            //agendamento.addAttribute(envelope.createName("antecedencia"), "10");
            //agendamento.addAttribute(envelope.createName("periodicidade"), "1");
            //agendamento.addAttribute(envelope.createName("periodos"), "12");
            agendamento.addAttribute(envelope.createName("sem_vencimento"), "n");
            agendamento.addTextNode("Teste 3: direto e agendado");
            // Add sacado
            
			SOAPElement sacado = f2bCobranca.addChildElement("sacado");
			sacado.addAttribute(envelope.createName("grupo"), "teste");
			sacado.addAttribute(envelope.createName("codigo"), "000001");

			// Indica como deve ser feito envio da cobranca
			// para o sacado: "e" significa apenas e-mail, "p"
			// apenas impressa pelo correio, "b" das duas
			// formas anteriores e "n" so e feito o registro da
			// cobranca (o default e "n").
			sacado.addAttribute(envelope.createName("envio"), "e");
			
			sacado.addChildElement("nome").addTextNode("José Oliveira");
			sacado.addChildElement("email").addTextNode("joseoliveira@f2b.com.br");

            SOAPElement endereco = sacado.addChildElement("endereco");
            endereco.addAttribute(envelope.createName("logradouro"), "Rua das Pedras");
            endereco.addAttribute(envelope.createName("numero"), "245");
            endereco.addAttribute(envelope.createName("complemento"), "sala 34");
            endereco.addAttribute(envelope.createName("bairro"), "Itaim Bibi");
            endereco.addAttribute(envelope.createName("cidade"), "São Paulo");
            endereco.addAttribute(envelope.createName("estado"), "SP");
            endereco.addAttribute(envelope.createName("cep"), "04536000");
            
            SOAPElement telefone = sacado.addChildElement("telefone");
            telefone.addAttribute(envelope.createName("ddd"), "11");
            telefone.addAttribute(envelope.createName("numero"), "35551234");

            SOAPElement telefone_comercial = sacado.addChildElement("telefone_com");
            telefone_comercial.addAttribute(envelope.createName("ddd_com"), "22");
            telefone_comercial.addAttribute(envelope.createName("numero_com"), "22222222");
			
            SOAPElement telefone_celular = sacado.addChildElement("telefone_celular");
            telefone_celular.addAttribute(envelope.createName("ddd_cel"), "33");
            telefone_celular.addAttribute(envelope.createName("numero_cel"), "33333333");
            
            sacado.addChildElement("cpf").addTextNode("90300270100");

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
