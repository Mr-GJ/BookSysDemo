<template>

  <el-form style="width: 50%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="编号">
      <el-input v-model="ruleForm.id" readonly></el-input>
    </el-form-item>
    <el-form-item label="书名" prop="bname">
      <el-input v-model="ruleForm.bname"></el-input>
    </el-form-item>
    <el-form-item label="作者" prop="author">
      <el-input v-model="ruleForm.author"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
  export default {
    name: "updateBook",
    methods: {
      submitForm(formName) {
        const that = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
            that.$http.put('http://localhost:8181/book/update',that.ruleForm).then(function (res) {
              console.log(res)
              if (res.data == "success"){
                that.$alert('《'+ that.ruleForm.bname +'》修改成功！', '消息', {
                  confirmButtonText: '确定',
                  callback: action => {
                    that.$router.push("/BookManage")
                  }
                } )
              }else{
                alert('修改失败！');
              }
            });
          } else {
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    },
    data() {
      return {
        ruleForm: {
          id:'',
          bname: '',
          author: ''
        },
        rules: {
          bname: [
            { required: true, message: '请输入图书名称', trigger: 'blur' },
          ],
          author: [
            { required: true, message: '请选择作者', trigger: 'change' }
          ]
        }
      };
    },
    created(){
      const that = this
      this.ruleForm.id = this.$route.query.id
      that.$http.get("http://localhost:8181/book/findById/"+this.ruleForm.id).then(function (res) {
        that.ruleForm.bname = res.data.bname
        that.ruleForm.author = res.data.author
      })
    }
  }
</script>

