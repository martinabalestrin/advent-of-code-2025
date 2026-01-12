import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Part1 {
    public static void main(String[] args) {

        // Modelo de entrada
        // x,y -> usar split(",")

        List<int[]> coordsGrid = new ArrayList<>();

        try {

            String filename = "input.txt";
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = br.readLine()) != null) {
                // Adicionar cada array de coordenada individualmente
                String[] lineCoords = line.split(",");
                int[] intArray = {Integer.parseInt(lineCoords[0]), Integer.parseInt(lineCoords[1])};
                coordsGrid.add(intArray);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Calcular o maior retângulo posível, testando todas as possibilidades
        long largestRectangle = 0;

        // Linha de cima
        for (int i = 0; i < coordsGrid.size() - 1; i++) {
            // Variáveis da linha i
            long ix = coordsGrid.get(i)[0];
            long iy = coordsGrid.get(i)[1];

            // Iterando para todas as linhas de i + 1
            for (int j = i + 1; j < coordsGrid.size(); j++) {
                // Variáveis da linha j
                long jx = coordsGrid.get(j)[0];
                long jy = coordsGrid.get(j)[1];

                // Calculo da área
                long differenceX = Math.abs(ix-jx) + 1;
                long differenceY = Math.abs(iy-jy) + 1;
                long area = differenceX * differenceY;

                // DEBUG
                // System.out.println("Coordenadas I: " + ix + ", " + iy);
                // System.out.println("Coordenadas J: " + jx + ", " + jy);
                // System.out.println("Cálculo: " + differenceX + " * " + differenceY + " = " + area);

                // Se a área calculada for maior que a área atual, substituir
                if (area > largestRectangle) largestRectangle = area;
            }
        }

        System.out.println(largestRectangle);
    }
}
