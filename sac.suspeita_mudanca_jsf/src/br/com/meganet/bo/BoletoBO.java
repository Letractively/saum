package br.com.meganet.bo;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.jboleto.JBoletoBean;

import br.com.meganet.exception.GAPBDException;
import br.com.meganet.facade.AdministracaoFacade;
import br.com.meganet.hbm.DAO.BoletosGeradosDAO;
import br.com.meganet.hbm.DAO.EnderecoIpDAO;
import br.com.meganet.hbm.DAO.GastoDAO;
import br.com.meganet.hbm.DAO.InfBoletoDAO;
import br.com.meganet.hbm.DAO.LogMeganetDAO;
import br.com.meganet.hbm.DAO.PlanosPacotesDAO;
import br.com.meganet.hbm.DAO.ServidorDAO;
import br.com.meganet.hbm.DAO.UsuarioDAO;
import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.hbm.vo.Demandas;
import br.com.meganet.hbm.vo.EnderecoIp;
import br.com.meganet.hbm.vo.Gasto;
import br.com.meganet.hbm.vo.InfBoleto;
import br.com.meganet.hbm.vo.LogMeganet;
import br.com.meganet.hbm.vo.LucroVO;
import br.com.meganet.hbm.vo.PlanosPacotes;
import br.com.meganet.hbm.vo.Resposta;
import br.com.meganet.hbm.vo.Servidor;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.servicos.F2BBoleto;
import br.com.meganet.servicos.vo.Cobranca;
import br.com.meganet.servicos.vo.Envelope;
import br.com.meganet.util.BufferLog;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.DateUtils;
import br.com.meganet.util.EnumBancos;
import br.com.meganet.util.Util;
import br.com.meganet.util.UtilFeriados;
import br.com.meganet.util.UtilNumero;

public class BoletoBO {

	private UsuarioDAO usuarioDAO;
	private BoletosGeradosDAO boletosGeradosDAO;
	private AdministracaoFacade administracaoFacade;
	private EnderecoIpDAO enderecoIpDAO;
	private ServidorDAO servidorDAO;
	private PlanosPacotesDAO planosPacotesDAO;
	private GastoDAO gastoDAO;
	private LogMeganetDAO logMeganetDAO;
	private InfBoletoDAO infBoletoDAO;

	public void setServidorDAO(ServidorDAO servidorDAO) {
		this.servidorDAO = servidorDAO;
	}

	public void setInfBoletoDAO(InfBoletoDAO infBoletoDAO) {
		this.infBoletoDAO = infBoletoDAO;
	}

	public void setLogMeganetDAO(LogMeganetDAO logMeganetDAO) {
		this.logMeganetDAO = logMeganetDAO;
	}

	public void setGastoDAO(GastoDAO gastoDAO) {
		this.gastoDAO = gastoDAO;
	}

	public void setEnderecoIpDAO(EnderecoIpDAO enderecoIpDAO) {
		this.enderecoIpDAO = enderecoIpDAO;
	}

	public void setPlanosPacotesDAO(PlanosPacotesDAO planosPacotesDAO) {
		this.planosPacotesDAO = planosPacotesDAO;
	}

	public void setAdministracaoFacade(AdministracaoFacade administracaoFacade) {
		this.administracaoFacade = administracaoFacade;
	}

	public void setBoletosGeradosDAO(BoletosGeradosDAO boletosGeradosDAO) {
		this.boletosGeradosDAO = boletosGeradosDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public void criaSequenciaBoletoUsuario(Usuario usuario, int qtd) throws Exception {

		Calendar cincoMesesAtras = new GregorianCalendar();
		cincoMesesAtras.setTimeInMillis(System.currentTimeMillis());
		cincoMesesAtras.set(Calendar.MONTH, cincoMesesAtras.get(Calendar.MONTH) - 5);

		Collection<BoletosGerados> boletosGeradosUsuario = boletosGeradosDAO.findByCriteria(Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usuario), Expression.ge(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, new Timestamp(cincoMesesAtras.getTimeInMillis())), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_BOLETO_EXTRA, false));
		BoletosGerados ultimoBoletoGerado = new BoletosGerados();
		BoletosGerados ultimoBoletoPago = new BoletosGerados();

		ultimoBoletoGerado = buscaUltimoBoletoGerado(boletosGeradosUsuario);
		ultimoBoletoPago = buscaUltimoBoletoPago(boletosGeradosUsuario);

		Calendar dataUltimoBoletoGerado = DateUtils.obterCalendarSemHorario(new Date(ultimoBoletoGerado.getBoletosgeradosDataVencimento().getTime()));
		Calendar dataUltimoBoletoPago;
		if (ultimoBoletoPago.getBoletosgeradosId() != null) {
			dataUltimoBoletoPago = DateUtils.obterCalendarSemHorario(new Date(ultimoBoletoPago.getBoletosgeradosDataVencimento().getTime()));
		} else {
			dataUltimoBoletoPago = new GregorianCalendar();
			dataUltimoBoletoPago.setTimeInMillis(System.currentTimeMillis());
		}
		int diferencaDias = DateUtils.obterIntervaloDiasComSinal(dataUltimoBoletoGerado.getTime(), dataUltimoBoletoPago.getTime());
		if (diferencaDias < 90) {
			for (int j = 1; j <= qtd; j++) {
				BoletosGerados boletosGerados = new BoletosGerados();
				Calendar dataVencimento = DateUtils.obterCalendarSemHorario(new Date(ultimoBoletoGerado.getBoletosgeradosDataVencimento().getTime()));
				dataVencimento.set(Calendar.MONTH, (dataVencimento.get(Calendar.MONTH) + j));

				boletosGerados.setBoletosgeradosDataVencimento(new Timestamp(dataVencimento.getTimeInMillis()));
				boletosGerados.setBoletosgeradosProcessamento(new Timestamp(System.currentTimeMillis()));
				boletosGerados.setBoletosgeradosValor(UtilNumero.formataNumeroCasaDecimal(usuario.getPlanosPacotes().getPlanospacotesValor()));
				boletosGerados.setUsuario(usuario);
				boletosGerados.setBoletosgeradosValorCreditoDebito("0.00");
				boletosGerados.setBoletosgeradosMotivoCreditoDebito("");
				boletosGerados.setBoletosgeradosTipoEnvioF2B("a");
				boletosGerados.setBoletosgeradosBoletoExtra(false);

				boletosGeradosDAO.attachDirty(boletosGerados);
			}
		}
	}

	private BoletosGerados buscaUltimoBoletoGerado(Collection<BoletosGerados> boletosGeradosUsuario) {
		BoletosGerados ultimoBoletoGerado = new BoletosGerados();
		int i = 0;

		for (Iterator<BoletosGerados> iterator = boletosGeradosUsuario.iterator(); iterator.hasNext();) {
			BoletosGerados boletosGerados = (BoletosGerados) iterator.next();
			if (i == 0) {
				ultimoBoletoGerado = boletosGerados;
			} else {
				if (DateUtils.compararDatas(ultimoBoletoGerado.getBoletosgeradosDataVencimento(), boletosGerados.getBoletosgeradosDataVencimento()) == 1) {
					ultimoBoletoGerado = boletosGerados;
				}
			}
			i++;
		}
		return ultimoBoletoGerado;
	}

	private BoletosGerados buscaUltimoBoletoPago(Collection<BoletosGerados> boletosGeradosUsuario) {
		BoletosGerados ultimoBoletoPago = new BoletosGerados();
		int i = 0;
		for (Iterator<BoletosGerados> iterator = boletosGeradosUsuario.iterator(); iterator.hasNext();) {
			BoletosGerados boletosGerados = (BoletosGerados) iterator.next();
			if (boletosGerados.getBoletosgeradosPago()) {
				if (i == 0) {
					ultimoBoletoPago = boletosGerados;
				} else {
					if (DateUtils.compararDatas(ultimoBoletoPago.getBoletosgeradosDataVencimento(), boletosGerados.getBoletosgeradosDataVencimento()) == 1 && boletosGerados.getBoletosgeradosPago()) {
						ultimoBoletoPago = boletosGerados;
					}
				}
				i++;
			}
		}
		return ultimoBoletoPago;
	}

	public JBoletoBean getBoletoById(BoletosGerados boletosGerados) throws IndexOutOfBoundsException, Exception {
		boletosGerados = (BoletosGerados) boletosGeradosDAO.findById(boletosGerados.getBoletosgeradosId());
		return construirBoleto(boletosGerados);
	}

	public JBoletoBean getBoletoByMAC(Usuario usr) throws IndexOutOfBoundsException, Exception {

		List<Usuario> usrs = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_MAC, usr.getUsuarioMac().toUpperCase()));
		if (usrs != null && usrs.size() > 0) {
			Usuario u = Util.copiaPropriedades(usrs.get(0));
			u.setUsuarioBloqueado(Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM);

			Calendar cal = new GregorianCalendar();
			cal.setTimeInMillis(System.currentTimeMillis());
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 3);
			u.setUsuarioDataLimiteDesbloqueio(new Timestamp(cal.getTimeInMillis()));

			Demandas dem = new Demandas();
			dem.setDemandasDataAbertura(new Timestamp(System.currentTimeMillis()));
			dem.setDemandasDescricao("Desbloqueio do acesso por impressão de segunda via de boleto vencido. Feita na máquina do cliente.");
			dem.setDemandasDataPrevistaAtendimento(new Timestamp(System.currentTimeMillis()));
			dem.setUsuarioAbriu(u);
			dem.setUsuarioFechou(u);
			dem.setUsuarioSolicitante(u);
			dem.setDemandasDataEncerramento(new Timestamp(System.currentTimeMillis()));
			administracaoFacade.gravaDemanda(dem, false);

			administracaoFacade.alteraUsuario(u);
			List<BoletosGerados> boletosGerados = boletosGeradosDAO.findByCriteria(Order.asc(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, u), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false), Expression.lt(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, new Date(System.currentTimeMillis())));

			if (boletosGerados != null && boletosGerados.size() > 0) {
				BoletosGerados bolAtrasado = boletosGerados.get(0);
				return construirBoleto(bolAtrasado);

			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public BoletosGerados getBoletoById(Long id) throws Exception {
		BoletosGerados boletosGerados = (BoletosGerados) boletosGeradosDAO.findById(id);
		return boletosGerados;
	}

	public List<BoletosGerados> getBoletoParaComprovante(Long id) throws Exception {
		List<BoletosGerados> boletosGerados = boletosGeradosDAO.findByCriteria(Expression.or(Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_ID, id), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_ID_F2B, id)));
		return boletosGerados;
	}

	public List<JBoletoBean> getBoletoByUsuarioMes(String cliente, String tp, int mes, int ano, String dtAdia, Usuario usrLogado, String idBol) throws IndexOutOfBoundsException, Exception {
		List<Usuario> listaUsuario = usuarioDAO.findByCriteria(Expression.like(tp, "%" + cliente.trim() + "%"));
		if (listaUsuario.size() > 0 && listaUsuario != null) {
			Usuario usuario = listaUsuario.get(0);
			List<JBoletoBean> boletos = new ArrayList<JBoletoBean>();

			if (idBol != null && dtAdia != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dt = sdf.parse(dtAdia);

				BoletosGerados bg = boletosGeradosDAO.findById(new Long(idBol));
				SimpleDateFormat sdff = new SimpleDateFormat("dd/MM/yyyy");
				Date dataNova = sdff.parse(dtAdia);
				Date dataAnti = new Date(bg.getBoletosgeradosDataVencimento().getTime());
				
				if(DateUtils.obterIntervaloDiasComSinal(dataNova, dataAnti) <= 0){
					throw new Exception("A funcionalidade é para ADIAR, não para ANTECIPAR.");
				}

				
				if (!bg.getBoletosgeradosPago()) {

					Demandas dem = new Demandas();
					dem.setDemandasDataAbertura(new Timestamp(System.currentTimeMillis()));
					dem.setDemandasDescricao("Boleto com pagamento em " + sdf.format(bg.getBoletosgeradosDataVencimento()) + ", adiado para o dia " + dtAdia);
					dem.setDemandasDescricaoEncerramento("Boleto gerado");
					dem.setDemandasDataPrevistaAtendimento(new Timestamp(System.currentTimeMillis()));
					dem.setUsuarioAbriu(usrLogado);
					dem.setUsuarioFechou(usrLogado);
					dem.setUsuarioSolicitante(usuario);
					dem.setDemandasDataEncerramento(new Timestamp(System.currentTimeMillis()));
					administracaoFacade.gravaDemanda(dem, false);
	
					bg.setBoletosgeradosDataVencimento(new Timestamp(dt.getTime()));
					bg.setBoletosgeradosDataVencimentoProrrogado(new Timestamp(dt.getTime()));
					
					if(bg.getBoletosgeradosIdF2B() != null && bg.getBoletosgeradosUrlBoletoF2b() != null && !"".equalsIgnoreCase(bg.getBoletosgeradosUrlBoletoF2b())){
						InfBoleto infBoleto = infBoletoDAO.findById(Constantes.ID_INF_BOLETO);
						BufferLog bl = new BufferLog();
						
						F2BBoleto fb = new F2BBoleto();
						fb.alteraCancela(bg, 1, infBoleto);
						enviaBoletos(bg, bl, ConfigUtil.getInstance().getProperty("boleto_f2b_tipo_registro_emergencia", "e"));
						boletos.add(construirBoleto(bg));
					}
					
					boletosGeradosDAO.attachDirty(bg);
					boletos.add(construirBoleto(bg));
				}

			} else {
				Calendar vencimentoIni = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
				vencimentoIni.set(Calendar.YEAR, ano);
				vencimentoIni.set(Calendar.MONTH, mes);
				vencimentoIni.set(Calendar.DAY_OF_MONTH, 1);

				Calendar vencimentoFim = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
				vencimentoFim.set(Calendar.YEAR, ano);
				vencimentoFim.set(Calendar.MONTH, mes);
				vencimentoFim.set(Calendar.DAY_OF_MONTH, vencimentoFim.getActualMaximum(Calendar.DATE));

				List<BoletosGerados> boletosGerados = 
						boletosGeradosDAO.findByCriteria(
						Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usuario), 
						Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false), 
						Expression.between(
								BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, 
								vencimentoIni.getTime(), 
								vencimentoFim.getTime()));

				for (Iterator<BoletosGerados> iterator = boletosGerados.iterator(); iterator.hasNext();) {
					BoletosGerados bg = (BoletosGerados) iterator.next();
					if (!bg.getBoletosgeradosPago()) {
						boletos.add(construirBoleto(bg));
					}
				}
			}
			return boletos;

		} else {
			return null;
		}
	}

	public List<JBoletoBean> getBoletoByUsuarioMes(Calendar dtIni, Calendar dtFim, String dtAdia, Usuario usrLogado) throws IndexOutOfBoundsException, Exception {

		List<JBoletoBean> boletos = new ArrayList<JBoletoBean>();

		List<BoletosGerados> bgBanco = boletosGeradosDAO.findByCriteria(
				Expression.and(
						Expression.ge(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, dtIni.getTime()), 
						Expression.le(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, dtFim.getTime())), 
				Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));

		for (Iterator<BoletosGerados> iterator = bgBanco.iterator(); iterator.hasNext();) {
			BoletosGerados bg = (BoletosGerados) iterator.next();
			if (bg.getUsuario().getUsuarioImprimeBoleto()) {
				if (dtAdia != null && !"".equalsIgnoreCase(dtAdia)) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date dt = sdf.parse(dtAdia);

					Demandas dem = new Demandas();
					dem.setDemandasDataAbertura(new Timestamp(System.currentTimeMillis()));
					dem.setDemandasDescricao("Boleto com pagamento em " + sdf.format(bg.getBoletosgeradosDataVencimento()) + ", adiado para o dia " + dtAdia);
					dem.setDemandasDescricaoEncerramento("Boleto gerado");
					dem.setDemandasDataPrevistaAtendimento(new Timestamp(System.currentTimeMillis()));
					dem.setUsuarioAbriu(usrLogado);
					dem.setUsuarioFechou(usrLogado);
					dem.setUsuarioSolicitante(bg.getUsuario());
					dem.setDemandasDataEncerramento(new Timestamp(System.currentTimeMillis()));
					administracaoFacade.gravaDemanda(dem, false);
					
					if(bg.getBoletosgeradosIdF2B() != null && bg.getBoletosgeradosUrlBoletoF2b() != null && !"".equalsIgnoreCase(bg.getBoletosgeradosUrlBoletoF2b())){
						InfBoleto infBoleto = infBoletoDAO.findById(Constantes.ID_INF_BOLETO);
						BufferLog bl = new BufferLog();
						
						F2BBoleto fb = new F2BBoleto();
						fb.alteraCancela(bg, 1, infBoleto);
						enviaBoletos(bg, bl, ConfigUtil.getInstance().getProperty("boleto_f2b_tipo_registro_emergencia", "e"));
						boletos.add(construirBoleto(bg));
					}
					
					bg.setBoletosgeradosDataVencimento(new Timestamp(dt.getTime()));
					boletosGeradosDAO.attachDirty(bg);
				}

				boletos.add(construirBoleto(bg));
			}
		}
		return boletos;
	}

	public JBoletoBean construirBoleto(BoletosGerados bg) throws IndexOutOfBoundsException, Exception {

		double valorCreditoDebito = verificaCreditoDebito(bg);
		double valorMensalidade = Double.valueOf(bg.getBoletosgeradosValor());

		double valorJurosMulta;
		double valorJurosDia;
		double valorDesconto;
		Long limiteDesconto;

		if (bg.getBoletosgeradosMulta() != null) {
			valorJurosMulta = bg.getBoletosgeradosMulta();
		} else {
			valorJurosMulta = bg.getUsuario().getPlanosPacotes().getPlanospacotesMulta();
		}

		if (bg.getBoletosgeradosJuros() != null) {
			valorJurosDia = bg.getBoletosgeradosJuros();
		} else {
			valorJurosDia = bg.getUsuario().getPlanosPacotes().getPlanospacotesJuroMes();
		}

		if (bg.getBoletosgeradosDesconto() != null) {
			valorDesconto = bg.getBoletosgeradosDesconto();
		} else {
			valorDesconto = bg.getUsuario().getPlanosPacotes().getPlanospacotesDesconto();
		}

		if (bg.getBoletosgeradosLimiteDesconto() != null) {
			limiteDesconto = bg.getBoletosgeradosLimiteDesconto();
		} else {
			limiteDesconto = bg.getUsuario().getPlanosPacotes().getPlanospacotesLimiteDesconto();
		}

		InfBoleto infBoleto = infBoletoDAO.findById(Constantes.ID_INF_BOLETO);

		JBoletoBean boletoBean = new JBoletoBean();
		Calendar dataAtual = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "br"));
		boletoBean.setInfBoleto(infBoleto);

		boletoBean.setDataDocumento(sdf.format(new Date(System.currentTimeMillis())));
		boletoBean.setDataProcessamento(sdf.format(bg.getBoletosgeradosProcessamento()));

		boletoBean.setCodigoTipoRegistro(bg.getBoletosgeradosTipoEnvioF2B());

		boletoBean.setCedente(infBoleto.getInfboletoNome());
		boletoBean.setCarteira(infBoleto.getInfboletoCarteira());
		boletoBean.setLocalPagamento(infBoleto.getInfboletoLocalPagamentoLn1());
		boletoBean.setLocalPagamento2(" ");

		if (valorCreditoDebito > 0) {
			boletoBean.setObservacaoBoleto("R$ " + bg.getBoletosgeradosValorCreditoDebito() + " de acréscimo ao valor da mensalidade referente a(o) " + bg.getBoletosgeradosMotivoCreditoDebito());
		} else if (valorCreditoDebito < 0) {
			boletoBean.setObservacaoBoleto("R$ " + bg.getBoletosgeradosValorCreditoDebito().replace("-", "") + " de crédito referente a(o) " + bg.getBoletosgeradosMotivoCreditoDebito() + ".<br>Valor concedido temporariamente.");
		}

		if (infBoleto.getInfboletoNumeroConvenio() != null && !"".equalsIgnoreCase(infBoleto.getInfboletoNumeroConvenio())) {
			boletoBean.setNumConvenio(infBoleto.getInfboletoNumeroConvenio());
		}
		if (infBoleto.getInfboletoCodigoFornecidoAgencia() != null && !"".equalsIgnoreCase(infBoleto.getInfboletoCodigoFornecidoAgencia())) {
			boletoBean.setCodigoFornecidoAgencia(infBoleto.getInfboletoCodigoFornecidoAgencia());
		}
		if (infBoleto.getInfboletoCodigoFornecidoAgenciaDV() != null && !"".equalsIgnoreCase(infBoleto.getInfboletoCodigoFornecidoAgenciaDV())) {
			boletoBean.setDvCodigoFornecidoAgencia(infBoleto.getInfboletoCodigoFornecidoAgenciaDV());
		}
		if (infBoleto.getInfboletoCodigoOperacao() != null && !"".equalsIgnoreCase(infBoleto.getInfboletoCodigoOperacao())) {
			boletoBean.setCodigoOperacao(infBoleto.getInfboletoCodigoOperacao());
		}
		if (infBoleto.getInfboletoCodigoCliente() != null && !"".equalsIgnoreCase(infBoleto.getInfboletoCodigoCliente())) {
			boletoBean.setCodCliente(infBoleto.getInfboletoCodigoCliente());
		}
		boletoBean.setInstrucao1(setaMensagemDesconto(bg, valorDesconto, limiteDesconto.intValue()));
		boletoBean.setInstrucao2(setaMensagemMultaAtraso(bg, valorJurosMulta) + " e " + setaMensagemJuroDiario(bg, valorJurosDia));
		boletoBean.setInstrucao3(infBoleto.getInfboletoInstrucao1());
		boletoBean.setInstrucao4(infBoleto.getInfboletoInstrucao2());
		if (infBoleto.getInfboletoAgencia().indexOf("-") > -1) {
			boletoBean.setAgencia(infBoleto.getInfboletoAgencia().split("-")[0]);
			boletoBean.setDvAgencia(infBoleto.getInfboletoAgencia().split("-")[1]);
		} else {
			boletoBean.setAgencia(infBoleto.getInfboletoAgencia());
		}
		boletoBean.setContaCorrente(infBoleto.getInfboletoConta().split("-")[0]);
		boletoBean.setDvContaCorrente(infBoleto.getInfboletoConta().split("-")[1]);

		boletoBean.setNomeSacado(bg.getUsuario().getUsuarioNome());
		boletoBean.setEnderecoSacado(bg.getUsuario().getUsuarioEndereco() + " - " + bg.getUsuario().getUsuarioComplementoEndereco());
		boletoBean.setBairroSacado(bg.getUsuario().getUsuarioBairro());
		boletoBean.setCidadeSacado(bg.getUsuario().getUsuarioCidade());
		boletoBean.setUfSacado(bg.getUsuario().getUsuarioEstado());
		boletoBean.setCepSacado(bg.getUsuario().getUsuarioCep());
		boletoBean.setCpfSacado(bg.getUsuario().getUsuarioCpf());

		if (DateUtils.aPrimeiraEhMaiorQueSegunda(bg.getBoletosgeradosDataVencimento(), dataAtual.getTime())) {
			String valorBoleto = UtilNumero.formataNumeroCasaDecimal(Double.valueOf(bg.getBoletosgeradosValor()) + valorCreditoDebito);
			boletoBean.setValorBoleto(valorBoleto);
			boletoBean.setDataVencimento(sdf.format(bg.getBoletosgeradosDataVencimento()));
		} else {
			Calendar novoVencimento = DateUtils.obterCalendarSemHorario(dataAtual.getTime());
			novoVencimento.set(Calendar.DATE, novoVencimento.get(Calendar.DATE) + 3);

			int qtdDias = DateUtils.obterIntervaloDias(bg.getBoletosgeradosDataVencimento(), novoVencimento.getTime());
			if (!bg.getUsuario().getPlanosPacotes().getPlanospacotesBloqueiaPgAtrasado()) {
				valorJurosMulta = 0;
				valorJurosDia = 0;
			}

			double valorFinalMulta = (valorJurosMulta * valorMensalidade) / 100;
			double valorFinalDia = ((valorJurosDia * valorMensalidade) / 100) * qtdDias;

			String valorBoleto = UtilNumero.formataNumeroCasaDecimal((valorMensalidade + valorFinalDia + valorFinalMulta) + valorCreditoDebito);

			boletoBean.setValorBoleto(valorBoleto);

			boletoBean.setDataVencimento(sdf.format(novoVencimento.getTime()));
			if (valorCreditoDebito > 0) {
				boletoBean.setInstrucao1("R$:" + bg.getBoletosgeradosValorCreditoDebito() + " referente: " + bg.getBoletosgeradosMotivoCreditoDebito() + "<br>Segunda via de boleto referente ao pagamento da mensalidade de " + sdf.format(bg.getBoletosgeradosDataVencimento()));
			} else {
				boletoBean.setInstrucao1("Segunda via de boleto referente ao pagamento da mensalidade de " + sdf.format(bg.getBoletosgeradosDataVencimento()));
			}
			boletoBean.setInstrucao2(setaMensagemJuroDiario(bg, valorJurosDia));
			bg.setBoletosgeradosDataVencimentoProrrogado(new Timestamp(novoVencimento.getTimeInMillis()));
		}

		boletoBean.setTituloBoletoHtml("Boleto " + ConfigUtil.getInstance().getProperty("empresa", "Meg@net"));
		if (bg.getBoletosgeradosUrlBoletoF2b() != null && bg.getBoletosgeradosIdF2B() != null) {
			boletoBean.setUrlF2B(bg.getBoletosgeradosUrlBoletoF2b());
			boletoBean.setNossoNumero(bg.getBoletosgeradosIdF2B() + "", EnumBancos.getMapaRotulos().get(infBoleto.getInfboletoBanco().toString()));
			boletoBean.setNoDocumento(bg.getBoletosgeradosIdF2B() + "");
		} else {
			boletoBean.setNossoNumero(bg.getBoletosgeradosId() + "", EnumBancos.getMapaRotulos().get(infBoleto.getInfboletoBanco().toString()));
			boletoBean.setNoDocumento(bg.getBoletosgeradosId() + "");
		}

		bg.setBoletosgeradosValor(UtilNumero.formataNumeroCasaDecimal(valorMensalidade));

		bg.setBoletosgeradosMulta(bg.getUsuario().getPlanosPacotes().getPlanospacotesMulta());
		bg.setBoletosgeradosJuros(bg.getUsuario().getPlanosPacotes().getPlanospacotesJuroMes());
		bg.setBoletosgeradosDesconto(bg.getUsuario().getPlanosPacotes().getPlanospacotesDesconto());
		bg.setBoletosgeradosLimiteDesconto(bg.getUsuario().getPlanosPacotes().getPlanospacotesLimiteDesconto());

		boletosGeradosDAO.attachDirty(bg);
		return boletoBean;
	}

	private double verificaCreditoDebito(BoletosGerados boletosGerados) {
		double ret = 0;

		if (boletosGerados.getBoletosgeradosValorCreditoDebito() != null && !"".equalsIgnoreCase(boletosGerados.getBoletosgeradosValorCreditoDebito()) && !"0.00".equalsIgnoreCase(boletosGerados.getBoletosgeradosValorCreditoDebito())) {
			ret += Double.valueOf(boletosGerados.getBoletosgeradosValorCreditoDebito());
		}
		return ret;
	}

	public void criaMassaBoletoUsuario(Usuario usuario) throws Exception {
		BoletosGerados primeiroBoleto = criaPrimeiroBoletoUsuario(usuario);
		BoletosGerados segundoBoleto = criaSegundoBoletoUsuario(usuario, primeiroBoleto);
		boletosGeradosDAO.attachDirty(primeiroBoleto);
		boletosGeradosDAO.attachDirty(segundoBoleto);
	}

	public Collection<BoletosGerados> adiquirirBoletosEmAberto(String cpf) throws Exception {
		Calendar dataAtual = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));

		Usuario usuario = usuarioDAO.findByProperty(UsuarioDAO.USUARIO_CPF, cpf).get(0);
		InfBoleto infBol = infBoletoDAO.findById(Constantes.ID_INF_BOLETO);
		Collection<BoletosGerados> boletosAtrasados;
		Collection<BoletosGerados> proximoBoleto;

		if (EnumBancos.F2B.getCodigo().equalsIgnoreCase(infBol.getInfboletoBanco().toString())) {

			boletosAtrasados = boletosGeradosDAO.findByCriteria(Order.asc(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO), Expression.le(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, dataAtual.getTime()), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usuario), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));

			proximoBoleto = boletosGeradosDAO.findByCriteria(Order.asc(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO), Expression.gt(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, new Date(System.currentTimeMillis())), Expression.isNotNull(BoletosGeradosDAO.BOLETOSGERADOS_ID_F2B), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usuario));
		} else {

			Calendar data3MesesDepois = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
			data3MesesDepois.set(Calendar.MONTH, data3MesesDepois.get(Calendar.MONTH) + 3);

			boletosAtrasados = null;
			proximoBoleto = boletosGeradosDAO.findByCriteria(Order.asc(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO), Expression.le(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, data3MesesDepois.getTime()), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usuario), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));
		}

		if (boletosAtrasados == null && proximoBoleto == null) {
			return null;
		}

		if (boletosAtrasados != null) {
			boletosAtrasados.addAll(proximoBoleto);
		} else {
			boletosAtrasados = new ArrayList<BoletosGerados>();
			boletosAtrasados.addAll(proximoBoleto);
		}

		for (Iterator<BoletosGerados> iterator = boletosAtrasados.iterator(); iterator.hasNext();) {
			BoletosGerados boletosGerados = (BoletosGerados) iterator.next();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "br"));
			boletosGerados.setDataTempVencimento(sdf.format(boletosGerados.getBoletosgeradosDataVencimento()));
			if (boletosGerados.getBoletosgeradosDataPagamento() != null) {
				boletosGerados.setDataTempPagamento(sdf.format(boletosGerados.getBoletosgeradosDataPagamento()));
			} else {
				boletosGerados.setDataTempPagamento("");
			}
			if (DateUtils.aPrimeiraEhMaiorQueSegunda(dataAtual.getTime(), boletosGerados.getBoletosgeradosDataVencimento())) {
				boletosGerados.setVencido(true);
			}
		}

		return boletosAtrasados;
	}

	public List<BoletosGerados> adiquiriRelatorioBoletoUsuario(Usuario usuario) throws Exception {
		Calendar dataAtual = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
		List<BoletosGerados> boletosAtrasados;
		List<BoletosGerados> proximoBoleto;

		boletosAtrasados = boletosGeradosDAO.findByCriteria(Order.asc(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO), Expression.le(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, dataAtual.getTime()), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usuario));

		proximoBoleto = boletosGeradosDAO.findByCriteria(Order.asc(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO), Expression.gt(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, dataAtual.getTime()), Expression.isNotNull(BoletosGeradosDAO.BOLETOSGERADOS_ID_F2B), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usuario));

		if (boletosAtrasados == null && proximoBoleto == null) {
			return null;
		}

		if (boletosAtrasados != null) {
			boletosAtrasados.addAll(proximoBoleto);
		} else {
			boletosAtrasados = new ArrayList<BoletosGerados>();
			boletosAtrasados.addAll(proximoBoleto);
		}

		return boletosAtrasados;
	}

	public boolean mudarDataPagamentoBoleto(Usuario usuario) throws Exception {
		List<BoletosGerados> boletos = boletosGeradosDAO.findByProperty(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usuario);
		Usuario usuarioBanco = usuarioDAO.findById(usuario.getUsuarioId());
		if (usuarioBanco.getPlanosPacotes().getPlanospacotesValor() != 0D) {
			int diaDataNova = usuario.getUsuarioDtPagamento().intValue();
			int diaDataAntiga = usuarioBanco.getUsuarioDtPagamento().intValue();
			if (diaDataNova == diaDataAntiga) {
				return true;
			} else {
				if (!temPagamentoAtrasado(boletos)) {
					BoletosGerados boletoASerAlterado = boletosGeradosDAO.findByCriteria(Order.asc(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usuario), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false)).get(0);

					int qtdDias = 0;
					Calendar calDataAntiga = GregorianCalendar.getInstance(new Locale("pt", "br"));
					calDataAntiga.setTime(boletoASerAlterado.getBoletosgeradosDataVencimento());

					Calendar calDataNova = GregorianCalendar.getInstance(new Locale("pt", "br"));
					calDataNova.setTime(boletoASerAlterado.getBoletosgeradosDataVencimento());
					calDataNova.set(Calendar.DATE, diaDataNova);

					qtdDias = DateUtils.obterIntervaloDiasComSinal(calDataNova.getTime(), calDataAntiga.getTime());
					boletoASerAlterado.setBoletosgeradosDataVencimento(new Timestamp(calDataNova.getTimeInMillis()));

					double valorPorDia = (Double.valueOf(boletoASerAlterado.getBoletosgeradosValor()) / 30);
					double valorFinal = (valorPorDia * qtdDias) + Double.valueOf(boletoASerAlterado.getBoletosgeradosValor());
					boletoASerAlterado.setBoletosgeradosValor(UtilNumero.formataNumeroCasaDecimal(valorFinal));

					boletosGeradosDAO.merge(boletoASerAlterado);

					boletos = boletosGeradosDAO.findByCriteria(Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usuario), Expression.ge(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, boletoASerAlterado.getBoletosgeradosDataVencimento()));
					for (Iterator<BoletosGerados> iterator = boletos.iterator(); iterator.hasNext();) {
						BoletosGerados boletosGerados = (BoletosGerados) iterator.next();
						Calendar cal = GregorianCalendar.getInstance(new Locale("pt", "br"));
						cal.setTime(boletosGerados.getBoletosgeradosDataVencimento());
						cal.set(Calendar.DATE, diaDataNova);
						boletosGerados.setBoletosgeradosDataVencimento(new Timestamp(cal.getTimeInMillis()));
						boletosGeradosDAO.merge(boletosGerados);
					}

					return true;
				} else {
					return false;
				}
			}
		} else {
			return true;
		}
	}

	public void atualizaPagamentoBoleto(Long numeroBoleto, String valorPagoAoBanco, String valorCreditadoConta, Timestamp dataPagamento) throws Exception {
		try {
			BoletosGerados bg = boletosGeradosDAO.findById(numeroBoleto);
			if (bg != null) {
				Usuario usuario = Util.copiaPropriedades(bg.getUsuario());
				if (usuario.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) != 0 && usuario.getUsuarioBloqueado().compareTo(Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM) != 0) {
					usuario.setUsuarioBloqueado(Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM);
					Servidor serv = servidorDAO.findById(usuario.getServidor().getServidorId());
					EnderecoIp enderecoIp = enderecoIpDAO.findById(usuario.getEnderecoIp().getEnderecoipId());
					PlanosPacotes planosPacotes = planosPacotesDAO.findById(usuario.getPlanosPacotes().getPlanospacotesId());
					usuario.setServidor(serv);
					usuario.setEnderecoIp(enderecoIp);
					usuario.setPlanosPacotes(planosPacotes);
					administracaoFacade.alteraUsuario(usuario);
				}

				bg.setBoletosgeradosDataPagamento(dataPagamento);
				bg.setBoletosgeradosPago(true);
				bg.setBoletosgeradosValorPago(calculaValorPago(valorCreditadoConta, valorPagoAoBanco));
				if (bg.getBoletosgeradosPagouEmMao()) {
					bg.setBoletosgeradosResticio("0.00");
				} else {
					bg.setBoletosgeradosResticio(calculaResticio(bg));
				}
				String vlrCD = bg.getBoletosgeradosResticio();
				bg.setBoletosgeradosResticio("0.00");
				Double vlrC = new Double(vlrCD);

				List<BoletosGerados> boletos = buscaProximosBoletosParaInserirCredito(new Usuario(bg.getUsuario().getUsuarioId()));
				if (boletos != null && boletos.size() > 0 && vlrC != null && vlrC != 0D) {
					BoletosGerados proxBG = boletos.get(0);
					String vlrBol = proxBG.getBoletosgeradosValor();
					Double vlrB = new Double(vlrBol);
					proxBG.setBoletosgeradosValor(UtilNumero.formataNumeroCasaDecimal((vlrB + vlrC)));
					boletosGeradosDAO.merge(proxBG);
				}

				boletosGeradosDAO.merge(bg);
				if (usuario.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) != 0) {
					criaSequenciaBoletoUsuario(bg.getUsuario(), 3);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BoletosGerados> buscaUsuarioCredito(String cliente, String tipo, boolean buscaSemAdiamento) throws Exception {
		List<Usuario> listaUsuario = usuarioDAO.findByCriteria(Expression.ilike(tipo, "%" + cliente + "%"));
		if (listaUsuario.size() > 0 && listaUsuario != null) {
			if (buscaSemAdiamento) {
				return buscaProximosBoletosParaInserirCredito(listaUsuario.get(0));
			} else {
				return buscaProximosBoletosParaAdiar(listaUsuario.get(0));
			}
		} else {
			return new ArrayList<BoletosGerados>();
		}
	}

	public List<BoletosGerados> buscaProximosBoletosParaInserirCredito(Usuario cliente) throws Exception {
		Calendar vencimento = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
		vencimento.set(Calendar.DAY_OF_MONTH, vencimento.get(Calendar.DAY_OF_MONTH) + ConfigUtil.getInstance().getIntProperty("dias_envio_antecipado_boleto", "15"));

		List<BoletosGerados> boletosGerados = null;
		InfBoleto infBol = infBoletoDAO.findById(Constantes.ID_INF_BOLETO);
		if (EnumBancos.F2B.getCodigo().equalsIgnoreCase(infBol.getInfboletoBanco().toString())) {
			boletosGerados = boletosGeradosDAO.findByCriteria(Order.asc(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, cliente), Expression.ge(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, vencimento.getTime()), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false), Expression.isNull(BoletosGeradosDAO.BOLETOSGERADOS_ID_F2B));

		} else {
			boletosGerados = boletosGeradosDAO.findByCriteria(Order.asc(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, cliente), Expression.ge(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, vencimento.getTime()), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));
		}

		for (Iterator<BoletosGerados> iterator = boletosGerados.iterator(); iterator.hasNext();) {
			BoletosGerados boletosGerados2 = (BoletosGerados) iterator.next();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "br"));
			boletosGerados2.setDataTempVencimento(sdf.format(boletosGerados2.getBoletosgeradosDataVencimento()));
		}
		return boletosGerados;
	}

	public List<BoletosGerados> buscaProximosBoletosParaAdiar(Usuario cliente) throws Exception {
		Calendar vencimento = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
		vencimento.set(Calendar.DAY_OF_MONTH, 1);
		vencimento.set(Calendar.MONTH, vencimento.get(Calendar.MONTH) - 1);

		List<BoletosGerados> boletosGerados = null;
		boletosGerados = boletosGeradosDAO.findByCriteria(Order.asc(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, cliente), Expression.ge(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, vencimento.getTime()), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));

		for (Iterator<BoletosGerados> iterator = boletosGerados.iterator(); iterator.hasNext();) {
			BoletosGerados boletosGerados2 = (BoletosGerados) iterator.next();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "br"));
			boletosGerados2.setDataTempVencimento(sdf.format(boletosGerados2.getBoletosgeradosDataVencimento()));
		}
		return boletosGerados;
	}

	public int salvaCredito(List<BoletosGerados> boletos) throws Exception {

		for (Iterator<BoletosGerados> iterator = boletos.iterator(); iterator.hasNext();) {
			BoletosGerados boletosGerados = (BoletosGerados) iterator.next();
			BoletosGerados boletosBanco = boletosGeradosDAO.findById(boletosGerados.getBoletosgeradosId());
			String vlrCD = "0.00";
			String vlrBol = "0.00";
			if (boletosGerados.getBoletosgeradosValorCreditoDebito() != null && !"".equalsIgnoreCase(boletosGerados.getBoletosgeradosValorCreditoDebito())) {
				vlrCD = boletosGerados.getBoletosgeradosValorCreditoDebito();
			}

			if (boletosBanco.getBoletosgeradosValor() != null && !"".equalsIgnoreCase(boletosBanco.getBoletosgeradosValor())) {
				vlrBol = boletosBanco.getBoletosgeradosValor();
			}

			Double vlrC = new Double(vlrCD);
			Double vlrB = new Double(vlrBol);

			if ((vlrB + vlrC) < 10) {
				return 2;
			}

		}

		for (Iterator<BoletosGerados> iterator = boletos.iterator(); iterator.hasNext();) {

			BoletosGerados boletosGerados = (BoletosGerados) iterator.next();

			BoletosGerados boletosBanco = boletosGeradosDAO.findById(boletosGerados.getBoletosgeradosId());
			boletosBanco.setBoletosgeradosMotivoCreditoDebito(boletosGerados.getBoletosgeradosMotivoCreditoDebito());
			boletosBanco.setBoletosgeradosValorCreditoDebito("0.00");

			String vlrCD = "0.00";
			String vlrBol = "0.00";
			if (boletosGerados.getBoletosgeradosValorCreditoDebito() != null && !"".equalsIgnoreCase(boletosGerados.getBoletosgeradosValorCreditoDebito())) {
				vlrCD = boletosGerados.getBoletosgeradosValorCreditoDebito();
			}

			if (boletosBanco.getBoletosgeradosValor() != null && !"".equalsIgnoreCase(boletosBanco.getBoletosgeradosValor())) {
				vlrBol = boletosBanco.getBoletosgeradosValor();
			}
			Double vlrC = new Double(vlrCD);
			Double vlrB = new Double(vlrBol);

			boletosBanco.setBoletosgeradosValor(UtilNumero.formataNumeroCasaDecimal((vlrB + vlrC)));
			boletosGeradosDAO.attachDirty(boletosBanco);
		}
		return 1;
	}

	public List<Long> procuraUsuariosComBoletosNaoPagosPorIntervaloDeTempo() throws Exception {
		Calendar dataAtual = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
		Calendar dataMenosCentoEVinte = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
		dataMenosCentoEVinte.set(Calendar.DAY_OF_MONTH, dataMenosCentoEVinte.get(Calendar.DAY_OF_MONTH) - 120);
		return usuarioDAO.procuraUsuariosComBoletosNaoPagosPorIntervaloDeTempo(new Date(dataAtual.getTimeInMillis()), new Date(dataMenosCentoEVinte.getTimeInMillis()));
	}

	// ############################################################################################
	// ############################################################################################
	// ############################################################################################

	private String calculaResticio(BoletosGerados boletosGerados) throws NumberFormatException, ParseException {
		boolean adicionaResquicio = ConfigUtil.getInstance().getBooleanProperty("adiciona_resquicio", false);

		Double desconto = boletosGerados.getBoletosgeradosDesconto();
		Double multa = boletosGerados.getBoletosgeradosMulta();
		Double juros = boletosGerados.getBoletosgeradosJuros();

		Double valorPago = new Double(boletosGerados.getBoletosgeradosValorPago().trim());
		Double valorBoleto = new Double(boletosGerados.getBoletosgeradosValor());

		Calendar dataPagamento = GregorianCalendar.getInstance(new Locale("pt", "br"));
		dataPagamento.setTimeInMillis(boletosGerados.getBoletosgeradosDataPagamento().getTime());

		Calendar dataVencimento = GregorianCalendar.getInstance(new Locale("pt", "br"));
		boolean ehDataVencimento;

		if (boletosGerados.getBoletosgeradosDataVencimentoProrrogado() == null || DateUtils.compararDatas(dataPagamento.getTime(), dataVencimento.getTime()) == 1) {
			dataVencimento.setTimeInMillis(boletosGerados.getBoletosgeradosDataVencimento().getTime());
			ehDataVencimento = true;
		} else {
			dataVencimento.setTimeInMillis(boletosGerados.getBoletosgeradosDataVencimentoProrrogado().getTime());
			ehDataVencimento = false;
		}

		// pagou no dia certo
		if (DateUtils.compararDatasDescontandoFimDeSemanaEFeriado(dataPagamento.getTime(), UtilFeriados.prorrogarDataParaFeriadoEFimDeSemana(dataVencimento.getTime()).getTime()) == 0 && adicionaResquicio) {

			return UtilNumero.formataNumeroCasaDecimal(valorBoleto - valorPago);

			// pagou atrasado
		} else if (DateUtils.compararDatasDescontandoFimDeSemanaEFeriado(dataPagamento.getTime(), UtilFeriados.prorrogarDataParaFeriadoEFimDeSemana(dataVencimento.getTime()).getTime()) == -1 && adicionaResquicio) {
			double valorJurosMulta = (new Double(boletosGerados.getBoletosgeradosValor()) * (multa)) / 100;
			double valorJurosDiario = (new Double(boletosGerados.getBoletosgeradosValor()) * (juros)) / 100;
			int qtdDiasAdiantado = DateUtils.obterIntervaloDiasComSinal(dataPagamento.getTime(), dataVencimento.getTime());
			double valorFinal = (valorJurosMulta + (valorJurosDiario * qtdDiasAdiantado)) + valorBoleto;
			return UtilNumero.formataNumeroCasaDecimal(valorFinal - valorPago);

			// pagou adiantado
		} else if (DateUtils.compararDatasDescontandoFimDeSemanaEFeriado(dataPagamento.getTime(), UtilFeriados.prorrogarDataParaFeriadoEFimDeSemana(dataVencimento.getTime()).getTime()) == 1 && adicionaResquicio) {
			int qtdDiasAdiantado = DateUtils.obterIntervaloDiasComSinal(dataVencimento.getTime(), dataPagamento.getTime());

			if (ehDataVencimento && qtdDiasAdiantado >= boletosGerados.getBoletosgeradosLimiteDesconto().intValue()) {
				Double valorQueDeveSerPago = valorBoleto - ((valorBoleto * desconto) / 100);
				return UtilNumero.formataNumeroCasaDecimal(valorQueDeveSerPago - valorPago);
			} else {
				return UtilNumero.formataNumeroCasaDecimal(valorBoleto - valorPago);
			}
		}
		return "0.00";
	}

	public boolean temPagamentoAtrasado(List<BoletosGerados> boletos) throws Exception {
		boolean retorno = false;
		Calendar dataAtual = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));

		for (Iterator<BoletosGerados> iterator = boletos.iterator(); iterator.hasNext();) {
			BoletosGerados boletosGerados = (BoletosGerados) iterator.next();
			if (!boletosGerados.getBoletosgeradosPago() && DateUtils.aPrimeiraEhMaiorQueSegunda(dataAtual.getTime(), boletosGerados.getBoletosgeradosDataVencimento())) {
				retorno = true;
			}
		}
		return retorno;
	}

	public String setaMensagemJuroDiario(BoletosGerados bg, double juro) {
		double valorJuros = (new Double(bg.getBoletosgeradosValor()) * (juro)) / 100;
		return "Cobrar R$ " + UtilNumero.formataNumeroCasaDecimal(valorJuros) + " por dia de atraso.";
	}

	public String setaMensagemMultaAtraso(BoletosGerados bg, double multa) {
		if (bg.getUsuario().getPlanosPacotes().getPlanospacotesBloqueiaPgAtrasado()) {
			double valorJuros = (new Double(bg.getBoletosgeradosValor()) * (multa)) / 100;
			return "Após o vencimento cobrar multa de R$ " + UtilNumero.formataNumeroCasaDecimal(valorJuros);
		} else {
			return "&nbsp;";
		}
	}

	public String setaMensagemDesconto(BoletosGerados boletosGerados, double desconto, int limiteDesc) {
		if (desconto > 0) {
			double valorDesconto = (new Double(boletosGerados.getBoletosgeradosValor()) * desconto) / 100;
			return "Conceder desconto de R$ " + UtilNumero.formataNumeroCasaDecimal(valorDesconto) + ", para pagamento até " + limiteDesc + " dia(s) antes do pagamento.";
		} else {
			return "";
		}
	}

	private BoletosGerados criaPrimeiroBoletoUsuario(Usuario usuario) throws Exception {
		JBoletoBean boletoBean = getValorEDataBoleto(usuario);
		BoletosGerados boletosGerados = new BoletosGerados();
		Calendar dataBoleto = DateUtils.obterCalendar(boletoBean.getDataVencimento(), false);

		boletosGerados.setBoletosgeradosDataVencimento(new Timestamp(dataBoleto.getTimeInMillis()));
		boletosGerados.setBoletosgeradosProcessamento(usuario.getUsuarioDtAtivacao());
		boletosGerados.setBoletosgeradosValor(boletoBean.getValorBoleto());
		boletosGerados.setUsuario(usuario);
		boletosGerados.setBoletosgeradosValorCreditoDebito("0.00");
		boletosGerados.setBoletosgeradosMotivoCreditoDebito("");
		boletosGerados.setBoletosgeradosTipoEnvioF2B("a");
		boletosGerados.setBoletosgeradosBoletoExtra(false);

		return boletosGerados;
	}

	private BoletosGerados criaSegundoBoletoUsuario(Usuario usuario, BoletosGerados boletosGeradosTemp) throws Exception {

		BoletosGerados boletosGerados = new BoletosGerados();

		Calendar dataVencimento = DateUtils.obterCalendarSemHorario(new Date(boletosGeradosTemp.getBoletosgeradosDataVencimento().getTime()));
		dataVencimento.set(Calendar.MONTH, (dataVencimento.get(Calendar.MONTH) + 1));

		boletosGerados.setBoletosgeradosDataVencimento(new Timestamp(dataVencimento.getTimeInMillis()));
		boletosGerados.setBoletosgeradosProcessamento(boletosGeradosTemp.getBoletosgeradosProcessamento());
		boletosGerados.setBoletosgeradosValor(UtilNumero.formataNumeroCasaDecimal(boletosGeradosTemp.getUsuario().getPlanosPacotes().getPlanospacotesValor()));
		boletosGerados.setUsuario(boletosGeradosTemp.getUsuario());
		boletosGerados.setBoletosgeradosValorCreditoDebito("0.00");
		boletosGerados.setBoletosgeradosMotivoCreditoDebito("");
		boletosGerados.setBoletosgeradosTipoEnvioF2B("a");
		boletosGerados.setBoletosgeradosBoletoExtra(false);
		return boletosGerados;
	}

	private JBoletoBean getValorEDataBoleto(Usuario usuario) throws Exception {

		Calendar dataAtual = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
		Calendar dataEnvioBoleto = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
		dataEnvioBoleto.set(Calendar.DATE, usuario.getUsuarioDtPagamento().intValue() - ConfigUtil.getInstance().getIntProperty("dias_envio_antecipado_boleto", "15"));

		Calendar dataAtivacoaUsr = DateUtils.obterCalendarSemHorario(usuario.getUsuarioDtAtivacao());
		Calendar dataProximoPg = DateUtils.obterCalendarSemHorario(usuario.getUsuarioDtAtivacao());

		dataProximoPg.set(Calendar.DATE, usuario.getUsuarioDtPagamento().intValue());

		/*
		 * data de pagamento menor que a data de ativação. tem que adicionar um
		 * mes pq senão gera um boleto vencido. OU se o boleto será gerado já
		 * vencido. OU se data de envio do boleto é menor que a data do boleto.
		 * ex: envio: 07/10/10, boleto:08/10/10
		 * 
		 * então adicionamos 1 mês para o primeiro boleto
		 */
		if (usuario.getUsuarioDtPagamento().intValue() < dataAtivacoaUsr.get(Calendar.DAY_OF_MONTH) || DateUtils.compararDatas(dataAtual.getTime(), dataProximoPg.getTime()) == -1 || DateUtils.compararDatas(dataEnvioBoleto.getTime(), dataAtual.getTime()) > 0) {
			dataProximoPg.set(Calendar.MONTH, (dataProximoPg.get(Calendar.MONTH) + 1));
		}

		String valorBoleto = "";
		int diasAntesPrimeiroPagamento = DateUtils.obterIntervaloDias(dataAtivacoaUsr.getTime(), dataProximoPg.getTime());
		double valorPorDia = (usuario.getPlanosPacotes().getPlanospacotesValor() / 30);
		double valorTemp = valorPorDia * diasAntesPrimeiroPagamento;
		if (valorTemp < 20) {
			diasAntesPrimeiroPagamento = DateUtils.obterIntervaloDias(dataAtivacoaUsr.getTime(), dataProximoPg.getTime());
			valorBoleto = UtilNumero.formataNumeroCasaDecimal(valorPorDia * diasAntesPrimeiroPagamento);
		} else {
			valorBoleto = UtilNumero.formataNumeroCasaDecimal(valorPorDia * diasAntesPrimeiroPagamento);
		}
		JBoletoBean boletoBean = new JBoletoBean();
		boletoBean.setValorBoleto(valorBoleto);
		boletoBean.setDataVencimento(DateUtils.formatar(dataProximoPg.getTime(), DateUtils.FORMATO_DATA_DD_MM_AAAA_BARRA));
		return boletoBean;
	}

	private String calculaValorPago(String valorCreditadoConta, String valorPagoAoBanco) {
		valorCreditadoConta = valorCreditadoConta.replace(".", "");
		valorPagoAoBanco = valorPagoAoBanco.replace(".", "");
		valorCreditadoConta = valorCreditadoConta.substring(0, valorCreditadoConta.length() - 2) + "." + valorCreditadoConta.substring(valorCreditadoConta.length() - 2, valorCreditadoConta.length());
		valorPagoAoBanco = valorPagoAoBanco.substring(0, valorPagoAoBanco.length() - 2) + "." + valorPagoAoBanco.substring(valorPagoAoBanco.length() - 2, valorPagoAoBanco.length());
		Double vlrPagoBanco = new Double(valorPagoAoBanco);
		Double vlrCreditadoConta = new Double(valorCreditadoConta);

		return UtilNumero.formataNumeroCasaDecimal(vlrCreditadoConta + vlrPagoBanco);
	}

	public List<BoletosGerados> buscaBoletosAtrasados(Usuario usr) throws Exception {
		Calendar dataAtual = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
		return boletosGeradosDAO.findByCriteria(Expression.le(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, dataAtual.getTime()), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usr), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));
	}

	public void deletaBoletosUsuarioInativo(Usuario usuario) throws Exception {
		Timestamp hoje = new Timestamp(System.currentTimeMillis());
		List<BoletosGerados> boletosGerados = boletosGeradosDAO.findByCriteria(Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usuario), Expression.ge(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, hoje), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));

		for (Iterator<BoletosGerados> iterator = boletosGerados.iterator(); iterator.hasNext();) {
			BoletosGerados boletosGerados2 = (BoletosGerados) iterator.next();
			if (boletosGerados2.getBoletosgeradosIdF2B() != null) {
				if (boletosGerados2.getBoletosgeradosDataPagamento() != null) {
					F2BBoleto f2b = new F2BBoleto();
					InfBoleto infBoleto = infBoletoDAO.findById(Constantes.ID_INF_BOLETO);
					f2b.alteraCancela(boletosGerados2, 2, infBoleto);
					boletosGeradosDAO.delete(boletosGerados2);
				} else {
					boletosGeradosDAO.delete(boletosGerados2);
				}
			} else {
				boletosGeradosDAO.delete(boletosGerados2);
			}
		}

	}

	public List<BoletosGerados> buscaBoletosAbertosAPartirDeUmaData(Calendar dataBoleto, Usuario usuario) throws Exception {
		return boletosGeradosDAO.findByCriteria(Expression.lt(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, dataBoleto.getTime()), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usuario), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));
	}

	public int informaPagamentoEmMaoPeloIdBoleto(Long idBoleto, String valor, Usuario usuarioAbriu) throws Exception {
		BoletosGerados boletosGerados = boletosGeradosDAO.findById(idBoleto);
		return informaPagamentoPeloBoleto(boletosGerados, valor, usuarioAbriu);
	}

	public int informaPagamentoPeloBoleto(BoletosGerados boletosGerados, String valor, Usuario usuarioAbriu) throws Exception {
		if (boletosGerados != null) {
			Usuario usuario = Util.copiaPropriedades(boletosGerados.getUsuario());
			if (!usuario.getUsuarioPodePagarMao()) {
				return 4;
			}
			Calendar dataBoleto = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
			dataBoleto.setTimeInMillis(boletosGerados.getBoletosgeradosDataVencimento().getTime());

			List<BoletosGerados> boletosAbertosAnteriorAoBoletoBaixado = buscaBoletosAbertosAPartirDeUmaData(dataBoleto, usuario);
			if (boletosAbertosAnteriorAoBoletoBaixado != null && boletosAbertosAnteriorAoBoletoBaixado.size() > 0 && !boletosGerados.getBoletosgeradosBoletoExtra().booleanValue()) {
				return 3;
			}

			boletosGerados.setBoletosgeradosPago(true);
			boletosGerados.setBoletosgeradosDataPagamento(new Timestamp(DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis())).getTimeInMillis()));
			boletosGerados.setBoletosgeradosResticio("0.00");
			boletosGerados.setBoletosgeradosPagouEmMao(true);
			boletosGerados.setBoletosgeradosValorPago(valor);
			InfBoleto infBol = infBoletoDAO.findById(Constantes.ID_INF_BOLETO);
			if (EnumBancos.F2B.getCodigo().equalsIgnoreCase(infBol.getInfboletoBanco().toString()) && boletosGerados.getBoletosgeradosIdF2B() != null) {
				F2BBoleto f2b = new F2BBoleto();
				InfBoleto infBoleto = infBoletoDAO.findById(Constantes.ID_INF_BOLETO);
				f2b.alteraCancela(boletosGerados, 2, infBoleto);
			}
			int ret = 5;
			if (usuario.getUsuarioBloqueado().compareTo(Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM) != 0 && usuario.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) != 0) {
				usuario.setUsuarioBloqueado(Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM);
				administracaoFacade.alteraUsuario(usuario);
				ret = 2;
			}

			boletosGeradosDAO.attachDirty(boletosGerados);
			if (!boletosGerados.getBoletosgeradosBoletoExtra()) {
				criaSequenciaBoletoUsuario(usuario, 3);
			}

			List<Usuario> usrAdmMon = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_ENVIA_EMAIL_MONETARIO, true), Expression.eq(UsuarioDAO.USUARIO_ADMINISTRATIVO, true));

			EnviaEmailBO.enviaEMailUsrAdmPagamentoBoletoEmMao(usrAdmMon, boletosGerados, valor, usuarioAbriu);
			EnviaEmailBO.enviaEMailComprovanteUsuario(usuario, boletosGerados, valor);
			return ret;
		} else {
			return 1;
		}
	}

	public List<BoletosGerados> buscaRelatorioRendimentoS(int comboEstado, int comboPesquisa, Timestamp dataInicial, Timestamp dataFinal) throws GAPBDException {
		DetachedCriteria findCriteria = DetachedCriteria.forClass(boletosGeradosDAO.getPojoClass());

		switch (comboEstado) {
		case 1:
			findCriteria.add(Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, true));
			break;
		case 2:
			findCriteria.add(Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));
			break;
		}

		switch (comboPesquisa) {
		case 0:
			findCriteria.add(Expression.and(Expression.ge(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, dataInicial), Expression.le(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, dataFinal)));
			break;
		case 1:
			findCriteria.add(Expression.and(Expression.ge(BoletosGeradosDAO.BOLETOSGERADOS_DATA_PAGAMENTO, dataInicial), Expression.le(BoletosGeradosDAO.BOLETOSGERADOS_DATA_PAGAMENTO, dataFinal)));
			break;
		}

		List<BoletosGerados> boletos = boletosGeradosDAO.findByDetachedCriteria(findCriteria);
		if (comboEstado == 1) {
			return formataRelatorioBoletosPagos(boletos);
		} else {
			return formataRelatorioBoletosDiferentesPagos(boletos);
		}
	}

	private List<BoletosGerados> formataRelatorioBoletosDiferentesPagos(List<BoletosGerados> boletos) {
		for (Iterator<BoletosGerados> iterator = boletos.iterator(); iterator.hasNext();) {
			BoletosGerados boletosGerados = (BoletosGerados) iterator.next();
			try {
				if (boletosGerados.getBoletosgeradosValorPago() != null && !boletosGerados.getBoletosgeradosValorPago().trim().equalsIgnoreCase("")) {
					boletosGerados.setBoletosgeradosValorPago(UtilNumero.aplicaMascaraMoeda(boletosGerados.getBoletosgeradosValorPago()));
				} else {
					boletosGerados.setBoletosgeradosValorPago("");
				}

				if (boletosGerados.getBoletosgeradosValor() != null && !boletosGerados.getBoletosgeradosValor().trim().equalsIgnoreCase("")) {
					boletosGerados.setBoletosgeradosValor(UtilNumero.aplicaMascaraMoeda(boletosGerados.getBoletosgeradosValor()));
				} else {
					boletosGerados.setBoletosgeradosValor("");
				}

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "br"));
				if (boletosGerados.getBoletosgeradosDataVencimento() != null) {
					Timestamp time = boletosGerados.getBoletosgeradosDataVencimento();
					boletosGerados.setDataTempVencimento(sdf.format(new Date(time.getTime())));
				}

				if (boletosGerados.getBoletosgeradosDataPagamento() != null) {
					Timestamp time2 = boletosGerados.getBoletosgeradosDataPagamento();
					boletosGerados.setDataTempPagamento(sdf.format(new Date(time2.getTime())));
				}

				Calendar dataAtual = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
				if (boletosGerados.getBoletosgeradosPago()) {
					boletosGerados.setEstaPago("Pago");
				} else {
					if (DateUtils.aPrimeiraEhMaiorQueSegunda(dataAtual.getTime(), boletosGerados.getBoletosgeradosDataVencimento())) {
						boletosGerados.setEstaPago("Vencido");
					} else {
						boletosGerados.setEstaPago("Não pago");
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return boletos;
	}

	private List<BoletosGerados> formataRelatorioBoletosPagos(List<BoletosGerados> boletos) {

		for (Iterator<BoletosGerados> iterator = boletos.iterator(); iterator.hasNext();) {
			BoletosGerados boletosGerados = (BoletosGerados) iterator.next();
			try {

				double vlrJurosMulta;
				double vlrJurosDia;
				double vlrDesconto;

				if (boletosGerados.getBoletosgeradosMulta() != null) {
					vlrJurosMulta = boletosGerados.getBoletosgeradosMulta();
				} else {
					vlrJurosMulta = boletosGerados.getUsuario().getPlanosPacotes().getPlanospacotesMulta();
				}

				if (boletosGerados.getBoletosgeradosJuros() != null) {
					vlrJurosDia = boletosGerados.getBoletosgeradosJuros();
				} else {
					vlrJurosDia = boletosGerados.getUsuario().getPlanosPacotes().getPlanospacotesJuroMes();
				}

				if (boletosGerados.getBoletosgeradosDesconto() != null) {
					vlrDesconto = boletosGerados.getBoletosgeradosDesconto();
				} else {
					vlrDesconto = boletosGerados.getUsuario().getPlanosPacotes().getPlanospacotesDesconto();
				}

				Double valorBoleto = new Double(boletosGerados.getBoletosgeradosValor());
				Double valorPago = new Double(boletosGerados.getBoletosgeradosValorPago());

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "br"));

				Double valorDeveriaSerPago = new Double("0");
				Double valorRecebidoNaConta = new Double("0");
				Double valorPagoAoBanco = new Double(ConfigUtil.getInstance().getProperty("valor_custo_boleto", "3.00"));

				Calendar dataVencimento = GregorianCalendar.getInstance(new Locale("pt", "br"));
				Calendar dataPagamento = GregorianCalendar.getInstance(new Locale("pt", "br"));
				dataPagamento.setTimeInMillis(boletosGerados.getBoletosgeradosDataPagamento().getTime());
				boolean ehDataVencimento;

				if (boletosGerados.getBoletosgeradosDataVencimentoProrrogado() == null || DateUtils.compararDatas(dataPagamento.getTime(), dataVencimento.getTime()) == 1) {
					dataVencimento.setTimeInMillis(boletosGerados.getBoletosgeradosDataVencimento().getTime());
					ehDataVencimento = true;
				} else {
					dataVencimento.setTimeInMillis(boletosGerados.getBoletosgeradosDataVencimentoProrrogado().getTime());
					ehDataVencimento = false;
				}

				// pagou no dia certo
				if (DateUtils.compararDatasDescontandoFimDeSemanaEFeriado(dataPagamento.getTime(), UtilFeriados.prorrogarDataParaFeriadoEFimDeSemana(dataVencimento.getTime()).getTime()) == 0) {

					valorDeveriaSerPago = valorPago;

					// pagou atrasado
				} else if (DateUtils.compararDatasDescontandoFimDeSemanaEFeriado(dataPagamento.getTime(), UtilFeriados.prorrogarDataParaFeriadoEFimDeSemana(dataVencimento.getTime()).getTime()) == -1) {
					double valorJurosMulta = (new Double(boletosGerados.getBoletosgeradosValor()) * (vlrJurosMulta)) / 100;
					double valorJurosDiario = (new Double(boletosGerados.getBoletosgeradosValor()) * (vlrJurosDia)) / 100;
					int qtdDiasAdiantado = DateUtils.obterIntervaloDiasComSinal(dataPagamento.getTime(), dataVencimento.getTime());
					double valorFinal = (valorJurosMulta + (valorJurosDiario * qtdDiasAdiantado)) + valorBoleto;
					valorDeveriaSerPago = (valorFinal - valorPago) + valorBoleto;
					// pagou adiantado

				} else if (DateUtils.compararDatasDescontandoFimDeSemanaEFeriado(dataPagamento.getTime(), UtilFeriados.prorrogarDataParaFeriadoEFimDeSemana(dataVencimento.getTime()).getTime()) == 1) {
					int qtdDiasAdiantado = DateUtils.obterIntervaloDiasComSinal(dataVencimento.getTime(), dataPagamento.getTime());

					if (ehDataVencimento && qtdDiasAdiantado >= boletosGerados.getBoletosgeradosLimiteDesconto().intValue()) {
						Double valorQueDeveSerPago = valorBoleto - ((valorBoleto * vlrDesconto) / 100);
						valorDeveriaSerPago = (valorQueDeveSerPago - valorPago) + valorBoleto;
					} else {
						valorDeveriaSerPago = (valorBoleto - valorPago) + valorBoleto;
					}
				}

				valorRecebidoNaConta = valorPago - valorPagoAoBanco;

				boletosGerados.setDataTempVencimento(sdf.format(new Date(dataVencimento.getTimeInMillis())));
				boletosGerados.setDataTempPagamento(sdf.format(new Date(dataPagamento.getTimeInMillis())));
				boletosGerados.setBoletosgeradosValorPago(UtilNumero.aplicaMascaraMoeda(valorPago.toString()));
				boletosGerados.setBoletosgeradosValor(UtilNumero.aplicaMascaraMoeda(valorBoleto.toString()));
				boletosGerados.setValorDeveriaSerPago(UtilNumero.aplicaMascaraMoeda(UtilNumero.formataNumeroCasaDecimalSemprePositivo(valorDeveriaSerPago)));
				boletosGerados.setValorPagoAoBanco(UtilNumero.aplicaMascaraMoeda(valorPagoAoBanco.toString()));
				boletosGerados.setValorRecebidoPeloBoletoNaConta(UtilNumero.aplicaMascaraMoeda(valorRecebidoNaConta.toString()));

			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return boletos;

	}

	public LucroVO buscaRelatorioLucros(String strInicial) throws Exception {
		Calendar dtInicial = GregorianCalendar.getInstance(new Locale("pt", "br"));
		Calendar dtFinal = GregorianCalendar.getInstance(new Locale("pt", "br"));
		SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy", new Locale("pt", "br"));

		Calendar hoje = GregorianCalendar.getInstance(new Locale("pt", "br"));
		hoje.setTimeInMillis(System.currentTimeMillis());

		dtInicial.setTime(sdf.parse(strInicial));
		dtInicial.set(Calendar.MONTH, dtInicial.get(Calendar.MONTH) + 1);
		dtInicial.set(Calendar.DAY_OF_MONTH, 1);
		dtFinal.setTime(sdf.parse(strInicial));
		dtFinal.set(Calendar.MONTH, dtFinal.get(Calendar.MONTH) + 1);
		dtFinal.set(GregorianCalendar.DATE, dtFinal.getActualMaximum(GregorianCalendar.DATE));

		// ///////////////////////////////////////////////////////////////////////////////////
		// Calculando o total de gastos.
		Double valorGastoNoPeriodo = new Double(0);
		List<Gasto> gastos = gastoDAO.findByCriteria(Expression.and(Expression.ge(GastoDAO.GASTO_DATA, new Timestamp(dtInicial.getTimeInMillis())), Expression.le(GastoDAO.GASTO_DATA, new Timestamp(dtFinal.getTimeInMillis()))));

		List<Gasto> gastosRet = new ArrayList<Gasto>();
		for (Iterator<Gasto> iterator = gastos.iterator(); iterator.hasNext();) {
			Gasto g = (Gasto) iterator.next();
			Gasto gt = new Gasto();
			gt.setDataTemp(sdf.format(new Date(g.getGastoData().getTime())));
			gt.setGastoMotivo(g.getGastoMotivo());
			gt.setGastoValor(g.getGastoValor());

			Usuario usr = new Usuario();
			usr.setUsuarioNome(g.getUsuario().getUsuarioNome());
			gt.setUsuario(usr);

			gastosRet.add(gt);
			valorGastoNoPeriodo += new Double(g.getGastoValor());
		}

		// ///////////////////////////////////////////////////////////////////////////////////
		Double valorTotalBoletos = new Double(0);// 1
		Double valorTotalBoletosPagos = new Double(0);// 2
		Double valorTotalBoletosPagosNoBanco = new Double(0);// 2
		Double valorTotalBoletosPagosEmMao = new Double(0);// 2

		Double valorPagoBanco = new Double(ConfigUtil.getInstance().getProperty("valor_custo_boleto", "0.0"));

		int qtdBoletos = 0;// 1
		int qtdBoletoVencidoNaoPago = 0;// 1
		int qtdBoletosPagos = 0;// 1
		int qtdBoletosPagosEmMao = 0;// 1
		int qtdClientesPagaramEmDia = 0;// 2
		int qtdClientesPagaramAdiantado = 0;// 2
		int qtdClientesPagaramAtrasado = 0;// 2

		// ///////////////////////////////////////////////////////////////////////////////////
		// boleto pela data de vencimento
		dtInicial.set(Calendar.MONTH, dtInicial.get(Calendar.MONTH) - 1);
		dtFinal.set(Calendar.MONTH, dtFinal.get(Calendar.MONTH) - 1);
		List<BoletosGerados> boletos = boletosGeradosDAO.findByCriteria(Expression.and(Expression.ge(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, new Timestamp(dtInicial.getTimeInMillis())), Expression.le(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, new Timestamp(dtFinal.getTimeInMillis()))));

		for (Iterator<BoletosGerados> iterator = boletos.iterator(); iterator.hasNext();) {
			BoletosGerados bol = (BoletosGerados) iterator.next();

			boolean boletoVencido = false;
			boolean boletoPago = bol.getBoletosgeradosPago();
			boolean boletoPagoEmMao = bol.getBoletosgeradosPagouEmMao();

			valorTotalBoletos += new Double(bol.getBoletosgeradosValor());
			qtdBoletos++;

			if (DateUtils.compararDatas(bol.getBoletosgeradosDataVencimento(), hoje.getTime()) > 0) {
				boletoVencido = true;
			}
			if (boletoVencido && !boletoPago) {
				qtdBoletoVencidoNaoPago++;
			}
			if (boletoPago) {
				qtdBoletosPagos++;
			}
			if (boletoPago && boletoPagoEmMao) {
				qtdBoletosPagosEmMao++;
			}
		}

		// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// boletos pela data de pagamento
		List<BoletosGerados> boletosPelaDataPG = boletosGeradosDAO.findByCriteria(Expression.and(Expression.ge(BoletosGeradosDAO.BOLETOSGERADOS_DATA_PAGAMENTO, new Timestamp(dtInicial.getTimeInMillis())), Expression.le(BoletosGeradosDAO.BOLETOSGERADOS_DATA_PAGAMENTO, new Timestamp(dtFinal.getTimeInMillis()))));

		for (Iterator<BoletosGerados> iterator = boletosPelaDataPG.iterator(); iterator.hasNext();) {
			BoletosGerados bol = (BoletosGerados) iterator.next();

			boolean boletoPago = bol.getBoletosgeradosPago();
			boolean boletoPagoEmMao = bol.getBoletosgeradosPagouEmMao();

			if (boletoPago) {
				valorTotalBoletosPagos += new Double(bol.getBoletosgeradosValorPago());
				if (!boletoPagoEmMao) {
					valorTotalBoletosPagosNoBanco += new Double(bol.getBoletosgeradosValorPago());
				} else {
					valorTotalBoletosPagosEmMao += (new Double(bol.getBoletosgeradosValorPago()));
				}
				// pagaram adiantado
				if (DateUtils.compararDatas(bol.getBoletosgeradosDataPagamento(), bol.getBoletosgeradosDataVencimento()) == 1) {
					qtdClientesPagaramAdiantado++;
					// pagaram em dia
				} else if (DateUtils.compararDatas(bol.getBoletosgeradosDataPagamento(), bol.getBoletosgeradosDataVencimento()) == 0) {
					qtdClientesPagaramEmDia++;
					// pagaram atrasado
				} else if (DateUtils.compararDatas(bol.getBoletosgeradosDataPagamento(), bol.getBoletosgeradosDataVencimento()) == -1) {
					qtdClientesPagaramAtrasado++;
				}
			}
		}

		LucroVO lucroVO = new LucroVO();
		lucroVO.setValorTotalBoletos(valorTotalBoletos);
		lucroVO.setValorTotalBoletosPagos(valorTotalBoletosPagos);
		lucroVO.setValorTotalBoletosPagosNoBanco(valorTotalBoletosPagosNoBanco);
		lucroVO.setValorTotalBoletosPagosEmMao(valorTotalBoletosPagosEmMao);
		lucroVO.setValorGastoNoPeriodo(valorGastoNoPeriodo);
		lucroVO.setValorPagoBanco((qtdBoletosPagos - qtdBoletosPagosEmMao) * valorPagoBanco);
		lucroVO.setValorLucro(valorTotalBoletosPagos - valorGastoNoPeriodo - ((qtdBoletosPagos - qtdBoletosPagosEmMao) * valorPagoBanco));

		lucroVO.setQtdBoletos(qtdBoletos);
		lucroVO.setQtdBoletoVencidoNaoPago(qtdBoletoVencidoNaoPago);
		lucroVO.setQtdBoletosPagos(qtdBoletosPagos);
		lucroVO.setQtdClientesPagaramEmDia(qtdClientesPagaramEmDia);
		lucroVO.setQtdClientesPagaramAtrasado(qtdClientesPagaramAtrasado);
		lucroVO.setQtdClientesPagaramAdiantado(qtdClientesPagaramAdiantado);

		lucroVO.setGastos(gastosRet);

		return lucroVO;
	}

	public Resposta criaBoletoExtraUsuario(String cliente, String valor, String dtBol, String tipoPesquisa, double multa, double desc, double juro, long dias) throws Exception {
		List<Usuario> LsUsuario = usuarioDAO.findByProperty(tipoPesquisa, cliente);
		Usuario usuario = null;
		
		Calendar data = GregorianCalendar.getInstance(new Locale("pt", "br"));
		data.set(Calendar.HOUR_OF_DAY, 0);
		data.set(Calendar.MINUTE, 0);
		data.set(Calendar.SECOND, 0);
		
		data.set(Calendar.YEAR, new Integer(dtBol.split("/")[2]).intValue());
		data.set(Calendar.MONTH, new Integer(dtBol.split("/")[1]).intValue() -1);
		data.set(Calendar.DAY_OF_MONTH, new Integer(dtBol.split("/")[0]).intValue());
		

		if (LsUsuario != null && LsUsuario.size() > 0) {
			usuario = Util.copiaPropriedades(LsUsuario.get(0));
			if (usuario != null) {
				usuario.getPlanosPacotes().setPlanospacotesMulta(multa);
				usuario.getPlanosPacotes().setPlanospacotesJuroMes(juro);
				usuario.getPlanosPacotes().setPlanospacotesDesconto(desc);
				usuario.getPlanosPacotes().setPlanospacotesLimiteDesconto(dias);
				return criaBoletoUsuario(usuario, data, valor);
			} else {
				return new Resposta(true, "Criar boleto", "Usuário não encontrado.");
			}
		} else {
			return new Resposta(true, "Criar boleto", "Usuário não encontrado.");
		}
	}

	private Resposta criaBoletoUsuario(Usuario usuario, Calendar data, String valor) throws Exception {
		BoletosGerados boletosGerados = new BoletosGerados();
		int qtdIntervalo = DateUtils.obterIntervaloDiasComSinal(data.getTime(), new Date(System.currentTimeMillis()));
		BufferLog bl = new BufferLog();
		int qtdDiasEnvioF2B = ConfigUtil.getInstance().getIntProperty("dias_envio_antecipado_boleto", "15");
		if (qtdIntervalo <= qtdDiasEnvioF2B) {
			boletosGerados.setBoletosgeradosDataVencimento(new Timestamp(data.getTimeInMillis()));
			boletosGerados.setBoletosgeradosProcessamento(new Timestamp(System.currentTimeMillis()));
			boletosGerados.setBoletosgeradosValor(valor);
			boletosGerados.setUsuario(usuario);
			boletosGerados.setBoletosgeradosValorCreditoDebito("0.00");
			boletosGerados.setBoletosgeradosMotivoCreditoDebito("");
			boletosGerados.setBoletosgeradosTipoEnvioF2B(ConfigUtil.getInstance().getProperty("boleto_f2b_tipo_registro_emergencia", "e"));
			boletosGerados.setBoletosgeradosBoletoExtra(true);
			try{
				boletosGeradosDAO.attachDirty(boletosGerados);
				enviaBoletos(boletosGerados, bl, ConfigUtil.getInstance().getProperty("boleto_f2b_tipo_registro_emergencia", "e"));
			}catch (Exception e) {
				boletosGeradosDAO.delete(boletosGerados);
				throw new Exception(e);
			}
			return new Resposta(false, "Criar boleto","O boleto foi criado com status de emergência.\nPossivelmente enviado apenas por e-mail ao cliente.\nA entrega deverá ser feita pessoalmente.");
		}else{
			boletosGerados.setBoletosgeradosDataVencimento(new Timestamp(data.getTimeInMillis()));
			boletosGerados.setBoletosgeradosProcessamento(new Timestamp(System.currentTimeMillis()));
			boletosGerados.setBoletosgeradosValor(valor);
			boletosGerados.setUsuario(usuario);
			boletosGerados.setBoletosgeradosValorCreditoDebito("0.00");
			boletosGerados.setBoletosgeradosMotivoCreditoDebito("");
			boletosGerados.setBoletosgeradosTipoEnvioF2B(ConfigUtil.getInstance().getProperty("boleto_f2b_tipo_registro", "e"));
			boletosGerados.setBoletosgeradosBoletoExtra(true);
			try{
				boletosGeradosDAO.attachDirty(boletosGerados);
				enviaBoletos(boletosGerados, bl, ConfigUtil.getInstance().getProperty("boleto_f2b_tipo_registro", "e"));
			}catch (Exception e) {
				boletosGeradosDAO.delete(boletosGerados);
				throw new Exception(e);
			}
			return new Resposta(false, "Criar boleto.", "Boleto criado com sucesso.");
		}
	}

	public void gravaLogArquivoRetorno(String arquivo, String numBolParaLOG) throws GAPBDException {
		List<LogMeganet> lsLog = logMeganetDAO.findByCriteria(Expression.eq(LogMeganetDAO.LOG_ACAO, arquivo));

		if (lsLog != null && lsLog.size() > 0) {
			LogMeganet log = lsLog.get(0);
			log.setLogData(new Timestamp(System.currentTimeMillis()));
			log.setLogDescricao(numBolParaLOG);
			logMeganetDAO.merge(log);
		}
	}

	public boolean oArquivoFoiLido(String arquivo) {
		try {
			LogMeganet log = logMeganetDAO.findByCriteria(Expression.eq(LogMeganetDAO.LOG_ACAO, arquivo)).get(0);
			if (log != null) {
				if (log.getLogDescricao() == null || "".equalsIgnoreCase(log.getLogDescricao())) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public List<LogMeganet> buscaLog(String dataInicial, String dataFinal, String tipo) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "br"));
		Timestamp dtIni = new Timestamp(sdf.parse(dataInicial).getTime());
		Timestamp dtFim = new Timestamp(sdf.parse(dataFinal).getTime());

		List<LogMeganet> ret = logMeganetDAO.findByCriteria(Expression.and(Expression.ge(LogMeganetDAO.LOG_DATA, dtIni), Expression.le(LogMeganetDAO.LOG_DATA, dtFim)), Expression.eq(LogMeganetDAO.LOG_TIPO, tipo));
		return ret;
	}

	public int gravaCusto(String tp, Timestamp dataT, String motivo, String valor, Usuario usr) {
		Gasto gasto = new Gasto();
		gasto.setGastoData(dataT);
		gasto.setGastoMotivo(tp);
		gasto.setGastoValor(valor);
		gasto.setGastoDescricaoMotivo("".equalsIgnoreCase(motivo) ? null : motivo);
		gasto.setUsuario(usr);
		gastoDAO.attachDirty(gasto);
		return 2;
	}

	public List<BoletosGerados> buscaBoletosPagoEmMao(String inicial, String fim) throws Exception {
		Timestamp tsInicial = DateUtils.traduzDataStringToTimeStamp(inicial);
		Timestamp tsFim = DateUtils.traduzDataStringToTimeStamp(fim);

		List<BoletosGerados> boletos = boletosGeradosDAO.findByCriteria(Expression.and(Expression.ge(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, tsInicial), Expression.le(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, tsFim)), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGOU_EM_MAO, true));

		List<BoletosGerados> ret = new ArrayList<BoletosGerados>();
		for (Iterator<BoletosGerados> iterator = boletos.iterator(); iterator.hasNext();) {
			BoletosGerados boletosGerados = (BoletosGerados) iterator.next();

			BoletosGerados bol = new BoletosGerados();
			Usuario usr = new Usuario();

			bol.setBoletosgeradosId(boletosGerados.getBoletosgeradosId());
			bol.setBoletosgeradosDataPagamento(boletosGerados.getBoletosgeradosDataPagamento());
			bol.setBoletosgeradosDataVencimento(boletosGerados.getBoletosgeradosDataVencimento());
			bol.setBoletosgeradosValorPago(boletosGerados.getBoletosgeradosValorPago());
			bol.setBoletosgeradosDinheiroEntregueAdministradorConta(boletosGerados.getBoletosgeradosDinheiroEntregueAdministradorConta());

			if (boletosGerados.getBoletosgeradosDinheiroEntregueAdministradorConta()) {
				bol.setEntregueAdm("Sim");
			} else {
				bol.setEntregueAdm("Não");
			}

			usr.setUsuarioNome(boletosGerados.getUsuario().getUsuarioNome());
			usr.setUsuarioTelefone1(boletosGerados.getUsuario().getUsuarioTelefone1());
			bol.setUsuario(usr);

			ret.add(bol);
		}
		return ret;
	}

	public Long setValorBoletoEntregue(String dtInicial, String dtFinal, Long id) throws Exception {
		BoletosGerados boletosGerados = boletosGeradosDAO.findById(id);
		boletosGerados.setBoletosgeradosDinheiroEntregueAdministradorConta(true);
		boletosGeradosDAO.attachDirty(boletosGerados);
		return id;
	}

	public List<BoletosGerados> localizarBoletos(Long idCliente) throws GAPBDException {
		Usuario usr = new Usuario();
		usr.setUsuarioId(idCliente);
		List<BoletosGerados> ret = boletosGeradosDAO.findByCriteria(Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usr));
		return ret;
	}

	public List<BoletosGerados> localizarBoletos(Long idCliente, int maxResult) throws GAPBDException {
		Usuario usr = new Usuario();
		usr.setUsuarioId(idCliente);
		List<BoletosGerados> ret = boletosGeradosDAO.findByCriteria(maxResult, Order.desc(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usr));
		return ret;
	}

	public List<LogMeganet> buscaUltimosLog() throws GAPBDException {
		List<LogMeganet> ret = logMeganetDAO.findByCriteria(8, Order.desc(LogMeganetDAO.LOG_ID), Expression.eq(LogMeganetDAO.LOG_TIPO, Constantes.LOG_TIPO_RETORNO[0]));
		return ret;
	}

	public boolean enviaBoletoEVerificaSituacaoF2B(BufferLog bl) {
		try {
			boolean enviaBolF2B = ConfigUtil.getInstance().getBooleanProperty("envia_bol_f2b", false);
			boolean situacaoBolF2B = ConfigUtil.getInstance().getBooleanProperty("verifica_situacao_boleto", false);

			if (enviaBolF2B) {
				enviaBoletos(bl);
			}
			if (situacaoBolF2B) {
				boolean situacao = situacaoBoletos(bl);
				if (situacao) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}

		} catch (Exception e) {
			System.out.println(bl.toString());
			e.printStackTrace();
			return false;
		}
	}

	private boolean situacaoBoletos(BufferLog bl) {
		try {
			F2BBoleto f2b = new F2BBoleto();
			bl.append("---------------------------------------------------");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			InfBoleto infBoleto = infBoletoDAO.findById(Constantes.ID_INF_BOLETO);

			Calendar hoje = new GregorianCalendar();
			hoje.setTime(new Date(System.currentTimeMillis()));

			Calendar dataAnterior = new GregorianCalendar();
			Calendar dataPosterior = new GregorianCalendar();

			dataAnterior.set(Calendar.DAY_OF_MONTH, hoje.get(Calendar.DAY_OF_MONTH) - 90);
			dataPosterior.set(Calendar.DAY_OF_MONTH, hoje.get(Calendar.DAY_OF_MONTH) + 30);

			Usuario u = new Usuario();
			u.setPesquisaF2BInicial(sdf.format(dataAnterior.getTime()));
			u.setPesquisaF2BFinal(sdf.format(dataPosterior.getTime()));
			u.setUsuarioId(new Long("1"));

			String ret = f2b.verificaBoleto(u, 3, bl, infBoleto);
			Envelope e = Util.converteRespostaF2B_SituacaoBoleto(ret);

			List<BoletosGerados> bols = new ArrayList<BoletosGerados>();

			for (Iterator<Cobranca> iterator = e.getBody().getRetorno().getCobranca().iterator(); iterator.hasNext();) {
				Cobranca c = (Cobranca) iterator.next();
				if (c.getSituacao().equalsIgnoreCase("Paga")) {
					BoletosGerados b = new BoletosGerados();
					b.setBoletosgeradosId(new Long(c.getNum_document()));
					b.setBoletosgeradosIdF2B(new Long(c.getNumero()));

					Double valorCreditadoNaConta = new Double(c.getValor_pago());
					Double taxa = new Double(c.getTaxa_pagamento());
					Double resultado = (valorCreditadoNaConta - taxa);

					b.setBoletosgeradosValorPago(UtilNumero.formataNumeroCasaDecimal(resultado));
					b.setTaxa_pagamento(c.getTaxa_pagamento());
					b.setBoletosgeradosDataPagamento(new Timestamp(sdf.parse(c.getPagamento()).getTime()));
					bols.add(b);
				}
			}

			bl.append("Atualizando boleto pagos");
			Map<String, String> listaBoletoEnviados = new HashMap<String, String>();
			for (Iterator<BoletosGerados> iterator = bols.iterator(); iterator.hasNext();) {
				BoletosGerados b = (BoletosGerados) iterator.next();
				List<BoletosGerados> bolBD = boletosGeradosDAO.findByCriteria(Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_ID_F2B, b.getBoletosgeradosIdF2B()), Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));

				if (bolBD != null && bolBD.size() > 0) {
					BoletosGerados tmp = bolBD.get(0);
					if (!tmp.getBoletosgeradosPago()) {
						bl.append("--------------------------------------------------------------------------------");
						bl.append("Atualizando boleto numero: " + tmp.getBoletosgeradosId());
						bl.append("Atualizando boleto vencimento: " + tmp.getDataTempVencimento());
						bl.append("Usuario: " + tmp.getUsuario().getUsuarioNome());
						bl.append("--------------------------------------------------------------------------------");
						atualizaPagamentoBoleto(tmp.getBoletosgeradosId(), b.getTaxa_pagamento(), b.getBoletosgeradosValorPago(), b.getBoletosgeradosDataPagamento());

						// roda todos os boletos para colocar em um map a
						// quantidade de boleto por valor.
						String valor = b.getBoletosgeradosValorPago();
						if (listaBoletoEnviados.containsKey(valor)) {
							int qtdBoleto = Integer.parseInt(listaBoletoEnviados.get(valor));
							qtdBoleto++;

							listaBoletoEnviados.remove(valor);
							listaBoletoEnviados.put(valor, qtdBoleto + "");
						} else {
							listaBoletoEnviados.put(valor, "1");
						}

					}
				}
			}
			List<Usuario> usrAdmMon = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_ENVIA_EMAIL_MONETARIO, true), Expression.eq(UsuarioDAO.USUARIO_ADMINISTRATIVO, true));
			if (listaBoletoEnviados.size() > 0) {
				EnviaEmailBO.enviaEmailManipulacaoBoletosPelaF2B(usrAdmMon, listaBoletoEnviados, false);
			}

			bl.append("---------------------------------------------------");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void enviaBoletos(BufferLog bl) throws Exception {

		Calendar hoje = new GregorianCalendar();
		hoje.setTimeInMillis(System.currentTimeMillis());
		Calendar diaPG = new GregorianCalendar();
		diaPG.setTimeInMillis(System.currentTimeMillis());
		diaPG.set(Calendar.DAY_OF_MONTH, hoje.get(Calendar.DAY_OF_MONTH) + ConfigUtil.getInstance().getIntProperty("dias_envio_antecipado_boleto", "15"));

		Calendar vencIni = new GregorianCalendar();
		Calendar vencFim = new GregorianCalendar();

		vencIni.setTime(diaPG.getTime());
		vencFim.setTime(diaPG.getTime());

		vencIni.set(Calendar.DAY_OF_MONTH, diaPG.get(Calendar.DAY_OF_MONTH) - 120);
		vencFim.set(Calendar.DAY_OF_MONTH, diaPG.get(Calendar.DAY_OF_MONTH) + 1);

		List<BoletosGerados> bols = boletosGeradosDAO.findByCriteria(
				Expression.and(
						Expression.ge(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, vencIni.getTime()), 
						Expression.le(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, vencFim.getTime())), 
				Expression.isNull(BoletosGeradosDAO.BOLETOSGERADOS_ID_F2B),
				Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));

		Map<String, String> listaBoletoEnviados = new HashMap<String, String>();
		for (Iterator<BoletosGerados> iterator = bols.iterator(); iterator.hasNext();) {
			BoletosGerados boleto = (BoletosGerados) iterator.next();
			int qtdBoletosAtrasados = buscaQtdBoletoNaoPagoDoCliente(boleto.getUsuario());
			if(qtdBoletosAtrasados < 2){
				Usuario usr = usuarioDAO.findById(boleto.getUsuario().getUsuarioId());
				boleto.setUsuario(usr);
				boleto = enviaBoletos(boleto, bl);
	
				// roda todos os boletos para colocar em um map a quantidade de
				// boleto por valor.
				if (boleto.getBoletosgeradosIdF2B() != null) {
					String valor = boleto.getBoletosgeradosValor();
					if (listaBoletoEnviados.containsKey(valor)) {
						int qtdBoleto = Integer.parseInt(listaBoletoEnviados.get(valor));
						qtdBoleto++;
	
						listaBoletoEnviados.remove(valor);
						listaBoletoEnviados.put(valor, qtdBoleto + "");
					} else {
						listaBoletoEnviados.put(valor, "1");
					}
				}
			}
		}

		List<Usuario> usrAdmMon = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_ENVIA_EMAIL_MONETARIO, true), Expression.eq(UsuarioDAO.USUARIO_ADMINISTRATIVO, true));
		if (listaBoletoEnviados.size() > 0) {
			EnviaEmailBO.enviaEmailManipulacaoBoletosPelaF2B(usrAdmMon, listaBoletoEnviados, true);
		}
	}

	private BoletosGerados enviaBoletos(BoletosGerados boletosGerados, BufferLog bl) throws Exception {
		String tpEnvio = "";
		if(!boletosGerados.getUsuario().getUsuarioImprimeBoleto()){
			tpEnvio = ConfigUtil.getInstance().getProperty("boleto_f2b_tipo_registro_emergencia", "e");
		}else{
			tpEnvio = ConfigUtil.getInstance().getProperty("boleto_f2b_tipo_registro", "n");
		}
		return enviaBoletos(boletosGerados, bl, tpEnvio);
	}
	private BoletosGerados enviaBoletos(BoletosGerados boletosGerados, BufferLog bl, String tipoEnvio) throws Exception {
		F2BBoleto f2b = new F2BBoleto();
		Calendar dataVencBoleto = new GregorianCalendar();
		Calendar dataAtual = new GregorianCalendar();
		dataAtual.setTimeInMillis(System.currentTimeMillis());

		InfBoleto infBoleto = infBoletoDAO.findById(Constantes.ID_INF_BOLETO);

		bl.append("-----------------------------------------------------");
		bl.append("Enviando Boleto número: " + boletosGerados.getBoletosgeradosId());
		bl.append("Enviando Boleto Usuário: " + boletosGerados.getUsuario().getUsuarioNome());

		dataVencBoleto.setTimeInMillis(boletosGerados.getBoletosgeradosDataVencimento().getTime());
		
		boletosGerados.setBoletosgeradosTipoEnvioF2B(tipoEnvio);
		String ret = f2b.criaBoleto(boletosGerados, bl, infBoleto, false);
		Envelope env = Util.converteRespostaF2B_RegistroBoleto(ret);

		if (env.getBody().getRetorno().getLog() != null && env.getBody().getRetorno().getLog().trim().equalsIgnoreCase("OK")) {
			boletosGerados.setBoletosgeradosIdF2B(new Long(env.getBody().getRetorno().getCobranca().get(0).getNumero()));
			boletosGerados.setBoletosgeradosUrlBoletoF2b(env.getBody().getRetorno().getCobranca().get(0).getUrl());

			boletosGerados.setBoletosgeradosMulta(boletosGerados.getUsuario().getPlanosPacotes().getPlanospacotesMulta());
			boletosGerados.setBoletosgeradosJuros(boletosGerados.getUsuario().getPlanosPacotes().getPlanospacotesJuroMes());
			boletosGerados.setBoletosgeradosDesconto(boletosGerados.getUsuario().getPlanosPacotes().getPlanospacotesDesconto());
			boletosGerados.setBoletosgeradosLimiteDesconto(boletosGerados.getUsuario().getPlanosPacotes().getPlanospacotesLimiteDesconto());
			
			boletosGeradosDAO.attachDirty(boletosGerados);
			EnviaEmailBO.enviaEmailBoletoCriadoCliente(boletosGerados, infBoleto);
		} else if (env.getBody().getRetorno().getLog() != null && env.getBody().getRetorno().getLog().indexOf("Saldo insuficiente") > 0) {
			EnviaEmailBO.enviaEmailErroCadastroBoleto(env.getBody().getRetorno().getLog());
			throw new Exception("Saldo insuficiente para enviar o boleto via carta.");
		} else {
			EnviaEmailBO.enviaEmailErroCadastroBoleto("boleto:" + boletosGerados.getBoletosgeradosId() + " usuario: - " + boletosGerados.getUsuario().getUsuarioNome() + " - " + env.getBody().getRetorno().getLog().trim());
			throw new Exception(env.getBody().getRetorno().getLog());
		}

		bl.append("-----------------------------------------------------");
		return boletosGerados;
	}

	public void eliminaIdF2B() {
		try {
			boletosGeradosDAO.eliminaIdF2B();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean aplicaCancelamentoBoleto(BoletosGerados bg){
		try {
			if (bg.getBoletosgeradosIdF2B() != null) {
				F2BBoleto f2b = new F2BBoleto();
				InfBoleto infBoleto = infBoletoDAO.findById(Constantes.ID_INF_BOLETO);
				f2b.alteraCancela(bg, 1, infBoleto);
			}

			bg.setBoletosgeradosPago(true);
			bg.setBoletosgeradosDataPagamento(new Timestamp(DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis())).getTimeInMillis()));
			bg.setBoletosgeradosResticio("0.00");
			bg.setBoletosgeradosPagouEmMao(true);
			bg.setBoletosgeradosDinheiroEntregueAdministradorConta(true);
			bg.setBoletosgeradosValorPago("0.00");

			boletosGeradosDAO.attachDirty(bg);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean cancelarBoleto(Long idBol, Usuario usuarioAbriu) throws Exception {
		BoletosGerados bg = (BoletosGerados) boletosGeradosDAO.findById(idBol);
		
		if(aplicaCancelamentoBoleto(bg)){
			
			Usuario usuario = Util.copiaPropriedades(bg.getUsuario());
			if (!bg.getBoletosgeradosBoletoExtra().booleanValue()) {
				criaSequenciaBoletoUsuario(usuario, 3);
			}
	
			if (usuario.getUsuarioBloqueado().compareTo(Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM) != 0 && usuario.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) != 0) {
				usuario.setUsuarioBloqueado(Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM);
				administracaoFacade.alteraUsuario(usuario);
			}
			
			List<Usuario> usrAdmMon = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_ENVIA_EMAIL_MONETARIO, true), Expression.eq(UsuarioDAO.USUARIO_ADMINISTRATIVO, true));
	
			EnviaEmailBO.enviaEMailUsrAdmCancelamentoBoleto(usrAdmMon, bg, usuarioAbriu);
			EnviaEmailBO.enviaEMailCancelamentoBoletoUsuario(usuario, bg);
	
			return true;
		}else{
			return false;
		}
	}

	public boolean reenviarBoleto(Long idBol) throws Exception {
		BoletosGerados bg = (BoletosGerados) boletosGeradosDAO.findById(idBol);
		F2BBoleto f2b = new F2BBoleto();
		BufferLog bl = new BufferLog();
		Double vlrAdicionar = new Double(ConfigUtil.getInstance().getProperty("valor_reenvio_boleto", "0.00"));
		Double vlrBoleto = new Double(bg.getBoletosgeradosValor());
		if (bg.getBoletosgeradosIdF2B() != null) {
			InfBoleto infBoleto = infBoletoDAO.findById(Constantes.ID_INF_BOLETO);
			f2b.alteraCancela(bg, 1, infBoleto);

			Calendar hoje = new GregorianCalendar();
			hoje.setTimeInMillis(System.currentTimeMillis());

			Calendar dataBoleto = new GregorianCalendar();
			dataBoleto.setTimeInMillis(bg.getBoletosgeradosDataVencimento().getTime());

			Calendar novaData = new GregorianCalendar();
			bg.setBoletosgeradosValor(UtilNumero.formataNumeroCasaDecimal(vlrAdicionar + vlrBoleto));

			int intervalo = DateUtils.obterIntervaloDiasComSinal(dataBoleto.getTime(), hoje.getTime());
			if (intervalo <= (ConfigUtil.getInstance().getIntProperty("dias_envio_antecipado_boleto", "15"))) {
				dataBoleto.set(Calendar.DAY_OF_MONTH, dataBoleto.get(Calendar.DAY_OF_MONTH) + ConfigUtil.getInstance().getIntProperty("dias_envio_antecipado_boleto", "15"));
			}
			novaData.setTime(dataBoleto.getTime());
			
			bg.setBoletosgeradosDataVencimento(new Timestamp(novaData.getTimeInMillis()));

			if(!bg.getUsuario().getUsuarioImprimeBoleto()){
				bg.setBoletosgeradosTipoEnvioF2B(ConfigUtil.getInstance().getProperty("boleto_f2b_tipo_registro_emergencia", "e"));
			}else{
				bg.setBoletosgeradosTipoEnvioF2B(ConfigUtil.getInstance().getProperty("boleto_f2b_tipo_registro", "n"));
			}
			
			String ret = f2b.criaBoleto(bg, bl, infBoleto, false);
			Envelope env = Util.converteRespostaF2B_RegistroBoleto(ret);

			if (env.getBody().getRetorno().getLog() != null && env.getBody().getRetorno().getLog().trim().equalsIgnoreCase("OK")) {
				bg.setBoletosgeradosIdF2B(new Long(env.getBody().getRetorno().getCobranca().get(0).getNumero()));
				bg.setBoletosgeradosUrlBoletoF2b(env.getBody().getRetorno().getCobranca().get(0).getUrl());
				boletosGeradosDAO.attachDirty(bg);
				return true;
			} else {
				EnviaEmailBO.enviaEmailErroCadastroBoleto("boleto:" + bg.getBoletosgeradosId() + " usuario: - " + bg.getUsuario().getUsuarioNome() + " - " + env.getBody().getRetorno().getLog().trim());
				return false;
			}
		} else {
			return false;
		}
	}

	public List<BoletosGerados> buscaPrimeiroBoletoNaoPagoDoCliente(Usuario usuario) throws Exception {
		Calendar dataAtual = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
		Calendar dataMenosCentoEVinte = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
		dataMenosCentoEVinte.set(Calendar.DAY_OF_MONTH, dataMenosCentoEVinte.get(Calendar.DAY_OF_MONTH) - 120);

		List<BoletosGerados> ret = boletosGeradosDAO.findByCriteria(
				1, 
				Order.asc(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO), 
				Expression.and(
						Expression.ge(
								BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, new Timestamp(dataMenosCentoEVinte.getTimeInMillis())), 
								Expression.le(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, new Timestamp(dataAtual.getTimeInMillis()))),
				Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usuario),
				Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));
		return ret;
	}

	public int buscaQtdBoletoNaoPagoDoCliente(Usuario usuario) throws Exception {
		Calendar dataAtual = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
		Calendar dataMenosCentoEVinte = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
		dataMenosCentoEVinte.set(Calendar.DAY_OF_MONTH, dataMenosCentoEVinte.get(Calendar.DAY_OF_MONTH) - 120);
		
		List<BoletosGerados> ret = boletosGeradosDAO.findByCriteria(
				Expression.between(
						BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, 
						new Timestamp(dataMenosCentoEVinte.getTimeInMillis()), 
						new Timestamp(dataAtual.getTimeInMillis())),
				Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO, usuario),
				Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));
		return ret.size();
	}
	
	public InfBoleto buscaDadosInfBoleto() {
		InfBoleto ret = infBoletoDAO.findById(Constantes.ID_INF_BOLETO);
		return Util.copiaPropriedades(ret);
	}

	public boolean gravaInfBoleto(InfBoleto infBoleto) throws GAPBDException {
		infBoleto.setInfboletoId(Constantes.ID_INF_BOLETO);
		infBoletoDAO.attachDirty(infBoleto);
		return true;
	}

	public InfBoleto findInfBoleto() {
		return infBoletoDAO.findById(Constantes.ID_INF_BOLETO);
	}
}
