package org.hunter.carrental4.application.service.impl;

import org.hunter.carrental4.application.assemble.Translatetion;
import org.hunter.carrental4.application.dto.CustomerReservationDTO;
import org.hunter.carrental4.application.service.EmployeeAppService;
import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.domain.model.entity.Customer;
import org.hunter.carrental4.domain.model.entity.CustomerReservation;
import org.hunter.carrental4.domain.service.CarReservationService;
import org.hunter.carrental4.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAppServiceImpl implements EmployeeAppService {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CarReservationService carReservationService;

    @Override
    public Result<CustomerReservationDTO> confirmReturning(String reservationId, String employeeId, String newDamage) {

        Result<CustomerReservation> customerReservationResult = carReservationService.returnCar(reservationId,
                employeeId, newDamage);
        if (!customerReservationResult.isSuccess()) {
            return Result.fail(customerReservationResult);
        }

        CustomerReservation customerReservation = customerReservationResult.getModel();

        Result<Customer> customerResult = customerService.retrieveByCustomerId(customerReservation.getCustomerId());
        if (!customerResult.isSuccess()) {
            return Result.fail(customerResult);
        }

        Customer customer = customerResult.getModel();

        CustomerReservationDTO customerReservationDTO = Translatetion.model2Dto(customerReservation, customer);
        return Result.success(customerReservationDTO);
    }

    @Override
    public Result<CustomerReservationDTO> confirmPickingUp(String reservationId, String employeeId, String existingDamage) {
        Result<CustomerReservation> customerReservationResult = carReservationService.pickUpCar(reservationId,
                employeeId, existingDamage);
        if (!customerReservationResult.isSuccess()) {
            return Result.fail(customerReservationResult);
        }

        CustomerReservation customerReservation = customerReservationResult.getModel();

        Result<Customer> customerResult = customerService.retrieveByCustomerId(customerReservation.getCustomerId());
        if (!customerResult.isSuccess()) {
            return Result.fail(customerResult);
        }

        Customer customer = customerResult.getModel();

        CustomerReservationDTO customerReservationDTO = Translatetion.model2Dto(customerReservation, customer);
        return Result.success(customerReservationDTO);
    }
}
