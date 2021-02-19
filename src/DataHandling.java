import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DataHandling {

    public static ArrayList<Pessoa> recuperarDadosSalvos(File arquivo){
        
        
        ArrayList<Pessoa> retorno = new ArrayList();
        String linhaAtual;
        try (Scanner leitorTexto = new Scanner(FileHandling.lerArquivo(arquivo))){
            while (leitorTexto.hasNextLine()){
                linhaAtual = leitorTexto.nextLine();
                String[] info = {"", "", "", "", ""};
                char[] letras = new char[linhaAtual.length()];
                linhaAtual.getChars(0, linhaAtual.length(), letras, 0);
                int i = 0;
                for (char letra : letras) {
                    if (letra != '$') {
                        if(letra != 0){
                            info[i] += letra;
                        }
                    } else {
                        i += 1;
                    }
                
                }
                for (String c : info) {
                    System.out.println(c);
                }
                retorno.add(new Pessoa(info[0], info[1], info[2], info[3], info[4]));
            }
        }
        return retorno;
    }
}