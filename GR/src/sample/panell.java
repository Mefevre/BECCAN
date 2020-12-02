package sample;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class panell {
    public static boolean directed = false, undirected = false, weighted = false, unweighted = false;
    @FXML
    public Button panelNext;
    @FXML
    private RadioButton dButton, udButton, wButton, uwButton;
    @FXML
    private AnchorPane panel1;

    static Controller cref;

    public void initialize()
    {
        dButton.setSelected(directed);
        wButton.setSelected(weighted);
        udButton.setSelected(undirected);
        uwButton.setSelected(unweighted);

        dButton.setOnAction(e -> {
            directed = true;
            undirected = false;
            System.out.println("dButton");
        });
        udButton.setOnAction(e -> {
            directed = false;
            undirected = true;
            System.out.println("udButton");
        });
        wButton.setOnAction(e -> {
            weighted = true;
            unweighted = false;
            System.out.println("wButton");
        });
        uwButton.setOnAction(e -> {
            weighted = false;
            unweighted = true;
            System.out.println("uwButton");
        });
        panelNext.setOnAction(e -> {
            loadNextScene();
        });
    }

    private void FadeOut() {
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
    }
    void loadNextScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            cref = loader.getController();

            Main.primaryStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(panell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
