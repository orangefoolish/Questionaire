<template>
  <div style="width: 100%;height:100vh;text-align: right;background-color: rgb(238, 241, 246)">
    <el-menu
      class="el-menu-vertical-demo"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="1" @click="changeShow('myInformation')">
        <i class="el-icon-user"></i>
        <span style="text-align:center;padding-top: 3vh;padding-bottom: 3vh;font-size: 25px">个人信息</span>
      </el-menu-item>
      <el-menu-item index="2" @click="changeShow('myQuestion')">
        <i class="el-icon-folder-opened"></i>
        <span style="text-align:center;padding-top: 3vh;padding-bottom: 3vh;font-size: 25px">我的问卷</span>
      </el-menu-item>
      <el-menu-item index="3" @click="changeShow('studentInformation')" >
        <i class="el-icon-folder-opened"></i>
        <span style="text-align:center;padding-top: 3vh;padding-bottom: 3vh;font-size: 25px">学生信息</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'myAside',
  props:['changeShow']
}
</script >
<style scoped>

</style>
