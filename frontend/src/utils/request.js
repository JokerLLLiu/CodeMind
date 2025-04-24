import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '@/router';

//创建axios实例对象
const request = axios.create({
  baseURL: '/api',
  timeout: 600000
});

//axios的响应 response 拦截器
request.interceptors.response.use(
  (response) => { //成功回调
    return response.data
  },
  (error) => { //失败回调                                \\  0\



















































    if (error.response.status === 401) {
      // 提示信息
      ElMessage.error("登录超时，请重新登录");
      // 跳转到登录页面
      router.push('/login');
    }else{
      ElMessage.error("接口访问异常");
    }
    return Promise.reject(error)
  }
)

export default request;