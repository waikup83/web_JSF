/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP_Final;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author waiku
 */
@Named(value = "commentaires")
@SessionScoped
public class commentaires implements Serializable {
    private Connection Connexion;
    private Statement Demande;
    private String Commentaire = "";
    private String[] Commentaires;
    
    public void setCommentaire(String s){
        Commentaire = s;
    }
    public String getCommentaire(){
        return Commentaire;
    }
    public String[] getCommentaires(){
        return Commentaires;
    }  
    
    
    public commentaires() throws SQLException {
        //Instanciation de la classe
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connexion = DriverManager.getConnection("jdbc:mysql://localhost/davidd?user=root");
        } catch (Exception e) {
            return;
        }
        
        Demande = Connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
            ResultSet.CONCUR_READ_ONLY);
    }
    
    
    // Chercher commentaires d'une image
    public void VoirCommentaire(String Param) throws SQLException {
        ResultSet Resultat = null;
        int i;
        
        String Requete = "SELECT * FROM commentaires c "
            + "LEFT JOIN utilisateurs u ON c.ID_Util = u.ID_Util "
            + "WHERE c.ID_Image='" + Param + "' "
            + "ORDER BY Date_Comm DESC";
        
        try {
            Resultat = Demande.executeQuery(Requete);
            Commentaires = new String[10];
            Resultat.first();
            i = 0;
            while(i < 10 && !Resultat.isAfterLast()) {
                Commentaires[i] = Resultat.getString("Utilisateur") + ": "
                    + Resultat.getString("Commentaire");
                
                Resultat.next();
                i++;
            }

        } catch (SQLException SQLe) {
        }
        finally {
            //Fermeture de la requête
            Resultat.close();
        }
    }
    
    
    public void AjouterCommentaire(String ID_Image, String ID_Util) throws SQLException {
        ResultSet Resultat = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connexion = DriverManager.getConnection("jdbc:mysql://localhost/davidd?user=root");
        } catch (Exception e) {
            return;
        }
        
        Demande = Connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
            ResultSet.CONCUR_READ_ONLY);
        
        String Requete = "INSERT INTO commentaires(ID_Util, ID_Image, Commentaire)"
            + " VALUES ('" + ID_Util + "', '" + ID_Image + "', '" + Commentaire + "')";
   
        try {
            Demande.executeUpdate(Requete);
        } catch (SQLException SQLe) {
            return;
        }
    }
    
}
