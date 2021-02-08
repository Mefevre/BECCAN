package sample;
import java.awt.Point;
import java.net.URL;
import java.util.*;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Algo.Dsatur.Arret;
import sample.Algo.Dsatur.GrapheDSat;
import sample.Algo.Dsatur.Sommet;
import sample.Control.Control_Choix;
import sample.Control.Control_Matrix;
import sample.Control.Control_Matrix_one;
import sample.Algo.BellmanFord.BellmanFord;

public class PanelPlan implements Initializable, ChangeListener {

    public Pane canvasGroup;
    public ToggleButton edge;
    public ToggleButton node ,dijkstraButton , BFSS;
    NodeFX selectedNode = null;
    private Label sourceText = new Label("Source"), weight;
    private ARROW arrow;
    private Linearrow_courbé Arrow_cou;
    @FXML
    private ComboBox List,Welsh;
    @FXML
    private TextArea MatriX;
    @FXML
    private Button vitesse;
    @FXML
    private Slider slider = new Slider();
    private Line edgeLine;
    public static TextArea textFlow = new TextArea();
    List<Label> distances = new ArrayList<Label>();

    boolean addNode = true, addEdge = false,paused = false,playing = false , dijkstra , Bfs = false;
    Algorithm algo = new Algorithm();
    public SequentialTransition st;
    public SequentialTransition stcolor;
    public static final int MAX_VALUE = 999; //bellman ford
    public int ValueNBsommet = 0;//bellmanFord
    public boolean WelABoolean = false;

    private boolean directed = Control_Choix.directed, undirected = Control_Choix.undirected , weighted = Control_Choix.weighted, unweighted = Control_Choix.unweighted;
    private String [][]matrice_nb = Control_Matrix.matrice;
    private boolean [][]matrice_true = Control_Matrix_one.matrice;
    private boolean etat_matrice = Control_Matrix.etat_matrice;
    private boolean etat_matrice_one = Control_Matrix_one.etat_matrice;
    private int[][] matriceBellman = new int[][]{{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
    private int[][] matriceBellmanRES = new int[ValueNBsommet][ValueNBsommet];
    int NB, NB2 , time = 700;;

    GrapheDSat g =new GrapheDSat();

    List<String> ListALgo = new ArrayList<>();
    List<NodeFX> circles = new ArrayList<NodeFX>();
    List<Shape> edges = new ArrayList<>();
    List<EEDGE> mstEdges = new ArrayList<EEDGE>();
    List<EEDGE> realEdges = new ArrayList<EEDGE>();
    List<Color> colorName = new ArrayList<Color>();
    List<String> colorCombo = new ArrayList<String>();
    //List<NodeE> ListNode = new ArrayList<>();

    int nNode = 0;
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
        colorCombo.add("RED");
        colorCombo.add("GREEN");
        colorCombo.add("ORANGE");
        colorCombo.add("PINK");
        colorCombo.add("YELLOW");
        colorCombo.add("VIOLET");
        Welsh.getItems().addAll(colorCombo);
        //Combobox
        if (true)//teste de la combobox
        {
            ListALgo.add("color");
            ListALgo.add("Welsh Powell");
            ListALgo.add("Dsatur");
            ListALgo.add("Eulérien");
            ListALgo.add("Hamiltonien");
            ListALgo.add("Kruskal");
            ListALgo.add("Prim");
            ListALgo.add("Bellman Ford");
            List.getItems().addAll(ListALgo);
        }
    }
    @FXML
    //Gere l'affichage de slider
    private void HandleV ()
    {

        vitesse.setOnMouseClicked((event) -> {

            vitesse.setVisible(false);
            slider.setVisible(true);

        });



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
        //time = temp;
        //System.out.println(time);
    }

    public void aide()
    {
        Label secondLabel;

        secondLabel = new Label("Page d'aide pour Premiere page :"+"\n"
                +"Page D'accueil : Le button centrale lance le logicile ."+"\n"+"\n"
                +"Page de choix : Vous devais decidé les type de votre graph un choix possible pour les poids"+"\n"
                +"et pour l'orientation."+"\n"+"\n"
                +"Page de la matrice : Vous deviez tout d'abord mettre le Nb de sommet puis remplir la matrice"+"\n"
                +"avec passe à l'etape du plan pour mettre en meouvre les algo."+"\n"+"\n"
                +"Page de plan : Cette page affiche le graph et sert a le modifier , on peut applique des algorithme"+"\n"
                +"sur le graph afficher."+"\n");

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

    public void handle(MouseEvent ev) {

        if (addNode) {
            if (ev.getEventType() == MouseEvent.MOUSE_CLICKED && WelABoolean == false && ev.getButton() == MouseButton.PRIMARY)
            {

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
            }
            else if (ev.getEventType() == MouseEvent.MOUSE_CLICKED && WelABoolean == true)
            {
                for (NodeFX node : circles)
                {
                    node.setOnMouseClicked(mouseHandler);
                }
            }
        }
    }
    boolean edgeExists(NodeFX u, NodeFX v) {
        for (EEDGE e : realEdges) {
            if (e.source == u.node && e.target == v.node || e.source == v.node && e.target == u.node) {
                return true;
            }
        }
        return false;
    }

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
            NodeFX circle = (NodeFX) mouseEvent.getSource();
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && mouseEvent.getButton() == MouseButton.PRIMARY && WelABoolean == true)
            {
                FillTransition ft1 = new FillTransition(Duration.millis(300), circle, Color.BLACK, colorName.get(Welsh.getSelectionModel().getSelectedIndex()+1));
                ft1.play();
            }
            else if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && mouseEvent.getButton() == MouseButton.PRIMARY && addEdge == true) {
                if (!circle.isSelected) {
                    if (selectedNode != null) {
                        weight = new Label();
                        if (!edgeExists(selectedNode, circle))
                        {
                            System.out.println("Adding Edge");
                        //DESINE LES LIGNE OU FLECHE ENTRE LES POINTS
                        if (undirected) {
                            Line edgeLine = new Line(selectedNode.point.x, selectedNode.point.y, circle.point.x, circle.point.y);
                            canvasGroup.getChildren().add(edgeLine);
                            edgeLine.setId("line");
                            g.ajouterArret(new Arret(selectedNode.nodeBIS, circle.nodeBIS));
                        } else if (directed) {
                            arrow = new ARROW(selectedNode.point.x, selectedNode.point.y, circle.point.x, circle.point.y);
                            canvasGroup.getChildren().add(arrow);
                            arrow.setId("arrow");
                            g.ajouterArret(new Arret(selectedNode.nodeBIS, circle.nodeBIS));
                        }

                        /*else
                        {
                            System.out.println("Adding Edge courbé");
                            //DESINE LES LIGNE OU FLECHE ENTRE LES POINTS
                            if (undirected)
                            {
                                Arrow_cou = new Linearrow_courbé(selectedNode.point.x, selectedNode.point.y, circle.point.x, circle.point.y , -1);
                                canvasGroup.getChildren().add(Arrow_cou);
                                arrow.setId("line");
                                g.ajouterArret(new Arret(selectedNode.nodeBIS, circle.nodeBIS));
                            }
                            else if (directed)
                            {
                                Arrow_cou = new Linearrow_courbé(selectedNode.point.x, selectedNode.point.y, circle.point.x, circle.point.y);
                                canvasGroup.getChildren().add(Arrow_cou);
                                arrow.setId("arrow");
                                g.ajouterArret(new Arret(selectedNode.nodeBIS, circle.nodeBIS));
                            }
                        }*/
                        if (weighted) {
                            weight.setLayoutX(((selectedNode.point.x) + (circle.point.x)) / 2);
                            weight.setLayoutY(((selectedNode.point.y) + (circle.point.y)) / 2);

                            TextInputDialog dialog = new TextInputDialog("0");
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
                            //                                circle.node.revAdjacents.add(new Edge(circle.node, selectedNode.node, Integer.valueOf(weight.getText()), arrow));
                            edges.add(arrow);
                            line_arrow = arrow;
                            realEdges.add(temp);
                        }
                    }
                        if (addNode || addEdge)
                        {
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
                    if (dijkstra)
                    {
                        algo.newDijkstra(circle.node);
                    }
                    else if (Bfs)
                    {
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
    //calcule la matrice du graph dans le plan
    public void MatriceGR()
    {
        matriceBellman = new int[][]{{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        for (NodeFX u : circles)
        {
            //ListNode.add(u.node);
            ValueNBsommet++;
            for (EEDGE e : u.node.adjacents)
            {
                matriceBellman[Integer.parseInt(e.source.name)][Integer.parseInt(e.target.name)] =(int)e.weight;
            }
        }

    }
    public void AppelBellmanFord()
    {
        int numberofvertices = 0;
        int source = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of vertices");
        numberofvertices = ValueNBsommet;//nb de sommet

        int adjacencymatrix[][] = new int[numberofvertices + 1][numberofvertices + 1];
        System.out.println("Enter the adjacency matrix");
        for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++)
        {
            for (int destinationnode = 1; destinationnode <= numberofvertices; destinationnode++)
            {
                adjacencymatrix[sourcenode][destinationnode] = matriceBellman[sourcenode][destinationnode];// ICI
                if (sourcenode == destinationnode)
                {
                    adjacencymatrix[sourcenode][destinationnode] = 0;
                    continue;
                }
                if (adjacencymatrix[sourcenode][destinationnode] == 0)
                {
                    adjacencymatrix[sourcenode][destinationnode] = MAX_VALUE;
                }
            }
        }
        //ICI
        System.out.println("Enter the source vertex [1 -- "+numberofvertices+"]");
        //source = scanner.nextInt();// on veut tout le tableau .
        MatriX.appendText("     ");
        for (int o = 1;o<=numberofvertices;o++)
        {
            MatriX.appendText("  "+o+"   ");
        }
        MatriX.appendText("\n"+"      ");
        for (int g = 1;g<=numberofvertices;g++)
        {
            MatriX.appendText("____");
        }
        MatriX.appendText("\n");
        for (int i = 1 ; i<=numberofvertices ; i++ )
        {
            BellmanFord bellmanford = new BellmanFord(numberofvertices);
            matriceBellmanRES = bellmanford.BellmanFordEvaluation(i,adjacencymatrix);
            MatriX.appendText(i+"   |");
            for (int j = 0 ;j< matriceBellmanRES.length;j++)
            {
                if (matriceBellmanRES[i-1][j] == 884)
                    MatriX.setText("Cycle negative !! ");
                else if (matriceBellmanRES[i-1][j] == 999)
                    MatriX.appendText(" [inf+] ");
                else
                    MatriX.appendText(String.valueOf(" ["+matriceBellmanRES[i-1][j])+"]  ");
            }
            MatriX.appendText("\n");

        }

    }
    public void WelshPowell()
    {

        WelABoolean = true;
    }
    public void ListALgo()
    {
        if (List.getSelectionModel().getSelectedItem().equals("color"))
            COLOR();
        else if (List.getSelectionModel().getSelectedItem().equals("Dsatur"))
        {
            dijkstra = true;
            Bfs = false;
        }
        else if (List.getSelectionModel().getSelectedItem().equals("Bellman Ford"))
        {
            if (etat_matrice ||etat_matrice_one)
            {
                //utilise la matrice créé
            }
            else
            {
                MatriX.setText("Resultat de Bellman-Ford\n");
                MatriceGR();
                AppelBellmanFord();

            }
        }
        else if (List.getSelectionModel().getSelectedItem().equals("Welsh Powell"))
        {
            WelshPowell();

        }

    }

    public void COLOR()
    {

        int U = 0;
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

        stcolor1.setOnFinished( event -> {
            for (NodeFX n : circles) {
                FillTransition unftcolor = new FillTransition(Duration.millis(slider.getValue()), n);
                unftcolor.setToValue(Color.BLACK);
                unftcolor.play();
            }
      });
    }




    public void DijkstraHandle(ActionEvent actionEvent) {
        dijkstra = true;
        Bfs = false;
    }
    public void BFSHandle(ActionEvent actionEvent) {
        Bfs = true;
        dijkstra = false;
    }

    public void ResetHandle(ActionEvent event) //a faire
    {
    }

    /**
     * Shape class for the nodes.
     */
    class NodeFX extends Circle {
        NodeE node;
        Sommet nodeBIS;
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

    public class Algorithm {
        private NodeE source;

        //<editor-fold defaultstate="collapsed" desc="Dijkstra">
        public void newDijkstra(NodeE source) {
            new Dijkstra(source);
        }

        class Dijkstra {

            Dijkstra(NodeE source) {
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
                PriorityQueue<NodeE> pq = new PriorityQueue<NodeE>();
                pq.add(source);
                while (!pq.isEmpty()) {
                    NodeE u = pq.poll();
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
                            NodeE v = e.target;
                            System.out.println("HERE " + v.name);
                            if (u.minDistance + e.weight < v.minDistance) {
                                pq.remove(v);
                                v.minDistance = u.minDistance + e.weight;
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
        public void newBFS(NodeE source) {
            new BFS(source);
        }

        class BFS {

            BFS(NodeE source) {

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
                LinkedList<NodeE> q = new LinkedList<NodeE>();
                q.push(source);
                while (!q.isEmpty()) {
                    NodeE u = q.removeLast();
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
                            NodeE v = e.target;

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
}


