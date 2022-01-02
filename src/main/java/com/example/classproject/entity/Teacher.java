package com.example.classproject.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Teacher {
    int Account;
    String password;
    @TableId(type=IdType.AUTO)
    Integer id;
    String name;
    String manageclass;
    String token;
    public Teacher(int Account, String password){
        this.Account=Account;
        this.password=password;
    }
}
