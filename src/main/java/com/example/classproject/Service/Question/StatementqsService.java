package com.example.classproject.Service.Question;

import com.example.classproject.Mapper.StatementqsMapper;
import com.example.classproject.Pojo.Status;
import com.example.classproject.entity.question.Statementqs;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StatementqsService {
    @Resource
    StatementqsMapper statementqsMapper;
    public Status addquestion(List question){
        statementqsMapper.addquestion(question);
        return Status.Success(200);
    }
    public Statementqs[] findqs(Integer questionaireid){
        return statementqsMapper.findqs(questionaireid);
    }

}
