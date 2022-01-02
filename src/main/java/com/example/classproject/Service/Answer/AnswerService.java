package com.example.classproject.Service.Answer;

import com.example.classproject.Mapper.BlankanswerMapper;
import com.example.classproject.Mapper.MultiselectanswerMapper;
import com.example.classproject.Mapper.SelectanswerMapper;
import com.example.classproject.Mapper.StatementanswerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AnswerService {
    @Resource
    SelectanswerMapper selectanswerMapper;
    @Resource
    MultiselectanswerMapper multiselectanswerMapper;;
    @Resource
    BlankanswerMapper blankanswerMapper;
    @Resource
    StatementanswerMapper statementanswerMapper;
    public void delete(Integer questionaireid){
        selectanswerMapper.deleteanswer(questionaireid);
        multiselectanswerMapper.deleteanswer(questionaireid);
        blankanswerMapper.deleteanswer(questionaireid);
        statementanswerMapper.deleteanswer(questionaireid);
    }
}
