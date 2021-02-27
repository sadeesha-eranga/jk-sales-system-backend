package com.icbt.jksalessystem.repository;

import com.icbt.jksalessystem.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-22
 */
public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findByBranchId(Integer branchId);

    long countAllByBranchId(Integer branchId);
}
