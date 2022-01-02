package com.example.classproject.Controller;
import com.example.classproject.Pojo.Status;
import com.example.classproject.Service.Answer.AnswerService;
import com.example.classproject.Service.CreatertoqsnaireService;
import com.example.classproject.Service.Question.QuestionService;
import com.example.classproject.Service.Question.SelectqsService;
import com.example.classproject.Service.QuestionaireService;
import com.example.classproject.Service.TeacherService;
import com.example.classproject.entity.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    TeacherService teacherService;
    @Resource
    CreatertoqsnaireService creatertoqsnaireService;
    @Resource
    QuestionaireService questionaireService;
    @Resource
    QuestionService questionService;
    @Resource
    AnswerService answerService;
    @GetMapping("/login")
    public Status GetUser(@RequestParam("account") Integer account, @RequestParam("password") String passowrd) {
        return teacherService.login(account,passowrd);
    }
    @DeleteMapping("/deleteteacher")
    public Status Delete(@RequestParam Integer id){
        return teacherService.delete(id);
    }
    @PutMapping("/updatepw")//修改密码
    public Status Updatepw(@RequestBody Teacher teacher){
        System.out.println(teacher.getAccount()+" "+teacher.getPassword());
        return teacherService.updatepw(teacher.getAccount(),teacher.getPassword());
    }

    @GetMapping("/getmyquestionaire")
    public Status GetMyQuestionaire(@RequestParam("creater") Integer creater){
        return creatertoqsnaireService.geymyquestionaire(creater);
    }
    @PostMapping("/createconnection")
    public Status CreateConnection(@RequestBody Questionaire questionaire){
        questionaireService.insert(questionaire);
        Creatertoqsnaire creatertoqsnaire=new Creatertoqsnaire(questionaire.getId(),questionaire.getCreater(),questionaire.getTitle());
        if(creatertoqsnaireService.createconnection(creatertoqsnaire).getCode()==200){
            return Status.Success(questionaire.getId(),200);
        }else{
            return Status.Error();
        }
    }
    @PutMapping("/updateconnection")
    public Status UpdateConnection(@RequestBody Creatertoqsnaire creatertoqsnaire){
        return creatertoqsnaireService.updateconnection(creatertoqsnaire);
    }
    @DeleteMapping("/deletequestionaire")
    public Status DeleteConnection(@RequestParam Integer questionaireid){

        if(creatertoqsnaireService.delete(questionaireid).getCode()==200){
            questionService.delete(questionaireid);
            answerService.delete(questionaireid);
        }
        return Status.Success(200);
    }
}