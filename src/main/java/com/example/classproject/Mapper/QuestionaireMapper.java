package com.example.classproject.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.classproject.entity.Questionaire;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionaireMapper extends BaseMapper<Questionaire> {
    void addquestionaire(Questionaire questionaire);
    Questionaire[] selectquestionaire(List qsid);
}
