package layout;

import classes.DataHandler;
import classes.FileHandler;
import classes.Pessoa;
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

    @FXML private TableView tabela;
    @FXML private TableColumn colunaNome;
    @FXML private TableColumn colunaSobrenome;

    @FXML private TextField nomeSalaAdicionar;
    @FXML private Button nomeSalaAdicionarBotao;

    @FXML private Button botaoAbrirDetalhes;


    @Override
    public void initialize(URL location, ResourceBundle resources){

        janela.widthProperty().addListener((obs, oldVal, newVal) -> {
            tabela.setMaxWidth((Math.round((double) newVal)) - 180);
            tabela.setMinWidth((Math.round((double) newVal)) - 180);
        });

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaSobrenome.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));

        colunaNome.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaSobrenome.setCellFactory(TextFieldTableCell.forTableColumn());

        File infoPessoas = FileHandler.criarArquivo("dados\\InfoPessoas.data");
        ObservableList<Pessoa> pessoas = DataHandler.gerarPessoas(DataHandler.recuperarDadosSalvos(infoPessoas));
        tabela.setItems(pessoas);


    }


}
