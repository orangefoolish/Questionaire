package com.example.classproject.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.classproject.entity.Studentinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentInfoMapper extends BaseMapper<Studentinfo> {
    Studentinfo login(Integer account);
    String getnewfinish(Integer studentid);
    void updatefinish(@Param("newqsnaireid")String newqsnaire,@Param("studentid")Integer studentid);

    Studentinfo[] findstudents(Integer beclass);//按班级分类

    Studentinfo[] searchStudent(Integer studentid);//按学号搜索

    void  addStudens(List studentinfos);

    void updatePassword(String newpassword,Integer studentid);
}
