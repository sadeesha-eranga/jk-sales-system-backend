package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.model.request.BranchRequestDTO;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
public interface BranchService {

    void saveBranch(BranchRequestDTO reqBody);
}
