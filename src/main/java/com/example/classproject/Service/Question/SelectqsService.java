package com.example.classproject.Service.Question;

import com.example.classproject.Mapper.SelectqsMapper;
import com.example.classproject.Pojo.Status;
import com.example.classproject.entity.question.Selectqs;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SelectqsService {
    @Resource
    SelectqsMapper selectqsMapper;
    public Status addquestion(List questions){
        selectqsMapper.addquestion(questions);
        return Status.Success(200);
    }
    public Selectqs[] findqs(Integer questionaireid){
        return selectqsMapper.findqs(questionaireid);
    }

}
