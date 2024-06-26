package com.asu_tp.repo;

import com.asu_tp.models.Directional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DirectionalRepository extends CrudRepository<Directional, Long> {
    //public List<Directional> findByWellId(String wellId);
}
