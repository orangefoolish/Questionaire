<template>
  <div style="width: 100%">
    <div class="title"><h1>{{title}}</h1></div>
    <img src="@/assets/Img/5.jpg" class="bgi">
    <div style="width: 70%;margin-left:15%;background-color: white" >
      <!--由于用groupname中问题的进行分类，index在这里指向了重复的selectanswer数组对象-->
      <div v-for="(question,id) in this.questionsbygroup" style="border-top: 2px solid">
        <div style="padding-top: 5vh;padding-bottom: 5vh;text-align: center;font-size: x-large">{{this.groupnames[id]}}</div>
        <selectquestion v-for="(item,index) in question.selectqs" :selectanswer="item.selectanswer" :questiondetail="item"></selectquestion>
        <multiselectquestion v-for="(item,index) in question.multiselectqs" :multiselectanswer="item.multiselectanswer" :questiondetail="item"></multiselectquestion>
        <blankquestion v-for="(item,index) in question.blankqs" :blankanswer="item.blankanswer" :questiondetail="item"></blankquestion>
        <statementquestion v-for="(item,index) in question.statementqs" :statementanswer="item.statementanswer" :questiondetail="item"></statementquestion>
      </div>
    </div>
    <div class="submit">
      <el-button type="primary" @click="submit">提交</el-button>
    </div>
    <el-backtop target="body"></el-backtop>

  </div>
</template>

<script>


import request from "@/pojo/request";
import selectquestion from "@/components/fillquestions/selectanswer";
import multiselectquestion from "@/components/fillquestions/multiselectanswer";
import blankquestion from "@/components/fillquestions/blankanswer";
import statementquestion from "@/components/fillquestions/statementanswer";

export default {
  name: 'Questionaire',
  components:{
    selectquestion,
    multiselectquestion,
    blankquestion,
    statementquestion
  },
  data() {
    return{
      title:sessionStorage.getItem("title"),
      questions:[],
      questionsbygroup:[],//按题目分类
      answer:[{}],

      selectqs:[],
      multiselectqs:[],
      blankqs:[],
      statementqs:[],

      selectanswer:[],
      multiselectanswer:[],
      blankanswer:[],
      statementanswer:[],
      groupnames:[],
      questionaireid:sessionStorage.getItem("questionaireid"),

      blankanswernum:[0],

    }


  },
  mounted:function () {//防止有傻逼回退页面导致多次填写
    this.$nextTick(function (){
      if(eval(sessionStorage.getItem("unpermission"))){
        this.$router.push("/student");
        return;
      }
      this.getquesionire()
    })
  },
  methods: {
    checkblank(){
      this.selectanswer=[];
      this.multiselectanswer=[];
      this.blankanswer=[];
      this.statementanswer=[];
      for(var i=0;i<this.questionsbygroup.length;i++){
          for(var j=0;j<this.questionsbygroup[i].selectqs.length;j++){
            this.selectanswer.push(this.questionsbygroup[i].selectqs[j].selectanswer);
            this.selectanswer[i].questionaireid=this.questionaireid;
          }
          for(var j=0;j<this.questionsbygroup[i].multiselectqs.length;j++){
            this.multiselectanswer.push(this.questionsbygroup[i].multiselectqs[j].multiselectanswer);

          }
          for(var j=0;j<this.questionsbygroup[i].blankqs.length;j++){
            this.blankanswer.push(this.questionsbygroup[i].blankqs[j].blankanswer);

          }
          for(var j=0;j<this.questionsbygroup[i].statementqs.length;j++){
            this.statementanswer.push(this.questionsbygroup[i].statementqs[j].statementanswer);
          }
      }
      for(var i=0;i<this.selectanswer.length;i++){
        if(!this.selectanswer[i].answer){
          return false;
        }
      }
      for(var i=0;i<this.multiselectanswer.length;i++){
        if(!this.multiselectanswer[i].answerarray){
          return false;
        }
        this.multiselectanswer[i].answer=this.multiselectanswer[i].answerarray.join(',');
      }
      for(var i=0;i<this.blankanswer.length;i++){
        if(this.blankanswer[i].answerarray.length!=this.blankanswer[i].blanknums){
          return false;
        }
        this.blankanswer[i].answer=this.blankanswer[i].answerarray.join(',');
      }
      for(var i=0;i<this.statementanswer.length;i++){
        if(!this.statementanswer[i].answer){
          return i;
        }
      }
      return -1;
    },
    submit() {
      if(this.checkblank()){
        this.$confirm('是否确定提交', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.selectanswer.length>0){
            request.post("/api/answer/submitselects",JSON.stringify(this.selectanswer))
          }if(this.blankanswer.length>0){
            request.post("/api/answer/submitblanks",JSON.stringify(this.blankanswer))
          }
          if(this.multiselectanswer.length>0){
            request.post("/api/answer/submitmultiselects",JSON.stringify(this.multiselectanswer))
          }
          if(this.statementanswer.length>0){
            request.post("/api/answer/submitstatements",JSON.stringify(this.statementanswer))
          }
          //获取新的完成的问卷列表
          request.put("/api/student/updatefinish",JSON.stringify({"hasfinish":this.questionaireid,"studentid":sessionStorage.getItem("studentid")})
              ).then(res=>{
             sessionStorage.setItem("hasfinish",JSON.stringify(res.data.split(",")))
          })
          sessionStorage.setItem("unpermission",true);
          this.$message({
            type: 'success',
            message: '提交成功!',
          });
          this.$router.push("/student")
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消',

          });
        });
      }
      else {
        this.$message({
          type: 'warning',
          message: '有题目未完成'
        });
      }
    },
    close(){
      window.opener=null;
      window.open("","_self");
      window.close();
    },
    getquesionire(){
      request.get("/api/question/findqs",{params:{"questionaireid":this.questionaireid}}).then(res=>{
            this.getgroupnames(res.data.selectqs,1)
            this.getgroupnames(res.data.multiselectqs,2)
            this.getgroupnames(res.data.blankqs,3)
            this.getgroupnames(res.data.statementqs,4)

      })

    },
    findgroupname(groupname){
      for(var i=0;i<this.groupnames.length;i++){
        if(groupname==this.groupnames[i]){
          return i;
        }
      }
      return -1;
    },
    getgroupnames(questions,type){
      var account=sessionStorage.getItem("account");
      var that=this;
      var pos=new Number();//记录题目在哪个组内
    for(var i=0;i<questions.length;i++){
      switch (type){
        case 1:
          questions[i].selectanswer={};
          questions[i].selectanswer.questionaireid=that.questionaireid;
          questions[i].selectanswer.questionid=questions[i].id;
          questions[i].selectanswer.stuaccount=account;
          break;
        case 2:
          questions[i].multiselectanswer= {};
          questions[i].multiselectanswer.questionaireid=that.questionaireid;
          questions[i].multiselectanswer.answerarray=[];
          questions[i].multiselectanswer.questionid=questions[i].id;
          questions[i].multiselectanswer.stuaccount=account;
          break;
        case 3:
          questions[i].blankanswer={};
          questions[i].blankanswer.answerarray=[];
          questions[i].blankanswer.questionaireid=that.questionaireid;
          questions[i].blankanswer.questionid=questions[i].id;
          questions[i].blankanswer.stuaccount=account;
          questions[i].blankanswer.blanknums=questions[i].blanknums;
          break;
        case 4:
          questions[i].statementanswer= {};
          questions[i].statementanswer.questionaireid=that.questionaireid;
          questions[i].statementanswer.questionid=questions[i].id;
          questions[i].statementanswer.stuaccount=account;
          break;
      }
      if(this.groupnames.length==0){
        this.questionsbygroup.push({
          "selectqs": [], "multiselectqs": [], "blankqs": [], "statementqs": [],
          "blanknum":0
        })
        this.groupnames.push(questions[i].groupname)
        pos=0;
      }
      else if((pos=this.findgroupname(questions[i].groupname))==-1){
        this.questionsbygroup.push({"selectqs":[],"multiselectqs":[],"blankqs":[],"statementqs":[],
          "blanknum":0
        })
        this.groupnames.push(questions[i].groupname);
        pos=this.questionsbygroup.length-1;

      }
      switch (type){
        case 1:
          this.questionsbygroup[pos].selectqs.push(questions[i])
          break;
        case 2:
          this.questionsbygroup[pos].blanknum++;
          this.questionsbygroup[pos].multiselectqs.push(questions[i])
          break;
        case 3:
          this.questionsbygroup[pos].blanknum++;
          this.questionsbygroup[pos].blankqs.push(questions[i])
          break;
        case 4:
          this.questionsbygroup[pos].statementqs.push(questions[i])
          break;
      }
    }


  },

  },

}
</script>

<style scoped>
*{
  margin: 0;
  padding: 0;
}
.submit {
  text-align: center;
  text-decoration:none;
  background-color: #fff;
  height: 5%;
  width: 70%;
  margin: 0 auto;
  margin-bottom: 3%;
  padding: 3% 5% 3%;
  box-sizing: border-box;
  text-align: center;
  color: #0095ff

}
img{
  height: 100%;
  width: 100%;
  position: fixed;
  left: 0%;
  top: 0%;
  z-index: -1;
}
.answer {
  margin:2% 0px ;
  width: 100%;

}
.question  .boxChoice{
  width:550px;
  margin: 0 auto;

}
.question  th{
  width: 30%;
  text-align: left;
}
.title{
  background-color: #fff;
  width: 70%;
  margin: 0 auto;
  margin-top: 5%;
  padding-top: 5vh;
  padding-bottom: 5vh;
  font-size: xxx-large;
  text-align: center;
  color: #0095ff
}
.el-input__inner{
  padding: 0 5px !important;
}
a {
  text-decoration: none;
  color: #000;
}
</style>
