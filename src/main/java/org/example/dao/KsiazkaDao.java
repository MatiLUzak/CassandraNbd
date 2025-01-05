package org.example.dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import org.example.model.Ksiazka;

@Dao
public interface KsiazkaDao extends BaseCrudDao<Ksiazka> {
}
