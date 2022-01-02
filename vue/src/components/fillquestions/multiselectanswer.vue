<template>
  <div style="width: 90%;margin-left: 5%;padding-bottom: 3vh">
    <p style="padding:5vh;font-size: 22px">{{this.questiondetail.detail}}</p>
    <el-checkbox-group :min="this.questiondetail.minselectnum" v-model="this.multiselectanswer.answerarray" :max="this.questiondetail.maxselectnum">
      <el-checkbox style="padding-left: 5%;padding-top: 5vh;display:inline-block" v-for="(item,index) in this.questiondetail.selects.split(',')"
                    :label="item"></el-checkbox>
    </el-checkbox-group>
  <el-divider/>
  </div>
</template>

<script>

export default {
  name: "multiselectanswer",
  props:["questiondetail","multiselectanswer"],
  data(){
    return{

    }
  }
}
</script>

<style scoped>

</style>