package com.icbt.jksalessystem.entity;

import com.icbt.jksalessystem.enums.BranchStatus;
import com.icbt.jksalessystem.enums.BranchType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private BranchType type;
    @Column(nullable = false, unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private BranchStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "branch")
    private List<BranchUser> users;

    public Branch(String name, BranchType type, String email, BranchStatus status) {
        this.name = name;
        this.type = type;
        this.email = email;
        this.status = status;
    }
}
