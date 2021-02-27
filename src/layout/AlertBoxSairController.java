package layout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe AlertBoxSairController: controlador do pop-up de requisição de salvamento que é gerado quando o usuário
 * tenta fechar o programa.
 *
 * @author Vitor Nathan Gonçalves
 */
public class AlertBoxSairController implements Initializable {

    //Definição dos elementos do alertBox
    @FXML private AnchorPane baseLayout;
    @FXML private Button botaoSim;
    @FXML private Button botaoNao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Gerencia o processo de fechamento quando o usuário deseja salvar os dados modificados ao sair
     */
    public void botaoSimClicked(){
        PrincipalController.salvarAoSair.setValue(1);
        Stage palco = (Stage) baseLayout.getScene().getWindow();
        palco.close();
    }

    /**
     * Gerencia o processo de fechamento quando o usuário não deseja salvar os dados
     */
    public void botaoNaoClicked(){
        PrincipalController.salvarAoSair.setValue(-1);
        Stage palco = (Stage) baseLayout.getScene().getWindow();
        palco.close();
    }
}
