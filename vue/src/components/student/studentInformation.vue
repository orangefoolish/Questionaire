<template>
  <div id="myInformation">
    <h1>个人信息:</h1>
    <el-divider/>
    <div class="table">
    <el-descriptions  border style="width:90%" >
    <el-descriptions-item label="用户名">{{information.account}}</el-descriptions-item>
    <el-descriptions-item label="姓名">{{information.studentname}}</el-descriptions-item>
    <el-descriptions-item label="学号">{{information.studentid}}</el-descriptions-item>
    <el-descriptions-item label="班级">{{information.class}}</el-descriptions-item>
    <el-descriptions-item label="完成的问卷数量">
      {{information.finishnum}}
    </el-descriptions-item>
    </el-descriptions>
    </div>
  </div>
</template>

<script>
export default {
    name:"studentInformation",
    props:["information"]

}
</script>

<style scoped>
h1 {
  padding-left: 5%;
  padding-top: 3vh;
  font-size: xxx-large;
}

#myInformation{
  position: relative;
}
.table {

  text-align: center;
  width:93%;
  position: absolute;
  top: 20vh;
  left: 8%;
}
</style>