/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

import org.json.JSONObject;
import java.sql.*;
import java.io.File;

/**
 *
 * @author waik
 */
@Named(value = "BD")
@RequestScoped
public class BD {
    private Connection m_Connexion = null;
    private Statement m_Demande = null;
    private ResultSet m_Resultat = null;
    
    
    public BD() throws SQLException {
        //Instanciation de la classe
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            m_Connexion = DriverManager.getConnection("jdbc:mysql://localhost/davidd?user=root");
        } catch (Exception e) {
            return;
        }
        
        // Initialisation des demandes    
        m_Demande = m_Connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
            ResultSet.CONCUR_READ_ONLY);
    }
    
    
    
    
    
    
}
