package com.icbt.jksalessystem.service.impl;

import com.icbt.jksalessystem.entity.Branch;
import com.icbt.jksalessystem.enums.BranchType;
import com.icbt.jksalessystem.exception.CustomServiceException;
import com.icbt.jksalessystem.model.request.BranchRequestDTO;
import com.icbt.jksalessystem.repository.BranchRepository;
import com.icbt.jksalessystem.service.BranchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.icbt.jksalessystem.util.ApplicationConstants.AlreadyExists.BRANCH_EXISTS_WITH_EMAIL;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
@Service
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveBranch(BranchRequestDTO reqBody) {
        if (branchRepository.existsByEmail(reqBody.getEmail()))
            throw new CustomServiceException(HttpStatus.CONFLICT.value(), BRANCH_EXISTS_WITH_EMAIL);
        Branch savedBranch = branchRepository.save(new Branch(reqBody.getName(), BranchType.NORMAL, reqBody.getEmail()));
        log.info("Branch saved: {}", savedBranch);
    }
}
