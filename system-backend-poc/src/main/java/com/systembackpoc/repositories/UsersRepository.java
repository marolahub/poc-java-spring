package com.systembackpoc.repositories;

import com.systembackpoc.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query(value = "select * from t_users where name like %?1%", nativeQuery = true)
    Users findByName(String name);

    @Query(value = "select * from t_users where user_name = ?1", nativeQuery = true)
    Users findByUserName(String userName);

    @Query(value = "select * from t_users where user_name = ?1 and pass_word = ?2", nativeQuery = true)
    Users findByLogin(String userName, String passWord);
}
