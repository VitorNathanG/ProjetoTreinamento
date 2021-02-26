package classes;

import java.util.ArrayList;
import java.util.List;

public class Espaco {
    private String nomeEspaco;
    private List<Pessoa> integrantesPrimeiraEtapa = new ArrayList<>();
    private List<Pessoa> integrantesSegundaEtapa = new ArrayList<>();
    private int lotacao;

    public Espaco(String nomeEspaco, int lotacao) {
        this.setNomeEspaco(nomeEspaco);
        this.setLotacao(lotacao);
    }

    public Espaco() {}

    public List<Pessoa> getIntegrantesSegundaEtapa() {
        return integrantesSegundaEtapa;
    }

    public void setIntegrantesSegundaEtapa(List<Pessoa> integrantesSegundaEtapa) {
        this.integrantesSegundaEtapa = integrantesSegundaEtapa;
    }

    public List<Pessoa> getIntegrantesPrimeiraEtapa() {
        return integrantesPrimeiraEtapa;
    }

    public void setIntegrantesPrimeiraEtapa(List<Pessoa> integrantesPrimeiraEtapa) {
        this.integrantesPrimeiraEtapa = integrantesPrimeiraEtapa;
    }

    public String getNomeEspaco() {
        return nomeEspaco;
    }

    public void setNomeEspaco(String nomeEspaco) {
        this.nomeEspaco = nomeEspaco;
    }

    public void adicionarIntegrantesPrimeiraEtapa(Pessoa... novosIntegrantes) {
        for (Pessoa pessoa : novosIntegrantes) {
            this.getIntegrantesPrimeiraEtapa().add(pessoa);
        }
    }

    public void removerIntegrantesPrimeiraEtapa(Pessoa integrante){
        this.getIntegrantesPrimeiraEtapa().remove(integrante);   
    }

    public void adicionarIntegrantesSegundaEtapa(Pessoa... novosIntegrantes) {
        for (Pessoa pessoa : novosIntegrantes) {
            this.getIntegrantesSegundaEtapa().add(pessoa);
        }
    }

    public void adicionarIntegrantesSegundaEtapa(List<Pessoa> novosIntegrantes) {
        for (Pessoa pessoa : novosIntegrantes) {
            this.getIntegrantesSegundaEtapa().add(pessoa);
        }
    }

    public void removerIntegrantesSegundaEtapa(Pessoa integrante){
        ArrayList<Pessoa> novosIntegrantes = new ArrayList<>();
        for (Pessoa pessoa: this.getIntegrantesSegundaEtapa()) {
            if (pessoa.equals(integrante)){
                continue;
            }
            novosIntegrantes.add(pessoa);
        }
        this.setIntegrantesSegundaEtapa(novosIntegrantes);
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }
}
