/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 * @author yassine
 */
public class Arret {
    private Sommet s1;
    private Sommet s2;
    
    public Arret(Sommet e, Sommet r){
        this.s1= e;
        this.s2= r;
    }
    public Sommet get_1(){
        return s1;
    }
    public Sommet get_2(){
        return s2;
    }
    @Override
    public int hashCode(){
        return s1.getNumero()*7+s2.getNumero()*5;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Arret arret = (Arret) obj;
        if(
                (this.s1.equals(arret.s1) && this.s2.equals(arret.s2)) ||
                (this.s1.equals(arret.s2) && this.s1.equals(arret.s2))
                
                ) return true;

        
        return false;
    }
}
