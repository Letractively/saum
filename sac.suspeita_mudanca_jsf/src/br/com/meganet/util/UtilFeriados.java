package br.com.meganet.util;

/*
 * Copyright (c) 2005 Tiago Fernandez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * &#91;url&#93;http://www.apache.org/licenses/LICENSE-2.0&#91;/url&#93;
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class UtilFeriados {

	private static final SimpleDateFormat sdfDM = new SimpleDateFormat("dd-MM", new Locale("pt", "br"));
	private final static long DAY_IN_MILLIS = 86400000;
	private static String[] feriadosNacionais = {"01-01", "21-04", "01-05", "07-09", "12-10", "02-11", "15-11", "25-12"};
	private static String[] feriadosEstaduais = ConfigUtil.getInstance().getProperty("feriados_estaduais", "30-11").split(",");

	public static Date getCarnival(int year) {
		return getSubtractedDays(getDomingoDePascoa(year), 47);
	}

	public static Date getCorpusChristi(int year) {
		return getAddedDays(getDomingoDePascoa(year), 60);
	}
	
	/**
	 * 
	 * @param data formato dd-MM
	 * @return int - dias a serem adicionados para o primeiro dia útil
	 * @throws ParseException 
	 */
	public static Calendar prorrogarDataParaFeriadoEFimDeSemana(String data) throws ParseException{
		return prorrogarDataParaFeriadoEFimDeSemana(sdfDM.parse(data));
	}
	
	/**
	 * 
	 * @param dia
	 * @param mes
	 * @return int - dias a serem adicionados para o primeiro dia útil
	 * @throws ParseException 
	 */
	public static Calendar prorrogarDataParaFeriadoEFimDeSemana(String dia, String mes) throws ParseException{
		return prorrogarDataParaFeriadoEFimDeSemana(sdfDM.parse(dia + "-" + mes));
	}
	
	/**
	 * @param data
	 * @return int - dias a serem adicionados para o primeiro dia útil
	 * @throws ParseException 
	 */
	public static Calendar prorrogarDataParaFeriadoEFimDeSemana(Date d) throws ParseException{

		Calendar hoje = GregorianCalendar.getInstance(new Locale("pt","br"));
		hoje.setTime(new Date(System.currentTimeMillis()));
		
		Calendar dataASerVerificada = new GregorianCalendar();
		dataASerVerificada.setTime(d);
		dataASerVerificada.set(Calendar.HOUR_OF_DAY, 0);
		dataASerVerificada.set(Calendar.HOUR, 0);
		dataASerVerificada.set(Calendar.MINUTE, 0);
		dataASerVerificada.set(Calendar.SECOND, 0);
		dataASerVerificada.set(Calendar.MILLISECOND, 0);
		
		dataASerVerificada.set(Calendar.DAY_OF_MONTH, dataASerVerificada.get(Calendar.DAY_OF_MONTH) + adicionaFeriadosNacionais(hoje, dataASerVerificada));
		dataASerVerificada.set(Calendar.DAY_OF_MONTH, dataASerVerificada.get(Calendar.DAY_OF_MONTH) + adicionaFeriadosEstaduais(hoje, dataASerVerificada));
		
		Date c = getCarnival(dataASerVerificada.get(Calendar.YEAR));
		Calendar carnaval = GregorianCalendar.getInstance(new Locale("pt","br"));
		carnaval.setTime(c);
		carnaval.set(Calendar.HOUR_OF_DAY, 0);
		carnaval.set(Calendar.HOUR, 0);
		carnaval.set(Calendar.MINUTE, 0);
		carnaval.set(Calendar.SECOND, 0);
		carnaval.set(Calendar.MILLISECOND, 0);
		if(compararDatas(carnaval, dataASerVerificada) == 0){
			dataASerVerificada.set(Calendar.DAY_OF_MONTH, dataASerVerificada.get(Calendar.DAY_OF_MONTH) + 2);
		}
		
		Date cc = getCorpusChristi(dataASerVerificada.get(Calendar.YEAR));
		Calendar corpusCristi = GregorianCalendar.getInstance(new Locale("pt","br"));
		corpusCristi.setTime(cc);
		corpusCristi.set(Calendar.HOUR_OF_DAY, 0);
		corpusCristi.set(Calendar.HOUR, 0);
		corpusCristi.set(Calendar.MINUTE, 0);
		corpusCristi.set(Calendar.SECOND, 0);
		corpusCristi.set(Calendar.MILLISECOND, 0);
		if(compararDatas(corpusCristi, dataASerVerificada) == 0){
			dataASerVerificada.set(Calendar.DAY_OF_MONTH, dataASerVerificada.get(Calendar.DAY_OF_MONTH) + 1);
		}
		
		Date p = getDomingoDePascoa(dataASerVerificada.get(Calendar.YEAR));
		Calendar sextaPaixao = GregorianCalendar.getInstance(new Locale("pt","br"));
		sextaPaixao.setTime(p);
		sextaPaixao.set(Calendar.DAY_OF_MONTH, sextaPaixao.get(Calendar.DAY_OF_MONTH) - 2);
		sextaPaixao.set(Calendar.HOUR_OF_DAY, 0);
		sextaPaixao.set(Calendar.HOUR, 0);
		sextaPaixao.set(Calendar.MINUTE, 0);
		sextaPaixao.set(Calendar.SECOND, 0);
		sextaPaixao.set(Calendar.MILLISECOND, 0);
		if(compararDatas(sextaPaixao, dataASerVerificada) == 0){
			dataASerVerificada.set(Calendar.DAY_OF_MONTH, dataASerVerificada.get(Calendar.DAY_OF_MONTH) + 3);
		}
		
		if(dataASerVerificada.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){//é domingo
			dataASerVerificada.set(Calendar.DAY_OF_MONTH, dataASerVerificada.get(Calendar.DAY_OF_MONTH) + 1);
		}else if(dataASerVerificada.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){//é sabado
			dataASerVerificada.set(Calendar.DAY_OF_MONTH, dataASerVerificada.get(Calendar.DAY_OF_MONTH) + 2);
		}

		return dataASerVerificada;
	}

	
	private static int adicionaFeriadosNacionais(Calendar hoje, Calendar dataASerVerificada) throws ParseException{
		for (String fn : feriadosNacionais) {
			Date dtFeriado = sdfDM.parse(fn);
			Calendar feriado = GregorianCalendar.getInstance(new Locale("pt","br"));
			feriado.setTime(dtFeriado);
			feriado.set(Calendar.YEAR, dataASerVerificada.get(Calendar.YEAR));
			feriado.set(Calendar.HOUR_OF_DAY, 0);
			feriado.set(Calendar.HOUR, 0);
			feriado.set(Calendar.MINUTE, 0);
			feriado.set(Calendar.SECOND, 0);
			feriado.set(Calendar.MILLISECOND, 0);
			
			if(compararDatas(feriado, dataASerVerificada) == 0){
				return 1;
			}
		}
		return 0;
	}
	
	private static int adicionaFeriadosEstaduais(Calendar hoje, Calendar dataASerVerificada) throws ParseException{
		for (String fn : feriadosEstaduais) {
			Date dtFeriado = sdfDM.parse(fn);
			Calendar feriado = GregorianCalendar.getInstance(new Locale("pt","br"));
			feriado.setTime(dtFeriado);
			feriado.set(Calendar.YEAR, dataASerVerificada.get(Calendar.YEAR));
			feriado.set(Calendar.HOUR_OF_DAY, 0);
			feriado.set(Calendar.HOUR, 0);
			feriado.set(Calendar.MINUTE, 0);
			feriado.set(Calendar.SECOND, 0);
			feriado.set(Calendar.MILLISECOND, 0);
			if(compararDatas(feriado, dataASerVerificada) == 0){
				return 1;
			}
		}
		return 0;
	}
	
	public static Date getDomingoDePascoa(int year) {

		int a = year % 19;
		int b = year / 100;
		int c = year % 100;
		int d = b / 4;
		int e = b % 4;
		int g = (8 * b + 13) / 25;
		int h = (19 * a + b - d - g + 15) % 30;
		int j = c / 4;
		int k = c % 4;
		int m = (a + 11 * h) / 319;
		int r = (2 * e + 2 * j - k - h + m + 32) % 7;
		int n = (h - m + r + 90) / 25;
		int p = (h - m + r + n + 19) % 32;

		Calendar calendar = GregorianCalendar.getInstance(new Locale("pt","br"));
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, n - 1);
		calendar.set(Calendar.DAY_OF_MONTH, p);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();

	}

	private static Date getAddedDays(Date date, long numOfDays) {
		return new Date(date.getTime() + (numOfDays * DAY_IN_MILLIS));
	}

	private static Date getSubtractedDays(Date date, long numOfDays) {
		return new Date(date.getTime() - (numOfDays * DAY_IN_MILLIS));
	}
	
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

	public static void main(String[] args){
		try {
			SimpleDateFormat sdfss = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = new GregorianCalendar();
			cal.setTime(sdfss.parse("01/01/2010"));

			System.out.println("Dias para adicionar: " + sdfss.format(prorrogarDataParaFeriadoEFimDeSemana(cal.getTime()).getTime()));
		
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}
}