<template>
  <div style="width: 90%;margin-left: 5%;padding-bottom: 3vh;">
    <p style="padding: 5vh;font-size: 22px">{{this.questiondetail.detail}}</p>
    <el-input :autosize="{ minRows:5}"  type="textarea" style="width: 90%;padding-left: 5%"  maxlength="125" v-model="this.statementanswer.answer"></el-input>
    <el-divider/>
  </div>
</template>

<script>

export default {
  name: "statementanswer",
  data(){
    return{
    }
  },
  props:["questiondetail","index","statementanswer"],
}
</script>

<style scoped>

</style>