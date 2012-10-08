/**
 * @author     Jose Flavio<jose.nascimento@br-tcs.com.br>
 * @version    1.0
 * <p>
 * Copyright   (c)2004
 *
 * Sep 28, 2004
 */
package br.com.meganet.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Contem funcoes utilitarias para manipulacao de Datas
 * 
 * @author jlins
 * 
 */
public class DateUtils {

	/**
	 * Pattern que representa o formato da data como sendo AAAAMMDDHHMMSS, onde
	 * AAAA e o ano, MM e o mês e DD o dia, HH e hora, MM e minuto e SS sao os
	 * segundos.
	 */
	public static final String FORTAMTO_DATA_AAAAMMDDHHMMSS = "yyyyMMddHHmmss";

	/**
	 * Pattern que representa o formato da data como sendo DDMMAA, onde AAAA e o
	 * ano, MM e o mês e DD o dia.
	 */
	public static final String FORMATO_DATA_DD_MM_AA = "ddMMyy";

	/**
	 * Pattern que representa o formato da data como sendo MMDDAA, onde AAAA e o
	 * ano, MM e o mês e DD o dia.
	 */
	public static final String FORMATO_DATA_MM_DD_AA = "MMddyy";

	/**
	 * Pattern que representa o formato da data como sendo DDMMAA, onde AAAA e o
	 * ano, MM e o mês e DD o dia.
	 */
	public static final String FORMATO_DATA_MM = "MM";

	/**
	 * Pattern que representa o formato da data como sendo DDMMAA, onde AAAA e o
	 * ano, MM e o mês e DD o dia.
	 */
	public static final String FORMATO_DATA_AAAA = "yyyy";

	/**
	 * Pattern que representa o formato da data como sendo MMAAAA, onde AAAA e o
	 * ano e MM e o mês.
	 */
	public static final String FORMATO_DATA_MMYYYY = "MMyyyy";

	/**
	 * Pattern que representa o formato da data como sendo AAAAMMDD, onde AAAA e
	 * o ano, MM e o mês e DD o dia.
	 */
	public static final String FORMATO_DATA_AAAA_MM_DD = "yyyyMMdd";

	/**
	 * Pattern que representa o formato da data como sendo DDMMAAAA, onde AAAA e
	 * o ano, MM e o mes e DD o dia.
	 */
	public static final String FORMATO_DATA_DD_MM_AAAA = "ddMMyyyy";

	/**
	 * Pattern que representa o formato da data como sendo DD/MM/AAAA, onde AAAA
	 * e o ano, MM e o mes e DD o dia.
	 */
	public static final String FORMATO_DATA_DD_MM_AAAA_BARRA = "dd/MM/yyyy";

	/**
	 * Pattern que representa o formato da data como sendo DD/MM/AAAA, onde AAAA
	 * e o ano, MM e o mes e DD o dia.
	 */
	public static final String FORMATO_DATA_DD_MM_AAAA_TRACO = "dd-MM-yyyy";

	/**
	 * Pattern que representa o formato da data/hora dd/MM/yyyy hh:mm:ss
	 */
	public static final String FORMATO_DATA_DD_MM_AAAA_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";

	/**
	 * Pattern que representa o formato da data/hora dd/MM/yyyy hh:mm:ss.zzz
	 */
	public static final String FORMATO_DATA_DD_MM_AAAA_HH_MM_SS_MILIS = "dd/MM/yyyy HH:mm:ss.SSS";

	/**
	 * Pattern que representa o formato da data como sendo AAAAMM, onde AAAA e o
	 * ano e MM e o mes.
	 */
	public static final String FORMATO_DATA_AAAA_MM = "yyyyMM";

	/**
	 * Pattern que representa o formato da data como sendo HHmmss, onde HH e a
	 * hora em 24 horas, mm sao os minutis e ss sao os segundos.
	 */
	public static final String FORMATO_HORA_HH_MM_SS = "HHmmss";

	final static Locale BR = new Locale("pt", "BR");

	/**
	 * <b>Funcionamento: </b> Este metodo retornara uma nova data com a adicao
	 * do offset, a partir <br>
	 * do parametro passado. <br>
	 * <p>
	 * 
	 * @author Renata Catao <br>
	 * @param offset
	 * <br>
	 * @param constanteTipo
	 *            (se o offset vai ser em anos, meses...) <br>
	 * @return nova data com o offset alterado <br>
	 */
	public static Date calcularData(int offset, int constanteTipo) {
		return calcularData(new Date(), constanteTipo, offset);
	} // fim calculaDataAno

	/**
	 * <b>Funcionamento: </b> Este metodo retornara uma nova data com base em
	 * uma String no formato MMYYYY.
	 * 
	 * @author Renata Soares Catao
	 * @param data
	 * @return data gerada
	 */
	public static Date gerarData(String data, String formato) {
		DateFormat df = new SimpleDateFormat(formato, new Locale("pt","br"));
		Date dataRetorno;

		try {
			dataRetorno = df.parse(data);
		} catch (ParseException e) {
			return null;
		}

		return dataRetorno;
	}

	/**
	 * <b>Funcionamento: </b> Este metodo retornara a maior data da lista
	 * passada como parametro A comparacao e feita apos a transformacao das
	 * datas, que veem como String e sao transformadas para Date. As datas tem
	 * que vir no mesmo formato.
	 * 
	 * @author Renata Soares Catao
	 * @param datas
	 *            A colecao de Strings contendo datas
	 * @return a data maior
	 */
	public static Date recuperarDataMaior(Collection<Date> datas) {

		Date dataRetorno = (Date) Collections.max(datas);

		return dataRetorno;
	} // fim recuperaDataMaior

	/**
	 * Converte uma String em um java.util.Date.
	 * 
	 * @author eandrade
	 * @param data
	 *            A String com a data.
	 * @param pattern
	 *            O pattern no qual a data se encontra na String.
	 * @return Um date com a data informada.
	 * @throws ParseException
	 */
	public static Date obterData(String data, String pattern) throws ParseException {
		Date retorno = null;

		if (data != null && pattern != null) {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			format.setLenient(false);
			retorno = format.parse(data);
		}

		return retorno;
	} // obterData()

	/**
	 * Calcula uma data a partir de uma data, de um montante e de um campo
	 * informados nos parametros.
	 * 
	 * @author eandrade
	 * @param data
	 *            A data a partir da qual sera feito o calculo
	 * @param field
	 *            O campo da data. Ex (Calendar.DATE, Calendar.MONTH, ...)
	 * @param amount
	 *            O montante que deve ser acrescentado ou diminuido da data
	 *            inicial.
	 * @return A data calculada.
	 */
	public static Date calcularData(Date data, int field, int amount) {
		Calendar calendar = GregorianCalendar.getInstance();

		calendar.setTime(data);
		calendar.add(field, amount);

		return calendar.getTime();
	} // calcularData()

	/**
	 * Retorna uma String com a data a partir do pattern informado.
	 * 
	 * @author eandrade
	 * @param data
	 *            Uma data para ser formatada.
	 * @param pattern
	 *            O pattern no qual se deseja o formato da data.
	 * @return Uma String com a data a partir do pattern informado. Se data ou
	 *         pattern for null, uma String vazia sera retornada.
	 */
	public static String formatar(Date data, String pattern) {

		String stData = "";
		if ((data != null) && (pattern != null)) {
			DateFormat format = new SimpleDateFormat(pattern);
			stData = format.format(data);
		} // if

		return stData;
	} // formatar()

	/**
	 * <p>
	 * Retorna a data formatada de acordo com o pattern passado e substitui o
	 * valor do mês pela abreviacao do nome do mês.
	 * </p>
	 * 
	 * @param data
	 * @param pattern
	 * @return String
	 */
	public static String formatarNomeMesAbrev(Date data, String pattern) {

		if (data == null) {
			return "";
		}

		String strData = formatar(data, pattern);
		int posMesPattern = pattern.indexOf("MM");
		String mesStrData = strData.substring(posMesPattern, posMesPattern + 2);
		String nomeMes = getNomeMesAbrev(Integer.parseInt(mesStrData) - 1);
		strData = strData.substring(0, posMesPattern) + nomeMes + strData.substring(posMesPattern + 2);

		return strData;

	}// formatarNomeMesAbrev ()

	/**
	 * <p>
	 * Retorna a data formatada de acordo com o pattern passado e substitui o
	 * valor do mês pela abreviacao do nome do mês.
	 * </p>
	 * 
	 * @param data
	 * @param pattern
	 * @return String
	 */
	public static String formatarNomeMesAbrevFormatoMmm(Date data, String pattern) {

		if (data == null) {
			return "";
		}

		String strData = formatar(data, pattern);
		int posMesPattern = pattern.indexOf("MM");
		String mesStrData = strData.substring(posMesPattern, posMesPattern + 2);
		String nomeMes = getNomeMesAbrevFormatoMmm(Integer.parseInt(mesStrData) - 1);
		strData = strData.substring(0, posMesPattern) + nomeMes + strData.substring(posMesPattern + 2);

		return strData;

	}// formatarNomeMesAbrev ()

	/**
	 * <p>
	 * Retorna a abreviacao do nome do mês passado como parametro. OBS.: A lista
	 * de meses comeca em 0.
	 * </p>
	 * 
	 * @param mes
	 * @return String
	 */
	public static String getNomeMesAbrevEsp(int mes) {

		String nomeMes = "";

		switch (mes) {
		case 0:
			nomeMes = "ENE";
			break;
		case 1:
			nomeMes = "FEB";
			break;
		case 2:
			nomeMes = "MAR";
			break;
		case 3:
			nomeMes = "ABR";
			break;
		case 4:
			nomeMes = "MAY";
			break;
		case 5:
			nomeMes = "JUN";
			break;
		case 6:
			nomeMes = "JUL";
			break;
		case 7:
			nomeMes = "AGO";
			break;
		case 8:
			nomeMes = "SEP";
			break;
		case 9:
			nomeMes = "OCT";
			break;
		case 10:
			nomeMes = "NOV";
			break;
		case 11:
			nomeMes = "DIC";
			break;
		default:
			nomeMes = String.valueOf(mes);
		}

		return nomeMes;

	}// getNomeMesAbrevEsp ()

	/**
	 * <p>
	 * Retorna a abreviacao do nome do mês passado como parametro. OBS.: A lista
	 * de meses comeca em 0.
	 * </p>
	 * 
	 * @param mes
	 * @return String
	 */
	public static String getNomeMesAbrev(int mes) {

		String nomeMes = "";

		switch (mes) {
		case 0:
			nomeMes = "JAN";
			break;
		case 1:
			nomeMes = "FEV";
			break;
		case 2:
			nomeMes = "MAR";
			break;
		case 3:
			nomeMes = "ABR";
			break;
		case 4:
			nomeMes = "MAI";
			break;
		case 5:
			nomeMes = "JUN";
			break;
		case 6:
			nomeMes = "JUL";
			break;
		case 7:
			nomeMes = "AGO";
			break;
		case 8:
			nomeMes = "SET";
			break;
		case 9:
			nomeMes = "OUT";
			break;
		case 10:
			nomeMes = "NOV";
			break;
		case 11:
			nomeMes = "DEZ";
			break;
		default:
			nomeMes = String.valueOf(mes);
		}

		return nomeMes;

	}// getNomeMesAbrev ()

	/**
	 * <p>
	 * Retorna a abreviacao do nome do mês passado como parametro. OBS.: A lista
	 * de meses comeca em 0.
	 * </p>
	 * 
	 * @param mes
	 * @return String
	 */
	public static String getNomeMesAbrevFormatoMmm(int mes) {

		String nomeMes = "";

		switch (mes) {
		case 0:
			nomeMes = "Jan";
			break;
		case 1:
			nomeMes = "Fev";
			break;
		case 2:
			nomeMes = "Mar";
			break;
		case 3:
			nomeMes = "Abr";
			break;
		case 4:
			nomeMes = "Mai";
			break;
		case 5:
			nomeMes = "Jun";
			break;
		case 6:
			nomeMes = "Jul";
			break;
		case 7:
			nomeMes = "Ago";
			break;
		case 8:
			nomeMes = "Set";
			break;
		case 9:
			nomeMes = "Out";
			break;
		case 10:
			nomeMes = "Nov";
			break;
		case 11:
			nomeMes = "Dez";
			break;
		default:
			nomeMes = String.valueOf(mes);
		}

		return nomeMes;

	}// getNomeMesAbrev ()

	/**
	 * <p>
	 * Retorna o número do mês da abreviacao passada como parametro. OBS.: A
	 * lista de meses comeca em 0.
	 * </p>
	 * 
	 * @param mes
	 * @return String
	 */
	public static int getNrMes(String mes) {

		int nrMes = 0;

		if (mes.equals("JAN")) {
			nrMes = 1;
		} else if (mes.equals("FEV")) {
			nrMes = 2;
		} else if (mes.equals("MAR")) {
			nrMes = 3;
		} else if (mes.equals("ABR")) {
			nrMes = 4;
		} else if (mes.equals("MAI")) {
			nrMes = 5;
		} else if (mes.equals("JUN")) {
			nrMes = 6;
		} else if (mes.equals("JUL")) {
			nrMes = 7;
		} else if (mes.equals("AGO")) {
			nrMes = 8;
		} else if (mes.equals("SET")) {
			nrMes = 9;
		} else if (mes.equals("OUT")) {
			nrMes = 10;
		} else if (mes.equals("NOV")) {
			nrMes = 11;
		} else if (mes.equals("DEZ")) {
			nrMes = 12;
		}

		return nrMes;

	}

	/**
	 * <p>
	 * Retorna uma instancia de Calendar de acordo com a data e hora passadas
	 * como parametro. Em caso de erro e lancada uma Exception com a mensagem
	 * passada no parametro msgErro.
	 * </p>
	 * 
	 * @param data
	 *            - dd/MM/aaaa
	 * @param hora
	 *            - hh:mm:ss
	 * @param recalcularDataInvalida
	 *            - Define se deve ser recalculada a data caso algum dos
	 *            parametros exceda o valor do campo. Caso seja passado false
	 *            uma Excecao sera lancada quando um valor invalido for passado.
	 * @param msgErro
	 *            - Mensagem de erro
	 * @return Calendar
	 * @throws EquifaxException
	 *             Excecao com a mensagem passada no parametro msgErro, ou com a
	 *             mensagem "infos.date" caso o parametro msgErro seja igual a
	 *             null.
	 */
	public static Calendar obterCalendar(String data, String hora, boolean recalcularDataInvalida, String msgErro) throws Exception {

		try {
			// Validando data
			if ((data == null) || data.equals("")) {
				return null;
			}

			// Objeto que sera retornado
			Calendar dtRetorno = GregorianCalendar.getInstance(new Locale("pt","br"));
			dtRetorno.setLenient(recalcularDataInvalida);

			// Obtendo dados da data
			int posBarra1 = data.indexOf("/");
			int posBarra2 = data.lastIndexOf("/");
			int dd = Integer.parseInt(data.substring(0, posBarra1));
			int MM = Integer.parseInt(data.substring(posBarra1 + 1, posBarra2)) - 1;
			int aaaa = Integer.parseInt(data.substring(posBarra2 + 1));

			// Definindo data de retorno
			dtRetorno.set(Calendar.DATE, dd);
			dtRetorno.set(Calendar.MONTH, MM);
			dtRetorno.set(Calendar.YEAR, aaaa);

			dtRetorno.get(Calendar.DATE);

			// Validando hora
			if ((hora != null) && !hora.equals("")) {
				// Obtendo dados da hora
				int posPontos1 = hora.indexOf(":");
				int posPontos2 = hora.lastIndexOf(":");
				int hh = Integer.parseInt(hora.substring(0, posPontos1));
				int mm = Integer.parseInt(hora.substring(posPontos1 + 1, posPontos2));
				int ss = Integer.parseInt(hora.substring(posPontos2 + 1));

				dtRetorno.set(Calendar.HOUR, hh);
				dtRetorno.set(Calendar.MINUTE, mm);
				dtRetorno.set(Calendar.SECOND, ss);
				dtRetorno.set(Calendar.MILLISECOND, 0);
			}

			return dtRetorno;
		} catch (Throwable e) {
			return null;
		}

	} // obterCalendar ()

	/**
	 * <p>
	 * Retorna uma instancia de Calendar a partir de um objeto java.util.Date.
	 * </p>
	 * 
	 * @param data
	 *            - Objeto Date
	 * @return Calendar
	 * @throws EquifaxException
	 *             Excecao com a mensagem passada no parametro msgErro, ou com a
	 *             mensagem "infos.date" caso o parametro msgErro seja igual a
	 *             null.
	 */
	public static Calendar obterCalendar(Date data) throws Exception {

		try {
			// Validando data
			if ((data == null) || data.equals("")) {
				return null;
			}
			// Objeto que sera retornado
			Calendar dtRetorno = GregorianCalendar.getInstance(new Locale("pt","br"));
			dtRetorno.setTime(data);

			return dtRetorno;
		} catch (Throwable e) {
			throw new Exception("efx.data_invalida");
		}

	}

	/**
	 * <p>
	 * Retorna uma instancia de Calendar a partir de um objeto java.util.Date
	 * sem informacao de Data/Hora/Minuto
	 * </p>
	 * 
	 * @author Gustavo Aquino(gaquino@equifax.com.br)
	 * @since 1.7
	 * @param data
	 * @return Calendar
	 * @throws Excecao
	 *             com a mensagem passada no parametro msgErro, ou com a
	 *             mensagem "infos.date" caso o parametro msgErro seja igual a
	 *             null.
	 */
	public static Calendar obterCalendarSemHorario(Date data) throws Exception {

		try {
			// Validando data
			if ((data == null) || data.equals("")) {
				return null;
			}
			// Objeto que sera retornado
			Calendar calRetorno = GregorianCalendar.getInstance(new Locale("pt","br"));
			calRetorno.setTime(data);

			calRetorno.set(Calendar.HOUR, 0);
			calRetorno.set(Calendar.MINUTE, 0);
			calRetorno.set(Calendar.SECOND, 0);

			return calRetorno;
		} catch (Throwable e) {
			throw new Exception("efx.data_invalida");
		}

	}

	/**
	 * <p>
	 * Zera as informacoes de Hora/Minuto/Segundo do calendario para comparacao
	 * somente de datas.
	 * </p>
	 * 
	 * @author Gustavo Aquino(gaquino@equifax.com.br)
	 * @since 1.7
	 * @param data
	 * @throws EquifaxException
	 *             Excecao com a mensagem passada no parametro msgErro, ou com a
	 *             mensagem "infos.date" caso o parametro msgErro seja igual a
	 *             null.
	 */
	public static Calendar zerarHorario(Calendar calendario) throws Exception {

		try {
			// Validando data
			if (calendario != null) {
				// Objeto que sera retornado
				calendario.set(Calendar.HOUR, 0);
				calendario.set(Calendar.MINUTE, 0);
				calendario.set(Calendar.SECOND, 0);
			}
			return calendario;
		} catch (Throwable e) {
			throw new Exception("efx.data_invalida");
		}
	}

	/**
	 * <p>
	 * Retorna uma instancia de Calendar de acordo com a data e hora passadas
	 * como parametro. Em caso de erro e lancada uma Exception com a mensagem
	 * passada no parametro msgErro.
	 * </p>
	 * 
	 * @param data
	 *            - dd/MM/aaaa
	 * @param hora
	 *            - hh:mm:ss
	 * @param msgErro
	 *            - Mensagem de erro
	 * @return Calendar
	 * @throws EquifaxException
	 *             Excecao com a mensagem passada no parametro msgErro, ou com a
	 *             mensagem "infos.date" caso o parametro msgErro seja igual a
	 *             null.
	 */
	public static Calendar obterCalendar(String data, String hora, String msgErro) throws Exception {

		return obterCalendar(data, hora, true, msgErro);

	} // obterCalendar ()

	/**
	 * <p>
	 * Retorna uma instancia de Calendar de acordo com a data como parametro. Em
	 * caso de erro e lancada uma Exception de data invalida.
	 * </p>
	 * 
	 * @param data
	 * @param recalcularDataInvalida
	 * @return
	 * @throws EquifaxException
	 */
	public static Calendar obterCalendar(String data, boolean recalcularDataInvalida) throws Exception {

		return obterCalendar(data, null, recalcularDataInvalida, null);

	}// obterCalendar ()

	/**
	 * <p>
	 * Retorna uma instancia de Calendar de acordo com a data passada como
	 * parametro. Em caso de erro e lancada uma EquifaxException com a mensagem
	 * passada no parametro msgErro.
	 * </p>
	 * 
	 * @param data
	 *            - dd/MM/aaaa
	 * @param msgErro
	 *            - Mensagem de erro
	 * @return Calendar
	 * @throws EquifaxException
	 * @see DateUtils.obterCalendar(String data, String hora, String msgErro)
	 */
	public static Calendar obterCalendarData(String data, String msgErro) throws Exception {

		return obterCalendar(data, null, msgErro);

	} // obterCalendarData ()

	/**
	 * <p>
	 * Retorna uma instancia de Calendar de acordo com a data passada como
	 * parametro. Em caso de erro e lancada uma EquifaxException com a mensagem
	 * padrao de data invalida.
	 * </p>
	 * 
	 * @param data
	 *            - dd/MM/aaaa
	 * @return Calendar
	 * @throws EquifaxException
	 * @see DateUtils.obterCalendar(String data, String hora, String msgErro)
	 */
	public static Calendar obterCalendarData(String data) throws Exception {

		return obterCalendar(data, null, null);

	} // obterCalendarData ()

	/**
	 * <p>
	 * Retorna uma instancia de Calendar de acordo com a data passada como
	 * parametro. A hora gerada sera 00:00:00. Em caso de erro e lancada uma
	 * EquifaxException com a mensagem passada no parametro msgErro.
	 * </p>
	 * 
	 * @param data
	 *            - dd/MM/aaaa
	 * @param msgErro
	 *            - Mensagem de erro
	 * @return Calendar
	 * @throws EquifaxException
	 * @see DateUtils.obterCalendar(String data, String hora, String msgErro)
	 */
	public static Calendar obterCalendarZeroHora(String data, String msgErro) throws Exception {

		String hora = "00:00:00";
		return obterCalendar(data, hora, msgErro);

	} // obterCalendarZeroHora ()

	/**
	 * <p>
	 * Retorna uma instancia de Calendar de acordo com a data passada como
	 * parametro. A hora gerada sera 00:00:00. Em caso de erro e lancada uma
	 * EquifaxException com a mensagem padrao de data invalida.
	 * </p>
	 * 
	 * @param data
	 *            - dd/MM/aaaa
	 * @return Calendar
	 * @throws EquifaxException
	 * @see DateUtils.obterCalendar(String data, String hora, String msgErro)
	 */
	public static Calendar obterCalendarZeroHora(String data) throws Exception {

		String hora = "00:00:00";
		return obterCalendar(data, hora, null);

	} // obterCalendarZeroHora ()

	/**
	 * <p>
	 * Retorna a data como String no formato dd/MM/aaaa.
	 * </p>
	 * 
	 * @param data
	 * @return String
	 */
	public static String toStringCalendar(Calendar data) {

		return toStringCalendar(data, "dd/MM/yyyy");

	} // toStringCalendar ()

	/**
	 * <p>
	 * Retorna a data como String no formato definido pelo parametro pattern.
	 * </p>
	 * 
	 * @param data
	 * @param pattern
	 * @return String
	 */
	public static String toStringCalendar(Calendar data, String pattern) {

		Date date = data.getTime();
		SimpleDateFormat formato = new SimpleDateFormat(pattern);

		return formato.format(date);

	}// toStringCalendar ()

	/**
	 * <p>
	 * Recebe um timestamp e retorna um calendar equivalente.
	 * </p>
	 * 
	 * @param ts
	 * @return Calendar
	 */
	public static Calendar tsToCalendar(Timestamp ts) {
		Calendar cal = null;
		try {
			Date dtBr = new Date(ts.getTime());
			cal = GregorianCalendar.getInstance(new Locale("pt","br"));

			cal.setTime(dtBr);
		} catch (Exception e) {
			return null;
		}

		return cal;
	} // tsToCalendar ()

	/**
	 * <p>
	 * Recebe um timestamp e retorna um calendar equivalente.
	 * </p>
	 * 
	 * @param ts
	 * @return Calendar
	 */
	public static Calendar tsToCalendarSQL(java.sql.Date date) {
		Calendar cal = null;
		try {
			cal = Calendar.getInstance();
			cal.setTime(date);
		} catch (Exception e) {
			return null;
		}

		return cal;
	} // tsToCalendar ()

	/**
	 * compara duas datas, se forem iguais retorna 0, se a primeira eh maior
	 * retorna -1, se a segunda for maior retorna 1, desprezando horas, minutos
	 * e segundos
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public static int compararDatas(Date arg0, Date arg1) {

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(arg0);
		c2.setTime(arg1);

		return compararDatas(c1, c2);

	}

	/**
	 * compara duas datas, se forem iguais retorna 0, se a primeira eh maior
	 * retorna -1, se a segunda for maior retorna 1, desprezando horas, minutos
	 * e segundos
	 * 
	 * Se a data de vencimento cair no fim de semana, ela é automaticamente colocada para
	 * segunda-feira
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public static int compararDatasDescontandoFimDeSemanaEFeriado(Date dtPg, Date dtVenc) {
		
		Calendar cPg = GregorianCalendar.getInstance(new Locale("pt","br"));
		Calendar cVenc = GregorianCalendar.getInstance(new Locale("pt","br"));
		
		cPg.setTime(dtPg);
		cVenc.setTime(dtVenc);
		
		if(cVenc.get(Calendar.DAY_OF_WEEK) == 7){
			cVenc.set(Calendar.DAY_OF_MONTH, cVenc.get(Calendar.DAY_OF_MONTH) + 2);
		}else if(cVenc.get(Calendar.DAY_OF_WEEK) == 1){
			cVenc.set(Calendar.DAY_OF_MONTH, cVenc.get(Calendar.DAY_OF_MONTH) + 1);
		}
		
		return compararDatas(cPg, cVenc);
		
	}
	
	/**
	 * Retorna a idade apartir de uma data de nascimento
	 * 
	 * @param dataNascimento
	 * @return
	 * @author Gustavo Aquino (gustavo.aquino@equifax.com.br)
	 */
	public static int obterIdade(Date dataNascimento) {
		Calendar cal = Calendar.getInstance();
		Calendar calAtual = Calendar.getInstance();
		calAtual.setTime(new Date());

		cal.setTime(dataNascimento);
		int anoNascimento = cal.get(Calendar.YEAR);
		int anoAtual = calAtual.get(Calendar.YEAR);
		int idade = anoAtual - anoNascimento;

		// Iguala o ano parar comparar meses e dias do nascimento
		cal.set(Calendar.YEAR, anoAtual);

		if (cal.after(calAtual)) {
			idade = idade - 1;
		}

		return idade;
	}

	/**
	 * compara duas datas, se forem iguais retorna false, <br>
	 * se a primeira eh maior retorna true,<br>
	 * se a segunda for maior retorna false,<br>
	 * desprezando horas, minutos e segundos
	 * 
	 * @param primeira
	 * @param segunda
	 * @return boolean
	 */

	public static boolean aPrimeiraEhMaiorQueSegunda(Date primeira, Date segunda) {

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(primeira);
		c2.setTime(segunda);

		int day = c1.get(Calendar.DATE);
		int month = c1.get(Calendar.MONTH);
		int year = c1.get(Calendar.YEAR);

		int day2 = c2.get(Calendar.DATE);
		int month2 = c2.get(Calendar.MONTH);
		int year2 = c2.get(Calendar.YEAR);

		if (year > year2) {
			return true;
		} else if (year2 > year) {
			return false;
		} else if (year == year2) {
			if (month > month2) {
				return true;
			} else if (month2 > month) {
				return false;
			} else if (month == month2) {
				if (day > day2) {
					return true;
				} else if (day < day2) {
					return false;
				}
			}
		}

		return false;
	}

	/**
	 * 
	 * @param c1
	 * @param c2
	 * @return
	 */

	private static int compararDatas(Calendar c1, Calendar c2) {

		int day = c1.get(Calendar.DATE);
		int month = c1.get(Calendar.MONTH);
		int year = c1.get(Calendar.YEAR);

		int day2 = c2.get(Calendar.DATE);
		int month2 = c2.get(Calendar.MONTH);
		int year2 = c2.get(Calendar.YEAR);

		if (year > year2) {
			return -1;
		} else if (year2 > year) {
			return 1;
		} else if (year == year2) {
			if (month > month2) {
				return -1;
			} else if (month2 > month) {
				return 1;
			} else if (month == month2) {
				if (day > day2) {
					return -1;
				} else if (day < day2) {
					return 1;
				}
			}
		}

		return 0;
	}

	/**
	 * Retorna uma String com a hora atual no formato hh:mm:ss
	 * 
	 * @return
	 */
	public static String getHoraAtual() {
		Calendar cal = Calendar.getInstance();

		String hora = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));

		hora = (hora.toCharArray().length < 2) ? ("0" + hora) : hora;

		String minuto = String.valueOf(cal.get(Calendar.MINUTE));

		minuto = (minuto.toCharArray().length < 2) ? ("0" + minuto) : minuto;

		String segundo = String.valueOf(cal.get(Calendar.SECOND));

		segundo = (segundo.toCharArray().length < 2) ? ("0" + segundo) : segundo;

		String horaCompleta = hora + ":" + minuto + ":" + segundo;

		return horaCompleta;
	} // getHoraAtual

	/**
	 * Obtem a data atual no formato dd/MM/yyyy.
	 * 
	 * @return String
	 */
	public static String getDataAtual() {

		Calendar data = GregorianCalendar.getInstance(new Locale("pt","br"));
		return toStringCalendar(data);

	}// getDataAtual ()

	/**
	 * Retorna uma data no formato DDMMAAAAHHMMSS que vai ser utilizada para
	 * gerar o arquivo de retorno.
	 * 
	 * @author Telesforo Lacerda Pereira da Cruz.
	 * @return
	 */
	public static String obterDataArquivoRetorno() {

		return formatar(new Date(), DateUtils.FORMATO_DATA_DD_MM_AAAA + DateUtils.FORMATO_HORA_HH_MM_SS);

	} // fim obterDataArquivoRetorno

	/**
	 * Retorna a data informada com o valor da hora igual a 00:00:00.0
	 * 
	 * @author eandrade
	 * @param entrada
	 * @return A data de entrada a zero hora.
	 */
	public static Date obterDataZeroHora(Date data) {

		Calendar calendario = GregorianCalendar.getInstance(new Locale("pt","br"));
		calendario.setTime(data);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.MINUTE, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);

		return calendario.getTime();

	}

	/**
	 * Valida se uma String possui uma data valida e se esta no pattern
	 * informado.
	 * 
	 * @param data
	 * @param pattern
	 * @return true se a data e valida, false caso contrario.
	 */
	public static boolean isDataValida(String data, String pattern) {

		boolean valida = true;

		try {

			Date dtFormatada = obterData(data, pattern);
			valida = (dtFormatada == null) ? false : valida;

		} catch (ParseException e) {
			valida = false;
		} // try - catch

		return valida;
	} // isDataValida()

	public static int obterMesReal(Date data) throws Exception {
		Calendar cal = DateUtils.obterCalendar(data);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static int obterAno(Date data) throws Exception {
		Calendar cal = DateUtils.obterCalendar(data);
		return cal.get(Calendar.YEAR);
	}

	public static int obterIntervaloDias(Date d1, Date d2) {
		int result = (int) ((d1.getTime() - d2.getTime()) / (24 * 60 * 60 * 1000));
		return result < 0 ? result * -1 : result;
	}
	
	/**
	 * retorna a quantidade de dias D1 - D2
	 * 
	 * @param Date d1
	 * @param Date d2
	 * @return quantidade de dias - int
	 * 
	 */
	public static int obterIntervaloDiasComSinal(Date d1, Date d2) {
		try {
			int result = (int) ((d1.getTime() - d2.getTime()) / (24 * 60 * 60 * 1000));
			return result;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static Timestamp traduzDataStringToTimeStamp(String strData) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date data;
		try {
			if(strData != null && !"".equalsIgnoreCase(strData)){
				data = sdf.parse(strData);
				Timestamp ts = new Timestamp(data.getTime());
				return ts;
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public static Timestamp traduzDataStringToTimeStampFinal(String strData) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		Date data;
		try {
			if(strData != null && !"".equalsIgnoreCase(strData)){
				data = sdf.parse(strData + " 23:59:59");
				Timestamp ts = new Timestamp(data.getTime());
				return ts;
			}else{
				data = sdf.parse(sdf2.format(new Date(System.currentTimeMillis())) + " 23:59:59");
				Timestamp ts = new Timestamp(data.getTime());
				return ts;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	public static int obterIntervaloMeses(Date dataMaior, Date dataMenor) throws Exception {

		Calendar calMaior = GregorianCalendar.getInstance(new Locale("pt","br"));
		calMaior.setTime(dataMaior);
		Calendar calMenor = GregorianCalendar.getInstance(new Locale("pt","br"));
		calMenor.setTime(dataMenor);

		int resultado = 0;

		while (calMaior.before(calMenor)) {
			calMenor.add(Calendar.MONTH, 1);
			resultado++;
		}

		return resultado;
	}

	/**
	 * devolve um Long de acordo com o tempo passado.
	 * 
	 * @param hora
	 * @param minuto
	 * @param segundo
	 * @return Long, time em milisegundos.
	 */
	public static long calculaTempo(int i, int j, int k) {
		long hora = i * (1000 * 60 * 60);
		long minuto = j * (1000 * 60);
		long segundo = k * (1000);
		return hora + minuto + segundo;
	}

} // DateUtils
