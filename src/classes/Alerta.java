package classes;

/**
 * Classe capaz de emitir alertas e erros
 */
public class Alerta {

    public static void novoErro(String titulo, String erro){
        System.err.println(titulo + ": " + erro);
    }

    public static void novoAlerta(String titulo, String alerta){
        System.err.println(titulo + ": " + alerta);
    }
}
