package sample;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.StrokeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Algo.BellmanFord.BellmanFord;
import sample.Algo.Dsatur.Arret;
import sample.Algo.Dsatur.GrapheDSat;
import sample.Algo.Dsatur.Sommet;
import sample.Algo.Hamiltonien.Hamiltonien;
//import sample.Algo.Kruskal.Kruskal;
import sample.Algo.Kruskal.Graph;
import sample.Control.Control_Choix;
import sample.Control.Control_Matrix;
import sample.Control.Control_Matrix_one;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.*;

public class PanelPlan implements Initializable, ChangeListener {

    public Pane canvasGroup;
    public ToggleButton edge;
    public ToggleButton node;
    NodeFX selectedNode = null;
    private Label sourceText = new Label("Source"), weight;
    private ARROW arrow;
    @FXML
    private ComboBox List;
    @FXML
    private TextArea MatriX;
    @FXML
    private Slider slider = new Slider();
    private Line edgeLine;
    public static TextArea textFlow = new TextArea();
    List<Label> distances = new ArrayList<Label>(), visitTime = new ArrayList<>(), lowTime = new ArrayList<Label>();

    boolean addNode = true;
    boolean addEdge = false;
    boolean paused = false;
    boolean playing = false;
    boolean dijkstra;
    boolean Bfs = false;
    Algorithm algo = new Algorithm();
    public SequentialTransition st;
    public static final int MAX_VALUE = 999; //bellman ford
    public int ValueNBsommet = Control_Matrix.ValueNBsommet;//bellmanFord
    public int ValueNBsommet_one = Control_Matrix_one.ValueNBsommet;


    public boolean WelABoolean = false;
    private LinkedList<Integer> adj[]; //welsh powell
    private int[][]tabcolor;
    private int[][] tempcoloriage;

    //kruskal
    private int[][] chemin;

    private boolean directed = Control_Choix.directed, undirected = Control_Choix.undirected, weighted = Control_Choix.weighted, unweighted = Control_Choix.unweighted;
    private String[][] matrice_nb = Control_Matrix.matrice;
    private boolean[][] matrice_true = Control_Matrix_one.matrice;
    private boolean etat_matrice = Control_Matrix.etat_matrice;
    private boolean etat_matrice_one = Control_Matrix_one.etat_matrice;
    private int[][] matriceBellman = new int[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
    private int[][] matriceBellmanRES = new int[ValueNBsommet][ValueNBsommet];
    int time = 700;
    ;

    GrapheDSat g = new GrapheDSat();

    List<String> ListALgo = new ArrayList<>();
    List<NodeFX> circles = new ArrayList<NodeFX>();
    List<Shape> edges = new ArrayList<>();
    List<EEDGE> mstEdges = new ArrayList<EEDGE>();
    List<EEDGE> realEdges = new ArrayList<EEDGE>();
    List<Color> colorName = new ArrayList<Color>();
    //List<NodeE> ListNode = new ArrayList<>();
    int nNode = 0;

    //
    //Init tout les variable et la combobox
    //
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Togglebutton
        edge.setDisable(false);
        node.setDisable(false);
        //SLider
        //Image img = new Image("sample/vitesse.png");
        //ImageView view = new ImageView(img);
        //vitesse.setGraphic(view);
        slider.setMin(10);
        slider.setMax(1000);
        slider.setValue(500);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        //Color
        colorName.add(Color.BLUE);
        colorName.add(Color.RED);
        colorName.add(Color.GREEN);
        colorName.add(Color.ORANGE);
        colorName.add(Color.PINK);
        colorName.add(Color.YELLOW);
        colorName.add(Color.VIOLET);
        //Combobox
        if (true)//teste de la combobox
        {
            ListALgo.add("Aucun");
            ListALgo.add("Dsatur");
            ListALgo.add("Welsh Powell");
            ListALgo.add("Dijkstra");
            ListALgo.add("Eulérien");
            ListALgo.add("Hamiltonien");
            ListALgo.add("Kruskal");
            ListALgo.add("Prim");
            ListALgo.add("Bellman Ford");
            List.getItems().addAll(ListALgo);
        }
    }

    @Override
    //
    //Gere le temps du slider pour le temps des algos
    //
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {

    }

    //
    //Fonction qui gere la fenetre AIDE
    //
    public void aide() {
        Label secondLabel;

        secondLabel = new Label("Page d'aide pour Premiere page :" + "\n"
                + "Page D'accueil : Le button centrale lance le logicile ." + "\n" + "\n"
                + "Page de choix : Vous devais decidé les type de votre graph un choix possible pour les poids" + "\n"
                + "et pour l'orientation." + "\n" + "\n"
                + "Page de la matrice : Vous deviez tout d'abord mettre le Nb de sommet puis remplir la matrice" + "\n"
                + "avec passe à l'etape du plan pour mettre en meouvre les algo." + "\n" + "\n"
                + "Page de plan : Cette page affiche le graph et sert a le modifier , on peut applique des algorithme" + "\n"
                + "sur le graph afficher." + "\n");

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(secondaryLayout, 650, 400);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Fenetre D'aide");
        newWindow.setScene(secondScene);


        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(Main.primaryStage);

        // Set position of second window, related to primary window.
        newWindow.setX(Main.primaryStage.getX() + 200);
        newWindow.setY(Main.primaryStage.getY() + 100);

        newWindow.show();
    }

    //
    //Fonction quand clique sur le pane , Gere le dessin des circle et appel la fonction des ARC ARRET
    //
    public void handle(MouseEvent ev) {

        if (addNode) {
            if (ev.getEventType() == MouseEvent.MOUSE_CLICKED && WelABoolean == false && ev.getButton() == MouseButton.PRIMARY) {

                nNode++;

                NodeFX circle = new NodeFX(ev.getX(), ev.getY(), 10, String.valueOf(nNode), nNode);
                canvasGroup.getChildren().add(circle);
                circle.setOnMousePressed(mouseHandler);
                //Easter eggs
                /*ScaleTransition tr = new ScaleTransition(Duration.millis(100), circle);
                tr.setByX(10f);
                tr.setByY(10f);
                tr.setInterpolator(Interpolator.EASE_OUT);
                tr.play();*/
            } else if (ev.getEventType() == MouseEvent.MOUSE_CLICKED && WelABoolean == true) {
                for (NodeFX node : circles) {
                    node.setOnMouseClicked(mouseHandler);
                }
            }
        }
    }

    //
    //Empeche les N-graph
    //
    public boolean edgeExists(NodeFX u, NodeFX v) {
        for (EEDGE e : realEdges) {
            if (e.source == u.node && e.target == v.node || e.source == v.node && e.target == u.node) {
                return true;
            }
        }
        return false;
    }

    //
    //Fonction qui gere l'affichage des line et le label poids
    //
    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
            NodeFX circle = (NodeFX) mouseEvent.getSource();
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && mouseEvent.getButton() == MouseButton.PRIMARY && addEdge == true) {
                if (!circle.isSelected) {
                    if (selectedNode != null) {
                        weight = new Label();
                        if (!edgeExists(selectedNode, circle)) {
                            System.out.println("Adding Edge");
                            //DESINE LES LIGNE OU FLECHE ENTRE LES POINTS
                            if (undirected) {
                                edgeLine = new Line(selectedNode.point.x, selectedNode.point.y, circle.point.x, circle.point.y);
                                canvasGroup.getChildren().add(edgeLine);
                                edgeLine.setId("line");
                                g.ajouterArret(new Arret(selectedNode.nodeBIS, circle.nodeBIS));
                            } else if (directed) {
                                arrow = new ARROW(selectedNode.point.x, selectedNode.point.y, circle.point.x, circle.point.y);
                                canvasGroup.getChildren().add(arrow);
                                arrow.setId("arrow");
                                g.ajouterArret(new Arret(selectedNode.nodeBIS, circle.nodeBIS));
                            }

                            if (weighted) {
                                weight.setLayoutX(((selectedNode.point.x) + (circle.point.x)) / 2);
                                weight.setLayoutY(((selectedNode.point.y) + (circle.point.y)) / 2);

                                TextInputDialog dialog = new TextInputDialog("1");
                                dialog.setTitle(null);
                                dialog.setHeaderText("Enter Weight of the Edge :");
                                dialog.setContentText(null);

                                Optional<String> result = dialog.showAndWait();
                                if (result.isPresent()) {
                                    weight.setText(result.get());
                                } else {
                                    weight.setText("0");
                                }
                                canvasGroup.getChildren().add(weight);
                            } else if (unweighted) {
                                weight.setText("1");
                            }
                            Shape line_arrow = null;
                            EEDGE temp = null;
                            if (undirected) {
                                temp = new EEDGE(selectedNode.node, circle.node, Integer.valueOf(weight.getText()), edgeLine, weight);
                                if (weighted) {
                                    mstEdges.add(temp);
                                }

                                selectedNode.node.adjacents.add(new EEDGE(selectedNode.node, circle.node, Double.valueOf(weight.getText()), edgeLine, weight));
                                circle.node.adjacents.add(new EEDGE(circle.node, selectedNode.node, Double.valueOf(weight.getText()), edgeLine, weight));
                                edges.add(edgeLine);
                                realEdges.add(selectedNode.node.adjacents.get(selectedNode.node.adjacents.size() - 1));
                                realEdges.add(circle.node.adjacents.get(circle.node.adjacents.size() - 1));
                                line_arrow = edgeLine;

                            } else if (directed) {
                                temp = new EEDGE(selectedNode.node, circle.node, Double.valueOf(weight.getText()), arrow, weight);
                                selectedNode.node.adjacents.add(temp);
                                //circle.node.revAdjacents.add(new EEDGE(selectedNode.node, circle.node,Double.valueOf(weight.getText()), arrow, weight));
                                edges.add(arrow);
                                line_arrow = arrow;
                                realEdges.add(temp);
                            }
                        }
                        if (addNode || addEdge) {
                            selectedNode.isSelected = false;
                            FillTransition ft1 = new FillTransition(Duration.millis(300), selectedNode, Color.RED, Color.BLACK);
                            ft1.play();
                        }
                        selectedNode = null;
                        return;

                    }
                    FillTransition ft = new FillTransition(Duration.millis(300), circle, Color.BLACK, Color.RED);
                    ft.play();
                    circle.isSelected = true;
                    selectedNode = circle;
                    //CHOIX DES ALGOS
                    if (dijkstra) {
                        algo.newDijkstra(circle.node);
                    } else if (Bfs) {
                        algo.newBFS(circle.node);
                    }
                } else {
                    circle.isSelected = false;
                    FillTransition ft1 = new FillTransition(Duration.millis(300), circle, Color.RED, Color.BLACK);
                    ft1.play();
                    selectedNode = null;
                }


            }
        }
    };

    //
    //Modifier la valeur en cliquant sur button Edge
    //
    public void AddEdgeHandle(ActionEvent actionEvent) {
        addNode = false;
        addEdge = true;
        node.setSelected(false);
        edge.setSelected(true);
        selectedNode = null;
        //Algo annulation
        WelABoolean = false;
    }

    //
    //Modifier la valeur en cliquant sur button NODE
    //
    public void AddNodeHandle(ActionEvent actionEvent) {
        addNode = true;
        addEdge = false;
        node.setSelected(true);
        edge.setSelected(false);
        selectedNode = null;
        //Algo annulation
        WelABoolean = false;
    }//
    //Modifier la valeur Boolean en cliquand sur button
    //
    public void DijkstraHandle(ActionEvent actionEvent) {
        dijkstra = true;
        Bfs = false;
    }

    //
    //Modifier la valeur Boolean en cliquand sur button
    //
    public void BFSHandle(ActionEvent actionEvent) {
        Bfs = true;
        dijkstra = false;
    }

    //
    //Reset a etat au demarage
    //
    public void ResetHandle(ActionEvent event) //a faire
    {
        selectedNode = null;
        addNode = true;
        addEdge = false;
        node.setSelected(false);
        edge.setSelected(false);
        for (NodeFX n : circles) {
            n.isSelected = false;
            n.node.visited = false;
            n.node.previous = null;
            n.node.minDistance = Double.POSITIVE_INFINITY;
            FillTransition ft1 = new FillTransition(Duration.millis(300), n);
            ft1.setToValue(Color.BLACK);
            ft1.play();
        }
        for (Shape x : edges) {
            if (undirected) {
                StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time), x);
                ftEdge.setToValue(Color.BLACK);
                ftEdge.play();
            } else if (directed) {
                FillTransition ftEdge = new FillTransition(Duration.millis(time), x);
                ftEdge.setToValue(Color.BLACK);
                ftEdge.play();
            }
        }
        canvasGroup.getChildren().remove(sourceText);
        for (Label x : distances) {
            x.setText("Distance : INFINITY");
            canvasGroup.getChildren().remove(x);
        }
        for (Label x : visitTime) {
            x.setText("Visit : 0");
            canvasGroup.getChildren().remove(x);
        }
        for (Label x : lowTime) {
            x.setText("Low Value : NULL");
            canvasGroup.getChildren().remove(x);
        }
        distances = new ArrayList<>();
        visitTime = new ArrayList<>();
        lowTime = new ArrayList<>();
        textFlow.clear();
        dijkstra = false;
        WelABoolean=false;
        matriceBellman = new int[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        playing = false;
        paused = false;

    }

    //calcule la matrice du graph dans le plan
    //
    //Fonction de calcule de la matrice du graph dessiner utilisé pour Bellman FORD
    //
    public void MatricePoids() {
        ValueNBsommet = 0;
        matriceBellman = new int[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        for (NodeFX u : circles) {
            //ListNode.add(u.node);
            ValueNBsommet++;
            for (EEDGE e : u.node.adjacents) {
                matriceBellman[Integer.parseInt(e.source.name)][Integer.parseInt(e.target.name)] = (int) e.getWeight();
            }
        }

    }

    //
    //Appel le constructeur de BEllmanford
    //
    public void AppelBellmanFord(int[][] matriceBellman) {
        int numberofvertices = 0;
        System.out.println("Enter the number of vertices");
        numberofvertices = ValueNBsommet;//nb de sommet
        int adjacencymatrix[][] = new int[numberofvertices + 1][numberofvertices + 1];
        System.out.println("Enter the adjacency matrix");
        for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++) {
            for (int destinationnode = 1; destinationnode <= numberofvertices; destinationnode++) {
                adjacencymatrix[sourcenode][destinationnode] = matriceBellman[sourcenode][destinationnode];// ICI
                if (sourcenode == destinationnode) {
                    adjacencymatrix[sourcenode][destinationnode] = 0;
                    continue;
                }
                if (adjacencymatrix[sourcenode][destinationnode] == 0) {
                    adjacencymatrix[sourcenode][destinationnode] = MAX_VALUE;
                }
            }
        }
        //ICI
        System.out.println("Enter the source vertex [1 -- " + numberofvertices + "]");
        //source = scanner.nextInt();// on veut tout le tableau .
        MatriX.appendText("     ");
        for (int o = 1; o <= numberofvertices; o++) {
            MatriX.appendText("  " + o + "   ");
        }
        MatriX.appendText("\n" + "      ");
        for (int g = 1; g <= numberofvertices; g++) {
            MatriX.appendText("____");
        }
        MatriX.appendText("\n");
        for (int i = 1; i <= numberofvertices; i++) {
            BellmanFord bellmanford = new BellmanFord(numberofvertices);
            matriceBellmanRES = bellmanford.BellmanFordEvaluation(i, adjacencymatrix);
            MatriX.appendText(i + "   |");
            for (int j = 0; j < matriceBellmanRES.length; j++) {
                if (matriceBellmanRES[i - 1][j] == 884)
                    MatriX.setText("Cycle negative !! ");
                else if (matriceBellmanRES[i - 1][j] == 999)
                    MatriX.appendText(" [inf+] ");
                else
                    MatriX.appendText(String.valueOf(" [" + matriceBellmanRES[i - 1][j]) + "]  ");
            }
            MatriX.appendText("\n");

        }

    }
    public int[][] Welsh()
    {
        tabcolor = new int[circles.size()][2];
        ArrayList<Integer> Interditsom = new ArrayList<Integer>();
        ArrayList<Integer> Interditcol = new ArrayList<Integer>();
        int tempDegre = 0 , tempSom = 0;
        adj = new LinkedList[circles.size()+1];
        for (int i=1; i<=circles.size(); ++i)
        {
            adj[i] = new LinkedList();
            tabcolor[i-1][1] = 0;
        }
        for (NodeFX u : circles)
        {
            for (EEDGE e : u.node.adjacents)
            {
                System.out.println(e.source.name+"  "+e.target.name);
                adj[Integer.parseInt(e.source.name)].add(Integer.parseInt(e.target.name));
                //adj[Integer.parseInt(e.target.name)].add(Integer.parseInt(e.source.name));
            }
        }
        //Creation de tableau avec ordre de degre
        System.out.println("Trie realiser");
        tempDegre = 0;
        tempSom = 0;
        Interditsom.clear();
        for (int i=0; i<tabcolor.length ; i++)
        {
            for (int j=1; j<adj.length; j++)
            {
                if (adj[j].size() > tempDegre && !Interditsom.contains(j))
                {
                    tempDegre = adj[j].size();
                    tempSom = j;
                }
            }
            tabcolor[i][0] =tempSom;
            Interditsom.add(tempSom);
            tempDegre = 0;
        }
        tabcolor[0][1] =1;
        Interditsom.clear();
        System.out.println("Affectation");
        //Affect couleur a sommet
        for (int i=1; i<tabcolor.length; i++)
        {
            Interditcol.clear();
            for (int j=0; j<=i-1; j++)
            {
                if (adj[tabcolor[i][0]].contains(tabcolor[j][0]))
                    Interditcol.add(tabcolor[j][1]);
            }
            for (int j=1; j<=circles.size(); j++)
            {
                if (!Interditcol.contains(j))
                {
                    tabcolor[i][1] =j;
                    j=99;
                }
            }
        }
        System.out.println("Welsh-Powell Fini");
        return tabcolor;
    }

    public void Krus()
    {
        int V,E=0,i=0;
        V = circles.size();
        //Node de sommet ↑ et nombre d'arc ou arret ↓
        for (NodeFX u : circles)
        {
            if (u.node.adjacents.isEmpty())
                V--;
            for (EEDGE e : u.node.adjacents)
            {
                E++;

            }
        }
        System.out.println("nb sommet "+V + "  "+(E/2));
        Graph graph = new Graph(V, (E/2));
        //ajout des arc a l'odjet Graph ↓
        for (NodeFX u : circles)
        {
            for (EEDGE e : u.node.adjacents)
            {
                if (!(e.target.Numero < u.node.Numero))
                {
                    graph.edge[i].src = e.source.Numero-1;
                    graph.edge[i].dest = e.target.Numero-1;
                    graph.edge[i].weight = (int)e.weight;
                    System.out.println(e.source.Numero + ">"+e.target.Numero + "avec "+(int)e.weight);
                    i++;
                }
            }
        }
        //Parti coloration  chaque sommet et arrret KRUSKAL
        SequentialTransition stcolorkru = new SequentialTransition();
        chemin = graph.KruskalMST();
        for (int k = 0 ;k < chemin.length;k++)
        {
            for (NodeFX u : circles)
            {
                for (EEDGE e : u.node.adjacents)
                {
                    if (e.source.Numero == (chemin[k][0]+1) && e.target.Numero == (chemin[k][1]+1) )
                    {
                        StrokeTransition ftEdge = new StrokeTransition(Duration.millis(slider.getValue()), e.line);
                        ftEdge.setToValue(Color.BLUE);
                        stcolorkru.getChildren().add(ftEdge);
                        //ftEdge.play();
                        FillTransition ftNodeS = new FillTransition(Duration.millis(slider.getValue()), e.source.circle);
                        ftNodeS.setToValue(Color.BLUE);
                        stcolorkru.getChildren().add(ftNodeS);
                        FillTransition ftNodeT = new FillTransition(Duration.millis(slider.getValue()), e.target.circle);
                        ftNodeT.setToValue(Color.BLUE);
                        stcolorkru.getChildren().add(ftNodeT);
                        System.out.println("Ajoout de line couleur "+e.source.Numero+" à "+e.target.Numero);
                    }
                }
            }
            //System.out.println((chemin[k][0]+1)+" -- "+(chemin[k][1]+1)+" = "+chemin[k][2]);
        }
        stcolorkru.onFinishedProperty();
        stcolorkru.play();

    }
    //
    //Le teste des choix dans la combobox
    //
    public void ListALgo() {
        if (List.getSelectionModel().getSelectedItem().equals("color"))
            COLOR();
        else if (List.getSelectionModel().getSelectedItem().equals("Dijkstra")) {
            dijkstra = true;
            Bfs = false;
        } else if (List.getSelectionModel().getSelectedItem().equals("Bellman Ford")) {
            if (etat_matrice_one) {
                // matrice_true matrice_nb  matriceBellman

                for (int i = 0; i < matrice_true.length; i++) {
                    for (int j = 0; j < matrice_true.length; j++) {
                        if (matrice_true[i][j] == true && etat_matrice_one)
                            matriceBellman[i][j] = 1;
                        else if (etat_matrice)
                            matriceBellman[i][j] = Integer.parseInt(matrice_nb[i][j]);
                    }
                }
            } else if (etat_matrice) {
                for (int i = 0; i < matrice_nb.length; i++) {
                    for (int j = 0; j < matrice_nb.length; j++) {
                        if (matrice_nb[i][j] == "") {
                            matriceBellman[i][j] = 0;
                        } else {
                            System.out.println("eee" + matrice_nb[i][j] + "eee");
                            matriceBellman[i][j] = Integer.parseInt(matrice_nb[i][j]);
                        }
                    }
                }
                MatriX.setText("Resultat de Bellman-Ford  :\n");
                AppelBellmanFord(matriceBellman);

            } else {
                MatriX.setText("Resultat de Bellman-Fords\n");
                MatricePoids();
                AppelBellmanFord(matriceBellman);

            }
        } else if (List.getSelectionModel().getSelectedItem().equals("Welsh Powell")) //pas besoin de reset ,marche en non orienté
        {
            //WelshPowell();
            tempcoloriage = new int[circles.size()][2];
            SequentialTransition stcolor2 = new SequentialTransition();
            tempcoloriage = Welsh();

            for (int i = 0;i < circles.size();i++)
            {
                FillTransition ftcolor = new FillTransition(Duration.millis(slider.getValue()),circles.get(tempcoloriage[i][0]-1) );
                ftcolor.setToValue(colorName.get(tempcoloriage[i][1]));
                stcolor2.getChildren().add(ftcolor);
            }
            stcolor2.play();
        } else if (List.getSelectionModel().getSelectedItem().equals("Kruskal")) {
            Krus();
        } else if (List.getSelectionModel().getSelectedItem().equals("Aucun")) {

        } else if (List.getSelectionModel().getSelectedItem().equals("Dsatur")) {
            COLOR();
            //dsatur
        } else if (List.getSelectionModel().getSelectedItem().equals("Hamiltonien")) {
            afficheMatrice(getMatriceGraph());
            new Hamiltonien(getMatriceGraph(), MatriX);
        }

    }

    //
    //Appel le contructeur de la class COLOR pour colorisé le graph
    //------------------------------------COLOR(g, cicles, colorName, slider)-----------------------------------------------
    public void COLOR() {

        int U;
        g.colorier();
        System.out.print(g.toString());
        SequentialTransition stcolor1 = new SequentialTransition();
        for (NodeFX n : circles) {
            U = n.nodeBIS.getCouleur();
            FillTransition ftcolor = new FillTransition(Duration.millis(slider.getValue()), n);

            ftcolor.setToValue(colorName.get(U));
            stcolor1.getChildren().add(ftcolor);
        }
        stcolor1.play();
        //if () pour allongé le temmps d'affiche des couleur.
        stcolor1.setOnFinished(event -> {
            for (NodeFX n : circles) {
                FillTransition unftcolor = new FillTransition(Duration.millis(slider.getValue()), n);
                unftcolor.setToValue(Color.BLACK);
                unftcolor.play();
            }
        });
    }




    /**
     * Shape class for the nodes.
     */
    class NodeFX extends Circle {
        Node node;
        Sommet nodeBIS;
        Point point;
        Label id;
        Label distance = new Label("Dist. : INFINITY");
        int Nombre;
        boolean isSelected = false;

        public NodeFX(double x, double y, double rad, String name, int N) {
            super(x, y, rad);

            node = new Node(name, this,N);
            g.ajouterSommet(nodeBIS = new Sommet(N, 0));
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

    //
    //Class ALgo
    //
    public class Algorithm {
        private Node source;

        //<editor-fold defaultstate="collapsed" desc="Dijkstra">
        public void newDijkstra(Node source) {
            new Dijkstra(source);
        }

        class Dijkstra {

            Dijkstra(Node source) {
                //<editor-fold defaultstate="collapsed" desc="Animation Control">
                for (NodeFX n : circles) {
                    distances.add(n.distance);
                    n.distance.setLayoutX(n.point.x + 20);
                    n.distance.setLayoutY(n.point.y);
                    canvasGroup.getChildren().add(n.distance);
                }
                sourceText.setLayoutX(source.circle.point.x + 20);
                sourceText.setLayoutY(source.circle.point.y + 10);
                canvasGroup.getChildren().add(sourceText);
                SequentialTransition st = new SequentialTransition();
                source.circle.distance.setText("Dist. : " + 0);
                //</editor-fold>

                source.minDistance = 0;
                PriorityQueue<Node> pq = new PriorityQueue<Node>();
                pq.add(source);
                while (!pq.isEmpty()) {
                    Node u = pq.poll();
                    System.out.println(u.name);
                    //<editor-fold defaultstate="collapsed" desc="Animation Control">
                    FillTransition ft = new FillTransition(Duration.millis(slider.getValue()), u.circle);
                    ft.setToValue(Color.CHOCOLATE);
                    st.getChildren().add(ft);
                    String str = "";
                    str = str.concat("Popped : Node(" + u.name + "), Current Distance: " + u.minDistance + "\n");
                    final String str2 = str;
                    FadeTransition fd = new FadeTransition(Duration.millis(10), textFlow);
                    fd.setOnFinished(e -> {
                        textFlow.appendText(str2);
                    });
                    fd.onFinishedProperty();
                    st.getChildren().add(fd);
                    //</editor-fold>
                    System.out.println(u.name);
                    for (EEDGE e : u.adjacents) {
                        if (e != null) {
                            Node v = e.target;
                            System.out.println("HERE " + v.name);
                            if (u.minDistance + e.getWeight() < v.minDistance) {
                                pq.remove(v);
                                v.minDistance = u.minDistance + e.getWeight();
                                v.previous = u;
                                pq.add(v);
                                //<editor-fold defaultstate="collapsed" desc="Node visiting animation">
                                //<editor-fold defaultstate="collapsed" desc="Change Edge colors">
                                if (undirected) {
                                    StrokeTransition ftEdge = new StrokeTransition(Duration.millis(slider.getValue()), e.line);
                                    ftEdge.setToValue(Color.FORESTGREEN);
                                    st.getChildren().add(ftEdge);
                                } else if (directed) {
                                    FillTransition ftEdge = new FillTransition(Duration.millis(slider.getValue()), e.line);
                                    ftEdge.setToValue(Color.FORESTGREEN);
                                    st.getChildren().add(ftEdge);
                                }
                                //</editor-fold>
                                FillTransition ft1 = new FillTransition(Duration.millis(slider.getValue()), v.circle);
                                ft1.setToValue(Color.FORESTGREEN);
                                ft1.setOnFinished(ev -> {
                                    v.circle.distance.setText("Dist. : " + v.minDistance);
                                });
                                ft1.onFinishedProperty();
                                st.getChildren().add(ft1);

                                str = "\t";
                                str = str.concat("Pushing : Node(" + v.name + "), (" + u.name + "--" + v.name + ") Distance : " + v.minDistance + "\n");
                                final String str1 = str;
                                FadeTransition fd2 = new FadeTransition(Duration.millis(10), textFlow);
                                fd2.setOnFinished(ev -> {
                                    textFlow.appendText(str1);
                                });
                                fd2.onFinishedProperty();
                                st.getChildren().add(fd2);
                                //</editor-fold>
                            }
                        }
                    }
                    //<editor-fold defaultstate="collapsed" desc="Animation Control">
                    FillTransition ft2 = new FillTransition(Duration.millis(slider.getValue()), u.circle);
                    ft2.setToValue(Color.BLUEVIOLET);
                    st.getChildren().add(ft2);
                    //</editor-fold>
                }

                //<editor-fold defaultstate="collapsed" desc="Animation Control">
                st.setOnFinished(ev -> {
                    for (NodeFX n : circles) {
                        FillTransition ft1 = new FillTransition(Duration.millis(slider.getValue()), n);
                        ft1.setToValue(Color.BLACK);
                        ft1.play();
                    }
                    if (directed) {
                        for (Shape n : edges) {
                            n.setFill(Color.BLACK);
                        }
                    } else if (undirected) {
                        for (Shape n : edges) {
                            n.setStroke(Color.BLACK);
                        }
                    }
                    FillTransition ft1 = new FillTransition(Duration.millis(slider.getValue()), source.circle);
                    ft1.setToValue(Color.RED);
                    ft1.play();
                    paused = true;
                    playing = false;
                    textFlow.appendText("---Finished--\n");
                });
                st.onFinishedProperty();
                st.play();
                playing = true;
                paused = false;
                //</editor-fold>
            }
        }

        //<editor-fold defaultstate="collapsed" desc="BFS">
        public void newBFS(Node source) {
            new BFS(source);
        }

        class BFS {

            BFS(Node source) {

                //<editor-fold defaultstate="collapsed" desc="Set labels and distances">
                for (NodeFX n : circles) {
                    distances.add(n.distance);
                    n.distance.setLayoutX(n.point.x + 20);
                    n.distance.setLayoutY(n.point.y);
                    canvasGroup.getChildren().add(n.distance);
                }
                sourceText.setLayoutX(source.circle.point.x + 20);
                sourceText.setLayoutY(source.circle.point.y + 10);
                canvasGroup.getChildren().add(sourceText);
                st = new SequentialTransition();
                source.circle.distance.setText("Dist. : " + 0);
                //</editor-fold>

                source.minDistance = 0;
                source.visited = true;
                LinkedList<Node> q = new LinkedList<Node>();
                q.push(source);
                while (!q.isEmpty()) {
                    Node u = q.removeLast();
                    //<editor-fold defaultstate="collapsed" desc="Node Popped Animation">
                    FillTransition ft = new FillTransition(Duration.millis(slider.getValue()), u.circle);
                    if (u.circle.getFill() == Color.BLACK) {
                        ft.setToValue(Color.CHOCOLATE);
                    }
                    st.getChildren().add(ft);

                    String str = "";
                    str = str.concat("Popped : Node(" + u.name + ")\n");
                    final String str2 = str;
                    FadeTransition fd = new FadeTransition(Duration.millis(10), textFlow);
                    fd.setOnFinished(e -> {
                        textFlow.appendText(str2);
                    });
                    fd.onFinishedProperty();
                    st.getChildren().add(fd);
                    //</editor-fold>
                    System.out.println(u.name);
                    for (EEDGE e : u.adjacents) {
                        if (e != null) {
                            Node v = e.target;

                            if (!v.visited) {
                                v.minDistance = u.minDistance + 1;
                                v.visited = true;
                                q.push(v);
                                v.previous = u;

                                //<editor-fold defaultstate="collapsed" desc="Node visiting animation">
                                //<editor-fold defaultstate="collapsed" desc="Change Edge colors">
                                if (undirected) {
                                    StrokeTransition ftEdge = new StrokeTransition(Duration.millis(slider.getValue()), e.line);
                                    ftEdge.setToValue(Color.FORESTGREEN);
                                    st.getChildren().add(ftEdge);
                                } else if (directed) {
                                    FillTransition ftEdge = new FillTransition(Duration.millis(slider.getValue()), e.line);
                                    ftEdge.setToValue(Color.FORESTGREEN);
                                    st.getChildren().add(ftEdge);
                                }
                                //</editor-fold>
                                FillTransition ft1 = new FillTransition(Duration.millis(slider.getValue()), v.circle);
                                ft1.setToValue(Color.FORESTGREEN);
                                ft1.setOnFinished(ev -> {
                                    v.circle.distance.setText("Dist. : " + v.minDistance);
                                });
                                ft1.onFinishedProperty();
                                st.getChildren().add(ft1);

                                str = "\t";
                                str = str.concat("Pushing : Node(" + v.name + ")\n");
                                final String str1 = str;
                                FadeTransition fd2 = new FadeTransition(Duration.millis(10), textFlow);
                                fd2.setOnFinished(ev -> {
                                    textFlow.appendText(str1);
                                });
                                fd2.onFinishedProperty();
                                st.getChildren().add(fd2);
                                //</editor-fold>
                            }
                        }
                    }
                    //<editor-fold defaultstate="collapsed" desc="Animation Control">
                    FillTransition ft2 = new FillTransition(Duration.millis(slider.getValue()), u.circle);
                    ft2.setToValue(Color.BLUEVIOLET);
                    st.getChildren().add(ft2);
                    //</editor-fold>
                }

                //<editor-fold defaultstate="collapsed" desc="Animation Control">
                st.setOnFinished(ev -> {
                    for (NodeFX n : circles) {
                        FillTransition ft1 = new FillTransition(Duration.millis(slider.getValue()), n);
                        ft1.setToValue(Color.BLACK);
                        ft1.play();
                    }
                    if (directed) {
                        for (Shape n : edges) {
                            n.setFill(Color.BLACK);
                        }
                    } else if (undirected) {
                        for (Shape n : edges) {
                            n.setStroke(Color.BLACK);
                        }
                    }
                    FillTransition ft1 = new FillTransition(Duration.millis(slider.getValue()), source.circle);
                    ft1.setToValue(Color.RED);
                    ft1.play();
                    paused = true;
                    playing = false;
                    textFlow.appendText("---Finished--\n");
                });
                st.onFinishedProperty();
                st.play();
                playing = true;
                paused = false;
                //</editor-fold>

            }
        }


    }

    /**
     * @author Nathan LEFEVRE
     * @version 1.0.0
     * Génère une matrice a partir du graph construit.
     * @return tab int[][]
     */
    public int[][] getMatriceGraph()
    {
        int nbSommets = g.getSommets().size();
        int[][] matrice = new int[nbSommets][nbSommets];

        for (int i=0; i<nbSommets; i++)
        {
            for (int j=0; j<nbSommets; j++)
            {
                boolean existe = edgeExists(circles.get(i), circles.get(j));
                if (existe)
                    matrice[j][i] = 1;
                 else
                    matrice[j][i] = 0;
            }
        }
        return matrice;
    }

    public void afficheMatrice(int[][] matrice)
    {
        System.out.print("   ");
        for (int i=0; i<matrice.length; i++)
        {
            System.out.print(i+1 + "   ");
        }
        System.out.print("\n");
        for (int i=0; i<matrice.length; i++)
        {
            System.out.print(i+1 + "  ");
            for (int j=0; j<matrice.length; j++)
            {
                System.out.print(matrice[j][i] + "   ");
            }
            System.out.print("\n");
        }
    }
}
