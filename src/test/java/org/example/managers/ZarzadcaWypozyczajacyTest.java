package org.example.managers;

import org.example.model.Wypozyczajacy;
import org.junit.jupiter.api.*;

import java.time.Instant;
import java.util.UUID;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ZarzadcaWypozyczajacyTest {

    private static ZarzadcaWypozyczajacy zarzadca;

    @BeforeAll
    static void initAll() {
        // 1) Tworzymy obiekt zarządcy
        zarzadca = new ZarzadcaWypozyczajacy();
    }

    @AfterAll
    static void closeAll() {
        // 2) zamykamy
        zarzadca.close();
    }

    @Test
    void testCRUD() {
        // Tworzymy wypozyczajacego
        Wypozyczajacy w = new Wypozyczajacy(
                null, // typId = null? w realnym scenariuszu dajemy UUID
                "Jan Kowalski",
                Instant.now(),
                "Adres 123"
        );
        // Ponieważ w Twoim kodzie jest 'typWypozyczajacy' obiektem?
        // docelowo powinniśmy dać 'typId' albo coś innego.
        // Na razie załóżmy 'null' i zobaczymy czy przejdzie walidacja.
        // ewentualnie wykomentuj walidacje, by test był "przykładowy".

        // CREATE
        zarzadca.dodajWypozyczajacy(w);

        // READ
        Wypozyczajacy found = zarzadca.znajdzWypozyczajacy(w.getWypozyczajacyId());
        assertNotNull(found, "Powinno odnaleźć wypozyczajacego w bazie");
        assertEquals("Jan Kowalski", found.getNazwa());

        // UPDATE
        found.setNazwa("Nowe Imię");
        zarzadca.zaktualizujWypozyczajacy(found);

        Wypozyczajacy afterUpdate = zarzadca.znajdzWypozyczajacy(found.getWypozyczajacyId());
        assertEquals("Nowe Imię", afterUpdate.getNazwa());

        // DELETE
        zarzadca.usunWypozyczajacy(afterUpdate);

        Wypozyczajacy afterDelete = zarzadca.znajdzWypozyczajacy(afterUpdate.getWypozyczajacyId());
        assertNull(afterDelete, "Po usunięciu nie powinno być w bazie");
    }
}
