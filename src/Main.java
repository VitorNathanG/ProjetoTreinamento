import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String args[]){

        File infoPessoas = FileHandling.criarArquivo("dados\\InfoPessoas.data");

        System.out.println(FileHandling.lerArquivo(infoPessoas));
        ArrayList<Pessoa> pessoas = DataHandling.recuperarDadosSalvos(infoPessoas);
        //System.out.println(pessoas.get(0).toString());
        
        /*
        for (int i = 0; i < 10000; i++) {
            FileHandling.adicionarAoArquivo(infoPessoas, "Vitor$Sala 2$Sala 3$Cafeteria$Cantina");
        }
        */
        
        /*
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
        */
        Espaco cafe = new Espaco("Cafeteria");
        //cafe.adicionarIntegrantesPrimeiraEtapa(vitor, marcos, joao, pedro, aline, nicolas, bernardo, gabriela, luciano, antonio, paula, kauan);
    }
}
