package CRUD.servicos;

/**
 * AEDS3 - TP01
 * 
 * @author Saulo de Moura Zandona Freitas - Matricula: 775777 - Unidade Coração Eucarístico
 * @author Joel Franco da Mata            - Matricula: 728003 - Unidade Praça da Liberdade
 * 
 * implementação do CRUD
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * AEDS3 - TP01
 * 
 * @author Saulo de Moura Zandona Freitas - Matricula: 775777 - Unidade Coração Eucarístico
 * 
 */


//OBS: os documentos dos métodos estão nas suas respectivas declarações no arquivo de interface

//----------- importações -----------------------
import java.util.Locale;

import CRUD.entidades.*;


public class DAOVideosImpl implements DAOVideos {
	private static final String Arquivo = "BD.db";
	private Dados dados;

	
	//----------- Constructor --------------------------------
	public DAOVideosImpl(Dados dados) {
		this.dados = dados;
	}

	public DAOVideosImpl(){
		this.dados = new Dados();
	}
	//--------------------------------------------------------

	//---------- getter e setter -----------------------------
	public Dados getDados() {
		return dados;
	}
	//--------------------------

	public void setDados(Dados dados) {
		this.dados = dados;
	}
	//--------------------------------------------------------


	
	//---------- metodos de CRUD -----------------------------

	// Create --------------------------

	@Override
	public Registro create(Video video) throws Exception {

		Registro registro = new Registro(dados.getUltimoId()+1, false, video);

		boolean criado = save(registro);
		if(criado){
			MyIO.println("o ID deste registro é: "+registro.getId());
			MyIO.println("registro criado com sucesso!!!");
		}else{
			MyIO.println("ERROR ao inserir: registro não criado!!!");
		}
		return registro;
	}

	// Read --------------------------

	@Override
	public Registro read(int id) throws IOException, ClassNotFoundException{
		Registro registro = new Registro();

		try (RandomAccessFile raf = new RandomAccessFile(Arquivo, "rw")){
			raf.seek(4);//posiciona o ponteiro para o primeiro registro
			while(raf.getFilePointer() < raf.length()){
				int tempID = raf.readInt();//recebe ID para verificação - 4bytes
				boolean lapide = raf.readBoolean();//recebe lápide para verificação - 1 byte
				int tamRegis = raf.readInt();//recebe tamanho do registro - 4 bytes
				if (tempID == id && !lapide) {//verifica se o id no arquivo é igual ao ID pedido e se o arquivo está excluido 
					registro.setId(tempID);// recupera do arquivo o ID 
					registro.getConteudo().setTipo(raf.readUTF());// recupera do arquivo o tipo
					registro.getConteudo().setTitulo(raf.readUTF()); // recupera do arquivo o titulo
					Long tempData = raf.readLong(); // recupera do arquivo a data em long (valor é baseado no tempo após 1 de janeiro de 1970)
					Date data = new Date();
					data.setTime(tempData);//converte a data de long para tipo Date
					registro.getConteudo().setDataPublicacao(data); // registra a data recuperada
					registro.getConteudo().setAnoLancamento(raf.readInt()); // recupera do arquivo o ano de lançamento
					int totalgenres = raf.readInt(); // recupera do arquivo a quantidade de generos
					List<String> genres = new ArrayList<String>();
					for (int i = 0; i < totalgenres; i++) {
						genres.add(raf.readUTF());//recupera do arquivo cada genero individualmente
					}
					registro.getConteudo().setGeneros(genres);//registra os generos recuperados do arquivo
					break;
				}
				else{
					raf.skipBytes(tamRegis);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return registro;
	}



	// Update --------------------------

	@Override
	public boolean update(Registro registro) throws Exception{
		
		boolean resp = false;

		try (RandomAccessFile raf = new RandomAccessFile(Arquivo, "rw")){
			
			

			raf.seek(4);//posiciona o ponteiro logo apoś os bytes de quantidades de ID

			while(raf.getFilePointer() < raf.length()){
				int tempID = raf.readInt();
				boolean tempLapide = raf.readBoolean();
				int tempTam = raf.readInt();
				if (tempID == registro.getId() && !tempLapide) {
					if(tempTam == registro.getConteudo().toByteArray().length){// verifica se o tamanho é igual 
																			   // ao tamanho do regsitro anterior				   
						raf.write(registro.getConteudo().toByteArray());//reescreve o conteudo do registro
						resp = true;
					}
					else{
						raf.seek(raf.getFilePointer() - 5);//posiciona o ponteiro para atualizar a lapide
						raf.writeBoolean(true);
						resp = save(registro);
					}
				}
				else{
					raf.skipBytes(tempTam);
				}
				
			}
		} catch (Exception e) {
			MyIO.println("ERROR ao atualizar o registro: " + e.getMessage());
		}
		return resp;
	}

	// Delete --------------------------

	@Override
	public boolean delete(int id) throws Exception {
		boolean resp = false;

		try(RandomAccessFile raf = new RandomAccessFile(Arquivo, "rw")) {
			
			raf.seek(4);//posiciona o ponteiro logo apoś os bytes de quantidades de ID

			while(raf.getFilePointer() < raf.length()){
				int tempID = raf.readInt();
				boolean tempLapide = raf.readBoolean();
				int tempTam = raf.readInt();
				if (tempID == id && !tempLapide) {
					raf.seek(raf.getFilePointer() - 5);//posiciona o ponteiro para atualizar a lapide
					raf.writeBoolean(true);
					resp = true;
					break;
				}
				else{
					raf.skipBytes(tempTam);
				}
			}
		} catch (Exception e) {
			MyIO.println("ERROR ao excluir o registro: " + e.getMessage());
		}

		return resp;
	}

	// métodos complementares ------------------------------------------------

	// Load --------------------------
	@Override
	public void load(String origem) throws Exception {
		deleteArq();
		initArq();
		BufferedReader br = new BufferedReader(new FileReader(origem));
		String line = br.readLine();
		line = br.readLine();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		while (line != null) {
			String[] parts = line.split(";");
			String[] generos = parts[5].split(",");			

			List<String> genres = new ArrayList<>();

			for (int i = 0; i < generos.length; i++) {
				genres.add(generos[i]);
			}
			Video video = new Video(parts[1], parts[2], sdf.parse(parts[3]),Integer.parseInt(parts[4]), genres);
			Registro registro = new Registro(Integer.parseInt(parts[0]), false, video);
			mostrar(registro);
			save(registro);
			line = br.readLine();
		}

		br.close();
	}

	// save --------------------------

	@Override
	public boolean save(Registro registro) throws Exception{
		boolean criado = false;
		try(RandomAccessFile raf = new RandomAccessFile(Arquivo, "rw")) {
			

			dados.setUltimoId(dados.getUltimoId() + 1);//atualiza a quantidade máxima de registros
			raf.seek(0);//posiciona o ponteiro na posição 0 do arquivo
			raf.writeInt(dados.getUltimoId());//atualiza a quantidade máxima de registros
			long fim = raf.length();//verifica o tamanho do arquivo
			raf.seek(fim);//posiciona o ponteiro para o fim do arquivo

			/**
			 * padrão de escrita em arquivo:
			 * >> id do registro
			 * >> verificador de exclusão de registro (lápide)
			 * >> identificador de tamanho do resgistro
			 * >> vetor de bytes relativos às variáveis do registro
			 */
			raf.writeInt(registro.getId());//escreve id
			raf.writeBoolean(registro.isLapide());//escreve lapide
			raf.writeInt(registro.getConteudo().toByteArray().length);//escreve tamanho do vetor de bytes
			raf.write(registro.getConteudo().toByteArray());//escreve o conteudo do registro
			
			
			criado = true;
		} catch (Exception e) {
			throw new Exception("ERRO ao inserir o registro: " + e.getMessage());
			
		}
		return criado;
	}

	// Iniciar Arquivo -----------------
	@Override
	public void initArq() throws Exception {
		RandomAccessFile raf;

		try {
			raf = new RandomAccessFile(Arquivo, "rw");
			
			raf.writeInt(0);//4



			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deleteArq(){
	File file = new File(Arquivo);

		try {
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mostrar(Registro r){
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		
		MyIO.println("ID >> "+r.getId());
		MyIO.println("Tipo >> " + r.getConteudo().getTipo());
		MyIO.println("Titulo >> " + r.getConteudo().getTitulo());
		MyIO.println("Data de publicação >> "+ sdf.format(r.getConteudo().getDataPublicacao()));
		MyIO.println("Ano de lançamento >> " + r.getConteudo().getAnoLancamento());
		MyIO.println("Generos >> "+ r.getConteudo().getGeneros());

	}

	public boolean checkArqLength(){
		return Arquivo.length() > 4;
	}


	//--------------------------------------------------------


}
