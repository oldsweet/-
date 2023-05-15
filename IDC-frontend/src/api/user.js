import axios from "./config";

/**
 * 登录
 * @param email
 * @param password
 * @returns {Promise<*|axios.AxiosResponse<any>>}
 */
async function login(email, password) {
  let params = new URLSearchParams()
  params.set("email", email)
  params.set("password", password)
  let res = await axios.post("/user/login", params)
  res = res.data
  return res;
}

/**
 * 获取用户的信息
 * @returns {Promise<*|axios.AxiosResponse<any>>}
 */
async function userInfo() {
  let params = new URLSearchParams()
  params.set("token",localStorage.getItem("token"))
  let res = await axios.post("/user/user_info", params)
  res = res.data
  return res;
}

/**
 * 请求验证码
 * @param email
 * @returns {Promise<*|axios.AxiosResponse<any>>}
 */
async function register(email) {
  let params = new URLSearchParams()
  params.set("email",email)
  params.set("token",localStorage.getItem("token"))
  let res = await axios.post("/user/register", params)
  res = res.data
  return res;
}

/**
 * 验证注册
 * @param email
 * @param password
 * @param code
 * @returns {Promise<*|axios.AxiosResponse<any>>}
 */
async function verifyRegister(email,password,code) {
  let params = new URLSearchParams()
  params.set("email",email)
  params.set("password",password)
  params.set("code",code)
  params.set("token",localStorage.getItem("token"))
  let res = await axios.post("/user/verify_register", params)
  res = res.data
  return res;
}

export {login,userInfo,register,verifyRegister}
