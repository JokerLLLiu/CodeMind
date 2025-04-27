import request from '@/utils/request';

// 查询题库
export const queryPageApi = (title,difficulty,tags) => {
    const tagString = tags.join(',');
    return request.get(`/problems?title=${title}&difficulty=${difficulty}&tags=${tagString}`);
}

// 查询标签库
export const queryTagsApi = () => request.get('/problems/tags');