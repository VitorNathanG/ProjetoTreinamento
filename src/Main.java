public class Main {

    public static void main(String args[]){
        
        Pessoa vitor = new Pessoa("Vitor");
        Pessoa marcos = new Pessoa("Marcos");
        Pessoa joao = new Pessoa("João");
        Pessoa pedro = new Pessoa("Pedro");
        Pessoa aline = new Pessoa("Aline");
        Pessoa nicolas = new Pessoa("Nicolas");
        Pessoa bernardo = new Pessoa("Bernardo");
        Pessoa gabriela = new Pessoa("Gabriela");
        Pessoa luciano = new Pessoa("Luciano");
        Pessoa antonio = new Pessoa("Antonio");
        Pessoa paula = new Pessoa("Paula");
        Pessoa kauan = new Pessoa("Kauan");

        Espaco cafe = new Espaco("Cafeteria");
        cafe.adicionarIntegrantes(vitor, marcos, joao, pedro, aline, nicolas, bernardo, gabriela,
                                    luciano, antonio, paula, kauan);
        

    }
}
