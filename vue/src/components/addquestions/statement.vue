<template>
  <div style="margin-top: 5vh">
    <el-form style="background-color: white;padding-left: 5%;padding-right: 5%;padding-bottom: 3vh" :inline="true" :model="selectquestions" class="demo-form-inline">
      <span style="font-size:35px ;padding-top: 2vh;padding-right: 2%">{{ questionid+1 }}</span>
      <el-select v-model="this.questiontype" placeholder="题目类型"
                 style="padding-top: 3vh" @change="changetype">
        <el-option label="单选题" value="singleselect"></el-option>
        <el-option label="多选题" value="multiselect"></el-option>
        <el-option label="填空题" value="blank"></el-option>
        <el-option label="陈述题" value="statement"></el-option>
      </el-select>
      <el-select v-model="this.questiondetail.groupname" placeholder="请选择组别"
                 style="padding-left: 10vh" @change="changetype">
        <el-option v-for="item in this.groups" :label="item" :value="item"></el-option>
      </el-select>
      <el-button style="margin-left: 22%;" @click="deletequestion">删除</el-button>
      <el-input maxlength="105" style="padding-top: 3vh;padding-bottom: 0.5vh" :autosize="{ minRows:6}" type="textarea" v-model="this.questiondetail.detail" placeholder="在此输入题干"></el-input>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "statement",
  data(){
    return{
      title:"",
      questiontype:"statement"
    }
  },
  props:['questionid','questiondetail','groups'],
  methods:{
    deletequestion(){
      this.$emit("deletequestion",this.questionid);
    },
    changetype(){
      this.$emit("changetype",this.questionid,this.questiontype);
      this.questiontype="statement";
    }
  }
}
</script>

<style scoped>

</style>