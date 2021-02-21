package com.icbt.jksalessystem.repository;

import com.icbt.jksalessystem.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    boolean existsByEmail(String email);
}
