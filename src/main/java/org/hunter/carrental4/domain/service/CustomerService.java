package org.hunter.carrental4.domain.service;

import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.Customer;

public interface CustomerService {

    Result<String> addCustomer(Customer customer);
}
