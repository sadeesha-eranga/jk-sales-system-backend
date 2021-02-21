package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.JkSalesSystemApplication;
import com.icbt.jksalessystem.model.BranchDTO;
import com.icbt.jksalessystem.model.request.BranchRequestDTO;
import com.icbt.jksalessystem.repository.BranchRepository;
import com.icbt.jksalessystem.service.impl.BranchServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = JkSalesSystemApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class BranchServiceTest {

    private final BranchService branchService;

    @Autowired
    public BranchServiceTest(BranchRepository branchRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.branchService = new BranchServiceImpl(branchRepository, modelMapper, passwordEncoder);
    }

    @Test
    void testSaveBranch() {
        BranchRequestDTO testBranch = new BranchRequestDTO("Branch name", "sadeeshae@ceyentra.com");
        BranchDTO savedBranch = branchService.saveBranch(testBranch);

        assertNotNull(savedBranch);
        assertNotNull(savedBranch.getId());
        assertEquals(testBranch.getName(), savedBranch.getName());
        assertEquals(testBranch.getEmail(), savedBranch.getEmail());
    }

    @Test
    void updateBranch() {
        int branchId = 1;
        String newBranchName = "New branch name";
        String newEmail = "new_email@test.com";

        BranchRequestDTO branchDTO = new BranchRequestDTO(branchId, newBranchName, newEmail);
        BranchDTO updatedBranch = branchService.updateBranch(branchDTO);

        assertNotNull(updatedBranch);
        assertEquals(branchId, updatedBranch.getId());
        assertEquals(newBranchName, updatedBranch.getName());
        assertEquals(newEmail, updatedBranch.getEmail());
    }

    @Test
    void deleteBranch() {
        int branchId = 1;
        boolean result = branchService.deleteBranch(branchId);

        assertTrue(result);
    }
}