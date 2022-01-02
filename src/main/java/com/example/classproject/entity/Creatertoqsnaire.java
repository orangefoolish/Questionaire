package com.example.classproject.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class Creatertoqsnaire {
    Integer creater;
    int qsnaireid;
    java.util.Date starttime;
    java.util.Date endtime;
    String target;
    String title;
    Boolean draft;
    java.sql.Date createdate;
    @TableId(type = IdType.AUTO)
    Integer id;
    public Creatertoqsnaire(int qsnaireid,Integer creater,String title){
        this.qsnaireid=qsnaireid;
        this.creater=creater;
        this.title=title;
    }
}
