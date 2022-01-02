package com.example.classproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Questionaire {
    @TableId(type= IdType.AUTO)
    Integer id;
    int creater;
    String title;
    public Questionaire(int creater, String title) {
        this.title=title;
        this.creater=creater;
    }
}
