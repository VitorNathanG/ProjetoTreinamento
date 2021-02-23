package classes;

public class EspacoTreinamento extends Espaco{

    private int lotacao;

    public EspacoTreinamento(String nomeEspaco, int lotacao) {
        super(nomeEspaco);
        this.setLotacao(lotacao);
    }

    public EspacoTreinamento() {}

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }
}
