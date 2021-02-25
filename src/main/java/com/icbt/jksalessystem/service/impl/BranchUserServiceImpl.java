package com.icbt.jksalessystem.service.impl;

import com.icbt.jksalessystem.entity.Branch;
import com.icbt.jksalessystem.entity.BranchUser;
import com.icbt.jksalessystem.exception.CustomServiceException;
import com.icbt.jksalessystem.model.AuthUserDetailDTO;
import com.icbt.jksalessystem.model.BranchUserDTO;
import com.icbt.jksalessystem.model.request.BranchUserRequestDTO;
import com.icbt.jksalessystem.repository.BranchRepository;
import com.icbt.jksalessystem.repository.BranchUserRepository;
import com.icbt.jksalessystem.service.BranchUserService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.icbt.jksalessystem.util.ApplicationConstants.NotFound.BRANCH_NOT_FOUND;
import static com.icbt.jksalessystem.util.ApplicationConstants.NotFound.USER_NOT_FOUND;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
@Service
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BranchUserServiceImpl implements BranchUserService {

    private final BranchRepository branchRepository;
    private final BranchUserRepository branchUserRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public BranchUserServiceImpl(BranchRepository branchRepository, BranchUserRepository branchUserRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.branchRepository = branchRepository;
        this.branchUserRepository = branchUserRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BranchUser user = branchUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        return new AuthUserDetailDTO(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public BranchUserDTO createBranchUser(BranchUserRequestDTO branchUser) {
        Branch branch = branchRepository.findById(branchUser.getBranchId())
                .orElseThrow(() -> new CustomServiceException(HttpStatus.NOT_FOUND.value(), BRANCH_NOT_FOUND));
        String password = passwordEncoder.encode(branchUser.getPassword());
        BranchUser savedUser = branchUserRepository
                .save(new BranchUser(branchUser.getName(), branchUser.getUsername(), password, branch));
        log.info("Branch user saved: {}", savedUser);
        return modelMapper.map(savedUser, BranchUserDTO.class);
    }

    @Override
    public BranchUserDTO searchUser(String username) {
        BranchUser branchUser = branchUserRepository.findByUsername(username)
                .orElseThrow(() -> new CustomServiceException(HttpStatus.UNAUTHORIZED.value(), USER_NOT_FOUND));
        return modelMapper.map(branchUser, BranchUserDTO.class);
    }
}
