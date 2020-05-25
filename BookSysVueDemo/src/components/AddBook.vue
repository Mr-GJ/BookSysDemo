<template>

  <el-form style="width: 50%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
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
    name:"AddBook",
    methods: {
      test(){
        console.log(this.ruleForm)
      },
      submitForm(formName) {
        const that = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
            that.$http.post('http://localhost:8181/book/save',that.ruleForm).then(function (res) {
              console.log(res)
              if (res.data == "success"){
                that.$alert('《'+ that.ruleForm.bname +'》添加成功！', '消息', {
                  confirmButtonText: '确定',
                  callback: action => {
                    that.$router.push("/BookManage")
                  }
                } )
              }else{
                alert('添加失败！');
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
    }
  }
</script>
