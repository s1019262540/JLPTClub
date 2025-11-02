-- 用户表 COMMENT='存储用户基本信息和账户数据'
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户唯一标识ID，自增长',
    username VARCHAR(50) NOT NULL COMMENT '用户昵称或显示名称',
    email VARCHAR(100) NOT NULL UNIQUE COMMENT '用户邮箱，用于登录和通知，唯一',
    password_hash VARCHAR(255) NOT NULL COMMENT '加密后的用户密码',
    avatar VARCHAR(255) COMMENT '用户头像URL路径',
    current_level ENUM('N5', 'N4', 'N3', 'N2', 'N1') DEFAULT 'N5' COMMENT '用户当前日语水平等级',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '用户注册时间',
    last_login TIMESTAMP COMMENT '用户最后登录时间'
);
-- 单词表 COMMENT='存储日语单词及其相关信息'
CREATE TABLE vocabulary (
    word_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '单词唯一标识ID，自增长',
    japanese VARCHAR(100) NOT NULL COMMENT '日语单词或短语',
    reading VARCHAR(100) NOT NULL COMMENT '单词的假名读法',
    meaning VARCHAR(255) NOT NULL COMMENT '单词的中文含义',
    part_of_speech VARCHAR(50) COMMENT '词性(动词、名词、形容词等)',
    jlpt_level ENUM('N5', 'N4', 'N3', 'N2', 'N1') COMMENT '单词对应的JLPT等级',
    category VARCHAR(50) COMMENT '单词分类(日常用语、商务用语等)',
    example_sentence TEXT COMMENT '单词的例句(日语)',
    example_translation TEXT COMMENT '例句的中文翻译',
    memory_tip TEXT COMMENT '记忆技巧或联想方法',
    added_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '单词添加时间'
);
-- 用户单词学习记录表 COMMENT='记录用户对每个单词的学习进度和记忆状态'
CREATE TABLE user_vocab_progress (
    progress_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '学习记录ID，自增长',
    user_id INT NOT NULL COMMENT '关联的用户ID',
    word_id INT NOT NULL COMMENT '关联的单词ID',
    familiarity_score INT DEFAULT 0 COMMENT '熟悉度分数(0-100)，越高表示越熟悉',
    last_reviewed TIMESTAMP COMMENT '最后一次复习时间',
    next_review_date TIMESTAMP COMMENT '计划下次复习时间',
    review_count INT DEFAULT 0 COMMENT '已复习次数',
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (word_id) REFERENCES vocabulary(word_id),
    CONSTRAINT uk_user_word UNIQUE (user_id, word_id) -- COMMENT '确保每个用户对每个单词只有一条记录'
);
-- 语法点表 COMMENT='存储日语语法知识点及其相关信息'
CREATE TABLE grammar_points (
    grammar_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '语法点唯一ID，自增长',
    grammar_point VARCHAR(100) NOT NULL COMMENT '语法点名称',
    structure VARCHAR(255) NOT NULL COMMENT '语法结构形式',
    meaning VARCHAR(255) NOT NULL COMMENT '语法点的中文解释',
    jlpt_level ENUM('N5', 'N4', 'N3', 'N2', 'N1') COMMENT '语法对应的JLPT等级',
    example_sentence TEXT COMMENT '语法点的例句(日语)',
    example_translation TEXT COMMENT '例句的中文翻译',
    notes TEXT COMMENT '语法点的注意事项或特殊用法'
);
-- 题库表 COMMENT='存储各类测试题目基本信息'
CREATE TABLE quiz_questions (
    question_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '题目唯一ID，自增长',
    question_type ENUM('grammar', 'vocabulary', 'reading', 'listening') NOT NULL COMMENT '题目类型(语法、词汇、阅读、听力)',
    question_text TEXT NOT NULL COMMENT '题目内容或问题描述',
    difficulty ENUM('N5', 'N4', 'N3', 'N2', 'N1') COMMENT '题目难度等级',
    grammar_id INT COMMENT '关联的语法点ID(如果是语法题)',
    word_id INT COMMENT '关联的单词ID(如果是词汇题)',
    FOREIGN KEY (grammar_id) REFERENCES grammar_points(grammar_id),
    FOREIGN KEY (word_id) REFERENCES vocabulary(word_id)
);
-- 题目选项表 COMMENT='存储题目的各个选项及其正确性'
CREATE TABLE question_options (
    option_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '选项唯一ID，自增长',
    question_id INT NOT NULL COMMENT '关联的题目ID',
    option_text TEXT NOT NULL COMMENT '选项内容',
    is_correct BOOLEAN DEFAULT FALSE COMMENT '是否为正确答案',
    explanation TEXT COMMENT '选项解析或说明',
    FOREIGN KEY (question_id) REFERENCES quiz_questions(question_id)
);
-- 阅读材料表 COMMENT='存储日语阅读学习材料'
CREATE TABLE reading_materials (
    material_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '阅读材料唯一ID，自增长',
    title VARCHAR(255) NOT NULL COMMENT '阅读材料的标题',
    content TEXT NOT NULL COMMENT '阅读内容的正文',
    difficulty ENUM('N5', 'N4', 'N3', 'N2', 'N1') COMMENT '阅读材料难度等级',
    category VARCHAR(50) COMMENT '材料分类(新闻、故事、文化等)',
    word_count INT COMMENT '材料的字数统计',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '材料创建时间'
);
-- 听力材料表 COMMENT='存储日语听力学习材料和音频信息'
CREATE TABLE listening_materials (
    material_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '听力材料唯一ID，自增长',
    title VARCHAR(255) NOT NULL COMMENT '听力材料的标题',
    audio_file VARCHAR(255) NOT NULL COMMENT '音频文件存储路径',
    transcript TEXT COMMENT '听力内容的文字稿',
    difficulty ENUM('N5', 'N4', 'N3', 'N2', 'N1') COMMENT '听力材料难度等级',
    category VARCHAR(50) COMMENT '材料分类(对话、新闻、演讲等)',
    duration INT COMMENT '音频时长(秒)',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '材料创建时间'
);
-- 用户学习进度表 COMMENT='记录用户每日的学习活动和进度'
CREATE TABLE user_progress (
    progress_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '进度记录ID，自增长',
    user_id INT NOT NULL COMMENT '关联的用户ID',
    date DATE NOT NULL COMMENT '学习日期',
    vocab_studied INT DEFAULT 0 COMMENT '当日学习的单词数量',
    grammar_studied INT DEFAULT 0 COMMENT '当日学习的语法点数量',
    quizzes_completed INT DEFAULT 0 COMMENT '当日完成的测试题数量',
    reading_completed INT DEFAULT 0 COMMENT '当日完成的阅读材料数量',
    listening_completed INT DEFAULT 0 COMMENT '当日完成的听力练习数量',
    total_study_time INT COMMENT '当日总学习时长(分钟)',
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT uk_user_date UNIQUE(user_id, date) -- COMMENT '确保每个用户每天只有一条记录'
);
-- 用户答题记录表 COMMENT='记录用户答题的详细结果和表现'
CREATE TABLE user_quiz_results (
    result_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '答题记录ID，自增长',
    user_id INT NOT NULL COMMENT '关联的用户ID',
    question_id INT NOT NULL COMMENT '关联的题目ID',
    selected_option_id INT COMMENT '用户选择的选项ID',
    is_correct BOOLEAN COMMENT '用户回答是否正确',
    time_spent INT COMMENT '答题耗时(秒)',
    answered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '答题时间',
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (question_id) REFERENCES quiz_questions(question_id),
    FOREIGN KEY (selected_option_id) REFERENCES question_options(option_id)
);



-- 用户表索引
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_level ON users(current_level);

-- 单词表索引
CREATE INDEX idx_vocab_japanese ON vocabulary(japanese);
CREATE INDEX idx_vocab_level ON vocabulary(jlpt_level);
CREATE INDEX idx_vocab_category ON vocabulary(category);

-- 用户单词进度索引
CREATE INDEX idx_user_vocab_user ON user_vocab_progress(user_id);
CREATE INDEX idx_user_vocab_review ON user_vocab_progress(next_review_date);

-- 题目索引
CREATE INDEX idx_questions_type ON quiz_questions(question_type);
CREATE INDEX idx_questions_difficulty ON quiz_questions(difficulty);

-- 用户进度索引
CREATE INDEX idx_user_progress_date ON user_progress(date);