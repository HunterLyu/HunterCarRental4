package org.hunter.carrental4.infrastructure.persistence;

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
    public Customer retrieveById(String customerId) throws Exception {
        return customers.get(customerId);
    }

    @Override
    public String addCustomer(Customer customer) throws Exception {
        if(customer == null){
            return null;
        }
        boolean exist = false;
        for (Customer c: customers.values()) {
            if(c.getId().getNumber().equals(customer.getId().getNumber())){
                return c.getCustomerId();
            }
        }

        if(!exist){
            customer.setCustomerId(UUID.randomUUID().toString());
            customers.put(customer.getCustomerId(), customer);

            return customer.getCustomerId();
        }

        return null;
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception {
        customers.put(customer.getCustomerId(), customer);
    }
}
