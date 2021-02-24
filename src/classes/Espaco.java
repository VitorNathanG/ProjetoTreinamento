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
            pessoa.setEspacoPrimeiraEtapa(this.getNomeEspaco());

            System.out.println("O integrante " + pessoa.getNome() + 
            " foi adicionado ao espaço \"" + this.getNomeEspaco() + "\" na primeira etapa");
        }        
    }

    public void removerIntegrantesPrimeiraEtapa(Pessoa integrante){
        this.getIntegrantesPrimeiraEtapa().remove(integrante);   
        integrante.setEspacoPrimeiraEtapa(null);     
    }

    public void adicionarIntegrantesSegundaEtapa(Pessoa... novosIntegrantes) {
        for (Pessoa pessoa : novosIntegrantes) {
            this.getIntegrantesSegundaEtapa().add(pessoa);
            pessoa.setEspacoSegundaEtapa(this.getNomeEspaco());
            System.out.println("O integrante " + pessoa.getNome() + 
            " foi adicionado ao espaço \"" + this.getNomeEspaco() + "\" na segunda etapa");
        }        
    }

    public void removerIntegrantesSegundaEtapa(Pessoa integrante){
        this.getIntegrantesSegundaEtapa().remove(integrante);        
        integrante.setEspacoSegundaEtapa(null);
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }
}
