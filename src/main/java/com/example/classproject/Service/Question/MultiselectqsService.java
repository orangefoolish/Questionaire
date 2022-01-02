package com.example.classproject.Service.Question;

import com.example.classproject.Mapper.MultiselectqsMapper;
import com.example.classproject.Pojo.Status;
import com.example.classproject.entity.question.Multiselectqs;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MultiselectqsService {
    @Resource
    MultiselectqsMapper multiselectqsMapper;
    public Status addquestion(List questions){
        multiselectqsMapper.addquestion(questions);
        return Status.Success(200);
    }
    public Multiselectqs[] findqs(Integer questionaireid){
        return multiselectqsMapper.findqs(questionaireid);
    }
}

