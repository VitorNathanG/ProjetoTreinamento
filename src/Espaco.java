import java.util.ArrayList;

public class Espaco {
    private String nomeEspaco;
    private ArrayList<Pessoa>[] integrantesEspacoPrimeiraEtapa = new ArrayList();

    public Espaco(String nomeEspaco) {
        this.setNomeEspaco(nomeEspaco);
        System.out.println("Novo espaço criado com nome \"" + nomeEspaco + "\"");
    }

    public ArrayList<Pessoa> getIntegrantesEspaco() {
        return integrantesEspacoPrimeiraEtapa;
    }

    public String getNomeEspaco() {
        return nomeEspaco;
    }

    public void setNomeEspaco(String nomeEspaco) {
        this.nomeEspaco = nomeEspaco;
    }

    public void setIntegrantesEspaco(ArrayList<Pessoa> integrantesEspaco) {
        this.integrantesEspacoPrimeiraEtapa = integrantesEspaco;
    }

    public void adicionarIntegrantes(Pessoa... novosIntegrantes){
        try {
            for (Pessoa pessoa : novosIntegrantes) {
                this.getIntegrantesEspaco().add(pessoa);
                System.out.println("O integrante " + pessoa.getNome() + " foi adicionado ao espaço \"" + this.getNomeEspaco() + "\"");
            }
        } catch (Exception e) {
            AvisoDeErro.executar(e.toString());
        }
        
    }

    public void removerIntegrantes(Pessoa integrante){
        try {
            this.getIntegrantesEspaco().remove(integrante);
        } catch (Exception e) {
            AvisoDeErro.executar(e.toString());
        }
        
    }

}
