package org.example.managers;

import org.example.model.*;
import org.junit.jupiter.api.*;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class ZarzadcaWypozyczajacyTest {

    private static ZarzadcaWypozyczajacy zarzadca;

    @BeforeAll
    static void initAll() {
        zarzadca = new ZarzadcaWypozyczajacy();
    }

    @AfterAll
    static void closeAll() {
        zarzadca.close();
    }

    @Test
    void testCRUDNauczyciel() {
        Nauczyciel n = new Nauczyciel(0.5, 14, 5, "dr hab.");
        Wypozyczajacy w = new Wypozyczajacy(null, "Adam Nauczyciel", Instant.now(), "Adres 999");
        zarzadca.dodajWypozyczajacyZNauczycielem(w, n);

        Wypozyczajacy foundW = zarzadca.znajdzWypozyczajacy(w.getWypozyczajacyId());
        assertNotNull(foundW);
        assertNotNull(foundW.getTypId());


        w.setNazwa("Nowe Nazwisko");
        n.setTytul("prof.");
        zarzadca.zaktualizujWypozyczajacy(w, n);

        Wypozyczajacy afterUpdate = zarzadca.znajdzWypozyczajacy(w.getWypozyczajacyId());
        assertEquals("Nowe Nazwisko", afterUpdate.getNazwa());

        zarzadca.usunWypozyczajacy(w, n);

        Wypozyczajacy afterDelete = zarzadca.znajdzWypozyczajacy(w.getWypozyczajacyId());
        assertNull(afterDelete);
    }

    @Test
    void testCRUDBazowyTyp() {
        TypWypozyczajacy t = new TypWypozyczajacy(0.3, 10, 3);
        Wypozyczajacy w = new Wypozyczajacy(null, "Adam Bazowy", Instant.now(), "Bazowy Adres");
        zarzadca.dodajWypozyczajacyZTyp(w, t);

        Wypozyczajacy found = zarzadca.znajdzWypozyczajacy(w.getWypozyczajacyId());
        assertNotNull(found);
        assertNotNull(found.getTypId());

        w.setNazwa("Zmieniona Bazowa");
        t.setKara(0.9); // np. wyższa kara
        zarzadca.zaktualizujWypozyczajacy(w, t);

        Wypozyczajacy afterUpdate = zarzadca.znajdzWypozyczajacy(w.getWypozyczajacyId());
        assertEquals("Zmieniona Bazowa", afterUpdate.getNazwa());

        zarzadca.usunWypozyczajacy(w, t);

        Wypozyczajacy afterDelete = zarzadca.znajdzWypozyczajacy(w.getWypozyczajacyId());
        assertNull(afterDelete);
    }
}
