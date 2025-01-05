package org.example.dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import org.example.model.Wypozyczenie;

@Dao
public interface WypozyczenieDao extends BaseCrudDao<Wypozyczenie> {
}
