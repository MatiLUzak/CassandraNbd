package org.example.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import org.example.exceptions.WypozyczajacyException;

import java.util.UUID;

@Entity
@CqlName("pozostali")
public class Pozostali extends TypWypozyczajacy {

    private String zawod;

    public Pozostali() { super(); }

    public Pozostali(double kara, int maxDlWypoz, int maksLKsiazek, String zawod) {
        super(kara, maxDlWypoz, maksLKsiazek);
        if (zawod == null || zawod.isEmpty()) {
            throw new WypozyczajacyException("Błędny zawód");
        }
        this.zawod = zawod;
    }

    @Override
    @CqlName("pozostali_id")
    public UUID getTypId() {
        return super.getTypId();
    }
    @Override
    public void setTypId(UUID t) {
        super.setTypId(t);
    }

    public String getZawod() {
        return zawod;
    }
    public void setZawod(String z) { this.zawod = z; }

    @Override
    public String pobierzInfo() {
        return super.pobierzInfo() + ", Zawód: " + zawod;
    }
}

