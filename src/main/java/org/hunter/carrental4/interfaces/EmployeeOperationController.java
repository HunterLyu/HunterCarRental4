package org.hunter.carrental4.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hunter.carrental4.application.dto.CustomerReservationDTO;
import org.hunter.carrental4.application.service.EmployeeAppService;
import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.interfaces.viewobject.SimpleReservationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(path = "/employee")
@Api(tags = "Employee operation")
public class EmployeeOperationController extends BaseController{

    @Resource
    private EmployeeAppService employeeAppService;

    @PostMapping(value = "/confirmPickingUp")
    @ApiOperation(value = "pick up a car")
    public Result<CustomerReservationDTO> confirmPickingUp(@RequestBody SimpleReservationRequest simpleReservationRequest) {

        Result<CustomerReservationDTO> reservationDTOResult = employeeAppService.confirmPickingUp(simpleReservationRequest.getReservationId(),
                "Tom", simpleReservationRequest.getDamage());

        return reservationDTOResult;
    }

    @PostMapping(value = "/confirmReturning")
    @ApiOperation(value = "return a car")
    public Result<CustomerReservationDTO> confirmReturning(@ApiParam(value = "reservationId", required = true) @RequestBody SimpleReservationRequest simpleReservationRequest) {

        Result<CustomerReservationDTO> reservationDTOResult =
                employeeAppService.confirmReturning(simpleReservationRequest.getReservationId(),
                "Tom", simpleReservationRequest.getDamage());

        return reservationDTOResult;
    }
}
