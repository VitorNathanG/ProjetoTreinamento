package layout;

import classes.Espaco;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InformacaoSalaController implements Initializable { //TODO
    @FXML private Label nomeSalaTreinamento;
    @FXML private Label lotacaoSalaTreinamento;
    @FXML private Label totalIntegrantesPrimeiraEtapa;
    @FXML private Label totalIntegrantesSegundaEtapa;

    @FXML private ListView listaParticipantesPrimeiraEtapa;
    @FXML private ListView listaParticipantesSegundaEtapa;
    @FXML private ListView listaParticipantesQueSaem;
    @FXML private ListView listaParticipantesQueVem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Espaco salaTreinamento = PrincipalController.getSalaTreinamentoSelecionada();
        nomeSalaTreinamento.setText(salaTreinamento.getNomeEspaco());
        lotacaoSalaTreinamento.setText(String.valueOf(salaTreinamento.getLotacao()));
        totalIntegrantesPrimeiraEtapa.setText(String.valueOf(salaTreinamento.getIntegrantesPrimeiraEtapa().size()));
        totalIntegrantesSegundaEtapa.setText(String.valueOf(salaTreinamento.getIntegrantesSegundaEtapa().size()));
        ArrayList<String> nomesParticipantesPrimeiraEtapa = new ArrayList<>();
        ArrayList<String> nomesParticipantesSegundaEtapa = new ArrayList<>();
        ArrayList<String> nomesParticipantesQueSaem = new ArrayList<>();
        ArrayList<String> nomesParticipantesQueVem = new ArrayList<>();

        for (int i = 0; i < salaTreinamento.getIntegrantesPrimeiraEtapa().size(); i++) {
            nomesParticipantesPrimeiraEtapa.add(salaTreinamento.getIntegrantesPrimeiraEtapa().get(i).getNome() + " " +
                                                  salaTreinamento.getIntegrantesPrimeiraEtapa().get(i).getSobrenome());
        }
        for (int i = 0; i < salaTreinamento.getIntegrantesSegundaEtapa().size(); i++) {
            nomesParticipantesSegundaEtapa.add(salaTreinamento.getIntegrantesSegundaEtapa().get(i).getNome() + " " +
                    salaTreinamento.getIntegrantesSegundaEtapa().get(i).getSobrenome());
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
