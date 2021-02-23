package classes;

public class EspacoCafe extends Espaco {

    private int lotacao;
    public EspacoCafe(String nomeEspaco, int lotacao) {

        super(nomeEspaco);
        this.setLotacao(lotacao);
    }

    public EspacoCafe() {
        super();
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }
}
