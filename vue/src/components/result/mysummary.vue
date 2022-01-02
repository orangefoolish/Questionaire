<template>
  <div id="summary">
    <h1>{{ this.qsinformation.title}}</h1>
    <hr>
    <div class="table">
      <el-descriptions  border style="width:90%" >
        <el-descriptions-item label="发布者">{{this.qsinformation.creater}}</el-descriptions-item>
        <el-descriptions-item label="开始时间">{{this.qsinformation.starttime}}</el-descriptions-item>
        <el-descriptions-item label="截至时间">{{this.qsinformation.endtime}}</el-descriptions-item>
        <el-descriptions-item label="目标班级">{{this.qsinformation.target}}</el-descriptions-item>
        <el-descriptions-item label="状态">{{this.qsinformation.status}}</el-descriptions-item>
      </el-descriptions>
    </div>
  </div>
</template>

<script>

export default {
  name: "mysummary",
  data(){
    return{
      qsinformation:JSON.parse(sessionStorage.getItem("qsinformation"))
    }
  },
  mounted() {
    console.log(this.qsinformation)
  },
  methods:{}
}
</script>

<style scoped>
  div{
    margin: 0 auto;
  }
  #summary{
    position: relative;
  }
  h1 {
    display: block;
    position: absolute;
    top: 50px ;
    left: 100px;
    font-size: xx-large;
  }
  hr {
    position: absolute;
    top: 120px;
    left: 50px;
    background-color: #063146;
    width: 95%;
  }
  .table {
    text-align: center;
    width:80%;
    position: absolute;
    top: 200px;
    left: 150px;
  }
</style>