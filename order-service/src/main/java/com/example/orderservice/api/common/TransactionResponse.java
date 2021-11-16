package com.example.orderservice.api.common;

import com.example.orderservice.api.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private Order order;
    private double amount;
    private String transaction_id;
    private String message;

}
