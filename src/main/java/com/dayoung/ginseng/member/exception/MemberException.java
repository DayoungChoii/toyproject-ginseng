package com.dayoung.ginseng.member.exception;

public class MemberException extends RuntimeException{
    public MemberException(){};
    public MemberException(String message){
        super(message);
    }
}
