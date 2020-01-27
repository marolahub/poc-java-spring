package com.systembackpoc.repositories;
import com.systembackpoc.entities.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientsRepository extends JpaRepository<Clients, Long> {

    @Query(value = "select * from t_clients where active = ?1 order by name", nativeQuery = true)
    List<Clients> findByStatus(Boolean status);

    @Query(value = "select * from t_clients where name like %?1% order by name", nativeQuery = true)
    List<Clients> findByName(String name);
}
