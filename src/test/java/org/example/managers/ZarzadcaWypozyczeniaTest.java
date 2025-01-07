package org.example.managers;

import org.example.model.Nauczyciel;
import org.example.model.Wolumin;
import org.example.model.Wypozyczajacy;
import org.example.model.Wypozyczenie;
import org.junit.jupiter.api.*;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ZarzadcaWypozyczeniaTest {

    private static ZarzadcaWypozyczenia zarzadcaWypozyczenia;
    private static ZarzadcaWypozyczajacy zarzadcaWypozyczajacy;
    private static ZarzadcaWoluminu zarzadcaWoluminu;

    @BeforeAll
    static void initAll() {
        // Inicjujemy wszystkie zarządców, bo chcemy w testach
        // najpierw utworzyć Wypozyczajacy i Wolumin
        zarzadcaWypozyczenia = new ZarzadcaWypozyczenia();
        zarzadcaWypozyczajacy = new ZarzadcaWypozyczajacy();
        zarzadcaWoluminu = new ZarzadcaWoluminu();
    }

    @AfterAll
    static void closeAll() {
        // zamykamy
        zarzadcaWypozyczenia.close();
        zarzadcaWypozyczajacy.close();
        zarzadcaWoluminu.close();
    }

    @Test
    void testCRUD() {
        // 1) Najpierw wstawiamy Wypozyczajacy (używając ZarzadcaWypozyczajacy)
        Nauczyciel n = new Nauczyciel(0.5, 14, 5, "dr hab.");
        Wypozyczajacy w = new Wypozyczajacy(null, "Jan Kowalski", Instant.now(), "Adres 123");
        zarzadcaWypozyczajacy.dodajWypozyczajacyZNauczycielem(w, n);

        Wypozyczajacy foundWyp = zarzadcaWypozyczajacy.znajdzWypozyczajacy(w.getWypozyczajacyId());
        assertNotNull(foundWyp);

        // 2) Wstawiamy Wolumin
        Wolumin wol = new Wolumin("WydTest", "PL", "Tytuł wol");
        zarzadcaWoluminu.dodajWolumin(wol);

        Wolumin foundWol = zarzadcaWoluminu.znajdzWolumin(wol.getWoluminId());
        assertNotNull(foundWol);

        // 3) Tworzymy Wypozyczenie z kluczami
        Wypozyczenie wypoz = new Wypozyczenie(
                foundWyp.getWypozyczajacyId(),
                foundWol.getWoluminId()
        );
        zarzadcaWypozyczenia.dodajWypozyczenie(wypoz);

        // READ
        Wypozyczenie found = zarzadcaWypozyczenia.znajdzWypozyczenie(wypoz.getWypozyczenieId());
        assertNotNull(found, "Powinno wstawić i odczytać wypozyczenie");
        assertEquals(foundWyp.getWypozyczajacyId(), found.getWypozyczajacyId());
        assertEquals(foundWol.getWoluminId(), found.getWoluminId());

        // UPDATE (zmieniamy dataOd)
        Instant nowaData = Instant.parse("2025-01-01T12:00:00Z");
        found.setDataOd(nowaData);
        zarzadcaWypozyczenia.zaktualizujWypozyczenie(found);

        Wypozyczenie afterUpdate = zarzadcaWypozyczenia.znajdzWypozyczenie(found.getWypozyczenieId());
        assertEquals(nowaData, afterUpdate.getDataOd());

        // DELETE
        zarzadcaWypozyczenia.usunWypozyczenie(afterUpdate);
        Wypozyczenie afterDelete = zarzadcaWypozyczenia.znajdzWypozyczenie(afterUpdate.getWypozyczenieId());
        assertNull(afterDelete, "Powinno być usunięte z bazy");
    }
}
