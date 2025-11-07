<template>
  <div>
    <button @click="loadQuizzes">加载测验</button>
    <div v-for="quiz in quizList" :key="quiz.id">
      {{ quiz.question }}
    </div>
  </div>
</template>

<script>
// ✅ 清晰简洁的导入
import { quizApi } from '@/api';

export default {
  data() {
    return {
      quizList: []
    };
  },
  methods: {
    async loadQuizzes() {
      try {
        // ✅ 直接使用封装好的 API
        const response = await quizApi.getQuizList({
          page: 0,
          size: 10,
          sort: 'questionType,desc'
        });
        
        this.quizList = response.content;
      } catch (error) {
        console.error('加载失败:', error);
      }
    },
    
    async submitAnswer(quizId, answer) {
      try {
        await quizApi.submitAnswer({ quizId, answer });
        this.$message.success('提交成功！');
      } catch (error) {
        this.$message.error('提交失败');
      }
    }
  },
  mounted() {
    this.loadQuizzes();
  }
};
</script>