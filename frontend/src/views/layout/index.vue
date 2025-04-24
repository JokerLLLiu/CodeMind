<script setup>
import { ref } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const activeIndex = ref(route.path);
</script>

<template>
    <div class="common-layout">
        <el-container>
            <el-header>
                <el-menu :default-active="activeIndex" mode="horizontal" @select="handleSelect" router="true">
                    <el-menu-item index="/index">首页</el-menu-item>
                    <el-menu-item index="/problems">题库</el-menu-item>
                </el-menu> </el-header>
            <el-main><router-view></router-view></el-main>
        </el-container>
    </div>

</template>

<style scoped>
.common-layout {
    height: 100%;
    margin: 0 10%;
}
</style>