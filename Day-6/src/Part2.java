import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) {

        // Lista com todas as linhas
        List<String> lines = new ArrayList<>();
        // Contador de linhas
        int countLines = 0;
        
        try {

            /*
                - Guardar as linhas em ArrayLists
                - Para cada 'i' (linha), verificar o 'j' (coluna)
                - Terá a variável boolean 'blankColumn' = true, se encontrar algum [i][j] que seja != ' ', 'blankColumn' = false
                - Se 'blankColumn' = false, então temos que ler os valores
                    - Se o valor for != ' ', 
                        - Se o valor for .equals('+') || .equals('*')
                            - operator = [i][j]
                        - Else
                            - Adicionar ao vetor daquele cálculo
                - Logo abaixo do while, fazer um for para repetir a operação daquela coluna e dar um resultado, que será guardado em uma array de 'results'

            */

            String line;
            String filename = "input.txt";

            BufferedReader br = new BufferedReader(new FileReader(filename));

            // Preenche a lista com todas as linhas
            while ((line = br.readLine()) != null) {
                lines.add(line);
                countLines++;
            }

            br.close();
        
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Lista com valores para soma final
        List<Long> results = new ArrayList<>();

        // 
        // Operador da seção
        char operator = 'X';
        // Array com números da seção
        List<Integer> sectionNumbers = new ArrayList<>();

        // Índice i para iterar sobre as colunas, sem ultrapassar o tamanho delas
        for (int i = 0; i < lines.get(0).length(); i++) {

            boolean blankColumn = true;

            // Índice j para iterar sobre as linhas, verificando se é uma coluna vazia
            for (int j = 0; j < countLines; j++) {

                if (i < lines.get(j).length() && lines.get(j).charAt(i) != ' ') {

                    blankColumn = false;
                    break;
                }
            }

            // Se a coluna é vazia, fazer o a lógica de cálculo do número anterior
            if (blankColumn) {
                // Vai funcionar para o último número?
                
                long value = 0;

                if (operator == '+') {
                    for (int n = 0; n < sectionNumbers.size(); n++) {
                        value += sectionNumbers.get(n);
                    }
                }
                else if (operator == '*') {
                    value = 1;
                    for (int n = 0; n < sectionNumbers.size(); n++) {
                        value *= sectionNumbers.get(n);
                    }
                }
                else {
                    System.out.println("Erro!!!");
                }

                // Adiciona o valor final da seção em results
                results.add(value);

                // Limpar sectionNumbers
                sectionNumbers.clear();
            }

            // Se não é uma coluna vazia, temos que armazenar o valor ou operador
            else {

                String numColumnString = "";

                // Índice j para iterar sobre as linhas
                for (int j = 0; j < countLines; j++) {

                    // Verificar se não é espaço vazio
                    if (i < lines.get(j).length() && lines.get(j).charAt(i) != ' ') {

                        // Verificar se é operador
                        if (lines.get(j).charAt(i) == '+' || lines.get(j).charAt(i) == '*') {
                            operator = lines.get(j).charAt(i);
                        }

                        else {
                            // Guardar o número individual na variável de string
                            numColumnString += lines.get(j).charAt(i);
                        }
                    }
                }

                // Adiciona o número dessa coluna ao número da seção
                sectionNumbers.add(Integer.parseInt(numColumnString));
            }
        }

        if (!sectionNumbers.isEmpty()) {
            long value = 0;

            if (operator == '+') {
                for (int n : sectionNumbers) value += n;
            } else if (operator == '*') {
                value = 1;
                for (int n : sectionNumbers) value *= n;
            }

            results.add(value);
        }

        long sum = 0;

        // Agora soma tudo de results
        for (int m = 0; m < results.size(); m++) {
            sum += results.get(m);
        }

        System.out.println(sum);
    }
}