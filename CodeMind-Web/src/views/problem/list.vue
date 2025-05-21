<script setup>
import { ref, onMounted, watch } from "vue";
import { Search } from "@element-plus/icons-vue";
import { queryPageApi, queryTagsApi } from "@/api/problem.js";
import { ElMessage } from "element-plus";
import * as echarts from "echarts/core";
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
} from "echarts/components";
import { PieChart } from "echarts/charts";
import { LabelLayout } from "echarts/features";
import { CanvasRenderer } from "echarts/renderers";
import router from "@/router";

const probelemList = ref({
  list: [],
  total: "",
});
const searchForm = ref({
  title: "",
  difficulty: "",
  tags: [],
  page: "1",
  pageSize: "10",
});

const tags = ref([]);

onMounted(() => {
  getAllTags();
  search();
  // 移除非 null 断言，使用条件判断来处理可能的 null 值
  const chartDom = document.getElementById("main");
  if (!chartDom) {
    console.error("未找到 ID 为 main 的元素");
  }
  const myChart = echarts.init(chartDom);
  myChart.setOption(option);
});

// 查询
const search = async () => {
  const res = await queryPageApi(
    searchForm.value.title,
    searchForm.value.difficulty,
    searchForm.value.tags,
    searchForm.value.page,
    searchForm.value.pageSize
  );
  if (res.code) {
    probelemList.value.list = res.data.rows;
    probelemList.value.total = res.data.total;
  }
};

// 获取所有标签
const getAllTags = async () => {
  const res = await queryTagsApi();

  if (res.code) {
    tags.value = res.data;
  }
};

// 点击难度下拉菜单时触发的函数
const handleDifficultyCommand = (command) => {
  searchForm.value.difficulty = command;
  search();
};

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
};

// 清空
const clear = () => {
  searchForm.value.title = "";
  searchForm.value.difficulty = "";
  searchForm.value.tags = [];
  search();
};

// 监听page和pageSize的变化
watch([() => searchForm.value.page, () => searchForm.value.pageSize], () => {
  search();
});

// 点击题目跳转
const goToProblemDetail = (id) => {
  console.log(id);

  router.push(`/problems/${id}`);
};

// 图表
echarts.use([
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  PieChart,
  CanvasRenderer,
  LabelLayout,
]);

const EChartsOption = echarts.ComposeOption;

const option = {
  title: {
    text: "个人完成情况",
    subtext: "Fake Data",
    left: "center",
  },
  tooltip: {
    trigger: "item",
  },
  legend: {
    orient: "vertical",
    left: "left",
  },
  series: [
    {
      name: "Access From",
      type: "pie",
      radius: "50%",
      data: [
        { value: 1048, name: "Search Engine" },
        { value: 735, name: "Direct" },
        { value: 580, name: "Email" },
        { value: 484, name: "Union Ads" },
        { value: 300, name: "Video Ads" },
      ],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: "rgba(0, 0, 0, 0.5)",
        },
      },
    },
  ],
};
</script>

<template>
  <div id="container">
    <p style="font-size: 20px; margin: 0">题目列表</p>
    <div class="type myMargin">
      <!-- 难度 -->
      <el-dropdown
        class="difficulty"
        hide-on-click="true"
        @command="handleDifficultyCommand"
      >
        <el-button type="info" plain>
          难度<el-icon><arrow-down /></el-icon>
        </el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item style="color: green" command="1"
              >简单</el-dropdown-item
            >
            <el-dropdown-item style="color: orange" command="2"
              >中等</el-dropdown-item
            >
            <el-dropdown-item style="color: red" command="3"
              >困难</el-dropdown-item
            >
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      <!-- 搜索框 -->
      <el-input
        v-model="searchForm.title"
        style="width: 240px"
        placeholder="请输入题目"
        clearable
      />
      <!-- 重置按钮 -->
      <el-button type="info" @click="clear()">清空</el-button>
      <!-- 查询按钮 -->
      <el-button type="primary" @click="search()">查询</el-button>
    </div>
    <!-- 标签 -->
    <div class="myMargin">
      <p style="font-size: 20px; margin: 0">标签</p>
      <el-check-tag
        class="check-tag"
        v-for="tag in tags"
        :key="tag.id"
        :checked="searchForm.tags.includes(tag.id)"
        type="primary"
        @change="(isChecked) => handleTagChange(tag.id, isChecked)"
      >
        {{ tag.name }}
      </el-check-tag>
    </div>
    <!-- 题库及数据展示 -->
    <div class="content myMargin">
      <el-row :gutter="20">
        <!-- 每个el-col之间的距离 -->
        <!-- 题库展示 -->
        <el-col :span="16">
          <el-table
            :data="probelemList.list"
            size="large"
            height="600"
            stripe
            style="width: 100%"
          >
            <el-table-column
              align="center"
              prop="id"
              label="题目ID"
              width="180"
            />
            <el-table-column label="题目名称" width="180">
              <template #default="scope">
                <span @click="goToProblemDetail(scope.row.id)">
                  <a href="javascript:void(0)">{{ scope.row.title }}</a>
                </span>
              </template>
            </el-table-column>
            <el-table-column label="难度">
              <template #default="scope">
                <el-tag
                  :type="
                    scope.row.difficulty === 1
                      ? 'success'
                      : scope.row.difficulty === 2
                      ? 'warning'
                      : 'danger'
                  "
                >
                  {{
                    scope.row.difficulty === 1
                      ? "简单"
                      : scope.row.difficulty === 2
                      ? "中等"
                      : "困难"
                  }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="passRate" label="通过率" />
            <el-table-column label="完成状态">
              <template #default="scope">
                <el-tag :type="scope.row.isPassed ? 'success' : 'info'">
                  {{ scope.row.isPassed ? "已完成" : "未完成" }}
                </el-tag>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
        <!-- 数据展示 -->
        <el-col :span="8">
          <div id="main" style="width: 100%; height: 400px"></div>
        </el-col>
      </el-row>
      <el-pagination
        background
        v-model:current-page="searchForm.page"
        v-model:page-size="searchForm.pageSize"
        :page-sizes="[10, 15, 30, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="probelemList.total"
      />
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

.type > * {
  margin-right: 10px;
}

.check-tag {
  margin-right: 10px;
}

/* 移除超链接默认样式 */
a {
  color: inherit;
  text-decoration: none;
}

/* 鼠标悬停时改变颜色 */
a:hover {
  color: #409eff;
  /* 可以根据需要修改颜色 */
}
</style>
