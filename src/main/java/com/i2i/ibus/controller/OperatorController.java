package com.i2i.ibus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.i2i.ibus.service.OperatorService;

@Controller
public class OperatorController {

    private OperatorService operatorService;

    @Autowired
    public OperatorController(OperatorService operatorService) {
	this.operatorService = operatorService;
    }
    
    
}
