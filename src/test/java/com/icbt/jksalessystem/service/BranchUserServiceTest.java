package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.JkSalesSystemApplication;
import com.icbt.jksalessystem.model.BranchDTO;
import com.icbt.jksalessystem.model.BranchUserDTO;
import com.icbt.jksalessystem.model.request.BranchRequestDTO;
import com.icbt.jksalessystem.model.request.BranchUserRequestDTO;
import com.icbt.jksalessystem.repository.BranchRepository;
import com.icbt.jksalessystem.repository.BranchUserRepository;
import com.icbt.jksalessystem.service.impl.BranchServiceImpl;
import com.icbt.jksalessystem.service.impl.BranchUserServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = JkSalesSystemApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class BranchUserServiceTest {

    private final BranchUserService branchUserService;
    private final BranchService branchService;

    @Autowired
    public BranchUserServiceTest(BranchRepository branchRepository, BranchUserRepository branchUserRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.branchUserService = new BranchUserServiceImpl(branchRepository, branchUserRepository, modelMapper, passwordEncoder);
        this.branchService = new BranchServiceImpl(branchRepository, modelMapper);
    }

    @Test
    void createBranchUser() {
        BranchRequestDTO testBranch = new BranchRequestDTO("Branch name", "sadeeshae@ceyentra.com");
        BranchDTO savedBranch = branchService.saveBranch(testBranch);
        int branchId = savedBranch.getId();
        String name = "Branch user";
        String username = "new_branch_user";
        String password = "password";
        BranchUserRequestDTO branchUser = new BranchUserRequestDTO(branchId, name, username, password);

        BranchUserDTO savedBranchUser = branchUserService.createBranchUser(branchUser);

        assertNotNull(savedBranchUser);
        assertNotNull(savedBranchUser.getId());
        assertEquals(name, savedBranchUser.getName());
        assertEquals(username, savedBranchUser.getUsername());
    }

    @Test
    void searchUser() {
        String username = "new_branch_user";
        BranchUserDTO searchedUser = branchUserService.searchUser(username);
        assertNotNull(searchedUser);
        assertEquals(searchedUser.getUsername(), username);
    }
}
