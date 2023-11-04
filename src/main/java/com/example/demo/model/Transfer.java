package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Builder
@Data
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_account_id")
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "recipient_account_id")
    private Account recipientAccount;
    @Column(name = "amount")
    private double amount;
    @Column(name = "transfer_type")
    private Enum transferType;
    @Column(name = "timestamp")
    private Timestamp timestamp;
}
