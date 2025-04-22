<script setup>
import { ref } from 'vue'

const user = ref({
    email: '',
    password: ''
})

const onSubmit = () => {
    console.log('submit!')
}
</script>

<template>
    <div id="container">
        <div class="login-form">
            <p class="title">
                智慧在线编程系统
            </p>
            <el-form :model="formInline">
                <el-form-item label="账号：">
                    <el-input v-model="user.email" placeholder="请输入邮箱" clearable />
                </el-form-item>
                <el-form-item label="密码：">
                    <el-input v-model="user.password" placeholder="请输入密码" clearable />
                </el-form-item>
                <el-form-item>
                    <el-button class="button" type="primary" @click="onSubmit">登录</el-button>
                </el-form-item>
            </el-form>
            <span class="link"><a href="javascript:void(0);">创建账号</a></span>
            <span class="link"><a href="javascript:void(0);">忘记密码</a></span>
        </div>
    </div>
</template>

<style scoped>
#container {
    padding: 10%;
    height: 410px;
    background-color: #F7F8FA;
}

.login-form {
    margin: 0 auto;
    background-color: #FFFFFF;
    padding: 30px;
    max-width: 400px;
    border: 1px solid #e0e0e0;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

.title {
    font-size: 30px;
    font-family: '楷体';
    text-align: center;
    margin-bottom: 30px;
    font-weight: bold;
}

.button {
    margin: 0 auto;
    width: 80%;
}

.create {
    margin-left: 10%;
}

.forget {
    margin-right: 10%;
}

.link {
    display: inline-block;
    width: 50%;
    text-align: center;
}

.link a {
    text-decoration: none;
    color: #007bff;
}
</style>