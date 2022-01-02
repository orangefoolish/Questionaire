package com.example.classproject.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.classproject.entity.question.Selectqs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SelectqsMapper extends BaseMapper<Selectqs> {
    void addquestion(List questions);
    Selectqs[] findqs(Integer questionaireid);
    void deleteqs(Integer questionaireid);
}
