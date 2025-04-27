import request from '@/utils/request';

// 查询题库
export const queryPageApi = (title,difficulty,tags,page,pageSize) => {
    const tagString = tags.join(',');
    return request.get(`/problems?title=${title}&difficulty=${difficulty}&tags=${tagString}&page=${page}&pageSize=${pageSize}`);
}

// 查询标签库
export const queryTagsApi = () => request.get('/problems/tags');