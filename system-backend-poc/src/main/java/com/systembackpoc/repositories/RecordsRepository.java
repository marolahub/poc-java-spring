package com.systembackpoc.repositories;
import com.systembackpoc.entities.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface RecordsRepository extends JpaRepository<Records, Long> {

    @Query(value = "select * from t_records where moment between ?1 and ?2", nativeQuery = true)
    List<Records> findByMoment(Date startDate, Date finalDate);

    @Query(value = "select r.* from t_records r join t_texts t on(t.id = r.text_id)" +
                   "where t.client_id = ?1 order by r.moment, r.id", nativeQuery = true)
    List<Records> findByClient(Long id);

    @Query(value = "select * from t_records where pay_status = ?1 order by moment, id", nativeQuery = true)
    List<Records> findByPayStatus(Long status);

    @Query(value = "select r.* from t_records r join t_texts t on(t.id = r.text_id)" +
                   "where t.client_id = ?1 and r.pay_status <> 0 order by r.moment, r.id", nativeQuery = true)
    List<Records> findByStatusOpenClient(Long client);
}
