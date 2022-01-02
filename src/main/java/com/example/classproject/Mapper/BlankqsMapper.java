package com.example.classproject.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.classproject.entity.question.Blankqs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlankqsMapper extends BaseMapper<Blankqs> {
    void addquestion(List question);
    Blankqs[] findqs(Integer questionaireid);

    void deleteqs(Integer questionaireid);
}
