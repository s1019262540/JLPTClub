import request from '../request';

// 测验相关的 API
export const quizApi = {
  // 获取测验列表
  getQuizList(params) {
    return request.get('/quiz/all', { params });
  },
  
  // 获取单个测验
  getQuizById(id) {
    return request.get(`/quiz/${id}`);
  },
  
  // 提交答案
  submitAnswer(data) {
    return request.post('/quiz/submit', data);
  },
  
  // 删除测验
  deleteQuiz(id) {
    return request.delete(`/quiz/${id}`);
  },
  
  // 创建测验
  createQuiz(data) {
    return request.post('/quiz', data);
  }
};

export default quizApi;