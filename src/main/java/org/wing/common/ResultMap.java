package org.wing.common;


import java.io.Serializable;

public class ResultMap<T> implements Serializable{

    private int status;
    private String msg;
    private T data;

    public ResultMap(){

    }

    private ResultMap(int status) {
        this.status = status;
    }

    private ResultMap(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ResultMap(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ResultMap(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public boolean isSuccess(){
        return this.status==ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return status;
    }
    public T getData(){
        return data;
    }
    public String getMsg(){
        return msg;
    }

    public static <T> ResultMap<T> createBySuccess(){
        return new ResultMap<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ResultMap<T> createBySuccess(T data){
        return new ResultMap<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ResultMap<T> createBySuccess(String msg,T data){
        return new ResultMap<T>(ResponseCode.SUCCESS.getCode()
        ,msg,data);
    }

    public static <T> ResultMap<T> createBySuccessMessage(String msg){
        return new ResultMap<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ResultMap<T> createByError(){
        return new ResultMap<T>(ResponseCode.ERROR.getCode(),
                ResponseCode.ERROR.getDesc());
    }

    public static <T> ResultMap<T> createByErrorMessage(String errorMessage){
        return new ResultMap<T>(ResponseCode.ERROR.getCode(),
                errorMessage);
    }

    public static <T> ResultMap<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ResultMap<T>(errorCode,errorMessage);
    }
}
