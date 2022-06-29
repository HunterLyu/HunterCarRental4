package org.hunter.carrental4.domain.service.impl;

import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.Customer;
import org.hunter.carrental4.domain.repository.CustomerRepository;
import org.hunter.carrental4.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Result<String> addCustomer(Customer customer) {
        try {
            if (customer == null) {
                return Result.fail("input customer is null", "");
            }

            String customerId = customerRepository.addCustomer(customer);

            return Result.success(customerId);

        } catch (Exception e) {
            return Result.fail("call repository throw exception", "");
        }
    }
}
