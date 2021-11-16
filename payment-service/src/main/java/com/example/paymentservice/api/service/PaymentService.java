package com.example.paymentservice.api.service;

import com.example.paymentservice.api.model.Payment;
import com.example.paymentservice.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment doPayment(Payment payment){
        payment.setPayment_status(paymentProcessing());
        payment.setTransaction_id(UUID.randomUUID().toString());
        return paymentRepository.save(payment);
    }

    public String paymentProcessing(){
        return new Random().nextBoolean()?"success":"failure";
    }

    public Payment findPaymentHistoryByOrderId(int orderId){
        Payment payment=new Payment();
        List<Payment> paymentList=paymentRepository.findAll();
        try
        {
            for(Payment p:paymentList){
                if(p.getOrder_id()==orderId){
                    payment=p;
                    break;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return payment;
    }
}
