package com.hospitalManager.model.utils;

import com.hospitalManager.model.workers.Worker;

public class Result<TValue> {

    private boolean result;
    private TValue value;
    private String message;


    public Result(boolean result, TValue value, String message) {
        this.result = result;
        this.value = value;
        this.message = message;
    }

    public static Result ok(){
        return new Result(true, null, null);
    }

    public static <TValue> Result okWithResult(TValue val){
        return new Result<>(true, val, null);
    }

    public static Result<Worker> error(String errorMessage){
        return new Result<>(false, null, errorMessage);
    }

    public boolean isTrue() {
        return result;
    }

    public boolean isFalse() {
        return ! result;
    }

    public TValue getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}

