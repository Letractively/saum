package br.com.meganet.conexoesBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author efren
 */
public class Insert {
    
   public int insert(PreparedStatement stm){
        try {
            
            return stm.executeUpdate();
            
        } catch (SQLException ex) {
            
            System.out.println("Erro na inserção");
            System.out.println("///////////////////////////////////////////////////////////////////////////");
            ex.printStackTrace();
            System.out.println("//////////////////////////////////////////////////////////////////////////");
            return 0;
            
        }
       
   }
    
}
