import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2Int {
    public static void main(String[] args) {

        // Lista com todas as linhas armazenadas em Long
        List<List<Long>> numberGrid = new ArrayList<>();

        try {

            String filename = "input.txt";
            BufferedReader br = new BufferedReader(new FileReader(filename));
            
            String line;

            while ((line = br.readLine()) != null) {
                List<Long> numbersLine = new ArrayList<>();
                // Colunas
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '^') {
                        numbersLine.add(-1L);
                    }
                    else if (line.charAt(i) == '.') {
                        numbersLine.add(0L);
                    }
                    else if (line.charAt(i) == 'S') {
                        numbersLine.add(1L);
                    }
                }

                // Adiciona a linha de números ao grid
                numberGrid.add(numbersLine);
            }

            br.close();
        
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Variáveis que guardam linhas e colunas para facilitar a legibilidade
        int rows = numberGrid.size();
        int cols = numberGrid.get(0).size();

        // Iterando para cada linha
        for (int i = 1; i < rows; i++) {

            List<Long> current = numberGrid.get(i);
            List<Long> previous = numberGrid.get(i-1);

            // Iterando para cada coluna
            for (int j = 0; j < cols; j++) {

                // Se for um spliter
                if (current.get(j) == -1) {
                    
                    long waysLeft = previous.get(j) + current.get(j-1);
                    current.set(j-1, waysLeft);

                    long waysRight = previous.get(j) + current.get(j+1);
                    current.set(j+1, waysRight);
                }

                // Se não for spliter
                else if (current.get(j) >= 0 && previous.get(j) >= 0) {
                    long waysUp = previous.get(j) + current.get(j);
                    current.set(j, waysUp);
                }
            }

            System.out.println(current);
        }

        long waysSum = 0;

        for (int i = 0; i < cols; i++) {
            waysSum += numberGrid.getLast().get(i);
        }

        System.out.println("Possible ways: " + waysSum);
    } 
} 