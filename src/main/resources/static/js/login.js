// 登录页面 Vue 组件
const LoginPage = {
    template: `
        <div class="login-page">
            <!-- 语言切换器 -->
            <div class="language-switcher">
                <select v-model="currentLanguage" @change="changeLanguage">
                    <option value="zh">中文</option>
                    <option value="ja">日本語</option>
                    <option value="en">English</option>
                </select>
            </div>

            <!-- 登录容器 -->
            <div class="login-container">
                <!-- Logo 区域 -->
                <div class="logo">
                    <h1>{{ translations[currentLanguage].title }}</h1>
                    <p>{{ translations[currentLanguage].subtitle }}</p>
                </div>

                <!-- 登录表单 -->
                <form class="login-form" @submit.prevent="handleLogin">
                    <!-- 邮箱输入 -->
                    <div class="form-group" :class="{ 'focused': isEmailFocused }">
                        <label for="email">{{ translations[currentLanguage].email }}</label>
                        <input 
                            type="email" 
                            id="email"
                            v-model="loginData.email"
                            placeholder="example@email.com"
                            @focus="setEmailFocus(true)"
                            @blur="setEmailFocus(false); validateEmail()"
                            :class="{ 'error': errors.email }"
                            required
                        >
                        <div class="error-message" v-if="errors.email">
                            {{ translations[currentLanguage].emailError }}
                        </div>
                    </div>

                    <!-- 密码输入 -->
                    <div class="form-group" :class="{ 'focused': isPasswordFocused }">
                        <label for="password">{{ translations[currentLanguage].password }}</label>
                        <input 
                            type="password" 
                            id="password"
                            v-model="loginData.password"
                            placeholder="••••••••"
                            @focus="setPasswordFocus(true)"
                            @blur="setPasswordFocus(false); validatePassword()"
                            :class="{ 'error': errors.password }"
                            required
                        >
                        <div class="error-message" v-if="errors.password">
                            {{ translations[currentLanguage].passwordError }}
                        </div>
                    </div>

                    <!-- 记住密码和忘记密码 -->
                    <div class="remember-forgot">
                        <div class="remember-me">
                            <input 
                                type="checkbox" 
                                id="remember" 
                                v-model="loginData.rememberMe"
                            >
                            <label for="remember">
                                {{ translations[currentLanguage].rememberMe }}
                            </label>
                        </div>
                        <div class="forgot-password">
                            <a href="#" @click.prevent="handleForgotPassword">
                                {{ translations[currentLanguage].forgotPassword }}
                            </a>
                        </div>
                    </div>

                    <!-- 登录按钮 -->
                    <button 
                        type="submit" 
                        class="btn btn-primary"
                        :disabled="isLoggingIn || !isFormValid"
                    >
                        <span v-if="isLoggingIn">
                            <i class="fas fa-spinner fa-spin"></i>
                            {{ translations[currentLanguage].loggingIn }}
                        </span>
                        <span v-else>
                            {{ translations[currentLanguage].login }}
                        </span>
                    </button>

                    <!-- 试用演示按钮 -->
                    <button 
                        type="button" 
                        class="btn btn-secondary" 
                        @click="tryDemo"
                    >
                        {{ translations[currentLanguage].tryDemo }}
                    </button>

                    <!-- 社交登录 -->
                    <div class="social-login">
                        <p>{{ translations[currentLanguage].socialLogin }}</p>
                        <div class="social-icons">
                            <a href="#" class="social-icon" @click.prevent="socialLogin('google')">
                                <i class="fab fa-google"></i>
                            </a>
                            <a href="#" class="social-icon" @click.prevent="socialLogin('facebook')">
                                <i class="fab fa-facebook-f"></i>
                            </a>
                            <a href="#" class="social-icon" @click.prevent="socialLogin('twitter')">
                                <i class="fab fa-twitter"></i>
                            </a>
                        </div>
                    </div>

                    <!-- 注册链接 -->
                    <div class="register-link">
                        {{ translations[currentLanguage].noAccount }}
                        <a href="register.html">{{ translations[currentLanguage].register }}</a>
                    </div>
                </form>

                <!-- 成功消息弹窗 -->
                <div v-if="showSuccessPopup" class="success-popup">
                    <div class="popup-content">
                        <i class="fas fa-check-circle"></i>
                        <h3>{{ translations[currentLanguage].loginSuccess }}</h3>
                        <p>{{ translations[currentLanguage].welcome }}</p>
                        <button @click="redirectToDashboard" class="btn btn-primary">
                            {{ translations[currentLanguage].goToDashboard }}
                        </button>
                    </div>
                </div>

                <!-- 全局错误消息 -->
                <div v-if="showError" class="error-message global-error">
                    {{ errorMessage }}
                </div>
            </div>
        </div>
    `,

    data() {
        return {
            // 登录数据
            loginData: {
                email: '',
                password: '',
                rememberMe: false
            },

            // 输入框焦点状态
            isEmailFocused: false,
            isPasswordFocused: false,

            // 错误状态
            errors: {
                email: false,
                password: false
            },

            // 界面状态
            isLoggingIn: false,
            showSuccessPopup: false,
            showError: false,
            errorMessage: '',

            // 多语言支持
            currentLanguage: 'ja',
            translations: {
                zh: {
                    title: '日语学习',
                    subtitle: '学无止境 - 让我们一起学习日语',
                    email: '邮箱地址',
                    emailError: '请输入有效的邮箱地址',
                    password: '密码',
                    passwordError: '密码不能为空',
                    rememberMe: '保持登录状态',
                    forgotPassword: '忘记密码？',
                    login: '登录',
                    loggingIn: '登录中...',
                    tryDemo: '试用演示',
                    socialLogin: '或使用社交账号登录',
                    noAccount: '没有账户？',
                    register: '立即注册',
                    loginSuccess: '登录成功！',
                    welcome: '欢迎回来！',
                    goToDashboard: '进入仪表板'
                },
                ja: {
                    title: '日本語学習',
                    subtitle: '学びましょう - 一緒に日本語を勉強しましょう',
                    email: 'メールアドレス',
                    emailError: '有効なメールアドレスを入力してください',
                    password: 'パスワード',
                    passwordError: 'パスワードを入力してください',
                    rememberMe: 'ログイン状態を保持',
                    forgotPassword: 'パスワードを忘れた？',
                    login: 'ログイン',
                    loggingIn: 'ログイン中...',
                    tryDemo: 'デモを試す',
                    socialLogin: 'またはソーシャルアカウントでログイン',
                    noAccount: 'アカウントをお持ちでないですか？',
                    register: '新規登録',
                    loginSuccess: 'ログイン成功！',
                    welcome: 'ようこそ！',
                    goToDashboard: 'ダッシュボードへ'
                },
                en: {
                    title: 'Japanese Learning',
                    subtitle: 'Keep Learning - Let\'s study Japanese together',
                    email: 'Email Address',
                    emailError: 'Please enter a valid email address',
                    password: 'Password',
                    passwordError: 'Password cannot be empty',
                    rememberMe: 'Remember me',
                    forgotPassword: 'Forgot password?',
                    login: 'Login',
                    loggingIn: 'Logging in...',
                    tryDemo: 'Try Demo',
                    socialLogin: 'Or login with social account',
                    noAccount: 'Don\'t have an account?',
                    register: 'Register now',
                    loginSuccess: 'Login Successful!',
                    welcome: 'Welcome back!',
                    goToDashboard: 'Go to Dashboard'
                }
            }
        }
    },

    computed: {
        // 表单验证状态
        isFormValid() {
            return this.loginData.email &&
                this.loginData.password &&
                !this.errors.email &&
                !this.errors.password;
        }
    },

    methods: {
        // 设置邮箱输入框焦点状态
        setEmailFocus(isFocused) {
            this.isEmailFocused = isFocused;
        },

        // 设置密码输入框焦点状态
        setPasswordFocus(isFocused) {
            this.isPasswordFocused = isFocused;
        },

        // 邮箱验证
        validateEmail() {
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            const isValid = emailRegex.test(this.loginData.email);
            this.errors.email = !isValid && this.loginData.email.length > 0;
            return isValid;
        },

        // 密码验证
        validatePassword() {
            const isValid = this.loginData.password.length >= 1;
            this.errors.password = !isValid && this.loginData.password.length > 0;
            return isValid;
        },

        // 处理登录
        async handleLogin() {
            // 验证表单
            const isEmailValid = this.validateEmail();
            const isPasswordValid = this.validatePassword();

            if (!isEmailValid || !isPasswordValid) {
                this.showError = true;
                this.errorMessage = this.translations[this.currentLanguage].formError || '请正确填写所有字段';
                return;
            }

            this.isLoggingIn = true;
            this.showError = false;

            try {
                // 发送登录请求
                const response = await fetch('/user/register', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'X-CSRF-TOKEN': this.getCsrfToken()
                    },
                    body: JSON.stringify(this.loginData)
                });

                if (!response.ok) {
                    const errorData = await response.json();
                    throw new Error(errorData.message || 'Login failed');
                }

                const data = await response.json();

                // 显示成功弹窗（根据截图中的弹窗样式）
                this.showSuccessPopup = true;

                // 如果选择了记住我，保存登录状态
                if (this.loginData.rememberMe) {
                    localStorage.setItem('userToken', data.token);
                    localStorage.setItem('userInfo', JSON.stringify(data.user));
                }

                // 3秒后自动跳转（根据截图中的重定向逻辑）
                setTimeout(() => {
                    this.redirectToDashboard();
                }, 3000);

            } catch (error) {
                this.showError = true;
                this.errorMessage = error.message || this.translations[this.currentLanguage].loginFailed;
            } finally {
                this.isLoggingIn = false;
            }
        },

        // 试用演示功能
        tryDemo() {
            this.loginData.email = 'demo@jlptclub.com';
            this.loginData.password = 'demopassword123';

            // 自动填充后直接登录
            setTimeout(() => {
                this.handleLogin();
            }, 500);
        },

        // 社交登录
        socialLogin(provider) {
            const providers = {
                google: '/auth/google',
                facebook: '/auth/facebook',
                twitter: '/auth/twitter'
            };

            window.location.href = providers[provider] || '/auth/google';
        },

        // 处理忘记密码
        handleForgotPassword() {
            // 这里可以跳转到忘记密码页面或显示重置密码模态框
            alert(this.translations[this.currentLanguage].forgotPasswordMessage || '密码重置功能即将推出');
        },

        // 跳转到仪表板
        redirectToDashboard() {
            window.location.href = '/dashboard?lang=' + this.currentLanguage;
        },

        // 获取 CSRF Token
        getCsrfToken() {
            const meta = document.querySelector('meta[name="csrf-token"]');
            return meta ? meta.content : '';
        },

        // 切换语言
        changeLanguage() {
            // 可以在这里添加语言切换的持久化逻辑
            console.log('Language changed to:', this.currentLanguage);
        }
    },

    mounted() {
        // 页面加载时检查是否有保存的登录信息
        const savedEmail = localStorage.getItem('savedEmail');
        if (savedEmail) {
            this.loginData.email = savedEmail;
            this.loginData.rememberMe = true;
        }

        // 检查URL参数，比如从注册页面跳转过来
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.get('registered') === 'true') {
            this.showSuccessPopup = true;
            this.successMessage = this.translations[this.currentLanguage].registrationSuccess;
        }
    }
}

export default LoginPage;