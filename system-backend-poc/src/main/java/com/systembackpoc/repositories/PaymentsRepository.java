package com.systembackpoc.repositories;
import com.systembackpoc.entities.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {

    @Query(value = "select * from t_payments where pay_date between ?1 and ?2", nativeQuery = true)
    List<Payments> findByPayDate(Date startDate, Date finalDate);
}
