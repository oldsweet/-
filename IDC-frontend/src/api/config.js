import axios from "axios";

axios.defaults.baseURL = "http://localhost:9091"
axios.defaults.timeout = 10000
// 添加响应拦截器
axios.interceptors.response.use(function (response) {

  if (response.data.msg.startsWith('Token无效：'))
  {
    location.href = "/login"
    alert("你的登录凭证无效,请重新登录");
  }
  return response;
}, function (error) {
  return Promise.reject(error);
});

export  default  axios
