import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Part2 {
    public static void main(String[] args) {
        
        // Nome do arquivo de entrada
        String filename = "input.txt";
        // Vetor com todos os intervalos
        List<Interval> intervals = new ArrayList<>();
        long count = 0;

        try {
            // Lê até a linha vazia e guarda os IDs
            BufferedReader br = new BufferedReader(new FileReader(filename));

            String line;

            while ((line = br.readLine()) != null) {

                // Quando a linha for vazia, para a leitura
                if(line.isEmpty()) {
                    br.close();
                    break;
                }

                // Divide com split() o início e fim do intervalo
                String[] intervalStr = line.split("-");
                long start = Long.parseLong(intervalStr[0]);
                long end = Long.parseLong(intervalStr[1]);

                // Adiciona o intervalo ao vetor
                intervals.add(new Interval(start, end));
            }
            
        // Se der qualquer erro ao ler o arquivo
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Arruma em ordem e imprime os intervalos originais
        Collections.sort(intervals);
        System.out.println("Intervalos originais: " + intervals);

        // Cria o vetor que vai juntar os conjuntos
        List<Interval> mergedIntervals = mergeIntervals(intervals);
        System.out.println("Intervalos unidos: " + mergedIntervals);

        // Para cada valor do mergedIntervals, soma a diferença+1 em count
        for (Interval i : mergedIntervals) {
            count += i.end - i.start + 1;
        }

        System.out.println("Number of fresh ingredients IDs: " + count);
    }

    public static List<Interval> mergeIntervals (List<Interval> intervals) {
        
        List<Interval> merged = new ArrayList<>();

        Interval current = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            Interval next = intervals.get(i);

            if (next.start <= current.end + 1) {
                current.end = Math.max(current.end, next.end);
            } else {
                merged.add(current);
                current = next;
            }
        }

        merged.add(current);

        return merged;
    }
}

class Interval implements Comparable<Interval> {
    long start;
    long end;
    
    Interval(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override 
    public int compareTo(Interval other) {
        if (this.start > other.start) {
            return 1;
        } else if (this.start < other.start) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return start + "-" + end;
    }
}