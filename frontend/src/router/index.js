import { createRouter, createWebHistory } from 'vue-router';

import LoginView from '../views/login/index.vue';
import LayoutView from '../views/layout/index.vue';
import ProblemListView from '../views/problem/list.vue';
import IndexView from '../views/index/index.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: '',
      component: LayoutView,
      redirect: '/problemList', // 重定向到首页
      children: [
        { path: 'problemList', name: 'problemList', component: ProblemListView },
        { path: 'index', name: 'index', component: IndexView },
      ]
    },
    { path: '/login', name: 'login', component: LoginView },
  ]
})

export default router
