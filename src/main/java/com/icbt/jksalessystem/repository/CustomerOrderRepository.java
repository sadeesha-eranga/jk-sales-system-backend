package com.icbt.jksalessystem.repository;

import com.icbt.jksalessystem.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-22
 */
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    List<CustomerOrder> findByCustomerId(Long customerId);

    List<CustomerOrder> findByBranchId(Integer branchId);
}
