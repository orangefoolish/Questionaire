<template>
  <div >
    <el-container style="height:100vh; ">
      <el-aside width="18%" >
        <teacherAside :changeShow="changeShow"/>
        </el-aside>
        <el-container>
          <el-button @click="this.logout()" style="position: absolute;top: 3vh;left: 93%;z-index: 1">登出</el-button>
          <el-main>
            <teacherQuestionarie v-if="show==='myQuestion'" />
            <TeacherInformation v-if="show==='myInformation'" :information="information"/>
            <si v-if="show==='studentInformation'"  :studentInformation="studentInformation" :classChoice='classChoice' />
          </el-main>
        </el-container>
    </el-container>
  </div>
</template>

<script>
import teacherAside from '../components/teacher/teacherAside.vue'
import teacherQuestionarie from "@/components/teacher/questionaireManage"
import si from "../components/teacher/studentManage.vue"
import TeacherInformation from "@/components/teacher/TeacherInformation";

export default {
  name: 'Teacher',
  components: {
    teacherAside,
    teacherQuestionarie,
    TeacherInformation,
    si
  },
  data() {
    return {
      show: "myInformation",
      information: {},
    }
  },
  mounted() {
    this.$nextTick(function (){
      if(!localStorage.getItem("token")||localStorage.getItem("token").length==0||!sessionStorage.getItem("manageclass")){
        this.$router.push("/");
        this.$message({
          type: "error",
          message: "登陆状态缺失！",
        })
      }
      sessionStorage.setItem("unpermission",true);
      this.information.manageclass=sessionStorage.getItem("manageclass");
      this.information.name=sessionStorage.getItem("teacherName");
      this.information.account=sessionStorage.getItem("account");
  })
  },
  methods:{
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
