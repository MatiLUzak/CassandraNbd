package org.example.managers;

import org.example.model.Wolumin;
import org.junit.jupiter.api.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ZarzadcaWoluminuTest {

    private static ZarzadcaWoluminu zarzadca;

    @BeforeAll
    static void initAll() {
        // utworzymy obiekt zarządcy
        zarzadca = new ZarzadcaWoluminu();
    }

    @AfterAll
    static void closeAll() {
        // zamknięcie sesji
        zarzadca.close();
    }

    @Test
    void testCRUD() {
        // 1) CREATE
        Wolumin w = new Wolumin("PWN", "PL", "Tytuł testowy");
        zarzadca.dodajWolumin(w);

        // 2) READ
        Wolumin found = zarzadca.znajdzWolumin(w.getWoluminId());
        assertNotNull(found);
        assertEquals("Tytuł testowy", found.getTytul());

        // 3) UPDATE
        found.setTytul("Zmieniony tytuł");
        zarzadca.zaktualizujWolumin(found);

        Wolumin afterUpdate = zarzadca.znajdzWolumin(w.getWoluminId());
        assertEquals("Zmieniony tytuł", afterUpdate.getTytul());

        // 4) DELETE
        zarzadca.usunWolumin(afterUpdate);

        Wolumin afterDelete = zarzadca.znajdzWolumin(w.getWoluminId());
        assertNull(afterDelete);
    }
}
