package layout;

import classes.Espaco;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InformacaoEspacoCafeController implements Initializable {

    @FXML private Label nomeEspacoCafe;
    @FXML private Label lotacaoEspacoCafe;
    @FXML private Label totalIntegrantesPrimeiraEtapa;
    @FXML private Label totalIntegrantesSegundaEtapa;

    @FXML private ListView listaParticipantesPrimeiraEtapa;
    @FXML private ListView listaParticipantesSegundaEtapa;
    @FXML private ListView listaParticipantesQueSaem;
    @FXML private ListView listaParticipantesQueVem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Espaco espacoCafe = PrincipalController.getEspacoCafeSelecionado();
        nomeEspacoCafe.setText(espacoCafe.getNomeEspaco());
        lotacaoEspacoCafe.setText(String.valueOf(espacoCafe.getLotacao()));
        totalIntegrantesPrimeiraEtapa.setText(String.valueOf(espacoCafe.getIntegrantesPrimeiraEtapa().size()));
        totalIntegrantesSegundaEtapa.setText(String.valueOf(espacoCafe.getIntegrantesSegundaEtapa().size()));

        ArrayList<String> nomesParticipantesPrimeiraEtapa = new ArrayList<>();
        ArrayList<String> nomesParticipantesSegundaEtapa = new ArrayList<>();
        ArrayList<String> nomesParticipantesQueSaem = new ArrayList<>();
        ArrayList<String> nomesParticipantesQueVem = new ArrayList<>();

        for (int i = 0; i < espacoCafe.getIntegrantesPrimeiraEtapa().size(); i++) {
            nomesParticipantesPrimeiraEtapa.add(espacoCafe.getIntegrantesPrimeiraEtapa().get(i).getNome() + " " +
                    espacoCafe.getIntegrantesPrimeiraEtapa().get(i).getSobrenome());
        }
        for (int i = 0; i < espacoCafe.getIntegrantesSegundaEtapa().size(); i++) {
            nomesParticipantesSegundaEtapa.add(espacoCafe.getIntegrantesSegundaEtapa().get(i).getNome() + " " +
                    espacoCafe.getIntegrantesSegundaEtapa().get(i).getSobrenome());
        }

        for (String participante: nomesParticipantesPrimeiraEtapa) {
            if(!nomesParticipantesSegundaEtapa.contains(participante)){
                nomesParticipantesQueSaem.add(participante);
            }
        }

        for (String participante: nomesParticipantesSegundaEtapa) {
            if(!nomesParticipantesPrimeiraEtapa.contains(participante)){
                nomesParticipantesQueVem.add(participante);
            }
        }

        listaParticipantesPrimeiraEtapa.getItems().addAll(nomesParticipantesPrimeiraEtapa);
        listaParticipantesSegundaEtapa.getItems().addAll(nomesParticipantesSegundaEtapa);
        listaParticipantesQueSaem.getItems().addAll(nomesParticipantesQueSaem);
        listaParticipantesQueVem.getItems().addAll(nomesParticipantesQueVem);


    }
}
