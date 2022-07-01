package org.hunter.carrental4.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@ApiModel(description = "The API common result wrapper")
public class Result<E> {

    @ApiModelProperty(value = "The actual data model")
    private E model;
    @ApiModelProperty(value = "result code or error code")
    private String code;
    @ApiModelProperty(value = "extra messages")
    private List<String> messsages;
    @ApiModelProperty(value = "result message")
    private String message;
    @ApiModelProperty(value = "application process success or not")
    private boolean success;

    public static Result success(Object obj){
        return Result.builder().model(obj).success(true).build();
    }

    public static Result fail(Exception e, String code){
        return Result.builder().code(code).message(e.getMessage()).build();
    }

    public static Result fail(String message, String code){
        return Result.builder().code(code).message(message).build();
    }

    public static Result fail(Result failResult){
        return Result.builder().code(failResult.code).message(failResult.message).messsages(failResult.messsages).build();
    }
}
