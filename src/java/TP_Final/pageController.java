/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP_Final;

/**
 *
 * @author waik
 */
public class pageController {    
    public String VoirPage(String page){
        if (page.equals("ident"))
            return "ident";
        
        return "index";
    }
    
    public String Logout(TP_Final.authentifier usager) {
        usager.Deconnexion();
        return VoirPage("index");
    }

    
}