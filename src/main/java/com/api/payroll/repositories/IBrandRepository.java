package com.api.payroll.repositories;

import com.api.payroll.models.BrandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandRepository extends JpaRepository<BrandModel, Long> {

}
