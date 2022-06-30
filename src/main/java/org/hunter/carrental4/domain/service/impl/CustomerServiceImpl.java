package org.hunter.carrental4.domain.service.impl;

import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.Customer;
import org.hunter.carrental4.domain.repository.CustomerRepository;
import org.hunter.carrental4.domain.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Result<Customer> retrieveByCustomerId(String customerId){
        try {
            return Result.success(customerRepository.retrieveByCustomerId(customerId));

        } catch (Exception e) {
            return Result.fail("call repository throw exception", "");
        }
    }

    @Override
    public Result<String> saveCustomer(Customer customer) {
        try {
            if (customer == null) {
                return Result.fail("input customer is null", "");
            }

            Customer existCustomer = customerRepository.retrieveByIdNumber(customer.getId().getNumber());

            boolean exist = existCustomer != null;

            if (exist) {
                BeanUtils.copyProperties(customer, existCustomer, "customerId", "id");
                customerRepository.updateCustomer(existCustomer);
                return Result.success(existCustomer.getCustomerId());
            } else {
                String customerId = customerRepository.addCustomer(customer);

                return Result.success(customerId);
            }

        } catch (Exception e) {
            return Result.fail("call repository throw exception", "");
        }
    }

    @Override
    public Result<Map<String, Customer>> listAll() {
        try {
            return Result.success(customerRepository.listAll());

        } catch (Exception e) {
            return Result.fail("call repository throw exception", "");
        }
    }
}
