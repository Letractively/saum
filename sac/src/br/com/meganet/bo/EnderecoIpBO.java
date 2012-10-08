package br.com.meganet.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.meganet.exception.GAPBDException;
import br.com.meganet.hbm.DAO.EnderecoIpDAO;
import br.com.meganet.hbm.vo.EnderecoIp;
import br.com.meganet.hbm.vo.Servidor;
import br.com.meganet.hbm.vo.Usuario;

public class EnderecoIpBO {
	
	private EnderecoIpDAO enderecoIpDAO;
	public void setEnderecoIpDAO(EnderecoIpDAO enderecoIpDAO) {
		this.enderecoIpDAO = enderecoIpDAO;
	}

	public Collection<EnderecoIp> carregaComboEnderecoIP(Long idTorre) {
		return enderecoIpDAO.findEnderecoIpNaoUsado(idTorre);
	}

	public EnderecoIp findByID(Long enderecoipId) {
		return enderecoIpDAO.findById(enderecoipId);
	}

	public List<EnderecoIp> buscaGrupos(Long combo) throws GAPBDException {
		List<Object> ends = enderecoIpDAO.buscaGrupos(combo);
		List<EnderecoIp> ret = new ArrayList<EnderecoIp>();
		
		for (Iterator<Object> iterator = ends.iterator(); iterator.hasNext();) {
			Object[] arrObj = (Object[]) iterator.next();
			EnderecoIp enderecoIp = (EnderecoIp) arrObj[1];
			Usuario usuario = (Usuario) arrObj[0];
			EnderecoIp end = new EnderecoIp();
			if(usuario == null){
				end.setTemUsuario(false);
				end.setNomeUsr("");
			}else{
				end.setTemUsuario(true);
				end.setNomeUsr(usuario.getUsuarioNome().split(" ")[0]);
			}
			end.setEnderecoipEndereco(enderecoIp.getEnderecoipEndereco());
			end.setEnderecoipGrupo(enderecoIp.getEnderecoipGrupo());
			end.setEnderecoipMacCadastrado(enderecoIp.getEnderecoipMacCadastrado());
			ret.add(end);
		}
		return ret;
	}

	public List<EnderecoIp> buscaIP(Long id) throws GAPBDException {
		return enderecoIpDAO.findByCriteria(
				Order.asc(EnderecoIpDAO.ENDERECOIP_ENDERECO), 
				Expression.eq(EnderecoIpDAO.SERVIDOR, new Servidor(id)));
	}

	public int gravaIP(List<EnderecoIp> ips, Long serv) throws Exception{
		enderecoIpDAO.deletePorTorre(serv);
		
		for (Iterator<EnderecoIp> iterator = ips.iterator(); iterator.hasNext();) {
			EnderecoIp enderecoIp = (EnderecoIp) iterator.next();
			enderecoIpDAO.attachDirty(enderecoIp);
		}
		return 0;
	}

}
