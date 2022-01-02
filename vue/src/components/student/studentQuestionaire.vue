<template>
  <div id="myQuestion">
       <h1>我的问卷:</h1>
       <el-divider/>
    <div class="table">
            <el-table :data="questionaire"
            stripe
            border
            style="color: black;">
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
            prop="statusword"
            label="填写状态">
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
            prop="address"
            label="操作">
            <template #default="scope">
            <el-button v-if="scope.row.status" @click="handleClick(scope.row)" type="text" >填写</el-button>
            </template>
            </el-table-column>
            </el-table>
    </div>
  </div>
</template>

<script>
import request from "@/pojo/request";

export default {
    name:"studentQuestionaire",
    data (){
      return{
        creater: sessionStorage.getItem("account"),
        questionaire:[],
        hasfinish:[],
      }
    },
    mounted:function () {
      this.$nextTick(function (){
        if(sessionStorage.getItem("hasfinish")){
          this.hasfinish=JSON.parse(sessionStorage.getItem("hasfinish"));
        }
          this.getquesionire();
      })
    },
  methods: {
    judgeStatus(hasfinished,questionairetemp,starttime,endtime) {
      var nowtime;
      nowtime = new Date();
      for (var i = 0; i < this.hasfinish.length; i++) {
        if (this.hasfinish[i] == hasfinished) {
          questionairetemp.statusword = "已完成";
          return false;
        }
      }
      if (nowtime > endtime) {
        questionairetemp.statusword = "已结束";
      } else if (nowtime < starttime) {
        questionairetemp.statusword = "未开始";
      } else {
        questionairetemp.statusword = "进行中";
        return true;
      }

    },
    handleClick(row) {
      sessionStorage.setItem("questionaireid",row.qsnaireid);
      sessionStorage.setItem("title",row.title)
      sessionStorage.setItem("unpermission",false);
      this.$router.push("/questionaire")
    },
    getquesionire(){
      request.get("/api/student/getquestionaires",{params:{"target":sessionStorage.getItem("class")}}).then(res=>{
        if(res.code==401){
          this.$router.push("/");
          this.$message({
            type: "error",
            message: "登陆过期！",
          })
          return;
        }
        this.questionaire=res.data
        var starttime;
        var endtime;
        var starttimetemp;
        var endtimetemp;
        for(var i=0;i<this.questionaire.length;i++){
          starttimetemp=endtimetemp="";
          starttime=new Date(this.questionaire[i].starttime);
          endtime=new Date(this.questionaire[i].endtime);
          this.questionaire[i].status=this.judgeStatus(this.questionaire[i].qsnaireid,this.questionaire[i],starttime,endtime);
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
    getIndex(index){
      return index+1;
    }
  },
}
</script>

<style scoped>
h1 {
  padding-left: 5%;
  padding-top: 3vh;
  font-size: xxx-large;
}

#myQuestion{

  position: relative;
}
.table {
  text-align: center;
  width:100%;
}
</style>