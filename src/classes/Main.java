package classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import layout.PrincipalController;

import java.io.IOException;

public class Main extends Application {

    public static void main(String args[]){
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

        definicoesDeFechamento(primaryStage);
    }

    public void definicoesDeFechamento(Stage primaryStage) {
        primaryStage.setOnCloseRequest(observer->{
            if(PrincipalController.isModificacoesRealizadas()){
                observer.consume();
                Stage novoPalco = new Stage();
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(getClass().getResource("/layout/alertboxsair.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene cena = new Scene(parent);
                novoPalco.setScene(cena);
                novoPalco.setResizable(false);
                novoPalco.show();
            }
        });
    }
}
