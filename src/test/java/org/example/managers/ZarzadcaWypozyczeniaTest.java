package org.example.managers;

import org.example.model.Wypozyczenie;
import org.junit.jupiter.api.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ZarzadcaWypozyczeniaTest {

    private static ZarzadcaWypozyczenia zarzadca;

    @BeforeAll
    static void initAll() {
        zarzadca = new ZarzadcaWypozyczenia();
    }

    @AfterAll
    static void closeAll() {
        zarzadca.close();
    }

    @Test
    void testCRUD() {
        // Stworzymy obiekt Wypozyczenie z polami:
        // 'wypozyczajacy' i 'wolumin' w Twoim modelu to obiekty,
        // a w cassandrze to raczej powinno być UUID,
        // ale załóżmy, że mamy minimalny test (może wywalić się jeśli brak adaptera).

        Wypozyczenie w = new Wypozyczenie();
        // w oryginalnym konstruktorze: Wypozyczenie(Wypozyczajacy w, Wolumin wol)
        // musielibyśmy wypełnić obiekty -> co raczej wymaga 2 zapisań.
        // By uprościć test "przykładowy", skorzystaj z pustego konstruktora + settery (jeśli wstawiłeś?).

        zarzadca.dodajWypozyczenie(w); // CREATE

        Wypozyczenie found = zarzadca.znajdzWypozyczenie(w.getWypozyczenieId());
        assertNotNull(found, "Powinno wstawić i odczytać");
        // Możesz sprawdzić dataOd etc.

        // UPDATE
        Instant nowaData = LocalDateTime.of(2025, 1, 1, 10, 15, 30).toInstant(ZoneOffset.UTC);
        found.setDataOd(nowaData);
        zarzadca.zaktualizujWypozyczenie(found);

        Wypozyczenie afterUpdate = zarzadca.znajdzWypozyczenie(w.getWypozyczenieId());
        assertEquals(nowaData, afterUpdate.getDataOd());

        // DELETE
        zarzadca.usunWypozyczenie(found);
        Wypozyczenie afterDelete = zarzadca.znajdzWypozyczenie(found.getWypozyczenieId());
        assertNull(afterDelete);
    }
}
