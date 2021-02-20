package com.icbt.jksalessystem.controller;

import com.icbt.jksalessystem.exception.CustomAuthenticationException;
import com.icbt.jksalessystem.model.LoginRequestDTO;
import com.icbt.jksalessystem.model.LoginResponseDTO;
import com.icbt.jksalessystem.service.BranchService;
import com.icbt.jksalessystem.service.BranchUserService;
import com.icbt.jksalessystem.util.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static com.icbt.jksalessystem.util.ApplicationConstants.Invalid.INVALID_USERNAME_PASSWORD;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
@Log4j2
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/branches")
public class BranchController {

    private final BranchService branchService;
    private final BranchUserService branchUserService;
    private final AuthenticationManager authenticationManager;

    public BranchController(BranchService branchService, BranchUserService branchUserService, AuthenticationManager authenticationManager) {
        this.branchService = branchService;
        this.branchUserService = branchUserService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/users/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDTO> authenticateBranchUser(@RequestBody LoginRequestDTO reqBody) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(reqBody.getUsername(), reqBody.getPassword()));
            UserDetails userDetails = branchUserService.loadUserByUsername(reqBody.getUsername());
            String accessToken = JwtUtil.getInstance().generateToken(userDetails);
            String authority = new ArrayList<GrantedAuthority>(userDetails.getAuthorities()).get(0).getAuthority();
            return ResponseEntity.ok(new LoginResponseDTO(true, reqBody.getUsername(), accessToken, authority));
        } catch (BadCredentialsException e) {
            log.info("Authentication failed: {}", reqBody.getUsername());
            throw new CustomAuthenticationException(HttpStatus.UNAUTHORIZED.value(), INVALID_USERNAME_PASSWORD);
        }
    }
}
