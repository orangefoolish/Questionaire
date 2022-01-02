<template>
  <div style="margin-top: 5vh" @click="getheight()" ref="select">
    <el-form style="background-color: white;padding-left: 5%;margin-top: 3vh;padding-right: 5%;padding-bottom: 3vh" :inline="true" :model="questiontype" class="demo-form-inline">
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
      <el-input maxlength="105" style="padding-top: 3vh" v-model="this.questiondetail.detail" type="textarea" placeholder="在此输入题干"></el-input>
      <ul>
        <li v-for="index in selectnums">
          <el-button style="margin: 2%;margin-left: 0;display: inline-block">{{charactors[index-1]}}</el-button>
          <el-input style="width: 91%" v-model="this.questiondetail.selectstemp[index-1]" @change="this.datacheck()"></el-input>
        </li>
        <el-button @click="addselect">添加选项</el-button>
      </ul>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "singleselect",
  data(){
    return{
      questiontype:"singleselect",
      charactors:['A','B','C','D','E','F','G','H','I','J'],
      selectnums:0,

    }
  },
  props:['questionid','questiondetail','groups'],
  mounted() {
    this.$nextTick(function (){
      this.addselect();
    })
  },
  methods:{
    getheight(){
      console.log(this.$refs.select.offsetHeight)
    },
    datacheck(){
      var len=this.questiondetail.selectstemp.length-1;
      var temp=this.questiondetail.selectstemp[len];
      console.log(len)
      if(this.questiondetail.selectstemp.indexOf(temp)!=len){
        this.questiondetail.selectstemp[len]=null;
        this.$message({
          type: "warning",
          message: "选项的值不可以重复！"
        })
      }
    },
    deletequestion(){
      this.$emit("deletequestion",this.questionid);
    },
    addselect(){
      if(this.selectnums==0){//渲染时需要一个selectstemp数组，但是没有，所以这里要创建一个，不然会出错
        this.questiondetail.selectstemp=[];
        this.selectnums++;
        return;
      }

      if(this.selectnums>=10){
        this.$message({
          type: "warning",
          message: "选项最多十个！",
        })
        return;
      }
      this.selectnums++;
    },
    changetype(){
      this.$emit("changetype",this.questionid,this.questiontype);
      this.questiontype="singleselect";//调用后questiontype里的值会改变，所以要固定它
    }
  }
}
</script>

<style scoped>

</style>