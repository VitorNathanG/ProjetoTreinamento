package classes;

public class Pessoa {

    private String nome;
    private String sobrenome;
    private String espacoPrimeiraEtapa;
    private String espacoCafePrimeiraEtapa;
    private String espacoCafeSegundaEtapa;
    private String espacoSegundaEtapa;

    public Pessoa(){}

    public Pessoa(String nome, String sobrenome, String espacoPrimeiraEtapa, String espacoSegundaEtapa,
                  String espacoCafePrimeiraEtapa, String espacoCafeSegundaEtapa){
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setEspacoCafePrimeiraEtapa(espacoCafePrimeiraEtapa);
        this.setEspacoCafeSegundaEtapa(espacoCafeSegundaEtapa);
        this.setEspacoPrimeiraEtapa(espacoPrimeiraEtapa);
        this.setEspacoSegundaEtapa(espacoSegundaEtapa);

    }

    public String getEspacoCafeSegundaEtapa() {
        return espacoCafeSegundaEtapa;
    }

    public void setEspacoCafeSegundaEtapa(String espacoCafeSegundaEtapa) {
        this.espacoCafeSegundaEtapa = espacoCafeSegundaEtapa;
    }

    public String getEspacoCafePrimeiraEtapa() {
        return espacoCafePrimeiraEtapa;
    }

    public void setEspacoCafePrimeiraEtapa(String espacoCafePrimeiraEtapa) {
        this.espacoCafePrimeiraEtapa = espacoCafePrimeiraEtapa;
    }

    public String getEspacoSegundaEtapa() {
        return espacoSegundaEtapa;
    }

    public void setEspacoSegundaEtapa(String espacoSegundaEtapa) {
        this.espacoSegundaEtapa = espacoSegundaEtapa;
    }

    public String getEspacoPrimeiraEtapa() {
        return espacoPrimeiraEtapa;
    }

    public void setEspacoPrimeiraEtapa(String espacoPrimeiraEtapa) {
        this.espacoPrimeiraEtapa = espacoPrimeiraEtapa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;

    }

    public static String separarNome(String nomeCompleto) {
        StringBuilder sobrenome = new StringBuilder(nomeCompleto);
        StringBuilder primeiroNome = new StringBuilder();
        StringBuilder sobrenomeOutput = new StringBuilder();
        sobrenome.reverse();
        for (int i = 0; i < sobrenome.length(); i++) {
            if (" ".equals(Character.toString(sobrenome.charAt(i)))) {
                break;
            } else {
                sobrenomeOutput.append(sobrenome.charAt(i));
            }
        }
        sobrenomeOutput.reverse();
        primeiroNome.append(nomeCompleto.replaceFirst(" " + sobrenomeOutput.toString(), ""));
        System.out.println(primeiroNome.toString());
        return primeiroNome.toString();
    }

    public static String separarSobrenome(String nomeCompleto) {
        StringBuilder sobrenome = new StringBuilder(nomeCompleto);
        StringBuilder sobrenomeOutput = new StringBuilder();
        sobrenome.reverse();
        for (int i = 0; i < sobrenome.length(); i++) {
            if (" ".equals(Character.toString(sobrenome.charAt(i)))) {
                break;
            } else {
                sobrenomeOutput.append(sobrenome.charAt(i));
            }
        }
        sobrenomeOutput.reverse();
        System.out.println(sobrenomeOutput.toString());
        return sobrenomeOutput.toString();
    }

    @Override
    public String toString(){
        return this.getNome() + ": " + FileHandler.ENTER +
                this.getSobrenome() + ": " + FileHandler.ENTER +
                this.getEspacoPrimeiraEtapa() + FileHandler.ENTER +
                this.getEspacoSegundaEtapa() + FileHandler.ENTER +
                this.getEspacoCafePrimeiraEtapa() + FileHandler.ENTER +
                this.getEspacoCafeSegundaEtapa();
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}