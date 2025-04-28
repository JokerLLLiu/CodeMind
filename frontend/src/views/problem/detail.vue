<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { queryProblemByIdApi } from '@/api/problem.js';
import { ElMessage } from 'element-plus';
import router from '@/router';

const route = useRoute();

const problemInfo = ref({
    id: '', // 题目ID
    title: '', // 题目名称
    description: '', // 题目描述
    inputExample: '', // 输入描述
    outputExample: '', // 输出描述
    timeLimit: '', // 时间限制
    memoryLimit: '', // 内存限制
    difficulty: '', // 题目难度
    passRate: '', // 通过率
    status: '', // 题目状态
    tags: [], // 题目标签
});

onMounted(() => {
    getProblemById(route.params.id);
});

// 根据ID查询题目信息
const getProblemById = async (id) => {
    const res = await queryProblemByIdApi(id);
    if (res.code) {
        problemInfo.value = res.data;
    } else {
        ElMessage.error(res.msg)
    }
}

// 返回上一页
const goBack = () => {
    router.go(-1);
}


</script>

<template>
    <div>
        <el-page-header @back="goBack">
            <template #content>
                <span class="text-large font-600 mr-3"> {{ problemInfo.title }} </span>
            </template>
        </el-page-header>
    </div>
</template>

<style scoped></style>