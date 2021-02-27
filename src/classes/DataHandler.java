package classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Classe DataHandler: criada para salvar e recuperar os dados salvos nos arquivos contidos na pasta de dados.
 *
 * O conteúdo desta classe não deve ser utilizado para situações de muita carga, visto que os métodos são pouco
 * otimizados para velocidade. Nesse caso, um banco de dados tradicional deve ser aplicado.
 * 
 * @author Vitor Nathan Gonçalves
 */
public class DataHandler {

    /**
     * Recupera os dados salvos relativos às informações das pessoas cadastradas para o treinamento.
     * Este método é rapido o suficiente para carregar dezenas de milhares de registros em poucos milissegundos
     * @param arquivo endereço do arquivo no sistema
     * @return String[n][m], n é o número de linhas, m é o número de informações por linha
     */
    public static String[][] recuperarDadosSalvos(File arquivo){
        String[][] retorno = new String[FileHandler.contLinhas(arquivo)][6];
        String linhaAtual;

        //Inicia o loop de checagem por fim de campo
        try (Scanner leitorTexto = new Scanner(FileHandler.lerArquivo(arquivo))){

            int cont = 0;

            while (leitorTexto.hasNextLine()){          //lê linha por linha do arquivo
                linhaAtual = leitorTexto.nextLine();
                String[] info = new String[10];
                char[] letras = new char[linhaAtual.length()];

                //Separa cada caractere da linha atual para o vetor letras[]
                linhaAtual.getChars(0, linhaAtual.length(), letras, 0);

                //i é o número da "coluna" da "tabela" do arquivo
                int i = 0;

                //Preenche cada elemento de info, para evitar NullPointerException
                for (int j = 0; j < info.length; j++) {
                    info[i] = "";
                }

                /*
                 * Para cada caractere da linha, verifica se é uma letra ou o caractere separador $,
                 * quando o loop encontra um $, ele passa a preencher a próxima "coluna" da tabela
                 */
                for (char letra : letras) {
                    if (letra != '$') {
                        if(letra != 0){
                            info[i] += letra;
                        }
                    } else {
                        i += 1;
                        for (int j = 0; j < info.length; j++) {
                            info[i] = "";
                        }
                    }
                }
                for (int j = 0; j < i; j++) {
                    retorno[cont][j] = info[j];
                }
                cont++;
            }
        }
        return retorno;
    }

    /*
     * O autor preferiu utilizar métodos diferentes para salvar tipos de dados distintos. Isso se deve ao fato de
     * haverem diferentes atributos que devem ser salvos em cada tipo. Para diferenciar entre os métodos de salvamento
     * sobrecarregados, ao chamar o método, deve-se inserir como 3º parâmetro um dummy do tipo de dado que será salvo.
     * Ele age como o indicador para diferenciá-los.
     *
     * Comentário de implementação por Vitor Nathan Gonçalves.
     */
    /**
     * Salva os dados contido em uma List de Pessoa no arquivo correspondente
     * @param arquivo o arquivo de destino do salvamento
     * @param pessoas a lista contendo as pessoas e as informações a serem salvas no arquivo
     * @param dummy
     */
    public static void salvarDados(File arquivo, List<Pessoa> pessoas, Pessoa dummy){
        StringBuilder textoSalvar = new StringBuilder();

        for (Pessoa pessoa : pessoas) { //Cada pessoa ocupa uma linha no arquivo
            textoSalvar.append(FileHandler.ENTER).append(unirStringParaSalvar(pessoa.getNome(),
                    pessoa.getSobrenome(), pessoa.getEspacoPrimeiraEtapa(), pessoa.getEspacoSegundaEtapa(),
                    pessoa.getEspacoCafePrimeiraEtapa(), pessoa.getEspacoCafeSegundaEtapa()));
        }
        
        FileHandler.escreverArquivo(arquivo , textoSalvar.toString());

        //O método escreverArquivo deixa a primeira linha em branco, deve-se removê-la
        FileHandler.removerLinha(arquivo, 1);
    }

    /**
     * Salva os dados contido em uma List de Espaco no arquivo correspondente
     * @param arquivo o arquivo de destino do salvamento
     * @param espaco a lista contendo os espaços e as informações a serem salvas no arquivo
     * @param dummy
     */
    public static void salvarDados(File arquivo, List<Espaco> espaco, Espaco dummy){
        StringBuilder textoSalvar = new StringBuilder();

        for (Espaco espacoAppend : espaco) { // Cada espaço ocupa uma linha no arquivo
            textoSalvar.append(FileHandler.ENTER).append(unirStringParaSalvar(espacoAppend.getNomeEspaco(),
                    String.valueOf(espacoAppend.getLotacao())));
        }

        FileHandler.escreverArquivo(arquivo , textoSalvar.toString());
        FileHandler.removerLinha(arquivo, 1);
    }

    /**
     * Adiciona uma pessoa ao fim de um arquivo.
     * Não utilizado na versão atual do software.
     * @param arquivo arquivo destino da adição
     * @param pessoa pessoa que será adicionada
     */
    public static void adicionarPessoa(File arquivo, Pessoa pessoa){
        StringBuilder textoSalvar = new StringBuilder();
        textoSalvar.append(unirStringParaSalvar(pessoa.getNome(), pessoa.getSobrenome(), pessoa.getEspacoPrimeiraEtapa(), pessoa.getEspacoSegundaEtapa(),
                                                pessoa.getEspacoCafePrimeiraEtapa(), pessoa.getEspacoCafeSegundaEtapa()));
        FileHandler.adicionarAoArquivo(arquivo, textoSalvar.toString());
    }

    /**
     * Adiciona uma espaco ao fim de um arquivo.
     * Não utilizado na versão atual do software.
     * @param arquivo arquivo destino da adição
     * @param espaco espaco que será adicionado
     */
    public static void adicionarEspaco(File arquivo, Espaco espaco){
        StringBuilder textoSalvar = new StringBuilder();
        textoSalvar.append(unirStringParaSalvar(espaco.getNomeEspaco(), String.valueOf(espaco.getLotacao())));
        FileHandler.adicionarAoArquivo(arquivo, textoSalvar.toString());
    }

    /**
     * Utilizado toda vez que é necessário criar uma linha para ser salva,
     * @param termos String... com as informações a serem salvas
     * @return String contendo uma linha de texto concatenado e pronto para ser salvo seguindo
     *         o estilo da classe FileHandler
     */
    private static String unirStringParaSalvar (String... termos){

        StringBuilder textoRetorno = new StringBuilder();
        String[] infoStrings = termos;
        for (String string : infoStrings) {
            if (string == null){
                textoRetorno.append("$");
            } else {
                textoRetorno.append(string + "$");
            }
        }
        return textoRetorno.toString();
    }

    /**
     * @deprecated Método anteriormente utilizado como parâmetro para o método gerarPessoas
     * @param pessoasList lista de pessoas para serem transformadas em String[][]
     * @param dummy
     * @return String [][] contendo as informações das pessoas
     */
    public static String[][] gerarStrings(List<Pessoa> pessoasList, Pessoa dummy){
        String[][] retorno = new String[pessoasList.size()][6];
        for (int i = 0; i < pessoasList.size(); i++) {
            retorno[i][0] = pessoasList.get(i).getNome();
            retorno[i][1] = pessoasList.get(i).getSobrenome();
            retorno[i][2] = pessoasList.get(i).getEspacoPrimeiraEtapa();
            retorno[i][3] = pessoasList.get(i).getEspacoSegundaEtapa();
            retorno[i][4] = pessoasList.get(i).getEspacoCafePrimeiraEtapa();
            retorno[i][5] = pessoasList.get(i).getEspacoCafeSegundaEtapa();
        }
        return retorno;
    }

    /**
     * Gera os objetos Pessoa baseados no parâmetro pessoasStrings.
     * Usado em conjunto com recuperarDadosSalvos(): gerarPessoas(recuperarDadosSalvos())
     * @param pessoasStrings
     * @return ObservableList<Pessoa> contendo os objetos à serem utilizados no PrincipalController
     */
    public static ObservableList<Pessoa> gerarPessoas(String[][] pessoasStrings) {
        ObservableList<Pessoa> retorno = FXCollections.observableArrayList();
        for (String[] strings : pessoasStrings) {
            if (strings[0] == null) {
                break;
            }
            retorno.add(new Pessoa(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5]));
        }
        return retorno;
    }

    /**
     * @deprecated Método anteriormente utilizado como parâmetro para o método gerarEspacos
     * @param espacoList lista de Espaco para serem transformados em String[][]
     * @param dummy
     * @return String [][] contendo as informações dos espaços
     */
    public static String[][] gerarStrings(List<Espaco> espacoList, Espaco dummy){
        String[][] retorno = new String[espacoList.size()][1];
        for (int i = 0; i < espacoList.size(); i++) {
            retorno[i][0] = espacoList.get(i).getNomeEspaco();
            retorno[i][1] = String.valueOf(espacoList.get(i).getLotacao());
        }
        return retorno;
    }

    /**
     * Gera os objetos Espaco baseados no parâmetro espacosStrings.
     * Usado em conjunto com recuperarDadosSalvos(): gerarEspacos(recuperarDadosSalvos())
     * @param espacosStrings
     * @return ObservableList<Espaco> contendo os objetos à serem utilizados no PrincipalController
     */
    public static ObservableList<Espaco> gerarEspacos(String[][] espacosStrings) {
        ObservableList<Espaco> retorno = FXCollections.observableArrayList();
        for (String[] strings : espacosStrings) {
            if(strings[1]==null) {
                break;
            }
            retorno.add(new Espaco(strings[0], Integer.parseInt(strings[1])));
        }
        return retorno;
    }
}