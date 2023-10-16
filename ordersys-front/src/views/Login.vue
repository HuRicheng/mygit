<template>
    <div align="center">
        <h3>订餐管理系统</h3>
        <div style="width: 500px">
            <el-form ref="form" :model="loginForm" label-width="80px">
                <el-form-item label="用户名">
                    <el-input v-model="loginForm.username"></el-input>
                </el-form-item>

                <el-form-item label="密码">
                    <el-input type="password" v-model="loginForm.password"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="onSubmit">登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>

    export default {
        name: "Login",
        data(){
            return{
                loginForm:{
                    username:'',
                    password:''
                },
                toPath:''
            }
        },
        methods:{
            onSubmit(){
                //将用户数据提交到后台实现登录校验
                this.$http.post('/login',this.loginForm).then(res => {
                    //res代表后台响应回来的内容
                    debugger
                    if (res.data.id == null){
                        this.$message.error("用户名或密码错误")
                    }else {
                        sessionStorage.setItem("uid",res.data.id)
                        sessionStorage.setItem("uname",res.data.name)
                        sessionStorage.setItem("role",res.data.role)
                        sessionStorage.setItem("deptId", res.data.deptId)

                        if(res.data.role == 2 ){
                            this.toPath = '/myOrdered'
                        }else if(res.data.role == 1 || res.data.role == 0){
                            this.toPath = '/manager'
                        }
                        this.$router.push({
                            path:this.toPath
                        })
                    }
                }).catch(res => {
                    //后台执行错误后回调的方法
                })
            }
        }
    }
</script>

<style scoped>

</style>