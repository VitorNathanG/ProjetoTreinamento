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

    @FXML private MenuBar barraDeMenu;
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

    @FXML private AnchorPane anchorPaneTabelas;

    @FXML private TableView tabelaParticipantes;
    @FXML private TableColumn colunaNome;
    @FXML private TableColumn colunaSobrenome;

    @FXML private TableView tabelaSalas;
    @FXML private TableColumn colunaIdentificacaoSala;

    @FXML private AnchorPane adicionarParticipantePainel;
    @FXML private TextField adicionarParticipanteNome;
    @FXML private Button adicionarParticipanteBotao;
    @FXML private Label adicionarParticipanteLabel;

    @FXML private AnchorPane adicionarSalaPainel;
    @FXML private TextField adicionarSalaNome;
    @FXML private Button adicionarSalaBotao;
    @FXML private Label adicionarSalaLabel;

    @FXML private Button botaoAbrirDetalhesParticipante;
    @FXML private Button botaoExcluirParticipante;

    @FXML private Button botaoAbrirDetalhesSala;
    @FXML private Button botaoExcluirSala;

    File infoEspacosCafe = FileHandler.criarArquivo("dados\\InfoEspacosCafe.data");
    ObservableList<EspacoCafe> espacosCafe = DataHandler.gerarEspacosCafe(DataHandler.recuperarDadosSalvos(infoEspacosCafe));

    File infoSalasTreinamento = FileHandler.criarArquivo("dados\\InfoSalasTreinamento.data");
    ObservableList<EspacoTreinamento> salasTreinamento = DataHandler.gerarSalasTreinamento(DataHandler.recuperarDadosSalvos(infoSalasTreinamento));

    File infoPessoas = FileHandler.criarArquivo("dados\\InfoPessoas.data");
    ObservableList<Pessoa> pessoas = DataHandler.gerarPessoas(DataHandler.recuperarDadosSalvos(infoPessoas));


    @Override
    public void initialize(URL location, ResourceBundle resources){



        tabelaParticipantes.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal != null) {
                botaoExcluirParticipante.setDisable(false);
                botaoAbrirDetalhesParticipante.setDisable(false);
            } else {
                botaoExcluirParticipante.setDisable(true);
                botaoAbrirDetalhesParticipante.setDisable(true);
            }
        });
        janelasReativas();
        definirTabelas();
        visibilidadeInicial();
    }

    public void adicionarParticipanteBotaoClicked() {
        pessoas.add(new Pessoa(Pessoa.separarNome(adicionarParticipanteNome.getText()),
                Pessoa.separarSobrenome(adicionarParticipanteNome.getText()), "", "",
                "", ""));
        adicionarParticipanteNome.setText("");
        DataHandler.salvarDados(infoPessoas, pessoas, new Pessoa());
    }

    public void adicionarSalaBotaoClicked() {
        salasTreinamento.add(new EspacoTreinamento(adicionarSalaNome.getText(), EspacoTreinamento.getLotacao()));
        adicionarSalaNome.setText("");
        DataHandler.salvarDados(infoSalasTreinamento, salasTreinamento, new EspacoTreinamento());
    }

    public void mudarParaParticipantesClicked() {
        adicionarParticipantePainel.setVisible(true);
        tabelaParticipantes.setVisible(true);
        botaoExcluirParticipante.setVisible(true);
        botaoAbrirDetalhesParticipante.setVisible(true);

        tabelaSalas.setVisible(false);
        adicionarSalaPainel.setVisible(false);
        botaoAbrirDetalhesSala.setVisible(false);
        botaoExcluirSala.setVisible(false);
    }

    public void mudarParaSalasClicked() {
        tabelaParticipantes.setVisible(false);
        adicionarParticipantePainel.setVisible(false);
        botaoExcluirParticipante.setVisible(false);
        botaoAbrirDetalhesParticipante.setVisible(false);

        tabelaSalas.setVisible(true);
        adicionarSalaPainel.setVisible(true);
        botaoAbrirDetalhesSala.setVisible(true);
        botaoExcluirSala.setVisible(true);
    }

    public void mudarParaEspacosCafeClicked() {
        tabelaParticipantes.setVisible(false);
        adicionarParticipantePainel.setVisible(false);
        botaoExcluirParticipante.setVisible(false);
        botaoAbrirDetalhesParticipante.setVisible(false);

        tabelaSalas.setVisible(false);
        adicionarSalaPainel.setVisible(false);
        botaoAbrirDetalhesSala.setVisible(false);
        botaoExcluirSala.setVisible(false);
    }

    public void visibilidadeInicial(){
        adicionarParticipantePainel.setVisible(true);
        tabelaParticipantes.setVisible(true);
        botaoExcluirParticipante.setVisible(true);
        botaoAbrirDetalhesParticipante.setVisible(true);

        adicionarSalaPainel.setVisible(false);
        tabelaSalas.setVisible(false);
        botaoExcluirSala.setVisible(false);
        botaoAbrirDetalhesSala.setVisible(false);
    }

    public void janelasReativas(){
        janela.widthProperty().addListener((obs, oldVal, newVal) -> {
            tabelaParticipantes.setMaxWidth((Math.round((double) newVal)) - 180);
            tabelaParticipantes.setMinWidth((Math.round((double) newVal)) - 180);

            tabelaSalas.setMaxWidth((Math.round((double) newVal)) - 180);
            tabelaSalas.setMinWidth((Math.round((double) newVal)) - 180);

            tabelaParticipantes.setMaxWidth((Math.round((double) newVal)) - 180);
            tabelaParticipantes.setMinWidth((Math.round((double) newVal)) - 180);
        });

        tabelaParticipantes.widthProperty().addListener((obs, oldVal, newVal) -> {
            colunaSobrenome.setMaxWidth((Math.round((double) newVal)) - 200);
            colunaSobrenome.setMinWidth((Math.round((double) newVal)) - 200);
        });

        tabelaSalas.widthProperty().addListener((obs, oldVal, newVal) -> {
            colunaIdentificacaoSala.setMaxWidth((Math.round((double) newVal)));
            colunaIdentificacaoSala.setMinWidth((Math.round((double) newVal)));
        });

        janela.heightProperty().addListener((obs, oldVal, newVal) -> {
            anchorPaneTabelas.setMaxHeight((Math.round((double) newVal)) - 80);
            anchorPaneTabelas.setMinHeight((Math.round((double) newVal)) - 80);
        });
    }

    public void definirTabelas(){
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaSobrenome.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));

        colunaNome.setCellFactory(TextFieldTableCell.forTableColumn());
        colunaSobrenome.setCellFactory(TextFieldTableCell.forTableColumn());

        tabelaParticipantes.setItems(pessoas);

        colunaIdentificacaoSala.setCellValueFactory(new PropertyValueFactory<>("nomeEspaco"));
        colunaIdentificacaoSala.setCellFactory(TextFieldTableCell.forTableColumn());

        tabelaSalas.setItems(salasTreinamento);
    }

}
