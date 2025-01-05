package org.example.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.DefaultNullSavingStrategy;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.entity.saving.NullSavingStrategy;
import org.example.exceptions.WypozyczenieException;

import java.time.Instant;
import java.util.Date;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@CqlName("wypozyczenie")

public class Wypozyczenie {

    @PartitionKey
    private UUID wypozyczenieId;

    // klucze
    private UUID wypozyczajacyId;
    private UUID woluminId;

    private Instant dataOd;
    private Instant dataDo;

    public Wypozyczenie() {
        this.wypozyczenieId = UUID.randomUUID();
        this.dataOd = Instant.now();
    }
    public Wypozyczenie(UUID wId, UUID wolId) {
        this.wypozyczenieId = UUID.randomUUID();
        this.wypozyczajacyId = wId;
        this.woluminId = wolId;
        this.dataOd = Instant.now();
    }

    public UUID getWypozyczenieId() { return wypozyczenieId; }
    public void setWypozyczenieId(UUID id) { this.wypozyczenieId = id; }

    public UUID getWypozyczajacyId() { return wypozyczajacyId; }
    public void setWypozyczajacyId(UUID w) { this.wypozyczajacyId = w; }

    public UUID getWoluminId() { return woluminId; }
    public void setWoluminId(UUID x) { this.woluminId = x; }

    public Instant getDataOd() { return dataOd; }
    public void setDataOd(Instant d) { this.dataOd = d; }

    public Instant getDataDo() { return dataDo; }
    public void setDataDo(Instant d) { this.dataDo = d; }

    public void koniecWypozyczenia() {
        this.dataDo = Instant.now();
    }

    public double dlugoscWypozyczenia() {
        if (dataDo == null) return 0;
        long diffInDays = Duration.between(dataOd, dataDo).toDays();
        return diffInDays;
    }
    // obliczKare => w menedżerze ściągasz Wypozyczajacy + typ => ...
}

