package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.model.BranchUserDTO;
import com.icbt.jksalessystem.model.request.BranchUserRequestDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
public interface BranchUserService extends UserDetailsService {

    BranchUserDTO createBranchUser(BranchUserRequestDTO branchUser);

    BranchUserDTO searchUser(String username);
}
