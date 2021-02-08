package sample.Control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Control_Start {

    @FXML
    public Button nextSTART , aide;


    public void initialize()
    {
        nextSTART.setOnAction(event -> loadNextScene_1());
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
    void loadNextScene_1() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Fxml/CHOIX.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);

            Main.primaryStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(Control_Choix.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
