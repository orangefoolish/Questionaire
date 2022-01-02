package com.example.classproject.Controller;

import com.example.classproject.Service.Question.BlankqsService;
import com.example.classproject.Service.Question.MultiselectqsService;
import com.example.classproject.Service.Question.SelectqsService;
import com.example.classproject.Service.Question.StatementqsService;
import com.example.classproject.Pojo.Status;
import com.example.classproject.entity.question.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    SelectqsService selectqsService;
    @Resource
    BlankqsService blankqsService;
    @Resource
    StatementqsService statementqsService;
    @Resource
    MultiselectqsService multiselectService;
    @PostMapping("/addquestions")
    public void AddQuestions(@RequestBody Questions questions){
        if(questions.getSelectqs().length!=0){
            List selectqs=new ArrayList();
            for (Selectqs selectqs1 :questions.getSelectqs()) {
                selectqs.add(selectqs1);
            }
            selectqsService.addquestion(selectqs);
        }
        if(questions.getMultiselectqs().length!=0){
            List multiselectqs=new ArrayList();
            for (Multiselectqs multiselectqs1 : questions.getMultiselectqs()) {
                multiselectqs.add(multiselectqs1);
            }
            multiselectService.addquestion(multiselectqs);
        }
        if(questions.getBlankqs().length!=0){
            List blankqs=new ArrayList();
            for (Blankqs blankqs1 : questions.getBlankqs()) {
                blankqs.add(blankqs1);
            }
            blankqsService.addquestion(blankqs);
        }
        if(questions.getStatementqs().length!=0){
            List stateqs=new ArrayList();
            for (Statementqs statementqs1 : questions.getStatementqs()) {
                stateqs.add(statementqs1);
            }
            statementqsService.addquestion(stateqs);
        }
    }
    @GetMapping("/findqs")
    public Status findqs(@RequestParam Integer questionaireid){
        Map<String,Object[]> map = new HashMap<>();
        map.put("selectqs",selectqsService.findqs(questionaireid));
        map.put("multiselectqs",multiselectService.findqs(questionaireid));
        map.put("blankqs",blankqsService.findqs(questionaireid));
        map.put("statementqs",statementqsService.findqs(questionaireid));
        return Status.Success(map,200);
    }

}
