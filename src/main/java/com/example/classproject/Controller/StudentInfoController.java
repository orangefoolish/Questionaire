package com.example.classproject.Controller;

import com.example.classproject.Service.CreatertoqsnaireService;
import com.example.classproject.Service.StudentInfoService;
import com.example.classproject.Pojo.Status;
import com.example.classproject.entity.Studentinfo;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentInfoController {
    @Resource
    StudentInfoService studentInfoService;
    @Resource
    CreatertoqsnaireService creatertoqsnaireService;
    @GetMapping("/login")
    public Status Login(@RequestParam("account")Integer account,@RequestParam("password")String password){
        return studentInfoService.login(account,password);
    }
    @PostMapping("/addstudents")
    public Status addStudents(@RequestBody Studentinfo[] studentinfostemp){
        return studentInfoService.addStudents(studentinfostemp);
    }
    @GetMapping("/search")
    public Status searchStudent(@RequestParam Integer studentid){
        return studentInfoService.searchStudent(studentid);
    }
    @PutMapping("/updatefinish")
    public Status updateFinish(@RequestBody Studentinfo studentinfo){
        return studentInfoService.updatefinish(studentinfo.getHasfinish(),studentinfo.getStudentid());
    }
    @GetMapping("/getquestionaires")
    public Status getQuestionaires(@RequestParam("target") String target){
        return creatertoqsnaireService.stugetquestionaire(target);
    }
    @GetMapping("/findstudents")
    public Status findStudents(@RequestParam Integer beclass){
        return studentInfoService.findstudents(beclass);
    }
    @PutMapping("/updatepassword")
    public Status updatePassword(@RequestBody Studentinfo studentinfo){
        return studentInfoService.updatePassword(studentinfo.getPassword(),studentinfo.getStudentid());
    }
}
