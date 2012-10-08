package br.com.meganet.facade;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Expression;
import org.jboleto.JBoletoBean;

import br.com.meganet.auditoria.BarraProgresso;
import br.com.meganet.bo.BoletoBO;
import br.com.meganet.exception.GAPBDException;
import br.com.meganet.hbm.DAO.LogMeganetDAO;
import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.hbm.vo.LogMeganet;
import br.com.meganet.hbm.vo.LucroVO;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.Logger;

public class BoletoFacade {

	private Logger log;
	BarraProgresso bp = new BarraProgresso();

	private BoletoBO boletoBO;
	private LogMeganetDAO logMeganetDAO;

	public void setLog(Logger log) {
		this.log = log;
	}
	public void setBoletoBO(BoletoBO boletoBO) {
		this.boletoBO = boletoBO;
	}
	public void setLogMeganetDAO(LogMeganetDAO logMeganetDAO) {
		this.logMeganetDAO = logMeganetDAO;
	}
	public JBoletoBean getBoletoByMAC(Usuario usr) {
		try {
			return boletoBO.getBoletoByMAC(usr);
		} catch (IndexOutOfBoundsException e) {
			log.erro("Erro no tamannho da lista, pode estar retornando NULL do banco\n usuario MAC:" + usr.getUsuarioMac().toUpperCase(), e);
			return null;
		} catch (Exception e) {
			log.erro("Erro ao adiquirir boleto, pode ser cast de datas", e);
			return null;
		}
	}

	public Collection<BoletosGerados> adiquirirBoletosEmAberto(String cpf) {
		try {
			return boletoBO.adiquirirBoletosEmAberto(cpf);
		} catch (Exception e) {
			log.erro("Erro ao adiquirir boleto, pode ser cast de datas\n usuario CPF" + cpf, e);
			return new ArrayList<BoletosGerados>();
		}
	}

	public List<BoletosGerados> adiquiriRelatorioBoletoUsuario(Usuario usuario) {
		try {
			return boletoBO.adiquiriRelatorioBoletoUsuario(usuario);
		} catch (Exception e) {
			log.erro("Erro ao adiquirir relatorios de boletos, para o usuario: " + usuario.getUsuarioId(), e);
			return null;
		}
	}
	
	public JBoletoBean getBoletoById(BoletosGerados boletosGerados) {
		try {
			return boletoBO.getBoletoById(boletosGerados);
		} catch (IndexOutOfBoundsException e) {
			log.erro("Erro no tamannho da lista, pode estar retornando NULL do banco", e);
			return null;
		} catch (Exception e) {
			log.erro("Erro ao adiquirir boleto, pode ser cast de datas", e);
			return null;
		}
	}

	public List<JBoletoBean> getBoletoByUsuarioMes(String cliente, String tp, int mes, int ano, String dtAdia, Usuario usrLogado, String idBol) {
		try {
			return boletoBO.getBoletoByUsuarioMes(cliente, tp, mes, ano, dtAdia, usrLogado, idBol);
		} catch (IndexOutOfBoundsException e) {
			log.erro("Erro no tamannho da lista, pode estar retornando NULL do banco", e);
			return null;
		} catch (Exception e) {
			log.erro("Erro ao adiquirir boleto, pode ser cast de datas", e);
			return null;
		}
	}

	public List<JBoletoBean> getBoletoByDataPagamento(Calendar dtIni, Calendar dtFim, String dtAdia, Usuario usrLogado) {
		try {
			return boletoBO.getBoletoByUsuarioMes(dtIni, dtFim, dtAdia, usrLogado);
		} catch (IndexOutOfBoundsException e) {
			log.erro("Erro no tamannho da lista, pode estar retornando NULL do banco", e);
			return null;
		} catch (Exception e) {
			log.erro("Erro ao adiquirir boletos para impressão por data.", e);
			return null;
		}
	}

	public LucroVO buscaPrevisaoLucro(String inicial) {
		try {
			return boletoBO.buscaRelatorioLucros(inicial);
		} catch (Exception e) {
			log.erro("Erro na busca de lucros", e);
			return new LucroVO();
		}
	}
	public List<BoletosGerados> buscaBoletosPagoEmMao(String inicial, String fim) {
		try {
			return boletoBO.buscaBoletosPagoEmMao(inicial, fim);
		} catch (Exception e) {
			log.erro(e);
			return new ArrayList<BoletosGerados>();
		}
	}

	public Long setValorBoletoEntregue(String dtInicial, String dtFinal, Long id) {
		try {
			return boletoBO.setValorBoletoEntregue(dtInicial, dtFinal, id);
		} catch (Exception e) {
			log.erro(e);
			return 0L;
		}
	}
	
	public boolean existeLogParaEsseArquivo(String arquivo){
		try {
			List<LogMeganet> ls = logMeganetDAO.findByCriteria(Expression.eq(LogMeganetDAO.LOG_ACAO, arquivo));
			if(ls != null && ls.size() > 0){
				return true;
			}else{
				return false;
			}
		} catch (GAPBDException e) {
			log.erro(e);
			return true;
		}
		
	}
	
	public boolean gravaLogArquivoRetorno(String arquivo) {
		if(!existeLogParaEsseArquivo(arquivo)){
			LogMeganet log = new LogMeganet();
			log.setLogData(new Timestamp(System.currentTimeMillis()));
			log.setLogTipo(Constantes.LOG_TIPO_RETORNO[0]);
			log.setLogAcao(arquivo);
			log.setLogDescricao("");
			logMeganetDAO.attachDirty(log);
			return true;
		}else{
			return false;
		}
	}
}
