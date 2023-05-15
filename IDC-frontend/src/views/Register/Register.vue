<template>
  <div class="register-container">

    <Logo></Logo>
    <el-form ref="registerForm" :model="registerForm" :rules="registerRules" label-width="80px">
      <el-form-item label="邮件地址" prop="email">
        <el-input v-model="registerForm.email"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="registerForm.password" type="password"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input v-model="registerForm.confirmPassword" type="password"></el-input>
      </el-form-item>
      <el-form-item label="验证码">
        <el-input v-model="registerForm.code">
          <el-button
            v-if="time === 0" slot="append"
            class="ask-code-button"
            size="small"
            :loading="submitting"
            @click="regsiter"
            type="info" style="border: none;;margin: 0;padding: 0"
          >请求验证码
          </el-button>
          <el-button
            plain
            disabled
            v-else slot="append"
            class="ask-code-button"
            size="small"
            type="info" style="border: none;margin: 0;padding: 0"
          >{{ time }}
          </el-button>

        </el-input>


      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="verifyRegister" :loading="verifying">注册</el-button>
      </el-form-item>
      <router-link to="/login" style="color: #66B1FF;">已有账号,登录</router-link>
    </el-form>
  </div>
</template>

<script>
import Logo from "../Logo";
import {register, verifyRegister} from "../../api/user";

export default {
  name: 'Register',
  components: {Logo},
  data() {
    return {
      buttonContent: "请求验证码",
      registerForm: {
        email: '',
        password: '',
        confirmPassword: '',
        code: "",

      },
      time: 0,
      registerRules: {
        email: [
          {required: true, message: '请输入邮件地址', trigger: 'blur'},
          {type: 'email', message: '请输入正确的邮件地址', trigger: ['blur', 'change']}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 20, message: '密码长度在6-20位之间', trigger: 'blur'}
        ],
        confirmPassword: [
          {required: true, message: '请再次输入密码', trigger: 'blur'},
          {validator: this.validateConfirmPassword, trigger: 'blur'}
        ]
      },
      submitting: false,
      verifying: false,
    };
  },
  methods: {
    regsiter() {

      this.$refs.registerForm.validate(valid => {
        if (valid) {
          this.submitting = true;
          setTimeout(async () => {
            let res = await register(this.registerForm.email);
            if (res.code === 200) {
              this.time = 60
            }
            setInterval(() => {
              this.time -= 1
              if (this.time <= 0) {
                this.time = 0
              }
            }, 1000)
            this.submitting = false;
            if (res.code ===200){
              this.$message.success('验证码发送成功，请注意查收');
            }
            else {
              this.$message.error(res.msg)
            }
          }, 1000);
        }
      });
    },

    verifyRegister() {
      if (this.registerForm.code === ""){
        this.$message.error("验证码不能为空")
        return
      }
      this.verifying = true;
      this.$refs.registerForm.validate(async valid => {
        if (valid) {
          setTimeout(async () => {
            let res = await verifyRegister(this.registerForm.email, this.registerForm.password, this.registerForm.code)
            if (res.code === 200) {
              this.verifying = false
              this.$message.success("注册成功");
              this.$router.replace("/login")
            }
            else {
              this.$message.error(res.msg)
            }
            this.verifying = false
          },1000)
        }
      });
    },
    validateConfirmPassword(rule, value, callback) {
      if (value !== this.registerForm.password) {
        callback(new Error('两次输入的密码不一致'));
      } else {
        callback();
      }
    }
  }
};
</script>

<style scoped>
.register-container {
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

.ask-code-button:hover {
  background-color: transparent;
}
</style>
