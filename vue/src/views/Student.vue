<template>
  <div id="app">
    <el-container style="height: 100vh">
      <studentAside style="width: 18%" :changeShow="changeShow"/>
        <el-container>
          <el-button @click="this.logout()" style="position: absolute;top: 3vh;left: 93%;z-index:1">登出</el-button>
          <el-button @click="this.updatepassword()" style="position: absolute;top: 3vh;left: 85%;z-index:1">修改密码</el-button>
          <el-dialog v-model="this.ifupdatepassword">
            <p style="text-align: center;font-size: xx-large;padding: 2vh;color: black">输入新密码</p>
            <el-input style="width: 80%;padding-left: 10%;padding-right: 10%" v-model="this.newpassword"></el-input>
            <el-button style="margin-left: 40%;margin-top: 5vh;" @click="submitnewpassword()">确认</el-button>
            <el-button @click="this.ifupdatepassword=false,this.newpassword=null">取消</el-button>
          </el-dialog>
          <el-main style="width: 82%">
            <studentQuestionaire v-if="show=='myQuestion'" :question="question"/>
            <studentInformation v-if="show=='myInformation'" :information="information"/>
          </el-main>
        </el-container>
    </el-container>
    <el-button @click="this.test"></el-button>
  </div>
</template>

<script>
import myInformation from "../components/student/studentInformation.vue"
import studentQuestionaire from "@/components/student/studentQuestionaire";
import studentAside from "@/components/student/studentAside";
import studentInformation from "@/components/student/studentInformation";
import request from "@/pojo/request";

export default {
  name: 'student',
  components: {
    studentAside,
    studentQuestionaire,
    studentInformation
  },
  data(){
    return{
      ifupdatepassword:false,
      newpassword:null,
      show:"myInformation",
      question:[],
      hasfinish:[],
      information:{},
      qsidReceive:{"questionid":[11,12,13]}
      
    }
  },
  mounted() {
    this.$nextTick(function (){
      if(!localStorage.getItem("token")||localStorage.getItem("token").length==0){
        this.$router.push("/");
        this.$message({
          type: "error",
          message: "登陆状态缺失！",
        })
      }
      sessionStorage.setItem("unpermission",true);
      this.information.studentid=sessionStorage.getItem("studentid");
      this.information.studentname=sessionStorage.getItem("studentname");
      this.information.class=sessionStorage.getItem("class");
      this.information.account=sessionStorage.getItem("account");
      if(sessionStorage.getItem("hasfinish")){
        this.information.finishnum=JSON.parse(sessionStorage.getItem("hasfinish")).length;
      }
    })
  },
  methods:{
    submitnewpassword(){
      request.put("/api/student/updatepassword", JSON.stringify({"password":this.newpassword,"studentid":this.information.studentid})).then(res=>{
        this.ifupdatepassword=false;
        if (res.code == 200) {
          this.$message({
            type: "success",
            message: "密码修改成功！",
          })
        } else {
          this.$message({
            type: "error",
            message: "未知错误，请重试！",
          })
        }
      })
    },
    updatepassword(){
      this.ifupdatepassword=true;
    },
    changeShow(paper){
      this.show=paper;
    },
    logout(){
      this.$router.push("/");
      this.$message({
        type: "success",
        message: "登出成功！",
      })
    }
  }
}
</script>
  
<style>
</style>
