package com.icbt.jksalessystem.service.impl;

import com.icbt.jksalessystem.entity.Branch;
import com.icbt.jksalessystem.enums.BranchStatus;
import com.icbt.jksalessystem.enums.BranchType;
import com.icbt.jksalessystem.exception.CustomServiceException;
import com.icbt.jksalessystem.model.BranchDTO;
import com.icbt.jksalessystem.model.BranchFullDTO;
import com.icbt.jksalessystem.model.request.BranchRequestDTO;
import com.icbt.jksalessystem.repository.BranchRepository;
import com.icbt.jksalessystem.service.BranchService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.icbt.jksalessystem.util.ApplicationConstants.AlreadyExists.BRANCH_EXISTS_WITH_EMAIL;
import static com.icbt.jksalessystem.util.ApplicationConstants.NotFound.BRANCH_NOT_FOUND;

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
    private final ModelMapper modelMapper;

    public BranchServiceImpl(BranchRepository branchRepository, ModelMapper modelMapper) {
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public BranchDTO saveBranch(BranchRequestDTO reqBody) {
        if (branchRepository.existsByEmail(reqBody.getEmail()))
            throw new CustomServiceException(HttpStatus.CONFLICT.value(), BRANCH_EXISTS_WITH_EMAIL);
        Branch savedBranch = branchRepository
                .save(new Branch(reqBody.getName(), BranchType.NORMAL, reqBody.getEmail(), BranchStatus.ACTIVE));
        log.info("Branch saved: {}", savedBranch);
        return modelMapper.map(savedBranch, BranchDTO.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public BranchDTO updateBranch(BranchRequestDTO requestDTO) {
        Branch branch = branchRepository.findById(requestDTO.getId())
                .orElseThrow(() -> new CustomServiceException(HttpStatus.NOT_FOUND.value(), BRANCH_NOT_FOUND));
        if (!branch.getEmail().equals(requestDTO.getEmail()) && branchRepository.existsByEmail(requestDTO.getEmail()))
            throw new CustomServiceException(HttpStatus.CONFLICT.value(), BRANCH_EXISTS_WITH_EMAIL);
        branch.setEmail(requestDTO.getEmail());
        branch.setName(requestDTO.getName());
        Branch updatedBranch = branchRepository.save(branch);
        log.info("Branch updated: {}", updatedBranch);
        return modelMapper.map(updatedBranch, BranchDTO.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteBranch(Integer branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new CustomServiceException(HttpStatus.NOT_FOUND.value(), BRANCH_NOT_FOUND));
        branch.setStatus(BranchStatus.INACTIVE);
        Branch inactivatedBranch = branchRepository.save(branch);
        log.info("Branch deleted: {}", inactivatedBranch);
        return true;
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        return branchRepository.findAll().stream()
                .map(branch -> modelMapper.map(branch, BranchDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BranchFullDTO searchBranch(Integer branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new CustomServiceException(HttpStatus.NOT_FOUND.value(), BRANCH_NOT_FOUND));
        return modelMapper.map(branch, BranchFullDTO.class);
    }

    @Override
    public long countAll() {
        return branchRepository.count();
    }
}
