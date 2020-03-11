<template>
    <div>
        <el-container>
            <el-header class="homeHeader">
                <div class="title">微人事</div>
                <el-dropdown class="userInfo" @command="commandHandler">
  <span class="el-dropdown-link">
<!--    {{user.name}}<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>-->
    username<i class="el-icon-arrow-down el-icon--right"></i>
  </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="userInfo">user account</el-dropdown-item>
                        <el-dropdown-item command="donationInfo">donation info</el-dropdown-item>
                        <el-dropdown-item divided command="signOut">sign out</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-header>

            <el-container>
                <el-aside width="200px">
                    <el-menu @select="menuClick">
                        <el-menu-item index="Home">
                            <i class="el-icon-menu"></i>
                            <span slot="title">Home</span>
                        </el-menu-item>

                        <el-submenu index="2">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>news</span>
                            </template>
                            <el-menu-item index="Announcement">announcement</el-menu-item>
                            <el-menu-item index="CharityNews">charity news</el-menu-item>
                            <el-menu-item index="Policies">policies and regulations</el-menu-item>
                        </el-submenu>

                        <el-submenu index="3">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>get involved</span>
                            </template>
                            <el-menu-item index="Donate">donate</el-menu-item>
                            <el-menu-item index="Fundraise">fundraise for us</el-menu-item>
                        </el-submenu>

                        <el-submenu index="4">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>about us</span>
                            </template>
                            <el-menu-item index="CharityInfo">our people</el-menu-item>
                            <el-menu-item index="OurPeople">charity info</el-menu-item>
                        </el-submenu>

                        <el-menu-item index="Contact">
                            <i class="el-icon-setting"></i>
                            <span slot="title">contact us</span>
                        </el-menu-item>

                    </el-menu>

                </el-aside>
                <el-main>
                    <router-view/>
                    <template>
                        <el-carousel :interval="4000" type="card" height="bannerHeight+'px'">
                            <el-carousel-item v-for="item in imagesbox" :key="item.id">
                                <el-row>
                                    <el-col :span="24" class="banner_img">
                                        <img ref="bannerHeight" :src="item.idView" class="bannerImg" @load="imgLoad">
                                    </el-col>
                                </el-row>
                                <!--              <el-carousel-item v-for="item in 6" :key="item">-->
                                <h3 class="medium">{{ item }}</h3>
                            </el-carousel-item>
                        </el-carousel>
                    </template>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Home",
        data() {
            return {
                user: JSON.parse(window.sessionStorage.getItem("user")),
                bannerHeight: "",
                imagesbox: [{id: 0, idView: require("../assets/images/car (1).jpg")},
                    {id: 1, idView: require("../assets/images/car (2).jpg")},
                    {id: 2, idView: require("../assets/images/car (3).jpg")},
                    {id: 3, idView: require("../assets/images/car (4).jpg")},
                    {id: 4, idView: require("../assets/images/car (5).jpg")}],
                note: {
                    backgroundImage: "url(" + require("../assets/images/car (2).jpg") + ") ",
                    backgroundPosition: "center center",
                    backgroundRepeat: "no-repeat",
                    backgroundSize: "cover"
                }
            }
        },
        mounted(){
            this.imgLoad();
            window.addEventListener('resize', ()=> {
                this.bannerHeight = this.$refs.bannerHeight[0].height
                this.imgLoad();
            },false)
        },
        methods: {
            commandHandler(cmd) {
                if (cmd == 'logout') {
                    this.$confirm('Whether to log out of the account?', 'prompt', {
                        confirmButtonText: 'Yes',
                        cancelButtonText: 'No',
                        type: 'warning'
                    }).then(() => {
                        this.getRequest("/logout");
                        window.sessionStorage.removeItem("user")
                        this.$router.replace("/");
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: 'Cancel log out'
                        });
                    });
                }
            }
        }
    }
</script>

<style>
    .homeHeader{
        background-color: #03e2ff;
        display: flex;
        align-items: center;
        justify-content:space-between;
        padding: 0px 15px;
        box-sizing:border-box;
    }
    .homeHeader .title{
        font-size: 30px;
        font-family: Calibri;
        color: aliceblue;
    }
    .homeHeader .userInfo{
        cursor: pointer;
    }
    .el-carousel__item h3 {
        color: #475669;
        font-size: 15px;
        opacity: 0.25;
        line-height: 200px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }
    img{
        height: 100%;
        width: 100%;
        object-fit: contain;
    }
</style>