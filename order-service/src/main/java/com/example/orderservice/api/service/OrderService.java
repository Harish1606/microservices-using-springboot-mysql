package com.example.orderservice.api.service;

import com.example.orderservice.api.common.Payment;
import com.example.orderservice.api.common.TransactionRequest;
import com.example.orderservice.api.common.TransactionResponse;
import com.example.orderservice.api.model.Order;
import com.example.orderservice.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    @Lazy
    private RestTemplate template;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    public TransactionResponse saveOrder(TransactionRequest request){
        String response="";
        Order order=request.getOrder();
        order = orderRepository.save(order);
        Payment payment=request.getPayment();
        payment.setOrder_id(order.getId());
        payment.setAmount(order.getPrice());
        Payment paymentResponse = template.postForObject(ENDPOINT_URL,payment,Payment.class);
        response=paymentResponse.getPayment_status().equals("success")?"payment processing successful":"there is a failure in payment";
        return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransaction_id(),response);
    }

}
