package com.example.classproject.Service.Answer;

import com.example.classproject.Mapper.SelectanswerMapper;
import com.example.classproject.Pojo.QsidReceive;
import com.example.classproject.Pojo.Status;
import com.example.classproject.entity.answer.Selectanswer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SelectAnswerService {
    @Resource
    SelectanswerMapper selectanswerMapper;
    public Status SubmitAnswer(Selectanswer[] answertemp){
        List answers=new ArrayList();
        for (Selectanswer selectanswer : answertemp) {
            answers.add(selectanswer);
        }
        selectanswerMapper.submitanswer(answers);
        return Status.Success(200);
    }
    public Status getanswer(QsidReceive qsidReceive){
        ArrayList answers=new ArrayList<Selectanswer>();
        Selectanswer temp;
        for (Selectanswer answer : selectanswerMapper.getanswer(qsidReceive.getQuestionid())) {
                if(answers.contains(answer)){
                    temp=(Selectanswer) answers.get(answers.indexOf(answer));
                    temp.setNum(temp.getNum()+1);
                }else{
                    answers.add(answer);
                }
        }
        return Status.Success(answers,200);
    }

    public Status getsingleanswer(Integer qsid) {
        return Status.Success(selectanswerMapper.getsingleanswer(qsid),200);
    }
}
