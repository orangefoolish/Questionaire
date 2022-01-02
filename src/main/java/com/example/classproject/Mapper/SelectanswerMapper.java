package com.example.classproject.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.classproject.Pojo.QsidReceive;
import com.example.classproject.entity.answer.Selectanswer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SelectanswerMapper extends BaseMapper<Selectanswer> {
    void submitanswer(List Selectanswer);
    Selectanswer[] getanswer(Integer[] qsid);
    Selectanswer[] getsingleanswer(Integer qsid);
    void deleteanswer(Integer questionaireid);

}
