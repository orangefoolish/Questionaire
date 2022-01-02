package com.example.classproject.Service;

import com.example.classproject.Mapper.QuestionaireMapper;
import com.example.classproject.entity.Questionaire;
import com.example.classproject.Pojo.Status;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionaireService {
    @Resource
    QuestionaireMapper questionaireMapper;
    public Status insert(Questionaire questionaire){
        questionaireMapper.addquestionaire(questionaire);
        return Status.Success(questionaire.getId(),200);
    }
    public Status find(List qsid){
        return Status.Success(questionaireMapper.selectquestionaire(qsid),200);
    }

}
