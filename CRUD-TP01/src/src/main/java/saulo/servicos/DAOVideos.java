package saulo.servicos;

import java.io.File;
import java.util.List;

import saulo.entidades.Video;

public interface DAOVideos {
	
	void carregar(File file);
	
	/**
	 * Passa a instância como parâmetro e ela vai ser persistida na 
	 * fonte de dados
	 * @param video
	 * @return
	 */
	Video salvar(Video video);
	
	/**
	 * O código passado é usado para retornar
	 * a instância do Video que possua o mesmo codigo
	 * @param codigo
	 * @return
	 */
	Video acharPorCodigo(String codigo);
	
	/**
	 * Remove o video passado como parâmetro da lista
	 * @param video
	 * @return
	 */
	boolean remover(Video video);
	
	/**
	 * Retorna todos os vídeos presentes na fonte de dados
	 * @return
	 */
	List<Video> todos();

}
