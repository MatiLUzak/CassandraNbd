package org.example.dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import org.example.model.Wypozyczajacy;

@Dao
public interface WypozyczajacyDao extends BaseCrudDao<Wypozyczajacy> {
}
