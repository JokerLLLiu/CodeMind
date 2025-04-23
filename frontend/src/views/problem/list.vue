<script setup>
import { ref } from 'vue';
import { Search } from '@element-plus/icons-vue';

const searchInfo = ref('');

const probelemList = ref([
    { proId: 1, proName: '两数之和', proDifficulty: '简单', proPassRate: '80%', proStatus: '已完成' },
    { proId: 2, proName: '回文数', proDifficulty: '中等', proPassRate: '70%', proStatus: '未完成' },
    { proId: 3, proName: '罗马数字转整数', proDifficulty: '困难', proPassRate: '60%', proStatus: '未完成' },
    { proId: 4, proName: '整数反转', proDifficulty: '简单', proPassRate: '50%', proStatus: '未完成' },
    { proId: 5, proName: '最长公共前缀', proDifficulty: '中等', proPassRate: '40%', proStatus: '未完成' },
    { proId: 5, proName: '最长公共前缀', proDifficulty: '中等', proPassRate: '40%', proStatus: '未完成' },
    { proId: 5, proName: '最长公共前缀', proDifficulty: '中等', proPassRate: '40%', proStatus: '未完成' },
    { proId: 5, proName: '最长公共前缀', proDifficulty: '中等', proPassRate: '40%', proStatus: '未完成' },
    { proId: 5, proName: '最长公共前缀', proDifficulty: '中等', proPassRate: '40%', proStatus: '未完成' },
    { proId: 5, proName: '最长公共前缀', proDifficulty: '中等', proPassRate: '40%', proStatus: '未完成' },
    { proId: 5, proName: '最长公共前缀', proDifficulty: '中等', proPassRate: '40%', proStatus: '未完成' },
    { proId: 5, proName: '最长公共前缀', proDifficulty: '中等', proPassRate: '40%', proStatus: '未完成' },
    { proId: 5, proName: '最长公共前缀', proDifficulty: '中等', proPassRate: '40%', proStatus: '未完成' },
    { proId: 5, proName: '最长公共前缀', proDifficulty: '中等', proPassRate: '40%', proStatus: '未完成' },
    { proId: 5, proName: '最长公共前缀', proDifficulty: '中等', proPassRate: '40%', proStatus: '未完成' },
    { proId: 5, proName: '最长公共前缀', proDifficulty: '中等', proPassRate: '40%', proStatus: '未完成' },
    { proId: 5, proName: '最长公共前缀', proDifficulty: '中等', proPassRate: '40%', proStatus: '未完成' },
    { proId: 5, proName: '最长公共前缀', proDifficulty: '中等', proPassRate: '40%', proStatus: '未完成' },
    { proId: 5, proName: '最长公共前缀', proDifficulty: '中等', proPassRate: '40%', proStatus: '未完成' },
]);
</script>

<template>
    <div id="container">
        <p style="font-size: 20px; margin:0">题目列表</p>
        <div class="type myMargin">
            <!-- 难度 -->
            <el-dropdown class="difficulty">
                <el-button type="info" plain>
                    难度<el-icon><arrow-down /></el-icon>
                </el-button>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item style="color:green">简单</el-dropdown-item>
                        <el-dropdown-item style="color:orange">中等</el-dropdown-item>
                        <el-dropdown-item style="color:red">困难</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
            <!-- 标签 -->
            <el-dropdown>
                <el-button type="info" plain>
                    标签<el-icon><arrow-down /></el-icon>
                </el-button>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item>Action 1</el-dropdown-item>
                        <el-dropdown-item>Action 2</el-dropdown-item>
                        <el-dropdown-item>Action 3</el-dropdown-item>
                        <el-dropdown-item>Action 4</el-dropdown-item>
                        <el-dropdown-item>Action 5</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
            <!-- 搜索框 -->
            <el-input v-model="searchInfo" style="width: 240px" placeholder="Please Input" :suffix-icon="Search" />
        </div>
        <!-- 题库及数据展示 -->
        <div class="content myMargin">
            <el-row :gutter="20"> <!-- 每个el-col之间的距离 -->
                <el-col :span="16">
                    <el-table :data="probelemList" size="large" height="700" stripe style="width: 100%">
                        <el-table-column align="center" prop="proId" label="题目ID" width="180" />
                        <el-table-column prop="proName" label="题目名称" width="180" />
                        <el-table-column prop="proDifficulty" label="难度" />
                        <el-table-column prop="proPassRate" label="通过率" />
                        <el-table-column prop="proStatus" label="完成状态" />
                    </el-table>
                    <el-affix :offset="120"> <!-- 距离顶部120px -->

                    </el-affix>
                </el-col>
                <el-col :span="8">

                </el-col>
            </el-row>
        </div>

    </div>
</template>

<style scoped>
#container {
    margin: 0;
}

.myMargin {
    margin-top: 20px;
}

.type .el-dropdown {
    margin-right: 10px;
}
</style>