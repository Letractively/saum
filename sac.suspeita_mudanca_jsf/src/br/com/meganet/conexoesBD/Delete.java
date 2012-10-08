package br.com.meganet.conexoesBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author efren
 */
public class Delete {
    
    public int delete(PreparedStatement stm){
        
        try {
            
            return stm.executeUpdate();
            
        } catch (SQLException ex) {
            
            System.out.println("Erro na deleção");
            System.out.println("///////////////////////////////////////////////////////////////////////////");
            ex.printStackTrace();
            System.out.println("//////////////////////////////////////////////////////////////////////////");
            return 0;
            
        }

    }
    
}
