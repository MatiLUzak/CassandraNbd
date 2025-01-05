package org.example.dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import org.example.model.Uczen;

@Dao
public interface UczenDao extends BaseCrudDao<Uczen> {
}
