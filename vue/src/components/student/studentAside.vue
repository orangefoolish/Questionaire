<template>
  <div id="myAside" style="height: 100vh;background-color: rgb(238, 241, 246)">
    <el-menu
      class="el-menu-vertical-demo"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#CCCCCC">
      <el-menu-item index="1" @click="changeShow('myInformation')">
        <i class="el-icon-user"></i>
        <p style="text-align:center;padding-top: 3vh;padding-bottom: 3vh;font-size: 25px">个人信息</p>
      </el-menu-item>
      <el-menu-item index="2" @click="changeShow('myQuestion')">
        <i class="el-icon-folder-opened"></i>
        <p style="text-align:center;padding-top: 3vh;padding-bottom: 3vh;font-size: 25px">我的问卷</p>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'studentAside',
  props:['changeShow']
}
</script >

<style scoped>
</style>
