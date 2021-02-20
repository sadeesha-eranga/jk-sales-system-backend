package com.icbt.jksalessystem.service.impl;

import com.icbt.jksalessystem.entity.BranchUser;
import com.icbt.jksalessystem.exception.CustomAuthenticationException;
import com.icbt.jksalessystem.model.AuthUserDetailDTO;
import com.icbt.jksalessystem.model.LoginRequestDTO;
import com.icbt.jksalessystem.model.LoginResponseDTO;
import com.icbt.jksalessystem.repository.BranchUserRepository;
import com.icbt.jksalessystem.service.BranchUserService;
import com.icbt.jksalessystem.util.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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
