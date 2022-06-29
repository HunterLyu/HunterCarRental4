package org.hunter.carrental4.domain.repository;

import org.hunter.carrental4.domain.model.entity.Customer;

public interface CustomerRepository {

    Customer retrieveById(String customerId) throws Exception;

    String addCustomer(Customer customer) throws Exception;

    void updateCustomer(Customer customer) throws Exception;
}
