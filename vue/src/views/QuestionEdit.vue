<template>
  <el-scrollbar height="100vh" style="padding-top:3vh;scroll-margin: 20px;text-align:center;display: inline-block;font-size: 35px;position:fixed;width: 20%;border-right: 1px solid;box-shadow:2px 0px 5px black ">
    <p>大纲</p>
    <ul>
      <li  class="asideselects" v-for="(item,index) in questiondetails">{{index+1}}.{{item.detail}}
        <span style="font-size: 14px">({{this.questionname[this.questions[index].name]}})</span></li>
    </ul>
  </el-scrollbar >
  <div style="margin-left: 20%;min-height:100vh;width: 76%;padding:2%;background-color: rgb(227,227,227)" >
    <div>
    <div style="position: fixed;box-shadow:3px 3px 6px grey;z-index:2;margin-top:-16vh;width: 52%;padding-left:3%;height: 6vh;padding-top:3vh;padding-bottom:3vh;font-size:25px;background-color: white">
      <span style="text-align: center">添加题目</span>
      <el-button style="margin-left: 3%" @click="addquestion(1)">单选题</el-button>
      <el-button @click="addquestion(2)">多选题</el-button>
      <el-button @click="addquestion(3)">填空题</el-button>
      <el-button @click="addquestion(4)">陈述题</el-button>
      <el-button @click="this.ifaddgroup=true" style="margin-right: 1%">添加组别</el-button>
      <el-dialog title="添加新的组别" v-model="this.ifaddgroup" width="40%">
        <div style="padding-left: 10%;padding-bottom: 7vh">
          <span style="padding-right: 5%">请输入组别名称</span>
          <el-input style="width: 60%" v-model="this.newgroup"></el-input>
        </div>
        <span style="padding-left: 30%" slot="footer" class="dialog-footer">
          <el-button style="margin: 10px" @click="this.ifaddgroup=false">取 消</el-button>
          <el-button style="margin: 10px" type="primary" @click="addgroup(this.newgroup)">确 定</el-button>
        </span>
      </el-dialog>
      <el-popconfirm @confirm="submit" title="保存后无法修改，确定保存吗?">
        <template #reference>
          <el-button>保存问卷</el-button>
        </template>
      </el-popconfirm>
    </div>
      <!--这里是题目区-->
    <div id="main" style="margin-top:14vh;width:72%">
      <ul>
        <li v-for="(item,index) in questions" >
          <keep-alive>
            <component ref="child" @deletequestion="deletequestion" :groups="groups" :addgroup="addgroup" :questiondetail="questiondetails[index]" @changetype="changetype" :questionid="index"  v-bind:is="item"></component>
          </keep-alive>
        </li>
      </ul>
    </div>
  </div>
    <!--这个是设定-->
    <el-scrollbar height="68vh" style="height: 70vh;box-shadow:-2px 2px 6px grey;padding: 2%;top:30vh;width: 17%;margin-top:-15vh;font-weight:1000;position:fixed;background-color:white;margin-left: 56%">
      <p >设置</p>
      <p style="padding-top: 3vh;padding-right:5%">开始日期</p>
      <el-date-picker
          v-model="this.creatertoqsnaire.starttime"
          type="datetime"
          placeholder="选择开始时间"
          style="margin-top: 2vh"
      >
      </el-date-picker>
      <p style="padding-top: 3vh;padding-right:5%">结束日期</p>
      <el-date-picker
          v-model="this.creatertoqsnaire.endtime"
          type="datetime"
          placeholder="选择结束时间"
          style="margin-top: 2vh"
      >
      </el-date-picker>
      <div>
        <span style="display: inline-block;padding-bottom:2vh;padding-top: 3vh;padding-right:5%">调查目标</span>
        <span style="display: inline-block;padding-left: 2%;padding-right: 2%;border-radius:3px;cursor: pointer;
        background-color: #a7a7a7;text-align:center;font-size: 25px" @click="this.targetnum++">+</span>
        <span style="display: inline-block;margin-left: 3%;padding-left: 3%;padding-right:3%;border-radius:3px;cursor: pointer;
        background-color: #aaaaaa;text-align:center;font-size: 25px" @click="this.targetnum=this.targetnum==1?this.targetnum:this.targetnum-1">-</span>
      </div>
      <el-select v-model="this.targets[index]" style="padding: 1vh" v-for="(item,index) in this.targetnum">
        <el-option v-for="item in manageclasses"
                   :value="item"
                   :label="item">
        </el-option>
      </el-select>
    </el-scrollbar>
   <el-backtop/>

  </div>

</template>

<script>
import "@/assets/css/reset.css"
import singleselect from "@/components/addquestions/singleselect";
import multiselect from "@/components/addquestions/multiselect";
import blank from "@/components/addquestions/blank";
import statement from "@/components/addquestions/statement";
import request from "@/pojo/request";
import global_variavble from "@/views/global_variavble";

export default {
  name: "QuestionEdit",
  components: {
    singleselect,
    multiselect,
    blank,
    statement
  },
  data() {
    return {
      targetnum:1,//问卷调查对象数量
      groups:[],//储存组别
      ifaddgroup:false,//是否添加组别
      newgroup:null,//新组别名称
      questions: [singleselect],//保存题目组件
      questiondetails: [{}],//刚开始有一个题目所以要放一个
      //父组件把detail传给子组件，然后子组件修改数据
      questionname: {"singleselect": "单选题", "multiselect": "多选题", "blank": "填空题", "statement": "陈述题"},
      questionnum: 1,
      creatertoqsnaire: {"starttime":null,"endtime":null},
      questionslist:{"selectqs":[],"multiselectqs":[],"blankqs":[],"statementqs":[]},//保存题目数组
      questionaireid:sessionStorage.getItem("qsnaireid"),
      hasempty:false,
      manageclasses:[],//老师管理的班级
      targets:[]//问卷的目标

    }
  },
  methods: {
    addquestion(type) {
      this.questiondetails.push({});//每次添加题目都要加一个json
      switch (type) {
        case 1:
          this.questions.push(singleselect);
          break;
        case 2:
          this.questions.push(multiselect);
          break;
        case 3:
          this.questions.push(blank);
          break;
        case 4:
          this.questions.push(statement)
          break;
      }
      this.questionnum++;
    },
    changetype(id, type) {
      var temp;
      switch (type) {
        case "singleselect":
          temp = 1;
          break;
        case "multiselect":
          temp = 2;
          break;
        case "blank":
          temp = 3;
          break;
        case "statement":
          temp = 4;
          break;
      }
      switch (temp) {
        case 1:
          if (this.questions[id] != singleselect) {
            this.questions[id] = singleselect;
          }
          break;
        case 2:
          if (this.questions[id] != multiselect) {
            this.questions[id] = multiselect;

          }
          break;
        case 3:
          if (this.questions[id] != blank) {
            this.questions[id] = blank
          }
          break;
        case 4:
          if (this.questions[id] != statement) {
            this.questions[id] = statement
          }
          break;
      }
    },
    datacheck(data, type) {//数据为空检查
      if (!(data.detail&&data.groupname)) {//公共数据检查，节省一部分资源
        return false;
      }
      switch (type) {
        case 1:
          if (data.selectstemp.length == 0) {
            return false;
          }
          break;
        case 2:
          if (data.selectstemp.length == 0 || !data.maxselectnum || !data.minselectnum) {
            return false;
          }
          break;
        case 3:
          if (data.blanknums == 0) {
            return false;
          }
          break;
        default:
          break;
      }
      return true;
    },
    submit() {
      if(!this.creatertoqsnaire.starttime||!this.creatertoqsnaire.endtime||!this.targets){
        this.$message({
          type: "warning",
          message: "问卷信息存在空值数据，请检查！",
        })
        return;
      }
      else{
        var now=new Date();
      if(this.creatertoqsnaire.endtime<now){
          this.$message({
            type: "warning",
            message: "结束时间不能晚于到当前时间！",
          })
          return;
        }
        else if(this.creatertoqsnaire.endtime<=this.creatertoqsnaire.starttime){
          this.$message({
            type: "warning",
            message: "结束时间不能早于开始时间！",
          })
          return;
        }
      }
      this.questionslist.selectqs = [];
      this.questionslist.multiselectqs = [];
      this.questionslist.blankqs = [];
      this.questionslist.statementqs = [];
      var that = this;//下边的this变了，所以这里得存一个this
      var len = this.questions.length;
      var item;
      var selectslen=0;
      for (var index = 0; index < len; index++) {
        that.questiondetails[index].questionaireid=that.questionaireid;
        item = this.questions[index];

        switch (item.name) {
          case "singleselect":
            if (!that.datacheck(that.questiondetails[index], 1)) {
              this.hasempty =true;
              this.emptymessage();
              return;
            }else{
              that.questionslist.selectqs.push(that.questiondetails[index]);
              selectslen=that.questionslist.selectqs.length-1;
              that.questionslist.selectqs[selectslen].selects=that.questiondetails[index].selectstemp.toString();
            }
            break;
          case "multiselect":
            if (!that.datacheck(that.questiondetails[index], 2)) {
              this.hasempty =true;
              this.emptymessage();
              return;
            }else{
              that.questionslist.multiselectqs.push(that.questiondetails[index]);
              selectslen=that.questionslist.multiselectqs.length-1;
              that.questionslist.multiselectqs[selectslen].selects=that.questiondetails[index].selectstemp.toString();
            }
            break;
          case "blank":
            if (!that.datacheck(that.questiondetails[index], 3)) {
              this.hasempty =true;
              this.emptymessage();
              return;
            }else{
              that.questionslist.blankqs.push(that.questiondetails[index]);
            }
            break;
          case "statement":
            if (!that.datacheck(that.questiondetails[index], 4)) {
              this.hasempty =true;
              this.emptymessage();
              return;
            }else{
              that.questionslist.statementqs.push(that.questiondetails[index]);

            }
            break;
        }
      }
      this.creatertoqsnaire.qsnaireid=this.questionaireid;
      this.creatertoqsnaire.target=this.targets.toString();
      console.log(this.creatertoqsnaire.target)
      request.put("/api/teacher/updateconnection",JSON.stringify(this.creatertoqsnaire)).then(res=>{
        if(res.code==401){
          this.$router.push("/");
          this.$message({
            type: "error",
            message: "登陆过期！",
          })
          return;
        }
      })
      request.post("/api/question/addquestions",JSON.stringify(this.questionslist));
      this.$message({
        type: "success",
        message: "保存成功",
      })
      sessionStorage.setItem("unpermission",false)
      this.$router.push("/teacher");
    },
    emptymessage(){
      if(this.hasempty){
        this.$message({
          type: "warning",
          message: "存在空值数据，请检查！",
        })
      }
    },
    addgroup(){
      if(this.newgroup==""||this.newgroup==null||this.newgroup==" "){
        this.$message({
          type: "warning",
          message: "组别不能为空！",
        })
        return;
      }
      this.groups.push(this.newgroup);
      this.newgroup=null;
      this.ifaddgroup=false;
    },
    deletequestion(questionid){//删除题目
      var len=this.questions.length;
     if(questionid!=len-1){
       for(var i=questionid;i<len;i++){
         this.questions[i]=this.questions[i+1];
         this.questiondetails[i]=this.questiondetails[i+1];
       }
     }
      this.questions.splice(len-1)
      this.questiondetails.splice(len-1)
    }

  },
  mounted() {
    this.$nextTick(function (){
      if(eval(sessionStorage.getItem("unpermission"))){
        this.$router.push("/teacher")
      }
      this.manageclasses=sessionStorage.getItem("manageclass").split(',');
    })
  }
}
</script>

<style scoped>
.asideselects{
  font-size: 25px;
  padding: 5%;
  width: 89%;
  overflow: hidden;

}
.asideselects:hover{
  padding: 5%;
  width: 89%;
  overflow: hidden;
  background-color: #d7d7d7;
}
</style>