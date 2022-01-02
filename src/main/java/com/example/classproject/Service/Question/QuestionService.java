package com.example.classproject.Service.Question;

import com.example.classproject.Mapper.BlankqsMapper;
import com.example.classproject.Mapper.MultiselectqsMapper;
import com.example.classproject.Mapper.SelectqsMapper;
import com.example.classproject.Mapper.StatementqsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuestionService {
    @Resource
    SelectqsMapper selectqsMapper;
    @Resource
    BlankqsMapper blankqsMapper;
    @Resource
    StatementqsMapper statementqsMapper;
    @Resource
    MultiselectqsMapper multiselectqsMapper;
    public void delete(Integer questionaireid){
        selectqsMapper.deleteqs(questionaireid);
        multiselectqsMapper.deleteqs(questionaireid);
        blankqsMapper.deleteqs(questionaireid);
        statementqsMapper.deleteqs(questionaireid);
    }
}
