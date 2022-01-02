<template>
  <div class="result">
    <el-container style="height: 900px;min-width: 1500px; border: 1px solid #eee">

      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu
            class="el-menu-vertical-demo"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
          <el-menu-item index="1" @click="changeShow('mysummary')">
            <i class="el-icon-user"></i>
            <span slot="title">总览</span>
          </el-menu-item>
          <el-sub-menu index="2">
            <template #title>
              <el-icon><location /></el-icon>
              <span>单选题</span>
            </template>
            <el-menu-item-group>
              <el-menu-item  v-for="(item,num) in data.selectqs"  @click="selectdata('resultSelect',item)" :index=item.id >第{{num+1}}题</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="3">
            <template #title>
              <el-icon><location /></el-icon>
              <span>多选题</span>
            </template>
            <el-menu-item-group>
              <el-menu-item  v-for="(item,num) in data.multiselectqs"  @click="mutiselectdata('resultMultiSelect',item)" :index=item.id>第{{num+1}}题</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="4">
            <template #title>
              <el-icon><location /></el-icon>
              <span>填空题</span>
            </template>
            <el-menu-item-group>
              <el-menu-item  v-for="(item,num) in data.blankqs"  @click="blankdata('resultText',item)" :index=item.id>第{{num+1}}题</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="5">
            <template #title>
              <el-icon><location /></el-icon>
              <span>陈述题</span>
            </template>
            <el-menu-item-group>
              <el-menu-item  v-for="(item,num) in data.statementqs"  @click="statementdata('resultStatement',item)" :index=item.id>第{{num+1}}题</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
        </el-menu>

      </el-aside>
      <el-container>
        <el-header >
          <div class="top"  style="margin-top: 2vh;float: right">
            <el-button @click="this.logout()" style="display: inline-block">登出</el-button>
            <el-button @click="this.back()" style="display: inline-block">返回</el-button>
          </div>
        </el-header>
        <el-main style="width: 100%">
          <mysummary v-if="show==='mysummary'" style="width: 100%"></mysummary>
          <resultSelect ref='resultSelect' v-if="show==='resultSelect'" :selectqs="this.select" />
          <resultMultiSelect ref='resultMultiSelect' v-if="show==='resultMultiSelect'" :mutiselectqs="this.mutiselect"/>
          <resultText v-if="show==='resultText'" :blankqs="this.blank"/>
          <resultStatement v-if="show==='resultStatement'" :statementqs='this.statement'/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>

import resultSelect from "@/components/result/resultSelect";
import resultMultiSelect from "@/components/result/resultMultiSelect";
import resultCombine from "@/components/result/resultCombine";
import resultText from "@/components/result/resultText";
import resultStatement from "@/components/result/resultStatement";
import mysummary from "@/components/result/mysummary";
import request from "@/pojo/request";


export default {
  name: "Result",
  data(){
    return{
      show:'mysummary',
      charactors:['A',"B",'C','D','E','F','G','H','I','J'],
      data:[],
      select:[],
      mutiselect:[],
      blank:[],
      statement:[],
      selectqsid:{"questionid":[]},
      mutiselectqsid:{"questionid":[]},
      blankqsid:{"questionid":[]},
      statementqsid:{"questionid":[]},
    }
  },
  components:{
    resultSelect,
    resultMultiSelect,
    resultCombine,
    resultText,
    resultStatement,
    mysummary
  },
  mounted() {
    document.title="学生答题情况",
    this.findqs()
  },
  methods:{
    changeShow(paper,item){
      this.show=paper
    },
    findqs(){
      request.get("/api/question/findqs",{params:{"questionaireid":sessionStorage.getItem("qsnaireid")}}).then(res=>{
        this.data=res.data
        for(var i=0;i<this.data.selectqs.length;i++){
          this.selectqsid.questionid[i]=this.data.selectqs[i].id
        }
        for(var i=0;i<this.data.multiselectqs.length;i++){
          this.mutiselectqsid.questionid[i]=this.data.multiselectqs[i].id
        }
        for(var i=0;i<this.data.blankqs.length;i++){
          this.blankqsid.questionid[i]=this.data.blankqs[i].id
        }
        for(var i=0;i<this.data.statementqs.length;i++){
          this.statementqsid.questionid[i]=this.data.statementqs[i].id
        }
      })
    },
    selectdata(paper,item){
      this.show=paper
      this.select=[]
      this.select.detail=item.detail
      var temp=item.selects.split(',');
      for(var i=0;i<temp.length;i++){
        this.select.push({})
        this.select[i].charactor=this.charactors[i];
        this.select[i].select=temp[i];
      }
      this.getselectanswer(item.id)
      if(this.$refs.resultSelect) this.$refs.resultSelect.show=false
    },
    mutiselectdata(paper,item){
      this.show=paper
      this.mutiselect=[]
      this.mutiselect.detail=item.detail
      var temp=item.selects.split(',');
      for(var i=0;i<temp.length;i++){
        this.mutiselect.push({})
        this.mutiselect[i].charactor=this.charactors[i];
        this.mutiselect[i].select=temp[i];
      }
      if(this.$refs.resultMultiSelect)this.$refs.resultMultiSelect.show=false
      this.getmutiselectanswer(item.id)
    },
    blankdata(paper,item){
      this.show=paper
      this.blank=[]
      this.blank.detail=item.detail
      this.blank.blanknum=item.blanknums
      this.getblanktanswer(item.id)
    },
    statementdata(paper,item,num){
      this.show=paper
      this.statement=[]
      this.statement.detail=item.detail
      this.getstatementanswer(item.id)
    },
    getselectanswer(qsid){
        request.get("/api/answer/getsingleanswer",{params:{"qsid":qsid,"qstype":1}}).then(res=>{
          console.log(res)
          for(var i=0;i< this.select.length;i++){
            this.select[i].num=0;
            for(var j=0;j<res.data.length;j++){
              if(this.select[i].select==res.data[j].answer){
                this.select[i].stuaccount=res.data[j].stuaccount
                this.select[i].num=res.data[j].num
              }
            }
          }
        })
      },
    getmutiselectanswer(qsid){
          request.get("/api/answer/getsingleanswer",{params:{"qsid":qsid,"qstype":2}}).then(res=>{
            this.mutiselect.answer=res.data
            for(var i=0;i<this.mutiselect.length;i++){
              this.mutiselect[i].num=0;
              var stu=[]
              for(var j=0;j<res.data.length;j++){
                var temp=res.data[j].answer.split(',');
                for(var x=0;x<temp.length;x++){
                  if(this.mutiselect[i].select===temp[x]){
                    this.mutiselect[i].num+=res.data[j].num
                    stu.push(res.data[j].stuaccount)
                  }
                  this.mutiselect[i].stuaccount=stu
                }
              }
            }
          })
    },
    getblanktanswer(qsid){
        request.get("/api/answer/getsingleanswer",{params:{"qsid":qsid,"qstype":3}}).then(res=>{
          this.blank.answer=res.data
        })
    },
    getstatementanswer(qsid){
      console.log(qsid)
        request.get("/api/answer/getsingleanswer",{params:{"qsid":qsid,"qstype":4}}).then(res=>{
          this.statement.answer=res.data
        })
    },
    logout(){
      this.$router.push("/");
      this.$message({
        type: "success",
        message: "登出成功！",
      })
    } ,
    back(){
      this.$router.push("/teacher");
    }

  }
}
</script>

<style scoped>
* {
  /* 页面初始化 清除元素的内外边距 */
  padding: 0;
  margin: 0;
}
body {
  /* 宽度占浏览器可视区域的高度 */
  width: 100vm;
  background-color: #f2f2f2;
  color: black;
}
li {
  /* 清除li元素前面的项目符号 */
  list-style: none;
}
a {
  /* 清除a元素的下划线 */
  text-decoration: none;
  color: #000;
}
.navbar {
  width: 100%;
  height: 70px;
  background-color: #fff;
  /* 盒子阴影 */
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
}
.navbar .nav {
  width: 1200px;
  height: 100%;
  /* 让元素自动水平居中 */
  margin: 0 auto;
}
.navbar .nav ul {
  /* 相对定位 */
  position: relative;
  /* 弹性布局 */
  display: flex;
  /* 让子元素平均分配宽度 */
  justify-content: space-around;
  width: 100%;
  height: 100%;
}
.navbar .nav ul > li {
  width: 100%;
  height: 100%;
}
.navbar .nav ul > li > a {
  /* 因为a元素是行内元素 必须将其转为行内块或者块级才能设置宽度和高度 */
  display: block;
  width: 100%;
  height: 100%;
  line-height: 70px;
  text-align: center;
}
.navbar .nav ul > li ol {
  width: 100%;
  background-color: #fff;
  box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2);
  /* 让盒子先沿着y轴缩放到0 也就是隐藏了 */
  transform: scaleY(0);
  /* 我们需要将盒子从上面滑动下来 设置一下缩放的中心点即可 设置到最上面的中间位置 */
  transform-origin: 50% 0;
  /* 设置过渡 */
  transition: all 0.6s;
}
.navbar .nav ul > li ol li {
  height: 70px;
  border-bottom: 1px solid rgb(245, 245, 245);
}
.navbar .nav ul > li ol li a {
  display: block;
  width: 100%;
  height: 100%;
  line-height: 70px;
  text-align: center;
}
.navbar .nav ul > li ol li:hover {
  background-color: rgba(0, 0, 0, 0.03);
}
.navbar .nav ul > li:hover ol {
  transform: scaleY(1);
}
/* 现在可以设置线条了 */
.navbar .nav ul .underline {
  /* 绝对定位 */
  position: absolute;
  bottom: 0;
  left: 0;
  width: 240px;
  height: 6px;
  /* 设置一下盒子的左上角和右上角的圆角 */
  border-top-left-radius: 6px;
  border-top-right-radius: 6px;
  background-color: #cc3333;
  /* 加过渡 */
  transition: all 0.5s;
  /* 现在有个小问题 我们一起看看 ne
  /* 就是这个问题 这个线条有点影响鼠标移入li这个效果 */
  /* 这个属性就可以解决这个问题 就貌似这个元素你能看见 但是鼠标是点击不到了 现在鼠标放到线条也没事了 */
  pointer-events: none;
}
.navbar .nav ul .underline::before {
  content: "";
  /* 利用::before伪元素设置三角 */
  position: absolute;
  top: -10px;
  /* calc方法自动计算数值 让盒子居中 */
  left: calc(50% - 9px);
  width: 10px;
  height: 10px;
  /* inherit可以继承父元素的属性值 */
  background-color: inherit;
  /* 各位小伙伴可能对着属性比较陌生 我简单说一下这句话如何生成了三角形 这个属性可以绘制图案 polygon这个属性只需要提供最少三个点 就可以将三个点连接到一起 然后绘制一个图案  两个点的坐标位置(x,y) 0 100% 这个点就是说在x轴上为0px 在y轴是100% 就是盒子的高度 x轴的方向是向右 y轴的方向是向下 那么我们设置了三个点 然后将三个点连起来刚好绘制出一个三角形 不明白的小伙伴可以在纸上画一下 画出来就理解了 */
  clip-path: polygon(0 100%, 50% 0, 100% 100%);
}

</style>