package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.model.BranchDTO;
import com.icbt.jksalessystem.model.BranchFullDTO;
import com.icbt.jksalessystem.model.request.BranchRequestDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
public interface BranchService {

    BranchDTO saveBranch(BranchRequestDTO requestDTO);

    BranchDTO updateBranch(BranchRequestDTO requestDTO);

    boolean deleteBranch(Integer branchId);

    List<BranchDTO> getAllBranches();

    BranchFullDTO searchBranch(Integer branchId);
}
