/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP_Final;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

import java.sql.*;
import org.json.JSONObject;
import org.json.JSONException;

/**
 *
 * @author waiku
 */
@Named(value = "bd")
@SessionScoped
public class bd implements Serializable {
    private Connection Connexion;
    private Statement Demande;
    private JSONObject Image;
    private commentaires Commentaires;
    
    
    public JSONObject getImage(){
        return Image;
    }
    public String[] getCommentaires() {
        return Commentaires.getCommentaires();
    }
    
    
    // Constructeur 
    public bd() throws SQLException, JSONException {
        //Instanciation de la classe
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connexion = DriverManager.getConnection("jdbc:mysql://localhost/davidd?user=root");
        } catch (Exception e) {
            return;
        }
        
        Demande = Connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
            ResultSet.CONCUR_READ_ONLY);
        
        Commentaires = new TP_Final.commentaires();
                
        this.Demande("-1");
    }

    
    // Demande de photos
    public void Demande(String Param) throws JSONException, SQLException {
        ResultSet Resultat = null;
        String Requete = "SELECT * FROM images ORDER BY Date_Image DESC";
        
        if (Param.equals("Precedent"))
            Param = Image.getString("IDPrecedent") ;
        else if (Param.equals("Suivant"))
            Param = Image.getString("IDSuivant") ;
        
        
        Image = new JSONObject();
        
        try {
            Resultat = Demande.executeQuery(Requete);
            Resultat.first();

            //Chercher l'ID donné
            if (!Param.equals("-1")) {
                while(!Resultat.isLast() && !Resultat.getString("ID_Image").equals(Param))
                    Resultat.next();

                //Image précédente
                if(!Resultat.isFirst() && Resultat.previous()) {
                    Image.put("IDPrecedent", Resultat.getString("ID_Image"));
                    Resultat.next();
                }
                else
                    Image.put("IDPrecedent", "-1");
            }
            else
                Image.put("IDPrecedent", "-1");

            //Image principale
            Image.put("ID", Resultat.getString("ID_Image"));
            Image.put("ID_Util", Resultat.getString("ID_Util"));
            
            if (Resultat.getString("Titre_Image") == null)
                Image.put("Titre", "");
            else
                Image.put("Titre", Resultat.getString("Titre_Image"));
            
            if (Resultat.getString("Desc_Image") == null)
                Image.put("Description", "");
            else
                Image.put("Description", Resultat.getString("Desc_Image"));
            
            // Image principale
            Image.put("Fichier", Resultat.getString("Image"));
            
            //Image suivante
            if (Resultat.next())
                Image.put("IDSuivant", Resultat.getString("ID_Image"));
            else
                Image.put("IDSuivant", "-1");
        } catch (SQLException SQLe) {
            Image.put("IDPrecedent", "-1");
            Image.put("ID", "-1");
            Image.put("IDSuivant", "-1");
            Image.put("Titre", "Aucune photo à afficher");
            Image.put("Description", "");
            Image.put("Fichier", "");   
        }
        finally {
            //Fermeture de la requête
            Resultat.close();
            
            // Trouver les commentaires
            if (!Image.getString("ID").equals("-1"))
                Commentaires.VoirCommentaire(Image.getString("ID"));
        }
    }    
}
