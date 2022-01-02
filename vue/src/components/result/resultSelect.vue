<template>
  <div id="resultSelect" >
    <div class="table">
      <el-table :data="selectqs"  stripe border style="width: 90%">
        <el-table-column :label="this.selectqs.detail" align="center">
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
                <el-button @click=" detail(scope.row)" type="text" >详情</el-button>
              </template>
            </el-table-column>
        </el-table-column>
      </el-table>

        <el-dialog v-model="showdialog" :title="this.choice.title">
          选择的该选项的学生：{{this.choice.student}}
        </el-dialog>

        <div class="map">
          <el-button primary @click="show=!show,draw()" style="display: inline-block">绘图</el-button>
          <div  id="thau" v-show="show" style="width: 500px;height:400px;display: inline-block"></div>
          <div  id="shn"  v-show="show" style="width: 500px;height:400px; display: inline-block"></div>
        </div>

    </div>
  </div>

</template>


<script>
import * as echarts from 'echarts'
export default {
  name: "resultSelect",
  props:['selectqs'],
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

      for(var i=0; i<this.selectqs.length;i++){
        this.tag[i]=this.selectqs[i].select
        this.num[i]=this.selectqs[i].num
        this.val.push({})
        this.val[i].value=this.selectqs[i].num
        this.val[i].name=this.selectqs[i].select
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
    drawthau(){
      // 基于准备好的dom，初始化echarts实例
      var myChart = echarts.init(document.getElementById('thau'));
      var option = {
        title: {
          text: this.selectqs.detail,
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: this.tag
        },
        series: [
          {
            name: '1',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: this.val,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };

      if(!this.flag){
        this.flag=true
        let currentIndex = -1;

        setInterval(function() {
          var dataLen = option.series[0].data.length;
          // 取消之前高亮的图形
          myChart.dispatchAction({
            type: 'downplay',
            seriesIndex: 0,
            dataIndex: currentIndex
          });
          currentIndex = (currentIndex + 1) % dataLen;
          // 高亮当前图形
          myChart.dispatchAction({
            type: 'highlight',
            seriesIndex: 0,
            dataIndex: currentIndex
          });
          // 显示 tooltip
          myChart.dispatchAction({
            type: 'showTip',
            seriesIndex: 0,
            dataIndex: currentIndex
          });
        }, 1000);
      }


      myChart.setOption(option);
    },
    draw(){
      if(this.show);
      this.datadeal()
      console.log(this.num)
      console.log(this.tag)
      console.log(this.val)
      this.drawshn()
      this.drawthau()
    },
    detail(row){
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
    margin-left: 10%;
    margin-top: 5vh;
  }
</style>