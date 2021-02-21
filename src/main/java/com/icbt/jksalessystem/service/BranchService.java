package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.model.BranchDTO;
import com.icbt.jksalessystem.model.request.BranchRequestDTO;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
public interface BranchService {

    BranchDTO saveBranch(BranchRequestDTO requestDTO);

    BranchDTO updateBranch(BranchRequestDTO requestDTO);

    boolean deleteBranch(Integer branchId);
}
