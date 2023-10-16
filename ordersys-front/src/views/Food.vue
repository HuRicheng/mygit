<template>
    <div>
        <el-row>
            <el-col :span="15">
                <el-form :inline="true" :model="queryData" class="demo-form-inline">
                    <el-form-item label="餐品名称">
                        <el-input v-model="queryData.foodName"></el-input>
                    </el-form-item>
                    <el-form-item label="用户是否可见">
                        <el-select v-model="queryData.isVisible" clearable>
                            <el-option
                                    v-for="item in states"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="query">查询</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="9">
                <el-button type="primary" @click="openDialog">新增</el-button>
                <el-button type="primary" @click="updateFood">修改</el-button>
            </el-col>
        </el-row>

        <el-table
                ref="multipleTable"
                :data="foodData"
                border
                stripe
                tooltip-effect="dark"
                style="width: 100%"
                height="610px"

                @selection-change="handleSelectionChange"
               >
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
                    prop="name"
                    label="餐品名称"
                >

            </el-table-column>
            <el-table-column
                    prop="price"
                    label="单价"
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="visibility"
                    label="是否可见"
                    show-overflow-tooltip>
                <template slot-scope="{ row }">
                    {{ Number(row.visibility) === 1 ? '可见' : '不可见' }}
                </template>
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

        <!--新增菜品的表单对话框-->
        <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible">
            <!--rules代表的是校验规则-->
            <el-form ref="fForm" :model="foodForm" :rules="rules" :label-width="formLabelWidth">
                <el-form-item label="餐品名称"  prop="name">
                    <el-input v-model="foodForm.name" autocomplete="off" :readonly="isUpdate"></el-input>
                </el-form-item>
                <el-form-item label="单价" align="left" prop="price">
                    <el-input v-model="foodForm.price" autocomplete="off" :readonly="isUpdate"></el-input>
                </el-form-item>
                <el-form-item label="用户可见" >
                    <el-select v-model="foodForm.visibility" clearable>
                        <el-option
                                v-for="item in states"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveFood">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "Food",
        data(){
            return{
                queryData: { //查询条件
                    page: 1,
                    limit: 5,
                    foodName:'',
                    isVisible:'',
                },
                foodData:[],
                total: 0, //总条数

                states:[
                    {
                        value: 2,
                        label: '不可见'
                    }, {
                        value: 1,
                        label: '可见'
                    },
                ],

                foodForm:{//餐品的全部信息
                    id:'',
                    name:'',
                    price:'',
                    visibility:1,
                },
                dialogFormVisible:false, //表单的可见性
                formLabelWidth:'100px', //文本占的宽度
                dialogTitle:'',
                rules: {
                    name:[
                        {required:true,message:'请输入餐品名称',trigger:'blur'}
                    ],
                    price:[
                        {required:true,message:'请输入价格',trigger:'blur'}
                    ],
                },
                checkData:[],
                isUpdate:false,
            }
        },
        methods: {
            /**
             * 表格数据加载
             */
            tableLoad() {
                this.$http.post('/getFoodListWithQuery', this.queryData).then(res => {
                    this.foodData = res.data.data
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
                this.tableLoad()
            },

            /**
             * 切换页码时触发
             * @param val
             */
            currentChange(val) {
                //val代表当前页码
                this.queryData.page = val
                this.tableLoad()
            },
            //选中项发生变化时触发
            handleSelectionChange(val){
                //val代表当前选中项的数据
                console.log(val)
                this.checkData = val
            },

            //查询动作
            query(){
                this.queryData.page = 1
                this.tableLoad()
            },

            /**
             * 新增打开弹出框
             */
            openDialog(){
                //修改标题
                this.dialogTitle = '新增餐品'
                this.isUpdate = false
                this.dialogFormVisible = true
                //清空校验信息
                this.reSetForm()
                //清空表单数据
                this.foodForm ={
                    id:null,
                    name:'',
                    price:'',
                    visibility:1,
                }

            },

            /**
             * 重置表单，清空提示信息
             */
            reSetForm(){
                if (this.$refs['fForm'] != undefined){
                    this.$refs['fForm'].resetFields()
                }
            },


            /**
             * 新增/修改餐品信息
             * @param row
             */
            saveFood(){
                this.$refs['fForm'].validate(valid => {
                    if(valid){
                        this.$http.post('/saveOrUpdateFood',this.foodForm).then(res => {
                            if(res.data == 'success'){
                                this.dialogFormVisible = false
                                this.tableLoad()
                                if (this.dialogTitle == '新增餐品'){
                                    this.$message.success("新增成功")
                                } else {
                                    this.$message.success("修改成功")
                                }
                            }
                        }).catch(res => {
                            this.$message.error("服务器出错")
                        })
                    }
                })
            },

            updateFood(){
                    //判断用户是否只勾选了一条数据
                    if (this.checkData.length > 1 || this.checkData.length == 0){
                        this.$message.warning("请选择一条餐品信息进行修改")
                        return
                    }

                    this.foodForm = this.checkData[0]

                    //修改标题信息
                    this.dialogTitle = "修改餐品"
                    //清空校验信息
                    this.reSetForm()
                    this.isUpdate = true
                    this.dialogFormVisible = true
            },

        },
        created() {
            this.tableLoad()
        }
    }
</script>

<style scoped>

</style>