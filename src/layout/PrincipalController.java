package layout;

import classes.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML private AnchorPane janela;

    @FXML private MenuBar barraDeMenu;
    @FXML private MenuItem salvarDadosMenuItem;
    @FXML private MenuItem sairSemSalvarMenuItem;
    @FXML private MenuItem salvarESairMenuItem;
    @FXML private MenuItem fazerBackupMenuItem;
    @FXML private MenuItem recuperarBackupMenuItem;
    @FXML private MenuItem abrirReadmeMenuItem;

    @FXML private Button mudarParaSalas;
    @FXML private Button mudarParaParticipantes;
    @FXML private Button mudarParaEspacosCafe;

    @FXML private AnchorPane anchorPaneTabelas;

    @FXML private TableView<Pessoa> tabelaParticipantes;
    @FXML private TableColumn<Pessoa, String> colunaNome;
    @FXML private TableColumn<Pessoa, String> colunaSobrenome;

    @FXML private TableView<Espaco> tabelaSalas;
    @FXML private TableColumn<Espaco, String> colunaIdentificacaoSala;
    @FXML private TableColumn<Espaco, Integer> colunaLotacaoSala;

    @FXML private TableView<Espaco> tabelaEspacosCafe;
    @FXML private TableColumn<Espaco, String> colunaIdentificacaoEspacosCafe;
    @FXML private TableColumn<Espaco, Integer> colunaLotacaoEspacosCafe;

    @FXML private AnchorPane adicionarParticipantePainel;
    @FXML private TextField adicionarParticipanteNome;
    @FXML private Button adicionarParticipanteBotao;
    @FXML private Label adicionarParticipanteLabel;

    @FXML private AnchorPane adicionarSalaPainel;
    @FXML private TextField adicionarSalaNome;
    @FXML private TextField adicionarSalaLotacao;
    @FXML private Button adicionarSalaBotao;
    @FXML private Label adicionarSalaLabel;

    @FXML private AnchorPane adicionarEspacoCafePainel;
    @FXML private TextField adicionarEspacoCafeNome;
    @FXML private TextField adicionarEspacoCafeLotacao;
    @FXML private Button adicionarEspacoCafeBotao;
    @FXML private Label adicionarEspacoCafeLabel;

    @FXML private Button botaoAbrirDetalhesParticipante;
    @FXML private Button botaoExcluirParticipante;

    @FXML private Button botaoAbrirDetalhesSala;
    @FXML private Button botaoExcluirSala;

    @FXML private Button botaoAbrirDetalhesEspacoCafe;
    @FXML private Button botaoExcluirEspacoCafe;

    private File infoEspacosCafe = FileHandler.criarArquivo("dados\\InfoEspacosCafe.data");
    private ObservableList<Espaco> espacosCafe = DataHandler.gerarEspacos(DataHandler.recuperarDadosSalvos(infoEspacosCafe));

    private File infoSalasTreinamento = FileHandler.criarArquivo("dados\\InfoSalasTreinamento.data");
    private ObservableList<Espaco> salasTreinamento = DataHandler.gerarEspacos(DataHandler.recuperarDadosSalvos(infoSalasTreinamento));

    private File infoPessoas = FileHandler.criarArquivo("dados\\InfoPessoas.data");
    private ObservableList<Pessoa> pessoas = DataHandler.gerarPessoas(DataHandler.recuperarDadosSalvos(infoPessoas));

    private StringConverter<Integer> conversor = new StringConverter<Integer>() {
        @Override
        public String toString(Integer object) {
            return object.toString();
        }

        @Override
        public Integer fromString(String string) {
            return Integer.parseInt(string);
        }
    };

    public static IntegerProperty salvarAoSair = new SimpleIntegerProperty();
    private static boolean modificacoesRealizadas;
    private static Region[] elementosOcultaveis;
    private static Pessoa pessoaSelecionada;
    private static Espaco espacoCafeSelecionado;
    private static Espaco espacoTreinamentoSelecionado;


    public static boolean isModificacoesRealizadas() {
        return modificacoesRealizadas;
    }

    public static void setModificacoesRealizadas(boolean modificacoesRealizadas) {
        PrincipalController.modificacoesRealizadas = modificacoesRealizadas;
    }

    public static Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public static void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        PrincipalController.pessoaSelecionada = pessoaSelecionada;
    }

    public static Espaco getEspacoCafeSelecionado() {
        return espacoCafeSelecionado;
    }

    public static void setEspacoCafeSelecionado(Espaco espacoCafe) {
        PrincipalController.espacoCafeSelecionado = espacoCafe;
    }

    public static Espaco getEspacoTreinamentoSelecionado() {
        return espacoTreinamentoSelecionado;
    }

    public static void setEspacoTreinamentoSelecionado(Espaco espacoTreinamentoSelecionado) {
        PrincipalController.espacoTreinamentoSelecionado = espacoTreinamentoSelecionado;
    }

    public void fazerBackupMenuClicked(){
        DataHandler.salvarDados(FileHandler.criarArquivo("backup\\InfoPessoas.bkp"), pessoas, new Pessoa());
        DataHandler.salvarDados(FileHandler.criarArquivo("backup\\InfoEspacosCafe.bkp"), espacosCafe, new Espaco());
        DataHandler.salvarDados(FileHandler.criarArquivo("backup\\InfoSalasTreinamento.bkp"), salasTreinamento, new Espaco());
        emitirAlertBox("Backup", "O backup foi realizado com sucesso");
    }

    public void recuperarBackupMenuClicked() {
        File infoEspacosCafeBackup = new File("backup\\InfoEspacosCafe.bkp");
        File infoPessoasBackup = new File("backup\\InfoPessoas.bkp");
        File infoSalasTreinamentoBackup = new File("backup\\InfoSalasTreinamento.bkp");
        if (!infoEspacosCafeBackup.exists()||!infoPessoasBackup.exists()|| !infoSalasTreinamentoBackup.exists()) {
            emitirAlertBox("Recuperar Backup", "Os arquivos de backup estão incompletos");
        } else {
            espacosCafe = DataHandler.gerarEspacos(DataHandler.recuperarDadosSalvos(infoEspacosCafeBackup));
            pessoas = DataHandler.gerarPessoas(DataHandler.recuperarDadosSalvos(infoPessoasBackup));
            salasTreinamento = DataHandler.gerarEspacos(DataHandler.recuperarDadosSalvos(infoSalasTreinamentoBackup));
            definirTabelas();
            emitirAlertBox("Recuperar Backup", "O backup foi recuperado");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){

        definirElementosOcultaveis();
        esmaecerBotoes();
        janelasReativas();
        definirTabelas();
        definirVisibilidadeInicial();
        criarOpcoesDeSaida();

    }

    private void ocultarElementos(Region[] elementosOcultaveis) {
        for (Region elemento: elementosOcultaveis) {
            elemento.setVisible(false);
        }
    }

    private void definirElementosOcultaveis() {
        elementosOcultaveis = new Region[12];
        elementosOcultaveis[0] = adicionarParticipantePainel;
        elementosOcultaveis[1] = tabelaParticipantes;
        elementosOcultaveis[2] = botaoExcluirParticipante;
        elementosOcultaveis[3] = botaoAbrirDetalhesParticipante;
        elementosOcultaveis[4] = tabelaSalas;
        elementosOcultaveis[5] = adicionarSalaPainel;
        elementosOcultaveis[6] = botaoAbrirDetalhesSala;
        elementosOcultaveis[7] = botaoExcluirSala;
        elementosOcultaveis[8] = tabelaEspacosCafe;
        elementosOcultaveis[9] = adicionarEspacoCafePainel;
        elementosOcultaveis[10] = botaoAbrirDetalhesEspacoCafe;
        elementosOcultaveis[11] = botaoExcluirEspacoCafe;
    }

    public void adicionarParticipanteBotaoClicked() {
        if (adicionarParticipanteNome.getText().length() < 5) {
            System.out.println("Nome muito curto");
        } else if (adicionarParticipanteNome.getText().contains("$")){
            System.out.println("O caractere $ não é válido");
        } else {
            pessoas.add(new Pessoa(Pessoa.separarNome(adicionarParticipanteNome.getText()),
                    Pessoa.separarSobrenome(adicionarParticipanteNome.getText()), "", "",
                    "", ""));
            adicionarParticipanteNome.setText("");
            setModificacoesRealizadas(true);
        }
    }

    public void adicionarSalaBotaoClicked() {
        try {
            if (Integer.parseInt(adicionarSalaLotacao.getText()) < 1){
                throw new NumberFormatException();
            } else if (adicionarSalaNome.getText().contains("$")){
                emitirAlertBox("Erro","O caractere $ não é válido");
            } else if (adicionarSalaNome.getText().length() < 1) {
                emitirAlertBox("Adicionar Nome da Sala", "Digite um valor no local indicado");
            } else {
                salasTreinamento.add(new Espaco(adicionarSalaNome.getText(), Integer.parseInt(adicionarSalaLotacao.getText())));
                adicionarSalaNome.setText("");
                adicionarSalaLotacao.setText("");
                setModificacoesRealizadas(true);
            }
        } catch (NumberFormatException e) {
            emitirAlertBox("Erro na lotação", "Verifique o valor da lotação");
        }
    }

    public void adicionarEspacoCafeBotaoClicked() {

        try {
            if (Integer.parseInt(adicionarEspacoCafeLotacao.getText()) < 1){
                throw new NumberFormatException();
            } else if (adicionarEspacoCafeNome.getText().contains("$")){
                emitirAlertBox("Erro","O caractere $ não é válido");
            } else if (adicionarEspacoCafeNome.getText().length() < 1) {
                emitirAlertBox("Adicionar Nome do Espaços", "Digite um valor no local indicado");
            } else {
                espacosCafe.add(new Espaco(adicionarEspacoCafeNome.getText(), Integer.parseInt(adicionarEspacoCafeLotacao.getText())));
                adicionarEspacoCafeNome.setText("");
                adicionarEspacoCafeLotacao.setText("");
                setModificacoesRealizadas(true);
            }
        } catch (NumberFormatException e) {
            emitirAlertBox("Erro na lotação", "Verifique o valor da lotação");
        }
    }

    public void botaoExcluirParticipanteClicked(){
        ObservableList<Pessoa> selecionado = tabelaParticipantes.getSelectionModel().getSelectedItems();
        tabelaParticipantes.getItems().removeAll(selecionado);
        pessoas.remove(selecionado);
        setModificacoesRealizadas(true);
    }

    public void botaoExcluirSalaClicked(){
        ObservableList<Espaco> selecionado = tabelaSalas.getSelectionModel().getSelectedItems();
        tabelaSalas.getItems().removeAll(selecionado);
        salasTreinamento.remove(selecionado);
        setModificacoesRealizadas(true);
    }

    public void botaoExcluirEspacoCafeClicked(){
        ObservableList<Espaco> selecionado = tabelaEspacosCafe.getSelectionModel().getSelectedItems();
        tabelaEspacosCafe.getItems().removeAll(selecionado);
        espacosCafe.remove(selecionado);
        setModificacoesRealizadas(true);
    }

    public void botaoAbrirDetalhesParticipanteClicked(){
        pessoaSelecionada = tabelaParticipantes.getSelectionModel().getSelectedItem();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("informacaoparticipante.fxml"));

            Scene cena = new Scene(root);
            Stage palco = new Stage();
            palco.setScene(cena);
            palco.setResizable(false);
            palco.initModality(Modality.APPLICATION_MODAL);
            palco.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void botaoAbrirDetalhesSalaClicked(){
        espacoTreinamentoSelecionado = tabelaSalas.getSelectionModel().getSelectedItem();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("informacaosala.fxml"));

            Scene cena = new Scene(root);
            Stage palco = new Stage();
            palco.setScene(cena);
            palco.setResizable(false);
            palco.initModality(Modality.APPLICATION_MODAL);
            palco.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mudarParaParticipantesClicked() {
        ocultarElementos(elementosOcultaveis);
        adicionarParticipantePainel.setVisible(true);
        tabelaParticipantes.setVisible(true);
        botaoExcluirParticipante.setVisible(true);
        botaoAbrirDetalhesParticipante.setVisible(true);
    }

    public void mudarParaSalasClicked() {
        ocultarElementos(elementosOcultaveis);

        tabelaSalas.setVisible(true);
        adicionarSalaPainel.setVisible(true);
        botaoAbrirDetalhesSala.setVisible(true);
        botaoExcluirSala.setVisible(true);
    }

    public void mudarParaEspacosCafeClicked() {

        ocultarElementos(elementosOcultaveis);

        tabelaEspacosCafe.setVisible(true);
        adicionarEspacoCafePainel.setVisible(true);
        botaoAbrirDetalhesEspacoCafe.setVisible(true);
        botaoExcluirEspacoCafe.setVisible(true);
    }

    public void definirVisibilidadeInicial(){
        ocultarElementos(elementosOcultaveis);

        adicionarParticipantePainel.setVisible(true);
        tabelaParticipantes.setVisible(true);
        botaoExcluirParticipante.setVisible(true);
        botaoAbrirDetalhesParticipante.setVisible(true);
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
            colunaSobrenome.setMaxWidth((Math.round((double) newVal)) - 250);
            colunaSobrenome.setMinWidth((Math.round((double) newVal)) - 250);
        });

        tabelaSalas.widthProperty().addListener((obs, oldVal, newVal) -> {
            colunaLotacaoSala.setMaxWidth((Math.round((double) newVal - 250)));
            colunaLotacaoSala.setMinWidth((Math.round((double) newVal - 250)));
        });

        tabelaEspacosCafe.widthProperty().addListener((obs, oldVal, newVal) -> {
            colunaLotacaoEspacosCafe.setMaxWidth((Math.round((double) newVal - 250)));
            colunaLotacaoEspacosCafe.setMinWidth((Math.round((double) newVal - 250)));
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

        colunaLotacaoSala.setCellValueFactory(new PropertyValueFactory<Espaco, Integer>("lotacao"));
        colunaLotacaoSala.setCellFactory(TextFieldTableCell.forTableColumn(conversor));

        tabelaSalas.setItems(salasTreinamento);

        colunaIdentificacaoEspacosCafe.setCellValueFactory(new PropertyValueFactory<>("nomeEspaco"));
        colunaIdentificacaoEspacosCafe.setCellFactory(TextFieldTableCell.forTableColumn());

        colunaLotacaoEspacosCafe.setCellValueFactory(new PropertyValueFactory<Espaco, Integer>("lotacao"));
        colunaLotacaoEspacosCafe.setCellFactory(TextFieldTableCell.forTableColumn(conversor));

        tabelaEspacosCafe.setItems(espacosCafe);
    }

    private void esmaecerBotoes(){

        tabelaParticipantes.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal != null) {
                botaoExcluirParticipante.setDisable(false);
                botaoAbrirDetalhesParticipante.setDisable(false);
            } else {
                botaoExcluirParticipante.setDisable(true);
                botaoAbrirDetalhesParticipante.setDisable(true);
            }
        });
        tabelaEspacosCafe.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal != null) {
                botaoExcluirEspacoCafe.setDisable(false);
                botaoAbrirDetalhesEspacoCafe.setDisable(false);
            } else {
                botaoExcluirEspacoCafe.setDisable(true);
                botaoAbrirDetalhesEspacoCafe.setDisable(true);
            }
        });
        tabelaSalas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal != null) {
                botaoExcluirSala.setDisable(false);
                botaoAbrirDetalhesSala.setDisable(false);
            } else {
                botaoExcluirSala.setDisable(true);
                botaoAbrirDetalhesSala.setDisable(true);
            }
        });
    }

    public void salvarESair(){
        salvarDados();
        fecharPrograma();
    }

    public void sairSemSalvar(){
        fecharPrograma();
    }

    public void fecharPrograma(){
        Stage palco = (Stage) janela.getScene().getWindow();
        palco.close();
    }

    public void salvarDados(){
        DataHandler.salvarDados(infoPessoas, pessoas, new Pessoa());
        DataHandler.salvarDados(infoSalasTreinamento, salasTreinamento, new Espaco());
        DataHandler.salvarDados(infoEspacosCafe, espacosCafe, new Espaco());
        emitirAlertBox("Salvamento", "Os dados foram salvos");
        setModificacoesRealizadas(false);
    }

    private void criarOpcoesDeSaida(){
        salvarAoSair.addListener((observable, oldVal, newVal) -> {
            if(newVal.intValue()==1){
                salvarDados();
                fecharPrograma();
            } else if (newVal.intValue() == -1) {
                fecharPrograma();
            }
        });
    }

    public void emitirAlertBox(String titulo, String mensagem) {
        Stage palcoAlertBox = new Stage();
        palcoAlertBox.setTitle(titulo);
        Label texto = new Label(mensagem);
        Button botaoOk = new Button("Ok");
        botaoOk.setOnAction( e -> {
            palcoAlertBox.close();
        });
        VBox layout = new VBox(20);
        layout.getChildren().setAll(texto, botaoOk);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefHeight(150);
        layout.setPrefWidth(300);
        Scene cena = new Scene(layout);
        palcoAlertBox.setResizable(false);
        palcoAlertBox.initModality(Modality.APPLICATION_MODAL);
        palcoAlertBox.setScene(cena);
        palcoAlertBox.sizeToScene();
        palcoAlertBox.show();
    }
}
