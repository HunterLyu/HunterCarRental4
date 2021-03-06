package org.hunter.carrental4.application.service;

import org.hunter.carrental4.application.dto.CustomerReservationDTO;
import org.hunter.carrental4.common.model.Result;

public interface EmployeeAppService {

    Result<CustomerReservationDTO> confirmReturning(String reservationId, String employeeId, String newDamage);

    Result<CustomerReservationDTO> confirmPickingUp(String reservationId, String employeeId, String existingDamage);
}
