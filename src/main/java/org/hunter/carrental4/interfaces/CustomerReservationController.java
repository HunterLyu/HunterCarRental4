package org.hunter.carrental4.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.time.DateUtils;
import org.hunter.carrental4.application.dto.BookingRecordDTO;
import org.hunter.carrental4.application.dto.CustomerReservationDTO;
import org.hunter.carrental4.application.service.CarReservationAppService;
import org.hunter.carrental4.common.model.Result;
import org.hunter.carrental4.interfaces.viewobject.BookingRecordVO;
import org.hunter.carrental4.interfaces.viewobject.SimpleReservationRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;


@RestController
@RequestMapping(path = "/reserve")
@Api(tags = "Car reservation operation")
public class CustomerReservationController extends BaseController{

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    @Resource
    private CarReservationAppService carReservationAppService;

    @GetMapping(value = "/listReservations")
    @ApiOperation(value = "list all reservations")
    public Result<Collection<CustomerReservationDTO>> listInventory() {

        Result<Collection<CustomerReservationDTO>> collectionResult = carReservationAppService.listAll();

        return collectionResult;
    }

    @PostMapping(value = "/reserveCar")
    @ApiOperation(value = "reserve a car")
    public Result<CustomerReservationDTO> reserveCar(@ApiParam(value = "input booking data") @RequestBody BookingRecordVO bookingRecordVO) throws ParseException {

        BookingRecordDTO bookingRecordDTO = BookingRecordDTO.builder().build();
        BeanUtils.copyProperties(bookingRecordVO, bookingRecordDTO, "");

        bookingRecordDTO.setCreateDate(new Date());

        bookingRecordDTO.setPickUpdate(DateUtils.parseDate(bookingRecordVO.getPickUpdateStr(), DATE_PATTERN));
        bookingRecordDTO.setReturnDate(DateUtils.parseDate(bookingRecordVO.getReturnDateStr(), DATE_PATTERN));

        Result<CustomerReservationDTO> result = carReservationAppService.reserveCar(bookingRecordDTO);

        return result;
    }

    @PostMapping(value = "/cancel")
    @ApiOperation(value = "cancel a reservation")
    public Result<CustomerReservationDTO> cancel(@RequestBody SimpleReservationRequest simpleReservationRequest) {

        Result<CustomerReservationDTO> result = carReservationAppService.cancel(simpleReservationRequest.getReservationId());

        return result;
    }
}