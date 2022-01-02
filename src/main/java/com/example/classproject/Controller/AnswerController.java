package com.example.classproject.Controller;

import com.example.classproject.Pojo.QsidReceive;
import com.example.classproject.Service.Answer.BlankAnswerService;
import com.example.classproject.Service.Answer.MultiselectAnswerService;
import com.example.classproject.Service.Answer.SelectAnswerService;
import com.example.classproject.Service.Answer.StatementAnswerService;
import com.example.classproject.Pojo.Status;
import com.example.classproject.entity.answer.Blankanswer;
import com.example.classproject.entity.answer.Multiselectanswer;
import com.example.classproject.entity.answer.Selectanswer;
import com.example.classproject.entity.answer.Statementanswer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Resource
    SelectAnswerService selectAnswerService;
    @Resource
    MultiselectAnswerService multiselectAnswerService;
    @Resource
    BlankAnswerService blankAnswerService;
    @Resource
    StatementAnswerService statementAnswerServicer;
    @PostMapping("/submitselects")
    public Status SbumitAnswer(@RequestBody Selectanswer[] answertemmp){
        return selectAnswerService.SubmitAnswer(answertemmp);
    }
    @PostMapping("/submitmultiselects")
    public Status SbumitMultiAnswer(@RequestBody Multiselectanswer[] answertemmp){
        return multiselectAnswerService.SubmitAnswer(answertemmp);
    }
    @PostMapping("/submitblanks")
    public Status SbumitAnswer(@RequestBody Blankanswer[] answertemmp){
        return blankAnswerService.SubmitAnswer(answertemmp);
    }
    @PostMapping("/submitstatements")
    public Status SbumitAnswer(@RequestBody Statementanswer[] answertemmp){
        return statementAnswerServicer.SubmitAnswer(answertemmp);
    }
    //获取某一种题型所有的答案
    @PostMapping("/getstatementanswers")
    public Status GetStatementAnswers(@RequestBody QsidReceive qsidReceive){
        return statementAnswerServicer.getanswer(qsidReceive);
    }
    @PostMapping("/getblankanswers")
    public Status GetBlankAnswers(@RequestBody QsidReceive qsidReceive){
        return blankAnswerService.getanswer(qsidReceive);
    }
    @PostMapping("/getselectanswers")
    public Status GetSelectAnswers(@RequestBody QsidReceive qsidReceive){
        return selectAnswerService.getanswer(qsidReceive);
    }
    @PostMapping("/getmultiselectanswers")
    public Status GetMultiSelectAnswers(@RequestBody QsidReceive qsidReceive){
        return multiselectAnswerService.getanswer(qsidReceive);
    }
    //获取某一种类型某一道题的答案
    @GetMapping("/getsingleanswer")
    public Status GetSingleAnswer(@RequestParam Integer qsid,@RequestParam Integer qstype){
        Status status = null;
        switch (qstype){
            case 1:
                    status=selectAnswerService.getsingleanswer(qsid);
                break;
            case 2:
                     status=multiselectAnswerService.getsingleanswer(qsid);
                break;
            case 3:
                    status=blankAnswerService.getsingleanswer(qsid);
                break;
            case 4:
                     status=statementAnswerServicer.getsingleanswer(qsid);
                break;
        }
        return status;
    }
}
