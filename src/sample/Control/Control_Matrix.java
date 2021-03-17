package sample.Control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Control_Matrix {
    public static String[][] matrice;
    public static int ValueNBsommet;
    public static boolean etat_matrice = false;
    @FXML
    TextField NBSommet;
    @FXML
    Button Saisir , Retour , Effacer , Plan , aide;
    @FXML
    TextField A1 , A2 ,A3 ,A4 ,A5;
    @FXML
    TextField B1 , B2 ,B3 ,B4 ,B5;
    @FXML
    TextField C1 , C2 ,C3 ,C4 ,C5;
    @FXML
    TextField D1 , D2 ,D3 ,D4 ,D5;
    @FXML
    TextField E1 , E2 ,E3 ,E4 ,E5;
    //
    // Change l'apparance de la matrice avec l'input
    //
    public void initialize()
    {
        Saisir.setOnAction(event -> {
            switch (NBSommet.getText()) {
                case "2":
                    A1.setVisible(true);
                    A2.setVisible(true);
                    A3.setVisible(false);
                    A4.setVisible(false);
                    A5.setVisible(false);
                    B1.setVisible(true);
                    B2.setVisible(true);
                    B3.setVisible(false);
                    B4.setVisible(false);
                    B5.setVisible(false);
                    C1.setVisible(false);
                    C2.setVisible(false);
                    C3.setVisible(false);
                    C4.setVisible(false);
                    C5.setVisible(false);
                    D1.setVisible(false);
                    D2.setVisible(false);
                    D3.setVisible(false);
                    D4.setVisible(false);
                    D5.setVisible(false);
                    E1.setVisible(false);
                    E2.setVisible(false);
                    E3.setVisible(false);
                    E4.setVisible(false);
                    E5.setVisible(false);
                    break;
                case "3":
                    A1.setVisible(true);
                    A2.setVisible(true);
                    A3.setVisible(true);
                    A4.setVisible(false);
                    A5.setVisible(false);
                    B1.setVisible(true);
                    B2.setVisible(true);
                    B3.setVisible(true);
                    B4.setVisible(false);
                    B5.setVisible(false);
                    C1.setVisible(true);
                    C2.setVisible(true);
                    C3.setVisible(true);
                    C4.setVisible(false);
                    C5.setVisible(false);
                    D1.setVisible(false);
                    D2.setVisible(false);
                    D3.setVisible(false);
                    D4.setVisible(false);
                    D5.setVisible(false);
                    E1.setVisible(false);
                    E2.setVisible(false);
                    E3.setVisible(false);
                    E4.setVisible(false);
                    E5.setVisible(false);

                    break;
                case "4":
                    A1.setVisible(true);
                    A2.setVisible(true);
                    A3.setVisible(true);
                    A4.setVisible(true);
                    A5.setVisible(false);
                    B1.setVisible(true);
                    B2.setVisible(true);
                    B3.setVisible(true);
                    B4.setVisible(true);
                    B5.setVisible(false);
                    C1.setVisible(true);
                    C2.setVisible(true);
                    C3.setVisible(true);
                    C4.setVisible(true);
                    C5.setVisible(false);
                    D1.setVisible(true);
                    D2.setVisible(true);
                    D3.setVisible(true);
                    D4.setVisible(true);
                    D5.setVisible(false);
                    E1.setVisible(false);
                    E2.setVisible(false);
                    E3.setVisible(false);
                    E4.setVisible(false);
                    E5.setVisible(false);
                    break;
                case "5":
                    A1.setVisible(true);
                    A2.setVisible(true);
                    A3.setVisible(true);
                    A4.setVisible(true);
                    A5.setVisible(true);
                    B1.setVisible(true);
                    B2.setVisible(true);
                    B3.setVisible(true);
                    B4.setVisible(true);
                    B5.setVisible(true);
                    C1.setVisible(true);
                    C2.setVisible(true);
                    C3.setVisible(true);
                    C4.setVisible(true);
                    C5.setVisible(true);
                    D1.setVisible(true);
                    D2.setVisible(true);
                    D3.setVisible(true);
                    D4.setVisible(true);
                    D5.setVisible(true);
                    E1.setVisible(true);
                    E2.setVisible(true);
                    E3.setVisible(true);
                    E4.setVisible(true);
                    E5.setVisible(true);
                    break;
            }
        });

        Effacer.setOnAction(event -> {
            A1.setVisible(true);A2.setVisible(true);A3.setVisible(true);A4.setVisible(true);A5.setVisible(true);
            B1.setVisible(true);B2.setVisible(true);B3.setVisible(true);B4.setVisible(true);B5.setVisible(true);
            C1.setVisible(true);C2.setVisible(true);C3.setVisible(true);C4.setVisible(true);C5.setVisible(true);
            D1.setVisible(true);D2.setVisible(true);D3.setVisible(true);D4.setVisible(true);D5.setVisible(true);
            E1.setVisible(true);E2.setVisible(true);E3.setVisible(true);E4.setVisible(true);E5.setVisible(true);

            A1.setText("");A2.setText("");A3.setText("");A4.setText("");A5.setText("");
            B1.setText("");B2.setText("");B3.setText("");B4.setText("");B5.setText("");
            C1.setText("");C2.setText("");C3.setText("");C4.setText("");C5.setText("");
            D1.setText("");D2.setText("");D3.setText("");D4.setText("");D5.setText("");
            E1.setText("");E2.setText("");E3.setText("");E4.setText("");E5.setText("");
        });
        Retour.setOnAction(e -> loadNextScene("/sample/Fxml/CHOIX.fxml"));
        Plan.setOnAction(e -> {
            matrice = new String[][]{
                    {A1.getText(), A2.getText(), A3.getText(), A4.getText(), A5.getText()},
                    {B1.getText(), B2.getText(), B3.getText(), B4.getText(), B5.getText()},
                    {C1.getText(), C2.getText(), C3.getText(), C4.getText(), C5.getText()},
                    {D1.getText(), D2.getText(), D3.getText(), D4.getText(), D5.getText()},
                    {E1.getText(), E2.getText(), E3.getText(), E4.getText(), E5.getText()}
            };
            etat_matrice = true;
            ValueNBsommet = Integer.parseInt(NBSommet.getText());
            loadNextScene("/sample/PLAN.fxml");
        });
        aide.setOnAction(event -> {

            Label secondLabel;

            secondLabel = new Label("Ici, vous allez pouvoir générer un graph via une matrice que vous allez compléter.\n" +
                    "\n" +
                    "Commencé par rentrée un nombre de sommet pour générer la matrice, puis cliquez sur valider.\n" +
                    "Compléter votre matrice avec les poids des arretes. Pour sinifier qu'il n'y a pas d'arrete entrée ............... .\n" +
                    "Pour générer votre graphe cliquez sur 'PLAN'.");

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
}
