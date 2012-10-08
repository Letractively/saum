package br.com.meganet.facade;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.hibernate.PropertyValueException;
import org.hibernate.criterion.Expression;
import org.springframework.dao.DataIntegrityViolationException;
import org.xml.sax.SAXException;

import br.com.meganet.bo.AvisosBO;
import br.com.meganet.bo.BoletoBO;
import br.com.meganet.bo.ContatoBO;
import br.com.meganet.bo.ContratoBO;
import br.com.meganet.bo.DemandasBO;
import br.com.meganet.bo.DominiosBO;
import br.com.meganet.bo.EnderecoIpBO;
import br.com.meganet.bo.EnviaEmailBO;
import br.com.meganet.bo.MenuBO;
import br.com.meganet.bo.PlanosBO;
import br.com.meganet.bo.PoolComandosBO;
import br.com.meganet.bo.StatusClienteBO;
import br.com.meganet.bo.TipoEquipamentoBO;
import br.com.meganet.bo.TorreBO;
import br.com.meganet.bo.UsuarioBO;
import br.com.meganet.exception.GAPBDException;
import br.com.meganet.hbm.DAO.InfServidorDAO;
import br.com.meganet.hbm.DAO.MapaDAO;
import br.com.meganet.hbm.DAO.ServidorDAO;
import br.com.meganet.hbm.vo.Avisos;
import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.hbm.vo.ComboVO;
import br.com.meganet.hbm.vo.Contato;
import br.com.meganet.hbm.vo.Contrato;
import br.com.meganet.hbm.vo.Demandas;
import br.com.meganet.hbm.vo.Dominios;
import br.com.meganet.hbm.vo.EnderecoIp;
import br.com.meganet.hbm.vo.InfBoleto;
import br.com.meganet.hbm.vo.InfServidor;
import br.com.meganet.hbm.vo.LogMeganet;
import br.com.meganet.hbm.vo.Mapa;
import br.com.meganet.hbm.vo.Menu;
import br.com.meganet.hbm.vo.PlanosPacotes;
import br.com.meganet.hbm.vo.PoolComandos;
import br.com.meganet.hbm.vo.Resposta;
import br.com.meganet.hbm.vo.Servidor;
import br.com.meganet.hbm.vo.StatusCliente;
import br.com.meganet.hbm.vo.StatusEquipamento;
import br.com.meganet.hbm.vo.TipoEquipamento;
import br.com.meganet.hbm.vo.Torre;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.socket.SocketRequisicao;
import br.com.meganet.socketParsers.ComandosMonitoramentoSocket;
import br.com.meganet.socketParsers.UsuarioSocket;
import br.com.meganet.threads.SendMailGeral;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.DateUtils;
import br.com.meganet.util.EnumBancos;
import br.com.meganet.util.Logger;
import br.com.meganet.util.Util;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.TableCell;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;

public class AdministracaoFacade {
	
	private Logger log;
	private MenuBO menuBO;
	private TipoEquipamentoBO tipoEquipamentoBO;
	private UsuarioBO usuarioBO;
	private PlanosBO planosBO;
	private DominiosBO dominiosBO;
	private TorreBO torreBO;
	private EnderecoIpBO enderecoIpBO;
	private BoletoBO boletoBO;
	private UsuarioSocket usuarioSocket;
	private ComandosMonitoramentoSocket comandosMonitoramentoSocket;
	private StatusClienteBO statusClienteBO;
	private DemandasBO demandasBO;
	private MapaDAO mapaDAO;
	private ContratoBO contratoBO;
	private ContatoBO contatoBO;
	private InfServidorDAO infServidorDAO;
	private PoolComandosBO poolComandosBO;
	private AvisosBO avisosBO;
	private ServidorDAO servidorDAO;
	
	public void setDominiosBO(DominiosBO dominiosBO) {
		this.dominiosBO = dominiosBO;
	}
	public void setServidorDAO(ServidorDAO servidorDAO) {
		this.servidorDAO = servidorDAO;
	}
	public void setTipoEquipamentoBO(TipoEquipamentoBO tipoEquipamentoBO) {
		this.tipoEquipamentoBO = tipoEquipamentoBO;
	}
	public void setAvisosBO(AvisosBO avisosBO) {
		this.avisosBO = avisosBO;
	}
	public void setPoolComandosBO(PoolComandosBO poolComandosBO) {
		this.poolComandosBO = poolComandosBO;
	}
	public void setLog(Logger log) {
		this.log = log;
	}
	public void setInfServidorDAO(InfServidorDAO infServidorDAO) {
		this.infServidorDAO = infServidorDAO;
	}
	public void setContatoBO(ContatoBO contatoBO) {
		this.contatoBO = contatoBO;
	}
	public void setContratoBO(ContratoBO contratoBO) {
		this.contratoBO = contratoBO;
	}
	public void setMapaDAO(MapaDAO mapaDAO) {
		this.mapaDAO = mapaDAO;
	}
	public void setDemandasBO(DemandasBO demandasBO) {
		this.demandasBO = demandasBO;
	}
	public void setStatusClienteBO(StatusClienteBO statusClienteBO) {
		this.statusClienteBO = statusClienteBO;
	}
	public void setComandosMonitoramentoSocket(ComandosMonitoramentoSocket comandosMonitoramentoSocket) {
		this.comandosMonitoramentoSocket = comandosMonitoramentoSocket;
	}
	public void setUsuarioSocket(UsuarioSocket usuarioSocket) {
		this.usuarioSocket = usuarioSocket;
	}
	public void setBoletoBO(BoletoBO boletoBO) {
		this.boletoBO = boletoBO;
	}
	public void setEnderecoIpBO(EnderecoIpBO enderecoIpBO) {
		this.enderecoIpBO = enderecoIpBO;
	}
	public void setTorreBO(TorreBO torreBO) {
		this.torreBO = torreBO;
	}
	public void setPlanosBO(PlanosBO planosBO) {
		this.planosBO = planosBO;
	}
	public void setUsuarioBO(UsuarioBO usuarioBO) {
		this.usuarioBO = usuarioBO;
	}
	public void setMenuBO(MenuBO menuBO) {
		this.menuBO = menuBO;
	}
	
	public Collection<Menu> buscaMenu(int coluna) {
		Collection<Menu> dadosMenu = menuBO.buscaMenu(coluna);
		return dadosMenu;
	}

	public int alteraMenu(Menu menu) {
		try {
			int retorno = menuBO.alteraMenu(menu);
			return retorno;
		} catch (Exception e) {
			log.erro(e);
			return 0;
		}
	}

	public Collection<Usuario> carregaComboUsuario() {
		return usuarioBO.buscaTodosUsuarios();
	}

	public Collection<PlanosPacotes> carregaComboPlano() {
		return planosBO.carregaComboPlano();
	}

	public Collection<Torre> carregaComboTorre() {
		try {
			return torreBO.carregaComboTorre();
		} catch (Exception e) {
			log.erro("Erro ao buscar torre: ", e.getCause());
			return null;
		}
	}

	public List<Torre> buscaTorresVinculadasAoServidor(Long servidorID) {
		try {
			return torreBO.carregaComboTorre(servidorID);
		} catch (Exception e) {
			log.erro("Erro ao buscar torres para o servidores: " + servidorID.toString(), e.getCause());
			return null;
		}
	}
	
	public Collection<Torre> carregaComboTodasTorre() {
		try {
			return torreBO.carregaComboTodasTorre();
		} catch (Exception e) {
			log.erro("Erro ao buscar torre: ", e.getCause());
			return null;
		}
	}
	
	public Collection<Servidor> carregaComboTodosServidores() {
		try {
			return torreBO.carregaComboTodosServidores();
		} catch (Exception e) {
			log.erro("Erro ao buscar servidores: ", e.getCause());
			return null;
		}
	}
	
	public Collection<Servidor> carregaComboServidoresAtivos() {
		try {
			return torreBO.carregaComboServidoresAtivos();
		} catch (Exception e) {
			log.erro("Erro ao buscar servidores: ", e.getCause());
			return null;
		}
	}
	
	public Collection<EnderecoIp> carregaComboEnderecoIP(Long idServ) {
		try {
			return enderecoIpBO.carregaComboEnderecoIP(idServ);
		} catch (Exception e) {
			log.erro("Erro ao buscar enderecoIp", e.getCause());
			return new ArrayList<EnderecoIp>();
		}
	}

	public Resposta insereUsuario(Usuario usuario){
		try{
			usuarioBO.insereUsuario(usuario);
			Servidor serv = servidorDAO.findById(usuario.getServidor().getServidorId());
			EnderecoIp enderecoIp = enderecoIpBO.findByID(usuario.getEnderecoIp().getEnderecoipId());
			PlanosPacotes planosPacotes = planosBO.procurarPorID(usuario.getPlanosPacotes().getPlanospacotesId());
			usuario.setServidor(serv);
			usuario.setEnderecoIp(enderecoIp);
			usuario.setPlanosPacotes(planosPacotes);
			usuarioSocket.setUsuario(usuario);
			Resposta resp = usuarioSocket.comandoInsere();
			if(resp.getResposta().length() > 1){
				usuarioBO.deletaUsuario(usuario);
				try {
					usuarioSocket.comandoRemove();
				} catch (Exception e1) {
					log.erro("Erro ao adicionar cliente: " + resp.getResposta(), e1.getCause());
				}
			}
			return resp;
		}catch (DataIntegrityViolationException e) {
			log.erro("login/senha ou CPF duplicado", e.getCause());
			return new Resposta(Resposta.ADICIONA_CLIENTE, "Login/senha ou CPF já existente.");
		}catch (PropertyValueException e) {
			log.erro("Alguns campos em branco", e.getCause());
			usuarioBO.deletaUsuario(usuario);
			try {
				usuarioSocket.comandoRemove();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return new Resposta(Resposta.ADICIONA_CLIENTE, "Alguns campos requeridos estão em branco");
		}catch (IOException e) {
			log.erro("Erro de comunicação com o servidor - " + usuario.getServidor().getServidorNome()); 
			usuarioBO.deletaUsuario(usuario);
			try {
				usuarioSocket.comandoRemove();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return new Resposta(Resposta.ADICIONA_CLIENTE, "Não foi possível comunicar com o servidor.");
		}catch (Exception e) {
			log.erro("Exceção desconhecida", e.getCause());
			usuarioBO.deletaUsuario(usuario);
			try {
				usuarioSocket.comandoRemove();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return new Resposta(Resposta.ADICIONA_CLIENTE, "Erro desconhecido\nPossivelmente sem comunicação com o servidor.");
		}
	}
	
	public Resposta insereDadosUsuario(Usuario usuario) {
		try {
			Usuario bd = usuarioBO.insereDadosUsuario(usuario);
			Mapa m = new Mapa();
			m.setMapaTop(usuario.getLat());
			m.setMapaLeft(usuario.getLng());
			m.setUsuario(usuario);
			m.setMapaId(usuario.getUsuarioId());
			mapaDAO.attachDirty(m);
			if (!usuario.getUsuarioAdministrativo()) {
				PlanosPacotes pacotes = planosBO.procurarPorID(usuario.getPlanosPacotes().getPlanospacotesId());
				bd.setPlanosPacotes(pacotes);
				if (pacotes != null && pacotes.getPlanospacotesValor() != 0D) {
					boletoBO.criaMassaBoletoUsuario(bd);
					boletoBO.criaSequenciaBoletoUsuario(bd, 3);
				}
				Servidor serv = servidorDAO.findById(usuario.getServidor().getServidorId());
				bd.setServidor(serv);
				
				Contrato contrato = contratoBO.buscaContrato(usuario.getContrato().getContratoId());
				bd.setContrato(contrato);

				PlanosPacotes planosPacotes = planosBO.procurarPorID(usuario.getPlanosPacotes().getPlanospacotesId());
				bd.setPlanosPacotes(planosPacotes);

				if (bd.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) != 0) {
					EnderecoIp enderecoIp = enderecoIpBO.findByID(usuario.getEnderecoIp().getEnderecoipId());
					bd.setEnderecoIp(enderecoIp);
				}
				

				usuarioSocket.setUsuario(bd);
				return usuarioSocket.comandoAltera();
			} else {
				return new Resposta(Resposta.ADICIONA_CLIENTE, "");
			}
		} catch (DataIntegrityViolationException e) {
			log.erro("login/senha ou CPF duplicado", e.getCause());
			return new Resposta(Resposta.ADICIONA_CLIENTE, "Login/senha ou CPF já existente.");
		} catch (PropertyValueException e) {
			log.erro("Alguns campos em branco", e.getCause());
			return new Resposta(Resposta.ADICIONA_CLIENTE, "Alguns campos requeridos estão em branco");
		} catch (IOException e) {
			log.erro("Exceção desconhecida", e.getCause());
			return new Resposta(Resposta.ADICIONA_CLIENTE, "Erro desconhecido");
		} catch (Exception e) {
			log.erro("Exceção desconhecida", e.getCause());
			return new Resposta(Resposta.ADICIONA_CLIENTE, "Erro desconhecido");
		}
	}

	public Resposta alteraUsuarioCliente(Usuario usuario, HttpServletRequest req) {
		try {
			Usuario usrBD = usuarioBO.buscaUsuario(usuario.getUsuarioId());
			
			usrBD.setUsuarioTelefone1(usuario.getUsuarioTelefone1());
			usrBD.setUsuarioTelefone2(usuario.getUsuarioTelefone2());
			usrBD.setUsuarioEmail(usuario.getUsuarioEmail());
			usrBD.setUsuarioImprimeBoleto(usuario.getUsuarioImprimeBoleto());
			usuarioBO.alteraUsuario(usrBD);
			req.getSession().setAttribute("usuario", usrBD);
			return new Resposta(Resposta.ALTERA_CLIENTE, "");
		} catch (DataIntegrityViolationException e) {
			log.erro("login/senha ou CPF duplicado", e.getCause());
			return new Resposta(Resposta.ALTERA_CLIENTE, "Login/senha ou CPF já existente.");
		} catch (PropertyValueException e) {
			log.erro("Alguns campos em branco", e.getCause());
			return new Resposta(Resposta.ALTERA_CLIENTE, "Alguns campos requeridos estão em branco");
		} catch (Exception e) {
			log.erro("Exceção desconhecida", e);
			return new Resposta(Resposta.ALTERA_CLIENTE, "Erro desconhecido\n" + e.getCause());
		}
	}
	public Resposta alteraUsuario(Usuario usuario) {
		try{
			setaValoresUsuarioAdministrativo(usuario);
			Usuario usrBD = usuarioBO.buscaUsuario(usuario.getUsuarioId());
			
			if(usrBD.getUsuarioMenu().toCharArray().length != usuario.getUsuarioMenu().toCharArray().length ||
					usuario.getUsuarioEnviaEmailMonetario() || usuario.getUsuarioAlteraProprioPerfil() ||
					usuario.getUsuarioAdministrativo()){
				EnviaEmailBO.enviaEmailAlteracaoUsuario(usuario, usrBD);
			}
			
			boolean alteraAlgoServidor = verificaSeAlterouAlgo(usuario, usrBD);

			if(!usuario.getUsuarioAdministrativo()){
				if(boletoBO.mudarDataPagamentoBoleto(usuario)){
					boolean usrSendoAtivado = usrBD.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) == 0 && 
						usuario.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) != 0;
					
					boolean mudancaDePlano = !usuario.getPlanosPacotes().getPlanospacotesId().equals(usrBD.getPlanosPacotes().getPlanospacotesId());
					if(usrSendoAtivado){
						usuario.setVaiInserir(true);
						usuario.setUsuarioDtAtivacao(new Timestamp(System.currentTimeMillis()));
					}
					if(mudancaDePlano){
						usuario.setUsuarioDtAtivacao(new Timestamp(System.currentTimeMillis()));
					}
					if(usrSendoAtivado || mudancaDePlano){
						PlanosPacotes pacotes = planosBO.procurarPorID(usuario.getPlanosPacotes().getPlanospacotesId());
						usuario.setPlanosPacotes(pacotes);
						
						boletoBO.deletaBoletosUsuarioInativo(usuario);
						if (pacotes != null && pacotes.getPlanospacotesValor() != 0D) {
							boletoBO.criaMassaBoletoUsuario(usuario);
							boletoBO.criaSequenciaBoletoUsuario(usuario, 3);
						}
					}
					
					usuario.setUsuarioImprimeBoleto(usrBD.getUsuarioImprimeBoleto());
					usuario.setUsuarioSenha(usrBD.getUsuarioSenha());
					
					if(usuario.getLat() != null && usuario.getLng() != null){
						Mapa m = new Mapa();
						m.setMapaTop(usuario.getLat());
						m.setMapaLeft(usuario.getLng());
						m.setUsuario(usuario);
						m.setMapaId(usuario.getUsuarioId());
						mapaDAO.attachDirty(m);
					}
					
					if(usuario.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) != 0){
						EnderecoIp enderecoIp = enderecoIpBO.findByID(usuario.getEnderecoIp().getEnderecoipId());
						usuario.setEnderecoIp(enderecoIp);
					}else{
						usuario.setEnderecoIp(null);
					}
					
					Servidor serv = servidorDAO.findById(usuario.getServidor().getServidorId());
					PlanosPacotes planosPacotes = planosBO.procurarPorID(usuario.getPlanosPacotes().getPlanospacotesId());
					
					usuario.setPlanosPacotes(planosPacotes);
					usuario.setServidor(serv);
					usuarioSocket.setUsuario(usuario);
					
					Resposta respServidorRemove = new Resposta(Resposta.EXCLUIR_CLIENTE, "");
					Resposta respServidorAltera = new Resposta(Resposta.ALTERA_CLIENTE, "");
					if(alteraAlgoServidor){
						//se tiver trocando de servidor esse primeiro if e chamado.
						if(usrBD.getServidor().getServidorId().compareTo(usuario.getServidor().getServidorId()) != 0){
							usuarioSocket.setUsuario(usrBD);
							respServidorRemove = usuarioSocket.comandoRemove();
							if(respServidorRemove != null && !"".equalsIgnoreCase(respServidorRemove.getResposta())){
								usuarioSocket.setUsuario(usrBD);
								respServidorAltera = usuarioSocket.comandoInsere();
							}else{
								usuarioSocket.setUsuario(usuario);
								respServidorAltera = usuarioSocket.comandoInsere();
							}
						}else{
							//usuario desativado, vai excluir o cidadao
							if(usuario.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) == 0){
								respServidorRemove = usuarioSocket.comandoRemove();
								if(respServidorRemove.getResposta().equalsIgnoreCase("")){
									List<BoletosGerados> boletosSeremCancelados = boletoBO.buscaBoletosAtrasados(usrBD);
									for (BoletosGerados boletosGerados : boletosSeremCancelados) {
										boletoBO.aplicaCancelamentoBoleto(boletosGerados);
									}
								}
							}else{
								if(usuario.getVaiInserir()){
									respServidorAltera = usuarioSocket.comandoInsere();
								}else{
									respServidorAltera = usuarioSocket.comandoAltera();
								}
							}
						}
					}
					if(respServidorAltera != null && !"".equalsIgnoreCase(respServidorAltera.getResposta())){
						usuarioSocket.setUsuario(usrBD);
						respServidorAltera = usuarioSocket.comandoAltera();
						return new Resposta(Resposta.ALTERA_CLIENTE, "Ocorreu um erro ao atualizar o servidor\n" + respServidorAltera.getResposta());
					}else{
						usuarioBO.alteraUsuario(usuario);
						if(usuario.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) == 0){
							boletoBO.deletaBoletosUsuarioInativo(usuario);
						}
						return new Resposta(Resposta.ALTERA_CLIENTE, "");
					}
				}else{
					return new Resposta(Resposta.ALTERA_CLIENTE, "Cliente com mensalidade em atraso\nnão é possível realizar a alteração.");
				}
			}else{
				usuario.setUsuarioSenha(usrBD.getUsuarioSenha());
				usuarioBO.alteraUsuario(usuario);
				return new Resposta(Resposta.ALTERA_CLIENTE, "");
			}
		}catch (DataIntegrityViolationException e) {
			log.erro("login/senha ou CPF duplicado", e.getCause());
			return new Resposta(Resposta.ALTERA_CLIENTE, "E-mail ou CPF já existe.");
		}catch (PropertyValueException e) {
			log.erro("Alguns campos em branco", e.getCause());
			return new Resposta(Resposta.ALTERA_CLIENTE, "Alguns campos requeridos estão em branco");
		}catch (Exception e) {
			log.erro("Exceção desconhecida", e);
			return new Resposta(Resposta.ALTERA_CLIENTE, "Erro desconhecido\n" + e.getMessage());
		}
	}
	
	private boolean verificaSeAlterouAlgo(Usuario usuario, Usuario uBD) {
		try{
			if(usuario.getEnderecoIp().getEnderecoipId().byteValue() != uBD.getEnderecoIp().getEnderecoipId().byteValue()){
				return true;
			}
			if(usuario.getServidor().getServidorId().byteValue() != uBD.getServidor().getServidorId().byteValue()){
				return true;
			}
			if(usuario.getUsuarioBloqueado().compareTo(uBD.getUsuarioBloqueado()) != 0){
				return true;
			}
			if(!usuario.getUsuarioMac().equalsIgnoreCase(uBD.getUsuarioMac())){
				return true;
			}
			if(usuario.getVaiInserir()){
				return true;
			}
			if(usuario.getUsuarioAdministrativo()){
				return false;
			}
		}catch (Exception e) {
			return true;
		}
		return false;
	}
	public Resposta cortaUsuario(Usuario usuario) {
		try{
			if(usuario.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) == 0){
				usuario.setEnderecoIp(null);
			}else{
				EnderecoIp enderecoIp = enderecoIpBO.findByID(usuario.getEnderecoIp().getEnderecoipId());
				usuario.setEnderecoIp(enderecoIp);
			}
			Usuario usrBD = usuarioBO.buscaUsuario(usuario.getUsuarioId());
			if(usrBD.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) == 0 && 
					usuario.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) != 0){
				usuario.setVaiInserir(true);
			}
			usuarioBO.alteraUsuario(usuario);
			Servidor serv = servidorDAO.findById(usuario.getServidor().getServidorId());
			PlanosPacotes planosPacotes = planosBO.procurarPorID(usuario.getPlanosPacotes().getPlanospacotesId());
			usuario.setPlanosPacotes(planosPacotes);
			usuario.setServidor(serv);
			usuarioSocket.setUsuario(usuario);
			return usuarioSocket.comandoAltera();
		}catch (DataIntegrityViolationException e) {
			log.erro("login/senha ou CPF duplicado", e);
			return new Resposta(Resposta.ALTERA_CLIENTE, "Login/senha ou CPF já existente.");
		}catch (PropertyValueException e) {
			log.erro("Alguns campos em branco", e);
			return new Resposta(Resposta.ALTERA_CLIENTE, "Alguns campos requeridos estão em branco");
		}catch (Exception e) {
			log.erro("Exceção desconhecida", e);
			return new Resposta(Resposta.ALTERA_CLIENTE, "Erro desconhecido");
		}
	}

	private void setaValoresUsuarioAdministrativo(Usuario usuario) {
		if(usuario.getUsuarioAdministrativo()){
			usuario.setBoletosGeradoses(null);
			usuario.setDemandases(null);
			usuario.setPlanosPacotes(null);
			usuario.setContrato(null);
			usuario.setServidor(null);
			usuario.setEnderecoIp(null);
			usuario.setUsuarioMac(null);
		}
	}
	
	public List<Usuario> buscaUsuarios() {
		try{
			return usuarioBO.buscaUsuarios();
		}catch (Exception e) {
			log.erro("Erro ao adiquirir usuarios", e);
			return new ArrayList<Usuario>();
		}
	}
	
	public Collection<Usuario> buscaUsuarios(String cliente) {
		try{
			return usuarioBO.buscaUsuarios(cliente);
		}catch (Exception e) {
			log.erro("Erro ao adiquirir usuarios", e);
			return new ArrayList<Usuario>();
		}
	}
	public Collection<Usuario> buscaClientes() {
		try{
			return usuarioBO.buscaClientes();
		}catch (Exception e) {
			log.erro("Erro ao adiquirir usuarios", e);
			return new ArrayList<Usuario>();
		}
	}
	public Collection<Usuario> buscaClientesSemLocalizacao() {
		try{
			return usuarioBO.buscaClientesSemLocalizacao();
		}catch (Exception e) {
			log.erro("Erro ao adiquirir usuarios", e);
			return new ArrayList<Usuario>();
		}
	}
	public Collection<Usuario> buscaClientesComboLocalizacao() {
		try{
			return usuarioBO.buscaClientesComboLocalizacao();
		}catch (Exception e) {
			log.erro("Erro ao adiquirir usuarios", e);
			return new ArrayList<Usuario>();
		}
	}
	public Collection<Usuario> buscaClientesComboLocalizacaoGoogle() {
		try{
			return usuarioBO.buscaClientesComboLocalizacaoGoogle();
		}catch (Exception e) {
			log.erro("Erro ao adiquirir usuarios", e);
			return new ArrayList<Usuario>();
		}
	}
	public List<Mapa> buscaClientesComLocalizacao() {
		try{
			return mapaDAO.findAll();
		}catch (Exception e) {
			log.erro("Erro ao adiquirir usuarios", e);
			return new ArrayList<Mapa>();
		}
	}
	public Usuario buscaUsuario(Long id) {
		return usuarioBO.buscaUsuario(id);
	}
	
	public Usuario buscaUsuarioPeloNome(String cliente) {
		try{
			Usuario u = usuarioBO.buscaUsuarioPeloNome(cliente);
			List<Mapa> m = mapaDAO.findByCriteria(Expression.eq(MapaDAO.USUARIO, u));
			if(m != null && m.size() > 0){
				u.setLat(m.get(0).getMapaTop());
				u.setLng(m.get(0).getMapaLeft());
			}
			return u;
		}catch (Exception e) {
			log.erro("Erro ao adiquirir usuarios", e);
			return null;
		}
	}
	
	public Usuario buscaUsuarioPeloId(Long cliente) {
		try{
			Usuario u = usuarioBO.buscaUsuarioPeloId(cliente);
			List<Mapa> m = mapaDAO.findByCriteria(Expression.eq(MapaDAO.USUARIO, u));
			if(m != null && m.size() > 0){
				u.setLat(m.get(0).getMapaTop());
				u.setLng(m.get(0).getMapaLeft());
			}
			return u;

		}catch (Exception e) {
			log.erro("Erro ao adiquirir usuarios ou usuario inexistente");
			return null;
		}
	}
	
	public Usuario buscaUsuarioPeloCPF(String cliente) {
		try{
			Usuario u = usuarioBO.buscaUsuarioPeloCPF(cliente);
			List<Mapa> m = mapaDAO.findByCriteria(Expression.eq(MapaDAO.MAPA_ID, u.getUsuarioId()));
			if(m != null && m.size() > 0){
				u.setLat(m.get(0).getMapaTop());
				u.setLng(m.get(0).getMapaLeft());
			}
			return u;
		}catch (Exception e) {
			log.erro("Erro ao adiquirir usuarios", e);
			return null;
		}

	}
	
	public Collection<Menu> getMenus() {
		return menuBO.buscaMenu();
	}
	public List<TipoEquipamento> carregaFormularioEqp() {
		return tipoEquipamentoBO.carregaFormularioEqp();
	}
	public PlanosPacotes carregaPlanoParaAlteracao(Long id) {
		PlanosPacotes planosPacotes = Util.copiaPropriedades(planosBO.carregaPlanoParaAlteracao(id));
		return planosPacotes;
	}
	public boolean gravaPlanoPacote(PlanosPacotes planosPacotes) {
		try{
			return planosBO.gravaPlanoPacote(planosPacotes);
		}catch (Exception e) {
			return false;
		}
	}
	public boolean gravaInfBoleto(InfBoleto infBoleto) {
		try{
			return boletoBO.gravaInfBoleto(infBoleto);
		}catch (Exception e) {
			return false;
		}
	}
	public Collection<PlanosPacotes> carregaComboPlanoParaAlteracao() {
		return planosBO.carregaComboPlanoParaAlteracao();
	}
	public Collection<Usuario> carregaComboMAC() {
		try{
			return usuarioBO.carregaComboMAC();
		}catch (GAPBDException e) {
			log.erro("login/senha ou CPF duplicado", e.getCause());
			return null;
		}
	}
	public Usuario buscaUsuario(String mac) {
		try{
			return usuarioBO.buscaUsuario(mac);
		}catch (Exception e) {
			log.erro("Erro ao adiquirir MAC", e);
			return new Usuario();
		}
	}
	public Collection<BoletosGerados> buscaUsuarioCredito(String cliente, String tipo, boolean buscaSemAdiamento) {
		try{
			return boletoBO.buscaUsuarioCredito(cliente, tipo, buscaSemAdiamento);
		}catch (Exception e) {
			log.erro("Erro ao adiquirir boletos para adicionar resticio: usuario:" + cliente, e);
			return null;
		}

	}
	public int salvaCredito(List<BoletosGerados> boletos) {
		try{
			return boletoBO.salvaCredito(boletos);
		}catch (Exception e) {
			log.erro("Erro ao gravar crédito ou débito " + boletos.toString(), e);
			return 3;
		}

	}
	public StatusEquipamento verificaTorre(long idCLiente) {
		try{
			if(ConfigUtil.getInstance().getBooleanProperty("envia_comando_verificar_torre_atual", true)){
				return comandosMonitoramentoSocket.verificaTorre(idCLiente);
			}else{
				return null;
			}
		}catch (Exception e) {
			log.erro("Erro ao verificar torre", e);
			return null;
		}
	}
	public StatusCliente verificaMediaPerfomanceAP(long idCLiente) {
		try{
			return statusClienteBO.verificaMediaPerfomanceAP(idCLiente);
		}catch (Exception e) {
			log.erro("Erro ao recuperar media de perfomance do ap idCLiente: " + idCLiente, e);
			return null;
		}
	}
	public StatusCliente verificaAtualPerfomanceAP(long idCLiente) {
		try{
			StatusCliente cliente = comandosMonitoramentoSocket.verificaAtualPerfomanceAP(idCLiente);
			return cliente;
		}catch (Exception e) {
			log.erro("Erro ao recuperar perfomance atual do ap idCLiente: " + idCLiente, e);
			return null;
		}
	}
	public PlanosPacotes verificarPlanoCliente(long idCLiente) {
		try{
			return planosBO.verificarPlanoCliente(idCLiente);
		}catch (Exception e) {
			log.erro("Erro ao verificar plano do cliente idCLiente: " + idCLiente, e);
			return null;
		}
	}
	
	
	public int gravaDemanda(Demandas dem) {
		return gravaDemanda(dem, true);
	}
	
	public int gravaDemanda(Demandas dem, boolean enviaEmail) {
		try{
			int ret = demandasBO.gravaDemanda(dem);
			if(enviaEmail){
				Usuario usrResp = usuarioBO.buscaUsuario(dem.getUsuarioResponsavel().getUsuarioId());
				Usuario usrSol = usuarioBO.buscaUsuario(dem.getUsuarioSolicitante().getUsuarioId());
				dem.setUsuarioResponsavel(usrResp);
				dem.setUsuarioSolicitante(usrSol);
				EnviaEmailBO.enviaEMailNovaDemanda(dem);
			}
			return ret;
		}catch (Exception e) {
			log.erro(e);
			return 0;
		}
	}
	public List<Demandas> adiquirirDemandas() {
		try{
			return demandasBO.adiquirirDemandas();
		}catch (Exception e) {
			log.erro(e);
			return new ArrayList<Demandas>();
		}
	}
	public Demandas adiquirirDemandaPeloID(Long id) {
		try{
			return demandasBO.adiquirirDemandaPeloID(id);
		}catch (Exception e) {
			log.erro(e);
			return null;
		}
	}
	public List<Contato> adiquirirContatosAbertos() {
		try{
			return contatoBO.adiquirirContatosAbertos();
		}catch (Exception e) {
			log.erro(e);
			return new ArrayList<Contato>();
		}
	}
	public int finalizarDemanda(long idDemanda, String desc, String tpErro, Usuario usuario, int dia, int mes, int ano, int hora, int minuto) {
		try{
			return demandasBO.finalizarDemanda(idDemanda, desc, tpErro, usuario, dia, mes, ano, hora, minuto);
		}catch (Exception e) {
			log.erro(e);
			return 1;
		}
	}
	public int informaPagamentoEmMaoPeloIdBoleto(Long idBoleto, String valor, Usuario usuarioAbriu) {
		try{
			return boletoBO.informaPagamentoEmMaoPeloIdBoleto(idBoleto, valor, usuarioAbriu);
		}catch (Exception e) {
			log.info(e);
			return 0;
		}
	}
	public List<BoletosGerados> buscaRelatorioRendimento(int comboEstado, int comboPesquisa, String inicial, String fim) {
		try{
			Timestamp dataInicial = DateUtils.traduzDataStringToTimeStamp(inicial);
			Timestamp dataFinal = DateUtils.traduzDataStringToTimeStamp(fim);
			List<BoletosGerados> gerados = boletoBO.buscaRelatorioRendimentoS(comboEstado, comboPesquisa, dataInicial, dataFinal);
			return gerados;
		}catch (Exception e) {
			log.erro(e);
			return new ArrayList<BoletosGerados>();
		}
	}
	public List<StatusCliente> buscaRelatorioTrafego(String inicial, String fim, String cliente, int tpCliente) {
		try {
			Timestamp dataInicial = DateUtils.traduzDataStringToTimeStamp(inicial);
			Timestamp dataFinal = DateUtils.traduzDataStringToTimeStamp(fim);
			
				List<StatusCliente> status = statusClienteBO.buscaRelatorioTrafego(cliente, tpCliente, dataInicial, dataFinal);
				return status;
		} catch (Exception e) {
			log.erro(e);
			return new ArrayList<StatusCliente>();
		}

	}
	public List<StatusCliente> buscaRelatorioTrafegoSemCliente(String inicial, String fim) {
		try {
			
			Timestamp dataInicial = DateUtils.traduzDataStringToTimeStamp(inicial);
			Timestamp dataFinal = DateUtils.traduzDataStringToTimeStamp(fim);
			
			List<StatusCliente> status = statusClienteBO.buscaRelatorioTrafegoSemCliente(dataInicial, dataFinal);
			return status;
		} catch (Exception e) {
			log.erro(e);
			return new ArrayList<StatusCliente>();
		}
	}
	public List<Demandas> buscaRelatorioDemandas(String inicial, String fim, String cliente, int tpCliente, int aberto) {
		try {
			Timestamp dataInicial = DateUtils.traduzDataStringToTimeStamp(inicial);
			Timestamp dataFinal = DateUtils.traduzDataStringToTimeStampFinal(fim);
			
			List<Demandas> status = demandasBO.buscaRelatorioDemandas(cliente, tpCliente, dataInicial, dataFinal, aberto);
			return status;
		} catch (Exception e) {
			log.erro(e);
			return new ArrayList<Demandas>();
		}
	}
	public List<Demandas> buscaRelatorioDemandasSemCliente(String inicial, String fim, int aberto) {
		try {
			Timestamp dataInicial = DateUtils.traduzDataStringToTimeStamp(inicial);
			Timestamp dataFinal = DateUtils.traduzDataStringToTimeStampFinal(fim);
			
			List<Demandas> status = demandasBO.buscaRelatorioDemandasSemCliente(dataInicial, dataFinal, aberto);
			return status;
		} catch (Exception e) {
			log.erro(e);
			return new ArrayList<Demandas>();
		}
	}
	public int gravaMapa(Mapa mapa) {
		mapaDAO.attachDirty(mapa);
		return 1;
	}
	public int deletaMapa(Long id) {
		Mapa map = mapaDAO.findById(id);
		mapaDAO.delete(map);
		return 0;
	}
	public Usuario buscaInfCliente(Long idMapa) {
		Mapa map = mapaDAO.findById(idMapa);
		Usuario usuario = usuarioBO.buscaUsuario(map.getUsuario().getUsuarioId());
		return usuario;
	}
	public boolean verificaDataLimiteClientePagamentoAtrasado(Long idCliente) {
		try{
			Usuario usuario = usuarioBO.buscaUsuario(idCliente);
			List<BoletosGerados> boletos = boletoBO.adiquiriRelatorioBoletoUsuario(usuario);
			return boletoBO.temPagamentoAtrasado(boletos);
		} catch (Exception e) {
			log.erro(e);
			return false;
		}

	}
	public Contrato buscaContratoParaUsuario(Usuario usuario) {
		return contratoBO.getContratoPeloCliente(usuario.getUsuarioId());
	}
	public Long localizarMapa(Long idUsuario) {
		try{
		Usuario usr = new Usuario();
		usr.setUsuarioId(idUsuario);
		List<Mapa> map = mapaDAO.findByCriteria(Expression.eq(MapaDAO.USUARIO, usr));
		return map.get(0).getMapaId();
		}catch (Exception e) {
			log.erro(e);
			return 0L;
		}
	}
	public Mapa localizarMapaGoogle(Long idUsuario) {
		try{
			Usuario usr = new Usuario();
			usr.setUsuarioId(idUsuario);
			List<Mapa> map = mapaDAO.findByCriteria(Expression.eq(MapaDAO.USUARIO, usr));
			return map.get(0);
		}catch (Exception e) {
			log.erro(e);
			return null;
		}
	}
	public Resposta criaBoletoExtraUsuario(String cliente, String valor, String data, String tipoPesquisa, double multa, double desc, double juro, long dias) {
		try{
			return boletoBO.criaBoletoExtraUsuario(cliente, valor, data, tipoPesquisa, multa, desc, juro, dias);
		}catch (Exception e) {
			log.erro(e);
			return new Resposta("Criar boleto", e.getMessage());
		}
	}
	public List<LogMeganet> buscaLog(String dataInicial, String dataFinal, String tipo) {
		try{
			return boletoBO.buscaLog(dataInicial, dataFinal, tipo);
		}catch (Exception e) {
			log.erro(e);
			return null;
		}
	}
	public List<LogMeganet> buscaUltimosLog() {
		try{
			return boletoBO.buscaUltimosLog();
		}catch (Exception e) {
			log.erro(e);
			return null;
		}
	}
	public int gravaCusto(String tp, String motivo, String valor, String data, Usuario usr) {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			Timestamp dataT = new Timestamp(sdf.parse(data).getTime());
			return boletoBO.gravaCusto(tp, dataT, motivo, valor, usr);
		}catch (Exception e) {
			log.erro(e);
			return 1;
		}
	}
	public List<Usuario> buscaSituacaoCliente(boolean asm, boolean acm, boolean blo, boolean des) {
		try {
			return usuarioBO.buscaSituacaoCliente(asm, acm, blo, des);
		} catch (Exception e) {
			log.erro(e);
			return new ArrayList<Usuario>();
		}
	}
	public String buscaRelatorioTrafegoInterface(String dataInicial, Long id, int diario) {
		if(diario == 1){
			return buscaRelatorioTrafegoInterfaceDIA(dataInicial, id);
		}else{
			return buscaRelatorioTrafegoInterfaceMES(dataInicial, id);
		}
	}
	
	private String buscaRelatorioTrafegoInterfaceDIA(String dataInicial, Long id) {
		try {
			Calendar dtInicial = GregorianCalendar.getInstance(new Locale("pt","br"));
			Calendar dtFinal = GregorianCalendar.getInstance(new Locale("pt","br"));
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			StringBuffer ret = new StringBuffer();
			
			dtInicial.setTime(sdf.parse(dataInicial));
			dtInicial.set(Calendar.HOUR, 0);
			dtInicial.set(Calendar.MINUTE, 0);
			dtInicial.set(Calendar.SECOND, 0);
			dtInicial.set(Calendar.MILLISECOND, 0);
			
			dtFinal.setTime(sdf.parse(dataInicial));
			dtFinal.set(Calendar.HOUR_OF_DAY, 23);
			dtFinal.set(Calendar.MINUTE, 59);
			dtFinal.set(Calendar.SECOND, 59);
			dtFinal.set(Calendar.MILLISECOND, 0);
			Double down[] = new Double[24];
			Double uplo[] = new Double[24];
			String nomeTorre = "";
			
			Torre torre = new Torre();
			torre.setTorreId(id);
			
			List<InfServidor> lista = infServidorDAO.findByCriteria(
				Expression.and(Expression.ge(InfServidorDAO.INFSERVIDOR_DATA, new Timestamp(dtInicial.getTimeInMillis())),
				Expression.le(InfServidorDAO.INFSERVIDOR_DATA, new Timestamp(dtFinal.getTimeInMillis()))),
				Expression.eq(InfServidorDAO.INFSERVIDOR_TORRE, torre));
			
			for (Iterator<InfServidor> iterator = lista.iterator(); iterator.hasNext();) {
				InfServidor infServidor = (InfServidor) iterator.next();
				nomeTorre = infServidor.getTorre().getTorreNome();
				
				Calendar dataAtual = GregorianCalendar.getInstance(new Locale("pt","br"));
				dataAtual.setTimeInMillis(infServidor.getInfservidorData().getTime());
				int horaAtual = dataAtual.get(Calendar.HOUR_OF_DAY);
				
				if(down[horaAtual] == null){
					down[horaAtual] = infServidor.getInfservidorDownload();
				}else if(down[horaAtual] < infServidor.getInfservidorDownload()){
					down[horaAtual] = infServidor.getInfservidorDownload();
				}
				if(uplo[horaAtual] == null){
					uplo[horaAtual] = infServidor.getInfservidorUpload();
				}else if(uplo[horaAtual] < infServidor.getInfservidorUpload()){
					uplo[horaAtual] = infServidor.getInfservidorUpload();
				}
				
			}			
			ret.append("<chart outCnvBaseFontColor='666666'\n" ); 
			ret.append("caption='Interface "+ nomeTorre +"'\n ");  
			ret.append("xAxisName='Tipo'\n ");
			ret.append("yAxisName='Velocidade' ");
			ret.append("numberPrefix='' "); 
			ret.append("showNames='1' "); 
			ret.append("showValues='0' "); 
			ret.append("plotFillAlpha='50' "); 
			ret.append("numVDivLines='30' "); 
			ret.append("showAlternateVGridColor='1' "); 
			ret.append("AlternateVGridColor='e1f5ff' "); 
			ret.append("divLineColor='FF0000' "); 
			ret.append("vdivLineColor='e1f5ff' ");  
			ret.append("baseFontColor='666666' ");
			ret.append("canvasBorderThickness='1' ");
			ret.append("showPlotBorder='1' "); 
			ret.append("bgColor='CCE3FD' ");
			ret.append("plotBorderThickness='1'><categories>\n");
			
			for (int i = 0; i <= 23; i++) {
				if(i < 10){
					ret.append("<category label='0"+ i +"' />\n");
				}else{
					ret.append("<category label='"+ i +"' />\n");
				}
			}
			
			ret.append("</categories><dataset seriesName='Download' color='B1D1DC' plotBorderColor='B1D1DC'>\n");
			
			for (int i = 0; i < down.length; i++) {
				ret.append("<set value='"+ down[i] +"' />\n");
			}
			
			ret.append("</dataset><dataset seriesName='Upload' color='C8A1D1' plotBorderColor='C8A1D1'>\n");
			
			for (int i = 0; i < uplo.length; i++) {
				ret.append("<set value='"+ uplo[i] +"' />\n");
			}
			
			ret.append("</dataset></chart>");
			return ret.toString();
		} catch (Exception e) {
			log.erro(e);
			return "";
		}
	}
	
	
	
	
	public String buscaRelatorioTrafegoInterfaceMES(String dataInicial, Long id) {
		try {
			Calendar dtInicial = GregorianCalendar.getInstance(new Locale("pt","br"));
			Calendar dtFinal = GregorianCalendar.getInstance(new Locale("pt","br"));
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			StringBuffer ret = new StringBuffer();
			
			dtInicial.setTime(sdf.parse(dataInicial));
			dtInicial.set(Calendar.DAY_OF_MONTH, 1);
			dtFinal.setTime(sdf.parse(dataInicial));
			dtFinal.set(GregorianCalendar.DATE, dtFinal.getActualMaximum(GregorianCalendar.DATE));
			
			Double d01 = new Double(0);
			Double d02 = new Double(0);
			Double d03 = new Double(0);
			Double d04 = new Double(0);
			Double d05 = new Double(0);
			Double d06 = new Double(0);
			Double d07 = new Double(0);
			Double d08 = new Double(0);
			Double d09 = new Double(0);
			Double d10 = new Double(0);
			Double d11 = new Double(0);
			Double d12 = new Double(0);
			Double d13 = new Double(0);
			Double d14 = new Double(0);
			Double d15 = new Double(0);
			Double d16 = new Double(0);
			Double d17 = new Double(0);
			Double d18 = new Double(0);
			Double d19 = new Double(0);
			Double d20 = new Double(0);
			Double d21 = new Double(0);
			Double d22 = new Double(0);
			Double d23 = new Double(0);
			Double d24 = new Double(0);
			Double d25 = new Double(0);
			Double d26 = new Double(0);
			Double d27 = new Double(0);
			Double d28 = new Double(0);
			Double d29 = new Double(0);
			Double d30 = new Double(0);
			Double d31 = new Double(0);
			
			Double u01 = new Double(0);
			Double u02 = new Double(0);
			Double u03 = new Double(0);
			Double u04 = new Double(0);
			Double u05 = new Double(0);
			Double u06 = new Double(0);
			Double u07 = new Double(0);
			Double u08 = new Double(0);
			Double u09 = new Double(0);
			Double u10 = new Double(0);
			Double u11 = new Double(0);
			Double u12 = new Double(0);
			Double u13 = new Double(0);
			Double u14 = new Double(0);
			Double u15 = new Double(0);
			Double u16 = new Double(0);
			Double u17 = new Double(0);
			Double u18 = new Double(0);
			Double u19 = new Double(0);
			Double u20 = new Double(0);
			Double u21 = new Double(0);
			Double u22 = new Double(0);
			Double u23 = new Double(0);
			Double u24 = new Double(0);
			Double u25 = new Double(0);
			Double u26 = new Double(0);
			Double u27 = new Double(0);
			Double u28 = new Double(0);
			Double u29 = new Double(0);
			Double u30 = new Double(0);
			Double u31 = new Double(0);
			
			Torre torre = new Torre();
			torre.setTorreId(id);
			boolean tem31 = false;
			
			List<InfServidor> lista = infServidorDAO.findByCriteria(
				Expression.and(Expression.ge(InfServidorDAO.INFSERVIDOR_DATA, new Timestamp(dtInicial.getTimeInMillis())),
				Expression.le(InfServidorDAO.INFSERVIDOR_DATA, new Timestamp(dtFinal.getTimeInMillis()))),
				Expression.eq(InfServidorDAO.INFSERVIDOR_TORRE, torre));
			String nomeTorre = "";
			boolean ehInterface = false;
			for (Iterator<InfServidor> iterator = lista.iterator(); iterator.hasNext();) {
				InfServidor infServidor = (InfServidor) iterator.next();
				nomeTorre = infServidor.getTorre().getTorreNome();
				if(infServidor.getTorre().getTorreInterfaceCliente()){
					ehInterface = true;
				}
				Calendar dataAtual = GregorianCalendar.getInstance(new Locale("pt","br"));
				dataAtual.setTimeInMillis(infServidor.getInfservidorData().getTime());
				int diaAtual = dataAtual.get(Calendar.DATE);
				switch (diaAtual) {
				case 1:
					if(d01 < infServidor.getInfservidorDownload())
						d01 = infServidor.getInfservidorDownload();
					if(u01 < infServidor.getInfservidorUpload())
						u01 = infServidor.getInfservidorUpload();
					break;
				case 2:
					if(d02 < infServidor.getInfservidorDownload())
						d02 = infServidor.getInfservidorDownload();
					if(u02 < infServidor.getInfservidorUpload())
						u02 = infServidor.getInfservidorUpload();
					break;
				case 3:
					if(d03 < infServidor.getInfservidorDownload())
						d03 = infServidor.getInfservidorDownload();
					if(u03 < infServidor.getInfservidorUpload())
						u03 = infServidor.getInfservidorUpload();
					break;
				case 4:
					if(d04 < infServidor.getInfservidorDownload())
						d04 = infServidor.getInfservidorDownload();
					if(u04 < infServidor.getInfservidorUpload())
						u04 = infServidor.getInfservidorUpload();
					break;
				case 5:
					if(d05 < infServidor.getInfservidorDownload())
						d05 = infServidor.getInfservidorDownload();
					if(u05 < infServidor.getInfservidorUpload())
						u05 = infServidor.getInfservidorUpload();
					break;
				case 6:
					if(d06 < infServidor.getInfservidorDownload())
						d06 = infServidor.getInfservidorDownload();
					if(u06 < infServidor.getInfservidorUpload())
						u06 = infServidor.getInfservidorUpload();
					break;
				case 7:
					if(d07 < infServidor.getInfservidorDownload())
						d07 = infServidor.getInfservidorDownload();
					if(u07 < infServidor.getInfservidorUpload())
						u07 = infServidor.getInfservidorUpload();
					break;
				case 8:
					if(d08 < infServidor.getInfservidorDownload())
						d08 = infServidor.getInfservidorDownload();
					if(u08 < infServidor.getInfservidorUpload())
						u08 = infServidor.getInfservidorUpload();
					break;
				case 9:
					if(d09 < infServidor.getInfservidorDownload())
						d09 = infServidor.getInfservidorDownload();
					if(u09 < infServidor.getInfservidorUpload())
						u09 = infServidor.getInfservidorUpload();
					break;
				case 10:
					if(d10 < infServidor.getInfservidorDownload())
						d10 = infServidor.getInfservidorDownload();
					if(u10 < infServidor.getInfservidorUpload())
						u10 = infServidor.getInfservidorUpload();
					break;
				case 11:
					if(d11 < infServidor.getInfservidorDownload())
						d11 = infServidor.getInfservidorDownload();
					if(u11 < infServidor.getInfservidorUpload())
						u11 = infServidor.getInfservidorUpload();
					break;
				case 12:
					if(d12 < infServidor.getInfservidorDownload())
						d12 = infServidor.getInfservidorDownload();
					if(u12 < infServidor.getInfservidorUpload())
						u12 = infServidor.getInfservidorUpload();
					break;
				case 13:
					if(d13 < infServidor.getInfservidorDownload())
						d13 = infServidor.getInfservidorDownload();
					if(u13 < infServidor.getInfservidorUpload())
						u13 = infServidor.getInfservidorUpload();
					break;
				case 14:
					if(d14 < infServidor.getInfservidorDownload())
						d14 = infServidor.getInfservidorDownload();
					if(u14 < infServidor.getInfservidorUpload())
						u14 = infServidor.getInfservidorUpload();
					break;
				case 15:
					if(d15 < infServidor.getInfservidorDownload())
						d15 = infServidor.getInfservidorDownload();
					if(u15 < infServidor.getInfservidorUpload())
						u15 = infServidor.getInfservidorUpload();
					break;
				case 16:
					if(d16 < infServidor.getInfservidorDownload())
						d16 = infServidor.getInfservidorDownload();
					if(u16 < infServidor.getInfservidorUpload())
						u16 = infServidor.getInfservidorUpload();
					break;
				case 17:
					if(d17 < infServidor.getInfservidorDownload())
						d17 = infServidor.getInfservidorDownload();
					if(u17 < infServidor.getInfservidorUpload())
						u17 = infServidor.getInfservidorUpload();
					break;
				case 18:
					if(d18 < infServidor.getInfservidorDownload())
						d18 = infServidor.getInfservidorDownload();
					if(u18 < infServidor.getInfservidorUpload())
						u18 = infServidor.getInfservidorUpload();
					break;
				case 19:
					if(d19 < infServidor.getInfservidorDownload())
						d19 = infServidor.getInfservidorDownload();
					if(u19 < infServidor.getInfservidorUpload())
						u19 = infServidor.getInfservidorUpload();
					break;
				case 20:
					if(d20 < infServidor.getInfservidorDownload())
						d20 = infServidor.getInfservidorDownload();
					if(u20 < infServidor.getInfservidorUpload())
						u20 = infServidor.getInfservidorUpload();
					break;
				case 21:
					if(d21 < infServidor.getInfservidorDownload())
						d21 = infServidor.getInfservidorDownload();
					if(u21 < infServidor.getInfservidorUpload())
						u21 = infServidor.getInfservidorUpload();
					break;
				case 22:
					if(d22 < infServidor.getInfservidorDownload())
						d22 = infServidor.getInfservidorDownload();
					if(u22 < infServidor.getInfservidorUpload())
						u22 = infServidor.getInfservidorUpload();
					break;
				case 23:
					if(d23 < infServidor.getInfservidorDownload())
						d23 = infServidor.getInfservidorDownload();
					if(u23 < infServidor.getInfservidorUpload())
						u23 = infServidor.getInfservidorUpload();
					break;
				case 24:
					if(d24 < infServidor.getInfservidorDownload())
						d24 = infServidor.getInfservidorDownload();
					if(u24 < infServidor.getInfservidorUpload())
						u24 = infServidor.getInfservidorUpload();
					break;
				case 25:
					if(d25 < infServidor.getInfservidorDownload())
						d25 = infServidor.getInfservidorDownload();
					if(u25 < infServidor.getInfservidorUpload())
						u25 = infServidor.getInfservidorUpload();
					break;
				case 26:
					if(d26 < infServidor.getInfservidorDownload())
						d26 = infServidor.getInfservidorDownload();
					if(u26 < infServidor.getInfservidorUpload())
						u26 = infServidor.getInfservidorUpload();
					break;
				case 27:
					if(d27 < infServidor.getInfservidorDownload())
						d27 = infServidor.getInfservidorDownload();
					if(u27 < infServidor.getInfservidorUpload())
						u27 = infServidor.getInfservidorUpload();
					break;
				case 28:
					if(d28 < infServidor.getInfservidorDownload())
						d28 = infServidor.getInfservidorDownload();
					if(u28 < infServidor.getInfservidorUpload())
						u28 = infServidor.getInfservidorUpload();
					break;
				case 29:
					if(d29 < infServidor.getInfservidorDownload())
						d29 = infServidor.getInfservidorDownload();
					if(u29 < infServidor.getInfservidorUpload())
						u29 = infServidor.getInfservidorUpload();
					break;
				case 30:
					if(d30 < infServidor.getInfservidorDownload())
						d30 = infServidor.getInfservidorDownload();
					if(u30 < infServidor.getInfservidorUpload())
						u30 = infServidor.getInfservidorUpload();
					break;
				case 31:
					tem31 = true;
					if(d31 < infServidor.getInfservidorDownload())
						d31 = infServidor.getInfservidorDownload();
					if(u31 < infServidor.getInfservidorUpload())
						u31 = infServidor.getInfservidorUpload();
					break;
				}
				
			}			
			ret.append("<chart outCnvBaseFontColor='666666'\n" ); 
			ret.append("caption='Interface "+ nomeTorre +"'\n ");  
			ret.append("xAxisName='Tipo'\n ");
			ret.append("yAxisName='Velocidade' ");
			ret.append("numberPrefix='' "); 
			ret.append("showNames='1' "); 
			ret.append("showValues='0' "); 
			ret.append("plotFillAlpha='50' "); 
			ret.append("numVDivLines='30' "); 
			ret.append("showAlternateVGridColor='1' "); 
			ret.append("AlternateVGridColor='e1f5ff' "); 
			ret.append("divLineColor='FF0000' "); 
			ret.append("vdivLineColor='e1f5ff' ");  
			ret.append("baseFontColor='666666' ");
			ret.append("canvasBorderThickness='1' ");
			ret.append("showPlotBorder='1' "); 
			ret.append("bgColor='CCE3FD' ");
			ret.append("plotBorderThickness='1'><categories>\n");
			
			for (int i = 1; i <= dtInicial.getActualMaximum(GregorianCalendar.DATE); i++) {
				if(i < 10){
					ret.append("<category label='0"+ i +"' />\n");
				}else{
					ret.append("<category label='"+ i +"' />\n");
				}
			}
			if(ehInterface){
				ret.append("</categories><dataset seriesName='Upload' color='C8A1D1' plotBorderColor='B1D1DC'>\n");
			}else{
				ret.append("</categories><dataset seriesName='Download' color='B1D1DC' plotBorderColor='B1D1DC'>\n");
			}
			
			ret.append("<set value='"+ d01 +"' />\n");
			ret.append("<set value='"+ d02 +"' />\n");
			ret.append("<set value='"+ d03 +"' />\n");
			ret.append("<set value='"+ d04 +"' />\n");
			ret.append("<set value='"+ d05 +"' />\n");
			ret.append("<set value='"+ d06 +"' />\n");
			ret.append("<set value='"+ d07 +"' />\n");
			ret.append("<set value='"+ d08 +"' />\n");
			ret.append("<set value='"+ d09 +"' />\n");
			ret.append("<set value='"+ d10 +"' />\n");
			ret.append("<set value='"+ d11 +"' />\n");
			ret.append("<set value='"+ d12 +"' />\n");
			ret.append("<set value='"+ d13 +"' />\n");
			ret.append("<set value='"+ d14 +"' />\n");
			ret.append("<set value='"+ d15 +"' />\n");
			ret.append("<set value='"+ d16 +"' />\n");
			ret.append("<set value='"+ d17 +"' />\n");
			ret.append("<set value='"+ d18 +"' />\n");
			ret.append("<set value='"+ d19 +"' />\n");
			ret.append("<set value='"+ d20 +"' />\n");
			ret.append("<set value='"+ d21 +"' />\n");
			ret.append("<set value='"+ d22 +"' />\n");
			ret.append("<set value='"+ d23 +"' />\n");
			ret.append("<set value='"+ d24 +"' />\n");
			ret.append("<set value='"+ d25 +"' />\n");
			ret.append("<set value='"+ d26 +"' />\n");
			ret.append("<set value='"+ d27 +"' />\n");
			ret.append("<set value='"+ d28 +"' />\n");
			ret.append("<set value='"+ d29 +"' />\n");
			ret.append("<set value='"+ d30 +"' />\n");
			if(tem31){
				ret.append("<set value='"+ d31 +"' />\n");
			}
			if(ehInterface){
				ret.append("</dataset><dataset seriesName='Download' color='B1D1DC' plotBorderColor='C8A1D1'>\n");
			}else{
				ret.append("</dataset><dataset seriesName='Upload' color='C8A1D1' plotBorderColor='C8A1D1'>\n");
			}
			
			ret.append("<set value='"+ u01 +"' />\n");
			ret.append("<set value='"+ u02 +"' />\n");
			ret.append("<set value='"+ u03 +"' />\n");
			ret.append("<set value='"+ u04 +"' />\n");
			ret.append("<set value='"+ u05 +"' />\n");
			ret.append("<set value='"+ u06 +"' />\n");
			ret.append("<set value='"+ u07 +"' />\n");
			ret.append("<set value='"+ u08 +"' />\n");
			ret.append("<set value='"+ u09 +"' />\n");
			ret.append("<set value='"+ u10 +"' />\n");
			ret.append("<set value='"+ u11 +"' />\n");
			ret.append("<set value='"+ u12 +"' />\n");
			ret.append("<set value='"+ u13 +"' />\n");
			ret.append("<set value='"+ u14 +"' />\n");
			ret.append("<set value='"+ u15 +"' />\n");
			ret.append("<set value='"+ u16 +"' />\n");
			ret.append("<set value='"+ u17 +"' />\n");
			ret.append("<set value='"+ u18 +"' />\n");
			ret.append("<set value='"+ u19 +"' />\n");
			ret.append("<set value='"+ u20 +"' />\n");
			ret.append("<set value='"+ u21 +"' />\n");
			ret.append("<set value='"+ u22 +"' />\n");
			ret.append("<set value='"+ u23 +"' />\n");
			ret.append("<set value='"+ u24 +"' />\n");
			ret.append("<set value='"+ u25 +"' />\n");
			ret.append("<set value='"+ u26 +"' />\n");
			ret.append("<set value='"+ u27 +"' />\n");
			ret.append("<set value='"+ u28 +"' />\n");
			ret.append("<set value='"+ u29 +"' />\n");
			ret.append("<set value='"+ u30 +"' />\n");
			if(tem31){
				ret.append("<set value='"+ u31 +"' />\n");
			}
			
			ret.append("</dataset></chart>");
			return ret.toString();
		} catch (Exception e) {
			log.erro(e);
			return "";
		}
	}
	public List<Usuario> buscarTodosUsuario() {
		try{
			return usuarioBO.buscaTodosClientes();
		}catch (Exception e) {
			log.erro("Erro ao adiquirir usuarios", e);
			return new ArrayList<Usuario>();
		}
	}
	public List<EnderecoIp> buscaGrupos(Long combo) {
		try{
			return enderecoIpBO.buscaGrupos(combo);
		}catch (Exception e) {
			log.erro("Erro ao adiquirir usuarios", e);
			return new ArrayList<EnderecoIp>();
		}
	}
	public BoletosGerados buscaUsuarioPeloBoleto(String boleto) {
		BoletosGerados boletosGerados = new BoletosGerados();
		try{
			boletosGerados = usuarioBO.buscaUsuarioPeloBoleto(boleto);
			Usuario usuario = boletosGerados.getUsuario();
			Usuario usrTmp = Util.copiaPropriedades(usuario);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			if(usrTmp != null){
				usrTmp.setDataTemp(sdf.format(usuario.getUsuarioDtAtivacao()));
			}
			if(boletosGerados != null){
				boletosGerados.setDataTempVencimento(sdf.format(boletosGerados.getBoletosgeradosDataVencimento()));
				if(boletosGerados.getBoletosgeradosDataPagamento() != null){
					boletosGerados.setDataTempPagamento(sdf.format(boletosGerados.getBoletosgeradosDataPagamento()));
				}
			}
			boletosGerados.setUsuario(usrTmp);
			return boletosGerados;
		}catch (Exception e) {
			log.erro("AdministracaoFacade - buscaUsuarioPeloBoleto\n" + e.getMessage());
			return null;
		}
	}
	public List<BoletosGerados> localizarBoletos(Long idCliente) {
		try{
			List<BoletosGerados> boletosGerados = boletoBO.localizarBoletos(idCliente, 12);
			List<BoletosGerados> ret = new ArrayList<BoletosGerados>();
			for (Iterator<BoletosGerados> iterator = boletosGerados.iterator(); iterator.hasNext();) {
				BoletosGerados boletosGerado = (BoletosGerados) iterator.next();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
				BoletosGerados bg = new BoletosGerados();

				bg.setBoletosgeradosId(boletosGerado.getBoletosgeradosId());
				if(boletosGerado.getBoletosgeradosDataVencimento() != null){
					bg.setDataTempVencimento(sdf.format(boletosGerado.getBoletosgeradosDataVencimento()));
				}else{
					bg.setDataTempVencimento("");
				}
				
				
				InfBoleto infBol = boletoBO.findInfBoleto();
				if(EnumBancos.F2B.getCodigo().equalsIgnoreCase(infBol.getInfboletoBanco().toString())){

					bg.setBoletosgeradosTipoEnvioF2B(boletosGerado.getBoletosgeradosTipoEnvioF2B());
					if(boletosGerado.getBoletosgeradosIdF2B() != null){
						bg.setBoletosgeradosIdF2B(boletosGerado.getBoletosgeradosIdF2B());
					}
					if(boletosGerado.getBoletosgeradosUrlBoletoF2b() != null){
						bg.setBoletosgeradosUrlBoletoF2b(boletosGerado.getBoletosgeradosUrlBoletoF2b());
					}
				
				}else{
					bg.setBoletosgeradosUrlBoletoF2b(ConfigUtil.getInstance().getProperty("url_site","www.google.com.br") + Constantes.URL_BOLETO + boletosGerado.getBoletosgeradosId());
				}
				
				if(boletosGerado.getBoletosgeradosDataPagamento() != null){
					bg.setDataTempPagamento(sdf.format(boletosGerado.getBoletosgeradosDataPagamento()));
				}else{
					bg.setDataTempPagamento("");
				}
				String valorBoleto = ConfigUtil.getInstance().getProperty("valor_reenvio_boleto", "0.00");
				bg.setValorReenvio(valorBoleto);
				bg.setBoletosgeradosPago(boletosGerado.getBoletosgeradosPago());
				bg.setBoletosgeradosPagouEmMao(boletosGerado.getBoletosgeradosPagouEmMao());
				bg.setBoletosgeradosValor(boletosGerado.getBoletosgeradosValor());
				ret.add(bg);
			}
			return ret;
		}catch (Exception e) {
			log.erro("Erro ao adiquirir boletos do cliente: " + idCliente);
			return null;
		}
	}
	public List<Demandas> localizarDemandas(Long idCliente) {
		try{
			Usuario usr = new Usuario();
			usr.setUsuarioId(idCliente);
			List<Demandas> dems = demandasBO.adiquirirTodasDemandas(usr);
			List<Demandas> ret = new ArrayList<Demandas>();
			for (Iterator<Demandas> iterator = dems.iterator(); iterator.hasNext();) {
				Demandas dem = (Demandas) iterator.next();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
				Demandas tmp = new Demandas();
				
				if(dem.getDemandasDataAbertura() != null){
					tmp.setDataAbertura(sdf.format(dem.getDemandasDataAbertura()));
				}else{
					tmp.setDataAbertura("");
				}
				if(dem.getDemandasDataEncerramento() != null){
					tmp.setDataEncerramento(sdf.format(dem.getDemandasDataEncerramento()));
				}else{
					tmp.setDataEncerramento("");
				}
				if(dem.getDemandasDataPrevistaAtendimento() != null){
					tmp.setDataPrevistaAtendimento(sdf.format(dem.getDemandasDataPrevistaAtendimento()));
				}else{
					tmp.setDataPrevistaAtendimento("");
				}
				tmp.setDemandasDescricao(dem.getDemandasDescricao());
				tmp.setDemandasDescricaoEncerramento(dem.getDemandasDescricaoEncerramento());
				ret.add(tmp);
			}
			return ret;
		}catch (Exception e) {
			log.erro("Erro ao adiquirir demandas do cliente: " + idCliente);
			return null;
		}
	}
	
	public Torre buscaTorrePeloID(Long id) {
		Torre torre = torreBO.findByID(id);
		return torre;
	}

	public Servidor buscaServidorPeloID(Long id) {
		Servidor ret = torreBO.findServidorByID(id);
		return ret;
	}
	
	public int salvaTorre(Torre t) {
		try {
			return torreBO.salvaTorre(t);
		} catch (Exception e) {
			log.erro("Erro ao gravar torre", e);
			return 1;
		}
	}
	public int salvaServidor(Servidor t) {
		try {
			return torreBO.salvaServidor(t);
		} catch (Exception e) {
			log.erro("Erro ao gravar Servidor", e);
			return 1;
		}
	}
	public List<EnderecoIp> buscaIP(Long id){
		try {
			return enderecoIpBO.buscaIP(id);
		} catch (Exception e) {
			log.erro("Erro ao buscar IP", e);
			return null;
		}
	}
	public int gravaIP(List<EnderecoIp> ips, Long serv) {
		try {
			return enderecoIpBO.gravaIP(ips, serv);
		} catch (Exception e) {
			log.erro("Erro ao buscar IP", e);
			return 1;
		}
	}
	public List<PoolComandos> buscaComandosDoServidor(Long servID, Long tipo) {
		try {
			Servidor t = new Servidor(servID);
			return poolComandosBO.buscaComando(Constantes.CMD_IDT_0L, t, tipo);
		} catch (Exception e) {
			log.erro("Erro ao buscar comandos para torre: " + servID, e);
			return null;
		}
	}
	public PoolComandos buscaComandoMonitoramentoTorre(Long servID, Long tipo) {
		try {
			Servidor t = new Servidor(servID);
			return poolComandosBO.buscaComandoMonitoramentoTorre(tipo, t, Constantes.CMD_GERAL);
		} catch (Exception e) {
			log.erro("Erro ao buscar comandos para torre: " + servID, e);
			return null;
		}
	}
	public int gravaAlteracaoComando(List<PoolComandos> comandos) {
		try {
			return poolComandosBO.gravaAlteracaoComando(comandos);
		} catch (Exception e) {
			log.erro("Erro ao alterar comando.", e);
			return 1;
		}
	}
	public int gravaAlteracaoComandoMonitoramento(PoolComandos comandos) {
		try {
			return poolComandosBO.gravaAlteracaoComandoMonitoramento(comandos);
		} catch (Exception e) {
			log.erro("Erro ao alterar comando.", e);
			return 1;
		}
	}
	public String verificarMAC(String mac) {
		try {
			Usuario us = usuarioBO.buscaUsuario(mac);
			if(us != null){
				return us.getUsuarioNome();
			}else{
				return null;
			}
		} catch (GAPBDException e) {
			log.erro("Erro ao buscar Usuario pelo mac para inserção de AP", e);
			return null;
		}
	}
	public boolean temAdministrador() {
		try {
			return usuarioBO.temAdministrador();
		} catch (Exception e) {
			System.out.println("Erro ao verificar se existe administrador");
			return false;
		}
	}
	public List<Avisos> carregaAvisos() {
		try {
			List<Avisos> avisos = avisosBO.carregaTodosAvisos();
			List<Avisos> ret = new ArrayList<Avisos>();
			for (Iterator<Avisos> iterator = avisos.iterator(); iterator.hasNext();) {
				Avisos a = (Avisos) iterator.next();
				Avisos tmp = new Avisos();
				
				tmp.setAvisosId(a.getAvisosId());
				tmp.setAvisosTitulo(a.getAvisosTitulo());
				ret.add(tmp);
			}
			return ret;
		} catch (Exception e) {
			log.erro("Erro ao buscar avisos", e);
			return null;
		}
	}
	public Avisos carregaAviso(Long id) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			Avisos aviso = avisosBO.carregaAviso(id);
			aviso.setTmp(sdf.format(aviso.getAvisosDataExpiracao()));
			aviso.setAvisosTitulo(null);
			return aviso;
		} catch (Exception e) {
			log.erro("Erro ao buscar aviso", e);
			return null;
		}
	}
	public boolean salvaAviso(Avisos aviso, List<String> opcoes, int opcEscolhida) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			if(opcEscolhida == 1){
				aviso.setAvisosDataExpiracao(new Timestamp(sdf.parse(aviso.getTmp()).getTime()));
				aviso.setAvisosAtivo(true);
			}else{
				aviso.setAvisosDataExpiracao(new Timestamp(System.currentTimeMillis()));
				aviso.setAvisosAtivo(false);
			}
			aviso = avisosBO.gravaAviso(aviso);
			List<Usuario> usr = new ArrayList<Usuario>();
			
			switch (opcEscolhida) {
			case 1://todos usuarios
				usr = usuarioBO.buscaUsuariosAtivos();
				break;
			case 2://torre
				usr = usuarioBO.buscaUsuariosAtivosPorTorre(new Long(opcoes.get(0)));
				break;
			case 3://clientes
				usr = usuarioBO.buscaUsuariosAtivosPorClientes(opcoes);
				break;
			case 4://data pagamento
				usr = usuarioBO.buscaUsuariosAtivosPorDatapagamento(opcoes.get(0));
				break;
			case 5://por estado
				usr = usuarioBO.buscaUsuariosAtivosPorEstado(opcoes.get(0));
				break;
			}
			new SendMailGeral(aviso, usr).start();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Resposta excluirUsuario(Long usuario) {
		try {
			Usuario bd = usuarioBO.excluirUsuario(usuario);
			usuarioSocket.setUsuario(bd);
			return usuarioSocket.comandoRemove();
		} catch (Exception e) {
			log.erro("Exceção desconhecida", e.getCause());
			return new Resposta(Resposta.EXCLUIR_CLIENTE, "Erro desconhecido");
		}
	}
	public List<Usuario> buscaUsuariosAdministrativos() {
		try{
			return usuarioBO.buscaUsuariosAdministrativos();
		}catch (Exception e) {
			log.erro("Erro ao adiquirir usuarios", e);
			return new ArrayList<Usuario>();
		}
	}
	public Collection<Mapa> buscarMapaPeloServidor(Long idServ) {
		try{
			return mapaDAO.buscarMapaPeloServidor(idServ);
		}catch (Exception e) {
			log.erro("Erro ao adiquirir mapas pela torre: " + idServ, e);
			return null;
		}
	}
	public List<ComboVO> carregaComboTipoErro() {
		try{
			return demandasBO.carregaComboTipoErro();
		}catch (Exception e) {
			log.erro("Erro ao adiquirir usuarios", e);
			return new ArrayList<ComboVO>();
		}
	}
	
	public List<Avisos> detalharAvisos(int opcao) throws GAPBDException {
		if(opcao == 1){
			return avisosBO.carregaClientesInadimplentes();
		}else if(opcao == 2){
			return avisosBO.carregaDemandasAtrasadas();
		}else if(opcao == 3){
			return usuarioBO.buscaUsuariosMensagemAdvertencia();
		}else if(opcao == 4){
			return usuarioBO.buscaUsuariosBloqueados();
		}else{
			return null;
		}
	}
	public String buscaMensagemCliente(Long id) {
		return contatoBO.buscaMensagemCliente(id);
	}
	public int gravaResposta(Long id, String descricao) {
		try {
			Contato c = contatoBO.gravaResposta(id, descricao);
			EnviaEmailBO.enviaRespostaContato(c);
			return 0;
		} catch (Exception e) {
			log.erro("Erro ao gravar resposta", e);
			return 1;
		}
	}
	public List<Contato> buscaRelatorioContato(String dataInicial, String dataFinal, int aberto) {
		try {
			return contatoBO.buscaRelatorioContato(dataInicial, dataFinal, aberto);
		} catch (Exception e) {
			log.erro("Erro ao adiquirir contatos", e);
			return new ArrayList<Contato>();
		}
	}
	public Contato detalharContato(Long id) {
		return contatoBO.detalharContato(id);
	}
	
	public boolean reenviarBoleto(Long idBol) {
		try {
			return boletoBO.reenviarBoleto(idBol);
		} catch (Exception e) {
			log.erro("Erro ao reenviar boleto", e);
			return false;
		}
	}
	public boolean cancelarBoleto(Long idBol, Usuario usuarioAbriu) {
		try {
			return boletoBO.cancelarBoleto(idBol, usuarioAbriu);
		} catch (Exception e) {
			log.erro("Erro ao cancelar boleto", e);
			return false;
		}
	}
	public Usuario buscaCEP(String cep) {
		WebConversation conversation = new WebConversation();
		HttpUnitOptions.setScriptingEnabled( false );		 
		WebForm form = null;
		WebResponse response = null;
		Usuario ret = new Usuario();
		try {
			String urlBase = "http://www.buscacep.correios.com.br";
			String endereco = urlBase + "/servicos/dnec/consultaLogradouroAction.do?CEP="+ cep +"&Metodo=listaLogradouro&TipoConsulta=cep&StartRow=1&EndRow=10";
			
			WebRequest req = new GetMethodWebRequest(endereco);
			response = conversation.getResponse(req);
			
			form = response.getFormWithName("DetalheGeral");
			if(form != null){
				endereco = urlBase + form.getAction() + "?Metodo=detalhe&Posicao=1&TipoCep=2&CEP=" + cep;
				req = new GetMethodWebRequest(endereco);
				response = conversation.getResponse(req);
				
				WebTable[] tabelas = response.getTables();
				if(tabelas != null && tabelas.length > 0){
					for (int i = 0; i < tabelas.length; i++) {
						WebTable tbTMP = tabelas[i];
						Usuario retTmp = traduzLinhasDaTabela(tbTMP);
						if(retTmp != null && retTmp.getUsuarioEndereco() != null){
							ret = retTmp;
							break;
						}
					}
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}
	private Usuario traduzLinhasDaTabela(WebTable tbTMP) throws Exception{
		Usuario ret = new Usuario();
		for (int lin = 0; lin < tbTMP.getRowCount(); lin++) {
			TableCell cel = tbTMP.getTableCell(lin, 0);
			
			String textoCel = cel.getText();
			if(textoCel != null && !"".equalsIgnoreCase(textoCel)){
				
				if(textoCel.toLowerCase().contains("logradouro")){
					cel = tbTMP.getTableCell(lin, 1);
					textoCel = cel.getText();
					ret.setUsuarioEndereco(textoCel);
				}else if (textoCel.toLowerCase().contains("bairro")){
					cel = tbTMP.getTableCell(lin, 1);
					textoCel = cel.getText();
					ret.setUsuarioBairro(textoCel);
				}else if (textoCel.toLowerCase().contains("localidade")){
					cel = tbTMP.getTableCell(lin, 1);
					textoCel = cel.getText();
					ret.setUsuarioCidade(textoCel.split("/")[0].trim());
					ret.setUsuarioEstado(textoCel.split("/")[1].trim());
				}
			}
		}
		return ret;
	}
	public InfBoleto buscaDadosInfBoleto() {
		return boletoBO.buscaDadosInfBoleto();
	}
	public List<Dominios> carregaDominios() {
		try {
			return dominiosBO.carregaDominiosDeConfiguracao();
		} catch (GAPBDException e) {
			log.erro("Erro ao buscar dominios", e);
			return null;
		}
	}
	public String salvaConfAvancadas(List<Dominios> dominios, boolean primeiroAcesso) {
		try {
			return dominiosBO.salvaConfAvancadas(dominios, primeiroAcesso);
		} catch (GAPBDException e) {
			log.erro("Erro ao alterar as configurações avançadas", e);
			return "Erro ao alterar as configurações avançadas";
		}
	}
	public Demandas localizaDemanda(Long id) {
		try {
			return demandasBO.localizaDemanda(id);
		} catch (GAPBDException e) {
			log.erro("Erro ao localizar demanda " + id, e);
			return null;
		}
	}
	public boolean testarConexao(String ip, int porta) {
		return SocketRequisicao.testaConexao(ip, porta);
	}
	public Resposta desbloqueiaUsr(Long id, String dataLimite, String motivo) {
		Usuario usrBD = Util.copiaPropriedades(usuarioBO.buscaUsuario(id));
		if(usrBD.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) != 0){
			usrBD.setUsuarioDataLimiteDesbloqueio(DateUtils.traduzDataStringToTimeStamp(dataLimite));
			usrBD.setMotivoDesbloqueio(motivo);
			usrBD.setUsuarioBloqueado(Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM);
			if(usrBD.getUsuarioDataLimiteDesbloqueio() != null){
	
				WebContext context = WebContextFactory.get();
				HttpServletRequest req = context.getHttpServletRequest();
				Usuario usuarioAbriu = (Usuario) req.getSession().getAttribute("usuario");
				
				Demandas dem = new Demandas();
				dem.setDemandasDataAbertura(new Timestamp(System.currentTimeMillis()));
				dem.setDemandasDescricao(usrBD.getMotivoDesbloqueio());
				dem.setDemandasDataPrevistaAtendimento(usrBD.getUsuarioDataLimiteDesbloqueio());
				dem.setUsuarioAbriu(usuarioAbriu);
				dem.setUsuarioFechou(usuarioAbriu);
				dem.setUsuarioSolicitante(usrBD);
				dem.setDemandasDescricaoEncerramento(Constantes.MENSAGEM_DESBLOQUEIO_TEMPORARIO);
				dem.setDemandasDataEncerramento(usrBD.getUsuarioDataLimiteDesbloqueio());
				gravaDemanda(dem, false);
			}
			if(usrBD.getUsuarioDataLimiteDesbloqueio() != null &&
					DateUtils.aPrimeiraEhMaiorQueSegunda(new Date(System.currentTimeMillis()), new Date(usrBD.getUsuarioDataLimiteDesbloqueio().getTime()))){
				return new Resposta(Resposta.ALTERA_CLIENTE, "Data limite menor que a data atual.");
			}else{
				Resposta retorno = alteraUsuario(usrBD);
				return retorno;
			}
		}else{
			return new Resposta(Resposta.ALTERA_CLIENTE, "Usuário desativado, não é possível desbloquear.");
		}
	}
}
