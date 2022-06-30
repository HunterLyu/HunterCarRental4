package org.hunter.carrental4.domain.repository;

import org.hunter.carrental4.domain.model.entity.Customer;

import java.util.Map;

public interface CustomerRepository {

    Customer retrieveByCustomerId(String customerId) throws Exception;

    String addCustomer(Customer customer) throws Exception;

    void updateCustomer(Customer customer) throws Exception;

    Customer retrieveByIdNumber(String idNumber) throws Exception;

    Map<String, Customer> listAll() throws Exception;
}
