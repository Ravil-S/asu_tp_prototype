package com.asu_tp.repo;

import com.asu_tp.models.Sidetrack;
import com.asu_tp.models.Well;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SidetrackRepository extends CrudRepository<Sidetrack, Long> {

    public Optional<Sidetrack> findFirstByOrderByIdDesc();

    public Optional<Sidetrack> findByName(int name);
}
