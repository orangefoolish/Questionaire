package com.example.classproject.Service;

import com.example.classproject.Mapper.StudentInfoMapper;
import com.example.classproject.Pojo.JWTobject;
import com.example.classproject.Pojo.Status;
import com.example.classproject.entity.Studentinfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentInfoService {
    @Resource
    StudentInfoMapper studentInfoMapper;
    public Status insert(){
        return Status.Success(200);
    }
    public Status updatefinish(String newqsnaireid, Integer studentid){
        studentInfoMapper.updatefinish(newqsnaireid,studentid);
        return Status.Success(studentInfoMapper.getnewfinish(studentid),200);
    }
    public Status login(Integer account, String password) {
        Studentinfo student=studentInfoMapper.login(account);
        if(student==null||!student.getPassword().equals(password)){
                return Status.Success(302);
        }
        student.setToken(JWTobject.getToken(student.getAccount()+"",student.getPassword()));
        student.setPassword("");
        return Status.Success(student,200);
    }

    public Status findstudents(Integer beclass) {
        return Status.Success(studentInfoMapper.findstudents(beclass),200);
    }

    public Status searchStudent(Integer studentid) {
        return Status.Success(studentInfoMapper.searchStudent(studentid),200);
    }

    public Status addStudents(Studentinfo[] studentinfos) {
        try{
            List temp=new ArrayList();
            for (Studentinfo studentinfo : studentinfos) {
                temp.add(studentinfo);
            }
            studentInfoMapper.addStudens(temp);
            return Status.Success(200);
        }catch (Exception e){
            return Status.Error();
        }
    }
    public Status updatePassword(String newpassword,Integer studentid){
        studentInfoMapper.updatePassword(newpassword,studentid);
        return Status.Success(200);
    }
}
