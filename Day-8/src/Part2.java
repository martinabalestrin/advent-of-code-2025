import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part2 {
    public static void main(String[] args) {

        // Lista com coordenadas
        List<Point> points = new ArrayList<>();

        try {

            String line;
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));

            // Leitura das linhas e armazenando as variáveis por x, y, z em uma ArrayList
            while ((line = br.readLine()) != null) {

                String parts[] = line.split(",");

                long x = Long.parseLong(parts[0]);
                long y = Long.parseLong(parts[1]);
                long z = Long.parseLong(parts[2]);

                points.add(new Point(x, y, z));
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

                // Calcula a distância
                double dist = Math.sqrt(
                    Math.pow(p.x - q.x, 2) +
                    Math.pow(p.y - q.y, 2) +
                    Math.pow(p.z - q.z, 2)
                );

                // Adiciona na ArrayList 
                edges.add(new Edge(i, j, dist));
            }
        }

        // Ordena da menor distância para a maior
        Collections.sort(edges);

        // Cria o UnionFind para fazer as conexões
        UnionFind uf = new UnionFind(points.size());

        int edgesUsed = 0;
        Edge lastUsedEdge = null;

        // Tenta unir a partir das menores arestas, se não der, ignora
        for (Edge e : edges) {
            if(uf.union(e.p, e.q)) {
                // Salva a última aresta e incrementa a quantidade utilizada
                lastUsedEdge = e;
                edgesUsed++;

                // Se a contagem chegar na quantidade de pontos - 1, para
                if (edgesUsed == points.size() - 1) break;
            }
        }

        // Multiplicação dos valores de X desses pontos
        Point p = points.get((int)lastUsedEdge.p);
        Point q = points.get((int)lastUsedEdge.q);
        long px = p.x;
        long qx = q.x;

        long result = px * qx;     

        System.out.println("Resultado: " + result);
    }

    static class Point {
        long x, y, z;

        Point(long x, long y, long z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge implements Comparable<Edge> {
        long p, q;
        double dist;

        Edge (long p, long q, double dist) {
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

    static class UnionFind {
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

        boolean union (long p, long q) {
            int rp = find((int)p);
            int rq = find((int)q);

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
}

