import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Home from "../views/Home";
import Contact from "../views/contact/Contact";
import Donate from "../views/get involved/Donate";
import Fundraise from "../views/get involved/Fundraise";
import CharityInfo from "../views/about us/CharityInfo";
import OurPeople from "../views/about us/OurPeople";
import Announcement from "../views/news/Announcement";
import CharityNews from "../views/news/CharityNews";
import Policies from "../views/news/Policies";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
    hidden: true
  },{
    path: '/home',
    name: '导航一',
    component: Home,
    children:[
      {
        path: '/donate',
        name: 'Donate',
        component: Donate,
      },
      {
        path: '/fundraise',
        name: 'Fundraise',
        component: Fundraise,
      },
      {
        path: '/charityinfo',
        name: 'CharityInfo',
        component: CharityInfo,
      },
      {
        path: '/fundraise',
        name: 'Fundraise',
        component: Fundraise,
      },
      {
        path: '/ourpeople',
        name: 'OurPeople',
        component: OurPeople,
      },
      {
        path: '/announcement',
        name: 'Announcement',
        component: Announcement,
      },
      {
        path: '/charitynews',
        name: 'CharityNews',
        component: CharityNews,
      },
      {
        path: '/policies',
        name: 'Policies',
        component: Policies,
      },
      {
        path: '/contact',
        name: 'Contact',
        component: Contact,
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
