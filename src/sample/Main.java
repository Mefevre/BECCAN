package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage sta) throws Exception{

        primaryStage = sta ;
        Parent root = FXMLLoader.load(getClass().getResource("Fxml/START.fxml"));
        primaryStage.setTitle("LOGICIEL DE GRAPH");
        primaryStage.setScene(new Scene(root, 786, 568));
        primaryStage.show();
    }
}
