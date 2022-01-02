package com.example.classproject.entity.question;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Blankqs {
    int questionaireid;
    String detail;
    @TableId(type= IdType.AUTO)
    Integer id;
    String groupname;
    int blanknums;

}
