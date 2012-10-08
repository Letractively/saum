package br.com.meganet.conexoesBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author efren
 *
 */
public class Select {
    
    public ResultSet executaSelect(PreparedStatement stm) {
        
        try {
            
            ResultSet rs = stm.executeQuery();
            return rs;
            
        } catch (SQLException ex) {
            System.out.println("Erro na Sintaxe do SQL");
            System.out.println("///////////////////////////////////////////////////////////////////////////");
            ex.printStackTrace();
            System.out.println("//////////////////////////////////////////////////////////////////////////");
            return null;
        }
    }
}
