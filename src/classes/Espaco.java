package classes;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe Espaco: modela um espaço físico onde é possível ocorrer um evento. Os atributos dessa classe são relacionados
 * aos participantes que farão parte do evento.
 * O evento é dividido em duas partes, como especificado no documento inicial.
 *
 * @author Vitor Nathan Gonçalves
 */
public class Espaco {
    //Definição dos atributos da classe
    private String nomeEspaco;
    private List<Pessoa> integrantesPrimeiraEtapa = new ArrayList<>();
    private List<Pessoa> integrantesSegundaEtapa = new ArrayList<>();
    private int lotacao;
    private int ocupacaoPrimeiraEtapa;
    private int ocupacaoSegundaEtapa;

    /**
     * Construtor da classe espaço, utilizado livremente. Recebe o nome do espaço e a
     * lotação máxima permitida para o mesmo.
     * @param nomeEspaco
     * @param lotacao
     */
    public Espaco(String nomeEspaco, int lotacao) {
        this.setNomeEspaco(nomeEspaco);
        this.setLotacao(lotacao);
    }

    /**
     * Construtor vazio, para ser utilizado pelos Dummies de salvamento
     */
    public Espaco() {}

    /**
     * Getter para o atributo integrantesSegundaEtapa
     * @return integrantesSegundaEtapa
     */
    public List<Pessoa> getIntegrantesSegundaEtapa() {
        return integrantesSegundaEtapa;
    }

    /**
     * Setter para o atributo integrantesSegundaEtapa
     * @param integrantesSegundaEtapa
     */
    public void setIntegrantesSegundaEtapa(List<Pessoa> integrantesSegundaEtapa) {
        this.integrantesSegundaEtapa = integrantesSegundaEtapa;
    }

    /**
     * Getter para o atributo integrantesPrimeiraEtapa
     * @return integrantesPrimeiraEtapa
     */
    public List<Pessoa> getIntegrantesPrimeiraEtapa() {
        return integrantesPrimeiraEtapa;
    }

    /**
     * Setter para o atributoIntegrantesPrimeiraEtapa
     * @param integrantesPrimeiraEtapa
     */
    public void setIntegrantesPrimeiraEtapa(List<Pessoa> integrantesPrimeiraEtapa) {
        this.integrantesPrimeiraEtapa = integrantesPrimeiraEtapa;
    }

    /**
     * Getter para o atributo nomeEspaco
     * @return nomeEspaco
     */
    public String getNomeEspaco() {
        return nomeEspaco;
    }

    /**
     * Setter para o atributo nomeEspaco
     * @param nomeEspaco
     */
    public void setNomeEspaco(String nomeEspaco) {
        this.nomeEspaco = nomeEspaco;
    }

    /**
     * Getter para o atributo lotacao
     * @return lotacao
     */
    public int getLotacao() {
        return lotacao;
    }

    /**
     * Setter para o atributo lotacao
     * @param lotacao
     */
    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    /**
     * Getter para o atributo ocupacaoPrimeiraEtapa
     * @return ocupacaoPrimeiraEtapa
     */
    public int getOcupacaoPrimeiraEtapa() {
        atualizarOcupacaoPrimeiraEtapa();
        return ocupacaoPrimeiraEtapa;
    }

    /**
     * Getter para o atributo ocupacaoSegundaEtapa
     * @return ocupacaoSegundaEtapa
     */
    public int getOcupacaoSegundaEtapa() {
        atualizarOcupacaoSegundaEtapa();
        return ocupacaoSegundaEtapa;
    }

    /**
     * Adiciona novos participantes à primeira etapa que ocorre no espaço
     * @param novosIntegrantes contendo os novos integrantes
     */
    public void adicionarIntegrantesPrimeiraEtapa(Pessoa... novosIntegrantes) {
        for (Pessoa pessoa : novosIntegrantes) {
            this.getIntegrantesPrimeiraEtapa().add(pessoa);
        }
    }

    /**
     * Remove um participante da primeira etapa que ocorre no espaço
     * @param integrante contendo o integrante a ser removido
     */
    public void removerIntegrantePrimeiraEtapa(Pessoa integrante){
        this.getIntegrantesPrimeiraEtapa().remove(integrante);
    }

    /**
     * Adiciona novos participantes à primeira etapa que ocorre no espaço
     * @param novosIntegrantes contendo os novos integrantes
     */
    public void adicionarIntegrantesSegundaEtapa(Pessoa... novosIntegrantes) {
        for (Pessoa pessoa : novosIntegrantes) {
            this.getIntegrantesSegundaEtapa().add(pessoa);
        }
    }

    /**
     * Remove um integrante da segunda etapa que ocorre no espaço
     * @param integrante contendo o integrante a ser removido
     */
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


    /**
     * Atualiza o valor de ocupacaoPrimeiraEtapa
     */
    private void atualizarOcupacaoPrimeiraEtapa(){
        ocupacaoPrimeiraEtapa = getIntegrantesPrimeiraEtapa().size();
    }

    /**
     * Atualiza o valor de ocupacaoSegundaEtapa
     */
    private void atualizarOcupacaoSegundaEtapa(){
        ocupacaoSegundaEtapa = getIntegrantesSegundaEtapa().size();
    }
}
