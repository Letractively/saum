package br.com.meganet.servicos;

import java.io.ByteArrayOutputStream;
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

import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.hbm.vo.InfBoleto;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.BufferLog;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Logger;

import com.sun.net.ssl.internal.ssl.Provider;

public class F2BBoleto {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static Logger logger = new Logger(F2BBoleto.class);

	
//	private String criaBoletoFake(Usuario usr, BufferLog bl) {
//		SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
//		String ret = "";
//		
//		ret += "<?xml version=\"1.0\" encoding=\"utf-8\" ?>";
//		ret += "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">";
//		ret += "<SOAP-ENV:Body>";
//		ret += "<m:F2bCobrancaRetorno xmlns:m=\"http://www.f2b.com.br/soap/wsbilling.xsd\">";
//		ret += "<sacado numero=\""+usr.getUsuarioId()+"\">";
//		ret += "<nome>"+usr.getUsuarioNome()+"</nome>";
//		ret += "<email>"+usr.getUsuarioEmail()+"</email>";
//		ret += "</sacado>";
//		ret += "<cobranca nosso_numero=\""+sdf.format(new Date(System.currentTimeMillis()))+"\" numero=\""+sdf.format(new Date(System.currentTimeMillis()))+"\" taxa_registro=\"0.00\">";
//		ret += "<nome>"+usr.getUsuarioNome()+"</nome>";
//		ret += "<email>"+usr.getUsuarioEmail()+"</email>";
//		ret += "<url>http://www.f2b.com.br/Billing?id=00000541587000344325798</url>";
//		ret += "</cobranca>";
//		ret += "<log>OK&#13; </log>";
//		ret += "</m:F2bCobrancaRetorno>";
//		ret += "</SOAP-ENV:Body>";
//		ret += "</SOAP-ENV:Envelope>";
//		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n criando boleto fake\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		return ret;
//	}

	public String criaBoleto(BoletosGerados b, BufferLog bl, InfBoleto infBoleto, boolean enviaAPenasEMail) throws Exception {
		Usuario usr = b.getUsuario();
		usr.setBoleto(b);
		return criaBoleto(usr, bl, infBoleto, enviaAPenasEMail);
	}

	/**
	 * @param Usuario usr
	 * @return String com o XML de resposta do webservice da F2B
	 * @throws Exception
	 */
	public String criaBoleto(Usuario usr, BufferLog bl, InfBoleto infBoleto, boolean enviaAPenasEMail) throws Exception {
		try {
			bl.append("********CRIANDO BOLETO*********");
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
			((Node) header).detachNode();
			// Add content to body
			SOAPBody body = envelope.getBody();
			Name f2bCobrancaName = envelope.createName("F2bCobranca", "m", "http://www.f2b.com.br/soap/wsbilling.xsd");
			SOAPBodyElement f2bCobranca = body.addBodyElement(f2bCobrancaName);
			// Add mensagem
			SOAPElement mensagem = f2bCobranca.addChildElement("mensagem");
			mensagem.addAttribute(envelope.createName("data"), sdf.format(new Date()));
			mensagem.addAttribute(envelope.createName("numero"), usr.getUsuarioId().toString());
			mensagem.addAttribute(envelope.createName("tipo_ws"), "WebService");
			// Add sacador
			SOAPElement sacador = f2bCobranca.addChildElement("sacador");
			sacador.addAttribute(envelope.createName("conta"), infBoleto.getInfboletoUsr());
			sacador.addAttribute(envelope.createName("senha"), infBoleto.getInfboletoSen());
			sacador.addTextNode(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"));
			// Add cobranca

			////////////////////////////cobranca
			SOAPElement cobranca = f2bCobranca.addChildElement("cobranca");

			cobranca.addAttribute(envelope.createName("valor"), usr.getBoleto().getBoletosgeradosValor());
			// B - Boleto; C - Cartão de crédito; D - Cartão de débito; T - TransferÊncia On-line
			// Caso queira permitir cobrança por mais de um tipo, enviar as
			// letras juntas. Ex.: "BCD" (Aceitar Boleto, Crédito e Débito)
			cobranca.addAttribute(envelope.createName("tipo_cobranca"),  ConfigUtil.getInstance().getProperty("tipo_cobranca", "BT"));

			// Tipo de cobrança:
			cobranca.addAttribute(envelope.createName("num_document"), usr.getBoleto().getBoletosgeradosId().toString());
			cobranca.addAttribute(envelope.createName("cod_banco"), ConfigUtil.getInstance().getProperty("boleto_f2b_cod_banco", ""));
			
			
			cobranca.addChildElement("demonstrativo").addTextNode(System.currentTimeMillis() + " - " + usr.getUsuarioNome());
			if(infBoleto.getInfboletoInstrucao1() != null && !"".equalsIgnoreCase(infBoleto.getInfboletoInstrucao1())){
				cobranca.addChildElement("demonstrativo").addTextNode(infBoleto.getInfboletoInstrucao1());
			}
			if(infBoleto.getInfboletoInstrucao2() != null && !"".equalsIgnoreCase(infBoleto.getInfboletoInstrucao2())){
				cobranca.addChildElement("demonstrativo").addTextNode(infBoleto.getInfboletoInstrucao2());
			}
			if(infBoleto.getInfboletoInstrucao3() != null && !"".equalsIgnoreCase(infBoleto.getInfboletoInstrucao3())){
				cobranca.addChildElement("demonstrativo").addTextNode(infBoleto.getInfboletoInstrucao3());
			}
			
			cobranca.addChildElement("sacador_avalista").addTextNode(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"));
			
			if(usr.getPlanosPacotes().getPlanospacotesDesconto() != null && usr.getPlanosPacotes().getPlanospacotesDesconto() > 0){
				SOAPElement desconto = cobranca.addChildElement("desconto");
				desconto.addAttribute(envelope.createName("valor"), usr.getPlanosPacotes().getPlanospacotesDesconto().toString());
				desconto.addAttribute(envelope.createName("tipo_desconto"), "1");
				desconto.addAttribute(envelope.createName("antecedencia"), usr.getPlanosPacotes().getPlanospacotesLimiteDesconto().toString());
			}

			SOAPElement multa = cobranca.addChildElement("multa");
			multa.addAttribute(envelope.createName("valor"), usr.getPlanosPacotes().getPlanospacotesMulta().toString());
			multa.addAttribute(envelope.createName("tipo_multa"), "1");
			multa.addAttribute(envelope.createName("valor_dia"), usr.getPlanosPacotes().getPlanospacotesJuroMes().toString());
			multa.addAttribute(envelope.createName("tipo_multa_dia"), "1");
			multa.addAttribute(envelope.createName("atraso"), ConfigUtil.getInstance().getProperty("boleto_f2b_qtd_dias_pagamento_atraso", "20"));

			// Add agendamento
			SOAPElement agendamento = f2bCobranca.addChildElement("agendamento");
			agendamento.addAttribute(envelope.createName("vencimento"), sdf.format(usr.getBoleto().getBoletosgeradosDataVencimento()));
			// Descomentar os atributos abaixo caso queria realizar cobranças com Agendamento //////
			// agendamento.addAttribute(envelope.createName("ultimo_dia"), "n");
			// agendamento.addAttribute(envelope.createName("antecedencia"),"10");
			// agendamento.addAttribute(envelope.createName("periodicidade"), "1");
			// agendamento.addAttribute(envelope.createName("periodos"), "12");
			agendamento.addAttribute(envelope.createName("sem_vencimento"), "n");
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
			String diaGrupo = sdf2.format(usr.getBoleto().getBoletosgeradosDataVencimento());
			
			// Add sacado
			SOAPElement sacado = f2bCobranca.addChildElement("sacado");
			sacado.addAttribute(envelope.createName("grupo"), ConfigUtil.getInstance().getProperty("empresa2", "Meganet")+ "-" + diaGrupo);
			sacado.addAttribute(envelope.createName("codigo"), usr.getUsuarioId().toString());
			
			
			sacado.addAttribute(envelope.createName("envio"), usr.getBoleto().getBoletosgeradosTipoEnvioF2B());

			sacado.addChildElement("nome").addTextNode(usr.getUsuarioNome());
			sacado.addChildElement("email").addTextNode(usr.getUsuarioEmail());

			SOAPElement endereco = sacado.addChildElement("endereco");
			endereco.addAttribute(envelope.createName("logradouro"), usr.getUsuarioEndereco());
			endereco.addAttribute(envelope.createName("numero"), "S/N");
			endereco.addAttribute(envelope.createName("complemento"), usr.getUsuarioComplementoEndereco());
			endereco.addAttribute(envelope.createName("bairro"), usr.getUsuarioBairro());
			endereco.addAttribute(envelope.createName("cidade"), usr.getUsuarioCidade());
			endereco.addAttribute(envelope.createName("estado"), usr.getUsuarioEstado());
			endereco.addAttribute(envelope.createName("cep"), usr.getUsuarioCep());

			if (usr.getUsuarioTelefone1() != null && usr.getUsuarioTelefone1().length() >= 12) {
				SOAPElement telefone = sacado.addChildElement("telefone");
				telefone.addAttribute(envelope.createName("ddd"), usr.getUsuarioTelefone1().substring(1, 3));
				telefone.addAttribute(envelope.createName("numero"), usr.getUsuarioTelefone1().substring(4, usr.getUsuarioTelefone1().length()).replace("-", ""));
			}

			if (usr.getUsuarioTelefone2() != null && usr.getUsuarioTelefone2().length() >= 12) {
				SOAPElement telefone_celular = sacado.addChildElement("telefone_celular");
				telefone_celular.addAttribute(envelope.createName("ddd_cel"), usr.getUsuarioTelefone2().substring(1, 3));
				telefone_celular.addAttribute(envelope.createName("numero_cel"), usr.getUsuarioTelefone2().substring(4, usr.getUsuarioTelefone2().length()).replace("-", ""));
			}
			
			String cpfCnpj = usr.getUsuarioCpf().replace(".", "").replace("/", "").replace("-", "");
			
			if(cpfCnpj.length() > 11){
				sacado.addChildElement("cnpj").addTextNode(cpfCnpj);
			}else{
				sacado.addChildElement("cpf").addTextNode(cpfCnpj);
			}
			
			System.out.println("----------------------------------------------------------");
			System.out.println("Enviando boleto: " + usr.getBoleto().getBoletosgeradosId());
			System.out.println("Usuario:         " + usr.getUsuarioNome());

			ByteArrayOutputStream bReq = new ByteArrayOutputStream();
			bl.append("request: " + request);
			request.writeTo(bReq);
			bl.append(bReq.toString());
			bl.append("");
			
			// Send request
			SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
			SOAPConnection sc = scf.createConnection();
			SOAPMessage response = sc.call(request, endpoint);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			bl.append("response: " + response);
			response.writeTo(bos);
			bl.append(bos.toString());

			if(bos.toString().indexOf("Erro") > 0){
				System.out.println("Erro:            " + bos.toString());
			}

			System.out.println("----------------------------------------------------------");

			bl.append("********FIM CRIANDO BOLETO*********");
			Thread.sleep(600);
			return bos.toString();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * @param BoletosGerados b
	 * @param int tipoPesquisa
	 *        <ol>
	 *        <li>Intervalos de cobrancas (número fornecido pela F2B)</li>
	 *        <li>Intervalo (Data de registro)</li>
	 *        <li>Intervalo (Data de vencimento)</li>
	 *        <li>Intervalo (Data de processamento)</li>
	 *        <li>Intervalo (Data de credito)</li>
	 *        <li>Codigo do sacado</li>
	 *        <li>Codigo do grupo</li>
	 *        <li>Tipo de pagamento</li>
	 *        <li>Nomero do pagamento</li>
	 *        </ol>
	 * @return String com o XML de resposta do webservice da F2B
	 * @throws Exception
	 */
	public String verificaBoleto(BoletosGerados b, int tipoPesquisa, BufferLog bl, InfBoleto infBoleto) throws Exception {
		Usuario usr = b.getUsuario();
		usr.setBoleto(b);
		return verificaBoleto(usr, tipoPesquisa, bl, infBoleto);
	}
	/**
	 * @param bl 
	 * @param Usuari usr
	 * @param int tipoPesquisa
	 *        <ol>
	 *        <li>Intervalos de cobranças (número fornecido pela F2B)</li>
	 *        <li>Intervalo (Data de registro)</li>
	 *        <li>Intervalo (Data de vencimento)</li>
	 *        <li>Intervalo (Data de processamento)</li>
	 *        <li>Intervalo (Data de crédito)</li>
	 *        <li>Codigo do sacado</li>
	 *        </ol>
	 * @return String com o XML de resposta do webservice da F2B
	 * @throws Exception
	 */
	public String verificaBoleto(Usuario usr, int tipoPesquisa, BufferLog bl, InfBoleto infBoleto) throws Exception {
		try {
			bl.append("********VERIFICANDO BOLETO*********");
			// Create request endpoint
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
			URL endpoint = new URL("https://www.f2b.com.br/WSBillingStatus");
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
			Name F2bSituacaoCobrancaName = envelope.createName("F2bSituacaoCobranca", "m", "http://www.f2b.com.br/soap/wsbillingstatus.xsd");
			SOAPBodyElement F2bSituacaoCobranca = body.addBodyElement(F2bSituacaoCobrancaName);

			// Add mensagem
			SOAPElement mensagem = F2bSituacaoCobranca.addChildElement("mensagem");
			mensagem.addAttribute(envelope.createName("data"), sdf.format(new Date()));
			mensagem.addAttribute(envelope.createName("numero"), usr.getUsuarioId().toString());

			// Add cliente
			SOAPElement cliente = F2bSituacaoCobranca.addChildElement("cliente");
			cliente.addAttribute(envelope.createName("conta"), infBoleto.getInfboletoUsr());
			cliente.addAttribute(envelope.createName("senha"), infBoleto.getInfboletoSen());

			// Add cobranca
			SOAPElement cobranca = F2bSituacaoCobranca.addChildElement("cobranca");
			
			//0 = todas; 1=somente registradas;2=somente pagas;3 = somente vencidas
			cobranca.addAttribute(envelope.createName("situacao"), "2");
			
			switch (tipoPesquisa) {
			case 1:
				// ********************** Intervalos de cobranças
				cobranca.addAttribute(envelope.createName("numero"), usr.getPesquisaF2BInicial());
				cobranca.addAttribute(envelope.createName("numero_final"), usr.getPesquisaF2BFinal());
				break;
			case 2:
				// ********************** Intervalo (Data de registro)
				cobranca.addAttribute(envelope.createName("registro"), usr.getPesquisaF2BInicial());
				cobranca.addAttribute(envelope.createName("registro_final"), usr.getPesquisaF2BFinal());
				break;
			case 3:
				// ********************** Intervalo (Data de vencimento)
				cobranca.addAttribute(envelope.createName("vencimento"), usr.getPesquisaF2BInicial());
				cobranca.addAttribute(envelope.createName("vencimento_final"), usr.getPesquisaF2BFinal());
				break;
			case 4:
				// ********************** Intervalo (Data de processamento)
				cobranca.addAttribute(envelope.createName("processamento"), usr.getPesquisaF2BInicial());
				cobranca.addAttribute(envelope.createName("processamento_final"), usr.getPesquisaF2BFinal());
				break;
			case 5:
				// ********************** Intervalo (Data de credito)
				cobranca.addAttribute(envelope.createName("credito"), usr.getPesquisaF2BInicial());
				cobranca.addAttribute(envelope.createName("credito_final"), usr.getPesquisaF2BFinal());
				break;
			case 6:
				// e/ou ---------------
				cobranca.addAttribute(envelope.createName("cod_sacado"), usr.getUsuarioId().toString());
				break;
			}

			ByteArrayOutputStream bReq = new ByteArrayOutputStream();
			bl.append("\nrequest: " + request);
			request.writeTo(bReq);
			bl.append(bReq.toString());

			// Send request
			SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
			SOAPConnection sc = scf.createConnection();
			SOAPMessage response = sc.call(request, endpoint);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			response.writeTo(bos);

			bl.append("\nresponse: " + response);
			bl.append(bos.toString());
			bl.append("********FIM VERIFICANDO BOLETO*********");
			return bos.toString();

		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * @param BoletosGerados b
	 * @return String com o XML de resposta do webservice da F2B
	 * @throws Exception
	 */
	public String segundaVia(BoletosGerados b, InfBoleto infBoleto) throws Exception {
		Usuario usr = b.getUsuario();
		usr.setBoleto(b);
		return segundaVia(usr, infBoleto);

	}
	/**
	 * @param Usuario usr
	 * @return String com o XML de resposta do webservice da F2B
	 * @throws Exception
	 */
	public String segundaVia(Usuario usr, InfBoleto infBoleto) throws Exception {
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
			cliente.addAttribute(envelope.createName("conta"), infBoleto.getInfboletoUsr());
			cliente.addAttribute(envelope.createName("senha"), infBoleto.getInfboletoSen());

			// Add sacado
			SOAPElement sacado = F2bSegundaVia.addChildElement("sacado");
			sacado.addAttribute(envelope.createName("txt_email"), usr.getUsuarioEmail());
			sacado.addAttribute(envelope.createName("num_cpf"), usr.getUsuarioCpf());

			// somente_registradas:1=exibe cobranças registradas;2=exibe cobranças registradas e pagas
			sacado.addAttribute(envelope.createName("somente_registradas"), "1");

			ByteArrayOutputStream bReq = new ByteArrayOutputStream();
			logger.info("\nrequest: " + request);
			request.writeTo(bReq);
			logger.info(bReq.toString());
			
			// Send request
			SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
			SOAPConnection sc = scf.createConnection();
			SOAPMessage response = sc.call(request, endpoint);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			logger.info("\nresponse: " + response);
			// get response
			response.writeTo(bos);
			logger.info(bos.toString());

			return bos.toString();

		} catch (Exception e) {
			throw new Exception();
		}
	}

	/**
	 * @param BoletosGerados b
	 * @param int tipoPesquisa
	 *        <ol>
	 *        <li>e pra cancelar a cobranca</li>
	 *        <li>Registrar pagamento em mao</li>
	 *        <li>Cancela a multa</li>
	 *        <li>Permitir pagamento</li>
	 *        <li>Reenviar e-mail</li>
	 *        </ol>
	 * @return String com o XML de resposta do webservice da F2B
	 * @throws Exception
	 */
	public String alteraCancela(BoletosGerados b, int tipoPesquisa, InfBoleto infBoleto) throws Exception {
		Usuario usr = b.getUsuario();
		usr.setBoleto(b);
		return alteraCancela(usr, tipoPesquisa, infBoleto);
	}
	/**
	 * @param Usuario usr
	 * @param int tipoPesquisa
	 *        <ol>
	 *        <li>e pra cancelar a cobranca</li>
	 *        <li>Registrar pagamento em mao</li>
	 *        <li>Cancela a multa</li>
	 *        <li>Permitir pagamento</li>
	 *        <li>Reenviar e-mail</li>
	 *        </ol>
	 * @return String com o XML de resposta do webservice da F2B
	 * @throws Exception
	 */
	public String alteraCancela(Usuario usr, int tipoPesquisa, InfBoleto infBoleto) throws Exception {
		
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
			((Node) header).detachNode();
			// Add content to body
			SOAPBody body = envelope.getBody();
			Name F2bAcaoCobrancaName = envelope.createName("F2bAcaoCobranca", "m", "http://www.f2b.com.br/soap/wsbillingaction.xsd");
			SOAPBodyElement F2bAcaoCobranca = body.addBodyElement(F2bAcaoCobrancaName);
			// Add mensagem
			SOAPElement mensagem = F2bAcaoCobranca.addChildElement("mensagem");
			mensagem.addAttribute(envelope.createName("data"), sdf.format(new java.util.Date()));
			mensagem.addAttribute(envelope.createName("numero"), usr.getUsuarioId().toString());

			// Add cliente
			SOAPElement cliente = F2bAcaoCobranca.addChildElement("cliente");
			cliente.addAttribute(envelope.createName("conta"), infBoleto.getInfboletoUsr());
			cliente.addAttribute(envelope.createName("senha"), infBoleto.getInfboletoSen());

			// Add cobranca
			SOAPElement cobranca = F2bAcaoCobranca.addChildElement("acao_cobranca");

			// ********************** Numero da cobranca ************************************
			cobranca.addAttribute(envelope.createName("numero"), usr.getBoleto().getBoletosgeradosIdF2B().toString());

			switch (tipoPesquisa) {
			case 1:
				cobranca.addAttribute(envelope.createName("cancelar_cobranca"), "1");
				break;
			case 2:
				// ********************** Acao Registrar Pagamento **************************
				cobranca.addAttribute(envelope.createName("registrar_pagamento"), "1");
				cobranca.addAttribute(envelope.createName("registrar_pagamento_valor"), usr.getBoleto().getBoletosgeradosValorPago());
				cobranca.addAttribute(envelope.createName("dt_registrar_pagamento"), sdf.format(usr.getBoleto().getBoletosgeradosDataPagamento()));
				break;
			case 3:
				cobranca.addAttribute(envelope.createName("cancelar_multa"), "1");
				break;
			case 4:
				// ********************** Acao Permitir Pagamento **************************
				cobranca.addAttribute(envelope.createName("permitir_pagamento"), "1");
				cobranca.addAttribute(envelope.createName("dt_permitir_pagamento"), sdf.format(usr.getBoleto().getBoletosgeradosDataVencimentoProrrogado()));
				break;
			case 5:
				// ********************** Acao Reenviar Email **************************
				cobranca.addAttribute(envelope.createName("reenviar_email"), "1");
				cobranca.addAttribute(envelope.createName("email_tosend"), usr.getUsuarioEmail());
				break;
			}
			
			System.out.println("----------------------------------------------------------");
			System.out.println("Enviando boleto: " + usr.getBoleto().getBoletosgeradosId());
			System.out.println("Usuario:         " + usr.getUsuarioNome());
			
			ByteArrayOutputStream bReq = new ByteArrayOutputStream();
			logger.info("\nrequest: " + request);
			request.writeTo(bReq);
			logger.info(bReq.toString());
			
			// Send request
			SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
			SOAPConnection sc = scf.createConnection();
			SOAPMessage response = sc.call(request, endpoint);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			logger.info("\nresponse: " + response);
			response.writeTo(bos);
			logger.info(bos.toString());
			
			if(bos.toString().indexOf("Erro") > 0){
				System.out.println("Erro:            " + bos.toString());
			}

			System.out.println("----------------------------------------------------------");
			return bos.toString();

		} catch (Exception e) {
			throw new Exception();
		}

	}

}
