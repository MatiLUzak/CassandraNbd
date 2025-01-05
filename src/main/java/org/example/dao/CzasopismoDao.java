package org.example.dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import org.example.model.Czasopismo;

@Dao
public interface CzasopismoDao extends BaseCrudDao<Czasopismo> {
}

