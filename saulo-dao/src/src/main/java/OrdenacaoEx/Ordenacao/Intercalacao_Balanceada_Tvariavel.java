import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Intercalacao_Balanceada_Tvariavel {
    private static final int BLOCK_SIZE = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String[]> bloco = new ArrayList<String[]>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("DisneyPlusDB.csv"));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                bloco.add(data);
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Ordena pelo id usando o bloco de tam variavel
        for (int i = 0; i < bloco.size(); i++) {
            for (int j = 0; j < bloco.size() - 1; j++) {
                String[] linhaAtual = bloco.get(j);
                String[] linhaSeguinte = bloco.get(j + 1);

                // Separa o id das outras informações usando o separador ";"
                String[] idAtual = linhaAtual[0].split(";");
                String[] idSeguinte = linhaSeguinte[0].split(";");

                // Converte o id de string para inteiro
                int idIntAtual = Integer.parseInt(idAtual[0]);
                int idIntSeguinte = Integer.parseInt(idSeguinte[0]);

                if (idIntAtual > idIntSeguinte) {
                    bloco.set(j, linhaSeguinte);
                    bloco.set(j + 1, linhaAtual);
                }
            }
        }

        // Escreve a lista ordenada em um novo arquivo CSV
        try {
            BufferedWriter csvWriter = new BufferedWriter(new FileWriter("I_Balanceada_Tvariavek.csv"));
            for (int i = 0; i < bloco.size(); i++) {
                String[] linha = bloco.get(i);
                csvWriter.write(linha[0] + "," + linha[1] + "\n");
            }
            csvWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}