import request from '@/utils/request'
// 登录
export function login(postData) {
  return request({
    url: '/login',
    method: 'post',
    data: postData
  })
}


export function certCheck() {
  return request({
    url: '/certCheck',
    method: 'post',
    data: {}
  })
}
