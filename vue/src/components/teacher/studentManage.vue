<template>
  <div id="si" >
        <h1 style="font-size: 50px">学生信息界面:</h1>
        <el-divider/>
        <div class="search">
            <el-input type="text" v-model="studentid" placeholder="请输入学号查询" @change="searchStudent()" @key.enter="searchNum()" ></el-input>
        </div>

        <div class="class">
            <el-select v-model="this.chooseclass" @change="findStudents(this.chooseclass)" placeholder="请选择班级">
            <el-option
            v-for="(item,index) in this.manageclass"
            :key="index"
            :label="item"
            :value="item">
            </el-option>
            </el-select>
        </div>

        <div class="excel">
          <el-upload action=""
                     :on-change="handleChange"
                     :on-remove="handleRemove"
                     multiple
                     accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel"
                     :auto-upload="false"
          >
            <el-button type="primary">上传学生信息</el-button>
          </el-upload>
        </div>

        <div class="table">
            <el-table 
            :data="this.studentInformation"
            border 
            stripe
            style="width: 100%;color: black;">
              <el-table-column
                  prop="beclass"
                  label="班级">
              </el-table-column>
              <el-table-column
                  prop="studentid"
                  label="学号">
              </el-table-column>
              <el-table-column
                  prop="studentname"
                  label="姓名">
              </el-table-column>
              <el-table-column
                  prop="profession"
                  label="专业">
              </el-table-column>

              <el-table-column
                  label="操作">
                <template #default="scope">
                  <a style="cursor:pointer;color: #0090f6" @click="this.resetpassword(scope.row.studentid)">重置密码</a>
                </template>
              </el-table-column>

            </el-table>
        </div>
            

  </div>

</template>


<script>
import request from "@/pojo/request";

export default {
    name:"123",
    data (){
        return{
            studentid:null,
            chooseclass:null,
            manageclass:sessionStorage.getItem('manageclass').split(","),
            myInformation:this.studentInformation,
            studentInformation:[],
            uploadstudents:[],
            uploadstatus:null,
        }
        },
  methods: {
    resetpassword(studentid) {
      request.put("/api/student/updatepassword", JSON.stringify({"password":"123456","studentid":studentid})).then(res=>{
        if (res.code == 200) {
          this.$message({
            type: "success",
            message: "密码重置为123456",
          })
        } else {
          this.$message({
            type: "error",
            message: "未知错误，请重试！",
          })
        }
      })

    },
    searchStudent() {
      if (this.studentid.length < 6 || this.manageclass.indexOf(this.studentid.substring(0, 6)) == -1) {
        this.studentInformation = null;
        return;
      }
      request.get("/api/student/search", {params: {"studentid": this.studentid}}).then(res => {
        if (res.code == 401) {
          this.$router.push("/");
          this.$message({
            type: "error",
            message: "登陆过期！",
          })
          return;
        }
        this.studentInformation = res.data;
      })
    },
    findStudents(beclass) {
      request.get("/api/student/findstudents", {params: {"beclass": beclass}}).then(res => {
        if (res.code == 401) {
          this.$router.push("/");
          this.$message({
            type: "error",
            message: "登陆过期！",
          })
          return;
        }
        this.studentInformation = res.data;
      })
    },
    handleChange(file, fileList) {
      this.fileTemp = file.raw
      let fileName = file.raw.name
      let fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
      // 判断上传文件格式
      if (this.fileTemp) {
        if ((fileType == 'xlsx') || (fileType == 'xls')) {
          this.importf(this.fileTemp);
        } else {
          this.$message({
            type: 'warning',
            message: '附件格式错误，请删除后重新上传！'
          })
        }
      } else {
        this.$message({
          type: 'warning',
          message: '请上传附件！'
        })
      }
    },
    handleRemove(file, fileList) {
      this.fileTemp = null
    },
    importf(obj) {
      this.uploadstudents = [];
      this.dialogVisible = true;
      let _this = this;
      let inputDOM = this.$refs.inputer;   // 通过DOM取文件数据
      this.file = event.currentTarget.files[0];
      var rABS = false; //是否将文件读取为二进制字符串
      var f = this.file;
      var reader = new FileReader();
      //if (!FileReader.prototype.readAsBinaryString) {
      FileReader.prototype.readAsBinaryString = function (f) {
        var binary = "";
        var rABS = false; //是否将文件读取为二进制字符串
        var pt = this;
        var wb; //读取完成的数据
        var outdata;
        var reader = new FileReader();
        reader.onload = function (e) {
          var bytes = new Uint8Array(reader.result);
          var length = bytes.byteLength;
          for (var i = 0; i < length; i++) {
            binary += String.fromCharCode(bytes[i]);
          }
          var XLSX = require('xlsx');
          if (rABS) {
            wb = XLSX.read(btoa(fixdata(binary)), { //手动转化
              type: 'base64'
            });
          } else {
            wb = XLSX.read(binary, {
              type: 'binary'
            });
          }
          // outdata就是你想要的东西 excel导入的数据
          outdata = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]);
          // excel 数据再处理
          outdata.map(v => {
            let jsonString = JSON.stringify(v).replace(/\//g, '').replace(/\s/ig, '');
            v = JSON.parse(jsonString);
            let obj = {}
            obj.studentid = v.学号
            obj.beclass = v.班级
            obj.studentname = v.姓名 + ""
            obj.profession = v.专业 + ""
            obj.account = v.账号
            obj.password = v.密码 + ""
            _this.uploadstudents.push(obj)
          })
          request.post("/api/student/addstudents", JSON.stringify(_this.uploadstudents)).then(res => {
            if (res.code != 200) {
              _this.$message({
                type: 'error',
                message: '重复的学号！！！'
              })
            } else {
              _this.$message({
                type: 'success',
                message: '上传成功！'
              })
            }
          })

        }
        reader.readAsArrayBuffer(f);
      }
      if (rABS) {
        reader.readAsArrayBuffer(f);
      } else {
        reader.readAsBinaryString(f);
      }
    },
  }
}

</script>

<style scoped>
    
*{

    margin: 0;
    padding: 0;
}

 h1 {
    padding-top: 3vh;
    padding-left: 5%;
    font-size: xx-large;
}
.search {
    position: absolute;
    top: 175px;
    left: 425px;
}

.class {
    position: absolute;
    top: 175px;
    left: 175px;
}

.excel {
    position: absolute;
    top: 175px;
    left: 1000px;
}
.table {
    text-align: center;
    width:80%;
    position: absolute;
    top: 250px;
    left: 150px;
}

#si{
        position: relative;
    }
</style>