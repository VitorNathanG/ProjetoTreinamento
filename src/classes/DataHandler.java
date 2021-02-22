package classes;

import java.io.File;
import java.util.ArrayList;
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
        String[][] retorno = new String[FileHandler.contLinhas(arquivo)][5];
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

    public static void salvarDados(File arquivo, List<Pessoa> pessoas){
        StringBuilder textoSalvar = new StringBuilder();
        for (Pessoa pessoa : pessoas) {
            textoSalvar.append(FileHandler.ENTER).append(unirStringParaSalvar(pessoa.getNome(), pessoa.getEspacoPrimeiraEtapa(), pessoa.getEspacoSegundaEtapa(),
            pessoa.getEspacoCafePrimeiraEtapa(), pessoa.getEspacoCafeSegundaEtapa()));
        }
        
        FileHandler.escreverArquivo(arquivo , textoSalvar.toString());
        FileHandler.removerLinhas(arquivo, 1);
    }

    public static void adicionarPessoa(File arquivo, Pessoa pessoa){
        StringBuilder textoSalvar = new StringBuilder();
        textoSalvar.append(unirStringParaSalvar(pessoa.getNome(), pessoa.getEspacoPrimeiraEtapa(), pessoa.getEspacoSegundaEtapa(),
                                                pessoa.getEspacoCafePrimeiraEtapa(), pessoa.getEspacoCafeSegundaEtapa()));
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

    public static String[][] gerarStrings(List<Pessoa> pessoasList){
        String[][] retorno = new String[pessoasList.size()][5];
        for (int i = 0; i < pessoasList.size(); i++) {
            retorno[i][0] = pessoasList.get(i).getNome();
            retorno[i][1] = pessoasList.get(i).getEspacoPrimeiraEtapa();
            retorno[i][2] = pessoasList.get(i).getEspacoSegundaEtapa();
            retorno[i][3] = pessoasList.get(i).getEspacoCafePrimeiraEtapa();
            retorno[i][4] = pessoasList.get(i).getEspacoCafeSegundaEtapa();
        }
        return retorno;
    }

    public static List<Pessoa> gerarPessoas(String[][] pessoasStrings) {
        List<Pessoa> retorno = new ArrayList<>();
        for (String[] strings : pessoasStrings) {
            retorno.add(new Pessoa(strings[0], strings[1], strings[2], strings[3], strings[4]));
        }
        return retorno;
    }

    private DataHandler(){}
}