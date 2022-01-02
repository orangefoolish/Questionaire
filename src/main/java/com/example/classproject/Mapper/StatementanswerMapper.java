package com.example.classproject.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.classproject.Pojo.QsidReceive;
import com.example.classproject.entity.answer.Statementanswer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatementanswerMapper extends BaseMapper<Statementanswer> {
    void submitanswer(List Selectanswer);
    Statementanswer[] getanswer(Integer[] qsid);

    Statementanswer[] getsingleanswer(Integer qsid);

    void deleteanswer(Integer questionaireid);
}
