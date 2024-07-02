package com.asu_tp.repo;

import com.asu_tp.models.Sequence;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SequenceRepository extends CrudRepository<Sequence, Long> {

    public Optional<Sequence> findFirstByOrderByIdDesc();

    public Optional<Sequence> findByName(int name);
}
