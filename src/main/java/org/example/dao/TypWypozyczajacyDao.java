package org.example.dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import org.example.model.TypWypozyczajacy;

@Dao
public interface TypWypozyczajacyDao extends BaseCrudDao<TypWypozyczajacy> {
}
