package br.com.meganet.conexoesBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbreConexao {

	private Connection con;
	private String DRIVER = "org.postgresql.Driver";
	private String CONEXAO = "jdbc:postgresql://localhost:5432/Meganet";
	private String USR = "postgres";
	private String SEN = "postgres";
	
	

	public Connection conecta() {
		try {
			if (con == null || con.isClosed()) {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(CONEXAO, USR, SEN);
			}
			return con;
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver não encontrado");
			System.out.println("/////////////////////////////////////////////////////////////////////");
			ex.printStackTrace();
			System.out.println("/////////////////////////////////////////////////////////////////////");
			return null;
		} catch (SQLException sqlEx) {
			System.out.println("Conexão falhou");
			System.out.println("/////////////////////////////////////////////////////////////////////");
			sqlEx.printStackTrace();
			System.out.println("/////////////////////////////////////////////////////////////////////");
			return null;
		}
	}

}

