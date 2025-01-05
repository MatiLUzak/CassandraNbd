package org.example.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import org.example.exceptions.WoluminException;

import java.util.List;
import java.util.UUID;

@Entity
@CqlName("naukowa")
public class Naukowa extends Ksiazka {

    private String recenzja;
    private String dział;

    public Naukowa() { super(); }

    public Naukowa(String wyd, String jez, String tit, List<String> aut, String rec, String dz) {
        super(wyd, jez, tit, aut);
        if (rec == null || rec.isEmpty()) {
            throw new WoluminException("Recenzja nie może być pusta");
        }
        if (dz == null || dz.isEmpty()) {
            throw new WoluminException("Dział nie może być pusty");
        }
        this.recenzja = rec;
        this.dział = dz;
    }

    @Override
    @CqlName("naukowa_id")
    public UUID getWoluminId() {
        return super.getWoluminId();
    }
    @Override
    public void setWoluminId(UUID wId) {
        super.setWoluminId(wId);
    }

    public String getRecenzja() {
        return recenzja;
    }
    public void setRecenzja(String r) { this.recenzja = r; }

    public String getDział() {
        return dział;
    }
    public void setDział(String d) { this.dział = d; }
}
