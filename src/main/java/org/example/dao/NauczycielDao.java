package org.example.dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import org.example.model.Nauczyciel;

@Dao
public interface NauczycielDao extends BaseCrudDao<Nauczyciel> {
}
