package classes;

public class EspacoCafe extends Espaco {

    private static int lotacao;
    public EspacoCafe(String nomeEspaco) {
        super(nomeEspaco);
    }

    public EspacoCafe() {
        super();
    }

    public static int getLotacao() {
        return lotacao;
    }

    public static void setLotacao(int lotacao) {
        EspacoCafe.lotacao = lotacao;
    }
}
