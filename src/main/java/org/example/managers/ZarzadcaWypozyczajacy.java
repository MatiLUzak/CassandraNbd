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

    // ================= CREATE =================
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

    // ================= READ =================
    public Wypozyczajacy znajdzWypozyczajacy(UUID id) {
        Wypozyczajacy w = wypozyczajacyDao.findById(id);
        if (w == null) return null;

        UUID typId = w.getTypId();
        if (typId != null) {
            // Spróbuj nauczyciel
            Nauczyciel n = nauczycielDao.findById(typId);
            if (n != null) {
                // ewentualnie w.setTyp(n);
                return w;
            }
            // Spróbuj uczen
            Uczen u = uczenDao.findById(typId);
            if (u != null) {
                return w;
            }
            // Spróbuj pozostali
            Pozostali p = pozostaliDao.findById(typId);
            if (p != null) {
                return w;
            }
            // Domyślnie bazowy typ
            TypWypozyczajacy t = typDao.findById(typId);
            // w.setTyp(t);
        }
        return w;
    }

    // ================= UPDATE =================
    /**
     * Aktualizujemy obiekt Wypozyczajacy, wraz z jego subklasą (jeśli jest).
     * Wymaga wiedzy, czy w typId jest Nauczyciel, Uczen czy Pozostali.
     */
    public void zaktualizujWypozyczajacy(Wypozyczajacy w, Object typObiekt) {
        // 1) jeśli subklasa => update w odpowiedniej tabeli
        if (typObiekt instanceof Nauczyciel) {
            nauczycielDao.update((Nauczyciel) typObiekt);
        } else if (typObiekt instanceof Uczen) {
            uczenDao.update((Uczen) typObiekt);
        } else if (typObiekt instanceof Pozostali) {
            pozostaliDao.update((Pozostali) typObiekt);
        } else if (typObiekt instanceof TypWypozyczajacy) {
            typDao.update((TypWypozyczajacy) typObiekt);
        }
        // 2) update samego Wypozyczajacy
        wypozyczajacyDao.update(w);
    }

    // Overload: jeśli nie potrzebujesz subklasy
    public void zaktualizujWypozyczajacy(Wypozyczajacy w) {
        // tu tylko update Wypozyczajacy, bez subklasy
        wypozyczajacyDao.update(w);
    }

    // ================= DELETE =================
    /**
     * Usuwamy Wypozyczajacy oraz (opcjonalnie) subklasę w typie.
     * Uważaj, czy ten typ nie jest referencjonowany przez innych Wypozyczajacy.
     */
    public void usunWypozyczajacy(Wypozyczajacy w, Object typObiekt) {
        // 1) usuwamy Wypozyczajacy
        wypozyczajacyDao.delete(w);
        // 2) usuwamy subklasę
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
        // usuwa tylko Wypozyczajacy, nie rusza subklasy
        wypozyczajacyDao.delete(w);
    }

    public void close() {
        CassandraSessionFactory.closeSession();
    }
}
