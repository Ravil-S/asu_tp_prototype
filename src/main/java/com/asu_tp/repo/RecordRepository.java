package com.asu_tp.repo;

import com.asu_tp.models.Record;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends CrudRepository<Record, Long> {

    public Optional<Record> findFirstByOrderByIdDesc();

    public Optional<Record> findByName(int name);
}
