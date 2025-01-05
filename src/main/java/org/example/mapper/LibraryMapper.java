package org.example.mapper;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.mapper.annotations.*;
import org.example.dao.*;

@Mapper
public interface LibraryMapper {

    // Wolumin
    @DaoFactory WoluminDao woluminDao();
    @DaoFactory KsiazkaDao ksiazkaDao();
    @DaoFactory BeletrystykaDao beletrystykaDao();
    @DaoFactory NaukowaDao naukowaDao();
    @DaoFactory CzasopismoDao czasopismoDao();

    // TypWypozyczajacy
    @DaoFactory TypWypozyczajacyDao typWypozyczajacyDao();
    @DaoFactory NauczycielDao nauczycielDao();
    @DaoFactory UczenDao uczenDao();
    @DaoFactory PozostaliDao pozostaliDao();

    // Wypozyczajacy, Wypozyczenie
    @DaoFactory WypozyczajacyDao wypozyczajacyDao();
    @DaoFactory WypozyczenieDao wypozyczenieDao();
}

