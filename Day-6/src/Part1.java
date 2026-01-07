import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) {
        
        // cada coluna forma uma soma ou multiplicacao, composta por 4 numeros
        // da pra ler o arquivo, quando nao for espaco, ele le a string até ser espaco de novo. guarda em um vetor
            // isso serve para cada linha de numeros, que vao estar em uma matriz -> fazer com arraylist??
            // fazer um vetor char para as operacoes
        // depois vai buscar os valores pelo mesmo indice, e guarda-los somados ou multiplicados em um novo vetor resultado
        // apos tudo isso, somar todos os valores do vetor resultado

        // Grid com todos os números, separados por linhas
        List<List<Integer>> numbersGrid = new ArrayList<>();
        // Lista com todos os operadores
        List<Character> operators = new ArrayList<>();
        // Contador de linhas com números
        int rows = 0;
        // Contador de colunas
        int columns = 0;

        try {

            String line;
            String filename = "input.txt";

            BufferedReader br = new BufferedReader(new FileReader(filename));

            while ((line = br.readLine()) != null) {
                
                // Índice
                int i = 0;
                // Lista de números por linha
                List<Integer> numbersByLine = new ArrayList<>();

                while (i < line.length()) {

                    // Incrementa o índice enquanto é espaço
                    while (i < line.length() && line.charAt(i) == ' ') {
                        i++;
                    }

                    // Se chegar ao fim da linha, para
                    if (i >= line.length()) break;

                    // String que será salva o número completo
                    String numberString = "";

                    // Leitura dos números
                    while (i < line.length() && line.charAt(i) != ' ') {
                        numberString += line.charAt(i);
                        i++;
                    }

                    // Imprime para debug
                    // System.out.println(numberString);

                    // Se for operador, adiciona ao vetor de operadores
                    if (numberString.equals("+") || numberString.equals("*")) {
                        operators.add(numberString.charAt(0));
                        columns++;
                    }

                    // Adiciona o número completo a array de números da linha
                    else {
                        numbersByLine.add(Integer.parseInt(numberString));
                    }
                    
                }

                // Só adiciona o array se ele não estiver vazios
                if (!numbersByLine.isEmpty()) {
                    numbersGrid.add(numbersByLine);
                    rows++;
                }
            }

            // System.out.println(numbersGrid);
            // System.out.println(operators);

            br.close();
        
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // --------------------------
        // Agora que já temos todos os vetores completos com os tipos corretos, temos que resolver os cálculos
        // --------------------------

        // numbersGrid.get(linha).get(coluna)

        // Array com os valores somados/multiplicados por coluna
        List<Long> results = new ArrayList<>();

        // Para cada coluna
        for (int i = 0; i < columns; i++) {
            // Localizar qual o operador
            char op = operators.get(i);

            // Se o operador for de soma
            if (op == '+') {
                long value = 0;

                // Para a linha
                for (int j = 0; j < rows; j++) {
                    value += numbersGrid.get(j).get(i);
                }

                // System.out.println(value);
                results.add(value);
            }
            // Se o operador for de multiplicação
            else if (op == '*') {
                long value = 1;

                // Para a linha
                for (int j = 0; j < rows; j++) {
                    value *= numbersGrid.get(j).get(i);
                }

                // System.out.println(value);
                results.add(value);
            }
            // Detectar erro
            else {
                System.out.println("Algum erro!");
            }
        }

        long sum = 0;

        // Somar todos os valores do array 'results'
        for (int n = 0; n < results.size(); n++) {
            sum += results.get(n);
        }

        System.out.println(sum);
    }
}
