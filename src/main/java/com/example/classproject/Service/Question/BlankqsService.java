package com.example.classproject.Service.Question;

import com.example.classproject.Mapper.BlankqsMapper;
import com.example.classproject.Pojo.Status;
import com.example.classproject.entity.question.Blankqs;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlankqsService {
    @Resource
    BlankqsMapper blankqsMapper;
    public Status addquestion(List question){
        blankqsMapper.addquestion(question);
        return Status.Success(200);
    }
    public Blankqs[] findqs(Integer questionaireid){
        return blankqsMapper.findqs(questionaireid);
    }
}
