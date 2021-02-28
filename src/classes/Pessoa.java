package classes;


/**
 * Classe Pessoa: modela um indivíduo que participará do treinamento, com suas informações relevantes para o propósito.
 * A adição de novos atributos para essa classe é relativamente simples. Deve-se:
 *
 *  1 - Declará-los nesta classe;
 *  2 - Adicionar as funcionalidades de salvamento desses dados na classe DataHandler, similarmente aos outros dados;
 *  3 - Adicionar um método de edição e exibição destes dados;
 *
 * @author Vitor Nathan Gonçalves
 */
public class Pessoa {

    //Definição dos atributos relevantes de uma pessoa, para o software
    private String nome;
    private String sobrenome;
    private String espacoPrimeiraEtapa;
    private String espacoCafePrimeiraEtapa;
    private String espacoCafeSegundaEtapa;
    private String espacoSegundaEtapa;
    private String nomeCompleto;

    /**
     * Construtor vazio, para uso exclusivo dos dummies de salvamento da classe DataHandler
     */
    public Pessoa(){}

    /**
     * Construtor com nome e sobrenome, para uso no método adicionarParticipanteBotaoClicked() da classe
     * PrincipalController.
     * @param nome o nome da nova Pessoa.
     * @param sobrenome o sobrenome da nova Pessoa.
     */
    public Pessoa(String nome, String sobrenome) {
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.nomeCompleto = this.getNome() + " " + this.getSobrenome();
    }

    /**
     * Construtor completo, usado para recriar as pessoas que foram salvas em forma de texto nos arquivos de salvamento
     * do programa
     * @param nome O nome da pessoa.
     * @param sobrenome O último nome da pessoa.
     * @param espacoPrimeiraEtapa  O espaço onde o indivíduo irá participar da primeira etapa do treinamento.
     * @param espacoSegundaEtapa  O espaço onde o indivíduo irá participar da segunda etapa do treinamento.
     * @param espacoCafePrimeiraEtapa  O espaço onde o indivíduo fará o intervalo de café na primeira etapa de treinamento.
     * @param espacoCafeSegundaEtapa  O espaço onde o indivíduo fará o intervalo de café na segunda etapa de treinamento.
     */
    public Pessoa(String nome, String sobrenome, String espacoPrimeiraEtapa, String espacoSegundaEtapa,
                  String espacoCafePrimeiraEtapa, String espacoCafeSegundaEtapa){
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.nomeCompleto = this.getNome() + " " + this.getSobrenome();
        this.setEspacoCafePrimeiraEtapa(espacoCafePrimeiraEtapa);
        this.setEspacoCafeSegundaEtapa(espacoCafeSegundaEtapa);
        this.setEspacoPrimeiraEtapa(espacoPrimeiraEtapa);
        this.setEspacoSegundaEtapa(espacoSegundaEtapa);

    }

    /**
     * Getter do atributo espacoCafeSegundaEtapa
     * @return String espacoCafeSegundaEtapa.
     */
    public String getEspacoCafeSegundaEtapa() {
        return espacoCafeSegundaEtapa;
    }

    /**
     * Setter do atributo espacoCafeSegundaEtapa
     * @param espacoCafeSegundaEtapa
     */
    public void setEspacoCafeSegundaEtapa(String espacoCafeSegundaEtapa) {
        this.espacoCafeSegundaEtapa = espacoCafeSegundaEtapa;
    }

    /**
     * Getter do atributo espacoCafePrimeiraEtapa
     * @return String espacoCafePrimeiraEtapa.
     */
    public String getEspacoCafePrimeiraEtapa() {
        return espacoCafePrimeiraEtapa;
    }

    /**
     * Setter do atributo espacoCafePrimeiraEtapa
     * @param espacoCafePrimeiraEtapa
     */
    public void setEspacoCafePrimeiraEtapa(String espacoCafePrimeiraEtapa) {
        this.espacoCafePrimeiraEtapa = espacoCafePrimeiraEtapa;
    }

    /**
     * Getter do atributo espacoSegundaEtapa
     * @return String espacoSegundaEtapa.
     */
    public String getEspacoSegundaEtapa() {
        return espacoSegundaEtapa;
    }

    /**
     * Setter do atributo espacoSegundaEtapa
     * @param espacoSegundaEtapa
     */
    public void setEspacoSegundaEtapa(String espacoSegundaEtapa) {
        this.espacoSegundaEtapa = espacoSegundaEtapa;
    }

    /**
     * Getter do atributo espacoPrimeiraEtapa
     * @return String espacoPrimeiraEtapa.
     */
    public String getEspacoPrimeiraEtapa() {
        return espacoPrimeiraEtapa;
    }

    /**
     * Setter do atributo espacoPrimeiraEtapa
     * @param espacoPrimeiraEtapa
     */
    public void setEspacoPrimeiraEtapa(String espacoPrimeiraEtapa) {
        this.espacoPrimeiraEtapa = espacoPrimeiraEtapa;
    }

    /**
     * Getter do atributo nome
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setter do atributo nome
     * @param nome nome da pessoa
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Getter para o atributo sobrenome
     * @return String sobrenome
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     * Setter para o atributo sobrenome
     * @param sobrenome
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    /**
     * Método estático usado para separar o sobrenome da pessoa do restante do nome. Retorna o nome completo
     * sem o último nome.
     * @param nomeCompleto o nome completo da pessoa.
     * @return String nome - o nome completo da pessoa sem o último nome.
     */
    public static String separarNome(String nomeCompleto) {
        StringBuilder sobrenome = new StringBuilder(nomeCompleto);
        StringBuilder primeiroNome = new StringBuilder();
        StringBuilder sobrenomeOutput = new StringBuilder();

        //Reverte o nome completo da pessoa, para que seja lido de trás para frente
        sobrenome.reverse();

        //Checa cada letra até achar um espaço, o que define o fim do sobrenome
        for (int i = 0; i < sobrenome.length(); i++) {
            if (" ".equals(Character.toString(sobrenome.charAt(i)))) {
                break;
            } else {
                sobrenomeOutput.append(sobrenome.charAt(i));
            }
        }
        //Retorna o nome da pessoa para o sentido convencional de leitura
        sobrenomeOutput.reverse();

        //Remove o sobrenome do nome completo da pessoa, incluindo o espaço
        primeiroNome.append(nomeCompleto.replaceFirst(" " + sobrenomeOutput.toString(), ""));

        return primeiroNome.toString();
    }

    /**
     * Método estático usado para separar o sobrenome da pessoa do restante do nome. Retorna apenas o sobrenome da pessoa.
     * @param nomeCompleto o nome completo da pessoa.
     * @return String sobrenome - apenas o último nome da pessoa.
     */
    public static String separarSobrenome(String nomeCompleto) {
        StringBuilder sobrenome = new StringBuilder(nomeCompleto);
        StringBuilder sobrenomeOutput = new StringBuilder();

        //Reverte o nome completo da pessoa, para que seja lido de trás para frente
        sobrenome.reverse();

        //Checa cada letra até achar um espaço, o que define o fim do sobrenome
        for (int i = 0; i < sobrenome.length(); i++) {
            if (" ".equals(Character.toString(sobrenome.charAt(i)))) {
                break;
            } else {
                sobrenomeOutput.append(sobrenome.charAt(i));
            }
        }

        //Retorna o nome da pessoa para o sentido convencional de leitura
        sobrenomeOutput.reverse();

        return sobrenomeOutput.toString();
    }

    /**
     * Método toString da classe Pessoa, usado principalmente para debug
     * @return String this.toString, com todos os atributos da pessoa
     */
    @Override
    public String toString(){
        return this.getNome() + " " + this.getSobrenome() + ": " + FileHandler.ENTER +
                this.getEspacoPrimeiraEtapa() + FileHandler.ENTER +
                this.getEspacoSegundaEtapa() + FileHandler.ENTER +
                this.getEspacoCafePrimeiraEtapa() + FileHandler.ENTER +
                this.getEspacoCafeSegundaEtapa() + FileHandler.ENTER;
    }

    /**
     * Getter para o atributo nomeCompleto
     * @return String nomeCompleto
     */
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    /**
     * Setter para o atributo nome completo
     * @param nomeCompleto
     */
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
}