package org.wing.common;

public enum ResponseCode {
    SUCCESS(1,"SUCCESS"),
    ERROR(0,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(20,"ILLEGAL_ARGUMENT");
    private final int code;
    private final  String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
        
    }
}
