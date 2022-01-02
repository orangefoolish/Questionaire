<template>
  <div style="width: 90%;margin-left: 5%;padding-bottom: 3vh;">
    <p style="padding:5vh;font-size: 22px">{{this.questiondetail.detail}}</p>
    <el-radio-group style="width: 100%" v-model="this.selectanswer.answer">
      <el-radio style="padding-left: 5%;display:inline-block" v-for="item in this.questiondetail.selects.split(',')" :label="item">{{item}}</el-radio>
    </el-radio-group>
    <el-divider></el-divider>
  </div>
</template>

<script>

export default {
  name: "selectanswer",
  props:["questiondetail","selectanswer"],
  data(){
    return{
    }
  },

}
</script>

<style scoped>

</style>