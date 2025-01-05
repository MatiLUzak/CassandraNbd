package org.example.dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import java.util.UUID;

public interface BaseCrudDao<T> {

    @Insert
    void save(T entity);

    @Select
    T findById(UUID id);

    @Update
    void update(T entity);

    @Delete
    void delete(T entity);
}
