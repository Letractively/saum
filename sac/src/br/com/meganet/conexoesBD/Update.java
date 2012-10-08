/*
 * Update.java
 *
 * Created on 29 de Maio de 2007, 23:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.meganet.conexoesBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author efren
 */
public class Update {
    
    public int executaUpdate(PreparedStatement stm) {
        
        try {
            
            return stm.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Erro na Sintaxe do SQL de update");
            System.out.println("///////////////////////////////////////////////////////////////////////////");
            ex.printStackTrace();
            System.out.println("//////////////////////////////////////////////////////////////////////////");
            return 0;
        }
    }
    
}
