package org.example.managers;

import com.datastax.oss.driver.api.core.CqlSession;
import org.example.config.CassandraSessionFactory;
import org.example.dao.*;
import org.example.mapper.LibraryMapper;
import org.example.mapper.LibraryMapperBuilder;
import org.example.model.*;

import java.util.UUID;

public class ZarzadcaWypozyczajacy {

    private final CqlSession session;
    private final WypozyczajacyDao wypozyczajacyDao;
    private final TypWypozyczajacyDao typDao;
    private final NauczycielDao nauczycielDao;
    private final UczenDao uczenDao;
    private final PozostaliDao pozostaliDao;

    public ZarzadcaWypozyczajacy() {
        CassandraSessionFactory.initSession();
        this.session = CassandraSessionFactory.getSession();
        LibraryMapper mapper = new LibraryMapperBuilder(session).build();

        wypozyczajacyDao = mapper.wypozyczajacyDao();
        typDao = mapper.typWypozyczajacyDao();
        nauczycielDao = mapper.nauczycielDao();
        uczenDao = mapper.uczenDao();
        pozostaliDao = mapper.pozostaliDao();
    }

    public void dodajWypozyczajacyZTyp(Wypozyczajacy w, TypWypozyczajacy t) {
        typDao.save(t);
        w.setTypId(t.getTypId());
        wypozyczajacyDao.save(w);
    }

    public void dodajWypozyczajacyZNauczycielem(Wypozyczajacy w, Nauczyciel n) {
        nauczycielDao.save(n);
        w.setTypId(n.getTypId());
        wypozyczajacyDao.save(w);
    }

    public void dodajWypozyczajacyZUczen(Wypozyczajacy w, Uczen u) {
        uczenDao.save(u);
        w.setTypId(u.getTypId());
        wypozyczajacyDao.save(w);
    }

    public void dodajWypozyczajacyZPozostali(Wypozyczajacy w, Pozostali p) {
        pozostaliDao.save(p);
        w.setTypId(p.getTypId());
        wypozyczajacyDao.save(w);
    }

    public Wypozyczajacy znajdzWypozyczajacy(UUID id) {
        Wypozyczajacy w = wypozyczajacyDao.findById(id);
        if (w == null) return null;

        UUID typId = w.getTypId();
        if (typId != null) {
            Nauczyciel n = nauczycielDao.findById(typId);
            if (n != null) {
                return w;
            }
            Uczen u = uczenDao.findById(typId);
            if (u != null) {
                return w;
            }
            Pozostali p = pozostaliDao.findById(typId);
            if (p != null) {
                return w;
            }
            TypWypozyczajacy t = typDao.findById(typId);
            // w.setTyp(t);
        }
        return w;
    }

    public void zaktualizujWypozyczajacy(Wypozyczajacy w, Object typObiekt) {
        if (typObiekt instanceof Nauczyciel) {
            nauczycielDao.update((Nauczyciel) typObiekt);
        } else if (typObiekt instanceof Uczen) {
            uczenDao.update((Uczen) typObiekt);
        } else if (typObiekt instanceof Pozostali) {
            pozostaliDao.update((Pozostali) typObiekt);
        } else if (typObiekt instanceof TypWypozyczajacy) {
            typDao.update((TypWypozyczajacy) typObiekt);
        }
        wypozyczajacyDao.update(w);
    }

    public void zaktualizujWypozyczajacy(Wypozyczajacy w) {
        wypozyczajacyDao.update(w);
    }

    public void usunWypozyczajacy(Wypozyczajacy w, Object typObiekt) {
        wypozyczajacyDao.delete(w);
        if (typObiekt instanceof Nauczyciel) {
            nauczycielDao.delete((Nauczyciel) typObiekt);
        } else if (typObiekt instanceof Uczen) {
            uczenDao.delete((Uczen) typObiekt);
        } else if (typObiekt instanceof Pozostali) {
            pozostaliDao.delete((Pozostali) typObiekt);
        } else if (typObiekt instanceof TypWypozyczajacy) {
            typDao.delete((TypWypozyczajacy) typObiekt);
        }
    }

    public void usunWypozyczajacy(Wypozyczajacy w) {
        wypozyczajacyDao.delete(w);
    }

    public void close() {
        CassandraSessionFactory.closeSession();
    }
}
