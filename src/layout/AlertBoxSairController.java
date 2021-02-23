package layout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertBoxSairController implements Initializable {

    @FXML private AnchorPane baseLayout;
    @FXML private Button botaoSim;
    @FXML private Button botaoNao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void botaoSimClicked(){
        PrincipalController.salvarAoSair.setValue(1);
        Stage palco = (Stage) baseLayout.getScene().getWindow();
        palco.close();
    }

    public void botaoNaoClicked(){
        PrincipalController.salvarAoSair.setValue(-1);
        Stage palco = (Stage) baseLayout.getScene().getWindow();
        palco.close();
    }
}
