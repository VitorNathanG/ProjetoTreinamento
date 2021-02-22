package layout;

import classes.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML private AnchorPane janela;

    @FXML private MenuItem abrirArquivoSalas;
    @FXML private MenuItem abrirArquivoParticipantes;
    @FXML private MenuItem abrirArquivoEspacosCafe;
    @FXML private MenuItem salvarDados;
    @FXML private MenuItem fazerBackup;
    @FXML private MenuItem recuperarBackup;
    @FXML private MenuItem abrirReadme;

    @FXML private Button mudarParaSalas;
    @FXML private Button mudarParaParticipantes;
    @FXML private Button mudarParaEspacosCafe;

    @FXML private TableView tabelaParticipantes;
    @FXML private TableColumn colunaNome;
    @FXML private TableColumn colunaSobrenome;

    @FXML private TextField nomeParticipanteAdicionar;
    @FXML private Button nomeParticipanteAdicionarBotao;

    @FXML private Button botaoAbrirDetalhes;
    @FXML private Button botaoExcluir;

    File infoEspacosCafe = FileHandler.criarArquivo("dados\\InfoEspacosCafe.data");
    ObservableList<EspacoCafe> espacosCafe = DataHandler.gerarEspacosCafe(DataHandler.recuperarDadosSalvos(infoEspacosCafe));

    File infoSalasTreinamento = FileHandler.criarArquivo("dados\\InfoSalasTreinamento.data");
    ObservableList<EspacoTreinamento> salasTreinamento = DataHandler.gerarSalasTreinamento(DataHandler.recuperarDadosSalvos(infoSalasTreinamento));

    File infoPessoas = FileHandler.criarArquivo("dados\\InfoPessoas.data");
    ObservableList<Pessoa> pessoas = DataHandler.gerarPessoas(DataHandler.recuperarDadosSalvos(infoPessoas));


    @Override
    public void initialize(URL location, ResourceBundle resources){

        janela.widthProperty().addListener((obs, oldVal, newVal) -> {
            tabelaParticipantes.setMaxWidth((Math.round((double) newVal)) - 180);
            tabelaParticipantes.setMinWidth((Math.round((double) newVal)) - 180);
        });
        tabelaParticipantes.widthProperty().addListener((obs, oldVal, newVal) -> {
            colunaSobrenome.setMaxWidth((Math.round((double) newVal)) - 200);
            colunaSobrenome.setMinWidth((Math.round((double) newVal)) - 200);
        });

        tabelaParticipantes.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal != null) {
                botaoExcluir.setDisable(false);
                botaoAbrirDetalhes.setDisable(false);
            } else {
                botaoExcluir.setDisable(true);
                botaoAbrirDetalhes.setDisable(true);
            }
        });

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaSobrenome.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));

        colunaNome.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaSobrenome.setCellFactory(TextFieldTableCell.forTableColumn());


        tabelaParticipantes.setItems(pessoas);

        DataHandler.salvarDados(infoPessoas, pessoas, new Pessoa());
        DataHandler.salvarDados(infoEspacosCafe, espacosCafe, new EspacoCafe());
        DataHandler.salvarDados(infoSalasTreinamento, salasTreinamento, new EspacoTreinamento());
        System.out.println("Arquivos Salvos");
    }

    public void nomeParticipanteAdicionarBotaoClicked() {
        pessoas.add(new Pessoa(Pessoa.separarNome(nomeParticipanteAdicionar.getText()),
                Pessoa.separarSobrenome(nomeParticipanteAdicionar.getText()), "", "",
                "", ""));
        nomeParticipanteAdicionar.setText("");
        DataHandler.salvarDados(infoPessoas, pessoas, new Pessoa());
    }

    public void mudarParaParticipantesClicked() {
        tabelaParticipantes.setVisible(true);
    }
}
