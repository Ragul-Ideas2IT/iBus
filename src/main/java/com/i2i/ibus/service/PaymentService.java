package com.i2i.ibus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.repository.PaymentRepository;

/**
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 *
 */
@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService(PaymentRepository paymentRepositary) {
	this.paymentRepository = paymentRepositary;
    }

}