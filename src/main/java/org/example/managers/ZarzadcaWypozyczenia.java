package org.example.managers;

import com.datastax.oss.driver.api.core.CqlSession;
import org.example.config.CassandraSessionFactory;
import org.example.dao.WypozyczenieDao;
import org.example.mapper.LibraryMapper;
import org.example.mapper.LibraryMapperBuilder;
import org.example.model.Wypozyczenie;

import java.util.UUID;

public class ZarzadcaWypozyczenia {
    private final CqlSession session;
    private final WypozyczenieDao wypozyczenieDao;

    public ZarzadcaWypozyczenia() {
        // 1) Inicjujemy sesję w fabryce
        CassandraSessionFactory.initSession();
        // 2) Pobieramy ją
        this.session = CassandraSessionFactory.getSession();
        // 3) Mapper
        LibraryMapper mapper = new LibraryMapperBuilder(session).build();
        // 4) DAO
        wypozyczenieDao = mapper.wypozyczenieDao();
    }

    public void dodajWypozyczenie(Wypozyczenie w) {
        wypozyczenieDao.save(w);
    }

    public Wypozyczenie znajdzWypozyczenie(UUID id) {
        return wypozyczenieDao.findById(id);
    }

    public void zaktualizujWypozyczenie(Wypozyczenie w) {
        wypozyczenieDao.update(w);
    }

    public void usunWypozyczenie(Wypozyczenie w) {
        wypozyczenieDao.delete(w);
    }

    public void close() {
        CassandraSessionFactory.closeSession();
    }
}
