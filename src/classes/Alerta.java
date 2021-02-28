package classes;

/**
 * Classe capaz de emitir erros
 *
 * @author Vitor Nathan Gonçalves
 */
public class Alerta {
    //Emite um erro no terminal
    public static void novoErro(String titulo, String erro){
        System.err.println(titulo + ": " + erro);
    }

}
