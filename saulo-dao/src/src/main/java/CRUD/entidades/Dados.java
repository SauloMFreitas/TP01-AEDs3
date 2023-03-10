package CRUD.entidades;

/**
 * AEDS3 - TP01
 * 
 * @author Saulo de Moura Zandona Freitas - Matricula: 775777 - Unidade Coração Eucarístico
 * @author Joel Franco da Mata            - Matricula: 728003 - Unidade Praça da Liberdade
 * 
 * Dados gerais do arquivo
 * 
 */


import java.io.Serializable;

public class Dados implements Serializable {
    
    private int ultimoId;
    public Dados() {
        this.ultimoId = 0;
    }

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

}
