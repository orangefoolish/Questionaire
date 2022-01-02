<template>
  <div id="resultText">
    <div class="table" >
      <el-table :data="blankqs.answer" stripe border style="width: 90%">
        <el-table-column :label="blankqs.detail" align="center">
          <el-table-column
              prop="stuaccount"
              label="学生姓名">
          </el-table-column>
          <el-table-column
              prop="answer"
              label="学生回答">
          </el-table-column>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "resultText",
  props:['blankqs'],
  data(){
    return{
    }
  },
}
</script>

<style scoped>
#resultText{
  width: 100%;
  margin-left: 5%;
}
.table {
  margin-top: 5vh;
}
</style>