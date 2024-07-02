package com.asu_tp.repo;

import com.asu_tp.models.Well;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface WellRepository extends CrudRepository<Well, Long> {

    public Optional<Well> findFirstByOrderByIdDesc();

    public Optional<Well> findFirstByName(String name);
}
