package com.api.payroll.repositories;

import com.api.payroll.models.BrandInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandInventoryRepository extends JpaRepository<BrandInventory, Long> {

}
