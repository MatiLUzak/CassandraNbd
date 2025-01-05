package org.example.dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import org.example.model.Wolumin;

@Dao
public interface WoluminDao extends BaseCrudDao<Wolumin> {

}
