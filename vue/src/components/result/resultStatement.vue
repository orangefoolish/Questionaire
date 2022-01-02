<template>
  <div id="resultStatement">
    <div class="table">
      <el-table :data="statementqs.answer" stripe border style="width: 90%">
        <el-table-column :label="statementqs.detail" align="center">
          <el-table-column
              prop="stuaccount"
              label="学生姓名">
          </el-table-column>
          <el-table-column
              prop="answer"
              label="学生作答">
          </el-table-column>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "resultStatement",
  props:['statementqs'],
  data(){
    return{
    }
  }
}
</script>

<style scoped>
.el-table{
  margin: 0 auto;
}
.table {
  margin-top: 5vh;
}
</style>