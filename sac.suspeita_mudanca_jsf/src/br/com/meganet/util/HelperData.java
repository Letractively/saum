package br.com.meganet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HelperData {
	
	Calendar calendario;
	
	public HelperData()
	{
		calendario = Calendar.getInstance();
		calendario.setTime(new Date(System.currentTimeMillis()));
	}
	
	public void addMes(int mes)
	{
		calendario.add(Calendar.MONTH, mes);		
	}
	
	public void addAno(int ano)
	{
		calendario.add(Calendar.YEAR, ano);		
	}
	
	
	
	public String getMesAnoFormatoBR(String formato)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(formato, new Locale("pt","BR"));
		return sdf.format(calendario.getTime());
	}

	public Calendar getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendar calendario) {
		this.calendario = calendario;
	}
	

	public static Date getDiaHoje() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			throw new Error(e);
		}
	}
}
