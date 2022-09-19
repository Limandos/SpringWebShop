package com.pet.springwebshop.repo;

import com.pet.springwebshop.entity.Refresh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshRepo extends JpaRepository<Refresh, Long> {
    List<Refresh> findAllRefreshByCustomer(Long customerId);
}