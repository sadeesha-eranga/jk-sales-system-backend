package com.icbt.jksalessystem.controller;

import com.icbt.jksalessystem.enums.BranchType;
import com.icbt.jksalessystem.enums.SecurityRole;
import com.icbt.jksalessystem.exception.CustomAuthenticationException;
import com.icbt.jksalessystem.model.BranchDTO;
import com.icbt.jksalessystem.model.BranchFullDTO;
import com.icbt.jksalessystem.model.BranchUserDTO;
import com.icbt.jksalessystem.model.request.BranchRequestDTO;
import com.icbt.jksalessystem.model.request.BranchUserRequestDTO;
import com.icbt.jksalessystem.model.response.BranchListResponseDTO;
import com.icbt.jksalessystem.model.response.BranchResponseDTO;
import com.icbt.jksalessystem.model.response.CommonResponseDTO;
import com.icbt.jksalessystem.model.request.LoginRequestDTO;
import com.icbt.jksalessystem.model.response.LoginResponseDTO;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.icbt.jksalessystem.enums.SecurityRole.BRANCH_ADMIN;
import static com.icbt.jksalessystem.enums.SecurityRole.HEAD_OFFICE_ADMIN;
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

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseDTO> createBranch(@Valid @RequestBody BranchRequestDTO reqBody) {
        log.info("createBranch: {}", reqBody);
        branchService.saveBranch(reqBody);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonResponseDTO(true, "Branch created!"));
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseDTO> updateBranch(@Valid @RequestBody BranchRequestDTO reqBody) {
        log.info("updateBranch: {}", reqBody);
        branchService.updateBranch(reqBody);
        return ResponseEntity.ok(new CommonResponseDTO(true, "Branch updated!"));
    }

    @DeleteMapping(value = "/{branchId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseDTO> deleteBranch(@PathVariable Integer branchId) {
        log.info("deleteBranch: {}", branchId);
        branchService.deleteBranch(branchId);
        return ResponseEntity.ok(new CommonResponseDTO(true, "Branch deleted!"));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BranchListResponseDTO> getAllBranches() {
        log.info("getAllBranches");
        List<BranchDTO> allBranches = branchService.getAllBranches();
        return ResponseEntity.ok(new BranchListResponseDTO(true, allBranches));
    }

    @GetMapping(value = "/{branchId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BranchResponseDTO> searchBranch(@PathVariable int branchId) {
        log.info("searchBranch: {}", branchId);
        BranchFullDTO branch = branchService.searchBranch(branchId);
        return ResponseEntity.ok(new BranchResponseDTO(true, branch));
    }

    @PostMapping(value = "/users/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDTO> authenticateBranchUser(@RequestBody LoginRequestDTO reqBody) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(reqBody.getUsername(), reqBody.getPassword()));
            UserDetails userDetails = branchUserService.loadUserByUsername(reqBody.getUsername());
            String accessToken = JwtUtil.getInstance().generateToken(userDetails);
            String authority = new ArrayList<GrantedAuthority>(userDetails.getAuthorities()).get(0).getAuthority();
            SecurityRole role = authority.equals(BranchType.HEAD_OFFICE.name()) ? HEAD_OFFICE_ADMIN : BRANCH_ADMIN;
            BranchUserDTO branchUserDTO = branchUserService.searchUser(reqBody.getUsername());
            return ResponseEntity.ok(new LoginResponseDTO(true, branchUserDTO.getUsername(), accessToken, role,
                    branchUserDTO.getId(), branchUserDTO.getBranch().getId()));
        } catch (BadCredentialsException e) {
            log.info("Authentication failed: {}", reqBody.getUsername());
            throw new CustomAuthenticationException(HttpStatus.UNAUTHORIZED.value(), INVALID_USERNAME_PASSWORD);
        }
    }

    @PostMapping(value = "/users/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseDTO> addUserToBranch(@Valid @RequestBody BranchUserRequestDTO reqBody) {
        log.info("addUserToBranch: {}", reqBody);
        branchUserService.createBranchUser(reqBody);
        return ResponseEntity.ok(new CommonResponseDTO(true, "Branch user created!"));
    }
}
