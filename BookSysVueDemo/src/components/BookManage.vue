<template>
  <div>
    <el-table
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        fixed
        prop="id"
        label="编号"
        width="150">
      </el-table-column>
      <el-table-column
        prop="bname"
        label="书名"
        width="300">
      </el-table-column>
      <el-table-column
        prop="author"
        label="作者"
        width="120">
      </el-table-column>
      <el-table-column
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">修改</el-button>
          <el-button type="text" size="small" @click="deleteBook(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="prev, pager, next"
      page-size = "5"
      :total="total"
      @current-change="page">
    </el-pagination>
  </div>

</template>

<script>
    export default {
        name: "BookManage",
      methods: {
        deleteBook(row){
          const that = this
          this.$http.delete('http://localhost:8181/book/deleteBook/'+row.id).then(function (res) {
            if (res.data == "success"){
              that.$alert('删除成功！', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  window.location.reload()
                }
              } )
            } else{
              that.$alert('删除失败！', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  that.$router.push('/BookManage')
                }
              } )
            }
          })
        },
        handleClick(row) {
          console.log(row);
          this.$router.push({path:'/updateBook',query:{id:row.id}})
        },
        page(currentPage){
          const _this = this
          this.$http.get('http://localhost:8181/book/findAll/'+currentPage+'/5').then(function (res) {
            // console.log(res)
            _this.tableData = res.data.content
            _this.total = res.data.totalElements
          });
        }
      },
      created(row){
          const _this = this
        this.$http.get('http://localhost:8181/book/findAll/1/5').then(function (res) {
          // console.log(res)
          _this.tableData = res.data.content
          _this.total = res.data.totalElements
    });
      },
      data() {
        return {
          total:null,
          tableData: [{
            id: '1',
            bname: '薛兆丰经济学讲义',
            author: '薛兆丰'
          },
            {
              id: '2',
              bname: '反脆弱:从不确定性中受益',
              author: '丹尼尔卡尼曼'
            }]
        }
      }
    }
</script>
