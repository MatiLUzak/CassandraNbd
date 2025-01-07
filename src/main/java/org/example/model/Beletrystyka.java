package org.example.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import org.example.exceptions.WoluminException;

import java.util.List;
import java.util.UUID;

@Entity
@CqlName("beletrystyka")
public class Beletrystyka extends Ksiazka {

    private String przedzialWiekowy;
    private String rodzaj;

    public Beletrystyka() {
        super();
    }

    public Beletrystyka(String wyd, String jez, String tit, List<String> aut, String wiek, String rodz) {
        super(wyd, jez, tit, aut);
        if (wiek == null || wiek.isEmpty()) {
            throw new WoluminException("Przedział wiekowy nie może być pusty");
        }
        if (rodz == null || rodz.isEmpty()) {
            throw new WoluminException("Rodzaj nie może być pusty");
        }
        this.przedzialWiekowy = wiek;
        this.rodzaj = rodz;
    }

    @Override
    @CqlName("beletrystyka_id")
    public UUID getWoluminId() {
        return super.getWoluminId();
    }
    @Override
    public void setWoluminId(UUID wId) {
        super.setWoluminId(wId);
    }

    public String getPrzedzialWiekowy() {
        return przedzialWiekowy;
    }

    public void setPrzedzialWiekowy(String w) {
        this.przedzialWiekowy = w;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String r) {
        this.rodzaj = r;
    }
}

