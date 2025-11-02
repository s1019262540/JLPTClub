// 注册页面 Vue 组件
const RegisterPage = {
    template: `
        <div class="register-page">
            <!-- 语言切换器 -->
            <div class="language-switcher">
                <select v-model="currentLanguage" @change="changeLanguage">
                    <option value="zh">中文</option>
                    <option value="ja">日本語</option>
                    <option value="en">English</option>
                </select>
            </div>

            <!-- 注册容器 -->
            <div class="register-container">
                <!-- Logo 区域 -->
                <div class="logo">
                    <h1>{{ translations[currentLanguage].title }}</h1>
                    <p>{{ translations[currentLanguage].subtitle }}</p>
                </div>

                <!-- 注册表单 -->
                <form class="register-form" @submit.prevent="handleSubmit">
                    <!-- 用户名输入 -->
                    <div class="form-group">
                        <label for="username">{{ translations[currentLanguage].username }}</label>
                        <input 
                            type="text" 
                            id="username"
                            v-model="formData.username"
                            :placeholder="translations[currentLanguage].usernamePlaceholder"
                            @blur="validateUsername"
                            :class="{ 'error': errors.username }"
                            required
                        >
                        <div class="error-message" v-if="errors.username">
                            {{ translations[currentLanguage].usernameError }}
                        </div>
                    </div>

                    <!-- 邮箱输入 -->
                    <div class="form-group">
                        <label for="email">{{ translations[currentLanguage].email }}</label>
                        <input 
                            type="email" 
                            id="email"
                            v-model="formData.email"
                            placeholder="example@email.com"
                            @blur="validateEmail"
                            :class="{ 'error': errors.email }"
                            required
                        >
                        <div class="error-message" v-if="errors.email">
                            {{ translations[currentLanguage].emailError }}
                        </div>
                    </div>

                    <!-- 密码输入 -->
                    <div class="form-group">
                        <label for="password">{{ translations[currentLanguage].password }}</label>
                        <input 
                            type="password" 
                            id="password"
                            v-model="formData.password"
                            :placeholder="translations[currentLanguage].passwordPlaceholder"
                            @input="updatePasswordStrength"
                            @blur="validatePassword"
                            :class="{ 'error': errors.password }"
                            required
                        >
                        <!-- 密码强度条 -->
                        <div class="password-strength">
                            <div 
                                class="strength-bar" 
                                :style="{
                                    width: passwordStrength.width + '%',
                                    backgroundColor: passwordStrength.color
                                }"
                            ></div>
                        </div>
                        <div class="error-message" v-if="errors.password">
                            {{ translations[currentLanguage].passwordError }}
                        </div>
                    </div>

                    <!-- 确认密码 -->
                    <div class="form-group">
                        <label for="confirm-password">{{ translations[currentLanguage].confirmPassword }}</label>
                        <input 
                            type="password" 
                            id="confirm-password"
                            v-model="formData.confirmPassword"
                            :placeholder="translations[currentLanguage].confirmPasswordPlaceholder"
                            @input="validatePasswordMatch"
                            :class="{ 'error': errors.confirmPassword }"
                            required
                        >
                        <div class="error-message" v-if="errors.confirmPassword">
                            {{ translations[currentLanguage].confirmPasswordError }}
                        </div>
                    </div>

                    <!-- 日语水平选择 -->
                    <div class="form-group">
                        <label for="level">{{ translations[currentLanguage].level }}</label>
                        <select 
                            id="level"
                            v-model="formData.level"
                            :class="{ 'error': errors.level }"
                            required
                        >
                            <option value="" disabled>{{ translations[currentLanguage].selectLevel }}</option>
                            <option value="N5">N5 ({{ translations[currentLanguage].levels.N5 }})</option>
                            <option value="N4">N4 ({{ translations[currentLanguage].levels.N4 }})</option>
                            <option value="N3">N3 ({{ translations[currentLanguage].levels.N3 }})</option>
                            <option value="N2">N2 ({{ translations[currentLanguage].levels.N2 }})</option>
                            <option value="N1">N1 ({{ translations[currentLanguage].levels.N1 }})</option>
                        </select>
                        <div class="error-message" v-if="errors.level">
                            {{ translations[currentLanguage].levelError }}
                        </div>
                    </div>

                    <!-- 表单底部 -->
                    <div class="form-footer">
                        <button 
                            type="submit" 
                            class="btn btn-primary"
                            :disabled="isSubmitting || !isFormValid"
                        >
                            <span v-if="isSubmitting">
                                <i class="fas fa-spinner fa-spin"></i>
                                {{ translations[currentLanguage].processing }}
                            </span>
                            <span v-else>
                                {{ translations[currentLanguage].register }}
                            </span>
                        </button>
                        
                        <button 
                            type="button" 
                            class="btn btn-secondary" 
                            @click="goToLogin"
                        >
                            {{ translations[currentLanguage].loginLink }}
                        </button>
                    </div>

                    <!-- 成功消息 -->
                    <div v-if="showSuccess" class="success-message">
                        <i class="fas fa-check-circle"></i>
                        <p>{{ successMessage }}</p>
                    </div>

                    <!-- 错误消息 -->
                    <div v-if="showError" class="error-message global-error">
                        <i class="fas fa-exclamation-circle"></i>
                        <p>{{ errorMessage }}</p>
                    </div>

                    <!-- 服务条款 -->
                    <div class="terms">
                        {{ translations[currentLanguage].termsText }}
                        <a href="#">{{ translations[currentLanguage].terms }}</a>
                        {{ translations[currentLanguage].and }}
                        <a href="#">{{ translations[currentLanguage].privacy }}</a>
                        {{ translations[currentLanguage].agree }}
                    </div>
                </form>
            </div>
        </div>
    `,

    data() {
        return {
            // 表单数据
            formData: {
                username: '',
                email: '',
                password: '',
                confirmPassword: '',
                level: ''
            },

            // 错误状态
            errors: {
                username: false,
                email: false,
                password: false,
                confirmPassword: false,
                level: false
            },

            // 密码强度
            passwordStrength: {
                width: 0,
                color: '#eee'
            },

            // 界面状态
            isSubmitting: false,
            showSuccess: false,
            showError: false,
            successMessage: '',
            errorMessage: '',

            // 多语言支持
            currentLanguage: 'ja',
            translations: {
                zh: {
                    title: '日语学习',
                    subtitle: '创建新账户',
                    username: '用户名',
                    usernamePlaceholder: '4-20个字符的用户名',
                    usernameError: '用户名必须是4-20个字符',
                    email: '邮箱地址',
                    emailError: '请输入有效的邮箱地址',
                    password: '密码',
                    passwordPlaceholder: '至少8个字符的密码',
                    passwordError: '密码必须至少8个字符，包含大小写字母和数字',
                    confirmPassword: '确认密码',
                    confirmPasswordPlaceholder: '再次输入密码',
                    confirmPasswordError: '密码不匹配',
                    level: '日语水平',
                    selectLevel: '请选择',
                    levelError: '请选择日语水平',
                    levels: {
                        N5: '初学者',
                        N4: '初级',
                        N3: '中级',
                        N2: '中高级',
                        N1: '高级'
                    },
                    register: '注册',
                    loginLink: '已有账户？登录',
                    processing: '处理中...',
                    termsText: '注册即表示您同意',
                    terms: '服务条款',
                    and: '和',
                    privacy: '隐私政策',
                    agree: ''
                },
                ja: {
                    title: '日本語学習',
                    subtitle: '新しいアカウントを作成',
                    username: 'ユーザー名',
                    usernamePlaceholder: '4-20文字のユーザー名',
                    usernameError: 'ユーザー名は4文字以上20文字以下で入力してください',
                    email: 'メールアドレス',
                    emailError: '有効なメールアドレスを入力してください',
                    password: 'パスワード',
                    passwordPlaceholder: '8文字以上のパスワード',
                    passwordError: 'パスワードは8文字以上で、大文字、小文字、数字を含めてください',
                    confirmPassword: 'パスワード確認',
                    confirmPasswordPlaceholder: 'パスワードを再入力',
                    confirmPasswordError: 'パスワードが一致しません',
                    level: '日本語レベル',
                    selectLevel: '選択してください',
                    levelError: '日本語レベルを選択してください',
                    levels: {
                        N5: '初心者',
                        N4: '初級',
                        N3: '中級',
                        N2: '中上級',
                        N1: '上級'
                    },
                    register: '登録',
                    loginLink: 'ログインはこちら',
                    processing: '処理中...',
                    termsText: '登録することで、',
                    terms: '利用規約',
                    and: 'と',
                    privacy: 'プライバシーポリシー',
                    agree: 'に同意したものとみなされます。'
                },
                en: {
                    title: 'Japanese Learning',
                    subtitle: 'Create New Account',
                    username: 'Username',
                    usernamePlaceholder: '4-20 characters username',
                    usernameError: 'Username must be 4-20 characters',
                    email: 'Email Address',
                    emailError: 'Please enter a valid email address',
                    password: 'Password',
                    passwordPlaceholder: 'At least 8 characters',
                    passwordError: 'Password must be at least 8 characters with uppercase, lowercase and numbers',
                    confirmPassword: 'Confirm Password',
                    confirmPasswordPlaceholder: 'Re-enter password',
                    confirmPasswordError: 'Passwords do not match',
                    level: 'Japanese Level',
                    selectLevel: 'Please select',
                    levelError: 'Please select Japanese level',
                    levels: {
                        N5: 'Beginner',
                        N4: 'Elementary',
                        N3: 'Intermediate',
                        N2: 'Upper Intermediate',
                        N1: 'Advanced'
                    },
                    register: 'Register',
                    loginLink: 'Already have an account? Login',
                    processing: 'Processing...',
                    termsText: 'By registering, you agree to our',
                    terms: 'Terms of Service',
                    and: 'and',
                    privacy: 'Privacy Policy',
                    agree: ''
                }
            }
        }
    },

    computed: {
        // 表单验证状态
        isFormValid() {
            return !this.errors.username &&
                !this.errors.email &&
                !this.errors.password &&
                !this.errors.confirmPassword &&
                !this.errors.level &&
                this.formData.username &&
                this.formData.email &&
                this.formData.password &&
                this.formData.confirmPassword &&
                this.formData.level;
        }
    },

    methods: {
        // 用户名验证
        validateUsername() {
            const isValid = this.formData.username.length >= 4 &&
                this.formData.username.length <= 20;
            this.errors.username = !isValid;
            return isValid;
        },

        // 邮箱验证
        validateEmail() {
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            const isValid = emailRegex.test(this.formData.email);
            this.errors.email = !isValid;
            return isValid;
        },

        // 密码验证
        validatePassword() {
            const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
            const isValid = passwordRegex.test(this.formData.password);
            this.errors.password = !isValid;
            return isValid;
        },

        // 密码匹配验证
        validatePasswordMatch() {
            const isValid = this.formData.password === this.formData.confirmPassword;
            this.errors.confirmPassword = !isValid;
            return isValid;
        },

        // 水平选择验证
        validateLevel() {
            const isValid = this.formData.level !== '';
            this.errors.level = !isValid;
            return isValid;
        },

        // 计算密码强度
        calculatePasswordStrength(password) {
            let strength = 0;

            if (password.length >= 8) strength += 1;
            if (password.length >= 12) strength += 1;
            if (/[A-Z]/.test(password)) strength += 1;
            if (/[a-z]/.test(password)) strength += 1;
            if (/[0-9]/.test(password)) strength += 1;
            if (/[^A-Za-z0-9]/.test(password)) strength += 1;

            return Math.min(strength, 5);
        },

        // 更新密码强度显示
        updatePasswordStrength() {
            const strength = this.calculatePasswordStrength(this.formData.password);
            const colors = ['#ff0000', '#ff5a00', '#ffb400', '#a0ff00', '#00ff00'];
            const width = strength * 20;

            this.passwordStrength = {
                width: width,
                color: colors[strength - 1] || '#eee'
            };

            this.validatePassword();
        },

        // 处理表单提交
        async handleSubmit() {
            // 验证所有字段
            this.validateUsername();
            this.validateEmail();
            this.validatePassword();
            this.validatePasswordMatch();
            this.validateLevel();

            if (!this.isFormValid) {
                this.showError = true;
                this.errorMessage = this.translations[this.currentLanguage].formError || '请正确填写所有字段';
                return;
            }

            this.isSubmitting = true;
            this.showError = false;

            try {
                const response = await fetch('/api/register', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'X-CSRF-TOKEN': this.getCsrfToken()
                    },
                    body: JSON.stringify(this.formData)
                });

                if (!response.ok) {
                    const errorData = await response.json();
                    throw new Error(errorData.message || 'Registration failed');
                }

                const data = await response.json();

                // 显示成功消息
                this.showSuccess = true;
                this.successMessage = data.message || this.translations[this.currentLanguage].successMessage;

                // 3秒后跳转到登录页面
                setTimeout(() => {
                    window.location.href = `/login?registered=true&lang=${this.currentLanguage}`;
                }, 3000);

            } catch (error) {
                this.showError = true;
                this.errorMessage = error.message || this.translations[this.currentLanguage].registerFailed;
            } finally {
                this.isSubmitting = false;
            }
        },

        // 获取 CSRF Token
        getCsrfToken() {
            const meta = document.querySelector('meta[name="csrf-token"]');
            return meta ? meta.content : '';
        },

        // 跳转到登录页面
        goToLogin() {
            window.location.href = `/login?lang=${this.currentLanguage}`;
        },

        // 切换语言
        changeLanguage() {
            // 这里可以添加语言切换的持久化逻辑
            console.log('Language changed to:', this.currentLanguage);
        }
    },

    watch: {
        // 监听密码确认字段的变化
        'formData.confirmPassword': function(newVal) {
            if (newVal) {
                this.validatePasswordMatch();
            }
        },

        // 监听水平选择的变化
        'formData.level': function(newVal) {
            if (newVal) {
                this.validateLevel();
            }
        }
    }
}

export default RegisterPage;