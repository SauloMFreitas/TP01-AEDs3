package CRUD.entidades;

/**
 * AEDS3 - TP01
 * 
 * @author Saulo de Moura Zandona Freitas - Matricula: 775777 - Unidade Coração Eucarístico
 * @author Joel Franco da Mata            - Matricula: 728003 - Unidade Praça da Liberdade
 * 
 * criação de um registro (ID, lápide e o conteudo)
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

public class Registro implements Serializable {

    private int id;

    private boolean Lapide = false;

    private Video conteudo;

    public Registro(int id, boolean lapide, Video conteudo) {
        this.id = id;
        Lapide = lapide;
        this.conteudo = conteudo;
    }

    public Registro(){
        this.id = -1;
        this.Lapide = false;
        this.conteudo = new Video();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLapide() {
        return Lapide;
    }

    public void setLapide(boolean Lapide) {
        this.Lapide = Lapide;
    }

    public Video getConteudo() {
        return conteudo;
    }

    public void setConteudo(Video conteudo) {
        this.conteudo = conteudo;
    }
    

    public byte[] toByteArray() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    
    dos.writeInt(this.id);
    dos.writeBoolean(this.Lapide);
    dos.write(conteudo.toByteArray());
    return baos.toByteArray();
    }
}
