package br.com.meganet.threads;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.hibernate.criterion.Expression;

import br.com.meganet.bo.BoletoBO;
import br.com.meganet.bo.EnviaEmailBO;
import br.com.meganet.facade.AdministracaoFacade;
import br.com.meganet.hbm.DAO.EnderecoIpDAO;
import br.com.meganet.hbm.DAO.PlanosPacotesDAO;
import br.com.meganet.hbm.DAO.ServidorDAO;
import br.com.meganet.hbm.DAO.UsuarioDAO;
import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.hbm.vo.EnderecoIp;
import br.com.meganet.hbm.vo.PlanosPacotes;
import br.com.meganet.hbm.vo.Servidor;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.BufferLog;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.DateUtils;
import br.com.meganet.util.Logger;

/**
 * @author efren
 */
public class CortarCliente {

	private BoletoBO boletoBO;
	private AdministracaoFacade administracaoFacade;
	private ServidorDAO servidorDAO;
	private EnderecoIpDAO enderecoIpDAO;
	private PlanosPacotesDAO planosPacotesDAO;
	private UsuarioDAO usuarioDAO;
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	public void setServidorDAO(ServidorDAO servidorDAO) {
		this.servidorDAO = servidorDAO;
	}
	public void setPlanosPacotesDAO(PlanosPacotesDAO planosPacotesDAO) {
		this.planosPacotesDAO = planosPacotesDAO;
	}
	public void setEnderecoIpDAO(EnderecoIpDAO enderecoIpDAO) {
		this.enderecoIpDAO = enderecoIpDAO;
	}
	public void setAdministracaoFacade(AdministracaoFacade administracaoFacade) {
		this.administracaoFacade = administracaoFacade;
	}
	public void setBoletoBO(BoletoBO boletoBO){
		this.boletoBO = boletoBO;
	}
	
	private static Logger logger = new Logger(CortarCliente.class);
	
	public void cortaCliente(BufferLog bl) {
		try{
			List<Long> usrs = boletoBO.procuraUsuariosComBoletosNaoPagosPorIntervaloDeTempo();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			bl.append("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			bl.append("------I N I C I O   C O R T E   C L I E N T E------");

			Calendar dataAtual = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));
			for (Iterator<Long> iterator = usrs.iterator(); iterator.hasNext();) {
				String usuarioTMP = "";
				String idTMP = "";
				String idBolTMP = "";
				try{
					Long id = (Long) iterator.next();
					idTMP = id + "";
					List<BoletosGerados> boletoAtrasado = boletoBO.buscaPrimeiroBoletoNaoPagoDoCliente(new Usuario(id));
					if(boletoAtrasado != null && boletoAtrasado.size() >  0){
						BoletosGerados boletosGerados = boletoAtrasado.get(0) ;
						idBolTMP = boletosGerados.getBoletosgeradosId() + "";
						
						Usuario usuario = boletosGerados.getUsuario();
						bl.append("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						bl.append("Verificando cliente " + usuario.getUsuarioNome());
						usuarioTMP = usuario.getUsuarioNome();
						if(usuario.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) != 0){
							
							usuario = usuarioDAO.findById(usuario.getUsuarioId());
							Servidor serv = servidorDAO.findById(usuario.getServidor().getServidorId());
							EnderecoIp enderecoIp = enderecoIpDAO.findById(usuario.getEnderecoIp().getEnderecoipId());
							PlanosPacotes planosPacotes = planosPacotesDAO.findById(usuario.getPlanosPacotes().getPlanospacotesId());
							
							usuario.setServidor(serv);
							usuario.setEnderecoIp(enderecoIp);
							usuario.setPlanosPacotes(planosPacotes);
							
							int qtdDias = DateUtils.obterIntervaloDias(dataAtual.getTime(), boletosGerados.getBoletosgeradosDataVencimento());
							bl.append("Quantidade de dias de inadimplência: " + qtdDias);
							
							Date dataLimiteBloqueioUsuario = usuario.getUsuarioDataLimiteDesbloqueio();
							if(dataLimiteBloqueioUsuario != null){
								bl.append("Data limite de bloqueio do Usuário: " + sdf.format(dataLimiteBloqueioUsuario));
							}
							Date hoje = new Date(System.currentTimeMillis());
							
							boolean entraBloqueio = true;
							if(dataLimiteBloqueioUsuario != null){
								if(DateUtils.aPrimeiraEhMaiorQueSegunda(dataLimiteBloqueioUsuario, hoje)){
									entraBloqueio = false;
								}else{
									usuario.setUsuarioDataLimiteDesbloqueio(null);
								}
							}
							boolean alterouAlgo = false;
							Short situacaoAntiga = usuario.getUsuarioBloqueado();
							Short novaSituacao = new Short("0");
							bl.append("Bloqueia por pagamento atrasado: " + (planosPacotes.getPlanospacotesBloqueiaPgAtrasado() ? "Sim" : "Não"));
							if(entraBloqueio && planosPacotes.getPlanospacotesBloqueiaPgAtrasado()){
								if(qtdDias >= ConfigUtil.getInstance().getIntProperty("tempo_1", "8")){
									alterouAlgo = true;
									bl.append("Setando mensagem de inadimplecia para o usuario " + usuario.getUsuarioNome());
									novaSituacao = Constantes.USR_ATIVO_DESBLOQUEADO_MENSAGEM;
								}
								
								//se falta 2 dias para bloquear um usuário, ai avisa nóis
								if((ConfigUtil.getInstance().getIntProperty("tempo_2", "20") - qtdDias) == 2){
									List<Usuario> usrAdm = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_ADMINISTRATIVO, new Boolean(true)));
									EnviaEmailBO.enviaEMailVaiDesativarUsuario("bloqueado", usuario, boletosGerados, usrAdm);
								}
								
								if(qtdDias >= ConfigUtil.getInstance().getIntProperty("tempo_2", "20")){
									alterouAlgo = true;
									bl.append("Setando bloqueio para o usuario " + usuario.getUsuarioNome());
									novaSituacao = Constantes.USR_ATIVO_BLOQUEADO;
								}
								
								//se falta 2 dias para desativar um usuário, ai avisa nóis
								if((ConfigUtil.getInstance().getIntProperty("tempo_3", "90") - qtdDias) == 2){
									List<Usuario> usrAdm = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_ADMINISTRATIVO, new Boolean(true)));
									EnviaEmailBO.enviaEMailVaiDesativarUsuario("desativado", usuario, boletosGerados, usrAdm);
								}
								
								if(qtdDias >= ConfigUtil.getInstance().getIntProperty("tempo_3", "90")){
									alterouAlgo = true;
									bl.append("Desativando o usuario " + usuario.getUsuarioNome());
									novaSituacao = Constantes.USR_DESATIVADO;
								}
								if(alterouAlgo && situacaoAntiga.compareTo(novaSituacao) != 0
										&& situacaoAntiga.compareTo(Constantes.USR_DESATIVADO) != 0){
									usuario.setUsuarioBloqueado(novaSituacao);
									try{
										administracaoFacade.alteraUsuario(usuario);
									}catch (Exception e) {
										logger.erro("Cortando cliente", e);
									}
								}
								if(qtdDias >= 4){
									try{
										Date dataUltimoEnvioBoleto;
										if(usuario.getUsuarioUltimoEnvioEmail() != null){
											dataUltimoEnvioBoleto = new Date(usuario.getUsuarioUltimoEnvioEmail().getTime());
										}else{
											dataUltimoEnvioBoleto = new Date(System.currentTimeMillis());
										}
										int qtdDiasIntevaloEnvioBoleto = DateUtils.obterIntervaloDias(dataAtual.getTime(), dataUltimoEnvioBoleto);
										int qtdDiasConfigurado = ConfigUtil.getInstance().getIntProperty("intervalo_envio_email_inadimplencia", "5");
										if(qtdDiasIntevaloEnvioBoleto >= qtdDiasConfigurado){
											EnviaEmailBO.enviaEMailInadimplenciaUsuario(usuario, boletosGerados, qtdDias, ConfigUtil.getInstance().getIntProperty("tempo_2", "20"));
											usuario.setUsuarioUltimoEnvioEmail(new Timestamp(System.currentTimeMillis()));
											usuarioDAO.merge(usuario);
										}
									}catch (Exception e) {
										logger.erro("Erro cortando cliente (I)" + usuarioTMP + "("+ idTMP +") - id boleto " + idBolTMP, e);
									}
								}
							}
						}
					}
				}catch (Exception e) {
					logger.erro("Erro cortando cliente (F)" + usuarioTMP + "("+ idTMP +") - id boleto " + idBolTMP, e);
				}
			}
			bl.append("-------F I N A L   C O R T E   C L I E N T E-------");
			bl.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
		}catch (Exception e) {
			logger.erro(e);
		}
	}
}
