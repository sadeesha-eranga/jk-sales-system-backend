package com.icbt.jksalessystem.repository;

import com.icbt.jksalessystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-22
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
