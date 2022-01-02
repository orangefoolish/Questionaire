package com.example.classproject.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.classproject.entity.question.Statementqs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatementqsMapper extends BaseMapper<Statementqs> {
    void addquestion(List addquestion);

    Statementqs[] findqs(Integer questionaireid);

    void deleteqs(Integer questionaireid);
}
