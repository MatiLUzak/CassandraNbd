package org.example.managers;

import com.datastax.oss.driver.api.core.CqlSession;
import org.example.config.CassandraSessionFactory;
import org.example.dao.WoluminDao;
import org.example.mapper.LibraryMapper;
import org.example.mapper.LibraryMapperBuilder;
import org.example.model.Wolumin;

import java.util.UUID;

public class ZarzadcaWoluminu {

    private final CqlSession session;
    private final WoluminDao woluminDao;

    public ZarzadcaWoluminu() {
        // 1) Inicjujemy sesję w CassandraSessionFactory
        CassandraSessionFactory.initSession();

        // 2) Pobieramy istniejącą sesję
        this.session = CassandraSessionFactory.getSession();

        // 3) Tworzymy mapper
        LibraryMapper mapper = new LibraryMapperBuilder(session).build();

        // 4) Pobieramy DAO z mappera
        woluminDao = mapper.woluminDao();
    }

    public void dodajWolumin(Wolumin wolumin) {
        woluminDao.save(wolumin);
    }

    public Wolumin znajdzWolumin(UUID id) {
        return woluminDao.findById(id);
    }

    public void zaktualizujWolumin(Wolumin wolumin) {
        woluminDao.update(wolumin);
    }

    public void usunWolumin(Wolumin wolumin) {
        woluminDao.delete(wolumin);
    }

    public void close() {
        // Zamiast session.close() – używamy metody z CassandraSessionFactory
        CassandraSessionFactory.closeSession();
    }
}
