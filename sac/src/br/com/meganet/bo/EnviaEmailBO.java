package br.com.meganet.bo;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;

import br.com.meganet.hbm.vo.Avisos;
import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.hbm.vo.Contato;
import br.com.meganet.hbm.vo.Demandas;
import br.com.meganet.hbm.vo.InfBoleto;
import br.com.meganet.hbm.vo.Servidor;
import br.com.meganet.hbm.vo.StatusCliente;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.DateUtils;
import br.com.meganet.util.EmailDelivery;
import br.com.meganet.util.HtmlTableGenerator;
import br.com.meganet.util.SendMailGMAIL;
import br.com.meganet.util.Util;
import br.com.meganet.util.UtilNumero;

public class EnviaEmailBO {
	private static void sendMail(String from, String to, String subject, String message) throws UnsupportedEncodingException, MessagingException {
		try{
			if(ConfigUtil.getInstance().getBooleanProperty("envia_email",false)){
				System.out.println("enviando email para: " + to);
				System.out.println("Assunto: " + subject);
				if(ConfigUtil.getInstance().getBooleanProperty("utiliza_gmail", true)){
					SendMailGMAIL smg = new SendMailGMAIL();
					smg.sendMail(from, to, subject, message);
				}else{
					EmailDelivery ed = new EmailDelivery();
					ed.setTo(to + ", " + to);
					ed.setFrom(ConfigUtil.getInstance().getProperty("email_administrativo_usr","teste@teste.com.br"),
							ConfigUtil.getInstance().getProperty("empresa2", "Meganet"));
					ed.setReplyTo(ConfigUtil.getInstance().getProperty("email_administrativo_usr","teste@teste.com.br"), 
							ConfigUtil.getInstance().getProperty("empresa2", "Meganet"));
					ed.setSubject(subject);
					ed.setBody(message);
					ed.sendMsg();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void enviaEMailCadastroUsuario(Usuario usuario, String senha) throws Exception {
		HtmlTableGenerator htg = new HtmlTableGenerator();
		htg.setTituloTabela("Cadastro do provedor de internet.<br>Confira seus dados e atualize-os, se necessário, no endereço " + 
				ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") + ".");
		htg.getDados().put("Endereço", usuario.getUsuarioEndereco() + " - " + usuario.getUsuarioComplementoEndereco());
		htg.getDados().put("Telefone", usuario.getTelefoneAtivo());
		htg.getDados().put("E-mail", usuario.getUsuarioEmail());
		htg.getDados().put("Nome", usuario.getUsuarioNome());
		htg.getDados().put("Senha", senha);
		
		String html = "<a href=\"" + ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") + "trocarSenha.do?id=" + usuario.getUsuarioIdTrocaSenha() + "\">Mudar a senha</a><br>";
		if(!usuario.getUsuarioAdministrativo()){
			html += "Para acessar a página e gerar boletos, conferir pagamentos e atualizar dados de cadastro<br>acesse: <a href=\""+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"\"> "+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +".</a><br/>Desconsidere qualquer outro e-mail recebido.<br/>Lembre-se que senha valida letras maiúsculas e minúsculas.";
		}
		
		htg.setRodapeTabela(html);
		System.out.println("trocarSenha.do?id=" + usuario.getUsuarioIdTrocaSenha());
		
		sendMail(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), 
				usuario.getUsuarioEmail(), "Senha " + ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), htg.montaTabela());
	}

	public static void enviaEMailRecuperacaoSenhaUsuario(Usuario usuario, String senha) throws Exception {
		HtmlTableGenerator htg = new HtmlTableGenerator();
		htg.setTituloTabela("Nova senha.<br>Solicitação emitida em <a href=\""+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"\"> "+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"</a>.<br>Senha gerada aleatoriamente.");
		htg.getDados().put("Endereço", usuario.getUsuarioEndereco() + " - " + usuario.getUsuarioComplementoEndereco());
		htg.getDados().put("Telefone", usuario.getTelefoneAtivo());
		htg.getDados().put("E-mail", usuario.getUsuarioEmail());
		htg.getDados().put("Nome", usuario.getUsuarioNome());
		htg.getDados().put("Senha temporária", senha);
		htg.setRodapeTabela("<a href=\"" + ConfigUtil.getInstance().getProperty("url_site","www.google.com.br")
				+ "trocarSenha.do?id=" + usuario.getUsuarioIdTrocaSenha() + "\"><h4>Mudar a senha</h4></a>" + "<br>Não é possível recuperar a senha antiga, pois os dados são criptografados e impossíveis de serem lidos.<br/>Lembre-se que senha valida letras maiúsculas e minúsculas.");
		System.out.println("trocarSenha.do?id=" + usuario.getUsuarioIdTrocaSenha());
		sendMail(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), 
				usuario.getUsuarioEmail(), "Senha " + ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), htg.montaTabela());
	}

	public static void enviaEMailNovaDemanda(Demandas d) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		
		HtmlTableGenerator htg = new HtmlTableGenerator();
		htg.setTituloTabela("Demanda " + d.getDemandasId());
		htg.getDados().put("Número da demanda", d.getDemandasId());
		htg.getDados().put("Cliente", d.getUsuarioSolicitante().getUsuarioNome());
		htg.getDados().put("Telefone contato", d.getUsuarioSolicitante().getTelefoneAtivo() != null ? d.getUsuarioSolicitante().getUsuarioTelefone1() : "" + " / " + d.getUsuarioSolicitante().getUsuarioTelefone2() != null ? d.getUsuarioSolicitante().getUsuarioTelefone2() : "");
		htg.getDados().put("Endereço", d.getUsuarioSolicitante().getUsuarioEndereco() + " - " + d.getUsuarioSolicitante().getUsuarioComplementoEndereco() + ", " + d.getUsuarioSolicitante().getUsuarioBairro() + ", " + d.getUsuarioSolicitante().getUsuarioCidade() + ", " + d.getUsuarioSolicitante().getUsuarioEstado());
		htg.getDados().put("Data e hora prevista", sdf.format(d.getDemandasDataPrevistaAtendimento()) + " às " + sdf2.format(d.getDemandasDataPrevistaAtendimento()));
		String linkDemanda = ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") + "imprimeDemanda.do?id=" + d.getDemandasId();
		htg.getDados().put("Para imprimir", "<a href=\""+ linkDemanda +"\"> " + linkDemanda + "</a>");
		htg.getDados().put("Descrição", d.getDemandasDescricao());
		sendMail(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), 
				d.getUsuarioResponsavel().getUsuarioEmail(), "Demanda " + d.getDemandasId() + " criada.", htg.montaTabela());
	}
	
	public static void enviaEmailAviso(Avisos aviso, Usuario usr) throws Exception {
		HtmlTableGenerator htg = new HtmlTableGenerator();
		htg.setTituloTabela(aviso.getAvisosTitulo());
		htg.getDados().put("Sr(a).", usr.getUsuarioNome());
		htg.getDados().put("Comunicado", aviso.getAvisosAviso());
		htg.setRodapeTabela("Qualquer dúvida entre em contato acessando o endereço <a href=\""+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"\"> "+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"</a>.");
		sendMail(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), usr.getUsuarioEmail(), aviso.getAvisosTitulo(), htg.montaTabela());
	}
	
	public static void enviaEMailAlteracaoSenhaUsuario(Usuario usuario, String senha) throws Exception {
		HtmlTableGenerator htg = new HtmlTableGenerator();
		htg.setTituloTabela("Nova senha.<br>Solicitação emitida em <a href=\""+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"\"> "+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"</a>.<br>Senha gerada pelo cliente.");
		htg.getDados().put("Endereço", usuario.getUsuarioEndereco() + " - " + usuario.getUsuarioComplementoEndereco());
		htg.getDados().put("Telefone", usuario.getTelefoneAtivo());
		htg.getDados().put("E-mail", usuario.getUsuarioEmail());
		htg.getDados().put("Nome", usuario.getUsuarioNome());
		htg.getDados().put("Senha", senha);
		htg.setRodapeTabela("Para mudar a senha acesse o site <a href=\""+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"\"> "+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"</a><br>Guarde sua senha, não é possível recuperá-la, pois os dados são criptografados e impossíveis de serem lidos.<br/>Lembre-se que senha valida letras maiúsculas e minúsculas.");

		sendMail(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), 
				usuario.getUsuarioEmail(), "Senha " + ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), htg.montaTabela());
	}

	public static void enviaEMailInadimplenciaUsuario(Usuario usuario, BoletosGerados boletosGerados, int qtdDias, int tempoBloqueio) throws Exception {
		HtmlTableGenerator htg = new HtmlTableGenerator();
		htg.setTituloTabela("AVISO.<br>Até o momento não registramos o pagamento de sua mensalidade.<br><b>Seu ponto poderá ser bloqueado em até " + (tempoBloqueio - qtdDias) + " dias.</b>");
		htg.getDados().put("Valor da mensalidade", "R$ " + UtilNumero.formataNumeroCasaDecimal(usuario.getPlanosPacotes().getPlanospacotesValor()));
		htg.getDados().put("Telefone", usuario.getTelefoneAtivo());
		htg.getDados().put("Data vencimento", DateUtils.formatar(boletosGerados.getBoletosgeradosDataVencimento(), "dd/MM/yyyy"));
		htg.getDados().put("E-mail", usuario.getUsuarioEmail());
		htg.getDados().put("Nome", usuario.getUsuarioNome());
		htg.setRodapeTabela("acesse: <a href=\""+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"\"> "+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"</a> e gere outro boleto.<br>Não deixe seu pagamento atrasar, evite o corte de seu serviço.<br><h3>Obs.: Caso este e-mail seja um engano ou você já tenha efetuado o pagamento, favor desconsiderar.</h3>");
		
		sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), 
				usuario.getUsuarioEmail(), "Mensalidade: " + qtdDias + " em atraso.", htg.montaTabela());
	}

	public static void enviaEMailVaiDesativarUsuario(String tipoBloq,Usuario usuario, BoletosGerados boletosGerados, List<Usuario> usrAdm) throws Exception {
		HtmlTableGenerator htg = new HtmlTableGenerator();
		htg.setTituloTabela("Usuario " + tipoBloq.toUpperCase() + ".");
		htg.getDados().put("Telefone", usuario.getTelefoneAtivo());
		htg.getDados().put("Data vencimento", DateUtils.formatar(boletosGerados.getBoletosgeradosDataVencimento(), "dd/MM/yyyy"));
		htg.getDados().put("E-mail", usuario.getUsuarioEmail());
		htg.getDados().put("Nome", usuario.getUsuarioNome());
		htg.setRodapeTabela("Em 2 dias o usuário acima será " + tipoBloq);
		
		for (Iterator<Usuario> iterator = usrAdm.iterator(); iterator.hasNext();) {
			Usuario srAdm = (Usuario) iterator.next();
			sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), 
					srAdm.getUsuarioEmail(), usuario.getUsuarioNome() + " - " + tipoBloq.toUpperCase(), htg.montaTabela());
		}
	}
	
	public static void enviaEMailClienteProblema(StatusCliente st, List<Usuario> usrAdm) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			SimpleDateFormat sdfH = new SimpleDateFormat("HH:MM:ss", new Locale("pt","br"));

			HtmlTableGenerator htg = new HtmlTableGenerator();
			
			htg.setTituloTabela("Aviso de sinal baixo");
			htg.getDados().put("Data", sdf.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Hora", sdfH.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Usuario", st.getUsuario().getUsuarioNome());
			htg.getDados().put("Id", st.getUsuario().getUsuarioId().toString());
			htg.getDados().put("Servidor", st.getUsuario().getServidor().getServidorNome());
			htg.getDados().put("Throughput", st.getStatusclienteThroughput());
			htg.getDados().put("Tx/Rx", st.getStatusclienteRxrate() + "/" + st.getStatusclienteTxrate());
			htg.getDados().put("Força de sinal", st.getStatusclienteSignalstrength());
			htg.setRodapeTabela("&nbsp;");

			for (Iterator<Usuario> iterator = usrAdm.iterator(); iterator.hasNext();) {
				Usuario usuario = (Usuario) iterator.next();
				sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), 
						usuario.getUsuarioEmail(), st.getUsuario().getUsuarioNome() + " - Nivel de sinal", htg.montaTabela());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void enviaEMailQuedaBruscaDesinal(StatusCliente statusAtual, StatusCliente statusAntigo, List<Usuario> usrAdm) {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			SimpleDateFormat sdfH = new SimpleDateFormat("HH:MM:ss", new Locale("pt","br"));
			
			HtmlTableGenerator htg = new HtmlTableGenerator();
			
			htg.setTituloTabela("Aviso de queda brusca de sinal");
			htg.getDados().put("Usuario", statusAtual.getUsuario().getUsuarioNome());
			htg.getDados().put("Id", statusAtual.getUsuario().getUsuarioId().toString());
			htg.getDados().put("Servidor", statusAtual.getUsuario().getServidor().getServidorNome());
			htg.getDados().put(" - ", "<strong>Dados verificação atual</strong>");
			htg.getDados().put("Data", sdf.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Hora", sdfH.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Throughput", statusAtual.getStatusclienteThroughput());
			htg.getDados().put("Tx/Rx", statusAtual.getStatusclienteRxrate() + "/" + statusAtual.getStatusclienteTxrate());
			htg.getDados().put("Força de sinal", statusAtual.getStatusclienteSignalstrength());
			
			htg.getDados().put(" -", "<strong>Dados da última verificação</strong>");
			htg.getDados().put("Data anterior", sdf.format(statusAntigo.getStatusclienteDatamedicao()));
			htg.getDados().put("Hora anterior", sdfH.format(statusAntigo.getStatusclienteDatamedicao()));
			htg.getDados().put("Throughput anterior", statusAntigo.getStatusclienteThroughput());
			htg.getDados().put("Tx/Rx anterior", statusAntigo.getStatusclienteRxrate() + "/" + statusAtual.getStatusclienteTxrate());
			htg.getDados().put("Força de sinal anterior", statusAntigo.getStatusclienteSignalstrength());
			htg.setRodapeTabela("&nbsp;");
			
			for (Iterator<Usuario> iterator = usrAdm.iterator(); iterator.hasNext();) {
				Usuario usuario = (Usuario) iterator.next();
				sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), 
						usuario.getUsuarioEmail(), statusAtual.getUsuario().getUsuarioNome() + " - Queda brusca", htg.montaTabela());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void enviaInterfaceDisconectada(String estado, String nomeInterface, Servidor serv, List<Usuario> usrAdm) {
		try {
			HtmlTableGenerator htg = new HtmlTableGenerator();
			htg.setTituloTabela("Interface disconectada.");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			SimpleDateFormat sdfH = new SimpleDateFormat("HH:MM:ss", new Locale("pt","br"));

			htg.getDados().put("Data", sdf.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Hora", sdfH.format(new Date(System.currentTimeMillis())));

			htg.getDados().put("Nome da interface", nomeInterface);
			htg.getDados().put("Estado", estado.trim().equalsIgnoreCase("") ? "Disconectado" : "Indefinido");
			htg.getDados().put("Servidor", serv.getServidorNome());
			htg.setRodapeTabela("&nbsp;");

			for (Iterator<Usuario> iterator = usrAdm.iterator(); iterator.hasNext();) {
				Usuario usuario = (Usuario) iterator.next();
				sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), 
						usuario.getUsuarioEmail(), "Interface " + nomeInterface + " desconectada.", htg.montaTabela());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void enviaEmailManipulacaoBoletosPelaF2B(List<Usuario> usrAdm, Map<String, String> boletos, boolean ehEnvio) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			SimpleDateFormat sdfH = new SimpleDateFormat("HH:MM:ss", new Locale("pt","br"));

			HtmlTableGenerator htg = new HtmlTableGenerator();
			if(ehEnvio){
				htg.setTituloTabela("Boletos enviados.");
			}else{
				htg.setTituloTabela("Boletos pagos.");
			}
			htg.getDados().put("Data", sdf.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Hora", sdfH.format(new Date(System.currentTimeMillis())));
			
			Set<String> chaves = boletos.keySet();
			for (String chave : chaves) {
				if (chave != null){
					System.out.println(chave + boletos.get(chave));
					htg.getDados().put(boletos.get(chave) + " boletos", "R$ " + chave);
				}
			}
			htg.setRodapeTabela("&nbsp;");
			
			for (Iterator<Usuario> iterator = usrAdm.iterator(); iterator.hasNext();) {
				Usuario usuario = (Usuario) iterator.next();
				sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), 
						usuario.getUsuarioEmail(), ehEnvio ? "Boletos enviados." : "Boletos pagos.", htg.montaTabela());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void enviaMensagemErro(String mensagem, StackTraceElement[] stack, List<String> emails) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			SimpleDateFormat sdfH = new SimpleDateFormat("HH:MM:ss", new Locale("pt","br"));

			HtmlTableGenerator htg = new HtmlTableGenerator();
			htg.setTituloTabela("Erro de sistema.");
			htg.getDados().put("Data", sdf.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Hora", sdfH.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Mensagem", mensagem);
			if (stack != null) {
				htg.getDados().put("Stack", "&nbsp;");
				String envio = "";
				for (int i = 0; i < stack.length; i++) {
					StackTraceElement stackTraceElement = stack[i];
					envio += "Classe: " + stackTraceElement.getClassName() + " &nbsp;&nbsp;-&nbsp;&nbsp;";
					envio += "Método: " + stackTraceElement.getMethodName() + " &nbsp;&nbsp;-&nbsp;&nbsp;";
					envio += "Linha: " +stackTraceElement.getLineNumber() + "<br>";
				}
				htg.getDados().put("&nbsp;", envio);
			}
			htg.setRodapeTabela("&nbsp;");

			for (Iterator<String> iterator = emails.iterator(); iterator.hasNext();) {
				String email = (String) iterator.next();

				sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), 
						email, " Erro - " + sdf.format(new Date(System.currentTimeMillis())), htg.montaTabela());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void enviaEMailComprovanteUsuario(Usuario usuario, BoletosGerados boletosGerados, String valor) {
		try {
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			String idComp = Util.geraComprovateBoleto(boletosGerados);

			HtmlTableGenerator htg = new HtmlTableGenerator();
			htg.setTituloTabela("Comprovante pagamento mensalidade.");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			SimpleDateFormat sdfH = new SimpleDateFormat("HH:MM:ss", new Locale("pt","br"));

			htg.getDados().put("Data de envio", sdf.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Hora de envio", sdfH.format(new Date(System.currentTimeMillis())));

			htg.getDados().put("Data vencimento", sdf2.format(new Date(boletosGerados.getBoletosgeradosDataVencimento().getTime())));
			htg.getDados().put("Data pagamento", sdf2.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Número boleto", boletosGerados.getBoletosgeradosId() + "");
			htg.getDados().put("Código", idComp);

			htg.setRodapeTabela("Com o código acima, você entra em <a href=\""+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"\"> "+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"</a> para emitir o comprovante.");
			sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), 
					usuario.getUsuarioEmail(), "Comprovante de pagamento de boleto.", htg.montaTabela());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void enviaEMailCancelamentoBoletoUsuario(Usuario usuario, BoletosGerados boletosGerados) {
		try {
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			String idComp = Util.geraComprovateBoleto(boletosGerados);
			
			HtmlTableGenerator htg = new HtmlTableGenerator();
			htg.setTituloTabela("Cancelamento de boleto.");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			SimpleDateFormat sdfH = new SimpleDateFormat("HH:MM:ss", new Locale("pt","br"));
			
			htg.getDados().put("Data de envio", sdf.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Hora de envio", sdfH.format(new Date(System.currentTimeMillis())));
			
			htg.getDados().put("Data vencimento", sdf2.format(new Date(boletosGerados.getBoletosgeradosDataVencimento().getTime())));
			htg.getDados().put("Número boleto", boletosGerados.getBoletosgeradosId() + "");
			htg.getDados().put("Código", idComp);
			
			htg.setRodapeTabela("Com o código acima, você entra em <a href=\""+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"\"> "+ ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"</a> para emitir o comprovante.");
			sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), 
					usuario.getUsuarioEmail(), "Comprovante de cancelamento de boleto.", htg.montaTabela());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void enviaEMailUsrAdmPagamentoBoletoEmMao(List<Usuario> usrAdm, BoletosGerados boletosGerados, String valor, Usuario usrQueRec) {
		try {
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			String idComp = Util.geraComprovateBoleto(boletosGerados);
			
			HtmlTableGenerator htg = new HtmlTableGenerator();
			htg.setTituloTabela("Boleto " + boletosGerados.getBoletosgeradosId() + " pago em mão.");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			SimpleDateFormat sdfH = new SimpleDateFormat("HH:MM:ss", new Locale("pt","br"));
			
			htg.getDados().put("Data de envio", sdf.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Hora de envio", sdfH.format(new Date(System.currentTimeMillis())));
			
			htg.getDados().put("Data vencimento", sdf2.format(new Date(boletosGerados.getBoletosgeradosDataVencimento().getTime())));
			htg.getDados().put("Data pagamento", sdf2.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Atendente", usrQueRec.getUsuarioNome());
			htg.getDados().put("Cliente", boletosGerados.getUsuario().getUsuarioNome());
			htg.getDados().put("Número boleto", boletosGerados.getBoletosgeradosId() + "");
			htg.getDados().put("Valor", valor);
			htg.getDados().put("Código", idComp);
			
			htg.setRodapeTabela("");
			
			for (Iterator<Usuario> iterator = usrAdm.iterator(); iterator.hasNext();) {
				Usuario usuario = (Usuario) iterator.next();
				sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), 
						usuario.getUsuarioEmail(), "Boleto pago.", htg.montaTabela());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void enviaEMailUsrAdmCancelamentoBoleto(List<Usuario> usrAdm, BoletosGerados boletosGerados, Usuario usrQueRec) {
		try {
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			String idComp = Util.geraComprovateBoleto(boletosGerados);
			
			HtmlTableGenerator htg = new HtmlTableGenerator();
			htg.setTituloTabela("Boleto " + boletosGerados.getBoletosgeradosId() + " cancelado.");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			SimpleDateFormat sdfH = new SimpleDateFormat("HH:MM:ss", new Locale("pt","br"));
			
			htg.getDados().put("Data de envio", sdf.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Hora de envio", sdfH.format(new Date(System.currentTimeMillis())));
			
			htg.getDados().put("Vencimento", sdf2.format(new Date(boletosGerados.getBoletosgeradosDataVencimento().getTime())));
			htg.getDados().put("Pagamento", sdf2.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Atendente", usrQueRec.getUsuarioNome());
			htg.getDados().put("Cliente", boletosGerados.getUsuario().getUsuarioNome());
			htg.getDados().put("Número boleto", boletosGerados.getBoletosgeradosId() + "");
			htg.getDados().put("Código", idComp);
			
			htg.setRodapeTabela("");
			
			for (Iterator<Usuario> iterator = usrAdm.iterator(); iterator.hasNext();) {
				Usuario usuario = (Usuario) iterator.next();
				sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), 
						usuario.getUsuarioEmail(), "Boleto cancelado.", htg.montaTabela());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void enviaContato(Contato contato, List<Usuario> usrAdm) {
		try {
			HtmlTableGenerator htg = new HtmlTableGenerator();
			htg.setTituloTabela("Contato do site.");
			htg.getDados().put("Nome", contato.getContatoNome());
			htg.getDados().put("Telefone", contato.getContatoTelefone());
			htg.getDados().put("Endereço", contato.getContatoEndCr());
			htg.getDados().put("E-mail", contato.getContatoEmail());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			SimpleDateFormat sdfH = new SimpleDateFormat("HH:MM:ss", new Locale("pt","br"));

			htg.getDados().put("Data de envio", sdf.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Hora de envio", sdfH.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Mensagem", contato.getContatoMensagem());

			htg.setRodapeTabela("&nbsp;");
			
			for (Iterator<Usuario> iterator = usrAdm.iterator(); iterator.hasNext();) {
				Usuario usuario = (Usuario) iterator.next();
				sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), 
						usuario.getUsuarioEmail(), "Contato no site.", htg.montaTabela());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void enviaRespostaContato(Contato contato) {
		try {
			HtmlTableGenerator htg = new HtmlTableGenerator();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			SimpleDateFormat sdfH = new SimpleDateFormat("HH:MM:ss", new Locale("pt","br"));

			htg.setTituloTabela("Resposta ao contato com a " + ConfigUtil.getInstance().getProperty("empresa", "nuftgogolandia") + ".");
			htg.getDados().put("Data de envio", sdf.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Hora de envio", sdfH.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Sua mensagem", contato.getContatoMensagem());
			htg.getDados().put("Nossa resposta", contato.getContatoResposta());

			htg.setRodapeTabela("Obrigado por entrar em contato.");
			
			sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), 
					contato.getContatoEmail(), "Resposta da " + ConfigUtil.getInstance().getProperty("empresa", "nuftgogolandia"), htg.montaTabela());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void enviaEmailErroCadastroBoleto(String ret) {
		try {
			String[] arr = ConfigUtil.getInstance().getProperty("email_administrativo_erro", "teste@gmail.com").split(",");

			HtmlTableGenerator htg = new HtmlTableGenerator();
			htg.setTituloTabela("Erro ao enviar boletos para a F2B.");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			SimpleDateFormat sdfH = new SimpleDateFormat("HH:MM:ss", new Locale("pt","br"));

			htg.getDados().put("Data de envio", sdf.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Hora de envio", sdfH.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Erro", ret);

			htg.setRodapeTabela("&nbsp;");
			
			for (int i = 0; i < arr.length; i++) {
				sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), arr[i], "Erro envio boleto F2B.", htg.montaTabela());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void enviaEmailBoletoEnviadoAtrasado(Usuario usr) {
		try {
			String[] arr = ConfigUtil.getInstance().getProperty("email_administrativo_erro", "teste@gmail.com").split(",");
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));

			HtmlTableGenerator htg = new HtmlTableGenerator();
			htg.setTituloTabela("Boleto enviado apenas para o e-mail.");
			htg.getDados().put("Data", sdf2.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Usuário", usr.getUsuarioNome());
			htg.getDados().put("Id Usuário", usr.getUsuarioId());
			htg.getDados().put("Data vencimento", sdf2.format(usr.getBoleto().getBoletosgeradosDataVencimento()));
			htg.getDados().put("Numero boleto", usr.getBoleto().getBoletosgeradosId());

			htg.setRodapeTabela("&nbsp;");
			
			for (int i = 0; i < arr.length; i++) {
				sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), arr[i], "Envio boleto F2B apenas por e-mail.", htg.montaTabela());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void enviaEmailAlteracaoUsuario(Usuario usr, Usuario usrBD) {
		try {
			String[] arr = ConfigUtil.getInstance().getProperty("email_administrativo_erro", "teste@gmail.com").split(",");
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));

			HtmlTableGenerator htg = new HtmlTableGenerator();
			htg.setTituloTabela("Alteração de cadastro de usuário.");
			htg.getDados().put("Data", sdf2.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Usuário ALTERADO", "");
			htg.getDados().put("Nome", usr.getUsuarioNome());
			htg.getDados().put("Id", usr.getUsuarioId());
			htg.getDados().put("Usuário que ALTEROU", "");
			htg.getDados().put("Nome", usr.getUsuarioRealizouAlteracao().getUsuarioNome());
			htg.getDados().put("Id", usr.getUsuarioRealizouAlteracao().getUsuarioId());

			htg.setRodapeTabela("Caso essa alteração não seja de seu conhecimento, verificar as configurações do usuario em questão.");
			
			for (int i = 0; i < arr.length; i++) {
				sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), arr[i], "Alteração de usuário.", htg.montaTabela());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void enviaEmailBoletoCriadoCliente(BoletosGerados bg, InfBoleto infBoleto) {
		try {
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			
			HtmlTableGenerator htg = new HtmlTableGenerator();
			htg.setTituloTabela("Boleto gerado");
			htg.getDados().put("Data processamento", sdf2.format(new Date(System.currentTimeMillis())));
			htg.getDados().put("Data vencimento", sdf2.format(bg.getBoletosgeradosDataVencimento()));
			htg.getDados().put("Valor", bg.getBoletosgeradosValor());
			htg.getDados().put("Nome", bg.getUsuario().getUsuarioNome());
			htg.getDados().put("URL do boleto", "<a href=\""+ bg.getBoletosgeradosUrlBoletoF2b()+ "\">" + bg.getBoletosgeradosUrlBoletoF2b() + "</a>");
			
			if(infBoleto.getInfboletoInstrucao1() != null && !"".equalsIgnoreCase(infBoleto.getInfboletoInstrucao1())){
				htg.getDados().put("Obs. 1", infBoleto.getInfboletoInstrucao1());
			}
			if(infBoleto.getInfboletoInstrucao2() != null && !"".equalsIgnoreCase(infBoleto.getInfboletoInstrucao2())){
				htg.getDados().put("Obs. 2", infBoleto.getInfboletoInstrucao2());
			}
			if(infBoleto.getInfboletoInstrucao3() != null && !"".equalsIgnoreCase(infBoleto.getInfboletoInstrucao3())){
				htg.getDados().put("Obs. 3", infBoleto.getInfboletoInstrucao3());
			}
			if(bg.getBoletosgeradosMotivoCreditoDebito() != null && !"".equalsIgnoreCase(bg.getBoletosgeradosMotivoCreditoDebito())){
				htg.getDados().put("Desc. acréscimo", bg.getUsuario().getUsuarioNome());
			}
			htg.setRodapeTabela("Não deixe de pagar seu boleto. A suspensão do serviço é automática e pode ser realizado com até " + ConfigUtil.getInstance().getIntProperty("tempo_2", "20") + " dias após o vencimento do boleto.");
			sendMail(ConfigUtil.getInstance().getProperty("empresa2","Meganet"), bg.getUsuario().getUsuarioEmail(), "Boleto " + ConfigUtil.getInstance().getProperty("empresa2","Meganet"), htg.montaTabela());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
