/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP_Final;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

import java.sql.*;
/**
 *
 * @author waik
 */
@Named(value = "inscription")
@RequestScoped
public class inscription {
    private String utilisateur = "";
    private String motDePasse = "";
    private String confirmer = "-";
    private String message = "";
    
    public void setUtilisateur(String s) {
        this.utilisateur = s;
    }
    public String getUtilisateur() {
        return utilisateur;
    }
    public void setMotDePasse(String s) {
        this.motDePasse = s;
    }
    public String getMotDePasse() {
        return motDePasse;
    }
    public void setConfirmer(String s) {
        this.confirmer = s;
    }
    public String getConfirmer() {
        return confirmer;
    }
    public String getMessage() {
        return message;
    }
    
    
    public String FaireInscription() throws SQLException {
        Connection Connexion = null;
        Statement Demande = null;
        ResultSet Resultat = null;
        
        //Instanciation de la classe
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connexion = DriverManager.getConnection("jdbc:mysql://localhost/davidd?user=root");
        } catch (Exception e) {
        }
        
        Demande = Connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
            ResultSet.CONCUR_READ_ONLY);
        
        // Vérifier si l'utilisateur existe déjà
        Resultat = Demande.executeQuery(
            "SELECT * FROM utilisateurs WHERE Utilisateur='" + utilisateur + "'");
        if (!Resultat.first()) {
            // Vérifier si le mot de passe est valide
            if (motDePasse.equals(confirmer)) {
                // Ajout dans la base de donnée
                Demande.executeUpdate("INSERT INTO utilisateurs (Utilisateur, "
                    + "MotDePasse) VALUES('" + utilisateur +"', '" + motDePasse + "')");

                return "ident";
            }   
            message = "Mot de passe invalide.";
            return "inscription";            
        }
        message = "Utilisateur déjà existant.";
        return "inscription";
    }

}
