<script setup>
import { ref } from 'vue';

const activeIndex = ref('1');
</script>

<template>
    <div class="common-layout">
        <el-container>
            <el-header>
                <el-menu :default-active="activeIndex" mode="horizontal" @select="handleSelect" router="true">
                    <el-menu-item index="/problemList">Processing Center</el-menu-item>
                </el-menu>
            </el-header>
            <el-main><router-view></router-view></el-main>
        </el-container>
    </div>
</template>

<style scoped></style>