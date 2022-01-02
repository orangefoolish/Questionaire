<template>
  <div id="myQuestion">
    <h1>我的问卷:</h1>
    <el-divider/>

    <el-button class="excel" type="primary" @click="createQuestionrie" style="margin-left: 85%">新建调查问卷</el-button>
    <el-dialog title="新建调查问卷" v-model="flag" width="40%">
      <el-input placeholder="输入问卷名称" style="margin-left: 20%;width: 60%;display: inline-block" v-model="newquestionaire.title" ></el-input>
      <el-button @click="submit" style="margin-left:35%;margin-top:5%">确定</el-button>
      <el-button @click="this.flag=false" style="margin-left:5%;margin-top:5%">取消</el-button>
    </el-dialog>
    <div class="table">
      <el-table :data="questionaire"
                stripe
                border
                style="width: 100%">
        <el-table-column
            type="index"
            :index="getIndex"
            label="序号">
        </el-table-column>
        <el-table-column
            prop="title"
            label="问卷名称">
        </el-table-column>
        <el-table-column
            prop="creater"
            label="发布者">
        </el-table-column>
        <el-table-column
            prop="starttime"
            label="发布时间">
        </el-table-column>
        <el-table-column
            prop="endtime"
            label="截止时间">
        </el-table-column>
        <el-table-column
            label="操作">
          <template #default="scope">
            <el-button @click="ToEdit(scope.row)" v-if="scope.row.draft==true" type="text">编辑</el-button>
            <el-button @click=" checkResult(scope.row)" v-if="scope.row.draft==false" type="text">查看</el-button>
            <el-button @click="deletequestionaire(scope.row.qsnaireid)" type="text" >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>

import request from "@/pojo/request";
import global_variavble from "@/views/global_variavble";

export default {
  name:"teacherQuestionarie",
  data (){
    return{
      creater: sessionStorage.getItem("account"),
      questionaire:[],
      flag:false,
      newquestionaire:{}
    }
  },
  mounted:function () {
    this.$nextTick(function (){
      this.getquesionire()
    })
  },
  methods: {
    ToEdit(row) {
      sessionStorage.setItem("qsnaireid",row.qsnaireid);
      this.$router.push("/qsedit");
    },
    deletequestionaire(id) {
      request.delete("/api/teacher/deletequestionaire", {params: {"questionaireid": id}}).then(res => {
        console.log(id)
        if (res.code == 200) {
          this.getquesionire();
          this.$message({
            type: "success",
            message: "删除成功",
          })
        } else {
          this.$message({
            type: "error",
            message: "删除失败，出现未知错误！",
          })
        }
      })
    },
    getquesionire() {
      request.get("/api/teacher/getmyquestionaire", {params: {"creater": this.creater}}).then(res=>{
        if(res.data!=200) {
          if (res.code == 401) {
            this.$router.push("/");
            this.$message({
              type: "error",
              message: "登陆过期！",
            })
            return;
          } else {
            if (res.code == 401) {
              this.$router.push("/");
              this.$message({
                type: "error",
                message: "未知错误！",
              })
            }
          }
        }
        this.questionaire=res.data;
        var starttime;
        var endtime;
        var starttimetemp;
        var endtimetemp;
        var nowtime=new Date();
        for(var i=0;i<this.questionaire.length;i++){
          starttime=endtime=starttimetemp=endtimetemp="";
          starttime=new Date(this.questionaire[i].starttime);
          endtime=new Date(this.questionaire[i].endtime);
          if(nowtime>endtime){
            this.questionaire[i].status="已结束";
          }
          else if(nowtime<starttime){
            this.questionaire[i].status="未开始";
          }
          else{
            this.questionaire[i].status="进行中";
          }
          starttimetemp+=starttime.getFullYear()+'/';
          starttimetemp+=starttime.getMonth()+1+'/';
          starttimetemp+=starttime.getDate()+' ';
          starttimetemp+=starttime.getHours()+':';
          starttimetemp+=starttime.getMinutes()+':';
          starttimetemp+=starttime.getSeconds();

          endtimetemp+=endtime.getFullYear()+'/';
          endtimetemp+=endtime.getMonth()+1+'/';
          endtimetemp+=endtime.getDate()+' ';
          endtimetemp+=endtime.getHours()+':';
          endtimetemp+=endtime.getMinutes()+':';
          endtimetemp+=endtime.getSeconds();
          this.questionaire[i].starttime=starttimetemp;
          this.questionaire[i].endtime=endtimetemp;
        }
      })
    },
    getIndex(index) {
      return index + 1;
    },
    createQuestionrie() {
      this.flag = true
    },
    submit() {
      if(!this.newquestionaire.title){
        this.$message({
          type: "error",
          message: "问卷名不能为空！",
        })
        return;
      }
      this.newquestionaire.creater = this.creater
      request.post("/api/teacher/createconnection", JSON.stringify(this.newquestionaire)).then(res => {
        this.ifcreate=false
        sessionStorage.setItem("qsnaireid",res.data);
        sessionStorage.setItem("unpermission",false);
        this.$router.push("/qsedit")
      })
    },
    checkResult(row){
      sessionStorage.setItem("qsnaireid", row.qsnaireid);
      sessionStorage.setItem("qsinformation",JSON.stringify(row))
      this.$router.push("/result")
    }
  }
}
</script>

<style scoped>
h1 {
  padding-top: 3vh;
  padding-left: 5%;
  font-size: xxx-large;
}
#myQuestion{

}
.table {
  display: inline-block;
  padding-top: 8vh;
  text-align: center;
  width:100%;

}

</style>