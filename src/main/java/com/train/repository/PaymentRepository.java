package com.train.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.train.model.PaymentDetails;

public interface PaymentRepository extends JpaRepository<PaymentDetails, Long>{


public PaymentDetails findByPaymentId(String paymentId);
}
