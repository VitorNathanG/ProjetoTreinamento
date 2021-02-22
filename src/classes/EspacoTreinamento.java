package classes;

public class EspacoTreinamento extends Espaco{

    private static int lotacao;

    public EspacoTreinamento(String nomeEspaco, int lotacao) {
        super(nomeEspaco);
        EspacoTreinamento.setLotacao(lotacao);
    }

    public EspacoTreinamento() {}

    public static int getLotacao() {
        return lotacao;
    }

    public static void setLotacao(int lotacao) {
        EspacoTreinamento.lotacao = lotacao;
    }
}
