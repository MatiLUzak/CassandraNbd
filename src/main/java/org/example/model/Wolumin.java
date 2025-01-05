package org.example.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import org.example.exceptions.WoluminException;

import java.util.UUID;
@Entity
@CqlName("wolumin")
public class Wolumin {

    @PartitionKey
    protected UUID woluminId;

    protected String wydawnictwo;
    protected String jezyk;
    protected String tytul;

    // ====== PUSTY KONSTRUKTOR, WYMAGANY DLA MAPPER ======
    public Wolumin() {
        // DataStax wypełni setWoluminId(...) itp. przez settery
    }

    // ====== KONSTRUKTOR Z ARGUMENTAMI (opcjonalnie, do Twojego kodu) ======
    public Wolumin(String wydawnictwo, String jezyk, String tytul) {
        // Walidacje:
        if (wydawnictwo == null || wydawnictwo.isEmpty()) {
            throw new WoluminException("Błędne Wydawnictwo");
        }
        if (jezyk == null || jezyk.isEmpty()) {
            throw new WoluminException("Błędny język");
        }
        if (tytul == null || tytul.isEmpty()) {
            throw new WoluminException("Błędny tytuł");
        }
        this.woluminId = UUID.randomUUID();
        this.wydawnictwo = wydawnictwo;
        this.jezyk = jezyk;
        this.tytul = tytul;
    }

    @CqlName("wolumin_id")
    public UUID getWoluminId() {
        return woluminId;
    }

    public void setWoluminId(UUID woluminId) {
        this.woluminId = woluminId;
    }

    public String getWydawnictwo() {
        return wydawnictwo;
    }

    public void setWydawnictwo(String wydawnictwo) {
        this.wydawnictwo = wydawnictwo;
    }

    public String getJezyk() {
        return jezyk;
    }

    public void setJezyk(String jezyk) {
        this.jezyk = jezyk;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }
}

