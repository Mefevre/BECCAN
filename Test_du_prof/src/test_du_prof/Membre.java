/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_du_prof;

import java.util.ArrayList;

/**
 *
 * @author Maître
 */
public class Membre {
    private String nom;
    private String prenom;
    private int age;
    private ArrayList<Membre> listProject;
    
    public Membre(String n, String p, int a)
    {
        nom = n;
        prenom = p;
        age = a;
         = new ArrayList<>();
    }
    
    public String toString()
    {
        return nom + " " + prenom + ", vie depuis " + age;
    }
    public void AddProject(String s){
        listProject.add(s);
    }
    public int NbProject(){
        return listProject.size();
    }
}
