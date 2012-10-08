package br.com.meganet.telnet;

import java.util.Properties;

import br.com.meganet.telnet.connection.TelnetConnection;
import br.com.meganet.telnet.exception.TelnetConnectionException;


public class Main {
	private final String LOOPBACK = "0";
	private final String OPEN_TIMEOUT = "100";
	private final String READ_TIMEOUT = "100";
	private final String WAIT_TIMEOUT = "10000";
	private static TelnetConnection tc = null;
	private static Main singleton = null;
	private static StringBuffer strTotal = null;
	
	protected Main() {
		
	}
	
	public static Main getMain() {
		if (singleton==null) {
			singleton = new Main(); 
			tc = new TelnetConnection();
			strTotal = new StringBuffer();
		}
		return singleton;
}
	
	public String connectTelnet(String host, String port) throws TelnetConnectionException {
		
		Properties propertiesCommParams = new Properties();
		propertiesCommParams.put("LOOPBACK", LOOPBACK);
		propertiesCommParams.put("OPEN_TIMEOUT", OPEN_TIMEOUT);
		propertiesCommParams.put("READ_TIMEOUT", READ_TIMEOUT);
		propertiesCommParams.put("WAIT_TIMEOUT", WAIT_TIMEOUT);
		propertiesCommParams.put("HOST_IPADDR", host);
		propertiesCommParams.put("HOST_PORT", port);
		return strTotal.append(tc.connectStream(propertiesCommParams, tc)).toString();
	}
	
	public String sendMessage(String message) {
		try {
			
			String sendCommand = tc.sendCommand(message);
			byte[] array = sendCommand.getBytes();
			char[] array_tratado = new char[array.length];
			for (int i = 0; i < array.length; i++) {
				if(array[i]!=0){
					array_tratado[i] = (char) array[i]; 
				} else {
					array_tratado[i] = (char) 32;
				}
			}
			return strTotal.append(array_tratado).toString();
		} catch (TelnetConnectionException e) {
			e.printStackTrace();
			return "Não foi possível enviar seu comando";
		}
	}
	
	public String sendMessage2(String message, long time) {
		try {
			
			String sendCommand = tc.sendCommand2(message, time);
			byte[] array = sendCommand.getBytes();
			char[] array_tratado = new char[array.length];
			for (int i = 0; i < array.length; i++) {
				if(array[i]!=0){
					array_tratado[i] = (char) array[i]; 
				} else {
					array_tratado[i] = (char) 32;
				}
			}
			StringBuffer tmp = new StringBuffer();
			return tmp.append(array_tratado).toString();
		} catch (TelnetConnectionException e) {
			e.printStackTrace();
			return "Não foi possível enviar seu comando";
		}
	}
	
	public void fecharConexao() {
		try {
			tc.disconnect();
			singleton = null;
			tc = null;
			strTotal = null;
		} catch (TelnetConnectionException e) {
			e.printStackTrace();
		}
	}
	
	

	
}
