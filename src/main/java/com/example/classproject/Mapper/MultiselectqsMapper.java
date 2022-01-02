package com.example.classproject.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.classproject.entity.question.Multiselectqs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MultiselectqsMapper extends BaseMapper<Multiselectqs> {
    void addquestion(List questions);
    Multiselectqs[] findqs(Integer questionaireid);

    void deleteqs(Integer questionaireid);
}
