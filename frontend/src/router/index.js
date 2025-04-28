import { createRouter, createWebHistory } from 'vue-router';

import LoginView from '@/views/login/index.vue';
import LayoutView from '@/views/layout/index.vue';
import ProblemsView from '@/views/problem/list.vue';
import IndexView from '@/views/index/index.vue';
import ProblemDetailView from '@/views/problem/detail.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: '',
      component: LayoutView,
      redirect: '/index', // 重定向到首页
      children: [
        { path: 'problems', name: 'problems', component: ProblemsView },
        { path: 'index', name: 'index', component: IndexView },
        { path: '/problems/:id', name: 'problemDetail', component: ProblemDetailView }
      ]
    },
    { path: '/login', name: 'login', component: LoginView },
    
  ]
})

export default router
