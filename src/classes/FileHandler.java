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
 * Classe FileHandler: diversos métodos para manipular linhas em um arquivo de texto qualquer.
 * Permite adicionar uma linha ao fim do documento, remover linhas, ler um 
 * arquivo e sobrescrever um arquivo.
 *
 * O conteúdo desta classe não deve ser utilizado para situações de muita carga, visto que os métodos são pouco
 * otimizados para velocidade. Nesse caso, um banco de dados tradicional deve ser aplicado.
 * 
 * @author Vitor Nathan Gonçalves
 * @version 1.0
 */
public class FileHandler {
    
    //Declaração do line separator, para possibilitar compatibilidade entre S.O.
    public static final String ENTER = System.getProperty("line.separator");

    /**
     * Cria uma referência ao arquivo no endereço especificado.
     * Cria um novo arquivo no computador, caso não exista
     * @param endereco endereço do arquivo no sistema
     * @return File, instanciado corretamente
     */
    public static File criarArquivo(String endereco) {
        File arquivo = new File(endereco);
        try {
            if (!arquivo.exists()) {        //cria um novo arquivo caso ele não exista
                arquivo.createNewFile();
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arquivo;
    }

    /**
     * Lê um arquivo completo e retorna-o em uma única String
     * @param arquivo referência ao arquivo-destino
     * @return String com o texto completo do arquivo
     */
    public static String lerArquivo(File arquivo){
        try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){
            return new String(Files.readAllBytes(arquivo.toPath()));        //lê o arquivo inteiro
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
                if (string.isBlank()){          //ignora as linhas em branco da string
                    continue;
                }
                out.write(ENTER + string);   //adiciona a próxima linha
            }
        } catch (Exception e) { //Debug
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
        } catch (Exception e) { //Debug
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
            linhaLeitura.skip(arquivo.length());            //pula para a ultima linha do arquivo
            int qtdLinha = linhaLeitura.getLineNumber();    //recupera o número da última linha do arquivo
            linhaLeitura.close();
            return qtdLinha + 1;                            //retorna + 1, visto que a contagem de linhas começa em 0
        } catch (Exception e) {  //Debug
            Alerta.novoErro("IOException", "removerDoArquivo em FileHandling. O arquivo passado para contLinhas não existe.");
            return 0;
        }
    }
    
    /**
     * Remove n-ésima linha de um arquivo 
     * @param arquivo referência ao arquivo-destino
     * @param indexLinha número da linha a ser removida
     */
    public static void removerLinha(File arquivo, int indexLinha) {
        BufferedReader br;
        
        try (FileReader fr = new FileReader(arquivo)) {

            // Inicializa as instâncias necessárias
            br = new BufferedReader(fr);
            StringBuilder novoTextoArquivo = new StringBuilder();
            int qtdLinha= contLinhas(arquivo);

            //String que armazena a próxima linha a ser adicionada ao novo arquivo
            String apendice;

            if (indexLinha == 1) {      //Caso a linha removida seja a primeira linha do arquivo, para evitar erros
                br.readLine();
                novoTextoArquivo.append(br.readLine());
                for (int i = 2; i < qtdLinha; i++) {
                    novoTextoArquivo.append(ENTER).append(br.readLine());
                } 
            
            } else {
                novoTextoArquivo.append(br.readLine());
                for (int i = 1; i < qtdLinha; i++) {
                    apendice = br.readLine();
                    if (i == indexLinha - 1 || apendice == null) {
                        
                    } else {
                        novoTextoArquivo.append(ENTER).append(apendice);
                    }
                }
            }
            escreverArquivo(arquivo, novoTextoArquivo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Construtor privado para evitar instanciamento
     */
    private FileHandler(){}
}
