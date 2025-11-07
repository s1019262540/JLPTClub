import request from '../request';

// 用户相关的 API
export const userApi = {
  login(credentials) {
    return request.post('/auth/login', credentials);
  },
  
  register(userData) {
    return request.post('/auth/register', userData);
  },
  
  getProfile() {
    return request.get('/user/profile');
  },
  
  updateProfile(data) {
    return request.put('/user/profile', data);
  }
};

export default userApi;