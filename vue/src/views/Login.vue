<template>
  <div id="Register">
    <h1>{{title}}</h1>
    <div class="register">
      <el-button class="indentity" plain @click="changeIndentity('student')" :disabled="indentity=='student'">学生登录</el-button>
      <el-button class="indentity" plain @click="changeIndentity('teacher')" :disabled="indentity=='teacher'">老师登录</el-button>
      <el-input v-model="account"  placeholder='请输入你的学号'  class="account" clearable @change="changeAccount(account)"></el-input>
      <el-input v-model="password"  placeholder='请输入你的密码'  show-password class="password" @change=" changePassword(password)"></el-input>
      <input type="checkbox" style="margin: 2vh;display: inline-block" v-model="this.ifsave">记住账号
      <div class="button">
        <el-button type="primary" round @click="loading" @keyup.enter="loading" :loading="flag">登录</el-button>
      </div>
    </div>
  </div>
</template>

<script>

import request from "@/pojo/request";
import global_variavble from "@/views/global_variavble";

export default {
  name: 'Login',
  data () {
    return{
      title:"调查问卷系统",
      account:null,
      password:"",
      flag:false,
      indentity:"student",
      ifsave:true//是否保存账号密码
    }
  },
  mounted() {
    this.password=null;
    this.account=localStorage.getItem("account");
    document.title='问卷调查系统';
    sessionStorage.clear();
    localStorage.setItem("token",null);
  },
  methods:{
    save(){
      localStorage.setItem("account",this.account);
      localStorage.setItem("account",this.account);
    },
    submit(){
      if(this.indentity=='teacher'){
        request.get('/api/teacher/login',{params:{"account":this.account,
            "password":this.password}}).then(res=>{
          if(res.code===200) {
            localStorage.setItem("token",res.data.token);
            sessionStorage.setItem("account",this.account);
            sessionStorage.setItem("teacherName",res.data.name);
            sessionStorage.setItem("manageclass",res.data.manageclass);
            if(this.ifsave){
              this.save()
            }
            this.$router.push("/teacher");
          }
          else {
            this.$message({type: 'warning', message: '账号或密码错误'});
            this.flag=false;
          }
        })
      }
      else if(this.indentity=='student'){
        request.get('/api/student/login',{params:{"account":this.account,
            "password":this.password}}).then(res=>{
          if(res.code===200) {
            localStorage.setItem("token",res.data.token);
            sessionStorage.setItem("account",this.account);
            sessionStorage.setItem("studentname",res.data.studentname);
            sessionStorage.setItem("studentid",res.data.studentid);
            sessionStorage.setItem("class",res.data.beclass)
            sessionStorage.setItem("studentid",res.data.studentid);
            if(res.data.hasfinish){
              sessionStorage.setItem("hasfinish",JSON.stringify(res.data.hasfinish.split(",")))
            }
            if(this.ifsave){
              this.save()
            }
            this.$router.push("/student")
          }
          else {
            this.$message({type: 'warning', message: '账号或密码错误'});
            this.flag=false;
          }
        })
      }

    },
    changeAccount(account){
      this.account=account
    },
    changePassword(password){
      this.password=password
    },
    loading(){
      if(this.account && this.password )
      {
        this.flag=true
        this.submit()
      }
      else  this.$message('请输入学号密码')
    },
    changeIndentity(indentity){
      this.indentity=indentity;
    }
  }
}
</script>
<style scoped>
a{
  text-decoration: none;
  color: #000;
}
#Register {
  background-image: url(../assets/Img/home-page-background-all.png);
  background-repeat: no-repeat;
  height: 850px;

}
.register {
  position: relative;
  left: 65%;
  width: 25%;
  height: 50vh;
  background-color: rgb(255, 255, 255);
  padding: 30px;
  padding-bottom: 20vh;
  box-sizing: border-box;

}
h1 {
  font-size: 400%;
  margin-left: 5%;
  color: white;
}

.button {
  margin: 0 auto;
  margin-top: 50px;
  text-align: center;
}

.account{
  margin: 30px 0px 30px 0px;
}
.el-button.is-round {
  width: 100%;
}
</style>
