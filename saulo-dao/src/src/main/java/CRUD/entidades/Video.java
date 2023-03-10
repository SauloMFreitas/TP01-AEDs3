package CRUD.entidades;

/**
 * AEDS3 - TP01
 * 
 * @author Saulo de Moura Zandona Freitas - Matricula: 775777 - Unidade Coração Eucarístico
 * @author Joel Franco da Mata            - Matricula: 728003 - Unidade Praça da Liberdade
 * 
 * criação do conteudo do registro
 * 
 */

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * AEDS3 - TP01
 * 
 * @author Saulo de Moura Zandona Freitas - Matricula: 775777 - Unidade Coração Eucarístico
 * 
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Video implements Serializable {
	
	private String tipo;

	private String titulo;
	
	private Date dataPublicacao;
	
	private int anoLancamento;
	
	private List<String> generos;

	

	public Video() {
		this.tipo = "";
		this.titulo = "";
		this.dataPublicacao = null;
		this.anoLancamento = -1;
		this.generos = new ArrayList<>();
	}

	public Video(String tipo, String titulo, Date dataPublicacao, int anoLancamento, List<String> generos) {
		this.tipo = tipo;
		this.titulo = titulo;
		this.dataPublicacao = dataPublicacao;
		this.anoLancamento = anoLancamento;
		this.generos = generos;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public List<String> getGeneros() {
		return generos;
	}

	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}

	public byte[] toByteArray() throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);

		dos.writeUTF(this.getTipo());//String de tamanho fixo
		dos.writeUTF(this.getTitulo());//String de tamanho variável
		dos.writeLong(this.getDataPublicacao().getTime());//data em long (valor é baseado no tempo após 1 de janeiro de 1970)
		dos.writeInt(this.getAnoLancamento());//ano de lancamento do video
		dos.writeInt(this.getGeneros().size());//qunatidade de generos existentes no registro
		for (int i = 0; i < this.getGeneros().size(); i++) {
			dos.writeUTF(this.getGeneros().get(i));//String do genero a ser adicionado
		}

		return baos.toByteArray();
	}

}
