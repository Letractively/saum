package br.com.meganet.threads;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimerTask;

import br.com.meganet.bo.BoletoBO;
import br.com.meganet.hbm.DAO.InfBoletoDAO;
import br.com.meganet.hbm.DAO.UsuarioDAO;
import br.com.meganet.hbm.vo.InfBoleto;
import br.com.meganet.util.ArquivoUtils;
import br.com.meganet.util.BufferLog;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.EnumBancos;
import br.com.meganet.util.Logger;

/**
 * @author efren
 * 
 * verifica o arquivo de retorno utilizando os seguinte enderecos para pesquisa
 * numero do boleto 63 - 71 valor pago ao banco 176-189 valor do desconto
 * 189-254 valor creditado na conta 254-267 data pagamento 111-117
 * 
 */
public class LerArquivoRetorno extends TimerTask {


	private BoletoBO boletoBO;
	private InfBoletoDAO infBoletoDAO;
	private UsuarioDAO usuarioDAO;
	private InfBoleto infBol;

	private CortarCliente cortarCliente;

	public void setInfBoletoDAO(InfBoletoDAO infBoletoDAO) {
		this.infBoletoDAO = infBoletoDAO;
	}
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public void setBoletoBO(BoletoBO boletoBO) {
		this.boletoBO = boletoBO;
	}

	public void setCortarCliente(CortarCliente cortarCliente) {
		this.cortarCliente = cortarCliente;
	}

	private static Logger logger = new Logger(LerArquivoRetorno.class);
	private static String diretorio;
	private static String diretorioBkp;


// SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy", new Locale("pt","br"));
// Long numeroBoleto = new Long(string.substring(62, 71).trim());
// String valorPagoAoBanco = string.substring(178, 188);
// String valorCreditadoConta = string.substring(256, 266);
// Timestamp dataPagamento = new Timestamp(sdf.parse(string.substring(110, 116)).getTime());
	
	private boolean lerArquivo(BufferLog bl) {
		boolean situacaoBolF2B = ConfigUtil.getInstance().getBooleanProperty("verifica_situacao_boleto", false);
		if(!situacaoBolF2B){
			return true;
		}
		
		diretorio = ConfigUtil.getInstance().getProperty("diretorio_retorno","c:\\retorno");
		diretorioBkp = ConfigUtil.getInstance().getProperty("diretorio_retorno","c:\\retorno") + ArquivoUtils.SEPARADOR_DIRETORIO + "processados";
		String nomeArquivoTMP = "";
		try {

			String[] arquivos = ArquivoUtils.listarArquivos(diretorio);
			SimpleDateFormat sdf = new SimpleDateFormat(infBol.getInfboletoDatapagamentoMascara().trim(), new Locale("pt","br"));

			for (int i = 0; i < arquivos.length; i++) {
				String numBolParaLOG = "";
				Collection<String> linhasArquivo = ArquivoUtils.leArquivo(diretorio + ArquivoUtils.SEPARADOR_DIRETORIO + arquivos[i]);
				nomeArquivoTMP = diretorio + ArquivoUtils.SEPARADOR_DIRETORIO + arquivos[i];
				boolean arquivoFoiLido = boletoBO.oArquivoFoiLido(arquivos[i]);
				if (!arquivoFoiLido) {
					for (Iterator<String> iterator = linhasArquivo.iterator(); iterator.hasNext();) {
						String linhaAtual = (String) iterator.next();
						try{
								
							Long numeroBoleto = new Long(linhaAtual.substring(infBol.getInfboletoNumeroboletoPosIni(), infBol.getInfboletoNumeroboletoPosFim()).trim());
							String valorPagoAoBanco = linhaAtual.substring(infBol.getInfboletoValorpagoaobancoPosIni(), infBol.getInfboletoValorpagoaobancoPosFim());
							String valorCreditadoConta = linhaAtual.substring(infBol.getInfboletoValorcreditadoPosIni(), infBol.getInfboletoValorcreditadoPosFim());
							Timestamp dataPagamento = new Timestamp(sdf.parse(linhaAtual.substring(infBol.getInfboletoDatapagamentoPosIni(), infBol.getInfboletoDatapagamentoPosFim())).getTime());

							bl.append("Lendo arquivo retorno - num boleto:" + numeroBoleto);
							numBolParaLOG += numeroBoleto + ",";
							
							boletoBO.atualizaPagamentoBoleto(numeroBoleto, valorPagoAoBanco, valorCreditadoConta, dataPagamento);
							
						}catch (Exception e) {
							logger.erroSemEmail("Erro na leitura do arquivo " + diretorio + ArquivoUtils.SEPARADOR_DIRETORIO + arquivos[i], e);
						}
					}
					boletoBO.gravaLogArquivoRetorno(arquivos[i], numBolParaLOG.substring(0, numBolParaLOG.length() - 1));
				}
				if(!arquivoFoiLido){
					ArquivoUtils.moverArquivo(diretorio + ArquivoUtils.SEPARADOR_DIRETORIO, arquivos[i], diretorioBkp + ArquivoUtils.SEPARADOR_DIRETORIO, "BKP" + arquivos[i]);
				}else{
					ArquivoUtils.excluirArquivo(diretorioBkp + ArquivoUtils.SEPARADOR_DIRETORIO + arquivos[i]);
				}
			}

			return true;
		} catch (Exception e) {
			bl.append("Erro na leitura do arquivo " + diretorio + ArquivoUtils.SEPARADOR_DIRETORIO + nomeArquivoTMP);
			bl.append(e);
			return false;
		}
	}
	 
	private boolean enviaBoletoEVerificaSituacaoF2B(BufferLog bl) {
		try {
			return boletoBO.enviaBoletoEVerificaSituacaoF2B(bl);
		} catch (Exception e) {
			logger.erro("Erro ao enviar boletos para a F2B", e);
			return false;
		}
	}

	@Override
	public void run() {
		try{
			BufferLog bl = new BufferLog();
			infBol = infBoletoDAO.findById(Constantes.ID_INF_BOLETO);
			
			bl.append("\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			bl.append("---I N Í C I O    "+ EnumBancos.getRotuloPorCodigo(infBol.getInfboletoBanco().toString()) +"----");
			bl.append("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			
			if(EnumBancos.F2B.getCodigo().equalsIgnoreCase(infBol.getInfboletoBanco().toString())){
				if (enviaBoletoEVerificaSituacaoF2B(bl)) {
					cortarCliente.cortaCliente(bl);
				}
			}else{
				if(lerArquivo(bl)){
					cortarCliente.cortaCliente(bl);
				}
			}
			
			bl.append("\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			bl.append("----F I N A L  "+ EnumBancos.getRotuloPorCodigo(infBol.getInfboletoBanco().toString()) +"-----");
			bl.append("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			logger.info(bl.toString());
		}catch (Exception e) {
			logger.erro("Erro geral na thread de manipulação de boletos", e);
		}
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}
}
