package com.simple.paymentservice.pg;

public interface CreditCardPaymentAdapter {
    Long processCreditPayment(Long amountKRW, String creditCardNumber);
}
