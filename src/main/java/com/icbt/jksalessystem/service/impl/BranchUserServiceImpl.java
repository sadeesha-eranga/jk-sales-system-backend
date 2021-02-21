package com.icbt.jksalessystem.service.impl;

import com.icbt.jksalessystem.entity.BranchUser;
import com.icbt.jksalessystem.model.AuthUserDetailDTO;
import com.icbt.jksalessystem.repository.BranchUserRepository;
import com.icbt.jksalessystem.service.BranchUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    private final BranchUserRepository branchUserRepository;

    public BranchUserServiceImpl(BranchUserRepository branchUserRepository) {
        this.branchUserRepository = branchUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BranchUser user = branchUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        return new AuthUserDetailDTO(user);
    }
}
