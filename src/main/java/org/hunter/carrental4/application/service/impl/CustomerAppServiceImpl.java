package org.hunter.carrental4.application.service.impl;

import org.hunter.carrental4.application.dto.BookingRecordDTO;
import org.hunter.carrental4.application.service.CustomerAppService;
import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.Customer;
import org.hunter.carrental4.domain.model.entity.PersonalId;
import org.hunter.carrental4.domain.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAppServiceImpl implements CustomerAppService {

    @Autowired
    private CustomerService customerService;

    @Override
    public Result<String> extractCustomer(BookingRecordDTO bookingRecordDTO) {

        Customer customer = new Customer();
        BeanUtils.copyProperties(bookingRecordDTO, customer);

        PersonalId personalId = new PersonalId();
        personalId.setPersonalIdIdType(bookingRecordDTO.getPersonalIdIdType());
        personalId.setNumber(bookingRecordDTO.getIdCardNumber());
        customer.setId(personalId);

        Result<String> customerIdResult = customerService.saveCustomer(customer);
        if (!customerIdResult.isSuccess()) {
            return Result.fail(customerIdResult);
        }
        String customerId = customerIdResult.getModel();


        return Result.success(customerId);
    }
}
