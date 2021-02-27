package layout;

import classes.Espaco;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Classe InformacaoEspacoCafeController: responsável por controlar a janela de detalhes dos espaços de café do programa
 *
 * @author Vitor Nathan Gonçalves
 */
public class InformacaoEspacoCafeController implements Initializable {

    //Indicações dos elementos contidos no layout informacaoespacocafe.fxml
    @FXML private Label nomeEspacoCafe;
    @FXML private Label lotacaoEspacoCafe;
    @FXML private Label totalIntegrantesPrimeiraEtapa;
    @FXML private Label totalIntegrantesSegundaEtapa;

    @FXML private ListView<String> listaParticipantesPrimeiraEtapa;
    @FXML private ListView<String> listaParticipantesSegundaEtapa;
    @FXML private ListView<String> listaParticipantesQueSaem;
    @FXML private ListView<String> listaParticipantesQueVem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Define qual é o espaço que será detalhado
        Espaco espacoCafe = PrincipalController.getEspacoCafeSelecionado();

        //Define o texto contido nas labels
        nomeEspacoCafe.setText(espacoCafe.getNomeEspaco());
        lotacaoEspacoCafe.setText(String.valueOf(espacoCafe.getLotacao()));
        totalIntegrantesPrimeiraEtapa.setText(String.valueOf(espacoCafe.getIntegrantesPrimeiraEtapa().size()));
        totalIntegrantesSegundaEtapa.setText(String.valueOf(espacoCafe.getIntegrantesSegundaEtapa().size()));

        //Definição de elementos necessários para determinar quais participantes trocam de sala
        ArrayList<String> nomesParticipantesPrimeiraEtapa = new ArrayList<>();
        ArrayList<String> nomesParticipantesSegundaEtapa = new ArrayList<>();
        ArrayList<String> nomesParticipantesQueSaem = new ArrayList<>();
        ArrayList<String> nomesParticipantesQueVem = new ArrayList<>();

        //Popula o array com os nomes dos participantes da primeira etapa
        for (int i = 0; i < espacoCafe.getIntegrantesPrimeiraEtapa().size(); i++) {
            nomesParticipantesPrimeiraEtapa.add(espacoCafe.getIntegrantesPrimeiraEtapa().get(i).getNome() + " " +
                    espacoCafe.getIntegrantesPrimeiraEtapa().get(i).getSobrenome());
        }

        //Popula o array com os nomes dos participantes da segunda etapa
        for (int i = 0; i < espacoCafe.getIntegrantesSegundaEtapa().size(); i++) {
            nomesParticipantesSegundaEtapa.add(espacoCafe.getIntegrantesSegundaEtapa().get(i).getNome() + " " +
                    espacoCafe.getIntegrantesSegundaEtapa().get(i).getSobrenome());
        }

        /*
         * Verifica quais participantes estão na primeira etapa e não estão na segunda (saem) e os adiciona ao
         * respectivo array
         */
        for (String participante: nomesParticipantesPrimeiraEtapa) {
            if(!nomesParticipantesSegundaEtapa.contains(participante)){
                nomesParticipantesQueSaem.add(participante);
            }
        }

        /*
         * Verifica quais participantes estão na segunda etapa e não estão na primeira (vem) e os adiciona ao
         * respectivo array
         */
        for (String participante: nomesParticipantesSegundaEtapa) {
            if(!nomesParticipantesPrimeiraEtapa.contains(participante)){
                nomesParticipantesQueVem.add(participante);
            }
        }

        //Popula as listas da janela
        listaParticipantesPrimeiraEtapa.getItems().addAll(nomesParticipantesPrimeiraEtapa);
        listaParticipantesSegundaEtapa.getItems().addAll(nomesParticipantesSegundaEtapa);
        listaParticipantesQueSaem.getItems().addAll(nomesParticipantesQueSaem);
        listaParticipantesQueVem.getItems().addAll(nomesParticipantesQueVem);


    }
}
