package com.example.classproject.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.classproject.entity.answer.Blankanswer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlankanswerMapper extends BaseMapper<Blankanswer> {
    void submitanswer(List blankanswer);
    Blankanswer[] getanswer(Integer[] qsid);
    Blankanswer[] getsingleanswer(Integer qsid);

    void deleteanswer(Integer questionaireid);
}
