<template>
  <div class="japanese-quiz-container">
    <!-- 头部信息 -->
    <header class="quiz-header">
      <h1>日语学习 - 答题练习</h1>
      <div class="progress-info">
        <span>进度: {{ currentQuestionIndex + 1 }} / {{ questions.length }}</span>
        <div class="progress-bar">
          <div class="progress-fill" :style="{ width: progressPercentage + '%' }"></div>
        </div>
      </div>
    </header>

    <!-- 答题区域 -->
    <main class="quiz-main" v-if="!quizCompleted">
      <div class="question-card">
        <div class="question-header">
          <span class="question-type">{{ currentQuestion.type }}</span>
          <span class="question-points">{{ currentQuestion.points }}分</span>
        </div>
        
        <h2 class="question-text">{{ currentQuestion.question }}</h2>
        
        <!-- 选项区域 -->
        <div class="options-container">
          <div 
            v-for="(option, index) in currentQuestion.options" 
            :key="index"
            :class="['option-item', { 
              selected: selectedAnswer === index,
              correct: showResult && index === currentQuestion.correctAnswer,
              incorrect: showResult && selectedAnswer === index && index !== currentQuestion.correctAnswer
            }]"
            @click="selectAnswer(index)"
          >
            <span class="option-label">{{ getOptionLabel(index) }}</span>
            <span class="option-text">{{ option }}</span>
          </div>
        </div>
        <!--:disabled="selectedAnswer === null || showResult"-->
        <!-- 操作按钮 -->
        <div class="quiz-actions">
          <button 
            class="btn btn-primary" 
            @click="submitAnswer" 
            
            :disabled="!showResult && selectedAnswer === null"
          >
            {{ showResult ? '继续' : '提交答案' }}
          </button>
        </div>
      </div>
      
      <!-- 结果提示 -->
      <div v-if="showResult" class="result-feedback">
        <div :class="['feedback-message', isAnswerCorrect ? 'correct' : 'incorrect']">
          <i :class="isAnswerCorrect ? 'icon-correct' : 'icon-incorrect'"></i>
          <span>{{ isAnswerCorrect ? '正确!' : '不正确' }}</span>
        </div>
        <div v-if="!isAnswerCorrect" class="correct-answer">
          正确答案: {{ getOptionLabel(currentQuestion.correctAnswer) }}. {{ currentQuestion.options[currentQuestion.correctAnswer] }}
        </div>
        <div class="explanation" v-if="currentQuestion.explanation">
          <h4>解析:</h4>
          <p>{{ currentQuestion.explanation }}</p>
        </div>
      </div>
    </main>

    <!-- 结果页面 -->
    <main class="quiz-results" v-else>
      <div class="results-card">
        <h2>答题完成!</h2>
        <div class="score-summary">
          <div class="score-circle">
            <span class="score-value">{{ score }}</span>
            <span class="score-total">/{{ totalPoints }}</span>
          </div>
          <div class="score-text">
            <p>你的得分</p>
            <p class="score-percentage">{{ scorePercentage }}%</p>
          </div>
        </div>
        
        <div class="results-details">
          <div class="result-item">
            <span>正确题数:</span>
            <span>{{ correctAnswers }}/{{ questions.length }}</span>
          </div>
          <div class="result-item">
            <span>总分:</span>
            <span>{{ totalPoints }}分</span>
          </div>
        </div>
        
        <div class="quiz-actions">
          <button class="btn btn-secondary" @click="restartQuiz">重新开始</button>
          <button class="btn btn-primary" @click="reviewAnswers">查看答案</button>
        </div>
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { quizApi } from '../api'
// 问题数据
const questions = ref([
  {
    id: 1,
    type: '五十音图',
    question: '请选择「は」的正确发音:',
    options: ['ha', 'he', 'hi', 'ho'],
    correctAnswer: 0,
    points: 10,
    explanation: '「は」在五十音图中的发音是"ha"。注意在助词用法中发音可能变化为"wa"。'
  },
  {
    id: 2,
    type: '动词变化',
    question: '「食べる」的ます形是:',
    options: ['食べます', '食べです', '食べます', '食べます'],
    correctAnswer: 0,
    points: 15,
    explanation: '一类动词「食べる」的ます形是去掉「る」加上「ます」，即「食べます」。'
  },
  {
    id: 3,
    type: '语法',
    question: '「～たい」表示什么含义?',
    options: ['想要做', '正在做', '做过', '不做'],
    correctAnswer: 0,
    points: 20,
    explanation: '「～たい」是表示第一人称愿望的助动词，表示"想要做某事"。'
  },
  {
    id: 4,
    type: '词汇',
    question: '「美しい」的中文意思是:',
    options: ['美丽的', '丑陋的', '聪明的', '愚蠢的'],
    correctAnswer: 0,
    points: 15,
    explanation: '「美しい」(utsukushii)意为"美丽的、漂亮的"。'
  },
  {
    id: 5,
    type: '敬语',
    question: '对上司说"请进"应该用:',
    options: ['入ってください', '入ります', 'お入りください', '入る'],
    correctAnswer: 2,
    points: 25,
    explanation: '对上司等尊敬对象应使用敬语「お入りください」。'
  }
])

//数据初始化
onMounted(()=>{
  const response=quizApi.getQuizList({
    page:0,
    size:5,
    sort:'questionType,desc'
  });
  this.questions.value=response.data.data;
})
// 状态管理
const currentQuestionIndex = ref(0)
const selectedAnswer = ref(null)
const showResult = ref(false)
const isAnswerCorrect = ref(false)
const quizCompleted = ref(false)
const userAnswers = ref([])

// 计算属性
const currentQuestion = computed(() => {
  return questions.value[currentQuestionIndex.value]
})

const progressPercentage = computed(() => {
  return ((currentQuestionIndex.value + 1) / questions.value.length) * 100
})

const score = computed(() => {
  return userAnswers.value.reduce((total, answer) => {
    if (answer.isCorrect) {
      const question = questions.value.find(q => q.id === answer.questionId)
      return total + (question ? question.points : 0)
    }
    return total
  }, 0)
})

const totalPoints = computed(() => {
  return questions.value.reduce((total, question) => total + question.points, 0)
})

const scorePercentage = computed(() => {
  return totalPoints.value > 0 ? Math.round((score.value / totalPoints.value) * 100) : 0
})

const correctAnswers = computed(() => {
  return userAnswers.value.filter(answer => answer.isCorrect).length
})

// 方法
const getOptionLabel = (index) => {
  const labels = ['A', 'B', 'C', 'D', 'E']
  return labels[index] || index
}

const selectAnswer = (index) => {
  if (!showResult.value) {
    selectedAnswer.value = index
  }
}

const submitAnswer = () => {
  if (selectedAnswer.value === null) return
  
  if (!showResult.value) {
    // 提交答案
    const correct = selectedAnswer.value === currentQuestion.value.correctAnswer
    isAnswerCorrect.value = correct
    
    userAnswers.value.push({
      questionId: currentQuestion.value.id,
      selectedAnswer: selectedAnswer.value,
      isCorrect: correct
    })
    
    showResult.value = true
  } else {
    // 继续下一题
    nextQuestion()
  }
}

const nextQuestion = () => {
  if (currentQuestionIndex.value < questions.value.length - 1) {
    currentQuestionIndex.value++
    selectedAnswer.value = null
    showResult.value = false
    isAnswerCorrect.value = false
  } else {
    // 测验完成
    quizCompleted.value = true
  }
}

const restartQuiz = () => {
  currentQuestionIndex.value = 0
  selectedAnswer.value = null
  showResult.value = false
  isAnswerCorrect.value = false
  quizCompleted.value = false
  userAnswers.value = []
}

const reviewAnswers = () => {
  // 这里可以跳转到答案回顾页面
  console.log('查看答案详情')
}
</script>

<style scoped>
.japanese-quiz-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Arial', sans-serif;
}

.quiz-header {
  text-align: center;
  margin-bottom: 30px;
}

.quiz-header h1 {
  color: #2c3e50;
  margin-bottom: 20px;
}

.progress-info {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.progress-info span {
  margin-bottom: 10px;
  font-weight: bold;
}

.progress-bar {
  width: 100%;
  height: 10px;
  background-color: #ecf0f1;
  border-radius: 5px;
  overflow: hidden;
}

.progress-fill {
  height: 100%;
  background-color: #3498db;
  transition: width 0.3s ease;
}

.question-card {
  background: white;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  padding: 25px;
  margin-bottom: 20px;
}

.question-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.question-type {
  background-color: #3498db;
  color: white;
  padding: 5px 10px;
  border-radius: 15px;
  font-size: 14px;
}

.question-points {
  background-color: #2ecc71;
  color: white;
  padding: 5px 10px;
  border-radius: 15px;
  font-size: 14px;
}

.question-text {
  font-size: 20px;
  margin-bottom: 25px;
  color: #2c3e50;
}

.options-container {
  display: flex;
  flex-direction: column;
  gap: 15px;
  margin-bottom: 25px;
}

.option-item {
  display: flex;
  align-items: center;
  padding: 15px;
  border: 2px solid #ecf0f1;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.option-item:hover {
  border-color: #3498db;
  background-color: #f8f9fa;
}

.option-item.selected {
  border-color: #3498db;
  background-color: #e1f0fa;
}

.option-item.correct {
  border-color: #2ecc71;
  background-color: #e8f8f5;
}

.option-item.incorrect {
  border-color: #e74c3c;
  background-color: #fdf2f2;
}

.option-label {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 30px;
  height: 30px;
  background-color: #3498db;
  color: white;
  border-radius: 50%;
  margin-right: 15px;
  font-weight: bold;
}

.quiz-actions {
  display: flex;
  justify-content: center;
}

.btn {
  padding: 12px 30px;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.btn-primary {
  background-color: #3498db;
  color: white;
}

.btn-primary:hover:not(:disabled) {
  background-color: #2980b9;
}

.btn-primary:disabled {
  background-color: #bdc3c7;
  cursor: not-allowed;
}

.btn-secondary {
  background-color: #95a5a6;
  color: white;
  margin-right: 15px;
}

.btn-secondary:hover {
  background-color: #7f8c8d;
}

.result-feedback {
  background: white;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  padding: 25px;
}

.feedback-message {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 15px;
  border-radius: 8px;
  margin-bottom: 20px;
  font-size: 20px;
  font-weight: bold;
}

.feedback-message.correct {
  background-color: #e8f8f5;
  color: #27ae60;
}

.feedback-message.incorrect {
  background-color: #fdf2f2;
  color: #c0392b;
}

.icon-correct::before {
  content: "✓";
  margin-right: 10px;
}

.icon-incorrect::before {
  content: "✗";
  margin-right: 10px;
}

.correct-answer {
  background-color: #fff9e6;
  padding: 15px;
  border-radius: 8px;
  margin-bottom: 20px;
  border-left: 4px solid #f1c40f;
}

.explanation {
  background-color: #f8f9fa;
  padding: 15px;
  border-radius: 8px;
}

.explanation h4 {
  margin-top: 0;
  color: #2c3e50;
}

.quiz-results {
  display: flex;
  justify-content: center;
}

.results-card {
  background: white;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  padding: 40px;
  text-align: center;
  max-width: 500px;
}

.results-card h2 {
  color: #2c3e50;
  margin-bottom: 30px;
}

.score-summary {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 30px;
}

.score-circle {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  background: conic-gradient(#3498db 0% 70%, #ecf0f1 70% 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 30px;
}

.score-value {
  font-size: 36px;
  font-weight: bold;
  color: #2c3e50;
}

.score-total {
  font-size: 18px;
  color: #7f8c8d;
}

.score-text p {
  margin: 5px 0;
}

.score-percentage {
  font-size: 24px;
  font-weight: bold;
  color: #3498db;
}

.results-details {
  background-color: #f8f9fa;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 30px;
}

.result-item {
  display: flex;
  justify-content: space-between;
  padding: 10px 0;
  border-bottom: 1px solid #ecf0f1;
}

.result-item:last-child {
  border-bottom: none;
}

@media (max-width: 768px) {
  .japanese-quiz-container {
    padding: 15px;
  }
  
  .question-card,
  .results-card {
    padding: 20px;
  }
  
  .question-text {
    font-size: 18px;
  }
  
  .score-summary {
    flex-direction: column;
  }
  
  .score-circle {
    margin-right: 0;
    margin-bottom: 20px;
  }
}
</style>
