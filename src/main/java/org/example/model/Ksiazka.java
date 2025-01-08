package org.example.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import org.example.exceptions.WoluminException;

import java.util.List;
import java.util.UUID;

@Entity
@CqlName("ksiazka")
public class Ksiazka extends Wolumin {

    protected List<String> autor;

    public Ksiazka() {
        super();
    }

    public Ksiazka(String wydawnictwo, String jezyk, String tytul, List<String> autor) {
        super(wydawnictwo, jezyk, tytul);
        if (autor == null || autor.isEmpty()) {
            throw new WoluminException("Brak autora książki");
        }
        this.autor = autor;
    }

    @Override
    @CqlName("ksiazka_id")
    public UUID getWoluminId() {
        return super.getWoluminId();
    }

    @Override
    public void setWoluminId(UUID wId) {
        super.setWoluminId(wId);
    }

    public List<String> getAutor() {
        return autor;
    }

    public void setAutor(List<String> autor) {
        if (autor == null || autor.isEmpty()) {
            throw new WoluminException("Brak autora książki");
        }
        this.autor = autor;
    }
}

