package br.com.meganet.dwr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import br.com.meganet.auditoria.BarraProgresso;
import br.com.meganet.facade.AdministracaoFacade;
import br.com.meganet.hbm.vo.Dominios;
import br.com.meganet.hbm.vo.EnderecoIp;
import br.com.meganet.hbm.vo.InfBoleto;
import br.com.meganet.hbm.vo.PlanosPacotes;
import br.com.meganet.hbm.vo.PoolComandos;
import br.com.meganet.hbm.vo.Servidor;
import br.com.meganet.hbm.vo.Torre;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.Criptografia;
import br.com.meganet.util.Util;

public class UtilsJS{
	
	private AdministracaoFacade administracaoFacade;
	public void setAdministracaoFacade(AdministracaoFacade administracaoFacade) {
		this.administracaoFacade = administracaoFacade;
	}
	
	BarraProgresso bp = new BarraProgresso();
	
	public Collection<Usuario> carregaComboUsuario(){
		return administracaoFacade.carregaComboUsuario();
	}
	
	public Collection<PlanosPacotes> carregaComboPlano(){
		Collection<PlanosPacotes> planos = administracaoFacade.carregaComboPlano();
		Collection<PlanosPacotes> ret = new ArrayList<PlanosPacotes>();
		for (Iterator<PlanosPacotes> iterator = planos.iterator(); iterator.hasNext();) {
			PlanosPacotes planosPacotes = (PlanosPacotes) iterator.next();
			PlanosPacotes pp= new PlanosPacotes();
			pp.setPlanospacotesId(planosPacotes.getPlanospacotesId());
			pp.setPlanospacotesNome(planosPacotes.getPlanospacotesNome());
			ret.add(pp);
		}
		return ret;
	}
	
	public Collection<PlanosPacotes> carregaComboPlanoParaAlteracao(){
		return administracaoFacade.carregaComboPlanoParaAlteracao();
	}
	
	public Collection<Torre> carregaComboTorre(){
		Collection<Torre> torres = administracaoFacade.carregaComboTorre();
		Collection<Torre> ret = new ArrayList<Torre>();
		for (Iterator<Torre> iterator = torres.iterator(); iterator.hasNext();) {
			Torre torre = (Torre) iterator.next();
			Torre t = new Torre();
			t.setTorreId(torre.getTorreId());
			t.setTorreNome(torre.getTorreNome());
			ret.add(t);
		}
		return ret;
	}
	
	public Collection<Torre> carregaComboTodasTorre(){
		Collection<Torre> torres = administracaoFacade.carregaComboTodasTorre();
		Collection<Torre> ret = new ArrayList<Torre>();
		for (Iterator<Torre> iterator = torres.iterator(); iterator.hasNext();) {
			Torre torre = (Torre) iterator.next();
			Torre t = new Torre();
			t.setTorreId(torre.getTorreId());
			t.setTorreNome(torre.getTorreNome());
			ret.add(t);
		}
		return ret;
	}
	
	public Collection<Servidor> carregaComboTodosServidores(){
		Collection<Servidor> servs = administracaoFacade.carregaComboTodosServidores();
		Collection<Servidor> ret = new ArrayList<Servidor>();
		for (Iterator<Servidor> iterator = servs.iterator(); iterator.hasNext();) {
			Servidor serv = (Servidor) iterator.next();
			Servidor t = new Servidor();
			t.setServidorId(serv.getServidorId());
			t.setServidorNome(serv.getServidorNome());
			ret.add(t);
		}
		return ret;
	}
	
	public Collection<Servidor> carregaComboServidoresAtivos(){
		Collection<Servidor> servs = administracaoFacade.carregaComboServidoresAtivos();
		Collection<Servidor> ret = new ArrayList<Servidor>();
		for (Iterator<Servidor> iterator = servs.iterator(); iterator.hasNext();) {
			Servidor serv = (Servidor) iterator.next();
			Servidor t = new Servidor();
			t.setServidorId(serv.getServidorId());
			t.setServidorNome(serv.getServidorNome());
			ret.add(t);
		}
		return ret;
	}
	
	public Collection<EnderecoIp> carregaComboEnderecoIP(Long idServ){
		Collection<EnderecoIp> lei = administracaoFacade.carregaComboEnderecoIP(idServ);
		Collection<EnderecoIp> ret = new ArrayList<EnderecoIp>();
		for (Iterator<EnderecoIp> iterator = lei.iterator(); iterator.hasNext();) {
			EnderecoIp ei = (EnderecoIp) iterator.next();
			if(ei.getEnderecoipAtivado() == true){
				EnderecoIp e = new EnderecoIp();
				e.setEnderecoipId(ei.getEnderecoipId());
				e.setEnderecoipEndereco(ei.getEnderecoipEndereco());
				ret.add(e);
			}
		}
		return ret;
	}
	
	public Torre buscaDadosTorre(Long idTorre){
		Torre t = administracaoFacade.buscaTorrePeloID(idTorre);
		Torre ret = new Torre();
		ret.setTorreNome(t.getTorreNome());
		ret.setTorreDescricao(t.getTorreDescricao());
		ret.setTorreProcessador(t.getTorreProcessador());
		ret.setTorreMemoriaRam(t.getTorreMemoriaRam());
		ret.setTorreEspacoDisco(t.getTorreEspacoDisco());
		ret.setTorreLocal(t.getTorreLocal());
		ret.setTorreUsuario(t.getTorreUsuario());
		ret.setTorreSenha(t.getTorreSenha());
		ret.setTorreDesativado(t.getTorreDesativado());
		ret.setTorreIpConexao(t.getTorreIpConexao());
		ret.setTorreIpConexao2(t.getTorreIpConexao2());
		ret.setTorreId(t.getTorreId());
		ret.setTorreNomeInterface(t.getTorreNomeInterface());
		ret.setTorrePorta(t.getTorrePorta());
		ret.setTorreModelo(t.getTorreModelo());
		ret.setTorreIpIntermediador(t.getTorreIpIntermediador());
		ret.setTorrePortaIntermediador(t.getTorrePortaIntermediador());
		ret.setTorreUtilizarIntermediador(t.getTorreUtilizarIntermediador());
		return ret;
	}
	
	public Servidor buscaDadosServidor(Long idServ){
		Servidor t = administracaoFacade.buscaServidorPeloID(idServ);
		Servidor ret = new Servidor();
		ret.setServidorId(t.getServidorId());
		ret.setServidorNome(t.getServidorNome());
		ret.setServidorLocal(t.getServidorLocal());
		ret.setServidorDescricao(t.getServidorDescricao());
		ret.setServidorTorres(t.getServidorTorres());
		ret.setServidorDesativado(t.getServidorDesativado());
		return ret;
	}
	
	public InfBoleto buscaDadosInfBoleto(){
		return administracaoFacade.buscaDadosInfBoleto();
	}
	
	public PlanosPacotes carregaPlanoParaAlteracao(Long id){
		return administracaoFacade.carregaPlanoParaAlteracao(id);
	}
	
	public boolean gravaInfBoleto(InfBoleto infBoleto){
		boolean ret = administracaoFacade.gravaInfBoleto(infBoleto);
		return ret;
	}
	
	public boolean gravaPlanoPacote(PlanosPacotes planosPacotes){
		boolean ret = administracaoFacade.gravaPlanoPacote(planosPacotes);
		return ret;
	}

	public Collection<Usuario> carregaComboMAC(){
		Collection<Usuario> tmp = administracaoFacade.carregaComboMAC();
		List<Usuario> ret = new ArrayList<Usuario>();
		for (Iterator<Usuario> iterator = tmp.iterator(); iterator.hasNext();) {
			Usuario usuario = (Usuario) iterator.next();
			Usuario usr = new Usuario();
			usr.setUsuarioMac(usuario.getUsuarioMac());
			ret.add(usr);
		}
		return ret;
	}
	
	public EnderecoIp buscaIP(Long id){
		List<EnderecoIp> lip = administracaoFacade.buscaIP(id);
		if(lip != null && lip.size() > 0){
			EnderecoIp ret = new EnderecoIp();
			for (Iterator<EnderecoIp> iterator = lip.iterator(); iterator.hasNext();) {
				EnderecoIp ip = (EnderecoIp) iterator.next();
				
				ret.setEnderecoipEndereco(ip.getEnderecoipEndereco());
				ret.setEnderecoipMascaraSubrede(ip.getEnderecoipMascaraSubrede());
				break;
			}
			return ret;
		}else{
			return null;
		}
	}
	public int gravaIP(List<EnderecoIp> ips, Long serv){
		return administracaoFacade.gravaIP(ips, serv);
	}
	public List<PoolComandos> buscaComandosDaTorre(Long servID, Long tipo){
		if(servID != null && tipo != null){
			List<PoolComandos> lPC = administracaoFacade.buscaComandosDoServidor(servID, tipo);
			List<PoolComandos> ret = null;
			
			if(lPC != null && lPC.size() > 0){
				ret = new ArrayList<PoolComandos>();
				for (Iterator<PoolComandos> iterator = lPC.iterator(); iterator.hasNext();) {
					PoolComandos p = (PoolComandos) iterator.next();
					PoolComandos pc = new PoolComandos();
					pc.setPoolcomandosComando(p.getPoolcomandosComando());
					pc.setPoolcomandosOrdem(p.getPoolcomandosOrdem());
					pc.setServidor(new Servidor(p.getServidor().getServidorId()));
					pc.setTorre(new Torre(p.getTorre().getTorreId()));
					ret.add(pc);
				}
			}
			return ret;
		}else{
			return null;
		}
	}
	
	public List<Torre> buscaTorresVinculadasAoServidor(Long servidorID){
		if(servidorID != null){
			List<Torre> torres = administracaoFacade.buscaTorresVinculadasAoServidor(servidorID);
			List<Torre> ret = new ArrayList<Torre>();
			for (Iterator<Torre> iterator = torres.iterator(); iterator.hasNext();) {
				Torre torre = (Torre) iterator.next();
				Torre t = new Torre();
				t.setTorreId(torre.getTorreId());
				t.setTorreNome(torre.getTorreNome());
				ret.add(t);
			}
			return ret;
		}else{
			return null;
		}
	}
	
	public PoolComandos buscaComandoMonitoramentoTorre(Long servID, Long tipo){
		if(servID != null && tipo != null){
			PoolComandos lPC = administracaoFacade.buscaComandoMonitoramentoTorre(servID, tipo);
			if(lPC != null){
				PoolComandos pc = new PoolComandos();
				pc.setPoolcomandosComando(lPC.getPoolcomandosComando());
				pc.setComandoExemplo(Util.getComandosExemplo(tipo));
				pc.setServidor(new Servidor(lPC.getServidor().getServidorId()));
				pc.setTorre(new Torre(lPC.getTorre().getTorreId()));
				return pc;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public int gravaAlteracaoComando(List<PoolComandos> comandos){
		return administracaoFacade.gravaAlteracaoComando(comandos);
	}
	
	public int gravaAlteracaoComandoMonitoramento(PoolComandos comandos){
		return administracaoFacade.gravaAlteracaoComandoMonitoramento(comandos);
	}
	
	public String verificarMAC(String mac){
		return administracaoFacade.verificarMAC(mac);
	}
	public String geraChave(){
		return Criptografia.generateKey();
	}
	
	public List<Dominios> carregaDominios(){
		return administracaoFacade.carregaDominios();
	}
	
	public String salvaConfAvancadas(List<Dominios> dominios){
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		boolean primeiroAcesso = false;
		if(req.getSession().getAttribute("PRIMEIRO_ACESSO") != null){
			primeiroAcesso = (Boolean) req.getSession().getAttribute("PRIMEIRO_ACESSO");
			req.getSession().removeAttribute("PRIMEIRO_ACESSO");
		}
		
		return administracaoFacade.salvaConfAvancadas(dominios, primeiroAcesso);
	}
}

