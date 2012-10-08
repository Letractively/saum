package br.com.meganet.threads;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.DateUtils;

public class Monitor extends TimerTask {
	private LerArquivoRetorno lerArquivoRetorno;
	private VerificaVelocidadeMedia verificaVelocidadeMedia;
	private VerificaVelocidadeInterfaces verificaVelocidadeInterfaces;
	private CarregaMapas carregaMapas;
	public static final Integer m = 30;
	public static final Integer s = 30;
	private boolean t;
//	private BackupServidores backupServidores;

	public void run() {
		try {
			
			Timer tcm = new Timer("Timer-carregaMapas");
			tcm.schedule(carregaMapas, 1000, DateUtils.calculaTempo(0, m, s));
			Thread.sleep(5000);
			t = true;
			while (t) {
				if (naoDeuAHora() && ConfigUtil.getInstance().getMapaCarregado()) {
					if (ConfigUtil.getInstance().getBooleanProperty("envia_comando_arquivo", true)) {
						Timer tLer = new Timer("Timer-lerarquivo");
						tLer.schedule(lerArquivoRetorno, 10000, DateUtils.calculaTempo(
								new Integer(ConfigUtil.getInstance().getProperty("intervalo_ler_arquivo_e_envio_f2b", "24").split(":")[0]), 
								new Integer(ConfigUtil.getInstance().getProperty("intervalo_ler_arquivo_e_envio_f2b", "0").split(":")[1]), 0));
					}
					if (ConfigUtil.getInstance().getBooleanProperty("envia_comando_velocidade_media", true)) {
						Timer tVel = new Timer("Timer-velocidadeMedia");
						tVel.schedule(verificaVelocidadeMedia, 1000, DateUtils.calculaTempo(
								new Integer(ConfigUtil.getInstance().getProperty("intervalo_nivel_clientes_monitoramento_interfaces", "24").split(":")[0]), 
								new Integer(ConfigUtil.getInstance().getProperty("intervalo_nivel_clientes_monitoramento_interfaces", "0").split(":")[1]), 0));
					}
					if (ConfigUtil.getInstance().getBooleanProperty("envia_comando_velocidade_interface", true)) {
						Timer tVelInt = new Timer("Timer-velocidadeInterface");
						tVelInt.schedule(verificaVelocidadeInterfaces, 5000, DateUtils.calculaTempo(
								new Integer(ConfigUtil.getInstance().getProperty("intervalo_trafego_interfaces", "24").split(":")[0]), 
								new Integer(ConfigUtil.getInstance().getProperty("intervalo_trafego_interfaces", "0").split(":")[1]), 0));
					}
				} else {
					Thread.sleep(new Long(240000));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			this.cancel();
		} finally {
			this.cancel();
		}

	}

	private boolean naoDeuAHora() {
		Calendar agora = GregorianCalendar.getInstance(new Locale("pt","br"));
		Calendar horaBatch = GregorianCalendar.getInstance(new Locale("pt","br"));
		agora.setTime(new Date(System.currentTimeMillis()));
		agora.set(Calendar.SECOND, 0);
		horaBatch.setTime(new Date(System.currentTimeMillis()));

		int horaE = new Integer(ConfigUtil.getInstance().getProperty("hora_inicio_comandos_HHMM", "00:00").split(":")[0]);
		int minE = new Integer(ConfigUtil.getInstance().getProperty("hora_inicio_comandos_HHMM", "00:00").split(":")[1]);
		horaBatch.set(Calendar.HOUR_OF_DAY, horaE);
		horaBatch.set(Calendar.MINUTE, minE);
		
		long difMinutos = ((agora.getTimeInMillis() - horaBatch.getTimeInMillis()) / 1000) / 60;
		
		if (difMinutos >= 0 && difMinutos <= 11) {
			t = false;
			return true;
		}
		return false;
	}

	public void setVerificaVelocidadeInterfaces(VerificaVelocidadeInterfaces verificaVelocidadeInterfaces) {
		this.verificaVelocidadeInterfaces = verificaVelocidadeInterfaces;
	}

	public void setCarregaMapas(CarregaMapas carregaMapas) {
		this.carregaMapas = carregaMapas;
	}
	
	public void setVerificaVelocidadeMedia(VerificaVelocidadeMedia verificaVelocidadeMedia) {
		this.verificaVelocidadeMedia = verificaVelocidadeMedia;
	}

	public void setLerArquivoRetorno(LerArquivoRetorno lerArquivoRetorno) {
		this.lerArquivoRetorno = lerArquivoRetorno;
	}

}