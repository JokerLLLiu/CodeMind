import request from '@/utils/request';

// 查询题库
export const queryPageApi = () => request.get('/problems');