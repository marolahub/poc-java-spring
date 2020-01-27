package com.systembackpoc.repositories;

import com.systembackpoc.entities.Segments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SegmentsRepository extends JpaRepository<Segments, Long> {

    @Query(value = "select * from t_segments where description like %?1%", nativeQuery = true)
    List<Segments> findByDescription(String description);
}
