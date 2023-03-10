package CRUD.servicos;

/**
 * AEDS3 - TP01
 * 
 * @author Saulo de Moura Zandona Freitas - Matricula: 775777 - Unidade Coração Eucarístico
 * @author Joel Franco da Mata            - Matricula: 728003 - Unidade Praça da Liberdade
 * 
 * implementação das requisições de valores utilizados no CRUD
 * 
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import CRUD.entidades.*;

public class RequestImpl implements Request {

    @Override
    public Video rCreate() {

        String tipo = "", titulo = "", date = "";
        char mais = ' ';
        int ano = 0;
        boolean testData = true;
        Date data = new Date();
        List<String> generos = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        while(!tipo.equals("MV") && !tipo.equals("TV")){//pede o tipo do video, garantindo que o tipo será MV ou TV
            MyIO.print("Digite o tipo da obra:\n01. Digite MV para filme\n02. Digite TV para serie\n >> ");
            tipo = MyIO.readLine();
            MyIO.println();
            MyIO.println(tipo);
            MyIO.println(tipo == "MV");
            if(!tipo.equals("MV") && !tipo.equals("TV")){
                MyIO.println("ERROR: Tipo inválido: digite um dos tipos permitidos para prosseguir!!!");
            }

        }

        MyIO.println("Digite o titulo da obra");
        MyIO.print(">> ");
        titulo = MyIO.readLine();// pede o titulo do video
        MyIO.println();    
        MyIO.println();    

        
        while(testData){//pede a data de publicação do video no Disney plus, garantindo que a data saia correta
            MyIO.println("Digite a data da publicação da obra no Disney+");
            MyIO.println("(OBS: o padrão de escrita deve ser MMM dd, yyyy. \n Em que MMM é o mes em ingles \n dd é o dia \n yyyy é o ano)");
            MyIO.print(">> ");
            date = MyIO.readLine();
            MyIO.println();
            try {
                data = sdf.parse(date);
                testData = false;
            } catch (ParseException e) {
                MyIO.println("Data incorreta, favor regititá-la");
                testData = true;
            }
        }
        MyIO.println();

        MyIO.println("Digite o ano de lançamento da obra");
        MyIO.print(">> ");
        MyIO.println();
        ano = MyIO.readInt();//pede o ano de lançamento do video
        MyIO.println();

        while(mais != 'n' && mais != 'N'){//pede os generos do video
            mais = ' ';
            MyIO.println("Digite um gênero da obra");
            MyIO.print(">> ");
            generos.add(MyIO.readString());
            MyIO.println();
            while(mais != 'n' && mais != 'N' && mais != 's' && mais != 'S'){
                MyIO.print("Deseja adicionar mais um genero?(s/N): ");
                mais = MyIO.readChar();
            }
        }
        MyIO.println();

        Video video = new Video(tipo, titulo, data, ano, generos);

        return video;
    }

    @Override
    public int rRead() {
        MyIO.print("Digite o ID do video que deseja ver\n>> ");
        int id = MyIO.readInt();
        MyIO.println();
        MyIO.println();
        return id;
    }

    @Override
    public Registro rUpdate() throws Exception{
        DAOVideos DAO = new DAOVideosImpl();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        MyIO.println("Qual o ID do registro que deseja alterar");
        MyIO.print(">> ");
        int id = MyIO.readInt();
        MyIO.println();
        MyIO.println();
        
        Registro r = DAO.read(id);
        if (r.getId() > 0) {
            int update = 0;
            while (update != 6) {
                MyIO.println("Elementos a atualizar no id "+id+":");
                MyIO.println("01. alterar tipo ( tipo atual: "+r.getConteudo().getTipo()+" )");
                MyIO.println("02. alterar titulo ( titulo atual: "+r.getConteudo().getTitulo()+" )");
                MyIO.println("03. Alterar data ( data de publicação atual: "+sdf.format(r.getConteudo().getDataPublicacao())+" )");
                MyIO.println("04. alterar Ano de Lançamento (ano de lançamento atual: "+ r.getConteudo().getAnoLancamento()+" )");
                MyIO.print("05. Alterar generos (generos atuais: [ ");
                for (int i = 0; i < r.getConteudo().getGeneros().size(); i++) {
                    MyIO.print(r.getConteudo().getGeneros().get(i) + " ");
                }
                MyIO.println("]");
                MyIO.println("06. exit ");
                MyIO.print(">> ");
                update = MyIO.readInt();
                MyIO.println();
                switch (update) {
                    case 1:
                        MyIO.println("Alterar tipo selecionado:");
                        if(r.getConteudo().getTipo().equals("MV")){
                            r.getConteudo().setTipo("TV");
                            MyIO.println("tipo alterado de MV para TV");
                            MyIO.println();
                        }
                        else{
                            r.getConteudo().setTipo("MV");
                            MyIO.println("tipo alterado de TV para MV");
                            MyIO.println();
                        }
                        break;
                    case 2:
                        MyIO.println("Alterar titulo selecionado:");
                        MyIO.print("Digite o titulo alterado\n>> ");
                        String titulo = MyIO.readLine();
                        MyIO.println();
                        r.getConteudo().setTitulo(titulo);
                        MyIO.println();
        
                        MyIO.println("Titulo alterado com sucesso;");
                        MyIO.println();
                        MyIO.println();
                        break;
                    case 3:
                        MyIO.println("Alterar data de publicação selecionado:");
                        MyIO.println();
                        MyIO.println();
                        boolean testData = true;
                        Date data = new Date();
                        while(testData){//pede a data de publicação do video no Disney plus, garantindo que a data saia correta
                            MyIO.println("Digite a data da publicação alterada");
                            MyIO.println("(OBS: o padrão de escrita deve ser MMM dd, yyyy. \n Em que MMM é o mes em ingles \n dd é o dia \n yyyy é o ano)");
                            MyIO.print(">> ");
                            String date = MyIO.readString();
                            MyIO.println();
                            MyIO.println();
                            try {
                                data = sdf.parse(date);
                                testData = false;
                            } catch (ParseException e) {
                                MyIO.println("Data incorreta, favor regititá-la");
                                testData = true;
                            }
                        }
                        r.getConteudo().setDataPublicacao(data);
                        MyIO.println("Data alterada com sucesso;");
                        MyIO.println();
                        MyIO.println();
        
                        break;
                    case 4:
                        MyIO.println("Alterar ano de lançamento selecionado:");
                        MyIO.print("Digite o ano de lançamento alterado\n>> ");
                        int ano = MyIO.readInt();
                        MyIO.println();
                        MyIO.println();
                        r.getConteudo().setAnoLancamento(ano);
                        MyIO.println("Ano de lançamento alterado");
                        MyIO.println();
                        break;
                    case 5:
                        MyIO.println("Alterar generos selecionado:");
                        MyIO.println();
                        char mais = ' ';
                        List<String> generos = new ArrayList<>();
                        while(mais != 'n' && mais != 'N'){//pede os generos do video
                            MyIO.println("Digite um gênero da obra");
                            MyIO.print(">> ");
                            generos.add(MyIO.readString());
                            MyIO.println();
                            MyIO.println();
                            while(mais != 'n' && mais != 'N' && mais != 's' && mais != 'S'){
                                MyIO.print("Deseja adicionar mais um genero?(s/N): ");
                                mais = MyIO.readChar();
                                MyIO.println();
                                MyIO.println();
                        }
                        }
        
                        r.getConteudo().setGeneros(generos);
                        break;
                    case 6:
                        break;
                    default:
                        break;
                }
            }
        }
        

        return r;
    }

    @Override
    public int rDelete() throws Exception{
        int id = 0;
        char confirm = ' ';
        DAOVideos DAO = new DAOVideosImpl();


        while(confirm != 's' && confirm != 'S'){
            MyIO.print("Digite o id do registro que deseja excluir\n>>");
            id = MyIO.readInt();
            MyIO.println();
            MyIO.println();
            Registro r = DAO.read(id);
            if (r.getId() > 0) {
                while(confirm != 'n' && confirm != 'N' && confirm != 's' && confirm != 'S'){
                    MyIO.print("Deseja deseja excluir o regsitro de titulo( "+r.getConteudo().getTitulo()+" )?(s/N): ");
                    confirm = MyIO.readChar();
                    MyIO.println();
                    MyIO.println();
                }    
            }
            else{
                confirm = 's';
            }
            
        }

        
        return id;
    }
    
}
