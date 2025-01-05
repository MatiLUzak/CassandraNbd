package org.example.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import org.example.exceptions.WypozyczajacyException;

import java.util.UUID;

@Entity
@CqlName("uczen")
public class Uczen extends TypWypozyczajacy {

    private String nrSemestru;

    public Uczen() { super(); }

    public Uczen(double kara, int maxDlWypoz, int maksLKsiazek, String nrSemestru) {
        super(kara, maxDlWypoz, maksLKsiazek);
        if (nrSemestru == null || nrSemestru.isEmpty()) {
            throw new WypozyczajacyException("Błędny nrSemestru");
        }
        this.nrSemestru = nrSemestru;
    }

    @Override
    @CqlName("uczen_id")
    public UUID getTypId() {
        return super.getTypId();
    }
    @Override
    public void setTypId(UUID t) {
        super.setTypId(t);
    }

    public String getNrSemestru() {
        return nrSemestru;
    }
    public void setNrSemestru(String ns) { this.nrSemestru = ns; }

    @Override
    public String pobierzInfo() {
        return super.pobierzInfo() + ", Nr semestru: " + nrSemestru;
    }
}

