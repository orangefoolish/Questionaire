package com.example.classproject.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.classproject.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    Teacher selectTeacher(Integer account);
    void updatepw(Integer account,String newpassword);
}
