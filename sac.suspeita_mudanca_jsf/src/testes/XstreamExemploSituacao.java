package testes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import br.com.meganet.servicos.vo.Body;
import br.com.meganet.servicos.vo.Cliente;
import br.com.meganet.servicos.vo.Cobranca;
import br.com.meganet.servicos.vo.Envelope;
import br.com.meganet.servicos.vo.Retorno;
import br.com.meganet.servicos.vo.Sacado;
import br.com.meganet.servicos.vo.Total;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;

public class XstreamExemploSituacao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sacado sacado = new Sacado();
		sacado.setEmail("joseoliveira@f2b.com.br");
		sacado.setNome("José Oliveira");
		sacado.setNumero("0000000001");

		Cobranca cobranca = new Cobranca();
		cobranca.setNome("José Oliveira");
		cobranca.setEmail("joseoliveira@f2b.com.br");
		cobranca.setUrl("http://www.f2b.com.br/Billing?id=00000541587000344325798");
		cobranca.setNosso_numero("0003443257");
		cobranca.setNumero("0003443257");
		cobranca.setTaxa_registro("0.00");

		Retorno retorno = new Retorno();
		retorno.getCobranca().add(cobranca);
		retorno.setSacado(sacado);
		retorno.setXmlns("http://www.f2b.com.br/soap/wsbilling.xsd");
		retorno.setLog("OK&#13; ");

		Body body = new Body();
		body.setRetorno(retorno);

		Envelope envelope = new Envelope();
		envelope.setBody(body);
		envelope.setXmlSoapEnv("http://schemas.xmlsoap.org/soap/envelope/");

		
		
		XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyReplacer("__", "_")));

		xstream.alias("SOAP-ENV:Envelope", Envelope.class);
		xstream.aliasField("SOAP-ENV:Body", Envelope.class, "body");
		xstream.aliasField("xmlns:SOAP-ENV", Envelope.class, "xmlSoapEnv");
		xstream.useAttributeFor(Envelope.class, "xmlSoapEnv");

		xstream.aliasField("m:F2bSituacaoCobrancaRetorno", Body.class, "retorno");

		xstream.useAttributeFor(Retorno.class, "xmlns");
		xstream.aliasField("xmlns:m", Retorno.class, "xmlns");

		xstream.useAttributeFor(Sacado.class, "numero");
		xstream.addImplicitCollection(Retorno.class, "cobranca", Cobranca.class);

		xstream.alias("cobranca", Cobranca.class);
		xstream.useAttributeFor(Cobranca.class, "nosso_numero");
		xstream.useAttributeFor(Cobranca.class, "numero");
		xstream.useAttributeFor(Cobranca.class, "num_document");
		xstream.useAttributeFor(Cobranca.class, "taxa_registro");
		xstream.useAttributeFor(Cobranca.class, "registro");
		xstream.useAttributeFor(Cobranca.class, "situacao");
		xstream.useAttributeFor(Cobranca.class, "valor");
		xstream.useAttributeFor(Cobranca.class, "vencimento");
		
		
		xstream.alias("cliente", Cliente.class);
		xstream.useAttributeFor(Cliente.class, "cod_state");
		xstream.useAttributeFor(Cliente.class, "cod_user");
		xstream.useAttributeFor(Cliente.class, "cod_zipcode");
		xstream.useAttributeFor(Cliente.class, "conta");
		xstream.useAttributeFor(Cliente.class, "electoral_donation_user");
		xstream.useAttributeFor(Cliente.class, "email");
		xstream.useAttributeFor(Cliente.class, "name_address");
		xstream.useAttributeFor(Cliente.class, "name_address_complement");
		xstream.useAttributeFor(Cliente.class, "name_city");
		xstream.useAttributeFor(Cliente.class, "name_city_district");
		xstream.useAttributeFor(Cliente.class, "num_address");
		xstream.useAttributeFor(Cliente.class, "nome");
		xstream.useAttributeFor(Cliente.class, "num_phone_area_code_com");
		xstream.useAttributeFor(Cliente.class, "num_phone_com");


		xstream.alias("total", Total.class);
		xstream.useAttributeFor(Total.class, "numero_cobrancas");
		xstream.useAttributeFor(Total.class, "numero_pago_bloqueado");
		xstream.useAttributeFor(Total.class, "numero_pago_liberado");
		xstream.useAttributeFor(Total.class, "valor_cobrado");
		xstream.useAttributeFor(Total.class, "valor_pago_bloqueado");
		xstream.useAttributeFor(Total.class, "valor_pago_liberado");
		
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(XstreamExemploSituacao.class.getResourceAsStream("/testes/situacao.xml")));
		String xmlEx = "";
		String linha = "";
		try {
			while ((linha = reader.readLine()) != null) {
				xmlEx += linha;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			Envelope e = (Envelope) xstream.fromXML(xmlEx);
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println(xstream.toXML(envelope));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}