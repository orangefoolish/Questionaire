import { createRouter, createWebHistory } from 'vue-router'
import Questionaire from "../components/student/Questionaire";
import Register from "../views/Login";
import Student from "../views/Student";
import teacher from "@/views/Teacher";
import QuestionEdit from "@/views/QuestionEdit";
import result from "../views/Result";
const routes = [
  {
    path:'/questionaire',
    name:'questionaire',
    component:Questionaire
  },
  {
    path:'',
    name:'Register',
    component: Register
  },
  {
    path: '/student',
    name:'student',
    component: Student
  },
  {
    path:'/teacher',
    name:'teacher',
    component: teacher
  },
  {
    path:'/qsedit',
    name:"QuestionEdit",
    component: QuestionEdit
  },
  {
    path:'/result',
    name:"result",
    component: result
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
