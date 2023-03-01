package saulo.entidades;

import java.util.Date;
import java.util.List;

public class Video {
	
	private int id;

	private String tipo;
	
	private String titulo;
	
	private Date dataPublicacao;
	
	private long anoLancamento;
	
	private List<String> generos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public long getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(long anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public List<String> getGeneros() {
		return generos;
	}

	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}

}
