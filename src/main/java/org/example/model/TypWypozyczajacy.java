package org.example.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import org.example.exceptions.WypozyczajacyException;

import java.util.UUID;
@Entity
@CqlName("typ_wypozyczajacy")
public class TypWypozyczajacy {

    @PartitionKey
    protected UUID typId;

    protected double kara;
    protected int maxDlWypoz;
    protected int maksLKsiazek;

    public TypWypozyczajacy() {
        this.typId = UUID.randomUUID();
    }

    public TypWypozyczajacy(double kara, int maxDlWypoz, int maksLKsiazek) {
        if (kara < 0) throw new WypozyczajacyException("Kara nie może być ujemna");
        if (maxDlWypoz < 0) throw new WypozyczajacyException("MaxDlWypoz nie może być ujemny");
        if (maksLKsiazek < 0) throw new WypozyczajacyException("Maksymalna liczba książek nie może być ujemna");

        this.typId = UUID.randomUUID();
        this.kara = kara;
        this.maxDlWypoz = maxDlWypoz;
        this.maksLKsiazek = maksLKsiazek;
    }

    @CqlName("typ_id")
    public UUID getTypId() {
        return typId;
    }
    public void setTypId(UUID t) { this.typId = t; }

    public double getKara() {
        return kara;
    }
    public void setKara(double k) { this.kara = k; }

    public int getMaxDlWypoz() {
        return maxDlWypoz;
    }
    public void setMaxDlWypoz(int m) { this.maxDlWypoz = m; }

    public int getMaksLKsiazek() {
        return maksLKsiazek;
    }
    public void setMaksLKsiazek(int ml) { this.maksLKsiazek = ml; }

    public String pobierzInfo() {
        return "Kara: " + kara + ", Max dl: " + maxDlWypoz + ", Maks l. ksiązek: " + maksLKsiazek;
    }
}

