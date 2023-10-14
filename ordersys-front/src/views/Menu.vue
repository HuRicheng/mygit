<template>
    <div style="height:99%">
        <el-container>
            <el-header height="90px">
                <h1>欢迎登录订餐系统</h1>
                <div style="float: right">
                    欢迎您，{{username}}
                    <el-button type="primary" @click="logout">退出</el-button>
                </div>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <!--左侧导航栏-->
                    <!--:router="true"开始路由模式：当点击菜单，会将具体菜单的index属性，作为浏览器要访问的路径-->
                    <!--default-active是让某个菜单高亮显示-->
                    <el-menu
                            :default-active="activeMenu"
                            class="el-menu-vertical-demo"
                            :router="true">
                        <el-menu-item v-for="m in menuList" :key="m.id" :index="m.path">
                            {{m.title}}
                        </el-menu-item>
                    </el-menu>

                </el-aside>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Menu",
        data(){
            return{
                username:sessionStorage.getItem("uname"),
                role:sessionStorage.getItem("role"),
                menuList:[],
                activeMenu:''
            }
        },
        methods:{
            getMenu(){
                this.$http.get("/getMenus/"+this.role).then(res => {
                    this.menuList = res.data
                })
            },
            logout(){
                //清除用户信息
                sessionStorage.clear()
                //跳转到登录页面
                this.$router.push({path:'/'})
            }
        },

        created() {
            this.getMenu()
            console.log(this.menuList)
        },

        beforeRouteEnter(to,from,next){
            let toPath = to.path
            next( vm => {
                //vm就是下一个页面的this对象
                vm.activeMenu = toPath
            })
        }
    }
</script>

<style scoped>
    /deep/.el-table {
        background-color: #E9EEF3
    }

    /deep/.el-table thead tr>th{
        background-color: lightblue;
    }

    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 0px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
</style>