package br.com.meganet.daoSQL.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.meganet.conexoesBD.AbreConexao;
import br.com.meganet.hbm.vo.BoletosGerados;


public class AlteraLoginBatch extends AbreConexao{
	static Connection con = null;
	
	public static void main(String[] args){
		AlteraLoginBatch alb = new AlteraLoginBatch();
		con = alb.conecta();
		
		PreparedStatement stm;
		PreparedStatement stmInsert;
		try {
			stm = con.prepareStatement("select * from boletos_gerados where boletosgerados_pagou_em_mao = true");
			ResultSet rs = stm.executeQuery();
			
			List<BoletosGerados> lsBol = new ArrayList<BoletosGerados>();
			
			while (rs.next()) {
				BoletosGerados bg = new BoletosGerados();
				bg.setBoletosgeradosId(rs.getLong("boletosgerados_id"));
				bg.setBoletosgeradosValorPago(rs.getString("boletosgerados_valor_pago_em_mao"));
				lsBol.add(bg);
			}
			
			stmInsert = con.prepareStatement("update boletos_gerados set boletosgerados_valor_pago = ? where boletosgerados_id = ?");
			for (Iterator<BoletosGerados> iterator = lsBol.iterator(); iterator.hasNext();) {
				BoletosGerados b = (BoletosGerados) iterator.next();
				stmInsert.setString(1, b.getBoletosgeradosValorPago());
				stmInsert.setLong(2, b.getBoletosgeradosId());
				stmInsert.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
