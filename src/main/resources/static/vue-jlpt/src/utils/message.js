// 消息提示工具
export function showMessage(message, type = 'info') {
  // 可以使用 Element UI、Ant Design 或自定义提示
  if (typeof window !== 'undefined' && window.$message) {
    window.$message[type](message);
  } else {
    console[type === 'error' ? 'error' : 'log'](message);
  }
}

export function showLoading() {
  // 显示加载状态
}

export function hideLoading() {
  // 隐藏加载状态
}