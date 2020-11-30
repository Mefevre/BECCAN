package sample;
import java.awt.Point;
import java.net.URL;
import java.util.*;

import com.sun.javafx.geom.Edge;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.StrokeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Controller implements Initializable, ChangeListener {
    public Pane canvasGroup;
    public ToggleButton edge;
    public ToggleButton node;
    NodeFX selectedNode = null;
    private Label sourceText = new Label("Source"), weight;
    private ARROW arrow;
    private Slider slider = new Slider();
    private Line edgeLine;
    public static TextArea textFlow = new TextArea();
    List<Label> distances = new ArrayList<Label>();

    boolean addNode = true, addEdge = false,paused = false,playing = false , dijkstra;
    private ImageView playPauseImage;
    public SequentialTransition st;
    public SequentialTransition stcolor;


    int NB, NB2 , time = 500;;


    List<NodeFX> circles = new ArrayList<NodeFX>();
    List<Shape> edges = new ArrayList<>();
    List<EEDGE> mstEdges = new ArrayList<EEDGE>();
    List<EEDGE> realEdges = new ArrayList<EEDGE>();
    List<Color> colorName = new ArrayList<Color>();


    int nNode = 0;


    public void handle(MouseEvent ev) {
        if (addNode) {
            if (true) {

                nNode++;

                NodeFX circle = new NodeFX(ev.getX(), ev.getY(), 10, String.valueOf(nNode), nNode);
                canvasGroup.getChildren().add(circle);
                circle.setOnMousePressed(mouseHandler);

                }
        }
    }

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
            NodeFX circle = (NodeFX) mouseEvent.getSource();


            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && mouseEvent.getButton() == MouseButton.PRIMARY && addEdge == true) {
                if (!circle.isSelected) {
                    if (selectedNode != null) {
                        if (addEdge) {

                            System.out.println("Adding Edge");

                            if (true) {
                                Line edgeLine = new Line(selectedNode.point.x, selectedNode.point.y, circle.point.x, circle.point.y);
                                canvasGroup.getChildren().add(edgeLine);
                                edgeLine.setId("line");

                            }


                            if (addNode || addEdge) {
                                selectedNode.isSelected = false;
                                FillTransition ft1 = new FillTransition(Duration.millis(300), selectedNode, Color.RED, Color.BLACK);
                                ft1.play();
                            }
                            selectedNode = null;
                            return;
                        }
                    }
                    FillTransition ft = new FillTransition(Duration.millis(300), circle, Color.BLACK, Color.RED);
                    ft.play();
                    circle.isSelected = true;
                    selectedNode = circle;


                } else {
                    circle.isSelected = false;
                    FillTransition ft1 = new FillTransition(Duration.millis(300), circle, Color.RED, Color.BLACK);
                    ft1.play();
                    selectedNode = null;
                }


            }
        }
    };
            public void AddEdgeHandle (ActionEvent actionEvent)
            {
                addNode = false;
                addEdge = true;
                node.setSelected(false);
                edge.setSelected(true);
                selectedNode = null;
            }


            public void AddNodeHandle (ActionEvent actionEvent)
            {
                addNode = true;
                addEdge = false;
                node.setSelected(true);
                edge.setSelected(false);
                selectedNode = null;
            }



    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        int temp = (int) slider.getValue();

        if (temp > 500) {
            int diff = temp - 500;
            temp = 500;
            temp -= diff;
            temp += 10;
        } else if (temp < 500) {
            int diff = 500 - temp;
            temp = 500;
            temp += diff;
            temp -= 10;
        }
        time = temp;
        System.out.println(time);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        edge.setDisable(false);
        node.setDisable(false);

    }


    /**
             * Shape class for the nodes.
             */
            class NodeFX extends Circle {
                NodeE node;

                Point point;
                Label id;
                Label distance = new Label("Dist. : INFINITY");
                Label visitTime = new Label("Visit : 0");
                Label lowTime = new Label("Low : 0");
                int Nombre;
                boolean isSelected = false;

                public NodeFX(double x, double y, double rad, String name, int N) {
                    super(x, y, rad);

                    node = new NodeE(name, this);

                    point = new Point();
                    point.setLocation(x, y);
                    id = new Label(name);
                    Nombre = N;
                    canvasGroup.getChildren().add(id);
                    id.setLayoutX(x - 18);
                    id.setLayoutY(y - 18);
                    this.setOpacity(0.5);
                    this.setBlendMode(BlendMode.MULTIPLY);
                    this.setId("node");
                    circles.add(this);
                    System.out.println("ADDing: " + circles.size());
                }
            }


}
