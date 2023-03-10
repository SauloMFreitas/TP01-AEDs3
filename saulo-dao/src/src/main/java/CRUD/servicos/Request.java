package CRUD.servicos;

import CRUD.entidades.*;
public interface Request {
    public Video    rCreate() throws Exception;
    public int      rRead();
    public Registro rUpdate() throws Exception;
    public int      rDelete() throws Exception;
}
