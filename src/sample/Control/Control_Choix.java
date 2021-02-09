package sample.Control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Control_Choix {
    public static boolean directed = false, undirected = false, weighted = false, unweighted = false;
    public static boolean etat_choix = false;
    @FXML
    public Button panelNext,NextMatrix , aide;
    @FXML
    private ToggleButton dButton, udButton, wButton, uwButton;

    //
    // Met des Var en boolean pour les testés
    //
    public void initialize()
    {
        dButton.setSelected(directed);
        wButton.setSelected(weighted);
        udButton.setSelected(undirected);
        uwButton.setSelected(unweighted);


        dButton.setOnAction(e -> {
            directed = true;
            undirected = false;
            System.out.println("direction_Button");
        });
        udButton.setOnAction(e -> {
            directed = false;
            undirected = true;
            System.out.println("no_direction_Button");
        });
        wButton.setOnAction(e -> {
            weighted = true;
            unweighted = false;
            System.out.println("poids_Button");
        });
        uwButton.setOnAction(e -> {
            weighted = false;
            unweighted = true;
            System.out.println("no_poids_Button");
        });
        panelNext.setOnAction(event -> {etat_choix = true; loadNextScene("/sample/PLAN.fxml") ; });
        NextMatrix.setOnAction(e -> {

            if (directed || undirected && weighted || unweighted)
                {
                    if (weighted)
                        loadNextScene("/sample/Fxml/MATRI.fxml");
                    else if(unweighted)
                        loadNextScene("/sample/Fxml/MATRIsansPoids.fxml");
                }
            else
                showAlertWithoutHeaderText();

        });
        aide.setOnAction(event -> {

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
        });
    }
    //
    //ELle est pas appel mais il faut la laissé
    //
    /*private void FadeOut() {
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(panel1);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(e -> {
            loadNextScene();
        });
        ft.play();
        System.out.println("Here");
    }*/
    //
    //Charge la page suivante qui est PLAN
    //
    void loadNextScene(String page) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
            Parent root = loader.load();
            Scene newScene = new Scene(root);

            Main.primaryStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(Control_Choix.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Show a Information Alert without Header Text
    private void showAlertWithoutHeaderText()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Test Connection");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Tu doit choisir une option pour la parti poids et la partie orientation !");

        alert.showAndWait();
    }
}
