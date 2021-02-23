package com.icbt.jksalessystem.entity;

import com.icbt.jksalessystem.enums.PaymentMethod;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Entity
@Builder
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Branch branch;

    @OneToMany(mappedBy = "customerOrder")
    private List<OrderDetail> orderDetails;
}
