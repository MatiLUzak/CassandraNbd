package org.example.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import org.example.exceptions.WypozyczajacyException;

import java.util.UUID;

@Entity
@CqlName("nauczyciel")
public class Nauczyciel extends TypWypozyczajacy {

    private String tytul;

    public Nauczyciel() { super(); }

    public Nauczyciel(double kara, int maxDlWypoz, int maksLKsiazek, String tytul) {
        super(kara, maxDlWypoz, maksLKsiazek);
        if (tytul == null || tytul.isEmpty()) {
            throw new WypozyczajacyException("Błędny tytuł");
        }
        this.tytul = tytul;
    }

    @Override
    @CqlName("nauczyciel_id")
    public UUID getTypId() {
        return super.getTypId();
    }
    @Override
    public void setTypId(UUID t) {
        super.setTypId(t);
    }

    public String getTytul() {
        return tytul;
    }
    public void setTytul(String t) { this.tytul = t; }

    @Override
    public String pobierzInfo() {
        return super.pobierzInfo() + ", Tytuł: " + tytul;
    }
}

