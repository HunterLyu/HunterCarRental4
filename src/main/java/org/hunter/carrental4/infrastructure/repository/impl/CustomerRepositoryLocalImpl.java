package org.hunter.carrental4.infrastructure.repository.impl;

import org.apache.commons.lang3.StringUtils;
import org.hunter.carrental4.domain.model.entity.Customer;
import org.hunter.carrental4.domain.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class CustomerRepositoryLocalImpl implements CustomerRepository {

    private static Map<String, Customer> customers = new HashMap<>();

    @Override
    public Customer retrieveByCustomerId(String customerId) throws Exception {
        return customers.get(customerId);
    }

    public Customer retrieveByIdNumber(String idNumber) throws Exception {
        if(StringUtils.isBlank(idNumber)){
            return null;
        }
        for (Customer c: customers.values()) {
            if(c.getId().getNumber().equals(idNumber)){
                return c;
            }
        }

        return null;
    }

    @Override
    public String addCustomer(Customer customer) throws Exception {
        if(customer == null) {
            return null;
        }

        customer.setCustomerId(UUID.randomUUID().toString());
        customers.put(customer.getCustomerId(), customer);

        return customer.getCustomerId();
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception {
        customers.put(customer.getCustomerId(), customer);
    }

    public Map<String, Customer> listAll() throws Exception{
        return customers;
    };
}
