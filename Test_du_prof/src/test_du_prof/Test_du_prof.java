/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_du_prof;

/**
 *
 * @author Maître
 */
public class Test_du_prof {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Membre tuteur = new Membre("LEROUX", "Stéphane", 200);
        Membre unicorn = new Membre("LEFEVRE", "Nathan", 19);
        Membre test = new Membre("TEST", "Test", 600);
        
        //Inserez un nouvel object a votre nom
        
        
        
        //rajouter le méthode toString() sur vous
        System.out.println(tuteur.toString());
        System.out.println(unicorn.toString());
    }
    
}
