package classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    
    public static void main(String args[]){
        Pessoa vitor = new Pessoa("Vitor Nathan", "Café", "Café", "Agua", "Domo");
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/layout/principal.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
