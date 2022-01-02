<template>
  <div id="resultMultiSelect">
    <div class="table" >
      <el-table :data="mutiselectqs" stripe border style="width: 90%">
        <el-table-column :label="mutiselectqs.detail" align="center">
          <el-table-column
              prop="charactor"
              label="选项">
          </el-table-column>
          <el-table-column
              prop="select"
              label="题目内容">
          </el-table-column>
          <el-table-column
              prop="num"
              label="人数">
          </el-table-column>
          <el-table-column
              label="操作">
            <template #default="scope">
              <el-button @click=" detail(scope.row)" type="text">详情</el-button>
            </template>
          </el-table-column>
        </el-table-column>
      </el-table>

      <el-dialog v-model="showdialog" :title="this.choice.title">
      选择的该选项的学生：{{this.choice.student}}
      </el-dialog>

      <div class="map">
        <el-button primary @click="show=!show,draw()" style="display: inline-block">绘图</el-button>
        <div  id="shn"  v-show="show" style="width: 500px;height:400px; display: inline-block"></div>
      </div>
    </div>
  </div>
</template>

<script>
import * as echarts from "echarts";

export default {
  name: "resultMultiSelect",
  props:['mutiselectqs'],
  data(){
    return{
      show:null,
      showdialog:false,
      choice:[],
      tag:[],
      num:[],
      val:[]
    }
  },
  methods:{
    datadeal(){
      console.log(this.mutiselectqs)
      for(var i=0; i<this.mutiselectqs.length;i++){
        this.tag[i]=this.mutiselectqs[i].select
        this.num[i]=this.mutiselectqs[i].num
        this.val.push({})
        this.val[i].value=this.mutiselectqs[i].num
        this.val[i].name=this.mutiselectqs[i].select
      }

    },
    drawshn(){
      var myChart = echarts.init(document.getElementById('shn'));
      var option = {
        title: {
        },
        xAxis: {
          type: 'category',
          data: this.tag
        },
        yAxis: {},
        series: [
          {
            type: 'bar',
            name: '1',
            data: this.num
          }
        ]
      };
      myChart.setOption(option);
    },
    draw(){
      if(this.show)
        this.datadeal()
        this.drawshn()
    },
    detail(row){
      console.log(this.mutiselectqs)
      console.log(row)
      this.showdialog=true
      this.choice.push({})
      this.choice.title=row.charactor+'、'+row.select
      this.choice.student=row.stuaccount

    }
  }
}
</script>

<style scoped>
  .map{
    width: 80%;

    margin-top: 5%;
  }
  .table {
    margin-top:5vh;
    margin-left: 10%;
  }
</style>