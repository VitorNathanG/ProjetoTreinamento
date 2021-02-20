import java.io.File;
import java.util.List;

public class Main {    

    
    public static void main(String args[]){

        File infoPessoas = FileHandler.criarArquivo("dados\\InfoPessoas.data");
        File infoEspacosCafe = FileHandler.criarArquivo("dados\\InfoEspacosCafe.data");
        File infoSalasTreinamento = FileHandler.criarArquivo("dados\\InfoSalasTreinamento.data");
        
        List<Pessoa> pessoas = DataHandler.gerarPessoas(DataHandler.recuperarDadosSalvos(infoPessoas));

        /*
        List<EspacoCafe> espacoCafes = new ArrayList<>();
        List<EspacoEvento> espacoEventos = new ArrayList<>();
        */
        String[][] x = DataHandler.gerarStrings(pessoas);
        
        //DataHandling.adicionarPessoa(arquivo, new Pessoa("Vitor Nathan Gonçalves", "Sala #2", "Sala #1", "Cafeteria", "Praça de alimentação"));
        DataHandler.salvarDados(infoPessoas, pessoas);
        /*
        DataHandling.adicionarPessoa(arquivo, new Pessoa("Vitor Nathan Gonçalves", "Sala #2", "Sala #1", "Cafeteria", "Praça de alimentação"));
        DataHandling.adicionarPessoa(arquivo, new Pessoa("Elton Mask", "Sala #1", "Sala #3", "Praça de alimentação", "Cafeteria"));
        DataHandling.adicionarPessoa(arquivo, new Pessoa("William Doors", "Sala #2", "Sala #1", "Cafeteria", "Praça de alimentação"));
        DataHandling.adicionarPessoa(arquivo, new Pessoa("Abba Liver", "Sala #3", "Sala #1", "Praça de alimentação", "Cafeteria"));
        DataHandling.adicionarPessoa(arquivo, new Pessoa("Marb Zuckerfeld", "Sala #4", "Sala #1", "Praça de alimentação", "Cafeteria"));
        DataHandling.adicionarPessoa(arquivo, new Pessoa("Peter Grew", "Sala #3", "Sala #2", "Cafeteria", "Praça de alimentação"));
        DataHandling.adicionarPessoa(arquivo, new Pessoa("Antonius Maximus III", "Sala #1", "Sala #3", "Cafeteria", "Praça de alimentação"));
        DataHandling.adicionarPessoa(arquivo, new Pessoa("Eratóstenes", "Sala #2", "Sala #3", "Cafeteria", "Praça de alimentação"));
        DataHandling.adicionarPessoa(arquivo, new Pessoa("Leonardo da Vinqui", "Sala #3", "Sala #3", "Praça de alimentação", "Cafeteria"));
        DataHandling.adicionarPessoa(arquivo, new Pessoa("Jeffree Bezoar", "Sala #4", "Sala #4", "Cafeteria", "Praça de alimentação"));
        DataHandling.adicionarPessoa(arquivo, new Pessoa("Sun Tzu", "Sala #2", "Sala #4", "Cafeteria", "Praça de alimentação"));
        DataHandling.adicionarPessoa(arquivo, new Pessoa("Timas Berman Lars", "Sala #1", "Sala #4", "Praça de alimentação", "Cafeteria"));
        DataHandling.adicionarPessoa(arquivo, new Pessoa("Jonas Eggart", "Sala #1", "Sala #2", "Cafeteria", "Praça de alimentação"));
        DataHandling.adicionarPessoa(arquivo, new Pessoa("Israel Nelson", "Sala #1", "Sala #3", "Praça de alimentação", "Cafeteria"));
        */
    }
}
