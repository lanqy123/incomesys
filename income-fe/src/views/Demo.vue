<template>
  <div>
    <div class="my-list-toolbar">
      <el-col :span="10" >
        <el-button  type="primary" @click="" >新建</el-button>
      </el-col>
      <el-col :span="14" style="text-align: right;">
        <el-checkbox v-model="checked" @change="">仅显示有效</el-checkbox>
        <el-input v-model="name" placeholder="请输入内容" style="width: 200px;">
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>
        <el-button  type="primary"  @click="" >查询</el-button>
      </el-col>
    </div>
    <!-- 编辑页面toolbar -->
    <div class="my-list-toolbar">
      <el-button type="primary" @click="test1">测试1</el-button>
      <el-button type="primary" @click="test2">测试2</el-button>
      <el-button type="primary" @click="test3">测试utils</el-button>
      <el-button type="primary" @click="test5">测试跳转</el-button>
    </div>
    <!-- 列表里面的操作按钮 -->
    <el-row>
      <el-button
        type="text"
        @click="">删除</el-button>
      <el-button
        type="text"
        @click="">编辑</el-button>
      <el-button
        type="text"
        @click="">管理模板</el-button>
    </el-row>
    <el-row>
      <el-button
        type="text"
        @click="" :disabled="true">删除</el-button>
      <el-button
        type="text"
        @click="">编辑</el-button>
      <el-button
        type="text"
        @click="">管理模板</el-button>
    </el-row>
    <el-row>
      <el-button
        type="text"
        @click="">恢复</el-button>
      <el-button
        type="text"
        @click="">编辑</el-button>
      <el-button
        type="text"
        @click="">管理模板</el-button>
    </el-row>
    <!-- 图标样式 -->
    <el-row>
      <i class="el-icon-circle-plus my-icon-btn-green"></i>
      <i class="el-icon-remove my-icon-btn-red"></i>
      <i class="el-icon-info my-icon-info"></i>
      <i class="el-icon-info my-icon-info my-color-disabled"></i>
      <i class="el-icon-search my-icon-btn-green my-font-bold"></i>
      <i class="el-icon-refresh my-icon-btn-green my-font-bold"></i>
    </el-row>
    <el-row>
      <i class="my-icon-text"></i>
      <i class="my-icon-arr"></i>
      <i class="my-icon-structure"></i>
    </el-row>
    <!-- 下面消息提示模板 -->
    <div class="my-list-toolbar">
    <template>
      <el-button :plain="true" @click="open1">消息</el-button>
      <el-button :plain="true" @click="open2">成功</el-button>
      <el-button :plain="true" @click="open3">警告</el-button>
      <el-button :plain="true" @click="open4">错误</el-button>
    </template>
    </div>
    <!-- 下面是表格模板 -->
    <el-table ref="multipleTable" :data="tableData" border style="height:100%;">
      <el-table-column prop="structureId" v-if="false" label="结构化id" ></el-table-column>
      <el-table-column prop="structureName" label="结构名称" width="180"></el-table-column>
      <el-table-column prop="description" label="描述" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="status" label="状态" width="80"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="100"></el-table-column>
      <el-table-column prop="modifyTime" label="修改时间" width="200"></el-table-column>
      <el-table-column prop="operation" label="操作" width="180">
        <template slot-scope="scope">
          <el-button @click="" type="text" size="small">编辑</el-button>
          <el-button type="text" @click="" size="small">删除</el-button>
          <el-button type="text" @click="" size="small">模板管理</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 下面不是模板，功能测试 -->
    <el-row>
      <el-col :span="3">
        请输入名称：
      </el-col>
      <el-col :span="6">
        <el-input v-model="name"></el-input>
      </el-col>
      <el-col :span="6">
        <el-button type="primary" @click="test4">测试jpa</el-button>
      </el-col>
    </el-row>
    {{users}}
  </div>
</template>

<script>
  import commonUtils from '../common/commonUtils'

  export default {
    data() {
      return {
        tableData: [
          {
            structureId:"1",
            structureName:"你好",
            description:"1234567891234567894564566111111111111111111111111111111111111111111111111111111111111",
            status:"有效",
            createTime:"2019-03-28",
            modifyTime:"2019-03-28 13:25:36"
          }
        ],
        activeNames: ['1'],
        title: '标题',
        name:'',
        users:[],
        checked:false
      }
    },
    activated(){
      commonUtils.Log("页面激活");
    },
    mounted(){
      commonUtils.Log("页面进来");
    },
    methods: {
      test1() {
        var info = {name: '张三', age: 24, id: '001'};
        localStorage.setItem("info", JSON.stringify(info));

      },
      test2() {
        var infoStr = localStorage.getItem('info');
        console.log(infoStr);
        localStorage.removeItem('info');
      },
      test3() {
        commonUtils.Log("就是测试打印而已");
      },
      async test4() {
        const self = this;
        const re = await self.$http.post('demo/getUsers.do_', self.$qs.stringify({
          name: self.name
        }));
        commonUtils.Log("打印1"+JSON.stringify(re));
      },
      test5(){
        // const routeData = this.$router.resolve({
        //   name: 'DemoList',
        //   params:{
        //     userId:123
        //   }
        // });
        const self = this;
        // window.open(routeData.href, '_blank');
        const routeData = this.$router.resolve({
          path: '/demoList',
          query:{
            userId:self.name,
            userInfo:{
              userName:'张三'
            }
          }
        });
        window.open(routeData.href, '_blank');
        // this.$router.push({
        //   name:'DemoList',
        //   params:{
        //     userId:123
        //   }
        // });
      },
      reload(){
        commonUtils.Log("没错，你调用的就是reload方法");
      },
      open1() {
        this.$message({
          showClose: true,
          message: '这是一条消息提示',
          type: 'info'
        });
      },

      open2() {
        this.$message({
          showClose: true,
          message: '恭喜你，这是一条成功消息',
          type: 'success'
        });
      },

      open3() {
        this.$message({
          showClose: true,
          message: '警告哦，这是一条警告消息',
          type: 'warning'
        });
      },

      open4() {
        this.$message({
          showClose: true,
          message: '错了哦，这是一条错误消息',
          type: 'error'
        });
      }
    }
  }
</script>

<style scoped>


</style>
