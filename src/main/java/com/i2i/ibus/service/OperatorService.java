package com.i2i.ibus.service;

import org.springframework.stereotype.Service;

import com.i2i.ibus.repository.AddressRepository;
import com.i2i.ibus.repository.OperatorRepository;

@Service
public class OperatorService {

    private OperatorRepository operatorRepository;
    private AddressRepository addressRepository;
    
    public OperatorService(OperatorRepository operatorRepository,
	    AddressRepository addressRepository) {
	this.operatorRepository = operatorRepository;
	this.addressRepository = addressRepository;
    }
    
    
}
