<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { queryProblemByIdApi } from "@/api/problem.js";
import { ElMessage } from "element-plus";
import router from "@/router";

const route = useRoute();

const problemInfo = ref({
  id: "", // 题目ID
  title: "", // 题目名称
  description: "", // 题目描述
  inputExample: "", // 输入描述
  outputExample: "", // 输出描述
  timeLimit: "", // 时间限制
  memoryLimit: "", // 内存限制
  difficulty: "", // 题目难度
  passRate: "", // 通过率
  status: "", // 题目状态
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
    ElMessage.error(res.msg);
  }
};

// 返回上一页
const goBack = () => {
  router.go(-1);
};
// 拖动相关逻辑
const isDragging = ref(false);
const startX = ref(0);
const startWidth = ref(0);

const handleMouseDown = (e) => {
  isDragging.value = true;
  startX.value = e.clientX;
  startWidth.value = document.querySelector(".left-pane").offsetWidth;
  document.body.style.cursor = "col-resize";
};

const handleMouseMove = (e) => {
  if (isDragging.value) {
    const deltaX = e.clientX - startX.value;
    const newWidth = startWidth.value + deltaX;
    document.querySelector(".left-pane").style.width = `${newWidth}px`;
  }
};

const handleMouseUp = () => {
  isDragging.value = false;
  document.body.style.cursor = "default";
};

onMounted(() => {
  document.addEventListener("mousemove", handleMouseMove);
  document.addEventListener("mouseup", handleMouseUp);
  return () => {
    document.removeEventListener("mousemove", handleMouseMove);
    document.removeEventListener("mouseup", handleMouseUp);
  };
});

const cssVarName = ref("--el-box-shadow-dark");
</script>

<template>
  <div>
    <el-page-header @back="goBack">
      <template #content>
        <span class="text-large font-600 mr-3"> {{ problemInfo.title }} </span>
        <span
          ><el-tag
            class="tag"
            :type="
              problemInfo.difficulty === 1
                ? 'success'
                : problemInfo.difficulty === 2
                ? 'warning'
                : 'danger'
            "
          >
            {{
              problemInfo.difficulty === 1
                ? "简单"
                : problemInfo.difficulty === 2
                ? "中等"
                : "困难"
            }}
          </el-tag></span
        >
        <span
          ><el-tag
            v-for="tag in problemInfo.tags"
            :key="tag"
            :type="primary"
            effect="light"
            round
            class="tag"
            >{{ tag }}</el-tag
          ></span
        >
      </template>
    </el-page-header>
    <div class="split-pane">
      <div class="left-pane">
        <!-- 左面板内容 -->
        <el-scrollbar max-height="400px">
          <div class="collapse">
            <el-collapse v-model="activeNames" @change="handleChange">
              <el-collapse-item title="题目描述" name="1" class="demo">
                <div>
                  {{ problemInfo.description }}
                </div>
              </el-collapse-item>
              <el-collapse-item title="示例输入" name="2">
                {{ problemInfo.inputExample }}
              </el-collapse-item>
              <el-collapse-item title="示例输出" name="3">
                {{ problemInfo.outputExample }}
              </el-collapse-item>
            </el-collapse>
          </div>
        </el-scrollbar>
      </div>
      <div class="separator" @mousedown="handleMouseDown"></div>
      <div class="right-pane">
        <!-- 右面板内容 -->
        右面板内容
      </div>
    </div>
  </div>
</template>

<style scoped>
.tag {
  margin: 0 3px;
}

.split-pane {
  display: flex;
  height: 100vh;
}

.left-pane {
  width: 50%;
  background-color: #fffefb;
  padding: 20px;
  overflow: auto;
}

.separator {
  width: 5px;
  background-color: #ccc;
  cursor: col-resize;
}

.right-pane {
  flex: 1;
  background-color: #fffefb;
  padding: 20px;
  overflow: auto;
}

::v-deep .el-collapse-item__header {
  background-color: #fffefb;
}

::v-deep .el-collapse-item__wrap {
  background-color: #fffefb;
}
</style>
