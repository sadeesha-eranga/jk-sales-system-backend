package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.JkSalesSystemApplication;
import com.icbt.jksalessystem.model.CustomerDTO;
import com.icbt.jksalessystem.model.request.CustomerRequestDTO;
import com.icbt.jksalessystem.repository.CustomerRepository;
import com.icbt.jksalessystem.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = JkSalesSystemApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceTest {

    private final CustomerService customerService;

    @Autowired
    public CustomerServiceTest(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerService = new CustomerServiceImpl(customerRepository, modelMapper);
    }

    @Test
    @Order(1)
    void createCustomer() {
        String name = "Customer Name";
        String nic = "123456789V";
        String email = "customer1@example.com";
        CustomerRequestDTO customer = new CustomerRequestDTO(name, nic, email);
        CustomerDTO createdCustomer = customerService.createCustomer(customer);
        assertNotNull(createdCustomer);
        assertNotNull(createdCustomer.getId());
        assertEquals(createdCustomer.getName(), name);
        assertEquals(createdCustomer.getNic(), nic);
        assertEquals(createdCustomer.getEmail(), email);
    }

    @Test
    @Order(2)
    void searchCustomerByNic() {
        String nic = "123456789V";
        CustomerDTO searchedCustomer = customerService.searchCustomerByNic(nic);
        assertNotNull(searchedCustomer);
        assertEquals(searchedCustomer.getNic(), nic);
    }

    @Test
    @Order(3)
    void getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        assertTrue(customers.size() > 0);
    }
}