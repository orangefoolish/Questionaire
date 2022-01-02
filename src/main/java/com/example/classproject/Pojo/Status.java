package com.example.classproject.Pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Status<T> {
    private int Code;
    private T Data;
    public Status(int Code){
        this.Code=Code;
    }
    public static Status Error(){
        return new Status(303);
    }
    public static Status UnLogin(){return new Status(401);}
    public static<T> Status Success(T Data,int Code){
        Status status=new Status();
        status.setCode(Code);
        status.setData(Data);
        return status;
    }
    public static<T> Status Success(int Code){
        Status status=new Status();
        status.setCode(Code);
        return status;
    }
}
