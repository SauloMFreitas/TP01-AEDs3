import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Intercalacao_Balanceada_Comum {

    public static void main(String[] args) {
        ArrayList<String[]> blocos = new ArrayList<String[]>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("DisneyPlusDB.csv"));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                blocos.add(data);
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Seleciona a coluna "id" para intercalação
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> a - b);
        for (int i = 0; i < blocos.size(); i++) {
            String[] linha = blocos.get(i);
            // Verifica se a linha possui o separador ";" na primeira coluna
            if (linha[0].contains(";")) {
                String[] idSeparado = linha[0].split(";");
                pq.add(Integer.parseInt(idSeparado[0]));
            } else {
                pq.add(Integer.parseInt(linha[0]));
            }
        }

        // Intercala os blocos
        ArrayList<String[]> listaIntercalada = new ArrayList<String[]>();
        while (!pq.isEmpty()) {
            int id = pq.poll();
            for (int i = 0; i < blocos.size(); i++) {
                String[] linha = blocos.get(i);
                if (linha[0].startsWith(Integer.toString(id))) {
                    listaIntercalada.add(linha);
                    break;
                }
            }
        }

        // Escreve a lista intercalada em um novo arquivo CSV
        try {
            BufferedWriter csvWriter = new BufferedWriter(new FileWriter("I_Balanceada_Comum.csv"));
            for (int i = 0; i < listaIntercalada.size(); i++) {
                String[] linha = listaIntercalada.get(i);
                csvWriter.write(linha[0] + "," + linha[1] + "\n");
            }
            csvWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}