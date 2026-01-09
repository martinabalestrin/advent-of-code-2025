import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 {
    public static void main(String[] args) {

        // Lista com coordenadas
        List<Point> points = new ArrayList<>();

        try {

            String line;
            String filename = "input.txt";
            BufferedReader br = new BufferedReader(new FileReader(filename));

            // Leitura das linhas e armazenando as variáveis por x, y, z em uma ArrayList
            while ((line = br.readLine()) != null) {

                String parts[] = line.split(",");

                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                int z = Integer.parseInt(parts[2]);

                points.add(new Point(x, y, z));

                // System.out.println(coords);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Gerar todas as distâncias possíveis
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {
            for(int j = i + 1; j < points.size(); j++) {
                Point p = points.get(i);
                Point q = points.get(j);

                double dist = Math.sqrt(
                    Math.pow(p.x - q.x, 2) +
                    Math.pow(p.y - q.y, 2) +
                    Math.pow(p.z - q.z, 2)
                );

                edges.add(new Edge(i, j, dist));
            }
        }

        // Ordena da menor distância para a maior
        Collections.sort(edges);

        // DEBUG - imprime a ArrayList de arestas
        // System.out.println(edges);

        // Cria o UnionFind para fazer as 1000 conexões
        UnionFind uf = new UnionFind(points.size());

        // Impor limite para não estourar com input menor
        int limit = Math.min(1000, edges.size());

        // Tenta unir a partir das 1000 menores arestas, se não der, ignora
        for (int i = 0; i < limit; i++) {
            Edge e = edges.get(i);
            uf.union(e.p, e.q);
        }

        // Contar o tamanho dos circuitos
        Map<Integer, Integer> circuitSizes = new HashMap<>();

        for (int i = 0; i < points.size(); i++) {
            // Tamanho do circuito
            int root = uf.find(i);
            circuitSizes.put(root, circuitSizes.getOrDefault(root, 0) + 1);
        }

        // Calcula resultado final
        List<Integer> sizes = new ArrayList<>(circuitSizes.values());
        sizes.sort(Collections.reverseOrder());

        // Para não quebrar, verifica se tem mais de 3 resultados
        if (sizes.size() < 3) {
            System.out.println("Menos de 3 circuitos encontrados: " + sizes);
            return;
        }

        long result = (long) sizes.get(0) * sizes.get(1) * sizes.get(2);

        System.out.println("Resultado: " + result);
    }
}

class Point {
    int x, y, z;

    Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

class Edge implements Comparable<Edge> {
    int p, q;
    double dist;

    Edge (int p, int q, double dist) {
        this.p = p;
        this.q = q;
        this.dist = dist;
    }

    @Override 
    // Função que vai facilitar muito para fazer .sort()
    public int compareTo (Edge other) {
        return Double.compare(this.dist, other.dist);
        // Se a distância da atual aresta for MENOR, retorna negativo
        // Se as distâncias forem iguais, retorna zero
        // Se a distância da atual aresta for MAIOR, retorna positivo
    }

    @Override
    public String toString() {
        return "\nP: " + p + "\nQ: " + q + "\nDistância: " + dist;
    }
}

class UnionFind {
    int[] parent;
    int[] size;

    UnionFind(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    int find (int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    boolean union (int p, int q) {
        int rp = find(p);
        int rq = find(q);

        if (rp == rq) return false;

        // Une o maior no menor
        if (size[rp] < size[rq]) {
            parent[rp] = rq;
            size[rq] += size[rp];
        } else {
            parent[rq] = rp;
            size[rp] += size[rq];
        }
        return true;
    }
}