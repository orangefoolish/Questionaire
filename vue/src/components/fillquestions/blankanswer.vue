<template>
  <div style="width: 90%;margin-left: 5%;padding-bottom: 3vh;">
    <p style="padding: 5vh;font-size: 18px">{{this.questiondetail.detail}}</p>
    <el-input style="width: 10%;margin-left: 3%;margin-bottom: 2vh"  v-for="(item,index) in this.questiondetail.blanknums" v-model="this.blankanswer.answerarray[index]"></el-input>
    <el-divider/>
  </div>
</template>

<script>

export default {
  name: "blankanswer",
  data(){
    return{
    }
  },
  props:["questiondetail","blankanswer"],
}
</script>

<style scoped>

</style>