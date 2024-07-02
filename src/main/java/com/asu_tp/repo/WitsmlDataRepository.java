package com.asu_tp.repo;

import com.asu_tp.models.Record;
import com.asu_tp.models.WitsmlData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WitsmlDataRepository extends CrudRepository<WitsmlData, Long> {

    public Optional<WitsmlData> findFirstByOrderByIdDesc();
}
