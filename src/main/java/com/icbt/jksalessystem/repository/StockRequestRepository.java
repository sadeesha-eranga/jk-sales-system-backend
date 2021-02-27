package com.icbt.jksalessystem.repository;

import com.icbt.jksalessystem.entity.StockRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-22
 */
public interface StockRequestRepository extends JpaRepository<StockRequest, Long> {

    @Query("SELECT st FROM StockRequest st WHERE st.fromBranch.id=:branchId OR st.toBranch.id=:branchId")
    List<StockRequest> getBranchStockRequests(Integer branchId);

    @Query(value = "SELECT COUNT(*) FROM stock_request WHERE from_branch_id=:branchId OR to_branch_id=:branchId", nativeQuery = true)
    long countAllByBranch(int branchId);
}
