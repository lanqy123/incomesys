<template>
  <div style="margin-top: 20px">
    <h1 style="text-align: center ;color: #409EFF">
      简易多功能收款管理系统
    </h1>
    <hr class="h"></hr>
  <div style="width:30%;height:300px;float:left;margin-top: 30px;margin-left: 50px">
    <el-form ref="form" :model="form" label-width="100px">
      <el-form-item label="姓名">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="金额">
        <el-input v-model="form.money"></el-input>
      </el-form-item>
      <el-form-item label="备注">
        <el-input type="textarea" v-model="form.detail"></el-input>
      </el-form-item>
      <el-form-item style="text-align: center">
        <el-button type="primary" @click="onSubmit">确定</el-button>
        <el-button type="warning" @click="reset">重置</el-button>
      </el-form-item>
    </el-form>
  </div>

  <div style="width:50%;height:300px;float:left;margin-left: 200px;margin-top: 30px">
    <div>
      <el-row>
        <el-col>
          <el-input v-model="name" placeholder="请输入姓名" style="width: 300px;margin-right: 20px"></el-input>
          <el-button type="primary" @click="fetchData">搜索</el-button>
          <el-button type="warning" @click="name=''">重置</el-button>
          <el-button type="success" @click="">导出Excel</el-button>
        </el-col>
      </el-row>
    </div>

    <!--查询结果表格-->
    <el-table ref="multipleTable" :data="tableData"   @selection-change="handleSelectionChange" :summary-method="getSummaries" show-summary>
      <el-table-column prop="name" label="姓名"  width="200px"></el-table-column>
      <el-table-column prop="money" label="金额"  width="200px"></el-table-column>
      <el-table-column prop="detail" label="备注"></el-table-column>
      <el-table-column prop="desc" label="操作"  width="200px">
        <template slot-scope="scope">
          <el-button type="primary"  @click="" width="auto">编辑</el-button>
          <el-button type="danger"  @click="" width="auto">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--分栏-->
    <el-pagination background
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page="currentPage"
                   :page-sizes="[10, 20, 50, 100]"
                   :page-size="pageSize"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="total">
    </el-pagination>

  </div>
  </div>
</template>

<script>

  export default {
    data() {
      return {
        form: {
          name: '',
          money: '',
          detail: ''
        },
        name:'' ,
        list:[],
        tableData:[],
        total:0,
        currentPage:1,
        pageSize:10,
      }
    },
    created() {
      this.fetchData();
    },
    methods: {
      onSubmit() {
        var self = this;
        var param = {
          name:self.form.name,
          detail:self.form.detail,
          money:self.form.money,
        };
        self.$http.get('info/insert.do_', {
          params: param
        }).then((result) => {
          self.$message.success("添加成功");
        }).catch(function (error) {
          self.$message.error("获取数据错误");
        });
        self.reset();
        self.fetchData();
        self.reset();
        self.fetchData();
      },
      reset() {
        this.form.name='';
        this.form.detail='';
        this.form.money='';
      },
      fetchData(){
        var self = this;
        var param = {
          page: self.currentPage,
          limit: self.pageSize,
          name:self.name,
        };
        self.$http.get('info/querylist.do_', {
          params: param
        }).then((result) => {
          self.tableData = result.page.list;
          self.total = result.page.totalCount;
          self.list = result.list;
        }).catch(function (error) {
          self.$message.error("获取数据错误");
        });
        self.reset();
      },
      getSummaries(param){
        const { columns, data } = param;
        const sums = [];
        columns.forEach((column, index) => {
          if (index === 0) {
            sums[index] = '总计';
            return;
          }
          if(index === 1) {
            let values = 0;
            for (let i = 0; i < this.list.length ; i++) {
              values  = values + Number(this.list[i].money);
            }
            sums[index] = values;
            sums[index] += ' 元';
          }
        });

        return sums;
      },
      handleSizeChange(val) {
        this.pageSize = val;
        this.currentPage = 1;
        this.fetchData();
      },
      handleCurrentChange(val) {
        this.currentPage = val;
        this.fetchData();
      },
      handleSelectionChange(val) {
        this.selection = val;
      },
    },
    watch: {

    }
  }
</script>

<style>
  .h{
    width: 95%;
    float: left;
    border:1px solid #409EFF;
    margin-top: -5px;
    margin-bottom: 5px;
    margin-left: 50px;
  }
</style>
