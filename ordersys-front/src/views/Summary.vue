<template>
    <div>
        <el-row>
            <el-col :span="16">
                <el-form :inline="true" :model="queryData" class="demo-form-inline">
                    <el-form-item label="申请日期">
                        <el-date-picker
                                v-model="queryData.dateStart"
                                align="right"
                                type="date"
                                placeholder="选择日期"
                                value-format="yyyy-MM-dd"
                                :picker-options="pickerOptions">
                        </el-date-picker>
                        至
                        <el-date-picker
                                v-model="queryData.dateEnd"
                                align="right"
                                type="date"
                                placeholder="选择日期"
                                value-format="yyyy-MM-dd"
                                :picker-options="pickerOptions">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="订餐人">
                        <el-input v-model="queryData.applicantName"></el-input>
                    </el-form-item>
                    <el-form-item label="申请编号">
                        <el-input v-model="queryData.oid"></el-input>
                    </el-form-item>
                    <el-form-item label="当前状态">
                        <el-select v-model="queryData.state" clearable>
                            <el-option
                                    v-for="item in states"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-button type="primary" @click="query">查询</el-button>
                     </el-form>
            </el-col>
        </el-row>

        <el-table
                :data="orderedData.slice((queryData.page-1)*queryData.limit,queryData.page*queryData.limit)"
                border
                stripe
                tooltip-effect="dark"
                style="width: 100%"
                height="550px"
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
                    prop="time"
                    label="申请时间"
                    show-overflow-tooltip>

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
        name: "Summary",
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
                states:[
                    {
                        value: '待部门提交',
                        label: '待部门提交'
                    }, {
                        value: '待系统确认',
                        label: '待系统确认'
                    },{
                        value: '已完成',
                        label: '已完成'
                    }
                ],

                queryData: { //查询条件
                    page: 1,
                    limit: 5,
                    dateStart: '',
                    dataEnd:'',
                    applicantName:'',
                    oid:'',
                    state:'',
                },
                total: 0, //总条数
                orderedData:[], //表格数据来源
            }
        },
        methods:{
            /**
             * 表格数据加载
             */
            tableLoad() {
                //调用后台，查询个人订餐记录
                this.$http.post('/getOrderWithAllList', this.queryData).then(res => {
                    this.orderedData = res.data.data
                    this.total = res.data.count
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
            //查询动作
            query(){
                this.queryData.page = 1
                this.tableLoad()
            },
            //行点击事件
            handle(row,column,event,cell){
                if(row[column.property] !== undefined){
                    if (row[column.property] === '0'){
                        return this.$message.error('没有数据')
                    } else {
                        /*    console.log(row)
                            console.log(column)*/
                        const orderid = row.id
                        alert(orderid)
                        this.$router.push({
                            path:'/applyOrder',
                            query:{
                                orderid: orderid
                            }
                        })
                    }
                }
            },
        },

        created() {
            //加载表格数据
            this.tableLoad()
        },
    }
</script>

<style scoped>

</style>