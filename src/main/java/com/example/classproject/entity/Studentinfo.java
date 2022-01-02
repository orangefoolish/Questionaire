package com.example.classproject.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Studentinfo {
    String studentname;
    int account;
    int beclass;
    String password;
    @TableId()
    Integer studentid;
    String profession;
    String hasfinish;
    String token;
}
