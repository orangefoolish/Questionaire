package com.example.classproject.Service.Answer;

import com.example.classproject.Mapper.MultiselectanswerMapper;
import com.example.classproject.Pojo.QsidReceive;
import com.example.classproject.Pojo.Status;
import com.example.classproject.entity.answer.Multiselectanswer;
import com.example.classproject.entity.answer.Selectanswer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MultiselectAnswerService {
    @Resource
    MultiselectanswerMapper multiselectanswerMapper;
    public Status SubmitAnswer(Multiselectanswer[] answertemp){
        List answers=new ArrayList();
        for (Multiselectanswer selectanswer : answertemp) {
            answers.add(selectanswer);
        }
        multiselectanswerMapper.submitanswer(answers);
        return Status.Success(200);
    }
    public Status getanswer(QsidReceive qsidReceive){
        ArrayList answers=new ArrayList<Multiselectanswer>();
        Multiselectanswer temp;
        for (Multiselectanswer answer : multiselectanswerMapper.getanswer(qsidReceive.getQuestionid())) {
            if(answers.contains(answer)){
                temp=(Multiselectanswer) answers.get(answers.indexOf(answer));
                temp.setNum(temp.getNum()+1);
            }else{
                answers.add(answer);
            }
        }
        return Status.Success(answers,200);
    }


    public Status getsingleanswer(Integer qsid) {
        return Status.Success(multiselectanswerMapper.getsingleanswer(qsid),200);
    }
}
