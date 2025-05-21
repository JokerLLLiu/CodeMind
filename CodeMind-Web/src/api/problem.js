import request from '@/utils/request';

// 查询题库
export const queryPageApi = (title,difficulty,tags,page,pageSize) => {
    const tagString = tags.join(',');
    return request.get(`/user/problems?title=${title}&difficulty=${difficulty}&tags=${tagString}&page=${page}&pageSize=${pageSize}`);
}

// 查询标签库
export const queryTagsApi = () => request.get('/user/problems/tags');

// 通过id查询题目详情
export const queryProblemByIdApi = (id) => request.get(`/problems/${id}`);