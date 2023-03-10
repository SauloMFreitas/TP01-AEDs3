package CRUD.servicos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import CRUD.entidades.*;

public class DAOTest {
    @Test
    public void testload() throws Exception {
        DAOVideos DAO = new DAOVideosImpl();
        List<Registro> list = new ArrayList<>();
        String file = "";
        DAO.load(file);
        for (int i = 0; i < DAO.getDados().getUltimoId(); i++) {
            list.add(DAO.read(i));
        }

        assertNotNull(list);
        assertFalse(list.isEmpty());

    }

    @Test
    public void testsave() throws Exception{
        DAOVideos DAO = new DAOVideosImpl();
        Video v = new Video("MV", "As aventuras de SharkBoy e lavaGirl", new Date(),2020 ,new ArrayList<>());
        v.getGeneros().add("Aventura");
        Registro r = new Registro(DAO.getDados().getUltimoId(), false, v);
        String arquivo = "";
        DAO.load(arquivo);
        DAO.save(r);
        v.setTitulo("Viagem ao centro da Terra");
        v.setAnoLancamento(2012);
        DAO.create(v);

        Registro re = DAO.read(r.getId());
        Registro reg = DAO.read(r.getId() + 1);

        assertNotNull(re);
        assertEquals(r.getId(), re.getId());
        assertEquals(r.getConteudo().getTitulo(), re.getConteudo().getTitulo());

        assertNotNull(reg);
        assertEquals(v.getTitulo(), reg.getConteudo().getTitulo());
    }

    @Test
    public void testDelete() throws Exception {
        DAOVideos DAO = new DAOVideosImpl();
        Video v = new Video("MV", "As aventuras de SharkBoy e lavaGirl", new Date(),2020 ,new ArrayList<>());
        v.getGeneros().add("Aventura");
        Registro r = new Registro(DAO.getDados().getUltimoId(), false, v);
        String arquivo = "";
        DAO.load(arquivo);
        DAO.save(r);

        Registro re = DAO.read(r.getId());
        DAO.delete(re.getId());

        assertNull(DAO.read(re.getId()));

    }
}
