package org.example.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import org.example.exceptions.WypozyczajacyException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
@Entity
@CqlName("wypozyczajacy")
public class Wypozyczajacy {

    @PartitionKey
    private UUID wypozyczajacyId;

    private UUID typId;

    private String nazwa;
    private Instant dataUr;
    private String adres;

    public Wypozyczajacy() {
        this.wypozyczajacyId = UUID.randomUUID();
    }

    public Wypozyczajacy(UUID typId, String nazwa, Instant dataUr, String adres) {
        this.wypozyczajacyId = UUID.randomUUID();
        this.typId = typId;
        this.nazwa = nazwa;
        this.dataUr = dataUr;
        this.adres = adres;
    }

    public UUID getWypozyczajacyId() {
        return wypozyczajacyId;
    }
    public void setWypozyczajacyId(UUID wId) { this.wypozyczajacyId = wId; }

    public UUID getTypId() {
        return typId;
    }
    public void setTypId(UUID t) { this.typId = t; }

    public String getNazwa() { return nazwa; }
    public void setNazwa(String n) { this.nazwa = n; }

    public Instant getDataUr() { return dataUr; }
    public void setDataUr(Instant d) { this.dataUr = d; }

    public String getAdres() { return adres; }
    public void setAdres(String a) { this.adres = a; }

    public String pobierzInformacjeWypozyczajacego() {
        return "Nazwa=" + nazwa + ", dataUr=" + dataUr + ", adres=" + adres;
    }
}

