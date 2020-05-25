import Vue from 'vue'
import Router from 'vue-router'
// import App from '../App'
import index from '../components/index'
import BookManage from '../components/BookManage'
import AddBook from '../components/AddBook'
import updateBook from '../components/updateBook'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '图书管理',
      component: index,
      redirect:"/BookManage",
      children:[
        {
          path: '/BookManage',
          name: '查询图书',
          component: BookManage
        },
        {
          path: '/AddBook',
          name: '添加图书',
          component: AddBook
        },
        {
          path: '/updateBook',
          name: '修改信息',
          component: updateBook
        }
      ]
    }
  ]
})
