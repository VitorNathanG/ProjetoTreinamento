package classes;

import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;

/**
 * Classe Testes: executa os testes unitários das classes utilizadas na construção do software
 *
 * @author Vitor Nathan Gonçalves
 */
public class Testes {

    public static void main(String[] args) {


        System.out.println("FileHandler.criarArquivo(): " + testarCriarArquivo());
        testarLerEEscreverArquivo();
        System.out.println("FileHandler.adicionarAoArquivo(): " + testarAdicionarAoArquivo());
        System.out.println("FileHandler.contLinhas(): " + testarContLinhas());
        System.out.println("FileHandler.removerLinha(): " + testarRemoverLinha());
        System.out.println("DataHandler.recuperarDadosSalvos(): " + testarRecuperarDadosSalvos());
        System.out.println("DataHandler.salvarDados(Pessoa): " + testarSalvarDadosPessoa());
        System.out.println("DataHandler.salvarDados(Espaco): " + testarSalvarDadosEspaco());
        System.out.println("DataHandler.adicionarPessoa(): " + testarAdicionarPessoa());
        System.out.println("DataHandler.adicionarEspaco(): " + testarAdicionarEspaco());
        System.out.println("DataHandler.unirStringParaSalvar(): " + testarUnirStringParaSalvar());
        System.out.println("DataHandler.gerarPessoas(): " + testarGerarPessoas());
        System.out.println("DataHandler.gerarEspacos(): " + testarGerarEspacos());
        System.out.println("Espaco.adicionarIntegrantesPrimeiraEtapa(): " + testarAdicionarIntegrantesPrimeiraEtapa());
        System.out.println("Espaco.adicionarIntegrantesSegundaEtapa(): " + testarAdicionarIntegrantesSegundaEtapa());
        System.out.println("Espaco.removerIntegrantesPrimeiraEtapa(): " + testarRemoverIntegrantesPrimeiraEtapa());
        System.out.println("Espaco.removerIntegrantesSegundaEtapa(): " + testarRemoverIntegrantesSegundaEtapa());
        System.out.println("Pessoa separar nome e sobrenome: " + testarSepararNomeESobrenome());
    }

    public static boolean testarCriarArquivo () {
        boolean retorno = false;
        File teste = FileHandler.criarArquivo("teste de criação de arquivo.teste");
        retorno = teste.exists();
        teste.deleteOnExit();
        return retorno;
    }

    public static void testarLerEEscreverArquivo(){
        File arquivoParaTesteLeituraFileHandler = FileHandler.criarArquivo("Arquivo para teste de leitura e escrita.txt");
        FileHandler.escreverArquivo(arquivoParaTesteLeituraFileHandler, "Texto substituto \n Texto substituto");
        if (FileHandler.lerArquivo(arquivoParaTesteLeituraFileHandler).equals("Texto substituto \n Texto substituto")) {
            System.out.println("FileHandler escreverArquivo e lerArquivo: true");
        } else {
            System.out.println("FileHandler escreverArquivo e lerArquivo: false");
        }
        FileHandler.escreverArquivo(arquivoParaTesteLeituraFileHandler, "Texto de teste para o arquivo");
    }

    public static boolean testarAdicionarAoArquivo(){
        File arquivoParaAdicionarTexto = FileHandler.criarArquivo("Arquivo para teste de leitura e escrita.txt");
        FileHandler.escreverArquivo(arquivoParaAdicionarTexto, "");
        FileHandler.adicionarAoArquivo(arquivoParaAdicionarTexto, "Texto de teste para o arquivo 2");
        String textoArquivo = FileHandler.lerArquivo(arquivoParaAdicionarTexto);
        if (textoArquivo.equals(FileHandler.ENTER + "Texto de teste para o arquivo 2")){
            FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
            return true;
        } else {
            FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
            return false;
        }
    }

    public static boolean testarContLinhas() {
        File arquivoParaAdicionarTexto = FileHandler.criarArquivo("Arquivo para teste de leitura e escrita.txt");
        FileHandler.escreverArquivo(arquivoParaAdicionarTexto, "");
        for (int i = 0; i < 10; i++) {
            FileHandler.adicionarAoArquivo(arquivoParaAdicionarTexto,"Linha " + i);
        }
        if(FileHandler.contLinhas(arquivoParaAdicionarTexto) == 11) {
            FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
            return true;
        } else {
            FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
            return false;
        }
    }

    public static boolean testarRemoverLinha(){
        File arquivoParaAdicionarTexto = FileHandler.criarArquivo("Arquivo para teste de leitura e escrita.txt");
        FileHandler.escreverArquivo(arquivoParaAdicionarTexto, "Linha 1" + FileHandler.ENTER + "Linha 2" + FileHandler.ENTER + "Linha 3");
        FileHandler.removerLinha(arquivoParaAdicionarTexto, 2);
        if(FileHandler.lerArquivo(arquivoParaAdicionarTexto).equals("Linha 1" + FileHandler.ENTER + "Linha 3")) {
            FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
            return true;
        } else {
            FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
            return false;
        }
    }

    public static boolean testarRecuperarDadosSalvos() {
        File arquivoParaAdicionarTexto = FileHandler.criarArquivo("Arquivo para teste de leitura e escrita.txt");
        FileHandler.escreverArquivo(arquivoParaAdicionarTexto, "João$Pedro$Sala 1$Sala 2$Cafeteria$Café$");
        String [][] dadoEscrito = DataHandler.recuperarDadosSalvos(arquivoParaAdicionarTexto);
        String[][] dadoEsperado = new String[1][6];
        dadoEsperado[0][0] = "João";
        dadoEsperado[0][1] = "Pedro";
        dadoEsperado[0][2] = "Sala 1";
        dadoEsperado[0][3] = "Sala 2";
        dadoEsperado[0][4] = "Cafeteria";
        dadoEsperado[0][5] = "Café";
        for (int i = 0; i < 6; i++) {
            if (!dadoEscrito[0][i].equals(dadoEsperado[0][i])) {
                FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
                return false;
            }
        }
        FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
        return true;
    }

    public static boolean testarSalvarDadosPessoa(){
        File arquivoParaAdicionarTexto = FileHandler.criarArquivo("Arquivo para teste de leitura e escrita.txt");
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("João", "Guilherme", "Sala 2", "Sala 1", "Restaurante", "Bar"));
        DataHandler.salvarDados(arquivoParaAdicionarTexto, pessoas, new Pessoa());
        if (FileHandler.lerArquivo(arquivoParaAdicionarTexto).equals("João$Guilherme$Sala 2$Sala 1$Restaurante$Bar$")) {
            FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
            return true;
        } else {
            FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
            return false;
        }
    }

    public static boolean testarSalvarDadosEspaco(){
        File arquivoParaAdicionarTexto = FileHandler.criarArquivo("Arquivo para teste de leitura e escrita.txt");
        ArrayList<Espaco> espacos = new ArrayList<>();
        espacos.add(new Espaco("Sala 2", 14));
        DataHandler.salvarDados(arquivoParaAdicionarTexto, espacos, new Espaco());
        if (FileHandler.lerArquivo(arquivoParaAdicionarTexto).equals("Sala 2$14$")) {
            FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
            return true;
        } else {
            FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
            return false;
        }
    }

    public static boolean testarAdicionarPessoa(){
        File arquivoParaAdicionarTexto = FileHandler.criarArquivo("Arquivo para teste de leitura e escrita.txt");
        DataHandler.adicionarPessoa(arquivoParaAdicionarTexto, new Pessoa("João", "Guilherme", "Sala 2", "Sala 1", "Restaurante", "Bar"));
        String textoArquivo = FileHandler.lerArquivo(arquivoParaAdicionarTexto);
        if (textoArquivo.equals("Texto de teste para o arquivo"+ FileHandler.ENTER +"João$Guilherme$Sala 2$Sala 1$Restaurante$Bar$")) {
            FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
            return true;
        } else {
            FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
            return false;
        }
    }

    public static boolean testarAdicionarEspaco() {
        File arquivoParaAdicionarTexto = FileHandler.criarArquivo("Arquivo para teste de leitura e escrita.txt");
        DataHandler.adicionarEspaco(arquivoParaAdicionarTexto, new Espaco("Sala 1", 14));
        String textoArquivo = FileHandler.lerArquivo(arquivoParaAdicionarTexto);
        if (textoArquivo.equals("Texto de teste para o arquivo"+ FileHandler.ENTER +"Sala 1$14$")) {
            FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
            return true;
        } else {
            FileHandler.escreverArquivo(arquivoParaAdicionarTexto,"Texto de teste para o arquivo");
            return false;
        }
    }

    public static boolean testarUnirStringParaSalvar(){
        return DataHandler.unirStringParaSalvar("String 1", "String 2", "String 3").equals("String 1$String 2$String 3$");
    }

    public static boolean testarGerarPessoas() {
        File arquivoParaAdicionarTexto = FileHandler.criarArquivo("Arquivo para teste de leitura e escrita.txt");
        FileHandler.escreverArquivo(arquivoParaAdicionarTexto, "Marcos$Guilherme$Sala 2$Sala 1$Restaurante$Bar$");
        ObservableList<Pessoa> pessoas = DataHandler.gerarPessoas(DataHandler.recuperarDadosSalvos(arquivoParaAdicionarTexto));
        Pessoa pessoa = pessoas.get(0);
        if(!pessoa.getNome().equals("Marcos")){
            return false;
        } else if (!pessoa.getSobrenome().equals("Guilherme")){
            return false;
        } else if (!pessoa.getEspacoPrimeiraEtapa().equals("Sala 2")){
            return false;
        } else if (!pessoa.getEspacoSegundaEtapa().equals("Sala 1")){
            return false;
        } else if (!pessoa.getEspacoCafePrimeiraEtapa().equals("Restaurante")){
            return false;
        } else if (!pessoa.getEspacoCafeSegundaEtapa().equals("Bar")){
            return false;
        } else {
            return true;
        }
    }

    public static boolean testarGerarEspacos() {
        File arquivoParaAdicionarTexto = FileHandler.criarArquivo("Arquivo para teste de leitura e escrita.txt");
        FileHandler.escreverArquivo(arquivoParaAdicionarTexto, "Bar$22$");
        ObservableList<Espaco> espacos = DataHandler.gerarEspacos(DataHandler.recuperarDadosSalvos(arquivoParaAdicionarTexto));
        Espaco espaco = espacos.get(0);
        if(!espaco.getNomeEspaco().equals("Bar")){
            return false;
        } else if (!(espaco.getLotacao() == 22)){
            return false;
        } else {
            return true;
        }
    }

    public static boolean testarAdicionarIntegrantesPrimeiraEtapa() {
        Espaco espacoTeste = new Espaco("Espaço de fabricação industrial de lasanhas", 1729);
        espacoTeste.adicionarIntegrantesPrimeiraEtapa(new Pessoa("Nova", "Pessoa"), new Pessoa("Nova", "Pessoa"));
        for (Pessoa p :espacoTeste.getIntegrantesPrimeiraEtapa()) {
            if (!p.getNome().equals("Nova")){
                return false;
            } else if (!p.getSobrenome().equals("Pessoa")) {
                return false;
            }
        }
        return true;
    }

    public static boolean testarAdicionarIntegrantesSegundaEtapa() {
        Espaco espacoTeste = new Espaco("Espaço de fabricação industrial de pokémon", 42);
        espacoTeste.adicionarIntegrantesSegundaEtapa(new Pessoa("Nova", "Pessoa"), new Pessoa("Nova", "Pessoa"));
        for (Pessoa p :espacoTeste.getIntegrantesSegundaEtapa()) {
            if (!p.getNome().equals("Nova")){
                return false;
            } else if (!p.getSobrenome().equals("Pessoa")) {
                return false;
            }
        }
        return true;
    }

    public static boolean testarRemoverIntegrantesPrimeiraEtapa() {
        Espaco espacoTeste = new Espaco("Espaço de fabricação industrial de garrafas térmicas", 121);
        espacoTeste.adicionarIntegrantesPrimeiraEtapa(new Pessoa("Nova", "Pessoa"), new Pessoa("Nova", "Pessoa"));
        for (Pessoa p :espacoTeste.getIntegrantesPrimeiraEtapa()) {
            if (!p.getNome().equals("Nova")){
                return false;
            } else if (!p.getSobrenome().equals("Pessoa")) {
                return false;
            }
        }
        espacoTeste.removerIntegrantePrimeiraEtapa(espacoTeste.getIntegrantesPrimeiraEtapa().get(0));
        espacoTeste.removerIntegrantePrimeiraEtapa(espacoTeste.getIntegrantesPrimeiraEtapa().get(0));
        if(espacoTeste.getIntegrantesPrimeiraEtapa().size() == 0) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean testarRemoverIntegrantesSegundaEtapa() {
        Espaco espacoTeste = new Espaco("Espaço de fabricação industrial de lasanhas", 1331);
        espacoTeste.adicionarIntegrantesSegundaEtapa(new Pessoa("Nova", "Pessoa"), new Pessoa("Nova", "Pessoa"));
        for (Pessoa p :espacoTeste.getIntegrantesSegundaEtapa()) {
            if (!p.getNome().equals("Nova")){
                return false;
            } else if (!p.getSobrenome().equals("Pessoa")) {
                return false;
            }
        }
        espacoTeste.removerIntegrantesSegundaEtapa(espacoTeste.getIntegrantesSegundaEtapa().get(0));
        espacoTeste.removerIntegrantesSegundaEtapa(espacoTeste.getIntegrantesSegundaEtapa().get(0));
        if(espacoTeste.getIntegrantesSegundaEtapa().size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean testarSepararNomeESobrenome(){
        boolean retorno = true;
        String nome = "Marcos Antônio José Almeida Anastácio de Alencar Borges";
        if(!Pessoa.separarNome(nome).equals("Marcos Antônio José Almeida Anastácio de Alencar")){
            retorno = false;
        }
        if(!Pessoa.separarSobrenome(nome).equals("Borges")){
            retorno = false;
        }
        return retorno;
    }
}

