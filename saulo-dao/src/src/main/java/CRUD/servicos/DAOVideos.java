package CRUD.servicos;

/**
 * AEDS3 - TP01
 * 
 * @author Saulo de Moura Zandona Freitas - Matricula: 775777 - Unidade Coração Eucarístico
 * @author Joel Franco da Mata            - Matricula: 728003 - Unidade Praça da Liberdade
 * 
 * interface para utilizar no CRUD
 * 
 */

import java.io.FileNotFoundException;
import java.io.IOException;

import CRUD.entidades.Dados;
import CRUD.entidades.Registro;
import CRUD.entidades.Video;

public interface DAOVideos {
	
	public Dados getDados();
	//------------------ Declaração dos métodos do CRUD --------------------------------
	/**
	 * cria uma nova instância no arquivo para um novo registro
	 * @param video video a ser adicionado no arquivo
	 * @return	registro que acabara de ser adicionado no arquivo
	 * @throws Exception
	 */
	Registro create(Video video) throws Exception;

	/**
	 * lê um registro no arquivo sequencial binário e o converte para registro.
	 * @return registro lido
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	Registro read(int id) throws IOException, ClassNotFoundException;
	
	/**
	 * atualiza um registro na base de dados
	 * @param video registro atualizado
	 * @return true >> video atualizado com sucesso | false >> video não atualizado
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	boolean update(Registro video) throws FileNotFoundException, Exception;

	/**
	 * Remove o video passado como parâmetro da lista
	 * @param id id do video a ser deletado
	 * @return true >> video deletado com sucesso | false >> video não deletado
	 * @throws Exception
	 */
	boolean delete(int id) throws Exception;

	//------------------------------------------------------------------------------------

	//---------------------- métodos complementares ao CRUD ------------------------------
	/**
	 * carrega todos os dados do arquivo .csv para o arquivo .bd
	 * @param origem caminho para o arquivo .csv
	 * @throws Exception
	 */
	void load(String origem) throws Exception;
	

	/**
	 * salva um novo registro no arquivo
	 * @param registro registro a ser adicionado no registro
	 * @return 
	 * @throws Exception
	 */
	boolean save(Registro registro) throws Exception;

	/**
	 * inicializa o arquivo sequencial
	 * @throws Exception
	 */
	void initArq() throws Exception;

	/**
	 * deleta o arquivo existente
	 */
	void deleteArq();

	/**
	 * mostra o registro no console
	 * @param r registro a ser mostrado
	 */
	void mostrar(Registro r);

	/**
	 * verifica se o arquivo possui conteudo ou não
	 * @return true  - possui conteudo
	 * 		   false - não possui conteudo
	 */
	boolean checkArqLength();
	//-------------------------------------------------------------------------------------
}
