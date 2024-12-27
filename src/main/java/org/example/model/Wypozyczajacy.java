package org.example.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import org.example.exceptions.WypozyczajacyException;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
@Entity
@CqlName("wypozyczajacy")
public class Wypozyczajacy {
    @PartitionKey
    private UUID wypozyczajacyId;
    private TypWypozyczajacy typWypozyczajacy;
    private String nazwa;
    private Date dataUr;
    private String adres;
    //private UUID uuid;

    public Wypozyczajacy(TypWypozyczajacy typWypozyczajacy, String nazwa, Date dataUr, String adres) {
        if (typWypozyczajacy == null) {
            throw new WypozyczajacyException("Błędny typWypozyczajacy");
        }
        if (nazwa == null || nazwa.isEmpty()) {
            throw new WypozyczajacyException("Błędna nazwa");
        }
        if (adres == null || adres.isEmpty()) {
            throw new WypozyczajacyException("Błędny adres");
        }

        this.typWypozyczajacy = typWypozyczajacy;
        this.nazwa = nazwa;
        this.dataUr = dataUr;
        this.adres = adres;
        this.wypozyczajacyId = UUID.randomUUID();
    }
    public Wypozyczajacy() {
        this.wypozyczajacyId = UUID.randomUUID();
    }
    public UUID getWypozyczajacyId() {
        return wypozyczajacyId;
    }

    public void setWypozyczajacyId(UUID id) {
        this.wypozyczajacyId = id;
    }

    /*public UUID getUuid() {
        return uuid;
    }
     */


    public TypWypozyczajacy getTypWypozyczajacy() {
        return typWypozyczajacy;
    }

    public String getNazwa() {
        return nazwa;
    }

    public Date getDataUr() {
        return dataUr;
    }

    public String getAdres() {
        return adres;
    }

    public void setTypWypozyczajacy(TypWypozyczajacy typWypozyczajacy) {
        if (typWypozyczajacy == null) {
            throw new WypozyczajacyException("Błędny typWypozyczajacy");
        }
        this.typWypozyczajacy = typWypozyczajacy;
    }

    public void setNazwa(String nazwa) {
        if (nazwa == null || nazwa.isEmpty()) {
            throw new WypozyczajacyException("Błędna nazwa");
        }
        this.nazwa = nazwa;
    }

    public void setDataUr(Date dataUr) {
        this.dataUr = dataUr;
    }

    public void setAdres(String adres) {
        if (adres == null || adres.isEmpty()) {
            throw new WypozyczajacyException("Błędny adres");
        }
        this.adres = adres;
    }

    public String pobierzInformacjeWypozyczajacego() {
        StringBuilder info = new StringBuilder();
        info.append("Nazwa: ").append(getNazwa()).append("\n");
        info.append("Data urodzenia: ").append(getDataUr()).append("\n");
        info.append("Adres: ").append(getAdres()).append("\n");
        if (typWypozyczajacy != null) {
            info.append("Typ wypożyczającego: ").append(typWypozyczajacy.pobierzInfo()).append("\n");
        }
        return info.toString();
    }
}
