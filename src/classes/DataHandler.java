package classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Classe criada para salvar e recuperar os dados salvos nos arquivos contidos na pasta de dados.
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
        try (Scanner leitorTexto = new Scanner(FileHandler.lerArquivo(arquivo))){
            int cont = 0;
            while (leitorTexto.hasNextLine()){
                linhaAtual = leitorTexto.nextLine();
                String[] info = new String[10];
                char[] letras = new char[linhaAtual.length()];
                linhaAtual.getChars(0, linhaAtual.length(), letras, 0);
                int i = 0;
                for (int j = 0; j < info.length; j++) {
                    info[i] = "";
                }
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

    public static void salvarDados(File arquivo, List<Pessoa> pessoas, Pessoa dummy){
        StringBuilder textoSalvar = new StringBuilder();
        for (Pessoa pessoa : pessoas) {
            textoSalvar.append(FileHandler.ENTER).append(unirStringParaSalvar(pessoa.getNome(), pessoa.getSobrenome(), pessoa.getEspacoPrimeiraEtapa(), pessoa.getEspacoSegundaEtapa(),
            pessoa.getEspacoCafePrimeiraEtapa(), pessoa.getEspacoCafeSegundaEtapa()));
        }
        
        FileHandler.escreverArquivo(arquivo , textoSalvar.toString());
        FileHandler.removerLinha(arquivo, 1);
    }

    public static void salvarDados(File arquivo, List<EspacoCafe> espacoCafe, EspacoCafe dummy){
        StringBuilder textoSalvar = new StringBuilder();
        for (EspacoCafe espaco : espacoCafe) {
            textoSalvar.append(FileHandler.ENTER).append(unirStringParaSalvar(espaco.getNomeEspaco(), String.valueOf(espaco.getLotacao())));
        }

        FileHandler.escreverArquivo(arquivo , textoSalvar.toString());
        FileHandler.removerLinha(arquivo, 1);
    }

    public static void salvarDados(File arquivo, List<EspacoTreinamento> espacoTreinamentos, EspacoTreinamento dummy){
        StringBuilder textoSalvar = new StringBuilder();
        for (EspacoTreinamento espaco : espacoTreinamentos) {
            textoSalvar.append(FileHandler.ENTER).append(unirStringParaSalvar(espaco.getNomeEspaco(), String.valueOf(espaco.getLotacao())));
        }

        FileHandler.escreverArquivo(arquivo , textoSalvar.toString());
        FileHandler.removerLinha(arquivo, 1);
    }

    public static void adicionarPessoa(File arquivo, Pessoa pessoa){
        StringBuilder textoSalvar = new StringBuilder();
        textoSalvar.append(unirStringParaSalvar(pessoa.getNome(), pessoa.getSobrenome(), pessoa.getEspacoPrimeiraEtapa(), pessoa.getEspacoSegundaEtapa(),
                                                pessoa.getEspacoCafePrimeiraEtapa(), pessoa.getEspacoCafeSegundaEtapa()));
        FileHandler.adicionarAoArquivo(arquivo, textoSalvar.toString());
    }

    public static void adicionarEspacoCafe(File arquivo, EspacoCafe espacoCafe){
        StringBuilder textoSalvar = new StringBuilder();
        textoSalvar.append(unirStringParaSalvar(espacoCafe.getNomeEspaco(), String.valueOf(espacoCafe.getLotacao())));
        FileHandler.adicionarAoArquivo(arquivo, textoSalvar.toString());
    }

    public static void adicionarSalaTreinamento(File arquivo, EspacoTreinamento salaTreinamento){
        StringBuilder textoSalvar = new StringBuilder();
        textoSalvar.append(unirStringParaSalvar(salaTreinamento.getNomeEspaco(), String.valueOf(salaTreinamento.getLotacao())));
        FileHandler.adicionarAoArquivo(arquivo, textoSalvar.toString());
    }

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

    public static String[][] gerarStrings(List<EspacoCafe> espacoCafeList, EspacoCafe dummy){
        String[][] retorno = new String[espacoCafeList.size()][1];
        for (int i = 0; i < espacoCafeList.size(); i++) {
            retorno[i][0] = espacoCafeList.get(i).getNomeEspaco();
            retorno[i][1] = String.valueOf(espacoCafeList.get(i).getLotacao());
        }
        return retorno;
    }

    public static ObservableList<EspacoCafe> gerarEspacosCafe(String[][] espacosStrings) {
        ObservableList<EspacoCafe> retorno = FXCollections.observableArrayList();
        for (String[] strings : espacosStrings) {
            if(strings[1]==null) {
                break;
                //strings[1] = String.valueOf(0);
            }
            retorno.add(new EspacoCafe(strings[0], Integer.parseInt(strings[1])));
        }
        return retorno;
    }

    public static String[][] gerarStrings(List<EspacoTreinamento> espacoTreinamentoList, EspacoTreinamento dummy){
        String[][] retorno = new String[espacoTreinamentoList.size()][1];
        for (int i = 0; i < espacoTreinamentoList.size(); i++) {
            retorno[i][0] = espacoTreinamentoList.get(i).getNomeEspaco();
            retorno[i][1] = String.valueOf(espacoTreinamentoList.get(i).getLotacao());
        }
        return retorno;
    }

    public static ObservableList<EspacoTreinamento> gerarSalasTreinamento(String[][] salasStrings) {
        ObservableList<EspacoTreinamento> retorno = FXCollections.observableArrayList();
        for (String[] strings : salasStrings) {
            if(strings[1] == null) {
                break;
            }
            retorno.add(new EspacoTreinamento(strings[0], Integer.parseInt(strings[1])));
        }
        return retorno;
    }
}