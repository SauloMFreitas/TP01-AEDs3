package CRUD;

import java.io.File;

import CRUD.entidades.*;
import CRUD.servicos.*;

public class App 
{


    public static void main( String[] args ) throws Exception
    {
        Dados dados = new Dados();
        DAOVideos DAO = new DAOVideosImpl(dados);
        Request request = new RequestImpl();
        String Arquivo = "";

        MyIO.setCharset("UTF-8");

        boolean exist = false;

        while (!exist) {//garantir que o usuário digite o caminho do arquivo corretamente
            MyIO.print("Digite abaixo o caminho COMPLETO que leva ao arquivo \"DisneyPlusBD.csv\"\n>>");
            Arquivo = MyIO.readLine();//pede o caminho completo da base de dados .csv
            MyIO.println();
            File f = new File(Arquivo);
            exist = f.exists();    
        }
                
        
        //---------------------------------- abrir requisições de CRUD ----------------------------------------
        MyIO.println("---------- Sistema iniciado ---------");
        
        int crud = 0;
        while(crud != 6){
            MyIO.println();
            MyIO.println("----------------- Menu --------------");
            MyIO.println("01. Carregar o arquivo .csv ---------");
            MyIO.println("02. Criar um novo registro ----------");
            MyIO.println("03. Ler um registro existente -------");
            MyIO.println("04. atualizar um registro existente -"); 
            MyIO.println("05. deletar um registro existente ---");
            MyIO.println("06. sair ----------------------------");
            MyIO.print("Digite a opção desejada: ");

            crud = MyIO.readInt();
            MyIO.println();
            MyIO.println();
            switch (crud) {
                case 1:
                    DAO.load(Arquivo);//descarrega a base de dados DisneyPlusBD.csv no arquivo sequencial
                    break;
                case 2:
                    if (DAO.checkArqLength()) {//garante o acesso apenas se o arquivo sequencial 
                                           //não estiver vazio (Arquivo com tamanho 4)

                        Video v = request.rCreate();//abre um requerimento para criação de um novo video
                        DAO.create(v);//insere um novo video ao arquivo
                    }
                    else{
                        MyIO.println("Função indisponível: Arquivo vazio, favor selecionar a função de carregar arquivo");
                    }
                    break;
                case 3:
                    if (DAO.checkArqLength()) {//garante o acesso apenas se o arquivo sequencial 
                                           //não estiver vazio (Arquivo com tamanho 4)

                        int id = request.rRead();//faz o requerimento para o ID a ser lido
                        Registro r = DAO.read(id);//pesquisa o ID no arquivo
                        if (r.getId() > 0) {//verifica se o ID foi encontrado
                            DAO.mostrar(r);//se ID encontrado então mostrar na tela
                        }
                        else{
                            MyIO.println("Registro " + id + " não encontrado");//mostrar mensagem de ID não encontrado na tela
                        }
                    }   
                    else{
                        MyIO.println("Função indisponível: Arquivo vazio, favor selecionar a função de carregar arquivo");
                    }
                    break;
                case 4:
                    if (DAO.checkArqLength()) {//garante o acesso apenas se o arquivo sequencial 
                                           //não estiver vazio (Arquivo com tamanho 4)
                        
                        Registro r = request.rUpdate();//abre requerimento para atualização de um registro
                        boolean updated = DAO.update(r);//atualiza o registro
                        if (updated) {//verifica se o arquivo foi atualizado
                            MyIO.println("Registro "+ r.getId() + " atualizado com sucesso");
                        }
                        else{
                            MyIO.println("não foi possivel atualizar o registro");    
                        }
                    }
                    else{
                        MyIO.println("Função indisponível: Arquivo vazio, favor selecionar a função de carregar arquivo");
                    }
                    break;
                case 5:
                    if (DAO.checkArqLength()) {//garante o acesso apenas se o arquivo sequencial 
                                           //não estiver vazio (Arquivo com tamanho 4)
                        int id = request.rDelete();//abre requerimento para pedir o ID a ser deletado
                        boolean deleted = DAO.delete(id);//deleta o registro no arquivo
                        if (deleted) {//verifica se foi possivel apagar o arquivo
                            MyIO.println("Registro "+ id + " deletado com sucesso");
                        }
                        else{
                            MyIO.println("Registro "+ id + " já excluido ou inexistente");    
                        }
                    }
                    else{
                        MyIO.println("Função indisponível: Arquivo vazio, favor selecionar a função de carregar arquivo");
                    }
                    break;
                case 6://finaliza as operações e a interface
                    break;
                default:
                    MyIO.println("Valor invalido!!");
                    break;
            }
        }
    }
}
