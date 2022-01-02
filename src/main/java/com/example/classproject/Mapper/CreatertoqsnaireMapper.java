package com.example.classproject.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.classproject.entity.Creatertoqsnaire;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CreatertoqsnaireMapper extends BaseMapper<Creatertoqsnaire> {
    void createconnect(Creatertoqsnaire createrToQsnaire);
    Creatertoqsnaire[] getmyquestionaire(Integer creater);
    Creatertoqsnaire[] stugetquestionaire(String traget);
    void updateconnection(Creatertoqsnaire creatertoqsnaire);

    void deleteByQuestionaireid(Integer questionaireid);
}
