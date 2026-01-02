import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {

    // Classe auxiliar para devolver dois valores
    static class Result {
        int newDial;
        int contZero;

        Result(int newDial, int contZero) {
            this.newDial = newDial;
            this.contZero = contZero;
        }
    }

    static Result rotations(String line, int oldDial) {
        int count = Integer.parseInt(line.substring(1).trim());
        char direction = line.charAt(0);
        int contZero = 0;
        int newDial = oldDial;

        if (direction == 'R') {
            for(int i = 0; i < count; i++) {
                newDial++;
                if (newDial == 100) newDial = 0;
                if (newDial == 0) contZero++;
            }
        } 
        else if (direction == 'L') {
            for(int i = 0; i < count; i++) {
                newDial--;
                if (newDial == -1) newDial = 99;
                if (newDial == 0) contZero++;
            }
        }

        return new Result(newDial, contZero);
    }
    public static void main(String[] args) throws Exception {
        
        // Declaração de variáveis
        int oldDial = 50;
        int cont = 0;

        // Abrindo o arquivo de leitura
        try(BufferedReader br = new BufferedReader(new FileReader("/Users/I772397/Coding/advent-of-code-2025/Java/src/input.txt"))) {
            
            String line;

            while ((line = br.readLine()) != null) {
                Result result = rotations(line, oldDial);
                cont += result.contZero;
                oldDial = result.newDial;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(cont);
    }
}