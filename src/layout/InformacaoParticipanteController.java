package layout;

import classes.Pessoa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe InformacaoParticipanteController: controlador do pop-up com os detalhes de um participante
 *
 * @author Vitor Nathan Gonçalves
 */
public class InformacaoParticipanteController implements Initializable {

    @FXML private Label nomeParticipante;
    @FXML private Label salaPrimeiraFase;
    @FXML private Label salaSegundaFase;
    @FXML private Label primeiroEspacoCafe;
    @FXML private Label segundoEspacoCafe;

    @FXML private AnchorPane layout;

    @FXML private Button botaoFechar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pessoa participanteSelecionado = PrincipalController.getPessoaSelecionada();
        nomeParticipante.setText(participanteSelecionado.getNome()+" "+participanteSelecionado.getSobrenome());
        salaPrimeiraFase.setText(participanteSelecionado.getEspacoPrimeiraEtapa());
        salaSegundaFase.setText(participanteSelecionado.getEspacoSegundaEtapa());
        primeiroEspacoCafe.setText(participanteSelecionado.getEspacoCafePrimeiraEtapa());
        segundoEspacoCafe.setText(participanteSelecionado.getEspacoCafeSegundaEtapa());
    }

    public void botaoFecharClicked(){
        Stage palcoAtual = (Stage) layout.getScene().getWindow();
        palcoAtual.close();
    }
}
