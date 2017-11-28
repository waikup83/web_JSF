package TP_Final;


import java.io.InputStream;
import java.nio.file.*;
import java.sql.*;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONException;



/**
 *
 * @author David Desbiens
 */
@Named(value = "ajouterFichier")
@RequestScoped
public class ajouterFichier {
    private Part Fichier;
    private String Titre = "";
    private String Description ="";
    private String Message = "";
    
    
    public Part getFichier(){
        return Fichier;
    }
    public void setFichier(Part F){
        Fichier = F;
    }
    public String getTitre(){
        return Titre;
    }
    public void setTitre(String s){
        Titre = s;
    }
    public String getDescription(){
        return Description;
    }
    public void setDescription(String s){
        Description = s;
    }
    public String getMessage(){
        return Message;
    }
    
    
    public String enregistrerFichier(String ID_usager, TP_Final.bd Donnees) throws java.io.IOException {
        // Répertoire de base
        ServletContext servletContext = (ServletContext) 
            FacesContext.getCurrentInstance().getExternalContext().getContext();
        Path folder = Paths.get(servletContext.getRealPath("/") + "/resources/images/photos/");
        // Nom du fichier
        String filename = FilenameUtils.getBaseName(Fichier.getSubmittedFileName()); 
        // Extension du fichier
        String extension = FilenameUtils.getExtension(Fichier.getSubmittedFileName());
        // Fichier temporaire
        Path file = Files.createTempFile(folder, filename + "-", "." + extension);
        // Écriture du fichier
        try (InputStream input = Fichier.getInputStream()) {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
            AjouterPhoto(ID_usager, file.getFileName().toString());
            Donnees.Demande("-1"); // Rafraichir la photo principale
        }
        catch (Exception e) {
            Message = e.getMessage();
        }
        
        return "index";
    }
    
    
    // Ajouter une photo
    public void AjouterPhoto(String ID_usager, String Fichier) throws SQLException{
        Connection Connexion;
        Statement Demande;
        ResultSet Resultat = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connexion = DriverManager.getConnection("jdbc:mysql://localhost/davidd?user=root");
        } catch (Exception e) {
            return;
        }
        
        Demande = Connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
            ResultSet.CONCUR_READ_ONLY);
        
        String Requete = "INSERT INTO images(ID_Util, Image, Titre_Image, "
            + "Desc_Image) VALUES ('" + ID_usager + "', '" + Fichier + "', '" 
            + Titre + "', '" + Description + "')";
   
        try {
            Demande.executeUpdate(Requete);
        } catch (SQLException SQLe) {
            return;
        }
    }
    
    
    // Enlever une photo
    public String EnleverPhoto(TP_Final.bd Donnees) throws SQLException, JSONException{
        Connection Connexion;
        Statement Demande;
        ResultSet Resultat = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connexion = DriverManager.getConnection("jdbc:mysql://localhost/davidd?user=root");
        } catch (Exception e) {
            return "index";
        }
        
        Demande = Connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
            ResultSet.CONCUR_READ_ONLY);
        
        String Requete = "DELETE FROM images WHERE ID_Image='" 
            + Donnees.getImage().getString("ID") + "'";
   
        try {
            Demande.executeUpdate(Requete);
        } catch (SQLException SQLe) {
            return "index";
        }

        Donnees.Demande(Donnees.getImage().getString("IDSuivant"));
        return "index";
    }
}
