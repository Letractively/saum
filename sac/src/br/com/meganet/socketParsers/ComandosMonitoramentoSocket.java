package br.com.meganet.socketParsers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.hibernate.criterion.Expression;

import br.com.meganet.bo.EnviaEmailBO;
import br.com.meganet.bo.PoolComandosBO;
import br.com.meganet.exception.GAPBDException;
import br.com.meganet.hbm.DAO.InfServidorDAO;
import br.com.meganet.hbm.DAO.ServidorDAO;
import br.com.meganet.hbm.DAO.TorreDAO;
import br.com.meganet.hbm.DAO.UsuarioDAO;
import br.com.meganet.hbm.vo.InfServidor;
import br.com.meganet.hbm.vo.PoolComandos;
import br.com.meganet.hbm.vo.Servidor;
import br.com.meganet.hbm.vo.StatusCliente;
import br.com.meganet.hbm.vo.StatusEquipamento;
import br.com.meganet.hbm.vo.Torre;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.socket.Comando;
import br.com.meganet.socket.Mensagem;
import br.com.meganet.socket.SocketRequisicao;
import br.com.meganet.util.BufferLog;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.Logger;
import br.com.meganet.util.UtilNumero;

public class ComandosMonitoramentoSocket extends Mensagem {

	private Logger log;
	private PoolComandosBO poolComandosBO;
	private TorreDAO torreDAO;
	private UsuarioDAO usuarioDAO;
	private ServidorDAO servidorDAO;
	private InfServidorDAO infServidorDAO;

	public void setLog(Logger log) {
		this.log = log;
	}
	public void setServidorDAO(ServidorDAO servidorDAO) {
		this.servidorDAO = servidorDAO;
	}
	public void setPoolComandosBO(PoolComandosBO poolComandosBO) {
		this.poolComandosBO = poolComandosBO;
	}
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	public void setInfServidorDAO(InfServidorDAO infServidorDAO) {
		this.infServidorDAO = infServidorDAO;
	}
	public void setTorreDAO(TorreDAO torreDAO) {
		this.torreDAO = torreDAO;
	}

	public List<StatusCliente> buscarNivelClientes() {
		try{
			List<Servidor> servidores = servidorDAO.findByCriteria(Expression.eq(ServidorDAO.SERVIDOR_DESATIVADO, false));
			List<StatusCliente> ret = new ArrayList<StatusCliente>();
			for (Iterator<Servidor> iterator = servidores.iterator(); iterator.hasNext();) {
				Servidor serv = (Servidor) iterator.next();
				List<StatusCliente> tmp = buscarNivelClientes(serv, true);
				for (Iterator<StatusCliente> iterator2 = tmp.iterator(); iterator2.hasNext();) {
					StatusCliente statusCliente = (StatusCliente) iterator2.next();
					ret.add(statusCliente);
				}
			}
			return ret;
		}catch (Exception e) {
			log.erro(e);
			return new ArrayList<StatusCliente>();
		}
	}

	public List<StatusCliente> buscarNivelClientes(Servidor serv, boolean enviaMail) throws Exception {
		List<StatusCliente> ret = new ArrayList<StatusCliente>();
		Comando cmd = new Comando();
		PoolComandos pc = poolComandosBO.buscarComando(Constantes.STATUS_CLIENTE, serv, Constantes.CMD_GERAL);
		String comando = pc.getPoolcomandosComando();
		
		Torre torre = poolComandosBO.buscarTorrePeloServidorEComando(serv.getServidorId(), pc.getServidor().getServidorTorres().split(","));
		
		comando = comando.replace("{1}", torre.getTorreNomeInterface());
		this.setComando(new TreeSet<Comando>());
		
		cmd.setEnderecoPelaTorre(torre);
		cmd.setComando(comando);
		cmd.setIdComando(pc.getPoolcomandosIdentificador());
		this.addComando(cmd);
		String resposta = SocketRequisicao.enviarRequisicao(this);
		String[] arrayConexao = resposta.split(";;;");

		for (int i = 0; i < (arrayConexao.length); i++) {
			if (i > 0) {
				String linhaAtual = arrayConexao[i];
				StatusCliente status = preencheStatus(linhaAtual, enviaMail);
				if (status != null) {
					ret.add(status);
				}
			}
		}
		return ret;
	}

	public void buscarTrafegoInterfaces(Calendar calCria, BufferLog bl) {
		try{
			bl.append(" ");
			List<Servidor> servidores = servidorDAO.findByCriteria(Expression.eq(ServidorDAO.SERVIDOR_DESATIVADO, false));
			for (Iterator<Servidor> iterator = servidores.iterator(); iterator.hasNext();) {
				Servidor serv = (Servidor) iterator.next();
				PoolComandos pc = poolComandosBO.buscarComando(Constantes.MEDE_INTERFACE_REDE, serv, Constantes.CMD_GERAL);
				Torre torre = poolComandosBO.buscarTorrePeloServidorEComando(serv.getServidorId(), pc.getServidor().getServidorTorres().split(","));

				this.setComando(new TreeSet<Comando>());
				String comando = pc.getPoolcomandosComando();
				comando = comando.replace("{1}", torre.getTorreNomeInterface());
				
				Comando cmd = new Comando();
				cmd.setEnderecoPelaTorre(torre);
				cmd.setComando(comando);
				cmd.setIdComando(pc.getPoolcomandosIdentificador());
				this.addComando(cmd);
				String resposta = SocketRequisicao.enviarRequisicao(this);

				if (resposta != null && !"".equalsIgnoreCase(resposta)) {
					try {
						String tmpDown = "";
						String tmpUp = "";
						Double down = new Double("0");
						Double uplo = new Double("0");
						
						if(resposta.indexOf("rx-bits-per-second") > -1){
							tmpDown = resposta.substring(resposta.indexOf("tx-bits-per-second: ") + 19, resposta.indexOf("\n", resposta.indexOf("tx-bits-per-second"))).trim();
							tmpUp = resposta.substring(resposta.indexOf("rx-bits-per-second: ") + 19, resposta.indexOf("\n", resposta.indexOf("rx-bits-per-second: "))).trim();
						}else{
							tmpDown = resposta.substring(resposta.indexOf("sent-bits-per-second: ") + 21, resposta.indexOf("\n", resposta.indexOf("sent-bits-per-second"))).trim();
							tmpUp = resposta.substring(resposta.indexOf("received-bits-per-second") + 25, resposta.indexOf("\n", resposta.indexOf("received-bits-per-second"))).trim();
						}
						
						if(tmpDown.indexOf("Mbps") > -1){
							tmpDown = tmpDown.replace("Mbps", "");
							down = UtilNumero.formataNumero(tmpDown);
							down = UtilNumero.formataNumero(down * 1024);
							
						}else if(tmpDown.indexOf("kbps") > -1){
							tmpDown = tmpDown.replace("kbps", "");
							down = new Double(tmpDown);
							
						}else if(tmpDown.indexOf("bps") > -1 && tmpDown.indexOf("k") == -1 && tmpDown.indexOf("M") == -1){
							tmpDown = tmpDown.replace("bps", "");
							down = new Double(tmpDown);
							down = UtilNumero.formataNumero(down / 1024);
						}
						
						if(tmpUp.indexOf("Mbps") > -1){
							tmpUp = tmpUp.replace("Mbps", "");
							uplo = new Double(tmpUp);
							uplo = UtilNumero.formataNumero(uplo * 1024);
							
						}else if(tmpUp.indexOf("kbps") > -1){
							tmpUp = tmpUp.replace("kbps", "");
							uplo = new Double(tmpUp);
							
						}else if(tmpUp.indexOf("bps") > -1 && tmpUp.indexOf("k") == -1 && tmpUp.indexOf("M") == -1){
							tmpUp = tmpUp.replace("bps", "");
							uplo = new Double(tmpUp);
							uplo = UtilNumero.formataNumero(uplo / 1024);
							
						}
						InfServidor infServidor = new InfServidor();
						infServidor.setInfservidorData(new Timestamp(calCria.getTimeInMillis()));
						infServidor.setInfservidorDownload(down);
						infServidor.setInfservidorUpload(uplo);
						infServidor.setTorre(torre);
						
						bl.append("--------------------------------------------");
						bl.append("trafego por interface. Interface: " + torre.getTorreNome());
						bl.append("download:                         " + infServidor.getInfservidorDownload());
						bl.append("upload:                           " + infServidor.getInfservidorUpload());
						bl.append("Data:                             " + infServidor.getInfservidorData());
						bl.append("--------------------------------------------");
						infServidorDAO.attachDirty(infServidor);

					} catch (Exception e) {
						log.erroSemEmail(e);
					}

				}
			}
		}catch (Exception e) {
			log.erro(e);
		}
	}
	
	
	private StatusCliente preencheStatus(String linhaAtual, boolean enviaMail) {
		try {
			String str = linhaAtual.trim();

			StatusCliente cliente = new StatusCliente();

			Usuario usr = new Usuario();
			String strUsr = str.substring(0, str.indexOf("interface") - 1).trim();
			if(strUsr == null || strUsr.indexOf("=") >= 0){
				return null;
			}
			usr.setUsuarioId(new Long(strUsr));
			cliente.setUsuario(usr);

			cliente.setStatusclienteDatamedicao(new Timestamp(System.currentTimeMillis()));
			if(str.indexOf("rx-rate=\"") > -1){
				cliente.setStatusclienteRxrate(str.substring(str.indexOf("rx-rate=\"") + 9, str.indexOf(" ", str.indexOf("rx-rate=\""))).replaceAll("\"", ""));
			}else{
				cliente.setStatusclienteRxrate("");
			}
			
			if(str.indexOf("tx-rate=\"") > -1){
				cliente.setStatusclienteTxrate(str.substring(str.indexOf("tx-rate=\"") + 9, str.indexOf(" ", str.indexOf("tx-rate=\""))).replaceAll("\"", ""));
			}else{
				cliente.setStatusclienteTxrate("0");
			}
			if(str.indexOf("signal-strength=") > -1){
				String ss = str.substring(str.indexOf("signal-strength=") + 16, str.indexOf("@", str.indexOf("signal-strength=")));
				ss = ss.replaceAll("dBm", "");
				ss = ss.replaceAll("dbm", "");
				cliente.setStatusclienteSignalstrength(ss);
			}else{
				cliente.setStatusclienteSignalstrength("");
			}
			if(str.indexOf("tx-ccq=") > -1){
				cliente.setStatusclienteTxccq(str.substring(str.indexOf("tx-ccq=") + 7, str.indexOf("% ", str.indexOf("tx-ccq="))));
			}else{
				cliente.setStatusclienteTxccq("");
			}
			if(str.indexOf("p-throughput=") > -1){
				cliente.setStatusclienteThroughput(str.substring(str.indexOf("p-throughput=") + 13, str.indexOf(" ", str.indexOf("p-throughput="))));
			}else{
				cliente.setStatusclienteThroughput("0");
			}
			
			if(Integer.parseInt(cliente.getStatusclienteThroughput()) < 500 && Integer.parseInt(cliente.getStatusclienteSignalstrength()) < -70 && enviaMail){
				usr = usuarioDAO.findById(cliente.getUsuario().getUsuarioId());
				List<Usuario> usrAdm = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_ADMINISTRATIVO, new Boolean(true)));
				cliente.setUsuario(usr);
				EnviaEmailBO.enviaEMailClienteProblema(cliente, usrAdm);
			}
			
			return cliente;
		} catch (NumberFormatException e) {
			log.info(linhaAtual, e);
			return null;
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public StatusEquipamento verificaTorre(long idCLiente) throws Exception {
		StatusEquipamento mikrotik = new StatusEquipamento();
		Usuario usuario = usuarioDAO.findById(idCLiente);
		Servidor serv = servidorDAO.findById(usuario.getServidor().getServidorId());

		
		
		PoolComandos pc = poolComandosBO.buscarComando(Constantes.STATUS_TORRE, serv, Constantes.CMD_GERAL);
		Torre torre = poolComandosBO.buscarTorrePeloServidorEComando(serv.getServidorId(), pc.getServidor().getServidorTorres().split(","));
		String comando = pc.getPoolcomandosComando();
		comando = comando.replace("{1}", torre.getTorreNomeInterface());
		
		PoolComandos pc2 = poolComandosBO.buscarComando(Constantes.MEDE_INTERFACE_REDE, serv, Constantes.CMD_GERAL);
		Torre torre2 = poolComandosBO.buscarTorrePeloServidorEComando(serv.getServidorId(), pc2.getServidor().getServidorTorres().split(","));
		String comando2 = pc2.getPoolcomandosComando();
		comando2 = comando2.replace("{1}", torre.getTorreNomeInterface());
		
		PoolComandos pc3 = poolComandosBO.buscarComando(Constantes.COMANDO_QUIT, serv, Constantes.CMD_GERAL);
		Torre torre3 = poolComandosBO.buscarTorrePeloServidorEComando(serv.getServidorId(), pc3.getServidor().getServidorTorres().split(","));
		String comando3 = pc3.getPoolcomandosComando();
		
		Comando cmd = new Comando();
		cmd.setComando(comando);
		cmd.setTorre(torre);
		cmd.setIdComando(pc.getPoolcomandosIdentificador());
		
		Comando cmd2 = new Comando();
		cmd2.setComando(comando2);
		cmd2.setTorre(torre2);
		cmd2.setIdComando(pc2.getPoolcomandosIdentificador());
		
		Comando cmd3 = new Comando();
		cmd3.setComando(comando3);
		cmd3.setTorre(torre3);
		cmd3.setIdComando(pc3.getPoolcomandosIdentificador());
		
		this.setComando(new TreeSet<Comando>());
		this.addComando(cmd);
		this.addComando(cmd2);
		this.addComando(cmd3);
		String resposta = SocketRequisicao.enviarRequisicao(this);
		mikrotik = preencheStatusMikrotik(resposta, torre.getTorreNome());
		
		if(mikrotik.getTxCCQ() != null){
			mikrotik.setTxCCQ(mikrotik.getTxCCQ().replace("%", ""));
		}
		if(mikrotik.getNoiseFloor() != null){
			mikrotik.setNoiseFloor(mikrotik.getNoiseFloor().replace("dBm", ""));
		}
		return mikrotik;
	}

	@Deprecated
	public String realizaBackupTorre(Torre torre) throws IOException {
//		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy-HHmm", new Locale("pt","br"));
//		String nome = sdf.format(new Date(System.currentTimeMillis())) + "-" + torre.getTorreNome();
//		String comando = "/system backup save name=" + nome;
//		this.setComando(new TreeSet<Comando>());
//		this.setEnderecoPelaTorre(torre);
//		this.addComando(comando);
		return null;
	}

	private StatusEquipamento preencheStatusMikrotik(String respTelnet, String torre) {
		String[] r = respTelnet.split("\n");
		StatusEquipamento ret = new StatusEquipamento();
		ret.setNomeTorre(torre);
		
		for (int i = 0; i < r.length; i++) {
			if(r[i].indexOf("authenticated-clients") >= 0){
				ret.setQtdClienteRegistrado(r[i].substring(r[i].indexOf(":") + 1).trim());
			}else if(r[i].indexOf("noise-floor") >= 0){
				ret.setNoiseFloor(r[i].substring(r[i].indexOf(":") + 1).trim());
			}else if(r[i].indexOf("overall-tx-ccq:") >= 0){
				ret.setTxCCQ(r[i].substring(r[i].indexOf(":") + 1).trim());
			}else if(r[i].indexOf("rx-bits-per-second:") >= 0){
				ret.setTrfficRXAtual(r[i].substring(r[i].indexOf(":") + 1).trim());
			}else if(r[i].indexOf("tx-bits-per-second:") >= 0){
				ret.setTrfficTXAtual(r[i].substring(r[i].indexOf(":") + 1).trim());
			}else if(r[i].indexOf("received-bits-per-second:") >= 0){
				ret.setTrfficRXAtual(r[i].substring(r[i].indexOf(":") + 1).trim());
			}else if(r[i].indexOf("sent-bits-per-second:") >= 0){
				ret.setTrfficTXAtual(r[i].substring(r[i].indexOf(":") + 1).trim());
			}
		}
		return ret;
	}

	public StatusCliente verificaAtualPerfomanceAP(long idCLiente) throws Exception {
		Usuario usr = usuarioDAO.findById(idCLiente);
		Servidor serv = usr.getServidor();
		List<StatusCliente> lista = buscarNivelClientes(serv, false);
		StatusCliente ret = new StatusCliente();
		for (Iterator<StatusCliente> iterator = lista.iterator(); iterator.hasNext();) {
			StatusCliente statusCliente = (StatusCliente) iterator.next();
			try{
			if (statusCliente.getUsuario().getUsuarioId() == idCLiente) {
				ret = statusCliente;
				ret.setStatusclienteTxrate(ret.getStatusclienteTxrate());
				ret.setStatusclienteRxrate(ret.getStatusclienteRxrate());
				ret.setStatusclienteTxccq(ret.getStatusclienteTxccq());
				ret.setStatusclienteThroughput(UtilNumero.formataNumeroCasaDecimal(new Double(ret.getStatusclienteThroughput()) / 1000) + "Mbps");
				ret.setStatusclienteSignalstrength(ret.getStatusclienteSignalstrength());
				break;
			}
			}catch (Exception e) {
				log.erro("Erro ao fazer parse do array de sinais de cliente<br>id cliente: " + 
						ret.getUsuario().getUsuarioId() +
						" Nome cliente: " + ret.getUsuario().getUsuarioNome(), e);
			}
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
		ret.setData(sdf.format(new Date(System.currentTimeMillis())));
		ret.setStatusclienteDatamedicao(new Timestamp(System.currentTimeMillis()));
		return ret;
	}
	
	public void monitoraInterfaces(BufferLog bl) {
		try {
			List<Servidor> servs = servidorDAO.findByCriteria(Expression.eq(ServidorDAO.SERVIDOR_DESATIVADO, false));
			for (Iterator<Servidor> iterator = servs.iterator(); iterator.hasNext();) {
				Servidor serv = (Servidor) iterator.next();
				
				PoolComandos pcEther = poolComandosBO.buscarComando(Constantes.ESTADO_ETHERNET, serv, Constantes.CMD_GERAL);
				PoolComandos pcPPPOE = poolComandosBO.buscarComando(Constantes.ESTADO_PPPOE_CLIENT, serv, Constantes.CMD_GERAL);
				
				Torre torreEther = poolComandosBO.buscarTorrePeloServidorEComando(serv.getServidorId(), pcEther.getServidor().getServidorTorres().split(","));
				Torre torrePPPOe = poolComandosBO.buscarTorrePeloServidorEComando(serv.getServidorId(), pcPPPOE.getServidor().getServidorTorres().split(","));
				
				this.setComando(new TreeSet<Comando>());
				
				Comando cmdEther = new Comando();
				cmdEther.setEnderecoPelaTorre(torreEther);
				cmdEther.setComando(pcEther.getPoolcomandosComando());
				cmdEther.setIdComando(pcEther.getPoolcomandosIdentificador());
				cmdEther.setOrdem(pcEther.getPoolcomandosOrdem());
				this.addComando(cmdEther);
				
				Comando cmdPPPOE = new Comando();
				cmdPPPOE.setEnderecoPelaTorre(torrePPPOe);
				cmdPPPOE.setComando(pcPPPOE.getPoolcomandosComando());
				cmdPPPOE.setIdComando(pcPPPOE.getPoolcomandosIdentificador());
				cmdPPPOE.setOrdem(pcPPPOE.getPoolcomandosOrdem());
				this.addComando(cmdPPPOE);
				String resposta = SocketRequisicao.enviarRequisicao(this);
				resposta = resposta.replaceAll("Flags: ", "");
				resposta = resposta.replaceAll("X - disabled, ", "");
				resposta = resposta.replaceAll("R - running, ", "");
				resposta = resposta.replaceAll("S - slave, ", "");
				resposta = resposta.replaceAll("X - disabled ", "");
				resposta = resposta.replaceAll("R - running ", "");
				resposta = resposta.replaceAll("S - slave ", "");
				
				resposta = resposta.replaceAll("X - Disabled, ", "");
				resposta = resposta.replaceAll("R - Running, ", "");
				resposta = resposta.replaceAll("S - Slave, ", "");
				resposta = resposta.replaceAll("X - Disabled ", "");
				resposta = resposta.replaceAll("R - Running ", "");
				resposta = resposta.replaceAll("S - Slave ", "");
				
				String array[] = resposta.split("\n\n");
				for (int i = 0; i < array.length; i++) {
					String linha = array[i];
					if(linha.length() > 15){
						String nomeInterface = linha.substring(linha.indexOf("name=\"") + 6, linha.indexOf("\" ", linha.indexOf("name=\""))).replaceAll("\"", "");
						String estado = linha.substring(0, linha.indexOf("name"));
						bl.append("Interface "+ nomeInterface +" está com status: "+ estado.replaceAll("\n", ""));
						if(!(estado.indexOf("X") > -1) && !(estado.indexOf("R") > -1) && !(nomeInterface.indexOf("bkp") > -1)){
							List<Usuario> usrAdm = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_ADMINISTRATIVO, new Boolean(true)));
							EnviaEmailBO.enviaInterfaceDisconectada(estado, nomeInterface.toUpperCase(), serv, usrAdm);
						}
					}
				}
				
			}
		} catch (Exception e) {
			bl.append(e.getMessage());
		}
	}
	
	public void realizaBackupServidores() {
		try{
			List<Torre> torres = torreDAO.findByCriteria(Expression.eq(TorreDAO.TORRE_DESTAIVADO, false));
	
			for (Iterator<Torre> iterator = torres.iterator(); iterator.hasNext();) {
				Torre torre = (Torre) iterator.next();
	
				FTPClient ftp = new FTPClient();
				try {
					ftp.connect(torre.getTorreIpConexao());
	
					// verifica se conectou com sucesso!
					if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
						ftp.login(torre.getTorreUsuario(), torre.getTorreSenha());
					} else {
						ftp.disconnect();
					}
					ftp.changeWorkingDirectory("/");
	
					FTPFile[] ff = ftp.listFiles();
					for (int i = 0; i < ff.length; i++) {
						FTPFile atual = ff[i];
						if(atual.isFile() && atual.getName().indexOf("backup") > -1){
							String nomeArq = atual.getName();
							FileOutputStream fos = new FileOutputStream(ConfigUtil.getInstance().getProperty("diretorio_bkp","c:\\backupservidor\\") + nomeArq);
							ftp.retrieveFile(nomeArq, fos);
							ftp.deleteFile(nomeArq);
							fos.close();
						}
					}
				} catch (Exception e) {
					log.erro(e);
				} finally {
					try {
						ftp.disconnect();
					} catch (IOException e) {
						log.info(e);
					}
				}
			}
		}catch (GAPBDException e2) {
			log.erro("Erro no backup do servidor", e2);
		}
	}
	
	public static void main(String args[]) {
		ComandosMonitoramentoSocket cms = new ComandosMonitoramentoSocket();
		cms.realizaBackupServidores();
	}
}
