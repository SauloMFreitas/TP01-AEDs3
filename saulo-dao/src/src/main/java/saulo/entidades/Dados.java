package saulo.entidades;

/**
 * AEDS3 - TP01
 * 
 * @author Saulo de Moura Zandona Freitas - Matricula: 775777 - Unidade Coração Eucarístico
 * 
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dados implements Serializable {
    
    private int ultimoId;

    private List<Registro> registros = new ArrayList<>();

    public Dados() {
        this.ultimoId = 0;
        this.registros = new ArrayList<>();
    }

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

}
