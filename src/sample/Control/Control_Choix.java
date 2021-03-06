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
    public Button panelNext,RETOUR , aide;
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
        panelNext.setOnAction(event -> {
            etat_choix = true;
            if (weighted || unweighted && directed||undirected)
                loadNextScene("/sample/PLAN.fxml") ;

        });
        RETOUR.setOnAction(e -> {
            loadNextScene("/sample/FXml/START.fxml") ;

            /*if (directed || undirected && weighted || unweighted)
                {
                    if (weighted)
                        loadNextScene("/sample/Fxml/MATRI.fxml");
                    else if(unweighted)
                        loadNextScene("/sample/Fxml/MATRIsansPoids.fxml");
                }
            else
                showAlertWithoutHeaderText();
             */

        });
        aide.setOnAction(event -> {

            Label secondLabel;

            secondLabel = new Label("Sur cette page, vous allez devoir choisir le type de graphe que vous souhaitez créer.\n" +
                    "\n" +
                    "Il y a 2 caractéristique principales à un graphe :\n" +
                    "   - si les arêtes sont valuées ou non valuées.\n" +
                    "   - si un graphe est orienté ou non. \n       l'arête a une source et une destination, et un sens.\n" +
                    "\n" +
                    "Puis, quand vous avez determiné vos 2 caratéristiques, vous pouvez construire votre graphe \nen plaçant des sommets sur un plan, ou le construire à partir d'une matrice.");

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
            Main.primaryStage.setResizable(false);

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
