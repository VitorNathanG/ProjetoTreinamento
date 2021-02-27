package classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import layout.PrincipalController;

import java.io.File;
import java.io.IOException;

/**
 * Classe Main do software Organizador de Treinamento.
 * A execução do programa começa por aqui, abrindo o Stage para o layout principal.FXML, que é controlado pela classe
 * PrincipalController, ambos no pacote layout.
 * A classe também faz as definições do onCloseRequest da janela principal do software.
 *
 * @author Vitor Nathan Gonçalves
 */
public class Main extends Application {

    public static void main(String[] args){

        File pastaBackup = new File("backup");  //Pasta onde serão armazenados os backups dos dados.
        if (!pastaBackup.exists()) {
            pastaBackup.mkdir();
        }

        File dados = new File("dados");         //Pasta onde serão armazenados os dados principais do programa.
        if (!dados.exists()) {
            dados.mkdir();
        }
        launch(args);                                    //Inicia o método start() da classe, necessário para o JavaFX
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Carrega o arquivo do layout principal
        Parent root = FXMLLoader.load(getClass().getResource("/layout/principal.fxml"));

        //Definições da Scene e do Stage do programa
        Scene scene = new Scene(root);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(400);

        primaryStage.setScene(scene);
        primaryStage.show();

        definicoesDeFechamento(primaryStage); //Inicia o loop de espera para fechamento
    }

    /**
     * Observa um Stage, de modo a capturar e tratar as requisições de fechamento do mesmo
     * @param primaryStage Stage que será observado
     */
    public void definicoesDeFechamento(Stage primaryStage) {
        primaryStage.setOnCloseRequest(observer->{
            if(PrincipalController.isModificacoesRealizadas()){ //Verifica se foram realizadas modificações
                observer.consume();                             //Consome o pedido de fechamento, para que ele não ocorra
                Stage novoPalco = new Stage();
                Parent parent = null;

                //Carrega o layout do alert box de confirmação de salvamento
                try {
                    parent = FXMLLoader.load(getClass().getResource("/layout/alertboxsair.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene cena = new Scene(parent);
                novoPalco.setScene(cena);
                novoPalco.setResizable(false);
                novoPalco.show();               //Mostra o layout de confirmação de salvamento
            }
        });
    }
}
