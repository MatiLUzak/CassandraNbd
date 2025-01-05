package org.example.dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import org.example.model.Naukowa;

@Dao
public interface NaukowaDao extends BaseCrudDao<Naukowa> {
}
