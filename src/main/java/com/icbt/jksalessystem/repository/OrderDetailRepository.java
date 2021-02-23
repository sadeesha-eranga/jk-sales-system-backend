package com.icbt.jksalessystem.repository;

import com.icbt.jksalessystem.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-24
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
