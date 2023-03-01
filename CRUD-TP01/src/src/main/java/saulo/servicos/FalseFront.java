package saulo.servicos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class FalseFront {
    Scanner sc = new Scanner(System.in);
    
    public String RequestType(){
        String Type = "";
        int Tipo;        
        do{
        System.out.println("Qual o Tipo do video:");
        System.out.println("Digite 1 para filme (type MV)");
        System.out.println("Digite 2 para Série (type TV)");
        Tipo = sc.nextInt();
        if (Tipo != 1 || Tipo != 2){
            System.out.println("ERROR: Tipo não reconhecido, favor redigitar o Tipo do video");
        }
        }while(Tipo != 1 || Tipo != 2);
        if(Tipo == 1){
            Type =  "MV";
        }
        else{
            Type = "TV";
        }

        return Type;
    }

    public String RequestTitulo(){
        
        System.out.println("Digite o titulo do video:");
        System.out.print(">> ");
        String Title = sc.nextLine();
        System.out.println("");

        return Title;

    }

    public Date RequestData(){
        
        String month, day, year;
        int mes, dia, ano;
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

        do {
            System.out.println("Digite o mês numericamente.");
            mes = sc.nextInt();
            if(mes < 1 || mes > 12){
                System.out.println("ERROR: mes não reconhecido, favor redigitar o mes de publicação do video");
            }
        } while (mes < 1 || mes > 12);
        return null;

    }
}
