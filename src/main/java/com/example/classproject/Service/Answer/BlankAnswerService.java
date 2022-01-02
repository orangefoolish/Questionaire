package com.example.classproject.Service.Answer;

import com.example.classproject.Mapper.BlankanswerMapper;
import com.example.classproject.Pojo.QsidReceive;
import com.example.classproject.Pojo.Status;
import com.example.classproject.entity.answer.Blankanswer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlankAnswerService {
    @Resource
    BlankanswerMapper blankanswerMapper;
    public Status SubmitAnswer(Blankanswer[] answertemp){
        List answers=new ArrayList();
        for (Blankanswer blankanswer: answertemp) {
            answers.add(blankanswer);
        }
        blankanswerMapper.submitanswer(answers);
        return Status.Success(200);
    }
    public Status getanswer(QsidReceive qsidReceive){
        return Status.Success(blankanswerMapper.getanswer(qsidReceive.getQuestionid()),200);
    }
    public Status getsingleanswer(Integer qsid){
        return Status.Success(blankanswerMapper.getsingleanswer(qsid),200);
    }
}
