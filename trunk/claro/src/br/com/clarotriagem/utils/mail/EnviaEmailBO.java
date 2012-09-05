package br.com.clarotriagem.utils.mail;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;

import br.com.clarotriagem.controller.BaseBean;
import br.com.clarotriagem.entitades.Calendario;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.utils.singleton.mapas.ConfigUtil;

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

	public static void enviaEMailCadastroAutorizado(UsuarioIdentificacao usuario) throws Exception {
		HtmlTableGenerator htg = new HtmlTableGenerator();
		htg.setTituloTabela(BaseBean.getBundeExterno("confirmacao_cadastro") + " "+ ConfigUtil.getInstance().getProperty("empresa","Bat-móvel")
				+".<br/>"+ BaseBean.getBundeExterno("acesso_liberado") +"." + 
				ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") + ".");
		htg.getDados().put(BaseBean.getBundeExterno("endereco_msg"), usuario.getEndereco() + " - " + usuario.getBairro());
		htg.getDados().put(BaseBean.getBundeExterno("telefone_msg"), usuario.getTelefone1());
		htg.getDados().put(BaseBean.getBundeExterno("msg_login"), usuario.getEmail());
		htg.getDados().put(BaseBean.getBundeExterno("nome_msg"), usuario.getNome());
		htg.getDados().put(BaseBean.getBundeExterno("msg_senha"), usuario.getSenha());
		
		htg.setRodapeTabela("<a href=\"" + ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") + "\">"+ 
		BaseBean.getBundeExterno("acessar_o_sistema") +"</a><br>");
		sendMail(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), 
				usuario.getEmail(), BaseBean.getBundeExterno("cadastro_msg") + " - " + ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), htg.montaTabela());
	}

	public static void enviaEMailCadastroUsuario(UsuarioIdentificacao usuario) throws Exception {
		HtmlTableGenerator htg = new HtmlTableGenerator();
		htg.setTituloTabela(BaseBean.getBundeExterno("confirmacao_cadastro") + " "+ ConfigUtil.getInstance().getProperty("empresa","Bat-móvel")
				+".<br/>"+ BaseBean.getBundeExterno("acesso_nao_liberado") +"." + 
				ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") + ".");
		htg.getDados().put(BaseBean.getBundeExterno("endereco_msg"), usuario.getEndereco() + " - " + usuario.getBairro());
		htg.getDados().put(BaseBean.getBundeExterno("telefone_msg"), usuario.getTelefone1());
		htg.getDados().put(BaseBean.getBundeExterno("msg_login"), usuario.getEmail());
		htg.getDados().put(BaseBean.getBundeExterno("nome_msg"), usuario.getNome());
		
		htg.setRodapeTabela("<a href=\"" + ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") + "\">"+ 
				BaseBean.getBundeExterno("acessar_o_sistema") +"</a><br>");
		sendMail(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), 
				usuario.getEmail(), BaseBean.getBundeExterno("cadastro_msg") + " - " + ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), htg.montaTabela());
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

	
	public static void enviaEMailRecuperacaoSenhaUsuario(UsuarioIdentificacao usuario) throws Exception {
		HtmlTableGenerator htg = new HtmlTableGenerator();
		
		String urlAltera = ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") + "alterar_senha.html";
		
		htg.setTituloTabela(BaseBean.getBundeExterno("solicitacao_emitida") + " <a href=\""+ 
				ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") +"\"> "+ 
				ConfigUtil.getInstance().getProperty("empresa","Bat-móvel") +"</a>.");
		htg.getDados().put(BaseBean.getBundeExterno("msg_login"), usuario.getEmail());
		htg.getDados().put(BaseBean.getBundeExterno("nome_msg"), usuario.getNome());
		htg.getDados().put(BaseBean.getBundeExterno("codigo_de_recuperacao"), usuario.getIdConfirmacaoCadastro());
		htg.setRodapeTabela(BaseBean.getBundeExterno("acesse_msg") + " <a href=\"" + urlAltera + "\">"+ urlAltera +"</a>" + " " + BaseBean.getBundeExterno("entre_com_codigo_acima"));

		sendMail(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), 
				usuario.getEmail(), BaseBean.getBundeExterno("recuperar_senha") + " - " + ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), htg.montaTabela());
	}

	public static void enviaEMailUsuarioDesativado(UsuarioIdentificacao usuario) throws Exception{
		HtmlTableGenerator htg = new HtmlTableGenerator();
		htg.setTituloTabela(BaseBean.getBundeExterno("cadastro_desativado"));
		htg.getDados().put(BaseBean.getBundeExterno("msg_login"), usuario.getEmail());
		htg.getDados().put(BaseBean.getBundeExterno("nome_msg"), usuario.getNome());
		
		htg.setRodapeTabela(null);
		sendMail(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), 
				usuario.getEmail(), BaseBean.getBundeExterno("desativacao_msg") + " - " + ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), htg.montaTabela());
	}

	public static void enviaEMailUsuarioAtivado(UsuarioIdentificacao usuario) throws Exception{
		HtmlTableGenerator htg = new HtmlTableGenerator();
		htg.setTituloTabela(BaseBean.getBundeExterno("cadastro_ativado"));
		htg.getDados().put(BaseBean.getBundeExterno("msg_login"), usuario.getEmail());
		htg.getDados().put(BaseBean.getBundeExterno("nome_msg"), usuario.getNome());
		
		htg.setRodapeTabela("<a href=\"" + ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") + "\">"+ 
				BaseBean.getBundeExterno("acessar_o_sistema") +"</a><br>");
		sendMail(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), 
				usuario.getEmail(), BaseBean.getBundeExterno("ativacao_msg") + " - " + ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), htg.montaTabela());
	}

	public static void enviaEmailNovoAgendamento(Calendario cal) throws Exception{
		sendMail(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), 
				cal.getUsuarioResponsavel().getEmail(), 
				BaseBean.getBundeExterno("agendamento_msg") + " - " + ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), 
				msgEnviaEmailNovoAgendamento(cal));
	}
	public static String msgEnviaEmailNovoAgendamento(Calendario cal){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		HtmlTableGenerator htg = new HtmlTableGenerator();
		htg.setTituloTabela(BaseBean.getBundeExterno("email_novo_agendamento"));
		htg.getDados().put(BaseBean.getBundeExterno("responsavel_msg"), cal.getUsuarioResponsavel().getNome());
		htg.getDados().put(BaseBean.getBundeExterno("data_msg"), sdf.format(cal.getDataInicial()));
		htg.getDados().put(BaseBean.getBundeExterno("contato_msg"), cal.getContato());
		htg.getDados().put(BaseBean.getBundeExterno("warehouse_msg"), cal.getWarehouse().getNomeFantasia());
		
		htg.setRodapeTabela("<a href=\"" + ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") + "\">"+ 
				BaseBean.getBundeExterno("acessar_o_sistema") +"</a><br>");
		return htg.montaTabela();
	}
	
	
	public static void enviaEmailAlteracaoAgendamento(Calendario cal) throws Exception{
		sendMail(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), 
				cal.getUsuarioResponsavel().getEmail(), 
				BaseBean.getBundeExterno("agendamento_msg") + " - " + ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), 
				msgEnviaEmailAlteracaoAgendamento(cal));
	}
	
	public static String msgEnviaEmailAlteracaoAgendamento(Calendario cal){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		HtmlTableGenerator htg = new HtmlTableGenerator();
		htg.setTituloTabela(BaseBean.getBundeExterno("email_alteracao_agendamento"));
		htg.getDados().put(BaseBean.getBundeExterno("responsavel_msg"), cal.getUsuarioResponsavel().getNome());
		htg.getDados().put(BaseBean.getBundeExterno("data_msg"), sdf.format(cal.getDataInicial()));
		htg.getDados().put(BaseBean.getBundeExterno("contato_msg"), cal.getContato());
		htg.getDados().put(BaseBean.getBundeExterno("warehouse_msg"), cal.getWarehouse().getNomeFantasia());
		
		htg.setRodapeTabela("<a href=\"" + ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") + "\">"+ 
				BaseBean.getBundeExterno("acessar_o_sistema") +"</a><br>");
		return htg.montaTabela();
	}

	public static void enviaEmaiAvisoAgendamentosAVencer(List<Calendario> cals) throws Exception{
		UsuarioIdentificacao usr = cals.get(0).getUsuarioResponsavel();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		String html = "";
		for(Calendario c : cals){
			HtmlTableGenerator htg = new HtmlTableGenerator();
			htg.setTituloTabelaSemNomeEmpresa(c.getWarehouse().getNomeFantasia().toUpperCase() + " - " + c.getWarehouse().getEstado());
			htg.getDados().put("Responsável", c.getUsuarioResponsavel().getNome().toUpperCase());
			htg.getDados().put("Contato na warehouse", c.getWarehouse().getContato() + " - " + c.getWarehouse().getTelefone1());
			htg.getDados().put("Qtd. aparelhos previsto", c.getQuantidade());
			htg.getDados().put("Qtd. de lotes", c.getQtdLotes());
			htg.getDados().put("Data/hora", sdf.format(c.getDataInicial()));
			htg.setRodapeTabelaSemAviso(" - ");
			html += htg.montaTabela() + "<br/>";
		}

		sendMail(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), usr.getEmail(), "Resumo de agendamentos a vencer", html);
	}
	
	public static void enviaEmaiAvisoAgendamentosAtrasados(List<Calendario> cals) throws Exception{
		UsuarioIdentificacao usr = cals.get(0).getUsuarioResponsavel();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		String html = "";
		for(Calendario c : cals){
			HtmlTableGenerator htg = new HtmlTableGenerator();
			htg.setTituloTabelaSemNomeEmpresa(c.getWarehouse().getNomeFantasia().toUpperCase() + " - " + c.getWarehouse().getEstado());
			htg.getDados().put("Responsável", c.getUsuarioResponsavel().getNome().toUpperCase());
			htg.getDados().put("Contato na warehouse", c.getWarehouse().getContato() + " - " + c.getWarehouse().getTelefone1());
			htg.getDados().put("Qtd. aparelhos previsto", c.getQuantidade());
			htg.getDados().put("Qtd. de lotes", c.getQtdLotes());
			htg.getDados().put("Data/hora", sdf.format(c.getDataInicial()));
			htg.setRodapeTabelaSemAviso(" - ");
			html += htg.montaTabela() + "<br/>";
		}
		
		sendMail(ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), usr.getEmail(), "Resumo de agendamentos atrasados", html);
	}
	
}
