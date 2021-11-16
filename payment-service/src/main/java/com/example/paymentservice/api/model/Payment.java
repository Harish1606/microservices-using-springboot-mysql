package com.example.paymentservice.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int payment_id;
    private String payment_status;
    private String transaction_id;
    private int order_id;
    private double amount;
}
