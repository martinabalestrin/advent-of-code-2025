import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) {
        
        String filename = "input.txt";
        List<Long> availableIngredientsID = new ArrayList<>(); // Tamanho inicial não definido
        int count = 0;
    
        try {

            // Lê até a linha vazia e guarda os IDs
            BufferedReader br = new BufferedReader(new FileReader(filename));

            String line;
            boolean foundEmptyLine = false;

            while((line = br.readLine()) != null) {
                
                // Até localizar a linha vazia
                if(line.isEmpty()) {
                    System.out.println("Linha vazia encontrada: Começando a ler os IDs.");
                    foundEmptyLine = true;
                }

                // Adiciona os IDs dos ingredientes no vetor
                else if (foundEmptyLine) {
                    availableIngredientsID.add(Long.parseLong(line));
                }
            }

            // Fecha o leitor
            br.close();

            // Reabre o arquivo para ler os intervalos
            br = new BufferedReader(new FileReader(filename));
            List<Long> freshIngredientsID = new ArrayList<>();

            while((line = br.readLine()) != null) {
                
                // Até localizar a linha vazia
                if(line.isEmpty()) {
                    System.out.println("Linha vazia encontrada: Fim do Programa.");
                    System.out.println("Number of fresh ingredients: " + count);
                    br.close();
                    return;
                }

                else {
                    // Divide com split() o início e fim do intervalo
                    String[] interval = line.split("-");
                    long start = Long.parseLong(interval[0]);
                    long end = Long.parseLong(interval[1]);

                    // Para cada ID no vetor de ingredientes disponíveis
                    for(long id : availableIngredientsID) {
                        // Se o ID não estiver no vetor de ingredientes já contabilizados
                        // & estiver no intervalo (assim economiza memória e tempo)
                        if (id >= start && id <= end && !freshIngredientsID.contains(id)) {
                            // Adiciona no vetor de ingredientes fresh e soma ao contador
                            freshIngredientsID.add(id);
                            count++;

                            // DEBUG
                            // System.out.println(
                            // "MATCH -> ID: " + id +
                            // " | Intervalo atual: " + start + "-" + end +
                            // " | i = " + i
                            // );
                        }
                    }
                }
            }

            br.close();

        // Se der qualquer erro ao ler o arquivo
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
