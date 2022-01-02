package com.example.classproject.entity.answer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class Selectanswer {
    Integer questionid;
    @TableId(type = IdType.AUTO)
    Integer id;
    String answer;
    Integer stuaccount;
    Integer num=1;
    Integer questionaireid;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Selectanswer that = (Selectanswer) o;
        return (answer.equals(that.answer)&&questionid==that.questionid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer);
    }

}
