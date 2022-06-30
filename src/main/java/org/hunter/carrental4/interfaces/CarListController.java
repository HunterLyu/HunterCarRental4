package org.hunter.carrental4.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hunter.carrental4.application.dto.CarInventoryDTO;
import org.hunter.carrental4.application.service.CarSearchAppService;
import org.hunter.carrental4.common.model.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping(path = "/car")
@Api(tags = "Car inventory searching.")
public class CarListController extends BaseController{

    @Resource
    private CarSearchAppService carSearchAppService;

    @GetMapping(value = "/listInventory")
    @ApiOperation(value = "list car inventory")
    public Result<List<CarInventoryDTO>> listInventory(@ApiParam(value = "car brand") @RequestParam(value = "brand", required = false) String brand) {

        Result<List<CarInventoryDTO>> carInventoryDTOResult = carSearchAppService.listAvailableCars(brand);

        return carInventoryDTOResult;
    }
}
