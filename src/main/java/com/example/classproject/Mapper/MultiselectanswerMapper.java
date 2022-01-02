package com.example.classproject.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.classproject.Pojo.Status;
import com.example.classproject.entity.answer.Multiselectanswer;
import com.example.classproject.entity.answer.Selectanswer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MultiselectanswerMapper extends BaseMapper<Multiselectanswer> {
    void submitanswer(List Multiselectanswer);
    Multiselectanswer[] getanswer(Integer[] questionid);

    Multiselectanswer[] getsingleanswer(Integer qsid);

    void deleteanswer(Integer questionaireid);
}
