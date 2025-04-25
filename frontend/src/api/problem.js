import request from '@/utils/request';

// 查询题库
export const queryPageApi = (title,difficulty,tags) => request.get(`/problems?title=${title}&difficulty=${difficulty}&tags=${tags}`);