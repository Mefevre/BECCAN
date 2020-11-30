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
public class Membre {
    private String nom;
    private String prenom;
    private int age;
    
    public Membre(String n, String p, int a)
    {
        nom = n;
        prenom = p;
        age = a;
    }
    
    public String toString()
    {
        return nom + " " + prenom + ", vie depuis " + age;
    }
}
