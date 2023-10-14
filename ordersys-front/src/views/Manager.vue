<template>
    <div>
        <el-row>
            <el-col :span="15">
                <el-form :inline="true" :model="queryData" class="demo-form-inline">
                    <el-form-item label="订餐日期">
                        <el-date-picker
                                v-model="queryData.dateOne"
                                align="right"
                                type="date"
                                placeholder="选择日期"
                                value-format="yyyy-MM-dd"
                                :clearable="false"
                                :editable="false"
                                :picker-options="pickerOptions">
                        </el-date-picker>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="query">查询</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="9">
                <el-button type="primary" @click="updateOrder">提交</el-button>
            </el-col>
        </el-row>

        <el-form :model="totalForm" label-width="100px">
            <el-form-item label="日期">
                <el-input v-model="queryData.dateOne" readonly=""></el-input>
            </el-form-item>
            <el-form-item label="总订餐数量">
                <el-input v-model="getTotalCount"></el-input>
            </el-form-item>
            <el-form-item label="总订餐金额">
                <el-input v-model="getTotalPrice"></el-input>
            </el-form-item>
            <el-form-item label="餐品明细">
                <el-input v-model="totalForm.totalFood"></el-input>
            </el-form-item>
        </el-form>

        <el-table
                ref="multipleTable"
                :data="orderedData.slice((queryData.page-1)*queryData.limit,queryData.page*queryData.limit)"
                border
                stripe
                tooltip-effect="dark"
                style="width: 100%"
                height="360px"
                @selection-change="handleSelectionChange"
                @cell-click="handle">
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
            <el-table-column
                    type="index"
                    label="序号"
                    width="55"
                    align="center"
            >

            </el-table-column>
            <el-table-column
                    prop="id"
                    label="申请编号"
                    width="120"
                    align="center">

            </el-table-column>
            <el-table-column
                    prop="dname"
                    label="费用部门"
                    width="120">

            </el-table-column>
            <el-table-column
                    prop="empList"
                    label="订餐人员"
                    show-overflow-tooltip>
                <template slot-scope="scope">
                    <span v-for="item in scope.row.empList">{{item.name}} </span>
                </template>
            </el-table-column>
            <el-table-column
                    prop="orderCount"
                    label="订餐数量"
                    show-overflow-tooltip>

            </el-table-column>
            <el-table-column
                    prop="totalPrice"
                    label="费用金额"
                    show-overflow-tooltip>

            </el-table-column>
            <el-table-column
                    prop="state"
                    label="当前状态"
                    show-overflow-tooltip>

            </el-table-column>
            <el-table-column
                    prop="comments"
                    label="备注"
                    show-overflow-tooltip>
            </el-table-column>
        </el-table>
        <!--layout用于设置分页栏的显示-->
        <!--page-sizes用于设置可选的每页显示条数-->
        <el-pagination
                align="right"
                background
                layout="prev, pager, next, sizes,total"
                :page-sizes="[1,5,10,15]"
                :total="total"
                :current-page="queryData.page"
                :page-size="queryData.limit"
                @size-change="sizeChange"
                @current-change="currentChange">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        name: "Manager",
        data() {
            return {
                //快捷日期选择器选项
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() > Date.now();
                    },
                    shortcuts: [{
                        text: '今天',
                        onClick(picker) {
                            picker.$emit('pick', new Date());
                        }
                    }, {
                        text: '昨天',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24);
                            picker.$emit('pick', date);
                        }
                    }, {
                        text: '一周前',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', date);
                        }
                    }]
                },
                queryData: { //查询条件
                    page: 1,
                    limit: 5,
                    dateOne: '',
                    deptId: sessionStorage.getItem('deptId'),
                    state:''
                },
                total: 0, //总条数
                orderedData:[], //表格数据来源
                checkData:[],  //选中项
                totalForm:{
                    date:'',
                    totalCount:'',
                    totalPrice:'',
                    totalFood:''
                }
            }
        },
        methods:{
            /**
             * 表格数据加载
             */
            tableLoad() {
                this.$http.post('/getOrderWithAllList', this.queryData).then(res => {
                    this.orderedData = res.data.data
                    this.total = res.data.count
                    this.getTotalFood()
                })
            },

            /**
             * 每页显示条数发生变化时触发
             * @param val
             */
            sizeChange(val) {
                //val代表当前选择的每页显示条数
                this.queryData.page = 1
                this.queryData.limit = val
            },

            /**
             * 切换页码时触发
             * @param val
             */
            currentChange(val) {
                //val代表当前页码
                this.queryData.page = val
            },

            //选中项发生变化时触发
            handleSelectionChange(val){
                //val代表当前选中项的数据
                if(sessionStorage.getItem("role") == 1){
                    if (val[val.length-1].state != '待部门提交'){
                        this.$message.warning("您只能选择待部门提交的订单提交")
                        return
                    }
                    this.checkData = val
                }else if(sessionStorage.getItem("role") == 0){
                    if (val[val.length-1].state != '待系统确认'){
                        this.$message.warning("您只能选择未提交的订单提交")
                        return
                    }
                    this.checkData = val
                }
            },

            //查询动作
            query(){
                this.queryData.page = 1
                this.tableLoad()
            },

            //获得当前时间
            getNowTime() {
                var now = new Date();
                var year = now.getFullYear(); //得到年份
                var month = now.getMonth(); //得到月份
                var date = now.getDate(); //得到日期
                month = month + 1;
                month = month.toString().padStart(2, "0");
                date = date.toString().padStart(2, "0");
                var defaultDate = `${year}-${month}-${date}`;
                this.$set(this.queryData, "dateOne", defaultDate);
            },

            //计算食物总计
            getTotalFood(){
                this.totalForm.totalFood = ''
                const self = this
                let fTypes = []
                let foods = []
                //共几种食物
                for (let i = 0; i < self.orderedData.length; i++) {
                    for (let j = 0; j < self.orderedData[i].odfdList.length; j++){
                        if ((fTypes.findIndex((v) => (v == self.orderedData[i].odfdList[j].foodId)) == -1)){
                            fTypes.push(self.orderedData[i].odfdList[j].foodId)
                            foods.push({'foodId': self.orderedData[i].odfdList[j].foodId, 'foodName': self.orderedData[i].odfdList[j].foodName})
                        }
                    }
                }
                console.log(foods)
                let fTotal = []
                //每次计算一种食物
                for (let i = 0; i < fTypes.length; i++) {
                    let count = 0

                    for (let j = 0; j < self.orderedData.length; j++) {
                        for (let k = 0; k < self.orderedData[j].odfdList.length; k++) {
                            if(fTypes[i] == self.orderedData[j].odfdList[k].foodId){
                                count += self.orderedData[j].odfdList[k].foodCount
                            }
                        }
                    }
                    fTotal.push({'foodId': foods[i].foodId, 'foodName': foods[i].foodName, 'foodCount': count})
                }
                console.log(fTotal)
                for (let i = 0; i < fTotal.length; i++) {
                    let foodinfo = fTotal[i].foodName + '共' + fTotal[i].foodCount + '份 '
                    self.totalForm.totalFood += foodinfo
                }


            },

            //更新订单状态
            updateOrder(){
                if (this.checkData.length == 0){
                    this.$message.warning("请选择要提交的订单")
                    return
                }

                let orderids = []

                for (let i = 0; i<this.checkData.length;i++){
                    orderids.push(this.checkData[i].id)
                }
                let toPath = ''
                if (sessionStorage.getItem("role") == 1){
                    toPath = '/managerConfirm'
                } else {
                    toPath = '/systemConfirm'
                }
                this.$http.post(toPath,orderids).then(res => {
                    if (res.data == 'success'){
                        this.$message.success("提交成功")
                        this.tableLoad()
                    }else {
                        this.$message.warning("提交失败")
                    }
                })
            },

            changeState(){
                if(sessionStorage.getItem("role") == 1){
                    this.queryData.state = '待部门提交'
                    return
                }
                if(sessionStorage.getItem("role") == 0 ){
                    this.queryData.state = '待系统确认'
                    return;
                }
            }
        },
        computed:{
            getTotalCount(){
                let totalCount = 0;
                for (let i = 0; i < this.orderedData.length; i++) {
                    totalCount += this.orderedData[i].orderCount
                }
                this.totalForm.totalCount = totalCount
                return totalCount
            },
            getTotalPrice(){
                let totalPrice = 0;
                for (let i = 0; i < this.orderedData.length; i++) {
                    totalPrice += this.orderedData[i].totalPrice
                }
                this.totalForm.totalPrice = totalPrice
                return totalPrice
            },
        },
        created() {
            this.getNowTime()
            this.changeState()
            //加载表格数据
            this.tableLoad()
        },
    }
</script>

<style scoped>

</style>