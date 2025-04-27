<script setup>
import { ref, onMounted, watch } from 'vue';
import { Search } from '@element-plus/icons-vue';
import { queryPageApi, queryTagsApi } from '@/api/problem.js'
import { ElMessage } from 'element-plus'

const probelemList = ref({
    list: [],
    total: ''
});
const searchForm = ref({
    title: '',
    difficulty: '',
    tags: [],
    page: '1',
    pageSize: '10'
});

const tags = ref([]);

onMounted(() => {
    getAllTags();
    search();
})

// 查询
const search = async () => {
    const res = await queryPageApi(searchForm.value.title, searchForm.value.difficulty, searchForm.value.tags, searchForm.value.page, searchForm.value.pageSize);
    if (res.code) {
        probelemList.value.list = res.data.rows;
        probelemList.value.total = res.data.total;
    }
}

// 获取所有标签
const getAllTags = async () => {
    const res = await queryTagsApi();
    console.log(res);

    if (res.code) {
        tags.value = res.data;
    }
}

// 点击难度下拉菜单时触发的函数
const handleDifficultyCommand = (command) => {
    searchForm.value.difficulty = command;
    search();
}

// 标签点击触发函数
const handleTagChange = (tagId, isChecked) => {
    if (isChecked) {
        searchForm.value.tags.push(tagId);
    } else {
        const index = searchForm.value.tags.indexOf(tagId);
        if (index !== -1) {
            searchForm.value.tags.splice(index, 1);
        }
    }
    search();
}

// 监听page和pageSize的变化
watch([() => searchForm.value.page, () => searchForm.value.pageSize], () => {
    search();
});
</script>

<template>
    <div id="container">
        <p style="font-size: 20px; margin:0">题目列表</p>
        <div class="type myMargin">
            <!-- 难度 -->
            <el-dropdown class="difficulty" hide-on-click="true" @command="handleDifficultyCommand">
                <el-button type="info" plain>
                    难度<el-icon><arrow-down /></el-icon>
                </el-button>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item style="color:green" command="1">简单</el-dropdown-item>
                        <el-dropdown-item style="color:orange" command="2">中等</el-dropdown-item>
                        <el-dropdown-item style="color:red" command="3">困难</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
            <!-- 搜索框 -->
            <el-input v-model="searchForm.title" style="width: 240px" placeholder="请输入题目" :suffix-icon="Search" />
        </div>
        <!-- 标签 -->
        <div class="myMargin">
            <p style="font-size: 20px; margin:0">标签</p>
            <el-check-tag class="check-tag" v-for="tag in tags" :key="tag.id"
                :checked="searchForm.tags.includes(tag.id)" type="primary"
                @change="(isChecked) => handleTagChange(tag.id, isChecked)">
                {{ tag.name }}
            </el-check-tag>
        </div>
        <!-- 题库及数据展示 -->
        <div class="content myMargin">
            <el-row :gutter="20"> <!-- 每个el-col之间的距离 -->
                <!-- 题库展示 -->
                <el-col :span="16">
                    <el-table :data="probelemList.list" size="large" height="600" stripe style="width: 100%">
                        <el-table-column align="center" prop="id" label="题目ID" width="180" />
                        <el-table-column prop="title" label="题目名称" width="180" />
                        <el-table-column prop="difficulty" label="难度" />
                        <el-table-column prop="passRate" label="通过率" />
                        <el-table-column prop="status" label="完成状态" />
                    </el-table>
                </el-col>
                <!-- 数据展示 -->
                <el-col :span="8">

                </el-col>
            </el-row>
            <el-pagination background v-model:current-page="searchForm.page" v-model:page-size="searchForm.pageSize"
                :page-sizes="[10, 15, 30, 50, 100]" layout="total, sizes, prev, pager, next, jumper"
                :total="probelemList.total" />
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

.check-tag {
    margin-right: 10px;
}
</style>