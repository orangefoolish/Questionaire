package com.example.classproject.Service;

import com.example.classproject.Mapper.CreatertoqsnaireMapper;
import com.example.classproject.Mapper.QuestionaireMapper;
import com.example.classproject.Service.Question.BlankqsService;
import com.example.classproject.entity.Creatertoqsnaire;
import com.example.classproject.Pojo.Status;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreatertoqsnaireService {
    @Resource
    CreatertoqsnaireMapper creatertoqsnaireMapper;
    public Status createconnection(Creatertoqsnaire createrToQsnaire){
        java.util.Date date=new java.util.Date();
        createrToQsnaire.setCreatedate(new java.sql.Date(date.getTime()));
        creatertoqsnaireMapper.createconnect(createrToQsnaire);
        return Status.Success(200);
    }
    public Status updateconnection(Creatertoqsnaire creatertoqsnaire){
        creatertoqsnaire.setDraft(false);
        creatertoqsnaireMapper.updateconnection(creatertoqsnaire);
        return Status.Success(200);
    }
    public Status geymyquestionaire(Integer creater){
        List qsid=new ArrayList();
        List data=new ArrayList();//把对应关系和存进去
        Creatertoqsnaire[]createrToQsnaire=creatertoqsnaireMapper.getmyquestionaire(creater);
        int count=0;
        for (Creatertoqsnaire createrToQsnairetemp :createrToQsnaire ) {
            createrToQsnairetemp.setId(++count);
            qsid.add(createrToQsnairetemp.getQsnaireid());
            data.add(createrToQsnairetemp);
        }
        return Status.Success(data,200);
    }
    public Status stugetquestionaire(String target){
        List data=new ArrayList();
        int count=0;
        for (Creatertoqsnaire createrToQsnaire : creatertoqsnaireMapper.stugetquestionaire(target)) {
            createrToQsnaire.setId(++count);
            data.add(createrToQsnaire);
        }
        return Status.Success(data,200);
    }
    public Status delete(Integer questionaireid){
        creatertoqsnaireMapper.deleteByQuestionaireid(questionaireid);
        return Status.Success(200);
    }
}
