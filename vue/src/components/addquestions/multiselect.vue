<template>
  <div style="margin-top: 5vh">
    <el-form style="padding-left: 5%;background-color: white;padding-right: 5%;padding-bottom: 3vh" :inline="true" :model="selectquestions" class="demo-form-inline">
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
        <li v-for="(index,item) in selectnums">
          <el-button style="margin: 2%;margin-left: 0;display: inline-block">{{charactors[index-1]}}</el-button>
          <el-input style="width: 91%" v-model="this.questiondetail.selectstemp[index-1]"></el-input>
        </li>
        <el-button @click="addselect">添加选项</el-button>
        <span style="margin-left: 40px">最少</span>
        <el-input-number v-model="this.questiondetail.minselectnum" :min="1" :max="10" @change="checkdata" />
        <span>个选项</span>
        <span style="margin-left: 40px">最多</span>
        <el-input-number v-model="this.questiondetail.maxselectnum" :min="1" :max="10" @change="checkdata" />
        <span>个选项</span>
      </ul>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "multiselect",
  data(){
    return{
      questiontype:"multiselect",
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
    deletequestion(){
      this.$emit("deletequestion",this.questionid);
    },
    checkdata(){
      if(this.questiondetail.maxselectnum&&this.questiondetail.minselectnum){
          if(this.questiondetail.minselectnum>this.questiondetail.maxselectnum){
            this.$message({
              type: "warning",
              message: "最多选项不能少于最少选项！",
            })
            this.questiondetail.minselectnum=1;
          }
      }
    },
    addselect(){
      if(this.selectnums==0){
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
      this.questiontype="multiselect";
    }
  }
}
</script>

<style scoped>

</style>