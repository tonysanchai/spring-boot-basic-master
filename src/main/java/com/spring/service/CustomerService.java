package com.spring.service;

import com.spring.dao.CustomerDao;
import com.spring.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    final private CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public CustomerDto saveCustomer(CustomerDto customerDto,String userName){
        return customerDao.saveCustomer(customerDto,userName);
    }

    public List<CustomerDto> getCustomer(CustomerDto customerDto){
        return customerDao.findAllCustomer();
    }
}
