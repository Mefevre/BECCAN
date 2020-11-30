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
        Membre emolga=new Membre("BOUHANA","Coralie",21);
        Membre Antonin = new Membre("ANtonin","me" , 45);
        Membre ClemLePacifiste = new Membre("Clément","Turbo" , 12);
        
        //Inserez un nouvel object a votre nom
        
        
        System.out.println("Vous etes beau ! ");
        //rajouter le méthode toString() sur vous
        System.out.println(tuteur.toString());
        System.out.println(Antonin.toString());
        System.out.println(unicorn.toString());
        System.out.println(ClemLePacifiste.toString());
    }
    
}
