package org.example.dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import org.example.model.Beletrystyka;

@Dao
public interface BeletrystykaDao extends BaseCrudDao<Beletrystyka> {
}
