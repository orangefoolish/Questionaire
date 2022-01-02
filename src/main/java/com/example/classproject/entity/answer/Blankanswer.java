package com.example.classproject.entity.answer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class Blankanswer {
    @TableId(type = IdType.AUTO)
    Integer id;
    String answer;
    Integer questionid;
    Integer stuaccount;
    Integer num;
    Integer questionaireid;
}
