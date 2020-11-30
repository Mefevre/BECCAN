/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.scene.control.Label;
import javafx.scene.shape.Shape;
import sample.NodeE;

/**
 *
 * @author sowme
 */
public class EEDGE {

    public NodeE source, target;
    public double weight;
    public Shape line;
    public Label weightLabel;

    public Shape getLine() {
        return line;
    }

    public EEDGE(NodeE argSource, NodeE argTarget) {
        source = argSource;
        target = argTarget;
        weight = 0;
    }

    public EEDGE(NodeE argSource, NodeE argTarget, double argWeight, Shape argline, Label weiLabel) {
        source = argSource;
        target = argTarget;
        weight = argWeight;
        line = argline;
        this.weightLabel = weiLabel;
    }

}
