package com.example.orderservice.api.controller;

import com.example.orderservice.api.common.TransactionRequest;
import com.example.orderservice.api.common.TransactionResponse;
import com.example.orderservice.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){
        return orderService.saveOrder(request);
    }

}
