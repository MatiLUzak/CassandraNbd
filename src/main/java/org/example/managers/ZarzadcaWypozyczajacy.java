package org.example.managers;

import com.datastax.oss.driver.api.core.CqlSession;
import org.example.config.CassandraSessionFactory;
import org.example.dao.WypozyczajacyDao;
import org.example.mapper.LibraryMapper;
import org.example.mapper.LibraryMapperBuilder;
import org.example.model.Wypozyczajacy;

import java.util.UUID;

public class ZarzadcaWypozyczajacy {

    private final CqlSession session;
    private final WypozyczajacyDao wypozyczajacyDao;

    public ZarzadcaWypozyczajacy() {
        // 1) Inicjujemy sesję
        CassandraSessionFactory.initSession();
        // 2) Pobieramy już gotową sesję
        this.session = CassandraSessionFactory.getSession();
        // 3) Tworzymy mapper
        LibraryMapper mapper = new LibraryMapperBuilder(session).build();
        // 4) Pobieramy DAO
        wypozyczajacyDao = mapper.wypozyczajacyDao();
    }

    public void dodajWypozyczajacy(Wypozyczajacy w) {
        wypozyczajacyDao.save(w);
    }

    public Wypozyczajacy znajdzWypozyczajacy(UUID id) {
        return wypozyczajacyDao.findById(id);
    }

    public void zaktualizujWypozyczajacy(Wypozyczajacy w) {
        wypozyczajacyDao.update(w);
    }

    public void usunWypozyczajacy(Wypozyczajacy w) {
        wypozyczajacyDao.delete(w);
    }

    public void close() {
        CassandraSessionFactory.closeSession();
    }
}
