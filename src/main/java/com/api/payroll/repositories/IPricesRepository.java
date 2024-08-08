package com.api.payroll.repositories;

import com.api.payroll.models.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface IPricesRepository extends JpaRepository<Prices, Long> {
    @Query(value = "SELECT * FROM PRICES p " +
            "WHERE :paramDate BETWEEN p.start_date AND p.end_date " +
            "AND p.product_id = :paramProductId " +
            "AND p.brand_id = :paramBrandId " +
            "ORDER BY p.priority DESC",
            nativeQuery = true)
    ArrayList<Prices> getProductsByProductIdDateBrandId(@Param("paramDate") Date paramDate,
                                                        @Param("paramProductId") long paramProductId,
                                                        @Param("paramBrandId") long paramBrandId);
}
