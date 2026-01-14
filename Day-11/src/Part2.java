import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {
    public static void main(String[] args) {

        // Para acesso: 0 - Device | Demais índices: próximo device OU out
        List<List<String>> devices = new ArrayList<>();
        
        try {
            // Criação do BufferedReader
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line;

            // Leitura de todas as linhas com o BufferedReader
            while ((line = br.readLine()) != null) {
                // Adiciona os devices no array
                List<String> lineDevices = new ArrayList<>();
                int index = line.indexOf(":");
                String mainDevice = line.substring(0, index);
                lineDevices.add(mainDevice);

                String devicesPart = line.substring(index + 1).trim();
                String[] devicesOut = devicesPart.split(" ");

                for (int i = 0; i < devicesOut.length; i++) {
                    lineDevices.add(devicesOut[i]);
                }

                devices.add(lineDevices);
            }

            br.close();
        
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Cria os binários que precisam ter no caminho
        boolean dac = false;
        boolean fft = false;

        // Chama a função countPaths, que cria um grafo e vai retornar apenas uma resposta
        long totalPaths = countPaths(devices, "svr", dac, fft);
        System.out.println("Total de caminhos: " + totalPaths);
    }

    // Função para retornar a linha do device. A busca é feita pela string (nome dele)
    static List<String> getDevice (List<List<String>> devices, String name) {
        for (List<String> d : devices) {
            if (d.get(0).equals(name)) {
                return d;
            }
        }

        return null;
    }

    // Função para contar os caminhos. Cria um looping interno, chamando a função dentro dela mesma e retornando os caminhos possíveis
    static long countPaths (List<List<String>> devices, String current, boolean dac, boolean fft) {

        // Se encontrar o out e os binários forem positivos, retorna que foi mais um caminho
        if (current.equals("out")) {
            return (dac && fft) ? 1 : 0;
        }

        // Usando chave única por estado
        String key = current + " | " + dac + " | " + fft;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Salva a linha que estamos, buscando pelo nome
        List<String> device = getDevice(devices, current);

        // Se retornar null ou não tiver valor atrelado ao device, retorna 0 caminhos
        if (device == null || device.size() == 1) {
            memo.put(key, 0L);
            return 0;
        }

        long paths = 0;
        
        // Para cada valor da linha, vai testar. Esse for entra em um grafo, pois chama a função dentro dele
        for (int i = 1; i < device.size(); i++) {
            String next = device.get(i);

            boolean newDac = dac || next.equals("dac");
            boolean newFft = fft || next.equals("fft");

            paths += countPaths(devices, next, newDac, newFft);
        }

        // Adiciona ao cache
        memo.put(key, paths);
        // No fim, vai acabar somando tudo e vai retornar um valor apenas
        return paths;
    }

    // Criação de cache para lembrar os caminhos
    static Map<String, Long> memo = new HashMap<>();
}
