package com.icbt.jksalessystem.repository;

import com.icbt.jksalessystem.entity.BranchUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
public interface BranchUserRepository extends JpaRepository<BranchUser, Integer> {

    Optional<BranchUser> findByUsername(String username);
}
