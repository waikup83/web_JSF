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
        
/**
 *
 * @author waiku
 */
@Named(value = "authentifier")
@SessionScoped
public class authentifier implements Serializable {
    private String id_utilisateur = "";
    private String utilisateur = "";
    private String motDePasse = "";
    private char type = '-';
    private String message = "";
    
    
    public String getId_utilisateur(){
        return id_utilisateur;
    }
    public void setUtilisateur(String s){
        utilisateur = s;
    }
    public String getUtilisateur(){
        return utilisateur;
    }
    public void setMotDePasse(String s) {
        motDePasse = s;
    }
    public String getMotDePasse(){
        return motDePasse;
    }
    public char getType(){
        return type;
    }
    public String getMessage(){
        return message;
    }
    
    
    public String Verifier() throws SQLException {
        Connection Connexion = null;
        Statement Demande = null;
        ResultSet Resultat = null;
        
        //Instanciation de la classe
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connexion = DriverManager.getConnection("jdbc:mysql://localhost/davidd?user=root");
        } catch (Exception e) {
            return "";
        }
        
        Demande = Connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
            ResultSet.CONCUR_READ_ONLY);
        
        // Requête
        Resultat = Demande.executeQuery(
            "SELECT * FROM utilisateurs WHERE Utilisateur='" + utilisateur + "'");
        
        if (Resultat.first())
            if (motDePasse.equals(Resultat.getString("MotDePasse"))) {
                type = Resultat.getString("Type").charAt(0);
                id_utilisateur = Resultat.getString("ID_Util");
                return "index";
            }

        message = "Mauvais utilisateur ou mot de passe";
        utilisateur = "";
        motDePasse = "";
        type = '-';
        return "ident";
    }

    
    public void Deconnexion() {
        id_utilisateur = "";
        utilisateur = "";
        motDePasse = "";
        type = '-';
        message = "";
    }

    
}
