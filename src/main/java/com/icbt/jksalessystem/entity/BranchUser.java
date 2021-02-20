package com.icbt.jksalessystem.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class BranchUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    private Branch branch;

    public BranchUser(BranchUser branchUser) {
        this.id = branchUser.id;
        this.name = branchUser.name;
        this.username = branchUser.username;
        this.password = branchUser.password;
        this.createdAt = branchUser.createdAt;
        this.updatedAt = branchUser.updatedAt;
        this.branch = branchUser.branch;
    }
}
