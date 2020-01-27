package com.systembackpoc.repositories;
import com.systembackpoc.entities.Texts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TextsRepository extends JpaRepository<Texts, Long>{

    @Query(value = "select * from t_texts where slug = upper(?1)", nativeQuery = true)
    Texts findBySlug(String slug);
}
