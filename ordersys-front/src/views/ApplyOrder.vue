<template>
    <div>
        <div>
            <el-row>
                <el-col :span="16">
                    <div style="text-align: left;margin-top: -20px"><h2>加班订餐申请</h2></div>
                </el-col>
                <el-col :span="8">
                    <div>
                        <el-button type="primary" @click="saveOrder">提交订单</el-button>
                    </div>
                </el-col>
            </el-row>
        </div>
        <el-divider></el-divider>
        <div style="text-align: left;margin-top: -20px"><h4>订餐信息</h4></div>
        <el-form :inline="true" ref="Form" :model="orderedForm" label-width="80px" size="mini" :rules="rules">
            <el-form-item label="订餐人">
                <el-input v-model="orderedForm.applicant" readonly></el-input>
            </el-form-item>
            <br>
            <el-form-item label="订餐人员"  prop="enames" >
                <el-input v-model="orderedForm.enames" readonly></el-input>
            </el-form-item>
            <el-button type="primary" @click="openDialog()">选择</el-button>
            <el-form-item label="订餐人数">
            <el-input v-model="changeEcount" readonly></el-input>
        </el-form-item>
            <br>
            <el-form-item label="订餐数量">
            <el-input v-model="orderedForm.orderCount"></el-input>
        </el-form-item>
            <el-form-item label="订餐类型">
                <el-select v-model="orderedForm.orderType">
                    <el-option label="午餐" value="午餐"></el-option>
                    <el-option label="晚餐" value="晚餐"></el-option>
                </el-select>
            </el-form-item>
            <br>
            <el-form-item label="费用金额">
                <el-input v-model="orderedForm.totalPrice"></el-input>
            </el-form-item>
            <el-form-item label="备注">
                <el-input v-model="orderedForm.comments"></el-input>
            </el-form-item>
            <br>
            <div style="text-align: left;margin-top: -20px"><h4>餐品信息</h4></div>
            <el-form-item label="餐品名称">
                <el-select v-model="orderedForm.odfdList[0].foodId" placeholder="请选择餐品" filterable=""  @change="getPrice1()">
                    <el-option
                            v-for="food in foodList"
                            :key="food.id"
                            :label="food.name"
                            :value="food.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="单价">
                <el-input :readonly="true" v-model="orderedForm.odfdList[0].foodPrice"></el-input>
            </el-form-item>
            <br>
            <el-form-item label="数量">
                <el-input-number v-model="orderedForm.odfdList[0].foodCount"  :min="1" :max="orderedForm.ecount"></el-input-number>
            </el-form-item>
            <el-form-item label="小计">
                <el-input v-model="orderedForm.odfdList[0].foodPrice * orderedForm.odfdList[0].foodCount"></el-input>
            </el-form-item>
            <br>
            <el-form-item label="备注">
                <el-input v-model="orderedForm.odfdList[0].foodComments" style="width: 400px"></el-input>
            </el-form-item>
            <el-divider></el-divider>
            <div style="text-align: right;margin-top: -20px">
                餐品数量：{{getTotalCount}}
                <br>
                总金额：{{getTotalPrice}}
            </div>
        </el-form>



        <el-dialog title="订餐人员选择" :visible.sync="dialogFormVisible">
                <el-transfer
                        filterable
                        filter-placeholder="请输入姓名"
                        @change="getEnames"
                        v-model="orderedForm.eids"
                        :titles="['待选人员', '已选人员']"
                        :data="EmpData">
                </el-transfer>

            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="dialogFormVisible=false">关闭</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import VXETable from 'vxe-table'
    export default {
        name: "ApplyOrder",
        data() {
            return {
                orderedForm: {
                    id:0,
                    deptname:'',
                    time:'',
                    applicant:sessionStorage.getItem("uname"),
                    applicantId:sessionStorage.getItem("uid"),
                    eids:[],
                    enames:'',
                    ecount:0,
                    orderCount:0,
                    orderType:'午餐',
                    totalPrice:0,
                    comments:'',
                    state:'待部门提交',
                    odfdList:[
                        {foodId:'',foodCount:0,foodPrice:0,foodComments:''}
                    ],
                },
                foodList:[],    //获取菜单
                EmpList:[], //本部门下的所有员工列表
                dialogFormVisible:false,
                EmpData:[], //穿梭框数据
                rules:{
                    enames:[
                        { required: true, message:"请选择人员", trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {

            getFoodList(){
                this.$http.get("/getFoodList").then(res => {
                    this.foodList = res.data
                    this.getFirstFood()
                })
            },

            //获取本部门下的所有员工
            getEmpList(){
                this.$http.get("/getEmpList/"+sessionStorage.getItem("deptId")).then(res => {

                    this.EmpList = res.data
                    for (let i = 0; i < this.EmpList.length; i++) {
                        this.EmpData.push({
                            label: this.EmpList[i].name,
                            key: this.EmpList[i].id,
                        });
                    }
                })
            },

            //根据所选菜品获得单价信息
            getPrice1(){
                for (let i = 0; i < this.foodList.length; i++) {
                    if (this.orderedForm.odfdList[0].foodId == this.foodList[i].id ) {
                        this.orderedForm.odfdList[0].foodPrice = this.foodList[i].price
                    }
                }
            },


            openDialog(){
                this.dialogFormVisible = true
            },
           /* changeEcount(){
                debugger
                this.orderedForm.ecount = this.orderedForm.ename.length
            }*/

           saveOrder(){
               this.$refs['Form'].validate((valid) => {
                   if(valid){
                       this.$http.post("/ordering",this.orderedForm).then(res => {
                           if (res.data == 'success'){
                               this.$router.push({
                                   path:'/myOrdered'
                               })
                               this.$message.success("点餐成功")
                           }else {
                               this.$message.warning("点餐失败")
                           }
                       })
                   }
               })
           },

            getFirstFood(){
                this.orderedForm.odfdList[0].foodId =  this.foodList[0].id
                this.orderedForm.odfdList[0].foodPrice = this.foodList[0].price
            },

/*          //获取订单详情
            getOrderId(){
               this.orderedForm.id = this.$route.query.id
            },

            getOrder(){

            },*/

            getEnames(){
               let self = this
                this.orderedForm.enames = ''
                for (let i = 0; i < this.orderedForm.eids.length; i++) {
                    for (let j = 0; j < this.EmpList.length; j++) {
                        if(this.EmpList[j].id == this.orderedForm.eids[i]){

                            this.orderedForm.enames += this.EmpList[j].name+' '
                            break
                        }
                    }
                }
            }
        },
        computed:{
            getTotalCount(){

                let totalCount = '';
                for (let i = 0; i < this.orderedForm.odfdList.length; i++) {
                    totalCount += this.orderedForm.odfdList[i].foodCount
                }
                this.orderedForm.orderCount = totalCount
                return totalCount
            },
            getTotalPrice(){
                let totalPrice = '';
                for (let i = 0; i < this.orderedForm.odfdList.length; i++) {
                    totalPrice += this.orderedForm.odfdList[i].foodCount * this.orderedForm.odfdList[i].foodPrice
                }
                this.orderedForm.totalPrice = totalPrice
                return totalPrice
            },
            changeEcount(){
                this.orderedForm.ecount = this.orderedForm.eids.length
                return this.orderedForm.ecount
            }
        },

        created() {
            this.getFoodList()
            this.getEmpList()
        },
        mounted() {
        }
    }
</script>

<style scoped>

</style>