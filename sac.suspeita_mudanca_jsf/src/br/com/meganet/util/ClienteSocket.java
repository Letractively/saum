package br.com.meganet.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;



public class ClienteSocket {
	
    // Create a socket with a timeout
	public Socket  Conecta(String host, int porta, int timeoutMs) throws IOException
	{
	      // Create an unbound socket
		 Socket sock = new Socket();
	     InetAddress addr = InetAddress.getByName(host);
	     SocketAddress sockaddr = new InetSocketAddress(addr, porta);       
	    
	        // This method will block no more than timeoutMs.
	        // If the timeout occurs, SocketTimeoutException is thrown.	        
	        sock.connect(sockaddr, timeoutMs); 
	    
		return sock;
	}
	
	public void  transmite(Socket socket, String texto)
	{
		try 
		{
	        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	        wr.write(texto);
	        wr.flush();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }		
	}
	
	
	public String  recebe(Socket socket)
	{
		 StringBuffer sb = new StringBuffer();
		try 
		{
	        BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        String str;
	        while ((str = rd.readLine()) != null) {
	            sb.append(str);
	            sb.append("\n");
	        }
	        rd.close();
	    } catch (IOException e) 
	    {
	    	e.printStackTrace();
	    }
	    
	    return sb.toString();
	
	}
   public static void main(String args[])
	{
		ClienteSocket cs = new ClienteSocket();
		Socket socket = null;
		String xml = "escrever o xlm aqui para executar o teste";
		try {
			socket = cs.Conecta("10.61.178.175", 8778, 30000);
			cs.transmite(socket, xml);
			xml = cs.recebe(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
