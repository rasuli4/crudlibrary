package com.sda.database.repository;

import com.google.common.base.Optional;
import com.sda.database.entity.EmployeeEntity;

import java.util.List;

/**
 * JPA specific of Repository
 *
 * @authro Rasul Turabov
 */

public interface CrudRepository<T> {

    /**
     * Retrieves all entities
     * @return
     */

    List<T> findAll();

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */

    EmployeeEntity findById(long id);

}
