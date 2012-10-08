package br.com.meganet.bo;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.criterion.Expression;

import br.com.meganet.hbm.DAO.ContatoDAO;
import br.com.meganet.hbm.vo.Contato;
import br.com.meganet.util.DateUtils;


public class ContatoBO{
	
	private ContatoDAO contatoDAO;
	public void setContatoDAO(ContatoDAO contatoDAO) {
		this.contatoDAO = contatoDAO;
	}

	public int insereContato(Contato contato) {
		try {
			Timestamp data = new Timestamp(System.currentTimeMillis());
			contato.setContatoDataEnvio(data);
			contatoDAO.saveOrUpdate(contato);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public List<Contato> adiquirirContatosAbertos() throws Exception {
		List<Contato> contatos = contatoDAO.findByCriteria(
				Expression.or(
						Expression.isNull(ContatoDAO.CONTATO_RESPOSTA),
						Expression.eq(ContatoDAO.CONTATO_RESPOSTA, "")));
		return contatos;
	}

	public String buscaMensagemCliente(Long id) {
		Contato c = contatoDAO.findById(id);
		return c.getContatoMensagem();
	}

	public Contato gravaResposta(Long id, String descricao) throws Exception{
		Contato c = contatoDAO.findById(id);
		c.setContatoResposta(descricao);
		c.setContatoDataResposta(new Timestamp(System.currentTimeMillis()));
		contatoDAO.saveOrUpdate(c);
		return c;
	}

	public List<Contato> buscaRelatorioContato(String dataInicial, String dataFinal, int aberto) throws Exception {
		if(aberto == 1){
			Timestamp inicio = DateUtils.traduzDataStringToTimeStamp(dataInicial);
			Timestamp fim = DateUtils.traduzDataStringToTimeStamp(dataFinal);
			
			List<Contato> contatos = contatoDAO.findByCriteria(
					Expression.ge(ContatoDAO.CONTATO_DATA_ENVIO, inicio),
					Expression.le(ContatoDAO.CONTATO_DATA_ENVIO, fim),
					Expression.isNull(ContatoDAO.CONTATO_DATA_RESPOSTA));
			return contatos;
		}else{
			Timestamp inicio = DateUtils.traduzDataStringToTimeStamp(dataInicial);
			Timestamp fim = DateUtils.traduzDataStringToTimeStamp(dataFinal);
			
			List<Contato> contatos = contatoDAO.findByCriteria(
					Expression.ge(ContatoDAO.CONTATO_DATA_ENVIO, inicio),
					Expression.le(ContatoDAO.CONTATO_DATA_ENVIO, fim));
			return contatos;
		}
	}

	public Contato detalharContato(Long id) {
		return contatoDAO.findById(id);
	}

}
