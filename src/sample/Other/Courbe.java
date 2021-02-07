package sample.Other;

import javafx.application.Application;
import javafx.scene.paint.Color;
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
                new MoveTo(0, 300),
                //new LineTo(500, 50));
                //new LineTo(150, 150),);
                new QuadCurveTo(200, 250, 400, 300));
                //new CubicCurveTo(0, 250, 400, 0, 300, 250));

        final Pane root = new Pane(path);
        final Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Test de chemin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
