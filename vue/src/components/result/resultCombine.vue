<template>
  <div id="resultCombine">
    <div class="table" >
      <el-table :data="data" stripe border style="width: 80%">
        <el-table-column :label="message" align="center">
          <el-table-column
              prop="id"
              label="选项">
          </el-table-column>
          <el-table-column
              prop="name"
              label="选择答案">
          </el-table-column>
          <el-table-column
              prop="st"
              label="填空答案">
          </el-table-column>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "resultCombine",
  data(){
    return{
      message:'1.大学你所学的哪几门课程或者哪几个老师给你留下深刻的印象，为什么？',
      title:['学生姓名','选择答案','填空答案'],
      data:[{id:'刘宇',name:'A',st:'xxxxxxxxxxxxxxxx'},
        {id:'王涛',name:'B' ,st:'xxxxxxxxxxxxxxxx'},
        {id:'茅岗',name:'A' ,st:'xxxxxxxxxxxxxxxx'},
        {id:'刘宇',name:'A',st:'xxxxxxxxxxxxxxxx' },
        {id:'王涛',name:'B',st:'xxxxxxxxxxxxxxxx' },
        {id:'茅岗',name:'A' ,st:'xxxxxxxxxxxxxxxx'},
        {id:'刘宇',name:'A' ,st:'xxxxxxxxxxxxxxxx'},
        {id:'王涛',name:'C' ,st:'xxxxxxxxxxxxxxxx'},
        {id:'茅岗',name:'D',st:'xxxxxxxxxxxxxxxx' }

      ]
    }
  }
}
</script>

<style scoped>
.el-table{
  text-align: center;
  margin: 0 auto;
}
</style>