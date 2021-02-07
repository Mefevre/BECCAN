package sample.Other;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Courbe extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final Path path = new Path();
        path.getElements().setAll(
                new MoveTo(50, 50),
                new LineTo(100, 50),
                new LineTo(150, 150),
                new QuadCurveTo(150, 100, 250, 200),
                new CubicCurveTo(0, 250, 400, 0, 300, 250));
        final Pane root = new Pane(path);
        final Scene scene = new Scene(root, 350, 300);
        primaryStage.setTitle("Test de chemin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
