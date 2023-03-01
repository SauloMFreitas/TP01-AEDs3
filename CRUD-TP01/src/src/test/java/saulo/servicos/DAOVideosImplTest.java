package saulo.servicos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import saulo.entidades.Video;

/**
 * Coisas que você vai precisar Saulo
 * 
 * java.io.File java.io.InputStream
 * BufferedReader (pra ler linha a linha o arquivo)
 * 
 * função split de String, pra quebrar a linha em componentes separados por ponto e vírgula
 * @author kicolobo
 *
 */
public class DAOVideosImplTest {

	private DAOVideos getDao() {
		return new DAOVideosImpl();
	}
	
	@Test
	public void testCarregar() {
		
		DAOVideos dao = getDao();
		File arquivo = new File("/caminho/pro/arquivo");
		dao.carregar(arquivo);
		
		List<Video> conteudo = dao.todos();
		assertNotNull(conteudo);
		assertFalse(conteudo.isEmpty());
		
	}

	@Test
	public void testSalvar() {
		
		Video video = new Video();
		video.setAnoLancamento(2023);
		video.setTitulo("Titulo de teste");
		video.setId(UUID.randomUUID().toString());
		video.setDataPublicacao(new Date());
		video.setTipo(UUID.randomUUID().toString());
		video.setGeneros(new ArrayList());
		video.getGeneros().add("Faroeste");
		
		DAOVideos dao = getDao();
		File arquivo = new File("/caminho/pro/arquivo");
		dao.carregar(arquivo);
		
		dao.salvar(video);
		
		Video persistido = dao.acharPorCodigo(video.getId());
		assertNotNull(persistido);
		assertEquals(video.getId(), persistido.getId());
		assertEquals(video.getTitulo(), persistido.getTitulo());
		
	}

	

	@Test
	public void testRemover() {
		Video video = new Video();
		video.setAnoLancamento(2023);
		video.setTitulo("Titulo de teste");
		video.setId(UUID.randomUUID().toString());
		video.setDataPublicacao(new Date());
		video.setTipo(UUID.randomUUID().toString());
		video.setGeneros(new ArrayList());
		video.getGeneros().add("Faroeste");
		
		DAOVideos dao = getDao();
		File arquivo = new File("/caminho/pro/arquivo");
		dao.carregar(arquivo);
		
		dao.salvar(video);
		
		Video persistido = dao.acharPorCodigo(video.getId());
		
		
		dao.remover(video);
		// registro não pode mais existir
		assertNull(dao.acharPorCodigo(video.getId()));
	}

	

}
