package sample.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Control_Matrix_one {
    public static boolean[][] matrice;
    public static boolean etat_matrice = false;
    public static int ValueNBsommet;
    @FXML
    TextField NBSommet;
    @FXML
    Button saisir , retour , Effacer , plan , aide;
    @FXML
    ToggleButton A1 , A2 ,A3 ,A4 ,A5;
    @FXML
    ToggleButton B1 , B2 ,B3 ,B4 ,B5;
    @FXML
    ToggleButton C1 , C2 ,C3 ,C4 ,C5;
    @FXML
    ToggleButton D1 , D2 ,D3 ,D4 ,D5;
    @FXML
    ToggleButton E1 , E2 ,E3 ,E4 ,E5;

    //
    // Change l'apparance de la matrice avec l'input Saisir
    //
    public void initialize()
    {
        saisir.setOnAction(this::handle);

        Effacer.setOnAction(event -> {
            A1.setVisible(true);A2.setVisible(true);A3.setVisible(true);A4.setVisible(true);A5.setVisible(true);
            B1.setVisible(true);B2.setVisible(true);B3.setVisible(true);B4.setVisible(true);B5.setVisible(true);
            C1.setVisible(true);C2.setVisible(true);C3.setVisible(true);C4.setVisible(true);C5.setVisible(true);
            D1.setVisible(true);D2.setVisible(true);D3.setVisible(true);D4.setVisible(true);D5.setVisible(true);
            E1.setVisible(true);E2.setVisible(true);E3.setVisible(true);E4.setVisible(true);E5.setVisible(true);

            A1.setSelected(false);A2.setSelected(false);A3.setSelected(false);A4.setSelected(false);A5.setSelected(false);
            B1.setSelected(false);B2.setSelected(false);B3.setSelected(false);B4.setSelected(false);B5.setSelected(false);
            C1.setSelected(false);C2.setSelected(false);C3.setSelected(false);C4.setSelected(false);C5.setSelected(false);
            D1.setSelected(false);D2.setSelected(false);D3.setSelected(false);D4.setSelected(false);D5.setSelected(false);
            E1.setSelected(false);E2.setSelected(false);E3.setSelected(false);E4.setSelected(false);E5.setSelected(false);
        });
        retour.setOnAction(e -> loadNextScene("/sample/Fxml/CHOIX.fxml"));
        plan.setOnAction(e -> {
            matrice = new boolean[][]{
                    {A1.isSelected(), A2.isSelected(), A3.isSelected(), A4.isSelected(), A5.isSelected()},
                    {B1.isSelected(), B2.isSelected(), B3.isSelected(), B4.isSelected(), B5.isSelected()},
                    {C1.isSelected(), C2.isSelected(), C3.isSelected(), C4.isSelected(), C5.isSelected()},
                    {D1.isSelected(), D2.isSelected(), D3.isSelected(), D4.isSelected(), D5.isSelected()},
                    {E1.isSelected(), E2.isSelected(), E3.isSelected(), E4.isSelected(), E5.isSelected()}
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
                    "Compléter votre matrice avec des 1 (=arête presente) et 0 (=arête inexistant).\n" +
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

    private void handle(ActionEvent event) {
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
    }
}
