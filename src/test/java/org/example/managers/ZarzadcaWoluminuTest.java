package org.example.managers;

import org.example.model.*;
import org.junit.jupiter.api.*;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ZarzadcaWoluminuTest {

    private static ZarzadcaWoluminu zarzadca;

    @BeforeAll
    static void initAll() {
        zarzadca = new ZarzadcaWoluminu();
    }

    @AfterAll
    static void closeAll() {
        zarzadca.close();
    }

    @Test
    void testWoluminCRUD() {
        // 1) CREATE
        Wolumin w = new Wolumin("PWN", "PL", "Tytuł woluminu");
        zarzadca.dodajWolumin(w);

        // READ
        Wolumin found = zarzadca.znajdzWolumin(w.getWoluminId());
        assertNotNull(found);
        assertEquals("Tytuł woluminu", found.getTytul());

        // UPDATE
        found.setTytul("Zmieniony wolumin");
        zarzadca.zaktualizujWolumin(found);

        Wolumin afterUpdate = zarzadca.znajdzWolumin(w.getWoluminId());
        assertEquals("Zmieniony wolumin", afterUpdate.getTytul());

        // DELETE
        zarzadca.usunWolumin(afterUpdate);
        Wolumin afterDelete = zarzadca.znajdzWolumin(w.getWoluminId());
        assertNull(afterDelete);
    }

    @Test
    void testKsiazkaCRUD() {
        Ksiazka k = new Ksiazka("WydawnictwoX", "ENG", "Tytuł ksiązki",
                List.of("Autor1", "Autor2"));
        zarzadca.dodajKsiazka(k);

        Ksiazka found = zarzadca.znajdzKsiazka(k.getWoluminId());
        assertNotNull(found);
        assertEquals(2, found.getAutor().size());

        found.setAutor(List.of("AutorNowy"));
        zarzadca.zaktualizujKsiazka(found);

        Ksiazka afterUpdate = zarzadca.znajdzKsiazka(k.getWoluminId());
        assertEquals(1, afterUpdate.getAutor().size());

        zarzadca.usunKsiazka(afterUpdate);
        Ksiazka afterDelete = zarzadca.znajdzKsiazka(k.getWoluminId());
        assertNull(afterDelete);
    }

    @Test
    void testBeletrystykaCRUD() {
        Beletrystyka b = new Beletrystyka("WydTest", "PL", "TytBeletr",
                List.of("AutorB1"), "Dorośli", "Powieść");
        zarzadca.dodajBeletrystyka(b);

        Beletrystyka found = zarzadca.znajdzBeletrystyka(b.getWoluminId());
        assertNotNull(found);
        assertEquals("Powieść", found.getRodzaj());

        found.setRodzaj("Opowiadanie");
        zarzadca.zaktualizujBeletrystyka(found);

        Beletrystyka afterUpdate = zarzadca.znajdzBeletrystyka(b.getWoluminId());
        assertEquals("Opowiadanie", afterUpdate.getRodzaj());

        zarzadca.usunBeletrystyka(afterUpdate);
        Beletrystyka afterDelete = zarzadca.znajdzBeletrystyka(b.getWoluminId());
        assertNull(afterDelete);
    }

    @Test
    void testNaukowaCRUD() {
        Naukowa n = new Naukowa("WydNauk", "PL", "TytulNauk",
                List.of("NaukAutor"), "RecenzjaX", "DziedzinaFizyka");
        zarzadca.dodajNaukowa(n);

        Naukowa found = zarzadca.znajdzNaukowa(n.getWoluminId());
        assertNotNull(found);
        assertEquals("RecenzjaX", found.getRecenzja());

        found.setRecenzja("RecenzjaZmieniona");
        zarzadca.zaktualizujNaukowa(found);

        Naukowa afterUpdate = zarzadca.znajdzNaukowa(n.getWoluminId());
        assertEquals("RecenzjaZmieniona", afterUpdate.getRecenzja());

        zarzadca.usunNaukowa(afterUpdate);
        Naukowa afterDelete = zarzadca.znajdzNaukowa(n.getWoluminId());
        assertNull(afterDelete);
    }

    @Test
    void testCzasopismoCRUD() {
        Czasopismo c = new Czasopismo("WydCzas", "FR", "GazetaTest", "Nr42");
        zarzadca.dodajCzasopismo(c);

        Czasopismo found = zarzadca.znajdzCzasopismo(c.getWoluminId());
        assertNotNull(found);
        assertEquals("Nr42", found.getNrWydania());

        found.setNrWydania("Nr43");
        zarzadca.zaktualizujCzasopismo(found);

        Czasopismo afterUpdate = zarzadca.znajdzCzasopismo(c.getWoluminId());
        assertEquals("Nr43", afterUpdate.getNrWydania());

        zarzadca.usunCzasopismo(afterUpdate);
        Czasopismo afterDelete = zarzadca.znajdzCzasopismo(c.getWoluminId());
        assertNull(afterDelete);
    }
}
