package org.hunter.carrental4.common.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Result<E> {
    private E model;
    private String code;
    private List<String> messsages;
    private String message;
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
