<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'

// 获取当前路由实例
const route = useRoute()
// 定义一个响应式变量来存储 id
const problemId = ref(null)

onMounted(() => {
    // 从路由参数中获取 id
    problemId.value = route.params.id
    console.log('获取到的题目 ID 是:', problemId.value)
})
</script>

<template>
    <div>
        <!-- 可以在这里使用 problemId -->
        <p>当前题目 ID 是: {{ problemId }}</p>
    </div>
</template>

<style scoped></style>