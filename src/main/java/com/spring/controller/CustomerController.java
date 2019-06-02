package com.spring.controller;

import com.spring.dto.CustomerDto;
import com.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CustomerController {

    final private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public ResponseEntity<CustomerDto> listAllUsers(@RequestBody CustomerDto customerDto) {

        return ResponseEntity.ok(customerService.saveCustomer(customerDto,"test"));
    }

    @RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDto>> getCustomer(@ModelAttribute CustomerDto customerDto) {

        return ResponseEntity.ok(customerService.getCustomer(customerDto));
    }
}
