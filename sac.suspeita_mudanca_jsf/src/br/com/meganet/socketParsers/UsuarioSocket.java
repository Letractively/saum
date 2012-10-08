package br.com.meganet.socketParsers;

import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import br.com.meganet.bo.PoolComandosBO;
import br.com.meganet.hbm.vo.PoolComandos;
import br.com.meganet.hbm.vo.Resposta;
import br.com.meganet.hbm.vo.Torre;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.socket.Comando;
import br.com.meganet.socket.Mensagem;
import br.com.meganet.socket.SocketRequisicao;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.Criptografia;

public class UsuarioSocket extends Mensagem {

	private PoolComandosBO poolComandosBO;
	private Usuario usuario;

	public void setPoolComandosBO(PoolComandosBO poolComandosBO) {
		this.poolComandosBO = poolComandosBO;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Resposta comandoAltera() throws Exception{
		this.setComando(new TreeSet<Comando>());
		List<PoolComandos> pc = poolComandosBO.buscaComando(Constantes.CMD_IDT_0L, usuario.getServidor(), Constantes.CMD_ALT);
		
		for (Iterator<PoolComandos> iterator = pc.iterator(); iterator.hasNext();) {
			PoolComandos poolComandos = (PoolComandos) iterator.next();
			String comando = poolComandos.getPoolcomandosComando();		
			Torre torre = poolComandosBO.buscarTorrePeloServidorEComando(usuario.getServidor().getServidorId(), poolComandos.getServidor().getServidorTorres().split(","));
			comando = substituiComandos(comando, torre);
			Comando cmd = new Comando();
			cmd.setComando(comando);
			cmd.setIdComando(poolComandos.getPoolcomandosIdentificador());
			cmd.setOrdem(poolComandos.getPoolcomandosOrdem());
			
			cmd.setEnderecoPelaTorre(torre);
			this.addComando(cmd);
		}

		String resp = "";
		if(ConfigUtil.getInstance().getBooleanProperty("envia_comando_usuario",true)){
			resp = SocketRequisicao.enviarRequisicao(this);
		}
		
		Resposta respVO = new Resposta();
		respVO.setAcao(Resposta.ALTERA_CLIENTE);
		respVO.setResposta(resp);
		return respVO;
	}

	public Resposta comandoInsere() throws Exception{
		this.setComando(new TreeSet<Comando>());
		List<PoolComandos> pc = poolComandosBO.buscaComando(Constantes.CMD_IDT_0L, usuario.getServidor(), Constantes.CMD_ADD);
		
		for (Iterator<PoolComandos> iterator = pc.iterator(); iterator.hasNext();) {
			PoolComandos poolComandos = (PoolComandos) iterator.next();
			String comando = poolComandos.getPoolcomandosComando();		
			Torre torre = poolComandosBO.buscarTorrePeloServidorEComando(usuario.getServidor().getServidorId(), poolComandos.getServidor().getServidorTorres().split(","));
			comando = substituiComandos(comando, torre);
			Comando cmd = new Comando();
			cmd.setComando(comando);
			cmd.setIdComando(poolComandos.getPoolcomandosIdentificador());
			cmd.setOrdem(poolComandos.getPoolcomandosOrdem());
			
			cmd.setEnderecoPelaTorre(torre);

			this.addComando(cmd);
		}
		
		String resp = "";
		if(ConfigUtil.getInstance().getBooleanProperty("envia_comando_usuario",true)){
			resp = SocketRequisicao.enviarRequisicao(this);
		}
		Resposta respVO = new Resposta();
		respVO.setAcao(Resposta.ADICIONA_CLIENTE);
		respVO.setResposta(resp);
		return respVO;
	}
	
	public Resposta comandoRemove() throws Exception{
		this.setComando(new TreeSet<Comando>());
		List<PoolComandos> pc = poolComandosBO.buscaComando(Constantes.CMD_IDT_0L, usuario.getServidor(), Constantes.CMD_DEL);
		
		for (Iterator<PoolComandos> iterator = pc.iterator(); iterator.hasNext();) {
			PoolComandos poolComandos = (PoolComandos) iterator.next();
			String comando = poolComandos.getPoolcomandosComando();		
			Torre torre = poolComandosBO.buscarTorrePeloServidorEComando(usuario.getServidor().getServidorId(), poolComandos.getServidor().getServidorTorres().split(","));
			comando = substituiComandos(comando, torre);
			Comando cmd = new Comando();
			cmd.setComando(comando);
			cmd.setIdComando(poolComandos.getPoolcomandosIdentificador());
			cmd.setOrdem(poolComandos.getPoolcomandosOrdem());
			
			cmd.setEnderecoPelaTorre(torre);

			this.addComando(cmd);
		}
		
		String resp = "";
		if(ConfigUtil.getInstance().getBooleanProperty("envia_comando_usuario",true)){
			resp = SocketRequisicao.enviarRequisicao(this);
		}
		Resposta respVO = new Resposta();
		respVO.setAcao(Resposta.EXCLUIR_CLIENTE);
		respVO.setResposta(resp);
		return respVO;
	}
	
	private String calculaLimite(Integer vel) {
		Double upload = new Double(vel / 4);
		int up = upload.intValue();
		return up + "000/" + vel + "000";
	}

	private String calculaLimiteBurst(Integer vel) {
		Double upload = new Double(vel / 4);
		int up = upload.intValue();
		return (up * 2) + "000/" + (vel * 2) + "000";
	}
	
	private String calculaLimiteBurstThreeshold(Integer vel) {
		Double upload = new Double(vel / 4) * 2;
		int up = (upload.intValue() * 4) / 8;
		vel = (vel * 3) / 8;
		return (up) + "000/" + (vel) + "000";
	}
	
	private String getUsuarioAtivo() {
		String ret = "no";
		if (usuario.getUsuarioBloqueado().compareTo(Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM) == 0) {
			ret = "no";
		} else if (usuario.getUsuarioBloqueado().compareTo(Constantes.USR_ATIVO_DESBLOQUEADO_MENSAGEM) == 0) {
			ret = "no";
		} else if (usuario.getUsuarioBloqueado().compareTo(Constantes.USR_ATIVO_BLOQUEADO) == 0) {
			ret = "no";
		} else if (usuario.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) == 0) {
			ret = "yes";
		}
		return ret;
	}

	private String getUsuarioBloqueado() {
		String ret = "blocked";
		if (usuario.getUsuarioBloqueado().compareTo(Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM) == 0) {
			ret = ConfigUtil.getInstance().getProperty("bloqueio_1", "blocked");
		} else if (usuario.getUsuarioBloqueado().compareTo(Constantes.USR_ATIVO_DESBLOQUEADO_MENSAGEM) == 0) {
			ret = ConfigUtil.getInstance().getProperty("bloqueio_2", "blocked");
		} else if (usuario.getUsuarioBloqueado().compareTo(Constantes.USR_ATIVO_BLOQUEADO) == 0) {
			ret = ConfigUtil.getInstance().getProperty("bloqueio_3", "blocked");
		} else if (usuario.getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) == 0) {
			ret = ConfigUtil.getInstance().getProperty("bloqueio_4", "blocked");
		}
		return ret;
	}
	
	public String substituiComandos(String comando, Torre t){
		comando = comando.replace("{1}", getUsuarioAtivo()); //disabled={1}
		if(usuario.getUsuarioMac() != null && !"".equalsIgnoreCase(usuario.getUsuarioMac())){
			comando = comando.replace("{2}", usuario.getUsuarioMac()); //mac-address={2}
		}
		comando = comando.replace("{3}", usuario.getUsuarioId().toString()); //id do usuario -- comment="{3}"
		comando = comando.replace("{4}", usuario.getUsuarioId().toString()); //id do usuario -- name={4}
		if(usuario.getEnderecoIp() != null){
			comando = comando.replace("{5}", usuario.getEnderecoIp().getEnderecoipEndereco()); //address={5}
			comando = comando.replace("{6}", usuario.getEnderecoIp().getEnderecoipEndereco()); //target-addresses={6}
		}
		
		if(t.getTorreNomeInterface() != null){
			comando = comando.replace("{7}", t.getTorreNomeInterface()); //interface={7}
			comando = comando.replace("{8}", t.getTorreNomeInterface()); //server={8}
		}
		comando = comando.replace("{9}", "yes"); //authentication={9}
		comando = comando.replace("{10}", "no"); //forwarding={10}
		comando = comando.replace("{11}", getUsuarioBloqueado()); //type={11}

		if(usuario.getPlanosPacotes() != null){
			comando = comando.replace("{12}", calculaLimiteBurst(usuario.getPlanosPacotes().getPlanospacotesVelocidade().intValue())); //burst-limit={12}
			comando = comando.replace("{13}", calculaLimiteBurstThreeshold(usuario.getPlanosPacotes().getPlanospacotesVelocidade().intValue()));//burst-threshold={13}
		}
		
		comando = comando.replace("{14}", "8s/8s"); //burst-time={14}
		comando = comando.replace("{15}", getUsuarioAtivo()); //disabled={15}
		
		if(usuario.getPlanosPacotes() != null){
			comando = comando.replace("{16}", calculaLimite(usuario.getPlanosPacotes().getPlanospacotesVelocidade().intValue())); //limit-at={16}
			comando = comando.replace("{17}", calculaLimite(usuario.getPlanosPacotes().getPlanospacotesVelocidade().intValue())); //max-limit={17}
		}
		
		comando = comando.replace("{19}", getUsuarioAtivo()); //posicao do queue{19}
		if(usuario.getUsuarioEmail() != null && !"".equalsIgnoreCase(usuario.getUsuarioEmail())){
			comando = comando.replace("{20}", usuario.getUsuarioEmail()); //password={20}
			comando = comando.replace("{21}", Criptografia.decrypt(usuario.getUsuarioSenha(), ConfigUtil.getInstance().getProperty("chave_criptografia",Constantes.CHAVE_CRIPTOGRAFIA))); //profile={21}*/
		}
		comando = comando.replace("{22}", "0"); //Move o queue para a posicao 0
		comando = comando.replace("{23}", ConfigUtil.getInstance().getProperty("horario_liberacao_cliente", "10h-21h59m59s,sun,mon,tue,wed,thu,fri,sat")); //time={8}
		return comando;
	}
	
}
