package layout;

import classes.DataHandler;
import classes.Espaco;
import classes.FileHandler;
import classes.Pessoa;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    //Indicações dos elementos contidos no layout FXML
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
    @FXML private TableColumn<Espaco, Integer> colunaOcupacaoPrimeiraFaseSalas;
    @FXML private TableColumn<Espaco, Integer> colunaOcupacaoSegundaFaseSalas;

    @FXML private TableView<Espaco> tabelaEspacosCafe;
    @FXML private TableColumn<Espaco, String> colunaIdentificacaoEspacosCafe;
    @FXML private TableColumn<Espaco, Integer> colunaLotacaoEspacosCafe;
    @FXML private TableColumn<Espaco, Integer> colunaOcupacaoPrimeiraFaseEspacoCafe;
    @FXML private TableColumn<Espaco, Integer> colunaOcupacaoSegundaFaseEspacoCafe;

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

    //Recuperação dos dados salvos e atribuição para as listas manipuláveis
    private File infoEspacosCafe = FileHandler.criarArquivo("dados\\InfoEspacosCafe.data");
    private ObservableList<Espaco> espacosCafe = DataHandler.gerarEspacos(DataHandler.recuperarDadosSalvos(infoEspacosCafe));

    private File infoSalasTreinamento = FileHandler.criarArquivo("dados\\InfoSalasTreinamento.data");
    private ObservableList<Espaco> salasTreinamento = DataHandler.gerarEspacos(DataHandler.recuperarDadosSalvos(infoSalasTreinamento));

    private File infoPessoas = FileHandler.criarArquivo("dados\\InfoPessoas.data");
    private ObservableList<Pessoa> pessoas = DataHandler.gerarPessoas(DataHandler.recuperarDadosSalvos(infoPessoas));

    //StringConverter necessário para popular as tabelas
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

    /**
     * Possui 3 estados:
     * 1 - Salva ao sair
     * 0 - Não definido ainda
     *-1 - Não salva ao sair
     * É definido ao responder sim/não no pop-up de fechamento do programa.
     */
    public static IntegerProperty salvarAoSair = new SimpleIntegerProperty();

    /**
     * Recebe verdadeiro se ocorre alguma modificação em alguma tabela. É utilizado ao fechar o programa, caso
     * verdadeiro, abre o pop-up questionando o salvamento ou não das modificações realizadas.
     */
    private static boolean modificacoesRealizadas;

    /**
     * Encapsula todos os elementos do layout que têm suas visibilidades alteradas durante o uso.
     */
    private static Region[] elementosOcultaveis;

    /**
     * Recebe a pessoa que está na linha selecionada da tabela de participantes
     */
    private static Pessoa pessoaSelecionada;

    /**
     * Recebe o espaço que está na linha selecionada da tabela de espaços de café
     */
    private static Espaco espacoCafeSelecionado;

    /**
     * Recebe o espaço que está na linha selecionada da tabela de espaços de café
     */
    private static Espaco salaTreinamentoSelecionada;

    /**
     * Recebe a capacidade total disponível nas salas de treinamento
     */
    public static int capacidadeSalas;

    /**
     * Recebe a capacidade total disponível nos espaços de café
     */
    public static int capacidadeEspacosCafe;

    /**
     * Atualiza a capacidade total disponível nas salas de treinamento
     * @return int - valor atualizado da capacidade das salas
     */
    public int atualizarCapacidadeSalas() {
        capacidadeSalas = 0;
        for (Espaco salaTreinamento: salasTreinamento) {
            capacidadeSalas += salaTreinamento.getLotacao();
        }
        return PrincipalController.capacidadeSalas;
    }

    /**
     * Atualiza a capacidade total disponível nos espaços de café
     * @return int - valor atualizado da capacidade dos espaços de café
     */
    public int atualizarCapacidadeEspacosCafe() {
        capacidadeEspacosCafe = 0;
        for (Espaco salaTreinamento: espacosCafe) {
            capacidadeEspacosCafe += salaTreinamento.getLotacao();
        }
        return PrincipalController.capacidadeEspacosCafe;
    }

    /**
     * Getter para o atributo modificações realizadas
     * @return modificacoesRealizadas - verdadeiro caso alguma modificação tenha sido realizada
     */
    public static boolean isModificacoesRealizadas() {
        return modificacoesRealizadas;
    }

    /**
     * Setter para o atributo modificações realizadas
     * @param modificacoesRealizadas - verdadeiro caso alguma modificação tenha sido realizada
     */
    public static void setModificacoesRealizadas(boolean modificacoesRealizadas) {
        PrincipalController.modificacoesRealizadas = modificacoesRealizadas;
    }

    /**
     * Getter para pessoaSelecionada
     * @return Pessoa pessoaSelecionada
     */
    public static Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    /**
     * Setter para pessoaSelecionada
     * @param pessoaSelecionada - a pessoa contida no atributo
     */
    public static void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        PrincipalController.pessoaSelecionada = pessoaSelecionada;
    }

    /**
     * Getter para espacoCafeSelecionado
     * @return Espaco espacoCafeSelecionado
     */
    public static Espaco getEspacoCafeSelecionado() {
        return espacoCafeSelecionado;
    }

    /**
     * Setter para espacoCafe selecionado
     * @param espacoCafe - o espaco contido no atributo
     */
    public static void setEspacoCafeSelecionado(Espaco espacoCafe) {
        PrincipalController.espacoCafeSelecionado = espacoCafe;
    }

    /**
     * Getter para salaTreinamento selecionado
     * @return Espaco salaTreinamentoSelecionada
     */
    public static Espaco getSalaTreinamentoSelecionada() {
        return salaTreinamentoSelecionada;
    }

    /**
     * Setter para salaTreinamento selecionada
     * @param salaTreinamentoSelecionada - o espaco contido no atributo
     */
    public static void setSalaTreinamentoSelecionada(Espaco salaTreinamentoSelecionada) {
        PrincipalController.salaTreinamentoSelecionada = salaTreinamentoSelecionada;
    }

    /**
     * Acionado ao selecionar a opção de fazer backup na barra de menu. Cria uma cópia das informações contidas no momento
     * nas tabelas, que é salva na pasta backup, criada no mesmo diretório que o .jar do programa está localizado
     */
    public void fazerBackupMenuClicked(){
        DataHandler.salvarDados(FileHandler.criarArquivo("backup\\InfoPessoas.bkp"), pessoas, new Pessoa());
        DataHandler.salvarDados(FileHandler.criarArquivo("backup\\InfoEspacosCafe.bkp"), espacosCafe, new Espaco());
        DataHandler.salvarDados(FileHandler.criarArquivo("backup\\InfoSalasTreinamento.bkp"), salasTreinamento, new Espaco());
        emitirAlertBox("Backup", "O backup foi realizado com sucesso");
    }

    /**
     * Recupera os arquivos de backup contidos na pasta backup, descartando os elementos atuais das tabelas
     */
    public void recuperarBackupMenuClicked() {
        File infoEspacosCafeBackup = new File("backup\\InfoEspacosCafe.bkp");
        File infoPessoasBackup = new File("backup\\InfoPessoas.bkp");
        File infoSalasTreinamentoBackup = new File("backup\\InfoSalasTreinamento.bkp");
        if (!infoEspacosCafeBackup.exists() || !infoPessoasBackup.exists() || !infoSalasTreinamentoBackup.exists()) {
            emitirAlertBox("Recuperar Backup", "Os arquivos de backup estão incompletos");
        } else {
            espacosCafe = DataHandler.gerarEspacos(DataHandler.recuperarDadosSalvos(infoEspacosCafeBackup));
            pessoas = DataHandler.gerarPessoas(DataHandler.recuperarDadosSalvos(infoPessoasBackup));
            salasTreinamento = DataHandler.gerarEspacos(DataHandler.recuperarDadosSalvos(infoSalasTreinamentoBackup));
            definirTabelas();
            emitirAlertBox("Recuperar Backup", "O backup foi recuperado");
        }
    }

    /**
     * Método que é executado ao criar o Stage. Chama métodos de configuração inicial de exibição.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){

        atualizarCapacidadeEspacosCafe();
        atualizarCapacidadeSalas();
        definirElementosOcultaveis();
        esmaecerBotoes();
        janelasReativas();
        popularSalas();
        definirTabelas();
        definirVisibilidadeInicial();
        criarOpcoesDeSaida();

    }

    /**
     * Método chamado para atribuir os participantes às salas de treinamento e espaços de café.
     * Somente é chamado ao iniciar o programa, devido ao método de gerenciamento de arquivo utilizado no programa
     */
    private void popularSalas() {
        for (Pessoa pessoa: pessoas) {
            for (Espaco salaPrimeiraEtapa: salasTreinamento) {
                if(pessoa.getEspacoPrimeiraEtapa().equals(salaPrimeiraEtapa.getNomeEspaco())){
                    salaPrimeiraEtapa.adicionarIntegrantesPrimeiraEtapa(pessoa);
                    break;
                }
            }
            for (Espaco salaSegundaEtapa: salasTreinamento) {
                if(pessoa.getEspacoSegundaEtapa().equals(salaSegundaEtapa.getNomeEspaco())){
                    salaSegundaEtapa.adicionarIntegrantesSegundaEtapa(pessoa);
                    break;
                }
            }
            for (Espaco espacoCafePrimeiraEtapa: espacosCafe) {
                if(pessoa.getEspacoCafePrimeiraEtapa().equals(espacoCafePrimeiraEtapa.getNomeEspaco())){
                    espacoCafePrimeiraEtapa.adicionarIntegrantesPrimeiraEtapa(pessoa);
                    break;
                }
            }
            for (Espaco espacoCafeSegundaEtapa: espacosCafe) {
                if(pessoa.getEspacoCafeSegundaEtapa().equals(espacoCafeSegundaEtapa.getNomeEspaco())){
                    espacoCafeSegundaEtapa.adicionarIntegrantesSegundaEtapa(pessoa);
                    break;
                }
            }
        }
    }

    /**
     * Oculta todos os elementos contidos em um vetor de Region. Utilizado sempre que ocorre uma troca de visibilidade
     * das tabelas
     * @param elementosOcultaveis vetor com os elementos a serem ocultados
     */
    private void ocultarElementos(Region[] elementosOcultaveis) {
        for (Region elemento: elementosOcultaveis) {
            elemento.setVisible(false);
        }
    }

    /**
     * Define todos os elementos ocultáveis da cena, adicionando-os ao vetor elementosOcultaveis
     */
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

    /**
     * Chamado ao clicar no botão de adição de participante. Checa se é possível adicionar o participante ao evento,
     * conferindo o nome e a lotação das salas.
     */
    public void adicionarParticipanteBotaoClicked() {
        atualizarCapacidadeSalas();
        atualizarCapacidadeEspacosCafe();
        if (adicionarParticipanteNome.getText().length() < 5) {
            emitirAlertBox("Nome", "O nome inserido é muito curto");
        } else if (adicionarParticipanteNome.getText().contains("$")){
            emitirAlertBox("Caractere Inválido", "O caractere $ não é valido");
        } else if (pessoas.size() + 1  > capacidadeEspacosCafe || pessoas.size() + 1 > capacidadeSalas){
            emitirAlertBox("Baixa capacidade", "O evento não comporta mais participantes." + FileHandler.ENTER +
                    "Aumente a lotação de salas e espaços de café");
        } else {

            String pessoaNome = Pessoa.separarNome(adicionarParticipanteNome.getText());
            String pessoaSobrenome = Pessoa.separarSobrenome(adicionarParticipanteNome.getText());
            Pessoa novaPessoa = new Pessoa(pessoaNome, pessoaSobrenome);

            pessoas.add(novaPessoa);
            adicionarParticipanteNome.setText("");
            setModificacoesRealizadas(true);
            redistribuirSalasTreinamento();
            redistribuirEspacosCafe();
            definirTabelas();
        }
    }

    /**
     * Chamado ao clicar no botão de adicionar uma sala. Checa se os valores contidos nos textFields são válidos.
     */
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
                setModificacoesRealizadas(true);
                atualizarCapacidadeSalas();
                redistribuirSalasTreinamento();
                definirTabelas();
            }
        } catch (NumberFormatException e) {
            emitirAlertBox("Erro na lotação", "Verifique o valor da lotação");
        }
    }

    /**
     * Chamado ao clicar no botão de adicionar um espaço de café. Checa se os valores contidos nos textFields são válidos.
     * Chama o método para redistribuir os participantes nos espaços de café.
     */
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
                setModificacoesRealizadas(true);
                atualizarCapacidadeEspacosCafe();
                redistribuirEspacosCafe(); //Redistribui os participantes
                definirTabelas();
            }
        } catch (NumberFormatException e) {
            emitirAlertBox("Erro na lotação", "Verifique o valor da lotação");
        }
    }

    /**
     * Chamado ao apertar o botão excluir participante. Exclui o participante e o retira das salas de treinamento e dos
     * espaços de café que ele estiver. Depois disso, chama os métodos de reorganização dos espaços.
     */
    public void botaoExcluirParticipanteClicked(){
        Pessoa pessoa = tabelaParticipantes.getSelectionModel().getSelectedItem();

        for (Espaco salaPrimeiraEtapa: salasTreinamento) {
            if(pessoa.getEspacoPrimeiraEtapa().equals(salaPrimeiraEtapa.getNomeEspaco())){
                salaPrimeiraEtapa.removerIntegrantesPrimeiraEtapa(pessoa);
                break;
            }
        }
        for (Espaco salaSegundaEtapa: salasTreinamento) {
            if(pessoa.getEspacoSegundaEtapa().equals(salaSegundaEtapa.getNomeEspaco())){
                salaSegundaEtapa.removerIntegrantesSegundaEtapa(pessoa);
                break;
            }
        }
        for (Espaco espacoCafePrimeiraEtapa: espacosCafe) {
            if(pessoa.getEspacoCafePrimeiraEtapa().equals(espacoCafePrimeiraEtapa.getNomeEspaco())){
                espacoCafePrimeiraEtapa.removerIntegrantesPrimeiraEtapa(pessoa);
                break;
            }
        }
        for (Espaco espacoCafeSegundaEtapa: espacosCafe) {
            if(pessoa.getEspacoCafeSegundaEtapa().equals(espacoCafeSegundaEtapa.getNomeEspaco())){
                espacoCafeSegundaEtapa.removerIntegrantesSegundaEtapa(pessoa);
                break;
            }
        }

        /*
         * As 8 linhas abaixo fazem exatamente o mesmo que pessoas.remove(pessoa), mas por algum
         * motivo, esse método quebra o ObservableList pessoas completamente
         */
        ObservableList<Pessoa> novasPessoas = FXCollections.observableArrayList();
        for (Pessoa pessoaSubstituta: pessoas) {
            if(!pessoaSubstituta.equals(pessoa)){
                novasPessoas.add(pessoaSubstituta);
            }
        }
        pessoas = FXCollections.observableArrayList();
        pessoas.addAll(novasPessoas);

        //Redistribui os participantes e redefine as tabelas
        setModificacoesRealizadas(true);
        redistribuirSalasTreinamento();
        redistribuirEspacosCafe();
        definirTabelas();
    }

    /**
     * Chamado ao apertar o botão excluir sala. Verifica se as outras salas comportam os participantes, para evitar que
     * falte espaço para as pessoas.
     */
    public void botaoExcluirSalaClicked(){
        Espaco selecionado = tabelaSalas.getSelectionModel().getSelectedItem();

        if (pessoas.size() != 0 && capacidadeSalas - selecionado.getLotacao() < pessoas.size()) {
            emitirAlertBox("Lotação insuficiente", "Não é possível excluir, capacidade do evento insuficiente");
        } else {
            tabelaSalas.getItems().removeAll(selecionado);
            salasTreinamento.remove(selecionado);
            redistribuirSalasTreinamento();
            definirTabelas();
            atualizarCapacidadeSalas();
            setModificacoesRealizadas(true);
        }
        definirTabelas();
    }

    /**
     * Chamado ao apertar o botão excluir espaço de café. Verifica se os outros espaços comportam os participantes, para
     * evitar que falte espaço para as pessoas.
     */
    public void botaoExcluirEspacoCafeClicked(){
        Espaco selecionado = tabelaEspacosCafe.getSelectionModel().getSelectedItem();
        if(capacidadeEspacosCafe - selecionado.getLotacao() < pessoas.size()) {
            emitirAlertBox("Lotação insuficiente", "Não é possível excluir, capacidade do evento insuficiente");
        } else {
            tabelaEspacosCafe.getItems().removeAll(selecionado);
            espacosCafe.remove(selecionado);
            redistribuirEspacosCafe();
            definirTabelas();
            atualizarCapacidadeEspacosCafe();
            setModificacoesRealizadas(true);
        }
        definirTabelas();
    }

    /**
     * Chamado ao apertar o botão detalhes do participante. Abre um pop-up com as informações do participante selecionado
     * na tabela.
     */
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

    /**
     * Chamado ao apertar o botão detalhes da sala. Abre um pop-up com as informações da sala selecionada na tabela.
     */
    public void botaoAbrirDetalhesSalaClicked(){
        salaTreinamentoSelecionada = tabelaSalas.getSelectionModel().getSelectedItem();
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

    /**
     * Chamado ao apertar o botão de detalhes dos espaços de café. Abre um pop-up com as informações do espaço selecionado
     * na tabela.
     */
    public void botaoAbrirDetalhesEspacoCafeClicked(){
        espacoCafeSelecionado = tabelaEspacosCafe.getSelectionModel().getSelectedItem();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("informacaoespacocafe.fxml"));

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

    /**
     * Chamado ao apertar o botão Participantes. Oculta a tabela atual e os elementos de adição e torna a tabela de
     * participantes visível.
     */
    public void mudarParaParticipantesClicked() {
        ocultarElementos(elementosOcultaveis);
        adicionarParticipantePainel.setVisible(true);
        tabelaParticipantes.setVisible(true);
        botaoExcluirParticipante.setVisible(true);
        botaoAbrirDetalhesParticipante.setVisible(true);
    }

    /**
     * Chamado ao apertar o botão Salas. Oculta a tabela atual e os elementos de adição e torna a tabela de
     * salas de treinamento visível.
     */
    public void mudarParaSalasClicked() {
        ocultarElementos(elementosOcultaveis);

        tabelaSalas.setVisible(true);
        adicionarSalaPainel.setVisible(true);
        botaoAbrirDetalhesSala.setVisible(true);
        botaoExcluirSala.setVisible(true);
    }

    /**
     * Chamado ao apertar o botão Espaços de Café. Oculta a tabela atual e os elementos de adição e torna a tabela de
     * espaços de café visível.
     */
    public void mudarParaEspacosCafeClicked() {

        ocultarElementos(elementosOcultaveis);

        tabelaEspacosCafe.setVisible(true);
        adicionarEspacoCafePainel.setVisible(true);
        botaoAbrirDetalhesEspacoCafe.setVisible(true);
        botaoExcluirEspacoCafe.setVisible(true);
    }

    /**
     * Define a tabela que é mostrada ao se iniciar o programa. Por padrão, abre a tabela de participantes
     */
    public void definirVisibilidadeInicial(){
        ocultarElementos(elementosOcultaveis); //Oculta os elementos deixados visíveis durante a elaboração do layout

        adicionarParticipantePainel.setVisible(true);
        tabelaParticipantes.setVisible(true);
        botaoExcluirParticipante.setVisible(true);
        botaoAbrirDetalhesParticipante.setVisible(true);
    }

    /**
     * Define algumas properties das janelas e tabelas, para que tenham o tamanho correto de exibição independentemente
     * do redimensionamento da janela
     */
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
            colunaIdentificacaoSala.setMaxWidth((Math.round((double) newVal - 410)));
            colunaIdentificacaoSala.setMinWidth((Math.round((double) newVal - 410)));
        });

        tabelaEspacosCafe.widthProperty().addListener((obs, oldVal, newVal) -> {
            colunaIdentificacaoEspacosCafe.setMaxWidth((Math.round((double) newVal - 410)));
            colunaIdentificacaoEspacosCafe.setMinWidth((Math.round((double) newVal - 410)));
        });

        janela.heightProperty().addListener((obs, oldVal, newVal) -> {
            anchorPaneTabelas.setMaxHeight((Math.round((double) newVal)) - 80);
            anchorPaneTabelas.setMinHeight((Math.round((double) newVal)) - 80);
        });
    }

    /**
     * Define os dados que serão exibidos em todas as colunas de tabelas do software.
     */
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

        colunaOcupacaoPrimeiraFaseSalas.setCellValueFactory(new PropertyValueFactory<Espaco, Integer>("ocupacaoPrimeiraEtapa"));
        colunaOcupacaoPrimeiraFaseSalas.setCellFactory(TextFieldTableCell.forTableColumn(conversor));

        colunaOcupacaoSegundaFaseSalas.setCellValueFactory(new PropertyValueFactory<Espaco, Integer>("ocupacaoSegundaEtapa"));
        colunaOcupacaoSegundaFaseSalas.setCellFactory(TextFieldTableCell.forTableColumn(conversor));

        tabelaSalas.setItems(salasTreinamento);

        colunaIdentificacaoEspacosCafe.setCellValueFactory(new PropertyValueFactory<>("nomeEspaco"));
        colunaIdentificacaoEspacosCafe.setCellFactory(TextFieldTableCell.forTableColumn());

        colunaLotacaoEspacosCafe.setCellValueFactory(new PropertyValueFactory<Espaco, Integer>("lotacao"));
        colunaLotacaoEspacosCafe.setCellFactory(TextFieldTableCell.forTableColumn(conversor));

        colunaOcupacaoPrimeiraFaseEspacoCafe.setCellValueFactory(new PropertyValueFactory<Espaco, Integer>("ocupacaoPrimeiraEtapa"));
        colunaOcupacaoPrimeiraFaseEspacoCafe.setCellFactory(TextFieldTableCell.forTableColumn(conversor));

        colunaOcupacaoSegundaFaseEspacoCafe.setCellValueFactory(new PropertyValueFactory<Espaco, Integer>("ocupacaoSegundaEtapa"));
        colunaOcupacaoSegundaFaseEspacoCafe.setCellFactory(TextFieldTableCell.forTableColumn(conversor));

        tabelaEspacosCafe.setItems(espacosCafe);
    }

    /**
     * Responsável por criar os listeners para os elementos selecionados na tabela. Caso algum elemento em alguma
     * tabela seja selecionado, os botões de excluir e de abrir detalhes desse elemento se tornam disponíveis para o
     * usuário. Caso contrário, esmaece-os.
     */
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

    /**
     * Executado ao se clicar no MenuItem Salvar e Sair. Salva os dados da tabela e fecha o programa.
     */
    public void salvarESair(){
        salvarDados();
        fecharPrograma();
    }

    /**
     * Executado ao se clicar no MenuItem Sair sem Salvar. Fecha o programa e descarta os dados das tabelas.
     */
    public void sairSemSalvar(){
        fecharPrograma();
    }

    /**
     * Fecha o Stage principal, encerrando o ciclo do programa.
     */
    public void fecharPrograma(){
        Stage palco = (Stage) janela.getScene().getWindow();
        palco.close();
    }

    /**
     * Salva os dados das tabelas nos arquivos contidos na pasta dados, no mesmo diretório que o .jar do programa.
     */
    public void salvarDados(){
        DataHandler.salvarDados(infoPessoas, pessoas, new Pessoa());
        DataHandler.salvarDados(infoSalasTreinamento, salasTreinamento, new Espaco());
        DataHandler.salvarDados(infoEspacosCafe, espacosCafe, new Espaco());
        emitirAlertBox("Salvamento", "Os dados foram salvos");
        setModificacoesRealizadas(false);
    }

    /**
     * Cria o listener que escuta alterações ao atributo salvarAoSair. Quando escuta uma modificação, fecha o programa,
     * respeitando a escolha do usuário de salvar ou não as modificações.
     */
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

    /**
     * Cria um pop-up com informações escolhidas pelo desenvolvedor para alertar ou informar o usuário.
     * @param titulo o título da caixa de alerta.
     * @param mensagem a mensagem que será exibida para o usuário.
     */
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
        layout.setPrefWidth(500);
        Scene cena = new Scene(layout);
        palcoAlertBox.setResizable(false);
        palcoAlertBox.initModality(Modality.APPLICATION_MODAL);
        palcoAlertBox.setScene(cena);
        palcoAlertBox.sizeToScene();
        palcoAlertBox.show();
    }

    /**
     * Redistribui os participantes nas salas de treinamento
     */
    public void redistribuirEspacosCafe(){
        /*
         * As linhas abaixo limpam as atuais definições de espaços de café
         */
        Random gerador = new Random();

        Collections.shuffle(pessoas);

        for (Pessoa pessoa: pessoas) {
            pessoa.setEspacoCafePrimeiraEtapa("");
            pessoa.setEspacoCafeSegundaEtapa("");
        }
        for (Espaco sala: espacosCafe) {
            sala.setIntegrantesPrimeiraEtapa(new ArrayList<>());
            sala.setIntegrantesSegundaEtapa(new ArrayList<>());
        }

        /*
         * As linhas abaixo definem aleatoriamente as salas de treinamento para
         * cada um dos participantes, mas não fazem a troca de participantes entre a primeira e a segunda etapas
         */

        for (Pessoa pessoa: pessoas) {
            ArrayList<Espaco> espacos = getEspacosMaisVaziosPrimeiraEtapa(espacosCafe);
            Espaco espaco = espacos.get(gerador.nextInt(espacos.size()));

            pessoa.setEspacoCafePrimeiraEtapa(espaco.getNomeEspaco());
            pessoa.setEspacoCafeSegundaEtapa(espaco.getNomeEspaco());
            espaco.adicionarIntegrantesPrimeiraEtapa(pessoa);
            espaco.adicionarIntegrantesSegundaEtapa(pessoa);
        }

        /*
         * As linhas abaixo definem quais participantes continuam na sala na segunda etapa
         * e quais pessoas vão para outras salas
         */

        ArrayList<Pessoa> pessoasQueMudamDeEspaco = new ArrayList<>();
        for (Espaco espaco : espacosCafe) {
            boolean selecionador = true;
            for (Pessoa participante: espaco.getIntegrantesSegundaEtapa()) {
                if (selecionador) {
                    espaco.removerIntegrantesSegundaEtapa(participante);
                    pessoasQueMudamDeEspaco.add(participante);
                    selecionador = false;
                } else {
                    selecionador = true;
                }
            }
        }

        for (Pessoa p: pessoasQueMudamDeEspaco) { //Desnecessário para o funcionamento correto, mas facilita no debug
            p.setEspacoCafeSegundaEtapa("");
        }

        /*
         *     As linhas até o final do método são responsáveis por distribuir os participantes que
         * trocam de espaço. Cada um deles é destinado aleatoriamente para um espaço que não esteja cheio
         * ou que já tenha atingido o seu "valor esperado". A extensão e complexidade do método se devem
         * às condições propostas no problema:
         *
         *  1- Para estimular a troca de conhecimentos, metade das pessoas precisam trocar de sala entre
         *     as duas etapas do treinamento.
         *  2- A diferença de pessoas em cada sala deverá ser de no máximo 1 pessoa.
         *
         *     Como a implementação deste sistema leva em conta um número arbitrário de participantes, salas de
         * treinamento e espaços de café, delegar os participantes consistentemente para uma sala diferente da qual eles
         * realizaram a primeira parte do treinamento torna-se complicado.
         *     A situação é agravada ao se considerar a segunda condição do problema. Tal condição torna muito complexo
         * o processo de correção de eventuais erros que os algoritmos usados para implementar a primeira parte
         * geram.
         *     As implementações anteriores que tentei aparentavam corrigir todos os problemas citados acima, porém,
         * em situações limite, falhavam e geravam anomalias na distribuição. Uma grande quantidade de
         * testes seria necessária para ter uma melhor avaliação estatística da probabilidade de falha em situações
         * extremas (salas com tamanhos muito variados ou próximas do limite de lotação), algo que meu tempo disponível
         * não permitiria fazer.
         *     A implementação atual demonstrou poucas anomalias às regras de distribuição. Mas, reiterando, é
         * possível que ocorra algum bug ou freeze durante a execução deste método, e uma implementação mais robusta
         * deveria ser aplicada para um software de uso comercial.
         *
         * Edit #1: as anomalias de distribuição citadas acima são causadas por fatores matemáticos. Leia mais informações
         * no ReadMe do programa
         * Edit #2: caso a lotação máxima de uma sala não seja atingida, não ocorre nenhum tipo de bug e a distribuição
         * ocorre perfeitamente.
         *
         * *Comentário de implementação por Vitor Nathan Gonçalves.
         */

        /*
         * As linhas abaixo definem alguns parâmetros necessários para definir os limites de lotação e distribuição dos
         * participantes. Destaque especial para os Booleanos rollOver: eles servem como sistema para evitar que o algoritmo
         * entre em softlock devido a uma tentativa de atribuir um participante ao mesmo espaço que ele realizou a
         * primeira etapa do treinamento.
         */

        ArrayList<Espaco> espacosMaisCheios = getEspacosMaisCheiosSegundaEtapa(espacosCafe);
        int contadorPessoa = 0;
        int contadorEspaco = espacosCafe.indexOf(espacosMaisCheios.get(espacosMaisCheios.size()-1)); //PEGAR O ESPAÇO SEGUINTE AO MAIS CHEIO
        if (contadorEspaco == espacosCafe.size()-1) { // Evita um IndexOutOfBounds caso todos os espaços estejam com
            contadorEspaco = 0;                       // a mesma capacidade
        }
        boolean rollOver1 = false;
        boolean rollOver2 = false;
        boolean rollOver3 = false;
        int valorEsperadoPorSala = getEspacosMaisCheiosPrimeiraEtapa(espacosCafe).get(0).getIntegrantesPrimeiraEtapa().size();
        int totalMaximosEsperados = getEspacosMaisCheiosPrimeiraEtapa(espacosCafe).size();

        ArrayList<Espaco> espacosNaoLotados = new ArrayList<>();
        espacosNaoLotados.addAll(espacosCafe);

        /*
         * Início do loop principal de distribuição, só termina quando todos participantes tenham sido designados para
         * uma sala que atende aos critérios. Um espaço é mantido fixo enquanto os participantes são "ciclados" até
         * que um participante possa integrar o espaço, respeitando os critérios de distribuição
         */
        while (pessoasQueMudamDeEspaco.size() != 0) {

            //Caso somente uma sala não esteja lotada, atribui todos os participantes que restam à ela
            if(espacosNaoLotados.size() == 1) {
                Espaco espaco = espacosCafe.get(espacosCafe.indexOf(espacosNaoLotados.get(0)));
                for (Pessoa p: pessoasQueMudamDeEspaco) {
                    p.setEspacoCafeSegundaEtapa(espaco.getNomeEspaco());
                    espaco.adicionarIntegrantesSegundaEtapa(p);
                }
                break;
            }
            //Capacidade máxima se refere à população das salas que possuem 1 participante a mais que as outras
            //Caso a sala esteja com sua capacidade máxima esperada, pula para a próxima sala
            else if (espacosCafe.get(contadorEspaco).getIntegrantesSegundaEtapa().size() == valorEsperadoPorSala) {
                if (contadorEspaco >= espacosCafe.size()-1){
                    contadorEspaco = 0;
                } else {
                    contadorEspaco++;
                }
                if (rollOver1) {
                    if(rollOver2){
                        if (rollOver3) {
                            rollOver1 = rollOver2 = rollOver3 = false;
                            Espaco espaco = getEspacosMaisVaziosSegundaEtapa(espacosCafe).get(0);
                            espaco.adicionarIntegrantesSegundaEtapa(pessoasQueMudamDeEspaco.get(contadorPessoa));
                            pessoasQueMudamDeEspaco.get(contadorPessoa).setEspacoCafeSegundaEtapa(espaco.getNomeEspaco());
                            pessoasQueMudamDeEspaco.remove(pessoasQueMudamDeEspaco.get(contadorPessoa));
                        }
                        rollOver3 = true;
                    }
                    rollOver2 = true;
                }
                rollOver1 = true;
                continue;
            }

            //Caso o limite de salas com a capacidade máxima seja alcançado (as outras terão capacidade máx. - 1)
            else if (espacosCafe.get(contadorEspaco).getIntegrantesSegundaEtapa().size() == valorEsperadoPorSala-1 && totalMaximosEsperados ==0) {
                if (contadorEspaco >= espacosCafe.size()-1){
                    contadorEspaco = 0;
                } else {
                    contadorEspaco++;
                }
                if (rollOver1) {
                    if(rollOver2){
                        if (rollOver3) {
                            rollOver1 = rollOver2 = rollOver3 = false;
                            Espaco espaco = getEspacosMaisVaziosSegundaEtapa(espacosCafe).get(0);
                            espaco.adicionarIntegrantesSegundaEtapa(pessoasQueMudamDeEspaco.get(contadorPessoa));
                            pessoasQueMudamDeEspaco.get(contadorPessoa).setEspacoCafeSegundaEtapa(espaco.getNomeEspaco());
                            pessoasQueMudamDeEspaco.remove(pessoasQueMudamDeEspaco.get(contadorPessoa));
                        }
                        rollOver3 = true;
                    }
                    rollOver2 = true;
                }
                rollOver1 = true;
                continue;
            }

            //Caso o espaco esteja lotado, pula para o próximo espaço
            else if(espacosCafe.get(contadorEspaco).getIntegrantesSegundaEtapa().size() == espacosCafe.get(contadorEspaco).getLotacao()){
                espacosNaoLotados.remove(espacosCafe.get(contadorEspaco));
                if (contadorEspaco >= espacosCafe.size()-1){
                    contadorEspaco = 0;
                } else {
                    contadorEspaco++;
                }

                continue;
            }

            //Caso seja possível mudar o espaço
            else if (!pessoasQueMudamDeEspaco.get(contadorPessoa).getEspacoCafePrimeiraEtapa().equals(espacosCafe.get(contadorEspaco).getNomeEspaco()) &&
                    espacosCafe.get(contadorEspaco).getIntegrantesSegundaEtapa().size() < valorEsperadoPorSala){

                pessoasQueMudamDeEspaco.get(contadorPessoa).setEspacoCafeSegundaEtapa(espacosCafe.get(contadorEspaco).getNomeEspaco());
                espacosCafe.get(contadorEspaco).adicionarIntegrantesSegundaEtapa(pessoasQueMudamDeEspaco.get(contadorPessoa));
                pessoasQueMudamDeEspaco.remove(contadorPessoa);

                //Reduz 1 no número de salas que ainda podem ter 1 integrante a mais que as outras
                if (espacosCafe.get(contadorEspaco).getIntegrantesSegundaEtapa().size() == valorEsperadoPorSala) {
                    totalMaximosEsperados--;
                }

                //Pula para o próximo espaço
                if (contadorEspaco >= espacosCafe.size()-1){
                    contadorEspaco = 0;
                } else {
                    contadorEspaco++;
                }

                //Pula para a próxima pessoa da lista
                if (contadorPessoa >= pessoasQueMudamDeEspaco.size()-1){
                    contadorPessoa = 0;
                }

                //Reseta os rollOvers, visto que não houve necessidade de pular um espaço indisponível
                rollOver1 = false;
                rollOver2 = false;
                continue;

            }
            //Atuação do rollOver: todas pessoas foram testadas no espaço e nenhuma pode ser atribuida a ele
            //Pula para o próximo espaço e reseta os rollovers
            else if (rollOver2) {
                if(rollOver3) { //se tudo der errado acontece isso, e sai com imperfeições
                    Espaco espaco = getEspacosMaisVaziosSegundaEtapa(espacosCafe).get(0);
                    espaco.adicionarIntegrantesSegundaEtapa(pessoasQueMudamDeEspaco.get(contadorPessoa));
                    pessoasQueMudamDeEspaco.get(contadorPessoa).setEspacoSegundaEtapa(espaco.getNomeEspaco());
                    pessoasQueMudamDeEspaco.remove(pessoasQueMudamDeEspaco.get(contadorPessoa));
                    rollOver3 = false;
                    rollOver1 = false;
                    rollOver2 = false;
                }
                if (contadorEspaco >= espacosCafe.size()-1){
                    contadorEspaco = 0;
                } else {
                    contadorEspaco++;
                }

            }
            //Caso nenhuma das opções acima seja atendida, simplesmente pula para a próxima pessoa
            if (contadorPessoa >= pessoasQueMudamDeEspaco.size()-1){
                contadorPessoa = 0;
                if (rollOver1) {
                    if (rollOver2) {
                        rollOver3 = true;
                    }
                    rollOver2 = true;
                }
                rollOver1 = true;
            } else {
                contadorPessoa++;
            }
        }
    }

    /**
     * Redistribui os participantes nas salas de treinamento
     */
    public void redistribuirSalasTreinamento(){
        /*
         * As linhas abaixo limpam as atuais definições de salas
         */
        Random gerador = new Random();

        Collections.shuffle(pessoas);

        for (Pessoa pessoa: pessoas) {
            pessoa.setEspacoPrimeiraEtapa("");
            pessoa.setEspacoSegundaEtapa("");
        }
        for (Espaco sala: salasTreinamento) {
            sala.setIntegrantesPrimeiraEtapa(new ArrayList<>());
            sala.setIntegrantesSegundaEtapa(new ArrayList<>());
        }

        /*
         * As linhas abaixo definem aleatoriamente as salas de treinamento para
         * cada um dos participantes, mas não fazem a troca de participantes entre a primeira e a segunda etapas
         */

        for (Pessoa pessoa: pessoas) {
            ArrayList<Espaco> salas = getEspacosMaisVaziosPrimeiraEtapa(salasTreinamento);
            Espaco sala = salas.get(gerador.nextInt(salas.size()));

            pessoa.setEspacoPrimeiraEtapa(sala.getNomeEspaco());
            pessoa.setEspacoSegundaEtapa(sala.getNomeEspaco());
            sala.adicionarIntegrantesPrimeiraEtapa(pessoa);
            sala.adicionarIntegrantesSegundaEtapa(pessoa);
        }

        /*
         * As linhas abaixo definem quais participantes continuam na sala na segunda etapa
         * e quais pessoas vão para outras salas
         */

        ArrayList<Pessoa> pessoasQueMudamDeSala = new ArrayList<>();
        for (Espaco espaco : salasTreinamento) {
            boolean selecionador = true;
            for (Pessoa participante: espaco.getIntegrantesSegundaEtapa()) {
                if (selecionador) {
                    espaco.removerIntegrantesSegundaEtapa(participante);
                    pessoasQueMudamDeSala.add(participante);
                    selecionador = false;
                } else {
                    selecionador = true;
                }
            }
        }

        for (Pessoa p: pessoasQueMudamDeSala) {
            p.setEspacoSegundaEtapa("");
        }

        /*
         *     As linhas até o final do método são responsáveis por distribuir os participantes que
         * trocam de espaço. Cada um deles é destinado aleatoriamente para um espaço que não esteja cheio
         * ou que já tenha atingido o seu "valor esperado". A extensão e complexidade do método se devem
         * às condições propostas no problema:
         *
         *  1- Para estimular a troca de conhecimentos, metade das pessoas precisam trocar de sala entre
         *     as duas etapas do treinamento.
         *  2- A diferença de pessoas em cada sala deverá ser de no máximo 1 pessoa.
         *
         *     Como a implementação deste sistema leva em conta um número arbitrário de participantes, salas de
         * treinamento e espaços de café, delegar os participantes consistentemente para uma sala diferente da qual eles
         * realizaram a primeira parte do treinamento torna-se complicado.
         *     A situação é agravada ao se considerar a segunda condição do problema. Tal condição torna muito complexo
         * o processo de correção de eventuais erros que os algoritmos usados para implementar a primeira parte
         * geram.
         *     As implementações anteriores que tentei aparentavam corrigir todos os problemas citados acima, porém,
         * em situações limite, falhavam e geravam anomalias na distribuição. Uma grande quantidade de
         * testes seria necessária para ter uma melhor avaliação estatística da probabilidade de falha em situações
         * extremas (salas com tamanhos muito variados ou próximas do limite de lotação), algo que meu tempo disponível
         * não permitiria fazer.
         *     A implementação atual demonstrou poucas anomalias às regras de distribuição. Mas, reiterando, é
         * possível que ocorra algum bug ou freeze durante a execução deste método, e uma implementação mais robusta
         * deveria ser aplicada para um software de uso comercial.
         *
         * Edit #1: as anomalias de distribuição citadas acima são causadas por fatores matemáticos. Leia mais informações
         * no ReadMe do programa.
         * Edit #2: caso a lotação máxima de uma sala não seja atingida, não ocorre nenhum tipo de bug e a distribuição
         * ocorre perfeitamente.
         *
         * *Comentário de implementação por Vitor Nathan Gonçalves.
         */

        /*
         * As linhas abaixo definem alguns parâmetros necessários para definir os limites de lotação e distribuição dos
         * participantes. Destaque especial para os Booleanos rollOver: eles servem como sistema para evitar que o algoritmo
         * entre em softlock devido a uma tentativa de atribuir um participante ao mesmo espaço que ele realizou a
         * primeira etapa do treinamento.
         */
        ArrayList<Espaco> espacosMaisCheios = getEspacosMaisCheiosSegundaEtapa(salasTreinamento);
        int contadorPessoa = 0;
        int contadorEspaco = salasTreinamento.indexOf(espacosMaisCheios.get(espacosMaisCheios.size()-1)); //PEGAR O ESPAÇO SEGUINTE AO MAIS CHEIO
        if (contadorEspaco == salasTreinamento.size()-1) { // Evita um IndexOutOfBounds caso todos os espaços estejam com
            contadorEspaco = 0;                            // a mesma capacidade
        }
        boolean rollOver1 = false;
        boolean rollOver2 = false;
        boolean rollOver3 = false;
        int valorEsperadoPorSala = getEspacosMaisCheiosPrimeiraEtapa(salasTreinamento).get(0).getIntegrantesPrimeiraEtapa().size();
        int totalMaximosEsperados = getEspacosMaisCheiosPrimeiraEtapa(salasTreinamento).size();

        ArrayList<Espaco> espacosNaoLotados = new ArrayList<>();
        espacosNaoLotados.addAll(salasTreinamento);

        /*
         * Início do loop principal de distribuição, só termina quando todos participantes tenham sido designados para
         * uma sala que atende aos critérios. Um espaço é mantido fixo enquanto os participantes são "ciclados" até
         * que um participante possa integrar o espaço, respeitando os critérios de distribuição
         */
        while (pessoasQueMudamDeSala.size() != 0) {

            //Caso somente uma sala não esteja lotada, atribui todos os participantes que restam à ela
            if(espacosNaoLotados.size() == 1) {
                Espaco espaco = salasTreinamento.get(salasTreinamento.indexOf(espacosNaoLotados.get(0)));
                for (Pessoa p: pessoasQueMudamDeSala) {
                    p.setEspacoSegundaEtapa(espaco.getNomeEspaco());
                    espaco.adicionarIntegrantesSegundaEtapa(p);
                }
                break;
            }
            //Capacidade máxima se refere à população das salas que possuem 1 participante a mais que as outras
            //Caso a sala esteja com sua capacidade máxima esperada, pula para a próxima sala
            if (salasTreinamento.get(contadorEspaco).getIntegrantesSegundaEtapa().size() == valorEsperadoPorSala) {
                if (contadorEspaco >= salasTreinamento.size()-1){
                    contadorEspaco = 0;
                } else {
                    contadorEspaco++;
                }
                if (rollOver1) {
                    if(rollOver2){
                        if (rollOver3) {
                            rollOver1 = rollOver2 = rollOver3 = false;
                            Espaco espaco = getEspacosMaisVaziosSegundaEtapa(salasTreinamento).get(0);
                            espaco.adicionarIntegrantesSegundaEtapa(pessoasQueMudamDeSala.get(contadorPessoa));
                            pessoasQueMudamDeSala.get(contadorPessoa).setEspacoSegundaEtapa(espaco.getNomeEspaco());
                            pessoasQueMudamDeSala.remove(pessoasQueMudamDeSala.get(contadorPessoa));
                        }
                        rollOver3 = true;
                    }
                    rollOver2 = true;
                }
                rollOver1 = true;
                continue;
            }

            //Caso o limite de salas com a capacidade máxima seja alcançado (as outras terão capacidade máx. - 1)
            else if (salasTreinamento.get(contadorEspaco).getIntegrantesSegundaEtapa().size() == valorEsperadoPorSala-1 && totalMaximosEsperados ==0) {
                if (contadorEspaco >= salasTreinamento.size()-1){
                    contadorEspaco = 0;
                } else {
                    contadorEspaco++;
                }
                if (rollOver1) {
                    if(rollOver2){
                        if (rollOver3) {
                            rollOver1 = rollOver2 = rollOver3 = false;
                            Espaco espaco = getEspacosMaisVaziosSegundaEtapa(salasTreinamento).get(0);
                            espaco.adicionarIntegrantesSegundaEtapa(pessoasQueMudamDeSala.get(contadorPessoa));
                            pessoasQueMudamDeSala.get(contadorPessoa).setEspacoSegundaEtapa(espaco.getNomeEspaco());
                            pessoasQueMudamDeSala.remove(pessoasQueMudamDeSala.get(contadorPessoa));
                        }
                        rollOver3 = true;
                    }
                    rollOver2 = true;
                }
                rollOver1 = true;
                continue;
            }

            //Caso o espaco esteja lotado, pula para o próximo espaço
            else if(salasTreinamento.get(contadorEspaco).getIntegrantesSegundaEtapa().size() == salasTreinamento.get(contadorEspaco).getLotacao()){
                espacosNaoLotados.remove(salasTreinamento.get(contadorEspaco));
                if (contadorEspaco >= salasTreinamento.size()-1){
                    contadorEspaco = 0;
                } else {
                    contadorEspaco++;
                }
                continue;
            }

            //Caso seja possível mudar a sala
            else if (!pessoasQueMudamDeSala.get(contadorPessoa).getEspacoPrimeiraEtapa().equals(salasTreinamento.get(contadorEspaco).getNomeEspaco()) &&
                    salasTreinamento.get(contadorEspaco).getIntegrantesSegundaEtapa().size() < valorEsperadoPorSala){

                pessoasQueMudamDeSala.get(contadorPessoa).setEspacoSegundaEtapa(salasTreinamento.get(contadorEspaco).getNomeEspaco());
                salasTreinamento.get(contadorEspaco).adicionarIntegrantesSegundaEtapa(pessoasQueMudamDeSala.get(contadorPessoa));
                pessoasQueMudamDeSala.remove(contadorPessoa);

                //Reduz 1 no número de salas que ainda podem ter 1 integrante a mais que as outras
                if (salasTreinamento.get(contadorEspaco).getIntegrantesSegundaEtapa().size() == valorEsperadoPorSala) {
                    totalMaximosEsperados--;
                }

                //Pula para o próximo espaço
                if (contadorEspaco >= salasTreinamento.size()-1){
                    contadorEspaco = 0;
                } else {
                    contadorEspaco++;
                }

                //Pula para a próxima pessoa da lista
                if (contadorPessoa >= pessoasQueMudamDeSala.size()-1){
                    contadorPessoa = 0;
                }

                //Reseta os rollOvers, visto que não houve necessidade de pular um espaço indisponível
                rollOver1 = false;
                rollOver2 = false;

                continue;

            }
            //Atuação do rollOver: todas pessoas foram testadas no espaço e nenhuma pode ser atribuida a ele
            //Pula para o próximo espaço e reseta os rollovers
            else if (rollOver2) {
                if(rollOver3) { //se tudo der errado acontece isso, e sai com imperfeições
                    Espaco espaco = getEspacosMaisVaziosSegundaEtapa(salasTreinamento).get(0);
                    espaco.adicionarIntegrantesSegundaEtapa(pessoasQueMudamDeSala.get(contadorPessoa));
                    pessoasQueMudamDeSala.get(contadorPessoa).setEspacoSegundaEtapa(espaco.getNomeEspaco());
                    pessoasQueMudamDeSala.remove(pessoasQueMudamDeSala.get(contadorPessoa));
                    rollOver3 = false;
                    rollOver1 = false;
                    rollOver2 = false;
                }
                if (contadorEspaco >= salasTreinamento.size()-1){
                    contadorEspaco = 0;
                } else {
                    contadorEspaco++;
                }

            }
            //Caso nenhuma das opções acima seja atendida, simplesmente pula para a próxima pessoa
            if (contadorPessoa >= pessoasQueMudamDeSala.size()-1){
                contadorPessoa = 0;
                if (rollOver1) {
                    if (rollOver2) {
                        rollOver3 = true;
                    }
                    rollOver2 = true;
                }
                rollOver1 = true;
            } else {
                contadorPessoa++;
            }
        }
    }

    public ArrayList<Espaco> getEspacosMaisVaziosPrimeiraEtapa(ObservableList<Espaco> espacos){
        int minimoPessoas = Integer.MAX_VALUE;
        ArrayList<Espaco> retorno = new ArrayList<>();
        for (Espaco espaco : espacos) {
            if (espaco.getLotacao() == espaco.getIntegrantesPrimeiraEtapa().size()){
                continue;
            }
            if (espaco.getIntegrantesPrimeiraEtapa().size() < minimoPessoas) {
                retorno = new ArrayList<>();
                retorno.add(espaco);
                minimoPessoas = espaco.getIntegrantesPrimeiraEtapa().size();
            } else if (espaco.getIntegrantesPrimeiraEtapa().size() == minimoPessoas) {
                retorno.add(espaco);
            }
        }
        return retorno;
    }

    public ArrayList<Espaco> getEspacosMaisVaziosSegundaEtapa(ObservableList<Espaco> espacos){
        int minimoPessoas = Integer.MAX_VALUE;
        ArrayList<Espaco> retorno = new ArrayList<>();
        for (Espaco espaco : espacos) {
            if(espaco.getLotacao() == espaco.getIntegrantesSegundaEtapa().size()) {
                continue;
            }
            if (espaco.getIntegrantesSegundaEtapa().size() < minimoPessoas) {
                retorno = new ArrayList<>();
                retorno.add(espaco);
                minimoPessoas = espaco.getIntegrantesSegundaEtapa().size();
            } else if (espaco.getIntegrantesSegundaEtapa().size() == minimoPessoas) {
                retorno.add(espaco);
            }
        }
        return retorno;
    }

    public ArrayList<Espaco> getEspacosMaisCheiosSegundaEtapa(ObservableList<Espaco> espacos){
        int maximoPessoas = Integer.MIN_VALUE;
        ArrayList<Espaco> retorno = new ArrayList<>();
        for (Espaco espaco : espacos) {
            if(espaco.getLotacao() == espaco.getIntegrantesSegundaEtapa().size()) {
                continue;
            }
            if (espaco.getIntegrantesSegundaEtapa().size() > maximoPessoas) {
                retorno = new ArrayList<>();
                retorno.add(espaco);
                maximoPessoas = espaco.getIntegrantesSegundaEtapa().size();
            } else if (espaco.getIntegrantesSegundaEtapa().size() == maximoPessoas) {
                retorno.add(espaco);
            }
        }
        return retorno;
    }

    public ArrayList<Espaco> getEspacosMaisCheiosPrimeiraEtapa(ObservableList<Espaco> espacos){
        int maximoPessoas = Integer.MIN_VALUE;
        ArrayList<Espaco> retorno = new ArrayList<>();
        for (Espaco espaco : espacos) {
            if(espaco.getLotacao() == espaco.getIntegrantesPrimeiraEtapa().size()) {
                continue;
            }
            if (espaco.getIntegrantesPrimeiraEtapa().size() > maximoPessoas) {
                retorno = new ArrayList<>();
                retorno.add(espaco);
                maximoPessoas = espaco.getIntegrantesPrimeiraEtapa().size();
            } else if (espaco.getIntegrantesPrimeiraEtapa().size() == maximoPessoas) {
                retorno.add(espaco);
            }
        }
        return retorno;
    }
}
