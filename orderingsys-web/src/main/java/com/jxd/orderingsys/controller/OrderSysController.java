package com.jxd.orderingsys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxd.orderingsys.model.*;
import com.jxd.orderingsys.service.*;
import com.jxd.orderingsys.vo.OrderWithAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderSysController
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/9 8:39
 * @Version 1.0
 */
@RestController
public class OrderSysController {
    @Autowired
    private IUserLoginService userLoginService;
    @Autowired
    private IOrderedService orderedService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IFoodService foodService;
    @Autowired
    private IEmpService empService;
    @Autowired
    private IOrderedWithOdfdService orderedWithOdfdService;

    /**
     * 登录
     * @param userLogin
     * @return
     */
    @RequestMapping("/login")
    public Emp login(@RequestBody UserLogin userLogin){
        Emp emp = userLoginService.login(userLogin);
        //System.out.println(emp.toString());
        return emp;
    }

    /**
     * 根据角色获取菜单项
     * @param role
     * @return
     */
    @RequestMapping("/getMenus/{role}")
    public List<Menu> getMenus(@PathVariable int role){
        return menuService.getMenu(role);
    }

    /**
     * 批量删除订单
     * @param orderids
     * @return
     */
    @RequestMapping("/delBatch")
    public String delBatch(@RequestBody List<Integer> orderids){
        if (orderedService.deleteBatch(orderids)){
            return "success";
        }else {
            return "error";
        }
    }

    /**
     * 获取食物集合
     * @return
     */
    @RequestMapping("/getFoodList")
    public List<Food> getFoodList(){
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.eq("visibility", "1");
        List<Food> list = foodService.list(wrapper);
      /*  for (Food food: list){
            System.out.println(food.toString());
        }*/
        return list;
    }

    /**
     * 获取部门下的所有员工信息
     * @param deptId
     * @return
     */
    @RequestMapping("/getEmpList/{deptId}")
    public List<Emp> getEmpList(@PathVariable int deptId){
        return empService.getEmpListWithDept(deptId);
    }

    /**
     * 点餐
     * @param orderedWithOdfd
     * @return
     */
    @RequestMapping("/ordering")
    public String ordering(@RequestBody OrderedWithOdfd orderedWithOdfd){
        boolean flag = orderedWithOdfdService.saveOrder(orderedWithOdfd);
        if (flag){
            return "success";
        }else {
            return "error";
        }
    }

    /**
     * 管理员确认
     * @param orderids
     * @return
     */
    @RequestMapping("/managerConfirm")
    public String managerConfirm(@RequestBody List<Integer> orderids){
        if (orderedService.managerConfirm(orderids)){
            return "success";
        }else {
            return "error";
        }
    }

    /**
     * 系统确认
     * @param orderids
     * @return
     */
    @RequestMapping("/systemConfirm")
    public String systemConfirm(@RequestBody List<Integer> orderids){
        if (orderedService.systemConfirm(orderids)){
            return "success";
        }else {
            return "error";
        }
    }

    /**
     * 根据条件获取订单信息
     * @param queryMap
     * @return
     */
    @RequestMapping("/getOrderWithAllList")
    public Map<String, Object> getOrderWithAllList(@RequestBody Map<String,String> queryMap){
        Map<String,Object> map = orderedService.getAllOrderWithAllList(queryMap);
        return map;
    }

    /**
     * 根据查询条件获取餐品集合
     * @param queryMap
     * @return
     */
    @RequestMapping("/getFoodListWithQuery")
    public Map<String, Object> getFoodListWithQuery(@RequestBody Map<String,String> queryMap){
        Map<String,Object> map = foodService.getAllFoodList(queryMap);
        return map;
    }

    /**
     * 新增或修改餐品信息
     * @param food
     * @return
     */
    @RequestMapping("/saveOrUpdateFood")
    public String saveOrUpdateFood(@RequestBody Food food){
        boolean flag = foodService.saveOrUpdate(food);
        if (flag){
            return "success";
        }else {
            return "error";
        }
    }

    @RequestMapping("/getOrderById/{oid}")
    public OrderWithAll getOrderById(@PathVariable Integer oid){
        OrderWithAll orderWithAll = orderedService.getOrderById(oid);
        return orderWithAll;
    }
}
