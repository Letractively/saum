package br.com.meganet.util;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.hbm.vo.Contrato;
import br.com.meganet.hbm.vo.Dominios;
import br.com.meganet.hbm.vo.EnderecoIp;
import br.com.meganet.hbm.vo.InfBoleto;
import br.com.meganet.hbm.vo.PlanosPacotes;
import br.com.meganet.hbm.vo.Servidor;
import br.com.meganet.hbm.vo.Torre;
import br.com.meganet.hbm.vo.Usuario;
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

public class Util {

	public static Contrato copiaPropriedades(Contrato u) {
		Contrato c = new Contrato();
		if (u != null) {
			c.setContratoContrato(u.getContratoContrato());
			c.setContratoId(u.getContratoId());
			c.setContratoNome(u.getContratoNome());
			return c;
		} else {
			return null;
		}
	}
	
	public static Dominios copiaPropriedades(Dominios u) {
		Dominios c = new Dominios();
		if (u != null) {
			c.setDominiosChave(u.getDominiosChave());
			c.setDominiosDescricao(u.getDominiosDescricao());
			c.setDominiosId(u.getDominiosId());
			c.setDominiosTipo(u.getDominiosTipo());
			c.setDominiosTratamentoEspecial(u.getDominiosTratamentoEspecial());
			c.setDominiosValor(u.getDominiosValor());
			return c;
		} else {
			return null;
		}
	}
	
	public static InfBoleto copiaPropriedades(InfBoleto u) {
		InfBoleto e = new InfBoleto();
		if(u != null){
			e.setInfboletoAgencia(u.getInfboletoAgencia());
			e.setInfboletoNumeroConvenio(u.getInfboletoNumeroConvenio());
			e.setInfboletoSen(u.getInfboletoSen());
			e.setInfboletoUsr(u.getInfboletoUsr());
			e.setInfboletoCarteira(u.getInfboletoCarteira());
			e.setInfboletoCnpj(u.getInfboletoCnpj());
			e.setInfboletoConta(u.getInfboletoConta());
			e.setInfboletoId(u.getInfboletoId());
			e.setInfboletoCodigoCliente(u.getInfboletoCodigoCliente());
			e.setInfboletoInstrucao1(u.getInfboletoInstrucao1());
			e.setInfboletoCodigoFornecidoAgencia(u.getInfboletoCodigoFornecidoAgencia());
			e.setInfboletoCodigoFornecidoAgenciaDV(u.getInfboletoCodigoFornecidoAgenciaDV());
			e.setInfboletoCodigoOperacao(u.getInfboletoCodigoOperacao());
			e.setInfboletoInstrucao2(u.getInfboletoInstrucao2());
			e.setInfboletoInstrucao3(u.getInfboletoInstrucao3());
			e.setInfboletoLocalPagamentoLn1(u.getInfboletoLocalPagamentoLn1());
			e.setInfboletoNome(u.getInfboletoNome());
			e.setInfboletoBanco(u.getInfboletoBanco());
			
			e.setInfboletoNumeroboletoPosIni(u.getInfboletoNumeroboletoPosIni());
			e.setInfboletoNumeroboletoPosFim(u.getInfboletoNumeroboletoPosFim());
			e.setInfboletoValorpagoaobancoPosIni(u.getInfboletoValorpagoaobancoPosIni());
			e.setInfboletoValorpagoaobancoPosFim(u.getInfboletoValorpagoaobancoPosFim());
			e.setInfboletoValorcreditadoPosIni(u.getInfboletoValorcreditadoPosIni());
			e.setInfboletoValorcreditadoPosFim(u.getInfboletoValorcreditadoPosFim());
			e.setInfboletoDatapagamentoPosIni(u.getInfboletoDatapagamentoPosIni());
			e.setInfboletoDatapagamentoPosFim(u.getInfboletoDatapagamentoPosFim());
			e.setInfboletoDatapagamentoMascara(u.getInfboletoDatapagamentoMascara());
			return e;
		}else{
			return null;
		}
	}
	

	public static EnderecoIp copiaPropriedades(EnderecoIp u) {
		EnderecoIp e = new EnderecoIp();
		if (u != null) {
			e.setEnderecoipAtivado(u.getEnderecoipAtivado());
			e.setEnderecoipEndereco(u.getEnderecoipEndereco());
			e.setEnderecoipGrupo(u.getEnderecoipGrupo());
			e.setEnderecoipId(u.getEnderecoipId());
			e.setServidor(u.getServidor());
			e.setEnderecoipMacCadastrado(u.getEnderecoipMacCadastrado());
			e.setNomeUsr(u.getNomeUsr());
			e.setTemUsuario(u.getTemUsuario());
			return e;
		} else {
			return null;
		}

	}

	public static PlanosPacotes copiaPropriedades(PlanosPacotes u) {
		PlanosPacotes p = new PlanosPacotes();
		if (u != null) {
			p.setPlanospacotesAtivado(u.getPlanospacotesAtivado());
			p.setPlanospacotesBloqueiaPgAtrasado(u.getPlanospacotesAtivado());
			p.setPlanospacotesDesconto(u.getPlanospacotesDesconto());
			p.setPlanospacotesDescricao(u.getPlanospacotesDescricao());
			p.setPlanospacotesId(u.getPlanospacotesId());
			p.setPlanospacotesNome(u.getPlanospacotesNome());
			p.setPlanospacotesValor(u.getPlanospacotesValor());
			p.setPlanospacotesVelocidade(u.getPlanospacotesVelocidade());
			p.setValor(u.getValor());
			
			p.setPlanospacotesLimiteDesconto(u.getPlanospacotesLimiteDesconto());
			p.setPlanospacotesMulta(u.getPlanospacotesMulta());
			p.setPlanospacotesJuroMes(u.getPlanospacotesJuroMes());
			return p;
		} else {
			return null;
		}

	}

	public static Torre copiaPropriedades(Torre u) {
		Torre t = new Torre();
		if (u != null) {
			t.setTorreInterfaceCliente(u.getTorreInterfaceCliente());
			t.setTorreDescricao(u.getTorreDescricao());
			t.setTorreId(u.getTorreId());
			t.setTorreIpConexao(u.getTorreIpConexao());
			t.setTorreIpConexao2(u.getTorreIpConexao2());
			t.setTorreNome(u.getTorreNome());
			t.setTorreNomeInterface(u.getTorreNomeInterface());
			t.setTorrePorta(u.getTorrePorta());
			t.setTorreSenha(u.getTorreSenha());
			t.setTorreUsuario(u.getTorreUsuario());
			return t;
		} else {
			return null;
		}

	}

	public static Servidor copiaPropriedades(Servidor u) {
		try {
			Servidor us = new Servidor();
			us.setServidorDesativado(u.getServidorDesativado());
			us.setServidorDescricao(u.getServidorDescricao());
			us.setServidorId(u.getServidorId());
			us.setServidorLocal(u.getServidorLocal());
			us.setServidorNome(u.getServidorNome());
			us.setServidorTorres(u.getServidorTorres());
			return us;
		} catch (Exception e) {
			return null;
		}
	}
	public static Usuario copiaPropriedades(Usuario u) {
		try {
			Usuario us = new Usuario();
			us.setUsuarioAdministrativo(u.getUsuarioAdministrativo());
			us.setUsuarioEnviaEmailMonetario(u.getUsuarioEnviaEmailMonetario());
			us.setUsuarioAtivo(u.getUsuarioAtivo());
			us.setUsuarioEqpComodato(u.getUsuarioEqpComodato());
			us.setUsuarioImprimeBoleto(u.getUsuarioImprimeBoleto());
			us.setUsuarioBairro(u.getUsuarioBairro());
			us.setUsuarioBloqueado(u.getUsuarioBloqueado());
			us.setUsuarioAlteraProprioPerfil(u.getUsuarioAlteraProprioPerfil());
			us.setUsuarioCep(u.getUsuarioCep());
			us.setUsuarioCidade(u.getUsuarioCidade());
			us.setUsuarioCpf(u.getUsuarioCpf());
			us.setUsuarioDataLimiteDesbloqueio(u.getUsuarioDataLimiteDesbloqueio());
			us.setUsuarioDtAtivacao(u.getUsuarioDtAtivacao());
			us.setUsuarioDtPagamento(u.getUsuarioDtPagamento());
			us.setUsuarioEmail(u.getUsuarioEmail());
			us.setUsuarioEndereco(u.getUsuarioEndereco());
			us.setUsuarioEstado(u.getUsuarioEstado());
			us.setUsuarioId(u.getUsuarioId());
			us.setUsuarioMac(u.getUsuarioMac());
			us.setUsuarioMenu(u.getUsuarioMenu());
			us.setUsuarioNome(u.getUsuarioNome());
			us.setUsuarioTelefone1(u.getUsuarioTelefone1());
			us.setUsuarioTelefone2(u.getUsuarioTelefone2());
			us.setPagamentoAtrasado(u.isPagamentoAtrasado());
			us.setUsuarioPodePagarMao(u.getUsuarioPodePagarMao());
			us.setUsuarioComplementoEndereco(u.getUsuarioComplementoEndereco());
			us.setEnderecoIp(copiaPropriedades(u.getEnderecoIp()));
			us.setLat(u.getLat());
			us.setLng(u.getLng());

			us.setContrato(copiaPropriedades(u.getContrato()));
			us.setEnderecoIp(copiaPropriedades(u.getEnderecoIp()));
			us.setPlanosPacotes(copiaPropriedades(u.getPlanosPacotes()));
			us.setServidor(copiaPropriedades(u.getServidor()));
			return us;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	/**
	 * Devolve o objeto MAP contido na sessao
	 */
	public static Map<String, String> acoesBusca(HttpServletRequest req) {
		Map<String, String> acoes = (HashMap<String, String>) req.getSession().getAttribute("acoes");
		return acoes;
	}

	@SuppressWarnings("unchecked")
	public static void acoesGrava(String chave, String valor, HttpServletRequest req) {
		Map<String, String> acoes = (HashMap<String, String>) req.getSession().getAttribute("acoes");
		if (acoes != null) {
			acoes.put(chave, valor);
			req.getSession().setAttribute("acoes", acoes);
		} else {
			acoes = new HashMap<String, String>();
			acoes.put(chave, valor);
			req.getSession().setAttribute("acoes", acoes);
		}
	}

	@SuppressWarnings("unchecked")
	public static void acoesRemove(String chave, HttpServletRequest req) {
		Map<String, String> acoes = (HashMap<String, String>) req.getSession().getAttribute("acoes");
		if (acoes != null) {
			acoes.remove(chave);
			req.getSession().setAttribute("acoes", acoes);
		}
	}

	@SuppressWarnings("unchecked")
	/**
	 * Devolve o valor de uma chave fornecida como parametro
	 */
	public static String acoesBusca(String chave, HttpServletRequest req) {
		Map<String, String> acoes = (HashMap<String, String>) req.getSession().getAttribute("acoes");
		if (acoes != null) {
			String ret = acoes.get(chave);
			if (ret != null) {
				return ret;
			} else {
				return "";
			}
		} else {
			return "";
		}
	}
	
	public static Envelope converteRespostaF2B_RegistroBoleto(String xml) throws NumberFormatException{
		return converteRespostaF2B(xml, "m:F2bCobrancaRetorno");
	}
	
	public static Envelope converteRespostaF2B_SituacaoBoleto(String xml) throws NumberFormatException{
		return converteRespostaF2B(xml, "m:F2bSituacaoCobrancaRetorno");
	}
	
	private static Envelope converteRespostaF2B(String xml, String servico) throws NumberFormatException{
		try{
			XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyReplacer("__", "_")));
	
			xstream.alias("SOAP-ENV:Envelope", Envelope.class);
			xstream.aliasField("SOAP-ENV:Body", Envelope.class, "body");
			xstream.aliasField("xmlns:SOAP-ENV", Envelope.class, "xmlSoapEnv");
			xstream.useAttributeFor(Envelope.class, "xmlSoapEnv");
	
			xstream.aliasField(servico, Body.class, "retorno");
	
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
			xstream.useAttributeFor(Cobranca.class, "pagamento");
			xstream.useAttributeFor(Cobranca.class, "taxa_pagamento");
			xstream.useAttributeFor(Cobranca.class, "valor_pago");
			
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
	
			Envelope env = (Envelope) xstream.fromXML(xml);
	
			return env;
		}catch (NumberFormatException nfe) {
			throw new NumberFormatException(nfe.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String geraComprovateBoleto(BoletosGerados boletosGerados) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		
		String dtVenc = sdf.format(new Date(boletosGerados.getBoletosgeradosDataVencimento().getTime()));
		String dtPag = sdf.format(new Date(boletosGerados.getBoletosgeradosDataPagamento().getTime()));
		
		String idBol = boletosGerados.getBoletosgeradosId() + "";
		String valor = boletosGerados.getBoletosgeradosValorPago().replace(".", "");
		
		for(int i = idBol.length(); i <= 6; i++){
			idBol = "0" + idBol;
		}
		
		for(int i = valor.length(); i <= 6; i++){
			valor = "0" + valor;
		}
		
		String ret = idBol + valor + dtVenc + dtPag;
		return ret;
	}
	public static BoletosGerados geraComprovateBoletoReverso(String numero) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			BoletosGerados bg = new BoletosGerados();
			
			String idBol = numero.substring(0, 7);
			String valor = numero.substring(7, 14);
			valor = new Double(valor.substring(0, valor.length() - 2) + "." + valor.substring(valor.length() - 2, valor.length())).toString();
			Date dtVenc = sdf.parse(numero.substring(14, 22));
			Date dtPag = sdf.parse(numero.substring(22, numero.length()));
			
			bg.setBoletosgeradosDataVencimento(new Timestamp(dtVenc.getTime()));
			bg.setBoletosgeradosDataPagamento(new Timestamp(dtPag.getTime()));
			bg.setBoletosgeradosId(new Long(idBol));
			bg.setBoletosgeradosValorPago(valor);
			
			return bg;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getComandosExemplo(Long tipo){
		switch (tipo.intValue()) {
		case 14:
			return "/interface wireless registration-table print stats without-paging where interface={1}";
		case 15:
			return "/interface wireless monitor {1}";
		case 16:
			return "q";
		case 20:
			return "/interface monitor-traffic \"{1}\"";
		case 21:
			return "/interface ethernet print detail without-paging";
		case 22:
			return "/interface pppoe-client print detail without-paging";
		}
		return "";
	}
	
	
	public static String getProximoIP(){
		try {
			String diretorio = ConfigUtil.getInstance().getProperty("diretorio_retorno","c:\\retorno");
			Collection<String> linhasArquivo = ArquivoUtils.leArquivo(diretorio + ArquivoUtils.SEPARADOR_DIRETORIO + "ip.txt");
			
			for (Iterator<String> iterator = linhasArquivo.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				
				String[] f = string.split("\\.");
				int f1 = new Integer(f[0]).intValue();
				int f2 = new Integer(f[1]).intValue();
				int f3 = new Integer(f[2]).intValue();
				int f4 = new Integer(f[3]).intValue();
				
				if(f4 < 253){
					f4++;
				}else{
					f4 = 1;
					f3++;
					if(f3 < 253){
						f3++;
					}else{
						f3 = 1;
						f2++;
						if(f2 < 253){
							f2++;
						}else{
							f2 = 1;
							f1++;
							if(f1 < 253){
								f1++;
							}
						}
					}
				}
				String ret = f1 + "." + f2 + "." + f3 + "." + f4;
				Collection<String> d = new ArrayList<String>();
				d.add(ret);
				ArquivoUtils.excluirArquivo(diretorio + ArquivoUtils.SEPARADOR_DIRETORIO + "ip.txt");
				ArquivoUtils.gravaArquivo(d, diretorio + ArquivoUtils.SEPARADOR_DIRETORIO + "ip.txt");
				return string;
			}
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getEmail(){
		try{
			String diretorio = ConfigUtil.getInstance().getProperty("diretorio_retorno","c:\\retorno");
			Collection<String> linhasArquivo = ArquivoUtils.leArquivo(diretorio + ArquivoUtils.SEPARADOR_DIRETORIO + "nome.txt");
			
			Object[] d =linhasArquivo.toArray();
			int nu1 = (int) Math.round(Math.random() * 500);
			int nu2 = (int) Math.round(Math.random() * 500);
			
			String e1 = (String) d[nu1];
			String e2 = (String) d[nu2];
			
			String n1 = e1.replace(" ", "").replace("/", "").replace(">", "").replace("�", "").replace("-", "");
			String n2 = e2.replace(" ", "").replace("/", "").replace(">", "").replace("�", "").replace("-", "");
			return trata(n1).toLowerCase() + trata(n2).toLowerCase() + Math.round(Math.random() * 1000) + "@meganetdf.com.br";
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	private static String trata(String passa) {
		passa = passa.replaceAll("[ÂÀÁÄÃ]", "A");
		passa = passa.replaceAll("[âãàáä]", "a");
		passa = passa.replaceAll("[ÊÈÉË]", "E");
		passa = passa.replaceAll("[êèéë]", "e");
		passa = passa.replaceAll("[ÎÍÌÏ]", "I");
		passa = passa.replaceAll("[îíìï]", "i");
		passa = passa.replaceAll("[ÔÕÒÓÖ]", "O");
		passa = passa.replaceAll("[ôõòóö]", "o");
		passa = passa.replaceAll("[ÛÙÚÜ]", "U");
		passa = passa.replaceAll("[ûúùü]", "u");
		passa = passa.replaceAll("Ç", "C");
		passa = passa.replaceAll("ç", "c");
		passa = passa.replaceAll("[ýÿ]", "y");
		passa = passa.replaceAll("Ý", "Y");
		passa = passa.replaceAll("ñ", "n");
		passa = passa.replaceAll("Ñ", "N");
		passa = passa.replaceAll("['<>\\|/]", "");
		return passa;
	}

	public static void main(String[] args){
		for (int i = 0; i < 6000; i++) {
			System.out.println(getEmail());
		}
	}
	public static String getProximaSessao(){
		return "071fbc139ba39ee6aa3" + System.currentTimeMillis();
	}
}