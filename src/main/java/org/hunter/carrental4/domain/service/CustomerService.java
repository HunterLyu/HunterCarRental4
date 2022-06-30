package org.hunter.carrental4.domain.service;

import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.Customer;

import java.util.Map;

public interface CustomerService {

    Result<String> saveCustomer(Customer customer);

    Result<Map<String, Customer>> listAll();

    Result<Customer> retrieveByCustomerId(String customerId);
}
