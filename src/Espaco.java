import java.util.ArrayList;

public class Espaco {
    private String nomeEspaco;
    private ArrayList<Pessoa> integrantesPrimeiraEtapa = new ArrayList();
    private ArrayList<Pessoa> integrantesSegundaEtapa = new ArrayList();

    public Espaco(String nomeEspaco) {
        this.setNomeEspaco(nomeEspaco);
        System.out.println("Novo espaço criado com nome \"" + nomeEspaco + "\"");
    }

    public ArrayList<Pessoa> getIntegrantesSegundaEtapa() {
        return integrantesSegundaEtapa;
    }

    public void setIntegrantesSegundaEtapa(ArrayList<Pessoa> integrantesSegundaEtapa) {
        this.integrantesSegundaEtapa = integrantesSegundaEtapa;
    }

    public ArrayList<Pessoa> getIntegrantesPrimeiraEtapa() {
        return integrantesPrimeiraEtapa;
    }

    public void setIntegrantesPrimeiraEtapa(ArrayList<Pessoa> integrantesPrimeiraEtapa) {
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
}
