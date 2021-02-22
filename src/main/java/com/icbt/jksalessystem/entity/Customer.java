package com.icbt.jksalessystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-22
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String nic;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<CustomerOrder> orders;
}
