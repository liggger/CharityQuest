import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Home from "../views/Home";
import Contact from "../views/contact/Contact";
import Donate from "../views/get involved/Donate";
import Projects from "../views/get involved/Projects";
import CharityInfo from "../views/about us/CharityInfo";
import OurPeople from "../views/about us/OurPeople";
import Announcement from "../views/news/Announcement";
import CharityNews from "../views/news/CharityNews";
import Activity from "../views/news/Activity";
import SendMessage from "../views/user/SendMessage";
import UserInfo from "../views/user/UserInfo";
import Register from "../views/user/Register";
import SendEmail from "../views/user/SendEmail";
import Publish from "../views/get involved/Publish";
import ApplyCharity from "../views/user/ApplyCharity";
import ProjectDetail from "../views/get involved/ProjectDetail";
import DonationInfo from "../views/user/DonationInfo";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
    hidden: true
  },{
    path: '/home',
    name: '',
    component: Home,
    children:[
      {
        path: '/donate',
        name: 'donate',
        component: Donate,
      },
      {
        path: '/publish',
        name: 'publish',
        component: Publish,
      },
      {
        path: '/charityinfo',
        name: 'Charity Info',
        component: CharityInfo,
      },
      {
        path: '/projects',
        name: 'Projects',
        component: Projects,
      },
      {
        path: '/ourpeople',
        name: 'ourpeople',
        component: OurPeople,
      },
      {
        path: '/announcement',
        name: 'announcement',
        component: Announcement,
      },
      {
        path: '/charitynews',
        name: 'charitynews',
        component: CharityNews,
      },
      {
        path: '/activity',
        name: 'activity',
        component: Activity,
      },
      {
        path: '/contact',
        name: 'contact',
        component: Contact,
      },
      {
        path: '/sendmessage',
        name: 'sendmessage',
        component: SendMessage,
      },
      {
        path: '/sendemail',
        name: 'sendemail',
        component: SendEmail,
      },
      {
        path: '/userinfo',
        name: 'userinfo',
        component: UserInfo,
      },
      {
        path: '/donationinfo',
        name: 'Donation Info',
        component: DonationInfo,
      },
      {
        path: '/register',
        name: 'register',
        component: Register,
      },
      {
        path: '/applycharity',
        name: 'applycharity',
        component: ApplyCharity,
      },
      {
        path: '/projectdetail',
        name: 'Project Detail',
        component: ProjectDetail,
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
