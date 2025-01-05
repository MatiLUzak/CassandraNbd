package org.example.dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import org.example.model.Pozostali;

@Dao
public interface PozostaliDao extends BaseCrudDao<Pozostali> {
}
