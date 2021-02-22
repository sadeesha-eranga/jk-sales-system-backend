package com.icbt.jksalessystem.repository;

import com.icbt.jksalessystem.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-22
 */
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
}
