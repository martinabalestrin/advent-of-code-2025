import java.util.*;
import java.io.*;

public class Part2 {
    public static void main(String[] args) {
        
        // Lista para guardar cada linha do arquivo
        List<char[]> grid = new ArrayList<>();

        // Nome do arquivo
        String filename = "input.txt";

        // Tenta abrir e ler o arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;

            // Alimentando o grid com o 'input.txt'
            while((line = br.readLine()) != null) {
                grid.add(line.toCharArray());
            }

        // Se der qualquer erro ao ler o arquivo
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
            // Para o sistema
            return;
        }

        // Qntd de linhas
        int rows = grid.size();
        // Qntd de colunas - não é o mesmo que rows?
        int cols = grid.get(0).length;

        // Movimentos em X e Y
        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] dy = {1, 1, 1, 0, 0, -1, -1, -1};
        // Contador de rolos possíveis
        int accessible = 0;

        while(true) {

            int oldAccessible = accessible;

            // Percorre todas as posições em busca de '@'
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {

                    if (grid.get(i)[j] == '@') {
                        int cont = 0;
                        
                        // Verifica as 8 posições ao redor
                        for (int d = 0; d < 8; d++) {
                            int ni = i + dx[d];
                            int nj = j + dy[d];

                            // Verifica se está dentro do grid
                            if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {
                                // Se a posição [ni][nj] também é '@'
                                if (grid.get(ni)[nj] == '@') {
                                    cont++;
                                }
                            }
                        }

                        if (cont < 4) {
                            accessible++;
                            grid.get(i)[j] = '.';
                            // Talvez aqui dê erro nos calculos, como to trocando por linha,
                            // talvez seja melhor trocar após calcular tudo direitinho
                        }
                    }
                }
            }

            // Trava do sistema, se não aumentar os accessibles, para
            if (oldAccessible == accessible) {
                System.out.println(accessible);
                return;
            }
        }
    }
}
