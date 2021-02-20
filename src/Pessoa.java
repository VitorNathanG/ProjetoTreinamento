import java.io.File;

public class Pessoa {

    private String nome;
    private String espacoPrimeiraEtapa;
    private String espacoCafePrimeiraEtapa;
    private String espacoCafeSegundaEtapa;
    private String espacoSegundaEtapa;

    public Pessoa(String nome) {
        this.setNome(nome);
    }

    public Pessoa(String nome, String espacoPrimeiraEtapa, String espacoSegundaEtapa, 
                  String espacoCafePrimeiraEtapa, String espacoCafeSegundaEtapa){
        this.setNome(nome);
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

    @Override
    public String toString(){
        return this.getNome() + ": " + FileHandler.ENTER +
                this.getEspacoPrimeiraEtapa() + FileHandler.ENTER +
                this.getEspacoSegundaEtapa() + FileHandler.ENTER +
                this.getEspacoCafePrimeiraEtapa() + FileHandler.ENTER +
                this.getEspacoCafeSegundaEtapa();
    }
}