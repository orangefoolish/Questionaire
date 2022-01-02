package com.example.classproject.entity.answer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Statementanswer {
    Integer questionid;
    @TableId(type = IdType.AUTO)
    Integer id;
    String answer;
    Integer stuaccount;
    Integer num;
    Integer questionaireid;
}
