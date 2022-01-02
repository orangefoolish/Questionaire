<template>
  <div id="myInformation">
    <h1 style="font-size: 50px">个人信息:</h1>
    <el-divider></el-divider>
    <div class="table">
    <el-descriptions  border style="width:90%">
    <el-descriptions-item label="姓名">{{information.name}}</el-descriptions-item>
    <el-descriptions-item label="账号">{{information.account}}</el-descriptions-item>
    <el-descriptions-item label="管理班级">{{information.manageclass}}</el-descriptions-item>
    <el-descriptions-item label="联系地址">{{information.address}}</el-descriptions-item>
    </el-descriptions>
    </div>
  </div>
</template>

<script>
export default {
    name:"TeacherInformation",
    props:["information"]

}
</script>

<style scoped>
h1 {
  padding-top: 3vh;
  padding-left: 5%;
  font-size: xx-large;
}

#myInformation{
  position: relative;
}
.table {
  text-align: center;
  width:80%;
  position: absolute;
  top: 150px;
  left: 150px;
}
</style>