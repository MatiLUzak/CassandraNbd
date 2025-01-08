package org.example.mapper;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.mapper.annotations.*;
import org.example.dao.*;

@Mapper
public interface LibraryMapper {

    @DaoFactory WoluminDao woluminDao();
    @DaoFactory KsiazkaDao ksiazkaDao();
    @DaoFactory BeletrystykaDao beletrystykaDao();
    @DaoFactory NaukowaDao naukowaDao();
    @DaoFactory CzasopismoDao czasopismoDao();

    @DaoFactory TypWypozyczajacyDao typWypozyczajacyDao();
    @DaoFactory NauczycielDao nauczycielDao();
    @DaoFactory UczenDao uczenDao();
    @DaoFactory PozostaliDao pozostaliDao();

    @DaoFactory WypozyczajacyDao wypozyczajacyDao();
    @DaoFactory WypozyczenieDao wypozyczenieDao();
}

