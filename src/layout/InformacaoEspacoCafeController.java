package layout;

import classes.Espaco;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class InformacaoEspacoCafeController implements Initializable {

    @FXML private Label nomeEspacoCafe;
    @FXML private Label lotacaoEspacoCafe;
    @FXML private Label totalIntegrantesPrimeiraEtapa;
    @FXML private Label totalIntegrantesSegundaEtapa;

    @FXML private ListView listaParticipantesPrimeiraEtapa;
    @FXML private ListView listaParticipantesSegundaEtapa;
    @FXML private ListView participantesQueSaem;
    @FXML private ListView participantesQueVem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Espaco espacoCafe = PrincipalController.getEspacoCafeSelecionado();
        nomeEspacoCafe.setText(espacoCafe.getNomeEspaco());
        lotacaoEspacoCafe.setText(String.valueOf(espacoCafe.getLotacao()));
        totalIntegrantesPrimeiraEtapa.setText(String.valueOf(espacoCafe.getIntegrantesPrimeiraEtapa().size()));
        totalIntegrantesSegundaEtapa.setText(String.valueOf(espacoCafe.getIntegrantesSegundaEtapa().size()));

    }
}
