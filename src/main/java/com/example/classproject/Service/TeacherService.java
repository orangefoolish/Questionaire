package com.example.classproject.Service;

import com.example.classproject.Mapper.TeacherMapper;
import com.example.classproject.Pojo.JWTobject;
import com.example.classproject.entity.Teacher;
import com.example.classproject.Pojo.Status;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeacherService {
    @Resource
    TeacherMapper teacherMapper;
    public Status login(Integer account,String password){
        Teacher teacher=teacherMapper.selectTeacher(account);
        if(teacher==null||!teacher.getPassword().equals(password)){
            return Status.Success(302);
        }
        teacher.setToken(JWTobject.getToken(teacher.getAccount()+"",teacher.getPassword()));
        teacher.setPassword("");
        return Status.Success(teacher,200);
    }
    public Status delete(Integer id){
        teacherMapper.deleteById(id);
        return Status.Success("删除成功",200);
    }
    public Status updatepw(Integer account,String newpassword){
        teacherMapper.updatepw(account,newpassword);
        return Status.Success(200);
    }
}
