package com.example.classproject.Service.Answer;

import com.example.classproject.Mapper.StatementanswerMapper;
import com.example.classproject.Pojo.QsidReceive;
import com.example.classproject.Pojo.Status;
import com.example.classproject.entity.answer.Statementanswer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatementAnswerService {
    @Resource
    StatementanswerMapper statementanswerMapper;
    public Status SubmitAnswer(Statementanswer[] answertemp){
        List answers=new ArrayList();
        for (Statementanswer statementanswer : answertemp) {
            answers.add(statementanswer);
        }
        statementanswerMapper.submitanswer(answers);
        return Status.Success(200);
    }
    public Status getanswer(QsidReceive qsidReceive){
        return Status.Success(statementanswerMapper.getanswer(qsidReceive.getQuestionid()),200);
    }
    public Status getsingleanswer(Integer qsid){
        return Status.Success(statementanswerMapper.getsingleanswer(qsid),200);
    }

}
