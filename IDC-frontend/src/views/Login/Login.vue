<template>
  <div class="login-container">
    <Logo></Logo>

    <el-form ref="loginForm" :model="loginForm" :rules="loginRules">
      <el-form-item  prop="email">
        <el-input v-model="loginForm.email" placeholder="邮箱"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="loginForm.password" type="password" placeholder="密码"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="handleSubmit" :loading="submitting">登陆</el-button>
      </el-form-item>
      <router-link to="/register" style="color: #66B1FF">没有账号,注册</router-link>
    </el-form>
  </div>
</template>

<script>
import {login,userInfo} from "../../api/user";
import Logo from "../Logo";
export default {
  name: 'Login',
  components: {Logo},
  data() {
    return {
      loginForm: {
        email: '',
        password: '',

      },
      loginRules: {
        email: [
          { required: true, message: '请输入邮件地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮件地址', trigger: ['blur', 'change'] }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '密码长度在6-20位之间', trigger: 'blur' }
        ]
      },
      submitting: false
    };
  },
  methods: {
    handleSubmit() {
      this.$refs.loginForm.validate(async valid => {
        if (valid) {
          // 表单验证通过，可以提交登陆请求
          console.log('loginForm: ', this.loginForm);
          this.submitting = true;
          let res = await login(this.loginForm.email, this.loginForm.password)
          if (res.code === 200)
          {
            this.submitting = false;
            localStorage.setItem("token",res.token)
            this.$message.success("登陆成功")
            await this.$router.push({
              path: "/",
            })
            let userRes = await userInfo()
            console.log(userRes)
          }
        }
      });
    }
  }
};
</script>
<style scoped>

.login-container {
  margin: 50px auto;
  width: 400px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #fff;
  box-shadow: 0px 0px 10px #ccc;
}

.el-form-item__label {
  font-size: 14px;
  color: #333;
}

.el-form-item__content {
  margin-left: 0;
}

.el-button {
  width: 100%;
  height: 40px;
  margin-top: 20px;
  background-color: #409eff;
  border: none;
  border-radius: 4px;
  color: #fff;
  font-size: 16px;
}

.el-button:hover {
  background-color: #66b1ff;
}

</style>
