package org.example.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import org.example.exceptions.WypozyczajacyException;
@Entity
@CqlName("uczen")
public class Uczen extends TypWypozyczajacy {
    private String nrSemestru;

    public Uczen(double kara, int maxDlWypoz, int maksLKsiazek, String nrSemestru) {
        super(kara, maxDlWypoz, maksLKsiazek);
        if (nrSemestru == null || nrSemestru.isEmpty()) {
            throw new WypozyczajacyException("Błędny nrSemestru");
        }
        this.nrSemestru = nrSemestru;
    }
    public Uczen() {}

    public String getNrSemestru() {
        return nrSemestru;
    }

    public void setNrSemestru(String nrSemestru) {
        if (nrSemestru == null || nrSemestru.isEmpty()) {
            throw new WypozyczajacyException("Błędny nrSemestru");
        }
        this.nrSemestru = nrSemestru;
    }

    public String pobierzInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Kara: ").append(getKara()).append("\n");
        info.append("Max długość wypożyczenia: ").append(getMaxDlWypoz()).append("\n");
        info.append("Maksymalna liczba książek: ").append(getMaksLKsiazek()).append("\n");
        info.append("Nr semestru: ").append(getNrSemestru()).append("\n");
        return info.toString();
    }
}
