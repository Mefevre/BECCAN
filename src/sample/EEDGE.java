/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.scene.control.Label;
import javafx.scene.shape.Shape;

/**
 *
 * @author sowme
 */
public class EEDGE {

    public Node source, target;
    public double weight;
    public Shape line;
    public Label weightLabel;

    public Shape getLine() {
        return line;
    }

    public EEDGE(Node argSource, Node argTarget) {
        source = argSource;
        target = argTarget;
        setWeight(0);
    }

    public EEDGE(Node argSource, Node argTarget, double argWeight, Shape argline, Label weiLabel) {
        source = argSource;
        target = argTarget;
        setWeight(argWeight);
        line = argline;
        this.weightLabel = weiLabel;
    }

    @Override
    public String toString()
    {
        return "Source: " + source + " | target: " + target + " | poids: " + getWeight() + ".\n";
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
