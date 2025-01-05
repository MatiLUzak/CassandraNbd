package org.example.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import org.example.exceptions.WoluminException;

import java.util.UUID;

@Entity
@CqlName("czasopismo")
public class Czasopismo extends Wolumin {

    private String nrWydania;

    public Czasopismo() {
        super();
    }

    public Czasopismo(String wydawnictwo, String jezyk, String tytul, String nr) {
        super(wydawnictwo, jezyk, tytul);
        if (nr == null || nr.isEmpty()) {
            throw new WoluminException("Brak numeru wydania");
        }
        this.nrWydania = nr;
    }

    @Override
    @CqlName("czasopismo_id")
    public UUID getWoluminId() {
        return super.getWoluminId();
    }

    @Override
    public void setWoluminId(UUID wId) {
        super.setWoluminId(wId);
    }

    public String getNrWydania() { return nrWydania; }
    public void setNrWydania(String nr) { this.nrWydania = nr; }
}

