package br.com.meganet.telnet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import br.com.meganet.telnet.exception.TelnetConnectionException;

public class TelnetJS {

	private static Main helper = Main.getMain();
	
	
	public static void main(String[] args) {
		try {
			TelnetJS tel = new TelnetJS();
			String con = "";
			try {
				con = tel.connectTelnet("187.5.67.237", "3129");
			} catch (Exception e) {
				con = tel.connectTelnet("10.4.0.1", "3129");
			}
			System.out.println(con);
			while(true){
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  
				String userId = "";  
				userId = reader.readLine();
				if(userId.equalsIgnoreCase("qq")){
					tel.fecharConexao();
				}else{
					
					String dd = tel.sendCommand(userId);
					System.out.println(dd);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 			
	}
	
	
	
	public String sendCommand(String userMessage) {
		String retorno = helper.sendMessage(userMessage);
		return retorno;
	}
	public String connectTelnet(String host, String port) throws TelnetConnectionException {
		helper= Main.getMain();
		String retorno = helper.connectTelnet(host, port);		
		return retorno;
	}
	public void fecharConexao() {
		helper.fecharConexao();
	}
}
