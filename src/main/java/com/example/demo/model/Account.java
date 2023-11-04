package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Builder
@Data
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<AccountType> accountTypes;
    @Column(name = "currency")
    private Enum currency;
    @Column(name = "balance")
    private double balance;
}
