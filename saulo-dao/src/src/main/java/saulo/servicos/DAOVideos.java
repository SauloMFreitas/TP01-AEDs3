package saulo.servicos;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * AEDS3 - TP01
 * 
 * @author Saulo de Moura Zandona Freitas - Matricula: 775777 - Unidade Coração Eucarístico
 * 
 */

//OBS: video é um registro sem ID, já o registro contem um ID para o video e um marcador de lápide
import saulo.entidades.Registro;
import saulo.entidades.Video;

public interface DAOVideos {
	
	//------------------ Declaração dos métodos do CRUD --------------------------------
	/**
	 * cria uma nova instância no arquivo para um novo registro
	 * @param video video a ser adicionado no arquivo
	 * @return	registro que acabara de ser adicionado no arquivo
	 * @throws Exception
	 */
	Registro Create(Video video) throws Exception;

	/**
	 * lê um registro no arquivo sequencial binário e o converte para registro.
	 * @return registro lido
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	Registro Read(int id) throws IOException, ClassNotFoundException;
	
	/**
	 * atualiza um registro na base de dados
	 * @param video registro atualizado
	 * @return true >> video atualizado com sucesso | false >> video não atualizado
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	boolean Update(Registro video) throws FileNotFoundException, Exception;

	/**
	 * Remove o video passado como parâmetro da lista
	 * @param id id do video a ser deletado
	 * @return true >> video deletado com sucesso | false >> video não deletado
	 * @throws Exception
	 */
	boolean Delete(int id) throws Exception;

	//------------------------------------------------------------------------------------

	//---------------------- métodos complementares ao CRUD ------------------------------
	/**
	 * carrega todos os dados do arquivo .csv para o arquivo .bd
	 * @param origem caminho para o arquivo .csv
	 * @throws Exception
	 */
	void Load(String origem) throws Exception;
	

	/**
	 * salva um novo registro no arquivo
	 * @param registro registro a ser adicionado no registro
	 * @return 
	 * @throws Exception
	 */
	boolean Save(Registro registro) throws Exception;

	/**
	 * inicializa o arquivo sequencial
	 * @throws Exception
	 */
	void initArq() throws Exception;

	/**
	 * deleta o arquivo existente
	 */
	void DeleteArq();

	/**
	 * mostra o registro no console
	 * @param r registro a ser mostrado
	 */
	void mostrar(Registro r);
	//-------------------------------------------------------------------------------------
}
