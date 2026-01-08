import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) {

        // Lista com todas as linhas armazenadas
        List<String> lines = new ArrayList<>();

        try {

            String filename = "input.txt";
            BufferedReader br = new BufferedReader(new FileReader(filename));
            
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            br.close();
        
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Contador de splitters
        int count = 0;

        // Iterando para cada linha
        for (int i = 1; i < lines.size(); i++) {

            String current = lines.get(i);
            String previous = lines.get(i-1);
            StringBuilder sb = new StringBuilder(current);

            for (int j = 0; j < current.length(); j++) {

                // Verifica se é um spliter
                if (current.charAt(j) == '^' && previous.charAt(j) == '|') {
                    sb.setCharAt(j-1, '|');
                    sb.setCharAt(j+1, '|');
                    count++;
                }

                // Verifica se na linha anterior tem 'S' ou '|'
                else if (previous.charAt(j) == 'S' || previous.charAt(j) == '|') {
                    sb.setCharAt(j, '|');
                }
            }

                // Converte o StringBuilder de volta para string
                String modifiedLine = sb.toString();

                // Atualiza a linha na lista
                lines.set(i, modifiedLine);

                // Imprime como ficou a linha atual
                System.out.println(lines.get(i));
        }

        System.out.println("Spliters: " + count);
    }
}
