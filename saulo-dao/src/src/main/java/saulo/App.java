package saulo;

/**
 * AEDS3 - TP01
 * 
 * @author Saulo de Moura Zandona Freitas - Matricula: 775777 - Unidade Coração Eucarístico
 * 
 */

import saulo.servicos.*;

import java.io.RandomAccessFile;
import java.util.Scanner;

import saulo.entidades.*;

public class App 
{


    public static void main( String[] args ) throws Exception
    {
        Dados dados = new Dados();
        DAOVideos DAO = new DAOVideosImpl(dados);
        Request request = new RequestImpl();
        Scanner sc = new Scanner(System.in);
        String Arquivo = "";
        RandomAccessFile raf = new RandomAccessFile("BD.txt", "rw");
        if (raf.length() == 0) {
            DAO.initArq();
        }
        MyIO.print("Digite abaixo o caminho COMPLETO que leva ao arquivo \"DisneyPlusBD.csv\"\n>>");
        Arquivo = sc.nextLine();
        MyIO.println();
        //---------------------------------- abrir requisições de CRUD ----------------------------------------
        MyIO.println("---------- Sistema iniciado ---------");
        
        int crud = 0;
        while(crud != 6){
            MyIO.println("----------------- Menu --------------");
            MyIO.println("01. Carregar o arquivo .csv ---------");
            MyIO.println("02. Criar um novo registro ----------");
            MyIO.println("03. Ler um registro existente -------");
            MyIO.println("04. atualizar um registro existente -"); 
            MyIO.println("05. deletar um registro existente ---");
            MyIO.println("06. sair ----------------------------");
            MyIO.print("Digite a opção desejada: ");

            crud = sc.nextInt();
            MyIO.println("");
            switch (crud) {
                case 1:
                    DAO.Load(Arquivo);//descarrega a base de dados DisneyPlusBD.csv no arquivo sequencial
                    break;
                case 2:
                    if (raf.length() > 4) {//garante o acesso apenas se o arquivo sequencial 
                                           //não estiver vazio (Arquivo com tamanho 4)

                        Video v = request.rCreate();
                        DAO.Create(v);
                    }
                    else{
                        MyIO.println("Função indisponível: Arquivo vazio, favor selecionar a função de carregar arquivo");
                    }
                    break;
                case 3:
                    if (raf.length() > 4) {//garante o acesso apenas se o arquivo sequencial 
                                           //não estiver vazio (Arquivo com tamanho 4)

                        int id = request.rRead();
                        Registro r = DAO.Read(id);
                        DAO.mostrar(r);
                    }   
                    else{
                        MyIO.println("Função indisponível: Arquivo vazio, favor selecionar a função de carregar arquivo");
                    }
                    break;
                case 4:
                    if (raf.length() > 4) {//garante o acesso apenas se o arquivo sequencial 
                                           //não estiver vazio (Arquivo com tamanho 4)
                        
                        Registro r = request.rUpdate();
                        DAO.Update(r);
                    }
                    else{
                        MyIO.println("Função indisponível: Arquivo vazio, favor selecionar a função de carregar arquivo");
                    }
                    break;
                case 5:
                    if (raf.length() > 4) {//garante o acesso apenas se o arquivo sequencial 
                                           //não estiver vazio (Arquivo com tamanho 4)
                        int id = request.rDelete();
                        DAO.Delete(id);
                    }
                    else{
                        MyIO.println("Função indisponível: Arquivo vazio, favor selecionar a função de carregar arquivo");
                    }
                    break;
                case 6:
                    break;
                default:
                    MyIO.println("Valor invalido!!");
                    break;
            }
        }
        raf.close();
        sc.close();
    }
}
