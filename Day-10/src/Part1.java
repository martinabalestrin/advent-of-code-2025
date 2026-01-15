import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) {

        List<String> indicatorsFinal = new ArrayList<>();
        List<List<String>> buttons = new ArrayList<>(); // salvar como string e transformar para int dps com split.(",")

        try {

            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line;

            while ((line = br.readLine()) != null) {

                // Armazena o indicador
                int bracketIndex = line.indexOf("]");
                indicatorsFinal.add(line.substring(0, bracketIndex+1));

                List<String> buttonsLine = new ArrayList<>();

                // Armazena os botões
                for (int i = bracketIndex + 1; i < line.length(); i++) {
                    if (line.charAt(i) == '(') {

                        i++;
                        String buttonStr = "";

                        while (line.charAt(i) != ')') {
                            buttonStr += line.charAt(i);
                            i++;
                        }

                        buttonsLine.add(buttonStr);
                    }
                }
            
                buttons.add(buttonsLine);

                // Até aqui, está tudo armazenado corretamente

                // Part 2: Fazer leitura das chaves. Não é necessário para a parte 1!
            }

            br.close();
        
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // lógica de resolução
    }
}
