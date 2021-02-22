package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.nio.file.Files;

/**
 * Diversos métodos para manipular linhas em um arquivo de texto qualquer.
 * Permite adicionar uma linha ao fim do documento, remover linhas, ler um 
 * arquivo e sobrescrever um arquivo.
 * 
 * @author Vitor Nathan Gonçalves
 * @version 1.0
 */
public class FileHandler {
    
    //Declaração do line separator, para possibilitar compatibilidade entre S.O.
    public static final String ENTER = System.getProperty("line.separator");

    /**
     * Caso não exista, cria um novo arquivo no endereço especificado.
     * Se já existir, não faz nada
     * @param endereco endereço do arquivo no sistema
     * @return File, instanciado corretamente
     */
    public static File criarArquivo(String endereco) {
        File arquivo = new File(endereco);
        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arquivo;
    }

    /**
     * Lê um arquivo completo e retorna-o em forma de String
     * @param arquivo referência ao arquivo-destino
     * @return String com o texto completo do arquivo
     */
    public static String lerArquivo(File arquivo){
        try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){
            return new String(Files.readAllBytes(arquivo.toPath()));
        } catch (Exception e){
            Alerta.novoErro(e.getClass().toString(), "exceção no método FileHandling.lerArquivo(). Arquivo não encontrado");
            return "";
        }
    }

    /**
     * Adiciona uma nova sequência de String... ao fim do arquivo, uma string por linha
     * Não permite a adição de linhas em branco
     * @param arquivo referência ao arquivo-destino
     * @param texto String... à serem adicionadas
     */
    public static void adicionarAoArquivo(File arquivo, String... texto) {
        try  (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(arquivo, true)))) {
            for (String string : texto) {
                if (string.isBlank()){
                    continue;
                }
                out.write(ENTER + string);
            }
        } catch (Exception e) {
            Alerta.novoErro(e.getClass().toString(), "exceção no método FileHandling.adicionarAoArquivo(). Arquivo inválido.");
        } 
    }

    /**
     * CUIDADO: Sobrescreve o arquivo de destino com o texto contido na String
     * @param arquivo referência ao arquivo-destino
     * @param texto Texto completo a ser adicionado
     */
    public static void escreverArquivo(File arquivo, String texto) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(arquivo, false)))){
            out.write(texto);
        } catch (Exception e) {
            Alerta.novoErro(e.getClass().toString(), "exceção no método FileHandling.escreverArquivo(). Arquivo inválido.");
        }
    }

    /**
     * Retorna o total de linhas em um arquivo de texto
     * @param arquivo referência ao arquivo-destino
     * @return int total de linhas no arquivo (incluindo linhas em branco)
     */
    public static int contLinhas(File arquivo){
        try (FileReader x = new FileReader(arquivo)){
            LineNumberReader linhaLeitura = new LineNumberReader(x);
            linhaLeitura.skip(arquivo.length());
            int qtdLinha = linhaLeitura.getLineNumber();
            linhaLeitura.close();
            return qtdLinha + 1;
        } catch (Exception e) {
            Alerta.novoErro("IOException", "removerDoArquivo em FileHandling. O arquivo passado para contLinhas não existe.");
            return 0;
        }
    }
    
    /**
     * Remove n-ésima linha de um arquivo 
     * @param arquivo referência ao arquivo-destino
     * @param indexLinha número da linha a ser removida
     * @throws IndexOutOfBoundsException
     */
    public static void removerLinhas(File arquivo, int indexLinha) throws IndexOutOfBoundsException {
        BufferedReader br;
        
        try (FileReader fr = new FileReader(arquivo)) {
            // Inicializa as instâncias necessárias
            br = new BufferedReader(fr);
            String novoTextoArquivo = "";
            int qtdLinha= contLinhas(arquivo);

            //String que armazena a próxima linha a ser adicionada ao novo arquivo
            String apendice;

            if (indexLinha == 1) { //Caso a linha removida seja a primeira linha do arquivo, para evitar erros
                br.readLine();
                novoTextoArquivo += br.readLine();
                for (int i = 2; i < qtdLinha; i++) {
                    novoTextoArquivo += ENTER + br.readLine();
                } 
            
            } else {
                novoTextoArquivo += br.readLine();
                for (int i = 1; i < qtdLinha; i++) {
                    apendice = br.readLine();
                    if (i == indexLinha - 1 || apendice == null) {
                        
                    } else {
                        novoTextoArquivo += ENTER + apendice;
                    }
                }
            }
            escreverArquivo(arquivo, novoTextoArquivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private FileHandler(){}
}
