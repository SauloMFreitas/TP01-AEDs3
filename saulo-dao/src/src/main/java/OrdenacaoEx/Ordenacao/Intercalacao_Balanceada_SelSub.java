import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Intercalacao_Balanceada_SelSub {

    public static void main(String[] args) {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("DisneyPlusDB.csv"));
            ArrayList<String[]> dados = new ArrayList<String[]>();
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                dados.add(data);
            }
            csvReader.close();

            intercalarBloco(dados, "I_Balanceada_SelSub.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void intercalarBloco(ArrayList<String[]> dados, String arquivoSaida) {
        int tamBloco = 4;
        int numBlocos = (int) Math.ceil((double) dados.size() / tamBloco);

        try {
            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(arquivoSaida));

            for (int bloco = 0; bloco < numBlocos; bloco++) {
                int inicioBloco = bloco * tamBloco;
                int fimBloco = Math.min((bloco + 1) * tamBloco, dados.size());

                PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

                for (int i = inicioBloco; i < fimBloco; i++) {
                    String[] linha = dados.get(i);
                    String[] colunas = linha[0].split(";");

                    int id = Integer.parseInt(colunas[0]);
                    pq.add(id);
                }

                ArrayList<String[]> linhasOrdenadas = new ArrayList<String[]>();
                while (!pq.isEmpty()) {
                    int id = pq.poll();

                    for (int i = inicioBloco; i < fimBloco; i++) {
                        String[] linha = dados.get(i);
                        String[] colunas = linha[0].split(";");

                        if (Integer.parseInt(colunas[0]) == id) {
                            linhasOrdenadas.add(linha);
                            break;
                        }
                    }
                }

                for (int i = 0; i < linhasOrdenadas.size(); i++) {
                    String[] linha = linhasOrdenadas.get(i);
                    csvWriter.write(linha[0] + "," + linha[1] + "\n");
                }
            }

            csvWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
